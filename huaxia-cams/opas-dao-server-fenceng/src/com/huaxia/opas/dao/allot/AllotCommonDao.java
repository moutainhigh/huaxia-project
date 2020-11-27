package com.huaxia.opas.dao.allot;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.allot.AllotQueue;

public interface AllotCommonDao {
	// 组员编号查询团队编号
	 AllotCommon selectGroupByUserCode(String userCode) throws CoreException;
	
	// 显示所有的组
	List<AllotCommon> selectAllotGroup(Map map) throws CoreException;	
	
	int countAllotGroup(Map map) throws CoreException;
	
	List<AllotCommon> selectAllotGroup(Map map,int currentPage, int pageSize) throws CoreException;
		
	//遍历查看组内组员的角色
	List<String> selectUserRoleByOrgId(String orgId) throws CoreException;

	int countUserRoleByCode(String orgId) throws CoreException;
	
	// 组成员查询
	List<String> selectUserIdByOrgId(String orgId) throws CoreException;
	
	List<AllotCommon> selectAllotUserByList(List<String> list) throws CoreException;
	
	AllotCommon selectGroupByOrgNo(String orgNo) throws CoreException;
	
	// 个人信息
	List<String> selectRoleCodeByUserId(String userId) throws CoreException;
	
	AllotCommon selectUser(Map map) throws CoreException;
	
	//
	int updateAutoStatus(AllotCommon ac) throws CoreException;
	
	//组长排查
	int selectCountShen(Map map) throws CoreException;
	
	//审批角色排查
	List<AllotCommon> selectApprovers(Map map) throws CoreException;
	//特定征信到审批
	List<AllotCommon> selectSpecialMen(Map map) throws CoreException;
	//业务字典查询
	List<AllotQueue> selectCreditDict(String str,int page, int rows) throws CoreException;
	 
	int countCreditDict() throws CoreException;
	List<AllotCommon> selectGroupByMap(Map map) throws CoreException;
}
