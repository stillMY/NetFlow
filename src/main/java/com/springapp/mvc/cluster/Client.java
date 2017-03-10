package com.springapp.mvc.cluster;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2016/4/17.
 */
public class Client {


    public static void main(String[] args) {
        //ArrayList<Point> points = Data.generateSinData(200);
        //DBScan dbScan = new DBScan(0.6,4);
        //ArrayList<Point> points = Data.generateSpecialData();
        boolean f = false;

        if (f) {
            double radius = 0.9991;
            int minPts = 10;
            for (int i = minPts; i < 150; i += 10) {
                for (double j = radius; j < 0.9998; j += 0.0002) {
                    List<Point> points = Data.loadFlow();
                    DBScan dbScan = new DBScan(j, i);
                    dbScan.process(points);
                    System.out.println("密度:" + i + "...半径：" + j);
                }
            }
        } else {
            List<Point> points = Data.loadFlow();
//            DBScan dbScan = new DBScan(0.99928, 50);
            DBScan dbScan = new DBScan(0.9993, 40);
            dbScan.process(points);
//        for (Point p : points) {
//            System.out.println(p);
//        }

            for (Integer key : dbScan.getClusterMap().keySet()) {
                System.out.print(key);
                cal(dbScan.getClusterMap().get(key));
                System.out.println();
            }
        }
//        Data.writeData(points,"data.txt");
    }

    private static void cal(List<Point> points) {
        System.out.print(" 总数" + points.size());
        System.out.println();
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        for (Point p : points) {
            add(countMap, p.getType());
        }
        for (String key : countMap.keySet()) {
            System.out.println(key + " " + countMap.get(key) * 1.0 / points.size());
        }
    }

    private static void add(Map<String, Integer> countMap, String type) {
        if (countMap.get(type) == null) {
            countMap.put(type, 1);
        } else {
            countMap.put(type, countMap.get(type) + 1);
        }
    }

}
