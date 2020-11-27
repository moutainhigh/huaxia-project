package com.huaxia.opas.domain.pboc.prh;

import java.io.Serializable;
/**
 * 异议提示信息段
 * @author gaoh
 *
 */
public class PA01E implements Serializable {
	private static final long serialVersionUID = -7696735891289409617L;
	private String PA01ES01  ;//异议标注数目[1..1]  --
	private String appId;//申请件编号
	
	public String getPA01ES01() {
		return PA01ES01;
	}

	public void setPA01ES01(String pA01ES01) {
		PA01ES01 = pA01ES01;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}
