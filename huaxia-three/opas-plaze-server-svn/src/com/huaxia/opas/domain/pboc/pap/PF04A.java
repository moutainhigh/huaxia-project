package com.huaxia.opas.domain.pboc.pap;

import java.io.Serializable;
/**
 * 行政处罚记录信息段
 * @author gaoh
 *
 */
public class PF04A implements Serializable {
	
	private static final long serialVersionUID = -6718844596035730983L;
	private String PF04AQ01;//处罚机构  --  [1..1]
	private String PF04AQ02;//处罚内容  --  [1..1]  
	private String PF04AJ01;//处罚金额  --  [1..1] 
	private String PF04AR01;//处罚生效日期  -- [1..1] 
	private String PF04AR02;//处罚截止日期  --  [1..1]  
	private String PF04AQ03;//行政复议结果  --  [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个行政处罚记录信息单元的关联id
	public String getPF04AQ01() {
		return PF04AQ01;
	}
	public void setPF04AQ01(String pF04AQ01) {
		PF04AQ01 = pF04AQ01;
	}
	public String getPF04AQ02() {
		return PF04AQ02;
	}
	public void setPF04AQ02(String pF04AQ02) {
		PF04AQ02 = pF04AQ02;
	}
	public String getPF04AJ01() {
		return PF04AJ01;
	}
	public void setPF04AJ01(String pF04AJ01) {
		PF04AJ01 = pF04AJ01;
	}
	public String getPF04AR01() {
		return PF04AR01;
	}
	public void setPF04AR01(String pF04AR01) {
		PF04AR01 = pF04AR01;
	}
	public String getPF04AR02() {
		return PF04AR02;
	}
	public void setPF04AR02(String pF04AR02) {
		PF04AR02 = pF04AR02;
	}
	public String getPF04AQ03() {
		return PF04AQ03;
	}
	public void setPF04AQ03(String pF04AQ03) {
		PF04AQ03 = pF04AQ03;
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
