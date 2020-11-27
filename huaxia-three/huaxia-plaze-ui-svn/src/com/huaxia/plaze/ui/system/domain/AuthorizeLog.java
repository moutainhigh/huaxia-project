package com.huaxia.plaze.ui.system.domain;

import org.apache.ibatis.type.Alias;

@Alias("AuthorizeLog")
public class AuthorizeLog extends Log {
	
	// 执行动作（例如：登录|退出）
	private String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String getCrtTime() {
		return super.getCrtTime();
	}

	@Override
	public void setCrtTime(String crtTime) {
		super.setCrtTime(crtTime);
	}

	@Override
	public String getAccount() {
		return super.getAccount();
	}

	@Override
	public void setAccount(String account) {
		super.setAccount(account);
	}

	@Override
	public String getStaffName() {
		return super.getStaffName();
	}

	@Override
	public void setStaffName(String staffName) {
		super.setStaffName(staffName);
	}

	@Override
	public String getCertNo() {
		return super.getCertNo();
	}

	@Override
	public void setCertNo(String certNo) {
		super.setCertNo(certNo);
	}

	@Override
	public String getIp() {
		return super.getIp();
	}

	@Override
	public void setIp(String ip) {
		super.setIp(ip);
	}
	
}
