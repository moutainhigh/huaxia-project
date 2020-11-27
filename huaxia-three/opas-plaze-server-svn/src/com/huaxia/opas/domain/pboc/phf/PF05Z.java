package com.huaxia.opas.domain.pboc.phf;

import java.io.Serializable;
import java.util.List;
/**
 * 标注及声明信息段   [0..1]
 * @author gaoh
 *
 */
public class PF05Z implements Serializable {
	 
	private static final long serialVersionUID = -6557158050319291231L;
	private PF05Zdata PF05Zdata;//标注及声明信息段数据
	private List<PF05ZH> PF05ZHList;//标注及声明信息   [1..5]
	public PF05Zdata getPF05Zdata() {
		return PF05Zdata;
	}
	public void setPF05Zdata(PF05Zdata pF05Zdata) {
		PF05Zdata = pF05Zdata;
	}
	public List<PF05ZH> getPF05ZHList() {
		return PF05ZHList;
	}
	public void setPF05ZHList(List<PF05ZH> pF05ZHList) {
		PF05ZHList = pF05ZHList;
	}
	
}
