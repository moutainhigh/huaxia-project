package com.huaxia.opas.domain.pboc.ppq;

import java.io.Serializable;
/**
 * 标注及声明信息 
 * @author gaoh
 *
 */
public class PF07ZH implements Serializable {
	 
	private static final long serialVersionUID = -7564281615849556862L;
	private String PF07ZD01;// 标注及声明类型  --  [1..1] 
	private String PF07ZQ01;//标注或声明内容  --  [1..1] 
	private String PF07ZR01;//添加日期  --  [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个执业资格记录的关联id
	public String getPF07ZD01() {
		return PF07ZD01;
	}
	public void setPF07ZD01(String pF07ZD01) {
		PF07ZD01 = pF07ZD01;
	}
	public String getPF07ZQ01() {
		return PF07ZQ01;
	}
	public void setPF07ZQ01(String pF07ZQ01) {
		PF07ZQ01 = pF07ZQ01;
	}
	public String getPF07ZR01() {
		return PF07ZR01;
	}
	public void setPF07ZR01(String pF07ZR01) {
		PF07ZR01 = pF07ZR01;
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
