package com.huaxia.opas.domain.workflow;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author yuanquan
 * date 20200616
 * 工作流手动回调实体类
 *
 */
public class WorkflowCallback implements  Serializable{
	private static final long serialVersionUID = -9014404950762149211L;
	private String appId;
	private String currNode;
	private String status;
	private int exceptionStatus;
	private String solve;
	private Timestamp crtTime;
	private String executionId;
	private String webApplicationId;
	private String activityName;
	private String priority;
	private String dbid;
	private String expMessage;
	private String varName;
	private String value;
	private String key;
	private String stringValue;
	private String operateDesc;//20201022 最新生命周期描述 关联梯队节点
	
	
	public String getExpMessage() {
		return expMessage;
	}
	public void setExpMessage(String expMessage) {
		this.expMessage = expMessage;
	}
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	public String getWebApplicationId() {
		return webApplicationId;
	}
	public void setWebApplicationId(String webApplicationId) {
		this.webApplicationId = webApplicationId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSolve() {
		return solve;
	}
	public void setSolve(String solve) {
		this.solve = solve;
	}
	public String getCurrNode() {
		return currNode;
	}
	public void setCurrNode(String currNode) {
		this.currNode = currNode;
	}
	public Timestamp getCrtTime() {
		return crtTime;
	}
	public void setCrtTime(Timestamp crtTime) {
		this.crtTime = crtTime;
	}
	public int getExceptionStatus() {
		return exceptionStatus;
	}
	public void setExceptionStatus(int exceptionStatus) {
		this.exceptionStatus = exceptionStatus;
	}
	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public String getOperateDesc() {
		return operateDesc;
	}
	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}
	
}
