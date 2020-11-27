package com.huaxia.opas.domain.pboc.pah;

import java.io.Serializable;
import java.util.List;

/**
 * 标注及声明信息段
 * @author gaoh
 *
 */
public class PF08Z implements Serializable {
	 
	private static final long serialVersionUID = 3123930896045593011L;
	private PF08Zdata PF08Zdata;//标注及声明信息段数据
	private List<PF08ZH> PF08ZHList;//标注及声明信息   [1..5] 
	public PF08Zdata getPF08Zdata() {
		return PF08Zdata;
	}
	public void setPF08Zdata(PF08Zdata pF08Zdata) {
		PF08Zdata = pF08Zdata;
	}
	public List<PF08ZH> getPF08ZHList() {
		return PF08ZHList;
	}
	public void setPF08ZHList(List<PF08ZH> pF08ZHList) {
		PF08ZHList = pF08ZHList;
	}
	
}
