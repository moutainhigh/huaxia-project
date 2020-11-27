package com.huaxia.opas.domain.pboc.pco;

import java.io.Serializable;
/**
 * 被追偿汇总信息
 * @author gaoh
 *
 */
public class PC02BH implements Serializable {
	private static final long serialVersionUID = -7329114917982193349L;
	private String  PC02BD01;// 业务类型   [1..1]  -- 
	private String  PC02BS03;//账户数   [1..1]  -- 
	private String  PC02BJ02;//余额    [1..1]  --
	private String appId;//申请件编号
	public String getPC02BD01() {
		return PC02BD01;
	}
	public void setPC02BD01(String pC02BD01) {
		PC02BD01 = pC02BD01;
	}
	public String getPC02BS03() {
		return PC02BS03;
	}
	public void setPC02BS03(String pC02BS03) {
		PC02BS03 = pC02BS03;
	}
	public String getPC02BJ02() {
		return PC02BJ02;
	}
	public void setPC02BJ02(String pC02BJ02) {
		PC02BJ02 = pC02BJ02;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

}