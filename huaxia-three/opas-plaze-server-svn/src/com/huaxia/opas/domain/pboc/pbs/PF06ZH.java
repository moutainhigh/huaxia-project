package com.huaxia.opas.domain.pboc.pbs;

import java.io.Serializable;
/**
 * 标注及声明信息 
 * @author gaoh
 *
 */
public class PF06ZH implements Serializable {
	 
	private static final long serialVersionUID = 8224961524828420235L;
	private String PF06ZD01;//标注及声明类型  --  [1..1]  
	private String PF06ZQ01;//标注或声明内容  --  [1..1]  
	private String PF06ZR01;// 添加日期  --  [1..1]  
	private String appId;//申请件编号
	private String relateId;//每个低保救助记录的关联id
	public String getPF06ZD01() {
		return PF06ZD01;
	}
	public void setPF06ZD01(String pF06ZD01) {
		PF06ZD01 = pF06ZD01;
	}
	public String getPF06ZQ01() {
		return PF06ZQ01;
	}
	public void setPF06ZQ01(String pF06ZQ01) {
		PF06ZQ01 = pF06ZQ01;
	}
	public String getPF06ZR01() {
		return PF06ZR01;
	}
	public void setPF06ZR01(String pF06ZR01) {
		PF06ZR01 = pF06ZR01;
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
