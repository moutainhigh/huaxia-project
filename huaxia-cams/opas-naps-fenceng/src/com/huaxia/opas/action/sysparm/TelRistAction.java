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
import com.huaxia.opas.domain.riskcheck.TelRiskList;
import com.huaxia.opas.domain.riskcheck.TelRiskListHistory;
import com.huaxia.opas.service.sysparm.TelRiskListHistoryService;
import com.huaxia.opas.service.sysparm.TelRiskListService;
import com.huaxia.opas.util.SequenceUtil;


/**
 * 电话风险类名单
 * 
 * @author shihuan
 *
 */
public class TelRistAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(TelRistAction.class);

	@Resource(name = "telRiskListService")
	private TelRiskListService telRiskListService;

	@Resource(name = "telRiskListHistoryService")
	private TelRiskListHistoryService telRiskListHistoryService;
	
	/**
	 * 查询列表信息
	 */
	public void tellist(Context ctx) throws CoreException {
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
		TelRiskList telrisklist = gson.fromJson(gson.toJson(map), TelRiskList.class);
		if(!"".equals(createTime1)){
			telrisklist.setCreateTime(createTime);
		}
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = telRiskListService.queryTelRistCount(telrisklist);
		List<TelRiskList> list = new ArrayList<TelRiskList>();
		if (count > 0) {
			list = telRiskListService.queryTelRistList(telrisklist, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	
	public void updateTelRist(Context ctx) throws CoreException {
		Map<String,Object> map = ctx.getDataMap();
		String invalidTime1 = (String)map.get("invalidTime");
		String autoId = (String)map.get("autoId");
		String userOperator = ctx.getData("userId");	
		
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
		TelRiskList telrisklist = gson.fromJson(gson.toJson(map), TelRiskList.class);
		telrisklist.setUserOperator(userOperator);
		TelRiskList telrisklist1 = telRiskListService.selectByPrimaryKey(autoId);
		if(!"".equals(invalidTime1)){
			telrisklist.setInvalidTime(invalidTime);
		}
		//添加时校验手机号码不得重复添加
		if(telrisklist.getMobileId()!=null&&!"".equals(telrisklist.getMobileId())){
			int n = telRiskListService.queryByMobileId(telrisklist.getMobileId());
			if(!telrisklist.getMobileId().equals(telrisklist1.getMobileId())){
				if(n>0){
					ctx.setData("isMobileId", true);
					return;
				}
			}
		}
		//添加时校验固话不得重复添加
		if(telrisklist.getCompanyTel()!=null&&!"".equals(telrisklist.getCompanyTel())){
			int n = telRiskListService.queryByCompanyTel(telrisklist.getCompanyTel());
			if(!telrisklist.getCompanyTel().equals(telrisklist1.getCompanyTel())){
				if(n>0){
					ctx.setData("isCompanyTel", true);
					return;
				}
			}
		}
		//添加时校验固话不得重复添加
		if(telrisklist.getLivingTel()!=null&&!"".equals(telrisklist.getLivingTel())){
			int n = telRiskListService.queryByLivingTel(telrisklist.getLivingTel());
			if(!telrisklist.getLivingTel().equals(telrisklist1.getLivingTel())){
				if(n>0){
					ctx.setData("isLivingTel", true);
					return;
				}
			}
		}
		//添加时校验固话不得重复添加
		if(telrisklist.getOtherTel()!=null&&!"".equals(telrisklist.getOtherTel())){
			int n = telRiskListService.queryByOtherTel(telrisklist.getOtherTel());
			if(!telrisklist.getOtherTel().equals(telrisklist1.getOtherTel())){
				if(n>0){
					ctx.setData("isOtherTel", true);
					return;
				}
			}
		}
		TelRiskListHistory telrisklisthistory = new TelRiskListHistory();
		telrisklisthistory.setId(SequenceUtil.getUUID());
		telrisklisthistory.setAutoId(telrisklist1.getAutoId());
		telrisklisthistory.setCompanyTel(telrisklist1.getCompanyTel());
		telrisklisthistory.setCreateTime(telrisklist1.getCreateTime());
		telrisklisthistory.setCurrStatus(telrisklist1.getCurrStatus());
		telrisklisthistory.setFraudType(telrisklist1.getFraudType());
		telrisklisthistory.setInvalidTime(telrisklist1.getInvalidTime());
		telrisklisthistory.setLivingTel(telrisklist1.getLivingTel());
		telrisklisthistory.setMemo(telrisklist1.getMemo());
		telrisklisthistory.setMobileId(telrisklist1.getMobileId());
		telrisklisthistory.setOperatTime(telrisklist1.getOperatTime());
		telrisklisthistory.setOtherTel(telrisklist1.getOtherTel());
		telrisklisthistory.setUserOperator(telrisklist1.getUserOperator());
		telrisklisthistory.setInfoChannel(telrisklist1.getInfoChannel());
		try {
			telRiskListService.updateByPrimaryKeySelective(telrisklist);
			telRiskListHistoryService.insertSelective(telrisklisthistory);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);

	}

	
	public void removeTelRist(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		List<String> autoid =  (List<String>) map.get("ids");
		try {	
		for(int i = 0;i<autoid.size();i++){
			String autoId=autoid.get(i);
			telRiskListService.deleteByPrimaryKey(autoId);
		   }
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	public void addTelRist(Context ctx) throws CoreException {
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
		TelRiskList telrisklist = gson.fromJson(gson.toJson(map), TelRiskList.class);
		if(!"".equals(invalidTime1)){
			telrisklist.setInvalidTime(invalidTime);
		}
		telrisklist.setAutoId(SequenceUtil.getUUID());
		String userOperator = ctx.getData("userId");	
		telrisklist.setUserOperator(userOperator);
		//启停状态：（下拉框）,名单库启用或停用，导入新增时不输入默认为启用
		String currStatus = telrisklist.getCurrStatus();
		if (null == currStatus || "".equals(currStatus)) {
			telrisklist.setCurrStatus("1");
		}
		//添加时校验手机号码不得重复添加
		if(telrisklist.getMobileId()!=null&&!"".equals(telrisklist.getMobileId())){
			int n = telRiskListService.queryByMobileId(telrisklist.getMobileId());
			if(n>0){
				ctx.setData("isMobileId", true);
				return;
			}
		}
		//添加时校验固话不得重复添加
		if(telrisklist.getCompanyTel()!=null&&!"".equals(telrisklist.getCompanyTel())){
			int n = telRiskListService.queryByCompanyTel(telrisklist.getCompanyTel());
			if(n>0){
				ctx.setData("isCompanyTel", true);
				return;
			}
		}
		//添加时校验固话不得重复添加
		if(telrisklist.getLivingTel()!=null&&!"".equals(telrisklist.getLivingTel())){
			int n = telRiskListService.queryByLivingTel(telrisklist.getLivingTel());
			if(n>0){
				ctx.setData("isLivingTel", true);
				return;
			}
		}
		//添加时校验固话不得重复添加
		if(telrisklist.getOtherTel()!=null&&!"".equals(telrisklist.getOtherTel())){
			int n = telRiskListService.queryByOtherTel(telrisklist.getOtherTel());
			if(n>0){
				ctx.setData("isOtherTel", true);
				return;
			}
		}
		try {
			telRiskListService.insertSelective(telrisklist);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	public void updateStatusTelRist(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		String obj = (String) map.get("obj");
		List<String> autoid = (List<String>) map.get("ids");
		try {	
		for(int i = 0;i<autoid.size();i++){
			String autoId=autoid.get(i);
			TelRiskList telrisklist = telRiskListService.selectByPrimaryKey(autoId);
			TelRiskListHistory telrisklisthistory = new TelRiskListHistory();
			telrisklisthistory.setId(SequenceUtil.getUUID());
			telrisklisthistory.setAutoId(telrisklist.getAutoId());
			telrisklisthistory.setCompanyTel(telrisklist.getCompanyTel());
			telrisklisthistory.setCreateTime(telrisklist.getCreateTime());
			telrisklisthistory.setCurrStatus(telrisklist.getCurrStatus());
			telrisklisthistory.setFraudType(telrisklist.getFraudType());
			telrisklisthistory.setInvalidTime(telrisklist.getInvalidTime());
			telrisklisthistory.setLivingTel(telrisklist.getLivingTel());
			telrisklisthistory.setMemo(telrisklist.getMemo());
			telrisklisthistory.setMobileId(telrisklist.getMobileId());
			telrisklisthistory.setOperatTime(telrisklist.getOperatTime());
			telrisklisthistory.setOtherTel(telrisklist.getOtherTel());
			telrisklisthistory.setUserOperator(telrisklist.getUserOperator());
			telrisklisthistory.setInfoChannel(telrisklist.getInfoChannel());
			if(obj.equals("start")){
				telRiskListService.updateStartStatus(autoId);
				if(telrisklist.getCurrStatus().equals("0")){
					telRiskListHistoryService.insertSelective(telrisklisthistory);
				}
			}else{
				telRiskListService.updateStopStatus(autoId);
				if(telrisklist.getCurrStatus().equals("1")){
					telRiskListHistoryService.insertSelective(telrisklisthistory);
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
	public void telHistorylist(Context ctx) throws CoreException {
		String autoId = (String) ctx.getDataMap().get("autoId");
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = telRiskListHistoryService.queryTelRistHisCount(autoId);
		List<TelRiskListHistory> list = new ArrayList<TelRiskListHistory>();
		if (count > 0) {
			list = telRiskListHistoryService.queryTelRistHisList(autoId, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
}
