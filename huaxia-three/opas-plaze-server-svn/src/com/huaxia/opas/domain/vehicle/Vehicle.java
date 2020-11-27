package com.huaxia.opas.domain.vehicle;

import java.io.Serializable;
import java.sql.Timestamp;

public class Vehicle implements Serializable {

	private static final long serialVersionUID = -4451295685139746562L;

	// 记录编号
	private String uuid;

	// 申请件编号
	private String appId;

	// 任务查询结果-1-失败1-成功
	private Integer queryResult;

	// 错误码
	private String errorCode;

	// 车产数量
	private Integer quantity;

	// 车辆价格
	private String price;

	// 购买车辆时间
	private String months;

	// 创建时间
	private Timestamp crtTime;

	// 创建用户
	private String crtUser;

	// 用户名
	private String name;

	// 车产状态
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(Integer queryResult) {
		this.queryResult = queryResult;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public Timestamp getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Timestamp crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

}
