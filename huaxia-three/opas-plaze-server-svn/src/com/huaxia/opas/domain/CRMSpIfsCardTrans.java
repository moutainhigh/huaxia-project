package com.huaxia.opas.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 第三方 & CRM & 借记卡交易流水（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsCardTrans extends CRM implements Serializable {

	private static final long serialVersionUID = -7153016552754005440L;
	
	// 客户ID
	private String custId;
	
	// 客户号
	private String custCode;
	
	// 流水号
	private String seqNo;
	
	// 币种
	private String currency;
	
	// 商户类型
	private String comType;
	
	// 商户类型名称
	private String comTypeName;
	
	// 商户编号（MCC编码）
	private String mccNo;
	
	// 商户名称
	private String comName;
	
	// 卡种类型
	private String cardType;
	
	// 卡号
	private String cardNo;
	
	// 交易类型
	private String transType;
	
	// 机构
	private String branch;
	
	// 终端号
	private String txnTerminal;
	
	// 交易渠道
	private String channel;
	
	// 交易币种
	private String transCurrency;
	
	// 交易金额
	private BigDecimal transAmt;
	
	// 交易人民币金额
	private BigDecimal rmbAmt;
	
	// 交易时间
	private String transDate;
	
	// 交易扣率
	private String feeRate;
	
	// 交易状态
	private String transStatus;
	
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

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getComType() {
		return comType;
	}

	public void setComType(String comType) {
		this.comType = comType;
	}

	public String getComTypeName() {
		return comTypeName;
	}

	public void setComTypeName(String comTypeName) {
		this.comTypeName = comTypeName;
	}

	public String getMccNo() {
		return mccNo;
	}

	public void setMccNo(String mccNo) {
		this.mccNo = mccNo;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getTxnTerminal() {
		return txnTerminal;
	}

	public void setTxnTerminal(String txnTerminal) {
		this.txnTerminal = txnTerminal;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getTransCurrency() {
		return transCurrency;
	}

	public void setTransCurrency(String transCurrency) {
		this.transCurrency = transCurrency;
	}

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}

	public BigDecimal getRmbAmt() {
		return rmbAmt;
	}

	public void setRmbAmt(BigDecimal rmbAmt) {
		this.rmbAmt = rmbAmt;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(String feeRate) {
		this.feeRate = feeRate;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}

}
