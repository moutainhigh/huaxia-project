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
import com.huaxia.opas.domain.sysparm.GoodcompanyList;
import com.huaxia.opas.domain.sysparm.GoodcompanyListHistory;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.sysparm.GoodCompanyListService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.SequenceUtil;


/**
 * 优质企业名单
 * 修复页面显示中文名问题
 * @author wangtao
 *@version 2017-10-10 v1.1 初始版本v1.0
 */
public class GoodCompanyAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(GoodCompanyAction.class);

	@Resource(name = "goodCompanyListService")
	private GoodCompanyListService goodCompanyListService;
	@Resource(name = "apUserService")
	private ApUserService  apUserService;
	/**
	 * 查询列表信息
	 */
	public void goodCompanyList(Context ctx) throws CoreException {
		Map<String,Object> map = ctx.getDataMap();
		String createTime1 = (String)map.get("createTime");
		Date createTime = null;
		if(!"".equals(createTime1)&&null!=createTime1){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				createTime = sdf.parse(createTime1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		map.remove("createTime");
		Gson gson = new Gson();
		GoodcompanyList goodcompanylist = gson.fromJson(gson.toJson(map), GoodcompanyList.class);
		if(!"".equals(createTime1)){
			goodcompanylist.setCreateTime(createTime);
		}
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = goodCompanyListService.queryCoodCompanyCount(goodcompanylist);
		List<GoodcompanyList> list = new ArrayList<GoodcompanyList>();
		if (count > 0) {
			list = goodCompanyListService.queryCoodCompanyList(goodcompanylist, curNum, pageNum);
			for (GoodcompanyList goodcompanyList2 : list) {
				String operator = goodcompanyList2.getOperator();
				if(operator!=null&&!"".equals(operator)){
					ApUser apUser = apUserService.queryApUserByUserCode(operator);
					if(apUser!=null){
						goodcompanyList2.setOperator(apUser.getUserName());
					}
				}
			}
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 修改
	 */
	public void updGoodCompanyRist(Context ctx) throws CoreException {
		Map<String, Object> map = ctx.getDataMap();
		String autoId = (String) map.get("autoId");
		String createTime1 = (String)map.get("createTime");
		String startTime1 = (String)map.get("startTime");
		String endTime1 = (String)map.get("endTime");
		Date createTime = null;
		Date startTime = null;
		Date endTime = null;
		if(!"".equals(createTime1)&&null!=createTime1){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				createTime = sdf.parse(createTime1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(!"".equals(endTime1)&&null!=endTime1){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				endTime = sdf.parse(endTime1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(!"".equals(startTime1)&&null!=startTime1){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				startTime = sdf.parse(startTime1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		map.remove("createTime");
		map.remove("endTime");
		map.remove("startTime");
		Gson gson = new Gson();
		GoodcompanyList goodcompanylist = gson.fromJson(gson.toJson(map), GoodcompanyList.class);
		String companyName = goodcompanylist.getCompanyName();
		if(!"".equals(createTime1)){
			goodcompanylist.setCreateTime(createTime);
		}
		if(!"".equals(endTime1)){
			goodcompanylist.setEndTime(endTime);
		}
		if(!"".equals(startTime1)){
			goodcompanylist.setStartTime(startTime);
		}
		GoodcompanyList goodcompanylists = goodCompanyListService.selectByPrimaryKey(autoId);
		if(!goodcompanylists.getCompanyName().equals(goodcompanylist.getCompanyName())){
			int count = goodCompanyListService.queryByCompanyName(companyName);
			if(count>0){
				ctx.setData("isFalse", true);
				return;
			}
		}
		//修改时，在历史记录表里增加数据
		GoodcompanyList goodcompanylist1 = goodCompanyListService.selectByPrimaryKey(autoId);
		GoodcompanyListHistory goodcompanylisthistory = new GoodcompanyListHistory();
		goodcompanylisthistory.setId(SequenceUtil.getUUID());
		goodcompanylisthistory.setAutoId(autoId);
		goodcompanylisthistory.setArea(goodcompanylist1.getArea());
		goodcompanylisthistory.setOperatTime(goodcompanylist1.getOperatTime());
		goodcompanylisthistory.setCurrStatus(goodcompanylist1.getCurrStatus());
		goodcompanylisthistory.setOperator(goodcompanylist1.getOperator());
		goodcompanylisthistory.setCompanyName(goodcompanylist1.getCompanyName());
		goodcompanylisthistory.setEndTime(goodcompanylist1.getEndTime());
		goodcompanylisthistory.setProjectCode(goodcompanylist1.getProjectCode());
		goodcompanylisthistory.setStartTime(goodcompanylist1.getStartTime());
		goodcompanylisthistory.setCreateTime(goodcompanylist1.getCreateTime());
		try {
			goodCompanyListService.insertHisSelective(goodcompanylisthistory);
			goodCompanyListService.updateByPrimaryKeySelective(goodcompanylist);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);

	}

	/**
	 * 批量删除信息
	 */
	public void delGoodCompanyRist(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		List<String> autoid =  (List<String>) map.get("ids");
		try {	
		for(int i = 0;i<autoid.size();i++){
			String autoId=autoid.get(i);
			//删除时，在历史记录表里增加数据
			goodCompanyListService.deleteByPrimaryKey(autoId);
		   }
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 新增信息
	 */
	public void addGoodCompanyRist(Context ctx) throws CoreException {
		Map<String,Object> map = ctx.getDataMap();
		String startTime1 = (String)map.get("startTime");
		String endTime1 = (String)map.get("endTime");
		Date createTime = null;
		Date startTime = null;
		Date endTime = null;
		if(!"".equals(endTime1)&&null!=endTime1){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				endTime = sdf.parse(endTime1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(!"".equals(startTime1)&&null!=startTime1){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				startTime = sdf.parse(startTime1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		map.remove("createTime");
		map.remove("endTime");
		map.remove("startTime");
		Gson gson = new Gson();
		GoodcompanyList goodcompanylist = gson.fromJson(gson.toJson(map), GoodcompanyList.class);
		String companyName = goodcompanylist.getCompanyName();
		if(!"".equals(endTime1)){
			goodcompanylist.setEndTime(endTime);
		}
		if(!"".equals(startTime1)){
			goodcompanylist.setStartTime(startTime);
		}
		goodcompanylist.setAutoId(SequenceUtil.getUUID());
		String userOperator = ctx.getData("userId");	
		goodcompanylist.setOperator(userOperator);
		//启停状态：（下拉框）,名单库启用或停用，导入新增时不输入默认为启用
		String currStatus = goodcompanylist.getCurrStatus();
		if (null == currStatus || "".equals(currStatus)) {
			goodcompanylist.setCurrStatus("1");
		}
		//添加校验公司名称不能重复
		int count = goodCompanyListService.queryByCompanyName(companyName);
		if(count>0){
			ctx.setData("isFalse", true);
			return;
		}
		try {
			goodCompanyListService.insertSelective(goodcompanylist);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 修改状态
	 */
	public void updateStatusGoodCompany(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		String obj = (String) map.get("obj");
		List<String> autoid = (List<String>) map.get("ids");
		try {	
		for(int i = 0;i<autoid.size();i++){
			String autoId=autoid.get(i);
			GoodcompanyList goodcompanylist1 = goodCompanyListService.selectByPrimaryKey(autoId);
			GoodcompanyListHistory goodcompanylisthistory = new GoodcompanyListHistory();
			goodcompanylisthistory.setId(SequenceUtil.getUUID());
			goodcompanylisthistory.setAutoId(autoId);
			goodcompanylisthistory.setArea(goodcompanylist1.getArea());
			goodcompanylisthistory.setOperatTime(goodcompanylist1.getOperatTime());
			goodcompanylisthistory.setCurrStatus(goodcompanylist1.getCurrStatus());
			goodcompanylisthistory.setOperator(goodcompanylist1.getOperator());
			goodcompanylisthistory.setCompanyName(goodcompanylist1.getCompanyName());
			goodcompanylisthistory.setEndTime(goodcompanylist1.getEndTime());
			goodcompanylisthistory.setProjectCode(goodcompanylist1.getProjectCode());
			goodcompanylisthistory.setStartTime(goodcompanylist1.getStartTime());
			goodcompanylisthistory.setCreateTime(goodcompanylist1.getCreateTime());
			if(obj.equals("start")){
				goodCompanyListService.updateStartStatus(autoId);
				if(goodcompanylist1.getCurrStatus().equals("0")){
					goodCompanyListService.insertHisSelective(goodcompanylisthistory);
				}
			}else{
				goodCompanyListService.updateStopStatus(autoId);
				if(goodcompanylist1.getCurrStatus().equals("1")){
					goodCompanyListService.insertHisSelective(goodcompanylisthistory);
				}
			}
		   }
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}
	
	/**
	 * 查看修改記錄  查询方法
	 */
	public void GoodCompanyHistorylist(Context ctx) throws CoreException {
		String autoId = (String) ctx.getDataMap().get("autoId");
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = goodCompanyListService.queryCoodCompanyHistoryCount(autoId);
		List<GoodcompanyList> list = new ArrayList<GoodcompanyList>();
		if (count > 0) {
			list = goodCompanyListService.queryCoodCompanyHistory(autoId, curNum, pageNum);
			for (GoodcompanyList goodcompanyList : list) {
				String operator = goodcompanyList.getOperator();
				if(operator!=null&&!"".equals(operator)){
					ApUser apUser = apUserService.queryApUserByUserCode(operator);
					if(apUser!=null){
						goodcompanyList.setOperator(apUser.getUserName());
					}
				}
			}
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
}
