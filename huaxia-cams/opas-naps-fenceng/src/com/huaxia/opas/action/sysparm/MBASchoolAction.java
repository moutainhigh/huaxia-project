package com.huaxia.opas.action.sysparm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.huaxia.opas.domain.sysparm.MBASchool;
import com.huaxia.opas.service.sysparm.MBASchoolService;
import com.huaxia.opas.util.SequenceUtil;

public class MBASchoolAction implements Action{
	
	private static Logger logger = LoggerFactory.getLogger(MBASchoolAction.class);
	
	@Resource(name = "MBASchoolService")
	private MBASchoolService MBASchoolService;
	
	/**
	 * 查询MBA学院名单
	 * @throws ParseException 
	 */
	public void queryMBASchoolList(Context ctx) throws ParseException{
		
		Gson gson = new Gson();
		
		Map<String, Object> dataMap2 = ctx.getDataMap();

		String date = ctx.getData("crtDate");
		if("".equals(date)){
			dataMap2.remove("crtDate");
		}
		
		if(date!=null && date!=""){
			String date1 = date + " 00:00:00";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date crtDate = format.parse(date1);
			dataMap2.put("crtDate", crtDate);
		}
		String json = gson.toJson(dataMap2);
		MBASchool MBASchool = gson.fromJson(json, MBASchool.class);

		int curNum = 0;

		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));

		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {

			curNum = (curPage - 1) * pageNum;

		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("schoolArea", MBASchool.getSchoolArea());//地区
		map.put("schoolName", MBASchool.getSchoolName());//MBA院校名称
		map.put("crtDate", MBASchool.getCrtDate());//创建日期
		
		int count = MBASchoolService.queryMBASchoolListCount(map);

		List<MBASchool> list = new ArrayList<MBASchool>();

		if (count > 0) {
			
			list = MBASchoolService.queryMBASchoolList(map, curNum, pageNum);
			
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 删除MBA院校信息
	 */
	public void deleteMBASchool(Context ctx){
		List<String> ids = ctx.getData("ids");
		try{
			for (String autoId : ids) {
				MBASchoolService.removeMBASchoolByAutoId(autoId);
			}
			ctx.setData("isSuccess", true);
		}catch(Exception e){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	
	
	/**
	 * 新增MBA院校名单
	 * @throws CoreException 
	 * @throws ParseException 
	 */
	public void saveMBASchool(Context ctx) throws CoreException, ParseException{
		String userId = ctx.getData("userId");
		Map<String, Object> map = ctx.getDataMap();	
		if("".equals(map.get("upper"))||map.get("upper")==null){
			map.put("upper", "0");
		}
//		Date crtDate = null;
//		if("".equals(map.get("crtDate"))|| null == map.get("crtDate")){
//			 map.put("crtDate", new Date());
//		}else{
//			crtDate =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) map.get("crtDate"));
//			map.put("crtDate", crtDate);
//		}
		
		Gson gson = new Gson();
		MBASchool MBASchool = gson.fromJson(gson.toJson(map), MBASchool.class);
		
		MBASchool.setAutoId(SequenceUtil.getUUID());
		MBASchool.setCrtDate(new Date());
		MBASchool.setCrtUser(userId);
		MBASchool.setOperatCode(userId);
		MBASchool.setOperatTime(new Date());
		
		try {
			int i = MBASchoolService.save(MBASchool);
			if(i>0){
				ctx.setData("isSuccess", true);
			}
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 修改MBA院校名单
	 * @throws ParseException 
	 * @throws CoreException 
	 */
	public void updateMBASchool(Context ctx) throws ParseException, CoreException{
		String operationCode = ctx.getData("userId");
		Map<String, Object> map = ctx.getDataMap();		
		
		if("".equals(map.get("upper"))||null==map.get("upper")){
			map.put("upper", "0");
		}
		
		Date crtDate = null;
		if("".equals(map.get("crtDate"))|| null == map.get("crtDate")){
			 map.put("crtDate", new Date());
		}else{
			crtDate =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) map.get("crtDate"));
			map.put("crtDate", crtDate);
		}
		Gson gson = new Gson();
		MBASchool MBASchool = gson.fromJson(gson.toJson(map), MBASchool.class);
		
		try {
			MBASchool.setOperatTime(new Date());
			MBASchool.setOperatCode(operationCode);
			MBASchoolService.updateMBASchool(MBASchool);
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 停用名单库
	 */
	public void noMBASchool(Context ctx){
		List<String> ids = ctx.getData("ids");
		try{
			for (String autoId : ids) {
				MBASchool MBASchool = new MBASchool();
				MBASchool.setAutoId(autoId);
				MBASchool.setCurrStatus("0");
				MBASchoolService.NoMBASchool(MBASchool);
			}
		}catch(Exception e){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg",e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess",true);
		
	}
	
	/**
	 * 启用名单库 
	 */
	public void okMBASchool(Context ctx){
		List<String> ids = ctx.getData("ids");
		try{
			for (String autoId : ids) {
				MBASchool MBASchool = new MBASchool();
				MBASchool.setAutoId(autoId);
				MBASchool.setCurrStatus("1");
				MBASchoolService.OkMBASchool(MBASchool);
			}
		}catch(Exception e){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg",e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess",true);
	}
	
	/**
	 * 查询历史信息列表
	 * @throws ParseException
	 */
	public void queryMBASchoolHistoryList(Context ctx) throws ParseException{
		String autoId = ctx.getData("autoId");
		MBASchool school = new MBASchool();
		school.setAutoId(autoId);
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?1:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?10:ctx.getDataMap().get("rows")));
		Map<String, Object> dataMap = MBASchoolService.queryMBASchoolHistoryList(school, curPage, pageNum);
		ctx.setDataMap(dataMap);
	}
	
}
