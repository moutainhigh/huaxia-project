package com.huaxia.opas.domain;

/**
 * 第三方 & CRM
 * 
 * @author zhiguo.li
 *
 */
public abstract class CRM {

	// 创建日期
	private String crtDate;

	// 创建人
	private String crtUser;

	// 最后操作日期
	private String lstUpdDate;

	// 最后更新人
	private String lstUpdUser;

	public String getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(String lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

}
