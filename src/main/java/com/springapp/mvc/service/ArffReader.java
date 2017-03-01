package com.springapp.mvc.service;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/2/23.
 */
public class ArffReader {


    public static void main(String[] args) {
        ArffLoader loader = new ArffLoader();
        try {
            loader.setFile(new File("c:/t.arff"));
            Instances instance = loader.getDataSet();
            Attribute attribute = instance.attribute("class");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
