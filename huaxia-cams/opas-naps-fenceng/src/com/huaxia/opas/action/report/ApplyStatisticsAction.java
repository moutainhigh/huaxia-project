package com.huaxia.opas.action.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.report.ApplyStatisticsService;

public class ApplyStatisticsAction implements Action{

	private static Logger logger=LoggerFactory.getLogger(ApplyStatisticsAction.class);

	@Resource(name = "applyStatisticsService")
	private ApplyStatisticsService applyStatisticsService;
	public ApplyStatisticsService getApplyStatisticsService() {
		return applyStatisticsService;
	}
	public void setApplyStatisticsService(ApplyStatisticsService applyStatisticsService) {
		this.applyStatisticsService = applyStatisticsService;
	}

	public void queryApplyStatistics(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();
		if(map.containsKey("startDate")) {
			int curNum = 0;
			int curPage = Integer
					.parseInt(String.valueOf(map.get("page") == null ? 0 : ctx.getDataMap().get("page")));
			int pageNum = Integer
					.parseInt(String.valueOf(map.get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			Map<String, Object> paraMap = new HashMap<String, Object>();
			//paraMap.put("userPms", "4");
			//paraMap.put("userPms", map.get("userPms"));
			if(!"--请选择--".equals((String)map.get("userOrg"))&&map.get("userOrg")!=null&&!"".equals((String)map.get("userOrg"))){
				paraMap.put("orgName", map.get("userOrg"));
			}
			if(!"--请选择所在组--".equals((String)map.get("operator"))&&map.get("operator")!=null&&!"".equals((String)map.get("operator"))){
				paraMap.put("name", map.get("operator"));
			}
			if(!"--请选择--".equals((String)map.get("gmOrAi"))&&map.get("gmOrAi")!=null&&!"".equals((String)map.get("gmOrAi")))
				paraMap.put("gmOrAi", map.get("gmOrAi"));
			if(map.get("startDate")!=null&&!"".equals((String)map.get("startDate"))){
				paraMap.put("startDate", (String) map.get("startDate"));
			}else{
				paraMap.put("startDate","2017-01-01");
			}
			if(map.get("endDate")!=null&&!"".equals((String)map.get("endDate"))){
				paraMap.put("endDate", (String) map.get("endDate"));
			}else{
				paraMap.put("endDate","2017-01-01");
			}
			int count = applyStatisticsService.queryApplyStatisticsCount(paraMap);
			List<Map<String, Object>> resultList = applyStatisticsService.queryApplyStatistics(paraMap, curNum, pageNum);
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
