package com.huaxia.plaze.ui.numread.domain;

import org.apache.ibatis.type.Alias;

@Alias("NumReadBatch")
public class NumReadBatch {
	
	// 记录编号
	private String uuid;
	// 创建时间
	private String crtTime;
	// 创建用户
	private String crtUser;
	//批次号
	private String batchNo;
	//总条数
	private int batchRecordSize;
	//操作人员工号
	private String staffId;
	//查询状态
	private String status;
	//未发起查询
	private int status0;
	//查询成功
	private int status3;
	
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
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public int getBatchRecordSize() {
		return batchRecordSize;
	}
	public void setBatchRecordSize(int batchRecordSize) {
		this.batchRecordSize = batchRecordSize;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public int getStatus0() {
		return status0;
	}
	public void setStatus0(int status0) {
		this.status0 = status0;
	}
	public int getStatus3() {
		return status3;
	}
	public void setStatus3(int status3) {
		this.status3 = status3;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
