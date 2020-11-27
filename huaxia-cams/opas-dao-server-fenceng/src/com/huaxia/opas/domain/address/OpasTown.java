package com.huaxia.opas.domain.address;

import java.io.Serializable;

public class OpasTown implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4693290343979834951L;
	private String Tuuid;
	private String TownId;
	private String TownId1;
	private String TownName;
	private String Lev;
	private String CurrStatus;
	
	public OpasTown() {
		super();
	}
	public String getTuuid() {
		return Tuuid;
	}
	public void setTuuid(String tuuid) {
		Tuuid = tuuid;
	}
	public String getTownId() {
		return TownId;
	}
	public void setTownId(String townId) {
		TownId = townId;
	}
	public String getTownId1() {
		return TownId1;
	}
	public void setTownId1(String townId1) {
		TownId1 = townId1;
	}
	public String getTownName() {
		return TownName;
	}
	public void setTownName(String townName) {
		TownName = townName;
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
