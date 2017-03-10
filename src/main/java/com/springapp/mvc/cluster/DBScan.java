package com.springapp.mvc.cluster;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2016/4/17.
 */
public class DBScan {
    private double radius;//半径
    private int minPts;//密度阈值

    public Map<Integer, List<Point>> getClusterMap() {
        return clusterMap;
    }

    private Map<Integer, List<Point>> clusterMap = new HashMap<Integer, List<Point>>();

    public DBScan(double radius, int minPts) {
        this.radius = radius;
        this.minPts = minPts;
    }

    public void process(List<Point> points) {
        int size = points.size();
        int idx = 0;
        int cluster = 1;
        int noiseNum = 0;
        while (idx < size) {
            Point p = points.get(idx++);//选择一个点
            if (!p.getVisit()) {
                p.setVisit(true);//设置访问过
                ArrayList<Point> adjacentPoints = getAdjacentPoints(p, points);
                //寻找p点半径内的点
                if (adjacentPoints != null && adjacentPoints.size() < minPts) {
                    p.setNoised(true);//小于密度 标记为噪声
                    noiseNum++;
                } else {
                    p.setCluster(cluster);
                    if (clusterMap.get(cluster) == null) {
                        List<Point> list = new ArrayList<Point>();
                        list.add(p);
                        clusterMap.put(cluster, list);
                    } else {
                        clusterMap.get(cluster).add(p);
                    }
                    for (int i = 0; i < adjacentPoints.size(); i++) {
                        Point adjacentPoint = adjacentPoints.get(i);
                        if (!adjacentPoint.getVisit()) {
                            adjacentPoint.setVisit(true);
                            //寻找p'点半径内的点
                            ArrayList<Point> adjacentAdjacentPoints = getAdjacentPoints(adjacentPoint, points);
                            //如果点个数大于密度，则把所有点加到p的集合里
                            if (adjacentAdjacentPoints != null && adjacentAdjacentPoints.size() >= minPts) {
                                for (Point pp : adjacentAdjacentPoints) {
                                    if (!adjacentPoints.contains(pp)) {
                                        adjacentPoints.add(pp);
                                    }
                                }
                            }
                        }
                        //如果没被加到过任何簇，则加到当前簇
                        if (adjacentPoint.getCluster() == 0) {
                            adjacentPoint.setCluster(cluster);
                            if (clusterMap.get(cluster) == null) {
                                List<Point> list = new ArrayList<Point>();
                                list.add(adjacentPoint);
                                clusterMap.put(cluster, list);
                            } else {
                                clusterMap.get(cluster).add(adjacentPoint);
                            }
                            //如果是噪声点，则标记为非噪声
                            if (adjacentPoint.getNoised()) {
                                adjacentPoint.setNoised(false);
                                noiseNum--;
                            }
                        }
                    }
                    cluster++;
                }
            }
//            if (idx%1000==0) {
//                System.out.println(idx);
//            }
        }
        System.out.println("cluster:" + (cluster - 1));
        System.out.println("噪声：" + noiseNum);
    }

    private ArrayList<Point> getAdjacentPoints(Point centerPoint, List<Point> points) {
        ArrayList<Point> adjacentPoints = new ArrayList<Point>();
        for (Point p : points) {
            //include centerPoint itself
            double distance = centerPoint.getDistance(p);
//            if(centerPoint.getType().equals("SERVICES") && p.getType().equals("FTP-PASV") && distance>radius) {
//                 System.out.println(distance);
//            }
            if (distance > radius) {
                adjacentPoints.add(p);
            }
        }
        return adjacentPoints;
    }

}
