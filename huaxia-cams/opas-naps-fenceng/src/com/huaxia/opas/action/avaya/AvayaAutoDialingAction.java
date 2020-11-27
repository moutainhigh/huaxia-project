package com.huaxia.opas.action.avaya;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.avaya.Avaya;
import com.huaxia.opas.service.avaya.AvayaAutoDialingService;
import com.huaxia.opas.util.SequenceUtil;

public class AvayaAutoDialingAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(AvayaAutoDialingAction.class);

	@Resource(name = "avayaAutoDialingService")
	private AvayaAutoDialingService avayaAutoDialingService;

	/**
	 * 查询 queryByConditionAvayaAutoDialing findAllAvayaAutoDialing
	 */

	// 分页查询？？？
	public void queryAvayaAutoDialing(Context ctx) throws CoreException {

		Gson gson = new Gson();
		Avaya avaya = gson.fromJson(gson.toJson(ctx.getDataMap()), Avaya.class);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		// 查询参数
		List<Avaya> list = new ArrayList<Avaya>();
		int count = avayaAutoDialingService.queryAvayaAutoDialingLimitCount(avaya);

		if (count > 0) {
			list = avayaAutoDialingService.queryAvayaAutoDialingLimit(avaya, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 删除avaya信息
	 */
	public void delAvayaAutoDialing(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String Id = (String) map.get("id");

		try {

			avayaAutoDialingService.del(Id);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 修改
	 */
	public void updateAvayaAutoDialing(Context ctx) throws CoreException {

		Gson json = new Gson();
		Avaya avaya = json.fromJson(json.toJson(ctx.getDataMap()), Avaya.class);
		String Id = ctx.getData("id");
		avaya.setId(Id);
		String Ip = ctx.getData("ip");
		if (StringUtils.isNotBlank(Ip)) {
			avaya.setIp(Ip);
		}

		try {
			avayaAutoDialingService.update(avaya);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增
	 */
	public void saveAvayaAutoDialing(Context ctx) throws CoreException {

		Gson json = new Gson();
		Avaya avaya = json.fromJson(json.toJson(ctx.getDataMap()), Avaya.class);

		avaya.setId(SequenceUtil.getUUID());

		try {
			avayaAutoDialingService.add(avaya);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	public void checkData1(Context ctx) throws CoreException {
		Avaya ava = new Avaya();
		String st = "";
		// 校验客服电脑IP
		String Ip = (String) ctx.getDataMap().get("ip");
		if (StringUtils.isNotBlank(Ip)) {
			ava.setIp(Ip);
			try {
				int count = avayaAutoDialingService.queryAvayaAutoDialingLimitCount(ava);
				if (count > 0) {
					st = "客服电脑Ip信息重复!";
					ctx.setData("exMsg", st);
					ctx.setData("isSuccess", false);
				} else {
					ctx.setData("isSuccess", true);
				}
			} catch (NullPointerException e) {
				logger.error("[自动拨号Avaya信息录入]：校验客服电脑IP异常", e);
			}
		}

		// 校验审核系统Id
		String userid = (String) ctx.getDataMap().get("userid");
		if (StringUtils.isNotBlank(userid)) {
			ava.setuserid(userid);
			try {
				int count = avayaAutoDialingService.queryAvayaAutoDialingLimitCount(ava);
				if (count > 0) {
					st = "审核系统Id信息重复!";
					ctx.setData("exMsg", st);
					ctx.setData("isSuccess", false);
				} else {
					ctx.setData("isSuccess", true);
				}
			} catch (NullPointerException e) {
				logger.error("[自动拨号Avaya信息录入]：校验审核系统Id异常", e);
			}
		}

		// 校验客服系统Id
		String avayaLoginId = (String) ctx.getDataMap().get("avayaLoginId");
		if (StringUtils.isNotBlank(avayaLoginId)) {
			ava.setAvayaLoginId(avayaLoginId);
			try {
				int count = avayaAutoDialingService.queryAvayaAutoDialingLimitCount(ava);
				if (count > 0) {
					st = "登录账号信息重复!";
					ctx.setData("exMsg", st);
					ctx.setData("isSuccess", false);
				} else {
					ctx.setData("isSuccess", true);
				}
			} catch (NullPointerException e) {
				logger.error("[自动拨号Avaya信息录入]：校验客服系统Id异常", e);
			}

		}

		// 校验分机号
		String avayaStationId = (String) ctx.getDataMap().get("avayaStationId");
		if (StringUtils.isNotBlank(avayaStationId)) {
			ava.setAvayaStationId(avayaStationId);
			try {
				int count = avayaAutoDialingService.queryAvayaAutoDialingLimitCount(ava);
				if (count > 0) {
					st = "分机号信息重复!";
					ctx.setData("exMsg", st);
					ctx.setData("isSuccess", false);
				} else {
					ctx.setData("isSuccess", true);
				}
			} catch (NullPointerException e) {
				logger.error("[自动拨号Avaya信息录入]：校验分机号异常", e);
			}
		}
		if ("".equals(st)) {
			ctx.setData("isSuccess", true);
		}
	}
}
