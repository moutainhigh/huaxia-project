package com.huaxia.opas.domain.audit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ApprovaInfoSupp implements Serializable {

	private static final long serialVersionUID = -470509711668179499L;
	private String appId;
	// 行业代码
	private String industryCode;
	// 职位代码
	private String jobCode;
	// 房产信息来源
	private String houseInfoFrom;
	// 房产价值
	private BigDecimal houseCost;
	// 房产面积
	private BigDecimal houseArea;
	// 车产价值
	private String carCost;
	// 车辆手别三
	private String carHand;
	// 车龄
	private String carOld;
	// 注册时间
	private Date registerDate;
	// 发证时间
	private Date issueDate;
	// 最早开户日期
	private Date perOpendate;
	// 月代发工资额
	private BigDecimal perPayWage;
	// 日均总资产余额
	private BigDecimal perAssetBalance;
	// 金融资产可用总额
	private BigDecimal perFinAssetSum;
	// 贷款本金
	private BigDecimal perLoanpri;
	// 个人客户贷款状态
	private String perLoanstatus;
	// 最早开户日期
	private Date pubOpendate;
	// 授信额度
	private BigDecimal pubCreditlimit;
	// 对公客户贷款状态
	private String pubLoanstatus;
	// 对公存款余额
	private BigDecimal pubSavebalance;
	// 对公存款日均余额
	private BigDecimal pubSaveDaybalance;
	// 华夏卡中心员工用工性质
	private String hxCardcentreStaff;
	// 华夏卡中心员工职级
	private String hxCardcentreStaffLevel;
	// 华夏银行员工用工性质
	private String hxBankStaff;
	// 华夏银行员工职级
	private String hxBankStaffLevel;
	// 我行对公客户推荐人
	private String refree;
	// 我行个人客户推荐人
	private String refree2;
	// 非我行客户推荐人
	private String refree3;
	// 我行对公客户推荐额度
	private Long refreeLimit;
	// 我行个人客户推荐额度
	private Long refreeLimit2;
	// 非我行客户推荐额度
	private Long refreeLimit3;
	// 当前缴交状态
	private String sbCurrPayStatus;
	// 月缴交金额
	private BigDecimal sbMonthPayment;
	// 参保日期
	private Date sbPayDate;
	// 月缴交基数
	private BigDecimal sbMonthPaybase;
	// 当前缴交单位
	private String sbCurrPaycompany;
	// 当前单位缴交月数
	private Long sbCurrCompanyMonth;
	// 公积金缴交状态
	private String gjjPayStatus;
	// 月缴交金额
	private BigDecimal gjjMonthPayment;
	// 个人缴存比例
	private Short gjjPerPayrate;
	// 参缴日期
	private Date gjjPayDate;
	// 缴至月份
	private Date gjjEndMonth;
	// 学习形式
	private String eduModel;
	// 学历类型
	private String eduType;
	// 高学历白领客户
	private String highCust;
	// 精英盈利客户
	private String eliteEarncust;
	// 年轻较高学历客户
	private String youngHigheducust;
	// 普通盈利客户
	private String normalearncust;
	// 预筛选客户
	private String prescreenCust;
	// 特殊项目/团办客户
	private String majorProject;
	// 其他目标客户
	private String otherCust;
	// 房产信息
	private String houseInfo;
	// 行驶证
	private String drivingLicense;
	// 交叉销售客户
	private String crossSalecust;
	// 本行员工
	private String bankStaff;
	// vip客户
	private String vipCust;
	// 社保客户
	private String sbCust;
	// 公积金客户
	private String gjjCust;

	private String userCode;

	private Date operTime;

	// 车辆性质 0营运 1非营运
	private String carCategroy;
	// 收入类型
	private String salaryCategroy;
	
	//第三方代缴
	private String thirdPartPay;
	//个体
	private String individual;
	//个体成立日期
	private Date individualMonth;

	public String getThirdPartPay() {
		return thirdPartPay;
	}

	public void setThirdPartPay(String thirdPartPay) {
		this.thirdPartPay = thirdPartPay;
	}

	public String getIndividual() {
		return individual;
	}

	public void setIndividual(String individual) {
		this.individual = individual;
	}

	public Date getIndividualMonth() {
		return individualMonth;
	}

	public void setIndividualMonth(Date individualMonth) {
		this.individualMonth = individualMonth;
	}

	public String getSalaryCategroy() {
		return salaryCategroy;
	}

	public void setSalaryCategroy(String salaryCategroy) {
		this.salaryCategroy = salaryCategroy;
	}

	public String getYearSalary() {
		return yearSalary;
	}

	public void setYearSalary(String yearSalary) {
		this.yearSalary = yearSalary;
	}

	public String getBalanceCategroy() {
		return balanceCategroy;
	}

	public void setBalanceCategroy(String balanceCategroy) {
		this.balanceCategroy = balanceCategroy;
	}

	public String getBalanceAcount() {
		return balanceAcount;
	}

	public void setBalanceAcount(String balanceAcount) {
		this.balanceAcount = balanceAcount;
	}

	public Date getSalarySignDate() {
		return salarySignDate;
	}

	public void setSalarySignDate(Date salarySignDate) {
		this.salarySignDate = salarySignDate;
	}

	public Date getSalarySignLatestDate() {
		return salarySignLatestDate;
	}

	public void setSalarySignLatestDate(Date salarySignLatestDate) {
		this.salarySignLatestDate = salarySignLatestDate;
	}

	public String getSpecialProject() {
		return specialProject;
	}

	public void setSpecialProject(String specialProject) {
		this.specialProject = specialProject;
	}

	public String getProjectCategroy() {
		return projectCategroy;
	}

	public void setProjectCategroy(String projectCategroy) {
		this.projectCategroy = projectCategroy;
	}

	public String getProjectAcount() {
		return projectAcount;
	}

	public void setProjectAcount(String projectAcount) {
		this.projectAcount = projectAcount;
	}

	public String getProjectMemo() {
		return projectMemo;
	}

	public void setProjectMemo(String projectMemo) {
		this.projectMemo = projectMemo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// 年收入
	private String yearSalary;
	// 资产类型
	private String balanceCategroy;
	// 资产金额
	private String balanceAcount;
	// 我行个人客户-代发工资签约日期
	private Date salarySignDate;
	// 我行个人客户-最近一次代发工资签约日期
	private Date salarySignLatestDate;
	// 是否特殊项目
	private String specialProject;
	// 项目类型
	private String projectCategroy;
	// 项目金额
	private String projectAcount;
	// 项目内容
	private String projectMemo;

	public String getCarCategroy() {
		return carCategroy;
	}

	public void setCarCategroy(String carCategroy) {
		this.carCategroy = carCategroy;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public String getRefree2() {
		return refree2;
	}

	public void setRefree2(String refree2) {
		this.refree2 = refree2;
	}

	public String getRefree3() {
		return refree3;
	}

	public void setRefree3(String refree3) {
		this.refree3 = refree3;
	}

	public Long getRefreeLimit2() {
		return refreeLimit2;
	}

	public void setRefreeLimit2(Long refreeLimit2) {
		this.refreeLimit2 = refreeLimit2;
	}

	public Long getRefreeLimit3() {
		return refreeLimit3;
	}

	public void setRefreeLimit3(Long refreeLimit3) {
		this.refreeLimit3 = refreeLimit3;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode == null ? null : industryCode.trim();
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode == null ? null : jobCode.trim();
	}

	public String getHouseInfoFrom() {
		return houseInfoFrom;
	}

	public void setHouseInfoFrom(String houseInfoFrom) {
		this.houseInfoFrom = houseInfoFrom == null ? null : houseInfoFrom.trim();
	}

	public BigDecimal getHouseCost() {
		return houseCost;
	}

	public void setHouseCost(BigDecimal houseCost) {
		this.houseCost = houseCost;
	}

	public BigDecimal getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(BigDecimal houseArea) {
		this.houseArea = houseArea;
	}

	public String getCarCost() {
		return carCost;
	}

	public void setCarCost(String carCost) {
		this.carCost = carCost;
	}

	public String getCarHand() {
		return carHand;
	}

	public void setCarHand(String carHand) {
		this.carHand = carHand == null ? null : carHand.trim();
	}

	public String getCarOld() {
		return carOld;
	}

	public void setCarOld(String carOld) {
		this.carOld = carOld;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getPerOpendate() {
		return perOpendate;
	}

	public void setPerOpendate(Date perOpendate) {
		this.perOpendate = perOpendate;
	}

	public BigDecimal getPerPayWage() {
		return perPayWage;
	}

	public void setPerPayWage(BigDecimal perPayWage) {
		this.perPayWage = perPayWage;
	}

	public BigDecimal getPerAssetBalance() {
		return perAssetBalance;
	}

	public void setPerAssetBalance(BigDecimal perAssetBalance) {
		this.perAssetBalance = perAssetBalance;
	}

	public BigDecimal getPerFinAssetSum() {
		return perFinAssetSum;
	}

	public void setPerFinAssetSum(BigDecimal perFinAssetSum) {
		this.perFinAssetSum = perFinAssetSum;
	}

	public BigDecimal getPerLoanpri() {
		return perLoanpri;
	}

	public void setPerLoanpri(BigDecimal perLoanpri) {
		this.perLoanpri = perLoanpri;
	}

	public String getPerLoanstatus() {
		return perLoanstatus;
	}

	public void setPerLoanstatus(String perLoanstatus) {
		this.perLoanstatus = perLoanstatus == null ? null : perLoanstatus.trim();
	}

	public Date getPubOpendate() {
		return pubOpendate;
	}

	public void setPubOpendate(Date pubOpendate) {
		this.pubOpendate = pubOpendate;
	}

	public BigDecimal getPubCreditlimit() {
		return pubCreditlimit;
	}

	public void setPubCreditlimit(BigDecimal pubCreditlimit) {
		this.pubCreditlimit = pubCreditlimit;
	}

	public String getPubLoanstatus() {
		return pubLoanstatus;
	}

	public void setPubLoanstatus(String pubLoanstatus) {
		this.pubLoanstatus = pubLoanstatus == null ? null : pubLoanstatus.trim();
	}

	public BigDecimal getPubSavebalance() {
		return pubSavebalance;
	}

	public void setPubSavebalance(BigDecimal pubSavebalance) {
		this.pubSavebalance = pubSavebalance;
	}

	public BigDecimal getPubSaveDaybalance() {
		return pubSaveDaybalance;
	}

	public void setPubSaveDaybalance(BigDecimal pubSaveDaybalance) {
		this.pubSaveDaybalance = pubSaveDaybalance;
	}

	public String getHxCardcentreStaff() {
		return hxCardcentreStaff;
	}

	public void setHxCardcentreStaff(String hxCardcentreStaff) {
		this.hxCardcentreStaff = hxCardcentreStaff == null ? null : hxCardcentreStaff.trim();
	}

	public String getHxCardcentreStaffLevel() {
		return hxCardcentreStaffLevel;
	}

	public void setHxCardcentreStaffLevel(String hxCardcentreStaffLevel) {
		this.hxCardcentreStaffLevel = hxCardcentreStaffLevel == null ? null : hxCardcentreStaffLevel.trim();
	}

	public String getHxBankStaff() {
		return hxBankStaff;
	}

	public void setHxBankStaff(String hxBankStaff) {
		this.hxBankStaff = hxBankStaff == null ? null : hxBankStaff.trim();
	}

	public String getHxBankStaffLevel() {
		return hxBankStaffLevel;
	}

	public void setHxBankStaffLevel(String hxBankStaffLevel) {
		this.hxBankStaffLevel = hxBankStaffLevel == null ? null : hxBankStaffLevel.trim();
	}

	public String getRefree() {
		return refree;
	}

	public void setRefree(String refree) {
		this.refree = refree == null ? null : refree.trim();
	}

	public Long getRefreeLimit() {
		return refreeLimit;
	}

	public void setRefreeLimit(Long refreeLimit) {
		this.refreeLimit = refreeLimit;
	}

	public String getSbCurrPayStatus() {
		return sbCurrPayStatus;
	}

	public void setSbCurrPayStatus(String sbCurrPayStatus) {
		this.sbCurrPayStatus = sbCurrPayStatus == null ? null : sbCurrPayStatus.trim();
	}

	public BigDecimal getSbMonthPayment() {
		return sbMonthPayment;
	}

	public void setSbMonthPayment(BigDecimal sbMonthPayment) {
		this.sbMonthPayment = sbMonthPayment;
	}

	public Date getSbPayDate() {
		return sbPayDate;
	}

	public void setSbPayDate(Date sbPayDate) {
		this.sbPayDate = sbPayDate;
	}

	public BigDecimal getSbMonthPaybase() {
		return sbMonthPaybase;
	}

	public void setSbMonthPaybase(BigDecimal sbMonthPaybase) {
		this.sbMonthPaybase = sbMonthPaybase;
	}

	public String getSbCurrPaycompany() {
		return sbCurrPaycompany;
	}

	public void setSbCurrPaycompany(String sbCurrPaycompany) {
		this.sbCurrPaycompany = sbCurrPaycompany == null ? null : sbCurrPaycompany.trim();
	}

	public Long getSbCurrCompanyMonth() {
		return sbCurrCompanyMonth;
	}

	public void setSbCurrCompanyMonth(Long sbCurrCompanyMonth) {
		this.sbCurrCompanyMonth = sbCurrCompanyMonth;
	}

	public String getGjjPayStatus() {
		return gjjPayStatus;
	}

	public void setGjjPayStatus(String gjjPayStatus) {
		this.gjjPayStatus = gjjPayStatus == null ? null : gjjPayStatus.trim();
	}

	public BigDecimal getGjjMonthPayment() {
		return gjjMonthPayment;
	}

	public void setGjjMonthPayment(BigDecimal gjjMonthPayment) {
		this.gjjMonthPayment = gjjMonthPayment;
	}

	public Short getGjjPerPayrate() {
		return gjjPerPayrate;
	}

	public void setGjjPerPayrate(Short gjjPerPayrate) {
		this.gjjPerPayrate = gjjPerPayrate;
	}

	public Date getGjjPayDate() {
		return gjjPayDate;
	}

	public void setGjjPayDate(Date gjjPayDate) {
		this.gjjPayDate = gjjPayDate;
	}

	public Date getGjjEndMonth() {
		return gjjEndMonth;
	}

	public void setGjjEndMonth(Date gjjEndMonth) {
		this.gjjEndMonth = gjjEndMonth;
	}

	public String getEduModel() {
		return eduModel;
	}

	public void setEduModel(String eduModel) {
		this.eduModel = eduModel == null ? null : eduModel.trim();
	}

	public String getEduType() {
		return eduType;
	}

	public void setEduType(String eduType) {
		this.eduType = eduType == null ? null : eduType.trim();
	}

	public String getHighCust() {
		return highCust;
	}

	public void setHighCust(String highCust) {
		this.highCust = highCust == null ? null : highCust.trim();
	}

	public String getEliteEarncust() {
		return eliteEarncust;
	}

	public void setEliteEarncust(String eliteEarncust) {
		this.eliteEarncust = eliteEarncust == null ? null : eliteEarncust.trim();
	}

	public String getYoungHigheducust() {
		return youngHigheducust;
	}

	public void setYoungHigheducust(String youngHigheducust) {
		this.youngHigheducust = youngHigheducust == null ? null : youngHigheducust.trim();
	}

	public String getNormalearncust() {
		return normalearncust;
	}

	public void setNormalearncust(String normalearncust) {
		this.normalearncust = normalearncust == null ? null : normalearncust.trim();
	}

	public String getPrescreenCust() {
		return prescreenCust;
	}

	public void setPrescreenCust(String prescreenCust) {
		this.prescreenCust = prescreenCust == null ? null : prescreenCust.trim();
	}

	public String getMajorProject() {
		return majorProject;
	}

	public void setMajorProject(String majorProject) {
		this.majorProject = majorProject == null ? null : majorProject.trim();
	}

	public String getOtherCust() {
		return otherCust;
	}

	public void setOtherCust(String otherCust) {
		this.otherCust = otherCust == null ? null : otherCust.trim();
	}

	public String getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(String houseInfo) {
		this.houseInfo = houseInfo == null ? null : houseInfo.trim();
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense == null ? null : drivingLicense.trim();
	}

	public String getCrossSalecust() {
		return crossSalecust;
	}

	public void setCrossSalecust(String crossSalecust) {
		this.crossSalecust = crossSalecust == null ? null : crossSalecust.trim();
	}

	public String getBankStaff() {
		return bankStaff;
	}

	public void setBankStaff(String bankStaff) {
		this.bankStaff = bankStaff == null ? null : bankStaff.trim();
	}

	public String getVipCust() {
		return vipCust;
	}

	public void setVipCust(String vipCust) {
		this.vipCust = vipCust == null ? null : vipCust.trim();
	}

	public String getSbCust() {
		return sbCust;
	}

	public void setSbCust(String sbCust) {
		this.sbCust = sbCust == null ? null : sbCust.trim();
	}

	public String getGjjCust() {
		return gjjCust;
	}

	public void setGjjCust(String gjjCust) {
		this.gjjCust = gjjCust == null ? null : gjjCust.trim();
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Override
	public String toString() {
		return "ApprovaInfoSupp [appId=" + appId + ", industryCode=" + industryCode + ", jobCode=" + jobCode
				+ ", houseInfoFrom=" + houseInfoFrom + ", houseCost=" + houseCost + ", houseArea=" + houseArea
				+ ", carCost=" + carCost + ", carHand=" + carHand + ", carOld=" + carOld + ", registerDate="
				+ registerDate + ", issueDate=" + issueDate + ", perOpendate=" + perOpendate + ", perPayWage="
				+ perPayWage + ", perAssetBalance=" + perAssetBalance + ", perFinAssetSum=" + perFinAssetSum
				+ ", perLoanpri=" + perLoanpri + ", perLoanstatus=" + perLoanstatus + ", pubOpendate=" + pubOpendate
				+ ", pubCreditlimit=" + pubCreditlimit + ", pubLoanstatus=" + pubLoanstatus + ", pubSavebalance="
				+ pubSavebalance + ", pubSaveDaybalance=" + pubSaveDaybalance + ", hxCardcentreStaff="
				+ hxCardcentreStaff + ", hxCardcentreStaffLevel=" + hxCardcentreStaffLevel + ", hxBankStaff="
				+ hxBankStaff + ", hxBankStaffLevel=" + hxBankStaffLevel + ", refree=" + refree + ", refree2=" + refree2
				+ ", refree3=" + refree3 + ", refreeLimit=" + refreeLimit + ", refreeLimit2=" + refreeLimit2
				+ ", refreeLimit3=" + refreeLimit3 + ", sbCurrPayStatus=" + sbCurrPayStatus + ", sbMonthPayment="
				+ sbMonthPayment + ", sbPayDate=" + sbPayDate + ", sbMonthPaybase=" + sbMonthPaybase
				+ ", sbCurrPaycompany=" + sbCurrPaycompany + ", sbCurrCompanyMonth=" + sbCurrCompanyMonth
				+ ", gjjPayStatus=" + gjjPayStatus + ", gjjMonthPayment=" + gjjMonthPayment + ", gjjPerPayrate="
				+ gjjPerPayrate + ", gjjPayDate=" + gjjPayDate + ", gjjEndMonth=" + gjjEndMonth + ", eduModel="
				+ eduModel + ", eduType=" + eduType + ", highCust=" + highCust + ", eliteEarncust=" + eliteEarncust
				+ ", youngHigheducust=" + youngHigheducust + ", normalearncust=" + normalearncust + ", prescreenCust="
				+ prescreenCust + ", majorProject=" + majorProject + ", otherCust=" + otherCust + ", houseInfo="
				+ houseInfo + ", drivingLicense=" + drivingLicense + ", crossSalecust=" + crossSalecust + ", bankStaff="
				+ bankStaff + ", vipCust=" + vipCust + ", sbCust=" + sbCust + ", gjjCust=" + gjjCust + ", userCode="
				+ userCode + ", operTime=" + operTime + ", thirdPartPay=" + thirdPartPay +", individual="
				+ individual +", individualMonth=" + individualMonth +"]";
		
	}
}
