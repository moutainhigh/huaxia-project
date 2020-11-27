package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

public class MajorschoolList implements Serializable {

	private static final long serialVersionUID = 6869311610554872532L;

	private String autoId;

	private String area;

	private String majorschoolName;

	private String highschoolType;

	private String operator;

	private Date operatTime;

	private String currStatus;

	private Date createTime;

	private String orderByNo;
	public String getOrderByNo() {
		return orderByNo;
	}

	public void setOrderByNo(String orderByNo) {
		this.orderByNo = orderByNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId == null ? null : autoId.trim();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	public String getMajorschoolName() {
		return majorschoolName;
	}

	public void setMajorschoolName(String majorschoolName) {
		this.majorschoolName = majorschoolName == null ? null : majorschoolName.trim();
	}

	public String getHighschoolType() {
		return highschoolType;
	}

	public void setHighschoolType(String highschoolType) {
		this.highschoolType = highschoolType == null ? null : highschoolType.trim();
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