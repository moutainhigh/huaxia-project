package com.huaxia.cams.modules.pengyuan.domain;

import java.util.Date;

/**
 * 鹏元个人信用报告地址信息表
 * 
 * @author User
 *
 */
public class PyPcrCrsCrtPbiAis {

	// 记录编号
	private String uuid;
	// 创建时间
	private Date crtTime;
	// 创建用户
	private String crtUser;
	// 交易编号
	private String trnId;
	// 详细地址
	private String address;
	// 联系电话
	private String tel;
	// 移动电话
	private String mobile;
	// 信息来源单位ID
	private Integer infoUnit;
	// 信息获取日期
	private String infoDate;

	private String appId;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getInfoUnit() {
		return infoUnit;
	}

	public void setInfoUnit(Integer infoUnit) {
		this.infoUnit = infoUnit;
	}

	public String getInfoDate() {
		return infoDate;
	}

	public void setInfoDate(String infoDate) {
		this.infoDate = infoDate;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Override
	public String toString() {
		return "PyPcrCrsCrtPbiAis [uuid=" + uuid + ", crtTime=" + crtTime + ", crtUser=" + crtUser + ", trnId=" + trnId
				+ ", address=" + address + ", tel=" + tel + ", mobile=" + mobile + ", infoUnit=" + infoUnit
				+ ", infoDate=" + infoDate + ", appId=" + appId + "]";
	}


}
