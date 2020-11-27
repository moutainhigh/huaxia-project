package com.huaxia.opas.action.sysparm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.TelNo;
import com.huaxia.opas.service.sysparm.TelNoService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 电话号码参数
 * 
 * @author liudi
 * @since 2017-04-07
 * @version 1.0
 */
public class TelNoAction implements Action {

	@Resource(name = "telNoService")
	private TelNoService telNoService;

	/**
	 * 获取照会对象码信息
	 */
	public void queryTelNo(Context ctx) throws CoreException {
		Gson gson = new Gson();
		TelNo telNo = gson.fromJson(gson.toJson(ctx.getDataMap()), TelNo.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		String field = "";
		// 查询参数
		Map<String, Object> params = new HashMap<String, Object>();
		if (ctx.getDataMap().get("dateType") != null) {
			int type = Integer.parseInt(ctx.getDataMap().get("dateType").toString());
			switch (type) {
			case 1:
				field = "START_DATE";
				break;
			case 2:
				field = "STOP_DATE";
				break;
			case 3:
				field = "LST_UPD_DATE";
			}

			String sort = (String) ctx.getDataMap().get("order");
			if ("2".equals(sort)) {
				sort = "DESC";
			} else {
				sort = "ASC";
			}
			params.put("field", field);
			params.put("sort", sort);
		}
		if (telNo.getTelTypeCode() != null) {
			params.put("telTypeCode", telNo.getTelTypeCode());
		}
		if (telNo.getTelNoDesc() != null) {
			params.put("telNoDesc", telNo.getTelNoDesc());
		}
		if (telNo.getAcctType() != null) {
			params.put("acctType", telNo.getAcctType());
		}
		if (telNo.getStatus() != null) {
			params.put("status", telNo.getStatus());
		}

		List<TelNo> list = new ArrayList<TelNo>();

		int count = telNoService.queryTelNoCount(telNo);
		if (params.size() == 0 && count > 0) {
			list = telNoService.queryTelNo(telNo, curNum, pageNum);
		}
		if (params.size() > 0 && count >= 0) {
			list = telNoService.queryTelNo(params, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改照会对象码信息
	 */
	public void updateTelNo(Context ctx) throws CoreException {

		Gson json = new Gson();

		TelNo telNo = json.fromJson(json.toJson(ctx.getDataMap()), TelNo.class);

		String userId = ctx.getData("userId");
		String autoId = ctx.getData("autoId");
		telNo.setLstUpdUser(userId);

		try {
			String status = telNoService.queryTelNoStatus(autoId);
			if (telNo.getStatus().equals(status)) {
				telNoService.updateTelNoWithoutStatus(telNo);
			} else {
				telNoService.updateTelNo(telNo);
			}
		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除照会对象码信息
	 */
	public void removeTelNo(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			telNoService.deleteTelNo(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增照会对象码信息
	 */
	public void saveTelNo(Context ctx) throws CoreException {

		Gson json = new Gson();

		TelNo telNo = json.fromJson(json.toJson(ctx.getDataMap()), TelNo.class);

		telNo.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		telNo.setCrtUser(userId);
		telNo.setLstUpdUser(userId);

		TelNo queryPassCode = telNoService.queryTelNo(telNo);

		if (queryPassCode != null) {
			ctx.setData("isFailed", true);
			return;
		}

		try {

			if (telNo.getStatus().equals("1")) {

				telNoService.saveTelNoStart(telNo);

			} else if (telNo.getStatus().equals("0")) {

				telNoService.saveTelNoEnd(telNo);

			} else {

			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}