package com.huaxia.opas.domain.collect;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PatchboltSpyInfo implements Serializable {

	private static final long serialVersionUID = -7184898448429718817L;

	private String autoId;

	private String appId;

	private String enterpriseProprrty;// 1-非私营企业 2-私营企业

	private String privateEntprseClientType;// 1- 私营企业主 2- 私营企业雇员

	private String licenseOffered;// 是否提供营业执照

	private String foundTime;// 成立时间

	private String registeredCapital;// 注册资金（人民币）

	private String privateEntprseClientPaper;// 私营企业客户准入材料

	private String privateEntprseCpaperType;// 私营企业客户准入材料类型

	private String educationBackgroundCheck;// 学历验证情况

	private BigDecimal productPrice;// 商品金额
	private Date crtDate;
	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId == null ? null : autoId.trim();
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getEnterpriseProprrty() {
		return enterpriseProprrty;
	}

	public void setEnterpriseProprrty(String enterpriseProprrty) {
		this.enterpriseProprrty = enterpriseProprrty == null ? null : enterpriseProprrty.trim();
	}

	public String getPrivateEntprseClientType() {
		return privateEntprseClientType;
	}

	public void setPrivateEntprseClientType(String privateEntprseClientType) {
		this.privateEntprseClientType = privateEntprseClientType == null ? null : privateEntprseClientType.trim();
	}

	public String getLicenseOffered() {
		return licenseOffered;
	}

	public void setLicenseOffered(String licenseOffered) {
		this.licenseOffered = licenseOffered == null ? null : licenseOffered.trim();
	}

	public String getFoundTime() {
		return foundTime;
	}

	public void setFoundTime(String foundTime) {
		this.foundTime = foundTime == null ? null : foundTime.trim();
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital == null ? null : registeredCapital.trim();
	}

	public String getPrivateEntprseClientPaper() {
		return privateEntprseClientPaper;
	}

	public void setPrivateEntprseClientPaper(String privateEntprseClientPaper) {
		this.privateEntprseClientPaper = privateEntprseClientPaper == null ? null : privateEntprseClientPaper.trim();
	}

	public String getPrivateEntprseCpaperType() {
		return privateEntprseCpaperType;
	}

	public void setPrivateEntprseCpaperType(String privateEntprseCpaperType) {
		this.privateEntprseCpaperType = privateEntprseCpaperType == null ? null : privateEntprseCpaperType.trim();
	}

	public String getEducationBackgroundCheck() {
		return educationBackgroundCheck;
	}

	public void setEducationBackgroundCheck(String educationBackgroundCheck) {
		this.educationBackgroundCheck = educationBackgroundCheck == null ? null : educationBackgroundCheck.trim();
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "PatchboltSpyInfo [autoId=" + autoId + ", appId=" + appId + ", enterpriseProprrty=" + enterpriseProprrty
				+ ", privateEntprseClientType=" + privateEntprseClientType + ", licenseOffered=" + licenseOffered
				+ ", foundTime=" + foundTime + ", registeredCapital=" + registeredCapital
				+ ", privateEntprseClientPaper=" + privateEntprseClientPaper + ", privateEntprseCpaperType="
				+ privateEntprseCpaperType + ", educationBackgroundCheck=" + educationBackgroundCheck
				+ ", productPrice=" + productPrice + "]";
	}

}