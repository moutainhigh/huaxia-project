package com.huaxia.opas.domain.pboc.phf;

import java.io.Serializable;
/**
 * 标注及声明信息段数据
 * @author gaoh
 *
 */
public class PF05Zdata implements Serializable {
	 
	private static final long serialVersionUID = 8760454827647940960L;
	private String PF05ZS01;//标注及声明个数  -- 
	private String appId;//申请件编号
	private String relateId;//每个住房公积金参缴记录的关联id
	public String getPF05ZS01() {
		return PF05ZS01;
	}

	public void setPF05ZS01(String pF05ZS01) {
		PF05ZS01 = pF05ZS01;
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
