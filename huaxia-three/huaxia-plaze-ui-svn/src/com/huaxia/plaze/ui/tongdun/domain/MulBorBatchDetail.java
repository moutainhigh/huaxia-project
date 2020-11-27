package com.huaxia.plaze.ui.tongdun.domain;

import java.util.Date;

/**
 *批次文件表
 * @author liuwei
 *
 */
public class MulBorBatchDetail {
	// uuid
	private String uuid;
	// 创建时间
	private String crtTime;
	// 创建者
	private String crtUser;
	//批次文件编号
	private String batchFileId;
	//交易编号
	private String trnId;
	//证件类型
	private String certType;
	//证件号码
	private String certNo;
	//姓名
	private String name;
	//手机号码
	private String mobile;
	//复核状态
	private String queryStatus;
	//查询时间
	private String queryTime;
	
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
	public String getBatchFileId() {
		return batchFileId;
	}
	public void setBatchFileId(String batchFileId) {
		this.batchFileId = batchFileId;
	}
	public String getTrnId() {
		return trnId;
	}
	public void setTrnId(String trnId) {
		this.trnId = trnId;
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
	public String getQueryStatus() {
		return queryStatus;
	}
	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	@Override
	public String toString() {
		return "MulBorTrnBatchDetail [uuid=" + uuid + ", crtTime=" + crtTime + ", crtUser=" + crtUser + ", batchFileId="
				+ batchFileId + ", trnId=" + trnId + ", certType=" + certType + ", certNo=" + certNo + ", name=" + name
				+ ", mobile=" + mobile + ", queryStatus=" + queryStatus + ", queryTime=" + queryTime + "]";
	}

}