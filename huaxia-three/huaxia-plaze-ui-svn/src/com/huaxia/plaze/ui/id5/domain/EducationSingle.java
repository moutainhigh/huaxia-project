package com.huaxia.plaze.ui.id5.domain;

import org.apache.ibatis.type.Alias;

@Alias("EducationSingle")
public class EducationSingle {

	// 记录编号
	private String uuid;
	
	// 创建时间
	private String crtTime;
	
	// 创建用户
	private String crtUser;
	
	// 业务主键
	private String trnId;
	
	// 姓名
	private String name;
	
	// 证件号码
	private String certNo;
	
	// 手机号
	private String queryStatus;
	
	// 冗余字段
	private String staffName;
	
	private String staffCertNo;
	
	private String ip;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getTrnId() {
		return trnId;
	}

	public void setTrnId(String trnId) {
		this.trnId = trnId;
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

	public String getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffCertNo() {
		return staffCertNo;
	}

	public void setStaffCertNo(String staffCertNo) {
		this.staffCertNo = staffCertNo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
