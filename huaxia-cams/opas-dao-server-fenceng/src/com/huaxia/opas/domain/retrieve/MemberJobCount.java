package com.huaxia.opas.domain.retrieve;

import java.io.Serializable;

/**
 * 组长调阅  征信 
 * @author 张立波
 * 2017年2月25日
 * */
public class MemberJobCount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1556299068982671829L;
	//员工Id 
	private String userId;
	//员工姓名
	private String staffName;
	//未完成
	private Integer hangInTheAirStatus;
	//已完成
	private Integer finishedStatus;
	//补件
	private Integer supplementLetter;
	//退件
	private Integer returnDocument;
	//挂起
	private Integer pauseDocument;
	//合计
	private int total;
	//当日提交量
	private int subNum;
	//当日归档量
	private int gdNum;
	//备注
	private String remark;
	//用于计算总件数
	private int sumTotal;
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
	public Integer getReturnDocument() {
		return returnDocument;
	}
	public void setReturnDocument(Integer returnDocument) {
		this.returnDocument = returnDocument;
	}
	public Integer getPauseDocument() {
		return pauseDocument;
	}
	public void setPauseDocument(Integer pauseDocument) {
		this.pauseDocument = pauseDocument;
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
	public int getSumTotal() {
		return sumTotal;
	}
	public void setSumTotal(int sumTotal) {
		this.sumTotal = sumTotal;
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
