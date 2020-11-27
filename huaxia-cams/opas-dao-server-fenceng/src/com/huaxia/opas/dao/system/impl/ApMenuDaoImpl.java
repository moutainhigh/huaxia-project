package com.huaxia.opas.dao.system.impl;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.system.ApMenuDao;
import com.huaxia.opas.domain.system.ApMenu;
import com.huaxia.opas.domain.system.ApUserRole;

public class ApMenuDaoImpl extends AbstractDAO implements ApMenuDao {

	private static final long serialVersionUID = 7214294286880120914L;

	private static final String NAMESPACES = "ApMenu.";

	@Override
	public int insertApMenu(ApMenu apMenu) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApMenu", apMenu);
	}

	@Override
	public int insertApMenuSelective(ApMenu apMenu) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertApMenuSelective", apMenu);
	}

	@Override
	public int deleteApMenuByMenuId(String[] menuId) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteApMenuByMenuId", menuId);
	}

	@Override
	public int updateApMenu(ApMenu apMenu) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApMenu", apMenu);
	}

	@Override
	public int updateApMenuSelective(ApMenu apMenu) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateApMenuSelective", apMenu);
	}

	@Override
	public ApMenu queryApMenuByMenuId(String menuId) throws CoreException {
		return (ApMenu) (getSqlMap().queryForObject(NAMESPACES + "queryApMenuByMenuId", menuId));
	}

	@Override
	public List<ApMenu> queryMenus(ApMenu menu) {
		List<ApMenu> menus = getSqlMap().queryForList(NAMESPACES + "menuList", menu);
		return menus;
	}

	@Override
	public int queryMenuTotal(ApMenu menu) {
		return Integer.parseInt(String.valueOf(sqlMap.queryForObject(NAMESPACES + "menuTotal", menu)));
	}

	@Override
	public List<ApMenu> queryUserMenus(ApUserRole userRole) {
		return sqlMap.queryForList(NAMESPACES + "userMenu", userRole);
	}

	@Override
	public List<ApMenu> queryUserMenuByUserId(ApUserRole userRole) {
		return sqlMap.queryForList(NAMESPACES + "userMenuByUserId", userRole);
	}

	@Override
	public List<ApMenu> queryAllMenus(ApMenu menu) {
		return sqlMap.queryForList(NAMESPACES + "menuAll");
	}

	@Override
	public List<ApMenu> queryMenuByFlag(Map<String, String> paraMap) {
		return sqlMap.queryForList(NAMESPACES + "queryMenuByFlag", paraMap);
	}

	@Override
	public List<ApMenu> queryMenuByUserIdAndNodeNo(Map<String, String> paraMap) {
		return sqlMap.queryForList(NAMESPACES + "queryMenuByUserIdAndNodeNo", paraMap);
	}

	@Override
	public List<ApMenu> queryMenuUrlByNodeNo(Map<String, String> paraMap) {
		return sqlMap.queryForList(NAMESPACES + "queryMenuUrlByNodeNo", paraMap);
	}

}
