package com.huaxia.opas.domain.address;

import java.io.Serializable;

public class OpasProvince implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6145400010623492004L;
	private String Puuid;     	
	private String ProId;    
	private String ProName;   	
	private String Lev;        	
	private String CurrStatus;
	
	public OpasProvince() {
		super();
	}
	public String getPuuid() {
		return Puuid;
	}
	public void setPuuid(String puuid) {
		Puuid = puuid;
	}
	public String getProId() {
		return ProId;
	}
	public void setProId(String proId) {
		ProId = proId;
	}
	public String getProName() {
		return ProName;
	}
	public void setProName(String proName) {
		ProName = proName;
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
