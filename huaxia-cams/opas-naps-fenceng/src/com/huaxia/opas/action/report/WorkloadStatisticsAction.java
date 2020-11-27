package com.huaxia.opas.action.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.report.WorkloadStatisticsService;

public class WorkloadStatisticsAction implements Action{

	private static Logger logger=LoggerFactory.getLogger(WorkloadStatisticsAction.class);

	@Resource(name = "workloadStatisticsService")
	private WorkloadStatisticsService workloadStatisticsService;
	public WorkloadStatisticsService getWorkloadStatisticsService() {
		return workloadStatisticsService;
	}
	public void setWorkloadStatisticsService(WorkloadStatisticsService workloadStatisticsService) {
		this.workloadStatisticsService = workloadStatisticsService;
	}

	public void queryWorkloadStatistics(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();
		if(map.containsKey("startTime")) {
			int curNum = 0;
			int curPage = Integer
					.parseInt(String.valueOf(map.get("page") == null ? 0 : ctx.getDataMap().get("page")));
			int pageNum = Integer
					.parseInt(String.valueOf(map.get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			Map<String, Object> paraMap = new HashMap<String, Object>();
			if(!"--请选择--".equals((String)map.get("userOrg"))&&map.get("userOrg")!=null&&!"".equals((String)map.get("userOrg"))){
				paraMap.put("userOrg", map.get("userOrg"));
			}
			if(!"--请选择所在组--".equals((String)map.get("operator"))&&map.get("operator")!=null&&!"".equals((String)map.get("operator"))){
				paraMap.put("user", map.get("operator"));
			}
			if(map.get("startTime")!=null&&!"".equals((String)map.get("startTime"))){
				paraMap.put("startTime",(String) map.get("startTime"));
			}else{
				paraMap.put("startTime","2017-01-01");
			}
			if(map.get("endTime")!=null&&!"".equals((String)map.get("endTime"))){
				paraMap.put("endTime", (String) map.get("endTime"));
			}else{
				paraMap.put("endTime","2017-01-01");
			}
			int count = workloadStatisticsService.queryWorkloadStatisticsCount(paraMap);
			List<Map<String, Object>> resultList = workloadStatisticsService.queryWorkloadStatistics(paraMap, curNum, pageNum);
			map.put("total", count);
			map.put("rows", resultList);
			ctx.setDataMap(map);
		} else {
			map.put("total", "");
			map.put("rows", "");
			ctx.setDataMap(map);
		}
	}
}
