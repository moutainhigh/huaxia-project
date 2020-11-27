package com.huaxia.opas.domain.pboc.pce;

import java.io.Serializable;
/**
 * 标注及声明信息段数据
 * @author gaoh
 *
 */
public class PF03Zdata implements Serializable {
	
	private static final long serialVersionUID = 772235924842041806L;
	private String PF03ZS01;//标注及声明个数  --   [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个强制执行记录信息单元的关联id
	public String getPF03ZS01() {
		return PF03ZS01;
	}

	public void setPF03ZS01(String pF03ZS01) {
		PF03ZS01 = pF03ZS01;
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
