package com.huaxia.opas.domain.pboc.pca;

import java.io.Serializable;
/**
 * 标注及声明信息
 * @author gaoh
 *
 */
public class PD02ZH implements Serializable {

	private static final long serialVersionUID = -960964191815006111L;
	private String PD02ZD01;// 标注及声明类型  --  [1..1] 
	private String PD02ZQ01;//标注或声明内容  --  [1..1] 
	private String PD02ZR01;//添加日期  --  [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个 授信协议信息单元 的关联id
	public String getPD02ZD01() {
		return PD02ZD01;
	}
	public void setPD02ZD01(String pD02ZD01) {
		PD02ZD01 = pD02ZD01;
	}
	public String getPD02ZQ01() {
		return PD02ZQ01;
	}
	public void setPD02ZQ01(String pD02ZQ01) {
		PD02ZQ01 = pD02ZQ01;
	}
	public String getPD02ZR01() {
		return PD02ZR01;
	}
	public void setPD02ZR01(String pD02ZR01) {
		PD02ZR01 = pD02ZR01;
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
