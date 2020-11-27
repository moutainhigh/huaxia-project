package com.huaxia.opas.domain.thirdparty;

import java.util.Date;

public class CivJudge {
    private String insideAppNo;

    private String regCourt;//立案法院

    private String caseReason;//案由

    private String regDate;//立案日期

    private String closeCaseWay;//结案方式

    private String judgMediateRst;//判决/调解结果

    private String judgMediateEffctDt;//判决/调解生效日期

    private String lawObject;//诉讼标的

    private String lawObjectAmt;//诉讼标的金额

    private String crtTime;

    private String crtUser;

    private Date lstUpdTime;

    private String lstUpdUser;

    private Date batchDate;

    private String recStatus;

    private String appId;

    public String getInsideAppNo() {
        return insideAppNo;
    }

    public void setInsideAppNo(String insideAppNo) {
        this.insideAppNo = insideAppNo == null ? null : insideAppNo.trim();
    }

    public String getRegCourt() {
        return regCourt;
    }

    public void setRegCourt(String regCourt) {
        this.regCourt = regCourt == null ? null : regCourt.trim();
    }

    public String getCaseReason() {
        return caseReason;
    }

    public void setCaseReason(String caseReason) {
        this.caseReason = caseReason == null ? null : caseReason.trim();
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate == null ? null : regDate.trim();
    }

    public String getCloseCaseWay() {
        return closeCaseWay;
    }

    public void setCloseCaseWay(String closeCaseWay) {
        this.closeCaseWay = closeCaseWay == null ? null : closeCaseWay.trim();
    }

    public String getJudgMediateRst() {
        return judgMediateRst;
    }

    public void setJudgMediateRst(String judgMediateRst) {
        this.judgMediateRst = judgMediateRst == null ? null : judgMediateRst.trim();
    }

    public String getJudgMediateEffctDt() {
        return judgMediateEffctDt;
    }

    public void setJudgMediateEffctDt(String judgMediateEffctDt) {
        this.judgMediateEffctDt = judgMediateEffctDt == null ? null : judgMediateEffctDt.trim();
    }

    public String getLawObject() {
        return lawObject;
    }

    public void setLawObject(String lawObject) {
        this.lawObject = lawObject == null ? null : lawObject.trim();
    }

    public String getLawObjectAmt() {
        return lawObjectAmt;
    }

    public void setLawObjectAmt(String lawObjectAmt) {
        this.lawObjectAmt = lawObjectAmt == null ? null : lawObjectAmt.trim();
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }
}