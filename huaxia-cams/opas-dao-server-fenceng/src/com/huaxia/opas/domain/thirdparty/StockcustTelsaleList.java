package com.huaxia.opas.domain.thirdparty;

import java.io.Serializable;
import java.util.Date;

public class StockcustTelsaleList implements Serializable{
    private String autoId;

    private String acctNo;

    private String certifiNo;

    private String custName;

    private Long telsaleLimit;

    private String adjustProcess;

    private Long peoplebankScore;

    private String operator;

    private Date operatTime;

    private String currStatus;

    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId == null ? null : autoId.trim();
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
    }

    public String getCertifiNo() {
        return certifiNo;
    }

    public void setCertifiNo(String certifiNo) {
        this.certifiNo = certifiNo == null ? null : certifiNo.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public Long getTelsaleLimit() {
        return telsaleLimit;
    }

    public void setTelsaleLimit(Long telsaleLimit) {
        this.telsaleLimit = telsaleLimit;
    }

    public String getAdjustProcess() {
        return adjustProcess;
    }

    public void setAdjustProcess(String adjustProcess) {
        this.adjustProcess = adjustProcess == null ? null : adjustProcess.trim();
    }

    public Long getPeoplebankScore() {
        return peoplebankScore;
    }

    public void setPeoplebankScore(Long peoplebankScore) {
        this.peoplebankScore = peoplebankScore;
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
}