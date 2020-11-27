package com.huaxia.plaze.ui.fico.domain;

import java.util.Date;

/**
 * 批次文件表
 * 
 * @author liuwei
 *
 */
public class FicoBatchDetail {
	// uuid
	private String uuid;
	// 创建时间
	private String crtTime;
	// 创建者
	private String crtUser;
	// 批次文件编号
	private String batchFileId;
	// 交易编号
	private String trnId;
	// 查询状态
	private String queryStatus;
	// 查询时间
	private String queryTime;
	// 证件号码
	private String certNo;
	// 手机号码
	private String mobile;
	//手机号前三位
	private String topThreeMobile;
	//日期
	private String inputSysDate;
	//唯一编号
	private String uniqueID;
	//发卡账户
	private String cardAccount;
	//顺序序号
	private String sequenceNumber;
	//加密证件号
	private String encryptionCertNO;
	//加密手机号
	private String encryptionMobile;
	//风险等级
	private String riskLevel;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	public String getBatchFileId() {
		return batchFileId;
	}
	public void setBatchFileId(String batchFileId) {
		this.batchFileId = batchFileId;
	}
	public String getTrnId() {
		return trnId;
	}
	public void setTrnId(String trnId) {
		this.trnId = trnId;
	}
	public String getQueryStatus() {
		return queryStatus;
	}
	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTopThreeMobile() {
		return topThreeMobile;
	}
	public void setTopThreeMobile(String topThreeMobile) {
		this.topThreeMobile = topThreeMobile;
	}
	public String getInputSysDate() {
		return inputSysDate;
	}
	public void setInputSysDate(String inputSysDate) {
		this.inputSysDate = inputSysDate;
	}
	public String getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	public String getCardAccount() {
		return cardAccount;
	}
	public void setCardAccount(String cardAccount) {
		this.cardAccount = cardAccount;
	}
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getEncryptionCertNO() {
		return encryptionCertNO;
	}
	public void setEncryptionCertNO(String encryptionCertNO) {
		this.encryptionCertNO = encryptionCertNO;
	}
	public String getEncryptionMobile() {
		return encryptionMobile;
	}
	public void setEncryptionMobile(String encryptionMobile) {
		this.encryptionMobile = encryptionMobile;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	
}
