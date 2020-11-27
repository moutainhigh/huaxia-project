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
import com.huaxia.opas.domain.sysparm.SysparmRepayFreeTermConf;
import com.huaxia.opas.service.sysparm.SysparmRepayFreeTermConfService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 免息还款期配置
 * 
 * @author liudi
 * @since 2017-03-21
 * @version 1.0
 */
public class SysparmRepayFreeTermConfAction implements Action {

	@Resource(name = "sysparmRepayFreeTermConfService")
	private SysparmRepayFreeTermConfService sysparmRepayFreeTermConfService;

	/**
	 * 免息还款期信息
	 */
	public void querySysparmRepayFreeTermConf(Context ctx) throws CoreException {
		Gson gson = new Gson();
		SysparmRepayFreeTermConf sysparmRepayFreeTermConf = gson.fromJson(gson.toJson(ctx.getDataMap()),
				SysparmRepayFreeTermConf.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		// 查询参数
		List<SysparmRepayFreeTermConf> list = new ArrayList<SysparmRepayFreeTermConf>();

		int count = sysparmRepayFreeTermConfService.querySysparmRepayFreeTermConfCount(sysparmRepayFreeTermConf);

		if (count > 0) {
			list = sysparmRepayFreeTermConfService.querySysparmRepayFreeTermConf(sysparmRepayFreeTermConf, curNum,
					pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改免息还款期信息
	 */
	public void updateSysparmRepayFreeTermConf(Context ctx) throws CoreException {

		Gson json = new Gson();

		SysparmRepayFreeTermConf sysparmRepayFreeTermConf = json.fromJson(json.toJson(ctx.getDataMap()),
				SysparmRepayFreeTermConf.class);

		// 判断免息还款期代码是否存在，如不存在，报错
		SysparmRepayFreeTermConf query1 = sysparmRepayFreeTermConfService
				.querySysparmRepayFreeTermConf1(sysparmRepayFreeTermConf);

		if (query1 == null) {
			ctx.setData("isFailed", true);
			return;
		}
		// 判断免息还款期代码状态是否停用，如已停用，报错
		SysparmRepayFreeTermConf query2 = sysparmRepayFreeTermConfService
				.querySysparmRepayFreeTermConf3(sysparmRepayFreeTermConf);

		if ("0".equals(query2.getStatus())) {
			ctx.setData("isFailed3", true);
			return;
		}

		try {

			sysparmRepayFreeTermConfService.updateSysparmRepayFreeTermConf(sysparmRepayFreeTermConf);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除免息还款期配置信息
	 */
	public void removeSysparmRepayFreeTermConf(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			sysparmRepayFreeTermConfService.deleteSysparmRepayFreeTermConf(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 全部删除免息还款期配置信息
	 */
	public void removeAllSysparmRepayFreeTermConf(Context ctx) throws CoreException {

		try {

			sysparmRepayFreeTermConfService.deleteAllSysparmRepayFreeTermConf();

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增免息还款期配置信息
	 */
	public void saveSysparmRepayFreeTermConf(Context ctx) throws CoreException {

		Gson json = new Gson();

		SysparmRepayFreeTermConf sysparmRepayFreeTermConf = json.fromJson(json.toJson(ctx.getDataMap()),
				SysparmRepayFreeTermConf.class);

		sysparmRepayFreeTermConf.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		sysparmRepayFreeTermConf.setCrtUser(userId);
		/* sysparmRepayFreeTermConf.setLstUpdUser(userId); */
		// 判断免息还款期代码是否存在，如不存在，报错
		SysparmRepayFreeTermConf query1 = sysparmRepayFreeTermConfService
				.querySysparmRepayFreeTermConf1(sysparmRepayFreeTermConf);

		if (query1 == null) {
			ctx.setData("isFailed1", true);
			return;
		}
		// 判断免息还款期客户分层结果标签是否存在，如已存在，报错
		SysparmRepayFreeTermConf query2 = sysparmRepayFreeTermConfService
				.querySysparmRepayFreeTermConf2(sysparmRepayFreeTermConf);

		if (query2 != null) {
			ctx.setData("isFailed2", true);
			return;
		}

		// 判断免息还款期代码状态是否停用，如已停用，报错
		SysparmRepayFreeTermConf query3 = sysparmRepayFreeTermConfService
				.querySysparmRepayFreeTermConf3(sysparmRepayFreeTermConf);

		if ("0".equals(query3.getStatus())) {
			ctx.setData("isFailed3", true);
			return;
		}
		try {

			sysparmRepayFreeTermConfService.saveSysparmRepayFreeTermConf(sysparmRepayFreeTermConf);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}