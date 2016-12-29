package com.springapp.mvc.service;

import com.springapp.mvc.dao.NetFlowDao;
import com.springapp.mvc.domain.Classifier;
import com.springapp.mvc.domain.Flow;
import com.springapp.mvc.domain.NetFlow;
import com.springapp.mvc.util.Constant;
import com.springapp.mvc.util.NameUtil;
import com.springapp.mvc.util.ThresholdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/27.
 */
@Service
public class ClassifyService {

    private Map<String, Classifier> classifiers;
    private Map<String, Double> thresholds;
    @Autowired
    private NetFlowDao dao;

    //提供接口，实时显示阈值
    public Map getThreshold() {
        return thresholds;
    }

    public void classify(Flow flow) {

        for (String key : classifiers.keySet()) {
            Classifier classifier = classifiers.get(key);
            classifier.classify(flow);//进行分类
            if (1 == 1) {
                //out
                classifier.getOut().add(flow);
            } else if (2 == 2) {
                //如果是分出一个类
                flow.setType("1");
                classifier.getIn().add(flow);
            } else if (3 == 3) {
                //分出多个类
                //1.先把原分类器取出来
                //2.复制多个副本，每个里面加上分类结果
                //3.删除原来分类器，加入新的分类器
            }
        }

    }


    @PostConstruct
    private void init() {
        //初始时，把分类器加载进来
        classifiers = loadClassifier();
        thresholds = loadThresholds();
    }

    //从数据库读取所有流量
    private Map<String, Classifier> loadClassifier() {
        List<NetFlow> netFlows = dao.getAllFlow();

        Map<String,Classifier> map = new HashMap<String, Classifier>();
        Classifier classifier = new Classifier();
        for(NetFlow netFlow:netFlows){
            classifier.getIn().add(netFlow.changeToFlow());
        }
        map.put(NameUtil.getName(),classifier);
        return map;
    }

    //计算所有分类器的阈值
    private Map<String, Double> loadThresholds() {
        Map<String,Double> map = new HashMap<String, Double>();
        for(String key : classifiers.keySet()){
            map.put(key, ThresholdUtil.getThreshold(classifiers.get(key).getIn()));
        }
        return map;
    }

    public List<Flow> loadData(final String dir) {
        return null;
    }


}
