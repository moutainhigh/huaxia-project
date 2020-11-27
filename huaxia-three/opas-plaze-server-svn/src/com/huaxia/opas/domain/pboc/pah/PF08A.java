package com.huaxia.opas.domain.pboc.pah;

import java.io.Serializable;
/**
 * 行政奖励记录信息段
 * @author gaoh
 *
 */
public class PF08A implements Serializable {
 
	private static final long serialVersionUID = 5669955877506447497L;
	private String PF08AQ01;//奖励机构  --  [1..1] 
	private String PF08AQ02;//奖励内容  --  [1..1] 
	private String PF08AR01;//生效年月  --  [1..1]  
	private String PF08AR02;// 截止年月  --  [1..1]  
	private String appId;//申请件编号
	private String relateId;//每个行政奖励记录的关联id
	public String getPF08AQ01() {
		return PF08AQ01;
	}
	public void setPF08AQ01(String pF08AQ01) {
		PF08AQ01 = pF08AQ01;
	}
	public String getPF08AQ02() {
		return PF08AQ02;
	}
	public void setPF08AQ02(String pF08AQ02) {
		PF08AQ02 = pF08AQ02;
	}
	public String getPF08AR01() {
		return PF08AR01;
	}
	public void setPF08AR01(String pF08AR01) {
		PF08AR01 = pF08AR01;
	}
	public String getPF08AR02() {
		return PF08AR02;
	}
	public void setPF08AR02(String pF08AR02) {
		PF08AR02 = pF08AR02;
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
