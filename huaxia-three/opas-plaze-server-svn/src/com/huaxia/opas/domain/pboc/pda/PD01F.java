package com.huaxia.opas.domain.pboc.pda;


import java.io.Serializable;
import java.util.List;
/**
 * 特殊交易信息段 
 * @author gaoh
 *
 */
public class PD01F implements Serializable {
	
	private static final long serialVersionUID = 2420026658667238598L;
	private PD01Fdata PD01Fdata;//特殊交易信息段数据
	private List<PD01FH> PD01FHList;//特殊交易信息    [1..99]  
	public PD01Fdata getPD01Fdata() {
		return PD01Fdata;
	}
	public void setPD01Fdata(PD01Fdata pD01Fdata) {
		PD01Fdata = pD01Fdata;
	}
	public List<PD01FH> getPD01FHList() {
		return PD01FHList;
	}
	public void setPD01FHList(List<PD01FH> pD01FHList) {
		PD01FHList = pD01FHList;
	}
	
}
