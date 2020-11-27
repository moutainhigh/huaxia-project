package com.huaxia.plaze.ui.system.domain;

public abstract class Log {
	
	private String account;
	
	private String crtTime;
	
	private String userAcct;
	
	private String staffName;
	
	private String certNo;
	
	private String ip;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	protected String getCrtTime() {
		return crtTime;
	}

	protected void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}

	public String getUserAcct() {
		return userAcct;
	}

	public void setUserAcct(String userAcct) {
		this.userAcct = userAcct;
	}

	protected String getStaffName() {
		return staffName;
	}

	protected void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	protected String getCertNo() {
		return certNo;
	}

	protected void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	protected String getIp() {
		return ip;
	}

	protected void setIp(String ip) {
		this.ip = ip;
	}
	
}
