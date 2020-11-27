package com.huaxia.opas.domain.pboc.prh;

import java.io.Serializable;

/**
 * 报告标识信息段
 * @author gaoh
 *
 */
public class PA01A implements Serializable {
	private static final long serialVersionUID = -7621264416781901192L;
	private String PA01AI01;//报告编号
	private String PA01AR01;//报告时间
	public String getPA01AI01() {
		return PA01AI01;
	}
	public void setPA01AI01(String pA01AI01) {
		PA01AI01 = pA01AI01;
	}
	public String getPA01AR01() {
		return PA01AR01;
	}
	public void setPA01AR01(String pA01AR01) {
		PA01AR01 = pA01AR01;
	}
	
}
