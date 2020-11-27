package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * 利率差异化参数配置实体类
 * @author liudi
 * @since 2017-03-17
 * @version 1.0
 */
public class SysparmRateConf implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4423475312626558400L;

	/**
	 * ID
	 */
	private String autoId;
	
	/**
	 * 利率客户分层结果标签
	 */
	private String custLevelTag;

	/**
	 * 利率代码
	 */
	private String rateCode;

	/**
	 * 状态
	 */
	private String status;

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

	public String getCustLevelTag() {
		return custLevelTag;
	}

	public void setCustLevelTag(String custLevelTag) {
		this.custLevelTag = (custLevelTag == null ? null : custLevelTag.trim());
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = (rateCode == null ? null : rateCode.trim());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
