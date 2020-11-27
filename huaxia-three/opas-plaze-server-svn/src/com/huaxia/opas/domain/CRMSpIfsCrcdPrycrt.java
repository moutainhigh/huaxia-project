package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & CRM & 预授信信息表（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsCrcdPrycrt extends CRM implements Serializable {

	private static final long serialVersionUID = -6993482630967551028L;
	
	// 客户ID
	private String custId;
	
	// 客户号
	private String custCode;
	
	// 预授信客户姓名
	private String custName;
	
	// 预授信客户证件类型
	private String certType;
	
	// 预授信客户证件号码
	private String certNo;
	
	// 预授信日期
	private String ysxBgDt;
	
	// 预授信产品名称1
	private String productNm1;
	
	// 预授信产品名称2
	private String productNm2;
	
	// 预授信产品名称3
	private String productNm3;
	
	// 预授信标准卡额度
	private String creditCl;
	
	// 预授信易达金额度
	private String cashLoanCl;
	
	// 预授信有效结束日期
	private String ysxEndDt;
	
	// 是否基于人行征信系统
	private String rhFlag;
	
	// 预授信过期标志
	private String expireFlag;

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

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getYsxBgDt() {
		return ysxBgDt;
	}

	public void setYsxBgDt(String ysxBgDt) {
		this.ysxBgDt = ysxBgDt;
	}

	public String getProductNm1() {
		return productNm1;
	}

	public void setProductNm1(String productNm1) {
		this.productNm1 = productNm1;
	}

	public String getProductNm2() {
		return productNm2;
	}

	public void setProductNm2(String productNm2) {
		this.productNm2 = productNm2;
	}

	public String getProductNm3() {
		return productNm3;
	}

	public void setProductNm3(String productNm3) {
		this.productNm3 = productNm3;
	}

	public String getCreditCl() {
		return creditCl;
	}

	public void setCreditCl(String creditCl) {
		this.creditCl = creditCl;
	}

	public String getCashLoanCl() {
		return cashLoanCl;
	}

	public void setCashLoanCl(String cashLoanCl) {
		this.cashLoanCl = cashLoanCl;
	}

	public String getYsxEndDt() {
		return ysxEndDt;
	}

	public void setYsxEndDt(String ysxEndDt) {
		this.ysxEndDt = ysxEndDt;
	}

	public String getRhFlag() {
		return rhFlag;
	}

	public void setRhFlag(String rhFlag) {
		this.rhFlag = rhFlag;
	}

	public String getExpireFlag() {
		return expireFlag;
	}

	public void setExpireFlag(String expireFlag) {
		this.expireFlag = expireFlag;
	}


}
