package com.huaxia.opas.domain.system;

import java.io.Serializable;
import java.util.Date;

public class ApUserOrg implements Serializable {

	private static final long serialVersionUID = -8887616980514578041L;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 机构ID
	 */
	private String orgId;

	/**
	 * 创建日期
	 */
	private Date crtDate;

	/**
	 * 创建时间
	 */
	private Date crtTime;

	/**
	 * 创建人
	 */
	private String crtUser;

	/**
	 * 最后操作人
	 */
	private String lstUpdUser;

	/**
	 * 最后操作日期
	 */
	private Date lstUpdDate;

	/**
	 * 最后更新时间
	 */
	private Date lstUpdTime;

	/**
	 * 批次日期
	 */
	private Date batchDate;

	/**
	 * 记录状态
	 */
	private String recStatus;

	/**
	 * 保密级别
	 */
	private String scrLevel;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = (userId == null ? null : userId.trim());
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = (orgId == null ? null : orgId.trim());
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = (crtUser == null ? null : crtUser.trim());
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = (lstUpdUser == null ? null : lstUpdUser.trim());
	}

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public Date getLstUpdTime() {
		return lstUpdTime;
	}

	public void setLstUpdTime(Date lstUpdTime) {
		this.lstUpdTime = lstUpdTime;
	}

	public Date getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}

	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = (recStatus == null ? null : recStatus.trim());
	}

	public String getScrLevel() {
		return scrLevel;
	}

	public void setScrLevel(String scrLevel) {
		this.scrLevel = (scrLevel == null ? null : scrLevel.trim());
	}

}
