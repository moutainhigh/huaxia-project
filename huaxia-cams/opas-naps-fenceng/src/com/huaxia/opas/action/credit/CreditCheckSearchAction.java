package com.huaxia.opas.action.credit;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.credit.T5eEsMiddle;
import com.huaxia.opas.service.credit.CreditCheckSearchService;

public class CreditCheckSearchAction implements Action{

	private static Logger logger = LoggerFactory.getLogger(CreditCheckSearchAction.class);

	@Resource(name = "creditCheckSearchService")
	private CreditCheckSearchService creditCheckSearchService;

	public CreditCheckSearchService getCreditCheckSearchService() {
		return creditCheckSearchService;
	}

	public void setCreditCheckSearchService(CreditCheckSearchService creditCheckSearchService) {
		this.creditCheckSearchService = creditCheckSearchService;
	}
	
	public void queryCreditInfo(Context ctx) throws CoreException, ParseException {
		String appId = (String) ctx.getData("appId");
		String ydjFlag = (String) ctx.getDataMap().get("ydjFlag");

		Map<String ,String> argMap = new HashMap<String ,String>();
		argMap.put("appId", appId);
		argMap.put("ydjFlag", ydjFlag);
		
		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]人行摘要信息");
			logger.debug("查询申请件[" + ydjFlag + "]卡类标示信息");
		}
		
		Map<String, Object> resultMap = creditCheckSearchService.queryCreditInfo(argMap);
		if (resultMap != null) {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			ctx.setData("success", true);
			ctx.setData("resultMap", jsonObject.toString());
		}else{
			ctx.setData("success", false);
		}
	}
	//征信调查查看页查询方法   安东
	public void creditCheckQuery(Context ctx) throws CoreException, ParseException {
		String appId = (String) ctx.getData("appId");
		String ydjFlag = (String) ctx.getDataMap().get("ydjFlag");

		Map<String ,String> argMap = new HashMap<String ,String>();
		argMap.put("appId", appId);
		argMap.put("ydjFlag", ydjFlag);
		
		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]人行摘要信息");
			logger.debug("查询申请件[" + ydjFlag + "]卡类标示信息");
		}
		
		Map<String, Object> resultMap = creditCheckSearchService.queryCreditCheckInfo(argMap);
		if (resultMap != null) {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			ctx.setData("success", true);
			ctx.setData("resultMap", jsonObject.toString());
		}else{
			ctx.setData("success", false);
		}
	}
	
	public void queryT5eEsMiddle(Context ctx) {
		Map<String, Object> dataMap = ctx.getDataMap();
		T5eEsMiddle t5eEsMiddle = creditCheckSearchService.queryT5eEsMiddle(dataMap);
		if (t5eEsMiddle != null) {
			ctx.setData("success", true);
			ctx.setData("t5eEsMiddle", t5eEsMiddle);
		}else{
			ctx.setData("success", false);
		}
	}
	
	
	
}
