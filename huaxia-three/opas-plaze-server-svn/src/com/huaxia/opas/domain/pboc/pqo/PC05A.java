package com.huaxia.opas.domain.pboc.pqo;

import java.io.Serializable;
/**
 * 上一次查询记录信息段
 * @author gaoh
 *
 */
public class PC05A implements Serializable {

	private static final long serialVersionUID = 2252208656944738986L;
	private String PC05AR01;//上一次查询日期   --  [1..1] 
	private String PC05AD01;//上一次查询机构机构类型    --  [1..1]  
	private String PC05AI01;//上一次查询机构代码    --  [1..1]  
	private String PC05AQ01;//上一次查询原因   --  [1..1]
	private String appId;//申请件编号
	public String getPC05AR01() {
		return PC05AR01;
	}
	public void setPC05AR01(String pC05AR01) {
		PC05AR01 = pC05AR01;
	}
	public String getPC05AD01() {
		return PC05AD01;
	}
	public void setPC05AD01(String pC05AD01) {
		PC05AD01 = pC05AD01;
	}
	public String getPC05AI01() {
		return PC05AI01;
	}
	public void setPC05AI01(String pC05AI01) {
		PC05AI01 = pC05AI01;
	}
	public String getPC05AQ01() {
		return PC05AQ01;
	}
	public void setPC05AQ01(String pC05AQ01) {
		PC05AQ01 = pC05AQ01;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

}