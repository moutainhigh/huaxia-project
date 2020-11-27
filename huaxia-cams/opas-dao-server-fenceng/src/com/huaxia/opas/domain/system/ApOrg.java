package com.huaxia.opas.domain.system;

import java.io.Serializable;
import java.util.Date;

public class ApOrg implements Serializable {

	private static final long serialVersionUID = -6587699178108174475L;

	/**
	 * 机构ID
	 */
	private String orgId;

	/**
	 * 机构名称
	 */
	private String orgName;

	/**
	 * 机构地址
	 */
	private String orgAddr;

	/**
	 * 机构级别
	 */
	private String orgLevel;

	/**
	 * 启用状态,0-停用1-启用
	 */
	private String status;

	/**
	 * 邮编
	 */
	private String zipcode;

	/**
	 * 联系人
	 */
	private String linkMan;

	/**
	 * 联系电话
	 */
	private String linkTel;

	/**
	 * 电子邮件
	 */
	private String email;

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

	/**
	 * 机构代码
	 * 
	 * @return
	 */
	private String orgNo;

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = (orgId == null ? null : orgId.trim());
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = (orgName == null ? null : orgName.trim());
	}

	public String getOrgAddr() {
		return orgAddr;
	}

	public void setOrgAddr(String orgAddr) {
		this.orgAddr = (orgAddr == null ? null : orgAddr.trim());
	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = (orgLevel == null ? null : orgLevel.trim());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = (status == null ? null : status.trim());
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = (zipcode == null ? null : zipcode.trim());
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = (linkMan == null ? null : linkMan.trim());
	}

	public String getLinkTel() {
		return linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = (linkTel == null ? null : linkTel.trim());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = (email == null ? null : email.trim());
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
