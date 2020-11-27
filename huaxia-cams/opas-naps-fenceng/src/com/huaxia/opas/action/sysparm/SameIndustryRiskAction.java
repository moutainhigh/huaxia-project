package com.huaxia.opas.action.sysparm;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.sysparm.SameIndustryRisk;
import com.huaxia.opas.service.sysparm.SameIndustryRiskService;
import com.huaxia.opas.util.SequenceUtil;
/**
 * 
 * @author 汪涛
 *
 */
public class SameIndustryRiskAction implements Action{
	private static Logger logger=LoggerFactory.getLogger(SameIndustryRiskAction.class);
	@Resource(name="sameIndustryRiskService")
	private SameIndustryRiskService sameIndustryRiskService;
	public SameIndustryRiskService getSameIndustryRiskService() {
		return sameIndustryRiskService;
	}
	public void setSameIndustryRiskService(SameIndustryRiskService sameIndustryRiskService) {
		this.sameIndustryRiskService = sameIndustryRiskService;
	}
	
	/**
	 * 查询同业关注风险名单列表
	 * @throws ParseException 
	 */
	public void querySameIndustryRiskList(Context ctx) throws ParseException{
		Gson gson = new Gson();
		
		Map<String,Object> map = ctx.getDataMap();
		if("".equals(map.get("createTime"))|| null==map.get("createTime")){
			map.remove("createTime");
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			map.put("createTime", sdf.parse((String) map.get("createTime")) );
		}
		SameIndustryRisk sameIndustryRisk = gson.fromJson(gson.toJson(map),  SameIndustryRisk.class) ;
		
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		
		int count = sameIndustryRiskService.querySameIndustryRiskCount(sameIndustryRisk);
		
		List<SameIndustryRisk> list = new ArrayList<SameIndustryRisk>();
		
		if(count >0 ){
			list =sameIndustryRiskService.querySameIndustryRiskList(sameIndustryRisk,curNum,pageNum);
		}
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		
		dataMap.put("rows", list);
		
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 新增同业关注风险名单
	 */
	public void insertSameIndustryRisk(Context ctx) throws Exception {
		
		Gson json = new Gson();
		Map<String,Object> map = ctx.getDataMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date invalidTime = sdf.parse((String) map.get("invalidTime"));
			map.put("invalidTime", invalidTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SameIndustryRisk sameIndustryRisk = json.fromJson(json.toJson(map),SameIndustryRisk.class);
		
		//给客户设置autoID
		sameIndustryRisk.setAuto_id(SequenceUtil.getUUID());
		
		String userId = ctx.getData("userId");
		sameIndustryRisk.setOperator(userId);
		
		try{
			sameIndustryRiskService.insertSameIndustryRisk(sameIndustryRisk);
			//新增成功后插入一条添加的历史记录进历史记录表
			sameIndustryRisk.setId(SequenceUtil.getUUID());
			sameIndustryRisk.setOperatType("添加");
			sameIndustryRiskService.insertSameIndustryRiskHistory(sameIndustryRisk);
		}catch(Exception e){
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
			ctx.setData("isSuccess",true);
	}

	/**
	 * 修改同业关注风险名单
	 */
	public void updateSameIndustryRisk(Context ctx) throws Exception{
		Gson json = new Gson();
		Map<String,Object> map = ctx.getDataMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date invalidTime = sdf.parse((String) map.get("invalidTime"));
			map.put("invalidTime", invalidTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SameIndustryRisk sameIndustryRisk= json.fromJson(json.toJson(map),SameIndustryRisk.class);
		
		String auto_id = ctx.getData("auto_id");
		sameIndustryRisk.setAuto_id(auto_id);
		String userId = ctx.getData("userId");
		sameIndustryRisk.setOperator(userId);
		
		try{
			sameIndustryRiskService.updateSameIndustryRisk(sameIndustryRisk);
			//修改成功后一条添加的历史记录进历史记录表
			sameIndustryRisk.setId(SequenceUtil.getUUID());
			sameIndustryRisk.setOperatType("修改");
			sameIndustryRiskService.insertSameIndustryRiskHistory(sameIndustryRisk);
		}catch(Exception e){
			ctx.setData("exMsg",e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess",true);
	}

	/**
	 * 删除同业关注风险名单
	 * @throws Exception 
	 */
	public void deleteSameIndustryRisk(Context ctx) throws Exception{

		Map<String,Object> map = ctx.getDataMap();
		
		String auto_id = (String) map.get("auto_id");
		
		try{
			sameIndustryRiskService.deleteSameIndustryRisk(auto_id);
			ctx.setData("isSuccess",true);
		}catch(Exception e){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg",e.getMessage());
			throw e;
		}
		
	}
	
	/**
	 * 批量启用同业关注风险名单
	 */
	public void batchStartSameIndustryRisk (Context ctx) throws Exception{
		
		try{
			sameIndustryRiskService.batchStartSameIndustryRisk(ctx);
		}catch(Exception e){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg",e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess",true);
	}
	
	/**
	 * 批量禁用同业关注风险名单
	 */
	public void batchStopSameIndustryRisk (Context ctx) throws Exception{
		
		try{
			sameIndustryRiskService.batchStopSameIndustryRisk(ctx);
		}catch(Exception e){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg",e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess",true);
	}
	
	/**
	 * 批量删除同业关注风险名单
	 */
	public void batchDeleteSameIndustryRisk(Context ctx) throws Exception{
		
		try{
			sameIndustryRiskService.batchDeleteSameIndustryRisk(ctx);
		}catch(Exception e){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg",e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess",true);
	}
	
	/**
	 * 同业关注风险名单历史记录查询
	 * @throws Exception 
	 */
	public void querySameIndustryRiskHistory(Context ctx) throws Exception{
		
		String auto_id = ctx.getData("auto_id");
		
		int curNum = 0;
		int curPage = 1;
		int pageNum = 10;
		
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		
		int count = sameIndustryRiskService.querySameIndustryRiskHistoryCount(auto_id);
		
		List<SameIndustryRisk> list = new ArrayList<SameIndustryRisk>();
		
		if(count >0 ){
			list =sameIndustryRiskService.querySameIndustryRiskHistoryList(auto_id,curNum,pageNum);
		}
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		
		dataMap.put("rows", list);
		
		ctx.setDataMap(dataMap);
	}
	
}
