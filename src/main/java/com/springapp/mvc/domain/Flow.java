package com.springapp.mvc.domain;

import lombok.Data;

/**
 * Created by Administrator on 2016/12/28.
 */
@Data
public class Flow {

    private int id;
    private double[] d;
    private String type;

    public NetFlow changeToNetFlow(){
        NetFlow netFlow = new NetFlow();
        netFlow.setId(this.id);
        netFlow.setType(this.type);
        netFlow.setD1(d[0]);
        netFlow.setD2(d[1]);
        netFlow.setD3(d[2]);
        netFlow.setD4(d[3]);
        netFlow.setD5(d[4]);
        netFlow.setD6(d[5]);
        netFlow.setD7(d[6]);
        netFlow.setD8(d[7]);
        netFlow.setD9(d[8]);
        netFlow.setD10(d[9]);
        netFlow.setD11(d[10]);
        netFlow.setD12(d[11]);
        netFlow.setD13(d[12]);
        netFlow.setD14(d[13]);
        netFlow.setD15(d[14]);
        netFlow.setD16(d[15]);
        netFlow.setD17(d[16]);
        netFlow.setD18(d[17]);
        netFlow.setD19(d[18]);
        return netFlow;
    }
}
