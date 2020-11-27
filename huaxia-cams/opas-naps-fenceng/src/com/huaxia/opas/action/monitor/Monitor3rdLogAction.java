package com.huaxia.opas.action.monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.monitor.Monitor3rdLog;
import com.huaxia.opas.service.monitor.Monitor3rdLogService;

public class Monitor3rdLogAction implements Action {
	
	@Resource(name = "monitor3rdLogService")
	private Monitor3rdLogService monitor3rdLogService;
	
	public void monitor3rdLogList(Context ctx) throws CoreException {
		Map<String,Object> map = ctx.getDataMap();
		Gson gson = new Gson();
		Monitor3rdLog monitor3rdLog = gson.fromJson(gson.toJson(map), Monitor3rdLog.class);
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = monitor3rdLogService.queryMonitor3rdLogCount(monitor3rdLog);
		List<Monitor3rdLog> list = new ArrayList<Monitor3rdLog>();
		if (count > 0) {
			list = monitor3rdLogService.queryMonitor3rdLogList(monitor3rdLog, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

}
