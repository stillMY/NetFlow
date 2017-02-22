package com.springapp.mvc.service;

import com.springapp.mvc.dao.NetFlowDao;
import com.springapp.mvc.domain.Classifier;
import com.springapp.mvc.domain.Flow;
import com.springapp.mvc.util.ClassifyUtil;
import com.springapp.mvc.util.Constant;
import com.springapp.mvc.util.NameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2016/12/27.
 */
@Service
public class ClassifyService {

    private Map<String, Classifier> classifiers;
    private Map<String, Map<String, Double>> thresholds;
    private List<Flow> trains;
    private String[] CLASS = {"WWW", "FTP-CONTROL", "DATABASE", "ATTACK", "SERVICES", "P2P", "MAIL", "FTP-PASV", "FTP-DATA"};
    private int cNum = 10;
    @Autowired
    private NetFlowDao dao;

    private Map<String, Integer> totalNum = new HashMap<String, Integer>();
    private Map<String, Integer> rightNum = new HashMap<String, Integer>();

    //提供接口，实时显示阈值
    public Map getThreshold() {
        return thresholds;
    }
    int num = 0;
    public void classify(Flow flow) {
        num++;
        if (totalNum.get(flow.getType()) == null) {
            totalNum.put(flow.getType(), 1);
        } else {
            totalNum.put(flow.getType(), totalNum.get(flow.getType()) + 1);
        }

        HashSet<String> keySet = new HashSet();
        keySet.addAll(classifiers.keySet());

        boolean flag = false;

            for (String key : keySet) {
            Classifier classifier = classifiers.get(key);
            Map<String, Double> result = ClassifyUtil.getXCo(classifier.getIn(), flow);//进行分类
            List<String> com = compare(key, result);
            if (com.size() == 0) {
                //out
                classifier.getOut().add(flow);
//                if (!flow.getType().equals(CLASS[0]) && !flow.getType().equals(CLASS[1]) && !flow.getType().equals(CLASS[2])) {
//                    rightNum++;
//                }
            } else if (com.size() == 1) {
                flow.setDiff(false);

                if (!flag && com.get(0).equals(flow.getType())) {
                    flag = true;
                    if (rightNum.get(flow.getType()) == null) {
                        rightNum.put(flow.getType(), 1);
                    } else {
                        rightNum.put(flow.getType(), rightNum.get(flow.getType()) + 1);
                    }
                }

                Map<String, String> map = new HashMap<String, String>();
                map.put(key, com.get(0));
                flow.setTypeMap(map);

                classifier.getIn().get(com.get(0)).add(flow);
                if (num % 50 == 0) {
                    thresholds.put(key, ClassifyUtil.getThreshold(classifier.getIn()));//更新阈值
                }

            } else if (com.size() > 1) {
                //分出多个类
                //1.先把原分类器取出来
                //2.复制多个副本，每个里面加上分类结果
                //3.删除原来分类器，加入新的分类器
                Map<String, String> map = new HashMap<String, String>();
                List<String> names = new ArrayList<String>();
                flow.setDiff(true);
                for (String type : com) {
                    Classifier newClassifier = new Classifier();
                    String classfierName = NameUtil.getName();
                    map.put(classfierName, type);

                    newClassifier.getIn().putAll(classifier.getIn());
                    newClassifier.getIn().get(type).add(flow);
                    newClassifier.getOut().addAll(classifier.getOut());

                    names.add(classfierName);

                    classifiers.put(classfierName, newClassifier);
                    if (num % 50 == 0) {
                        thresholds.put(classfierName, ClassifyUtil.getThreshold(classifier.getIn()));//更新阈值
//                    }
//                    if (num % 50 ==0) {
//                        thresholds.get(key).put(type, ClassifyUtil.getThreshold(newClassifier.getIn().get(type)));
//                        thresholds.put(classfierName, thresholds.get(key));
                    } else {
                        thresholds.put(classfierName, thresholds.get(key));
                    }
                }
                flow.setTypeMap(map);

                //对原来的流量，更改他们的分类器名
                for (String type : classifier.getIn().keySet()) {
                    List<Flow> flows = classifier.getIn().get(type);
                    for (int i = Constant.NNN; i < flows.size(); i++) {
                        Flow f = flows.get(i);
                        if (f.isDiff()) {
                            if (f.getTypeMap().containsKey(key)) {
                                String type1 = f.getTypeMap().get(key);
                                for (String name : names) {
                                    f.getTypeMap().remove(key);
                                    f.getTypeMap().put(name, type1);
                                }
                            }
                        }
                    }
                }

                classifiers.remove(key);
                thresholds.remove(key);
            }
        }

        //如果超过10个分类器，减枝
        //有分歧最大的那个流，如果分类器 分类错误 删去
        if (classifiers.size() >= 4) {
            while (classifiers.size() > 1) {
                List<Flow> diffs = getAllDiff();
                double max = Integer.MIN_VALUE;
                Flow sel = null;
                for (Flow flow1 : diffs) {
                    double entropy = getEntropy(flow1);
                    if (entropy > max) {
                        max = entropy;
                        sel = flow1;
                    }
                }
                if (sel == null) {
                    if (classifiers.size() > 1) {
                        String key = (String) classifiers.keySet().toArray()[0];
                        Classifier classifier = classifiers.get(key);
                        classifiers = new HashMap<String, Classifier>();
                        classifiers.put(key, classifier);
                    }
                    break;
                }
                sel.setDiff(false);
                for (String key : sel.getTypeMap().keySet()) {
                    if (!sel.getType().equals(sel.getTypeMap().get(key)) && classifiers.size() > 1) {
                        classifiers.remove(key);
                    }
                }
            }
        }

        System.out.println("分类器数量：" + classifiers.size());
    }

