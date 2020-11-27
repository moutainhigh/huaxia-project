package com.huaxia.opas.action.workflow;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.workflow.Workflow;
import com.huaxia.opas.domain.workflow.WorkflowCallback;
import com.huaxia.opas.service.workflow.WorkflowQuService;

public class WorkflowAction implements Action {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(WorkflowAction.class);
	@Resource(name = "workflowQuService")
	private WorkflowQuService workflowQuService;

	public void selectWorkflow(Context ctx) throws Exception {
		Workflow workflow = new Workflow();
		Map<String, Object> map = ctx.getData("jsondata");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		if (StringUtils.isNotEmpty((String) map.get("startTime"))) {
			Date startTime = sdf.parse((String) map.get("startTime"));
			workflow.setStartTime(startTime);
		}
		if (StringUtils.isNotEmpty((String) map.get("endTime"))) {
			Date endTime = sdf.parse((String) map.get("endTime"));
			workflow.setEndTime(endTime);
		}
		if (StringUtils.isNotEmpty((String) map.get("status"))) {
			workflow.setStatus((String) map.get("status"));
		}
		if (StringUtils.isNotEmpty((String) map.get("packageid"))) {// bzk ydj
			workflow.setPackageid((String) map.get("packageid"));
		}
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));

		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		workflow.setCurNum(curNum);
		workflow.setPageNum(pageNum);
		Map<String, Object> rMap = workflowQuService.selectWorkflow(workflow);
		ctx.setDataMap(rMap);
	}
	/**
	 * 20200616 yuanquan
	 * 查询需要手动回调工作流的条码,插入指定条码对应的推进表数据
	 * 20200715 自动和人工环节条码展示
	 */
	public void queryTopbpmExceSolveAppid(Context ctx){
		String queryStatus = (String) ctx.getDataMap().get("queryStatus");
		String queryAppId = (String) ctx.getDataMap().get("queryAppId");
		String solveorNot = (String) ctx.getDataMap().get("solveorNot");
		String operateDesc = (String) ctx.getDataMap().get("operateDesc");
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map <String,String> paramMap = new HashMap<String,String>();
		paramMap.put("queryStatus", queryStatus);
		paramMap.put("queryAppid", queryAppId);
		paramMap.put("solveorNot", solveorNot);
		paramMap.put("operateDesc", operateDesc);
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap = workflowQuService.queryTopbpmExceSolveAppid(paramMap,curNum, pageNum);
		ctx.setDataMap(rMap);
	
	}
	//自动节点异常条码展示 20200714
	public void queryTopbpmExceSolveAutoAppid(Context ctx){
		String queryStatus = (String) ctx.getDataMap().get("queryStatus");
		String queryAppId = (String) ctx.getDataMap().get("queryAppId");
		String currNode = (String) ctx.getDataMap().get("currNode");
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map <String,String> paramMap = new HashMap<String,String>();
		paramMap.put("queryStatus", queryStatus);
		paramMap.put("queryAppid", queryAppId);
		paramMap.put("currNode", currNode);
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap = workflowQuService.queryTopbpmExceSolveAutoAppid(paramMap,curNum, pageNum);
		ctx.setDataMap(rMap);
		
	}
	//推进表展示 20200714
	public void queryTopbpmTmpExecutionAppid(Context ctx){
		String executionId = (String) ctx.getDataMap().get("executionId");
		String webApplicationId = (String) ctx.getDataMap().get("webApplicationId");
		String status = (String) ctx.getDataMap().get("status");
		String activityName = (String) ctx.getDataMap().get("activityName");
		String priority = (String) ctx.getDataMap().get("priority");
		String dbid = (String) ctx.getDataMap().get("dbid");
		String appId = (String) ctx.getDataMap().get("appId");
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map <String,String> paramMap = new HashMap<String,String>();
		Map<String, Object> rMap = new HashMap<String, Object>();
		paramMap.put("executionId", executionId);
		paramMap.put("webApplicationId", webApplicationId);
		paramMap.put("status", status);
		paramMap.put("activityName", activityName);
		paramMap.put("priority", priority);
		paramMap.put("dbid", dbid);
		paramMap.put("appId", appId);
		rMap = workflowQuService.queryTopbpmTmpExecutionAppid(paramMap,curNum, pageNum);
		ctx.setDataMap(rMap);
		
	}
	public void queryTopbpmTmpExecutionAppidDetail(Context ctx){
		String executionId = ctx.getData("executionId");
		Map <String,String> paramMap = new HashMap<String,String>();
		Map<String, Object> rMap = new HashMap<String, Object>();
		paramMap.put("executionId", executionId);
		rMap = workflowQuService.queryTopbpmTmpExecutionAppidDetail(paramMap);
		ctx.setData("tmpDetail", rMap);
	}
	public void queryTopbpmExpMessageDetail(Context ctx){
		String dbid = ctx.getData("dbid");
		Map <String,String> paramMap = new HashMap<String,String>();
		Map<String, Object> rMap = new HashMap<String, Object>();
		paramMap.put("dbid", dbid);
		rMap = workflowQuService.queryTopbpmExpMessageDetail(paramMap);
		ctx.setData("topbpmExpMessageDetail", rMap);
	}
	//根据条码查询process表 20200822
	public void queryTopbpmProcessByAppid(Context ctx){
		String queryAppId = (String) ctx.getDataMap().get("queryAppId");
		String queryStatus = (String) ctx.getDataMap().get("queryStatus");
		Map <String,String> paramMap = new HashMap<String,String>();
		paramMap.put("queryAppid", queryAppId);
		paramMap.put("queryStatus", queryStatus);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap = workflowQuService.queryTopbpmProcessByAppid(paramMap,curNum,pageNum);
		ctx.setDataMap(rMap);
	}
	//查询topbpm_j_activity表 20200902
	public void queryActivityInfo(Context ctx){
		String hproci = (String) ctx.getDataMap().get("hproci_");
		Map <String,String> paramMap = new HashMap<String,String>();
		paramMap.put("hproci", hproci);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap = workflowQuService.queryActivityInfo(paramMap,curNum,pageNum);
		ctx.setDataMap(rMap);
	}
	//查询topbpm_j_processvariable表 20200902
	public void queryProcessvariableInfo(Context ctx){
		String hproci = (String) ctx.getDataMap().get("hproci_");
		Map <String,String> paramMap = new HashMap<String,String>();
		paramMap.put("hproci", hproci);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap = workflowQuService.queryProcessvariableInfo(paramMap,curNum,pageNum);
		ctx.setDataMap(rMap);
	}
	//查询topbpm_j_processvariable_t表 20200902
	public void queryProcessvariable_tInfo(Context ctx){
		String execution = (String) ctx.getDataMap().get("execution_");
		Map <String,String> paramMap = new HashMap<String,String>();
		paramMap.put("execution", execution);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap = workflowQuService.queryProcessvariable_tInfo(paramMap,curNum,pageNum);
		ctx.setDataMap(rMap);
	}
	//查询topbpm_j_process_t表 20200902
	public void queryProcess_tDetail(Context ctx){
		String dbid = (String) ctx.getDataMap().get("dbid_");
		Map <String,String> paramMap = new HashMap<String,String>();
		paramMap.put("dbid", dbid);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap = workflowQuService.queryProcess_tDetail(paramMap,curNum,pageNum);
		ctx.setDataMap(rMap);
	}
	
	//更改流程相关表详细  20200903
	public void updateProcessDetail(Context ctx){
		Map<String, String> jsondata= (Map<String, String>) ctx.getDataMap().get("jsondata");
		Map<String, String> processDetailMap= (Map<String, String>) ctx.getDataMap().get("processDetailMap");
		WorkflowCallback workflowCallback = new WorkflowCallback();
		//解析相关字段
		String varname = jsondata.get("varname");
		String key = jsondata.get("key");
		String activityName = jsondata.get("activityName");
		String varname_ruleOpt = processDetailMap.get("varname_ruleOpt");
		String varname_dbid = processDetailMap.get("varname_dbid");
		String key_ruleOpt = processDetailMap.get("key_ruleOpt");
		String key_dbid = processDetailMap.get("key_dbid");
		String activityname_ = processDetailMap.get("activityname");
		String activityname_dbid = processDetailMap.get("activityname_dbid");
		//匹配校验 判断需要更改的值
		if(varname!=null&&!varname.trim().equals("")&&varname_ruleOpt!=null&&varname_ruleOpt.equals("ruleOpt")){
			workflowCallback.setDbid(varname_dbid);
			workflowCallback.setValue(varname);
			workflowQuService.updateProcessvariableInfo(workflowCallback);
		}
		if(key!=null&&!key.trim().equals("")&&key_ruleOpt!=null&&key_ruleOpt.equals("ruleOpt")){
			workflowCallback.setDbid(key_dbid);
			workflowCallback.setStringValue(key);
			workflowQuService.updateProcessvariable_tInfo(workflowCallback);
		}
		if(activityName!=null&&!activityName.trim().equals("")&&activityname_!=null){
			workflowCallback.setDbid(activityname_dbid);
			workflowCallback.setActivityName(activityName);;
			workflowQuService.updateProcess_tInfo(workflowCallback);
		}
		
		
		ctx.setData("isSuccess", true);
		
	}
	public void updateTopbpmTmpExecutionDetail(Context ctx){
		Map<String, String> jsondata= (Map<String, String>) ctx.getDataMap().get("jsondata");
		String executionId = jsondata.get("executionId");
		String webApplicationId = jsondata.get("webApplicationId");
		String status = jsondata.get("status");
		String activityName = jsondata.get("activityName");
		String priority = jsondata.get("priority");
		String dbid = jsondata.get("dbid");
		Map <String,String> paramMap = new HashMap<String,String>();
		paramMap.put("executionId", executionId);
		paramMap.put("webApplicationId", webApplicationId);
		paramMap.put("status", status);
		paramMap.put("activityName", activityName);
		paramMap.put("priority", priority);
		paramMap.put("dbid", dbid);
		workflowQuService.updateTopbpmTmpExecutionDetail(paramMap);
		ctx.setData("isSuccess", true);
		
	}
	public void deleteTopbpmTmpExecutionDetail(Context ctx){
		String executionId = ctx.getData("executionId");
		Map <String,String> paramMap = new HashMap<String,String>();
		paramMap.put("executionId", executionId);
		workflowQuService.deleteTopbpmTmpExecutionDetail(paramMap);
		ctx.setData("isSuccess", true);
		
	}
	
	public void updateTmpSingleOrBatch(Context ctx){
		//executionId不可更改
		Map<String, String> paramMap= (Map<String, String>) ctx.getDataMap().get("jsondata");
		if(null!=paramMap.get("executionId")&&!"".equals(paramMap.get("executionId"))){
			return;
		}
		if(paramMap.containsKey("appId")){
			paramMap.remove("appId");
		}
		Map<String, Object> map = ctx.getDataMap();
		String executionId = "";
		List list = (List) map.get("executionIdList");
		for(int i=0;i<list.size();i++){
			executionId = String.valueOf(list.get(i));
			paramMap.put("executionId", executionId);
			workflowQuService.updateTopbpmTmpExecutionDetail(paramMap);
		}
		ctx.setData("isSuccess", true);
	}
	public void insertTmpSingle(Context ctx){
		Map<String, String> dataMap= (Map<String, String>) ctx.getDataMap().get("jsondata");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("appId",dataMap.get("appId"));
		paramMap.put("status",dataMap.get("status"));
		paramMap.put("activityName",dataMap.get("activityName"));
		paramMap.put("executionId",dataMap.get("executionId"));//新增executionId
		workflowQuService.insertTmpSingle(paramMap);
		ctx.setData("isSuccess", true);
	}
	
	
	public void callbackSingleOrBatch(Context ctx){
		Map<String, Object> map = ctx.getDataMap();
		List list = (List) map.get("appIds");
		String appId = "";
		try {
			for(int i=0;i<list.size();i++){
				appId = String.valueOf(list.get(i));
				workflowQuService.insertTopbpmTmpExecutionAndUpdateStatus(appId);
			}
			
		} catch (Exception e) {
			logger.error("[{}]手动回调工作流程失败!",appId, e);
		}
		ctx.setData("msg", "手动回调工作流程结束!");
	}
	//手动更改梯队条码处理状态 20200904
	public void updateTierAppidStatus(Context ctx){
		Map<String, Object> map = ctx.getDataMap();
		List list = (List) map.get("appIds");
		String solveorNot = (String) map.get("solveorNot");
		String appId = "";
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("solveorNot", solveorNot);
		try {
			for(int i=0;i<list.size();i++){
				appId = String.valueOf(list.get(i));
				paramMap.put("appId", appId);
				workflowQuService.updateTierAppidStatus(paramMap);
			}
			
		} catch (Exception e) {
			logger.error("[{}]手动更改状态失败!",appId, e);
		}
		ctx.setData("msg", "手动更改状态结束!");
	}
	//手动更改梯队条码处理状态 20200904
	public void updateWeiguidangAppidStatus(Context ctx){
		Map<String, Object> map = ctx.getDataMap();
		List list = (List) map.get("appIds");
		String solveorNot = (String) map.get("solveorNot");
		String appId = "";
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("solveorNot", solveorNot);
		try {
			for(int i=0;i<list.size();i++){
				appId = String.valueOf(list.get(i));
				paramMap.put("appId", appId);
				workflowQuService.updateWeiguidangAppidStatus(paramMap);
			}
			
		} catch (Exception e) {
			logger.error("[{}]手动更改状态失败!",appId, e);
		}
		ctx.setData("msg", "手动更改状态结束!");
	}
	
	
	
	
	public void solveExceptionSingleOrBatch(Context ctx){
		Map<String, Object> map = ctx.getDataMap();
		List list = (List) map.get("appIds");
		String appId = "";
		try {
			for(int i=0;i<list.size();i++){
				appId = String.valueOf(list.get(i));
				workflowQuService.solveExceptionSingleOrBatch(appId);
			}
		} catch (Exception e) {
			logger.error("[{}]异常处理失败!",appId, e);
		}
		ctx.setData("msg", "异常处理结束!");
	}
	//更改错误的异常状态  20200717
	public void updateErrorExceptionStatus(Context ctx){
		int updateNum = 0;
		String msg = "";
		try {
			updateNum = workflowQuService.updateErrorExceptionStatus();
			msg = "更改错误的异常状态["+updateNum+"]条";
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			msg = "更改错误的异常状态失败!";
		}
		ctx.setData("msg", msg);
	}
	//更改错误的异常状态  20200804
	public void updateAppointErrorExceptionStatus(Context ctx){
		Map<String, Object> map = ctx.getDataMap();
		List list = (List) map.get("dbids");
		String queryStatus = (String) map.get("queryStatus");
		String dbid = "";
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("queryStatus", queryStatus);
		try {
			for(int i=0;i<list.size();i++){
				dbid = String.valueOf(list.get(i));
				paramMap.put("dbid", dbid);
				workflowQuService.updateAppointErrorExceptionStatus(paramMap);
			}
		} catch (Exception e) {
			logger.error("[{}]更改指定的异常状态失败!",dbid, e);
		}
		ctx.setData("msg", "更改指定的异常状态结束!");
		
	}
	//更改指定条码process表状态 20200824
	public void updateAppointErrorProcessStatus(Context ctx){
		Map<String, Object> map = ctx.getDataMap();
		List list = (List) map.get("dbids");
		String queryStatus = (String) map.get("queryStatus");
		String dbid = "";
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("queryStatus", queryStatus);
		try {
			for(int i=0;i<list.size();i++){
				dbid = String.valueOf(list.get(i));
				paramMap.put("dbid", dbid);
				workflowQuService.updateAppointErrorProcessStatus(paramMap);
			}
		} catch (Exception e) {
			logger.error("[{}]更改指定的异常状态失败!",dbid, e);
		}
		ctx.setData("msg", "更改指定的异常状态结束!");
		
	}
	
	
	
	/**
	 * 未归档条码展示 20200630 
	 */
	public void queryWeiguidangAppid(Context ctx){
		String queryAppId = (String) ctx.getDataMap().get("queryAppId");
		String solveorNot = (String) ctx.getDataMap().get("solveorNot");
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map <String,String> paramMap = new HashMap<String,String>();
		paramMap.put("queryAppid", queryAppId);
		paramMap.put("solveorNot", solveorNot);
		Map<String, Object> rMap = workflowQuService.queryWeiguidangAppid(paramMap,curNum, pageNum);
		ctx.setDataMap(rMap);
	
	}
}
