package com.huaxia.opas.domain.pboc.prh;

import java.io.Serializable;

/**
 * 防欺诈警示信息段
 * @author  gaoh
 *
 */
public class PA01D implements Serializable {
	private static final long serialVersionUID = 5545253490253210817L;
	private String  PA01DQ01 ;//防欺诈警示标志   [1..1]  --
	private String  PA01DQ02 ;//防欺诈警示联系电话   [1..1]  --
	private String  PA01DR01; //防欺诈警示生效日期    [1..1]  --
	private String  PA01DR02 ;// 防欺诈警示截止日期   [1..1]  --
	private String  appId;//申请件编号
	public String getPA01DQ01() {
		return PA01DQ01;
	}
	public void setPA01DQ01(String pA01DQ01) {
		PA01DQ01 = pA01DQ01;
	}
	public String getPA01DQ02() {
		return PA01DQ02;
	}
	public void setPA01DQ02(String pA01DQ02) {
		PA01DQ02 = pA01DQ02;
	}
	public String getPA01DR01() {
		return PA01DR01;
	}
	public void setPA01DR01(String pA01DR01) {
		PA01DR01 = pA01DR01;
	}
	public String getPA01DR02() {
		return PA01DR02;
	}
	public void setPA01DR02(String pA01DR02) {
		PA01DR02 = pA01DR02;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}