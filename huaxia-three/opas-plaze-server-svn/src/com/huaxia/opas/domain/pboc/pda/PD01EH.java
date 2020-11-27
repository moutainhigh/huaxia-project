package com.huaxia.opas.domain.pboc.pda;

import java.io.Serializable;
/**
 * 历史表现信息
 * @author gaoh
 *
 */
public class PD01EH implements Serializable {
	
	private static final long serialVersionUID = 4294401040511824509L;
	private String PD01ER03;// 月份  --   [1..1]  
	private String PD01ED01;// 还款状态  --   [1..1] 
	private String PD01EJ01;//逾期（透支）总额  --  
	private String  appId;//申请件编号
	private String  relateId;//每个 借贷账户信息单元 的关联id
	public String getPD01ER03() {
		return PD01ER03;
	}
	public void setPD01ER03(String pD01ER03) {
		PD01ER03 = pD01ER03;
	}
	public String getPD01ED01() {
		return PD01ED01;
	}
	public void setPD01ED01(String pD01ED01) {
		PD01ED01 = pD01ED01;
	}
	public String getPD01EJ01() {
		return PD01EJ01;
	}
	public void setPD01EJ01(String pD01EJ01) {
		PD01EJ01 = pD01EJ01;
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