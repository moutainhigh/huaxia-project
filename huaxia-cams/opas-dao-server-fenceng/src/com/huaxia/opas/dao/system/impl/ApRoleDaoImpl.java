package com.huaxia.opas.dao.system.impl;

import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.system.ApRoleDao;
import com.huaxia.opas.domain.system.ApRole;

public class ApRoleDaoImpl extends AbstractDAO implements ApRoleDao {

	private static final long serialVersionUID = -2786327678357864632L;

	private static final String NAMESPACES = "ApRole.";

	@Override
	public int insertApRole(ApRole apRole) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApRole", apRole);
	}

	@Override
	public int insertApRoleSelective(ApRole apRole) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApRoleSelective", apRole);
	}

	@Override
	public int deleteApRoleByRoleId(String roleId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteApRoleByRoleId", roleId);
	}

	@Override
	public int updateApRole(ApRole apRole) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApRole", apRole);
	}

	@Override
	public int updateApRoleSelective(ApRole apRole) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApRoleSelective", apRole);
	}

	@Override
	public ApRole queryApRoleByRoleId(String roleId) throws CoreException {
		return (ApRole) (getSqlMap().queryForObject(NAMESPACES + "queryApRoleByRoleId", roleId));
	}

	@Override
	public List<ApRole> queryRoleList(ApRole apRole, int curNum, int pageNum) throws CoreException {
		List<ApRole> list = new ArrayList<ApRole>();
		list = getSqlMap().queryForList(NAMESPACES + "queryRoleList", apRole, curNum, pageNum);
		return list;
	}
	
	//用户筛选条件  shihuan 2017-04-14 
	@Override
	public List<ApRole> queryRoleForUser() throws CoreException {
		List<ApRole> list = new ArrayList<ApRole>();
		list = getSqlMap().queryForList(NAMESPACES + "queryRoleForUser");
		return list;
	}
	
	@Override
	public int queryRoleCount(ApRole apRole) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryRoleCountByUser", apRole);
	}

	@Override
	public List<ApRole> queryRoleListWithoutPages(ApRole apRole) throws CoreException {
		List<ApRole> list = new ArrayList<ApRole>();
		list = getSqlMap().queryForList(NAMESPACES + "queryRoleListByUser", apRole);
		return list;
	}
	
	@Override
	public List<ApRole> queryUserRoles(String userId) throws CoreException {
		return getSqlMap().queryForList(NAMESPACES + "queryUserRole", userId);
	}

	@Override
	public int queryApRoleByRoleCode(String roleCode) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryApRoleByRoleCode", roleCode);
	}
	@Override
	public List<ApRole> queryThreeApRole() throws CoreException {
		List<ApRole> list = new ArrayList<ApRole>();
		list = getSqlMap().queryForList(NAMESPACES + "queryThreeApRole");
		return list;
	}
	@Override
	public ApRole queryRoleCodeByUserId(String userId) throws CoreException {
		return (ApRole) (getSqlMap().queryForObject(NAMESPACES + "queryRoleCodeByUserId", userId));
	}
	
	//添加角色校验角色姓名不重复  shihuan 2017-04-15
	@Override
	public int queryApRoleByRoleName(String roleName) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryApRoleByRoleName", roleName);
	}
}
