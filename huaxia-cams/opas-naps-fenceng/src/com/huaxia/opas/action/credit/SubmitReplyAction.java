package com.huaxia.opas.action.credit;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.service.credit.CreditCheckSearchService;
import com.huaxia.opas.service.credit.SubmitReplyService;

import net.sf.json.JSONObject;

public class SubmitReplyAction implements Action{

	private static Logger logger = LoggerFactory.getLogger(SubmitReplyAction.class);

	@Resource(name = "submitReplyService")
	private SubmitReplyService submitReplyService;

	public SubmitReplyService getSubmitReplyService() {
		return submitReplyService;
	}

	public void setSubmitReplyService(SubmitReplyService submitReplyService) {
		this.submitReplyService = submitReplyService;
	}

	public void querySubmitReplyInfo(Context ctx) throws CoreException, ParseException {
		String appId = (String) ctx.getDataMap().get("appId");
		String ydjFlag = (String) ctx.getDataMap().get("ydjFlag");
		Assert.notNull(appId, "请求申请件编号为空!");
		Assert.notNull(ydjFlag, "易达金标示为空!");
		Map<String ,String> argMap = new HashMap<String ,String>();
		argMap.put("appId", appId);
		argMap.put("ydjFlag", ydjFlag);
		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]人行摘要信息");
			logger.debug("查询申请件[" + ydjFlag + "]卡类标示信息");
		}
		Map<String, Object> resultMap = submitReplyService.querySubmitReplyInfo(argMap);
//		Date approveTime =(Date) resultMap.get("approveTime");
//		System.out.println(approveTime);
		if (resultMap != null) {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			ctx.setData("success", true);
			ctx.setData("resultMap", jsonObject.toString());
		}else{
			ctx.setData("success", false);
		}
	}
	
	/**
	 * 查询历史审批结果
	 * @param context
	 * @throws Exception
	 */
	public void historyAuditQuery(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");

		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]历史审批结果");
		}

		Map<String, Object> historyAuditInfo = submitReplyService.selectByHistoryAuditInfo(appId);
 		if (historyAuditInfo != null) {
			JSONObject jsonObject = JSONObject.fromObject(historyAuditInfo);
			context.setData("success", true);
			context.setData("historyAuditInfo",historyAuditInfo);
			context.setData("historyInfo",jsonObject.toString());
			return;
		}

		context.setData("success", false);
	}
	/**
	 * 系统决策页面预审提报回复查看  
	 * @user wdb 
	 * @param context
	 * @date 2019.9.10 10:22
	 */
	public void queryYsSubmitReplyInfo(Context ctx) throws CoreException, ParseException {
		String appId = (String) ctx.getDataMap().get("appId");
		String ydjFlag = (String) ctx.getDataMap().get("ydjFlag");
		Assert.notNull(appId, "请求申请件编号为空!");
		Assert.notNull(ydjFlag, "易达金标示为空!");
		Map<String ,String> argMap = new HashMap<String ,String>();
		argMap.put("appId", appId);
		argMap.put("ydjFlag", ydjFlag);
		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]人行摘要信息");
			logger.debug("查询申请件[" + ydjFlag + "]卡类标示信息");
		}
		Map<String, Object> resultMap = submitReplyService.queryYsSubmitReplyInfo(argMap);
		if (resultMap != null) {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			ctx.setData("success", true);
			ctx.setData("resultMap", jsonObject.toString());
		}else{
			ctx.setData("success", false);
		}
	}
	
}
