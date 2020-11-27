package com.huaxia.opas.domain.pboc.ppq;

import java.io.Serializable;
/**
 * 执业资格记录信息段
 * @author gaoh
 *
 */
public class PF07A implements Serializable {
	 
	private static final long serialVersionUID = 65328655019232629L;
	private String PF07AQ01;//执业资格名称  --  [1..1]
	private String PF07AQ02;//颁发机构  --   [1..1]
	private String PF07AD01;//等级  --   [1..1]  
	private String PF07AD02;//机构所在地  --  [1..1]  
	private String PF07AR01;//获得年月  --  [1..1]  
	private String PF07AR02;//到期年月  --  [1..1]  
	private String PF07AR03;//吊销年月  --  [1..1] 
	private String appId;//申请件编号
	private String relateId;//每个执业资格记录的关联id
	public String getPF07AQ01() {
		return PF07AQ01;
	}
	public void setPF07AQ01(String pF07AQ01) {
		PF07AQ01 = pF07AQ01;
	}
	public String getPF07AQ02() {
		return PF07AQ02;
	}
	public void setPF07AQ02(String pF07AQ02) {
		PF07AQ02 = pF07AQ02;
	}
	public String getPF07AD01() {
		return PF07AD01;
	}
	public void setPF07AD01(String pF07AD01) {
		PF07AD01 = pF07AD01;
	}
	public String getPF07AD02() {
		return PF07AD02;
	}
	public void setPF07AD02(String pF07AD02) {
		PF07AD02 = pF07AD02;
	}
	public String getPF07AR01() {
		return PF07AR01;
	}
	public void setPF07AR01(String pF07AR01) {
		PF07AR01 = pF07AR01;
	}
	public String getPF07AR02() {
		return PF07AR02;
	}
	public void setPF07AR02(String pF07AR02) {
		PF07AR02 = pF07AR02;
	}
	public String getPF07AR03() {
		return PF07AR03;
	}
	public void setPF07AR03(String pF07AR03) {
		PF07AR03 = pF07AR03;
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
