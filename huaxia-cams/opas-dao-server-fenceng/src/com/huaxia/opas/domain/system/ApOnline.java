package com.huaxia.opas.domain.system;

import java.io.Serializable;
import java.util.Date;

public class ApOnline implements Serializable {

	private static final long serialVersionUID = 3624431842940950572L;

	private String onlineId;
	
	private String userId;

	private String userCode;
	
	private String userName;

	private String ipAddr;
	
	private Date loginTime;
	
	private Date logoutTime;

	private String sessionId;

	private String status;

	public String getOnlineId() {
		return onlineId;
	}

	public void setOnlineId(String onlineId) {
		this.onlineId = onlineId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}