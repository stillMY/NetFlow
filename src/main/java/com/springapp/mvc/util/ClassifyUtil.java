package com.springapp.mvc.util;

import Tx.Tx;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.springapp.mvc.domain.Flow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/29.
 */
public class ClassifyUtil {
    private static String[] CLASS = {"WWW", "FTP-CONTROL", "DATABASE"};
    private static Tx tx = null;
    public static Map<String, Double> classify(List<Flow> trains, Flow flow) {
        double[] d = flow.getD();
        try {
            if(tx == null) {
                tx = new Tx();
            }
            Object[] result = tx.getXCo(1,d, Constant.NCLASS,getDoubleArr(trains),Constant.NNN,Constant.FNUM);
            double[] featureVec = ((MWNumericArray) result[0]).getDoubleData();
            Map<String, Double> map = new HashMap<String, Double>();
            for (int i = 0; i < Constant.NCLASS; i++) {
                map.put(CLASS[i], featureVec[i]);
            }
            return map;
        } catch (MWException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取所有类的阈值
     * @param flows
     * @return
     * @TODO
     */
    public static Map<String, Double> getThreshold(List<Flow> flows) {
        double[][] trains = getDoubleArr(flows);
        double[] threshold = new double[Constant.NCLASS];
        MWNumericArray input = new MWNumericArray(trains, MWClassID.DOUBLE);
        try {
            Tx tx = new Tx();
            Object[] result = tx.getTh(1, Constant.NCLASS, Constant.NNN, Constant.FNUM, input);
            threshold = ((MWNumericArray) result[0]).getDoubleData();
        } catch (MWException e) {
            e.printStackTrace();
        }
        Map<String, Double> map = new HashMap<String, Double>();
        for (int i = 0; i < Constant.NCLASS; i++) {
            map.put(CLASS[i], threshold[i]);
        }
        return map;
    }

    private static double[][] getDoubleArr(List<Flow> flows) {
        double[][] dd = new double[flows.size()][];
        for (int i = 0; i < flows.size(); i++) {
            double[] d = flows.get(i).getD();
            dd[i] = d;
        }
        return dd;
    }

}
