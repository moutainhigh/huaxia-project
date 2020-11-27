package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 汪涛
 *
 */
public class SameIndustryRisk implements Serializable{
	private static final long serialVersionUID = -1041680798511814623L;
	/**
	 * 每一条操作历史记录的唯一ID
	 */
	private String id;
	
	/**
	 * 唯一ID
	 */
	private String auto_id;
	
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 证件类型
	 */
	private String certifiType;
	/**
	 * 证件号码
	 */
	private String certifiNo;
	/**
	 * 报送行
	 */
	private String submitBank;
	/**
	 * 星级
	 */
	private String starLevle;
	/**
	 * 信息来源
	 */
	private String infoChannel;
	/**
	 * 欺诈类型
	 */
	private String fraudType;
	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 失效时间
	 */
	private Date invalidTime;
	/**
	 * 添加时间
	 */
	private Date createTime;
	/**
	 * 操作类型
	 */
	private String operatType;

	/**
	 * 操作用户
	 */
	private String operator;
	/**
	 * 操作时间
	 */
	private Date operatTime;
	/**
	 * 当前状态
	 */
	private String currStatus;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuto_id() {
		return auto_id;
	}
	public void setAuto_id(String auto_id) {
		this.auto_id = auto_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCertifiType() {
		return certifiType;
	}
	public void setCertifiType(String certifiType) {
		this.certifiType = certifiType;
	}
	public String getCertifiNo() {
		return certifiNo;
	}
	public void setCertifiNo(String certifiNo) {
		this.certifiNo = certifiNo;
	}
	public String getSubmitBank() {
		return submitBank;
	}
	public void setSubmitBank(String submitBank) {
		this.submitBank = submitBank;
	}
	public String getStarLevle() {
		return starLevle;
	}
	public void setStarLevle(String starLevle) {
		this.starLevle = starLevle;
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
	public String getOperatType() {
		return operatType;
	}
	public void setOperatType(String operatType) {
		this.operatType = operatType;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
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
	@Override
	public String toString() {
		return "SameIndustryRisk [id=" + id + ", auto_id=" + auto_id + ", name=" + name + ", certifiType=" + certifiType
				+ ", certifiNo=" + certifiNo + ", submitBank=" + submitBank + ", starLevle=" + starLevle
				+ ", infoChannel=" + infoChannel + ", fraudType=" + fraudType + ", memo=" + memo + ", invalidTime="
				+ invalidTime + ", createTime=" + createTime + ", operatType=" + operatType + ", operator=" + operator
				+ ", operatTime=" + operatTime + ", currStatus=" + currStatus + "]";
	}
	
	
}
