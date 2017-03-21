package com.springapp.mvc.cluster;

import com.springapp.mvc.util.ClassifyUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
public class clu {


    public static void main(String[] args) {
        List<Point> points = Data.loadFlow();
        List<Point> out = new ArrayList<Point>();
        Map<String, Double> thresholds = new HashMap<String, Double>();//阈值
        Map<String, List<Point>> trainSet = new HashMap<String, List<Point>>();//聚类后保存在这，也有分类的
        Map<String, Integer> all = new HashMap<String, Integer>();
        Map<String, Integer> right = new HashMap<String, Integer>();


        while (points.size() > 0) {
            while (out.size() < 200 && points.size() > 0) {
                Point point = points.get(0);
                points.remove(0);

                if (thresholds.size() == 0) {
                    out.add(point);//初始时out里加进来200条
                } else {
                    //如果大于某类阈值，加到训练集中去---分类
                    String type = null;
                    for (String key : thresholds.keySet()) {
                        double dis = ClassifyUtil.getPointXCo(point, trainSet.get(key));
                        if (dis >= thresholds.get(key)) {//取最大的比较?
                            trainSet.get(key).add(point);
                            type = key;
                            //
                            add(all,key);
                            if(key.equals(point.getType())) {
                                add(right,key);
                            }
                            break;
                        }
                    }
                    //如果都不满足，加入异常流量集
                    if (type == null) {
                        out.add(point);
                    }
                }
            }
            //异常流量集数量到达200，进行聚类
            List<Point> dataSet = deepcopy(out);
            DBScan dbScan = new DBScan(0.9996, 7);
            dbScan.process(dataSet);

            for (Integer key : dbScan.getClusterMap().keySet()) {
                List<Point> clusterPoints = dbScan.getClusterMap().get(key);
                //打印聚类结果
                cal(clusterPoints);

                String label = getLabel(clusterPoints);//获取簇标签
                //如果训练集中已经有该类型则先取出来再加进去
                if (trainSet.containsKey(label)) {
                    trainSet.get(label).addAll(clusterPoints);
                } else {
                    trainSet.put(label, clusterPoints);
                    thresholds.put(label, ClassifyUtil.getThreshold(clusterPoints));//新建阈值
                }
                out.removeAll(clusterPoints);//从out中删除已聚出类的
            }
        }

        System.out.println();
        System.out.println("结果统计");
        //统计召回率
        for(String key : all.keySet()){
            System.out.println(key+" 的召回率："+right.get(key) * 1.0 / all.get(key));
        }
//        for (String key : trainSet.keySet()) {
//            List<Point> list = trainSet.get(key);
//            int num =0;
//            for(Point point : list){
//                if(point.getType().equals(key)){
//                    num++;
//                }
//            }
//
//            System.out.println(key+"的召回率："+num);
//        }
    }

    //统计一个List<Point>中各类型分布数量
    private static void cal(List<Point> points) {
        System.out.println("总数" + points.size());

        Map<String, Integer> countMap = new HashMap<String, Integer>();
        for (Point p : points) {
            add(countMap, p.getType());
        }
        for (String key : countMap.keySet()) {
            System.out.println("-------------");
            System.out.println(key + " " + countMap.get(key));
            //System.out.println(key + " " + countMap.get(key) * 1.0 / points.size());
        }
        System.out.println("************************");
    }

    private static void add(Map<String, Integer> countMap, String type) {
        if (countMap.get(type) == null) {
            countMap.put(type, 1);
        } else {
            countMap.put(type, countMap.get(type) + 1);
        }
    }

    private static String getLabel(List<Point> points) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        String label = null;
        int count = 0;
        for (Point point : points) {
            if (map.get(point.getType()) == null) {
                map.put(point.getType(), 1);
            } else {
                map.put(point.getType(), map.get(point.getType()) + 1);
            }

            if (map.get(point.getType()) > count) {
                count = map.get(point.getType());
                label = point.getType();
            }
        }

        return label;
    }

    public static List deepcopy(List src) {
        ByteArrayOutputStream byteout = new ByteArrayOutputStream();
        List dest = null;
        try {
            ObjectOutputStream out = new ObjectOutputStream(byteout);
            out.writeObject(src);
            ByteArrayInputStream bytein = new ByteArrayInputStream(byteout
                    .toByteArray());
            ObjectInputStream in = new ObjectInputStream(bytein);
            dest = (List) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return dest;

    }
}
