package com.huaxia.opas.domain.search;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统参数--一二级分行代码实体类
 * 
 * @author Administrator
 *
 */
public class BranchLevelParam implements Serializable {

	private static final long serialVersionUID = -6059417121299025686L;

	/**
	 * 省份ID
	 */
	private String provId;

	/**
	 * 分行父级ID
	 */
	private String branchParentId;

	/**
	 * 分行层级
	 */
	private String branchLevel;

	/**
	 * 分行码
	 */
	private String branchCode;

	/**
	 * 分行名
	 */
	private String branchName;

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
	 * 分行审批机构代码
	 */
	private String branchAppOrgCode;

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = (provId == null ? null : provId.trim());
	}

	public String getBranchParentId() {
		return branchParentId;
	}

	public void setBranchParentId(String branchParentId) {
		this.branchParentId = (branchParentId == null ? null : branchParentId
				.trim());
	}

	public String getBranchLevel() {
		return branchLevel;
	}

	public void setBranchLevel(String branchLevel) {
		this.branchLevel = (branchLevel == null ? null : branchLevel.trim());
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = (branchCode == null ? null : branchCode.trim());
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = (branchName == null ? null : branchName.trim());
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

	public String getBranchAppOrgCode() {
		return branchAppOrgCode;
	}

	public void setBranchAppOrgCode(String branchAppOrgCode) {
		this.branchAppOrgCode = (branchAppOrgCode == null ? null
				: branchAppOrgCode.trim());
	}

}
