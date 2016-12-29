package com.springapp.mvc.domain;

import lombok.Data;

/**
 * Created by Administrator on 2016/12/22.
 */
@Data
public class NetFlow {

    private int id;
    private String type;
    private double d1;
    private double d2;
    private double d3;
    private double d4;
    private double d5;
    private double d6;
    private double d7;
    private double d8;
    private double d9;
    private double d10;
    private double d11;
    private double d12;
    private double d13;
    private double d14;
    private double d15;
    private double d16;
    private double d17;
    private double d18;
    private double d19;

    public Flow changeToFlow(){
        Flow flow = new Flow();
        flow.setId(this.id);
        flow.setType(this.type);
        double[] d= new double[19];
        d[0] = d1;
        d[1] = d2;
        d[2] = d3;
        d[3] = d4;
        d[4] = d5;
        d[5] = d6;
        d[6] = d7;
        d[7] = d8;
        d[8] = d9;
        d[9] = d10;
        d[10] = d11;
        d[11] = d12;
        d[12] = d13;
        d[13] = d14;
        d[14] = d15;
        d[15] = d16;
        d[16] = d17;
        d[17] = d18;
        d[18] = d19;
        return flow;
    }
}
