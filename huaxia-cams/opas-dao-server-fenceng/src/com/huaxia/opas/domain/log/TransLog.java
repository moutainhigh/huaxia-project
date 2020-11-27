package com.huaxia.opas.domain.log;

import java.io.Serializable;
import java.util.Date;

public class TransLog implements Serializable {

	private static final long serialVersionUID = -6525600441729410946L;
	private String logId;
	private String insideAppNo;
	private String requestId;
	private Date transDate;
	private String nodeId;
	private String transCode;
	private String transType;
	private Date requestTime;
	private Long duration;
	private String reqClob;
	private String rspClob;
	private Date crtDate;
	private Date crtTime;
	private String crtUser;
	private String lstUpdUser;
	private Date lstUpdDate;
	private Date lstUpdTime;
	private Date batchDate;
	private String recStatus;
	private String scrLevel;

	private String resultFlag;
	private String returnCode;

	private long requestTimeLong;

	public long getRequestTimeLong() {
		return requestTimeLong;
	}

	public void setRequestTimeLong(long requestTimeLong) {
		this.requestTimeLong = requestTimeLong;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = (logId == null ? null : logId.trim());
	}

	public String getInsideAppNo() {
		return insideAppNo;
	}

	public void setInsideAppNo(String insideAppNo) {
		this.insideAppNo = (insideAppNo == null ? null : insideAppNo.trim());
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = (requestId == null ? null : requestId.trim());
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = (nodeId == null ? null : nodeId.trim());
	}

	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = (transCode == null ? null : transCode.trim());
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = (transType == null ? null : transType.trim());
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getReqClob() {
		return reqClob;
	}

	public void setReqClob(String reqClob) {
		this.reqClob = (reqClob == null ? null : reqClob.trim());
	}

	public String getRspClob() {
		return rspClob;
	}

	public void setRspClob(String rspClob) {
		this.rspClob = (rspClob == null ? null : rspClob.trim());
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = (crtUser == null ? null : crtUser.trim());
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = (lstUpdUser == null ? null : lstUpdUser.trim());
	}

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public Date getLstUpdTime() {
		return lstUpdTime;
	}

	public void setLstUpdTime(Date lstUpdTime) {
		this.lstUpdTime = lstUpdTime;
	}

	public Date getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}

	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = (recStatus == null ? null : recStatus.trim());
	}

	public String getScrLevel() {
		return scrLevel;
	}

	public void setScrLevel(String scrLevel) {
		this.scrLevel = (scrLevel == null ? null : scrLevel.trim());
	}

	public String getResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(String resultFlag) {
		this.resultFlag = (resultFlag == null ? null : resultFlag.trim());
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = (returnCode == null ? null : returnCode.trim());
	}

}
