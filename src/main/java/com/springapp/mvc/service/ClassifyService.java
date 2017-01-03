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
    private int count;
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
            Map<String, Double> result = ClassifyUtil.classify(trains, flow);//进行分类
            List<String> com = compare(key, result);
            if (com.size() == 0) {
                //out
                classifier.getOut().add(flow);
            } else if (com.size() == 1) {
                //如果是分出一个类
                if(flow.getType().equals(com.get(0))){
                    count++;
                }
                flow.setType(com.get(0));
                classifier.getIn().add(flow);
            } else if (com.size() > 1) {
                //分出多个类
                //1.先把原分类器取出来
                //2.复制多个副本，每个里面加上分类结果
                //3.删除原来分类器，加入新的分类器
                for (String type : com) {
                    Classifier newClassifier = new Classifier();
                    flow.setType(type);
                    if(flow.getType().equals(type)){
                        count++;
                    }
                    newClassifier.getIn().addAll(classifier.getIn());
                    newClassifier.getIn().add(flow);
                    newClassifier.getOut().addAll(classifier.getOut());
                    String classfierName = NameUtil.getName();
                    classifiers.put(classfierName, newClassifier);
                    thresholds.put(classfierName,thresholds.get(key));
                }
                classifiers.remove(key);
                thresholds.remove(key);
            }
        }

        System.out.println("分类器数量：" + classifiers.size());
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
        for (String key : classifiers.keySet()) {
            trains = classifiers.get(key).getIn();
        }
    }

    //从数据库读取所有流量
    private Map<String, Classifier> loadClassifier() {
        //从csv读，没数据库情况下
        List<Flow> flows = loadData();
        Map<String, Classifier> map = new HashMap<String, Classifier>();
        Classifier classifier = new Classifier();
        for (Flow flow : flows) {
            classifier.getIn().add(flow);
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
