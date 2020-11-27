package com.huaxia.opas.service.allot.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotApplyDao;
import com.huaxia.opas.dao.allot.AllotCommonDao;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.allot.AllotQueue;
import com.huaxia.opas.service.allot.AllotCommonService;

/**
 * 分件组 及人查询 
 *
 * @author wangdebin
 * @version v1.1(初始v1.0)
 * @history 修改历史记录
 * ------------------------------------------------
 *  2017-10-19 添加特定征信人员 及特定审批人员展示功能
 * ------------------------------------------------
 */
public class AllotCommonServiceImpl extends AbstractDAO implements AllotCommonService, Serializable {

	private static final long serialVersionUID = 5713985425365466454L;
	
	private static final Logger logger = LoggerFactory.getLogger(AllotCommonServiceImpl.class);
	
	@Resource(name = "allotCommonDao")
	private AllotCommonDao allotCommonDao;
	
	public AllotCommonDao getAllotCommonDao() {
		return allotCommonDao;
	}
	public void setAllotCommonDao(AllotCommonDao allotCommonDao) {
		this.allotCommonDao = allotCommonDao;
	}
	
	@Resource(name = "allotApplyDao")
	private AllotApplyDao allotApplyDao;
	
	public AllotApplyDao getAllotApplyDao() {
		return allotApplyDao;
	}
	public void setAllotApplyDao(AllotApplyDao allotApplyDao) {
		this.allotApplyDao = allotApplyDao;
	}
	
	@Override
	public List<AllotCommon> queryAllotGroup(Map map) throws CoreException {
		List<String> list=new ArrayList<String>();
		String userCode=(String) map.get("userCode");
		String currNode=(String) map.get("currNode");
		//标准卡组  4  易达金组  5  欺诈组  6
		String orgLevel=(String) map.get("orgLevel");
		Map<String,Object> dataMap=new HashMap<String,Object>();
		dataMap.put("orgLevel", orgLevel);
		//查询所有的组
		List<AllotCommon> groupList=new ArrayList<AllotCommon>();
		//排查符合条件的组
		 List<AllotCommon> gpList1= new ArrayList<AllotCommon>();
		 if(currNode!=null&&!"".equals(currNode)){
			 //全部组
			 groupList=getAllotCommonDao().selectAllotGroup(dataMap);
			List<String> roleCodes=new ArrayList<String>();
			 if("01".equals(currNode)){
				 roleCodes.add("DE");
			 }else if("02".equals(currNode)){
				 roleCodes.add("CI");
				 roleCodes.add("HY");
			 }else if("03".equals(currNode)){
				 roleCodes.add("L1");
				 roleCodes.add("L2");
				 roleCodes.add("L3");
				 roleCodes.add("HY");
			 }else if("05".equals(currNode)){
				 roleCodes.add("AP");
			 }
			 for(int i=0;i<groupList.size();i++){
				 AllotCommon allotCommon=groupList.get(i);
				 //组id
				 String orgId=allotCommon.getOrgId();
				 //该组拥有的全部角色
				 list=getAllotCommonDao().selectUserRoleByOrgId(orgId);
				 for(int j=0;j<roleCodes.size();j++){
					 if(list.contains(roleCodes.get(j))){
						 String orgNo=allotCommon.getOrgNo();
						 Map<String,Object> hashMap=new HashMap<String,Object>();
						 hashMap.put("currGroup", orgNo);
						 hashMap.put("currNode", currNode);
						 String ydjFlag="";
						 if("4".equals(orgLevel)){
							 ydjFlag="2";
						 }else if("5".equals(orgLevel)){
							 ydjFlag="1";
						 }else if("6".equals(orgLevel)){
							 ydjFlag="";
						 }
						 hashMap.put("ydjFlag", ydjFlag);
						//根据易达金标识 依主卡为主
						String ydjStr="";
						if("1".equals(ydjFlag)||"2".equals(ydjFlag)||"".equals(ydjFlag)){
							ydjStr="AND (allot.MATCHING_CARD_FLAG in ('0','2') or allot.LAOJIANFLAG='01' or allot.LAOJIANFLAG='02' or allot.DEL_STATUS='3' )";
						}
						 hashMap.put("ydjStr", ydjStr);
						 hashMap.put("delStatus", "0");
						//组中未分配
						 List<String> currStatusList=new ArrayList<String>();
						 currStatusList.add("1");
						 hashMap.put("currStatusList", currStatusList);
						String submitStr="";
						if(!"05".equals(currNode)){//提报状态
							submitStr="submit";
						}
						 hashMap.put("submitStr",submitStr);
						//未叫停申请件  质检叫停 2 不能分件 
						 String stopStr="AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
						 hashMap.put("stopStr", stopStr);
						 int num=allotApplyDao.selectCount(hashMap);
						 allotCommon.setOrgNum(num);
						 gpList1.add(allotCommon);
						 break;
					 }else{
						 continue;
					 }
				 }
			 }
		 }else{
			int curPage = Integer.parseInt(
						String.valueOf(map.get("curNum") == null ? 0 : map.get("curNum")));
			int pageNum = Integer.parseInt(
						String.valueOf(map.get("pageNum") == null ? 0 : map.get("pageNum")));
			 groupList=getAllotCommonDao().selectAllotGroup(map,curPage,pageNum);
			 gpList1.addAll(groupList);
		 }
		return gpList1;
	}
	
