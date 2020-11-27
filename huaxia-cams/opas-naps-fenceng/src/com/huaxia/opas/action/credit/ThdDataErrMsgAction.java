package com.huaxia.opas.action.credit;

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
import com.google.gson.JsonSyntaxException;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.ThdErrMsg;
import com.huaxia.opas.service.credit.ThdErrDataService;
import com.huaxia.opas.util.TransDateUtil;

/**
 * 三方异常查询处理action类
 * 
 * @author susha 2017/3/13
 *
 */
public class ThdDataErrMsgAction implements Action {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ThdDataErrMsgAction.class);

	@Resource(name = "thdErrDataService")
	private ThdErrDataService thdErrDataService;

	/**
	 * 三方数据查询异常列表查询
	 * @param ctx
	 * @throws CoreException 
	 */
	public void queryThdErrDatalist(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(map.get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(map.get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map<String, Object> dataMap =  new HashMap<String, Object>();
		if(map.get("taskType") != null){
			dataMap = getThdErrDataList(map,curNum,pageNum);
		}else{
			dataMap.put("total", 0);
		}
		ctx.setDataMap(dataMap);

	}
	// 查询列表方法
	private Map <String, Object> getThdErrDataList(Map<String, Object> map, int curNum, int pageNum) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Gson gson = new Gson();
			if("".equals(map.get("startDate"))){
				map.remove("startDate");
			}else{
				map.put("startDate",(TransDateUtil.String2Date((String)map.get("startDate"), "yyyy-MM-dd")));
			}
			if("".equals(map.get("endDate"))){
				map.remove("endDate");
			}else{
				map.put("endDate",(TransDateUtil.String2Date((String)map.get("endDate"), "yyyy-MM-dd")));
			}
			ThdErrMsg prom = gson.fromJson(gson.toJson(map), ThdErrMsg.class);
			int count = thdErrDataService.queryCount(prom);
			List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
			if (count > 0) {
				list = thdErrDataService.queryList(prom, curNum, pageNum);
			}
			resultMap.put("total", count);
			resultMap.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * 三方数据查询异常列表批量重查
	 * @param ctx
	 * @throws CoreException 
	 */
	@SuppressWarnings("unchecked")
	public void thdErrMsgRequery(Context ctx) throws CoreException {
		try {
			Map<String, Object> dataMap = ctx.getDataMap();
			List<Map<String,Object>> errMsgArr = (List<Map<String,Object>>) dataMap.get("errMsg");
			thdErrDataService.addHis(errMsgArr);
			for (Map<String, Object> errMsgMap : errMsgArr) {
				errMsgMap.put("lstOptUser", dataMap.get("userId"));
			}
			thdErrDataService.updateRequeryData(errMsgArr);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			ctx.setData("exMsg", e.getMessage());
			throw e;
		} 
		ctx.setData("isSuccess", true);
	}
	
	/**
	 * 三方数据查询异常列表批量流转
	 * @param ctx
	 * @throws CoreException 
	 */
	@SuppressWarnings("unchecked")
	public void thdErrMsgPassData(Context ctx) throws CoreException {
		try {
			Map<String, Object> dataMap = ctx.getDataMap();
			List<Map<String, Object>> errMsgArr = (List<Map<String, Object>>) dataMap.get("errMsg");
			thdErrDataService.addHis(errMsgArr);
			for (Map<String, Object> errMsgMap : errMsgArr) {
				errMsgMap.put("lstOptUser", dataMap.get("userId"));
			}
			thdErrDataService.updatePassListData(errMsgArr);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			ctx.setData("exMsg", e.getMessage());
			return ;
		} 
		ctx.setData("isSuccess", true);
	}
	
	/**
	 * 海航和fico三方数据查询异常列表查询
	 * @param ctx
	 * @throws CoreException 
	 */
	public void querySailAndFicoErrDatalist(Context ctx) throws CoreException {

		Map<String, Object> map = ctx.getDataMap();
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(map.get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(map.get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map<String, Object> dataMap =  new HashMap<String, Object>();
		if(map.get("taskType") != null){
			dataMap = getSailAndFicoErrDataList(map,curNum,pageNum);
		}else{
			dataMap.put("total", 0);
		}
		ctx.setDataMap(dataMap);

	}
	// 查询列表方法
	private Map <String, Object> getSailAndFicoErrDataList(Map<String, Object> map, int curNum, int pageNum) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Gson gson = new Gson();
			if("".equals(map.get("startDate"))){
				map.remove("startDate");
			}else{
				map.put("startDate",(TransDateUtil.String2Date((String)map.get("startDate"), "yyyy-MM-dd")));
			}
			if("".equals(map.get("endDate"))){
				map.remove("endDate");
			}else{
				map.put("endDate",(TransDateUtil.String2Date((String)map.get("endDate"), "yyyy-MM-dd")));
			}
			ThdErrMsg prom = gson.fromJson(gson.toJson(map), ThdErrMsg.class);
			int count = thdErrDataService.querySailAndFicoCount(prom);
			List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
			if (count > 0) {
				list = thdErrDataService.querySailAndFicoList(prom, curNum, pageNum);
			}
			resultMap.put("total", count);
			resultMap.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * 三方数据查询异常列表批量重查
	 * @param ctx
	 * @throws CoreException 
	 */
	@SuppressWarnings("unchecked")
	public void sailAndFicoErrMsgRequery(Context ctx) throws CoreException {
		try {
			Map<String, Object> dataMap = ctx.getDataMap();
			List<Map<String,Object>> errMsgArr = (List<Map<String,Object>>) dataMap.get("errMsg");
			thdErrDataService.addSailAndFicoHis(errMsgArr);
			for (Map<String, Object> errMsgMap : errMsgArr) {
				errMsgMap.put("lstOptUser", dataMap.get("userId"));
			}
			thdErrDataService.updateSailAndFicoRequeryData(errMsgArr);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			ctx.setData("exMsg", e.getMessage());
			throw e;
		} 
		ctx.setData("isSuccess", true);
	}
	
	/**
	 * 三方数据查询异常列表批量流转
	 * @param ctx
	 * @throws CoreException 
	 */
	@SuppressWarnings("unchecked")
	public void sailAndFicoErrMsgPassData(Context ctx) throws CoreException {
		try {
			Map<String, Object> dataMap = ctx.getDataMap();
			List<Map<String, Object>> errMsgArr = (List<Map<String, Object>>) dataMap.get("errMsg");
			thdErrDataService.addSailAndFicoHis(errMsgArr);
			for (Map<String, Object> errMsgMap : errMsgArr) {
				errMsgMap.put("lstOptUser", dataMap.get("userId"));
			}
			thdErrDataService.updateSailAndFicoPassListData(errMsgArr);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			ctx.setData("exMsg", e.getMessage());
			return ;
		} 
		ctx.setData("isSuccess", true);
	}
}
