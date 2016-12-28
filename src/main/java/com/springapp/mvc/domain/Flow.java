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
        return null;
    }
}
