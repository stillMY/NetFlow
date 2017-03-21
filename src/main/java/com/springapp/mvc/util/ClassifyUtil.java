package com.springapp.mvc.util;

import Tx.Tx;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import com.springapp.mvc.cluster.Point;
import com.springapp.mvc.domain.Flow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/1/12.
 */
public class ClassifyUtil {


    private static Tx tx;

    private static Tx getInstance() {
        if (tx == null) {
            try {
                tx = new Tx();
            } catch (MWException e) {
                e.printStackTrace();
            }
        }
        return tx;
    }

    /**
     * 获取与其他类的互相关熵
     */
    public static Map<String, Double> getXCo(Map<String, List<Flow>> data, Flow flow) {
        tx = getInstance();
        Map<String, Double> map = new HashMap<String, Double>();
        for (String key : data.keySet()) {
            List<Flow> flows = data.get(key);
            try {
                Object[] result = tx.getFeature(1, flow.getD(), getDoubleArr(flows), flows.size(), Constant.FNUM);
                map.put(key, Double.parseDouble(result[0].toString()));
            } catch (MWException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 获取阈值
     * 对每个类型进行遍历
     *
     * @param data
     * @return
     */
    public static Map<String, Double> getThreshold(Map<String, List<Flow>> data) {
        tx = getInstance();
        Map<String, Double> thresholds = new HashMap<String, Double>();
        for (String key : data.keySet()) {
            List<Flow> flows = data.get(key);
            double[][] d = getDoubleArr(flows);
            MWNumericArray input = new MWNumericArray(d, MWClassID.DOUBLE);
            try {
                Object[] result = tx.getThreshold(1, flows.size(), Constant.FNUM, input);
                thresholds.put(key, Double.parseDouble(result[0].toString()));
            } catch (MWException e) {
                e.printStackTrace();
            }
        }
        return thresholds;
    }

    public static Double getThreshold(List<Point> data) {
        tx = getInstance();
        double[][] d = getDoubleArr2(data);
        MWNumericArray input = new MWNumericArray(d, MWClassID.DOUBLE);
        try {
            Object[] result = tx.getThreshold(1, data.size(), Constant.FNUM, input);
            return Double.parseDouble(result[0].toString());
        } catch (MWException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    private static double[][] getDoubleArr(List<Flow> flows) {
        double[][] dd = new double[flows.size()][];
        for (int i = 0; i < flows.size(); i++) {
            double[] d = flows.get(i).getD();
            dd[i] = d;
        }
        return dd;
    }

    private static double[][] getDoubleArr2(List<Point> flows) {
        double[][] dd = new double[flows.size()][];
        for (int i = 0; i < flows.size(); i++) {
            double[] d = flows.get(i).getFeature();
            dd[i] = d;
        }
        return dd;
    }

    public static double getPointXCo(Point point, List<Point> points) {
        try {
            Object[] result = tx.getFeature(1, point.getFeature(), getDoubleArr2(points), points.size(), Constant.FNUM);
            return Double.parseDouble(result[0].toString());
        } catch (MWException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
