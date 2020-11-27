package com.huaxia.opas.domain.pboc.pda;

import java.io.Serializable;
import java.util.List;
/**
 * 最近24个月还款记录信息段
 * @author gaoh
 *
 */
public class PD01D implements Serializable {
	
	private static final long serialVersionUID = 1987795571062698859L;
	private PD01Ddata  PD01Ddata;   //最近24个月还款记录信息段数据
	private List<PD01DH> PD01DHList;//还款状态信息 [1..24]
	public PD01Ddata getPD01Ddata() {
		return PD01Ddata;
	}
	public void setPD01Ddata(PD01Ddata pD01Ddata) {
		PD01Ddata = pD01Ddata;
	}
	public List<PD01DH> getPD01DHList() {
		return PD01DHList;
	}
	public void setPD01DHList(List<PD01DH> pD01DHList) {
		PD01DHList = pD01DHList;
	}
	
}