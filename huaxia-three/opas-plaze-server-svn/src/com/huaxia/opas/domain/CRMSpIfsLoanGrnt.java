package com.huaxia.opas.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 第三方 & CRM & 个贷担保信息（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsLoanGrnt extends CRM implements Serializable {

	private static final long serialVersionUID = -1462650111135040711L;

	// 客户ID
	private String custId;
	
	// 客户号
	private String custCode;
	
	// 与被担保人关系
	private String grntRelation;
	
	// 担保业务种类
	private String grntClass;
	
	// 担保业务编码
	private String grntNo;
	
	// 担保金额
	private BigDecimal grntAmt;
	
	// 担保起期
	private String grntBDate;
	
	// 担保止期
	private String grntEDate;
	
	// 贷款状态
	private String loanStatus;
	
	// 贷款风险分级
	private String riskLevel;
	
	// 被担保人的主营销人
	private String empCode;
	
	// 被担保人的维护人
	private String ownerId;

	// 数据日期
	private String dDate;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getGrntRelation() {
		return grntRelation;
	}

	public void setGrntRelation(String grntRelation) {
		this.grntRelation = grntRelation;
	}

	public String getGrntClass() {
		return grntClass;
	}

	public void setGrntClass(String grntClass) {
		this.grntClass = grntClass;
	}

	public String getGrntNo() {
		return grntNo;
	}

	public void setGrntNo(String grntNo) {
		this.grntNo = grntNo;
	}

	public BigDecimal getGrntAmt() {
		return grntAmt;
	}

	public void setGrntAmt(BigDecimal grntAmt) {
		this.grntAmt = grntAmt;
	}

	public String getGrntBDate() {
		return grntBDate;
	}

	public void setGrntBDate(String grntBDate) {
		this.grntBDate = grntBDate;
	}

	public String getGrntEDate() {
		return grntEDate;
	}

	public void setGrntEDate(String grntEDate) {
		this.grntEDate = grntEDate;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}
	
}
