package com.huaxia.opas.dao.system.impl;

import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.system.ApUserRoleDao;
import com.huaxia.opas.domain.system.ApUserRole;

public class ApUserRoleDaoImpl extends AbstractDAO implements ApUserRoleDao {

	private static final long serialVersionUID = 1878216697563527223L;

	private static final String NAMESPACES = "ApUserRole.";

	@Override
	public int insertApUserRole(ApUserRole apUserRole) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApUserRole", apUserRole);
	}

	@Override
	public int insertApUserRoleSelective(ApUserRole apUserRole) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApUserRoleSelective", apUserRole);
	}

	@Override
	public int deleteApUserRoleByRoleIdAndUserId(String roleId, String userId) throws CoreException {
		ApUserRole apUserRole = new ApUserRole();
		apUserRole.setRoleId(roleId);
		apUserRole.setUserId(userId);
		return getSqlMap().delete(NAMESPACES + "deleteApUserRoleByRoleIdAndUserId", apUserRole);
	}

	@Override
	public int updateApUserRole(ApUserRole apUserRole) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApUserRole", apUserRole);
	}

	@Override
	public int updateApUserRoleSelective(ApUserRole apUserRole) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApUserRoleSelective", apUserRole);
	}

	@Override
	public ApUserRole queryApUserRoleByRoleIdAndUserId(String roleId, String userId) throws CoreException {
		ApUserRole apUserRole = new ApUserRole();
		apUserRole.setRoleId(roleId);
		apUserRole.setUserId(userId);
		return (ApUserRole) (getSqlMap().queryForObject(NAMESPACES + "queryApUserRoleByRoleIdAndUserId", apUserRole));
	}

	@Override
	public List<ApUserRole> queryApUserRoleByUserId(String userId) throws CoreException {

		List<ApUserRole> list = new ArrayList<ApUserRole>();

		ApUserRole apUserRole = new ApUserRole();

		apUserRole.setUserId(userId);

		list = getSqlMap().queryForList(NAMESPACES + "queryApUserRoleByUserId", apUserRole);

		return list;
	}

	@Override
	public int deleteApUserRoleByUserId(String userId) throws CoreException {

		ApUserRole apUserRole = new ApUserRole();

		apUserRole.setUserId(userId);

		return getSqlMap().delete(NAMESPACES + "deleteApUserRoleByUserId", apUserRole);
	}

	@Override
	public int deleteApUserRoleByRoleId(String roleId) throws CoreException {

		ApUserRole apUserRole = new ApUserRole();

		apUserRole.setRoleId(roleId);

		return getSqlMap().delete(NAMESPACES + "deleteApUserRoleByRoleId", apUserRole);
	}

}
