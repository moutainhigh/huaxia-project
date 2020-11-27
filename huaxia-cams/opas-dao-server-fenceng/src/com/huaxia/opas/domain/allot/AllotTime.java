package com.huaxia.opas.domain.allot;

import java.io.Serializable;
import java.util.Date;
/**
 * 自动分件时间
 * @author wangdebin
 */
public class AllotTime implements Serializable {
	
	private static final long serialVersionUID = 3944684223222869664L;

	private String id;
	
	private String mappingId;
	
	private String ruleType;
	
	private String startTime;
	
	private String endTime;
	
	private String crtUser;

	private Date crtDate;

	private String lstUser;
	
	private Date lstDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMappingId() {
		return mappingId;
	}

	public void setMappingId(String mappingId) {
		this.mappingId = mappingId;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getLstUser() {
		return lstUser;
	}

	public void setLstUser(String lstUser) {
		this.lstUser = lstUser;
	}

	public Date getLstDate() {
		return lstDate;
	}

	public void setLstDate(Date lstDate) {
		this.lstDate = lstDate;
	}
	
	
}
