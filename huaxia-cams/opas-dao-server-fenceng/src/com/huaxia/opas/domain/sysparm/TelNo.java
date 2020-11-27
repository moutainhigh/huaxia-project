package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * 电话号码参数实体类
 * @author liudi
 * @since 2017-04-07
 * @version 1.0
 */
public class TelNo implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6175305838909504000L;

	/**
	 * ID
	 */
	private String autoId;

	/**
	 * 电话类型编号
	 */
	private String telTypeCode;

	/**
	 * 电话号码描述
	 */
	private String telNoDesc;

	/**
	 * 账户类型
	 */
	private String acctType;

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

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = (autoId == null ? null : autoId.trim());
	}

	public String getTelTypeCode() {
		return telTypeCode;
	}

	public void setTelTypeCode(String telTypeCode) {
		this.telTypeCode = (telTypeCode == null ? null : telTypeCode.trim());
	}

	public String getTelNoDesc() {
		return telNoDesc;
	}

	public void setTelNoDesc(String telNoDesc) {
		this.telNoDesc = (telNoDesc == null ? null : telNoDesc.trim());
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = (acctType == null ? null : acctType.trim());
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
