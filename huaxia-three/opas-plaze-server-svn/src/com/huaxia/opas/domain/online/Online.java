package com.huaxia.opas.domain.online;

import java.io.Serializable;
import java.sql.Timestamp;

public class Online implements Serializable {

	/**
	 * 通讯运营商在网时长实体类
	 * @author dingguofeng
	 */
	private static final long serialVersionUID = -7948650703293142141L;
	
	private String UUID;          //记录编号
	
	private Timestamp CRT_TIME;   //创建时间
	
	private String CRT_USER;      //创建用户
	
	private String APP_ID;        //申请件编号
	
	private String CODE;          //返回编码
	
	private String RESULT_DATA;   //返回结果
	
	private String RESULT_DESC;   //返回结果描述

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public Timestamp getCRT_TIME() {
		return CRT_TIME;
	}

	public void setCRT_TIME(Timestamp cRT_TIME) {
		CRT_TIME = cRT_TIME;
	}

	public String getCRT_USER() {
		return CRT_USER;
	}

	public void setCRT_USER(String cRT_USER) {
		CRT_USER = cRT_USER;
	}

	public String getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(String aPP_ID) {
		APP_ID = aPP_ID;
	}

	public String getCODE() {
		return CODE;
	}

	public void setCODE(String cODE) {
		CODE = cODE;
	}

	public String getRESULT_DATA() {
		return RESULT_DATA;
	}

	public void setRESULT_DATA(String rESULT_DATA) {
		RESULT_DATA = rESULT_DATA;
	}

	public String getRESULT_DESC() {
		return RESULT_DESC;
	}

	public void setRESULT_DESC(String rESULT_DESC) {
		RESULT_DESC = rESULT_DESC;
	}
	
}
