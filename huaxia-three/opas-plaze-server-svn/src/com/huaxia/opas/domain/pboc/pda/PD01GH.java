package com.huaxia.opas.domain.pboc.pda;

import java.io.Serializable;
/**
 * 特殊事件说明信息
 * @author gaoh
 *
 */
public class PD01GH implements Serializable {
	private static final long serialVersionUID = -1019172099837025009L;
	private String PD01GR01;// 特殊事件发生月份 --  [1..1]  
	private String PD01GD01;// 特殊事件类型  --   [1..1] 
	private String  appId;//申请件编号
	private String  relateId;//每个 借贷账户信息单元 的关联id
	public String getPD01GR01() {
		return PD01GR01;
	}
	public void setPD01GR01(String pD01GR01) {
		PD01GR01 = pD01GR01;
	}
	public String getPD01GD01() {
		return PD01GD01;
	}
	public void setPD01GD01(String pD01GD01) {
		PD01GD01 = pD01GD01;
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