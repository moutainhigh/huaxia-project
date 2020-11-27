package com.huaxia.opas.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 第三方 & CRM & 借记卡交易基本信息表（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsCustCard extends CRM implements Serializable {

	private static final long serialVersionUID = 3042281923529147486L;

	// 客户ID
	private String custId;
	
	// 客户号
	private String custCode;
	
	// 卡号
	private String cardNo;
	
	// 活期主账号
	private String accNo;
	
	// 标准卡种类
	private String cardType;
	
	// 卡的BIN码
	private String cardBin;
	
	// 卡号段
	private String cardNum;
	
	// 卡等级
	private String cardLevel;
	
	// 后台标志位
	private String htbzw;
	
	// 电话号码
	private String mobilePhone;
	
	// 发卡日期
	private String openDate;
	
	// 到期日
	private String endDate;
	
	// 卡状态
	private String status;
	
	// 发卡支行
	private String subBrId;
	
	// 发卡分行
	private String brId;
	
	// POS交易限额
	private BigDecimal posTransAmt;
	
	// ATM交易限额
	private BigDecimal atmTransAmt;
	
	// 转账交易限额
	private BigDecimal accTransAmt;
	
	// 卡介质类型
	private String mediumType;
	
	// 产品机构代码
	private String prodBrId;
	
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardBin() {
		return cardBin;
	}

	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCardLevel() {
		return cardLevel;
	}

	public void setCardLevel(String cardLevel) {
		this.cardLevel = cardLevel;
	}

	public String getHtbzw() {
		return htbzw;
	}

	public void setHtbzw(String htbzw) {
		this.htbzw = htbzw;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubBrId() {
		return subBrId;
	}

	public void setSubBrId(String subBrId) {
		this.subBrId = subBrId;
	}

	public String getBrId() {
		return brId;
	}

	public void setBrId(String brId) {
		this.brId = brId;
	}
	
	public BigDecimal getPosTransAmt() {
		return posTransAmt;
	}

	public void setPosTransAmt(BigDecimal posTransAmt) {
		this.posTransAmt = posTransAmt;
	}

	public BigDecimal getAtmTransAmt() {
		return atmTransAmt;
	}

	public void setAtmTransAmt(BigDecimal atmTransAmt) {
		this.atmTransAmt = atmTransAmt;
	}

	public BigDecimal getAccTransAmt() {
		return accTransAmt;
	}

	public void setAccTransAmt(BigDecimal accTransAmt) {
		this.accTransAmt = accTransAmt;
	}

	public String getMediumType() {
		return mediumType;
	}

	public void setMediumType(String mediumType) {
		this.mediumType = mediumType;
	}

	public String getProdBrId() {
		return prodBrId;
	}

	public void setProdBrId(String prodBrId) {
		this.prodBrId = prodBrId;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}

	
}
