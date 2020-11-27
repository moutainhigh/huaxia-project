package com.huaxia.opas.domain.rule;

import java.io.Serializable;
import java.util.Date;

public class OpasPbocCreditCue implements Serializable{
    private String insideAppNo;

    private String personHouLoanNo;

    private String personComHouLoanNo;

    private String otherLoanNum;

    private String frsLoanGrantMon;

    private String creditCardAcctNum;

    private String frsCredCrdIssueMon;

    private String semiCredAcctNum;

    private String frsSemiCredIssuMon;

    private String selfAnnounceCnt;

    private String dissentCount;

    private String numberRead;

    private String oppositePosition;

    private Date crtTime;

    private String crtUser;

    private Date lstUpdTime;

    private String lstUpdUser;

    private Date batchDate;

    private String recStatus;

    private String reportNo;

    private String appId;

    private String queryReqTime;

    public String getQueryReqTime() {
		return queryReqTime;
	}

	public void setQueryReqTime(String queryReqTime) {
		this.queryReqTime = queryReqTime == null ? null : queryReqTime.trim();
	}

	public String getInsideAppNo() {
        return insideAppNo;
    }

    public void setInsideAppNo(String insideAppNo) {
        this.insideAppNo = insideAppNo == null ? null : insideAppNo.trim();
    }

    public String getPersonHouLoanNo() {
        return personHouLoanNo;
    }

    public void setPersonHouLoanNo(String personHouLoanNo) {
        this.personHouLoanNo = personHouLoanNo == null ? null : personHouLoanNo.trim();
    }

    public String getPersonComHouLoanNo() {
        return personComHouLoanNo;
    }

    public void setPersonComHouLoanNo(String personComHouLoanNo) {
        this.personComHouLoanNo = personComHouLoanNo == null ? null : personComHouLoanNo.trim();
    }

    public String getOtherLoanNum() {
        return otherLoanNum;
    }

    public void setOtherLoanNum(String otherLoanNum) {
        this.otherLoanNum = otherLoanNum == null ? null : otherLoanNum.trim();
    }

    public String getFrsLoanGrantMon() {
        return frsLoanGrantMon;
    }

    public void setFrsLoanGrantMon(String frsLoanGrantMon) {
        this.frsLoanGrantMon = frsLoanGrantMon == null ? null : frsLoanGrantMon.trim();
    }

    public String getCreditCardAcctNum() {
        return creditCardAcctNum;
    }

    public void setCreditCardAcctNum(String creditCardAcctNum) {
        this.creditCardAcctNum = creditCardAcctNum == null ? null : creditCardAcctNum.trim();
    }


    public String getSemiCredAcctNum() {
        return semiCredAcctNum;
    }

    public void setSemiCredAcctNum(String semiCredAcctNum) {
        this.semiCredAcctNum = semiCredAcctNum == null ? null : semiCredAcctNum.trim();
    }

    public String getFrsSemiCredIssuMon() {
        return frsSemiCredIssuMon;
    }

    public void setFrsSemiCredIssuMon(String frsSemiCredIssuMon) {
        this.frsSemiCredIssuMon = frsSemiCredIssuMon == null ? null : frsSemiCredIssuMon.trim();
    }

    public String getSelfAnnounceCnt() {
        return selfAnnounceCnt;
    }

    public void setSelfAnnounceCnt(String selfAnnounceCnt) {
        this.selfAnnounceCnt = selfAnnounceCnt == null ? null : selfAnnounceCnt.trim();
    }

    public String getDissentCount() {
        return dissentCount;
    }

    public void setDissentCount(String dissentCount) {
        this.dissentCount = dissentCount == null ? null : dissentCount.trim();
    }

    public String getNumberRead() {
        return numberRead;
    }

    public void setNumberRead(String numberRead) {
        this.numberRead = numberRead == null ? null : numberRead.trim();
    }

    public String getOppositePosition() {
        return oppositePosition;
    }

    public void setOppositePosition(String oppositePosition) {
        this.oppositePosition = oppositePosition == null ? null : oppositePosition.trim();
    }


	public String getFrsCredCrdIssueMon() {
		return frsCredCrdIssueMon;
	}

	public void setFrsCredCrdIssueMon(String frsCredCrdIssueMon) {
		this.frsCredCrdIssueMon = frsCredCrdIssueMon == null ? null : frsCredCrdIssueMon.trim();
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
        this.crtUser = crtUser == null ? null : crtUser.trim();
    }

    public Date getLstUpdTime() {
        return lstUpdTime;
    }

    public void setLstUpdTime(Date lstUpdTime) {
        this.lstUpdTime = lstUpdTime;
    }

    public String getLstUpdUser() {
        return lstUpdUser;
    }

    public void setLstUpdUser(String lstUpdUser) {
        this.lstUpdUser = lstUpdUser == null ? null : lstUpdUser.trim();
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
        this.recStatus = recStatus == null ? null : recStatus.trim();
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo == null ? null : reportNo.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }
}