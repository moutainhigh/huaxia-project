package com.huaxia.opas.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 第三方 & 人行 & 公共信息明细
 * 
 * @author zhiguo.li
 *
 */
public class PBOCPublicInfo implements Serializable {

	private static final long serialVersionUID = 754525992637693311L;

	// 欠税记录
	private List<TaxArrear> taxArrearList;

	// 民事判决记录
	private List<CivilJudgement> civilJudgementList;

	// 强制执行记录
	private List<ForceExecution> forceExecutionList;

	// 行政处罚记录
	private List<AdminPunishment> adminPunishmentList;

	// 住房公积金参缴记录
	private List<AccFund> accFundList;

	// 养老保险金缴存记录
	private List<EndowmentInsuranceDeposit> endowmentInsuranceDepositList;

	// 养老保险金发放记录
	private List<EndowmentInsuranceDeliver> endowmentInsuranceDeliverList;

	// 低保救助记录
	private List<Salvation> salvationList;

	// 执业资格记录
	private List<Competence> competenceList;

	// 行政奖励记录
	private List<AdminAward> adminAwardList;

	// 车辆交易和抵押记录
	private List<Vehicle> vehicleList;

	// 电信缴费记录
	private List<TelPayment> telPaymentList;

	public List<TaxArrear> getTaxArrearList() {
		return taxArrearList;
	}

	public void setTaxArrearList(List<TaxArrear> taxArrearList) {
		this.taxArrearList = taxArrearList;
	}

	public List<CivilJudgement> getCivilJudgementList() {
		return civilJudgementList;
	}

	public void setCivilJudgementList(List<CivilJudgement> civilJudgementList) {
		this.civilJudgementList = civilJudgementList;
	}

	public List<ForceExecution> getForceExecutionList() {
		return forceExecutionList;
	}

	public void setForceExecutionList(List<ForceExecution> forceExecutionList) {
		this.forceExecutionList = forceExecutionList;
	}

	public List<AdminPunishment> getAdminPunishmentList() {
		return adminPunishmentList;
	}

	public void setAdminPunishmentList(List<AdminPunishment> adminPunishmentList) {
		this.adminPunishmentList = adminPunishmentList;
	}

	public List<AccFund> getAccFundList() {
		return accFundList;
	}

	public void setAccFundList(List<AccFund> accFundList) {
		this.accFundList = accFundList;
	}

	public List<EndowmentInsuranceDeposit> getEndowmentInsuranceDepositList() {
		return endowmentInsuranceDepositList;
	}

	public void setEndowmentInsuranceDepositList(List<EndowmentInsuranceDeposit> endowmentInsuranceDepositList) {
		this.endowmentInsuranceDepositList = endowmentInsuranceDepositList;
	}

	public List<EndowmentInsuranceDeliver> getEndowmentInsuranceDeliverList() {
		return endowmentInsuranceDeliverList;
	}

	public void setEndowmentInsuranceDeliverList(List<EndowmentInsuranceDeliver> endowmentInsuranceDeliverList) {
		this.endowmentInsuranceDeliverList = endowmentInsuranceDeliverList;
	}

	public List<Salvation> getSalvationList() {
		return salvationList;
	}

	public void setSalvationList(List<Salvation> salvationList) {
		this.salvationList = salvationList;
	}

	public List<Competence> getCompetenceList() {
		return competenceList;
	}

	public void setCompetenceList(List<Competence> competenceList) {
		this.competenceList = competenceList;
	}

	public List<AdminAward> getAdminAwardList() {
		return adminAwardList;
	}

	public void setAdminAwardList(List<AdminAward> adminAwardList) {
		this.adminAwardList = adminAwardList;
	}

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	public List<TelPayment> getTelPaymentList() {
		return telPaymentList;
	}

	public void setTelPaymentList(List<TelPayment> telPaymentList) {
		this.telPaymentList = telPaymentList;
	}

	public class TaxArrear {

		// 主管税务机关
		private String organname;

		// 欠税总额
		private String taxArreaAmount;

		// 欠税统计日期
		private String revenuedate;

		public String getOrganname() {
			return organname;
		}

		public void setOrganname(String organname) {
			this.organname = organname;
		}

		public String getTaxArreaAmount() {
			return taxArreaAmount;
		}

		public void setTaxArreaAmount(String taxArreaAmount) {
			this.taxArreaAmount = taxArreaAmount;
		}

		public String getRevenuedate() {
			return revenuedate;
		}

		public void setRevenuedate(String revenuedate) {
			this.revenuedate = revenuedate;
		}
	}

	public class CivilJudgement {

