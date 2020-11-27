package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 人行请求检查
 * 
 * @author zhiguo.li
 *
 */
public class VerificationRequestCheck implements Serializable {

	private static final long serialVersionUID = 412039173793101005L;

	// 验证码
	private String verificationCode;
	
	// 用户名称
	private String userName;
	
	// 用户密码
	private String userPass;
	
	// 当前日期
	private String currDate;

	public VerificationRequestCheck() {
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getCurrDate() {
		return currDate;
	}

	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}

}
