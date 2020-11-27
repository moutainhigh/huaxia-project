package com.huaxia.opas.domain.pboc.ppo;

import java.io.Serializable;
/**
 * 公共信息概要信息单元数据
 * @author gaoh
 *
 */
public class PC04data implements Serializable {

	private static final long serialVersionUID = -6295833286356028362L;
	private String PC040S01;//公共信息类型数量  -- [1..1]
	private String appId;//申请件编号
	
	public String getPC040S01() {
		return PC040S01;
	}

	public void setPC040S01(String pC040S01) {
		PC040S01 = pC040S01;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}