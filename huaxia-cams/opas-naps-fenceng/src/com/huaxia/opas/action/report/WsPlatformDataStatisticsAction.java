package com.huaxia.opas.action.report;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.service.report.WsPlatformDataStatisticsService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 网申合作平台数据统计清单-进件情况统计表
 * 
 * @author wenyh
 *
 */
public class WsPlatformDataStatisticsAction implements Action{
	private static Logger logger = LoggerFactory.getLogger(WsPlatformDataStatisticsAction.class);
	@Resource(name = "wsPlatformDataStatisticsService")
	private WsPlatformDataStatisticsService wsPlatformDataStatisticsService;

	/**
	*@Title:queryWsPlatformDataStatistics
	*@Description:按日期区间对进件量进行查询，并可对未完成申请件条形码进行调阅链接
	*@param context
	*@author: gaohui
	*@Date:2017年3月20日下午4:05:15
	 */
	public void queryWsPlatformDataStatistics(Context context) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.fromObject(context.getDataMap());
		//SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String beginDate = null;
		String endDate = null;
		if(jsonObject.containsKey("beginDate")){//用于放入map中的时间字段的控制、及键是否存在的控制
			try {//前台控制 时间、维度 为必填项
				if(!jsonObject.getString("beginDate").toString().equals("")){
				beginDate=jsonObject.getString("beginDate").toString();
				paraMap.put("beginDate", beginDate);
				}else{
				paraMap.put("beginDate","");
				}
				if(!jsonObject.getString("endDate").toString().equals("")){
					endDate=jsonObject.getString("endDate").toString();
					paraMap.put("endDate", endDate);
				}else{
					paraMap.put("endDate","");
				}
				paraMap.put("business",jsonObject.getString("business").toString());//公司编码
				paraMap.put("module",jsonObject.getString("module").toString());//模块编码
				context.setDataMap(wsPlatformDataStatisticsService.findWsPlatformDataStatistics(paraMap));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else{//页面第一次加载"beginDate"等键不存在，不进行查询
			Map<String, Object> paraNullMap = new HashMap<String, Object>();
			paraNullMap.put("rows","");
			context.setDataMap(paraNullMap);
		}
	}
	/**
	 * 获取公司编码
	 * @param ctx
	 * @throws CoreException
	 */
	public void queryBusinessNumber(Context ctx) throws CoreException {
		List<Map<String, Object>> resultList = wsPlatformDataStatisticsService.queryBusinessNumber();
		JSONArray ja = JSONArray.fromObject(resultList);
		ctx.setData("resultList", ja.toString());
	}
	/**
	 * 获取模块编码
	 * @param ctx
	 */
	public void queryModuleNumber(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();
		List<Map<String, Object>> resultList = wsPlatformDataStatisticsService.queryModuleNumber((String)map.get("businessNumber"));
		JSONArray ja = JSONArray.fromObject(resultList);
		ctx.setData("moduleList", ja.toString());
	}
	
}
