package com.huaxia.opas.domain.pboc.pno;

import java.io.Serializable;
/**
 * 非信贷交易信息概要数据
 * @author gaoh
 *
 */
public class PC03data implements Serializable{

	private static final long serialVersionUID = -8090907267002195467L;
	private String PC030S01;//后付费业务类型数量  --
	private String appId;//申请件编号
	
	public String getPC030S01() {
		return PC030S01;
	}

	public void setPC030S01(String pC030S01) {
		PC030S01 = pC030S01;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}