package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

//WarningList实体类,实现可序列化,属性来源于数据库
public class Warning implements Serializable{

	private static final long serialVersionUID = -4036980821102704185L;

	/**
	 * ID
	 */
	private String autoId;
	
	/**
	 * 风险类型
	 */
	private String riskType;
	
	/**
	 * 状态,0-停用,1-启用
	 */
	private String status;
	
	/**
	 * 启用日期
	 */
	private Date startDate;
	
	/**
	 * 停用日期
	 */
	private Date stopDate;
	
	/**
	 * 创建日期
	 */
	private Date crtDate;
	
	/**
	 * 创建人
	 */
	private String crtUser;
	
	/**
	 * 最后操作人
	 */
	private String lstUpdUser;
	
	/**
	 * 最后操作日期
	 */
	private Date lstUpdDate;

	/**
	 * 当前登录用户
	 */
	private String operator;

	public Warning() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Warning(String autoId, String riskType, String status, Date startDate, Date stopDate, Date crtDate,
			String crtUser, String lstUpdUser, Date lstUpdDate, String operator) {
		super();
		this.autoId = autoId;
		this.riskType = riskType;
		this.status = status;
		this.startDate = startDate;
		this.stopDate = stopDate;
		this.crtDate = crtDate;
		this.crtUser = crtUser;
		this.lstUpdUser = lstUpdUser;
		this.lstUpdDate = lstUpdDate;
		this.operator = operator;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	

}
