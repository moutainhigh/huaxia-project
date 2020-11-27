package com.huaxia.opas.action.sysparm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.domain.sysparm.StockcustTelSale;
import com.huaxia.opas.service.sysparm.StockcustTelSaleService;
import com.ibm.icu.text.SimpleDateFormat;
/**
 * 存量客户电销 名单
 * @author 汪涛
 *
 */
public class StockcustTelSaleAction implements Action{
	@Resource(name="stockcustTelSaleService")
	private StockcustTelSaleService stockcustTelSaleService;
	public StockcustTelSaleService getStockcustTelSaleService() {
		return stockcustTelSaleService;
	}
	public void setStockcustTelSaleService(StockcustTelSaleService stockcustTelSaleService) {
		this.stockcustTelSaleService = stockcustTelSaleService;
	}
	
	/**
	 * 查询存量客户电销名单
	 * @throws Exception 
	 */
	public void queryStockcustTelSaleList (Context ctx) throws Exception{
		Gson gson = new Gson();
		
		Map<String,Object> map = ctx.getDataMap();
		if("".equals(map.get("createTime"))|| null==map.get("createTime")){
			map.remove("createTime");
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			map.put("createTime", sdf.parse((String) map.get("createTime")) );
		}
		StockcustTelSale stockcustTelSale = gson.fromJson(gson.toJson(map),  StockcustTelSale.class) ;
		
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		
		int count = stockcustTelSaleService.queryStockcustTelSaleCount(stockcustTelSale);
		
		List<StockcustTelSale> list = new ArrayList<StockcustTelSale>();
		
		if(count >0 ){
			list =stockcustTelSaleService.queryStockcustTelSaleList(stockcustTelSale,curNum,pageNum);
		}
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		
		dataMap.put("rows", list);
		
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 新增存量客户电销名单
	 * @throws Exception 
	 */
	public void insertStockcustTelSale (Context ctx) throws Exception{
		try{
			stockcustTelSaleService.insertStockcustTelSale(ctx);
		}catch(Exception e){
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
			ctx.setData("isSuccess",true);	
	}
	
	/**
	 * 修改存量客户电销名单
	 * @throws Exception 
	 */
	public void updateStockcustTelSale(Context ctx) throws Exception{
		try{
			stockcustTelSaleService.updateStockcustTelSale(ctx);
		}catch(Exception e){
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
			ctx.setData("isSuccess",true);	
	}


	/**
	 * 删除和批量删除
	 * @throws Exception 
	 */
	public void deleteStockcustTelSale(Context ctx) throws Exception{
		try{
			stockcustTelSaleService.deleteStockcustTelSale(ctx);
		}catch(Exception e){
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
			ctx.setData("isSuccess",true);	
	}
	
	
	/**
	 * 批量启用
	 * @throws Exception 
	 */
	public void batchStartStockcustTelSale(Context ctx) throws Exception{
		try{
			stockcustTelSaleService.batchStartStockcustTelSale(ctx);
		}catch(Exception e){
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
			ctx.setData("isSuccess",true);	
	}
	
	/**
	 * 批量禁用
	 * @throws Exception 
	 */
	public void batchStopStockcustTelSale(Context ctx) throws Exception{
		try{
			stockcustTelSaleService.batchStopStockcustTelSale(ctx);
		}catch(Exception e){
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
			ctx.setData("isSuccess",true);	
	}
	
	/**
	 * 存量客户电销历史查询
	 */
	public void stockcustTelSaleHistory(Context ctx){
		String autoId = ctx.getData("autoId");
		
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		int curNum = 0;
		
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		
		int count = stockcustTelSaleService.stockcustTelSaleHistoryCount(autoId);
		
		List<StockcustTelSale> list = new ArrayList<StockcustTelSale>();
		
		if(count >0 ){
			list =stockcustTelSaleService.stockcustTelSaleHistory(autoId,curNum,pageNum);
		}
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		
		dataMap.put("rows", list);
		
		ctx.setDataMap(dataMap);
	}
}
