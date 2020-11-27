package com.huaxia.opas.domain.pboc.pda;

import java.io.Serializable;
/**
 * 大额专项分期信息段
 * @author gaoh
 *
 */
public class PD01Hdata implements Serializable {
	
	private static final long serialVersionUID = 1768105925097029139L;
	private String PD01HS01;//大额专项分期笔数  --  [1..1]   
	private String  appId;//申请件编号
	private String  relateId;//每个 借贷账户信息单元 的关联id
	public String getPD01HS01() {
		return PD01HS01;
	}

	public void setPD01HS01(String pD01HS01) {
		PD01HS01 = pD01HS01;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getRelateId() {
		return relateId;
	}

	public void setRelateId(String relateId) {
		this.relateId = relateId;
	}
	
}
