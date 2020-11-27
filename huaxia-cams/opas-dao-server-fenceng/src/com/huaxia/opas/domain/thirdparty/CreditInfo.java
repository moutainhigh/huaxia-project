package com.huaxia.opas.domain.thirdparty;

import java.util.Date;

public class CreditInfo {
    private String insideAppNo;//内审编号

    private String personHouLoanNo;//个人住房贷款笔数

    private String personComHouLoanNo;//个人商用房贷款笔数

    private String otherLoanNum;//其他贷款笔数

    private String frsLoanGrantMon;//首笔贷款发放月份

    private String creditCardAcctNum;//贷记卡账户数

    private String frsCredCrdIssueMon;//首张贷记卡发卡月份

    private String semiCredAcctNum;//准贷记卡账户数

    private String frsSemiCredIssuMon;//首张准贷记卡发卡月份

    private String selfAnnounceCnt;//本人声明数目

    private String dissentCount;//异议标注数目

    private String numberRead;//数字解读

    private String oppositePosition;//相对位置

    private String crtTime;//创建时间

    private String crtUser;//

    private Date lstUpdTime;//

    private String lstUpdUser;//

    private Date batchDate;//

    private String recStatus;//

    private String reportNo;//

    private String appId;

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

    public String getFrsCredCrdIssueMon() {
        return frsCredCrdIssueMon;
    }

    public void setFrsCredCrdIssueMon(String frsCredCrdIssueMon) {
        this.frsCredCrdIssueMon = frsCredCrdIssueMon == null ? null : frsCredCrdIssueMon.trim();
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

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime == null ? null : crtTime.trim();
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