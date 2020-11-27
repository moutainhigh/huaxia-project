package com.huaxia.opas.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 第三方 & CRM & 个贷还款明细（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsLoanDtl extends CRM implements Serializable {

	private static final long serialVersionUID = -2519893547610278752L;
	
	// 客户ID
	private String custId;
	
	// 客户号
	private String custCode;
	
	// 账号
	private String accNo;
	
	// 还款账号
	private String payAccNo;
	
	// 还款日期
	private String psDueDt;
	
	// 当期还款利息
	private BigDecimal payInte;
	
	// 当期还款本金
	private BigDecimal payCapital;
	
	// 剩余未还利息
	private BigDecimal interestBal;
	
	// 剩余未还本金
	private BigDecimal capitalBal;
	
	// 当期应还款利息
	private BigDecimal mustPayInte;
	
	// 当期应还款本金
	private BigDecimal mustPayCapital;
	
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

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getPayAccNo() {
		return payAccNo;
	}

	public void setPayAccNo(String payAccNo) {
		this.payAccNo = payAccNo;
	}

	public String getPsDueDt() {
		return psDueDt;
	}

	public void setPsDueDt(String psDueDt) {
		this.psDueDt = psDueDt;
	}

	public BigDecimal getPayInte() {
		return payInte;
	}

	public void setPayInte(BigDecimal payInte) {
		this.payInte = payInte;
	}

	public BigDecimal getPayCapital() {
		return payCapital;
	}

	public void setPayCapital(BigDecimal payCapital) {
		this.payCapital = payCapital;
	}

	public BigDecimal getInterestBal() {
		return interestBal;
	}

	public void setInterestBal(BigDecimal interestBal) {
		this.interestBal = interestBal;
	}

	public BigDecimal getCapitalBal() {
		return capitalBal;
	}

	public void setCapitalBal(BigDecimal capitalBal) {
		this.capitalBal = capitalBal;
	}

	public BigDecimal getMustPayInte() {
		return mustPayInte;
	}

	public void setMustPayInte(BigDecimal mustPayInte) {
		this.mustPayInte = mustPayInte;
	}

	public BigDecimal getMustPayCapital() {
		return mustPayCapital;
	}

	public void setMustPayCapital(BigDecimal mustPayCapital) {
		this.mustPayCapital = mustPayCapital;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}
	
}
