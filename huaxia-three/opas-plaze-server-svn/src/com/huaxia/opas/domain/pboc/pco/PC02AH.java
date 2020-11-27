package com.huaxia.opas.domain.pboc.pco;

import java.io.Serializable;

/**
 * 信贷交易提示信息
 * @author gaoh
 *
 */
public class PC02AH implements Serializable {
	
	private static final long serialVersionUID = 8580992020180703690L;
	private String  PC02AD01;// 业务类型   [1..1]  -- 
	private String  PC02AD02;// 业务大类   [1..1]   --  
	private String  PC02AS03;// 账户数    [1..1]   --  
	private String  PC02AR01;// 首笔业务发放月份     [1..1]   -- 
	private String  appId ;//申请件编号
	public String getPC02AD01() {
		return PC02AD01;
	}
	public void setPC02AD01(String pC02AD01) {
		PC02AD01 = pC02AD01;
	}
	public String getPC02AD02() {
		return PC02AD02;
	}
	public void setPC02AD02(String pC02AD02) {
		PC02AD02 = pC02AD02;
	}
	public String getPC02AS03() {
		return PC02AS03;
	}
	public void setPC02AS03(String pC02AS03) {
		PC02AS03 = pC02AS03;
	}
	public String getPC02AR01() {
		return PC02AR01;
	}
	public void setPC02AR01(String pC02AR01) {
		PC02AR01 = pC02AR01;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

}
