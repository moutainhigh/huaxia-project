package com.huaxia.opas.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 第三方 & 人行 & 个人基本信息
 * 
 * @author zhiguo.li
 *
 */
public class PBOCPersonalInfo implements Serializable {

	private static final long serialVersionUID = 6602516045428162157L;
	
	// 申请件编号
	private String appId;

	// 身份信息
	private Identity identity;
	
	// 配偶信息
	private Spouse spouse;

	// 居住信息
	private List<Residence> residenceList;

	// 职业信息
	private List<Professional> professionalList;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public Spouse getSpouse() {
		return spouse;
	}

	public void setSpouse(Spouse spouse) {
		this.spouse = spouse;
	}

	public List<Residence> getResidenceList() {
		return residenceList;
	}

	public void setResidenceList(List<Residence> residenceList) {
		this.residenceList = residenceList;
	}

	public List<Professional> getProfessionalList() {
		return professionalList;
	}

	public void setProfessionalList(List<Professional> professionalList) {
		this.professionalList = professionalList;
	}

	public class Identity {

		// 性别
		private String gender;

		// 出生日期
		private String birthday;

		// 婚姻状况
		private String maritalState;

		// 手机号码
		private String mobile;

		// 单位电话
		private String officeTelphoneNo;

		// 住宅电话
		private String homeTelphoneNo;

		// 学历
		private String eduLevel;

		// 学位
		private String eduDegree;

		// 通讯地址
		private String postAddress;
		
		// 户籍地址
		private String registeredAddress;

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getMaritalState() {
			return maritalState;
		}

		public void setMaritalState(String maritalState) {
			this.maritalState = maritalState;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getOfficeTelphoneNo() {
			return officeTelphoneNo;
		}

		public void setOfficeTelphoneNo(String officeTelphoneNo) {
			this.officeTelphoneNo = officeTelphoneNo;
		}

		public String getHomeTelphoneNo() {
			return homeTelphoneNo;
		}

		public void setHomeTelphoneNo(String homeTelphoneNo) {
			this.homeTelphoneNo = homeTelphoneNo;
		}

		public String getEduLevel() {
			return eduLevel;
		}

		public void setEduLevel(String eduLevel) {
			this.eduLevel = eduLevel;
		}

		public String getEduDegree() {
			return eduDegree;
		}

		public void setEduDegree(String eduDegree) {
			this.eduDegree = eduDegree;
		}

		public String getPostAddress() {
			return postAddress;
		}

		public void setPostAddress(String postAddress) {
			this.postAddress = postAddress;
		}

		public String getRegisteredAddress() {
			return registeredAddress;
		}

		public void setRegisteredAddress(String registeredAddress) {
			this.registeredAddress = registeredAddress;
		}

	}
	
	public class Spouse {
		
		// 姓名
		private String name;
		
		// 证件类型
		private String certtype;
		
		// 证件号码
		private String certno;
		
		// 工作单位
		private String employer;
		
		// 联系电话
		private String telephoneNo;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCerttype() {
			return certtype;
		}

		public void setCerttype(String certtype) {
			this.certtype = certtype;
		}

		public String getCertno() {
			return certno;
		}

		public void setCertno(String certno) {
			this.certno = certno;
		}

		public String getEmployer() {
			return employer;
		}

		public void setEmployer(String employer) {
			this.employer = employer;
		}

		public String getTelephoneNo() {
			return telephoneNo;
		}

		public void setTelephoneNo(String telephoneNo) {
			this.telephoneNo = telephoneNo;
		}
		
	}
	
	public class Residence {
		
		// 居住地址
		private String address;
		
		// 居住情况
		private String residenceType;
		
		// 信息获取时间
		private String getTime;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getResidenceType() {
			return residenceType;
		}

		public void setResidenceType(String residenceType) {
			this.residenceType = residenceType;
		}

		public String getGetTime() {
			return getTime;
		}

		public void setGetTime(String getTime) {
			this.getTime = getTime;
		}
		
	}
	
	public class Professional {
		
		// 工作单位
		private String employer;
		
		// 单位地址
		private String employerAddress;	
		
		// 职业
		private String occupation;
		
		// 行业
		private String industry;
		
		// 职务
		private String duty;
		
		// 职称
		private String title;
		
		// 进入本单位年份
		private String startYear;
		
		// 信息更新时间
		private String getTime;

		public String getEmployer() {
			return employer;
		}

		public void setEmployer(String employer) {
			this.employer = employer;
		}

		public String getEmployerAddress() {
			return employerAddress;
		}

		public void setEmployerAddress(String employerAddress) {
			this.employerAddress = employerAddress;
		}

		public String getOccupation() {
			return occupation;
		}

		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}

		public String getIndustry() {
			return industry;
		}

		public void setIndustry(String industry) {
			this.industry = industry;
		}

		public String getDuty() {
			return duty;
		}

		public void setDuty(String duty) {
			this.duty = duty;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getStartYear() {
			return startYear;
		}

		public void setStartYear(String startYear) {
			this.startYear = startYear;
		}

		public String getGetTime() {
			return getTime;
		}

		public void setGetTime(String getTime) {
			this.getTime = getTime;
		}
		
	}

}
