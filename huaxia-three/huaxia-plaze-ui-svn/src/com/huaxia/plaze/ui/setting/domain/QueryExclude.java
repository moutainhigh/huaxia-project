package com.huaxia.plaze.ui.setting.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("QueryExclude")
public class QueryExclude implements Serializable {

	private static final long serialVersionUID = -1589852133186879079L;
	
	private String queryId;
	
	private String account;
	
	private String staffName;

	private String excludeStartDate;
	
	private String excludeEndDate;
	
	private String excludeTime;
	
	private String excludeCause;

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}


	public String getExcludeStartDate() {
		return excludeStartDate;
	}

	public void setExcludeStartDate(String excludeStartDate) {
		this.excludeStartDate = excludeStartDate;
	}

	public String getExcludeEndDate() {
		return excludeEndDate;
	}

	public void setExcludeEndDate(String excludeEndDate) {
		this.excludeEndDate = excludeEndDate;
	}

	public String getExcludeTime() {
		return excludeTime;
	}

	public void setExcludeTime(String excludeTime) {
		this.excludeTime = excludeTime;
	}

	public String getExcludeCause() {
		return excludeCause;
	}

	public void setExcludeCause(String excludeCause) {
		this.excludeCause = excludeCause;
	}
	
}
