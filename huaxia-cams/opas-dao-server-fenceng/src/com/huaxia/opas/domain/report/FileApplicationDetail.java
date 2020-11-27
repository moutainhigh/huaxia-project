package com.huaxia.opas.domain.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FileApplicationDetail implements Serializable{
 
	private static final long serialVersionUID = 674271584564642962L;

	private String appId;//申请件编号

    private String custName;//客户姓名

    private String credNo;//证件号码

    private String operator;//最后操作员

    private Date operatTime;//最后操作时间

    private String spreadorganCode;//推广机构代码

    private BigDecimal inDays;//进入系统天数

    private String appResult;//申请件结果

    private String status;//状态

    private String fileNo;//归档编号

    private String alreadySendFlag;//是否已送过发卡标识

    private Date decTime;//归档2小时时间

    private Date toArchiveTime;//进入待归档时间

    private String applyCard;//申请卡种
    
    private String approveCreditProduct;//审批授信产品

    private String aprroveWay;//审批方式
    
    private String flag;

    private String ydjFlag;//易达金标识
    
    private String smsFlag;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getCredNo() {
        return credNo;
    }

    public void setCredNo(String credNo) {
        this.credNo = credNo == null ? null : credNo.trim();
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

    public String getSpreadorganCode() {
        return spreadorganCode;
    }

    public void setSpreadorganCode(String spreadorganCode) {
        this.spreadorganCode = spreadorganCode == null ? null : spreadorganCode.trim();
    }

    public BigDecimal getInDays() {
        return inDays;
    }

    public void setInDays(BigDecimal inDays) {
        this.inDays = inDays;
    }

    public String getAppResult() {
        return appResult;
    }

    public void setAppResult(String appResult) {
        this.appResult = appResult == null ? null : appResult.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo == null ? null : fileNo.trim();
    }

    public String getAlreadySendFlag() {
        return alreadySendFlag;
    }

    public void setAlreadySendFlag(String alreadySendFlag) {
        this.alreadySendFlag = alreadySendFlag == null ? null : alreadySendFlag.trim();
    }

    public Date getDecTime() {
        return decTime;
    }

    public void setDecTime(Date decTime) {
        this.decTime = decTime;
    }

    public Date getToArchiveTime() {
        return toArchiveTime;
    }

    public void setToArchiveTime(Date toArchiveTime) {
        this.toArchiveTime = toArchiveTime;
    }

    public String getApplyCard() {
        return applyCard;
    }

    public void setApplyCard(String applyCard) {
        this.applyCard = applyCard == null ? null : applyCard.trim();
    }
    public String getApproveCreditProduct() {
        return approveCreditProduct;
    }

    public void setApproveCreditProduct(String approveCreditProduct) {
        this.approveCreditProduct = approveCreditProduct == null ? null : approveCreditProduct.trim();
    }

    public String getAprroveWay() {
        return aprroveWay;
    }

    public String getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}

	public void setAprroveWay(String aprroveWay) {
        this.aprroveWay = aprroveWay == null ? null : aprroveWay.trim();
    }
    
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getYdjFlag() {
        return ydjFlag;
    }

    public void setYdjFlag(String ydjFlag) {
        this.ydjFlag = ydjFlag == null ? null : ydjFlag.trim();
    }
    
    
}