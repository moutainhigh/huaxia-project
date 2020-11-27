package com.huaxia.opas.allot.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.service.allot.AllotApplyService;

public class AutoAction implements Action {
	
	private static final Logger log = LoggerFactory.getLogger(AutoAction.class);
	
	@Resource(name="allotApplyService")
	private AllotApplyService allotApplyService;
	
	public void countAutoApp(Context context) throws CoreException {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map = context.getData("jsondata"); 
		try {
			//查询两次  显示本环节池已分配
			Map<String,Object> allMap=new HashMap<String,Object>();
			allMap=allotApplyService.saveApplyByConditions(map);
			context.setData("allResult", allMap.get("allResult"));
			//本环节池待分配
			Map<String, Object> dataMap = new HashMap<String, Object>();
			map.put("strType","3");
			dataMap=allotApplyService.saveApplyByConditions(map);
			context.setData("total", dataMap.get("allResult"));
			context.setData("isSuccess", true);
		} catch (CoreException e) {
			log.error("AllotHandAction.countTodayApp occur error"+e);
			 context.setData("isSuccess", false);
		}
	}
}
