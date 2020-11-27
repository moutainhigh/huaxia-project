package com.huaxia.opas.domain.pboc.psm;

import java.io.Serializable;
/**
 * 评分信息分数说明
 * @author gaoh
 *
 */
public class PC01scoreEle implements Serializable {

	private static final long serialVersionUID = -6332951209351622804L;
	private String PC010D01;//分数说明  --    [1..2]
	private String appId;//申请件编号
	public String getPC010D01() {
		return PC010D01;
	}

	public void setPC010D01(String pC010D01) {
		PC010D01 = pC010D01;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}