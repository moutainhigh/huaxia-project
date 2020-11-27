package com.huaxia.opas.domain.pboc.pbs;

import java.io.Serializable;
/**
 * 标注及声明信息段数据
 * @author gaoh
 *
 */
public class PF06Zdata implements Serializable {
	 
	private static final long serialVersionUID = -8695403984911947721L;
	private String PF06ZS01;//标注及声明个数  --  [1..1]  
	private String appId;//申请件编号
	private String relateId;//每个低保救助记录的关联id
	public String getPF06ZS01() {
		return PF06ZS01;
	}

	public void setPF06ZS01(String pF06ZS01) {
		PF06ZS01 = pF06ZS01;
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
