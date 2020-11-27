package com.huaxia.opas.domain.pboc.pco;

import java.io.Serializable;
/**
 * 逾期（透支）汇总信息段数据
 * @author gaoh
 *
 */
public class PC02Ddata implements Serializable {
	private static final long serialVersionUID = -6617430205626546784L;
	private String PC02DS01;//账户类型数量    [1..1]  -- 
	private String appId;//申请件编号

	public String getPC02DS01() {
		return PC02DS01;
	}

	public void setPC02DS01(String pC02DS01) {
		PC02DS01 = pC02DS01;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}