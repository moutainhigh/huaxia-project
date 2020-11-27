package com.huaxia.opas.domain.address;

import java.io.Serializable;

public class OpasCity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1209994550038138191L;
	/**
	 * 
	 */
	private String Cuuid;
	private String CityId;
	private String CityName;
	private String ProId;
	private String Lev;
	private String CurrStatus;
	
	public OpasCity() {
		super();
	}
	public String getCuuid() {
		return Cuuid;
	}
	public void setCuuid(String cuuid) {
		Cuuid = cuuid;
	}
	public String getCityId() {
		return CityId;
	}
	public void setCityId(String cityId) {
		CityId = cityId;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public String getProId() {
		return ProId;
	}
	public void setProId(String proId) {
		ProId = proId;
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
