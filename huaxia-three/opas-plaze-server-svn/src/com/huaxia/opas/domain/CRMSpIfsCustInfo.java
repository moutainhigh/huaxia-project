package com.huaxia.opas.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 第三方 & CRM & 客户基本信息（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsCustInfo extends CRM implements Serializable {

	private static final long serialVersionUID = -7724518984635292452L;

	// 客户ID
	private String custId;
	
	// 客户号
	private String custCode;
	
	// 被合并的客户号
	private String mgCustCode;
	
	// 客户名称
	private String custName;
	
	// 性别
	private String sex;
	
	// 年龄
	private Integer age;
	
	// 证件类型
	private String certType;
	
	// 证件号码
	private String certNo;
	
	// 转换后的证件号码
	private String certNoNew;
	
	// 证件有效期
	private String validat;
	
	// 联系手机
	private String mobilePhone;
	
	// 联系电话
	private String phone;
	
	// 住宅固定电话
	private String homePhone;
	
	// 公司固定电话
	private String busPhone;
	
	// 最早开户时间
	private String openDate;
	
	// 婚姻状态
	private String marriedStatus;
	
	// 教育程度
	private String educationStatus;
	
	// 住宅地址
	private String homeAddr;
	
	// 住宅邮政编码
	private String homePostCode;
	
	// 联系地址
	private String addr;
	
	// 邮政编码
	private String postCode;
	
	// 单位地址
	private String busAddr;
	
	// 单位邮政编码
	private String busPostCode;
	
	// 传真
	private String fax;
	
	// EMAIL
	private String emailAddr;
	
	// 投资偏好
	private String preinvst;
	
	// 血型
	private String bloodType;
	
	// 宗教信仰
	private String relBelief;
	
	// 联系时间
	private String contTime;
	
	// 出生日期
	private String bornDate;
	
	// 出生日期阴历
	private String lunarcal;
	
	// 民族
	private String nation;
	
	// 国籍
	private String country;
	
	// 籍贯
	private String originPlace;
	
	// 开户分行
	private String branch;
	
	// 开户支行
	private String subBranch;
	
	// 是否我行员工
	private String isEmp;
	
	// 满意度
	private String satis;
	
	// 工作单位
	private String company;
	
	// 工作岗位
	private String workPost;
	
	// 职业代码
	private String profession;
	
	// 职务
	private String dutie;
	
	// 单位性质
	private String compant;
	
	// 行业类别
	private String indType;
	
	// 年收入
	private BigDecimal yearIncome;
	
	// 现单位工龄
	private String workAge;
	
	// 客户转介绍关系
	private String custMan;
	
	// 客户状态
	private String status;
	
	// 是否免打扰
	private String isDisturb;
	
	// 数据日期
	private String dDate;
	
	// 是否持有信用卡
	private String isCreditCard;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getMgCustCode() {
		return mgCustCode;
	}

	public void setMgCustCode(String mgCustCode) {
		this.mgCustCode = mgCustCode;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getCertNoNew() {
		return certNoNew;
	}

	public void setCertNoNew(String certNoNew) {
		this.certNoNew = certNoNew;
	}

	public String getValidat() {
		return validat;
	}

	public void setValidat(String validat) {
		this.validat = validat;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getBusPhone() {
		return busPhone;
	}

	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getMarriedStatus() {
		return marriedStatus;
	}

	public void setMarriedStatus(String marriedStatus) {
		this.marriedStatus = marriedStatus;
	}

	public String getEducationStatus() {
		return educationStatus;
	}

	public void setEducationStatus(String educationStatus) {
		this.educationStatus = educationStatus;
	}

	public String getHomeAddr() {
		return homeAddr;
	}

	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}

	public String getHomePostCode() {
		return homePostCode;
	}

	public void setHomePostCode(String homePostCode) {
		this.homePostCode = homePostCode;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getBusAddr() {
		return busAddr;
	}

	public void setBusAddr(String busAddr) {
		this.busAddr = busAddr;
	}

	public String getBusPostCode() {
		return busPostCode;
	}

	public void setBusPostCode(String busPostCode) {
		this.busPostCode = busPostCode;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getPreinvst() {
		return preinvst;
	}

	public void setPreinvst(String preinvst) {
		this.preinvst = preinvst;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getRelBelief() {
		return relBelief;
	}

	public void setRelBelief(String relBelief) {
		this.relBelief = relBelief;
	}

	public String getContTime() {
		return contTime;
	}

	public void setContTime(String contTime) {
		this.contTime = contTime;
	}

	public String getBornDate() {
		return bornDate;
	}

	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}

	public String getLunarcal() {
		return lunarcal;
	}

	public void setLunarcal(String lunarcal) {
		this.lunarcal = lunarcal;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOriginPlace() {
		return originPlace;
	}

	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSubBranch() {
		return subBranch;
	}

	public void setSubBranch(String subBranch) {
		this.subBranch = subBranch;
	}

	public String getIsEmp() {
		return isEmp;
	}

	public void setIsEmp(String isEmp) {
		this.isEmp = isEmp;
	}

	public String getSatis() {
		return satis;
	}

	public void setSatis(String satis) {
		this.satis = satis;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getWorkPost() {
		return workPost;
	}

	public void setWorkPost(String workPost) {
		this.workPost = workPost;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getDutie() {
		return dutie;
	}

	public void setDutie(String dutie) {
		this.dutie = dutie;
	}

	public String getCompant() {
		return compant;
	}

	public void setCompant(String compant) {
		this.compant = compant;
	}

	public String getIndType() {
		return indType;
	}

	public void setIndType(String indType) {
		this.indType = indType;
	}

	public BigDecimal getYearIncome() {
		return yearIncome;
	}

	public void setYearIncome(BigDecimal yearIncome) {
		this.yearIncome = yearIncome;
	}

	public String getWorkAge() {
		return workAge;
	}

	public void setWorkAge(String workAge) {
		this.workAge = workAge;
	}

	public String getCustMan() {
		return custMan;
	}

	public void setCustMan(String custMan) {
		this.custMan = custMan;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsDisturb() {
		return isDisturb;
	}

	public void setIsDisturb(String isDisturb) {
		this.isDisturb = isDisturb;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}

	public String getIsCreditCard() {
		return isCreditCard;
	}

	public void setIsCreditCard(String isCreditCard) {
		this.isCreditCard = isCreditCard;
	}

}
