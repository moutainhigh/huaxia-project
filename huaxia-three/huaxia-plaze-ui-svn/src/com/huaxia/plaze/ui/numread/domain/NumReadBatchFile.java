package com.huaxia.plaze.ui.numread.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("NumReadBatchFile")
public class NumReadBatchFile {
	
	//记录编号
	private String uuid;
	//创建时间
	private String crtTime;
	//创建用户
	private String crtUser;
	//批次号
	private String batchNo;
	//文件编号
	private String batchFileId;
	//文件名
	private String batchFileName;
	//文件总条数
	private int batchFileRecordSize;
	//查询状态
	private String queryStatus;
	//操作员工编号
	private String staffId;
	//未开始查询
	private int status0;
	//查询完成
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
	public int getBatchFileRecordSize() {
		return batchFileRecordSize;
	}
	public void setBatchFileRecordSize(int batchFileRecordSize) {
		this.batchFileRecordSize = batchFileRecordSize;
	}
}
