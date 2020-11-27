package com.huaxia.opas.service.system.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.system.ApRoleMenuDao;
import com.huaxia.opas.domain.system.ApRoleMenu;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.system.ApRoleMenuService;

public class ApRoleMenuServiceImpl extends AbstractService implements
		ApRoleMenuService, Serializable {

	private static final long serialVersionUID = -6410299201046331327L;

	@Resource(name = "apRoleMenuDao")
	private ApRoleMenuDao apRoleMenuDao;

	@Override
	public int insertApRoleMenu(ApRoleMenu apRoleMenu) throws CoreException {
		return getApRoleMenuDao().insertApRoleMenu(apRoleMenu);
	}

	@Override
	public int insertApRoleMenuSelective(ApRoleMenu apRoleMenu)
			throws CoreException {
		return getApRoleMenuDao().insertApRoleMenuSelective(apRoleMenu);
	}

	@Override
	public int deleteApRoleMenuByMenuIdAndRoleId(String menuId, String roleId)
			throws CoreException {
		return getApRoleMenuDao().deleteApRoleMenuByMenuIdAndRoleId(menuId,
				roleId);
	}

	@Override
	public int updateApRoleMenu(ApRoleMenu apRoleMenu) throws CoreException {
		return getApRoleMenuDao().updateApRoleMenu(apRoleMenu);
	}

	@Override
	public int updateApRoleMenuSelective(ApRoleMenu apRoleMenu)
			throws CoreException {
		return getApRoleMenuDao().updateApRoleMenuSelective(apRoleMenu);
	}

	@Override
	public ApRoleMenu queryApRoleMenuByMenuIdAndRoleId(String menuId,
			String roleId) throws CoreException {
		return getApRoleMenuDao().queryApRoleMenuByMenuIdAndRoleId(menuId,
				roleId);
	}

	@Override
	public int deleteApRoleMenuByRoleId(String roleId) {
		return getApRoleMenuDao().deleteApRoleMenuByRoleId(roleId);
	}

	@Override
	public int insertApRoleMenuBatch(List<ApRoleMenu> roleMenus) {
		return getApRoleMenuDao().insertApRoleMenuBatch(roleMenus);
	}

	@Override
	public int deleteApRoleMenuByMenuId(String menuId) {
		return getApRoleMenuDao().deleteApRoleMenuByMenuId(menuId);
	}

	public ApRoleMenuDao getApRoleMenuDao() {
		return apRoleMenuDao;
	}

	public void setApRoleMenuDao(ApRoleMenuDao apRoleMenuDao) {
		this.apRoleMenuDao = apRoleMenuDao;
	}

}
