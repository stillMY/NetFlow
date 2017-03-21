package com.springapp.mvc.cluster;

import com.springapp.mvc.util.Constant;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Still on 2017/3/10.
 */
public class l {

    public static void main(String[] args) {
        List<Point> points = Data.loadFlow();//读取全部文件并随机打乱
        List<Point> out = new ArrayList<Point>();
        //还未结束
        Map<String, List<Point>> map = new HashMap<String, List<Point>>();
        while (points.size() > 0) {
            int n = out.size();
            int m = Constant.Onum - n;
            m = points.size() > m ? m : points.size();
            //将out中流量数量补足200,out+=points的前m个
            for (int i = 0; i < m; i++) {
                out.add(points.get(i));
            }
            //从points中删除
            for (int i = 0; i < m; i++) {
                points.remove(0);
            }

            //查看此时out中类型分布
//            cal(out);
            List<Point> dataSet = deepcopy(out);
            //对out做聚类
            DBScan dbScan = new DBScan(0.9996, 7);
            dbScan.process(dataSet);
            System.out.println();
            //把聚类结果每个簇保存一个CSV并从OUT中删除
            for (Integer key : dbScan.getClusterMap().keySet()) {
                List<Point> l = dbScan.getClusterMap().get(key);
//                csvWrite(l,key);
                String label = getLabel(l);//获取一个簇标签

                if (map.get(label) == null) {//如果已知类型里没有，直接加进去
                    map.put(label, l);
                } else {
                    map.get(label).addAll(l);//如果有，添加到原有的
                }
                //从out中删除已经聚出类的
                out.removeAll(l);
            }
        }

        System.out.println("结果统计");
        for (String key : map.keySet()) {
            System.out.print(key);
            cal(map.get(key));
            System.out.println();
        }

//            DBScan dbScan = new DBScan(0.99928, 50);

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

    private static void csvWrite(List<Point> list, Integer key) {
        //String head = "d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12,d13,d14,d15,d16,d17,d18,d19";
        String name = "簇" + key;

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Still\\Desktop\\我的硕士论文实验数据\\第三章聚类实验\\" + name + ".csv"));
            for (int i = 0; i < list.size(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < Constant.FNUM; j++) {
                    sb.append(list.get(i).getFeature()[j]);
                    sb.append(",");
                }
                sb.append(list.get(i).getType());
                sb.append("\r\n");
                bw.write(sb.toString());
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //统计一个List<Point>中各类型分布数量
    private static void cal(List<Point> points) {
        System.out.print("总数" + points.size());
        System.out.println();
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        for (Point p : points) {
            add(countMap, p.getType());
        }
        for (String key : countMap.keySet()) {
            System.out.println(key + " " + countMap.get(key));
            //System.out.println(key + " " + countMap.get(key) * 1.0 / points.size());
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
