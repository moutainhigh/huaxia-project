package com.huaxia.opas.domain.phone;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * 手机实名制实体类
 * 
 * @author dingguofeng
 *
 */

public class PhoneIdentification implements Serializable {
	
	private static final long serialVersionUID = -7138095499774583060L;
	
	private String UUID;          //记录编号
	
	private Timestamp CRT_TIME;   //创建时间
	
	private String CRT_USER;      //创建用户
	
	private String APP_ID;        //申请件编号
	
	private String SUCCESS;       //是否成功
	
	private String RESPONSE_CODE; //响应编码
	
	private String RESPONSE_DESC; //响应描述
	
	private String RESULT_CODE;   //返回编码
	
	private String RESULT_DESC;   //返回描述

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

	public String getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(String aPP_ID) {
		APP_ID = aPP_ID;
	}
	
	public String getCRT_USER() {
		return CRT_USER;
	}

	public void setCRT_USER(String cRT_USER) {
		CRT_USER = cRT_USER;
	}

	public String getSUCCESS() {
		return SUCCESS;
	}

	public void setSUCCESS(String sUCCESS) {
		SUCCESS = sUCCESS;
	}

	public String getRESPONSE_CODE() {
		return RESPONSE_CODE;
	}

	public void setRESPONSE_CODE(String rESPONSE_CODE) {
		RESPONSE_CODE = rESPONSE_CODE;
	}

	public String getRESPONSE_DESC() {
		return RESPONSE_DESC;
	}

	public void setRESPONSE_DESC(String rESPONSE_DESC) {
		RESPONSE_DESC = rESPONSE_DESC;
	}

	public String getRESULT_CODE() {
		return RESULT_CODE;
	}

	public void setRESULT_CODE(String rESULT_CODE) {
		RESULT_CODE = rESULT_CODE;
	}

	public String getRESULT_DESC() {
		return RESULT_DESC;
	}

	public void setRESULT_DESC(String rESULT_DESC) {
		RESULT_DESC = rESULT_DESC;
	}
	
}
