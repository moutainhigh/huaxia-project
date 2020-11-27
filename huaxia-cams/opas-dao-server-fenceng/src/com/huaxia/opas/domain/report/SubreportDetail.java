package com.huaxia.opas.domain.report;

import java.io.Serializable;

public class SubreportDetail implements Serializable{
	
	/**
	 * 提报报表实体类
	 */
	private static final long serialVersionUID = -3119903300022059770L;

	//条形码、
	private String appId;
	
	//申请卡种
	private String prodName;
	
	//提报环节
	private String submitType;
	
	//提报人员
	private String currOptUser;
	
	//提报时间
	private String crtDate;
	
	//处理人员
	private String crtUser;
	
	//提报环节处理情况
	private String vdelStatus;
	
	//申请件当前状态
	private String operateDesc;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getSubmitType() {
		return submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	public String getCurrOptUser() {
		return currOptUser;
	}

	public void setCurrOptUser(String currOptUser) {
		this.currOptUser = currOptUser;
	}

	public String getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getVdelStatus() {
		return vdelStatus;
	}

	public void setVdelStatus(String vdelStatus) {
		this.vdelStatus = vdelStatus;
	}

	public String getOperateDesc() {
		return operateDesc;
	}

	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}
	
	
}
