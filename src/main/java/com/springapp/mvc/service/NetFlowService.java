package com.springapp.mvc.service;

import com.springapp.mvc.dao.NetFlowDao;
import com.springapp.mvc.domain.Flow;
import com.springapp.mvc.domain.NetFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public int insertSingleFlow(NetFlow netFlow){
        return dao.insertSingleFlow(netFlow);
    }
    //插入一个list
    public int insertNetFlowList(List<NetFlow> netFlows){
        return dao.insertNetFlowList(netFlows);
    }

    public int insertFlowList(List<Flow> flows){
        List<NetFlow> list = new ArrayList<NetFlow>();
        for (Flow flow:flows){
            list.add(flow.changeToNetFlow());
        }
        return dao.insertNetFlowList(list);
    }

}
