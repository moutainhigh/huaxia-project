package com.huaxia.opas.domain.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FicoYdjBlaze implements Serializable{
	private String numberRead;
	private String creditDecisionResult;
	private String creditDecisionDesc;
	private String custLevelTagA;
	private String rateName;
	private String custLevelTagB;
	private String repayRationValue;
	private String custLevelTagC;
	private String repayFreeValue;
	private String custLevelTagD;
	private String rateValueLoseDate;
	private String matchIncome;
	
    private String ydjId;

    private String appId;

    private Date crtTime;

    private String productType;

    private String isAmlFlg;

    private String areaType;

    private String contCustType;

    private String finalCustType;

    private Long cifAge;

    private String isGreyCif;

    private Long workAge;

    private BigDecimal cdtScore;

    private BigDecimal cdtCardDebt;

    private BigDecimal realyMIncome;

    private BigDecimal wageFlowMincome;

    private BigDecimal taxMincome;

    private BigDecimal jobMincome;

    private BigDecimal socialSetyMincome;

    private BigDecimal pubAclaFundsMincome;

    private BigDecimal spouseMincome;

    private BigDecimal rentMincome;

    private BigDecimal taxListMincome;

    private BigDecimal sysProMincome;

    private BigDecimal wageFlowRate;

    private BigDecimal wageFlowDuct;

    private BigDecimal jobRate;

    private BigDecimal jobDuct;

    private BigDecimal taxRate;

    private BigDecimal taxDuct;

    private BigDecimal pubAclaFundsRatio;

    private BigDecimal pubAclaFundsUp;

    private BigDecimal wfPafAmt;

    private BigDecimal wfIncomeAmt;

    private BigDecimal taxPafAmt;

    private BigDecimal taxIncomeAmt;

    private BigDecimal jobBfMincome;

    private BigDecimal jobAfMincome;

    private BigDecimal taxThreshold;

    private BigDecimal proposedLmt;

    private BigDecimal pbUnPlgeLoanDebt;

    private BigDecimal pbPlgeLoanDebt;

    private BigDecimal pbCdtCardExp;

    private BigDecimal pbUnPlgeLoanExp;

    private BigDecimal loanLmt;

    private BigDecimal cifIncomeMul;

    private BigDecimal cdtLoanSc;

    private BigDecimal dtiLmtUp;

    private BigDecimal cifDtiUp;

    private BigDecimal unPlgeLoanDebt;

    private BigDecimal unPlgeExp;

    private BigDecimal plgeLoanDebt;

    private BigDecimal dtiViolLmtUp;

    private BigDecimal cifDtiViolUp;

    private BigDecimal cpDtiLmtUp;

    private BigDecimal cpDtiViolLmtUp;

    private BigDecimal openLmtUp;

    private BigDecimal openViolLmtUp;

    private BigDecimal hFinanCifLmt;

    private BigDecimal mueViolLmtUp;

    private BigDecimal rankIncome;

    private BigDecimal cifLmtDown;

    private BigDecimal irbLoanSc;

    private Long foundingY;

    private String houseCtfn;

    private String carCtfn;

    private BigDecimal violLmtUp;

    private String trgerRuleCode;

    private BigDecimal unPlgeDti;

    private BigDecimal cpDti;

    private BigDecimal mue;

    private String dityNameType;

    private String apprRst;

    private String apprRiskRst;

    private String isHavePboc;

    private BigDecimal ttPayRate;

    private BigDecimal pay;

    private BigDecimal payDue;

    private String revolvingLabel;

    private String cifLabel;

    private String lowestPayAmtLabel;

    private String freePayTermLabel;

    private BigDecimal appScroe;

    private String isHavePbData;

    private Long loancard12mactivecount;

    private Long loan12mactivecount;

    private Long standardloancard12mactivecount;

    private BigDecimal mactivethan3cardamt12;

    private BigDecimal mactivethan3cardoverdraft12;

    private Long loancard12munrepaymentcount;

    private Long loancardmaxacctage;

    private Long creditcard24mmaxarcount;

    private BigDecimal otherloancardmaxsecamt;

    private Long loan24mmaxarcount;

    private String cardorloanacctstatusabl;

    private String creditcard12mdueabl;

    private String creditcard3mdueabl;

    private String cardorloan3mdueabl;

    private String loancarddueabl;

    private String loandueabl;

    private String loan12mdueabl;

    private String loan3mdueabl;

    private String newloancardacctcountabl;

    private String loancardamtuseratioabl;

    private String cardloanselectcountabl;

    private BigDecimal paymentmonthamt;

    private String isActiveCard;

    private BigDecimal activeCardSharAmt;

    private BigDecimal amtuseratio;

    private Long creditcardmaxduecount;

    private String loanmaxduestatus;

    private String cifmustprovematerial;

    private String cifmustprovematerialremark;

    private BigDecimal productamtdown;

    private BigDecimal refUnPlgeDti;

    private BigDecimal refCpDti;

    private BigDecimal refMue;

    private BigDecimal unPlgeDtiRange;

    private BigDecimal cpDtiRange;

    private Long unPlgeDtiRangeLv;

    private Long cpDtiRangeLv;

    private Long mueLv;

    private String apprMatrix;

    private String policyCd;

    private String rejectCd;

    private String violationCd;

    private BigDecimal houseTotalRatio;

    private BigDecimal wageIncomeRate;

    private BigDecimal wageIncomeDuct;

    private BigDecimal taxIncomeRate;

    private BigDecimal taxIncomeDuct;

    private BigDecimal wageIncomeAvgSum;

    private BigDecimal taxIncomeAvgSum;

    private String tacType;

    private String fqzRst;

    private String fqzRstDesp;

    private String isSpecialCust;

    private String eduProvRst;

    private String decisionResult;

    private String decisionDesp;

    private BigDecimal sampMIncome;

    private BigDecimal cifCpDtiUp;

    private BigDecimal cifCpDtiViolUp;

    private BigDecimal cifOpenLmtUp;

    private BigDecimal cifOpenLmtViolUp;

    private BigDecimal violRefLmt;

    private String genderCd;

    private String interestRateProduct;
    
    private String wfResult;
    
    private BigDecimal dlAmt;

	public String getMatchIncome() {
		return matchIncome;
	}

	public void setMatchIncome(String matchIncome) {
		this.matchIncome = matchIncome;
	}

	public String getYdjId() {
        return ydjId;
    }

    public void setYdjId(String ydjId) {
        this.ydjId = ydjId == null ? null : ydjId.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getIsAmlFlg() {
        return isAmlFlg;
    }

    public void setIsAmlFlg(String isAmlFlg) {
        this.isAmlFlg = isAmlFlg == null ? null : isAmlFlg.trim();
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType == null ? null : areaType.trim();
    }

    public String getContCustType() {
        return contCustType;
    }

    public void setContCustType(String contCustType) {
        this.contCustType = contCustType == null ? null : contCustType.trim();
    }

    public String getFinalCustType() {
        return finalCustType;
    }

    public void setFinalCustType(String finalCustType) {
        this.finalCustType = finalCustType == null ? null : finalCustType.trim();
    }

    public Long getCifAge() {
        return cifAge;
    }

    public void setCifAge(Long cifAge) {
        this.cifAge = cifAge;
    }

    public String getIsGreyCif() {
        return isGreyCif;
    }

    public void setIsGreyCif(String isGreyCif) {
        this.isGreyCif = isGreyCif == null ? null : isGreyCif.trim();
    }

    public Long getWorkAge() {
        return workAge;
    }

    public void setWorkAge(Long workAge) {
        this.workAge = workAge;
    }

    public BigDecimal getCdtScore() {
        return cdtScore;
    }

    public void setCdtScore(BigDecimal cdtScore) {
        this.cdtScore = cdtScore;
    }

    public BigDecimal getCdtCardDebt() {
        return cdtCardDebt;
    }

    public void setCdtCardDebt(BigDecimal cdtCardDebt) {
        this.cdtCardDebt = cdtCardDebt;
    }

    public BigDecimal getRealyMIncome() {
        return realyMIncome;
    }

    public void setRealyMIncome(BigDecimal realyMIncome) {
        this.realyMIncome = realyMIncome;
    }

    public BigDecimal getWageFlowMincome() {
        return wageFlowMincome;
    }

    public void setWageFlowMincome(BigDecimal wageFlowMincome) {
        this.wageFlowMincome = wageFlowMincome;
    }

    public BigDecimal getTaxMincome() {
        return taxMincome;
    }

    public void setTaxMincome(BigDecimal taxMincome) {
        this.taxMincome = taxMincome;
    }

    public BigDecimal getJobMincome() {
        return jobMincome;
    }

    public void setJobMincome(BigDecimal jobMincome) {
        this.jobMincome = jobMincome;
    }

    public BigDecimal getSocialSetyMincome() {
        return socialSetyMincome;
    }

    public void setSocialSetyMincome(BigDecimal socialSetyMincome) {
        this.socialSetyMincome = socialSetyMincome;
    }

    public BigDecimal getPubAclaFundsMincome() {
        return pubAclaFundsMincome;
    }

    public void setPubAclaFundsMincome(BigDecimal pubAclaFundsMincome) {
        this.pubAclaFundsMincome = pubAclaFundsMincome;
    }

    public BigDecimal getSpouseMincome() {
        return spouseMincome;
    }

    public void setSpouseMincome(BigDecimal spouseMincome) {
        this.spouseMincome = spouseMincome;
    }

    public BigDecimal getRentMincome() {
        return rentMincome;
    }

    public void setRentMincome(BigDecimal rentMincome) {
        this.rentMincome = rentMincome;
    }

    public BigDecimal getTaxListMincome() {
        return taxListMincome;
    }

    public void setTaxListMincome(BigDecimal taxListMincome) {
        this.taxListMincome = taxListMincome;
    }

    public BigDecimal getSysProMincome() {
        return sysProMincome;
    }

    public void setSysProMincome(BigDecimal sysProMincome) {
        this.sysProMincome = sysProMincome;
    }

    public BigDecimal getWageFlowRate() {
        return wageFlowRate;
    }

    public void setWageFlowRate(BigDecimal wageFlowRate) {
        this.wageFlowRate = wageFlowRate;
    }

    public BigDecimal getWageFlowDuct() {
        return wageFlowDuct;
    }

    public void setWageFlowDuct(BigDecimal wageFlowDuct) {
        this.wageFlowDuct = wageFlowDuct;
    }

    public BigDecimal getJobRate() {
        return jobRate;
    }

    public void setJobRate(BigDecimal jobRate) {
        this.jobRate = jobRate;
    }

    public BigDecimal getJobDuct() {
        return jobDuct;
    }

    public void setJobDuct(BigDecimal jobDuct) {
        this.jobDuct = jobDuct;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxDuct() {
        return taxDuct;
    }

    public void setTaxDuct(BigDecimal taxDuct) {
        this.taxDuct = taxDuct;
    }

    public BigDecimal getPubAclaFundsRatio() {
        return pubAclaFundsRatio;
    }

    public void setPubAclaFundsRatio(BigDecimal pubAclaFundsRatio) {
        this.pubAclaFundsRatio = pubAclaFundsRatio;
    }

    public BigDecimal getPubAclaFundsUp() {
        return pubAclaFundsUp;
    }

    public void setPubAclaFundsUp(BigDecimal pubAclaFundsUp) {
        this.pubAclaFundsUp = pubAclaFundsUp;
    }

    public BigDecimal getWfPafAmt() {
        return wfPafAmt;
    }

    public void setWfPafAmt(BigDecimal wfPafAmt) {
        this.wfPafAmt = wfPafAmt;
    }

    public BigDecimal getWfIncomeAmt() {
        return wfIncomeAmt;
    }

    public void setWfIncomeAmt(BigDecimal wfIncomeAmt) {
        this.wfIncomeAmt = wfIncomeAmt;
    }

    public BigDecimal getTaxPafAmt() {
        return taxPafAmt;
    }

    public void setTaxPafAmt(BigDecimal taxPafAmt) {
        this.taxPafAmt = taxPafAmt;
    }

    public BigDecimal getTaxIncomeAmt() {
        return taxIncomeAmt;
    }

    public void setTaxIncomeAmt(BigDecimal taxIncomeAmt) {
        this.taxIncomeAmt = taxIncomeAmt;
    }

    public BigDecimal getJobBfMincome() {
        return jobBfMincome;
    }

    public void setJobBfMincome(BigDecimal jobBfMincome) {
        this.jobBfMincome = jobBfMincome;
    }

    public BigDecimal getJobAfMincome() {
        return jobAfMincome;
    }

    public void setJobAfMincome(BigDecimal jobAfMincome) {
        this.jobAfMincome = jobAfMincome;
    }

    public BigDecimal getTaxThreshold() {
        return taxThreshold;
    }

    public void setTaxThreshold(BigDecimal taxThreshold) {
        this.taxThreshold = taxThreshold;
    }

    public BigDecimal getProposedLmt() {
        return proposedLmt;
    }

    public void setProposedLmt(BigDecimal proposedLmt) {
        this.proposedLmt = proposedLmt;
    }

    public BigDecimal getPbUnPlgeLoanDebt() {
        return pbUnPlgeLoanDebt;
    }

    public void setPbUnPlgeLoanDebt(BigDecimal pbUnPlgeLoanDebt) {
        this.pbUnPlgeLoanDebt = pbUnPlgeLoanDebt;
    }

    public BigDecimal getPbPlgeLoanDebt() {
        return pbPlgeLoanDebt;
    }

    public void setPbPlgeLoanDebt(BigDecimal pbPlgeLoanDebt) {
        this.pbPlgeLoanDebt = pbPlgeLoanDebt;
    }

    public BigDecimal getPbCdtCardExp() {
        return pbCdtCardExp;
    }

    public void setPbCdtCardExp(BigDecimal pbCdtCardExp) {
        this.pbCdtCardExp = pbCdtCardExp;
    }

    public BigDecimal getPbUnPlgeLoanExp() {
        return pbUnPlgeLoanExp;
    }

    public void setPbUnPlgeLoanExp(BigDecimal pbUnPlgeLoanExp) {
        this.pbUnPlgeLoanExp = pbUnPlgeLoanExp;
    }

    public BigDecimal getLoanLmt() {
        return loanLmt;
    }

    public void setLoanLmt(BigDecimal loanLmt) {
        this.loanLmt = loanLmt;
    }

    public BigDecimal getCifIncomeMul() {
        return cifIncomeMul;
    }

    public void setCifIncomeMul(BigDecimal cifIncomeMul) {
        this.cifIncomeMul = cifIncomeMul;
    }

    public BigDecimal getCdtLoanSc() {
        return cdtLoanSc;
    }

    public void setCdtLoanSc(BigDecimal cdtLoanSc) {
        this.cdtLoanSc = cdtLoanSc;
    }

    public BigDecimal getDtiLmtUp() {
        return dtiLmtUp;
    }

    public void setDtiLmtUp(BigDecimal dtiLmtUp) {
        this.dtiLmtUp = dtiLmtUp;
    }

    public BigDecimal getCifDtiUp() {
        return cifDtiUp;
    }

    public void setCifDtiUp(BigDecimal cifDtiUp) {
        this.cifDtiUp = cifDtiUp;
    }

    public BigDecimal getUnPlgeLoanDebt() {
        return unPlgeLoanDebt;
    }

    public void setUnPlgeLoanDebt(BigDecimal unPlgeLoanDebt) {
        this.unPlgeLoanDebt = unPlgeLoanDebt;
    }

    public BigDecimal getUnPlgeExp() {
        return unPlgeExp;
    }

    public void setUnPlgeExp(BigDecimal unPlgeExp) {
        this.unPlgeExp = unPlgeExp;
    }

    public BigDecimal getPlgeLoanDebt() {
        return plgeLoanDebt;
    }

    public void setPlgeLoanDebt(BigDecimal plgeLoanDebt) {
        this.plgeLoanDebt = plgeLoanDebt;
    }

    public BigDecimal getDtiViolLmtUp() {
        return dtiViolLmtUp;
    }

    public void setDtiViolLmtUp(BigDecimal dtiViolLmtUp) {
        this.dtiViolLmtUp = dtiViolLmtUp;
    }

    public BigDecimal getCifDtiViolUp() {
        return cifDtiViolUp;
    }

    public void setCifDtiViolUp(BigDecimal cifDtiViolUp) {
        this.cifDtiViolUp = cifDtiViolUp;
    }

    public BigDecimal getCpDtiLmtUp() {
        return cpDtiLmtUp;
    }

    public void setCpDtiLmtUp(BigDecimal cpDtiLmtUp) {
        this.cpDtiLmtUp = cpDtiLmtUp;
    }

    public BigDecimal getCpDtiViolLmtUp() {
        return cpDtiViolLmtUp;
    }

    public void setCpDtiViolLmtUp(BigDecimal cpDtiViolLmtUp) {
        this.cpDtiViolLmtUp = cpDtiViolLmtUp;
    }

    public BigDecimal getOpenLmtUp() {
        return openLmtUp;
    }

    public void setOpenLmtUp(BigDecimal openLmtUp) {
        this.openLmtUp = openLmtUp;
    }

    public BigDecimal getOpenViolLmtUp() {
        return openViolLmtUp;
    }

    public void setOpenViolLmtUp(BigDecimal openViolLmtUp) {
        this.openViolLmtUp = openViolLmtUp;
    }

    public BigDecimal gethFinanCifLmt() {
        return hFinanCifLmt;
    }

    public void sethFinanCifLmt(BigDecimal hFinanCifLmt) {
        this.hFinanCifLmt = hFinanCifLmt;
    }

    public BigDecimal getMueViolLmtUp() {
        return mueViolLmtUp;
    }

    public void setMueViolLmtUp(BigDecimal mueViolLmtUp) {
        this.mueViolLmtUp = mueViolLmtUp;
    }

    public BigDecimal getRankIncome() {
        return rankIncome;
    }

    public void setRankIncome(BigDecimal rankIncome) {
        this.rankIncome = rankIncome;
    }

    public BigDecimal getCifLmtDown() {
        return cifLmtDown;
    }

    public void setCifLmtDown(BigDecimal cifLmtDown) {
        this.cifLmtDown = cifLmtDown;
    }

    public BigDecimal getIrbLoanSc() {
        return irbLoanSc;
    }

    public void setIrbLoanSc(BigDecimal irbLoanSc) {
        this.irbLoanSc = irbLoanSc;
    }

    public Long getFoundingY() {
        return foundingY;
    }

    public void setFoundingY(Long foundingY) {
        this.foundingY = foundingY;
    }

    public String getHouseCtfn() {
        return houseCtfn;
    }

    public void setHouseCtfn(String houseCtfn) {
        this.houseCtfn = houseCtfn == null ? null : houseCtfn.trim();
    }

    public String getCarCtfn() {
        return carCtfn;
    }

    public void setCarCtfn(String carCtfn) {
        this.carCtfn = carCtfn == null ? null : carCtfn.trim();
    }

    public BigDecimal getViolLmtUp() {
        return violLmtUp;
    }

    public void setViolLmtUp(BigDecimal violLmtUp) {
        this.violLmtUp = violLmtUp;
    }

    public String getTrgerRuleCode() {
        return trgerRuleCode;
    }

    public void setTrgerRuleCode(String trgerRuleCode) {
        this.trgerRuleCode = trgerRuleCode == null ? null : trgerRuleCode.trim();
    }

    public BigDecimal getUnPlgeDti() {
        return unPlgeDti;
    }

    public void setUnPlgeDti(BigDecimal unPlgeDti) {
        this.unPlgeDti = unPlgeDti;
    }

    public BigDecimal getCpDti() {
        return cpDti;
    }

    public void setCpDti(BigDecimal cpDti) {
        this.cpDti = cpDti;
    }

    public BigDecimal getMue() {
        return mue;
    }

    public void setMue(BigDecimal mue) {
        this.mue = mue;
    }

    public String getDityNameType() {
        return dityNameType;
    }

    public void setDityNameType(String dityNameType) {
        this.dityNameType = dityNameType == null ? null : dityNameType.trim();
    }

    public String getApprRst() {
        return apprRst;
    }

    public void setApprRst(String apprRst) {
        this.apprRst = apprRst == null ? null : apprRst.trim();
    }

    public String getApprRiskRst() {
        return apprRiskRst;
    }

    public void setApprRiskRst(String apprRiskRst) {
        this.apprRiskRst = apprRiskRst == null ? null : apprRiskRst.trim();
    }

    public String getIsHavePboc() {
        return isHavePboc;
    }

    public void setIsHavePboc(String isHavePboc) {
        this.isHavePboc = isHavePboc == null ? null : isHavePboc.trim();
    }

    public BigDecimal getTtPayRate() {
        return ttPayRate;
    }

    public void setTtPayRate(BigDecimal ttPayRate) {
        this.ttPayRate = ttPayRate;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public BigDecimal getPayDue() {
        return payDue;
    }

    public void setPayDue(BigDecimal payDue) {
        this.payDue = payDue;
    }

    public String getRevolvingLabel() {
        return revolvingLabel;
    }

    public void setRevolvingLabel(String revolvingLabel) {
        this.revolvingLabel = revolvingLabel == null ? null : revolvingLabel.trim();
    }

    public String getCifLabel() {
        return cifLabel;
    }

    public void setCifLabel(String cifLabel) {
        this.cifLabel = cifLabel == null ? null : cifLabel.trim();
    }

    public String getLowestPayAmtLabel() {
        return lowestPayAmtLabel;
    }

    public void setLowestPayAmtLabel(String lowestPayAmtLabel) {
        this.lowestPayAmtLabel = lowestPayAmtLabel == null ? null : lowestPayAmtLabel.trim();
    }

    public String getFreePayTermLabel() {
        return freePayTermLabel;
    }

    public void setFreePayTermLabel(String freePayTermLabel) {
        this.freePayTermLabel = freePayTermLabel == null ? null : freePayTermLabel.trim();
    }

    public BigDecimal getAppScroe() {
        return appScroe;
    }

    public void setAppScroe(BigDecimal appScroe) {
        this.appScroe = appScroe;
    }

    public String getIsHavePbData() {
        return isHavePbData;
    }

    public void setIsHavePbData(String isHavePbData) {
        this.isHavePbData = isHavePbData == null ? null : isHavePbData.trim();
    }

    public Long getLoancard12mactivecount() {
        return loancard12mactivecount;
    }

    public void setLoancard12mactivecount(Long loancard12mactivecount) {
        this.loancard12mactivecount = loancard12mactivecount;
    }

    public Long getLoan12mactivecount() {
        return loan12mactivecount;
    }

    public void setLoan12mactivecount(Long loan12mactivecount) {
        this.loan12mactivecount = loan12mactivecount;
    }

    public Long getStandardloancard12mactivecount() {
        return standardloancard12mactivecount;
    }

    public void setStandardloancard12mactivecount(Long standardloancard12mactivecount) {
        this.standardloancard12mactivecount = standardloancard12mactivecount;
    }

    public BigDecimal getMactivethan3cardamt12() {
        return mactivethan3cardamt12;
    }

    public void setMactivethan3cardamt12(BigDecimal mactivethan3cardamt12) {
        this.mactivethan3cardamt12 = mactivethan3cardamt12;
    }

    public BigDecimal getMactivethan3cardoverdraft12() {
        return mactivethan3cardoverdraft12;
    }

    public void setMactivethan3cardoverdraft12(BigDecimal mactivethan3cardoverdraft12) {
        this.mactivethan3cardoverdraft12 = mactivethan3cardoverdraft12;
    }

    public Long getLoancard12munrepaymentcount() {
        return loancard12munrepaymentcount;
    }

    public void setLoancard12munrepaymentcount(Long loancard12munrepaymentcount) {
        this.loancard12munrepaymentcount = loancard12munrepaymentcount;
    }

    public Long getLoancardmaxacctage() {
        return loancardmaxacctage;
    }

    public void setLoancardmaxacctage(Long loancardmaxacctage) {
        this.loancardmaxacctage = loancardmaxacctage;
    }

    public Long getCreditcard24mmaxarcount() {
        return creditcard24mmaxarcount;
    }

    public void setCreditcard24mmaxarcount(Long creditcard24mmaxarcount) {
        this.creditcard24mmaxarcount = creditcard24mmaxarcount;
    }

    public BigDecimal getOtherloancardmaxsecamt() {
        return otherloancardmaxsecamt;
    }

    public void setOtherloancardmaxsecamt(BigDecimal otherloancardmaxsecamt) {
        this.otherloancardmaxsecamt = otherloancardmaxsecamt;
    }

    public Long getLoan24mmaxarcount() {
        return loan24mmaxarcount;
    }

    public void setLoan24mmaxarcount(Long loan24mmaxarcount) {
        this.loan24mmaxarcount = loan24mmaxarcount;
    }

    public String getCardorloanacctstatusabl() {
        return cardorloanacctstatusabl;
    }

    public void setCardorloanacctstatusabl(String cardorloanacctstatusabl) {
        this.cardorloanacctstatusabl = cardorloanacctstatusabl == null ? null : cardorloanacctstatusabl.trim();
    }

    public String getCreditcard12mdueabl() {
        return creditcard12mdueabl;
    }

    public void setCreditcard12mdueabl(String creditcard12mdueabl) {
        this.creditcard12mdueabl = creditcard12mdueabl == null ? null : creditcard12mdueabl.trim();
    }

    public String getCreditcard3mdueabl() {
        return creditcard3mdueabl;
    }

    public void setCreditcard3mdueabl(String creditcard3mdueabl) {
        this.creditcard3mdueabl = creditcard3mdueabl == null ? null : creditcard3mdueabl.trim();
    }

    public String getCardorloan3mdueabl() {
        return cardorloan3mdueabl;
    }

    public void setCardorloan3mdueabl(String cardorloan3mdueabl) {
        this.cardorloan3mdueabl = cardorloan3mdueabl == null ? null : cardorloan3mdueabl.trim();
    }

    public String getLoancarddueabl() {
        return loancarddueabl;
    }

    public void setLoancarddueabl(String loancarddueabl) {
        this.loancarddueabl = loancarddueabl == null ? null : loancarddueabl.trim();
    }

    public String getLoandueabl() {
        return loandueabl;
    }

    public void setLoandueabl(String loandueabl) {
        this.loandueabl = loandueabl == null ? null : loandueabl.trim();
    }

    public String getLoan12mdueabl() {
        return loan12mdueabl;
    }

    public void setLoan12mdueabl(String loan12mdueabl) {
        this.loan12mdueabl = loan12mdueabl == null ? null : loan12mdueabl.trim();
    }

    public String getLoan3mdueabl() {
        return loan3mdueabl;
    }

    public void setLoan3mdueabl(String loan3mdueabl) {
        this.loan3mdueabl = loan3mdueabl == null ? null : loan3mdueabl.trim();
    }

    public String getNewloancardacctcountabl() {
        return newloancardacctcountabl;
    }

    public void setNewloancardacctcountabl(String newloancardacctcountabl) {
        this.newloancardacctcountabl = newloancardacctcountabl == null ? null : newloancardacctcountabl.trim();
    }

    public String getLoancardamtuseratioabl() {
        return loancardamtuseratioabl;
    }

    public void setLoancardamtuseratioabl(String loancardamtuseratioabl) {
        this.loancardamtuseratioabl = loancardamtuseratioabl == null ? null : loancardamtuseratioabl.trim();
    }

    public String getCardloanselectcountabl() {
        return cardloanselectcountabl;
    }

    public void setCardloanselectcountabl(String cardloanselectcountabl) {
        this.cardloanselectcountabl = cardloanselectcountabl == null ? null : cardloanselectcountabl.trim();
    }

    public BigDecimal getPaymentmonthamt() {
        return paymentmonthamt;
    }

    public void setPaymentmonthamt(BigDecimal paymentmonthamt) {
        this.paymentmonthamt = paymentmonthamt;
    }

    public String getIsActiveCard() {
        return isActiveCard;
    }

    public void setIsActiveCard(String isActiveCard) {
        this.isActiveCard = isActiveCard == null ? null : isActiveCard.trim();
    }

    public BigDecimal getActiveCardSharAmt() {
        return activeCardSharAmt;
    }

    public void setActiveCardSharAmt(BigDecimal activeCardSharAmt) {
        this.activeCardSharAmt = activeCardSharAmt;
    }

    public BigDecimal getAmtuseratio() {
        return amtuseratio;
    }

    public void setAmtuseratio(BigDecimal amtuseratio) {
        this.amtuseratio = amtuseratio;
    }

    public Long getCreditcardmaxduecount() {
        return creditcardmaxduecount;
    }

    public void setCreditcardmaxduecount(Long creditcardmaxduecount) {
        this.creditcardmaxduecount = creditcardmaxduecount;
    }

    public String getLoanmaxduestatus() {
        return loanmaxduestatus;
    }

    public void setLoanmaxduestatus(String loanmaxduestatus) {
        this.loanmaxduestatus = loanmaxduestatus == null ? null : loanmaxduestatus.trim();
    }

    public String getCifmustprovematerial() {
        return cifmustprovematerial;
    }

    public void setCifmustprovematerial(String cifmustprovematerial) {
        this.cifmustprovematerial = cifmustprovematerial == null ? null : cifmustprovematerial.trim();
    }

    public String getCifmustprovematerialremark() {
        return cifmustprovematerialremark;
    }

    public void setCifmustprovematerialremark(String cifmustprovematerialremark) {
        this.cifmustprovematerialremark = cifmustprovematerialremark == null ? null : cifmustprovematerialremark.trim();
    }

    public BigDecimal getProductamtdown() {
        return productamtdown;
    }

    public void setProductamtdown(BigDecimal productamtdown) {
        this.productamtdown = productamtdown;
    }

    public BigDecimal getRefUnPlgeDti() {
        return refUnPlgeDti;
    }

    public void setRefUnPlgeDti(BigDecimal refUnPlgeDti) {
        this.refUnPlgeDti = refUnPlgeDti;
    }

    public BigDecimal getRefCpDti() {
        return refCpDti;
    }

    public void setRefCpDti(BigDecimal refCpDti) {
        this.refCpDti = refCpDti;
    }

    public BigDecimal getRefMue() {
        return refMue;
    }

    public void setRefMue(BigDecimal refMue) {
        this.refMue = refMue;
    }

    public BigDecimal getUnPlgeDtiRange() {
        return unPlgeDtiRange;
    }

    public void setUnPlgeDtiRange(BigDecimal unPlgeDtiRange) {
        this.unPlgeDtiRange = unPlgeDtiRange;
    }

    public BigDecimal getCpDtiRange() {
        return cpDtiRange;
    }

    public void setCpDtiRange(BigDecimal cpDtiRange) {
        this.cpDtiRange = cpDtiRange;
    }

    public Long getUnPlgeDtiRangeLv() {
        return unPlgeDtiRangeLv;
    }

    public void setUnPlgeDtiRangeLv(Long unPlgeDtiRangeLv) {
        this.unPlgeDtiRangeLv = unPlgeDtiRangeLv;
    }

    public Long getCpDtiRangeLv() {
        return cpDtiRangeLv;
    }

    public void setCpDtiRangeLv(Long cpDtiRangeLv) {
        this.cpDtiRangeLv = cpDtiRangeLv;
    }

    public Long getMueLv() {
        return mueLv;
    }

    public void setMueLv(Long mueLv) {
        this.mueLv = mueLv;
    }

    public String getApprMatrix() {
        return apprMatrix;
    }

    public void setApprMatrix(String apprMatrix) {
        this.apprMatrix = apprMatrix == null ? null : apprMatrix.trim();
    }

    public String getPolicyCd() {
        return policyCd;
    }

    public void setPolicyCd(String policyCd) {
        this.policyCd = policyCd == null ? null : policyCd.trim();
    }

    public String getRejectCd() {
        return rejectCd;
    }

    public void setRejectCd(String rejectCd) {
        this.rejectCd = rejectCd == null ? null : rejectCd.trim();
    }

    public String getViolationCd() {
        return violationCd;
    }

    public void setViolationCd(String violationCd) {
        this.violationCd = violationCd == null ? null : violationCd.trim();
    }

    public BigDecimal getHouseTotalRatio() {
        return houseTotalRatio;
    }

    public void setHouseTotalRatio(BigDecimal houseTotalRatio) {
        this.houseTotalRatio = houseTotalRatio;
    }

    public BigDecimal getWageIncomeRate() {
        return wageIncomeRate;
    }

    public void setWageIncomeRate(BigDecimal wageIncomeRate) {
        this.wageIncomeRate = wageIncomeRate;
    }

    public BigDecimal getWageIncomeDuct() {
        return wageIncomeDuct;
    }

    public void setWageIncomeDuct(BigDecimal wageIncomeDuct) {
        this.wageIncomeDuct = wageIncomeDuct;
    }

    public BigDecimal getTaxIncomeRate() {
        return taxIncomeRate;
    }

    public void setTaxIncomeRate(BigDecimal taxIncomeRate) {
        this.taxIncomeRate = taxIncomeRate;
    }

    public BigDecimal getTaxIncomeDuct() {
        return taxIncomeDuct;
    }

    public void setTaxIncomeDuct(BigDecimal taxIncomeDuct) {
        this.taxIncomeDuct = taxIncomeDuct;
    }

    public BigDecimal getWageIncomeAvgSum() {
        return wageIncomeAvgSum;
    }

    public void setWageIncomeAvgSum(BigDecimal wageIncomeAvgSum) {
        this.wageIncomeAvgSum = wageIncomeAvgSum;
    }

    public BigDecimal getTaxIncomeAvgSum() {
        return taxIncomeAvgSum;
    }

    public void setTaxIncomeAvgSum(BigDecimal taxIncomeAvgSum) {
        this.taxIncomeAvgSum = taxIncomeAvgSum;
    }

    public String getTacType() {
        return tacType;
    }

    public void setTacType(String tacType) {
        this.tacType = tacType == null ? null : tacType.trim();
    }

    public String getFqzRst() {
        return fqzRst;
    }

    public void setFqzRst(String fqzRst) {
        this.fqzRst = fqzRst == null ? null : fqzRst.trim();
    }

    public String getFqzRstDesp() {
        return fqzRstDesp;
    }

    public void setFqzRstDesp(String fqzRstDesp) {
        this.fqzRstDesp = fqzRstDesp == null ? null : fqzRstDesp.trim();
    }

    public String getIsSpecialCust() {
        return isSpecialCust;
    }

    public void setIsSpecialCust(String isSpecialCust) {
        this.isSpecialCust = isSpecialCust == null ? null : isSpecialCust.trim();
    }

    public String getEduProvRst() {
        return eduProvRst;
    }

    public void setEduProvRst(String eduProvRst) {
        this.eduProvRst = eduProvRst == null ? null : eduProvRst.trim();
    }

    public String getDecisionResult() {
        return decisionResult;
    }

    public void setDecisionResult(String decisionResult) {
        this.decisionResult = decisionResult == null ? null : decisionResult.trim();
    }

    public String getDecisionDesp() {
        return decisionDesp;
    }

    public void setDecisionDesp(String decisionDesp) {
        this.decisionDesp = decisionDesp == null ? null : decisionDesp.trim();
    }

    public BigDecimal getSampMIncome() {
        return sampMIncome;
    }

    public void setSampMIncome(BigDecimal sampMIncome) {
        this.sampMIncome = sampMIncome;
    }

    public BigDecimal getCifCpDtiUp() {
        return cifCpDtiUp;
    }

    public void setCifCpDtiUp(BigDecimal cifCpDtiUp) {
        this.cifCpDtiUp = cifCpDtiUp;
    }

    public BigDecimal getCifCpDtiViolUp() {
        return cifCpDtiViolUp;
    }

    public void setCifCpDtiViolUp(BigDecimal cifCpDtiViolUp) {
        this.cifCpDtiViolUp = cifCpDtiViolUp;
    }

    public BigDecimal getCifOpenLmtUp() {
        return cifOpenLmtUp;
    }

    public void setCifOpenLmtUp(BigDecimal cifOpenLmtUp) {
        this.cifOpenLmtUp = cifOpenLmtUp;
    }

    public BigDecimal getCifOpenLmtViolUp() {
        return cifOpenLmtViolUp;
    }

    public void setCifOpenLmtViolUp(BigDecimal cifOpenLmtViolUp) {
        this.cifOpenLmtViolUp = cifOpenLmtViolUp;
    }

    public BigDecimal getViolRefLmt() {
        return violRefLmt;
    }

    public void setViolRefLmt(BigDecimal violRefLmt) {
        this.violRefLmt = violRefLmt;
    }

    public String getGenderCd() {
        return genderCd;
    }

    public void setGenderCd(String genderCd) {
        this.genderCd = genderCd == null ? null : genderCd.trim();
    }

    public String getInterestRateProduct() {
        return interestRateProduct;
    }

    public void setInterestRateProduct(String interestRateProduct) {
        this.interestRateProduct = interestRateProduct == null ? null : interestRateProduct.trim();
    }

	public String getNumberRead() {
		return numberRead;
	}

	public void setNumberRead(String numberRead) {
		this.numberRead = numberRead;
	}

	public String getCreditDecisionResult() {
		return creditDecisionResult;
	}

	public void setCreditDecisionResult(String creditDecisionResult) {
		this.creditDecisionResult = creditDecisionResult;
	}

	public String getCreditDecisionDesc() {
		return creditDecisionDesc;
	}

	public void setCreditDecisionDesc(String creditDecisionDesc) {
		this.creditDecisionDesc = creditDecisionDesc;
	}

	public String getCustLevelTagA() {
		return custLevelTagA;
	}

	public void setCustLevelTagA(String custLevelTagA) {
		this.custLevelTagA = custLevelTagA;
	}

	public String getRateName() {
		return rateName;
	}

	public void setRateName(String rateName) {
		this.rateName = rateName;
	}

	public String getCustLevelTagB() {
		return custLevelTagB;
	}

	public void setCustLevelTagB(String custLevelTagB) {
		this.custLevelTagB = custLevelTagB;
	}

	public String getRepayRationValue() {
		return repayRationValue;
	}

	public void setRepayRationValue(String repayRationValue) {
		this.repayRationValue = repayRationValue;
	}

	public String getCustLevelTagC() {
		return custLevelTagC;
	}

	public void setCustLevelTagC(String custLevelTagC) {
		this.custLevelTagC = custLevelTagC;
	}

	public String getRepayFreeValue() {
		return repayFreeValue;
	}

	public void setRepayFreeValue(String repayFreeValue) {
		this.repayFreeValue = repayFreeValue;
	}

	public String getWfResult() {
		return wfResult;
	}

	public void setWfResult(String wfResult) {
		this.wfResult = wfResult;
	}
	
	public BigDecimal getDlAmt() {
			return dlAmt;
	}

	public void setDlAmt(BigDecimal dlAmt) {
		this.dlAmt = dlAmt;
	}

	public String getCustLevelTagD() {
		return custLevelTagD;
	}

	public void setCustLevelTagD(String custLevelTagD) {
		this.custLevelTagD = custLevelTagD;
	}

	public String getRateValueLoseDate() {
		return rateValueLoseDate;
	}

	public void setRateValueLoseDate(String rateValueLoseDate) {
		this.rateValueLoseDate = rateValueLoseDate;
	}
	
}