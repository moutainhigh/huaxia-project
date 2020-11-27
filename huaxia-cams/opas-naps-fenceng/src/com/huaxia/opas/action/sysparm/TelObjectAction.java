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
import com.huaxia.opas.domain.sysparm.TelObject;
import com.huaxia.opas.service.sysparm.TelObjectService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 照会对象参数
 * 
 * @author liudi
 * @since 2017-02-28
 * @version 1.0
 */
public class TelObjectAction implements Action {

	@Resource(name = "telObjectService")
	private TelObjectService telObjectService;

	/**
	 * 获取照会对象码信息
	 */
	public void queryTelObject(Context ctx) throws CoreException {
		Gson gson = new Gson();
		TelObject telObject = gson.fromJson(gson.toJson(ctx.getDataMap()), TelObject.class);

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
		if (telObject.getTelObjectCode() != null) {
			params.put("telObjectCode", telObject.getTelObjectCode());
		}
		if (telObject.getTelObjectDesc() != null) {
			params.put("telObjectDesc", telObject.getTelObjectDesc());
		}
		if (telObject.getAcctType() != null) {
			params.put("acctType", telObject.getAcctType());
		}
		if (telObject.getStatus() != null) {
			params.put("status", telObject.getStatus());
		}

		List<TelObject> list = new ArrayList<TelObject>();

		int count = telObjectService.queryTelObjectCount(telObject);
		if (params.size() == 0 && count > 0) {
			list = telObjectService.queryTelObject(telObject, curNum, pageNum);
		}
		if (params.size() > 0 && count >= 0) {
			list = telObjectService.queryTelObject(params, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改照会对象码信息
	 */
	public void updateTelObject(Context ctx) throws CoreException {

		Gson json = new Gson();

		TelObject telObject = json.fromJson(json.toJson(ctx.getDataMap()), TelObject.class);

		String userId = ctx.getData("userId");
		String autoId = ctx.getData("autoId");
		telObject.setLstUpdUser(userId);

		try {
			String status = telObjectService.queryTelObjectStatus(autoId);
			if(telObject.getStatus().equals(status)){
				telObjectService.updateTelObjectWithoutStatus(telObject);
			}else{
				telObjectService.updateTelObject(telObject);
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
	public void removeTelObject(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			telObjectService.deleteTelObject(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增照会对象码信息
	 */
	public void saveTelObject(Context ctx) throws CoreException {

		Gson json = new Gson();

		TelObject telObject = json.fromJson(json.toJson(ctx.getDataMap()), TelObject.class);

		telObject.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		telObject.setCrtUser(userId);
		telObject.setLstUpdUser(userId);

		TelObject queryPassCode = telObjectService.queryTelObject(telObject);

		if (queryPassCode != null) {
			ctx.setData("isFailed", true);
			return;
		}

		try {

			if (telObject.getStatus().equals("1")) {

				telObjectService.saveTelObjectStart(telObject);

			} else if (telObject.getStatus().equals("0")) {

				telObjectService.saveTelObjectEnd(telObject);

			} else {

			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}