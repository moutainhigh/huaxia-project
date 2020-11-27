package com.huaxia.opas.domain.thirdparty;

import java.util.Date;

public class Eninsurdep {
    private String insideAppNo;

    private String joinSoclInsuPl;//参保地

    private String joinSoclInsuYm;//参保年月

    private String cumulPayMonths;//累计缴费月数

    private String workMonths;//参加工作月份

    private String payStatus;//缴费状态

    private String personPayBase;//个人缴费基数

    private String curMpayAmt;//本月缴费金额

    private String payComp;//缴费单位

    private String pausePayReason;//中断或终止缴费原因

    private String infoUpdTime;//信息更新日期

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

    public String getJoinSoclInsuPl() {
        return joinSoclInsuPl;
    }

    public void setJoinSoclInsuPl(String joinSoclInsuPl) {
        this.joinSoclInsuPl = joinSoclInsuPl == null ? null : joinSoclInsuPl.trim();
    }

    public String getJoinSoclInsuYm() {
        return joinSoclInsuYm;
    }

    public void setJoinSoclInsuYm(String joinSoclInsuYm) {
        this.joinSoclInsuYm = joinSoclInsuYm == null ? null : joinSoclInsuYm.trim();
    }

    public String getCumulPayMonths() {
        return cumulPayMonths;
    }

    public void setCumulPayMonths(String cumulPayMonths) {
        this.cumulPayMonths = cumulPayMonths == null ? null : cumulPayMonths.trim();
    }

    public String getWorkMonths() {
        return workMonths;
    }

    public void setWorkMonths(String workMonths) {
        this.workMonths = workMonths == null ? null : workMonths.trim();
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getPersonPayBase() {
        return personPayBase;
    }

    public void setPersonPayBase(String personPayBase) {
        this.personPayBase = personPayBase == null ? null : personPayBase.trim();
    }

    public String getCurMpayAmt() {
        return curMpayAmt;
    }

    public void setCurMpayAmt(String curMpayAmt) {
        this.curMpayAmt = curMpayAmt == null ? null : curMpayAmt.trim();
    }

    public String getPayComp() {
        return payComp;
    }

    public void setPayComp(String payComp) {
        this.payComp = payComp == null ? null : payComp.trim();
    }

    public String getPausePayReason() {
        return pausePayReason;
    }

    public void setPausePayReason(String pausePayReason) {
        this.pausePayReason = pausePayReason == null ? null : pausePayReason.trim();
    }

    public String getInfoUpdTime() {
        return infoUpdTime;
    }

    public void setInfoUpdTime(String infoUpdTime) {
        this.infoUpdTime = infoUpdTime == null ? null : infoUpdTime.trim();
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