package com.huaxia.opas.domain.pboc.pim;

import java.io.Serializable;

/**
 * 手机号码信息段数据
 * @author gaoh
 *
 */
public class PB01Bdata implements Serializable {
	private static final long serialVersionUID = -5271653485780324565L;
	private String PB01BS01 ;//手机号码个数 [1..1] 

	private String appId;//申请件编号
	
	public String getPB01BS01() {
		return PB01BS01;
	}

	public void setPB01BS01(String pB01BS01) {
		PB01BS01 = pB01BS01;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}
