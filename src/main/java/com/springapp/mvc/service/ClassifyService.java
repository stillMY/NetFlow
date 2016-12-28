package com.springapp.mvc.service;

import com.springapp.mvc.domain.Classifier;
import com.springapp.mvc.domain.Flow;
import com.springapp.mvc.util.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/27.
 */
@Service
public class ClassifyService {

    private Map<String, Classifier> classifiers;
    private Map<String, Integer> thresholds;
    private long pointer;
    private long fileLen;

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
        try {

            FileInputStream inputStream = new FileInputStream(Constant.dir);
            fileLen = inputStream.available();
        } catch (Exception e) {
        }
    }

    private Map<String, Classifier> loadClassifier() {
        return null;

    }

    private Map<String, Integer> loadThresholds() {

        return null;
    }

    public List<Flow> loadData(final String dir) {
        return null;
    }


}
