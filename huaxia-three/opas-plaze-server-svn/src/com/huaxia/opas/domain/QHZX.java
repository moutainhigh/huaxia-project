package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 前海征信
 * 
 * @author zhiguo.li
 *
 */
public abstract class QHZX implements Serializable {
	
	private static final long serialVersionUID = -4350936915445543347L;

	// 申请件编号
	private String appId;

	// 创建时间
	private String crtTime;

	// 创建人
	private String crtUser;

	// 最后操作时间
	private String lstUpdTime;

	// 最后更新人
	private String lstUpdUser;
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getLstUpdTime() {
		return lstUpdTime;
	}

	public void setLstUpdTime(String lstUpdTime) {
		this.lstUpdTime = lstUpdTime;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

}
