package com.huaxia.opas.domain.pboc.pcj;

import java.io.Serializable;
/**
 * 民事判决记录信息段 
 * @author gaoh
 *
 */
public class PF02A implements Serializable {
	
	private static final long serialVersionUID = 509767892496330591L;
	private String PF02AQ01;// 立案法院  --   [1..1]
	private String PF02AQ02;//案由  --  [1..1]  
	private String PF02AR01;//立案日期  --  [1..1]  
	private String PF02AD01;//结案方式  --  [1..1]  
	private String PF02AQ03;//判决/调解结果  --  [1..1]  
	private String PF02AR02;//判决/调解生效日期  --  [1..1]  
	private String PF02AQ04;//诉讼标的  --  [1..1]  
	private String PF02AJ01;//诉讼标的金额  --  [1..1]  
	private String appId;//申请件编号
	private String relateId;//每个民事判决记录信息单元的关联id
	public String getPF02AQ01() {
		return PF02AQ01;
	}
	public void setPF02AQ01(String pF02AQ01) {
		PF02AQ01 = pF02AQ01;
	}
	public String getPF02AQ02() {
		return PF02AQ02;
	}
	public void setPF02AQ02(String pF02AQ02) {
		PF02AQ02 = pF02AQ02;
	}
	public String getPF02AR01() {
		return PF02AR01;
	}
	public void setPF02AR01(String pF02AR01) {
		PF02AR01 = pF02AR01;
	}
	public String getPF02AD01() {
		return PF02AD01;
	}
	public void setPF02AD01(String pF02AD01) {
		PF02AD01 = pF02AD01;
	}
	public String getPF02AQ03() {
		return PF02AQ03;
	}
	public void setPF02AQ03(String pF02AQ03) {
		PF02AQ03 = pF02AQ03;
	}
	public String getPF02AR02() {
		return PF02AR02;
	}
	public void setPF02AR02(String pF02AR02) {
		PF02AR02 = pF02AR02;
	}
	public String getPF02AQ04() {
		return PF02AQ04;
	}
	public void setPF02AQ04(String pF02AQ04) {
		PF02AQ04 = pF02AQ04;
	}
	public String getPF02AJ01() {
		return PF02AJ01;
	}
	public void setPF02AJ01(String pF02AJ01) {
		PF02AJ01 = pF02AJ01;
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
