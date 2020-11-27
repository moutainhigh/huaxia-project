package com.huaxia.opas.dao.system;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.system.ApMenu;
import com.huaxia.opas.domain.system.ApUserRole;

public interface ApMenuDao {

	public int insertApMenu(ApMenu apMenu) throws CoreException;

	public int insertApMenuSelective(ApMenu apMenu) throws CoreException;

	public int deleteApMenuByMenuId(String[] menuId) throws CoreException;

	public int updateApMenu(ApMenu apMenu) throws CoreException;

	public int updateApMenuSelective(ApMenu apMenu) throws CoreException;

	public ApMenu queryApMenuByMenuId(String menuId) throws CoreException;

	public List<ApMenu> queryMenus(ApMenu menu);

	public int queryMenuTotal(ApMenu menu);

	public List<ApMenu> queryUserMenus(ApUserRole userRole);
	
	public List<ApMenu> queryUserMenuByUserId(ApUserRole userRole);

	public List<ApMenu> queryAllMenus(ApMenu menu);

	public List<ApMenu> queryMenuByFlag(Map<String, String> paraMap);

	public List<ApMenu> queryMenuByUserIdAndNodeNo(Map<String, String> paraMap);

	public List<ApMenu> queryMenuUrlByNodeNo(Map<String, String> paraMap);
}
