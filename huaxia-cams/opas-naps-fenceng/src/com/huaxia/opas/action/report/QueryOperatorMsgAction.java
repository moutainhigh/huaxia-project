package com.huaxia.opas.action.report;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.json.JSONObject;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.service.report.QueryOperatorMsgService;
import com.huaxia.opas.util.TransDateUtil;

import net.sf.json.JSONArray;

public class QueryOperatorMsgAction implements Action{

	private static Logger logger=LoggerFactory.getLogger(QueryOperatorMsgAction.class);

	@Resource(name = "queryOperatorMsgService")
	private QueryOperatorMsgService queryOperatorMsgService;

	public QueryOperatorMsgService getQueryOperatorMsgService() {
		return queryOperatorMsgService;
	}

	public void setQueryOperatorMsgService(QueryOperatorMsgService queryOperatorMsgService) {
		this.queryOperatorMsgService = queryOperatorMsgService;
	}

	public void queryOrgName(Context ctx) throws CoreException {
		Map<String, Object> map = ctx.getDataMap();
		List<Map<String, Object>> resultList = queryOperatorMsgService.queryOrgName();
		JSONArray ja = JSONArray.fromObject(resultList);
		ctx.setData("resultList", ja.toString());
	}
	public void queryOperatorName(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();
		//System.out.println(map);
		List<Map<String, Object>> resultList = queryOperatorMsgService.queryOperatorName((String)map.get("orgNo"));
		JSONArray ja = JSONArray.fromObject(resultList);
		ctx.setData("userList", ja.toString());
	}
	public void queryApplyOrgName(Context ctx) throws CoreException {
		Map<String, Object> map = ctx.getDataMap();
		List<Map<String, Object>> resultList = queryOperatorMsgService.queryApplyOrgName();
		JSONArray ja = JSONArray.fromObject(resultList);
		ctx.setData("resultList", ja.toString());
	}
	public void queryApplyOperatorName(Context ctx) {
		Map<String, Object> map = ctx.getDataMap();
		//System.out.println(map);
		List<Map<String, Object>> resultList = queryOperatorMsgService.queryApplyOperatorName((String)map.get("orgNo"));
		JSONArray ja = JSONArray.fromObject(resultList);
		ctx.setData("userList", ja.toString());
	}
}
