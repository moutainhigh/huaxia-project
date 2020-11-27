package com.huaxia.opas.domain.pboc.pco;

import java.io.Serializable;
/**
 * 信贷交易提示信息段数据
 * @author gaoh
 *
 */
public class PC02Adata implements Serializable {
	private static final long serialVersionUID = -1043692982560145708L;
	private String  PC02AS01;//  账户数合计     [1..1]  --
	private String  PC02AS02;//  业务类型数量   [1..1]   -- 
	private String  appId;// 申请件编号
	
	public String getPC02AS01() {
		return PC02AS01;
	}
	public void setPC02AS01(String pC02AS01) {
		PC02AS01 = pC02AS01;
	}
	public String getPC02AS02() {
		return PC02AS02;
	}
	public void setPC02AS02(String pC02AS02) {
		PC02AS02 = pC02AS02;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

}