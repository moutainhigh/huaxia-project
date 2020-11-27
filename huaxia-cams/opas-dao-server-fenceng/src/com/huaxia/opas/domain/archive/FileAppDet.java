package com.huaxia.opas.domain.archive;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FileAppDet implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5594477833574999951L;

	private String appId;

    private String custName;

    private String credNo;

    private String operator;

    private Date operatTime;

    private String spreadorganCode;

    private BigDecimal inDays;

    private String appResult;

    private String status;

    private String fileNo;

    private String alreadySendFlag;

    private Date decTime;

    private Date toArchiveTime;

    private String applyCard;

    private String approveCreditProduct;

    private String aprroveWay;

    private String quickCardInterfaceFlag;

    private String flag;

    private String ydjFlag;

    private String interface4092InvokeFlag;

    private String interface4093InvokeFlag;
    
    private String huFlag;
    
    private String backFlag;
    
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

    public void setAprroveWay(String aprroveWay) {
        this.aprroveWay = aprroveWay == null ? null : aprroveWay.trim();
    }

    public String getQuickCardInterfaceFlag() {
        return quickCardInterfaceFlag;
    }

    public void setQuickCardInterfaceFlag(String quickCardInterfaceFlag) {
        this.quickCardInterfaceFlag = quickCardInterfaceFlag == null ? null : quickCardInterfaceFlag.trim();
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

    public String getInterface4092InvokeFlag() {
        return interface4092InvokeFlag;
    }

    public void setInterface4092InvokeFlag(String interface4092InvokeFlag) {
        this.interface4092InvokeFlag = interface4092InvokeFlag == null ? null : interface4092InvokeFlag.trim();
    }

    public String getInterface4093InvokeFlag() {
        return interface4093InvokeFlag;
    }

    public void setInterface4093InvokeFlag(String interface4093InvokeFlag) {
        this.interface4093InvokeFlag = interface4093InvokeFlag == null ? null : interface4093InvokeFlag.trim();
    }

	public String getHuFlag() {
		return huFlag;
	}

	public void setHuFlag(String huFlag) {
		this.huFlag = huFlag;
	}

	public String getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}

	public String getBackFlag() {
		return backFlag;
	}

	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}
    
	
}