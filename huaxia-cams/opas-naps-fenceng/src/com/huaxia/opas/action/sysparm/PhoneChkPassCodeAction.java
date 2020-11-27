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
import com.huaxia.opas.domain.sysparm.PhoneChkPassCode;
import com.huaxia.opas.service.sysparm.PhoneChkPassCodeService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 电核过件码
 * 
 * @author liudi 、wangdebin
 * @since 2017-02-28
 * @version 1.0
 */
public class PhoneChkPassCodeAction implements Action {

	@Resource(name = "phoneChkPassCodeService")
	private PhoneChkPassCodeService phoneChkPassCodeService;

	/**
	 * 获取电核过件码信息
	 */
	public void queryPhoneChk(Context ctx) throws CoreException {
		Gson gson = new Gson();
		PhoneChkPassCode phoneChkPassCode = gson.fromJson(gson.toJson(ctx.getDataMap()), PhoneChkPassCode.class);

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
		if (phoneChkPassCode.getPhoneChkPassCode() != null) {
			params.put("phoneChkPassCode", phoneChkPassCode.getPhoneChkPassCode());
		}
		if (phoneChkPassCode.getPhoneChkPassName() != null) {
			params.put("phoneChkPassName", phoneChkPassCode.getPhoneChkPassName());
		}
		if (phoneChkPassCode.getAcctType() != null) {
			params.put("acctType", phoneChkPassCode.getAcctType());
		}
		if (phoneChkPassCode.getStatus() != null) {
			params.put("status", phoneChkPassCode.getStatus());
		}

		List<PhoneChkPassCode> list = new ArrayList<PhoneChkPassCode>();

		int count = phoneChkPassCodeService.queryPhoneChkPassCodeCount(phoneChkPassCode);
		if (params.size() == 0 && count > 0) {
			list = phoneChkPassCodeService.queryPhoneChkPassCode(phoneChkPassCode, curNum, pageNum);
		}
		if (params.size() > 0 && count >= 0) {
			list = phoneChkPassCodeService.queryPhoneChkPassCode(params, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/*******************************
	 * @describe:获取全部电核过件码信息,赋值给下拉列表
	 * @author: wangdebin
	 * @date:2017-06-1
	 ******************************/
	public void queryAllPhoneChk(Context ctx) throws CoreException {
		String acctType = (String) ctx.getDataMap().get("acctType");
		String status = (String) ctx.getDataMap().get("status");
		// 查询参数
		Map<String, Object> params = new HashMap<String, Object>();
		if (acctType != null) {
			params.put("acctType", acctType);
		}
		if (status != null) {
			params.put("status", status);
		}
		params.put("reasonType", "2");
		List<PhoneChkPassCode> list = new ArrayList<PhoneChkPassCode>();
		list = phoneChkPassCodeService.queryAllPhoneChkPassCode(params);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("phoneList", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 修改电核过件码信息
	 */
	public void updatePhoneChk(Context ctx) throws CoreException {

		Gson json = new Gson();

		PhoneChkPassCode phoneChkPassCode = json.fromJson(json.toJson(ctx.getDataMap()), PhoneChkPassCode.class);

		String userId = ctx.getData("userId");
		String autoId = ctx.getData("autoId");
		phoneChkPassCode.setLstUpdUser(userId);

		try {
			String status = phoneChkPassCodeService.queryPhoneChkPassCodeStatus(autoId);
			if (phoneChkPassCode.getStatus().equals(status)) {
				phoneChkPassCodeService.updatePhoneChkPassCodeWithoutStatus(phoneChkPassCode);
			} else {
				phoneChkPassCodeService.updatePhoneChkPassCode(phoneChkPassCode);
			}
		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除电核过件码信息
	 */
	public void removePhoneChk(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			phoneChkPassCodeService.deletePhoneChkPassCode(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增电核过件码信息
	 */
	public void savePhoneChk(Context ctx) throws CoreException {

		Gson json = new Gson();

		PhoneChkPassCode phoneChkPassCode = json.fromJson(json.toJson(ctx.getDataMap()), PhoneChkPassCode.class);

		phoneChkPassCode.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		phoneChkPassCode.setCrtUser(userId);
		phoneChkPassCode.setLstUpdUser(userId);

		PhoneChkPassCode queryPassCode = phoneChkPassCodeService.queryPhoneChkPassCode(phoneChkPassCode);

		if (queryPassCode != null) {
			ctx.setData("isFailed", true);
			return;
		}

		try {

			if (phoneChkPassCode.getStatus().equals("1")) {

				phoneChkPassCodeService.savePhoneChkPassCodeStart(phoneChkPassCode);

			} else if (phoneChkPassCode.getStatus().equals("0")) {

				phoneChkPassCodeService.savePhoneChkPassCodeEnd(phoneChkPassCode);

			} else {

			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}