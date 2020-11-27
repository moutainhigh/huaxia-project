package com.huaxia.opas.action.sysparm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.SysparmRepayLimit;
import com.huaxia.opas.service.sysparm.SysparmRepayLimitService;
import com.huaxia.opas.util.SequenceUtil;

import net.sf.json.JSONObject;

/**
 * 最低还款额维护
 * 
 * @author liudi
 * @since 2017-03-20
 * @version 1.0
 */
public class SysparmRepayLimitAction implements Action {

	@Resource(name = "sysparmRepayLimitService")
	private SysparmRepayLimitService sysparmRepayLimitService;

	/**
	 * 最低还款额信息
	 */
	public void querySysparmRepayLimit(Context ctx) throws CoreException {

		JSONObject jsonObject = JSONObject.fromObject(ctx.getDataMap());
		SysparmRepayLimit sysparmRepayLimit = (SysparmRepayLimit) JSONObject.toBean(jsonObject,
				SysparmRepayLimit.class);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		// 查询参数
		List<SysparmRepayLimit> list = new ArrayList<SysparmRepayLimit>();

		if (sysparmRepayLimit.getRepayRatioValue() != null) {
			BigDecimal repayRatioValue = new BigDecimal("0");
			repayRatioValue = sysparmRepayLimit.getRepayRatioValue();
			sysparmRepayLimit.setRepayRatioValue(repayRatioValue);
		}

		int count = sysparmRepayLimitService.querySysparmRepayLimitCount(sysparmRepayLimit);

		if (count > 0) {

			list = sysparmRepayLimitService.querySysparmRepayLimit(sysparmRepayLimit, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改最低还款额信息
	 */
	public void updateSysparmRepayLimit(Context ctx) throws CoreException {

		Gson json = new Gson();

		SysparmRepayLimit sysparmRepayLimit = json.fromJson(json.toJson(ctx.getDataMap()), SysparmRepayLimit.class);

		try {

			BigDecimal repayRatioValue = new BigDecimal("0");
			repayRatioValue = sysparmRepayLimit.getRepayRatioValue();
			sysparmRepayLimit.setRepayRatioValue(repayRatioValue);
			sysparmRepayLimitService.updateSysparmRepayLimit(sysparmRepayLimit);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除最低还款额信息
	 */
	public void removeSysparmRepayLimit(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			sysparmRepayLimitService.deleteSysparmRepayLimit(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 全部删除最低还款额信息
	 */
	public void removeAllSysparmRepayLimit(Context ctx) throws CoreException {

		try {

			sysparmRepayLimitService.deleteAllSysparmRepayLimit();

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增最低还款额信息
	 */
	public void saveSysparmRepayLimit(Context ctx) throws CoreException {

		Gson json = new Gson();

		SysparmRepayLimit sysparmRepayLimit = json.fromJson(json.toJson(ctx.getDataMap()), SysparmRepayLimit.class);

		sysparmRepayLimit.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		sysparmRepayLimit.setCrtUser(userId);

		BigDecimal repayRatioValue = new BigDecimal("0");
		repayRatioValue = sysparmRepayLimit.getRepayRatioValue();
		sysparmRepayLimit.setRepayRatioValue(repayRatioValue);

		// 判断最低还款额比例代码是否存在，如已存在，报错
		SysparmRepayLimit query = sysparmRepayLimitService.querySysparmRepayLimit(sysparmRepayLimit);

		if (query != null) {
			ctx.setData("isFailed", true);
			return;
		}

		try {

			sysparmRepayLimitService.saveSysparmRepayLimit(sysparmRepayLimit);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}