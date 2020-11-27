package com.huaxia.plaze.ui.system.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

/**
 * 菜单树
 * 
 * @author zhiguo.li
 *
 */
public class MenuTree implements Serializable {

	private static final long serialVersionUID = 304632914155461833L;

	public static List<Menu> sort(List<Menu> menuList) {
		Collections.sort(menuList, new Comparator<Menu>() {

			@Override
			public int compare(Menu menu1, Menu menu2) {
				if (menu1.getIndex().compareTo(menu2.getIndex()) > 0) {
					return 1;
				} else if (menu1.getIndex().compareTo(menu2.getIndex()) < 0) {
					return -1;
				} else {
					return 0;
				}
			}

		});
		return menuList;
	}

	public static String json(List<Menu> menuList) {
		Map<String, Object> tree = new HashMap<String, Object>();
		tree.put("USERMENU", menuList);
		return JSONArray.toJSONString(tree);
	}

	public static List<Menu> walk(List<Menu> menuList) {
		if (menuList == null) {
			throw new IllegalArgumentException("菜单列表数据非法");
		}

		List<Menu> tree = new ArrayList<Menu>();
		for (Menu menu : menuList) {
			if (tree.contains(menu)) {
				continue;
			}
			
			if (menu.getLevel() == 1) {
				tree.add(menu);
			} else {
				if (tree.size() == 0) {
					for (Menu first : menuList) {
						if (first.getLevel() == 1 && first.getId().equals(menu.getPid())) {
							List<Menu> children = new ArrayList<Menu>();
							children.add(menu);
							first.setChildren(children);
							tree.add(first);
							break;
						}
					}
				} else {
					build(tree, menu);
				}
			}
		}

		return tree;
	}

	private static void build(List<Menu> tree, Menu menu) {
		if (tree == null) {
			return;
		}
		
		for (Menu node : tree) {
			if (node.getId().equals(menu.getPid())) {
				if (node.getChildren() == null) {
					List<Menu> children = new ArrayList<Menu>();
					children.add(menu);
					node.setChildren(children);
				} else {
					node.getChildren().add(menu);
				}
			} else {
				build(node.getChildren(), menu);
			}
		}
	}

	public static void main(String[] args) {
		List<Menu> menuList = new ArrayList<Menu>();

		Menu menu0100 = new Menu("001", "菜单0100", 1, "/001", 1, null);
		Menu menu0101 = new Menu("002", "菜单0101", 2, "/002", 2, "001");
		Menu menu0102 = new Menu("003", "菜单0102", 2, "/003", 3, "001");
		Menu menu0200 = new Menu("004", "菜单0200", 1, "/004", 4, null);
		Menu menu0201 = new Menu("005", "菜单0201", 2, "/005", 5, "004");

		menuList.add(menu0100);
		menuList.add(menu0101);
		menuList.add(menu0102);
		menuList.add(menu0200);
		menuList.add(menu0201);

		List<Menu> tree = MenuTree.walk(menuList);
		MenuTree.sort(tree);
		String json = MenuTree.json(tree);
		System.out.println(json);
	}

}
