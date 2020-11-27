package com.huaxia.plaze.ui.numread.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
@Alias("NumReadLog")
public class NumReadLog {

	//记录编号
	private String uuid;
	//创建时间
	private String crtTime;
	//创建用户
	private String crtUser;
	//被查询人姓名
	private String name;
	//被查询人证件号
	private String certNo;
	//查询方式
	private String queryModel;
	//交易编号
	private String trnId;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCrtTime() {
		return crtTime;
	}
	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQueryModel() {
		return queryModel;
	}
	public void setQueryModel(String queryModel) {
		this.queryModel = queryModel;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getTrnId() {
		return trnId;
	}
	public void setTrnId(String trnId) {
		this.trnId = trnId;
	}
	
	
	
}
