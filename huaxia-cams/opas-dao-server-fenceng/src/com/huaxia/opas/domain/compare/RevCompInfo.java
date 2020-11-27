package com.huaxia.opas.domain.compare;

import java.io.Serializable;
import java.util.Date;

/**
 * 审查录入比对信息
 * 
 * @author xiebinxu 2017年2月25日
 */
public class RevCompInfo implements Serializable {

	private static final long serialVersionUID = 8569785823282443384L;

	private String appId;

	private String applyInputfull;

	private String signFull;

	private String nationalInutfull;

	private String certifiPeriodfull;

	private String applyWorkTure;

	private String peobankCompanyNameAddr;

	private String peobankCompanyNameTel;

	private String company114NameAddr;

	private String company114NameTel;

	private String pyuanCompanyNameAddr;

	private String pyuanCompanyNameTel;

	private String noOfficwebNameAddr;

	private String noOfficwebNameTel;

	private String otherThirdNameAddr;

	private String officwebNameAddr;

	private String officwebNameTel;

	private String thirddataAddr;

	private String applyWorkinfoTure;

	private String sbandgjj6monthsStatus;

	private String businetworkLegal;

	private String others;

	private String otherMemo;

	private String pubPolice;

	private String businetworkMinor;

	private String peobankFirstcard6months;

	private String insurance;

	private String applyerTrue;

	private String peobankPhone;

	private String thirdPhone;

	private String otherthirdInfo;
	
	private String orderAddress;
	
	private Date crtDate;
	
	private String company;
	
	private String homeAddress;
	
	private String regionalDataListName;
	
	private String peobankCompanyName;
	
	private String padPositionAddr;//PAD定位地址
	
	public String getPadPositionAddr() {
		return padPositionAddr;
	}

	public void setPadPositionAddr(String padPositionAddr) {
		this.padPositionAddr = padPositionAddr;
	}

	public String getPeobankCompanyName() {
		return peobankCompanyName;
	}

	public void setPeobankCompanyName(String peobankCompanyName) {
		this.peobankCompanyName = peobankCompanyName;
	}

	public String getRegionalDataListName() {
		return regionalDataListName;
	}

