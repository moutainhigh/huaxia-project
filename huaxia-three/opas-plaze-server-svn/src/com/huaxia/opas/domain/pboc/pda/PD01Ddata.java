package com.huaxia.opas.domain.pboc.pda;

import java.io.Serializable;
/**
 * 最近24个月还款记录信息段数据
 * @author gaoh
 *
 */
public class PD01Ddata implements Serializable {
	
	private static final long serialVersionUID = -626403895039524512L;
	private String  PD01DR01 ;//起始年月--   [1..1]  
	private String  PD01DR02 ;//截止年月--   [1..1]  
	private String  appId;//申请件编号
	private String  relateId;//每个 借贷账户信息单元 的关联id
	public String getPD01DR01() {
		return PD01DR01;
	}
	public void setPD01DR01(String pD01DR01) {
		PD01DR01 = pD01DR01;
	}
	public String getPD01DR02() {
		return PD01DR02;
	}
	public void setPD01DR02(String pD01DR02) {
		PD01DR02 = pD01DR02;
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