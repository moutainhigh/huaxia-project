package com.huaxia.opas.domain.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @description: 退件表字段
 * @author 张志宽
 * @since 2017年2月25日
 * @version 1.0
 * @lastUpdateDate
 * @lastUpdateUser
 */
@SuppressWarnings("serial")
public class BizInpApplErr implements Serializable {

	private String insideAppNo;
	
	private String xmlOrigData;
	
	private String errReason;
	
	private Date crtTime;

    private Date crtDate;

    private String crtUser;

    private Date lstUpdTime;

    private Date lstUpdDate;

    private String lstUpdUser;
	public String getInsideAppNo() {
		return insideAppNo;
	}
	public void setInsideAppNo(String insideAppNo) {
		this.insideAppNo = insideAppNo;
	}
	public String getXmlOrigData() {
		return xmlOrigData;
	}
	public void setXmlOrigData(String xmlOrigData) {
		this.xmlOrigData = xmlOrigData;
	}
	public String getErrReason() {
		return errReason;
	}
	public void setErrReason(String errReason) {
		this.errReason = errReason;
	}
	public Date getCrtTime() {
		return crtTime;
	}
	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
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
	public Date getLstUpdTime() {
		return lstUpdTime;
	}
	public void setLstUpdTime(Date lstUpdTime) {
		this.lstUpdTime = lstUpdTime;
	}
	public Date getLstUpdDate() {
		return lstUpdDate;
	}
	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}
	public String getLstUpdUser() {
		return lstUpdUser;
	}
	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

}
