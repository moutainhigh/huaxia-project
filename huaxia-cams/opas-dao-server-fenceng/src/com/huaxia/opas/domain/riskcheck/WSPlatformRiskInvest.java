package com.huaxia.opas.domain.riskcheck;

import java.io.Serializable;
import java.util.Date;

public class WSPlatformRiskInvest implements Serializable{
	private static final long serialVersionUID = 5913990026577457067L;
	private String autoId;//UUID
	private String appId;//流水号
    private String businessNumber;//公司编码
    private String moduleNumber;//模块编码
    private String sceneDNLinkPart;//场景链接域名部分
    private String ptfljResult;//场景链接域名部分判断结果 0-不一致,1-一致,-1-空,-2-疑似原生态,-3-其他
    private String ptsszdResult;//是否为超出阈值进件 0-否,1-是
    private String isBSOpen;//是否因阻断开关开启的进件 0-否,1-是
    private String alInvestResult;//反链接-人工调查结果 2-乱码,1-违规布放,0-正常布放,-1-新增场景
    private String misszdResult;//实时阻断-人工调查结果 0-正常进件,1-疑似中介进件,-1-无法判断
    private String remark;//备注
    private Date crtDate;//创建日期
    private String currOptUser;//当前操作人
    private Date lastOptDate;//最后操作人
    private String lastOptUser;//最后操作日期
    private String alreadySendFlag;//是否已发卡
    private String refuseReason;//未发卡原因（拒绝码）
    private String circulateFlag;//流转标识 1-继续流转，2-拒绝流转
    private String refuseCirculateReason;//拒绝流转原因 WS101-链接违规布放
    
	public WSPlatformRiskInvest() {
		super();
	}

	public WSPlatformRiskInvest(String autoId, String appId, String businessNumber, String moduleNumber,
			String sceneDNLinkPart, String ptfljResult, String ptsszdResult, String isBSOpen, String alInvestResult,
			String misszdResult, String remark, Date crtDate, String currOptUser, Date lastOptDate, String lastOptUser,
			String alreadySendFlag, String refuseReason, String circulateFlag, String refuseCirculateReason) {
		super();
		this.autoId = autoId;
		this.appId = appId;
		this.businessNumber = businessNumber;
		this.moduleNumber = moduleNumber;
		this.sceneDNLinkPart = sceneDNLinkPart;
		this.ptfljResult = ptfljResult;
		this.ptsszdResult = ptsszdResult;
		this.isBSOpen = isBSOpen;
		this.alInvestResult = alInvestResult;
		this.misszdResult = misszdResult;
		this.remark = remark;
		this.crtDate = crtDate;
		this.currOptUser = currOptUser;
		this.lastOptDate = lastOptDate;
		this.lastOptUser = lastOptUser;
		this.alreadySendFlag = alreadySendFlag;
		this.refuseReason = refuseReason;
		this.circulateFlag = circulateFlag;
		this.refuseCirculateReason = refuseCirculateReason;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getModuleNumber() {
		return moduleNumber;
	}

	public void setModuleNumber(String moduleNumber) {
		this.moduleNumber = moduleNumber;
	}

	public String getSceneDNLinkPart() {
		return sceneDNLinkPart;
	}

	public void setSceneDNLinkPart(String sceneDNLinkPart) {
		this.sceneDNLinkPart = sceneDNLinkPart;
	}

	public String getPtfljResult() {
		return ptfljResult;
	}

	public void setPtfljResult(String ptfljResult) {
		this.ptfljResult = ptfljResult;
	}

	public String getPtsszdResult() {
		return ptsszdResult;
	}

	public void setPtsszdResult(String ptsszdResult) {
		this.ptsszdResult = ptsszdResult;
	}

	public String getIsBSOpen() {
		return isBSOpen;
	}

	public void setIsBSOpen(String isBSOpen) {
		this.isBSOpen = isBSOpen;
	}

	public String getAlInvestResult() {
		return alInvestResult;
	}

	public void setAlInvestResult(String alInvestResult) {
		this.alInvestResult = alInvestResult;
	}

	public String getMisszdResult() {
		return misszdResult;
	}

	public void setMisszdResult(String misszdResult) {
		this.misszdResult = misszdResult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getCurrOptUser() {
		return currOptUser;
	}

	public void setCurrOptUser(String currOptUser) {
		this.currOptUser = currOptUser;
	}

	public Date getLastOptDate() {
		return lastOptDate;
	}

	public void setLastOptDate(Date lastOptDate) {
		this.lastOptDate = lastOptDate;
	}

	public String getLastOptUser() {
		return lastOptUser;
	}

	public void setLastOptUser(String lastOptUser) {
		this.lastOptUser = lastOptUser;
	}

	public String getAlreadySendFlag() {
		return alreadySendFlag;
	}

	public void setAlreadySendFlag(String alreadySendFlag) {
		this.alreadySendFlag = alreadySendFlag;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getCirculateFlag() {
		return circulateFlag;
	}

	public void setCirculateFlag(String circulateFlag) {
		this.circulateFlag = circulateFlag;
	}

	public String getRefuseCirculateReason() {
		return refuseCirculateReason;
	}

	public void setRefuseCirculateReason(String refuseCirculateReason) {
		this.refuseCirculateReason = refuseCirculateReason;
	}

	
}