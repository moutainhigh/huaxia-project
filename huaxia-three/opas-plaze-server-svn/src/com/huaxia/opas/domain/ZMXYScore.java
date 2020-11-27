package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 芝麻信用 & 芝麻信用评分
 * 
 * @author zhiguo.li
 *
 */
public class ZMXYScore implements Serializable {

	private static final long serialVersionUID = 3154055459877470009L;

	// 申请件编号
	private String appId;

	// 请求参数 & 身份证号
	private String identityCard;

	// 商户业务流水号
	private String transactionId;

	// 产品码
	private String productCode;

	// 芝麻会员在商户端的身份标识
	private String openId;

	// 芝麻信用业务号
	private String bizNo;

	// 芝麻信用评分（分值范围[350,950]）
	private String zmScore;

	// 请求结果
	private String success;

	// 创建日期
	private String crtTime;

	// 创建人
	private String crtUser;

	// 最后操作日期
	private String lstUpdTime;

	// 最后更新人
	private String lstUpdUser;

	// [非持久化字段] 报文响应结果
	private Boolean responseResult;
	
	// [非持久化字段] 错误代码
	private String errorCode;
	
	// [非持久化字段] 错误描述
	private String errorDesc;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public Boolean getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(Boolean responseResult) {
		this.responseResult = responseResult;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public String getZmScore() {
		return zmScore;
	}

	public void setZmScore(String zmScore) {
		this.zmScore = zmScore;
	}

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
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

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

}
