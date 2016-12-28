package com.springapp.mvc.service;

import com.springapp.mvc.domain.Classifier;
import com.springapp.mvc.domain.NetFlow;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/27.
 */
@Service
public class ClassifyService {

    private Map<String, Classifier> classifiers;
    private Map<String, Integer> thresholds;

    //提供接口，实时显示阈值
    public Map getThreshold() {
        return thresholds;
    }

    public void classify(NetFlow flow) {

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

    private Map<String, Classifier> loadClassifier() {
        return null;

    }

    private Map<String, Integer> loadThresholds() {

        return null;
    }
}
