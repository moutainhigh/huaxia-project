package com.huaxia.opas.domain.pboc.pda;



import java.io.Serializable;
/**
 * 最近5年内历史表现信息段数据
 * @author gaoh
 *
 */
public class PD01Edata implements Serializable {
	
	private static final long serialVersionUID = -5187436581850652229L;
	private String PD01ER01;//起始年月  --   [1..1]  
	private String PD01ER02;//截止年月  --   [1..1]  
	private String PD01ES01;//月数  --   [1..1] 
	private String  appId;//申请件编号
	private String  relateId;//每个 借贷账户信息单元 的关联id
	public String getPD01ER01() {
		return PD01ER01;
	}
	public void setPD01ER01(String pD01ER01) {
		PD01ER01 = pD01ER01;
	}
	public String getPD01ER02() {
		return PD01ER02;
	}
	public void setPD01ER02(String pD01ER02) {
		PD01ER02 = pD01ER02;
	}
	public String getPD01ES01() {
		return PD01ES01;
	}
	public void setPD01ES01(String pD01ES01) {
		PD01ES01 = pD01ES01;
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