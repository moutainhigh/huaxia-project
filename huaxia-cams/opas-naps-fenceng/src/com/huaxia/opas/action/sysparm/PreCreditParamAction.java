package com.huaxia.opas.action.sysparm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.sysparm.BatchfileInfo;
import com.huaxia.opas.domain.sysparm.PreCreditParam;
import com.huaxia.opas.service.sysparm.PreCreditParamService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 预授信Action
 * 
 * @author 汪涛
 *
 */
public class PreCreditParamAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(PreCreditParamAction.class);

	@Resource(name = "preCreditParamService")
	private PreCreditParamService preCreditParamService;

	public PreCreditParamService getPreCreditParamService() {
		return preCreditParamService;
	}

	public void setPreCreditParamService(PreCreditParamService preCreditParamService) {
		this.preCreditParamService = preCreditParamService;
	}

	/**
	 * 查询预授信信息
	 * 
	 * @param ctx
	 */

	public void queryPreCreditParamList(Context ctx) {
		Gson gson = new Gson();
		PreCreditParam preCreditParam = gson.fromJson(gson.toJson(ctx.getDataMap()), PreCreditParam.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		int count = preCreditParamService.queryPreCreditParamCount(preCreditParam);

		List<PreCreditParam> list = new ArrayList<PreCreditParam>();

		if (count > 0) {
			list = preCreditParamService.queryPreCreditParamList(preCreditParam, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 添加预授信信息
	 * 
	 * @throws Exception
	 */
	public void insertPreCreditParam(Context ctx) throws Exception {

		Gson json = new Gson();
		Map<String, Object> map = ctx.getDataMap();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = sdf.parse((String) map.get("startDate"));
			Date endDate = sdf.parse((String) map.get("endDate"));
			map.put("startDate", startDate);
			map.put("endDate", endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		PreCreditParam preCreditParam = json.fromJson(json.toJson(map), PreCreditParam.class);

		// 给客户设置autoID
		preCreditParam.setAutoID(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		preCreditParam.setLstUpdUser(userId);
		preCreditParam.setCrtUser(userId);

		try {
			if ("1".equals(preCreditParam.getStatus())) {
				preCreditParamService.insertPreCreditParamStart(preCreditParam);
			} else {
				preCreditParamService.insertPreCreditParamEnd(preCreditParam);
			}
		} catch (Exception e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 修改预授信信息
	 * 
	 * @throws Exception
	 */
	public void updatePreCreditParam(Context ctx) throws Exception {

		Gson json = new Gson();
		Map<String, Object> map = ctx.getDataMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = sdf.parse((String) map.get("startDate"));
			Date endDate = sdf.parse((String) map.get("endDate"));
			map.put("startDate", startDate);
			map.put("endDate", endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		PreCreditParam preCreditParam = json.fromJson(json.toJson(map), PreCreditParam.class);
		String autoID = ctx.getData("autoID");
		String userId = ctx.getData("userId");
		preCreditParam.setLstUpdUser(userId);
		try {
			String status = preCreditParamService.queryPreCreditParamStatus(autoID);
			if((preCreditParam.getStatus()).equals(status)){
				preCreditParamService.updatePreCreditParamWithoutStatus(preCreditParam);
			}else{
				preCreditParamService.updatePreCreditParam(preCreditParam);
			}
		} catch (Exception e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 批量启用预授信信息
	 * 
	 * @throws Exception
	 */
	public void batchStartPreCreditParam(Context ctx) throws Exception {
		Map<String, Object> map = ctx.getDataMap();
		List<Map<Object, Object>> preCreditParamMaps = (List<Map<Object, Object>>) map.get("custObj");
		String userId = ctx.getData("userId");
		try {
			for (Map<Object, Object> preCreditParamMap : preCreditParamMaps) {
				preCreditParamMap.put("status", "1");
				preCreditParamMap.put("lstUpdUser", userId);
			}
			preCreditParamService.batchStartPreCreditParam(preCreditParamMaps);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 批量停用预授信信息
	 * 
	 * @throws Exception
	 */
	public void batchStopPreCreditParam(Context ctx) throws Exception {
		Map<String, Object> map = ctx.getDataMap();
		List<Map<Object, Object>> preCreditParamMaps = (List<Map<Object, Object>>) map.get("custObj");
		String userId = ctx.getData("userId");
		try {
			for (Map<Object, Object> preCreditParamMap : preCreditParamMaps) {
				preCreditParamMap.put("status", "0");
				preCreditParamMap.put("lstUpdUser", userId);
			}
			preCreditParamService.batchStopPreCreditParam(preCreditParamMaps);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 批量删除
	 * 
	 * @throws Exception
	 */
	public void batchDeletePreCreditParam(Context ctx) throws Exception {
		Map<String, Object> map = ctx.getDataMap();
		List<String> autoIds = (List<String>) map.get("autoIds");
		try {
			preCreditParamService.batchDeletePreCreditParam(autoIds);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 预授信信息历史查询
	 */
	public void queryPreCreditParamHistory(Context ctx) {
		String autoID = ctx.getData("autoID");
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		int curNum = 0;

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		int count = preCreditParamService.queryPreCreditParamHistoryCount(autoID);

		List<PreCreditParam> list = new ArrayList<PreCreditParam>();

		if (count > 0) {
			list = preCreditParamService.queryPreCreditParamHistoryList(autoID, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);
	}

	/**
	 * 预授信信息批量导入的记录展示
	 * 
	 * @throws ParseException
	 */
	public void queryPreCreditParamUpload(Context ctx) throws ParseException {
		Gson gson = new Gson();
		Map<String, Object> dataMap = ctx.getDataMap();
		if (!"".equals(dataMap.get("inputTime")) && null != dataMap.get("inputTime")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date inputTime = sdf.parse((String) dataMap.get("inputTime"));
			dataMap.put("inputTime", inputTime);
		} else {
			dataMap.remove("inputTime");
		}
		BatchfileInfo batchfileInfo = gson.fromJson(gson.toJson(dataMap), BatchfileInfo.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		int count = preCreditParamService.queryPreCreditParamUploadCount(batchfileInfo);
		List<BatchfileInfo> list = new ArrayList<>();
		if (count > 0) {
			list = preCreditParamService.queryPreCreditParamUpload(batchfileInfo, curNum, pageNum);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("total", count);
		map.put("rows", list);

		ctx.setDataMap(map);
	}
}
