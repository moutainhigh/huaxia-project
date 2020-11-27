package com.huaxia.opas.action.sysparm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.decision.ExcellentCompanyList;
import com.huaxia.opas.service.sysparm.ExcellentService;
import com.huaxia.opas.util.SequenceUtil;
/**
 * 营销员白名单
 * @author ad
 *
 */
public class ExcellentCompanyAction implements Action{

	@Resource
	private ExcellentService excellentService;
	
	/**
	 * 查询功能实现
	 * @param ctx
	 * @throws CoreException
	 */
	public void queryExcellentCompany(Context ctx) throws CoreException{
		Gson gson = new Gson();
		ExcellentCompanyList excellent = gson.fromJson(gson.toJson(ctx.getDataMap()), ExcellentCompanyList.class);
		int curNum = 0;
		Object pageObj = ctx.getDataMap().get("page");
		int curPage = Integer.parseInt(String.valueOf(pageObj == null ? 0 : pageObj));
		Object rowsObj = ctx.getDataMap().get("rows");
		int pageNum = Integer.parseInt(String.valueOf(rowsObj == null ? 0 : rowsObj));
		if(curPage > 1){
			curNum = (curPage-1)*pageNum;
		}
		List<ExcellentCompanyList> list = new ArrayList<ExcellentCompanyList>();
		int count = excellentService.queryExcellentCount(excellent);
		if(count > 0){
			list = excellentService.queryExcellentList(excellent,curNum,pageNum);
		}
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
	
	//删除方法
	public void excellent_remove(Context ctx) {
		try {
			ExcellentCompanyList  excellent = getexcellent(ctx,"ids");
			excellentService.deleteExcellent(excellent);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
		}
	}
	//批量启用
	public void excellent_setStatusOK(Context ctx) {
		try {
			ExcellentCompanyList  excellent = getexcellent(ctx,"ids","currStatus");
			excellentService.excellentStatusOK(excellent);
			ctx.setData("isSuccess", true);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
		}
	}
	
	//批量停用
	public void excellent_setStatusNO(Context ctx) {
		try {
			ExcellentCompanyList  excellent = getexcellent(ctx,"ids","currStatus");
			excellentService.excellentStatusStop(excellent);
			ctx.setData("isSuccess", true);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
		}
	}
	
	//保存
	public void excellent_save(Context ctx) throws Exception {
		Gson json = new Gson();
		Map<String, Object> map = ctx.getDataMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(!"".equals(map.get("startTime"))){
				Date startTime = sdf.parse((String) map.get("startTime"));
				map.put("startTime", startTime);
			}else {
				map.remove("startTime");
			}
			if(!"".equals(map.get("stopTime"))){
				Date stopTime = sdf.parse((String) map.get("stopTime"));
				map.put("stopTime", stopTime);
			}else {
				map.remove("stopTime");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ExcellentCompanyList excellent = json.fromJson(json.toJson(map), ExcellentCompanyList.class);
		excellent.setAutoId(SequenceUtil.getUUID());
		List<ExcellentCompanyList> excellentList=new ArrayList<ExcellentCompanyList>();
		excellentList = excellentService.checkIsExistExcellent(excellent);
		if(excellentList != null && excellentList.size()!=0){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", "数据库已存在该条纪录！");
		} else {
			excellentService.insertExcellent(excellent);
		} 
		ctx.setData("isSuccess", true);
	}
	//更新
	public void excellent_update(Context ctx) throws Exception {
		Gson json = new Gson();
		Map<String, Object> map = ctx.getDataMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			if(!"".equals(map.get("startTime"))){
				Date startTime = sdf.parse((String) map.get("startTime"));
				map.put("startTime", startTime);
			}else {
				map.remove("startTime");
			}
			if(!"".equals(map.get("stopTime"))){
				Date stopTime = sdf.parse((String) map.get("stopTime"));
				map.put("stopTime", stopTime);
			}else {
				map.remove("stopTime");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ExcellentCompanyList excellent = json.fromJson(json.toJson(map), ExcellentCompanyList.class);
		/*if(startTime!=null&&!"".equals(startTime)){
			excellent.setstartTime(fmt.parse(startTime));
		}
		if(stopTime!=null&&!"".equals(stopTime)){
			excellent.setstopTime(fmt.parse(stopTime));
		}*/
		
		//excellent.setUserName(operator);
		excellentService.updateExcellent(excellent);
		ctx.setData("isSuccess", true);
		
	}
	
	/**
	 * 公共部分的验证操作
	 */
	public ExcellentCompanyList getexcellent(Context ctx,String ...strings) throws CoreException{
		List<String> list = Arrays.asList(strings);
		Map<String, Object> map = ctx.getDataMap();
		Date createTime = null;
		if (list.contains("crtTime")) {
			String createDateString = (String)map.get("crtTime");
			if(null!=createDateString && !createDateString.trim().isEmpty()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					createTime  = sdf.parse(createDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			map.remove("crtTime");
		}
		Gson gson = new Gson();
		ExcellentCompanyList excellent = gson.fromJson(gson.toJson(map), ExcellentCompanyList.class);
		
		String operator = (String)ctx.getData("userId");
		if( null == operator || operator.trim().isEmpty()){
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		//excellent.setUserName(operator);
		
		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if( null == ids || ids.size() <= 0){
					CoreException e = new CoreException("请选择需要操作的行ids");
					throw e;
				}
				excellent.setIds(ids);
				break;
				
			case "currStatus":
				String currStatus = (String)map.get("currStatus");
				if( null == currStatus || (!currStatus.trim().equals("1") && !currStatus.trim().equals("0"))){
					CoreException e = new CoreException("启用状态值currStatus非法");
					throw e;
				}
				break;
				
			case "crtTime":
				excellent.setCrtTime(createTime);
				break;
				
			default:
				break;
			}
		}
		return excellent;
	}
}
