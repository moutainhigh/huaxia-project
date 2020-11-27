package com.huaxia.opas.domain;

import com.huaxia.opas.domain.system.ApMenu;

/**
 * 菜单树domain
 * 
 * @author Administrator
 *
 */
public class MenuTreeObj extends ApMenu {

	private static final long serialVersionUID = -8197067762752167038L;
	
	private String id;
	private String _parentId;
	private String state = "closed";

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		if ("null".equals(_parentId) || "0".equals(_parentId)) {
			this._parentId = null;
		} else {
			this._parentId = _parentId;
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public MenuTreeObj(ApMenu menu) {
		super();
		this.setId(menu.getMenuId());
		this.setMenuId(menu.getMenuId());
		this.setMenuName(menu.getMenuName());
		this.setMenuParentId(menu.getMenuParentId());
		this.setMenuUrl(menu.getMenuUrl());
		this.setMenuLevel(menu.getMenuLevel());
		this.set_parentId(menu.getMenuParentId());
		this.setLstUpdTime(menu.getLstUpdDate());
		this.setBatchDate(menu.getBatchDate());
		this.setCrtDate(menu.getCrtDate());
		this.setCrtTime(menu.getCrtTime());
		this.setCrtUser(menu.getCrtUser());
		this.setLstUpdDate(menu.getLstUpdDate());
		this.setRecStatus(menu.getRecStatus());
		this.setScrLevel(menu.getScrLevel());
		this.setQueueFlag(menu.getQueueFlag());
		this.setNodeNo(menu.getNodeNo());
		if (!"0".equals(menu.getMenuParentId())
				&& menu.getMenuParentId() != null) {
			this.setState("");
		} else {
			this.setStatus(menu.getStatus());
		}
	}

}
