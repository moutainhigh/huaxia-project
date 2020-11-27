package com.huaxia.opas.domain.archive;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ArDetail implements Serializable {

	private static final long serialVersionUID = -6083940899090943720L;

	/**
	 * 申请件编号
	 */
	private String appId;

	/**
	 * 客户姓名
	 */
	private String custName;

	/**
	 * 证件号码
	 */
	private String credNo;

	/**
	 * 最后操作员
	 */
	private String operator;
	
	/**
	 * 最后操作时间
	 */
	private Date operatTime;
	
	/**
	 * 推广机构代码
	 */
	private String spreadorganCode;
	
	/**
	 * 进入系统天数
	 */
	private int inDays;

	/**
	 * 申请件结果
	 */
	private String appResult;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 归档编号
	 */
	private String fileNo;
	
	/**
	 * 是否已送过发卡标识
	 */
	private String alreadySendFlag;
	
	/**
	 * 归档2小时时间
	 */
	private Date decTime;
	
	/**
	 * 进入待归档时间
	 */
	private Date toArchiveTime;
	
	/**
	 * 申请卡种
	 */
	private String applyCard;
	
	/**
	 * 审批授信产品
	 */
	private String approveCreditProduct;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCredNo() {
		return credNo;
	}

	public void setCredNo(String credNo) {
		this.credNo = credNo;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperatTime() {
		return operatTime;
	}

	public void setOperatTime(Date operatTime) {
		this.operatTime = operatTime;
	}

	public String getSpreadorganCode() {
		return spreadorganCode;
	}

	public void setSpreadorganCode(String spreadorganCode) {
		this.spreadorganCode = spreadorganCode;
	}

	public int getInDays() {
		return inDays;
	}

	public void setInDays(int inDays) {
		this.inDays = inDays;
	}

	public String getAppResult() {
		return appResult;
	}

	public void setAppResult(String appResult) {
		this.appResult = appResult;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getAlreadySendFlag() {
		return alreadySendFlag;
	}

	public void setAlreadySendFlag(String alreadySendFlag) {
		this.alreadySendFlag = alreadySendFlag;
	}

	public Date getDecTime() {
		return decTime;
	}

	public void setDecTime(Date decTime) {
		this.decTime = decTime;
	}

	public Date getToArchiveTime() {
		return toArchiveTime;
	}

	public void setToArchiveTime(Date toArchiveTime) {
		this.toArchiveTime = toArchiveTime;
	}

	public String getApplyCard() {
		return applyCard;
	}

	public void setApplyCard(String applyCard) {
		this.applyCard = applyCard;
	}

	public String getApproveCreditProduct() {
		return approveCreditProduct;
	}

	public void setApproveCreditProduct(String approveCreditProduct) {
		this.approveCreditProduct = approveCreditProduct;
	}
	
	/**
	 * 建立一个ID的集合,便于批量操作使用
	 */
	
	private List<String> ids;


	public List<String> getIds() {
		return ids;
	}


	public void setIds(List<String> ids) {
		this.ids = ids;
	}
}
