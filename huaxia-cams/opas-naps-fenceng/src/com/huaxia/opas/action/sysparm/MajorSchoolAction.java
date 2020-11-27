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
import com.huaxia.opas.domain.sysparm.MajorschoolList;
import com.huaxia.opas.domain.sysparm.MajorschoolListHistory;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.sysparm.MajorschoolListService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.SequenceUtil;


/**
 * 修复页面显示中文名问题
 * 2017-10-10 
 * @author wangtao
 *@version v1.1 初始版本(v1.0)
 */
public class MajorSchoolAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(MajorSchoolAction.class);

	@Resource(name = "majorschoolListService")
	private MajorschoolListService majorschoolListService;
	@Resource(name = "apUserService")
	private ApUserService  apUserService;
	/**
	 * 查询列表信息
	 */
	public void majorSchoolList(Context ctx) throws CoreException {
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
		MajorschoolList majorschoollist = gson.fromJson(gson.toJson(map), MajorschoolList.class);
		if(!"".equals(createTime1)){
			majorschoollist.setCreateTime(createTime);
		}
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = majorschoolListService.queryMajorSchooCount(majorschoollist);
		List<MajorschoolList> list = new ArrayList<MajorschoolList>();
		if (count > 0) {
			list = majorschoolListService.queryMajorSchooList(majorschoollist, curNum, pageNum);
			for (MajorschoolList majorschoolList : list) {
				String operator = majorschoolList.getOperator();
				if(operator!=null&&!"".equals(operator)){
					ApUser apUser = apUserService.queryApUserByUserCode(operator);
					if(apUser!=null){
						majorschoolList.setOperator(apUser.getUserName());
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
	public void majorSchoolRist(Context ctx) throws CoreException {
		Map<String, Object> map = ctx.getDataMap();
		String autoId = (String) map.get("autoId");
		Gson gson = new Gson();
		MajorschoolList majorschoollist = gson.fromJson(gson.toJson(map), MajorschoolList.class);
		String majorschoolName = majorschoollist.getMajorschoolName();
		MajorschoolList majorschoollists = majorschoolListService.selectByPrimaryKey(autoId);
		if(!majorschoollists.getMajorschoolName().equals(majorschoollist.getMajorschoolName())){
			int count = majorschoolListService.queryByMajorschoolName(majorschoolName);
			if(count>0){
				ctx.setData("isFalse", true);
				return;
			}
		}
		//修改时，在历史记录表里增加数据
		MajorschoolList majorschoollist1 = majorschoolListService.selectByPrimaryKey(autoId);
		MajorschoolListHistory majorschoolListHistory = new MajorschoolListHistory();
		majorschoolListHistory.setId(SequenceUtil.getUUID());
		majorschoolListHistory.setAutoId(autoId);
		majorschoolListHistory.setArea(majorschoollist1.getArea());
		majorschoolListHistory.setOperatTime(majorschoollist1.getOperatTime());
		majorschoolListHistory.setCurrStatus(majorschoollist1.getCurrStatus());
		majorschoolListHistory.setHighschoolType(majorschoollist1.getHighschoolType());
		majorschoolListHistory.setOperator(majorschoollist1.getOperator());
		majorschoolListHistory.setMajorschoolName(majorschoollist1.getMajorschoolName());
		majorschoolListHistory.setCreateTime(majorschoollist1.getCreateTime());
		try {
			majorschoolListService.insertHisSelective(majorschoolListHistory);
			majorschoolListService.updateByPrimaryKeySelective(majorschoollist);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);

	}

	/**
	 * 批量删除信息
	 */
	public void delMajorSchoolRist(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		List<String> autoid =  (List<String>) map.get("ids");
		try {	
		for(int i = 0;i<autoid.size();i++){
			String autoId=autoid.get(i);
			//删除时，在历史记录表里增加数据
			majorschoolListService.deleteByPrimaryKey(autoId);
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
	public void addMajorSchoolRist(Context ctx) throws CoreException {
		Map<String,Object> map = ctx.getDataMap();
		Gson gson = new Gson();
		MajorschoolList majorschoollist = gson.fromJson(gson.toJson(map), MajorschoolList.class);
		String majorschoolName = majorschoollist.getMajorschoolName();
		majorschoollist.setAutoId(SequenceUtil.getUUID());
		String userOperator = ctx.getData("userId");	
		majorschoollist.setOperator(userOperator);
		//启停状态：（下拉框）,名单库启用或停用，导入新增时不输入默认为启用
		String currStatus = majorschoollist.getCurrStatus();
		if (null == currStatus || "".equals(currStatus)) {
			majorschoollist.setCurrStatus("1");
		}
		int count = majorschoolListService.queryByMajorschoolName(majorschoolName);
		if(count>0){
			ctx.setData("isFalse", true);
			return;
		}
		try {
			majorschoolListService.insertSelective(majorschoollist);
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
			MajorschoolList majorschoollist1 = majorschoolListService.selectByPrimaryKey(autoId);
			MajorschoolListHistory majorschoolListHistory = new MajorschoolListHistory();
			majorschoolListHistory.setId(SequenceUtil.getUUID());
			majorschoolListHistory.setAutoId(majorschoollist1.getAutoId());
			majorschoolListHistory.setArea(majorschoollist1.getArea());
			majorschoolListHistory.setOperatTime(majorschoollist1.getOperatTime());
			majorschoolListHistory.setCurrStatus(majorschoollist1.getCurrStatus());
			majorschoolListHistory.setHighschoolType(majorschoollist1.getHighschoolType());
			majorschoolListHistory.setOperator(majorschoollist1.getOperator());
			majorschoolListHistory.setMajorschoolName(majorschoollist1.getMajorschoolName());
			if(obj.equals("start")){
				majorschoolListService.updateStartStatus(autoId);
				if(majorschoollist1.getCurrStatus().equals("0")){
					majorschoolListService.insertHisSelective(majorschoolListHistory);
				}
			}else{
				majorschoolListService.updateStopStatus(autoId);
				if(majorschoollist1.getCurrStatus().equals("1")){
					majorschoolListService.insertHisSelective(majorschoolListHistory);
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
	public void schoolHistorylist(Context ctx) throws CoreException {
		String autoId = (String) ctx.getDataMap().get("autoId");
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		// 查询机构总条数
		int count = majorschoolListService.queryMajorSchoolHistoryCount(autoId);
		List<MajorschoolList> list = new ArrayList<MajorschoolList>();
		if (count > 0) {
			list = majorschoolListService.queryMajorSchoolHistory(autoId, curNum, pageNum);
			for (MajorschoolList majorschoolList : list) {
				String operator = majorschoolList.getOperator();
				if(operator!=null&&!"".equals(operator)){
					ApUser apUser = apUserService.queryApUserByUserCode(operator);
					if(apUser!=null){
						majorschoolList.setOperator(apUser.getUserName());
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
