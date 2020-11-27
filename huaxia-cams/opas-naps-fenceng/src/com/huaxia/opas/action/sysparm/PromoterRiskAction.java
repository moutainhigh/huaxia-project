package com.huaxia.opas.action.sysparm;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.Promoter;
import com.huaxia.opas.domain.sysparm.PromoterHis;
import com.huaxia.opas.service.sysparm.PromoterRiskService;
import com.huaxia.opas.service.sysparm.impl.PromoterRiskServiceImpl;
import com.huaxia.opas.util.TransDateUtil;


public class PromoterRiskAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(PromoterRiskAction.class);

	@Resource(name = "promoterRiskService")
	private PromoterRiskService promoterRiskService;


	/**
	 * 推广员风险名单信息分页查询
	 * 
	 * @param context
	 * @throws CoreException
	 * @throws ParseException 
	 */
	public void queryPromoterList(Context ctx) throws CoreException, ParseException {
		Map<String, Object> map = ctx.getDataMap();
		if(map.get("createTime")==""||map.get("createTime")==null){
			map.remove("createTime");
		}else{
			map.put("createTime", TransDateUtil.String2Date((String) ctx.getDataMap().get("createTime"), "yyyy-MM-dd"));
		}
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(map.get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(map.get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		String autoId = (String)map.get("autoId");
		Map<String, Object> dataMap =  new HashMap<String, Object>();
		if(autoId == null){
			dataMap = getPromoters(map,curNum,pageNum);
		}else{
			dataMap = getPromoterHiss(map,curNum,pageNum);
		}
		ctx.setDataMap(dataMap);
	}
	
	private Map <String, Object> getPromoterHiss(Map<String, Object> map, int curNum, int pageNum) throws CoreException {
		Gson gson = new Gson();
		PromoterHis promHis = gson.fromJson(gson.toJson(map), PromoterHis.class);
		int count = promoterRiskService.queryPromoterHisCount(promHis);
		List<PromoterHis> list = new ArrayList<PromoterHis>();
		if (count > 0) {
			list = promoterRiskService.queryPromoterHisList(promHis, curNum, pageNum);
		}
		Map <String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", count);
		resultMap.put("rows", list);
		return resultMap;
	}

	private Map <String, Object> getPromoters(Map<String, Object> map, int curNum, int pageNum) throws CoreException {
		Gson gson = new Gson();
		Promoter prom = gson.fromJson(gson.toJson(map), Promoter.class);
		int count = promoterRiskService.queryPromoterCount(prom);
		List<Promoter> list = new ArrayList<Promoter>();
		if (count > 0) {
			list = promoterRiskService.queryPromoterList(prom, curNum, pageNum);
		}
		Map <String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", count);
		resultMap.put("rows", list);
		return resultMap;
	}

	/**
	 * 保存推广员风险信息
	 */
	public void addPromoterInfo(Context ctx) throws CoreException {
		try {
			Map<String, Object> dataMap = ctx.getDataMap();
			String user = (String) (dataMap.get("userId")!=null?dataMap.get("userId"):"");
			//String user = ctx.getUser()!=null?ctx.getUser().getName():"";
 			Gson gson = new Gson();
			// 2017/4/11 创建时间改为当前时间,操作时间在新增时为空
			/*if(dataMap.get("createTime")!=null&&"".equals((String)dataMap.get("createTime"))==false){
				dataMap.put("createTime", TransDateUtil.String2Date((String) dataMap.get("createTime"), "yyyy-MM-dd hh:mm:ss"));
			}else{
				dataMap.remove("createTime");
			}*/
			if(dataMap.get("invalidTime")!=null&&"".equals((String)dataMap.get("invalidTime"))==false){
				dataMap.put("invalidTime", TransDateUtil.String2Date((String) dataMap.get("invalidTime"), "yyyy-MM-dd hh:mm:ss"));
			}else{
				dataMap.remove("invalidTime");
			}
			Promoter prom = gson.fromJson(gson.toJson(dataMap), Promoter.class);
			PromoterHis promHis = gson.fromJson(gson.toJson(dataMap), PromoterHis.class);
			String uuid = PromoterRiskServiceImpl.getUUID();
			prom.setAutoId(uuid);
			promHis.setAutoId(uuid);
			prom.setCreateTime(new Date());
			promHis.setCreateTime(new Date());
			prom.setOperator(user);
			promHis.setOperator(user);
			promHis.setOperation("新增");
			promHis.setKeyId(PromoterRiskServiceImpl.getUUID());
			promoterRiskService.insertPromoter(prom, promHis);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		} catch (ParseException e2) {
			logger.error("日期转换错误");
			e2.printStackTrace();
		}
		ctx.setData("isSuccess", true);
	}
	
	/**
	 * 修改推广员风险信息
	 */
	public void editPromoterInfo(Context ctx) throws CoreException {
		try {
			Gson gson = new Gson();
			Map<String, Object> dataMap = ctx.getDataMap();
			String user = (String) (dataMap.get("userId")!=null?dataMap.get("userId"):"");
			//String user = ctx.getUser()!=null?ctx.getUser().getName():"";
			//System.out.println((String) dataMap.get("createTime"));
			if(dataMap.get("createTime")!=null&&"".equals((String)dataMap.get("createTime"))==false){
				dataMap.put("createTime", TransDateUtil.String2Date((String) dataMap.get("createTime"), "yyyy-MM-dd hh:mm:ss"));
			}else{
				dataMap.remove("createTime");
			}
			//dataMap.remove("createTime");	
			if(dataMap.get("invalidTime")!=null&&"".equals((String)dataMap.get("invalidTime"))==false){
				dataMap.put("invalidTime", TransDateUtil.String2Date((String) dataMap.get("invalidTime"), "yyyy-MM-dd hh:mm:ss"));
			}else{
				dataMap.remove("invalidTime");
			}
			Promoter prom = gson.fromJson(gson.toJson(dataMap), Promoter.class);
			PromoterHis promHis = gson.fromJson(gson.toJson(dataMap), PromoterHis.class);
			prom.setOperatTime(new Date());
			promHis.setOperatTime(new Date());
			prom.setOperator(user);
			promHis.setOperator(user);
			promHis.setOperation("修改");
			promHis.setKeyId(PromoterRiskServiceImpl.getUUID());
			promoterRiskService.updatePromoter(prom, promHis);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		} catch (ParseException e2) {
			logger.error("日期转换错误");
			e2.printStackTrace();
		}
		ctx.setData("isSuccess", true);
	}
	
	/**
	 * 推广员风险信息启用
	 */
	@SuppressWarnings("unchecked")
	public void openPromoterInfo(Context ctx) throws CoreException {
		try {
			Map<String, Object> dataMap = ctx.getDataMap();
			List<String> autoIds = (List<String>) dataMap.get("ids");
			String user = (String) (dataMap.get("userId")!=null?dataMap.get("userId"):"");
			//String user = ctx.getUser()!=null?ctx.getUser().getName():"";
			promoterRiskService.openPromoters(autoIds,user);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		} catch (ParseException e2) {
			logger.error("日期转换错误");
			e2.printStackTrace();
		}
		ctx.setData("isSuccess", true);
	}
	/**
	 * 推广员风险信息禁用
	 */
	@SuppressWarnings("unchecked")
	public void closePromoterInfo(Context ctx) throws CoreException {
		try {
			Map<String, Object> dataMap = ctx.getDataMap();
			List<String> autoIds = (List<String>) dataMap.get("ids");
			String user = (String) (dataMap.get("userId")!=null?dataMap.get("userId"):"");
			//String user = ctx.getUser()!=null?ctx.getUser().getName():"";
			promoterRiskService.closePromoters(autoIds,user);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		} catch (ParseException e2) {
			logger.error("日期转换错误");
			e2.printStackTrace();
		}
		ctx.setData("isSuccess", true);
	}
	/**
	 * 删除推广员风险信息
	 */
	public void removePromoterInfo(Context ctx) throws CoreException {
		try {
			Map<String, Object> dataMap = ctx.getDataMap();
			List<String> autoIds = (List<String>) dataMap.get("ids");
			promoterRiskService.deletePromoters(autoIds);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		} catch (ParseException e2) {
			logger.error("日期转换错误");
			e2.printStackTrace();
		}
		ctx.setData("isSuccess", true);
	}
	
}
