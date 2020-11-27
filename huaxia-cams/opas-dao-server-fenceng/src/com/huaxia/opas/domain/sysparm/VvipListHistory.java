package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;


public class VvipListHistory implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8061192161728876424L;

	/**
	 * UUID
	 */
	private String uuId;
	
	/**
	 * ID
	 */
	private String autoId;

	/**
	 * 姓名
	 */
	private String appName;

	/**
	 * 证件号
	 */
	private String idNo;
	
	/**
	 * 当前状态
	 */
	private String status;

	/**
	 * 启用时间
	 */
	private Date startDate;

	/**
	 * 停用时间
	 */
	private Date stopDate;

	/**
	 * 创建人
	 */
	private String crtUser;

	/**
	 * 创建时间
	 */
	private Date crtDate;
	
	/**
	 * 最后操作人
	 */
	private String lstUpdUser;
	
	/**
	 * 最后操作时间
	 */
	private Date lstUpdDate;

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = (uuId == null ? null : uuId.trim());
	}
	
	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = (autoId == null ? null : autoId.trim());
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = (appName == null ? null : appName.trim());
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = (idNo == null ? null : idNo.trim());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
