package com.huaxia.plaze.ui.id5.domain;

import org.apache.ibatis.type.Alias;

@Alias("EducationBatch")
public class EducationBatch {

	// 记录编号
	private String uuid;

	// 创建时间
	private String crtTime;

	// 创建用户
	private String crtUser;

	// 业务主键
	private String batchId;

	// 批次文件总条数
	private String batchRecordSize;

	// 冗余字段
	private String staffId;

	private String staffName;

	// 查询人证件号
	private String certNo;

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

	public String getBatchRecordSize() {
		return batchRecordSize;
	}

	public void setBatchRecordSize(String batchRecordSize) {
		this.batchRecordSize = batchRecordSize;
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

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

}
