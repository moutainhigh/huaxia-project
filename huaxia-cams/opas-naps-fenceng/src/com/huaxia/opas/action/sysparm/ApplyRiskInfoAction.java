package com.huaxia.opas.action.sysparm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.sysparm.ApplyRiskInfo;
import com.huaxia.opas.service.sysparm.ApplyRiskInfoService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 参数管理 风险联系人信息
 * @author 汪涛
 *
 */
public class ApplyRiskInfoAction implements Action {

	@Resource(name = "applyRiskInfoService")
	private ApplyRiskInfoService applyRiskInfoService;

	/**
	 * 查询风险联系人信息列表
	 * 
	 * @param ctx
	 */

	public void queryApplyRiskInfoList(Context ctx) {
		Gson gson = new Gson();
		ApplyRiskInfo applyRiskInfo = gson.fromJson(gson.toJson(ctx.getDataMap()), ApplyRiskInfo.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		int count = applyRiskInfoService.queryApplyRiskInfoCount(applyRiskInfo);

		List<ApplyRiskInfo> list = new ArrayList<ApplyRiskInfo>();

		if (count > 0) {
			list = applyRiskInfoService.queryApplyRiskInfoList(applyRiskInfo, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 参数管理 风险联系人信息 单条添加
	 * 
	 * @author Wangtao 2018-6-26 16:06
	 * @throws Exception
	 */
	public void addApplyRiskInfo(Context ctx) throws Exception {

		Gson json = new Gson();
		Map<String, Object> map = ctx.getDataMap();
		ApplyRiskInfo applyRiskInfo = json.fromJson(json.toJson(map), ApplyRiskInfo.class);
		String userId = ctx.getData("userId");
		try {
			applyRiskInfo.setAutoId(SequenceUtil.getUUID());
			applyRiskInfo.setCrtUser(userId);
			applyRiskInfo.setLstUpdUser(userId);
			applyRiskInfoService.addApplyRiskInfo(applyRiskInfo);
			ctx.setData("isSuccess", true);
		} catch (Exception e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 参数管理风险联系人信息  单条修改
	 * @author Wangtao 2018-06-26 16:34
	 * @param ctx
	 * @throws Exception
	 */
	public void updateApplyRiskInfo(Context ctx) throws Exception{
		Gson json = new Gson();
		Map<String, Object> map = ctx.getDataMap();
		ApplyRiskInfo applyRiskInfo = json.fromJson(json.toJson(map), ApplyRiskInfo.class);
		String userId = ctx.getData("userId");
		String autoId = ctx.getData("autoId");
		try {
			applyRiskInfo.setAutoId(autoId);
			applyRiskInfo.setCrtUser(userId);
			applyRiskInfo.setLstUpdUser(userId);
			applyRiskInfoService.updateApplyRiskInfo(applyRiskInfo);
			ctx.setData("isSuccess", true);
		} catch (Exception e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	
	
	public void deleteApplyRiskInfo(Context ctx) throws Exception{
		Map<String, Object> map = ctx.getDataMap();
		List<String> autoIds = (List<String>) map.get("autoIds");
		try{
			applyRiskInfoService.deleteApplyRiskInfo(autoIds);
			ctx.setData("isSuccess", true);
		}catch(Exception e){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	
	public void queryAllApplyRiskInfo(Context ctx) throws Exception{
		List<Map<String,String>> applyRiskInfoList = new ArrayList<>();
		String appId = ctx.getData("appId");
		applyRiskInfoList = applyRiskInfoService.queryAllApplyRiskInfo();
		String applyRiskInfoStr = JSON.toJSONString(applyRiskInfoList);
		String c5AbCode = applyRiskInfoService.queryC5AbCode(appId);
		ctx.setData("applyRiskInfoStr", applyRiskInfoStr);
		ctx.setData("c5AbCode", c5AbCode);
	}
	
	public void queryApplyRiskInfoByAbCode(Context ctx) throws Exception{
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<ApplyRiskInfo> applyRiskInfoListByAbCode = new ArrayList<>();
		String abCode = ctx.getData("abCode");
		int count = applyRiskInfoService.queryApplyRiskInfoByAbCodeCount(abCode);
		if(count > 0){
			applyRiskInfoListByAbCode = applyRiskInfoService.queryApplyRiskInfoByAbCode(abCode,curNum,pageNum);
		}
		Map<String,Object> dataMap = new HashMap<>();
		dataMap.put("total", count);
		dataMap.put("rows", applyRiskInfoListByAbCode);
		ctx.setDataMap(dataMap);
	}
}
