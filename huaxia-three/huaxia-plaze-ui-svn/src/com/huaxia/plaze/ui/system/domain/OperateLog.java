package com.huaxia.plaze.ui.system.domain;

import org.apache.ibatis.type.Alias;

@Alias("OperateLog")
public class OperateLog extends Log {
	
	// 被查询人账户
	private String userAcct;
	
	// 被查询人名称
	private String userName;

	// 操作前值
	private String operateBefore;
	
	// 操作后值
	private String operateAfter;
	
	// 操作员账户
	private String operateAcct;
	
	// 操作员名称
	private String operateName;

	private String operateTime;
	public String getUserAcct() {
		return userAcct;
	}

	public void setUserAcct(String userAcct) {
		this.userAcct = userAcct;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperateBefore() {
		return operateBefore;
	}

	public void setOperateBefore(String operateBefore) {
		this.operateBefore = operateBefore;
	}

	public String getOperateAfter() {
		return operateAfter;
	}

	public void setOperateAfter(String operateAfter) {
		this.operateAfter = operateAfter;
	}

	public String getOperateAcct() {
		return operateAcct;
	}

	public void setOperateAcct(String operateAcct) {
		this.operateAcct = operateAcct;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
}
