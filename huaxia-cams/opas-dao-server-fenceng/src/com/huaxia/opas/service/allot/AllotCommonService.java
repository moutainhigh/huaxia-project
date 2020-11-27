package com.huaxia.opas.service.allot;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.allot.AllotQueue;

public interface AllotCommonService {
	
	// 显示当前环节所有的组
	
	public List<AllotCommon> queryAllotGroup(Map<String,Object> map) throws CoreException;	
	
	int countAllotGroup(Map map) throws CoreException;
	
	//遍历查看组内组员的角色
	public List<String> queryUserRoleByCode(String orgId) throws CoreException;

	public int countUserRoleByCode(String orgId) throws CoreException;
	
	// 组成员查询
	public AllotCommon queryGroupByUserCode(String userCode) throws CoreException;
	
	public List<AllotCommon> queryAllotUserByOrgId(Map map,AllotCommon allotCommon) throws CoreException;
	
	//查询orgNo
	public AllotCommon queryGroupByOrgNo(String orgNo) throws CoreException;
	
	// 个人信息
	public AllotCommon queryUser(Map map) throws CoreException;
	//批量修改
	int updateAutoStatus(AllotCommon ac) throws CoreException;
	
	//查询审批角色的所有人
	List<AllotCommon> queryApprovers(Map map) throws CoreException;
	
	 //特定征信到审批
	 List<AllotCommon> querySpecialMen(Map map) throws CoreException;
	 //业务字典查询
	 int countCreditDict() throws CoreException;
	 List<AllotQueue> selectCreditDict(String str, int page, int rows) throws CoreException;
	 //二期优化快速显示组
	 public List<AllotCommon> queryAllotGroupByMap(Map<String,Object> map) throws CoreException;	

}
