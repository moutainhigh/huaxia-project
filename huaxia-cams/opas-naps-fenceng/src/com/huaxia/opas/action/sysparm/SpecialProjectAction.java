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
import com.huaxia.opas.domain.sysparm.SpecialprojectList;
import com.huaxia.opas.domain.sysparm.SpecialprojectListHis;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.sysparm.SpecialProjectService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.SequenceUtil;


/**
 * 特殊项目名单
 * 
 * @author shihuan
 *
 */
public class SpecialProjectAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(SpecialProjectAction.class);

	@Resource(name = "specialProjectService")
	private SpecialProjectService specialProjectService;

	@Resource(name = "apUserService")
	private ApUserService  apUserService;
	/**
	 * 查询列表信息
	 */
	public void specialProjecList(Context ctx) throws CoreException {
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
		SpecialprojectList specialprojectlist = gson.fromJson(gson.toJson(map), SpecialprojectList.class);
		if(!"".equals(createTime1)){
			specialprojectlist.setCreateTime(createTime);
		}
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = specialProjectService.querySpecialprojectCount(specialprojectlist);
		List<SpecialprojectList> list = new ArrayList<SpecialprojectList>();
		if (count > 0) {
			list = specialProjectService.querySpecialprojectList(specialprojectlist, curNum, pageNum);
			for (SpecialprojectList specialprojectList2 : list) {
				String operator = specialprojectList2.getOperator();
				if(operator!=null&&!"".equals(operator)){
					ApUser apUser = apUserService.queryApUserByUserCode(operator);
					if(apUser!=null){
						specialprojectList2.setOperator(apUser.getUserName());
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
	public void updSpecialProjec(Context ctx) throws CoreException {
		Map<String,Object> map = ctx.getDataMap();
		String autoId = (String) map.get("autoId");
		String startDate1 = (String) map.get("startDate");
		Date startDate = null;
		if(!"".equals(startDate1)&&null!=startDate1){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				startDate = sdf.parse(startDate1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		map.remove("startDate");
		String endDate1 = (String) map.get("endDate");
		Date endDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(!"".equals(endDate1)&&null!=endDate1){
			try {
				endDate = sdf.parse(endDate1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		map.remove("endDate");
		Gson gson = new Gson();
		SpecialprojectList specialprojectlist = gson.fromJson(gson.toJson(map), SpecialprojectList.class);
		String projectCode = specialprojectlist.getProjectCode();
		//修改时，在历史记录表里增加数据
		SpecialprojectList specialprojectlist1 = specialProjectService.selectByPrimaryKey(autoId);
		SpecialprojectListHis specialprojectlisthis = new SpecialprojectListHis();
		specialprojectlisthis.setId(SequenceUtil.getUUID());
		specialprojectlisthis.setAutoId(autoId);
		specialprojectlisthis.setCompanyName(specialprojectlist1.getCompanyName());
		specialprojectlisthis.setCreateTime(specialprojectlist1.getCreateTime());
		specialprojectlisthis.setCurrStatus(specialprojectlist1.getCurrStatus());
		specialprojectlisthis.setEndDate(specialprojectlist1.getEndDate());
		specialprojectlisthis.setOperator(specialprojectlist1.getOperator());
		specialprojectlisthis.setOperatTime(specialprojectlist1.getOperatTime());
		specialprojectlisthis.setProjectCode(specialprojectlist1.getProjectCode());
		specialprojectlisthis.setStartDate(specialprojectlist1.getStartDate());
		if(!specialprojectlist.getProjectCode().equals(specialprojectlist1.getProjectCode())){
			int count = specialProjectService.queryProjectCode(projectCode);
			if(count>0){
				ctx.setData("isFalse", true);
				return;
			}
		}
		if(!"".equals(startDate1)){
			specialprojectlist.setStartDate(startDate);
		}
		if(!"".equals(endDate1)){
			specialprojectlist.setEndDate(endDate);
		}else{
			Date date = null;
			try {
				date = sdf.parse("2099-12-31");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			specialprojectlist.setEndDate(date);
		}
		try {
			specialProjectService.insertHisSelective(specialprojectlisthis);
			specialProjectService.updateByPrimaryKeySelective(specialprojectlist);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);

	}

	/**
	 * 批量删除信息
	 */
	public void delSpecialProjec(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		List<String> autoid =  (List<String>) map.get("ids");
		try {	
		for(int i = 0;i<autoid.size();i++){
			String autoId=autoid.get(i);
			//删除时，在历史记录表里增加数据
			specialProjectService.deleteByPrimaryKey(autoId);
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
	public void addSpecialProject(Context ctx) throws CoreException {
		Map<String,Object> map = ctx.getDataMap();
		String startDate1 = (String) map.get("startDate");
		Date startDate = null;
		if(!"".equals(startDate1)&&null!=startDate1){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				startDate = sdf.parse(startDate1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		map.remove("startDate");
		String endDate1 = (String) map.get("endDate");
		Date endDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(!"".equals(endDate1)&&null!=endDate1){
			try {
				endDate = sdf.parse(endDate1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		map.remove("endDate");
		Gson gson = new Gson();
		SpecialprojectList specialprojectlist = gson.fromJson(gson.toJson(map), SpecialprojectList.class);
		String projectCode = specialprojectlist.getProjectCode();
		int n = specialProjectService.queryProjectCode(projectCode);
		if(n>0){
			ctx.setData("isFalse", true);
			return;
		}
		specialprojectlist.setAutoId(SequenceUtil.getUUID());
		if(!"".equals(startDate1)){
			specialprojectlist.setStartDate(startDate);
		}
		if(!"".equals(endDate1)){
			specialprojectlist.setEndDate(endDate);
		}else{
			Date date = null;
			try {
				date = sdf.parse("2099-12-31");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			specialprojectlist.setEndDate(date);
		}
		String userOperator = ctx.getData("userId");	
		specialprojectlist.setOperator(userOperator);
		//启停状态：（下拉框）,名单库启用或停用，导入新增时不输入默认为启用
		String currStatus = specialprojectlist.getCurrStatus();
		if (null == currStatus || "".equals(currStatus)) {
			specialprojectlist.setCurrStatus("1");
		}
		try {
			specialProjectService.insertSelective(specialprojectlist);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 修改状态
	 */
	public void updStaSpecialProject(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		String obj = (String) map.get("obj");
		List<String> autoid = (List<String>) map.get("ids");
		try {	
		for(int i = 0;i<autoid.size();i++){
			String autoId=autoid.get(i);
			SpecialprojectList specialprojectlist1 = specialProjectService.selectByPrimaryKey(autoId);
			SpecialprojectListHis specialprojectlisthis = new SpecialprojectListHis();
			specialprojectlisthis.setId(SequenceUtil.getUUID());
			specialprojectlisthis.setAutoId(autoId);
			specialprojectlisthis.setCompanyName(specialprojectlist1.getCompanyName());
			specialprojectlisthis.setCreateTime(specialprojectlist1.getCreateTime());
			specialprojectlisthis.setCurrStatus(specialprojectlist1.getCurrStatus());
			specialprojectlisthis.setEndDate(specialprojectlist1.getEndDate());
			specialprojectlisthis.setOperator(specialprojectlist1.getOperator());
			specialprojectlisthis.setOperatTime(specialprojectlist1.getOperatTime());
			specialprojectlisthis.setProjectCode(specialprojectlist1.getProjectCode());
			specialprojectlisthis.setStartDate(specialprojectlist1.getStartDate());
			if(obj.equals("start")){
				specialProjectService.updateStartStatus(autoId);
				if(specialprojectlist1.getCurrStatus().equals("0")){
					specialProjectService.insertHisSelective(specialprojectlisthis);
				}
			}else{
				specialProjectService.updateStopStatus(autoId);
				if(specialprojectlist1.getCurrStatus().equals("1")){
					specialProjectService.insertHisSelective(specialprojectlisthis);
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
	public void SpecProtHistorylist(Context ctx) throws CoreException {
		String autoId = (String) ctx.getDataMap().get("autoId");
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = specialProjectService.queryHistoryCount(autoId);
		List<SpecialprojectList> list = new ArrayList<SpecialprojectList>();
		if (count > 0) {
			list = specialProjectService.querySpecialprojectHistory(autoId, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
}
