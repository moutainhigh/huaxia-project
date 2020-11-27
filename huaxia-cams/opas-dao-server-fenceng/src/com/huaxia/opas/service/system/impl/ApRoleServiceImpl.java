package com.huaxia.opas.service.system.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.system.ApRoleDao;
import com.huaxia.opas.domain.system.ApRole;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.system.ApRoleService;

public class ApRoleServiceImpl extends AbstractService implements ApRoleService , Serializable{

	private static final long serialVersionUID = 6856077024094060633L;

	@Resource(name = "apRoleDao")
	private ApRoleDao apRoleDao;
	
	/**
	 * 查询L1,L2,L3三个角色
	 */
	@Override
	public List<ApRole> queryThreeApRole() throws CoreException {
		return getApRoleDao().queryThreeApRole();
	}

	@Override
	public int insertApRole(ApRole apRole) throws CoreException {
		return getApRoleDao().insertApRole(apRole);
	}

	@Override
	public int insertApRoleSelective(ApRole apRole) throws CoreException {
		return getApRoleDao().insertApRoleSelective(apRole);
	}

	@Override
	public int deleteApRoleByRoleId(String roleId) throws CoreException {
		return getApRoleDao().deleteApRoleByRoleId(roleId);
	}

	@Override
	public int updateApRole(ApRole apRole) throws CoreException {
		return getApRoleDao().updateApRole(apRole);
	}

	@Override
	public int updateApRoleSelective(ApRole apRole) throws CoreException {
		return getApRoleDao().updateApRoleSelective(apRole);
	}

	@Override
	public ApRole queryApRoleByRoleId(String roleId) throws CoreException {
		return getApRoleDao().queryApRoleByRoleId(roleId);
	}

	@Override
	public List<ApRole> queryRoleList(ApRole apRole, int curNum, int pageNum)
			throws CoreException {
		return getApRoleDao().queryRoleList(apRole, curNum, pageNum);
	}

	@Override
	public List<ApRole> queryRoleListWithoutPages(ApRole apRole)
			throws CoreException {
		return getApRoleDao().queryRoleListWithoutPages(apRole);
	}

	@Override
	public int queryRoleCount(ApRole apRole) throws CoreException {
		return getApRoleDao().queryRoleCount(apRole);
	}

	@Override
	public List<ApRole> queryUserRoles(String userId) throws CoreException {
		return getApRoleDao().queryUserRoles(userId);
	}

	@Override
	public int queryApRoleByRoleCode(String roleCode) throws CoreException {
		return getApRoleDao().queryApRoleByRoleCode(roleCode);
	}
	
	//用户筛选条件  shihuan 2017-04-14 
	@Override
	public List<ApRole> queryRoleForUser() throws CoreException {
		return getApRoleDao().queryRoleForUser();
	}
	
	//添加角色校验角色姓名不重复  shihuan 2017-04-15
	@Override
	public int queryApRoleByRoleName(String roleName) throws CoreException {
		return getApRoleDao().queryApRoleByRoleName(roleName);
	}
	
	public ApRoleDao getApRoleDao() {
		return apRoleDao;
	}

	public void setApRoleDao(ApRoleDao apRoleDao) {
		this.apRoleDao = apRoleDao;
	}

}
