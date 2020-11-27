package com.huaxia.plaze.ui.unicom.domain;

import org.apache.ibatis.type.Alias;

@Alias("PhoneQueryLog")
public class PhoneQueryLog {

	private String uuid;
	private String crtTime;
	private String crtUser;
	private String logId;
	private String trnId;
	private String queryMode;
	private String queryReason;
	private String queryResult;
	private String staffName;
	private String staffCertNo;
	private String certType;
	private String certNo;
	private String name;
	private String mobile;
	private String ip;
	private String mac;
	private String compay;

	public String getCompay() {
		return compay;
	}

	public void setCompay(String compay) {
		this.compay = compay;
	}

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

	public String getQueryReason() {
		return queryReason;
	}

	public void setQueryReason(String queryReason) {
		this.queryReason = queryReason;
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

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	@Override
	public String toString() {
		return "PhoneQueryLog [uuid=" + uuid + ", crtTime=" + crtTime + ", crtUser=" + crtUser + ", logId=" + logId
				+ ", trnId=" + trnId + ", queryMode=" + queryMode + ", queryReason=" + queryReason + ", queryResult="
				+ queryResult + ", staffName=" + staffName + ", staffCertNo=" + staffCertNo + ", certType=" + certType
				+ ", certNo=" + certNo + ", name=" + name + ", mobile=" + mobile + ", ip=" + ip + ", mac=" + mac + "]";
	}

}
