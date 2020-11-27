package com.huaxia.opas.action.monitor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.monitor.Monitor3rdList;
import com.huaxia.opas.service.monitor.Monitor3rdService;

public class Monitor3rdAction implements Action {

	@Resource(name = "monitor3rdService")
	private Monitor3rdService monitor3rdService;

	public void monitorlist(Context ctx) throws CoreException {
		Map<String,Object> map = ctx.getDataMap();
		Gson gson = new Gson();
		Monitor3rdList monitor3rdList = gson.fromJson(gson.toJson(map), Monitor3rdList.class);
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = monitor3rdService.queryMonitor3rdCount(monitor3rdList);
		List<Monitor3rdList> list = new ArrayList<Monitor3rdList>();
		if (count > 0) {
			list = monitor3rdService.queryMonitor3rdList(monitor3rdList, curNum, pageNum);
			for(Monitor3rdList ml : list){
				Timestamp sysdate = ml.getSysdate();
				Timestamp end = ml.getTime_STAMP();
				long minus = (sysdate.getTime() - end.getTime())/60000;
				ml.setMinus(minus);
			}
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	//单条修改状态 task_STATUS
	public void updateMonitor3rd(Context ctx) throws CoreException {
		Map<String,Object> map = ctx.getDataMap();
		Gson gson = new Gson();
		Monitor3rdList monitor3rdList = gson.fromJson(gson.toJson(map), Monitor3rdList.class);
		try {
			monitor3rdService.updateByPrimaryKeySelective(monitor3rdList);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);

	}
	
	//批量修改状态task_STATUS
	public void updateStatusSelective(Context ctx) throws CoreException {
		
		Map map = ctx.getDataMap();
		List<String> app_IDs = (List<String>) map.get("app_IDs");
		List<String> task_TYPEs = (List<String>) map.get("task_TYPEs");
		String task_STATUS = (String) map.get("task_STATUS");
		try {	
			for(int i = 0;i<app_IDs.size();i++){
				String app_id = app_IDs.get(i);
				String task_type = task_TYPEs.get(i);
				Monitor3rdList monitor3rdList = new Monitor3rdList();
				monitor3rdList.setApp_ID(app_id);
				monitor3rdList.setTask_TYPE(task_type);
				monitor3rdList.setTask_STATUS(task_STATUS);
				monitor3rdService.updateStatusSelective(monitor3rdList);
			   }
			} catch (CoreException e) {
				ctx.setData("exMsg", e.getMessage());
				throw e;
			}
			ctx.setData("isSuccess", true);
		
	}
	

}
