package com.huaxia.opas.action.sysparm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.Business;
import com.huaxia.opas.service.sysparm.NetCooperativeService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 网申合作商户
 * 
 * @author lipengfei
 *
 */
public class NetCooperativeAction implements Action {
	@Resource(name = "netCooperativeService")
	private NetCooperativeService netCooperativeService;

	/**
	 * 查询
	 * 
	 * @throws CoreException
	 * @throws ParseException
	 */
	public void queryBusiness(Context ctx) throws CoreException, ParseException {
		Business business = getBusiness(ctx);
		String date = business.getCrtDate1();
		if (!"".equals(date) && date != null) {

			business.setCrtDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		}
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<Business> list = new ArrayList<Business>();
		int count = netCooperativeService.queryBusinessCount(business);
		if (count > 0) {
			list = netCooperativeService.queryBusinessList(business, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 添加
	 */
	public void saveBusiness(Context ctx) throws CoreException {
		try {
			Business business = getBusiness(ctx);
			business.setBusinessID(SequenceUtil.getUUID());
			checkData(business);
			netCooperativeService.insertBusiness(business);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 修改
	 */
	public void updateBusiness(Context ctx) throws CoreException {
		try {
			Business business = getBusiness(ctx);
			checkData(business);
			netCooperativeService.updateBusiness(business);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 删除
	 */
	public void deleteBusiness(Context ctx) throws CoreException {
		try {
			Business business = getBusiness(ctx, "ids");
			netCooperativeService.deleteBusiness(business);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}

	/**
	 * 批量启用
	 */
	public void BusinesssetStatusOK(Context ctx) throws CoreException {
		try {
			Business business = getBusiness(ctx, "ids", "status");
			netCooperativeService.BusinesssetStatusOK(business);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			ctx.setData("isSuccess", false);
			throw e;
		}
	}

	/**
	 * 批量禁用
	 */
	public void BusinesssetStatusNO(Context ctx) throws CoreException {
		try {
			Business business = getBusiness(ctx, "ids", "status");
			netCooperativeService.BusinesssetStatusNO(business);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			ctx.setData("isSuccess", false);
			throw e;
		}
	}

	/**
	 * 校验的方法
	 */
	public void checkData(Business business) throws CoreException {
		// 公司编码和模块编码要求组合唯一
		try {
			Business only = netCooperativeService.queryBusinessOnly(business);			
			if (!"".equals(only.getBusinessID())&&only.getBusinessID() != null) {
				throw new CoreException("公司编码或模块编码重复!");
			}
		} catch (NullPointerException e) {
		}
	}

	/**
	 * 公共部分的验证操作
	 */
	public Business getBusiness(Context ctx, String... strings) throws CoreException {
		List<String> list = Arrays.asList(strings);
		Map<String, Object> map = ctx.getDataMap();
		Date createTime = null;
		if (list.contains("crtDate")) {
			String createDateString = (String) map.get("crtDate");
			if (null != createDateString && !createDateString.trim().isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					createTime = sdf.parse(createDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			map.remove("crtDate");
		}
		Gson gson = new Gson();
		Business Business = gson.fromJson(gson.toJson(map), Business.class);

		String operator = (String) ctx.getData("userId");
		if (null == operator || operator.trim().isEmpty()) {
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		Business.setOperator(operator);

		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if (null == ids || ids.size() <= 0) {
					CoreException e = new CoreException("请选择需要操作的行");
					throw e;
				}
				Business.setIds(ids);
				break;

			case "status":
				String status = (String) map.get("status");
				if (null == status || (!status.trim().equals("1") && !status.trim().equals("0"))) {
					CoreException e = new CoreException("启用状态值status非法");
					throw e;
				}
				break;

			case "crtDate":
				Business.setCrtDate(createTime);
				break;

			default:
				break;
			}
		}
		return Business;
	}
	
	public void queryAllNet(Context ctx) throws Exception{
		try {
			List<Map<String,String>> netList=new ArrayList<Map<String,String>>();
			List<Map<String,String>> netList2=netCooperativeService.queryAllNet();
			Map<String,String> netMap=new HashMap<String,String>();
			netMap.put("businessNumber", "");
			netMap.put("businessName", "--请选择--");
			netList.add(netMap);
			netList.addAll(netList2);
		    Gson gson=new Gson();
			String wsCodeInfo=gson.toJson(netList);
			ctx.setData("wsCodeInfo", wsCodeInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
