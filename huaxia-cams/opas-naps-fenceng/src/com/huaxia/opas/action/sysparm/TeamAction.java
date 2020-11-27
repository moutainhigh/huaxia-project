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
import com.huaxia.opas.domain.sysparm.TeamName;
import com.huaxia.opas.service.sysparm.TeamService;
import com.huaxia.opas.util.SequenceUtil;

public class TeamAction implements Action{
	
	private static Logger logger = LoggerFactory.getLogger(TeamAction.class);
	
	@Resource(name = "teamService")
	private TeamService teamService;
	
	/**
	 * 查询团办名单库列表信息
	 * @throws ParseException 
	 */
	public void queryTeamList(Context ctx) throws ParseException{
		
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
		TeamName teamList = gson.fromJson(json, TeamName.class);
//		TeamList teamList = gson.fromJson(gson.toJson(ctx.getDataMap()), TeamList.class);

		int curNum = 0;

		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));

		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {

			curNum = (curPage - 1) * pageNum;

		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teamId", teamList.getTeamId());//团办号
		map.put("company", teamList.getCompany());//单位名称
		map.put("crtDate", teamList.getCrtDate());//创建日期
		map.put("approveVailudDate", teamList.getApproveVailudDate());//失效时间
		
		int count = teamService.queryTeamListCount(map);

		List<TeamName> list = new ArrayList<TeamName>();

		if (count > 0) {
			
			list = teamService.queryTeamList(map, curNum, pageNum);
			
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("total", count);

		dataMap.put("rows", list);

		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 删除团办名单库列表信息
	 */
	public void deleteTeamName(Context ctx){
		List<String> ids = ctx.getData("ids");
		try{
			for (String autoId : ids) {
				teamService.deleteTeamNameByAutoId(autoId);
			}
			ctx.setData("isSuccess", true);
		}catch(Exception e){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 新增团办名单
	 * @throws CoreException 
	 * @throws ParseException 
	 */
	public void saveTeamName(Context ctx) throws CoreException, ParseException{
		
		String approveTime = ctx.getData("approveTime");
		String approveVailudDate = ctx.getData("approveVailudDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map = ctx.getDataMap();		
		if("".equals(approveTime)|| null == approveTime){
			map.remove("approveTime");
		}else{
			Date parseApproveTime = sdf.parse((String) map.get("approveTime"));
			map.put("approveTime", parseApproveTime);
		}
		if("".equals(approveVailudDate)||null == approveVailudDate ){
			map.remove("approveVailudDate");
		}else{
			Date parseApproveVailudDate = sdf.parse((String) map.get("approveVailudDate"));
			map.put("approveVailudDate", parseApproveVailudDate);
		}
		map.put("crtTime", new Date());
		map.put("crtDate",new Date());
		Gson gson = new Gson();
		TeamName teamName = gson.fromJson(gson.toJson(map), TeamName.class);
		String userId = ctx.getData("userId");
		teamName.setAutoId(SequenceUtil.getUUID());
		teamName.setCrtUser(userId);
		try {
			int i = teamService.save(teamName);
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
	 * 修改团办名单
	 * @throws ParseException 
	 * @throws CoreException 
	 */
	public void updateTeamName(Context ctx) throws ParseException, CoreException{
		
		String approveTime = ctx.getData("approveTime");
		String approveVailudDate = ctx.getData("approveVailudDate");
		
		Map<String, Object> map = ctx.getDataMap();		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if("".equals(approveTime)|| null == approveTime){
			map.remove("approveTime");
		}else{
			Date parseApproveTime = sdf.parse((String) map.get("approveTime"));
			map.put("approveTime", parseApproveTime);
		}
		if("".equals(approveVailudDate)||null == approveVailudDate ){
			map.remove("approveVailudDate");
		}else{
			Date parseApproveVailudDate = sdf.parse((String) map.get("approveVailudDate"));
			map.put("approveVailudDate", parseApproveVailudDate);
		}
		map.put("crtDate",new Date());
		Gson gson = new Gson();
		TeamName teamName = gson.fromJson(gson.toJson(map), TeamName.class);
		String userId = ctx.getData("userId");
		teamName.setCrtUser(userId);
		try {
			int i = teamService.update(teamName);
			if(i>0){
				ctx.setData("isSuccess", true);
			}
		} catch (Exception e) {
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 停用名单库
	 * @throws Exception 
	 */
	public void noTeamName(Context ctx) throws Exception{
		List<String> ids = ctx.getData("ids");
		try{
			for (String autoId : ids) {
				TeamName teamName = new TeamName();
				teamName.setAutoId(autoId);
				teamName.setStatus("0");
				teamService.NoTeamName(teamName);
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
	 * @throws Exception 
	 */
	public void okTeamName(Context ctx) throws Exception{
		List<String> ids = ctx.getData("ids");
		try{
			for (String autoId : ids) {
				TeamName teamName = new TeamName();
				teamName.setAutoId(autoId);
				teamName.setStatus("1");
				teamService.OkTeamName(teamName);
			}
		}catch(Exception e){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg",e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess",true);
	}
	
	/**
	 * 名单库历史操作查询
	 */
	public void showTeamNameHis(Context ctx){
		String autoId = ctx.getData("autoId");
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		int curNum = 0;
		
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		
		int count = teamService.showTeamNameHisCount(autoId);
		
		List<TeamName> list = new ArrayList<TeamName>();
		
		if(count >0 ){
			list =teamService.showTeamNameHisList(autoId,curNum,pageNum);
		}
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		
		dataMap.put("rows", list);
		
		ctx.setDataMap(dataMap);
	}
}
