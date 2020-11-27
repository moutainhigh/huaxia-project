package com.huaxia.opas.domain.pboc.pcj;

import java.io.Serializable;
/**
 * 标注及声明信息段数据
 * @author gaoh
 *
 */
public class PF02Zdata implements Serializable {
	
	private static final long serialVersionUID = 7900915999096626735L;
	private String PF02ZS01;//标注及声明个数  --  [1..1]  
	private String appId;//申请件编号
	private String relateId;//每个民事判决记录信息单元的关联id
	public String getPF02ZS01() {
		return PF02ZS01;
	}

	public void setPF02ZS01(String pF02ZS01) {
		PF02ZS01 = pF02ZS01;
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
