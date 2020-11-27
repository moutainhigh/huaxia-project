package com.huaxia.opas.domain.rule;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Opasbizapprovainfosupp implements Serializable{
    private String insideAppNo;

    private String empUniversalLimit;

    private String empSpendLimit;

    private String busCardLimit;

    private String busCardRank;

    private String batchAppPeopleNo;

    private String otherAssets;

    private String batchEnterpriseNo;

    private String batchProgrammeNo;

    private String oneBrank;

    private String whetherExpress;

    private String otherRmbBankDeposit;

    private String rmbBankDeposit;

    private String wageWaterAnnualIncome;

    private String taxBillCalcuAnnualIncome;

    private String airMiles;

    private String socialSecurity;

    private String vehicle;

    private String houseInitialLoan;

    private String houseMonthlyPayment;

    private String ownHouse;

    private String luckyFee;

    private String memo;

    private String judgeReason1;

    private String judgeReason2;

    private String judgeReason3;

    private String judgeStageUniversal;

    private String judgeLimit;

    private String judgeResult;

    private String privateBankLogo;

    private String bankCardLimit;

    private String issueBankTime;

    private String issueBank;

    private String companyStartTime;

    private String companyRegFund;

    private String hopeUniversalLimit;

    private String hopeSpendLimit;

    private String staffRankLimit;

    private String staffRank;

    private String executiveRank;

    private String mspMatch;

    private String govTeacherRank;

    private String branchAgree;

    private String mobileStaffLimit;

    private String mobileStaffRank;

    private String batchLimitFactor;

    private String batchRank;

    private String batchPayRate;

    private String batchMonthPayAmt;

    private String openManCheckFinishFlag;

    private String batchSchemaOpenManMobile;

    private String batchSchemaOpenManPhone;

    private String batchSchemaOpenManPosition;

    private String batchSchemaOpenManDept;

    private String batchSchemaOpenManName;

    private String batchSchemaSaleManagerCont;

    private String batchSchemaSaleManagerName;

    private String batchSchemaSaleManContact;

    private String batchSchemaSaleManName;

    private String batchSchemaCreditScale;

    private String batchSchemaPersons;

    private String batchSchemaValidDate;

    private String batchSchemaSendDate;

    private String memoInfo;

    private String creditRefuseReason1;

    private String creditRefuseReason2;

    private String creditRefuseReason3;

    private String manpJudgeConc;

    private String payerIncome;

    private String guaranteeIncome;

    private String income;

    private String shLicense;

    private String purchaseInvoice;

    private String rank;

    private Short mortgageLoans;

    private String insType;

    private String downFeeRate;

    private String jobCode;

    private String serviceYears;

    private String dCompName;

    private String acctType;

    private String operatorNo;

    private Date crtTime;

    private Date crtDate;

    private String crtUser;

    private Date lstUpdTime;

    private Date lstUpdDate;

    private String lstUpdUser;

    private Date batchDate;

    private String recStatus;

    private String scrLevel;

    private String topLevel;

    private String appId;

    private String eduType;

    private BigDecimal sbCurrCompanyMonth;

    private String sbCurrPaycompany;

    private BigDecimal sbMonthPaybase;

    private Date sbPayDate;

    private BigDecimal sbMonthPayment;

    private String sbCurrPayStatus;

    private Long refreeLimit;

    private String refree;

    private String hxBankStaffLevel;

    private String hxBankStaff;

    public String getInsideAppNo() {
        return insideAppNo;
    }

    public void setInsideAppNo(String insideAppNo) {
        this.insideAppNo = insideAppNo == null ? null : insideAppNo.trim();
    }

    public String getEmpUniversalLimit() {
        return empUniversalLimit;
    }

    public void setEmpUniversalLimit(String empUniversalLimit) {
        this.empUniversalLimit = empUniversalLimit == null ? null : empUniversalLimit.trim();
    }

    public String getEmpSpendLimit() {
        return empSpendLimit;
    }

    public void setEmpSpendLimit(String empSpendLimit) {
        this.empSpendLimit = empSpendLimit == null ? null : empSpendLimit.trim();
    }

    public String getBusCardLimit() {
        return busCardLimit;
    }

    public void setBusCardLimit(String busCardLimit) {
        this.busCardLimit = busCardLimit == null ? null : busCardLimit.trim();
    }

    public String getBusCardRank() {
        return busCardRank;
    }

    public void setBusCardRank(String busCardRank) {
        this.busCardRank = busCardRank == null ? null : busCardRank.trim();
    }

    public String getBatchAppPeopleNo() {
        return batchAppPeopleNo;
    }

    public void setBatchAppPeopleNo(String batchAppPeopleNo) {
        this.batchAppPeopleNo = batchAppPeopleNo == null ? null : batchAppPeopleNo.trim();
    }

    public String getOtherAssets() {
        return otherAssets;
    }

    public void setOtherAssets(String otherAssets) {
        this.otherAssets = otherAssets == null ? null : otherAssets.trim();
    }

    public String getBatchEnterpriseNo() {
        return batchEnterpriseNo;
    }

    public void setBatchEnterpriseNo(String batchEnterpriseNo) {
        this.batchEnterpriseNo = batchEnterpriseNo == null ? null : batchEnterpriseNo.trim();
    }

    public String getBatchProgrammeNo() {
        return batchProgrammeNo;
    }

    public void setBatchProgrammeNo(String batchProgrammeNo) {
        this.batchProgrammeNo = batchProgrammeNo == null ? null : batchProgrammeNo.trim();
    }

    public String getOneBrank() {
        return oneBrank;
    }

    public void setOneBrank(String oneBrank) {
        this.oneBrank = oneBrank == null ? null : oneBrank.trim();
    }

    public String getWhetherExpress() {
        return whetherExpress;
    }

    public void setWhetherExpress(String whetherExpress) {
        this.whetherExpress = whetherExpress == null ? null : whetherExpress.trim();
    }

    public String getOtherRmbBankDeposit() {
        return otherRmbBankDeposit;
    }

    public void setOtherRmbBankDeposit(String otherRmbBankDeposit) {
        this.otherRmbBankDeposit = otherRmbBankDeposit == null ? null : otherRmbBankDeposit.trim();
    }

    public String getRmbBankDeposit() {
        return rmbBankDeposit;
    }

    public void setRmbBankDeposit(String rmbBankDeposit) {
        this.rmbBankDeposit = rmbBankDeposit == null ? null : rmbBankDeposit.trim();
    }

    public String getWageWaterAnnualIncome() {
        return wageWaterAnnualIncome;
    }

    public void setWageWaterAnnualIncome(String wageWaterAnnualIncome) {
        this.wageWaterAnnualIncome = wageWaterAnnualIncome == null ? null : wageWaterAnnualIncome.trim();
    }

    public String getTaxBillCalcuAnnualIncome() {
        return taxBillCalcuAnnualIncome;
    }

    public void setTaxBillCalcuAnnualIncome(String taxBillCalcuAnnualIncome) {
        this.taxBillCalcuAnnualIncome = taxBillCalcuAnnualIncome == null ? null : taxBillCalcuAnnualIncome.trim();
    }

    public String getAirMiles() {
        return airMiles;
    }

    public void setAirMiles(String airMiles) {
        this.airMiles = airMiles == null ? null : airMiles.trim();
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity == null ? null : socialSecurity.trim();
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle == null ? null : vehicle.trim();
    }

    public String getHouseInitialLoan() {
        return houseInitialLoan;
    }

    public void setHouseInitialLoan(String houseInitialLoan) {
        this.houseInitialLoan = houseInitialLoan == null ? null : houseInitialLoan.trim();
    }

    public String getHouseMonthlyPayment() {
        return houseMonthlyPayment;
    }

    public void setHouseMonthlyPayment(String houseMonthlyPayment) {
        this.houseMonthlyPayment = houseMonthlyPayment == null ? null : houseMonthlyPayment.trim();
    }

    public String getOwnHouse() {
        return ownHouse;
    }

    public void setOwnHouse(String ownHouse) {
        this.ownHouse = ownHouse == null ? null : ownHouse.trim();
    }

    public String getLuckyFee() {
        return luckyFee;
    }

    public void setLuckyFee(String luckyFee) {
        this.luckyFee = luckyFee == null ? null : luckyFee.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getJudgeReason1() {
        return judgeReason1;
    }

    public void setJudgeReason1(String judgeReason1) {
        this.judgeReason1 = judgeReason1 == null ? null : judgeReason1.trim();
    }

    public String getJudgeReason2() {
        return judgeReason2;
    }

    public void setJudgeReason2(String judgeReason2) {
        this.judgeReason2 = judgeReason2 == null ? null : judgeReason2.trim();
    }

    public String getJudgeReason3() {
        return judgeReason3;
    }

    public void setJudgeReason3(String judgeReason3) {
        this.judgeReason3 = judgeReason3 == null ? null : judgeReason3.trim();
    }

    public String getJudgeStageUniversal() {
        return judgeStageUniversal;
    }

    public void setJudgeStageUniversal(String judgeStageUniversal) {
        this.judgeStageUniversal = judgeStageUniversal == null ? null : judgeStageUniversal.trim();
    }

    public String getJudgeLimit() {
        return judgeLimit;
    }

    public void setJudgeLimit(String judgeLimit) {
        this.judgeLimit = judgeLimit == null ? null : judgeLimit.trim();
    }

    public String getJudgeResult() {
        return judgeResult;
    }

    public void setJudgeResult(String judgeResult) {
        this.judgeResult = judgeResult == null ? null : judgeResult.trim();
    }

    public String getPrivateBankLogo() {
        return privateBankLogo;
    }

    public void setPrivateBankLogo(String privateBankLogo) {
        this.privateBankLogo = privateBankLogo == null ? null : privateBankLogo.trim();
    }

    public String getBankCardLimit() {
        return bankCardLimit;
    }

    public void setBankCardLimit(String bankCardLimit) {
        this.bankCardLimit = bankCardLimit == null ? null : bankCardLimit.trim();
    }

    public String getIssueBankTime() {
        return issueBankTime;
    }

    public void setIssueBankTime(String issueBankTime) {
        this.issueBankTime = issueBankTime == null ? null : issueBankTime.trim();
    }

    public String getIssueBank() {
        return issueBank;
    }

    public void setIssueBank(String issueBank) {
        this.issueBank = issueBank == null ? null : issueBank.trim();
    }

    public String getCompanyStartTime() {
        return companyStartTime;
    }

    public void setCompanyStartTime(String companyStartTime) {
        this.companyStartTime = companyStartTime == null ? null : companyStartTime.trim();
    }

    public String getCompanyRegFund() {
        return companyRegFund;
    }

    public void setCompanyRegFund(String companyRegFund) {
        this.companyRegFund = companyRegFund == null ? null : companyRegFund.trim();
    }

    public String getHopeUniversalLimit() {
        return hopeUniversalLimit;
    }

    public void setHopeUniversalLimit(String hopeUniversalLimit) {
        this.hopeUniversalLimit = hopeUniversalLimit == null ? null : hopeUniversalLimit.trim();
    }

    public String getHopeSpendLimit() {
        return hopeSpendLimit;
    }

    public void setHopeSpendLimit(String hopeSpendLimit) {
        this.hopeSpendLimit = hopeSpendLimit == null ? null : hopeSpendLimit.trim();
    }

    public String getStaffRankLimit() {
        return staffRankLimit;
    }

    public void setStaffRankLimit(String staffRankLimit) {
        this.staffRankLimit = staffRankLimit == null ? null : staffRankLimit.trim();
    }

    public String getStaffRank() {
        return staffRank;
    }

    public void setStaffRank(String staffRank) {
        this.staffRank = staffRank == null ? null : staffRank.trim();
    }

    public String getExecutiveRank() {
        return executiveRank;
    }

    public void setExecutiveRank(String executiveRank) {
        this.executiveRank = executiveRank == null ? null : executiveRank.trim();
    }

    public String getMspMatch() {
        return mspMatch;
    }

    public void setMspMatch(String mspMatch) {
        this.mspMatch = mspMatch == null ? null : mspMatch.trim();
    }

    public String getGovTeacherRank() {
        return govTeacherRank;
    }

    public void setGovTeacherRank(String govTeacherRank) {
        this.govTeacherRank = govTeacherRank == null ? null : govTeacherRank.trim();
    }

    public String getBranchAgree() {
        return branchAgree;
    }

    public void setBranchAgree(String branchAgree) {
        this.branchAgree = branchAgree == null ? null : branchAgree.trim();
    }

    public String getMobileStaffLimit() {
        return mobileStaffLimit;
    }

    public void setMobileStaffLimit(String mobileStaffLimit) {
        this.mobileStaffLimit = mobileStaffLimit == null ? null : mobileStaffLimit.trim();
    }

    public String getMobileStaffRank() {
        return mobileStaffRank;
    }

    public void setMobileStaffRank(String mobileStaffRank) {
        this.mobileStaffRank = mobileStaffRank == null ? null : mobileStaffRank.trim();
    }

    public String getBatchLimitFactor() {
        return batchLimitFactor;
    }

    public void setBatchLimitFactor(String batchLimitFactor) {
        this.batchLimitFactor = batchLimitFactor == null ? null : batchLimitFactor.trim();
    }

    public String getBatchRank() {
        return batchRank;
    }

    public void setBatchRank(String batchRank) {
        this.batchRank = batchRank == null ? null : batchRank.trim();
    }

    public String getBatchPayRate() {
        return batchPayRate;
    }

    public void setBatchPayRate(String batchPayRate) {
        this.batchPayRate = batchPayRate == null ? null : batchPayRate.trim();
    }

    public String getBatchMonthPayAmt() {
        return batchMonthPayAmt;
    }

    public void setBatchMonthPayAmt(String batchMonthPayAmt) {
        this.batchMonthPayAmt = batchMonthPayAmt == null ? null : batchMonthPayAmt.trim();
    }

    public String getOpenManCheckFinishFlag() {
        return openManCheckFinishFlag;
    }

    public void setOpenManCheckFinishFlag(String openManCheckFinishFlag) {
        this.openManCheckFinishFlag = openManCheckFinishFlag == null ? null : openManCheckFinishFlag.trim();
    }

    public String getBatchSchemaOpenManMobile() {
        return batchSchemaOpenManMobile;
    }

    public void setBatchSchemaOpenManMobile(String batchSchemaOpenManMobile) {
        this.batchSchemaOpenManMobile = batchSchemaOpenManMobile == null ? null : batchSchemaOpenManMobile.trim();
    }

    public String getBatchSchemaOpenManPhone() {
        return batchSchemaOpenManPhone;
    }

    public void setBatchSchemaOpenManPhone(String batchSchemaOpenManPhone) {
        this.batchSchemaOpenManPhone = batchSchemaOpenManPhone == null ? null : batchSchemaOpenManPhone.trim();
    }

    public String getBatchSchemaOpenManPosition() {
        return batchSchemaOpenManPosition;
    }

    public void setBatchSchemaOpenManPosition(String batchSchemaOpenManPosition) {
        this.batchSchemaOpenManPosition = batchSchemaOpenManPosition == null ? null : batchSchemaOpenManPosition.trim();
    }

    public String getBatchSchemaOpenManDept() {
        return batchSchemaOpenManDept;
    }

    public void setBatchSchemaOpenManDept(String batchSchemaOpenManDept) {
        this.batchSchemaOpenManDept = batchSchemaOpenManDept == null ? null : batchSchemaOpenManDept.trim();
    }

    public String getBatchSchemaOpenManName() {
        return batchSchemaOpenManName;
    }

    public void setBatchSchemaOpenManName(String batchSchemaOpenManName) {
        this.batchSchemaOpenManName = batchSchemaOpenManName == null ? null : batchSchemaOpenManName.trim();
    }

    public String getBatchSchemaSaleManagerCont() {
        return batchSchemaSaleManagerCont;
    }

    public void setBatchSchemaSaleManagerCont(String batchSchemaSaleManagerCont) {
        this.batchSchemaSaleManagerCont = batchSchemaSaleManagerCont == null ? null : batchSchemaSaleManagerCont.trim();
    }

    public String getBatchSchemaSaleManagerName() {
        return batchSchemaSaleManagerName;
    }

    public void setBatchSchemaSaleManagerName(String batchSchemaSaleManagerName) {
        this.batchSchemaSaleManagerName = batchSchemaSaleManagerName == null ? null : batchSchemaSaleManagerName.trim();
    }

    public String getBatchSchemaSaleManContact() {
        return batchSchemaSaleManContact;
    }

    public void setBatchSchemaSaleManContact(String batchSchemaSaleManContact) {
        this.batchSchemaSaleManContact = batchSchemaSaleManContact == null ? null : batchSchemaSaleManContact.trim();
    }

    public String getBatchSchemaSaleManName() {
        return batchSchemaSaleManName;
    }

    public void setBatchSchemaSaleManName(String batchSchemaSaleManName) {
        this.batchSchemaSaleManName = batchSchemaSaleManName == null ? null : batchSchemaSaleManName.trim();
    }

    public String getBatchSchemaCreditScale() {
        return batchSchemaCreditScale;
    }

    public void setBatchSchemaCreditScale(String batchSchemaCreditScale) {
        this.batchSchemaCreditScale = batchSchemaCreditScale == null ? null : batchSchemaCreditScale.trim();
    }

    public String getBatchSchemaPersons() {
        return batchSchemaPersons;
    }

    public void setBatchSchemaPersons(String batchSchemaPersons) {
        this.batchSchemaPersons = batchSchemaPersons == null ? null : batchSchemaPersons.trim();
    }

    public String getBatchSchemaValidDate() {
        return batchSchemaValidDate;
    }

    public void setBatchSchemaValidDate(String batchSchemaValidDate) {
        this.batchSchemaValidDate = batchSchemaValidDate == null ? null : batchSchemaValidDate.trim();
    }

    public String getBatchSchemaSendDate() {
        return batchSchemaSendDate;
    }

    public void setBatchSchemaSendDate(String batchSchemaSendDate) {
        this.batchSchemaSendDate = batchSchemaSendDate == null ? null : batchSchemaSendDate.trim();
    }

    public String getMemoInfo() {
        return memoInfo;
    }

    public void setMemoInfo(String memoInfo) {
        this.memoInfo = memoInfo == null ? null : memoInfo.trim();
    }

    public String getCreditRefuseReason1() {
        return creditRefuseReason1;
    }

    public void setCreditRefuseReason1(String creditRefuseReason1) {
        this.creditRefuseReason1 = creditRefuseReason1 == null ? null : creditRefuseReason1.trim();
    }

    public String getCreditRefuseReason2() {
        return creditRefuseReason2;
    }

    public void setCreditRefuseReason2(String creditRefuseReason2) {
        this.creditRefuseReason2 = creditRefuseReason2 == null ? null : creditRefuseReason2.trim();
    }

    public String getCreditRefuseReason3() {
        return creditRefuseReason3;
    }

    public void setCreditRefuseReason3(String creditRefuseReason3) {
        this.creditRefuseReason3 = creditRefuseReason3 == null ? null : creditRefuseReason3.trim();
    }

    public String getManpJudgeConc() {
        return manpJudgeConc;
    }

    public void setManpJudgeConc(String manpJudgeConc) {
        this.manpJudgeConc = manpJudgeConc == null ? null : manpJudgeConc.trim();
    }

    public String getPayerIncome() {
        return payerIncome;
    }

    public void setPayerIncome(String payerIncome) {
        this.payerIncome = payerIncome == null ? null : payerIncome.trim();
    }

    public String getGuaranteeIncome() {
        return guaranteeIncome;
    }

    public void setGuaranteeIncome(String guaranteeIncome) {
        this.guaranteeIncome = guaranteeIncome == null ? null : guaranteeIncome.trim();
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income == null ? null : income.trim();
    }

    public String getShLicense() {
        return shLicense;
    }

    public void setShLicense(String shLicense) {
        this.shLicense = shLicense == null ? null : shLicense.trim();
    }

    public String getPurchaseInvoice() {
        return purchaseInvoice;
    }

    public void setPurchaseInvoice(String purchaseInvoice) {
        this.purchaseInvoice = purchaseInvoice == null ? null : purchaseInvoice.trim();
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank == null ? null : rank.trim();
    }

    public Short getMortgageLoans() {
        return mortgageLoans;
    }

    public void setMortgageLoans(Short mortgageLoans) {
        this.mortgageLoans = mortgageLoans;
    }

    public String getInsType() {
        return insType;
    }

    public void setInsType(String insType) {
        this.insType = insType == null ? null : insType.trim();
    }

    public String getDownFeeRate() {
        return downFeeRate;
    }

    public void setDownFeeRate(String downFeeRate) {
        this.downFeeRate = downFeeRate == null ? null : downFeeRate.trim();
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode == null ? null : jobCode.trim();
    }

    public String getServiceYears() {
        return serviceYears;
    }

    public void setServiceYears(String serviceYears) {
        this.serviceYears = serviceYears == null ? null : serviceYears.trim();
    }

    public String getdCompName() {
        return dCompName;
    }

    public void setdCompName(String dCompName) {
        this.dCompName = dCompName == null ? null : dCompName.trim();
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType == null ? null : acctType.trim();
    }

    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo == null ? null : operatorNo.trim();
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

    public Date getLstUpdTime() {
        return lstUpdTime;
    }

    public void setLstUpdTime(Date lstUpdTime) {
        this.lstUpdTime = lstUpdTime;
    }

    public Date getLstUpdDate() {
        return lstUpdDate;
    }

    public void setLstUpdDate(Date lstUpdDate) {
        this.lstUpdDate = lstUpdDate;
    }

    public String getLstUpdUser() {
        return lstUpdUser;
    }

    public void setLstUpdUser(String lstUpdUser) {
        this.lstUpdUser = lstUpdUser == null ? null : lstUpdUser.trim();
    }

    public Date getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(Date batchDate) {
        this.batchDate = batchDate;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus == null ? null : recStatus.trim();
    }

    public String getScrLevel() {
        return scrLevel;
    }

    public void setScrLevel(String scrLevel) {
        this.scrLevel = scrLevel == null ? null : scrLevel.trim();
    }

    public String getTopLevel() {
        return topLevel;
    }

    public void setTopLevel(String topLevel) {
        this.topLevel = topLevel == null ? null : topLevel.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getEduType() {
        return eduType;
    }

    public void setEduType(String eduType) {
        this.eduType = eduType == null ? null : eduType.trim();
    }

    public BigDecimal getSbCurrCompanyMonth() {
        return sbCurrCompanyMonth;
    }

    public void setSbCurrCompanyMonth(BigDecimal sbCurrCompanyMonth) {
        this.sbCurrCompanyMonth = sbCurrCompanyMonth;
    }

    public String getSbCurrPaycompany() {
        return sbCurrPaycompany;
    }

    public void setSbCurrPaycompany(String sbCurrPaycompany) {
        this.sbCurrPaycompany = sbCurrPaycompany == null ? null : sbCurrPaycompany.trim();
    }

    public BigDecimal getSbMonthPaybase() {
        return sbMonthPaybase;
    }

    public void setSbMonthPaybase(BigDecimal sbMonthPaybase) {
        this.sbMonthPaybase = sbMonthPaybase;
    }

    public Date getSbPayDate() {
        return sbPayDate;
    }

    public void setSbPayDate(Date sbPayDate) {
        this.sbPayDate = sbPayDate;
    }

    public BigDecimal getSbMonthPayment() {
        return sbMonthPayment;
    }

    public void setSbMonthPayment(BigDecimal sbMonthPayment) {
        this.sbMonthPayment = sbMonthPayment;
    }

    public String getSbCurrPayStatus() {
        return sbCurrPayStatus;
    }

    public void setSbCurrPayStatus(String sbCurrPayStatus) {
        this.sbCurrPayStatus = sbCurrPayStatus == null ? null : sbCurrPayStatus.trim();
    }

    public Long getRefreeLimit() {
        return refreeLimit;
    }

    public void setRefreeLimit(Long refreeLimit) {
        this.refreeLimit = refreeLimit;
    }

    public String getRefree() {
        return refree;
    }

    public void setRefree(String refree) {
        this.refree = refree == null ? null : refree.trim();
    }

    public String getHxBankStaffLevel() {
        return hxBankStaffLevel;
    }

    public void setHxBankStaffLevel(String hxBankStaffLevel) {
        this.hxBankStaffLevel = hxBankStaffLevel == null ? null : hxBankStaffLevel.trim();
    }

    public String getHxBankStaff() {
        return hxBankStaff;
    }

    public void setHxBankStaff(String hxBankStaff) {
        this.hxBankStaff = hxBankStaff == null ? null : hxBankStaff.trim();
    }
}