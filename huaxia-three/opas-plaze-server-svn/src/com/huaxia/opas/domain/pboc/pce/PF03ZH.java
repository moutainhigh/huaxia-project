package com.huaxia.opas.domain.pboc.pce;

import java.io.Serializable;
/**
 * 标注及声明信息
 * @author gaoh
 *
 */
public class PF03ZH implements Serializable {

	private static final long serialVersionUID = 1685346252328338104L;
	private String PF03ZD01;//标注及声明类型   --   [1..1]  
	private String PF03ZQ01;//标注或声明内容  --   [1..1]  
	private String PF03ZR01;//添加日期  --   [1..1]  
	private String appId;//申请件编号
	private String relateId;//每个强制执行记录信息单元的关联id
	public String getPF03ZD01() {
		return PF03ZD01;
	}
	public void setPF03ZD01(String pF03ZD01) {
		PF03ZD01 = pF03ZD01;
	}
	public String getPF03ZQ01() {
		return PF03ZQ01;
	}
	public void setPF03ZQ01(String pF03ZQ01) {
		PF03ZQ01 = pF03ZQ01;
	}
	public String getPF03ZR01() {
		return PF03ZR01;
	}
	public void setPF03ZR01(String pF03ZR01) {
		PF03ZR01 = pF03ZR01;
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
