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
import com.huaxia.opas.domain.sysparm.CompanyInfoList;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.sysparm.CompanyInfoService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.SequenceUtil;

public class CompanyInfoAction implements Action{


	private static Logger logger = LoggerFactory.getLogger(TargetCompanyAction.class);

	@Resource(name = "companyInfoService")
	private CompanyInfoService companyInfoService;

	@Resource(name = "apUserService")
	private ApUserService  apUserService;
	/**
	 * 查询列表信息
	 */
	public void companyInfoList(Context ctx) throws CoreException {
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
		CompanyInfoList companyInfoList = gson.fromJson(gson.toJson(map), CompanyInfoList.class);
		if(!"".equals(createTime1)){
			companyInfoList.setCreateTime(createTime);
		}
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = companyInfoService.queryCompanyInfoCount(companyInfoList);
		List<CompanyInfoList> list = new ArrayList<CompanyInfoList>();
		if (count > 0) {
			list = companyInfoService.queryCompanyInfo(companyInfoList, curNum, pageNum);
			for (CompanyInfoList companyInfoList2 : list) {
				String operator = companyInfoList2.getOperator();
				if(operator!=null||!"".equals(operator)){
					ApUser apUser = apUserService.queryApUserByUserCode(operator);
					if(apUser!=null){
						companyInfoList2.setOperator(apUser.getUserName());
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
	public void updCompanyInfoRist(Context ctx) throws CoreException {
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
		CompanyInfoList companyInfolist = gson.fromJson(gson.toJson(map), CompanyInfoList.class);		
		String companyName = companyInfolist.getCompanyName();
		if(!"".equals(createTime1)){
			companyInfolist.setCreateTime(createTime);
		}
		//修改时，在历史记录表里增加数据
		CompanyInfoList companyInfolist1 = companyInfoService.selectByPrimaryKey(autoId);
		/*CompanyInfoList companyInfolisthistory = new CompanyInfoList();
		companyInfolisthistory.setId(SequenceUtil.getUUID());
		companyInfolisthistory.setAutoId(autoId);
		companyInfolisthistory.setCompanyName(companyInfolist1.getCompanyName());
		companyInfolisthistory.setBizIndustry(companyInfolist1.getBizIndustry());  
		companyInfolisthistory.setOperatTime(companyInfolist1.getOperatTime());
		//companyInfolisthistory.setCurrStatus(companyInfolist1.getBusinessLevel());
		companyInfolisthistory.setBusinessLevel(companyInfolist1.getBusinessLevel());
		companyInfolisthistory.setBusinessType(companyInfolist1.getBusinessType());
		companyInfolisthistory.setSecuritiesAbbreviation(companyInfolist1.getSecuritiesAbbreviation());
		companyInfolisthistory.setCompanyProperty(companyInfolist1.getCompanyProperty());
		companyInfolisthistory.setOperator(companyInfolist1.getOperator());
		companyInfolisthistory.setCreateUser(companyInfolist1.getCreateUser());
		companyInfolisthistory.setCreateTime(companyInfolist1.getCreateTime());*/
		if(!companyInfolist.getCompanyName().equals(companyInfolist1.getCompanyName())){
			int n = companyInfoService.queryCompanyName(companyName);
			if(n>0){
				ctx.setData("isFalse", true);	
				return;
			}
		}
		try {
			//companyInfoService.insertHisSelective(companyInfolisthistory);
			companyInfoService.updateByPrimaryKeySelective(companyInfolist);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);

	}

	/**
	 * 批量删除信息
	 */
	public void delCompanyInfoRist(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		List<String> autoid =  (List<String>) map.get("ids");
		try {	
		for(int i = 0;i<autoid.size();i++){
			String autoId=autoid.get(i);
			//删除时，在历史记录表里增加数据
			companyInfoService.deleteByPrimaryKey(autoId);
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
	public void addCompanyInfoRist(Context ctx) throws CoreException {
		Gson gson = new Gson();
		CompanyInfoList companyInfolist = gson.fromJson(gson.toJson(ctx.getDataMap()), CompanyInfoList.class);
		String companyName = companyInfolist.getCompanyName();
		companyInfolist.setAutoId(SequenceUtil.getUUID());
		String userOperator = ctx.getData("userId");	
		companyInfolist.setOperator(userOperator);
		companyInfolist.setCreateUser(userOperator);
		//启停状态：（下拉框）,名单库启用或停用，导入新增时不输入默认为启用
		/*String currStatus = companyInfolist.getCurrStatus();
		if (null == currStatus || "".equals(currStatus)) {
			companyInfolist.setCurrStatus("1");
		}*/
		int n = companyInfoService.queryCompanyName(companyName);
		if(n>0){
			ctx.setData("isFalse", true);	
			return;
		}
		try {
			companyInfoService.insertSelective(companyInfolist);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 修改状态
	 */
	public void updateStatusCompanyInfo(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		String obj = (String) map.get("obj");
		List<String> autoid = (List<String>) map.get("ids");
		try {	
		for(int i = 0;i<autoid.size();i++){
			String autoId=autoid.get(i);
			CompanyInfoList companyInfolist1 = companyInfoService.selectByPrimaryKey(autoId);
			CompanyInfoList companyInfolisthistory = new CompanyInfoList();
			companyInfolisthistory.setId(SequenceUtil.getUUID());
			companyInfolisthistory.setAutoId(autoId);
			companyInfolisthistory.setCompanyName(companyInfolist1.getCompanyName());
			companyInfolisthistory.setBizIndustry(companyInfolist1.getBizIndustry());  
			companyInfolisthistory.setOperatTime(companyInfolist1.getOperatTime());
			//companyInfolisthistory.setCurrStatus(companyInfolist1.getBusinessLevel());
			companyInfolisthistory.setBusinessLevel(companyInfolist1.getBusinessLevel());
			companyInfolisthistory.setBusinessType(companyInfolist1.getBusinessType());
			companyInfolisthistory.setSecuritiesAbbreviation(companyInfolist1.getSecuritiesAbbreviation());
			companyInfolisthistory.setCompanyProperty(companyInfolist1.getCompanyProperty());
			companyInfolisthistory.setOperator(companyInfolist1.getOperator());
			companyInfolisthistory.setCreateUser(companyInfolist1.getCreateUser());
			companyInfolisthistory.setCreateTime(companyInfolist1.getCreateTime());
			/*if(obj.equals("start")){
				companyInfoService.updateStartStatus(autoId);
				if(companyInfolist1.getCurrStatus().equals("0")){
					companyInfoService.insertHisSelective(companyInfolisthistory);
				}
			}else{
				companyInfoService.updateStopStatus(autoId);
				if(companyInfolist1.getCurrStatus().equals("1")){
					companyInfoService.insertHisSelective(companyInfolisthistory);
				}
			}*/
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
		int count = companyInfoService.queryCompanyInfoHistoryCount(autoId);
		List<CompanyInfoList> list = new ArrayList<CompanyInfoList>();
		if (count > 0) {
			list = companyInfoService.queryCompanyInfoHistory(autoId, curNum, pageNum);
			for (CompanyInfoList companyInfoList : list) {
				String operator = companyInfoList.getOperator();
				if(operator!=null||!"".equals(operator)){
					ApUser apUser = apUserService.queryApUserByUserCode(operator);
					if(apUser!=null){
						companyInfoList.setOperator(apUser.getUserName());
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
