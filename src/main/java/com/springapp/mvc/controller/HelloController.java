package com.springapp.mvc.controller;

import com.springapp.mvc.domain.Flow;
import com.springapp.mvc.domain.NetFlow;
import com.springapp.mvc.service.ClassifyService;
import com.springapp.mvc.service.NetFlowService;
import com.springapp.mvc.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HelloController {

	@Autowired
	private NetFlowService service;
    @Autowired
    private ClassifyService classifyService;

	@RequestMapping("/index")
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		List<NetFlow> list = service.getAllFlow();
		model.addAttribute("list",list);
		return "index";
	}

	@RequestMapping("/task.do")
    public String task(){
        List<Flow> flows = classifyService.loadData(Constant.TRAINFILE);//从csv中读取的
        for(Flow flow : flows){
            classifyService.classify(flow);
        }

        return "index";
    }
}