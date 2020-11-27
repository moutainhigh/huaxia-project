package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//医院类名单
public class Hospital implements Serializable {

	private static final long serialVersionUID = -4020108805171503300L;

	
	private String uuid;
	private String hospitalName;
	private String hospitalLevel;
	private String city;
	private String businessLevel;
	private Date crtTime;
	
	/**
	 * 设置ID的集合,便于批量操作时的调用
	 */
	private List<String> ids;

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getHospitalLevel() {
		return hospitalLevel;
	}
	public void setHospitalLevel(String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBusinessLevel() {
		return businessLevel;
	}
	public void setBusinessLevel(String businessLevel) {
		this.businessLevel = businessLevel;
	}
	public Date getCrtTime() {
		return crtTime;
	}
	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}
	
	
}
