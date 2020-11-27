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
import com.huaxia.opas.domain.sysparm.ApproveReasonCode;
import com.huaxia.opas.service.sysparm.ApproveReasonCodeService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 审批原因码
 * @author liudi  wangdebin
 * @since 2017-03-06
 * @version 1.0
 */
public class ApproveReasonCodeAction implements Action {

	@Resource(name = "approveReasonCodeService")
	private ApproveReasonCodeService approveReasonCodeService;

	/**
	 * 获取审批原因码信息
	 */
	public void queryApproveReasonCode(Context ctx) throws CoreException {
		Gson gson = new Gson();
		ApproveReasonCode approveReasonCode = gson.fromJson(gson.toJson(ctx.getDataMap()), ApproveReasonCode.class);

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
		if(ctx.getDataMap().get("dateType")!= null){
			int type = Integer.parseInt(ctx.getDataMap().get("dateType").toString()) ;
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
		if (approveReasonCode.getReasonCode()!=null){
			params.put("reasonCode", approveReasonCode.getReasonCode());
		}
		if (approveReasonCode.getReasonType()!=null){
			params.put("reasonType", approveReasonCode.getReasonType());
		}
		if (approveReasonCode.getAcctType()!=null){
			params.put("acctType", approveReasonCode.getAcctType());
		}
		if (approveReasonCode.getStatus()!=null){
			params.put("status", approveReasonCode.getStatus());
		}

		List<ApproveReasonCode> list = new ArrayList<ApproveReasonCode>();

		int count = approveReasonCodeService.queryApproveReasonCodeCount(approveReasonCode);
		if (params.size()==0 && count > 0) {
			list = approveReasonCodeService.queryApproveReasonCode(approveReasonCode, curNum, pageNum);
		}
		if (params.size()>0 && count >= 0) {
			list = approveReasonCodeService.queryApproveReasonCode(params, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}
	/*******************************
	 * @describe:获取全部审批拒绝码,赋值给下拉列表
	 * @author: wangdebin
	 * @date:2017-06-6
	 ******************************/
	public void queryAllApproveReasonCode(Context ctx) throws CoreException {
		String acctType = (String) ctx.getDataMap().get("acctType");
		String status = (String) ctx.getDataMap().get("status");
		// 查询参数
		Map<String, String> params = new HashMap<String, String>();
		if (acctType!=null){
			params.put("acctType", acctType);
		}
		if (status!=null){
			params.put("status", status);
		}
		//2拒绝  
		params.put("reasonType", "2");
		List<ApproveReasonCode> list = new ArrayList<ApproveReasonCode>();
		list = approveReasonCodeService.queryAllApproveReason(params);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("reasonList", list);
		ctx.setDataMap(dataMap);
	}
 
	/**
	 * 修改审批原因码信息
	 */
	public void updateApproveReasonCode(Context ctx) throws CoreException {

		Gson json = new Gson();

		ApproveReasonCode approveReasonCode = json.fromJson(json.toJson(ctx.getDataMap()), ApproveReasonCode.class);
		

		String userId = ctx.getData("userId");
		String autoId = ctx.getData("autoId");
		approveReasonCode.setLstUpdUser(userId);

		try {
			String status = approveReasonCodeService.queryAllApproveReasonCodeStatus(autoId);
			if(approveReasonCode.getStatus().equals(status)){
				approveReasonCodeService.updateApproveReasonCodeWithoutStatus(approveReasonCode);
			}else{
				approveReasonCodeService.updateApproveReasonCode(approveReasonCode);
			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除审批原因码信息
	 */
	public void removeApproveReasonCode(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			approveReasonCodeService.deleteApproveReasonCode(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增审批原因码信息
	 */
	public void saveApproveReasonCode(Context ctx) throws CoreException {
		
		Gson json = new Gson();
	
		ApproveReasonCode approveReasonCode = json.fromJson(json.toJson(ctx.getDataMap()), ApproveReasonCode.class);

		approveReasonCode.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		approveReasonCode.setCrtUser(userId);
		approveReasonCode.setLstUpdUser(userId);
		
		ApproveReasonCode queryReasonCode = approveReasonCodeService.queryApproveReasonCode(approveReasonCode);
		if(queryReasonCode != null){
				ctx.setData("isFailed", true);
				return;
		}

		try {
			
			if(approveReasonCode.getStatus().equals("1") ){
				
				approveReasonCodeService.saveApproveReasonCodeStart(approveReasonCode);
				
			}else if(approveReasonCode.getStatus().equals("0") ){

				approveReasonCodeService.saveApproveReasonCodeEnd(approveReasonCode);
			
			}else{
				
			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}