package com.huaxia.plaze.ui.quartz.domain;

public class BatchQuartz {

	// 记录编号
	private String uuid;
	// 创建时间
	private String crtTime;
	// 创建用户
	private String updTime;
	// 任务名称
	private String jobName;
	// 设置的运行时间
	private String runTime;
	// 开关，1:开启，2:关闭
	private String turn;
	// 任务简称
	private String jobJc;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCrtTime() {
		return crtTime;
	}
	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public String getTurn() {
		return turn;
	}
	public void setTurn(String turn) {
		this.turn = turn;
	}
	public String getJobJc() {
		return jobJc;
	}
	public void setJobJc(String jobJc) {
		this.jobJc = jobJc;
	}
	public String getUpdTime() {
		return updTime;
	}
	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}
	
	
}
