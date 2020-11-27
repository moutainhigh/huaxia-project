package com.huaxia.opas.domain.report;

import java.io.Serializable;

public class OutsourceDetail implements Serializable{

	/**
	 * 外包报表实体类
	 */
	private static final long serialVersionUID = 1244940493888758947L;

	//姓名	当日提交量	当日归档量	当日库存量	退件数量	进入个人未完成队列申请件数
	//1天	2天	3天	4天	5天
	//
	private String appId;
	
	private String userCode;
	
	private String userName;
	
	//当日提交量
	private int nowSubNum;
	
	//当日归档量
	private int nowDetailNum;
	
	//当日库存量
	private int nowInventoryNum;
	
	//退件数量
	private int backNum;
	
	//1天
	private int oneDay;
	
	//2天
	private int twoDay;
	
	//3天
	private int threeDay;
	
	//4天
	private int fourDay;
	
	//5天
	private int fiveDay;

	//退件理由
	private String memo;
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getNowSubNum() {
		return nowSubNum;
	}

	public void setNowSubNum(int nowSubNum) {
		this.nowSubNum = nowSubNum;
	}

	public int getNowDetailNum() {
		return nowDetailNum;
	}

	public void setNowDetailNum(int nowDetailNum) {
		this.nowDetailNum = nowDetailNum;
	}

	public int getNowInventoryNum() {
		return nowInventoryNum;
	}

	public void setNowInventoryNum(int nowInventoryNum) {
		this.nowInventoryNum = nowInventoryNum;
	}

	public int getBackNum() {
		return backNum;
	}

	public void setBackNum(int backNum) {
		this.backNum = backNum;
	}

	public int getOneDay() {
		return oneDay;
	}

	public void setOneDay(int oneDay) {
		this.oneDay = oneDay;
	}

	public int getTwoDay() {
		return twoDay;
	}

	public void setTwoDay(int twoDay) {
		this.twoDay = twoDay;
	}

	public int getThreeDay() {
		return threeDay;
	}

	public void setThreeDay(int threeDay) {
		this.threeDay = threeDay;
	}

	public int getFourDay() {
		return fourDay;
	}

	public void setFourDay(int fourDay) {
		this.fourDay = fourDay;
	}

	public int getFiveDay() {
		return fiveDay;
	}

	public void setFiveDay(int fiveDay) {
		this.fiveDay = fiveDay;
	}
		
}
