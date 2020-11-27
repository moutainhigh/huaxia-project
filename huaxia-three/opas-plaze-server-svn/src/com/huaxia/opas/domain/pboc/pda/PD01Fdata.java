package com.huaxia.opas.domain.pboc.pda;



import java.io.Serializable;
/**
 * 特殊交易信息段数据
 * @author gaoh
 *
 */
public class PD01Fdata implements Serializable {
	
	private static final long serialVersionUID = 3843728572018124745L;
	private String PD01FS01;// 特殊交易个数  --  [1..1] 
	private String  appId;//申请件编号
	private String  relateId;//每个 借贷账户信息单元 的关联id
	public String getPD01FS01() {
		return PD01FS01;
	}

	public void setPD01FS01(String pD01FS01) {
		PD01FS01 = pD01FS01;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getRelateId() {
		return relateId;
	}

	public void setRelateId(String relateId) {
		this.relateId = relateId;
	}
	
}