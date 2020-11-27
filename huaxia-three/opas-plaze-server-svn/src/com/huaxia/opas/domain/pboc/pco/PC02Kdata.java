package com.huaxia.opas.domain.pboc.pco;

import java.io.Serializable;
/**
 * 相关还款责任汇总信息段数据
 * @author gaoh
 *
 */
public class PC02Kdata implements Serializable {
	private static final long serialVersionUID = -2849566618296452006L;
	private String PC02KS01;//相关还款责任个数    [1..1]  -- 
	private String   appId;//申请件编号
	public String getPC02KS01() {
		return PC02KS01;
	}

	public void setPC02KS01(String pC02KS01) {
		PC02KS01 = pC02KS01;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}