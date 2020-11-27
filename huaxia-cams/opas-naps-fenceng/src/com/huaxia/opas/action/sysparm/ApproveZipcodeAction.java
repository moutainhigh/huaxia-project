package com.huaxia.opas.action.sysparm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.ApproveZipcode;
import com.huaxia.opas.service.sysparm.ApproveZipcodeService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 邮编区号维护
 * 
 * @author liudi
 * @since 2017-03-16
 * @version 1.0
 */
public class ApproveZipcodeAction implements Action {

	@Resource(name = "approveZipcodeService")
	private ApproveZipcodeService approveZipcodeService;

	/**
	 * 获取邮编区号信息
	 */
	public void queryApproveZipcode(Context ctx) throws CoreException {
		Gson gson = new Gson();
		ApproveZipcode approveZipcode = gson.fromJson(gson.toJson(ctx.getDataMap()), ApproveZipcode.class);

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
				field = "REMOTE_START_DATE";
				break;
			case 4:
				field = "REMOTE_STOP_DATE";
				break;
			case 5:
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
		if (approveZipcode.getZipCode() != null) {
			params.put("zipCode", approveZipcode.getZipCode());
		}
		if (approveZipcode.getAcctType() != null) {
			params.put("acctType", approveZipcode.getAcctType());
		}
		if (approveZipcode.getProvince() != null) {
			params.put("province", approveZipcode.getProvince());
		}
		if (approveZipcode.getCity() != null) {
			params.put("city", approveZipcode.getCity());
		}
		if (approveZipcode.getTelNo() != null) {
			params.put("telNo", approveZipcode.getTelNo());
		}
		if (approveZipcode.getStatus() != null) {
			params.put("status", approveZipcode.getStatus());
		}
		if (approveZipcode.getRemoteMarket() != null) {
			params.put("remoteMarket", approveZipcode.getRemoteMarket());
		}
		if (approveZipcode.getRemoteMarketCity() != null) {
			params.put("remoteMarketCity", approveZipcode.getRemoteMarketCity());
		}
		if (approveZipcode.getRemoteMarketStatus() != null) {
			params.put("remoteMarketStatus", approveZipcode.getRemoteMarketStatus());
		}

		List<ApproveZipcode> list = new ArrayList<ApproveZipcode>();

		int count = approveZipcodeService.queryApproveZipcodeCount(approveZipcode);
		if (params.size() == 0 && count > 0) {
			list = approveZipcodeService.queryApproveZipcode(approveZipcode, curNum, pageNum);
		}
		if (params.size() > 0 && count >= 0) {
			list = approveZipcodeService.queryApproveZipcode(params, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改邮编区号信息
	 */
	public void updateApproveZipcode(Context ctx) throws CoreException {

		Gson json = new Gson();
		Date date = new Date();

		ApproveZipcode approveZipcode = json.fromJson(json.toJson(ctx.getDataMap()), ApproveZipcode.class);

		String userId = ctx.getData("userId");
		String autoId = ctx.getData("autoId");
		approveZipcode.setLstUpdUser(userId);
		approveZipcode.setLstUpdDate(date);
		ApproveZipcode approveZipcode1 = approveZipcodeService.selectStatus(autoId);
		try {

			// 网点状态：启用
			if ("1".equals(approveZipcode.getStatus())) {
				// 异地城市：是
				if ("1".equals(approveZipcode.getRemoteMarket())) {
					// 异地城市状态：启用
					if ("1".equals(approveZipcode.getRemoteMarketStatus())) {
						approveZipcode.setStartDate(date);
						approveZipcode.setRemoteStartDate(date);
						if ("1".equals(approveZipcode1.getStatus())) {
							if ("1".equals(approveZipcode1.getRemoteMarketStatus())) {
								approveZipcodeService.updateApproveZipcodeWithoutAllStatus(approveZipcode);
							} else {
								approveZipcodeService.updateApproveZipcodeWithoutStatus(approveZipcode);
							}
						} else {
							if ("1".equals(approveZipcode1.getRemoteMarketStatus())) {
								approveZipcodeService.updateApproveZipcodeWithoutRemoteStatus(approveZipcode);
							} else {
								approveZipcodeService.updateApproveZipcode(approveZipcode);
							}
						}
						// 异地城市状态：停用
					} else if ("0".equals(approveZipcode.getRemoteMarketStatus())) {
						approveZipcode.setStartDate(date);
						approveZipcode.setRemoteStopDate(date);
						if ("1".equals(approveZipcode1.getStatus())) {
							if ("0".equals(approveZipcode1.getRemoteMarketStatus())) {
								approveZipcodeService.updateApproveZipcodeWithoutAllStatus(approveZipcode);
							} else {
								approveZipcodeService.updateApproveZipcodeWithoutStatus(approveZipcode);
							}
						} else {
							if ("0".equals(approveZipcode1.getRemoteMarketStatus())) {
								approveZipcodeService.updateApproveZipcodeWithoutRemoteStatus(approveZipcode);
							} else {
								approveZipcodeService.updateApproveZipcode(approveZipcode);
							}
						}
					} else {
						approveZipcode.setStartDate(date);
						if ("1".equals(approveZipcode1.getStatus())) {
							approveZipcodeService.updateApproveZipcodeWithoutStatus(approveZipcode);
						} else {
							approveZipcodeService.updateApproveZipcode(approveZipcode);
						}
					}
					// 异地城市：否
				} else {

					approveZipcode.setStartDate(date);
					approveZipcode.setRemoteMarketCity("");
					approveZipcode.setRemoteStartDate(null);
					approveZipcode.setRemoteStopDate(null);
					if ("1".equals(approveZipcode1.getStatus())) {
						approveZipcodeService.updateApproveZipcodeWithoutStatus(approveZipcode);
					} else {
						approveZipcodeService.updateApproveZipcode(approveZipcode);
					}
				}
				// 网点状态：停用
			} else if ("0".equals(approveZipcode.getStatus())) {
				// 异地城市：是
				if ("1".equals(approveZipcode.getRemoteMarket())) {
					// 异地城市状态：启用
					if ("1".equals(approveZipcode.getRemoteMarketStatus())) {
						approveZipcode.setStopDate(date);
						approveZipcode.setRemoteStartDate(date);
						if ("0".equals(approveZipcode1.getStatus())) {
							if ("1".equals(approveZipcode1.getRemoteMarketStatus())) {
								approveZipcodeService.updateApproveZipcodeWithoutAllStatus(approveZipcode);
							} else {
								approveZipcodeService.updateApproveZipcodeWithoutStatus(approveZipcode);
							}
						} else {
							if ("1".equals(approveZipcode1.getRemoteMarketStatus())) {
								approveZipcodeService.updateApproveZipcodeWithoutRemoteStatus(approveZipcode);
							} else {
								approveZipcodeService.updateApproveZipcode(approveZipcode);
							}
						}
						// 异地城市状态：停用
					} else if ("0".equals(approveZipcode.getRemoteMarketStatus())) {

						approveZipcode.setStopDate(date);
						approveZipcode.setRemoteStopDate(date);
						if ("0".equals(approveZipcode1.getStatus())) {
							if ("0".equals(approveZipcode1.getRemoteMarketStatus())) {
								approveZipcodeService.updateApproveZipcodeWithoutAllStatus(approveZipcode);
							} else {
								approveZipcodeService.updateApproveZipcodeWithoutStatus(approveZipcode);
							}
						} else {
							if ("0".equals(approveZipcode1.getRemoteMarketStatus())) {
								approveZipcodeService.updateApproveZipcodeWithoutRemoteStatus(approveZipcode);
							} else {
								approveZipcodeService.updateApproveZipcode(approveZipcode);
							}
						}
					} else {
						approveZipcode.setStopDate(date);
						if ("0".equals(approveZipcode1.getStatus())) {
							approveZipcodeService.updateApproveZipcodeWithoutStatus(approveZipcode);
						} else {
							approveZipcodeService.updateApproveZipcode(approveZipcode);
						}
					}
					// 异地城市：否
				} else {

					approveZipcode.setStopDate(date);
					approveZipcode.setRemoteMarketCity("");
					approveZipcode.setRemoteStartDate(null);
					approveZipcode.setRemoteStopDate(null);
					if ("0".equals(approveZipcode1.getStatus())) {
						approveZipcodeService.updateApproveZipcodeWithoutStatus(approveZipcode);
					} else {
						approveZipcodeService.updateApproveZipcode(approveZipcode);
					}
				}
			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除邮编区号信息
	 */
	public void removeApproveZipcode(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			approveZipcodeService.deleteApproveZipcode(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增邮编区号信息
	 */
	public void saveApproveZipcode(Context ctx) throws CoreException {

		Gson json = new Gson();
		Date date = new Date();

		ApproveZipcode approveZipcode = json.fromJson(json.toJson(ctx.getDataMap()), ApproveZipcode.class);		
		approveZipcode.setAutoId(SequenceUtil.getUUID());
		String userId = ctx.getData("userId");
		approveZipcode.setCrtUser(userId);
		approveZipcode.setLstUpdUser(userId);

		try {
			// 网点状态：启用
			if ("1".equals(approveZipcode.getStatus())) {
				// 异地城市：是
				if ("1".equals(approveZipcode.getRemoteMarket())) {
					// 异地城市状态：启用
					if ("1".equals(approveZipcode.getRemoteMarketStatus())) {
						approveZipcode.setStartDate(date);
						approveZipcode.setStopDate(null);
						approveZipcode.setRemoteStartDate(date);
						approveZipcode.setRemoteStopDate(null);
						approveZipcodeService.saveApproveZipcode(approveZipcode);
						// 异地城市状态：停用
					} else if ("0".equals(approveZipcode.getRemoteMarketStatus())) {

						approveZipcode.setStartDate(date);
						approveZipcode.setStopDate(null);
						approveZipcode.setRemoteStartDate(null);
						approveZipcode.setRemoteStopDate(date);
						approveZipcodeService.saveApproveZipcode(approveZipcode);
					}
					// 异地城市：否
				} else if ("0".equals(approveZipcode.getRemoteMarket())) {

					approveZipcode.setStartDate(date);
					approveZipcode.setStopDate(null);
					approveZipcode.setRemoteMarketCity("");
					approveZipcode.setRemoteStartDate(null);
					approveZipcode.setRemoteStopDate(null);
					approveZipcodeService.saveApproveZipcode(approveZipcode);
				}
				// 网点状态：启用
			} else if ("0".equals(approveZipcode.getStatus())) {
				// 异地城市：是
				if ("1".equals(approveZipcode.getRemoteMarket())) {
					// 异地城市状态：启用
					if ("1".equals(approveZipcode.getRemoteMarketStatus())) {
						approveZipcode.setStartDate(null);
						approveZipcode.setStopDate(date);
						approveZipcode.setRemoteStartDate(date);
						approveZipcode.setRemoteStopDate(null);
						approveZipcodeService.saveApproveZipcode(approveZipcode);
						// 异地城市状态：停用
					} else if ("0".equals(approveZipcode.getRemoteMarketStatus())) {

						approveZipcode.setStartDate(null);
						approveZipcode.setStopDate(date);
						approveZipcode.setRemoteStartDate(null);
						approveZipcode.setRemoteStopDate(date);
						approveZipcodeService.saveApproveZipcode(approveZipcode);
					}
					// 异地城市：否
				} else if ("0".equals(approveZipcode.getRemoteMarket())) {

					approveZipcode.setStartDate(null);
					approveZipcode.setStopDate(date);
					approveZipcode.setRemoteMarketCity("");
					approveZipcode.setRemoteStartDate(null);
					approveZipcode.setRemoteStopDate(null);
					approveZipcodeService.saveApproveZipcode(approveZipcode);
				}
			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);
	}

	/**
	 * 批量删除
	 */
	public void deleteApproveZipcodes(Context ctx) throws CoreException {
		try {
			Map<String, Object> map = ctx.getDataMap();
			List<String> ids = (List<String>) map.get("ids");
			approveZipcodeService.deleteApproveZipcodes(ids);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}

	/**
	 * 全部删除
	 */
	public void deleteAllApproveZipcodes(Context ctx) throws CoreException {
		try {
			approveZipcodeService.deleteAllApproveZipcodes();
			ctx.setData("isSuccess", true);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 重复校验
	 */
	public void queryRepetition(Context ctx){	
		Gson json = new Gson();
		Date date = new Date();
		ApproveZipcode approveZipcode = json.fromJson(json.toJson(ctx.getDataMap()), ApproveZipcode.class);

		Map<String, String> map = new HashMap<String,String>();
		map.put("zipCode", approveZipcode.getZipCode());
		map.put("acctType", approveZipcode.getAcctType());
		map.put("telNo", approveZipcode.getTelNo());
		int i = approveZipcodeService.queryRepetition(map);
		if(i>0){
			//有重复数据的情况
			ctx.setData("repetition", true);
		}else{
			ctx.setData("repetition", false);
		}
		
		
		
		 		
	}
}