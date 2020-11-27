package com.huaxia.plaze.ui.unicom.domain;

import org.apache.ibatis.type.Alias;

@Alias("PhoneHistory")
public class PhoneHistory {

	private String trnId;

	private String name;
	private String certNo;
	private String mobile;
	private String operateTime;
	private String checkResult;
	private String status;
	private String crtTime;
	private String code;
	private String company;

	// 多余字段
	private String staffId;
	private String queryStatus;

	public String getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}

	public String getTrnId() {
		return trnId;
	}

	public void setTrnId(String trnId) {
		this.trnId = trnId;
	}

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "PhoneTrnHistory [trnId=" + trnId + ", name=" + name + ", certNo=" + certNo + ", mobile=" + mobile
				+ ", operateTime=" + operateTime + ", crtTime=" + crtTime + ", code=" + code + ", company="
				+ company + ", staffId=" + staffId + ", queryStatus=" + queryStatus + "]";
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
