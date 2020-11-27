package com.huaxia.opas.domain.pboc.prm;

import java.io.Serializable;

public class PB03 implements Serializable {
	private static final long serialVersionUID = 7841267994311875002L;
	private String  PB030D01;//  居住状况  [1..1]          
	private String PB030Q01;// 居住地址       [1..1]   
	private String PB030Q02;// 住宅电话         [1..1]
	private String PB030R01;//信息更新日期     [1..1]  
	private String appId ;//申请件编号
	public String getPB030D01() {
		return PB030D01;
	}
	public void setPB030D01(String pB030D01) {
		PB030D01 = pB030D01;
	}
	public String getPB030Q01() {
		return PB030Q01;
	}
	public void setPB030Q01(String pB030Q01) {
		PB030Q01 = pB030Q01;
	}
	public String getPB030Q02() {
		return PB030Q02;
	}
	public void setPB030Q02(String pB030Q02) {
		PB030Q02 = pB030Q02;
	}
	public String getPB030R01() {
		return PB030R01;
	}
	public void setPB030R01(String pB030R01) {
		PB030R01 = pB030R01;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

}