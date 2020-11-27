package com.huaxia.opas.domain.system;

import java.io.Serializable;
import java.util.Date;

public class ApRole implements Serializable {

	private static final long serialVersionUID = 4399639542992242651L;

	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色描述
	 */
	private String roleDesc;

	/**
	 * 启用状态,0-停用1-启用
	 */
	private String status;
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
	 * 默认节点编号
	 */
	private String defaultNodeNo;

	/**
	 * 角色代码
	 */
	private String roleCode;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = (roleId == null ? null : roleId.trim());
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = (roleName == null ? null : roleName.trim());
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = (roleDesc == null ? null : roleDesc.trim());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = (status == null ? null : status.trim());
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

	public String getDefaultNodeNo() {
		return defaultNodeNo;
	}

	public void setDefaultNodeNo(String defaultNodeNum) {
		this.defaultNodeNo = (defaultNodeNum == null ? null : defaultNodeNum.trim());
	}

	@Override
	public String toString() {
		return "ApRole [status=" + status + "]";
	}

}