	public void setRegionalDataListName(String regionalDataListName) {
		this.regionalDataListName = regionalDataListName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getOrderAddress() {
		return orderAddress;
	}
	
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getApplyInputfull() {
		return applyInputfull;
	}

	public void setApplyInputfull(String applyInputfull) {
		this.applyInputfull = applyInputfull == null ? null : applyInputfull
				.trim();
	}

	public String getSignFull() {
		return signFull;
	}

	public void setSignFull(String signFull) {
		this.signFull = signFull == null ? null : signFull.trim();
	}

	public String getNationalInutfull() {
		return nationalInutfull;
	}

	public void setNationalInutfull(String nationalInutfull) {
		this.nationalInutfull = nationalInutfull == null ? null
				: nationalInutfull.trim();
	}

	public String getCertifiPeriodfull() {
		return certifiPeriodfull;
	}

	public void setCertifiPeriodfull(String certifiPeriodfull) {
		this.certifiPeriodfull = certifiPeriodfull == null ? null
				: certifiPeriodfull.trim();
	}

	public String getApplyWorkTure() {
		return applyWorkTure;
	}

	public void setApplyWorkTure(String applyWorkTure) {
		this.applyWorkTure = applyWorkTure == null ? null : applyWorkTure
				.trim();
	}

	public String getPeobankCompanyNameAddr() {
		return peobankCompanyNameAddr;
	}

	public void setPeobankCompanyNameAddr(String peobankCompanyNameAddr) {
		this.peobankCompanyNameAddr = peobankCompanyNameAddr == null ? null
				: peobankCompanyNameAddr.trim();
	}

	public String getPeobankCompanyNameTel() {
		return peobankCompanyNameTel;
	}

	public void setPeobankCompanyNameTel(String peobankCompanyNameTel) {
		this.peobankCompanyNameTel = peobankCompanyNameTel == null ? null
				: peobankCompanyNameTel.trim();
	}

	public String getCompany114NameAddr() {
		return company114NameAddr;
	}

	public void setCompany114NameAddr(String company114NameAddr) {
		this.company114NameAddr = company114NameAddr == null ? null
				: company114NameAddr.trim();
	}

	public String getCompany114NameTel() {
		return company114NameTel;
	}

	public void setCompany114NameTel(String company114NameTel) {
		this.company114NameTel = company114NameTel == null ? null
				: company114NameTel.trim();
	}

	public String getPyuanCompanyNameAddr() {
		return pyuanCompanyNameAddr;
	}

	public void setPyuanCompanyNameAddr(String pyuanCompanyNameAddr) {
		this.pyuanCompanyNameAddr = pyuanCompanyNameAddr == null ? null
				: pyuanCompanyNameAddr.trim();
	}

	public String getPyuanCompanyNameTel() {
		return pyuanCompanyNameTel;
	}

	public void setPyuanCompanyNameTel(String pyuanCompanyNameTel) {
		this.pyuanCompanyNameTel = pyuanCompanyNameTel == null ? null
				: pyuanCompanyNameTel.trim();
	}

	public String getNoOfficwebNameAddr() {
		return noOfficwebNameAddr;
	}

	public void setNoOfficwebNameAddr(String noOfficwebNameAddr) {
		this.noOfficwebNameAddr = noOfficwebNameAddr == null ? null
				: noOfficwebNameAddr.trim();
	}

	public String getNoOfficwebNameTel() {
		return noOfficwebNameTel;
	}

	public void setNoOfficwebNameTel(String noOfficwebNameTel) {
		this.noOfficwebNameTel = noOfficwebNameTel == null ? null
				: noOfficwebNameTel.trim();
	}

	public String getOtherThirdNameAddr() {
		return otherThirdNameAddr;
	}

	public void setOtherThirdNameAddr(String otherThirdNameAddr) {
		this.otherThirdNameAddr = otherThirdNameAddr == null ? null
				: otherThirdNameAddr.trim();
	}

	public String getOfficwebNameAddr() {
		return officwebNameAddr;
	}

	public void setOfficwebNameAddr(String officwebNameAddr) {
		this.officwebNameAddr = officwebNameAddr == null ? null
				: officwebNameAddr.trim();
	}

	public String getOfficwebNameTel() {
		return officwebNameTel;
	}

	public void setOfficwebNameTel(String officwebNameTel) {
		this.officwebNameTel = officwebNameTel == null ? null : officwebNameTel
				.trim();
	}

	public String getThirddataAddr() {
		return thirddataAddr;
	}

	public void setThirddataAddr(String thirddataAddr) {
		this.thirddataAddr = thirddataAddr == null ? null : thirddataAddr
				.trim();
	}

	public String getApplyWorkinfoTure() {
		return applyWorkinfoTure;
	}

	public void setApplyWorkinfoTure(String applyWorkinfoTure) {
		this.applyWorkinfoTure = applyWorkinfoTure == null ? null
				: applyWorkinfoTure.trim();
	}

	public String getSbandgjj6monthsStatus() {
		return sbandgjj6monthsStatus;
	}

	public void setSbandgjj6monthsStatus(String sbandgjj6monthsStatus) {
		this.sbandgjj6monthsStatus = sbandgjj6monthsStatus == null ? null
				: sbandgjj6monthsStatus.trim();
	}

	public String getBusinetworkLegal() {
		return businetworkLegal;
	}

	public void setBusinetworkLegal(String businetworkLegal) {
		this.businetworkLegal = businetworkLegal == null ? null
				: businetworkLegal.trim();
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others == null ? null : others.trim();
	}

	public String getOtherMemo() {
		return otherMemo;
	}

	public void setOtherMemo(String otherMemo) {
		this.otherMemo = otherMemo == null ? null : otherMemo.trim();
	}

	public String getPubPolice() {
		return pubPolice;
	}

	public void setPubPolice(String pubPolice) {
		this.pubPolice = pubPolice == null ? null : pubPolice.trim();
	}

	public String getBusinetworkMinor() {
		return businetworkMinor;
	}

	public void setBusinetworkMinor(String businetworkMinor) {
		this.businetworkMinor = businetworkMinor == null ? null
				: businetworkMinor.trim();
	}

	public String getPeobankFirstcard6months() {
		return peobankFirstcard6months;
	}

	public void setPeobankFirstcard6months(String peobankFirstcard6months) {
		this.peobankFirstcard6months = peobankFirstcard6months == null ? null
				: peobankFirstcard6months.trim();
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance == null ? null : insurance.trim();
	}

	public String getApplyerTrue() {
		return applyerTrue;
	}

	public void setApplyerTrue(String applyerTrue) {
		this.applyerTrue = applyerTrue == null ? null : applyerTrue.trim();
	}

	public String getPeobankPhone() {
		return peobankPhone;
	}

	public void setPeobankPhone(String peobankPhone) {
		this.peobankPhone = peobankPhone == null ? null : peobankPhone.trim();
	}

	public String getThirdPhone() {
		return thirdPhone;
	}

	public void setThirdPhone(String thirdPhone) {
		this.thirdPhone = thirdPhone == null ? null : thirdPhone.trim();
	}

	public String getOtherthirdInfo() {
		return otherthirdInfo;
	}

	public void setOtherthirdInfo(String otherthirdInfo) {
		this.otherthirdInfo = otherthirdInfo == null ? null : otherthirdInfo
				.trim();
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "RevCompInfo [appId=" + appId + ", applyInputfull=" + applyInputfull + ", signFull=" + signFull
				+ ", nationalInutfull=" + nationalInutfull + ", certifiPeriodfull=" + certifiPeriodfull
				+ ", applyWorkTure=" + applyWorkTure + ", peobankCompanyNameAddr=" + peobankCompanyNameAddr
				+ ", peobankCompanyNameTel=" + peobankCompanyNameTel + ", company114NameAddr=" + company114NameAddr
				+ ", company114NameTel=" + company114NameTel + ", pyuanCompanyNameAddr=" + pyuanCompanyNameAddr
				+ ", pyuanCompanyNameTel=" + pyuanCompanyNameTel + ", noOfficwebNameAddr=" + noOfficwebNameAddr
				+ ", noOfficwebNameTel=" + noOfficwebNameTel + ", otherThirdNameAddr=" + otherThirdNameAddr
				+ ", officwebNameAddr=" + officwebNameAddr + ", officwebNameTel=" + officwebNameTel + ", thirddataAddr="
				+ thirddataAddr + ", applyWorkinfoTure=" + applyWorkinfoTure + ", sbandgjj6monthsStatus="
				+ sbandgjj6monthsStatus + ", businetworkLegal=" + businetworkLegal + ", others=" + others
				+ ", otherMemo=" + otherMemo + ", pubPolice=" + pubPolice + ", businetworkMinor=" + businetworkMinor
				+ ", peobankFirstcard6months=" + peobankFirstcard6months + ", insurance=" + insurance + ", applyerTrue="
				+ applyerTrue + ", peobankPhone=" + peobankPhone + ", thirdPhone=" + thirdPhone + ", otherthirdInfo="
				+ otherthirdInfo + ", orderAddress=" + orderAddress + ", crtDate=" + crtDate + ", company=" + company
				+ ", homeAddress=" + homeAddress + ", regionalDataListName=" + regionalDataListName + "]";
	}

}