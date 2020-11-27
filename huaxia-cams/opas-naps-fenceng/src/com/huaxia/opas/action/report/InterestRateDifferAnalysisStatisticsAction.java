package com.huaxia.opas.action.report;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.report.InterestRateDifferAnalysisStatisticsService;

/**
 * 利率差异化分析统计报表
 * 
 * @author gaohui 
 *
 */
public class InterestRateDifferAnalysisStatisticsAction implements Action{

	//private static Logger logger = LoggerFactory.getLogger(FishingAction.class);
	@Resource(name = "interestRateDifferAnalysisStatisticsService")
	private InterestRateDifferAnalysisStatisticsService interestRateDifferAnalysisStatisticsService;
	public InterestRateDifferAnalysisStatisticsService getInterestRateDifferAnalysisStatisticsService() {
		return interestRateDifferAnalysisStatisticsService;
	}
	public void setInterestRateDifferAnalysisStatisticsService(
			InterestRateDifferAnalysisStatisticsService interestRateDifferAnalysisStatisticsService) {
		this.interestRateDifferAnalysisStatisticsService = interestRateDifferAnalysisStatisticsService;
	}

	/**
	*@Title:queryInterestRateDifferAnalysisStatisticsDataByDate
	*@Description:根据时间区间从不同报表中获取   申请类型、新旧户、卡产品、利率客户分层结果标签、利率代码、核准数量、核准数量占比 等信息
	*@param context
	*@author: gaohui
	*@Date:2017年3月22日下午2:30:13
	 */
	public void queryInterestRateDifferAnalysisStatisticsDataByDate(Context context) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
	    Map<String, Object> htmlMap	=context.getDataMap();
		//SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String beginDate=null;
		String endDate=null;
		if(htmlMap.containsKey("beginDate")){//用于放入map中的时间字段的控制
			if(!htmlMap.get("beginDate").toString().equals("")){
			beginDate=htmlMap.get("beginDate").toString();
			paraMap.put("beginDate", beginDate);
			}else{
			paraMap.put("beginDate","");
			}
			if(!htmlMap.get("endDate").toString().equals("")){
				endDate=htmlMap.get("endDate").toString();
				paraMap.put("endDate", endDate);
			}else{
				paraMap.put("endDate","");
			}
		try{
		context.setDataMap(interestRateDifferAnalysisStatisticsService.findDataInterestRateDifferAnalysisStatisticsByDate(paraMap));
		}catch(Exception e){
			e.printStackTrace();
		}
		}else{//页面第一次加载"beginDate"等键不存在，不进行查询
			Map<String, Object> paraNullMap = new HashMap<String, Object>();
			paraNullMap.put("rows","");
			context.setDataMap(paraNullMap);
		}
	}
}
