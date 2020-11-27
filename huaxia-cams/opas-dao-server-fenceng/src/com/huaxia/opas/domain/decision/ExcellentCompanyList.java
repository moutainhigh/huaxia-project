package com.huaxia.opas.domain.decision;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ExcellentCompanyList implements Serializable{

	private static final long serialVersionUID = -8465832232105571313L;

	private String autoId;

    private String companyName;

    private String name;

    private String certifiNo;

    private String phone;
    
    private String currStatus;

    private Date startTime;

    private Date stopTime;
    private Date crtTime;
    
    private String operator;
	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCertifiNo() {
		return certifiNo;
	}
	public void setCertifiNo(String certifiNo) {
		this.certifiNo = certifiNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCurrStatus() {
		return currStatus;
	}
	public void setCurrStatus(String currStatus) {
		this.currStatus = currStatus;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getStopTime() {
		return stopTime;
	}
	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}
	public Date getCrtTime() {
		return crtTime;
	}
	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}


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

}