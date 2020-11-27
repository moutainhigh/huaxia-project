package com.huaxia.opas.action.thirdparty;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.thirdparty.HzGjjService;

import net.sf.json.JSONObject;

public class HzGjjAction implements Action{

	
	private static Logger logger = LoggerFactory.getLogger(HzGjjAction.class);
	
	@Autowired HzGjjService hzGjjService;
	
	//公积金个人信息
	public void queryGrxxInfo(Context context) {
		String appId = (String) context.getData("appId");
		Map<String, String> gjjDetail = hzGjjService.queryGrxxInfo(appId);
		JSONObject jsonObject = JSONObject.fromObject(gjjDetail);
		context.setData("success", true);
		context.setData("gjjxxInfo", jsonObject.toString());
	}
	//公积金信息
	public void queryGjjxxInfo(Context context) {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> gjjDetail = hzGjjService.queryGjjxxInfo(appId);
		communalDetail(gjjDetail,context);
	}
	//社保缴费信息 
	public void querySBfeeinfogridInfo(Context context) {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> gjjDetail = hzGjjService.querySBfeeinfogridInfo(appId);
		communalDetail(gjjDetail,context);
	}
	//社保基本信息
	public void queryHzRsjZxAc01(Context context) {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> gjjDetail = hzGjjService.queryHzRsjZxAc01(appId);
		communalDetail(gjjDetail,context);
	}
	//水务缴费信息
	public void queryHzWaterAffairsInfo(Context context) {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> gjjDetail = hzGjjService.queryHzWaterAffairsInfo(appId);
		communalDetail(gjjDetail,context);
	}
	//法院失信
	public void queryHzFyNaturalPerson(Context context) {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> gjjDetail = hzGjjService.queryHzFyNaturalPerson(appId);
		communalDetail(gjjDetail,context);
	}
	//公积金贷款信息
	public void queryHzGjjLoanInfo(Context context) {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> gjjDetail = hzGjjService.queryGjjLoadBrief(appId);
		communalDetail(gjjDetail,context);
	}
	//车辆信息
	public void queryHzVehicleInfo(Context context) {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> gjjDetail = hzGjjService.queryHzVehicleInfo(appId);
		communalDetail(gjjDetail,context);
	}
	//车辆处罚信息
	public void queryHzVehiclePenaltyInfo(Context context) {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> gjjDetail = hzGjjService.queryHzVehiclePenaltyInfo(appId);
		communalDetail(gjjDetail,context);
	}

	//公用的处理查询的数据的结果
	public static void communalDetail(List<Map<String, String>> gjjDetail,Context context){
		if (gjjDetail != null) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				jsonObject=gson.toJson(gjjDetail);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("gjjxxInfo", jsonObject);
			return;
		}
		context.setData("success", false);
	}
}
