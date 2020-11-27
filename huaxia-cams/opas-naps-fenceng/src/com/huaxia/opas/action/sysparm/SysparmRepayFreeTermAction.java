package com.huaxia.opas.action.sysparm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.SysparmRepayFreeTerm;
import com.huaxia.opas.service.sysparm.SysparmRepayFreeTermService;
import com.huaxia.opas.util.SequenceUtil;

import net.sf.json.JSONObject;

/**
 * 免息还款期维护
 * @author liudi
 * @since 2017-03-21
 * @version 1.0
 */
public class SysparmRepayFreeTermAction implements Action {

	@Resource(name = "sysparmRepayFreeTermService")
	private SysparmRepayFreeTermService sysparmRepayFreeTermService;

	/**
	 * 免息还款期信息
	 */
	public void querySysparmRepayFreeTerm(Context ctx) throws CoreException {

		JSONObject jsonObject = JSONObject.fromObject(ctx.getDataMap());
		SysparmRepayFreeTerm sysparmRepayFreeTerm = (SysparmRepayFreeTerm) JSONObject.toBean(jsonObject, SysparmRepayFreeTerm.class);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		
		// 查询参数
		List<SysparmRepayFreeTerm> list = new ArrayList<SysparmRepayFreeTerm>();
		
		if(sysparmRepayFreeTerm.getRepayFreeValue() != null){
			BigDecimal repayFreeValue = new BigDecimal("0");
			repayFreeValue = sysparmRepayFreeTerm.getRepayFreeValue();
			sysparmRepayFreeTerm.setRepayFreeValue(repayFreeValue);
		}

		int count = sysparmRepayFreeTermService.querySysparmRepayFreeTermCount(sysparmRepayFreeTerm);
		
		if(count>0){
			
			list = sysparmRepayFreeTermService.querySysparmRepayFreeTerm(sysparmRepayFreeTerm, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改免息还款期信息
	 */
	public void updateSysparmRepayFreeTerm(Context ctx) throws CoreException {

		Gson json = new Gson();

		SysparmRepayFreeTerm sysparmRepayFreeTerm = json.fromJson(json.toJson(ctx.getDataMap()), SysparmRepayFreeTerm.class);
		
		
		try {
			
			BigDecimal repayFreeValue = new BigDecimal("0");
			repayFreeValue = sysparmRepayFreeTerm.getRepayFreeValue();
			sysparmRepayFreeTerm.setRepayFreeValue(repayFreeValue);
			sysparmRepayFreeTermService.updateSysparmRepayFreeTerm(sysparmRepayFreeTerm);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除免息还款期信息
	 */
	public void removeSysparmRepayFreeTerm(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			sysparmRepayFreeTermService.deleteSysparmRepayFreeTerm(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 全部删除免息还款期信息
	 */
	public void removeAllSysparmRepayFreeTerm(Context ctx) throws CoreException {


		try {

			sysparmRepayFreeTermService.deleteAllSysparmRepayFreeTerm();

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}
	
	/**
	 * 新增免息还款期信息
	 */
	public void saveSysparmRepayFreeTerm(Context ctx) throws CoreException {
		
		Gson json = new Gson();
	
		SysparmRepayFreeTerm sysparmRepayFreeTerm = json.fromJson(json.toJson(ctx.getDataMap()), SysparmRepayFreeTerm.class);

		sysparmRepayFreeTerm.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		sysparmRepayFreeTerm.setCrtUser(userId);
		
		BigDecimal repayFreeValue = new BigDecimal("0");
		repayFreeValue = sysparmRepayFreeTerm.getRepayFreeValue();
		sysparmRepayFreeTerm.setRepayFreeValue(repayFreeValue);
		
		//判断免息还款期代码是否存在，如已存在，报错
		SysparmRepayFreeTerm query = sysparmRepayFreeTermService.querySysparmRepayFreeTerm(sysparmRepayFreeTerm);
		
		if(query != null){
			ctx.setData("isFailed", true);
			return;
		}

		try {
				
				sysparmRepayFreeTermService.saveSysparmRepayFreeTerm(sysparmRepayFreeTerm);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}