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
import com.huaxia.opas.domain.sysparm.TelChkConclusion;
import com.huaxia.opas.service.sysparm.TelChkConclusionService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 电核结论维护
 * 
 * @author liudi
 * @since 2017-02-28
 * @version 1.0
 */
public class TelChkConclusionAction implements Action {

	@Resource(name = "telChkConclusionService")
	private TelChkConclusionService telChkConclusionService;

	/**
	 * 获取电核结论信息
	 */
	public void queryTelChkConclusion(Context ctx) throws CoreException {
		Gson gson = new Gson();
		TelChkConclusion telChkConclusion = gson.fromJson(gson.toJson(ctx.getDataMap()), TelChkConclusion.class);

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
		if (telChkConclusion.getTelChkConclusionCode() != null) {
			params.put("telChkConclusionCode", telChkConclusion.getTelChkConclusionCode());
		}
		if (telChkConclusion.getTelChkConclusionDesc() != null) {
			params.put("telChkConclusionDesc", telChkConclusion.getTelChkConclusionDesc());
		}
		if (telChkConclusion.getAcctType() != null) {
			params.put("acctType", telChkConclusion.getAcctType());
		}
		if (telChkConclusion.getStatus() != null) {
			params.put("status", telChkConclusion.getStatus());
		}

		List<TelChkConclusion> list = new ArrayList<TelChkConclusion>();

		int count = telChkConclusionService.queryTelChkConclusionCount(telChkConclusion);
		if (params.size() == 0 && count > 0) {
			list = telChkConclusionService.queryTelChkConclusion(telChkConclusion, curNum, pageNum);
		}
		if (params.size() > 0 && count >= 0) {
			list = telChkConclusionService.queryTelChkConclusion(params, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改电核结论信息
	 */
	public void updateTelChkConclusion(Context ctx) throws CoreException {

		Gson json = new Gson();

		TelChkConclusion telChkConclusion = json.fromJson(json.toJson(ctx.getDataMap()), TelChkConclusion.class);

		String userId = ctx.getData("userId");
		String autoId = ctx.getData("autoId");
		telChkConclusion.setLstUpdUser(userId);

		try {
			String status = telChkConclusionService.queryTelChkConclusionStatus(autoId);
			if (telChkConclusion.getStatus().equals(status)) {
				telChkConclusionService.updateTelChkConclusionWithoutStatus(telChkConclusion);
			} else {
				telChkConclusionService.updateTelChkConclusion(telChkConclusion);
			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除电核结论信息
	 */
	public void removeTelChkConclusion(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			telChkConclusionService.deleteTelChkConclusion(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增电核结论信息
	 */
	public void saveTelChkConclusion(Context ctx) throws CoreException {

		Gson json = new Gson();

		TelChkConclusion telChkConclusion = json.fromJson(json.toJson(ctx.getDataMap()), TelChkConclusion.class);

		telChkConclusion.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		telChkConclusion.setCrtUser(userId);
		telChkConclusion.setLstUpdUser(userId);

		TelChkConclusion queryPassCode = telChkConclusionService.queryTelChkConclusion(telChkConclusion);

		if (queryPassCode != null) {
			ctx.setData("isFailed", true);
			return;
		}

		try {

			if (telChkConclusion.getStatus().equals("1")) {

				telChkConclusionService.saveTelChkConclusionStart(telChkConclusion);

			} else if (telChkConclusion.getStatus().equals("0")) {

				telChkConclusionService.saveTelChkConclusionEnd(telChkConclusion);

			} else {

			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}