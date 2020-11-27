package com.huaxia.opas.domain.pboc.pos;

import java.io.Serializable;
/**
 * 标注及声明信息单元数据
 * @author gaoh
 *
 */
public class PG01data implements Serializable {
	 
	private static final long serialVersionUID = 7611949268113428559L;
	private String PG010D01;// 对象类型  --  [1..1] 
	private String PG010D02;//对象标识  --   [1..1]  
	private String PG010S01;//标注及声明类型个数  --    [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个其他标注及声明的 关联id
	public String getPG010D01() {
		return PG010D01;
	}
	public void setPG010D01(String pG010D01) {
		PG010D01 = pG010D01;
	}
	public String getPG010D02() {
		return PG010D02;
	}
	public void setPG010D02(String pG010D02) {
		PG010D02 = pG010D02;
	}
	public String getPG010S01() {
		return PG010S01;
	}
	public void setPG010S01(String pG010S01) {
		PG010S01 = pG010S01;
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
