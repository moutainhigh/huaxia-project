package com.huaxia.plaze.ui.system.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("PasswordDetail")
public class PasswordDetail implements Serializable {

	private static final long serialVersionUID = 8199961575686540632L;

	// 记录编号
	private String uuid;

	// 用户账号
	private String account;

	// 用户口令
	private String password;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
