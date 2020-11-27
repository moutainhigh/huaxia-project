package com.huaxia.opas.domain.riskcheck;

import java.io.Serializable;
import java.util.Date;

public class TelRiskList implements Serializable{
	
	private static final long serialVersionUID = 5913990026577457067L;

	private String autoId;

    private String mobileId;

    private String companyTel;

    private String livingTel;

    private String otherTel;

    private String infoChannel;

    private String fraudType;

    private String memo;

    private Date invalidTime;

    private Date createTime;

    private String userOperator;

    private Date operatTime;

    private String currStatus;
    

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getMobileId() {
		return mobileId;
	}

	public void setMobileId(String mobileId) {
		this.mobileId = mobileId;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public String getLivingTel() {
		return livingTel;
	}

	public void setLivingTel(String livingTel) {
		this.livingTel = livingTel;
	}

	public String getOtherTel() {
		return otherTel;
	}

	public void setOtherTel(String otherTel) {
		this.otherTel = otherTel;
	}

	public String getInfoChannel() {
		return infoChannel;
	}

	public void setInfoChannel(String infoChannel) {
		this.infoChannel = infoChannel;
	}

	public String getFraudType() {
		return fraudType;
	}

	public void setFraudType(String fraudType) {
		this.fraudType = fraudType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getUserOperator() {
		return userOperator;
	}

	public void setUserOperator(String userOperator) {
		this.userOperator = userOperator;
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
		this.currStatus = currStatus;
	}
}