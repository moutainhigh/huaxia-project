package com.huaxia.opas.dao.system;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.system.ApRoleMenu;

public interface ApRoleMenuDao {

	public int insertApRoleMenu(ApRoleMenu apRoleMenu) throws CoreException;

	public int insertApRoleMenuSelective(ApRoleMenu apRoleMenu) throws CoreException;

	public int deleteApRoleMenuByMenuIdAndRoleId(String menuId, String roleId) throws CoreException;

	public int updateApRoleMenu(ApRoleMenu apRoleMenu) throws CoreException;

	public int updateApRoleMenuSelective(ApRoleMenu apRoleMenu) throws CoreException;

	public ApRoleMenu queryApRoleMenuByMenuIdAndRoleId(String menuId, String roleId) throws CoreException;

	public int deleteApRoleMenuByRoleId(String roleId);

	public int insertApRoleMenuBatch(List<ApRoleMenu> roleMenus);

	public int deleteApRoleMenuByMenuId(String menuId);
}
