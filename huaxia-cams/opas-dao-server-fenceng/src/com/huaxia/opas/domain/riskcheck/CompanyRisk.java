package com.huaxia.opas.domain.riskcheck;

import java.io.Serializable;
import java.util.Date;

public class CompanyRisk implements Serializable {
	private static final long serialVersionUID = -6967004662668870854L;

	private String autoId;

	private String companyName;

	private String infoChannel;

	private String fraudType;

	private String memo;

	private Date invalidTime;

	private Date createTime;

	private String operator;

	private Date operatTime;

	private String currStatus;

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId == null ? null : autoId.trim();
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName == null ? null : companyName.trim();
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

}
