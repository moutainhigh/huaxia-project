package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 简项公安
 * 
 * @author zhiguo.li
 *
 */
public class SimplifyPolice implements Serializable {

	private static final long serialVersionUID = -4639261121883590343L;

	// 申请件编号
	private String appId;
	
	// 创建时间
	private String crtTime;

	// 创建人
	private String crtUser;

	// 最后操作时间
	private String lstUpdTime;

	// 最后更新人
	private String lstUpdUser;
	
	// 公民身份号码
	private String gmsfhm;
	
	// 公民身份号码核查结果
	private String gmsfhmResult;
	
	// 姓名
	private String xm;
	
	// 姓名核查结果
	private String xmResult;
	
	// 照片
	private String xp;
	
	// 错误代码
	private String errorCode;
	
	// 错误描述/缺失必录项描述
	private String errorMessage;
	
	// 缺失必录项字段名称
	private String errorMessageCol;
	
	/** 区分主附卡标识 */
	private String cardFlag;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}

	public String getLstUpdTime() {
		return lstUpdTime;
	}

	public void setLstUpdTime(String lstUpdTime) {
		this.lstUpdTime = lstUpdTime;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	public String getGmsfhm() {
		return gmsfhm;
	}

	public void setGmsfhm(String gmsfhm) {
		this.gmsfhm = gmsfhm;
	}

	public String getGmsfhmResult() {
		return gmsfhmResult;
	}

	public void setGmsfhmResult(String gmsfhmResult) {
		this.gmsfhmResult = gmsfhmResult;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXmResult() {
		return xmResult;
	}

	public void setXmResult(String xmResult) {
		this.xmResult = xmResult;
	}

	public String getXp() {
		return xp;
	}

	public void setXp(String xp) {
		this.xp = xp;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessageCol() {
		return errorMessageCol;
	}

	public void setErrorMessageCol(String errorMessageCol) {
		this.errorMessageCol = errorMessageCol;
	}

	public String getCardFlag() {
		return cardFlag;
	}

	public void setCardFlag(String cardFlag) {
		this.cardFlag = cardFlag;
	}
	
}
