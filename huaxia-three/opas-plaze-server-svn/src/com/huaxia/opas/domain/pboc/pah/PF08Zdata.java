package com.huaxia.opas.domain.pboc.pah;

import java.io.Serializable;
/**
 * 标注及声明信息段数据
 * @author gaoh
 *
 */
public class PF08Zdata implements Serializable {
	 
	private static final long serialVersionUID = 1262356423247223288L;
	private String PF08ZS01;// 标注及声明个数  -- [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个行政奖励记录的关联id
	public String getPF08ZS01() {
		return PF08ZS01;
	}

	public void setPF08ZS01(String pF08ZS01) {
		PF08ZS01 = pF08ZS01;
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
