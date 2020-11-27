package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * @version1.0
 * @author 汪涛
 *2018-06-25 15:00
 */
@SuppressWarnings("serial")
public class ApplyRiskInfo implements Serializable {
	/**
	 * 唯一主键
	 */
	private String autoId;
	
	/**
	 * 推广机构代码
	 */
	private String abCode;
	
	/**
	 * 联系人姓名
	 */
	private String conName;
	
	/**
	 * 联系人手机号
	 */
	private String telRisk;
	
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
	 * 最后操作时间
	 */
	private Date lstUpdTime;

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getAbCode() {
		return abCode;
	}

	public void setAbCode(String abCode) {
		this.abCode = abCode;
	}

	public String getConName() {
		return conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
	}

	public String getTelRisk() {
		return telRisk;
	}

	public void setTelRisk(String telRisk) {
		this.telRisk = telRisk;
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

	public Date getLstUpdTime() {
		return lstUpdTime;
	}

	public void setLstUpdTime(Date lstUpdTime) {
		this.lstUpdTime = lstUpdTime;
	}

	@Override
	public String toString() {
		return "ApplyRiskInfo [autoId=" + autoId + ", abCode=" + abCode + ", conName=" + conName + ", telRisk="
				+ telRisk + ", crtDate=" + crtDate + ", crtUser=" + crtUser + ", lstUpdUser=" + lstUpdUser
				+ ", lstUpdTime=" + lstUpdTime + "]";
	}
	
}
