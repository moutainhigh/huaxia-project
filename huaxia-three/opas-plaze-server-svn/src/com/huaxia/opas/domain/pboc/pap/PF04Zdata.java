package com.huaxia.opas.domain.pboc.pap;

import java.io.Serializable;
/**
 * 标注及声明信息段数据
 * @author gaoh
 *
 */
public class PF04Zdata implements Serializable {
	
	private static final long serialVersionUID = 5880070003385687658L;
	private String PF04ZS01;//标注及声明个数  --  [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个行政处罚记录信息单元的关联id
	public String getPF04ZS01() {
		return PF04ZS01;
	}

	public void setPF04ZS01(String pF04ZS01) {
		PF04ZS01 = pF04ZS01;
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
