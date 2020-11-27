package com.huaxia.opas.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 第三方 & CRM & 个贷账户月日均（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsLoanAccAvg extends CRM implements Serializable {

	private static final long serialVersionUID = 1029488232526088865L;

	// 客户ID
	private String custId;
	
	// 客户号
	private String custCode;
	
	// 贷款合同号
	private String contrNo;
	
	
	// 贷款账号/公积金账号
	private String accNo;
	
	// 本月月日均
	private BigDecimal mAvg;
	
	// 本季季日均
	private BigDecimal qAvg;
	
	// 本年年日均
	private BigDecimal yAvg;
	
	// 数据日期
	private String dDate;
	
	// 子账号
	private String subAccNo;

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

	public String getContrNo() {
		return contrNo;
	}

	public void setContrNo(String contrNo) {
		this.contrNo = contrNo;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public BigDecimal getmAvg() {
		return mAvg;
	}

	public void setmAvg(BigDecimal mAvg) {
		this.mAvg = mAvg;
	}

	public BigDecimal getqAvg() {
		return qAvg;
	}

	public void setqAvg(BigDecimal qAvg) {
		this.qAvg = qAvg;
	}

	public BigDecimal getyAvg() {
		return yAvg;
	}

	public void setyAvg(BigDecimal yAvg) {
		this.yAvg = yAvg;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}

	public String getSubAccNo() {
		return subAccNo;
	}

	public void setSubAccNo(String subAccNo) {
		this.subAccNo = subAccNo;
	}

}
