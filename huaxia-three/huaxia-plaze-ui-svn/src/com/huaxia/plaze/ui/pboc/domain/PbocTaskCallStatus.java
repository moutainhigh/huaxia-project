package com.huaxia.plaze.ui.pboc.domain;

import org.apache.ibatis.type.Alias;

@Alias("PbocTaskCallStatus")
public class PbocTaskCallStatus {

	// 记录编号
	private String id;
	// 唯一标 主键关联
	private String uniqueFlag;
	// 任务类型
	private String taskType;
	
	private String taskIp;
	// 任务状态
	private String taskStatus;
	// 证件类型
	private String identityType;
	// 身份证号
	private String identityNo;
	// 姓名
	private String name;
	// 手机号
	private String mobile;
	// 快速标识
	private String quickFlag;
	// 报文返回的错误码
	private String errorCode;
	// 请求次数
	private String requestNum;
	// 失败次数
	private String failureNum;
	// 请求参数
	private String queryParam;
	// 最后操作人
	private String lstOptUser;
	// 最后操作时间
	private String lstOptTime;
	// 创建时间
	private String crtTime;
	//查询原因
	private String  queryReason;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUniqueFlag() {
		return uniqueFlag;
	}

	public void setUniqueFlag(String uniqueFlag) {
		this.uniqueFlag = uniqueFlag;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
   
	public String getTaskIp() {
		return taskIp;
	}

	public void setTaskIp(String taskIp) {
		this.taskIp = taskIp;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQuickFlag() {
		return quickFlag;
	}

	public void setQuickFlag(String quickFlag) {
		this.quickFlag = quickFlag;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getRequestNum() {
		return requestNum;
	}

	public void setRequestNum(String requestNum) {
		this.requestNum = requestNum;
	}

	public String getFailureNum() {
		return failureNum;
	}

	public void setFailureNum(String failureNum) {
		this.failureNum = failureNum;
	}

	public String getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}

	public String getLstOptUser() {
		return lstOptUser;
	}

	public void setLstOptUser(String lstOptUser) {
		this.lstOptUser = lstOptUser;
	}

	public String getLstOptTime() {
		return lstOptTime;
	}

	public void setLstOptTime(String lstOptTime) {
		this.lstOptTime = lstOptTime;
	}

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}

	public String getQueryReason() {
		return queryReason;
	}

	public void setQueryReason(String queryReason) {
		this.queryReason = queryReason;
	}

}
