package com.huaxia.opas.domain.system;

import java.io.Serializable;
import java.util.Date;

public class ApMenu implements Serializable {

	private static final long serialVersionUID = 5487128450031741420L;

	/**
	 * 菜单ID
	 */
	private String menuId;
	
	/**
	 * 菜单中文名称
	 */
	private String menuName;
	
	/**
	 * 菜单层级
	 */
	private String menuLevel;
	
	/**
	 * 父菜单ID
	 */
	private String menuParentId;
	
	/**
	 * 菜单路径
	 */
	private String menuUrl;
	
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
	 * 队列标识
	 */
	private String queueFlag;
	
	/**
	 * 节点编号
	 */
	private String nodeNo;
	private String sortFlag;

	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = (menuId == null ? null : menuId.trim());
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = (menuName == null ? null : menuName.trim());
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = (menuLevel == null ? null : menuLevel.trim());
	}
	public String getMenuParentId() {
		return menuParentId;
	}
	public void setMenuParentId(String menuParentId) {
		this.menuParentId = (menuParentId == null ? null : menuParentId.trim());
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = (menuUrl == null ? null : menuUrl.trim());
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
	public String getQueueFlag() {
		return queueFlag;
	}
	public void setQueueFlag(String queueFlag) {
		this.queueFlag = (queueFlag == null ? null : queueFlag.trim());
	}
	public String getNodeNo() {
		return nodeNo;
	}
	public void setNodeNo(String nodeNo) {
		this.nodeNo = (nodeNo == null ? null : nodeNo.trim());
	}
	public String getSortFlag() {
		return sortFlag;
	}
	public void setSortFlag(String sortFlag) {
		this.sortFlag = sortFlag;
	}
}
