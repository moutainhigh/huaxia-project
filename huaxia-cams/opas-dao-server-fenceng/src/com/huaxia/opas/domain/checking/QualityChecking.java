package com.huaxia.opas.domain.checking;

import java.io.Serializable;
import java.util.Date;

/**
 * 质检的实体类
 * @author wangtao
 *
 */
public class QualityChecking implements Serializable{

	private static final long serialVersionUID = -2165138444439658135L;
	
	//序列值
	private String autoId;
	
	//流水号
	private String appId;
	
	//检查情况
	private String checkCondition;
	
	//检查结果
	private String checkResult;
	
	//创建日期
	private Date crtDate;
	
	//创建人
	private String crtUser;

	//备注
	private String memo;
	
	//质检状态
	private String stopFlag;
	
	//最后操作时间
	private Date lstDate;
	
	//外包质检标志
	private String employeeFlag;
	
	//释放标志
	private String breakFlag;
	
	//被质检时申请件所属人
	private String currOptUser;

	private String currNode;
	
	private String userRecord;
	
	private String ydjFlag;
	
	public String getYdjFlag() {
		return ydjFlag;
	}

	public void setYdjFlag(String ydjFlag) {
		this.ydjFlag = ydjFlag;
	}

	public String getCurrNode() {
		return currNode;
	}

	public void setCurrNode(String currNode) {
		this.currNode = currNode;
	}

	public String getCurrOptUser() {
		return currOptUser;
	}

	public void setCurrOptUser(String currOptUser) {
		this.currOptUser = currOptUser;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}


	public String getCheckCondition() {
		return checkCondition;
	}

	public void setCheckCondition(String checkCondition) {
		this.checkCondition = checkCondition;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getStopFlag() {
		return stopFlag;
	}

	public void setStopFlag(String stopFlag) {
		this.stopFlag = stopFlag;
	}

	public Date getLstDate() {
		return lstDate;
	}

	public void setLstDate(Date lstDate) {
		this.lstDate = lstDate;
	}

	public String getEmployeeFlag() {
		return employeeFlag;
	}

	public void setEmployeeFlag(String employeeFlag) {
		this.employeeFlag = employeeFlag;
	}
	
	
	public String getBreakFlag() {
		return breakFlag;
	}

	public void setBreakFlag(String breakFlag) {
		this.breakFlag = breakFlag;
	}

	public String getUserRecord() {
		return userRecord;
	}

	public void setUserRecord(String userRecord) {
		this.userRecord = userRecord;
	}

	@Override
	public String toString() {
		return "QualityChecking [autoId=" + autoId + ", appId=" + appId + ", checkCondition=" + checkCondition
				+ ", checkResult=" + checkResult + ", crtDate=" + crtDate + ", crtUser=" + crtUser + ", memo=" + memo
				+ ", stopFlag=" + stopFlag + ", lstDate=" + lstDate + ", employeeFlag=" + employeeFlag + ", breakFlag="
				+ breakFlag + ", currOptUser=" + currOptUser + ", currNode=" + currNode + ", userRecord=" + userRecord
				+ "]";
	}


}
