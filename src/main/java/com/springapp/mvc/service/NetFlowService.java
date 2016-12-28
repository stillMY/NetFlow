package com.springapp.mvc.service;

import com.springapp.mvc.dao.NetFlowDao;
import com.springapp.mvc.domain.NetFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */
@Service
public class NetFlowService {


    @Autowired
    private NetFlowDao dao;

    public List<NetFlow> getAllFlow() {

        return dao.getAllFlow();
    }
    //插入单个流
    public int insertSingleFlow(NetFlow flow){
        return dao.insertSingleFlow(flow);
    }
    //插入一个list
    public int insertFlowList(List<NetFlow> flowList){
        return dao.insertFlowList(flowList);
    }
}
