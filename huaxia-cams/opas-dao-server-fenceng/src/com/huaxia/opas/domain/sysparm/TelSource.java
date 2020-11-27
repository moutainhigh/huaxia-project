package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * 照会对象参数DAO层实体类
 * @author liudi
 * @since 2017-02-28
 * @version 1.0
 */
public class TelSource implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3142418516460514537L;

	/**
	 * ID
	 */
	private String autoId;

	/**
	 * 电话来源码
	 */
	private String telSourceCode;

	/**
	 * 电话来源描述
	 */
	private String telSourceDesc;

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

	public String getTelSourceCode() {
		return telSourceCode;
	}

	public void setTelSourceCode(String telSourceCode) {
		this.telSourceCode = (telSourceCode == null ? null : telSourceCode.trim());
	}

	public String getTelSourceDesc() {
		return telSourceDesc;
	}

	public void setTelSourceDesc(String telSourceDesc) {
		this.telSourceDesc = (telSourceDesc == null ? null : telSourceDesc.trim());
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
