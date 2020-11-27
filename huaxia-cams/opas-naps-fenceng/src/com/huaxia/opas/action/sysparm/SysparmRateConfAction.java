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
import com.huaxia.opas.domain.sysparm.SysparmRateConf;
import com.huaxia.opas.service.sysparm.SysparmRateConfService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 利率差异化参数配置
 * @author liudi
 * @since 2017-03-17
 * @version 1.0
 */
public class SysparmRateConfAction implements Action {

	@Resource(name = "sysparmRateConfService")
	private SysparmRateConfService sysparmRateConfService;

	/**
	 * 利率差异化参数配置信息
	 */
	public void querySysparmRateConf(Context ctx) throws CoreException {
		Gson gson = new Gson();
		SysparmRateConf sysparmRateConf = gson.fromJson(gson.toJson(ctx.getDataMap()), SysparmRateConf.class);

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		
		// 查询参数
		List<SysparmRateConf> list = new ArrayList<SysparmRateConf>();

		int count = sysparmRateConfService.querySysparmRateConfCount(sysparmRateConf);
		
		if(count>0){
			list = sysparmRateConfService.querySysparmRateConf(sysparmRateConf, curNum, pageNum);
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);

	}

	/**
	 * 修改利率差异化参数配置信息
	 */
	public void updateSysparmRateConf(Context ctx) throws CoreException {

		Gson json = new Gson();

		SysparmRateConf sysparmRateConf = json.fromJson(json.toJson(ctx.getDataMap()), SysparmRateConf.class);
		

		/*String userId = ctx.getData("userId");
		
		sysparmRateConf.setLstUpdUser(userId);*/
		
		//判断利率代码是否存在，如不存在，报错
		SysparmRateConf query1 = sysparmRateConfService.querySysparmRateConf1(sysparmRateConf);
		
		if(query1 == null){
			ctx.setData("isFailed1", true);
			return;
		}

		//判断最低还款额比例代码状态是否停用，如已停用，报错
		SysparmRateConf query2 = sysparmRateConfService.querySysparmRateConf3(sysparmRateConf);
		
		if("0".equals(query2.getStatus())){
			ctx.setData("isFailed2", true);
			return;
		}
		
		try {

			sysparmRateConfService.updateSysparmRateConf(sysparmRateConf);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 删除利率差异化参数配置信息
	 */
	public void removeSysparmRateConf(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();

		String autoId = (String) map.get("autoId");

		try {

			sysparmRateConfService.deleteSysparmRateConf(autoId);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

	/**
	 * 全部删除利率差异化参数配置信息
	 */
	public void removeAllSysparmRateConf(Context ctx) throws CoreException {


		try {

			sysparmRateConfService.deleteAllSysparmRateConf();

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}
	
	/**
	 * 新增利率差异化参数配置信息
	 */
	public void saveSysparmRateConf(Context ctx) throws CoreException {
		
		Gson json = new Gson();
		
		SysparmRateConf sysparmRateConf = json.fromJson(json.toJson(ctx.getDataMap()), SysparmRateConf.class);

		sysparmRateConf.setAutoId(SequenceUtil.getUUID());

		String userId = ctx.getData("userId");
		sysparmRateConf.setCrtUser(userId);
		/*sysparmRateConf.setLstUpdUser(userId);*/
		//判断利率代码是否存在，如不存在，报错
		SysparmRateConf query1 = sysparmRateConfService.querySysparmRateConf1(sysparmRateConf);
		
		if(query1 == null){
			ctx.setData("isFailed1", true);
			return;
		}
		//判断利率客户分层结果标签是否存在，如已存在，报错
		SysparmRateConf query2 = sysparmRateConfService.querySysparmRateConf2(sysparmRateConf);
		
		if(query2 != null){
			ctx.setData("isFailed2", true);
			return;
		}

		//判断利率代码状态是否停用，如已停用，报错
		SysparmRateConf query3 = sysparmRateConfService.querySysparmRateConf3(sysparmRateConf);
		
		if("0".equals(query3.getStatus())){
			ctx.setData("isFailed3", true);
			return;
		}
		
		try {
				
				sysparmRateConfService.saveSysparmRateConf(sysparmRateConf);

		} catch (CoreException e) {

			ctx.setData("exMsg", e.getMessage());

			throw e;
		}

		ctx.setData("isSuccess", true);

	}

}