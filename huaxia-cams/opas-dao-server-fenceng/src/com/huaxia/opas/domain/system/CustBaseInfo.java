package com.huaxia.opas.domain.system;

import java.io.Serializable;
import java.util.Date;

public class CustBaseInfo implements Serializable {

	private static final long serialVersionUID = 2782549401251390945L;

	private String autoId;

	private String appId;

	private String custName;

	private String sex;

	private String nationaity;

	private Short age;

	private String certifiType;

	private String certifiNo;

	private String workCompany;

	private String officeTel;

	private String mobileNo;

	private String applyCard;

	private String policeStatus;

	private String teamnoIsmatch;

	private String isOneselfCust;

	private String isHavecardCust;

	private String isHavecardStandard;

	private String isPrefiltercustStandard;

	private Date crtTime;

	private Date crtDate;

	private String crtUser;

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

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName == null ? null : custName.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getNationaity() {
		return nationaity;
	}

	public void setNationaity(String nationaity) {
		this.nationaity = nationaity == null ? null : nationaity.trim();
	}

	public Short getAge() {
		return age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public String getCertifiType() {
		return certifiType;
	}

	public void setCertifiType(String certifiType) {
		this.certifiType = certifiType == null ? null : certifiType.trim();
	}

	public String getCertifiNo() {
		return certifiNo;
	}

	public void setCertifiNo(String certifiNo) {
		this.certifiNo = certifiNo == null ? null : certifiNo.trim();
	}

	public String getWorkCompany() {
		return workCompany;
	}

	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany == null ? null : workCompany.trim();
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel == null ? null : officeTel.trim();
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo == null ? null : mobileNo.trim();
	}

	public String getApplyCard() {
		return applyCard;
	}

	public void setApplyCard(String applyCard) {
		this.applyCard = applyCard == null ? null : applyCard.trim();
	}

	public String getPoliceStatus() {
		return policeStatus;
	}

	public void setPoliceStatus(String policeStatus) {
		this.policeStatus = policeStatus == null ? null : policeStatus.trim();
	}

	public String getTeamnoIsmatch() {
		return teamnoIsmatch;
	}

	public void setTeamnoIsmatch(String teamnoIsmatch) {
		this.teamnoIsmatch = teamnoIsmatch == null ? null : teamnoIsmatch.trim();
	}

	public String getIsOneselfCust() {
		return isOneselfCust;
	}

	public void setIsOneselfCust(String isOneselfCust) {
		this.isOneselfCust = isOneselfCust == null ? null : isOneselfCust.trim();
	}

	public String getIsHavecardCust() {
		return isHavecardCust;
	}

	public void setIsHavecardCust(String isHavecardCust) {
		this.isHavecardCust = isHavecardCust == null ? null : isHavecardCust.trim();
	}

	public String getIsHavecardStandard() {
		return isHavecardStandard;
	}

	public void setIsHavecardStandard(String isHavecardStandard) {
		this.isHavecardStandard = isHavecardStandard == null ? null : isHavecardStandard.trim();
	}

	public String getIsPrefiltercustStandard() {
		return isPrefiltercustStandard;
	}

	public void setIsPrefiltercustStandard(String isPrefiltercustStandard) {
		this.isPrefiltercustStandard = isPrefiltercustStandard == null ? null : isPrefiltercustStandard.trim();
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
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