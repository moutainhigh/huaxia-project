package com.huaxia.opas.dao.system;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.system.ApUserRole;

public interface ApUserRoleDao {

	public int insertApUserRole(ApUserRole apUserRole) throws CoreException;

	public int insertApUserRoleSelective(ApUserRole apUserRole) throws CoreException;

	public int deleteApUserRoleByRoleIdAndUserId(String roleId, String userId) throws CoreException;

	public int updateApUserRole(ApUserRole apUserRole) throws CoreException;

	public int updateApUserRoleSelective(ApUserRole apUserRole) throws CoreException;

	public ApUserRole queryApUserRoleByRoleIdAndUserId(String roleId, String userId) throws CoreException;

	public List<ApUserRole> queryApUserRoleByUserId(String userId) throws CoreException;

	public int deleteApUserRoleByUserId(String userId) throws CoreException;

	public int deleteApUserRoleByRoleId(String roleId) throws CoreException;
}
