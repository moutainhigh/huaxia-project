package com.huaxia.opas.domain.pboc.pcr;

import java.io.Serializable;
/**
 * 标注及声明信息 
 * @author gaoh
 *
 */
public class PD03ZH implements Serializable {
	
	private static final long serialVersionUID = 5482759477443883361L;
	private String PD03ZD01;// 标注及声明类型   [1..1]   --
	private String PD03ZQ01;//标注或声明内容   [1..1]  --  
	private String PD03ZR01;//添加日期   [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个相关还款责任信息单元的关联id
	public String getPD03ZD01() {
		return PD03ZD01;
	}
	public void setPD03ZD01(String pD03ZD01) {
		PD03ZD01 = pD03ZD01;
	}
	public String getPD03ZQ01() {
		return PD03ZQ01;
	}
	public void setPD03ZQ01(String pD03ZQ01) {
		PD03ZQ01 = pD03ZQ01;
	}
	public String getPD03ZR01() {
		return PD03ZR01;
	}
	public void setPD03ZR01(String pD03ZR01) {
		PD03ZR01 = pD03ZR01;
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
