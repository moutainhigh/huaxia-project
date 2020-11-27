package com.huaxia.opas.domain.pboc.ppq;

import java.io.Serializable;
/**
 * 标注及声明信息段数据
 * @author gaoh
 *
 */
public class PF07Zdata implements Serializable {
	private static final long serialVersionUID = -8329629882912983351L;
	private String PF07ZS01;//标注及声明个数  --  [1..1]
	private String appId;//申请件编号
	private String relateId;//每个执业资格记录的关联id
	public String getPF07ZS01() {
		return PF07ZS01;
	}

	public void setPF07ZS01(String pF07ZS01) {
		PF07ZS01 = pF07ZS01;
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
