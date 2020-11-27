package com.huaxia.opas.domain.workflow;

import java.util.Date;

public class Workflow implements java.io.Serializable {// jpc0522
	private static final long serialVersionUID = 8989938936584360268L;

	private String name;
	private Integer count;

	private Date startTime;
	private Date endTime;
	private String status;//节点未结束或异常1 节点已结束或异常16
	private String packageid;//bzk ydj
	private int curNum;
	private int pageNum;
	
	
	public int getCurNum() {
		return curNum;
	}

	public void setCurNum(int curNum) {
		this.curNum = curNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPackageid() {
		return packageid;
	}

	public void setPackageid(String packageid) {
		this.packageid = packageid;
	}
}
