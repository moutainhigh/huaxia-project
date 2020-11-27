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
import com.huaxia.opas.domain.sysparm.TargetcompanyList;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.sysparm.TargetCompanyService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.SequenceUtil;


/**
 * 目标企业名单
 * 修复页面中文名问题
 * @author wangtao
 *@version v1.1 初始版本v1.1 
 *2017-10-10
 */
public class TargetCompanyAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(TargetCompanyAction.class);

	@Resource(name = "targetCompanyService")
	private TargetCompanyService targetCompanyService;

	@Resource(name = "apUserService")
	private ApUserService  apUserService;
	/**
	 * 查询列表信息
	 */
	public void targetCompanyList(Context ctx) throws CoreException {
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
		TargetcompanyList targetcompanylist = gson.fromJson(gson.toJson(map), TargetcompanyList.class);
		if(!"".equals(createTime1)){
			targetcompanylist.setCreateTime(createTime);
		}
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = targetCompanyService.queryTargetCompanyCount(targetcompanylist);
		List<TargetcompanyList> list = new ArrayList<TargetcompanyList>();
		if (count > 0) {
			list = targetCompanyService.queryTargetCompany(targetcompanylist, curNum, pageNum);
			for (TargetcompanyList targetcompanyList2 : list) {
				String operator = targetcompanyList2.getOperator();
				if(operator!=null||!"".equals(operator)){
					ApUser apUser = apUserService.queryApUserByUserCode(operator);
					if(apUser!=null){
						targetcompanyList2.setOperator(apUser.getUserName());
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
	public void updTargetCompanyRist(Context ctx) throws CoreException {
		Map<String, Object> map = ctx.getDataMap();
		String autoId = (String) map.get("autoId");
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
		TargetcompanyList targetcompanylist = gson.fromJson(gson.toJson(map), TargetcompanyList.class);		
		String companyName = targetcompanylist.getCompanyName();
		if(!"".equals(createTime1)){
			targetcompanylist.setCreateTime(createTime);
		}
		//修改时，在历史记录表里增加数据
		TargetcompanyList targetcompanylist1 = targetCompanyService.selectByPrimaryKey(autoId);
		TargetcompanyList targetcompanylisthistory = new TargetcompanyList();
		targetcompanylisthistory.setId(SequenceUtil.getUUID());
		targetcompanylisthistory.setAutoId(autoId);
		targetcompanylisthistory.setCompanyName(targetcompanylist1.getCompanyName());
		targetcompanylisthistory.setCompanyType(targetcompanylist1.getCompanyType());  
		targetcompanylisthistory.setOperatTime(targetcompanylist1.getOperatTime());
		targetcompanylisthistory.setCurrStatus(targetcompanylist1.getCurrStatus());
		targetcompanylisthistory.setOperator(targetcompanylist1.getOperator());
		targetcompanylisthistory.setCreateTime(targetcompanylist1.getCreateTime());
		if(!targetcompanylist.getCompanyName().equals(targetcompanylist1.getCompanyName())){
			int n = targetCompanyService.queryCompanyName(companyName);
			if(n>0){
				ctx.setData("isFalse", true);	
				return;
			}
		}
		try {
			targetCompanyService.insertHisSelective(targetcompanylisthistory);
			targetCompanyService.updateByPrimaryKeySelective(targetcompanylist);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);

	}

	/**
	 * 批量删除信息
	 */
	public void delTargetCompanyRist(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		List<String> autoid =  (List<String>) map.get("ids");
		try {	
		for(int i = 0;i<autoid.size();i++){
			String autoId=autoid.get(i);
			//删除时，在历史记录表里增加数据
			targetCompanyService.deleteByPrimaryKey(autoId);
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
	public void addTargetCompanyRist(Context ctx) throws CoreException {
		Gson gson = new Gson();
		TargetcompanyList targetcompanylist = gson.fromJson(gson.toJson(ctx.getDataMap()), TargetcompanyList.class);
		String companyName = targetcompanylist.getCompanyName();
		targetcompanylist.setAutoId(SequenceUtil.getUUID());
		String userOperator = ctx.getData("userId");	
		targetcompanylist.setOperator(userOperator);
		//启停状态：（下拉框）,名单库启用或停用，导入新增时不输入默认为启用
		String currStatus = targetcompanylist.getCurrStatus();
		if (null == currStatus || "".equals(currStatus)) {
			targetcompanylist.setCurrStatus("1");
		}
		int n = targetCompanyService.queryCompanyName(companyName);
		if(n>0){
			ctx.setData("isFalse", true);	
			return;
		}
		try {
			targetCompanyService.insertSelective(targetcompanylist);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 修改状态
	 */
	public void updateStatusTargetCompany(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		String obj = (String) map.get("obj");
		List<String> autoid = (List<String>) map.get("ids");
		try {	
		for(int i = 0;i<autoid.size();i++){
			String autoId=autoid.get(i);
			TargetcompanyList targetcompanylist1 = targetCompanyService.selectByPrimaryKey(autoId);
			TargetcompanyList targetcompanylisthistory = new TargetcompanyList();
			targetcompanylisthistory.setId(SequenceUtil.getUUID());
			targetcompanylisthistory.setAutoId(autoId);
			targetcompanylisthistory.setCompanyName(targetcompanylist1.getCompanyName());
			targetcompanylisthistory.setCompanyType(targetcompanylist1.getCompanyType());  
			targetcompanylisthistory.setOperatTime(targetcompanylist1.getOperatTime());
			targetcompanylisthistory.setCurrStatus(targetcompanylist1.getCurrStatus());
			targetcompanylisthistory.setOperator(targetcompanylist1.getOperator());
			targetcompanylisthistory.setCreateTime(targetcompanylist1.getCreateTime());
			if(obj.equals("start")){
				targetCompanyService.updateStartStatus(autoId);
				if(targetcompanylist1.getCurrStatus().equals("0")){
					targetCompanyService.insertHisSelective(targetcompanylisthistory);
				}
			}else{
				targetCompanyService.updateStopStatus(autoId);
				if(targetcompanylist1.getCurrStatus().equals("1")){
					targetCompanyService.insertHisSelective(targetcompanylisthistory);
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
	public void TargetCompanyHistorylist(Context ctx) throws CoreException {
		String autoId = (String) ctx.getDataMap().get("autoId");
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = targetCompanyService.queryTargetCompHistoryCount(autoId);
		List<TargetcompanyList> list = new ArrayList<TargetcompanyList>();
		if (count > 0) {
			list = targetCompanyService.queryTargetCompanyHistory(autoId, curNum, pageNum);
			for (TargetcompanyList targetcompanyList : list) {
				String operator = targetcompanyList.getOperator();
				if(operator!=null||!"".equals(operator)){
					ApUser apUser = apUserService.queryApUserByUserCode(operator);
					if(apUser!=null){
						targetcompanyList.setOperator(apUser.getUserName());
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
