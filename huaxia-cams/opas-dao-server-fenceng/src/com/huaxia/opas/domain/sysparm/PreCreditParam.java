package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 汪涛
 *
 */
@SuppressWarnings("serial")
public class PreCreditParam implements Serializable {
	/**
	 * 历史记录的ID
	 */
	private String operationID;

	/**
	 * 预授信 ID
	 */
	private String autoID;
	
	/**
	 * 批次号
	 */
	private String batchNo;
	
	/**
	 * 导入日期
	 */
	private Date importDate;
	
	/**
	 * 客户ID
	 */
	private String custID;
	
	/**
	 * 预筛选额度
	 */
	private int preSellLimit;
	
	/**
	 * 开始日期
	 */
	private Date startDate;
	
	/**
	 * 结束日期
	 */
	private Date endDate;
	
	/**
	 * 客户姓名
	 */
	private String custName;
	
	/**
	 * 身份证号
	 */
	private String phoneNum;
	
	/**
	 * 身份证号
	 */
	private String certifiID;
	
	/**
	 * 帐户类型
	 */
	private String acctType;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 启用日期
	 */
	private Date beginDate;
	
	/**
	 * 停用日期
	 */
	private Date stopDate;
	
	/**
	 * 创建日期
	 */
	private Date crtDate;
	
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
	private String operatContent;
	private String operatDo;

	public String getAutoID() {
		return autoID;
	}

	public void setAutoID(String autoID) {
		this.autoID = autoID;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	public int getPreSellLimit() {
		return preSellLimit;
	}

	public void setPreSellLimit(int preSellLimit) {
		this.preSellLimit = preSellLimit;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCertifiID() {
		return certifiID;
	}

	public void setCertifiID(String certifiID) {
		this.certifiID = certifiID;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
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

	public String getOperatContent() {
		return operatContent;
	}

	public void setOperatContent(String operatContent) {
		this.operatContent = operatContent;
	}

	public String getOperatDo() {
		return operatDo;
	}

	public void setOperatDo(String operatDo) {
		this.operatDo = operatDo;
	}
	
	public String getOperationID() {
		return operationID;
	}

	public void setOperationID(String operationID) {
		this.operationID = operationID;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "PreCreditParam [autoID=" + autoID + ", batchNo=" + batchNo + ", importDate=" + importDate + ", custID="
				+ custID + ", preSellLimit=" + preSellLimit + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", custName=" + custName + ", certifiID=" + certifiID + ", phoneNum=" + phoneNum + ", acctType=" + acctType + ", status="
				+ status + ", beginDate=" + beginDate + ", stopDate=" + stopDate + ", crtDate=" + crtDate + ", crtUser="
				+ crtUser + ", lstUpdUser=" + lstUpdUser + ", lstUpdDate=" + lstUpdDate + ", operatContent="
				+ operatContent + ", operatDo=" + operatDo + "]";
	}

}
