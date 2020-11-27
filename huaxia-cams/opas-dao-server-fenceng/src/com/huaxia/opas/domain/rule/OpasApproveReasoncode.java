package com.huaxia.opas.domain.rule;

import java.io.Serializable;

public class OpasApproveReasoncode implements Serializable{
	private String reasonCode;
	private String reasonType;
	private String reasonDesc;
	private String matchRole;
	private String status;
	private String autoId;

	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getReasonType() {
		return reasonType;
	}
	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}
	public String getReasonDesc() {
		return reasonDesc;
	}
	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}
	public String getMatchRole() {
		return matchRole;
	}
	public void setMatchRole(String matchRole) {
		this.matchRole = matchRole;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
