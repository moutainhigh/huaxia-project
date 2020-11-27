package com.huaxia.opas.service.system.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.system.ApMenuDao;
import com.huaxia.opas.domain.system.ApMenu;
import com.huaxia.opas.domain.system.ApUserRole;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.system.ApMenuService;

public class ApMenuServiceImpl extends AbstractService implements ApMenuService, Serializable {

	private static final long serialVersionUID = 5780613385104222248L;

	@Resource(name = "apMenuDao")
	private ApMenuDao apMenuDao;

	@Override
	public int insertApMenu(ApMenu apMenu) throws CoreException {
		return getApMenuDao().insertApMenu(apMenu);
	}

	@Override
	public int insertApMenuSelective(ApMenu apMenu) throws CoreException {
		return getApMenuDao().insertApMenuSelective(apMenu);
	}

	@Override
	public int deleteApMenuByMenuId(String[] menuId) throws CoreException {
		return getApMenuDao().deleteApMenuByMenuId(menuId);
	}

	@Override
	public int updateApMenu(ApMenu apMenu) throws CoreException {
		return getApMenuDao().updateApMenu(apMenu);
	}

	@Override
	public int updateApMenuSelective(ApMenu apMenu) throws CoreException {
		return getApMenuDao().updateApMenuSelective(apMenu);
	}

	@Override
	public ApMenu queryApMenuByMenuId(String menuId) throws CoreException {
		return getApMenuDao().queryApMenuByMenuId(menuId);
	}

	@Override
	public List<ApMenu> queryMenus(ApMenu menu) {
		return getApMenuDao().queryMenus(menu);
	}

	@Override
	public int queryMenuTotal(ApMenu menu) {
		return getApMenuDao().queryMenuTotal(menu);
	}

	@Override
	public List<ApMenu> queryUserMenus(ApUserRole userRole) {
		return getApMenuDao().queryUserMenus(userRole);
	}

	@Override
	public List<ApMenu> queryUserMenuByUserId(ApUserRole userRole) {
		return getApMenuDao().queryUserMenuByUserId(userRole);
	}

	@Override
	public List<ApMenu> queryAllMenus(ApMenu menu) {
		return getApMenuDao().queryAllMenus(menu);
	}

	@Override
	public List<ApMenu> queryMenuByFlag(Map<String, String> paraMap) {
		return getApMenuDao().queryMenuByFlag(paraMap);
	}

	@Override
	public List<ApMenu> queryMenuByUserIdAndNodeNo(Map<String, String> paraMap) {
		return getApMenuDao().queryMenuByUserIdAndNodeNo(paraMap);
	}

	@Override
	public List<ApMenu> queryMenuUrlByNodeNo(Map<String, String> paraMap) {
		return getApMenuDao().queryMenuUrlByNodeNo(paraMap);
	}

	public ApMenuDao getApMenuDao() {
		return apMenuDao;
	}

	public void setApMenuDao(ApMenuDao apMenuDao) {
		this.apMenuDao = apMenuDao;
	}

}
