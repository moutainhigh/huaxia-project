package com.huaxia.opas.domain.address;

import java.io.Serializable;

public class OpasArea implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1453082467201420123L;
	private String Auuid;
	private String AreaId;
	private String AreaName;
	private String CityId;
	private String Lev;
	private String CurrStatus;
	
	public OpasArea() {
		super();
	}
	public String getAuuid() {
		return Auuid;
	}
	public void setAuuid(String auuid) {
		Auuid = auuid;
	}
	public String getAreaId() {
		return AreaId;
	}
	public void setAreaId(String areaId) {
		AreaId = areaId;
	}
	public String getAreaName() {
		return AreaName;
	}
	public void setAreaName(String areaName) {
		AreaName = areaName;
	}
	public String getCityId() {
		return CityId;
	}
	public void setCityId(String cityId) {
		CityId = cityId;
	}
	public String getLev() {
		return Lev;
	}
	public void setLev(String lev) {
		Lev = lev;
	}
	public String getCurrStatus() {
		return CurrStatus;
	}
	public void setCurrStatus(String currStatus) {
		CurrStatus = currStatus;
	}
	
	
}
