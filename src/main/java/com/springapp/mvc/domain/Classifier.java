package com.springapp.mvc.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */
@Data
public class Classifier {

    private List<NetFlow> out;
    private List<NetFlow> in;

    public void classify(NetFlow flow) {

    }
}
