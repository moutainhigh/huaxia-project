package com.huaxia.opas.action.channel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.decision.InputChannel;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.BizInpApplErr;
import com.huaxia.opas.service.channel.InputChannelService;

public class InputChannelAction implements Action{

	private static Logger logger=LoggerFactory.getLogger(InputChannelAction.class);
	@Resource(name = "inputChannelService")
	private InputChannelService inputChannelService;
	/**
	 *@Title:queryInputChannel
	 *@Description:进件管理表查询
	 *@param ctx
	 *@author: andong
	 */
	public void queryInputChannel(Context ctx){
		String msg = "操作成功";
		Map<String, Object> map = ctx.getDataMap();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<InputChannel> inputChannel = inputChannelService.queryInputChannel(map);
		paramMap.put("inputChannel", inputChannel);
		paramMap.put("msg", msg);
		ctx.setDataMap(paramMap);
	}
	/**
	 *@Title:updateInputChannel
	 *@Description:更新进件时间快速进件
	 *@param ctx
	 *@author: andong
	 */
	public void updateInputChannel(Context ctx) throws ParseException{
		String msg = "操作成功";
		Map<String, Object> map = ctx.getDataMap();
		
		try {
			inputChannelService.updateInputChannel(map);
			ctx.setData("success", true);
			ctx.setData("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("success", false);
			ctx.setData("msg", "更新失败。");
		}
		ctx.setData("msg", msg);
	}
	/**
	 *@Title:queryErrorCode
	 *@Description:查询C1表的乱码字段
	 *@param ctx
	 *@author: andong
	 */
	public void queryErrorCode(Context ctx){
		Map<String, Object> map = ctx.getDataMap();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		BizInpApplC1 bizInpApplC1 = inputChannelService.queryErrorCode(map);
		paramMap.put("inputChannel", bizInpApplC1);
		ctx.setDataMap(paramMap);
	}
	/**
	 *@updateErrorCode
	 *@Description:根据appid更新乱码字段
	 *@param ctx
	 *@author: andong
	 */
	public void updateErrorCode(Context ctx) throws ParseException{
		String msg = "保存成功";
		Map<String, Object> map = ctx.getDataMap();
		try {
			inputChannelService.updateErrorCode(map);
			ctx.setData("success", true);
			ctx.setData("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("success", false);
			ctx.setData("msg", "更新失败。");
		}
	}
	
	/**
	 *@Title:querySendCard
	 *@Description:送发卡结果查询
	 *@param ctx
	 *@author: andong
	 */
	public void querySendCard(Context ctx){
		String msg = "操作成功";
		Map<String, Object> map = ctx.getDataMap();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sendCard = inputChannelService.querySendCard(map);
		paramMap.put("sendCard", sendCard);
		paramMap.put("msg", msg);
		ctx.setDataMap(paramMap);
	}
	
	public void updateEveryTable(Context ctx) throws ParseException{
		String msg = "保存成功";
		Map<String, Object> map = ctx.getDataMap();
		try {
			inputChannelService.updateEveryTable(map);
			ctx.setData("success", true);
			ctx.setData("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("success", false);
			ctx.setData("msg", "更新失败。");
		}
	}
	
	public void searchEveryTable(Context ctx){
		String msg = "操作成功";
		Map<String, Object> map = ctx.getDataMap();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int count= inputChannelService.searchEveryTableCount(map);
		if (count > 0) {
				list = inputChannelService.searchEveryTable(map, curNum, pageNum);
		}
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 *@Title:queryAllotInfo
	 *@Description:查询分件表的字段
	 *@param ctx
	 *@author: andong
	 */
	public void queryAllotInfo(Context ctx){
		Map<String, Object> map = ctx.getDataMap();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		AllotApplyAllot allotInfo = inputChannelService.queryAllotInfo(map);
		paramMap.put("allotInfo", allotInfo);
		ctx.setDataMap(paramMap);
	}
	/**
	 *@updateAllotInfo
	 *@Description:根据appid更新分件
	 *@param ctx
	 *@author: andong
	 */
	public void updateAllotInfo(Context ctx) throws ParseException{
		String msg = "保存成功";
		Map<String, Object> map = ctx.getDataMap();
		try {
			inputChannelService.updateAllotInfo(map);
			ctx.setData("success", true);
			ctx.setData("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("success", false);
			ctx.setData("msg", "更新失败。");
		}
	}
		
	//预审分件表
	public void queryYsAllotInfo(Context ctx){
		Map<String, Object> map = ctx.getDataMap();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		AllotApplyAllot allotInfo = inputChannelService.queryYsAllotInfo(map);
		paramMap.put("allotInfo", allotInfo);
		ctx.setDataMap(paramMap);
	}
	//预审分件表
	public void updateYsAllotInfo(Context ctx) throws ParseException{
		String msg = "保存成功";
		Map<String, Object> map = ctx.getDataMap();
		try {
			inputChannelService.updateYsAllotInfo(map);
			ctx.setData("success", true);
			ctx.setData("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("success", false);
			ctx.setData("msg", "更新失败。");
		}
	}
	
	/**
	 * 
	 * @param ctx
	 */
	public void queryBreakFqz(Context ctx){
		String msg = "操作成功";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String breaker = inputChannelService.queryBreakFqz();
		if(breaker == null || breaker == ""){
			breaker = "0";
			paramMap.put("breaker", breaker);
		}else{
			paramMap.put("breaker", breaker.trim());
		}
		paramMap.put("msg", msg);
		ctx.setDataMap(paramMap);
	}
	
	public void updateBreakFqz(Context ctx) throws ParseException{
		String msg = "更改成功";
		Map<String, Object> map = ctx.getDataMap();
		String turn = (String) map.get("turn");
		try {
			inputChannelService.updateBreakFqz(turn);
			ctx.setData("success", true);
			ctx.setData("msg", msg);
		} catch (Exception e) {
			//e.printStackTrace();
			ctx.setData("success", false);
			ctx.setData("msg", "更新失败。");
		}
	}
	
	/**
	 * 查询重复条码 20200804 
	 */
	public void queryRepeatDataAppid(Context ctx){
		String queryAppId = (String) ctx.getDataMap().get("queryAppId");
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
		Map<String, Object> rMap = inputChannelService.queryRepeatDataAppid(paramMap,curNum, pageNum);
		ctx.setDataMap(rMap);
	
	}
	/**
	 *批量更改
	 * @param ctx
	 * @throws CoreException
	 */
	public void batchupdate(Context ctx) throws CoreException{
			Map<String, Object> map = ctx.getDataMap();
			String msg = "更改成功";
			@SuppressWarnings("unchecked")
			List<String> appidlist = (List<String>) map.get("code");
			for(String appid : appidlist){
		    try {
			Map<String, Object> mp = new HashMap<String, Object>();
			mp.put("appid", appid);
			mp.put("targappid", switchDate(appid,appid,"1"));
				inputChannelService.updateAppid(mp);
				ctx.setData("success", true);
				ctx.setData("msg", msg);
			} catch (Exception e) {
				//e.printStackTrace();
				ctx.setData("success", false);
				ctx.setData("msg", "更新失败。");
			}
		}
	}
	
	public void updateAppid(Context ctx) throws ParseException{
		String msg = "更改成功";
		Map<String, Object> map = ctx.getDataMap();
		String appid = (String) map.get("appid");
		String flag = (String) map.get("flag");
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("appid", appid);
		mp.put("targappid", switchDate(appid,appid,flag));
		try {
			inputChannelService.updateAppid(mp);
			ctx.setData("success", true);
			ctx.setData("msg", msg);
		} catch (Exception e) {
			//e.printStackTrace();
			ctx.setData("success", false);
			ctx.setData("msg", "更新失败。");
		}
	}
	
	public String switchDate(String appid,String app_id, String flag){
		if("1".equals(flag)){
		char  [] dst = new char [2];
		appid.getChars(0, 2, dst, 0);
		StringBuffer stringBuffer1 = new StringBuffer();
		for (char c : dst) {
			c =(char)(c+17) ;
			System.out.println(c);
			stringBuffer1.append(c);
		}
		return stringBuffer1.toString()+app_id.substring(2, 16);
		}else{
		
		char  [] dst2 = new char [2];
		appid.getChars(0, 2, dst2, 0);
		System.out.println(dst2);
		StringBuffer stringBuffer = new StringBuffer();
		for (char c : dst2) {
			c = (char)(c-17);
			System.out.println(c);
			stringBuffer.append(c);
		}
		return stringBuffer.toString()+app_id.substring(2, 16);
	}
	}
	
	/**
	 * 查询进件错误条码
	 * */
	public void queryOpasBizInpApplErr(Context context){
		Gson gson = new Gson();
		BizInpApplErr bizInpApplErr = gson.fromJson(gson.toJson(context.getDataMap()), BizInpApplErr.class);
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
		if (curPage > 1){
			curNum = (curPage -1) * pageNum;
		}
		bizInpApplErr.setInsideAppNo(bizInpApplErr.getInsideAppNo().toUpperCase());
		int count = inputChannelService.queryOpasBizInpApplErrCount(bizInpApplErr);
		List<BizInpApplErr> list = new ArrayList<BizInpApplErr>();
		if(count > 0){
			list = inputChannelService.queryOpasBizInpApplErrList(bizInpApplErr, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		context.setDataMap(dataMap);
	}
	
	public void queryTaskFicoFraudAppid(Context ctx){
		String appid = (String) ctx.getDataMap().get("appid_");
		String taskType = (String) ctx.getDataMap().get("taskType_");
		String status = (String) ctx.getDataMap().get("status_");
		String requestType = (String) ctx.getDataMap().get("requestType_");
		String quickFlag = (String) ctx.getDataMap().get("quickFlag_");
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
		paramMap.put("appid", appid);
		paramMap.put("taskType", taskType);
		paramMap.put("status", status);
		paramMap.put("requestType", requestType);
		paramMap.put("quickFlag", quickFlag);
		rMap = inputChannelService.queryTaskFicoFraudAppid(paramMap,curNum, pageNum);
		ctx.setDataMap(rMap);
	
	}
	public void queryfqzFicoExpMessageDetail(Context ctx){
		String dbid = ctx.getData("app_id");
		Map <String,String> paramMap = new HashMap<String,String>();
		Map<String, Object> rMap = new HashMap<String, Object>();
		paramMap.put("app_id", dbid);
		rMap = inputChannelService.queryTopbpmExpMessageDetail(paramMap);
		ctx.setData("fqzFicoExpMessageDetail", rMap);
	}
	
	public void insertTaskFicoFraudAppid(Context ctx) throws ParseException{
		String msg = "添加成功";
		String msg1 = "条码已存在";
		Map<String, String> paramMap= (Map<String, String>) ctx.getDataMap().get("jsondata");
		try {
			Map<String, Object> rMap = new HashMap<String, Object>();
			int total = inputChannelService.queryTaskFicoFraudAppidCount(paramMap);
			if(total == 0){
				inputChannelService.insertTaskFicoFraudAppid(paramMap);
				ctx.setData("success", true);
				ctx.setData("msg", msg);
			}else if(total > 0){
				ctx.setData("success", false);
				ctx.setData("msg", msg1);
			}
			
		} catch (Exception e) {
			ctx.setData("success", false);
			ctx.setData("msg", "添加失败。");
		}
	}
	
	public void updatefqzficoSingleOrBatch(Context ctx){
		Map<String, String> paramMap = new HashMap<String, String>();
		Map<String, Object> map = ctx.getDataMap();
		String executionId = "";
		List list = (List) map.get("executionIdList");
		for(int i=0;i<list.size();i++){
			executionId = String.valueOf(list.get(i));
			if(executionId == null){
				ctx.setData("isSuccess", false);
				break;
			}
			paramMap.put("appid", executionId);
			inputChannelService.updatefqzficoSingleOrBatch(paramMap);
		}
		ctx.setData("isSuccess", true);
	}
}



