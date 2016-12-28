package com.springapp.mvc.dao;

import com.springapp.mvc.domain.NetFlow;
import com.springapp.mvc.util.MyBatisRepository;

import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */
@MyBatisRepository
public interface NetFlowDao {
    List<NetFlow> getAllFlow();

    int insertSingleFlow(NetFlow flow);

    int insertFlowList(List<NetFlow> flowList);
}
