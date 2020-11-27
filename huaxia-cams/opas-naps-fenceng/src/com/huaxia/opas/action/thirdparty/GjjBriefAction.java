package com.huaxia.opas.action.thirdparty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.thirdparty.GjjService;

import net.sf.json.JSONObject;

public class GjjBriefAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(GjjBriefAction.class);

	@Resource(name = "gjjService")
	private GjjService gjjService;
	public GjjService getGjjService() {
		return gjjService;
	}
	public void setGjjService(GjjService gjjService) {
		this.gjjService = gjjService;
	}
	
	//征信信息页面展示公积金信息
	public void queryGjjInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		Map<String, String> param = new HashMap<String, String>();
		Map<String, String>	 gjjInfo=  gjjService.selectGjjInfo(appId);
		String status="未发起查询";
        if(gjjInfo==null){
        	status="未发起查询";
		}else {
			status="查询成功";
        }
        param.put("status", status);
		JSONObject jsonObject = JSONObject.fromObject(param);
		context.setData("success", true);
		context.setData("gjjBriefInfo", jsonObject);
		
	}
	
	//公积金数据
	public void queryGjjBriefInfo(Context context) {
		//Map<String, Object> map = context.getDataMap();
		String appId = (String) context.getData("appId");
		Map<String, String>	 gjjBriefInfo=  gjjService.selectGjjBriefInfo(appId);
		String status="未发起查询";
		if(gjjBriefInfo==null){
        	status="未发起查询";
		}else {
			status="查询成功";
        }
        gjjBriefInfo.put("status", status);
		JSONObject jsonObject = JSONObject.fromObject(gjjBriefInfo);
		context.setData("success", true);
		context.setData("gjjBriefInfo", jsonObject.toString());
	}
	
	//公积金明细
	public void queryGjjDetail (Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> gjjDetail = gjjService.queryGjjDetail(appId);
		if (gjjDetail != null) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				jsonObject=gson.toJson(gjjDetail);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("gjjDetailInfo", jsonObject);
			return;
		}
		context.setData("success", false);
	}
	/*//公积金基本信息分析
	public void queryGjjBriefAnalysis (Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> gjjDetail = gjjService.queryGjjBriefAnalysis(appId);
		if (gjjDetail != null) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				jsonObject=gson.toJson(gjjDetail);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("gjjDetailInfo", jsonObject);
			return;
		}
		context.setData("success", false);
	}*/
	
	/*//明细分析queryGjjDetailAnalysis
	public void queryGjjDetailAnalysis (Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> gjjDetail = gjjService.queryGjjDetailAnalysis(appId);
		if (gjjDetail != null) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				jsonObject=gson.toJson(gjjDetail);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("gjjDetailInfo", jsonObject);
			return;
		}
		context.setData("success", false);
	}*/
	
	//公司明细分析queryCompanyAnalData
	public void queryCompanyAnalData (Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> gjjDetail = gjjService.queryCompanyAnalData(appId);
		if (gjjDetail != null) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				jsonObject=gson.toJson(gjjDetail);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("gjjDetailInfo", jsonObject);
			return;
		}
		context.setData("success", false);
	}
	//贷款基本信息
	public void queryGjjLoadBrief (Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> gjjDetail = gjjService.queryGjjLoadBrief(appId);
		if (gjjDetail != null) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				jsonObject=gson.toJson(gjjDetail);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("gjjDetailInfo", jsonObject);
			return;
		}
		context.setData("success", false);
	}
	//贷款明细
	public void queryGjjLoadDetail (Context context) throws Exception {
		String appId = (String) context.getData("appId");
		List<Map<String, String>> gjjDetail = gjjService.queryGjjLoadDetail(appId);
		if (gjjDetail != null) {
			String jsonObject=null;
			try {
				Gson gson = new Gson();
				jsonObject=gson.toJson(gjjDetail);	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			context.setData("success", true);
			context.setData("gjjDetailInfo", jsonObject);
			return;
		}
		context.setData("success", false);
	}
}
