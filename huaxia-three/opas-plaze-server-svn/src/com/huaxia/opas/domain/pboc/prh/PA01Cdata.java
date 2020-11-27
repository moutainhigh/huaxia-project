package com.huaxia.opas.domain.pboc.prh;

import java.io.Serializable;

/**
 * 其他身份标识信息段数据
 * @author gaoh
 *
 */
public class PA01Cdata implements Serializable {
	private static final long serialVersionUID = -1309561554512117028L;
	private String PA01CS01 ; //身份标识个数
	private String appId ;//申请件编号
	
	public String getPA01CS01() {
		return PA01CS01;
	}

	public void setPA01CS01(String pA01CS01) {
		PA01CS01 = pA01CS01;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}
