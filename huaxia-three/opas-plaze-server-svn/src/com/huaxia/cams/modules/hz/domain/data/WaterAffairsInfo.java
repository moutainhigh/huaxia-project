package com.huaxia.cams.modules.hz.domain.data;

public class WaterAffairsInfo {
	
	private String servCode;
	private String collectionDt;
	private String paymentStatus;
	private String crtUser;
	private String appId;
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	public String getServCode() {
		return servCode;
	}
	public void setServCode(String servCode) {
		this.servCode = servCode;
	}
	public String getCollectionDt() {
		return collectionDt;
	}
	public void setCollectionDt(String collectionDt) {
		this.collectionDt = collectionDt;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	

}
