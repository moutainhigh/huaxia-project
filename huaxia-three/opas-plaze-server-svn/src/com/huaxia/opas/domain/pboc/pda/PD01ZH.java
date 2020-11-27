package com.huaxia.opas.domain.pboc.pda;

import java.io.Serializable;
/**
 * 标注及声明信息
 * @author gaoh
 *
 */
public class PD01ZH implements Serializable {
	
	private static final long serialVersionUID = 602411318051695423L;
	private String PD01ZD01;//标注及声明类型  --  [1..1] 
	private String PD01ZQ01;//标注或声明内容  --  [1..1] 
	private String PD01ZR01;//添加日期  --  [1..1]  
	private String appId;//申请件编号
	private String relateId;//每个 借贷账户信息单元 的关联id
	public String getPD01ZD01() {
		return PD01ZD01;
	}
	public void setPD01ZD01(String pD01ZD01) {
		PD01ZD01 = pD01ZD01;
	}
	public String getPD01ZQ01() {
		return PD01ZQ01;
	}
	public void setPD01ZQ01(String pD01ZQ01) {
		PD01ZQ01 = pD01ZQ01;
	}
	public String getPD01ZR01() {
		return PD01ZR01;
	}
	public void setPD01ZR01(String pD01ZR01) {
		PD01ZR01 = pD01ZR01;
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
