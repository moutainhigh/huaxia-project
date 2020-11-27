package com.huaxia.opas.domain.retrieve;

import java.io.Serializable;

public class MemberJobCountApproveCheckUp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1731056924287591444L;

	//员工Id 
	private String userId;
	//员工姓名
	private String staffName;
	//审批未完成
	private Integer hangInTheAirStatus;
	//审批已完成
	private Integer finishedStatus;
	//审批补件
	private Integer supplementLetter;
	//征审挂起
	private Integer pauseDocument;
	//征信退件
	private Integer returnDocument;
	//征信未完成
	private Integer completed;
	//征信补件
	private Integer supplement;
	//合计
	private int total;
	//当日提交量
	private int subNum;
	//当日归档量
	private int gdNum;
	//备注
	private String remark;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Integer getHangInTheAirStatus() {
		return hangInTheAirStatus;
	}
	public void setHangInTheAirStatus(Integer hangInTheAirStatus) {
		this.hangInTheAirStatus = hangInTheAirStatus;
	}
	public Integer getFinishedStatus() {
		return finishedStatus;
	}
	public void setFinishedStatus(Integer finishedStatus) {
		this.finishedStatus = finishedStatus;
	}
	public Integer getSupplementLetter() {
		return supplementLetter;
	}
	public void setSupplementLetter(Integer supplementLetter) {
		this.supplementLetter = supplementLetter;
	}
	public Integer getPauseDocument() {
		return pauseDocument;
	}
	public void setPauseDocument(Integer pauseDocument) {
		this.pauseDocument = pauseDocument;
	}
	public Integer getReturnDocument() {
		return returnDocument;
	}
	public void setReturnDocument(Integer returnDocument) {
		this.returnDocument = returnDocument;
	}
	public Integer getCompleted() {
		return completed;
	}
	public void setCompleted(Integer completed) {
		this.completed = completed;
	}
	public Integer getSupplement() {
		return supplement;
	}
	public void setSupplement(Integer supplement) {
		this.supplement = supplement;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getSubNum() {
		return subNum;
	}
	public void setSubNum(int subNum) {
		this.subNum = subNum;
	}
	public int getGdNum() {
		return gdNum;
	}
	public void setGdNum(int gdNum) {
		this.gdNum = gdNum;
	}
}
