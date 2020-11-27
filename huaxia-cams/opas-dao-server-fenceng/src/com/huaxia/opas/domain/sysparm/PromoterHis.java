package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

public class PromoterHis implements Serializable {
	private static final long serialVersionUID = -3624431232940950571L;
	//主键Id
	private String keyId;
	//实时表主键Id
	private String autoId;
	//姓名
	private String promoterName;
	//推广员编号
	private String promoterNo;
	//证件类型
	private String certifiType;
	//证件编号
	private String certifiNo;
	//手机号
	private String mobileNo;
	//信息来源
	private String infoChannel;
	//欺诈类型
	private String fraudType;
	//备注
	private String memo;
	//失效时间
	private Date invalidTime;
	//添加时间
	private Date createTime;
	//操作用户
	private String operator;
	//操作时间
	private Date operatTime;
	//当前状态
	private String currStatus;
	//操作动作
	private String operation;
	
	public PromoterHis() {
		super();
	}

	public PromoterHis(String keyId, String autoId, String promoterName, String promoterNo, String certifiType,
			String certifiNo, String mobileNo, String infoChannel, String fraudType, String memo, Date invalidTime,
			Date createTime, String operator, Date operatTime, String currStatus, String operation) {
		super();
		this.keyId = keyId;
		this.autoId = autoId;
		this.promoterName = promoterName;
		this.promoterNo = promoterNo;
		this.certifiType = certifiType;
		this.certifiNo = certifiNo;
		this.mobileNo = mobileNo;
		this.infoChannel = infoChannel;
		this.fraudType = fraudType;
		this.memo = memo;
		this.invalidTime = invalidTime;
		this.createTime = createTime;
		this.operator = operator;
		this.operatTime = operatTime;
		this.currStatus = currStatus;
		this.operation = operation;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId == null ? null : keyId.trim();
	}

	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId == null ? null : autoId.trim();
	}
	public String getPromoterName() {
		return promoterName;
	}
	public void setPromoterName(String promoterName) {
		this.promoterName = promoterName == null ? null : promoterName.trim();
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
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation == null ? null : operation.trim();
	}
}
