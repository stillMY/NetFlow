package com.springapp.mvc.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/27.
 */
@Data
public class Classifier {

    private List<Flow> out = new ArrayList<Flow>();
    private Map<String,List<Flow>> in = new HashMap<String, List<Flow>>();


    public String getName() {
        return null;
    }
}
