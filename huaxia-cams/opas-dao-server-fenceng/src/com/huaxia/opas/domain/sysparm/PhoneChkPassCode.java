package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * 电核过件码实体类
 * @author liudi
 * @since 2017-02-28
 * @version 1.0
 */
public class PhoneChkPassCode implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2382812805466859825L;

	/**
	 * ID
	 */
	private String autoId;

	/**
	 * 电核过件码
	 */
	private String phoneChkPassCode;

	/**
	 * 电核过件码名称
	 */
	private String phoneChkPassName;

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

	public String getPhoneChkPassCode() {
		return phoneChkPassCode;
	}

	public void setPhoneChkPassCode(String phoneChkPassCode) {
		this.phoneChkPassCode = (phoneChkPassCode == null ? null : phoneChkPassCode.trim());
	}

	public String getPhoneChkPassName() {
		return phoneChkPassName;
	}

	public void setPhoneChkPassName(String phoneChkPassName) {
		this.phoneChkPassName = (phoneChkPassName == null ? null : phoneChkPassName.trim());
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
