package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 最低还款额维护实体类
 * @author liudi
 * @since 2017-03-20
 * @version 1.0
 */
public class SysparmRepayLimit implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -6902833599027809650L;

	/**
	 * ID
	 */
	private String autoId;

	/**
	 * 最低还款额比例代码
	 */
	private String repayRatioCode;

	/**
	 * 最低还款额比例值
	 */
	private BigDecimal repayRatioValue;

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



	public String getRepayRatioCode() {
		return repayRatioCode;
	}

	public void setRepayRatioCode(String repayRatioCode) {
		this.repayRatioCode = (repayRatioCode == null ? null : repayRatioCode.trim());
	}

	public BigDecimal getRepayRatioValue() {
		return repayRatioValue;
	}

	public void setRepayRatioValue(BigDecimal repayRatioValue) {
		this.repayRatioValue = repayRatioValue;
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
