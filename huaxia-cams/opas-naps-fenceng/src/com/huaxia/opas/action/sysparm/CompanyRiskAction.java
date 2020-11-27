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
import com.huaxia.opas.domain.riskcheck.CompanyRisk;
import com.huaxia.opas.domain.riskcheck.CompanyRiskHistory;
import com.huaxia.opas.service.sysparm.CompanyRiskService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 单位类风险名单库
 * 
 * @date 2017年2月28日下午2:09:40
 * @author wangy
 */
public class CompanyRiskAction implements Action {
	private static Logger logger = LoggerFactory.getLogger(CompanyRiskAction.class);
	@Resource(name = "companyRiskService")
	private CompanyRiskService companyRiskService;

	/**
	 * 查询单位类风险名单
	 */
	public void queryCompanyRiskList(Context ctx) throws CoreException {

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
		CompanyRisk companyRisk = gson.fromJson(gson.toJson(map), CompanyRisk.class);
		if(!"".equals(createTime1)){
			companyRisk.setCreateTime(createTime);
		}
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		int count = companyRiskService.queryCompanyRiskCount(companyRisk);
		List<CompanyRisk> list = new ArrayList<CompanyRisk>();
		if (count > 0) {
			list = companyRiskService.queryCompanyRiskList(companyRisk, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 添加单位类风险名单
	 */
	public void addCompanyRiskList(Context ctx) throws CoreException {
		Map<String,Object> map = ctx.getDataMap();
		String invalidTime1 = (String) map.get("invalidTime");
		Date invalidTime = null;
		if(!"".equals(invalidTime1)&&null!=invalidTime1){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				invalidTime = sdf.parse(invalidTime1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		map.remove("invalidTime");
		
		Gson gson = new Gson();
		
		CompanyRisk companyRisk = gson.fromJson(gson.toJson(map), CompanyRisk.class);
		String companyName = companyRisk.getCompanyName();
		int count = companyRiskService.queryByComName(companyName);
		if(count > 0){
			ctx.setData("isComName", true);
			return;
		}
		if(!"".equals(invalidTime1)){
			companyRisk.setInvalidTime(invalidTime);
		}
		companyRisk.setAutoId(SequenceUtil.getUUID());
		String userOperator = ctx.getData("userId");	
		companyRisk.setOperator(userOperator);
		String currStatus = companyRisk.getCurrStatus();
		if (null == currStatus || "".equals(currStatus)) {
			companyRisk.setCurrStatus("1");
		}
		try {
			companyRiskService.insertCompanyRisk(companyRisk);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);

	}

	
	/**
	 * 修改单位类风险名单
	 */
	public void updateCompanyRisk(Context ctx) throws CoreException {

		Map<String,Object> map = ctx.getDataMap();
		String invalidTime1 = (String)map.get("invalidTime");
		Date invalidTime = null;
		if(!"".equals(invalidTime1)&&null!=invalidTime1){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				invalidTime = sdf.parse(invalidTime1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		map.remove("invalidTime");
		Gson gson = new Gson();
		CompanyRisk companyRisk = gson.fromJson(gson.toJson(map), CompanyRisk.class);
		String autoId = companyRisk.getAutoId();
		String userOperator = ctx.getData("userId");	
		companyRisk.setOperator(userOperator);
		CompanyRisk companyrisk = companyRiskService.selectByPrimaryKey(autoId);
		String companyName = companyRisk.getCompanyName();
		int count = companyRiskService.queryByComName(companyName);
		if(!companyrisk.getCompanyName().equals(companyRisk.getCompanyName())){
			if(count > 0){
				ctx.setData("isComName", true);
				return;
			}
		}
		if(!"".equals(invalidTime1)){
			companyRisk.setInvalidTime(invalidTime);
		}
		try {
			CompanyRisk companyrisk1 = companyRiskService.selectByPrimaryKey(autoId);
			CompanyRiskHistory companyRiskHistory = new CompanyRiskHistory();
			companyRiskHistory.setId(SequenceUtil.getUUID());
			companyRiskHistory.setAutoId(autoId);
			companyRiskHistory.setCompanyName(companyrisk1.getCompanyName());
			companyRiskHistory.setCreateTime(companyrisk1.getCreateTime());
			companyRiskHistory.setCurrStatus(companyrisk1.getCurrStatus());
			companyRiskHistory.setFraudType(companyrisk1.getFraudType());
			companyRiskHistory.setInfoChannel(companyrisk1.getInfoChannel());
			companyRiskHistory.setInvalidTime(companyrisk1.getInvalidTime());
			companyRiskHistory.setMemo(companyrisk1.getMemo());
			companyRiskHistory.setOperator(companyrisk1.getOperator());
			companyRiskHistory.setOperatTime(companyrisk1.getOperatTime());
			companyRiskService.insertCompanyRiskHis(companyRiskHistory);
			companyRiskService.updateCompanyRisk(companyRisk);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);


	}

	/**
	 * 删除单位类风险名单
	 */
	public void deleteCompanyRisk(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		List<String> autoid =  (List<String>) map.get("ids");
		try {	
		for(int i = 0;i<autoid.size();i++){
			String autoId=autoid.get(i);
			companyRiskService.deleteCompanyRisk(autoId);
		   }
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}
	
	/**
	 * 修改单位类风险名单状态
	 */
	public void updateStatusCompanyRisk(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		String obj = (String) map.get("obj");
		List<String> autoid =  (List<String>) map.get("ids");
		try {	
		for(int i = 0;i<autoid.size();i++){
			String autoId=autoid.get(i);
			CompanyRisk companyrisk = companyRiskService.selectByPrimaryKey(autoId);
			CompanyRiskHistory companyRiskHistory = new CompanyRiskHistory();
			companyRiskHistory.setId(SequenceUtil.getUUID());
			companyRiskHistory.setAutoId(autoId);
			companyRiskHistory.setCompanyName(companyrisk.getCompanyName());
			companyRiskHistory.setCreateTime(companyrisk.getCreateTime());
			companyRiskHistory.setCurrStatus(companyrisk.getCurrStatus());
			companyRiskHistory.setFraudType(companyrisk.getFraudType());
			companyRiskHistory.setInfoChannel(companyrisk.getInfoChannel());
			companyRiskHistory.setInvalidTime(companyrisk.getInvalidTime());
			companyRiskHistory.setMemo(companyrisk.getMemo());
			companyRiskHistory.setOperator(companyrisk.getOperator());
			companyRiskHistory.setOperatTime(companyrisk.getOperatTime());
			if(obj.equals("start")){
				companyRiskService.updateStartStatus(autoId);
				if(companyrisk.getCurrStatus().equals("0")){
					companyRiskService.insertCompanyRiskHis(companyRiskHistory);
				}
			}else{
				companyRiskService.updateStopStatus(autoId);
				if(companyrisk.getCurrStatus().equals("1")){
					companyRiskService.insertCompanyRiskHis(companyRiskHistory);
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
	public void companyRistHistorylist(Context ctx) throws CoreException {
		String autoId = (String) ctx.getDataMap().get("autoId");
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = companyRiskService.queryCompanyRistHistoryCount(autoId);
		List<CompanyRisk> list = new ArrayList<CompanyRisk>();
		if (count > 0) {
			list = companyRiskService.queryCompanyRistListHistory(autoId, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
}
