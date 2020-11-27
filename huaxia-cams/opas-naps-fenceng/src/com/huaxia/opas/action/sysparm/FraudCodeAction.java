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
import com.huaxia.opas.domain.sysparm.FraudCode;
import com.huaxia.opas.service.sysparm.FraudCodeService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 欺诈结果原因码
 * 
 * @author liudi
 * @since 2017-02-26
 * @version 1.0
 */

public class FraudCodeAction implements Action {

	@Resource(name = "fraudCodeService")
	private FraudCodeService fraudCodeService;

	/**
	 * 获取欺诈原因信息
	 */
	public void queryFraudCode(Context ctx) throws CoreException {
		Gson gson = new Gson();
		FraudCode fraudCode = gson.fromJson(gson.toJson(ctx.getDataMap()), FraudCode.class);

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
		if (fraudCode.getFraudCode() != null) {
			params.put("fraudCode", fraudCode.getFraudCode());
		}
		if (fraudCode.getFraudName() != null) {
			params.put("fraudName", fraudCode.getFraudName());
		}
		if (fraudCode.getStatus() != null) {
			params.put("status", fraudCode.getStatus());
		}
		List<FraudCode> list = new ArrayList<FraudCode>();

		int count = fraudCodeService.queryFraudCodeCount(fraudCode);
		if (params.size() == 0 && count > 0) {
			list = fraudCodeService.queryFraudCode(fraudCode, curNum, pageNum);
		}
		if (params.size() > 0 && count >= 0) {
			list = fraudCodeService.queryFraudCode(params, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改工作组信息
	 */
	public void updateFraudCode(Context ctx) throws CoreException {

		Gson json = new Gson();

		FraudCode fraudCode = json.fromJson(json.toJson(ctx.getDataMap()), FraudCode.class);

		String userId = ctx.getData("userId");
		String autoId = ctx.getData("autoId");
		fraudCode.setLstUpdUser(userId);

		try {
			String status = fraudCodeService.selectFraudCode(autoId);
			if (fraudCode.getStatus().equals(status)) {
				fraudCodeService.updateFraudCodeWithoutStatus(fraudCode);
			} else {
				fraudCodeService.updateFraudCode(fraudCode);
			}
		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除机构信息
	 */
	public void removeFraudCode(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			fraudCodeService.deleteFraudCode(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增人员信息
	 */
	public void saveFraudCode(Context ctx) throws CoreException {

		Gson json = new Gson();

		FraudCode fraudCode = json.fromJson(json.toJson(ctx.getDataMap()), FraudCode.class);

		fraudCode.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		fraudCode.setCrtUser(userId);
		fraudCode.setLstUpdUser(userId);

		FraudCode queryFraudCode = fraudCodeService.queryFraudCode(fraudCode);

		if (queryFraudCode != null) {
			ctx.setData("isFailed", true);
			return;
		}

		try {

			if (fraudCode.getStatus().equals("1")) {

				fraudCodeService.saveFraudCodeStart(fraudCode);

			} else if (fraudCode.getStatus().equals("0")) {

				fraudCodeService.saveFraudCodeEnd(fraudCode);

			} else {

			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}