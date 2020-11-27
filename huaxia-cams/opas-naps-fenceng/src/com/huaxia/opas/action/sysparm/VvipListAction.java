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
import com.huaxia.opas.domain.sysparm.VvipList;
import com.huaxia.opas.domain.sysparm.VvipListHistory;
import com.huaxia.opas.service.sysparm.VvipListService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * VVIP名单信息参数
 * 
 * @author liudi
 * @since 2017-03-23
 * @version 1.0
 */
public class VvipListAction implements Action {

	@Resource(name = "vvipListService")
	private VvipListService vvipListService;

	/**
	 * 获取VVIP名单信息信息
	 */
	public void queryVvipList(Context ctx) throws CoreException {
		Gson gson = new Gson();
		VvipList vvipList = gson.fromJson(gson.toJson(ctx.getDataMap()), VvipList.class);

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
		if (vvipList.getAppName() != null) {
			params.put("appName", vvipList.getAppName());
		}
		if (vvipList.getIdNo() != null) {
			params.put("idNo", vvipList.getIdNo());
		}
		if (vvipList.getStatus() != null) {
			params.put("status", vvipList.getStatus());
		}

		List<VvipList> list = new ArrayList<VvipList>();

		int count = vvipListService.queryVvipListCount(vvipList);
		if (params.size() == 0 && count > 0) {
			list = vvipListService.queryVvipList(vvipList, curNum, pageNum);
		}
		if (params.size() > 0 && count >= 0) {
			list = vvipListService.queryVvipList(params, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 获取历史修改信息
	 */
	public void queryHistoryUpdate(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<VvipListHistory> list = new ArrayList<VvipListHistory>();

		int count = vvipListService.queryVvipListHistoryCount(autoId);

		list = vvipListService.queryHistoryUpdate(autoId, curNum, pageNum);

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改VVIP名单信息信息
	 */
	public void updateVvipList(Context ctx) throws CoreException {

		String autoId = (String) ctx.getDataMap().get("autoId");
		Gson json = new Gson();

		VvipList vvipList = json.fromJson(json.toJson(ctx.getDataMap()), VvipList.class);

		String userId = ctx.getData("userId");

		vvipList.setLstUpdUser(userId);

		// 根据主键获取主表信息，复制到历史记录表中
		VvipList vvipList1 = vvipListService.queryByPrimaryKey(autoId);// 根据主表主键取到修改前所有项目
		VvipListHistory vvipListHistory = new VvipListHistory();// 新建修改历史类
		// 每个值分别传值
		vvipListHistory.setUuId(SequenceUtil.getUUID());
		vvipListHistory.setAutoId(vvipList1.getAutoId());
		vvipListHistory.setAppName(vvipList1.getAppName());
		vvipListHistory.setIdNo(vvipList1.getIdNo());
		vvipListHistory.setStatus(vvipList1.getStatus());
		vvipListHistory.setCrtDate(vvipList1.getCrtDate());
		vvipListHistory.setCrtUser(vvipList1.getCrtUser());
		vvipListHistory.setLstUpdDate(vvipList1.getLstUpdDate());
		vvipListHistory.setLstUpdUser(vvipList1.getLstUpdUser());
		vvipListHistory.setStartDate(vvipList1.getStartDate());
		vvipListHistory.setStopDate(vvipList1.getStopDate());

		try {
			// 插入历史修改表
			vvipListService.saveVvipListHistory(vvipListHistory);
			// 更新主表
			vvipListService.updateVvipList(vvipList);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除VVIP名单信息信息
	 */
	public void removeVvipList(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		ArrayList<String> ids = (ArrayList<String>) map.get("ids");
		VvipList vvipList = new VvipList();
		vvipList.setIds(ids);

		try {

			vvipListService.deleteVvipList(vvipList);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 新增VVIP名单信息信息
	 */
	public void saveVvipList(Context ctx) throws CoreException {

		Gson json = new Gson();

		VvipList vvipList = json.fromJson(json.toJson(ctx.getDataMap()), VvipList.class);

		vvipList.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		vvipList.setCrtUser(userId);
		vvipList.setLstUpdUser(userId);

		VvipList idNo = vvipListService.queryVvipList(vvipList);

		if (idNo != null) {
			ctx.setData("isFailed", true);
			return;
		}

		try {

			if (vvipList.getStatus().equals("1")) {

				vvipListService.saveVvipListStart(vvipList);

			} else if (vvipList.getStatus().equals("0")) {

				vvipListService.saveVvipListEnd(vvipList);

			} else {

			}

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}