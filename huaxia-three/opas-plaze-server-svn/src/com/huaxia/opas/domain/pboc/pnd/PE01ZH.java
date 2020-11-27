package com.huaxia.opas.domain.pboc.pnd;

import java.io.Serializable;
/**
 * 标注及声明信息   
 * @author gaoh
 *
 */
public class PE01ZH implements Serializable{
	
	private static final long serialVersionUID = -4301137583556519444L;
	private String PE01ZD01;//标注及声明类型  --  [1..1]  
	private String PE01ZQ01;//标注或声明内容  --  [1..1]  
    private String PE01ZR01;//添加日期  --  [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个后付费业务信息单元的关联id
	public String getPE01ZD01() {
		return PE01ZD01;
	}
	public void setPE01ZD01(String pE01ZD01) {
		PE01ZD01 = pE01ZD01;
	}
	public String getPE01ZQ01() {
		return PE01ZQ01;
	}
	public void setPE01ZQ01(String pE01ZQ01) {
		PE01ZQ01 = pE01ZQ01;
	}
	public String getPE01ZR01() {
		return PE01ZR01;
	}
	public void setPE01ZR01(String pE01ZR01) {
		PE01ZR01 = pE01ZR01;
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
