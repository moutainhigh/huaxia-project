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
import com.huaxia.opas.domain.sysparm.SysparmRepayLimitConf;
import com.huaxia.opas.service.sysparm.SysparmRepayLimitConfService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 最低还款额配置
 * 
 * @author liudi
 * @since 2017-03-20
 * @version 1.0
 */
public class SysparmRepayLimitConfAction implements Action {

	@Resource(name = "sysparmRepayLimitConfService")
	private SysparmRepayLimitConfService sysparmRepayLimitConfService;

	/**
	 * 最低还款额信息
	 */
	public void querySysparmRepayLimitConf(Context ctx) throws CoreException {
		Gson gson = new Gson();
		SysparmRepayLimitConf sysparmRepayLimitConf = gson.fromJson(gson.toJson(ctx.getDataMap()),
				SysparmRepayLimitConf.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		// 查询参数
		List<SysparmRepayLimitConf> list = new ArrayList<SysparmRepayLimitConf>();

		int count = sysparmRepayLimitConfService.querySysparmRepayLimitConfCount(sysparmRepayLimitConf);

		if (count > 0) {
			list = sysparmRepayLimitConfService.querySysparmRepayLimitConf(sysparmRepayLimitConf, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改最低还款额信息
	 */
	public void updateSysparmRepayLimitConf(Context ctx) throws CoreException {

		Gson json = new Gson();

		SysparmRepayLimitConf sysparmRepayLimitConf = json.fromJson(json.toJson(ctx.getDataMap()),
				SysparmRepayLimitConf.class);

		/*
		 * String userId = ctx.getData("userId");
		 * 
		 * sysparmRepayLimitConf.setLstUpdUser(userId);
		 */

		// 判断最低还款额比例代码是否存在，如不存在，报错
		SysparmRepayLimitConf query1 = sysparmRepayLimitConfService.querySysparmRepayLimitConf1(sysparmRepayLimitConf);

		if (query1 == null) {
			ctx.setData("isFailed1", true);
			return;
		}
		// 判断最低还款额比例代码状态是否停用，如已停用，报错
		SysparmRepayLimitConf query2 = sysparmRepayLimitConfService.querySysparmRepayLimitConf3(sysparmRepayLimitConf);

		if ("0".equals(query2.getStatus())) {
			ctx.setData("isFailed2", true);
			return;
		}

		try {

			sysparmRepayLimitConfService.updateSysparmRepayLimitConf(sysparmRepayLimitConf);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除最低还款额信息
	 */
	public void removeSysparmRepayLimitConf(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			sysparmRepayLimitConfService.deleteSysparmRepayLimitConf(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 全部删除最低还款额信息
	 */
	public void removeAllSysparmRepayLimitConf(Context ctx) throws CoreException {

		try {

			sysparmRepayLimitConfService.deleteAllSysparmRepayLimitConf();

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增最低还款额信息
	 */
	public void saveSysparmRepayLimitConf(Context ctx) throws CoreException {

		Gson json = new Gson();

		SysparmRepayLimitConf sysparmRepayLimitConf = json.fromJson(json.toJson(ctx.getDataMap()),
				SysparmRepayLimitConf.class);

		sysparmRepayLimitConf.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		sysparmRepayLimitConf.setCrtUser(userId);
		/* sysparmRepayLimitConf.setLstUpdUser(userId); */
		// 判断最低还款额比例代码是否存在，如不存在，报错
		SysparmRepayLimitConf query1 = sysparmRepayLimitConfService.querySysparmRepayLimitConf1(sysparmRepayLimitConf);

		if (query1 == null) {
			ctx.setData("isFailed1", true);
			return;
		}
		// 判断最低还款额客户分层结果标签是否存在，如已存在，报错
		SysparmRepayLimitConf query2 = sysparmRepayLimitConfService.querySysparmRepayLimitConf2(sysparmRepayLimitConf);

		if (query2 != null) {
			ctx.setData("isFailed2", true);
			return;
		}

		// 判断最低还款额比例代码状态是否停用，如已停用，报错
		SysparmRepayLimitConf query3 = sysparmRepayLimitConfService.querySysparmRepayLimitConf3(sysparmRepayLimitConf);

		if ("0".equals(query3.getStatus())) {
			ctx.setData("isFailed3", true);
			return;
		}
		try {

			sysparmRepayLimitConfService.saveSysparmRepayLimitConf(sysparmRepayLimitConf);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}