package com.huaxia.opas.domain.collect;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 增强信息采集
 * 
 * @author zhiguo.li
 *
 */
public class EnhanceInfo implements Serializable {

	private static final long serialVersionUID = -90581636747326261L;
	
	// UUID
	private String insideAppNo;

	// 申请件编号
	private String appId;

	// 用工性质
	private String staffNature;

	// 行政级别（工作单位）
	private String executiveLevel;

	// 单位性质
	private String unitNature;
	
	// 政府机构编制
	private String orgAuthStrength;
	
	// 政府机构级别
	private String orgLevel;

	// 事业单位编制
	private String institutionAuthStrength;

	// 事业单位级别
	private String institutionLevel;

	// 学校性质
	private String schoolNature;

	// 学校类别
	private String schoolType;

	// 学校职位级别
	private String schoolPositionLevel;

	// 医院性质
	private String hospitalNature;

	// 医院级别
	private String hospitalLevel;

	// 医院等级
	private String hospitalGrade;

	// 医生级别
	private String doctorLevel;

	// 国有企业行业类型
	private String industryType;

	// 下属企业
	private String affiliates;

	// 行员类别
	private String bankStaffType;

	// 行员级别
	private String bankStaffLevel;

	// 私营企业类别
	private String privateBusinessType;

	// 财力客户
	private String financesCust;

	// 武汉财智分期类别
	private String wuHanCzType;

	// 特殊客户
	private String specialCust;

	// MBA学校名称
	private String mbaSchoolName;

	// 学历证明类型
	private String eduCertifType;

	// 学历类型
	private String eduBackType;

	// 学历
	private String eduBack;

	// 房产证明类型
	private String houseProveType;

	// 房产证明份数
	private Integer houseProveNum;

	// 房产面积
	private BigDecimal houseArea;

	// 房产总价
	private BigDecimal houseTotalPrice;

	// 房产位置
	private String houseLocation;

	// 房贷已还款时间
	private BigDecimal houseRepayTime;

	// 车产证明类型
	private String vehicleProveType;

	// 车辆手别
	private String vehicleHandType;

	// 车产价格
	private BigDecimal vehicleTotalPrice;

	// 车龄
	private BigDecimal vehicleAge;

	// 行业代码
	private String industryCode;

	// 职位代码
	private String positionCode;

	// 公司成立日期
	private Date companyEstablishDate;

	// 注册资本
	private Integer registeredCapital;

	// 当前单位入职时间
	private Date companyEntryDate;

	// 实际月收入
	private BigDecimal realMonthIncome;

	// 可采纳月收入
	private BigDecimal adoptMonthIncome;

	// 可采纳项（外键）
	private String adoptItemId;

	// 人行抵押贷款负债
	private BigDecimal pbocMontgageDebt;

	// 人行无抵押贷款负债
	private BigDecimal pbocUnMontgageDebt;

	// 人行信用卡负债
	private BigDecimal pbocCreditDebt;

	// 人行无抵押贷款敞口
	private BigDecimal pbocUnmontgageOpen;

	// 信用卡敞口
	private BigDecimal creditOpen;

	// 核实无抵押贷款负债
	private BigDecimal verifryCleanLoanDebt;

	// 核实抵押贷款负债
	private BigDecimal verifryMortgageDebt;

	// 核实无抵押贷款敞口
	private BigDecimal verifryCleanLoanOpen;

	// 客户需求额度
	private BigDecimal custExpectCredit;

	// 易达金申请期数
	private Integer applyTerm;

	// 费率
	private BigDecimal rate;
	
	// BLAZE - 证明材料
	private String certifiMaterial;
	
	// BLAZE - 备注
	private String remark;
	
	// BLAZE - 满足客群类别
	private String custGroupType;
	
	// BLAZE - 实际客群
	private String realGroup;
	
	// BLAZE - 工资流水月收入
	private BigDecimal salaryMonthIncome;
	
	// BLAZE - 完税证明月收入
	private BigDecimal taxMonthIncome;
	
	// BLAZE - 工作证明月收入
	private BigDecimal workMonthIncome;
	
	// BLAZE - 社保月收入
	private BigDecimal socialSecurityMonthIncome;
	
	// BLAZE - 公积金月收入
	private BigDecimal fundMonthIncome;
	
	// BLAZE - 配偶月收入
	private BigDecimal spouseMonthIncome;
	
	// BLAZE - 房租月收入
	private BigDecimal houseMonthIncome;
	
	// BLAZE - 系统建议可采纳月收入
	private BigDecimal sysAdoptMonthIncome;
	
