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
import com.huaxia.opas.domain.sysparm.SysparmRate;
import com.huaxia.opas.service.sysparm.SysparmRateService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 利率差异化维护
 * @author liudi
 * @since 2017-03-17
 * @version 1.0
 */
public class SysparmRateAction implements Action {

	@Resource(name = "sysparmRateService")
	private SysparmRateService sysparmRateService;

	/**
	 * 利率差异化信息
	 */
	public void querySysparmRate(Context ctx) throws CoreException {
		Gson gson = new Gson();
		SysparmRate sysparmRate = gson.fromJson(gson.toJson(ctx.getDataMap()), SysparmRate.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		
		// 查询参数
		List<SysparmRate> list = new ArrayList<SysparmRate>();

		int count = sysparmRateService.querySysparmRateCount(sysparmRate);
		
		if(count>0){
			list = sysparmRateService.querySysparmRate(sysparmRate, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改利率差异化信息
	 */
	public void updateSysparmRate(Context ctx) throws CoreException {

		Gson json = new Gson();

		SysparmRate sysparmRate = json.fromJson(json.toJson(ctx.getDataMap()), SysparmRate.class);
		

		/*String userId = ctx.getData("userId");
		
		sysparmRate.setLstUpdUser(userId);*/

		try {

			sysparmRateService.updateSysparmRate(sysparmRate);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除利率差异化信息
	 */
	public void removeSysparmRate(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			sysparmRateService.deleteSysparmRate(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 全部删除利率差异化信息
	 */
	public void removeAllSysparmRate(Context ctx) throws CoreException {


		try {

			sysparmRateService.deleteAllSysparmRate();

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}
	
	/**
	 * 新增利率差异化信息
	 */
	public void saveSysparmRate(Context ctx) throws CoreException {
		
		Gson json = new Gson();
	
		SysparmRate sysparmRate = json.fromJson(json.toJson(ctx.getDataMap()), SysparmRate.class);

		sysparmRate.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		sysparmRate.setCrtUser(userId);
		/*sysparmRate.setLstUpdUser(userId);*/
		//判断利率代码是否存在，如已存在，报错
		SysparmRate query = sysparmRateService.querySysparmRate(sysparmRate);
		
		if(query != null){
			ctx.setData("isFailed", true);
			return;
		}

		try {
				
				sysparmRateService.saveSysparmRate(sysparmRate);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}