package com.huaxia.opas.domain.allot;

import java.io.Serializable;
import java.util.Date;
/**
 * 存储过程表
 * @author wangdebin
 */
public class AllotAppId implements Serializable{
	
	private static final long serialVersionUID = -7197860198951105416L;

	private String appId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}
