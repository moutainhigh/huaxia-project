package com.huaxia.opas.domain.decision;

import java.io.Serializable;
import java.util.Date;

public class Teamlist implements Serializable {

	private static final long serialVersionUID = -7229369680400750919L;

	private String autoId;

	private String teamId;

	private String teamContent;

	private String company;

	private String approveContent;

	private String authCriterion;

	private Date approveTime;

	private Date approveVailudDate;

	private Date crtTime;

	private Date crtDate;

	private String crtUser;

	private String status;

	private Object remark;

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId == null ? null : autoId.trim();
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId == null ? null : teamId.trim();
	}

	public String getTeamContent() {
		return teamContent;
	}

	public void setTeamContent(String teamContent) {
		this.teamContent = teamContent == null ? null : teamContent.trim();
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company == null ? null : company.trim();
	}

	public String getApproveContent() {
		return approveContent;
	}

	public void setApproveContent(String approveContent) {
		this.approveContent = approveContent == null ? null : approveContent.trim();
	}

	public String getAuthCriterion() {
		return authCriterion;
	}

	public void setAuthCriterion(String authCriterion) {
		this.authCriterion = authCriterion == null ? null : authCriterion.trim();
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public Date getApproveVailudDate() {
		return approveVailudDate;
	}

	public void setApproveVailudDate(Date approveVailudDate) {
		this.approveVailudDate = approveVailudDate;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
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
		this.crtUser = crtUser == null ? null : crtUser.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getRemark() {
		return remark;
	}

	public void setRemark(Object remark) {
		this.remark = remark;
	}
}