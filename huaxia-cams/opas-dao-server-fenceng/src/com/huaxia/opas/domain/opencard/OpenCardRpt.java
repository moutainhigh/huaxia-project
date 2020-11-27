package com.huaxia.opas.domain.opencard;

import java.io.Serializable;
import java.util.Date;

public class OpenCardRpt implements Serializable {

	private static final long serialVersionUID = -9052739149330031415L;
	private String bizOpenCardRptId;
	private String openCardFileName;
	private String applyNo;
	private Long lineNum;
	private String appCardProd;
	private String cardBelongBranchNo;
	private String errorColumnName;
	private String errorDesc;
	private Date openCardTime;
	private Date crtDate;
	private Date crtTime;
	private String crtUser;
	private String lstUpdUser;
	private Date lstUpdDate;
	private Date lstUpdTime;
	private Date batchDate;
	private String recStatus;
	private String scrLevel;

	public String getBizOpenCardRptId() {
		return bizOpenCardRptId;
	}

	public void setBizOpenCardRptId(String bizOpenCardRptId) {
		this.bizOpenCardRptId = (bizOpenCardRptId == null ? null
				: bizOpenCardRptId.trim());
	}

	public String getOpenCardFileName() {
		return openCardFileName;
	}

	public void setOpenCardFileName(String openCardFileName) {
		this.openCardFileName = (openCardFileName == null ? null
				: openCardFileName.trim());
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = (applyNo == null ? null : applyNo.trim());
	}

	public Long getLineNum() {
		return lineNum;
	}

	public void setLineNum(Long lineNum) {
		this.lineNum = lineNum;
	}

	public String getAppCardProd() {
		return appCardProd;
	}

	public void setAppCardProd(String appCardProd) {
		this.appCardProd = (appCardProd == null ? null : appCardProd.trim());
	}

	public String getCardBelongBranchNo() {
		return cardBelongBranchNo;
	}

	public void setCardBelongBranchNo(String cardBelongBranchNo) {
		this.cardBelongBranchNo = (cardBelongBranchNo == null ? null
				: cardBelongBranchNo.trim());
	}

	public String getErrorColumnName() {
		return errorColumnName;
	}

	public void setErrorColumnName(String errorColumnName) {
		this.errorColumnName = (errorColumnName == null ? null
				: errorColumnName.trim());
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = (errorDesc == null ? null : errorDesc.trim());
	}

	public Date getOpenCardTime() {
		return openCardTime;
	}

	public void setOpenCardTime(Date openCardTime) {
		this.openCardTime = openCardTime;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = (crtUser == null ? null : crtUser.trim());
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = (lstUpdUser == null ? null : lstUpdUser.trim());
	}

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public Date getLstUpdTime() {
		return lstUpdTime;
	}

	public void setLstUpdTime(Date lstUpdTime) {
		this.lstUpdTime = lstUpdTime;
	}

	public Date getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}

	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = (recStatus == null ? null : recStatus.trim());
	}

	public String getScrLevel() {
		return scrLevel;
	}

	public void setScrLevel(String scrLevel) {
		this.scrLevel = (scrLevel == null ? null : scrLevel.trim());
	}

}
