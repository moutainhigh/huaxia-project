package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * 欺诈结果原因码维护实体类
 * @author liudi
 * @since 2017-02-26
 * @version 1.0
 */
public class FraudCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5528354838390943096L;

	
	/**
	 * ID
	 */
	private String autoId;

	/**
	 * 欺诈原因码
	 */
	private String fraudCode;

	/**
	 * 欺诈原因码名称
	 */
	private String fraudName;

	/**
	 * 当前状态
	 */
	private String status;

	/**
	 * 启用时间
	 */
	private Date startDate;

	/**
	 * 停用时间
	 */
	private Date stopDate;

	/**
	 * 创建人
	 */
	private String crtUser;

	/**
	 * 创建时间
	 */
	private Date crtDate;
	
	/**
	 * 最后操作人
	 */
	private String lstUpdUser;
	
	/**
	 * 最后操作时间
	 */
	private Date lstUpdDate;

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = (autoId == null ? null : autoId.trim());
		}

	public String getFraudCode() {
		return fraudCode;
	}

	public void setFraudCode(String fraudCode) {
		this.fraudCode = (fraudCode == null ? null : fraudCode.trim());
		}

	public String getFraudName() {
		return fraudName;
	}

	public void setFraudName(String fraudName) {
		this.fraudName = fraudName;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
