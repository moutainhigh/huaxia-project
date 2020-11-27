package com.huaxia.opas.domain.thirdparty;

import java.util.Date;

public class AssetDisposition {
    private String insideAppNo;

    private String assetsManageComp;//资产管理公司

    private String debtReceDate;//债务接收日期

    private String receDebtAmt;//接收的债务金额

    private String latestPaymentDate;//最近一次还款日期

    private String balance;//余额

    private String crtTime;//

    private String crtUser;//

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

    public String getAssetsManageComp() {
        return assetsManageComp;
    }

    public void setAssetsManageComp(String assetsManageComp) {
        this.assetsManageComp = assetsManageComp == null ? null : assetsManageComp.trim();
    }

    public String getDebtReceDate() {
        return debtReceDate;
    }

    public void setDebtReceDate(String debtReceDate) {
        this.debtReceDate = debtReceDate == null ? null : debtReceDate.trim();
    }

    public String getReceDebtAmt() {
        return receDebtAmt;
    }

    public void setReceDebtAmt(String receDebtAmt) {
        this.receDebtAmt = receDebtAmt == null ? null : receDebtAmt.trim();
    }

    public String getLatestPaymentDate() {
        return latestPaymentDate;
    }

    public void setLatestPaymentDate(String latestPaymentDate) {
        this.latestPaymentDate = latestPaymentDate == null ? null : latestPaymentDate.trim();
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance == null ? null : balance.trim();
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