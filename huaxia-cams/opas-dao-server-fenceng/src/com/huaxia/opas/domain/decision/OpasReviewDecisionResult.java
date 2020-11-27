package com.huaxia.opas.domain.decision;

import java.io.Serializable;
import java.util.Date;

//审查决策结果信息表
public class OpasReviewDecisionResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7214060522269332618L;

	private String autoId;// 序列值

	private String appId;// 申请件编号

	private String batchCode;// 批量标识

	private String industryCode;// 行员代码

	private String professinonCode;// 职业代码

	private String auditor;// 审查员登录名

	private String auditorName;// 审查员姓名

	private Date crtDate;// 创建日期

	private String crtUser;// 创建人

	private String currOptGroup;// 当前操作组

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getProfessinonCode() {
		return professinonCode;
	}

	public void setProfessinonCode(String professinonCode) {
		this.professinonCode = professinonCode;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
	
	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getCurrOptGroup() {
		return currOptGroup;
	}

	public void setCurrOptGroup(String currOptGroup) {
		this.currOptGroup = currOptGroup;
	}
}
