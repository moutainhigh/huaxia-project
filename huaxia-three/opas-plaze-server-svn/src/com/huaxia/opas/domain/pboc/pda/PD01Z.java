package com.huaxia.opas.domain.pboc.pda;

import java.io.Serializable;
import java.util.List;
/**
 * 标注及声明信息段
 * @author gaoh
 *
 */
public class PD01Z implements Serializable {
	private static final long serialVersionUID = 1136866223663259580L;
	private PD01Zdata PD01Zdata;//标注及声明信息段数据
	private List<PD01ZH> PD01ZHList;//标注及声明信息    [1..5]
	public PD01Zdata getPD01Zdata() {
		return PD01Zdata;
	}
	public void setPD01Zdata(PD01Zdata pD01Zdata) {
		PD01Zdata = pD01Zdata;
	}
	public List<PD01ZH> getPD01ZHList() {
		return PD01ZHList;
	}
	public void setPD01ZHList(List<PD01ZH> pD01ZHList) {
		PD01ZHList = pD01ZHList;
	}
	
}
