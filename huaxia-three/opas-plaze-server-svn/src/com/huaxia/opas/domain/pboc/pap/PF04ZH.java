package com.huaxia.opas.domain.pboc.pap;

import java.io.Serializable;
/**
 * 标注及声明信息
 * @author gaoh
 *
 */
public class PF04ZH implements Serializable {
	 
	private static final long serialVersionUID = -3128190402212364484L;
	private String PF04ZD01;// 标注及声明类型  --  [1..1] 
	private String PF04ZQ01;//标注或声明内容  --  [1..1] 
	private String PF04ZR01;//添加日期  --  [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个行政处罚记录信息单元的关联id
	public String getPF04ZD01() {
		return PF04ZD01;
	}
	public void setPF04ZD01(String pF04ZD01) {
		PF04ZD01 = pF04ZD01;
	}
	public String getPF04ZQ01() {
		return PF04ZQ01;
	}
	public void setPF04ZQ01(String pF04ZQ01) {
		PF04ZQ01 = pF04ZQ01;
	}
	public String getPF04ZR01() {
		return PF04ZR01;
	}
	public void setPF04ZR01(String pF04ZR01) {
		PF04ZR01 = pF04ZR01;
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
