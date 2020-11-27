package com.huaxia.opas.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 成功任务
 * 
 * @author zhiguo.li
 *
 */
public class TaskCallSuccess implements Serializable {

	private static final long serialVersionUID = 8341203704383418844L;

	// UUID
	private String id;

	// 申请件编号
	private String appId;

	// 任务类型
	private String taskType;

	// 操作类型（手动、自动）
	private String operateType;

	// 申请类型
	private String applyType;

	// 易达金标志
	private String ydjFlag;

	// 套卡标志
	private String taoFlag;

	// 时间戳
	private Date date;

	public TaskCallSuccess() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getYdjFlag() {
		return ydjFlag;
	}

	public void setYdjFlag(String ydjFlag) {
		this.ydjFlag = ydjFlag;
	}

	public String getTaoFlag() {
		return taoFlag;
	}

	public void setTaoFlag(String taoFlag) {
		this.taoFlag = taoFlag;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
