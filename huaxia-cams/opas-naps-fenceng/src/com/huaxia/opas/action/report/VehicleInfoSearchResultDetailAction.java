package com.huaxia.opas.action.report;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.report.VehicleInfoSearchResultDetailService;

import net.sf.json.JSONObject;

/**
 * 
 * @author dingguofeng
 * 车辆信息查询结果明细报表
 *
 */

public class VehicleInfoSearchResultDetailAction implements Action{
	
	@Resource(name = "vehicleInfoSearchResultDetailService")
	private VehicleInfoSearchResultDetailService vehicleInfoSearchResultDetailService;
	public VehicleInfoSearchResultDetailService getVehicleInfoSearchResultDetailService(){
		return vehicleInfoSearchResultDetailService;
	}
	public void setVehicleInfoSearchResultDetailService(VehicleInfoSearchResultDetailService vehicleInfoSearchResultDetailService){
		this.vehicleInfoSearchResultDetailService = vehicleInfoSearchResultDetailService;
	}
	
	/**
	 * @Title:queryVehicleInfoSearchResultDetailDataByDate
	 * @author dingguofeng
	 * @Description:根据时间区间,查询对应的数据
	 * @param context
	 * @Date:2017-10-27
	 * 
	 */
	public void queryVehicleInfoSearchResultDetailDataByDate(Context context){
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
				
				paraMap.put("choseClass", jsonObject.getString("choseClass").toString());
				context.setDataMap(vehicleInfoSearchResultDetailService.findVehicleInfoSearchResultDetailDataByDate(paraMap, pageNum, pageRows));
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
