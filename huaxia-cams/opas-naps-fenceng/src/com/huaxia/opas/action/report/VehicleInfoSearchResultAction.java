package com.huaxia.opas.action.report;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.report.VehicleInfoSearchResultService;

import net.sf.json.JSONObject;

/**
 * 
 * @author dingguofeng
 * 车辆信息查询结果汇总报表
 *
 */

public class VehicleInfoSearchResultAction implements Action {
	
	@Resource(name = "vehicleInfoSearchResultService")
	private VehicleInfoSearchResultService vehicleInfoSearchResultService;
	public VehicleInfoSearchResultService getVehicleInfoSearchResultService(){
		return vehicleInfoSearchResultService;
	}
	public void setVehicleInfoSearchResultService(VehicleInfoSearchResultService vehicleInfoSearchResultService){
		this.vehicleInfoSearchResultService = vehicleInfoSearchResultService;
	}
	
	/**
	 * @Title:queryVehicleInfoSearchResultDataByDate
	 * @author dingguofeng
	 * @Description:根据时间区间,查询对应的数据
	 * @param context
	 * @Date:2017-10-25
	 * 
	 */
	public void queryVehicleInfoSearchResultDataByDate(Context context){
		
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
				context.setDataMap(vehicleInfoSearchResultService.findVehicleInfoSearchResultDataByDate(paraMap));
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
