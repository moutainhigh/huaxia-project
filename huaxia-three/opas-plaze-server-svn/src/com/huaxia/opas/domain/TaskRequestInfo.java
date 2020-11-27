package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 任务请求信息
 * 
 * @author zhiguo.li
 *
 */
public class TaskRequestInfo implements Serializable {

	private static final long serialVersionUID = -3318293256425057852L;

	// UUID
	private String id;

	// 创建日期
	private String crtTime;

	// 创建人
	private String crtUser;

	// 最后更新日期
	private String lstUpdTime;

	// 最后更新人
	private String lstUpdUser;

	// 申请件编号
	private String appId;

	// 请求标示&fico大数据
	private String reqFico;

	// 请求标志 & 车辆
	private String reqVehicle;

	// 请求标志 & 人行
	private String reqPBOC;

	// 请求标志 & 公安
	private String reqPolice;

	// 请求标志 & 学历
	private String reqEducation;

	// 请求标志 & 百融
	private String reqBRZX;

	// 请求标志 & 芝麻信用 & 芝麻评分
	private String reqScoreOfZMXY;

	// 请求标志 & 芝麻信用 & IVS分
	private String reqIvsOfZMXY;

	// 请求标志 & 芝麻信用 & 风险名单
	private String reqRistListOfZMXY;

	// 请求标志 & 芝麻信用 & 好信度
	private String reqMsc8005OfQHZX;

	// 请求标志 & 芝麻信用 & 地址通
	private String reqMsc8007OfQHZX;

	// 请求标志 & 芝麻信用 & 风险度提示
	private String reqMsc8036OfQHZX;

	// 请求标志 & 芝麻信用 & 常贷客
	private String reqMsc8037OfQHZX;
	
	// 请求标志 & 芝麻信用 & 好信欺诈度
	private String reqMsc8075OfQHZX;
	
	// 请求标志 & 芝麻信用 & 好信一鉴通
	private String reqMsc8107OfQHZX;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getLstUpdTime() {
		return lstUpdTime;
	}

	public void setLstUpdTime(String lstUpdTime) {
		this.lstUpdTime = lstUpdTime;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getReqPBOC() {
		return reqPBOC;
	}

	public void setReqPBOC(String reqPBOC) {
		this.reqPBOC = reqPBOC;
	}

	public String getReqPolice() {
		return reqPolice;
	}

	public void setReqPolice(String reqPolice) {
		this.reqPolice = reqPolice;
	}

	public String getReqEducation() {
		return reqEducation;
	}

	public void setReqEducation(String reqEducation) {
		this.reqEducation = reqEducation;
	}

	public String getReqBRZX() {
		return reqBRZX;
	}

	public void setReqBRZX(String reqBRZX) {
		this.reqBRZX = reqBRZX;
	}

	public String getReqScoreOfZMXY() {
		return reqScoreOfZMXY;
	}

	public void setReqScoreOfZMXY(String reqScoreOfZMXY) {
		this.reqScoreOfZMXY = reqScoreOfZMXY;
	}

	public String getReqIvsOfZMXY() {
		return reqIvsOfZMXY;
	}

	public void setReqIvsOfZMXY(String reqIvsOfZMXY) {
		this.reqIvsOfZMXY = reqIvsOfZMXY;
	}

	public String getReqRistListOfZMXY() {
		return reqRistListOfZMXY;
	}

	public void setReqRistListOfZMXY(String reqRistListOfZMXY) {
		this.reqRistListOfZMXY = reqRistListOfZMXY;
	}

	public String getReqMsc8005OfQHZX() {
		return reqMsc8005OfQHZX;
	}

	public void setReqMsc8005OfQHZX(String reqMsc8005OfQHZX) {
		this.reqMsc8005OfQHZX = reqMsc8005OfQHZX;
	}

	public String getReqMsc8007OfQHZX() {
		return reqMsc8007OfQHZX;
	}

	public void setReqMsc8007OfQHZX(String reqMsc8007OfQHZX) {
		this.reqMsc8007OfQHZX = reqMsc8007OfQHZX;
	}

	public String getReqMsc8036OfQHZX() {
		return reqMsc8036OfQHZX;
	}

	public void setReqMsc8036OfQHZX(String reqMsc8036OfQHZX) {
		this.reqMsc8036OfQHZX = reqMsc8036OfQHZX;
	}

	public String getReqMsc8037OfQHZX() {
		return reqMsc8037OfQHZX;
	}

	public void setReqMsc8037OfQHZX(String reqMsc8037OfQHZX) {
		this.reqMsc8037OfQHZX = reqMsc8037OfQHZX;
	}

	public String getReqMsc8075OfQHZX() {
		return reqMsc8075OfQHZX;
	}

	public void setReqMsc8075OfQHZX(String reqMsc8075OfQHZX) {
		this.reqMsc8075OfQHZX = reqMsc8075OfQHZX;
	}

	public String getReqMsc8107OfQHZX() {
		return reqMsc8107OfQHZX;
	}

	public void setReqMsc8107OfQHZX(String reqMsc8107OfQHZX) {
		this.reqMsc8107OfQHZX = reqMsc8107OfQHZX;
	}

	public String getReqVehicle() {
		return reqVehicle;
	}

	public void setReqVehicle(String reqVehicle) {
		this.reqVehicle = reqVehicle;
	}

	public String getReqFico() {
		return reqFico;
	}

	public void setReqFico(String reqFico) {
		this.reqFico = reqFico;
	}
	

}
