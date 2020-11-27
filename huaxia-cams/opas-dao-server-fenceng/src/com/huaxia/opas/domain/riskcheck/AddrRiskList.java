package com.huaxia.opas.domain.riskcheck;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AddrRiskList implements Serializable {

	private static final long serialVersionUID = -2815544286378386828L;

	private String autoId;

	private String companyAddr;

	private String livingAddr;

	private String bornAddr;

	private String billAddr;

	private String otherAddr;

	private String fraudType;

	private String infoChannel;

	private String memo;

	private Date invalidTime;

	private Date createTime;

	private String userOperator;

	private Date operatTime;

	private String currStatus;

	private List<String> ids;

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId == null ? null : autoId.trim();
	}

	public String getCompanyAddr() {
		return companyAddr;
	}

	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr == null ? null : companyAddr.trim();
	}

	public String getLivingAddr() {
		return livingAddr;
	}

	public void setLivingAddr(String livingAddr) {
		this.livingAddr = livingAddr == null ? null : livingAddr.trim();
	}

	public String getBornAddr() {
		return bornAddr;
	}

	public void setBornAddr(String bornAddr) {
		this.bornAddr = bornAddr == null ? null : bornAddr.trim();
	}

	public String getBillAddr() {
		return billAddr;
	}

	public void setBillAddr(String billAddr) {
		this.billAddr = billAddr == null ? null : billAddr.trim();
	}

	public String getOtherAddr() {
		return otherAddr;
	}

	public void setOtherAddr(String otherAddr) {
		this.otherAddr = otherAddr == null ? null : otherAddr.trim();
	}

	public String getFraudType() {
		return fraudType;
	}

	public void setFraudType(String fraudType) {
		this.fraudType = fraudType == null ? null : fraudType.trim();
	}

	public String getInfoChannel() {
		return infoChannel;
	}

	public void setInfoChannel(String infoChannel) {
		this.infoChannel = infoChannel == null ? null : infoChannel.trim();
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