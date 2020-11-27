package com.huaxia.opas.action.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.MenuObj;
import com.huaxia.opas.domain.MenuTreeObj;
import com.huaxia.opas.domain.system.ApMenu;
import com.huaxia.opas.domain.system.ApRoleMenu;
import com.huaxia.opas.domain.system.ApUserRole;
import com.huaxia.opas.service.system.ApMenuService;
import com.huaxia.opas.service.system.ApRoleMenuService;
import com.huaxia.opas.util.MenuTreeUtil;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 菜单
 * 
 * @author zhiguo.li
 *
 */
public class MenuAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(MenuAction.class);

	@Resource(name = "apMenuService")
	private ApMenuService apMenuService;

	@Resource(name = "apRoleMenuService")
	private ApRoleMenuService apRoleMenuService;

	/**
	 * 菜单全量信息分页查询
	 * 
	 * @param context
	 * @throws CoreException
	 */
	public void listMenus(Context context) throws CoreException {
		Map<String, Object> menuMap = new HashMap<String, Object>();
		ApMenu menu = null;
		List<ApMenu> menus = apMenuService.queryMenus(menu);
		List<MenuTreeObj> menuTree = buildMenuTreeList(menus);
		menuMap.put("rows", menuTree);
		context.setDataMap(menuMap);
	}

	/**
	 * 菜单添加
	 * 
	 * @param context
	 * @throws CoreException
	 */
	public void saveMenu(Context context) throws CoreException {
		Gson json = new Gson();
		ApMenu apMenu = json.fromJson(json.toJson(context.getDataMap()),
				ApMenu.class);
		apMenu.setMenuId(SequenceUtil.getUUID());
		int num = apMenuService.insertApMenu(apMenu);
		context.setState(String.valueOf(num));
	}

	/**
	 * 菜单删除
	 * 
	 * @param context
	 * @throws CoreException
	 */
	public void removeMenu(Context context) throws CoreException {
		String[] menuId = String.valueOf(context.getDataMap().get("menuids"))
				.split(",");
		for (int i = 0; i < menuId.length; i++) {
			apRoleMenuService.deleteApRoleMenuByMenuId(menuId[i]);
		}
		int num = apMenuService.deleteApMenuByMenuId(menuId);
		context.setState(String.valueOf(num));
	}

	/**
	 * 菜单更新
	 * 
	 * @param context
	 * @throws CoreException
	 */
	public void updateMenu(Context context) throws CoreException {
		Gson json = new Gson();
		ApMenu apMenu = json.fromJson(json.toJson(context.getDataMap()),
				ApMenu.class);
		int num = apMenuService.updateApMenu(apMenu);
		context.setState(String.valueOf(num));
	}

	/**
	 * 全量菜单信息查询
	 * 
	 * @param context
	 */
	public void listAllMenus(Context context) {
		String roleId = String.valueOf(context.getDataMap().get("roleId"));
		ApUserRole userRole = new ApUserRole();
		userRole.setRoleId(roleId);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		ArrayList<ApMenu> apMenus = (ArrayList<ApMenu>) apMenuService
				.queryAllMenus(null);
		logger.info("角色授权开始...................................................begin!"
				+ apMenus.size());
		ArrayList<ApMenu> userMenus = (ArrayList<ApMenu>) apMenuService
				.queryUserMenus(userRole);
		logger.info("用户角色..................................................."
				+ userMenus.size());
		List<MenuObj> treeMenu = MenuTreeUtil.buildMenuData(MenuTreeUtil
				.sortMenus(apMenus));
		for (MenuObj menuObj : treeMenu) {
			if (menuObj.getChildren() != null
					&& menuObj.getChildren().size() > 0) {
				logger.info("菜单名称:" + menuObj.getText() + "**********子菜单数:"
						+ menuObj.getChildren().size());
			} else {
				logger.info("菜单名称:" + menuObj.getText() + "**********子菜单数:" + 0);
			}
		}
		logger.info("角色排序后........................................."
				+ treeMenu.size());
		List<MenuObj> finalTreeMenu = MenuTreeUtil
				.setCheck(treeMenu, userMenus);
		logger.info("角色授权结束...................................................end!"
				+ finalTreeMenu.size());
		dataMap.put("menus", finalTreeMenu);
		context.setDataMap(dataMap);
	}

	/**
	 * 保存角色菜单关系信息
	 * 
	 * @param context
	 */
	public void saveRoleMenus(Context context) {
		Gson gson = new Gson();
		String roleId = String.valueOf(context.getDataMap().get("roleId"));
		String jsonData = gson.toJson(context.getDataMap().get("menuInfo"));
		List<ApRoleMenu> roleMenus = gson.fromJson(jsonData,
				new TypeToken<List<ApRoleMenu>>() {
				}.getType());
		int num = apRoleMenuService.deleteApRoleMenuByRoleId(roleId);
		int num01 = apRoleMenuService.insertApRoleMenuBatch(roleMenus);

	}

	/**
	 * 用户菜单查询
	 * 
	 * @param context
	 */
	public void userMenu(Context context) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		ApUserRole apUsrRole = new ApUserRole();
		ArrayList<ApMenu> menus = (ArrayList<ApMenu>) apMenuService
				.queryUserMenus(apUsrRole);
		List<MenuObj> treeMenu = MenuTreeUtil.buildMenuData(MenuTreeUtil
				.sortMenus(menus));
		dataMap.put("menus", treeMenu);
		context.setDataMap(dataMap);
	}

	/**
	 * 构建菜单表格树
	 * 
	 * @param menus
	 * @return
	 */
	public List<MenuTreeObj> buildMenuTreeList(List<ApMenu> menus) {
		List<MenuTreeObj> menuLists = new ArrayList<MenuTreeObj>();
		for (ApMenu apMenu : menus) {
			MenuTreeObj obj = new MenuTreeObj(apMenu);
			menuLists.add(obj);
		}
		return menuLists;
	}

	public ApMenuService getApMenuService() {
		return apMenuService;
	}

	public void setApMenuService(ApMenuService apMenuService) {
		this.apMenuService = apMenuService;
	}

	public ApRoleMenuService getApRoleMenuService() {
		return apRoleMenuService;
	}

	public void setApRoleMenuService(ApRoleMenuService apRoleMenuService) {
		this.apRoleMenuService = apRoleMenuService;
	}

}
