package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & CRM & 网银签约表（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsSignCybk extends CRM implements Serializable {

	private static final long serialVersionUID = 3994107493678512080L;
	
	// 客户ID
	private String custId;
	
	// 客户号
	private String custCode;
	
	// 签约版本
	private String signVersion;
	
	// 签约日期
	private String signDate;
	
	// 签约支行
	private String subBrId;
	
	// 签约渠道
	private String signChanel;
	
	// 签约卡号
	private String signCard;
	
	// 活期主账号
	private String hqAccNo;
	
	// 状态
	private String status;
	
	// 数据日期
	private String dDate;
	
	// 所属分行
	private String brId;

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

	public String getSignVersion() {
		return signVersion;
	}

	public void setSignVersion(String signVersion) {
		this.signVersion = signVersion;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getSubBrId() {
		return subBrId;
	}

	public void setSubBrId(String subBrId) {
		this.subBrId = subBrId;
	}

	public String getSignChanel() {
		return signChanel;
	}

	public void setSignChanel(String signChanel) {
		this.signChanel = signChanel;
	}

	public String getSignCard() {
		return signCard;
	}

	public void setSignCard(String signCard) {
		this.signCard = signCard;
	}

	public String getHqAccNo() {
		return hqAccNo;
	}

	public void setHqAccNo(String hqAccNo) {
		this.hqAccNo = hqAccNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}

	public String getBrId() {
		return brId;
	}

	public void setBrId(String brId) {
		this.brId = brId;
	}

}
