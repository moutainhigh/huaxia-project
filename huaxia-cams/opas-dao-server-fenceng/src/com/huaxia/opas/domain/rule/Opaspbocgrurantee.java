package com.huaxia.opas.domain.rule;

import java.io.Serializable;
import java.util.Date;

public class Opaspbocgrurantee implements Serializable{
    private String insideAppNo;

    private String guaranteeType;

    private String secLoanOrCreditIssOrg;

    private String loanConAmt;

    private String guarLoanGrantDate;

    private String guarLoanGrantExpDt;

    private String guaranteeAmt;

    private String loanAmt;

    private String guarLoanSort5;

    private String duePaymentDate;

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

    public String getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType == null ? null : guaranteeType.trim();
    }

    public String getSecLoanOrCreditIssOrg() {
        return secLoanOrCreditIssOrg;
    }

    public void setSecLoanOrCreditIssOrg(String secLoanOrCreditIssOrg) {
        this.secLoanOrCreditIssOrg = secLoanOrCreditIssOrg == null ? null : secLoanOrCreditIssOrg.trim();
    }

    public String getLoanConAmt() {
        return loanConAmt;
    }

    public void setLoanConAmt(String loanConAmt) {
        this.loanConAmt = loanConAmt == null ? null : loanConAmt.trim();
    }

    public String getGuarLoanGrantDate() {
        return guarLoanGrantDate;
    }

    public void setGuarLoanGrantDate(String guarLoanGrantDate) {
        this.guarLoanGrantDate = guarLoanGrantDate == null ? null : guarLoanGrantDate.trim();
    }

    public String getGuarLoanGrantExpDt() {
        return guarLoanGrantExpDt;
    }

    public void setGuarLoanGrantExpDt(String guarLoanGrantExpDt) {
        this.guarLoanGrantExpDt = guarLoanGrantExpDt == null ? null : guarLoanGrantExpDt.trim();
    }

    public String getGuaranteeAmt() {
        return guaranteeAmt;
    }

    public void setGuaranteeAmt(String guaranteeAmt) {
        this.guaranteeAmt = guaranteeAmt == null ? null : guaranteeAmt.trim();
    }

    public String getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(String loanAmt) {
        this.loanAmt = loanAmt == null ? null : loanAmt.trim();
    }

    public String getGuarLoanSort5() {
        return guarLoanSort5;
    }

    public void setGuarLoanSort5(String guarLoanSort5) {
        this.guarLoanSort5 = guarLoanSort5 == null ? null : guarLoanSort5.trim();
    }

    public String getDuePaymentDate() {
        return duePaymentDate;
    }

    public void setDuePaymentDate(String duePaymentDate) {
        this.duePaymentDate = duePaymentDate == null ? null : duePaymentDate.trim();
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