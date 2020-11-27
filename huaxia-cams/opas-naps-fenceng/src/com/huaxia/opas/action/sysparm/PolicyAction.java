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
import com.huaxia.opas.domain.sysparm.Policy;
import com.huaxia.opas.service.sysparm.PolicyService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 政策码信息维护
 * 
 * @author liudi
 * @since 2017-03-08
 * @version 1.0
 */
public class PolicyAction implements Action {

	@Resource(name = "policyService")
	private PolicyService policyService;

	/**
	 * 获取政策码信息
	 */
	public void queryPolicy(Context ctx) throws CoreException {
		Gson gson = new Gson();
		Policy policy = gson.fromJson(gson.toJson(ctx.getDataMap()), Policy.class);

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
		if (policy.getPolicyCode() != null) {
			params.put("policyCode", policy.getPolicyCode());
		}
		if (policy.getPolicyName() != null) {
			params.put("policyName", policy.getPolicyName());
		}
		if (policy.getAcctType() != null) {
			params.put("acctType", policy.getAcctType());
		}
		if (policy.getStatus() != null) {
			params.put("status", policy.getStatus());
		}

		List<Policy> list = new ArrayList<Policy>();

		int count = policyService.queryPolicyCount(policy);
		if (params.size() == 0 && count > 0) {
			list = policyService.queryPolicy(policy, curNum, pageNum);
		}
		if (params.size() > 0 && count >= 0) {
			list = policyService.queryPolicy(params, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改政策码信息
	 */
	public void updatePolicy(Context ctx) throws CoreException {

		Gson json = new Gson();

		Policy policy = json.fromJson(json.toJson(ctx.getDataMap()), Policy.class);

		String userId = ctx.getData("userId");
		String autoId = ctx.getData("autoId");
		policy.setLstUpdUser(userId);

		try {
			String status = policyService.queryPolicyStatus(autoId);
			if (policy.getStatus().equals(status)) {
				policyService.updatePolicyWithoutStatus(policy);
			} else {
				policyService.updatePolicy(policy);
			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除政策码信息
	 */
	public void removePolicy(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			policyService.deletePolicy(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增政策码信息
	 */
	public void savePolicy(Context ctx) throws CoreException {

		Gson json = new Gson();

		Policy policy = json.fromJson(json.toJson(ctx.getDataMap()), Policy.class);

		policy.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		policy.setCrtUser(userId);
		policy.setLstUpdUser(userId);

		Policy queryPassCode = policyService.queryPolicy(policy);

		if (queryPassCode != null) {
			ctx.setData("isFailed", true);
			return;
		}

		try {

			if (policy.getStatus().equals("1")) {

				policyService.savePolicyStart(policy);

			} else if (policy.getStatus().equals("0")) {

				policyService.savePolicyEnd(policy);

			} else {

			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}