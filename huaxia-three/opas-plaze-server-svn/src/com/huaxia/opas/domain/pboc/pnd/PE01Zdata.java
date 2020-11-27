package com.huaxia.opas.domain.pboc.pnd;

import java.io.Serializable;
/**
 * 
 * @author gaoh
 *
 */
public class PE01Zdata implements Serializable {
 
	private static final long serialVersionUID = 1463525058720204861L;
	private String PE01ZS01;//标注及声明个数  --  [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个后付费业务信息单元的关联id
	public String getPE01ZS01() {
		return PE01ZS01;
	}

	public void setPE01ZS01(String pE01ZS01) {
		PE01ZS01 = pE01ZS01;
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
