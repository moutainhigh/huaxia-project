package com.huaxia.plaze.ui.pboc.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("SingleQueryReview")
public class SingleQueryReview {

	// 记录编号
	private String uuid;
	// 创建时间
	private String crtTime;
	// 创建用户
	private String crtUser;
	// 最后修改时间
	private Date updTime;
	// 最后修改用户
	private String updUser;
	// 姓名
	private String name;
	// 证件类型
	private String certType;
	// 证件号码
	private String certNo;
	// 手机号
	private String mobile;
	// 查询类型
	private String queryType;
	// 查询原因
	private String queryReason;
	// 文件名称
	private String fileId;
	// 文件路径
	private String sourceId;
	// 拒绝原因
	private String refuseReason;
	// 驳回时间
	private String refuseTime;
	// 复核状态（复核状态 0-待复核 1-复核通过-2 正在查询 3-查询成功 4-查询失败5）
	private String reviewStatus;
	// 复核时间
	private String reviewTime;
	// 查询来源 0-单条 1-批量
	private String queryFrom;
	private String trnId;

	// 冗余字段
	// 用户工号
	private String staffId;
	// 查询人名字
	private String staffName;
	// 查询人证件号
	private String staffCert;
	
	private String imageMatch;

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

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getUpdUser() {
		return updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryReason() {
		return queryReason;
	}

	public void setQueryReason(String queryReason) {
		this.queryReason = queryReason;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getRefuseTime() {
		return refuseTime;
	}

	public void setRefuseTime(String refuseTime) {
		this.refuseTime = refuseTime;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getQueryFrom() {
		return queryFrom;
	}

	public void setQueryFrom(String queryFrom) {
		this.queryFrom = queryFrom;
	}

	public String getTrnId() {
		return trnId;
	}

	public void setTrnId(String trnId) {
		this.trnId = trnId;
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

	public String getStaffCert() {
		return staffCert;
	}

	public void setStaffCert(String staffCert) {
		this.staffCert = staffCert;
	}

	public String getImageMatch() {
		return imageMatch;
	}

	public void setImageMatch(String imageMatch) {
		this.imageMatch = imageMatch;
	}

}
