package com.huaxia.opas.domain.report;

import java.io.Serializable;

public class WorkloadDetail implements Serializable {

	private static final long serialVersionUID = 6677885525996777108L;
	
	private String appId;//申请件条形码
	
	private String appIdLetter;//流水号字母
	
	private String entryClerk;//录入商
	
	private String channel;//渠道
	
	private String area;//地区
	
	private String c1Cname;//客户姓名
	
	private String c1Idtype;//证件类型
	
	private String crtDate;//进入系统时间
	
	private String auditorName;//录入员
	
	private String inputGroup;//录入员组别
	
	private String crtUser;//征信调查员
	
	private String creditGroup;//征信调查员组别
	
	private String approver;//授信审批员
	
	private String approverGroup;//授信审批员组别
	
	private String policyCode1;//政策码1
	
	private String policyCode2;//政策码2
	
	private String policyCode3;//政策码3
	
	private String violateCode1;//违例码1
	
	private String violateCode2;//违例码2
	
	private String violateCode3;//违例码3
	
	private String refuseCode1;//拒绝码1
	
	private String refuseCode2;//拒绝码2
	
	private String refuseCode3;//拒绝码3
	
	private String mastercardDecresult;//决策结果
	
	private String toArchiveTime;//归档日期
	
	private String operator;//归档员OPERATOR
	
	private String appProd;//申请卡种
	
	private String approveCreditProduct;//审批卡种
	
	private String approveCreditLimit;//审批额度
	
	private String appResult;//审批通过拒绝标识

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppIdLetter() {
		return appIdLetter;
	}

	public void setAppIdLetter(String appIdLetter) {
		this.appIdLetter = appIdLetter;
	}

	public String getEntryClerk() {
		return entryClerk;
	}

	public void setEntryClerk(String entryClerk) {
		this.entryClerk = entryClerk;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getC1Cname() {
		return c1Cname;
	}

	public void setC1Cname(String c1Cname) {
		this.c1Cname = c1Cname;
	}

	public String getC1Idtype() {
		return c1Idtype;
	}

	public void setC1Idtype(String c1Idtype) {
		this.c1Idtype = c1Idtype;
	}

	public String getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public String getInputGroup() {
		return inputGroup;
	}

	public void setInputGroup(String inputGroup) {
		this.inputGroup = inputGroup;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getCreditGroup() {
		return creditGroup;
	}

	public void setCreditGroup(String creditGroup) {
		this.creditGroup = creditGroup;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getApproverGroup() {
		return approverGroup;
	}

	public void setApproverGroup(String approverGroup) {
		this.approverGroup = approverGroup;
	}

	public String getPolicyCode1() {
		return policyCode1;
	}

	public void setPolicyCode1(String policyCode1) {
		this.policyCode1 = policyCode1;
	}

	public String getPolicyCode2() {
		return policyCode2;
	}

	public void setPolicyCode2(String policyCode2) {
		this.policyCode2 = policyCode2;
	}

	public String getPolicyCode3() {
		return policyCode3;
	}

	public void setPolicyCode3(String policyCode3) {
		this.policyCode3 = policyCode3;
	}

	public String getViolateCode1() {
		return violateCode1;
	}

	public void setViolateCode1(String violateCode1) {
		this.violateCode1 = violateCode1;
	}

	public String getViolateCode2() {
		return violateCode2;
	}

	public void setViolateCode2(String violateCode2) {
		this.violateCode2 = violateCode2;
	}

	public String getViolateCode3() {
		return violateCode3;
	}

	public void setViolateCode3(String violateCode3) {
		this.violateCode3 = violateCode3;
	}

	public String getRefuseCode1() {
		return refuseCode1;
	}

	public void setRefuseCode1(String refuseCode1) {
		this.refuseCode1 = refuseCode1;
	}

	public String getRefuseCode2() {
		return refuseCode2;
	}

	public void setRefuseCode2(String refuseCode2) {
		this.refuseCode2 = refuseCode2;
	}

	public String getRefuseCode3() {
		return refuseCode3;
	}

	public void setRefuseCode3(String refuseCode3) {
		this.refuseCode3 = refuseCode3;
	}

	public String getMastercardDecresult() {
		return mastercardDecresult;
	}

	public void setMastercardDecresult(String mastercardDecresult) {
		this.mastercardDecresult = mastercardDecresult;
	}

	public String getToArchiveTime() {
		return toArchiveTime;
	}

	public void setToArchiveTime(String toArchiveTime) {
		this.toArchiveTime = toArchiveTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getAppProd() {
		return appProd;
	}

	public void setAppProd(String appProd) {
		this.appProd = appProd;
	}

	public String getApproveCreditProduct() {
		return approveCreditProduct;
	}

	public void setApproveCreditProduct(String approveCreditProduct) {
		this.approveCreditProduct = approveCreditProduct;
	}

	public String getApproveCreditLimit() {
		return approveCreditLimit;
	}

	public void setApproveCreditLimit(String approveCreditLimit) {
		this.approveCreditLimit = approveCreditLimit;
	}

	public String getAppResult() {
		return appResult;
	}

	public void setAppResult(String appResult) {
		this.appResult = appResult;
	}
	
}
