package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & CRM & 个贷客户基本信息（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsCustInfoLns extends CRM implements Serializable {

	private static final long serialVersionUID = -3097979818779415177L;
	
	// 信贷客户号
	private String lnsCode;
	
	// 客户ID
	private String custId;
	
	// 客户号
	private String custCode;
	
	// 客户姓名
	private String custName;
	
	// 证件类型
	private String certType;
	
	// 证件号码
	private String certNo;
	
	// 手机号码
	private String mobilePhone;
	
	// 家庭电话
	private String telPhone;
	
	// 办公电话
	private String busPhone;
	
	// 邮政编码
	private String postCode;
	
	// 住宅地址
	private String address;
	
	// 邮箱地址
	private String email;
	
	// 银行代码
	private String bankId;
	
	// 网点代码
	private String subBrId;
	
	// 开户日期
	private String openDate;
	
	// 婚姻状态
	private String marriedStatus;
	
	// 教育程度
	private String educationStatus;
	
	// 客户状态
	private String custStatus;
	
	// 数据日期
	private String dDate;

	public String getLnsCode() {
		return lnsCode;
	}

	public void setLnsCode(String lnsCode) {
		this.lnsCode = lnsCode;
	}

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

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getBusPhone() {
		return busPhone;
	}

	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getSubBrId() {
		return subBrId;
	}

	public void setSubBrId(String subBrId) {
		this.subBrId = subBrId;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getMarriedStatus() {
		return marriedStatus;
	}

	public void setMarriedStatus(String marriedStatus) {
		this.marriedStatus = marriedStatus;
	}

	public String getEducationStatus() {
		return educationStatus;
	}

	public void setEducationStatus(String educationStatus) {
		this.educationStatus = educationStatus;
	}

	public String getCustStatus() {
		return custStatus;
	}

	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}

}
