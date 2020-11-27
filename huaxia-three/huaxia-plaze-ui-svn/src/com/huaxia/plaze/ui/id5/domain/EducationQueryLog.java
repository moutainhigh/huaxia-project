package com.huaxia.plaze.ui.id5.domain;


import org.apache.ibatis.type.Alias;

@Alias("EducationQueryLog")
public class EducationQueryLog {

	// 记录编号
	private String uuid;
	// 创建时间
	private String crtTime;
	// 创建用户
	private String crtUser;
	//业务主键
	private String logId;
	// 批次文件总条数
	private String trnId;
	private String queryMode;
	private String queryResult;
	private String staffName;
	private String staffCertNo;
	private String certNo;
	private String name;
	private String ip;
	private String mac;
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
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getTrnId() {
		return trnId;
	}
	public void setTrnId(String trnId) {
		this.trnId = trnId;
	}
	public String getQueryMode() {
		return queryMode;
	}
	public void setQueryMode(String queryMode) {
		this.queryMode = queryMode;
	}
	public String getQueryResult() {
		return queryResult;
	}
	public void setQueryResult(String queryResult) {
		this.queryResult = queryResult;
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
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	

	
	

}
