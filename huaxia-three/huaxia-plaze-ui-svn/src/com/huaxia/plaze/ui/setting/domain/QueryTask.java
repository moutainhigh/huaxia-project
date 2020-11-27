package com.huaxia.plaze.ui.setting.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 任务查询类
 * @author zhiguo.li
 *
 */
@Alias("QueryTask")
public class QueryTask {
	
	// 系统编号
	private String sysId;
	
	// 任务类型
	private String taskType;
	
	// 最大查询数量
	private int maxCount;
	
	// 当前查询数量
	private int queryCount;
	
	// 开始时间
	private Date startTime;
	
	// 结束时间
	private Date endTime;
	
	private String startTimeString;
	
	private String endTimeString;

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public int getQueryCount() {
		return queryCount;
	}

	public void setQueryCount(int queryCount) {
		this.queryCount = queryCount;
	}

	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStartTimeString() {
		return startTimeString;
	}

	public void setStartTimeString(String startTimeString) {
		this.startTimeString = startTimeString;
	}

	public String getEndTimeString() {
		return endTimeString;
	}

	public void setEndTimeString(String endTimeString) {
		this.endTimeString = endTimeString;
	}

}
