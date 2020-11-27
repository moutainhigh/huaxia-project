package com.huaxia.plaze.ui.id5.domain;

import org.apache.ibatis.type.Alias;

@Alias("EducationBatchDetail")
public class EducationBatchDetail {

	// 记录编号
	private String uuid;
	
	// 创建时间
	private String crtTime;
	
	// 创建用户
	private String crtUser;
	
	// 交易编号
	private String trnId;
	
	// 关联导入文件表的外键
	private String batchFileId;
	
	// 姓名
	private String name;
	
	// 证件号码
	private String certNo;
	
	// 查询状态
	private String queryStatus;
	
	// 查询时间
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

	public String getTrnId() {
		return trnId;
	}

	public void setTrnId(String trnId) {
		this.trnId = trnId;
	}

	public String getBatchFileId() {
		return batchFileId;
	}

	public void setBatchFileId(String batchFileId) {
		this.batchFileId = batchFileId;
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

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

}
