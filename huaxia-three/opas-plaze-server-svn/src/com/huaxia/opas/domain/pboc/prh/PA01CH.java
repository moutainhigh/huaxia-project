package com.huaxia.opas.domain.pboc.prh;

import java.io.Serializable;

/**
 * 身份信息
 * @author gaoh
 *
 */
public class PA01CH implements Serializable {
	private static final long serialVersionUID = -7070511367197797444L;
	private String 	PA01CD01 ;//证件类型
	private String 	PA01CI01  ;//证件号码  
	private String  appId;//申请件编号
	public String getPA01CD01() {
		return PA01CD01;
	}
	public void setPA01CD01(String pA01CD01) {
		PA01CD01 = pA01CD01;
	}
	public String getPA01CI01() {
		return PA01CI01;
	}
	public void setPA01CI01(String pA01CI01) {
		PA01CI01 = pA01CI01;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}
