package com.huaxia.opas.domain.pboc.pno;

import java.io.Serializable;
/**
 * 后付费业务欠信息汇总信息 PC030H 
 * @author gaoh
 *
 */
public class PC030H implements Serializable{

	private static final long serialVersionUID = 4559727119080723347L;
	private String PC030D01;//后付费业务类型  --  [1..1]
	private String PC030S02;//欠费账户数  --  [1..1]
    private String PC030J01;//欠费金额  --   [1..1] 
    private String appId;//申请件编号
	public String getPC030D01() {
		return PC030D01;
	}

	public void setPC030D01(String pC030D01) {
		PC030D01 = pC030D01;
	}

	public String getPC030S02() {
		return PC030S02;
	}

	public void setPC030S02(String pC030S02) {
		PC030S02 = pC030S02;
	}

	public String getPC030J01() {
		return PC030J01;
	}

	public void setPC030J01(String pC030J01) {
		PC030J01 = pC030J01;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}