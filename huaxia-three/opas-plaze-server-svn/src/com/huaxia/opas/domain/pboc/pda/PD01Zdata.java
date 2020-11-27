package com.huaxia.opas.domain.pboc.pda;

import java.io.Serializable;
/**
 * 标注及声明信息段数据
 * @author gaoh
 *
 */
public class PD01Zdata implements Serializable {
	private static final long serialVersionUID = 1225832326001756608L;
	private String PD01ZS01;//标注及声明个数  -- [1..1]  
	private String appId;//申请件编号
	private String relateId;//每个 借贷账户信息单元 的关联id
	public String getPD01ZS01() {
		return PD01ZS01;
	}

	public void setPD01ZS01(String pD01ZS01) {
		PD01ZS01 = pD01ZS01;
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
