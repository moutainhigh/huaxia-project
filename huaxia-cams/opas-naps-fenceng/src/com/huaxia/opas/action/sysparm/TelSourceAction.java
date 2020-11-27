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
import com.huaxia.opas.domain.sysparm.TelSource;
import com.huaxia.opas.service.sysparm.TelSourceService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 电话来源码
 * 
 * @author liudi
 * @since 2017-02-28
 * @version 1.0
 */
public class TelSourceAction implements Action {

	@Resource(name = "telSourceService")
	private TelSourceService telSourceService;

	/**
	 * 获取电话来源码信息
	 */
	public void queryTelSource(Context ctx) throws CoreException {
		Gson gson = new Gson();
		TelSource telSource = gson.fromJson(gson.toJson(ctx.getDataMap()), TelSource.class);

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
		if (telSource.getTelSourceCode() != null) {
			params.put("telSourceCode", telSource.getTelSourceCode());
		}
		if (telSource.getTelSourceDesc() != null) {
			params.put("telSourceDesc", telSource.getTelSourceDesc());
		}
		if (telSource.getAcctType() != null) {
			params.put("acctType", telSource.getAcctType());
		}
		if (telSource.getStatus() != null) {
			params.put("status", telSource.getStatus());
		}

		List<TelSource> list = new ArrayList<TelSource>();

		int count = telSourceService.queryTelSourceCount(telSource);
		if (params.size() == 0 && count > 0) {
			list = telSourceService.queryTelSource(telSource, curNum, pageNum);
		}
		if (params.size() > 0 && count >= 0) {
			list = telSourceService.queryTelSource(params, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改电话来源码信息
	 */
	public void updateTelSource(Context ctx) throws CoreException {

		Gson json = new Gson();

		TelSource telSource = json.fromJson(json.toJson(ctx.getDataMap()), TelSource.class);

		String userId = ctx.getData("userId");
		String autoId = ctx.getData("autoId");
		telSource.setLstUpdUser(userId);

		try {
			String status = telSourceService.queryTelSourceStatus(autoId);
			if (telSource.getStatus().equals(status)) {
				telSourceService.updateTelSourceWithoutStatus(telSource);
			} else {
				telSourceService.updateTelSource(telSource);
			}
		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除电话来源码信息
	 */
	public void removeTelSource(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			telSourceService.deleteTelSource(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增电话来源码信息
	 */
	public void saveTelSource(Context ctx) throws CoreException {

		Gson json = new Gson();

		TelSource telSource = json.fromJson(json.toJson(ctx.getDataMap()), TelSource.class);

		telSource.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		telSource.setCrtUser(userId);
		telSource.setLstUpdUser(userId);

		TelSource queryPassCode = telSourceService.queryTelSource(telSource);

		if (queryPassCode != null) {
			ctx.setData("isFailed", true);
			return;
		}

		try {

			if (telSource.getStatus().equals("1")) {

				telSourceService.saveTelSourceStart(telSource);

			} else if (telSource.getStatus().equals("0")) {

				telSourceService.saveTelSourceEnd(telSource);

			} else {

			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}