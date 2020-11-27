package com.huaxia.opas.domain.decision;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class OpaRiskInfoCheck implements Serializable {

	private static final long serialVersionUID = 661758666803538409L;
	
	private String telRisklist;
	private String autoId;

	private String appId;
	/**
	 * 身份类风险名单库 0否1是
	 */
	private String identityRisklist;
	/**
	 * 单位类风险名单库 0否1是
	 */
	private String companyRisklist;
	/**
	 * 申请人手机号码 0否1是
	 */
	private String telRisklist1;
	/**
	 * 联系人手机号码 0否1是
	 */
	private String telRisklist2;
	/**
	 * 申请人所有电话 0否1是
	 */
	private String telRisklist3;
	/**
	 * 联系人所有电话 0否1是
	 */
	private String telRisklist4;
	/**
	 * 申请人住宅电话 0否1是
	 */
	private String telRisklist5;
	/**
	 * 联系人住宅电话 0否1是
	 */
	private String telRisklist6;
	/**
	 * 申请人其他电话 0否1是
	 */
	private String telRisklist7;
	/**
	 * 联系人其他电话 0否1是
	 */
	private String telRisklist8;
	/**
	 * 申请人单位地址 0否1是
	 */
	private String addrRisklist1;
	/**
	 * 申请人住宅地址 0否1是
	 */
	private String addrRisklist2;
	/**
	 * 推广员风险名单库 0否1是
	 */
	private String promoterRisklist;
	/**
	 * \ 银联风险名单 0否1是
	 */
	private String unionRisklist;
	/**
	 * 同业风险名单 0否1是
	 */
	private String sameRisklist;
	/**
	 * 风险星级
	 */
	private Short riskStar;
	/**
	 * 公安负面信息 0否1是
	 */
	private String policeBadinfo;
	/**
	 * 百融风险名单 0否1是
	 */
	private String bairongRisklist;
	/**
	 * 蚂蚁风险名单 0否1是
	 */
	private String antRisklist;
	/**
	 * 洗钱风险名单 0否1是
	 */
	private String washmoneyRisklist;
	/**
	 * 客户洗钱风险等级 0否1是
	 */
	private String custwashmoneyRisklevel;
	/**
	 * 附卡洗钱风险名单 0否1是
	 */
	private String washmoneyRisklist2;
	/**
	 * 附卡客户洗钱风险等级 0否1是
	 */
	private String custwashmoneyRisklevel2;
	/**
	 * 手机实名认证结果 0否1是
	 */
	private String addrRisklist;
	private String phoneCertification;
	@JSONField(format = "yyyy/MM/dd HH:mm:ss")
	private Date crtDate;

	private String crtUser;
	
	public String getWashmoneyRisklist2() {
		return washmoneyRisklist2;
	}

	public void setWashmoneyRisklist2(String washmoneyRisklist2) {
		this.washmoneyRisklist2 = washmoneyRisklist2;
	}

	public String getCustwashmoneyRisklevel2() {
		return custwashmoneyRisklevel2;
	}

	public void setCustwashmoneyRisklevel2(String custwashmoneyRisklevel2) {
		this.custwashmoneyRisklevel2 = custwashmoneyRisklevel2;
	}

	public String getAddrRisklist() {
		return addrRisklist;
	}

	public void setAddrRisklist(String addrRisklist) {
		this.addrRisklist = addrRisklist;
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

	public String getIdentityRisklist() {
		return identityRisklist;
	}

	public void setIdentityRisklist(String identityRisklist) {
		this.identityRisklist = identityRisklist == null ? null : identityRisklist.trim();
	}

	public String getCompanyRisklist() {
		return companyRisklist;
	}

	public void setCompanyRisklist(String companyRisklist) {
		this.companyRisklist = companyRisklist == null ? null : companyRisklist.trim();
	}

	public String getTelRisklist1() {
		return telRisklist1;
	}

	public void setTelRisklist1(String telRisklist1) {
		this.telRisklist1 = telRisklist1 == null ? null : telRisklist1.trim();
	}

	public String getTelRisklist2() {
		return telRisklist2;
	}

	public void setTelRisklist2(String telRisklist2) {
		this.telRisklist2 = telRisklist2 == null ? null : telRisklist2.trim();
	}

	public String getTelRisklist3() {
		return telRisklist3;
	}

	public void setTelRisklist3(String telRisklist3) {
		this.telRisklist3 = telRisklist3 == null ? null : telRisklist3.trim();
	}

	public String getTelRisklist4() {
		return telRisklist4;
	}

	public void setTelRisklist4(String telRisklist4) {
		this.telRisklist4 = telRisklist4 == null ? null : telRisklist4.trim();
	}

	public String getTelRisklist5() {
		return telRisklist5;
	}

	public void setTelRisklist5(String telRisklist5) {
		this.telRisklist5 = telRisklist5 == null ? null : telRisklist5.trim();
	}

	public String getTelRisklist6() {
		return telRisklist6;
	}

	public void setTelRisklist6(String telRisklist6) {
		this.telRisklist6 = telRisklist6 == null ? null : telRisklist6.trim();
	}

	public String getTelRisklist7() {
		return telRisklist7;
	}

	public void setTelRisklist7(String telRisklist7) {
		this.telRisklist7 = telRisklist7 == null ? null : telRisklist7.trim();
	}

	public String getTelRisklist8() {
		return telRisklist8;
	}

	public void setTelRisklist8(String telRisklist8) {
		this.telRisklist8 = telRisklist8 == null ? null : telRisklist8.trim();
	}

	public String getAddrRisklist1() {
		return addrRisklist1;
	}

	public void setAddrRisklist1(String addrRisklist1) {
		this.addrRisklist1 = addrRisklist1 == null ? null : addrRisklist1.trim();
	}

	public String getAddrRisklist2() {
		return addrRisklist2;
	}

	public void setAddrRisklist2(String addrRisklist2) {
		this.addrRisklist2 = addrRisklist2 == null ? null : addrRisklist2.trim();
	}

	public String getPromoterRisklist() {
		return promoterRisklist;
	}

	public void setPromoterRisklist(String promoterRisklist) {
		this.promoterRisklist = promoterRisklist == null ? null : promoterRisklist.trim();
	}

	public String getUnionRisklist() {
		return unionRisklist;
	}

	public void setUnionRisklist(String unionRisklist) {
		this.unionRisklist = unionRisklist == null ? null : unionRisklist.trim();
	}

	public String getSameRisklist() {
		return sameRisklist;
	}

	public void setSameRisklist(String sameRisklist) {
		this.sameRisklist = sameRisklist == null ? null : sameRisklist.trim();
	}

	public Short getRiskStar() {
		return riskStar;
	}

	public void setRiskStar(Short riskStar) {
		this.riskStar = riskStar;
	}

	public String getPoliceBadinfo() {
		return policeBadinfo;
	}

	public void setPoliceBadinfo(String policeBadinfo) {
		this.policeBadinfo = policeBadinfo == null ? null : policeBadinfo.trim();
	}

	public String getBairongRisklist() {
		return bairongRisklist;
	}

	public void setBairongRisklist(String bairongRisklist) {
		this.bairongRisklist = bairongRisklist == null ? null : bairongRisklist.trim();
	}

	public String getAntRisklist() {
		return antRisklist;
	}

	public void setAntRisklist(String antRisklist) {
		this.antRisklist = antRisklist == null ? null : antRisklist.trim();
	}

	public String getWashmoneyRisklist() {
		return washmoneyRisklist;
	}

	public void setWashmoneyRisklist(String washmoneyRisklist) {
		this.washmoneyRisklist = washmoneyRisklist == null ? null : washmoneyRisklist.trim();
	}

	public String getCustwashmoneyRisklevel() {
		return custwashmoneyRisklevel;
	}

	public void setCustwashmoneyRisklevel(String custwashmoneyRisklevel) {
		this.custwashmoneyRisklevel = custwashmoneyRisklevel == null ? null : custwashmoneyRisklevel.trim();
	}

	public String getTelRisklist() {
		return telRisklist;
	}

	public void setTelRisklist(String telRisklist) {
		this.telRisklist = telRisklist;
	}

	public String getPhoneCertification() {
		return phoneCertification;
	}

	public void setPhoneCertification(String phoneCertification) {
		this.phoneCertification = phoneCertification == null ? null : phoneCertification.trim();
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser == null ? null : crtUser.trim();
	}
}