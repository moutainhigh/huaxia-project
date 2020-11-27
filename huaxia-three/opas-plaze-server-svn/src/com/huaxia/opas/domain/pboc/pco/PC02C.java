package com.huaxia.opas.domain.pboc.pco;

import java.io.Serializable;
/**
 * 呆账汇总信息段
 * @author gaoh
 *
 */
public class PC02C implements Serializable {
	private static final long serialVersionUID = -6158938942848389448L;
	private String  PC02CS01 ;//账户数 [1..1]  -- 
	private String  PC02CJ01 ;//  余额    [1..1]  -- 
	private String  appId;//申请件编号
	
	public String getPC02CS01() {
		return PC02CS01;
	}
	public void setPC02CS01(String pC02CS01) {
		PC02CS01 = pC02CS01;
	}
	public String getPC02CJ01() {
		return PC02CJ01;
	}
	public void setPC02CJ01(String pC02CJ01) {
		PC02CJ01 = pC02CJ01;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}