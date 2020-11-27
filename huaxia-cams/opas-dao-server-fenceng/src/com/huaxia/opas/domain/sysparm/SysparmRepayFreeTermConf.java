package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * 免息期参数配置实体类
 * @author liudi
 * @since 2017-03-21
 * @version 1.0
 */
public class SysparmRepayFreeTermConf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4753095273606706623L;

	/**
	 * ID
	 */
	private String autoId;

	/**
	 * 免息还款期客户分层结果标签
	 */
	private String custLevelTag;

	/**
	 * 免息还款期代码
	 */
	private String repayFreeCode;

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



	public String getRepayFreeCode() {
		return repayFreeCode;
	}

	public void setRepayFreeCode(String repayFreeCode) {
		this.repayFreeCode = (repayFreeCode == null ? null : repayFreeCode.trim());
	}



	public String getCustLevelTag() {
		return custLevelTag;
	}

	public void setCustLevelTag(String custLevelTag) {
		this.custLevelTag = (custLevelTag == null ? null : custLevelTag.trim());
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
