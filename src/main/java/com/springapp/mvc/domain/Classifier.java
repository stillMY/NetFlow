package com.springapp.mvc.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */
@Data
public class Classifier {

    private List<Flow> out;
    private List<Flow> in;

    public void classify(Flow flow) {

    }
}