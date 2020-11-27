package com.huaxia.plaze.ui.id5.domain;

import org.apache.ibatis.type.Alias;

@Alias("EducationBatchFile")
public class EducationBatchFile {

	// 记录编号
	private String uuid;
	
	// 创建时间
	private String crtTime;
	
	// 创建用户
	private String crtUser;
	
	// 业务主键
	private String batchId;
	
	// 批次文件编号（业务主键）
	private String batchFileId;
	
	// 证件号码
	private String batchFileName;
	
	// 批次文件总条数
	private String batchFileRecordSize;
	
	// 查询状态
	private String queryStatus;

	// 冗余字段
	private String staffId;
	
	private String staffName;

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

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBatchFileId() {
		return batchFileId;
	}

	public void setBatchFileId(String batchFileId) {
		this.batchFileId = batchFileId;
	}

	public String getBatchFileName() {
		return batchFileName;
	}

	public void setBatchFileName(String batchFileName) {
		this.batchFileName = batchFileName;
	}

	public String getBatchFileRecordSize() {
		return batchFileRecordSize;
	}

	public void setBatchFileRecordSize(String batchFileRecordSize) {
		this.batchFileRecordSize = batchFileRecordSize;
	}

	public String getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

}