		// 立案法院
		private String court;

		// 案由
		private String caseReason;

		// 立案日期
		private String registerDate;

		// 结案方式
		private String closedType;

		// 判决/调解结果
		private String caseResult;

		// 判决/调解生效日期
		private String caseValidatedate;

		// 诉讼标的
		private String suitObject;

		// 诉讼标的金额
		private String suitObjectMoney;

		public String getCourt() {
			return court;
		}

		public void setCourt(String court) {
			this.court = court;
		}

		public String getCaseReason() {
			return caseReason;
		}

		public void setCaseReason(String caseReason) {
			this.caseReason = caseReason;
		}

		public String getRegisterDate() {
			return registerDate;
		}

		public void setRegisterDate(String registerDate) {
			this.registerDate = registerDate;
		}

		public String getClosedType() {
			return closedType;
		}

		public void setClosedType(String closedType) {
			this.closedType = closedType;
		}

		public String getCaseResult() {
			return caseResult;
		}

		public void setCaseResult(String caseResult) {
			this.caseResult = caseResult;
		}

		public String getCaseValidatedate() {
			return caseValidatedate;
		}

		public void setCaseValidatedate(String caseValidatedate) {
			this.caseValidatedate = caseValidatedate;
		}

		public String getSuitObject() {
			return suitObject;
		}

		public void setSuitObject(String suitObject) {
			this.suitObject = suitObject;
		}

		public String getSuitObjectMoney() {
			return suitObjectMoney;
		}

		public void setSuitObjectMoney(String suitObjectMoney) {
			this.suitObjectMoney = suitObjectMoney;
		}

	}

	public class ForceExecution {

		// 立案法院
		private String court;

		// 执行案由
		private String caseReason;

		// 立案日期
		private String registerDate;

		// 结案方式
		private String closedType;

		// 案件状态
		private String caseState;

		// 结案日期
		private String closedDate;

		// 申请执行标的
		private String enforceObject;

		// 申请执行标的金额
		private String enforceObjectMoney;

		// 已执行标的
		private String alreadyEnforceObject;

		// 已执行标的金额
		private String alreadyEnforceObjectMoney;

		public String getCourt() {
			return court;
		}

		public void setCourt(String court) {
			this.court = court;
		}

		public String getCaseReason() {
			return caseReason;
		}

		public void setCaseReason(String caseReason) {
			this.caseReason = caseReason;
		}

		public String getRegisterDate() {
			return registerDate;
		}

		public void setRegisterDate(String registerDate) {
			this.registerDate = registerDate;
		}

		public String getClosedType() {
			return closedType;
		}

		public void setClosedType(String closedType) {
			this.closedType = closedType;
		}

		public String getCaseState() {
			return caseState;
		}

		public void setCaseState(String caseState) {
			this.caseState = caseState;
		}

		public String getClosedDate() {
			return closedDate;
		}

		public void setClosedDate(String closedDate) {
			this.closedDate = closedDate;
		}

		public String getEnforceObject() {
			return enforceObject;
		}

		public void setEnforceObject(String enforceObject) {
			this.enforceObject = enforceObject;
		}

		public String getEnforceObjectMoney() {
			return enforceObjectMoney;
		}

		public void setEnforceObjectMoney(String enforceObjectMoney) {
			this.enforceObjectMoney = enforceObjectMoney;
		}

		public String getAlreadyEnforceObject() {
			return alreadyEnforceObject;
		}

		public void setAlreadyEnforceObject(String alreadyEnforceObject) {
			this.alreadyEnforceObject = alreadyEnforceObject;
		}

		public String getAlreadyEnforceObjectMoney() {
			return alreadyEnforceObjectMoney;
		}

		public void setAlreadyEnforceObjectMoney(String alreadyEnforceObjectMoney) {
			this.alreadyEnforceObjectMoney = alreadyEnforceObjectMoney;
		}

	}

	public class AdminPunishment {

		// 处罚机构
		private String organname;

		// 处罚内容
		private String content;

		// 处罚金额
		private String money;

		// 生效日期
		private String beginDate;

		// 截止日期
		private String endDate;

		// 行政复议结果
		private String result;

		public String getOrganname() {
			return organname;
		}

