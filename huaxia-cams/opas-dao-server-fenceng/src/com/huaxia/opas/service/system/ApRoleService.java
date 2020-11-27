package com.huaxia.opas.service.system;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.system.ApRole;

public interface ApRoleService {

	public int insertApRole(ApRole apRole) throws CoreException;

	public int insertApRoleSelective(ApRole apRole) throws CoreException;

	public int deleteApRoleByRoleId(String roleId) throws CoreException;

	public int updateApRole(ApRole apRole) throws CoreException;

	public int updateApRoleSelective(ApRole apRole) throws CoreException;

	public ApRole queryApRoleByRoleId(String roleId) throws CoreException;

	public List<ApRole> queryRoleList(ApRole apRole, int curNum, int pageNum)
			throws CoreException;

	public List<ApRole> queryRoleListWithoutPages(ApRole apRole)
			throws CoreException;

	public int queryRoleCount(ApRole apRole) throws CoreException;

	public List<ApRole> queryUserRoles(String userId) throws CoreException;

	public int queryApRoleByRoleCode(String roleCode) throws CoreException;

	List<ApRole> queryThreeApRole() throws CoreException;
	
	//用户筛选条件  shihuan 2017-04-14 
	List<ApRole> queryRoleForUser() throws CoreException;
	
	//添加角色校验角色姓名不重复  shihuan 2017-04-15
	public int queryApRoleByRoleName(String roleName) throws CoreException;

}
