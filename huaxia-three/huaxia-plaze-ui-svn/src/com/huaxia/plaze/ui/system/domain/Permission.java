package com.huaxia.plaze.ui.system.domain;

import org.apache.ibatis.type.Alias;

@Alias("Permission")
public class Permission {

	// 记录编号
	private String uuid;

	// 权限ID
	private String permId;

	// 资源对应权限
	private String permName;

	// 访问资源
	private String url;

	// 权限关键字
	private String keyword;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPermId() {
		return permId;
	}

	public void setPermId(String permId) {
		this.permId = permId;
	}

	public String getPermName() {
		return permName;
	}

	public void setPermName(String permName) {
		this.permName = permName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
