package com.huaxia.opas.dao.system.impl;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.system.ApRoleMenuDao;
import com.huaxia.opas.domain.system.ApRoleMenu;

public class ApRoleMenuDaoImpl extends AbstractDAO implements ApRoleMenuDao {

	private static final long serialVersionUID = -4491455802504827274L;

	private static final String NAMESPACES = "ApRoleMenu.";

	@Override
	public int insertApRoleMenu(ApRoleMenu apRoleMenu) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApRoleMenu", apRoleMenu);
	}

	@Override
	public int insertApRoleMenuSelective(ApRoleMenu apRoleMenu) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApRoleMenuSelective", apRoleMenu);
	}

	@Override
	public int deleteApRoleMenuByMenuIdAndRoleId(String menuId, String roleId) throws CoreException {
		ApRoleMenu apRoleMenu = new ApRoleMenu();
		apRoleMenu.setMenuId(menuId);
		apRoleMenu.setRoleId(roleId);
		return getSqlMap().delete(NAMESPACES + "deleteApRoleMenuByMenuIdAndRoleId", apRoleMenu);
	}

	@Override
	public int updateApRoleMenu(ApRoleMenu apRoleMenu) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApRoleMenu", apRoleMenu);
	}

	@Override
	public int updateApRoleMenuSelective(ApRoleMenu apRoleMenu) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApRoleMenuSelective", apRoleMenu);
	}

	@Override
	public ApRoleMenu queryApRoleMenuByMenuIdAndRoleId(String menuId, String roleId) throws CoreException {
		ApRoleMenu apRoleMenu = new ApRoleMenu();
		apRoleMenu.setMenuId(menuId);
		apRoleMenu.setRoleId(roleId);
		return (ApRoleMenu) (getSqlMap().queryForObject(NAMESPACES + "queryApRoleMenuByMenuIdAndRoleId", apRoleMenu));
	}

	@Override
	public int deleteApRoleMenuByRoleId(String roleId) {
		return getSqlMap().delete(NAMESPACES + "deleteApRoleMenuByRoleId", roleId);
	}

	@Override
	public int insertApRoleMenuBatch(List<ApRoleMenu> roleMenus) {
		return getSqlMap().insert(NAMESPACES + "insertRoleMenuBatch", roleMenus);
	}

	@Override
	public int deleteApRoleMenuByMenuId(String menuId) {
		return getSqlMap().delete(NAMESPACES + "deleteApRoleMenuByMenuId", menuId);
	}

}
