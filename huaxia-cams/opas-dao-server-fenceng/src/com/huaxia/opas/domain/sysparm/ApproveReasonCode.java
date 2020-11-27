package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * 审批原因码实体类
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public class ApproveReasonCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3294922286409089841L;

	/**
	 * ID
	 */
	private String autoId;

	/**
	 * 原因代码
	 */
	private String reasonCode;

	/**
	 * 原因类型
	 */
	private String reasonType;

	/**
	 * 原因期限
	 */
	private int reasonTimeLimit;
	
	/**
	 * 原因描述
	 */
	private String reasonDesc;
	
	/**
	 * 银联原因代码
	 */
	private String uniReasonCode;

	/**
	 * 银联原因类型
	 */
	private String uniReasonType;
	
	/**
	 * 银联原因描述
	 */
	private String uniReasonDesc;
	
	/**
	 * 账户类型
	 */
	private String acctType;
	
	/**
	 * 对应角色
	 */
	private String corRole;

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

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = (reasonCode == null ? null : reasonCode.trim());
	}

	public String getReasonType() {
		return reasonType;
	}

	public void setReasonType(String reasonType) {
		this.reasonType = (reasonCode == null ? null : reasonType.trim());
	}

	public int getReasonTimeLimit() {
		return reasonTimeLimit;
	}

	public void setReasonTimeLimit(int reasonTimeLimit) {
		this.reasonTimeLimit = reasonTimeLimit;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = (reasonDesc == null ? null : reasonDesc.trim());
	}

	public String getUniReasonCode() {
		return uniReasonCode;
	}

	public void setUniReasonCode(String uniReasonCode) {
		this.uniReasonCode = uniReasonCode;
	}

	public String getUniReasonType() {
		return uniReasonType;
	}

	public void setUniReasonType(String uniReasonType) {
		this.uniReasonType = (uniReasonType == null ? null : uniReasonType.trim());
	}

	public String getUniReasonDesc() {
		return uniReasonDesc;
	}

	public void setUniReasonDesc(String uniReasonDesc) {
		this.uniReasonDesc = (uniReasonDesc == null ? null : uniReasonDesc.trim());
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = (acctType == null ? null : acctType.trim());
	}

	public String getCorRole() {
		return corRole;
	}

	public void setCorRole(String corRole) {
		this.corRole = (corRole == null ? null : corRole.trim());
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
