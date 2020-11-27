package com.huaxia.opas.domain.pboc.pda;

import java.io.Serializable;
/**
 * 特殊事件说明信息段数据
 * @author gaoh
 *
 */
public class PD01Gdata implements Serializable {
	
	private static final long serialVersionUID = -8429072372627049908L;
	private String PD01GS01;//特殊事件说明个数  --  [1..1]
	private String  appId;//申请件编号
	private String  relateId;//每个 借贷账户信息单元 的关联id
	public String getPD01GS01() {
		return PD01GS01;
	}

	public void setPD01GS01(String pD01GS01) {
		PD01GS01 = pD01GS01;
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