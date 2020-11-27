package com.huaxia.opas.domain.pboc.pcj;

import java.io.Serializable;
/**
 * 标注及声明信息
 * @author gaoh
 *
 */
public class PF02ZH implements Serializable {
	
	private static final long serialVersionUID = -6836942818637382306L;
	private String PF02ZD01;//标注及声明类型  --  [1..1] 
	private String PF02ZQ01;//标注或声明内容  --  [1..1] 
	private String PF02ZR01;//添加日期  --  [1..1]
	private String appId;//申请件编号
	private String relateId;//每个民事判决记录信息单元的关联id
	public String getPF02ZD01() {
		return PF02ZD01;
	}
	public void setPF02ZD01(String pF02ZD01) {
		PF02ZD01 = pF02ZD01;
	}
	public String getPF02ZQ01() {
		return PF02ZQ01;
	}
	public void setPF02ZQ01(String pF02ZQ01) {
		PF02ZQ01 = pF02ZQ01;
	}
	public String getPF02ZR01() {
		return PF02ZR01;
	}
	public void setPF02ZR01(String pF02ZR01) {
		PF02ZR01 = pF02ZR01;
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
