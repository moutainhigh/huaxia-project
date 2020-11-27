package com.huaxia.opas.domain.pboc.phf;

import java.io.Serializable;
/**
 * 标注及声明信息
 * @author gaoh
 *
 */
public class PF05ZH implements Serializable {
	 
	private static final long serialVersionUID = -4806831381831206669L;
	private String PF05ZD01;//标注及声明类型  --  [1..1] 
	private String PF05ZQ01;// 标注或声明内容   --  [1..1]  
	private String PF05ZR01;//添加日期  --  [1..1]
	private String appId;//申请件编号
	private String relateId;//每个住房公积金参缴记录的关联id
	public String getPF05ZD01() {
		return PF05ZD01;
	}
	public void setPF05ZD01(String pF05ZD01) {
		PF05ZD01 = pF05ZD01;
	}
	public String getPF05ZQ01() {
		return PF05ZQ01;
	}
	public void setPF05ZQ01(String pF05ZQ01) {
		PF05ZQ01 = pF05ZQ01;
	}
	public String getPF05ZR01() {
		return PF05ZR01;
	}
	public void setPF05ZR01(String pF05ZR01) {
		PF05ZR01 = pF05ZR01;
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
