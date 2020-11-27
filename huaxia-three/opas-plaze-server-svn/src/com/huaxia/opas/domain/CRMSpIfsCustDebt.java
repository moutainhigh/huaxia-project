package com.huaxia.opas.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 第三方 & CRM & 客户全行负债（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsCustDebt extends CRM implements Serializable {

	private static final long serialVersionUID = 1766701930840785988L;
	
	// 客户ID
	private String custId;
	
	// 信用卡负债
	private BigDecimal crdDebtBal;
	
	// 贷款负债
	private BigDecimal loanDebtBal;
	
	// 负债总额
	private BigDecimal debtBal;
	
	// 贷款笔数
	private BigDecimal loanNum;
	
	// 贷款授信总额
	private BigDecimal credLmt;
	
	// 数据日期
	private String dDate;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public BigDecimal getCrdDebtBal() {
		return crdDebtBal;
	}

	public void setCrdDebtBal(BigDecimal crdDebtBal) {
		this.crdDebtBal = crdDebtBal;
	}

	public BigDecimal getLoanDebtBal() {
		return loanDebtBal;
	}

	public void setLoanDebtBal(BigDecimal loanDebtBal) {
		this.loanDebtBal = loanDebtBal;
	}

	public BigDecimal getDebtBal() {
		return debtBal;
	}

	public void setDebtBal(BigDecimal debtBal) {
		this.debtBal = debtBal;
	}

	public BigDecimal getLoanNum() {
		return loanNum;
	}

	public void setLoanNum(BigDecimal loanNum) {
		this.loanNum = loanNum;
	}

	public BigDecimal getCredLmt() {
		return credLmt;
	}

	public void setCredLmt(BigDecimal credLmt) {
		this.credLmt = credLmt;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}

}
