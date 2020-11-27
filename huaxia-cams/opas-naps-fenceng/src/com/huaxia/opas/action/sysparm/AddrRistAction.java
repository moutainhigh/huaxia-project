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
import com.huaxia.opas.domain.riskcheck.AddrRiskList;
import com.huaxia.opas.domain.riskcheck.AddrRiskListHistory;
import com.huaxia.opas.service.compare.TestObjService;
import com.huaxia.opas.service.sysparm.AddrRiskListHistroyService;
import com.huaxia.opas.service.sysparm.AddrRiskListService;
import com.huaxia.opas.util.SequenceUtil;


/**
 * 地址类风险名单
 * 
 * @author shihuan
 *
 */
public class AddrRistAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(AddrRistAction.class);

	@Resource(name = "addrRiskListService")
	private AddrRiskListService addrRiskListService;

	@Resource(name = "addrRiskListHistroyService")
	private AddrRiskListHistroyService addrRiskListHistroyService;
	
	@Resource(name = "testObjService")
	private TestObjService testObjService;

	
	/**
	 * 查询列表信息
	 */
	public void addrlist(Context ctx) throws CoreException {
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
		AddrRiskList addrrisklist = gson.fromJson(gson.toJson(map), AddrRiskList.class);
		if(!"".equals(createTime1)){
			addrrisklist.setCreateTime(createTime);
		}
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = addrRiskListService.queryAddrRistCount(addrrisklist);
		List<AddrRiskList> list = new ArrayList<AddrRiskList>();
		if (count > 0) {
			list = addrRiskListService.queryAddrRistList(addrrisklist, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 修改
	 */
	public void updateAddrRist(Context ctx) throws CoreException {
		Map<String,Object> map = ctx.getDataMap();
		String invalidTime1 = (String)map.get("invalidTime");
		String autoId = (String) map.get("autoId");
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
		AddrRiskList addrrisklist = gson.fromJson(gson.toJson(map), AddrRiskList.class);
		if(!"".equals(invalidTime1)){
			addrrisklist.setInvalidTime(invalidTime);
		}
		addrrisklist.setUserOperator(userOperator);
			//修改时，在历史记录表里增加数据
			AddrRiskList addrrisklist1 = addrRiskListService.selectByPrimaryKey(autoId);
			AddrRiskListHistory addrrisklisthistroy = new AddrRiskListHistory();
			addrrisklisthistroy.setId(SequenceUtil.getUUID());
			addrrisklisthistroy.setAutoId(autoId);
			addrrisklisthistroy.setBillAddr(addrrisklist1.getBillAddr());
			addrrisklisthistroy.setBornAddr(addrrisklist1.getBornAddr());
			addrrisklisthistroy.setCompanyAddr(addrrisklist1.getCompanyAddr());
			addrrisklisthistroy.setCreateTime(addrrisklist1.getCreateTime());
			addrrisklisthistroy.setCurrStatus(addrrisklist1.getCurrStatus());
			addrrisklisthistroy.setFraudType(addrrisklist1.getFraudType());
			addrrisklisthistroy.setInfoChannel(addrrisklist1.getInfoChannel());
			addrrisklisthistroy.setInvalidTime(addrrisklist1.getInvalidTime());
			addrrisklisthistroy.setLivingAddr(addrrisklist1.getLivingAddr());
			addrrisklisthistroy.setMemo(addrrisklist1.getMemo());
			addrrisklisthistroy.setOperatTime(addrrisklist1.getOperatTime());
			addrrisklisthistroy.setOtherAddr(addrrisklist1.getOtherAddr());
			addrrisklisthistroy.setUserOperator(addrrisklist1.getUserOperator());
		try {
			addrRiskListHistroyService.insertSelective(addrrisklisthistroy);
			addrRiskListService.updateByPrimaryKeySelective(addrrisklist);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);

	}

	/**
	 * 批量删除信息
	 */
	public void removeAddrRist(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		List<String> autoid =  (List<String>) map.get("ids");
		try {	
		for(int i = 0;i<autoid.size();i++){
			String autoId=autoid.get(i);
			addrRiskListService.deleteByPrimaryKey(autoId);
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
	public void addAddrRist(Context ctx) throws CoreException {
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
		AddrRiskList addrrisklist = gson.fromJson(gson.toJson(map), AddrRiskList.class);
		if(!"".equals(invalidTime1)){
			addrrisklist.setInvalidTime(invalidTime);
		}
		addrrisklist.setAutoId(SequenceUtil.getUUID());
		String userOperator = ctx.getData("userId");	
		addrrisklist.setUserOperator(userOperator);
		//启停状态：（下拉框）,名单库启用或停用，导入新增时不输入默认为启用
		String currStatus = addrrisklist.getCurrStatus();
		if (null == currStatus || "".equals(currStatus)) {
			addrrisklist.setCurrStatus("1");
		}
		try {
			addrRiskListService.insertSelective(addrrisklist);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	/**
	 * 修改状态
	 */
	public void updateStatusAddrRist(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		String obj = (String) map.get("obj");
		List<String> autoid = (List<String>) map.get("ids");
		try {	
		for(int i = 0;i<autoid.size();i++){
			String autoId=autoid.get(i);
			AddrRiskList addrrisklist = addrRiskListService.selectByPrimaryKey(autoId);
			AddrRiskListHistory addrrisklisthistroy = new AddrRiskListHistory();
			addrrisklisthistroy.setId(SequenceUtil.getUUID());
			addrrisklisthistroy.setAutoId(addrrisklist.getAutoId());
			addrrisklisthistroy.setBillAddr(addrrisklist.getBillAddr());
			addrrisklisthistroy.setBornAddr(addrrisklist.getBornAddr());
			addrrisklisthistroy.setCompanyAddr(addrrisklist.getCompanyAddr());
			addrrisklisthistroy.setCreateTime(addrrisklist.getCreateTime());
			addrrisklisthistroy.setCurrStatus(addrrisklist.getCurrStatus());
			addrrisklisthistroy.setFraudType(addrrisklist.getFraudType());
			addrrisklisthistroy.setInfoChannel(addrrisklist.getInfoChannel());
			addrrisklisthistroy.setInvalidTime(addrrisklist.getInvalidTime());
			addrrisklisthistroy.setLivingAddr(addrrisklist.getLivingAddr());
			addrrisklisthistroy.setMemo(addrrisklist.getMemo());
			addrrisklisthistroy.setOperatTime(addrrisklist.getOperatTime());
			addrrisklisthistroy.setOtherAddr(addrrisklist.getOtherAddr());
			addrrisklisthistroy.setUserOperator(addrrisklist.getUserOperator());
			if(obj.equals("start")){
				addrRiskListService.updateStartStatus(autoId);
				if(addrrisklist.getCurrStatus().equals("0")){
					addrRiskListHistroyService.insertSelective(addrrisklisthistroy);
				}
			}else{
				addrRiskListService.updateStopStatus(autoId);
				if(addrrisklist.getCurrStatus().equals("1")){
					addrRiskListHistroyService.insertSelective(addrrisklisthistroy);
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
	public void addrHistorylist(Context ctx) throws CoreException {
		String autoId = (String) ctx.getDataMap().get("autoId");
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = addrRiskListHistroyService.queryAddrRistHistoryCount(autoId);
		List<AddrRiskListHistory> list = new ArrayList<AddrRiskListHistory>();
		if (count > 0) {
			list = addrRiskListHistroyService.queryAddrRistListHistory(autoId, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
}
