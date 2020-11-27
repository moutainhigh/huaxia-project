package com.huaxia.opas.domain.thirdparty;

import java.util.Date;

public class ForCeexe {
    private String insideAppNo;

    private String executeCourt;//执行法院

    private String executeCaseRsn;//执行案由

    private String regDate;//立案日期

    private String closeCaseWay;//结案方式

    private String caseStatus;//案件状态

    private String closeCaseDate;//结案日期

    private String applyExeObj;//申请执行标的

    private String applyExeObjAmt;//申请执行标的金额

    private String alreadyExeObj;//已执行标的

    private String alreadyExeObjAmt;//已执行标的金额

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

    public String getExecuteCourt() {
        return executeCourt;
    }

    public void setExecuteCourt(String executeCourt) {
        this.executeCourt = executeCourt == null ? null : executeCourt.trim();
    }

    public String getExecuteCaseRsn() {
        return executeCaseRsn;
    }

    public void setExecuteCaseRsn(String executeCaseRsn) {
        this.executeCaseRsn = executeCaseRsn == null ? null : executeCaseRsn.trim();
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

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus == null ? null : caseStatus.trim();
    }

    public String getCloseCaseDate() {
        return closeCaseDate;
    }

    public void setCloseCaseDate(String closeCaseDate) {
        this.closeCaseDate = closeCaseDate == null ? null : closeCaseDate.trim();
    }

    public String getApplyExeObj() {
        return applyExeObj;
    }

    public void setApplyExeObj(String applyExeObj) {
        this.applyExeObj = applyExeObj == null ? null : applyExeObj.trim();
    }

    public String getApplyExeObjAmt() {
        return applyExeObjAmt;
    }

    public void setApplyExeObjAmt(String applyExeObjAmt) {
        this.applyExeObjAmt = applyExeObjAmt == null ? null : applyExeObjAmt.trim();
    }

    public String getAlreadyExeObj() {
        return alreadyExeObj;
    }

    public void setAlreadyExeObj(String alreadyExeObj) {
        this.alreadyExeObj = alreadyExeObj == null ? null : alreadyExeObj.trim();
    }

    public String getAlreadyExeObjAmt() {
        return alreadyExeObjAmt;
    }

    public void setAlreadyExeObjAmt(String alreadyExeObjAmt) {
        this.alreadyExeObjAmt = alreadyExeObjAmt == null ? null : alreadyExeObjAmt.trim();
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