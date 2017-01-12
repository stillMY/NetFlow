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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Administrator on 2016/12/27.
 */
@Service
public class ClassifyService {

    private Map<String, Classifier> classifiers;
    private Map<String, Map<String, Double>> thresholds;
    private List<Flow> trains;
    private String[] CLASS = {"WWW", "FTP-CONTROL", "DATABASE"};
    private int cNum = 10;
    @Autowired
    private NetFlowDao dao;

    //提供接口，实时显示阈值
    public Map getThreshold() {
        return thresholds;
    }

    public void classify(Flow flow) {
        HashSet<String> keySet = new HashSet();
        keySet.addAll(classifiers.keySet());
        for (String key : keySet) {
            Classifier classifier = classifiers.get(key);
            Map<String, Double> result = ClassifyUtil.getXCo(classifier.getIn(), flow);//进行分类
            List<String> com = compare(key, result);
            if (com.size() == 0) {
                //out
                classifier.getOut().add(flow);
            } else if (com.size() == 1) {
                flow.setDiff(false);

                Map<String, String> map = new HashMap<String, String>();
                map.put(key, com.get(0));
                flow.setTypeMap(map);

                classifier.getIn().get(com.get(0)).add(flow);
//                thresholds.put(key, ClassifyUtil.getThreshold(classifier.getIn()));//更新阈值
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
                    thresholds.put(classfierName, ClassifyUtil.getThreshold(newClassifier.getIn()));
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
        if (classifiers.size() >= cNum) {
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
                sel.setDiff(false);
                for (String key : sel.getTypeMap().keySet()) {
                    if (!sel.getType().equals(sel.getTypeMap().get(key))) {
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
                if(flows.get(i).isDiff()){
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
            if(d>0){
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
    }

    //从数据库读取所有流量
    private Map<String, Classifier> loadClassifier() {
        //从csv读，没数据库情况下
        List<Flow> flows = loadData();
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

    public List<Flow> loadData() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(Constant.TRAINFILE));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            List<Flow> list = new ArrayList<Flow>();
            while ((line = reader.readLine()) != null) {
                list.add(changeToFlow(line));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Flow changeToFlow(String line) {
        String[] arr = line.split(",");
        double[] d = new double[19];
        for (int i = 0; i < Constant.FNUM; i++) {
            d[i] = Double.parseDouble(arr[i]);
        }
        Flow flow = new Flow();
        flow.setD(d);
        flow.setType(arr[19]);
        return flow;
    }


}
