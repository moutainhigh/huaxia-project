package com.huaxia.plaze.ui.pboc.domain;

import org.apache.ibatis.type.Alias;

@Alias("BatchQueryReview")
public class BatchQueryReview {

	// 记录编号
	private String uuid;
	// 创建时间
	private String crtTime;
	// 创建用户
	private String crtUser;
	// 批次号
	private String batchId;
	// 文件记录ID（业务主键）
	private String fileId;
	// 文件姓名
	private String fileName;
	// 总条数
	private String totalRecord;
	//复核状态（复核状态 0-待复核 1-复核通过-2 正在查询 3-查询成功 4-查询失败5）
	private String reviewStatus;
	//退回原因
	private String refuseReason;
	//退回时间
	private String refuseTime;
	//查询类型
	private String queryType;

	// 冗余字段
	// 用户工号
	private String staffId;
	// 查询人名字
	private String staffName;
	//查询人证件号
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

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}
    
	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	
	public String getRefuseTime() {
		return refuseTime;
	}

	public void setRefuseTime(String refuseTime) {
		this.refuseTime = refuseTime;
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

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	
}
