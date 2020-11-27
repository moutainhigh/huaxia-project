package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author wenyh
 *
 */
@SuppressWarnings("serial")
public class PadLocationAddress implements Serializable {
	/**
	 * PAD定位地址ID
	 */
	private String autoID;
	
	/**
	 * 导入日期
	 */
	private Date importDate;
	
	/**
	 * 城市ID
	 */
	private String cityID;
	
	/**
	 * 现有PAD端传输的定位地址
	 */
	private String padLocationAddress;
	
	/**
	 * 启停状态 0：停用 1：启用
	 */
	private String status;
	
	/**
	 * 启用日期
	 */
	private Date beginDate;
	
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

	public String getAutoID() {
		return autoID;
	}

	public void setAutoID(String autoID) {
		this.autoID = autoID;
	}

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public String getCityID() {
		return cityID;
	}

	public void setCityID(String cityID) {
		this.cityID = cityID;
	}

	public String getPadLocationAddress() {
		return padLocationAddress;
	}

	public void setPadLocationAddress(String padLocationAddress) {
		this.padLocationAddress = padLocationAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
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

	@Override
	public String toString() {
		return "PadLocationAddress [autoID=" + autoID + ", importDate=" + importDate + ", cityID=" + cityID + ", padLocationAddress=" + padLocationAddress + ", status="
				+ status + ", beginDate=" + beginDate + ", stopDate=" + stopDate + ", crtDate=" + crtDate + ", crtUser=" + crtUser + ", lstUpdUser=" + lstUpdUser + ", lstUpdDate=" + lstUpdDate + "]";
	}

}
