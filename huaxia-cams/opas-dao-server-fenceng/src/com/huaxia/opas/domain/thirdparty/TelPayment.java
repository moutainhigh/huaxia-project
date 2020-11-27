package com.huaxia.opas.domain.thirdparty;

import java.util.Date;

public class TelPayment {
    private String insideAppNo;

    private String telecomFacilitator;//电信运营商

    private String busiType;//业务类型

    private String busiOpnDate;//业务开通日期

    private String curPayStat;//当前缴费状态

    private String curDebtAmt;//当前欠费金额

    private String curDebtMonths;//当前欠费月数

    private String prest24mpayStat;//最近24个月缴费状态

    private String tallyYm;//记账年月

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

    public String getTelecomFacilitator() {
        return telecomFacilitator;
    }

    public void setTelecomFacilitator(String telecomFacilitator) {
        this.telecomFacilitator = telecomFacilitator == null ? null : telecomFacilitator.trim();
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    public String getBusiOpnDate() {
        return busiOpnDate;
    }

    public void setBusiOpnDate(String busiOpnDate) {
        this.busiOpnDate = busiOpnDate == null ? null : busiOpnDate.trim();
    }

    public String getCurPayStat() {
        return curPayStat;
    }

    public void setCurPayStat(String curPayStat) {
        this.curPayStat = curPayStat == null ? null : curPayStat.trim();
    }

    public String getCurDebtAmt() {
        return curDebtAmt;
    }

    public void setCurDebtAmt(String curDebtAmt) {
        this.curDebtAmt = curDebtAmt == null ? null : curDebtAmt.trim();
    }

    public String getCurDebtMonths() {
        return curDebtMonths;
    }

    public void setCurDebtMonths(String curDebtMonths) {
        this.curDebtMonths = curDebtMonths == null ? null : curDebtMonths.trim();
    }

    public String getPrest24mpayStat() {
        return prest24mpayStat;
    }

    public void setPrest24mpayStat(String prest24mpayStat) {
        this.prest24mpayStat = prest24mpayStat == null ? null : prest24mpayStat.trim();
    }

    public String getTallyYm() {
        return tallyYm;
    }

    public void setTallyYm(String tallyYm) {
        this.tallyYm = tallyYm == null ? null : tallyYm.trim();
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