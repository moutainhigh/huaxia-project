package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
/**
 * 三方数据查询异常处理实体
 * @author weijinfeng
 *
 */
public class ThdErrMsg implements Serializable{

	private static final long serialVersionUID = -2997444597304759399L;

	private String appId; //条形码
	private String taskType; //三方数据名称
	private String errorCode; //异常响应详情
	private String failureNum; //失败次数
	private String taskStatus; //流转状态码
	private Date timeStamp;//查询时间
	private Date startDate;//查询开始时间
	private Date endDate;//查询结束时间
	private Date lstOptTime;//最后操作时间
	private String lstOptUser; //最后操作
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getFailureNum() {
		return failureNum;
	}
	public void setFailureNum(String failureNum) {
		this.failureNum = failureNum;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Date getLstOptTime() {
		return lstOptTime;
	}
	public void setLstOptTime(Date lstOptTime) {
		this.lstOptTime = lstOptTime;
	}
	public String getLstOptUser() {
		return lstOptUser;
	}
	public void setLstOptUser(String lstOptUser) {
		this.lstOptUser = lstOptUser;
	}
	
	
}
