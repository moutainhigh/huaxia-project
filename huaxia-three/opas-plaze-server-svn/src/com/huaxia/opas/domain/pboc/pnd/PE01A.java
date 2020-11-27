package com.huaxia.opas.domain.pboc.pnd;
import java.io.Serializable;
/**
 * 后付费业务信息段 
 * @author gaoh
 *
 */
public class PE01A implements Serializable {

	private static final long serialVersionUID = 5265761706825172485L;
	private String PE01AD01;//后付费账户类型  --  [1..1]  
	private String PE01AQ01;//机构名称  --  [1..1]  
	private String PE01AD02;//业务类型  --  [1..1]  
	private String PE01AR01;//业务开通日期  --  [1..1]  
	private String PE01AD03;//当前缴费状态  --  [1..1]  
	private String PE01AJ01;//当前欠费金额  --  [1..1]  
	private String PE01AR02;//记账年月  --  [1..1]  
	private String PE01AQ02;//最近24个月缴费记录  --   [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个后付费业务信息单元的关联id
	
	public String getPE01AD01() {
		return PE01AD01;
	}
	public void setPE01AD01(String pE01AD01) {
		PE01AD01 = pE01AD01;
	}
	public String getPE01AQ01() {
		return PE01AQ01;
	}
	public void setPE01AQ01(String pE01AQ01) {
		PE01AQ01 = pE01AQ01;
	}
	public String getPE01AD02() {
		return PE01AD02;
	}
	public void setPE01AD02(String pE01AD02) {
		PE01AD02 = pE01AD02;
	}
	public String getPE01AR01() {
		return PE01AR01;
	}
	public void setPE01AR01(String pE01AR01) {
		PE01AR01 = pE01AR01;
	}
	public String getPE01AD03() {
		return PE01AD03;
	}
	public void setPE01AD03(String pE01AD03) {
		PE01AD03 = pE01AD03;
	}
	public String getPE01AJ01() {
		return PE01AJ01;
	}
	public void setPE01AJ01(String pE01AJ01) {
		PE01AJ01 = pE01AJ01;
	}
	public String getPE01AR02() {
		return PE01AR02;
	}
	public void setPE01AR02(String pE01AR02) {
		PE01AR02 = pE01AR02;
	}
	public String getPE01AQ02() {
		return PE01AQ02;
	}
	public void setPE01AQ02(String pE01AQ02) {
		PE01AQ02 = pE01AQ02;
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