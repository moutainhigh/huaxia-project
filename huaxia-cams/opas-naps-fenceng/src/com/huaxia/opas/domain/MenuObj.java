package com.huaxia.opas.domain;

import java.util.List;

/**
 * easy ui tree数据结构
 * 
 * @author Administrator
 *
 */
public class MenuObj {

	private static final long serialVersionUID = 1L;
	private String id;
	private String text;
	private String url;
	private String parentId;
	private String level;
	private boolean checked;
	private List<MenuObj> children;

	public MenuObj(String id, String text, String url, String parentId,
			String level) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.parentId = parentId;
		this.level = level;
	}

	public List<MenuObj> getChildren() {
		return children;
	}

	public void setChildren(List<MenuObj> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
