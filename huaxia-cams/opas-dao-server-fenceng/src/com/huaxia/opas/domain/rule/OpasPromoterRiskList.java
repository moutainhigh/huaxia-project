package com.huaxia.opas.domain.rule;

import java.io.Serializable;
import java.util.Date;

public class OpasPromoterRiskList implements Serializable{
    private String autoId;

    private String promoterNo;

    private String certifiType;

    private String certifiNo;

    private String mobileNo;

    private String infoChannel;

    private String fraudType;

    private String memo;

    private Date invalidTime;

    private Date createTime;

    private String operator;

    private Date operatTime;

    private String currStatus;

    private String promoterName;

    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId == null ? null : autoId.trim();
    }

    public String getPromoterNo() {
        return promoterNo;
    }

    public void setPromoterNo(String promoterNo) {
        this.promoterNo = promoterNo == null ? null : promoterNo.trim();
    }

    public String getCertifiType() {
        return certifiType;
    }

    public void setCertifiType(String certifiType) {
        this.certifiType = certifiType == null ? null : certifiType.trim();
    }

    public String getCertifiNo() {
        return certifiNo;
    }

    public void setCertifiNo(String certifiNo) {
        this.certifiNo = certifiNo == null ? null : certifiNo.trim();
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo == null ? null : mobileNo.trim();
    }

    public String getInfoChannel() {
        return infoChannel;
    }

    public void setInfoChannel(String infoChannel) {
        this.infoChannel = infoChannel == null ? null : infoChannel.trim();
    }

    public String getFraudType() {
        return fraudType;
    }

    public void setFraudType(String fraudType) {
        this.fraudType = fraudType == null ? null : fraudType.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperatTime() {
        return operatTime;
    }

    public void setOperatTime(Date operatTime) {
        this.operatTime = operatTime;
    }

    public String getCurrStatus() {
        return currStatus;
    }

    public void setCurrStatus(String currStatus) {
        this.currStatus = currStatus == null ? null : currStatus.trim();
    }

    public String getPromoterName() {
        return promoterName;
    }

    public void setPromoterName(String promoterName) {
        this.promoterName = promoterName == null ? null : promoterName.trim();
    }
}