	@Override
	public int countAllotGroup(Map map) throws CoreException {
		return getAllotCommonDao().countAllotGroup(map);
	}
	
	@Override
	public AllotCommon queryGroupByOrgNo(String orgNo) throws CoreException {
		return getAllotCommonDao().selectGroupByOrgNo(orgNo);
	}
	

	@Override
	public List<String> queryUserRoleByCode(String orgId) throws CoreException {
		return getAllotCommonDao().selectUserRoleByOrgId(orgId);
	}
	
	@Override
	public int countUserRoleByCode(String orgId) throws CoreException {
		return getAllotCommonDao().countUserRoleByCode(orgId);
	}
	
	//组成员  查询 
	@Override
	public AllotCommon queryUser(Map map) throws CoreException {
		return getAllotCommonDao().selectUser(map);
	}
	
	//当前组及当前节点符合要求的
	@Override
	public AllotCommon queryGroupByUserCode(String userCode) throws CoreException {
		return getAllotCommonDao().selectGroupByUserCode(userCode);
	}
	
	@Override
	public List<AllotCommon> queryAllotUserByOrgId(Map dataMap, AllotCommon allotCommon) throws CoreException {
		String currNode = (String) dataMap.get("currNode");
		String ydjFlag = (String) dataMap.get("ydjFlag");
		String autoAllot = (String) dataMap.get("autoAllot");
		String auto = (String) dataMap.get("auto");
		String isHangUp = (String) dataMap.get("isHangUp");// added by
															// jipengchun
		String secondNode = (String) dataMap.get("secondNode");
		String orgNo = allotCommon.getOrgNo();
		String orgLevel = allotCommon.getOrgLevel();
		logger.info("queryAllotUserByOrgId --该组名称--- orgNo" + orgNo);
		String orgId = allotCommon.getOrgId();
		List<String> roleCodes = new ArrayList<String>();
		String roleCode = "";
		if ("01".equals(currNode)) {
			roleCodes.add("DE");
		} else if ("02".equals(currNode)) {
			roleCodes.add("CI");
			roleCodes.add("HY");
		} else if ("03".equals(currNode)) {
			roleCodes.add("L1");
			roleCodes.add("L2");
			roleCodes.add("L3");
			roleCodes.add("HY");
		} else if ("05".equals(currNode)) {
			roleCodes.add("AP");
		} else if ("".equals(currNode) && "1".equals(auto)) {
			roleCodes.add("DE");
			roleCodes.add("CI");
			roleCodes.add("HY");
			roleCodes.add("L1");
			roleCodes.add("L2");
			roleCodes.add("L3");
		}
		String leaderRoleCode = "DO40";// 组长编码DO40 added by jipengchun
		String l1RoleCode = "L1";
		String l2RoleCode = "L2";
		String l3RoleCode = "L3";
		List<AllotCommon> leaderList = new ArrayList<AllotCommon>();// added by jipengchun
		List<AllotCommon> l1List = new ArrayList<AllotCommon>();
		List<AllotCommon> l2List = new ArrayList<AllotCommon>();
		List<AllotCommon> l3List = new ArrayList<AllotCommon>();
		boolean isLnFlag = false;
		// 易达金 审批 组 需要根据L2 L1排序
		if ("1".equals(ydjFlag) && "03".equals(currNode) && "3".equals(secondNode)) {
			isLnFlag = true;
		}
		// 查询组内所有组员userId
		List<String> list = getAllotCommonDao().selectUserIdByOrgId(orgId);
		// 符合条件的组员信息
		List<AllotCommon> list3 = new ArrayList<AllotCommon>();
		AllotCommon allotCommon2 = new AllotCommon();
		Map<String, Object> userMap = new HashMap<String, Object>();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String userID = list.get(i);
				List<String> roleCodeList = getAllotCommonDao().selectRoleCodeByUserId(userID);
				if (roleCodeList.size() > 0) {
					for (int j = 0; j < roleCodes.size(); j++) {
						roleCode = roleCodes.get(j);
						if (roleCodeList.contains(roleCode)) {// 包含满足该环节的角色
																// 展示在前台
							userMap.put("userId", userID);
							if ("1".equals(autoAllot)) {// 自动分件参与标识
								userMap.put("autoStatus", "1");
							} else {
								userMap.put("autoStatus", "");
							}
							allotCommon2 = getAllotCommonDao().selectUser(userMap);
							String currUser = "";
							String currGroup = "";
							if (allotCommon2 != null) {
								currUser = allotCommon2.getUserCode();
								currGroup = allotCommon2.getOrgNo();
								Map<String, Object> map = new HashMap<String, Object>();
								// 根据易达金标识 依主卡为主
								String ydjStr = "";
								if ("1".equals(ydjFlag) || "2".equals(ydjFlag) || "".equals(ydjFlag)) {
									ydjStr = "AND (allot.MATCHING_CARD_FLAG in ('0','2') or allot.LAOJIANFLAG='01' or allot.LAOJIANFLAG='02' or allot.DEL_STATUS='3')";
								}
								map.put("ydjStr", ydjStr);
								map.put("currGroup", currGroup);
								map.put("currUser", currUser);
								map.put("currNode", currNode);
								map.put("ydjFlag", ydjFlag);
								map.put("delStatus", "0");
								String submitStr = "";
								if (!"05".equals(currNode)) {// 提报状态
									submitStr = "submit";
								}
								// 个人中已分配
								List<String> currStatusList = new ArrayList<String>();
								currStatusList.add("3");
								// 如果是转移,需要加已挂起 added by jipengchun
								if (StringUtils.isNotEmpty(isHangUp)) {
									currStatusList.add("4");
								}
								// added by jipengchun end
								map.put("currStatusList", currStatusList);
								map.put("submitStr", submitStr);
								// 未叫停申请件 质检叫停 2 不能分件
								String stopStr = "AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
								map.put("stopStr", stopStr);
								// 欺诈环节 区分欺诈调查和欺诈审批
								if ("".equals(ydjFlag)) {
									map.put("fraudStr", "and allot.BATCH_APPROVAL_FLAG in ('0','1') ");
								}
								int num = allotApplyDao.selectCount(map);
								allotCommon2.setUserNum(num);
								if (roleCodeList.contains(leaderRoleCode)) {
									leaderList.add(allotCommon2);
								} else if (isLnFlag && roleCodeList.contains(l3RoleCode)) {
									l3List.add(allotCommon2);
								} else if (isLnFlag && roleCodeList.contains(l2RoleCode)) {
									l2List.add(allotCommon2);
								} else if (isLnFlag && roleCodeList.contains(l1RoleCode)) {
									l1List.add(allotCommon2);
								} else {
									list3.add(allotCommon2);
								}
								break;
							}
						} else {
							logger.info("queryAllotUserByOrgId --继续排查组员其他角色码");
							continue;
						}
					}
				}
			}
		}
		if(isLnFlag){
			leaderList.addAll(l3List);
			leaderList.addAll(l2List);
			leaderList.addAll(l1List);
		}
		leaderList.addAll(list3);
		return leaderList;
	}
	
	@Override
	public int updateAutoStatus(AllotCommon ac) throws CoreException {
		return getAllotCommonDao().updateAutoStatus(ac);
	}
	
	@Override
	public List<AllotCommon> queryApprovers(Map dataMap) throws CoreException {
		String ydjFlag=(String) dataMap.get("ydjFlag");
		List<String> roleCodes=new ArrayList<String>();
		 roleCodes.add("L1");
		 roleCodes.add("L2");
		 roleCodes.add("L3");
		 roleCodes.add("HY");
		 dataMap.put("roleCodes", roleCodes);
		 //审批权限所有人
		 List<AllotCommon> list=allotCommonDao.selectApprovers(dataMap);
		 String currUser="";
		 for(int i=0;i<list.size();i++){
			 AllotCommon ac=list.get(i);
			 currUser=ac.getUserCode()==null?"":ac.getUserCode();
			 if(!"".equals(currUser)){
				//根据易达金标识 依主卡为主
					String ydjStr="AND (allot.MATCHING_CARD_FLAG in ('0','2') or allot.LAOJIANFLAG='01' or allot.LAOJIANFLAG='02' or allot.DEL_STATUS='3')";
					dataMap.put("ydjStr", ydjStr);
					dataMap.put("currUser", currUser);
					dataMap.put("currNode", "03");
					dataMap.put("ydjFlag", ydjFlag); 
					 //个人中已分配
					 List<String> currStatusList=new ArrayList<String>();
					 currStatusList.add("3");
					 dataMap.put("currStatusList", currStatusList);
					 dataMap.put("submitStr","submit");
					 //组中未叫停申请件  质检叫停 2 不能分件 
			    	 String stopStr="AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
			    	 dataMap.put("stopStr", stopStr);
			    	 int num=allotApplyDao.selectCount(dataMap);
					 ac.setUserNum(num);
			 }else{
				 ac.setUserNum(0);
			 }
		 }
		return list;
	}
	
	//特定征信人员	
	@Override
	public List<AllotCommon> querySpecialMen(Map map) throws CoreException {
		String secondNode=StringUtils.trimToEmpty((String) map.get("secondNode"));
		List<String> currStatusList=new ArrayList<String>();
		//分配
		if("1".equals(secondNode)){
			currStatusList.add("0");
		}else if("3".equals(secondNode)){
			currStatusList.add("1");
		}
		String ydjStr="AND (allot.MATCHING_CARD_FLAG in ('0','2') or allot.LAOJIANFLAG='01' or allot.LAOJIANFLAG='02' or allot.DEL_STATUS='3')";
		map.put("ydjStr", ydjStr);
		map.put("currStatusList", currStatusList);
		map.put("submitStr","submit");
		//组中未叫停申请件  质检叫停 2 不能分件 
    	String stopStr="AND (info.STOP_FLAG is null or info.STOP_FLAG in('0','1')) ";
    	map.put("stopStr", stopStr);
		return allotCommonDao.selectSpecialMen(map);
	}
	@Override
	public int countCreditDict() throws CoreException {
		return allotCommonDao.countCreditDict();
	}
	@Override
	public List<AllotQueue> selectCreditDict(String str, int page, int rows) throws CoreException {
		return allotCommonDao.selectCreditDict(str, page, rows);
	}
	@Override
	public List<AllotCommon> queryAllotGroupByMap(Map<String, Object> map) throws CoreException {
		String currNode=(String) map.get("currNode");
		List<AllotCommon> groupList=new ArrayList<AllotCommon>();
		if(currNode!=null&&!"".equals(currNode)){
			//标准卡组  4  易达金组  5  欺诈组  6
			String orgLevel=(String) map.get("orgLevel");
			List<String> roleCodes=new ArrayList<String>();
			 if("01".equals(currNode)){
				 roleCodes.add("DE");
			 }else if("02".equals(currNode)){
				 roleCodes.add("CI");
				 roleCodes.add("HY");
			 }else if("03".equals(currNode)){
				 roleCodes.add("L1");
				 roleCodes.add("L2");
				 roleCodes.add("L3");
				 roleCodes.add("HY");
			 }else if("05".equals(currNode)){
				 roleCodes.add("AP");
			 }
			 String ydjFlag="",submitStr="";
			 if("4".equals(orgLevel)){
				 ydjFlag="2";
			 }else if("5".equals(orgLevel)){
				 ydjFlag="1";
			 }else if("6".equals(orgLevel)){
				 ydjFlag="";
			 }
			 if(!"05".equals(currNode)){//提报状态
				submitStr="submit";
			}
			 map.put("ydjFlag", ydjFlag);
			 map.put("roleCodes", roleCodes);
			 map.put("submitStr",submitStr);
			 groupList=allotCommonDao.selectGroupByMap(map);
		 }else{
			int curPage = Integer.parseInt(
						String.valueOf(map.get("curNum") == null ? 0 : map.get("curNum")));
			int pageNum = Integer.parseInt(
						String.valueOf(map.get("pageNum") == null ? 0 : map.get("pageNum")));
			 groupList=getAllotCommonDao().selectAllotGroup(map,curPage,pageNum);
		 }
		return groupList;
	}
	
}