package com.huaxia.opas.domain.thirdparty;

import java.util.Date;

public class LoanCardSpetrad {
    private String faRecSeq;

    private String messType;

    private String insideAppNo;

    private String type;

    private String occurDate;

    private String changingMonths;

    private String occurAmt;

    private String detailRec;

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

    public String getMessType() {
        return messType;
    }

    public void setMessType(String messType) {
        this.messType = messType == null ? null : messType.trim();
    }

    public String getInsideAppNo() {
        return insideAppNo;
    }

    public void setInsideAppNo(String insideAppNo) {
        this.insideAppNo = insideAppNo == null ? null : insideAppNo.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(String occurDate) {
        this.occurDate = occurDate == null ? null : occurDate.trim();
    }

    public String getChangingMonths() {
        return changingMonths;
    }

    public void setChangingMonths(String changingMonths) {
        this.changingMonths = changingMonths == null ? null : changingMonths.trim();
    }

    public String getOccurAmt() {
        return occurAmt;
    }

    public void setOccurAmt(String occurAmt) {
        this.occurAmt = occurAmt == null ? null : occurAmt.trim();
    }

    public String getDetailRec() {
        return detailRec;
    }

    public void setDetailRec(String detailRec) {
        this.detailRec = detailRec == null ? null : detailRec.trim();
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