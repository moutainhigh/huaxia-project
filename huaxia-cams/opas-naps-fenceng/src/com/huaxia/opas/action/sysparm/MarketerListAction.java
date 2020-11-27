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
import com.huaxia.opas.domain.sysparm.Marketer;
import com.huaxia.opas.service.sysparm.MarketerService;
import com.huaxia.opas.util.SequenceUtil;
/**
 * 营销员白名单
 * @author hxb
 *
 */
public class MarketerListAction implements Action{

	@Resource
	private MarketerService marketerService;
	
	/**
	 * 查询功能实现
	 * @param ctx
	 * @throws CoreException
	 */
	public void queryWhiteListMarketers(Context ctx) throws CoreException{
		Gson gson = new Gson();
		Marketer marketer = gson.fromJson(gson.toJson(ctx.getDataMap()), Marketer.class);
		int curNum = 0;
		Object pageObj = ctx.getDataMap().get("page");
		int curPage = Integer.parseInt(String.valueOf(pageObj == null ? 0 : pageObj));
		Object rowsObj = ctx.getDataMap().get("rows");
		int pageNum = Integer.parseInt(String.valueOf(rowsObj == null ? 0 : rowsObj));
		if(curPage > 1){
			curNum = (curPage-1)*pageNum;
		}
		List<Marketer> list = new ArrayList<Marketer>();
		int count = marketerService.queryMarketersCount(marketer);
		if(count > 0){
			list = marketerService.queryMarketersList(marketer,curNum,pageNum);
		}
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
	
	//删除方法
	public void marketer_remove(Context ctx) {
		try {
			Marketer  marketer = getmarketer(ctx,"ids");
			marketerService.deleteMarketer(marketer);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
		}
	}
	//批量启用
	public void marketer_setStatusOK(Context ctx) {
		try {
			Marketer  marketer = getmarketer(ctx,"ids","Status");
			marketerService.marketerStatusOK(marketer);
			ctx.setData("isSuccess", true);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
		}
	}
	
	//批量停用
	public void marketer_setStatusNO(Context ctx) {
		try {
			Marketer  marketer = getmarketer(ctx,"ids","Status");
			marketerService.marketerStatusStop(marketer);
			ctx.setData("isSuccess", true);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
		}
	}
	
	//保存
	public void marketer_save(Context ctx) throws Exception {
		Gson json = new Gson();
		Map<String, Object> map = ctx.getDataMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(!"".equals(map.get("startDate"))){
				Date startDate = sdf.parse((String) map.get("startDate"));
				map.put("startDate", startDate);
			}else {
				map.remove("startDate");
			}
			if(!"".equals(map.get("endDate"))){
				Date endDate = sdf.parse((String) map.get("endDate"));
				map.put("endDate", endDate);
			}else {
				map.remove("endDate");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Marketer marketer = json.fromJson(json.toJson(map), Marketer.class);
		marketer.setAutoId(SequenceUtil.getUUID());
		List<Marketer> marketerList=new ArrayList<Marketer>();
		marketerList = marketerService.checkIsExistMarketer(marketer);
		if(marketerList != null && marketerList.size()!=0){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", "数据库已存在该条纪录！");
		} else {
			marketerService.insertMarketer(marketer);
		} 
		ctx.setData("isSuccess", true);
	}
	//更新
	public void marketer_update(Context ctx) throws Exception {
		Gson json = new Gson();
		Map<String, Object> map = ctx.getDataMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			if(!"".equals(map.get("startDate"))){
				Date startDate = sdf.parse((String) map.get("startDate"));
				map.put("startDate", startDate);
			}else {
				map.remove("startDate");
			}
			if(!"".equals(map.get("endDate"))){
				Date endDate = sdf.parse((String) map.get("endDate"));
				map.put("endDate", endDate);
			}else {
				map.remove("endDate");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Marketer marketer = json.fromJson(json.toJson(map), Marketer.class);
		/*if(startDate!=null&&!"".equals(startDate)){
			marketer.setStartDate(fmt.parse(startDate));
		}
		if(endDate!=null&&!"".equals(endDate)){
			marketer.setEndDate(fmt.parse(endDate));
		}*/
		
		//marketer.setUserName(operator);
		marketerService.updateMarketer(marketer);
		ctx.setData("isSuccess", true);
		
	}
	
	/**
	 * 公共部分的验证操作
	 */
	public Marketer getmarketer(Context ctx,String ...strings) throws CoreException{
		List<String> list = Arrays.asList(strings);
		Map<String, Object> map = ctx.getDataMap();
		Date createTime = null;
		if (list.contains("crtDate")) {
			String createDateString = (String)map.get("crtDate");
			if(null!=createDateString && !createDateString.trim().isEmpty()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					createTime  = sdf.parse(createDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			map.remove("crtDate");
		}
		Gson gson = new Gson();
		Marketer marketer = gson.fromJson(gson.toJson(map), Marketer.class);
		
		String operator = (String)ctx.getData("userId");
		if( null == operator || operator.trim().isEmpty()){
			CoreException e = new CoreException("操作用户operator不能为空，请先登录");
			throw e;
		}
		//marketer.setUserName(operator);
		
		for (String key : strings) {
			switch (key) {
			case "ids":
				@SuppressWarnings("unchecked")
				List<String> ids = (List<String>) map.get("ids");
				if( null == ids || ids.size() <= 0){
					CoreException e = new CoreException("请选择需要操作的行ids");
					throw e;
				}
				marketer.setIds(ids);
				break;
				
			case "Status":
				String Status = (String)map.get("Status");
				if( null == Status || (!Status.trim().equals("1") && !Status.trim().equals("0"))){
					CoreException e = new CoreException("启用状态值Status非法");
					throw e;
				}
				break;
				
			case "crtDate":
				marketer.setCrtDate(createTime);
				break;
				
			default:
				break;
			}
		}
		return marketer;
	}
}
