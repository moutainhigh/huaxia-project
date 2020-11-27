package com.huaxia.opas.domain.thirdparty;

import java.util.Date;

public class TaxArrear {
    private String insideAppNo;//内审编号

    private String taxOrgName;//主管税务机关

    private String outstdTaxesAmtSum;//欠税总额

    private String outstdTaxStatDt;//欠税统计日期

    private String crtTime;//

    private String crtUser;//

    private Date lstUpdTime;//

    private String lstUpdUser;//

    private Date batchDate;//

    private String recStatus;//

    private String appId;

    public String getInsideAppNo() {
        return insideAppNo;
    }

    public void setInsideAppNo(String insideAppNo) {
        this.insideAppNo = insideAppNo == null ? null : insideAppNo.trim();
    }

    public String getTaxOrgName() {
        return taxOrgName;
    }

    public void setTaxOrgName(String taxOrgName) {
        this.taxOrgName = taxOrgName == null ? null : taxOrgName.trim();
    }

    public String getOutstdTaxesAmtSum() {
        return outstdTaxesAmtSum;
    }

    public void setOutstdTaxesAmtSum(String outstdTaxesAmtSum) {
        this.outstdTaxesAmtSum = outstdTaxesAmtSum == null ? null : outstdTaxesAmtSum.trim();
    }

    public String getOutstdTaxStatDt() {
        return outstdTaxStatDt;
    }

    public void setOutstdTaxStatDt(String outstdTaxStatDt) {
        this.outstdTaxStatDt = outstdTaxStatDt == null ? null : outstdTaxStatDt.trim();
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