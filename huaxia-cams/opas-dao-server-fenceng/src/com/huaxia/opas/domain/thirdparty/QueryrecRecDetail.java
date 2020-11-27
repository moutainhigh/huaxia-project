package com.huaxia.opas.domain.thirdparty;

import java.util.Date;

public class QueryrecRecDetail {
    private String faRecSeq;//父记录编号

    private String insideAppNo;//

    private String qryDate;//查询日期

    private String qryOperator;//查询操作员

    private String qryReason;//查询原因

    private String crtTime;

    private String crtUser;

    private Date lstUpdTime;

    private String lstUpdUser;

    private Date batchDate;

    private String recStatus;

    private String appId;

    public String getFaRecSeq() {
        return faRecSeq;
    }

    public void setFaRecSeq(String faRecSeq) {
        this.faRecSeq = faRecSeq == null ? null : faRecSeq.trim();
    }

    public String getInsideAppNo() {
        return insideAppNo;
    }

    public void setInsideAppNo(String insideAppNo) {
        this.insideAppNo = insideAppNo == null ? null : insideAppNo.trim();
    }

    public String getQryDate() {
        return qryDate;
    }

    public void setQryDate(String qryDate) {
        this.qryDate = qryDate == null ? null : qryDate.trim();
    }

    public String getQryOperator() {
        return qryOperator;
    }

    public void setQryOperator(String qryOperator) {
        this.qryOperator = qryOperator == null ? null : qryOperator.trim();
    }

    public String getQryReason() {
        return qryReason;
    }

    public void setQryReason(String qryReason) {
        this.qryReason = qryReason == null ? null : qryReason.trim();
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