	// BLAZE - 职级替代月收入
	private BigDecimal rankMonthIncome;

	// 创建日期
	private Date createDate;

	// 创建人
	private String createUser;

	// 最后修改日期
	private Date lastUpdateDate;

	// 最后修改人
	private String lastUpdateUser;
	
	// BLAZE - 无抵押DTI
	private BigDecimal dtiUnMontgage;
	
	// BLAZE - 综合DTI
	private BigDecimal dtiSynthetical;
	
	// BLAZE - 风险敞口MUE
	private BigDecimal mueRiskOpen;
	
	// BLAZE - 系统建议额度
	private BigDecimal sysAdviseLimit;
	
	public BigDecimal getSysAdviseLimit() {
		return sysAdviseLimit;
	}

	public void setSysAdviseLimit(BigDecimal sysAdviseLimit) {
		this.sysAdviseLimit = sysAdviseLimit;
	}

	public EnhanceInfo() {
	}

	public String getInsideAppNo() {
		return insideAppNo;
	}

	public void setInsideAppNo(String insideAppNo) {
		this.insideAppNo = insideAppNo;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getStaffNature() {
		return staffNature;
	}

	public void setStaffNature(String staffNature) {
		this.staffNature = staffNature;
	}

	public String getExecutiveLevel() {
		return executiveLevel;
	}

	public void setExecutiveLevel(String executiveLevel) {
		this.executiveLevel = executiveLevel;
	}

	public String getUnitNature() {
		return unitNature;
	}

	public void setUnitNature(String unitNature) {
		this.unitNature = unitNature;
	}

	public String getOrgAuthStrength() {
		return orgAuthStrength;
	}

	public void setOrgAuthStrength(String orgAuthStrength) {
		this.orgAuthStrength = orgAuthStrength;
	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public String getInstitutionAuthStrength() {
		return institutionAuthStrength;
	}

	public void setInstitutionAuthStrength(String institutionAuthStrength) {
		this.institutionAuthStrength = institutionAuthStrength;
	}

	public String getInstitutionLevel() {
		return institutionLevel;
	}

	public void setInstitutionLevel(String institutionLevel) {
		this.institutionLevel = institutionLevel;
	}

	public String getSchoolNature() {
		return schoolNature;
	}

	public void setSchoolNature(String schoolNature) {
		this.schoolNature = schoolNature;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public String getSchoolPositionLevel() {
		return schoolPositionLevel;
	}

	public void setSchoolPositionLevel(String schoolPositionLevel) {
		this.schoolPositionLevel = schoolPositionLevel;
	}

	public String getHospitalNature() {
		return hospitalNature;
	}

	public void setHospitalNature(String hospitalNature) {
		this.hospitalNature = hospitalNature;
	}

	public String getHospitalLevel() {
		return hospitalLevel;
	}

	public void setHospitalLevel(String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}

	public String getHospitalGrade() {
		return hospitalGrade;
	}

	public void setHospitalGrade(String hospitalGrade) {
		this.hospitalGrade = hospitalGrade;
	}

	public String getDoctorLevel() {
		return doctorLevel;
	}

	public void setDoctorLevel(String doctorLevel) {
		this.doctorLevel = doctorLevel;
	}

	public String getAffiliates() {
		return affiliates;
	}

	public void setAffiliates(String affiliates) {
		this.affiliates = affiliates;
	}

	public String getBankStaffType() {
		return bankStaffType;
	}

	public void setBankStaffType(String bankStaffType) {
		this.bankStaffType = bankStaffType;
	}

	public String getBankStaffLevel() {
		return bankStaffLevel;
	}

	public void setBankStaffLevel(String bankStaffLevel) {
		this.bankStaffLevel = bankStaffLevel;
	}

	public String getPrivateBusinessType() {
		return privateBusinessType;
	}

	public void setPrivateBusinessType(String privateBusinessType) {
		this.privateBusinessType = privateBusinessType;
	}

	public String getFinancesCust() {
		return financesCust;
	}

	public void setFinancesCust(String financesCust) {
		this.financesCust = financesCust;
	}

	public String getWuHanCzType() {
		return wuHanCzType;
	}

	public void setWuHanCzType(String wuHanCzType) {
		this.wuHanCzType = wuHanCzType;
	}

	public String getSpecialCust() {
		return specialCust;
	}

	public void setSpecialCust(String specialCust) {
		this.specialCust = specialCust;
	}

	public String getMbaSchoolName() {
		return mbaSchoolName;
	}

	public void setMbaSchoolName(String mbaSchoolName) {
		this.mbaSchoolName = mbaSchoolName;
	}

	public String getEduCertifType() {
		return eduCertifType;
	}

	public void setEduCertifType(String eduCertifType) {
		this.eduCertifType = eduCertifType;
	}

	public String getEduBackType() {
		return eduBackType;
	}

	public void setEduBackType(String eduBackType) {
		this.eduBackType = eduBackType;
	}

	public String getEduBack() {
		return eduBack;
	}

	public void setEduBack(String eduBack) {
		this.eduBack = eduBack;
	}

	public String getHouseProveType() {
		return houseProveType;
	}

	public void setHouseProveType(String houseProveType) {
		this.houseProveType = houseProveType;
	}

	public Integer getHouseProveNum() {
		return houseProveNum;
	}

	public void setHouseProveNum(Integer houseProveNum) {
		this.houseProveNum = houseProveNum;
	}

	public BigDecimal getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(BigDecimal houseArea) {
		this.houseArea = houseArea;
	}

	public BigDecimal getHouseTotalPrice() {
		return houseTotalPrice;
	}

	public void setHouseTotalPrice(BigDecimal houseTotalPrice) {
		this.houseTotalPrice = houseTotalPrice;
	}

	public String getHouseLocation() {
		return houseLocation;
	}

	public void setHouseLocation(String houseLocation) {
		this.houseLocation = houseLocation;
	}

	public BigDecimal getHouseRepayTime() {
		return houseRepayTime;
	}

	public void setHouseRepayTime(BigDecimal houseRepayTime) {
		this.houseRepayTime = houseRepayTime;
	}

	public String getVehicleProveType() {
		return vehicleProveType;
	}

	public void setVehicleProveType(String vehicleProveType) {
		this.vehicleProveType = vehicleProveType;
	}

	public String getVehicleHandType() {
		return vehicleHandType;
	}

	public void setVehicleHandType(String vehicleHandType) {
		this.vehicleHandType = vehicleHandType;
	}

	public BigDecimal getVehicleTotalPrice() {
		return vehicleTotalPrice;
	}

	public void setVehicleTotalPrice(BigDecimal vehicleTotalPrice) {
		this.vehicleTotalPrice = vehicleTotalPrice;
	}

	public BigDecimal getVehicleAge() {
		return vehicleAge;
	}

	public void setVehicleAge(BigDecimal vehicleAge) {
		this.vehicleAge = vehicleAge;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public Date getCompanyEstablishDate() {
		return companyEstablishDate;
	}

	public void setCompanyEstablishDate(Date companyEstablishDate) {
		this.companyEstablishDate = companyEstablishDate;
	}

	public Integer getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(Integer registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public Date getCompanyEntryDate() {
		return companyEntryDate;
	}

	public void setCompanyEntryDate(Date companyEntryDate) {
		this.companyEntryDate = companyEntryDate;
	}

	public BigDecimal getRealMonthIncome() {
		return realMonthIncome;
	}

	public void setRealMonthIncome(BigDecimal realMonthIncome) {
		this.realMonthIncome = realMonthIncome;
	}

	public BigDecimal getAdoptMonthIncome() {
		return adoptMonthIncome;
	}

	public void setAdoptMonthIncome(BigDecimal adoptMonthIncome) {
		this.adoptMonthIncome = adoptMonthIncome;
	}

	public String getAdoptItemId() {
		return adoptItemId;
	}

	public void setAdoptItemId(String adoptItemId) {
		this.adoptItemId = adoptItemId;
	}

	public BigDecimal getPbocMontgageDebt() {
		return pbocMontgageDebt;
	}

	public void setPbocMontgageDebt(BigDecimal pbocMontgageDebt) {
		this.pbocMontgageDebt = pbocMontgageDebt;
	}

	public BigDecimal getPbocUnMontgageDebt() {
		return pbocUnMontgageDebt;
	}

	public void setPbocUnMontgageDebt(BigDecimal pbocUnMontgageDebt) {
		this.pbocUnMontgageDebt = pbocUnMontgageDebt;
	}

	public BigDecimal getPbocCreditDebt() {
		return pbocCreditDebt;
	}

	public void setPbocCreditDebt(BigDecimal pbocCreditDebt) {
		this.pbocCreditDebt = pbocCreditDebt;
	}

	public BigDecimal getPbocUnmontgageOpen() {
		return pbocUnmontgageOpen;
	}

	public void setPbocUnmontgageOpen(BigDecimal pbocUnmontgageOpen) {
		this.pbocUnmontgageOpen = pbocUnmontgageOpen;
	}

	public BigDecimal getCreditOpen() {
		return creditOpen;
	}

	public void setCreditOpen(BigDecimal creditOpen) {
		this.creditOpen = creditOpen;
	}

	public BigDecimal getVerifryCleanLoanDebt() {
		return verifryCleanLoanDebt;
	}

	public void setVerifryCleanLoanDebt(BigDecimal verifryCleanLoanDebt) {
		this.verifryCleanLoanDebt = verifryCleanLoanDebt;
	}

	public BigDecimal getVerifryMortgageDebt() {
		return verifryMortgageDebt;
	}

	public void setVerifryMortgageDebt(BigDecimal verifryMortgageDebt) {
		this.verifryMortgageDebt = verifryMortgageDebt;
	}

	public BigDecimal getVerifryCleanLoanOpen() {
		return verifryCleanLoanOpen;
	}

	public void setVerifryCleanLoanOpen(BigDecimal verifryCleanLoanOpen) {
		this.verifryCleanLoanOpen = verifryCleanLoanOpen;
	}

	public BigDecimal getCustExpectCredit() {
		return custExpectCredit;
	}

	public void setCustExpectCredit(BigDecimal custExpectCredit) {
		this.custExpectCredit = custExpectCredit;
	}

	public Integer getApplyTerm() {
		return applyTerm;
	}

	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getCertifiMaterial() {
		return certifiMaterial;
	}

	public void setCertifiMaterial(String certifiMaterial) {
		this.certifiMaterial = certifiMaterial;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCustGroupType() {
		return custGroupType;
	}

	public void setCustGroupType(String custGroupType) {
		this.custGroupType = custGroupType;
	}

	public String getRealGroup() {
		return realGroup;
	}

	public void setRealGroup(String realGroup) {
		this.realGroup = realGroup;
	}

	public BigDecimal getSalaryMonthIncome() {
		return salaryMonthIncome;
	}

	public void setSalaryMonthIncome(BigDecimal salaryMonthIncome) {
		this.salaryMonthIncome = salaryMonthIncome;
	}

	public BigDecimal getTaxMonthIncome() {
		return taxMonthIncome;
	}

	public void setTaxMonthIncome(BigDecimal taxMonthIncome) {
		this.taxMonthIncome = taxMonthIncome;
	}

	public BigDecimal getWorkMonthIncome() {
		return workMonthIncome;
	}

	public void setWorkMonthIncome(BigDecimal workMonthIncome) {
		this.workMonthIncome = workMonthIncome;
	}

	public BigDecimal getSocialSecurityMonthIncome() {
		return socialSecurityMonthIncome;
	}

	public void setSocialSecurityMonthIncome(BigDecimal socialSecurityMonthIncome) {
		this.socialSecurityMonthIncome = socialSecurityMonthIncome;
	}

	public BigDecimal getFundMonthIncome() {
		return fundMonthIncome;
	}

	public void setFundMonthIncome(BigDecimal fundMonthIncome) {
		this.fundMonthIncome = fundMonthIncome;
	}

	public BigDecimal getSpouseMonthIncome() {
		return spouseMonthIncome;
	}

	public void setSpouseMonthIncome(BigDecimal spouseMonthIncome) {
		this.spouseMonthIncome = spouseMonthIncome;
	}

	public BigDecimal getHouseMonthIncome() {
		return houseMonthIncome;
	}

	public void setHouseMonthIncome(BigDecimal houseMonthIncome) {
		this.houseMonthIncome = houseMonthIncome;
	}

	public BigDecimal getSysAdoptMonthIncome() {
		return sysAdoptMonthIncome;
	}

	public void setSysAdoptMonthIncome(BigDecimal sysAdoptMonthIncome) {
		this.sysAdoptMonthIncome = sysAdoptMonthIncome;
	}

	public BigDecimal getRankMonthIncome() {
		return rankMonthIncome;
	}

	public void setRankMonthIncome(BigDecimal rankMonthIncome) {
		this.rankMonthIncome = rankMonthIncome;
	}

	public BigDecimal getDtiUnMontgage() {
		return dtiUnMontgage;
	}

	public void setDtiUnMontgage(BigDecimal dtiUnMontgage) {
		this.dtiUnMontgage = dtiUnMontgage;
	}

	public BigDecimal getDtiSynthetical() {
		return dtiSynthetical;
	}

	public void setDtiSynthetical(BigDecimal dtiSynthetical) {
		this.dtiSynthetical = dtiSynthetical;
	}

	public BigDecimal getMueRiskOpen() {
		return mueRiskOpen;
	}

	public void setMueRiskOpen(BigDecimal mueRiskOpen) {
		this.mueRiskOpen = mueRiskOpen;
	}

}
