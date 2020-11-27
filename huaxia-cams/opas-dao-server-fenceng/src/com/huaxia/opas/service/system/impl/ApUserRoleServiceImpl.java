package com.huaxia.opas.service.system.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.system.ApUserRoleDao;
import com.huaxia.opas.domain.system.ApUserRole;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.system.ApUserRoleService;

public class ApUserRoleServiceImpl extends AbstractService implements
		ApUserRoleService, Serializable {

	private static final long serialVersionUID = -5718022446909417608L;

	@Resource(name = "apUserRoleDao")
	private ApUserRoleDao apUserRoleDao;

	@Override
	public int insertApUserRole(ApUserRole apUserRole) throws CoreException {
		return getApUserRoleDao().insertApUserRole(apUserRole);
	}

	@Override
	public int insertApUserRoleSelective(ApUserRole apUserRole)
			throws CoreException {
		return getApUserRoleDao().insertApUserRoleSelective(apUserRole);
	}

	@Override
	public int deleteApUserRoleByRoleIdAndUserId(String roleId, String userId)
			throws CoreException {
		return getApUserRoleDao().deleteApUserRoleByRoleIdAndUserId(roleId,
				userId);
	}

	@Override
	public int updateApUserRole(ApUserRole apUserRole) throws CoreException {
		return getApUserRoleDao().updateApUserRole(apUserRole);
	}

	@Override
	public int updateApUserRoleSelective(ApUserRole apUserRole)
			throws CoreException {
		return getApUserRoleDao().updateApUserRoleSelective(apUserRole);
	}

	@Override
	public ApUserRole queryApUserRoleByRoleIdAndUserId(String roleId,
			String userId) throws CoreException {
		return getApUserRoleDao().queryApUserRoleByRoleIdAndUserId(roleId,
				userId);
	}

	@Override
	public List<ApUserRole> queryApUserRoleByUserId(String userId)
			throws CoreException {
		return getApUserRoleDao().queryApUserRoleByUserId(userId);
	}

	@Override
	public int deleteApUserRoleByUserId(String userId) throws CoreException {
		return getApUserRoleDao().deleteApUserRoleByUserId(userId);
	}

	@Override
	public int deleteApUserRoleByRoleId(String roleId) throws CoreException {
		return getApUserRoleDao().deleteApUserRoleByRoleId(roleId);
	}

	public ApUserRoleDao getApUserRoleDao() {
		return apUserRoleDao;
	}

	public void setApUserRoleDao(ApUserRoleDao apUserRoleDao) {
		this.apUserRoleDao = apUserRoleDao;
	}

}
