package com.huaxia.opas.domain.pboc.pot;

import java.io.Serializable;
/**
 * 欠税记录信息段 
 * @author gaoh
 *
 */
public class PF01A implements Serializable {
	
	private static final long serialVersionUID = 5393666922370135320L;
	private String PF01AQ01;//主管税务机关  --  [1..1]  
	private String PF01AJ01;//欠税总额  --  [1..1]  
	private String PF01AR01;//欠税统计日期  --  [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个欠税记录信息单元的关联id
	public String getPF01AQ01() {
		return PF01AQ01;
	}
	public void setPF01AQ01(String pF01AQ01) {
		PF01AQ01 = pF01AQ01;
	}
	public String getPF01AJ01() {
		return PF01AJ01;
	}
	public void setPF01AJ01(String pF01AJ01) {
		PF01AJ01 = pF01AJ01;
	}
	public String getPF01AR01() {
		return PF01AR01;
	}
	public void setPF01AR01(String pF01AR01) {
		PF01AR01 = pF01AR01;
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
