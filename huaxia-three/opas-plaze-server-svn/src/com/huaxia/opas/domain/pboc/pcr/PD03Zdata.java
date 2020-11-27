package com.huaxia.opas.domain.pboc.pcr;

import java.io.Serializable;
/**
 * 标注及声明信息段数据
 * @author gaoh
 *
 */
public class PD03Zdata implements Serializable {
	
	private static final long serialVersionUID = 9188345227267447465L;
	private String PD03ZS01;// 标注及声明个数     [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个相关还款责任信息单元的关联id
	public String getPD03ZS01() {
		return PD03ZS01;
	}
	public void setPD03ZS01(String pD03ZS01) {
		PD03ZS01 = pD03ZS01;
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