    private List<Flow> getAllDiff() {
        List<Flow> list = new ArrayList<Flow>();

        Map<String, List<Flow>> in = classifiers.get(classifiers.keySet().toArray()[0]).getIn();

        for (String key : in.keySet()) {
            List<Flow> flows = in.get(key);
            for (int i = Constant.NNN; i < flows.size(); i++) {
                if (flows.get(i).isDiff()) {
                    list.add(flows.get(i));
                }
            }
        }

        return list;
    }

    //获取信息熵
    private double getEntropy(Flow flow) {
        double sum = 0;
        for (String c : CLASS) {
            int d = 0;
            for (String key : flow.getTypeMap().keySet()) {
                String type = flow.getTypeMap().get(key);
                if (c.equals(type)) {
                    d++;
                }
            }
            double a = 0;
            if (d > 0) {
                a = -d * Math.log(d);
            }
            sum += a;
        }

        return sum;
    }

    private List<String> compare(String classfierId, Map<String, Double> result) {
        List<String> list = new ArrayList<String>();
        for (String key : thresholds.get(classfierId).keySet()) {
            if (result.get(key) > thresholds.get(classfierId).get(key)) {
                list.add(key);
            }
        }
        return list;
    }


    @PostConstruct
    private void init() {
        //初始时，把分类器加载进来
        classifiers = loadClassifier();
        thresholds = loadThresholds();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int c = 0;
                while (true) {
                    if (totalNum.size() > 0) {
                        c += 5;
                        System.out.println("第" + c + "秒");
                        print();
                    }

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            private void print() {
                cal(classifiers.get(classifiers.keySet().toArray()[0]));
//                for (String key : totalNum.keySet()) {
//                    int right = rightNum.get(key) == null ? 0 : rightNum.get(key);
//                    System.out.println(key + ":" + right * 1.00 / totalNum.get(key));
//                }
            }
        }).start();
    }

    //从数据库读取所有流量
    private Map<String, Classifier> loadClassifier() {
        //从csv读，没数据库情况下
        List<Flow> flows = new ArrayList<Flow>();
        flows.addAll(loadData("C:\\Users\\Administrator\\Documents\\Tencent Files\\971266976\\FileRecv\\我的所有类实验数据/WWW_1500.csv"));
        flows.addAll(loadData("C:\\Users\\Administrator\\Documents\\Tencent Files\\971266976\\FileRecv\\我的所有类实验数据/SERVICES_1500.csv"));
        flows.addAll(loadData("C:\\Users\\Administrator\\Documents\\Tencent Files\\971266976\\FileRecv\\我的所有类实验数据/P2P_1500.csv"));
        flows.addAll(loadData("C:\\Users\\Administrator\\Documents\\Tencent Files\\971266976\\FileRecv\\我的所有类实验数据/MAIL_1500.csv"));
        flows.addAll(loadData("C:\\Users\\Administrator\\Documents\\Tencent Files\\971266976\\FileRecv\\我的所有类实验数据/FTP-PASV_1500.csv"));
        flows.addAll(loadData("C:\\Users\\Administrator\\Documents\\Tencent Files\\971266976\\FileRecv\\我的所有类实验数据/FTP-DATA_1500.csv"));
        flows.addAll(loadData("C:\\Users\\Administrator\\Documents\\Tencent Files\\971266976\\FileRecv\\我的所有类实验数据/FTP-CONTROL_1500.csv"));
        flows.addAll(loadData("C:\\Users\\Administrator\\Documents\\Tencent Files\\971266976\\FileRecv\\我的所有类实验数据/DATABASE_1500.csv"));
        flows.addAll(loadData("C:\\Users\\Administrator\\Documents\\Tencent Files\\971266976\\FileRecv\\我的所有类实验数据/ATTACK_1500.csv"));

        Map<String, Classifier> map = new HashMap<String, Classifier>();
        Classifier classifier = new Classifier();
        for (Flow flow : flows) {
            List<Flow> list = classifier.getIn().get(flow.getType());

            if (list == null) {
                list = new ArrayList<Flow>();
            }

            list.add(flow);
            classifier.getIn().put(flow.getType(), list);
        }
        map.put(NameUtil.getName(), classifier);
        return map;

//        List<NetFlow> netFlows = dao.getAllFlow();
//
//        Map<String, Classifier> map = new HashMap<String, Classifier>();
//        Classifier classifier = new Classifier();
//        for (NetFlow netFlow : netFlows) {
//            classifier.getIn().add(netFlow.changeToFlow());
//        }
//        map.put(NameUtil.getName(), classifier);
//        return map;
    }

    //计算所有分类器的阈值
    private Map<String, Map<String, Double>> loadThresholds() {
        Map<String, Map<String, Double>> map = new HashMap<String, Map<String, Double>>();
        for (String key : classifiers.keySet()) {
            map.put(key, ClassifyUtil.getThreshold(classifiers.get(key).getIn()));
        }
        return map;
    }

    public List<Flow> loadData(String fileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(fileName));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            List<Flow> list = new ArrayList<Flow>();
            int count = 1;
            while ((line = reader.readLine()) != null) {
                list.add(changeToFlow(line, fileName.split("_")[0].split("/")[1]));
                count++;
                if (count > Constant.TRAINNUM) break;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Flow changeToFlow(String line, String type) {
        String[] arr = line.split(",");
        double[] d = new double[19];
        for (int i = 0; i < Constant.FNUM; i++) {
            d[i] = Double.parseDouble(arr[i]);
        }
        Flow flow = new Flow();
        flow.setD(d);
        flow.setType(type);
        return flow;
    }

    private void cal(Classifier classifier) {
        Map<String, Integer> m1 = new HashMap<String, Integer>();
        for (Flow flow : classifier.getOut()) {
            addMap(m1, flow.getType());
        }
        Map<String, Integer> m2 = new HashMap<String, Integer>();
        for (String key : classifier.getIn().keySet()) {
            List list = classifier.getIn().get(key);
            for (int i = Constant.TRAINNUM; i < list.size(); i++) {
                Flow flow = (Flow) list.get(i);
                if (flow.getType().equals(key)) {
                    addMap(m2, key);
                    addMap(m1, key);
                }

            }
        }
        int total = 0;
        int right1 = 0;
        for (String key : m1.keySet()) {
            int right = m2.get(key) == null ? 0 : m2.get(key);
            right1 += right;
            total += m1.get(key);
            System.out.println(key + ":" + right * 1.00 / m1.get(key));
        }
        System.out.println("总准确率：" + right1 * 1.00 / total);
    }

    private void addMap(Map<String, Integer> map, String key) {
        if (map.get(key) == null) {
            map.put(key, 1);
        } else {
            map.put(key, map.get(key) + 1);
        }
    }

    public void saveResult() {
        Classifier classifier = classifiers.get(classifiers.keySet().toArray()[0]);

        Map<String, Integer> m1 = new HashMap<String, Integer>();
        for (Flow flow : classifier.getOut()) {
            addMap(m1, flow.getType());
        }
        Map<String, Integer> m2 = new HashMap<String, Integer>();
        for (String key : classifier.getIn().keySet()) {
            List list = classifier.getIn().get(key);
            for (int i = Constant.TRAINNUM; i < list.size(); i++) {
                Flow flow = (Flow) list.get(i);
                if (flow.getType().equals(key)) {
                    addMap(m2, key);
                    addMap(m1, key);
                }

            }
        }
        int total = 0;
        int right1 = 0;
        try {
            FileOutputStream outputStream = new FileOutputStream("c:/result.txt",true);
            outputStream.write((Constant.TRAINNUM+"\r\n").getBytes());
            for (String key : m1.keySet()) {
                int right = m2.get(key) == null ? 0 : m2.get(key);
                right1 += right;
                total += m1.get(key);
//                System.out.println(key + ":" + right * 1.00 / m1.get(key));
                String s = key + ":" + right * 1.00 / m1.get(key)+"\r\n";
                outputStream.write(s.getBytes());
            }
            outputStream.write(("总准确率：" + right1 * 1.00 / total+"\r\n").getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("写文件完成。");
    }
}