		public void setOrganname(String organname) {
			this.organname = organname;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public String getBeginDate() {
			return beginDate;
		}

		public void setBeginDate(String beginDate) {
			this.beginDate = beginDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

	}

	public class AccFund {

		// 参缴地
		private String area;

		// 参缴日期
		private String registerDate;

		// 初缴月份
		private String firstMonth;

		// 缴至月份
		private String toMonth;

		// 缴费状态
		private String state;

		// 月缴存额
		private String pay;

		// 个人缴存比例
		private String ownPercent;

		// 单位缴存比例
		private String comPercent;

		// 缴费单位
		private String organname;

		// 信息更新日期
		private String getTime;

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}

		public String getRegisterDate() {
			return registerDate;
		}

		public void setRegisterDate(String registerDate) {
			this.registerDate = registerDate;
		}

		public String getFirstMonth() {
			return firstMonth;
		}

		public void setFirstMonth(String firstMonth) {
			this.firstMonth = firstMonth;
		}

		public String getToMonth() {
			return toMonth;
		}

		public void setToMonth(String toMonth) {
			this.toMonth = toMonth;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getPay() {
			return pay;
		}

		public void setPay(String pay) {
			this.pay = pay;
		}

		public String getOwnPercent() {
			return ownPercent;
		}

		public void setOwnPercent(String ownPercent) {
			this.ownPercent = ownPercent;
		}

		public String getComPercent() {
			return comPercent;
		}

		public void setComPercent(String comPercent) {
			this.comPercent = comPercent;
		}

		public String getOrganname() {
			return organname;
		}

		public void setOrganname(String organname) {
			this.organname = organname;
		}

		public String getGetTime() {
			return getTime;
		}

		public void setGetTime(String getTime) {
			this.getTime = getTime;
		}

	}

	public class EndowmentInsuranceDeposit {

		// 参保地
		private String area;

		// 参保日期
		private String registerDate;

		// 累计缴费月数
		private String monthDuration;

		// 参加工作月份
		private String workDate;

		// 缴费状态
		private String state;

		// 个人缴费基数
		private String ownBasicMoney;

		// 本月缴费金额
		private String money;

		// 缴费单位
		private String organname;

		// 中断或终止缴费原因
		private String pauseReason;

		// 信息更新日期
		private String getTime;

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}

		public String getRegisterDate() {
			return registerDate;
		}

		public void setRegisterDate(String registerDate) {
			this.registerDate = registerDate;
		}

		public String getMonthDuration() {
			return monthDuration;
		}

		public void setMonthDuration(String monthDuration) {
			this.monthDuration = monthDuration;
		}

		public String getWorkDate() {
			return workDate;
		}

		public void setWorkDate(String workDate) {
			this.workDate = workDate;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getOwnBasicMoney() {
			return ownBasicMoney;
		}

		public void setOwnBasicMoney(String ownBasicMoney) {
			this.ownBasicMoney = ownBasicMoney;
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public String getOrganname() {
			return organname;
		}

		public void setOrganname(String organname) {
			this.organname = organname;
		}

		public String getPauseReason() {
			return pauseReason;
		}

		public void setPauseReason(String pauseReason) {
			this.pauseReason = pauseReason;
		}

		public String getGetTime() {
			return getTime;
		}

		public void setGetTime(String getTime) {
			this.getTime = getTime;
		}

	}

	public class EndowmentInsuranceDeliver {

		// 发放地
		private String area;
		
		// 离退休类别
		private String retireType;
		
		// 离退休月份
		private String retiredDate;
		
		// 参加工作月份
		private String workDate;
		
		// 本月实发养老金
		private String money;
		
		// 停发原因
		private String pauseReason;
		
		// 原单位名称
		private String organname;
		
		// 信息更新日期
		private String getTime;

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}

		public String getRetireType() {
			return retireType;
		}

		public void setRetireType(String retireType) {
			this.retireType = retireType;
		}

		public String getRetiredDate() {
			return retiredDate;
		}

		public void setRetiredDate(String retiredDate) {
			this.retiredDate = retiredDate;
		}

		public String getWorkDate() {
			return workDate;
		}

		public void setWorkDate(String workDate) {
			this.workDate = workDate;
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public String getPauseReason() {
			return pauseReason;
		}

		public void setPauseReason(String pauseReason) {
			this.pauseReason = pauseReason;
		}

		public String getOrganname() {
			return organname;
		}

		public void setOrganname(String organname) {
			this.organname = organname;
		}

		public String getGetTime() {
			return getTime;
		}

		public void setGetTime(String getTime) {
			this.getTime = getTime;
		}

	}

	public class Salvation {

		// 人员类别
		private String PersonnelType;

		// 所在地
		private String area;

		// 工作单位
		private String organname;

		// 家庭月收入
		private String money;

		// 申请日期
		private String registerDate;

		// 批准日期
		private String passDate;

		// 信息更新日期
		private String getTime;

		public String getPersonnelType() {
			return PersonnelType;
		}

		public void setPersonnelType(String personnelType) {
			PersonnelType = personnelType;
		}

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}

		public String getOrganname() {
			return organname;
		}

		public void setOrganname(String organname) {
			this.organname = organname;
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public String getRegisterDate() {
			return registerDate;
		}

		public void setRegisterDate(String registerDate) {
			this.registerDate = registerDate;
		}

		public String getPassDate() {
			return passDate;
		}

		public void setPassDate(String passDate) {
			this.passDate = passDate;
		}

		public String getGetTime() {
			return getTime;
		}

		public void setGetTime(String getTime) {
			this.getTime = getTime;
		}

	}

	public class Competence {

		// 执业资格名称
		private String competencyName;

		// 等级
		private String grade;

		// 获得日期
		private String awardDate;

		// 到期日期
		private String endDate;

		// 吊销日期
		private String revokeDate;

		// 颁发机构
		private String organname;

		// 机构所在地
		private String area;

		public String getCompetencyName() {
			return competencyName;
		}

		public void setCompetencyName(String competencyName) {
			this.competencyName = competencyName;
		}

		public String getGrade() {
			return grade;
		}

		public void setGrade(String grade) {
			this.grade = grade;
		}

		public String getAwardDate() {
			return awardDate;
		}

		public void setAwardDate(String awardDate) {
			this.awardDate = awardDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getRevokeDate() {
			return revokeDate;
		}

		public void setRevokeDate(String revokeDate) {
			this.revokeDate = revokeDate;
		}

		public String getOrganname() {
			return organname;
		}

		public void setOrganname(String organname) {
			this.organname = organname;
		}

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}

	}

	public class AdminAward {

		// 奖励机构
		private String organname;

		// 奖励内容
		private String content;

		// 生效日期
		private String beginDate;

		// 截止日期
		private String endDate;

		public String getOrganname() {
			return organname;
		}

		public void setOrganname(String organname) {
			this.organname = organname;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getBeginDate() {
			return beginDate;
		}

		public void setBeginDate(String beginDate) {
			this.beginDate = beginDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

	}

	public class Vehicle {

		// 发动机号
		private String engineCode;

		// 车牌号码
		private String licenseCode;

		// 品牌
		private String brand;

		// 车辆类型
		private String carType;

		// 使用性质
		private String useCharacter;

		// 车辆状态
		private String state;

		// 抵押标记
		private String pledgeFlag;

		// 信息更新日期
		private String getTime;

		public String getEngineCode() {
			return engineCode;
		}

		public void setEngineCode(String engineCode) {
			this.engineCode = engineCode;
		}

		public String getLicenseCode() {
			return licenseCode;
		}

		public void setLicenseCode(String licenseCode) {
			this.licenseCode = licenseCode;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public String getCarType() {
			return carType;
		}

		public void setCarType(String carType) {
			this.carType = carType;
		}

		public String getUseCharacter() {
			return useCharacter;
		}

		public void setUseCharacter(String useCharacter) {
			this.useCharacter = useCharacter;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getPledgeFlag() {
			return pledgeFlag;
		}

		public void setPledgeFlag(String pledgeFlag) {
			this.pledgeFlag = pledgeFlag;
		}

		public String getGetTime() {
			return getTime;
		}

		public void setGetTime(String getTime) {
			this.getTime = getTime;
		}

	}

	public class TelPayment {

		// 电信运营商
		private String organname;

		// 业务类型
		private String type;

		// 业务开通日期
		private String registerDate;

		// 当前缴费状态
		private String state;

		// 当前欠费金额
		private String arrearMoney;

		// 当前欠费月数
		private String arrearMonths;

		// 最近24个月缴费状态
		private String status24;

		// 记账年月
		private String getTime;

		public String getOrganname() {
			return organname;
		}

		public void setOrganname(String organname) {
			this.organname = organname;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getRegisterDate() {
			return registerDate;
		}

		public void setRegisterDate(String registerDate) {
			this.registerDate = registerDate;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getArrearMoney() {
			return arrearMoney;
		}

		public void setArrearMoney(String arrearMoney) {
			this.arrearMoney = arrearMoney;
		}

		public String getArrearMonths() {
			return arrearMonths;
		}

		public void setArrearMonths(String arrearMonths) {
			this.arrearMonths = arrearMonths;
		}

		public String getStatus24() {
			return status24;
		}

		public void setStatus24(String status24) {
			this.status24 = status24;
		}

		public String getGetTime() {
			return getTime;
		}

		public void setGetTime(String getTime) {
			this.getTime = getTime;
		}
	}

}
