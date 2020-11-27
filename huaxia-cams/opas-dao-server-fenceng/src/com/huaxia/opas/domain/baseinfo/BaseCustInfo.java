package com.huaxia.opas.domain.baseinfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
*客户基本信息表实体类
*@author:susha
*@versin:2017年3月25日下午3:23:16
*/
public class BaseCustInfo implements Serializable{
	
	private static final long serialVersionUID = -470509711668179499L;
	
	private String autoId;//id主键
	private String appId;//流水号
	private String custName;//客户姓名
	private String sex;//性别  0女/1男
	private String nationaity;//国籍
	private BigDecimal age;//年龄
	private String certifiType;//证件类型
	private String certifiNo;//证件号码
	private String housePhone;//住宅电话
	private String workCompany;//工作单位
	private String officeTel;//办公电话
	private String mobileNo;//手机号码
	private String applyCard;//申请卡种
	private String policeStatus;//公安状态 :0完全匹配/1库中无此号/2身份证匹配,姓名不匹配/3姓名匹配,身份证不匹配
	private String teamnoIsMatch;//团办号是否匹配 0否/1是
	private String isOneselfCust;//是否我行客户 0否/1是
	private String isHavecardCust;//是否已持卡客户  0否/1是
	private String isHavecardStandard;//是否符合我行入组标准  0否/1是
	private String isPerfiltercustStandard;//是否符合预筛选客户入组标准  0否/1是
	private String isEdubackStandard;//是否学历入组标准  0否/1是
	private String isVecileStandard;//是否符合车辆入组标准  0否/1是
	private Date crtTime;//创建时间
	private Date crtDate;//创建日期
	private String crtUser;//创建人
	//getter&&setter
	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNationaity() {
		return nationaity;
	}
	public void setNationaity(String nationaity) {
		this.nationaity = nationaity;
	}
	public BigDecimal getAge() {
		return age;
	}
	public void setAge(BigDecimal age) {
		this.age = age;
	}
	public String getCertifiType() {
		return certifiType;
	}
	public void setCertifiType(String certifiType) {
		this.certifiType = certifiType;
	}
	public String getCertifiNo() {
		return certifiNo;
	}
	public void setCertifiNo(String certifiNo) {
		this.certifiNo = certifiNo;
	}
	public String getHousePhone() {
		return housePhone;
	}
	public void setHousePhone(String housePhone) {
		this.housePhone = housePhone;
	}
	public String getWorkCompany() {
		return workCompany;
	}
	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}
	public String getOfficeTel() {
		return officeTel;
	}
	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getApplyCard() {
		return applyCard;
	}
	public void setApplyCard(String applyCard) {
		this.applyCard = applyCard;
	}
	public String getPoliceStatus() {
		return policeStatus;
	}
	public void setPoliceStatus(String policeStatus) {
		this.policeStatus = policeStatus;
	}
	public String getTeamnoIsMatch() {
		return teamnoIsMatch;
	}
	public void setTeamnoIsMatch(String teamnoIsMatch) {
		this.teamnoIsMatch = teamnoIsMatch;
	}
	public String getIsOneselfCust() {
		return isOneselfCust;
	}
	public void setIsOneselfCust(String isOneselfCust) {
		this.isOneselfCust = isOneselfCust;
	}
	public String getIsHavecardCust() {
		return isHavecardCust;
	}
	public void setIsHavecardCust(String isHavecardCust) {
		this.isHavecardCust = isHavecardCust;
	}
	public String getIsHavecardStandard() {
		return isHavecardStandard;
	}
	public void setIsHavecardStandard(String isHavecardStandard) {
		this.isHavecardStandard = isHavecardStandard;
	}
	public String getIsPerfiltercustStandard() {
		return isPerfiltercustStandard;
	}
	public void setIsPerfiltercustStandard(String isPerfiltercustStandard) {
		this.isPerfiltercustStandard = isPerfiltercustStandard;
	}
	public String getIsEdubackStandard() {
		return isEdubackStandard;
	}
	public void setIsEdubackStandard(String isEdubackStandard) {
		this.isEdubackStandard = isEdubackStandard;
	}
	public String getIsVecileStandard() {
		return isVecileStandard;
	}
	public void setIsVecileStandard(String isVecileStandard) {
		this.isVecileStandard = isVecileStandard;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
		this.crtUser = crtUser;
	}

}
