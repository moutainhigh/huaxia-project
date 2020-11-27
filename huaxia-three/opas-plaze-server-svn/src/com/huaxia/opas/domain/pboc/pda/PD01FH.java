package com.huaxia.opas.domain.pboc.pda;

import java.io.Serializable;
/**
 * 特殊交易信息
 * @author gaoh
 *
 */
public class PD01FH implements Serializable {
	
	private static final long serialVersionUID = 4276071114773650737L;
	private String PD01FD01;// 特殊交易类型 --  [1..1]  
	private String PD01FR01;// 特殊交易发生日期  --  [1..1] 
	private String PD01FS02;// 到期日期变更月数  --   [1..1] 
	private String PD01FJ01;// 特殊交易发生金额  --  [1..1] 
	private String PD01FQ01;// 特殊交易明细记录  --  [1..1] 
	private String  appId;//申请件编号
	private String  relateId;//每个 借贷账户信息单元 的关联id
	public String getPD01FD01() {
		return PD01FD01;
	}
	public void setPD01FD01(String pD01FD01) {
		PD01FD01 = pD01FD01;
	}
	public String getPD01FR01() {
		return PD01FR01;
	}
	public void setPD01FR01(String pD01FR01) {
		PD01FR01 = pD01FR01;
	}
	public String getPD01FS02() {
		return PD01FS02;
	}
	public void setPD01FS02(String pD01FS02) {
		PD01FS02 = pD01FS02;
	}
	public String getPD01FJ01() {
		return PD01FJ01;
	}
	public void setPD01FJ01(String pD01FJ01) {
		PD01FJ01 = pD01FJ01;
	}
	public String getPD01FQ01() {
		return PD01FQ01;
	}
	public void setPD01FQ01(String pD01FQ01) {
		PD01FQ01 = pD01FQ01;
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