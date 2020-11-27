package com.huaxia.opas.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.domain.MenuObj;
import com.huaxia.opas.domain.system.ApMenu;

/**
 * 菜单树工具类
 * 
 * @author Administrator
 *
 */
public class MenuTreeUtil {

	private static Logger logger = LoggerFactory.getLogger(MenuTreeUtil.class);

	/**
	 * 选中用户拥有菜单
	 * 
	 * @return
	 */
	public static List<MenuObj> setCheck(List<MenuObj> menuObjs,
			List<ApMenu> menus) {
		for (int i = 0; i < menuObjs.size(); i++) {
			MenuObj menuObj = menuObjs.get(i);
			for (int j = 0; j < menus.size(); j++) {
				ApMenu apMenu = menus.get(j);
				if (menuObj.getId().equals(apMenu.getMenuId())) {
					menuObjs.get(i).setChecked(true);
				} else if (menuObj.getChildren() != null
						&& menuObj.getChildren().size() > 0) {
					setCheck(menuObj.getChildren(), menus);
				} else {
					continue;
				}
			}
		}

		return menuObjs;
	}

	/**
	 * 根据菜单层级排序
	 * 
	 * @param menus
	 * @return
	 */
	public static List<ApMenu> sortMenus(ArrayList<ApMenu> menus) {
		Collections.sort(menus, new Comparator<ApMenu>() {
			@Override
			public int compare(ApMenu o1, ApMenu o2) {
				if (Integer.parseInt(o1.getMenuLevel().trim()) > Integer
						.parseInt(o2.getMenuLevel().trim())) {
					return 1;
				} else if (Integer.parseInt(o1.getMenuLevel().trim()) > Integer
						.parseInt(o2.getMenuLevel().trim())) {
					return 0;
				} else {
					return -1;
				}
			}
		});

		for (ApMenu apMenu : menus) {
			logger.info("菜单名称" + apMenu.getMenuName() + "...菜单级别:"
					+ apMenu.getMenuLevel());
		}
		return menus;

	}

	/**
	 * 构建菜单数据
	 * 
	 * @param menus
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<MenuObj> buildMenuData(List<ApMenu> menus) {
		List<MenuObj> menuDatas = new ArrayList<MenuObj>();
		for (int i = 0; i < menus.size(); i++) {
			ApMenu menu = menus.get(i);
			MenuObj menuObj = new MenuObj(menu.getMenuId(), menu.getMenuName(),
					menu.getMenuUrl(), menu.getMenuParentId(),
					menu.getMenuLevel());
			if (Constant.ZERO_MENU.equals(menu.getMenuLevel())) {
				menuDatas.add(menuObj);
			} else {
				Map<String, Object> dataMap = appendMenuChild(menuDatas,
						menuObj);
				menuDatas = (List<MenuObj>) dataMap.get("datas");
				boolean isadd = (Boolean) dataMap.get("isadd");
				if (!isadd && "0".equals(menu.getMenuLevel())) {
					menuDatas.add(menuObj);
				}
			}
		}
		return menuDatas;
	}

	/**
	 * 递归封装菜单树结构
	 * 
	 * @param menuObjs
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> appendMenuChild(List<MenuObj> menuObjs,
			MenuObj obj) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		boolean isadd = false;
		for (int j = 0; j < menuObjs.size(); j++) {
			MenuObj menuObj = menuObjs.get(j);
			if (menuObj.getId().equals(obj.getParentId())) {
				if (menuObj.getChildren() != null) {
					menuObj.getChildren().add(obj);
					isadd = true;
				} else {
					List<MenuObj> childs = new ArrayList<MenuObj>();
					childs.add(obj);
					menuObj.setChildren(childs);
					isadd = true;
				}
			} else {
				List<MenuObj> menus = menuObj.getChildren();
				if (menus != null) {
					appendMenuChild(menus, obj);
				}
			}
		}
		dataMap.put("isadd", isadd);
		dataMap.put("datas", menuObjs);
		return dataMap;
	}

}
