package com.huaxia.opas.domain.thirdparty;

import java.util.Date;

public class AccFund {
    private String insideAppNo;

    private String joinSoclInsuPl;//参保地

    private String joinSoclInsuDt;//参缴日期

    private String firstDepositYm;//初缴月份

    private String payYm;//缴至月份

    private String payStatus;//缴费状态

    private String monthlyDeposit;//月缴存额

    private String personDepositRate;//个人缴存比例

    private String compDepositRate;//单位缴存比例

    private String payComp;//缴费单位

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

    public String getJoinSoclInsuDt() {
        return joinSoclInsuDt;
    }

    public void setJoinSoclInsuDt(String joinSoclInsuDt) {
        this.joinSoclInsuDt = joinSoclInsuDt == null ? null : joinSoclInsuDt.trim();
    }

    public String getFirstDepositYm() {
        return firstDepositYm;
    }

    public void setFirstDepositYm(String firstDepositYm) {
        this.firstDepositYm = firstDepositYm == null ? null : firstDepositYm.trim();
    }

    public String getPayYm() {
        return payYm;
    }

    public void setPayYm(String payYm) {
        this.payYm = payYm == null ? null : payYm.trim();
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getMonthlyDeposit() {
        return monthlyDeposit;
    }

    public void setMonthlyDeposit(String monthlyDeposit) {
        this.monthlyDeposit = monthlyDeposit == null ? null : monthlyDeposit.trim();
    }

    public String getPersonDepositRate() {
        return personDepositRate;
    }

    public void setPersonDepositRate(String personDepositRate) {
        this.personDepositRate = personDepositRate == null ? null : personDepositRate.trim();
    }

    public String getCompDepositRate() {
        return compDepositRate;
    }

    public void setCompDepositRate(String compDepositRate) {
        this.compDepositRate = compDepositRate == null ? null : compDepositRate.trim();
    }

    public String getPayComp() {
        return payComp;
    }

    public void setPayComp(String payComp) {
        this.payComp = payComp == null ? null : payComp.trim();
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