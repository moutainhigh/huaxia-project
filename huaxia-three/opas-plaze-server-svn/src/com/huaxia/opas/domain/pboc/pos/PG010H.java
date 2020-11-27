package com.huaxia.opas.domain.pboc.pos;

import java.io.Serializable;
/**
 * 标注及声明信息
 * @author gaoh
 *
 */
public class PG010H implements Serializable {
	 
	private static final long serialVersionUID = 7114229609349959416L;
	private String PG010D03;//标注及声明类型  --   [1..1] 
	private String PG010Q01;// 标注或声明内容  --   [1..1]  
	private String PG010R01;//添加日期  --   [1..1]  
	private String appId;//申请件编号
	private String relateId;//每个其他标注及声明的 关联id
	public String getPG010D03() {
		return PG010D03;
	}
	public void setPG010D03(String pG010D03) {
		PG010D03 = pG010D03;
	}
	public String getPG010Q01() {
		return PG010Q01;
	}
	public void setPG010Q01(String pG010Q01) {
		PG010Q01 = pG010Q01;
	}
	public String getPG010R01() {
		return PG010R01;
	}
	public void setPG010R01(String pG010R01) {
		PG010R01 = pG010R01;
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
