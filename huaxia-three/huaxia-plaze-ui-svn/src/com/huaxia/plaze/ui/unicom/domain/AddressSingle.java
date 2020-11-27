package com.huaxia.plaze.ui.unicom.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
@Alias("AddressSingle")
public class AddressSingle {

	// 记录编号
	private String uuid;

	// 创建时间
	private Date crtTime;

	// 创建用户
	private String crtUser;

	// 交易编号
	private String trnId;

	// 电话号
	private String mobile;

	//产品ID 360015工作地址 360016居住地址
	private String apiKey;
	
	// 详细地址
	private String address;

	// 查询状态
	private String queryStatus;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getTrnId() {
		return trnId;
	}

	public void setTrnId(String trnId) {
		this.trnId = trnId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}
	
	
}
