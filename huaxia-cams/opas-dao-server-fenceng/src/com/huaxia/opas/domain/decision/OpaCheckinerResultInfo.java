package com.huaxia.opas.domain.decision;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class OpaCheckinerResultInfo implements Serializable {
	
	private static final long serialVersionUID = 7331507635727517581L;

	private String autoId;

	private String appId;
	/**
	 * 检查情况
	 */
	private String checkCondition;
	/**
	 * 检查结果
	 */
	private String checkResult;
	@JSONField(format = "yyyy/MM/dd HH:mm:ss")
	private Date crtDate;

	private String crtUser;
	private String stopFlag;
	public String getStopFlag() {
		return stopFlag;
	}

	public void setStopFlag(String stopFlag) {
		this.stopFlag = stopFlag;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId == null ? null : autoId.trim();
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getCheckCondition() {
		return checkCondition;
	}

	public void setCheckCondition(String checkCondition) {
		this.checkCondition = checkCondition == null ? null : checkCondition.trim();
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult == null ? null : checkResult.trim();
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
}