package com.huaxia.opas.action.report;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.report.FicoResultService;

import net.sf.json.JSONObject;

/**
 * 
 * @author lipengfei(xiao)
 * FICO大数据评分查询结果汇总报表
 *
 */

public class FicoReportAction implements Action {
	
	@Resource(name = "ficoResultService")
	private FicoResultService ficoResultService;
	public FicoResultService getFicoResultService(){
		return ficoResultService;
	}
	public void setFicoResultService(FicoResultService ficoResultService){
		this.ficoResultService = ficoResultService;
	}
	
	/**
	 * 查询FICO汇总报表
	 * 
	 */
	public void queryFicoSummaryReportDataByDate(Context context){
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.fromObject(context.getDataMap());
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
				context.setDataMap(ficoResultService.queryFicoSummaryDataByDate(paraMap));
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
	 * FICO报表明细
	 * 需要添查询条件
	 * 
	 */	
	public void queryFicoDetailDataByDate(Context context){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		int pageNum = 0;//当前页
		JSONObject jsonObject = JSONObject.fromObject(context.getDataMap());
		String beginDate = null;
		String endDate = null;
		if(jsonObject.containsKey("beginDate")){//用于放入map中的时间字段的控制、及键是否存在的控制
			try {//前台控制 时间
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
				
				int curNum = Integer.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
				//每页显示的条数
				int pageRows = Integer.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
				if (curNum > 1) {
					pageNum = (curNum - 1) * pageRows;
				}
				
				//添加前台传输的选择条件
				paraMap.put("choseClass", jsonObject.getString("choseClass").toString());
				context.setDataMap(ficoResultService.queryFicoDetailDataByDate(paraMap, pageNum, pageRows));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else{//页面第一次加载"beginDate"等键不存在，不进行查询
			Map<String, Object> paraNullMap = new HashMap<String, Object>();
			paraNullMap.put("rows","");
			context.setDataMap(paraNullMap);
		}
	}
	
}
