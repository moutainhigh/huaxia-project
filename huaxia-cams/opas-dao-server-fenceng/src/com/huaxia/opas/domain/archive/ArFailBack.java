package com.huaxia.opas.domain.archive;

import java.io.Serializable;
import java.util.Date;

public class ArFailBack implements Serializable {

	private static final long serialVersionUID = 4255318443891864573L;

	/**
	 * 申请件编号
	 */
	private String appId;

	/**
	 * 客户姓名
	 */
	private String custName;

	/**
	 * 证件号码
	 */
	private String credNo;

	/**
	 * 附属卡客户姓名
	 */
	private String suppCardCustName;

	/**
	 * 报错原因
	 */
	private String reason;

	/**
	 * 操作员
	 */
	private String operator;

	/**
	 * 最后操作时间
	 */
	private Date operatTime;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCredNo() {
		return credNo;
	}

	public void setCredNo(String credNo) {
		this.credNo = credNo;
	}

	public String getSuppCardCustName() {
		return suppCardCustName;
	}

	public void setSuppCardCustName(String suppCardCustName) {
		this.suppCardCustName = suppCardCustName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

}
