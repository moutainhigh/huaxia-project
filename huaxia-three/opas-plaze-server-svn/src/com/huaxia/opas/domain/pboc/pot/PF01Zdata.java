package com.huaxia.opas.domain.pboc.pot;

import java.io.Serializable;
/**
 * 标注及声明信息段数据
 * @author gaoh
 *
 */
public class PF01Zdata implements Serializable {
	
	private static final long serialVersionUID = 5284104833870094063L;
	private String PF01ZS01;//标注及声明个数  --  [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个欠税记录信息单元的关联id
	public String getPF01ZS01() {
		return PF01ZS01;
	}

	public void setPF01ZS01(String pF01ZS01) {
		PF01ZS01 = pF01ZS01;
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
