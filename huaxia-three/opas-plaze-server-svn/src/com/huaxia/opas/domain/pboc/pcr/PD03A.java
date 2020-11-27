package com.huaxia.opas.domain.pboc.pcr;

import java.io.Serializable;
/**
 * 相关还款责任信息段   
 * @author gaoh
 *
 */
public class PD03A implements Serializable {

	private static final long serialVersionUID = 5753220098542029792L;
	private String PD03AD08;//主借款人身份类别   -- [1..1]
	private String PD03AD01;//业务管理机构类型  -- [1..1] 
	private String PD03AQ01;//业务管理机构  --  [1..1]  
	private String PD03AD02;//业务种类  --  [1..1]  
	private String PD03AR01;//开立日期  --  [1..1] 
	private String PD03AR02;//到期日  --  [1..1]  
	private String PD03AD03;//相关还款责任人类型  --  [1..1]  
	private String PD03AQ02;//保证合同编号  --  [1..1]  
	private String PD03AJ01;//相关还款责任金额  --  [1..1]  
	private String PD03AD04;//币种  --  [1..1] 
	private String PD03AJ02;//余额  --   [1..1]  
	private String PD03AD05;//五级分类  -- [1..1]  
	private String PD03AD06;//账户类型  --   [1..1] 
	private String PD03AD07;//还款状态  --  [1..1]   
	private String PD03AS01;//逾期月数  --  [1..1]  
	private String PD03AR03;//信息报告日期  --  [1..1]
	private String appId;//申请件编号
	private String relateId;//每个相关还款责任信息单元的关联id
	
	public String getPD03AD08() {
		return PD03AD08;
	}
	public void setPD03AD08(String pD03AD08) {
		PD03AD08 = pD03AD08;
	}
	public String getPD03AD01() {
		return PD03AD01;
	}
	public void setPD03AD01(String pD03AD01) {
		PD03AD01 = pD03AD01;
	}
	public String getPD03AQ01() {
		return PD03AQ01;
	}
	public void setPD03AQ01(String pD03AQ01) {
		PD03AQ01 = pD03AQ01;
	}
	public String getPD03AD02() {
		return PD03AD02;
	}
	public void setPD03AD02(String pD03AD02) {
		PD03AD02 = pD03AD02;
	}
	public String getPD03AR01() {
		return PD03AR01;
	}
	public void setPD03AR01(String pD03AR01) {
		PD03AR01 = pD03AR01;
	}
	public String getPD03AR02() {
		return PD03AR02;
	}
	public void setPD03AR02(String pD03AR02) {
		PD03AR02 = pD03AR02;
	}
	public String getPD03AD03() {
		return PD03AD03;
	}
	public void setPD03AD03(String pD03AD03) {
		PD03AD03 = pD03AD03;
	}
	public String getPD03AQ02() {
		return PD03AQ02;
	}
	public void setPD03AQ02(String pD03AQ02) {
		PD03AQ02 = pD03AQ02;
	}
	public String getPD03AJ01() {
		return PD03AJ01;
	}
	public void setPD03AJ01(String pD03AJ01) {
		PD03AJ01 = pD03AJ01;
	}
	public String getPD03AD04() {
		return PD03AD04;
	}
	public void setPD03AD04(String pD03AD04) {
		PD03AD04 = pD03AD04;
	}
	public String getPD03AJ02() {
		return PD03AJ02;
	}
	public void setPD03AJ02(String pD03AJ02) {
		PD03AJ02 = pD03AJ02;
	}
	public String getPD03AD05() {
		return PD03AD05;
	}
	public void setPD03AD05(String pD03AD05) {
		PD03AD05 = pD03AD05;
	}
	public String getPD03AD06() {
		return PD03AD06;
	}
	public void setPD03AD06(String pD03AD06) {
		PD03AD06 = pD03AD06;
	}
	public String getPD03AD07() {
		return PD03AD07;
	}
	public void setPD03AD07(String pD03AD07) {
		PD03AD07 = pD03AD07;
	}
	public String getPD03AS01() {
		return PD03AS01;
	}
	public void setPD03AS01(String pD03AS01) {
		PD03AS01 = pD03AS01;
	}
	public String getPD03AR03() {
		return PD03AR03;
	}
	public void setPD03AR03(String pD03AR03) {
		PD03AR03 = pD03AR03;
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
