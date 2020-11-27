package com.huaxia.opas.action.sysparm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.sysparm.PadLocationAddress;
import com.huaxia.opas.service.sysparm.PadLocationAddressService;
import com.huaxia.opas.util.SequenceUtil;

/**
 *  PAD定位地址Action
 * 
 * @author wenyh
 *
 */
public class PadLocationAddressAction implements Action {
	private static Logger logger = LoggerFactory.getLogger(PadLocationAddressAction.class);
	@Resource(name = "padLocationAddressService")
	private PadLocationAddressService padLocationAddressService;
	
	public PadLocationAddressService getPadLocationAddressService() {
		return padLocationAddressService;
	}

	public void setPadLocationAddressService(PadLocationAddressService padLocationAddressService) {
		this.padLocationAddressService = padLocationAddressService;
	}

	/**
	 * 查询PAD定位地址列表显示信息
	 * 
	 * @param ctx
	 */
	public void queryPadLocationAddressList(Context ctx) {
		Gson gson = new Gson();
		PadLocationAddress padLocationAddress = gson.fromJson(gson.toJson(ctx.getDataMap()), PadLocationAddress.class);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		int count = padLocationAddressService.queryPadLocationAddressCount(padLocationAddress);
		List<PadLocationAddress> list = new ArrayList<PadLocationAddress>();
		if (count > 0) {
			list = padLocationAddressService.queryPadLocationAddressList(padLocationAddress, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 添加PAD定位地址信息
	 * 
	 * @throws Exception
	 */
	public void insertPadLocationAddress(Context ctx) throws Exception {
		Gson json = new Gson();
		Map<String, Object> map = ctx.getDataMap();
		PadLocationAddress padLocationAddress = json.fromJson(json.toJson(map), PadLocationAddress.class);
		//给客户设置autoID
		padLocationAddress.setAutoID(SequenceUtil.getUUID());
		String userId = ctx.getData("userId");
		padLocationAddress.setLstUpdUser(userId);
		padLocationAddress.setCrtUser(userId);
		try {
			if ("1".equals(padLocationAddress.getStatus())) {
				padLocationAddressService.insertPadLocationAddressStart(padLocationAddress);
			} else {
				padLocationAddressService.insertPadLocationAddressEnd(padLocationAddress);
			}
		} catch (Exception e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 修改PAD定位地址信息
	 * @throws Exception
	 */
	public void updatePadLocationAddress(Context ctx) throws Exception {
		Gson json = new Gson();
		Map<String, Object> map = ctx.getDataMap();
		PadLocationAddress padLocationAddress = json.fromJson(json.toJson(map), PadLocationAddress.class);
		String autoID = ctx.getData("autoID");
		String userId = ctx.getData("userId");
		padLocationAddress.setLstUpdUser(userId);
		try {
			String status = padLocationAddressService.queryPadLocationAddressStatus(autoID);
			if((padLocationAddress.getStatus()).equals(status)){
				padLocationAddressService.updatePadLocationAddressWithoutStatus(padLocationAddress);
			}else{
				padLocationAddressService.updatePadLocationAddress(padLocationAddress);
			}
		} catch (Exception e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 批量启用PAD定位地址信息
	 * @throws Exception
	 */
	public void batchStartPadLocationAddress(Context ctx) throws Exception {
		Map<String, Object> map = ctx.getDataMap();
		List<Map<Object, Object>> padLocationAddressMaps = (List<Map<Object, Object>>) map.get("padLocationAddressObj");
		String userId = ctx.getData("userId");
		try {
			for (Map<Object, Object> padLocationAddressMap : padLocationAddressMaps) {
				padLocationAddressMap.put("status", "1");
				padLocationAddressMap.put("lstUpdUser", userId);
			}
			padLocationAddressService.batchStartPadLocationAddress(padLocationAddressMaps);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 批量停用PAD定位地址信息
	 * @throws Exception
	 */
	public void batchStopPadLocationAddress(Context ctx) throws Exception {
		Map<String, Object> map = ctx.getDataMap();
		List<Map<Object, Object>> padLocationAddressMaps = (List<Map<Object, Object>>) map.get("padLocationAddressObj");
		String userId = ctx.getData("userId");
		try {
			for (Map<Object, Object> padLocationAddressMap : padLocationAddressMaps) {
				padLocationAddressMap.put("status", "0");
				padLocationAddressMap.put("lstUpdUser", userId);
			}
			padLocationAddressService.batchStopPadLocationAddress(padLocationAddressMaps);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 批量删除
	 * @throws Exception
	 */
	public void batchDeletePadLocationAddress(Context ctx) throws Exception {
		Map<String, Object> map = ctx.getDataMap();
		List<String> autoIds = (List<String>) map.get("autoIds");
		try {
			padLocationAddressService.batchDeletePadLocationAddress(autoIds);
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}
	
}
