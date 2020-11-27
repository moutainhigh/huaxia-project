package com.huaxia.plaze.ui.fico.domain;

public class FicoBatch {
	// uuid
	private String uuid;
	// 创建时间
	private String crtTime;
	// 创建者
	private String crtUser;
	// 批次编号
	private String batchId;
	// 批次总条数
	private Integer batchRecordSize;
	// 查询类型
	//private String queryType;
	// 员工ID
	private String staffId;
	//员工姓名
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

	public Integer getBatchRecordSize() {
		return batchRecordSize;
	}

	public void setBatchRecordSize(Integer batchRecordSize) {
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
}