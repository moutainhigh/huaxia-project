package com.huaxia.opas.domain.pboc.pim;

import java.io.Serializable;

/**
 *手机号码信息
 * @author gaoh
 *
 */
public class PB01BH implements Serializable {
	private static final long serialVersionUID = -465427642823643937L;
	private String PB01BQ01 ;//手机号码   --     [1..1]     
	private String PB01BR01 ;//信息更新日期  --   [1..1]
	private String appId;//申请件编号
	public String getPB01BQ01() {
		return PB01BQ01;
	}
	public void setPB01BQ01(String pB01BQ01) {
		PB01BQ01 = pB01BQ01;
	}
	public String getPB01BR01() {
		return PB01BR01;
	}
	public void setPB01BR01(String pB01BR01) {
		PB01BR01 = pB01BR01;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

}