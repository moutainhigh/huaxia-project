package com.huaxia.opas.domain.riskcheck;

import java.io.Serializable;
import java.util.Date;

public class TelRiskListHistory implements Serializable {

	private static final long serialVersionUID = -77431681612688627L;

	private String Id;

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

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId == null ? null : autoId.trim();
	}

	public String getMobileId() {
		return mobileId;
	}

	public void setMobileId(String mobileId) {
		this.mobileId = mobileId == null ? null : mobileId.trim();
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel == null ? null : companyTel.trim();
	}

	public String getLivingTel() {
		return livingTel;
	}

	public void setLivingTel(String livingTel) {
		this.livingTel = livingTel == null ? null : livingTel.trim();
	}

	public String getOtherTel() {
		return otherTel;
	}

	public void setOtherTel(String otherTel) {
		this.otherTel = otherTel == null ? null : otherTel.trim();
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

	public String getUserOperator() {
		return userOperator;
	}

	public void setUserOperator(String userOperator) {
		this.userOperator = userOperator == null ? null : userOperator.trim();
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