package com.huaxia.opas.domain.pboc.pca;

import java.io.Serializable;
/**
 * 标注及声明信息段数据
 * @author gaoh
 *
 */
public class PD02Zdata implements Serializable {
	private static final long serialVersionUID = 4576206670453225449L;
	private String PD02ZS01;//标注及声明个数  --  [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个 授信协议信息单元 的关联id
	public String getPD02ZS01() {
		return PD02ZS01;
	}

	public void setPD02ZS01(String pD02ZS01) {
		PD02ZS01 = pD02ZS01;
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
