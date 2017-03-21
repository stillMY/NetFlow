package com.springapp.mvc.cluster;

import com.springapp.mvc.util.Constant;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Jason on 2016/4/17.
 */
public class Point implements Serializable{
    private double x;
    private double y;

    public double[] getFeature() {
        return feature;
    }

    public void setFeature(double[] feature) {
        this.feature = feature;
    }

    private double[] feature;
    private boolean isVisit;
    private int cluster;
    private boolean isNoised;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Point(){

    }
    public Point(double x, double y) {
        this.x = x;

        this.y = y;
        this.isVisit = false;
        this.cluster = 0;
        this.isNoised = false;
        this.feature = new double[19];
    }
    public Point(double[] feature){
        this.isVisit = false;
        this.cluster = 0;
        this.isNoised = false;
        this.feature = feature;
    }
    public double getDistance(Point point) {
        double sum = 0;
        double k;
        for (int i = 0; i < Constant.FNUM; i++) {
            //k=exp(0- ( a(i)-b(i) )^2/(2*omg^2) );
            k = Math.exp(- Math.pow((this.feature[i] - point.feature[i]), 2)
                    / 2 * Math.pow(Constant.omg, 2));
            sum += k;
        }
        return sum / Constant.FNUM;
    }

    public double getDistance2(Point point) {
        double sum = 0;
        for(int i=0;i<feature.length;i++){
            sum += Math.pow(this.feature[i]-point.feature[i],2);
        }

        return Math.sqrt(sum);
    }

//    public double getDistance(Point point) {
//        return Math.sqrt((x-point.x)*(x-point.x)+(y-point.y)*(y-point.y));
//    }


    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setVisit(boolean isVisit) {
        this.isVisit = isVisit;
    }

    public boolean getVisit() {
        return isVisit;
    }

    public int getCluster() {
        return cluster;
    }

    public void setNoised(boolean isNoised) {
        this.isNoised = isNoised;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public boolean getNoised() {
        return this.isNoised;
    }

    @Override
    public String toString() {
        return "Point{" +
                "feature=" + Arrays.toString(feature) +
                ", cluster=" + cluster +
                ", isNoised=" + isNoised +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (Double.compare(point.x, x) != 0) return false;
        if (Double.compare(point.y, y) != 0) return false;
        if (!Arrays.equals(feature, point.feature)) return false;
        return !(type != null ? !type.equals(point.type) : point.type != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + Arrays.hashCode(feature);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
