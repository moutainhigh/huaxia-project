package com.huaxia.opas.domain.pboc.pda;



import java.io.Serializable;
/**
 * 还款状态信息
 * @author gaoh
 *
 */
public class PD01DH implements Serializable {
	
	private static final long serialVersionUID = 2682428909140487022L;
	private String PD01DR03;// 月份  --   [1..1]  
	private String PD01DD01;//还款状态 --   [1..1]  
	private String  appId;//申请件编号
	private String  relateId;//每个 借贷账户信息单元 的关联id
	public String getPD01DR03() {
		return PD01DR03;
	}
	public void setPD01DR03(String pD01DR03) {
		PD01DR03 = pD01DR03;
	}
	public String getPD01DD01() {
		return PD01DD01;
	}
	public void setPD01DD01(String pD01DD01) {
		PD01DD01 = pD01DD01;
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