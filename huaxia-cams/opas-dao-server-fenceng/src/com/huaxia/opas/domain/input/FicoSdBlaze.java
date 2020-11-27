package com.huaxia.opas.domain.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FicoSdBlaze implements Serializable{
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private String creditScore;
	private String creditDecisionResult;
	private String creditDecisionDesc;
	private String c1Credpct;
	private String custLevelTagA;
	private String rateName;
	private String custLevelTagD;
	private String rateValueLoseDate;
	private String custLevelTagB;
	private String repayRationValue;
	private String custLevelTagC;
	private String repayFreeValue;
    private String sdId;
    private double triggerType;
    private String appId;
    private String prodName;
    private String policyCode1;
    private String policyCode3;
    public String getPolicyCode1() {
		return policyCode1;
	}

	public void setPolicyCode1(String policyCode1) {
		this.policyCode1 = policyCode1;
	}

	public String getPolicyCode3() {
		return policyCode3;
	}

	public void setPolicyCode3(String policyCode3) {
		this.policyCode3 = policyCode3;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public double getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(double triggerType) {
		this.triggerType = triggerType;
	}

	private Date crtTime;

    private BigDecimal proposedLmt;

    public String getIrbScore() {
		return irbScore;
	}

	public void setIrbScore(String irbScore) {
		this.irbScore = irbScore;
	}

	private BigDecimal appScore;

    private String irbScore;
    
    private String appScoreLvl;

    private BigDecimal revolvingLmt;

    private String appChannel;

    private String opAcctDt;

    private String supplementRst;

    private String supplementRstDscp;

    private String fqzRst;

    private String fqzRstDesp;
    
    private Date fqzRstTime;

    private String cpRst;

    private String cpRstDesp;

    private String pbocRst;

    private String pbocRstFin;

    private String appScoreRst;

    private String appScoreRstFin;

    private String cifAge;

    private String isHavePboc;

    private String isHaveLoancard;

    private BigDecimal ttPayRate;

    private BigDecimal pay;

    private BigDecimal payDue;

    private String revolvingLabel;

    private String cifLabel;

    private String lowestPayAmtLabel;

    private String freePayTermLabel;

    private BigDecimal accfundIncome;

    private BigDecimal comfirmAmt;

    private BigDecimal adbAmt;

    private BigDecimal csLoanAmt;

    private String cityTyp;

    private Integer avgMob;

    private Integer oneCardMob;

    private BigDecimal riskLvl;

    private String appLable;

    private Integer cardActivTimes;

    private BigDecimal maxRcCriditLmt;

    private BigDecimal maxRcChareLmt;

    private BigDecimal maxRcCardMob;

    private BigDecimal maxCsLoanMob;

    private String haveCcUseRecord;

    private String haveClUseRecord;

    private BigDecimal incomeAmt;

    private BigDecimal otherBankAmt;

    private BigDecimal payAmt;

    private BigDecimal eduAmt;

    private BigDecimal houseAmt;

    private BigDecimal carAmt;

    private BigDecimal otherBankMaxLmt;

    private BigDecimal otherBankSecondLmt;

    private String isMasterCustApp;

    private String isNewCustApp;

    private String custType;

    private String isSpecialCust;

    private String gjmCd;

    private String jujCd;

    private BigDecimal dti;

    private BigDecimal dlLmt;

    private String cardorloanacctstatusabl;

    private String creditcard12mdueabl;

    private String creditcard3mdueabl;

    private String cardorloan3mdueabl;

    private String loancarddueabl;

    private String loan12mdueabl;

    private String loan3mdueabl;

    private String newloancardacctcountabl;

    private String loancardamtuseratioabl;

    private String cardloanselectcountabl;

    private String creditcardcountabl;

    private String rzLable;
    
    private String rzLableCode;

    private String isHxEmp;

    public String getRzLableCode() {
		return rzLableCode;
	}

	public void setRzLableCode(String rzLableCode) {
		this.rzLableCode = rzLableCode;
	}

	private String isHxXykEmp;

    private String tempDecisionresult;

    private String mainRollingLabel;

    private String mainRollingResult;

    private String decisionResult;

    private String decisionDesp;

    private String interestRateProduct;

    private String custTypeDesp;

    private String rcCcCl12mNotAction;

    private String rzResult;

    private String rzDesp;

    private String loandueabl;

    private String appScoreRstDesp;

    private String appScoreRstFinDesp;

    private String pbocRstDesp;

    private String pbocRstFinDesp;

    private String wfResult;

    private BigDecimal ysxAmt;

    private BigDecimal jingdongAmt;

    private String cutOffDirection;

    private BigDecimal historyProposedLmt;

    private String indCdRst;
    
    private String vigilantCust;
    
    private String specialCust;

    private String c4Abtype;//wdb 新增亲见签名
    public String getSdId() {
        return sdId;
    }

    public void setSdId(String sdId) {
        this.sdId = sdId == null ? null : sdId.trim();
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

	public BigDecimal getProposedLmt() {
        return proposedLmt;
    }

    public void setProposedLmt(BigDecimal proposedLmt) {
        this.proposedLmt = proposedLmt;
    }

    public BigDecimal getAppScore() {
        return appScore;
    }

    public void setAppScore(BigDecimal appScore) {
        this.appScore = appScore;
    }

    public String getAppScoreLvl() {
        return appScoreLvl;
    }

    public void setAppScoreLvl(String appScoreLvl) {
        this.appScoreLvl = appScoreLvl == null ? null : appScoreLvl.trim();
    }

    public BigDecimal getRevolvingLmt() {
        return revolvingLmt;
    }

    public void setRevolvingLmt(BigDecimal revolvingLmt) {
        this.revolvingLmt = revolvingLmt;
    }

    public String getAppChannel() {
        return appChannel;
    }

    public void setAppChannel(String appChannel) {
        this.appChannel = appChannel == null ? null : appChannel.trim();
    }

    public String getOpAcctDt() {
        return opAcctDt;
    }

    public void setOpAcctDt(String opAcctDt) {
        this.opAcctDt = opAcctDt == null ? null : opAcctDt.trim();
    }

    public String getSupplementRst() {
        return supplementRst;
    }

    public void setSupplementRst(String supplementRst) {
        this.supplementRst = supplementRst == null ? null : supplementRst.trim();
    }

    public String getSupplementRstDscp() {
        return supplementRstDscp;
    }

    public void setSupplementRstDscp(String supplementRstDscp) {
        this.supplementRstDscp = supplementRstDscp == null ? null : supplementRstDscp.trim();
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

    public String getCpRst() {
        return cpRst;
    }

    public void setCpRst(String cpRst) {
        this.cpRst = cpRst == null ? null : cpRst.trim();
    }

    public String getCpRstDesp() {
        return cpRstDesp;
    }

    public void setCpRstDesp(String cpRstDesp) {
        this.cpRstDesp = cpRstDesp == null ? null : cpRstDesp.trim();
    }

    public String getPbocRst() {
        return pbocRst;
    }

    public void setPbocRst(String pbocRst) {
        this.pbocRst = pbocRst == null ? null : pbocRst.trim();
    }

    public String getPbocRstFin() {
        return pbocRstFin;
    }

    public void setPbocRstFin(String pbocRstFin) {
        this.pbocRstFin = pbocRstFin == null ? null : pbocRstFin.trim();
    }

    public String getAppScoreRst() {
        return appScoreRst;
    }

    public void setAppScoreRst(String appScoreRst) {
        this.appScoreRst = appScoreRst == null ? null : appScoreRst.trim();
    }

    public String getAppScoreRstFin() {
        return appScoreRstFin;
    }

    public void setAppScoreRstFin(String appScoreRstFin) {
        this.appScoreRstFin = appScoreRstFin == null ? null : appScoreRstFin.trim();
    }

    public String getCifAge() {
        return cifAge;
    }

    public void setCifAge(String cifAge) {
        this.cifAge = cifAge == null ? null : cifAge.trim();
    }

    public String getIsHavePboc() {
        return isHavePboc;
    }

    public void setIsHavePboc(String isHavePboc) {
        this.isHavePboc = isHavePboc == null ? null : isHavePboc.trim();
    }

    public String getIsHaveLoancard() {
        return isHaveLoancard;
    }

    public void setIsHaveLoancard(String isHaveLoancard) {
        this.isHaveLoancard = isHaveLoancard == null ? null : isHaveLoancard.trim();
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

    public BigDecimal getAccfundIncome() {
        return accfundIncome;
    }

    public void setAccfundIncome(BigDecimal accfundIncome) {
        this.accfundIncome = accfundIncome;
    }

    public BigDecimal getComfirmAmt() {
        return comfirmAmt;
    }

    public void setComfirmAmt(BigDecimal comfirmAmt) {
        this.comfirmAmt = comfirmAmt;
    }

    public BigDecimal getAdbAmt() {
        return adbAmt;
    }

    public void setAdbAmt(BigDecimal adbAmt) {
        this.adbAmt = adbAmt;
    }

    public BigDecimal getCsLoanAmt() {
        return csLoanAmt;
    }

    public void setCsLoanAmt(BigDecimal csLoanAmt) {
        this.csLoanAmt = csLoanAmt;
    }

    public String getCityTyp() {
        return cityTyp;
    }

    public void setCityTyp(String cityTyp) {
        this.cityTyp = cityTyp == null ? null : cityTyp.trim();
    }

    public Integer getAvgMob() {
        return avgMob;
    }

    public void setAvgMob(Integer avgMob) {
        this.avgMob = avgMob;
    }

    public Integer getOneCardMob() {
        return oneCardMob;
    }

    public void setOneCardMob(Integer oneCardMob) {
        this.oneCardMob = oneCardMob;
    }

    public BigDecimal getRiskLvl() {
        return riskLvl;
    }

    public void setRiskLvl(BigDecimal riskLvl) {
        this.riskLvl = riskLvl;
    }

    public String getAppLable() {
        return appLable;
    }

    public void setAppLable(String appLable) {
        this.appLable = appLable == null ? null : appLable.trim();
    }

    public Integer getCardActivTimes() {
        return cardActivTimes;
    }

    public void setCardActivTimes(Integer cardActivTimes) {
        this.cardActivTimes = cardActivTimes;
    }

    public BigDecimal getMaxRcCriditLmt() {
        return maxRcCriditLmt;
    }

    public void setMaxRcCriditLmt(BigDecimal maxRcCriditLmt) {
        this.maxRcCriditLmt = maxRcCriditLmt;
    }

    public BigDecimal getMaxRcChareLmt() {
        return maxRcChareLmt;
    }

    public void setMaxRcChareLmt(BigDecimal maxRcChareLmt) {
        this.maxRcChareLmt = maxRcChareLmt;
    }

    public BigDecimal getMaxRcCardMob() {
        return maxRcCardMob;
    }

    public void setMaxRcCardMob(BigDecimal maxRcCardMob) {
        this.maxRcCardMob = maxRcCardMob;
    }

    public BigDecimal getMaxCsLoanMob() {
        return maxCsLoanMob;
    }

    public void setMaxCsLoanMob(BigDecimal maxCsLoanMob) {
        this.maxCsLoanMob = maxCsLoanMob;
    }

    public String getHaveCcUseRecord() {
        return haveCcUseRecord;
    }

    public void setHaveCcUseRecord(String haveCcUseRecord) {
        this.haveCcUseRecord = haveCcUseRecord == null ? null : haveCcUseRecord.trim();
    }

    public String getHaveClUseRecord() {
        return haveClUseRecord;
    }

    public void setHaveClUseRecord(String haveClUseRecord) {
        this.haveClUseRecord = haveClUseRecord == null ? null : haveClUseRecord.trim();
    }

    public BigDecimal getIncomeAmt() {
        return incomeAmt;
    }

    public void setIncomeAmt(BigDecimal incomeAmt) {
        this.incomeAmt = incomeAmt;
    }

    public BigDecimal getOtherBankAmt() {
        return otherBankAmt;
    }

    public void setOtherBankAmt(BigDecimal otherBankAmt) {
        this.otherBankAmt = otherBankAmt;
    }

    public BigDecimal getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(BigDecimal payAmt) {
        this.payAmt = payAmt;
    }

    public BigDecimal getEduAmt() {
        return eduAmt;
    }

    public void setEduAmt(BigDecimal eduAmt) {
        this.eduAmt = eduAmt;
    }

    public BigDecimal getHouseAmt() {
        return houseAmt;
    }

    public void setHouseAmt(BigDecimal houseAmt) {
        this.houseAmt = houseAmt;
    }

    public BigDecimal getCarAmt() {
        return carAmt;
    }

    public void setCarAmt(BigDecimal carAmt) {
        this.carAmt = carAmt;
    }

    public BigDecimal getOtherBankMaxLmt() {
        return otherBankMaxLmt;
    }

    public void setOtherBankMaxLmt(BigDecimal otherBankMaxLmt) {
        this.otherBankMaxLmt = otherBankMaxLmt;
    }

    public BigDecimal getOtherBankSecondLmt() {
        return otherBankSecondLmt;
    }

    public void setOtherBankSecondLmt(BigDecimal otherBankSecondLmt) {
        this.otherBankSecondLmt = otherBankSecondLmt;
    }

    public String getIsMasterCustApp() {
        return isMasterCustApp;
    }

    public void setIsMasterCustApp(String isMasterCustApp) {
        this.isMasterCustApp = isMasterCustApp == null ? null : isMasterCustApp.trim();
    }

    public String getIsNewCustApp() {
        return isNewCustApp;
    }

    public void setIsNewCustApp(String isNewCustApp) {
        this.isNewCustApp = isNewCustApp == null ? null : isNewCustApp.trim();
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType == null ? null : custType.trim();
    }

    public String getIsSpecialCust() {
        return isSpecialCust;
    }

    public void setIsSpecialCust(String isSpecialCust) {
        this.isSpecialCust = isSpecialCust == null ? null : isSpecialCust.trim();
    }

    public String getGjmCd() {
        return gjmCd;
    }

    public void setGjmCd(String gjmCd) {
        this.gjmCd = gjmCd == null ? null : gjmCd.trim();
    }

    public String getJujCd() {
        return jujCd;
    }

    public void setJujCd(String jujCd) {
        this.jujCd = jujCd == null ? null : jujCd.trim();
    }

    public BigDecimal getDti() {
        return dti;
    }

    public void setDti(BigDecimal dti) {
        this.dti = dti;
    }

    public BigDecimal getDlLmt() {
        return dlLmt;
    }

    public void setDlLmt(BigDecimal dlLmt) {
        this.dlLmt = dlLmt;
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

    public String getCreditcardcountabl() {
        return creditcardcountabl;
    }

    public void setCreditcardcountabl(String creditcardcountabl) {
        this.creditcardcountabl = creditcardcountabl == null ? null : creditcardcountabl.trim();
    }

    public String getRzLable() {
        return rzLable;
    }

    public void setRzLable(String rzLable) {
        this.rzLable = rzLable == null ? null : rzLable.trim();
    }

    public String getIsHxEmp() {
        return isHxEmp;
    }

    public void setIsHxEmp(String isHxEmp) {
        this.isHxEmp = isHxEmp == null ? null : isHxEmp.trim();
    }

    public String getIsHxXykEmp() {
        return isHxXykEmp;
    }

    public void setIsHxXykEmp(String isHxXykEmp) {
        this.isHxXykEmp = isHxXykEmp == null ? null : isHxXykEmp.trim();
    }

    public String getTempDecisionresult() {
        return tempDecisionresult;
    }

    public void setTempDecisionresult(String tempDecisionresult) {
        this.tempDecisionresult = tempDecisionresult == null ? null : tempDecisionresult.trim();
    }

    public String getMainRollingLabel() {
        return mainRollingLabel;
    }

    public void setMainRollingLabel(String mainRollingLabel) {
        this.mainRollingLabel = mainRollingLabel == null ? null : mainRollingLabel.trim();
    }

    public String getMainRollingResult() {
        return mainRollingResult;
    }

    public void setMainRollingResult(String mainRollingResult) {
        this.mainRollingResult = mainRollingResult == null ? null : mainRollingResult.trim();
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

    public String getInterestRateProduct() {
        return interestRateProduct;
    }

    public void setInterestRateProduct(String interestRateProduct) {
        this.interestRateProduct = interestRateProduct == null ? null : interestRateProduct.trim();
    }

    public String getCustTypeDesp() {
        return custTypeDesp;
    }

    public void setCustTypeDesp(String custTypeDesp) {
        this.custTypeDesp = custTypeDesp == null ? null : custTypeDesp.trim();
    }

    public String getRcCcCl12mNotAction() {
        return rcCcCl12mNotAction;
    }

    public void setRcCcCl12mNotAction(String rcCcCl12mNotAction) {
        this.rcCcCl12mNotAction = rcCcCl12mNotAction == null ? null : rcCcCl12mNotAction.trim();
    }

    public String getRzResult() {
        return rzResult;
    }

    public void setRzResult(String rzResult) {
        this.rzResult = rzResult == null ? null : rzResult.trim();
    }

    public String getRzDesp() {
        return rzDesp;
    }

    public void setRzDesp(String rzDesp) {
        this.rzDesp = rzDesp == null ? null : rzDesp.trim();
    }

    public String getLoandueabl() {
        return loandueabl;
    }

    public void setLoandueabl(String loandueabl) {
        this.loandueabl = loandueabl == null ? null : loandueabl.trim();
    }

    public String getAppScoreRstDesp() {
        return appScoreRstDesp;
    }

    public void setAppScoreRstDesp(String appScoreRstDesp) {
        this.appScoreRstDesp = appScoreRstDesp == null ? null : appScoreRstDesp.trim();
    }

    public String getAppScoreRstFinDesp() {
        return appScoreRstFinDesp;
    }

    public void setAppScoreRstFinDesp(String appScoreRstFinDesp) {
        this.appScoreRstFinDesp = appScoreRstFinDesp == null ? null : appScoreRstFinDesp.trim();
    }

    public String getPbocRstDesp() {
        return pbocRstDesp;
    }

    public void setPbocRstDesp(String pbocRstDesp) {
        this.pbocRstDesp = pbocRstDesp == null ? null : pbocRstDesp.trim();
    }

    public String getPbocRstFinDesp() {
        return pbocRstFinDesp;
    }

    public void setPbocRstFinDesp(String pbocRstFinDesp) {
        this.pbocRstFinDesp = pbocRstFinDesp == null ? null : pbocRstFinDesp.trim();
    }

    public String getWfResult() {
        return wfResult;
    }

    public void setWfResult(String wfResult) {
        this.wfResult = wfResult == null ? null : wfResult.trim();
    }

    public BigDecimal getYsxAmt() {
        return ysxAmt;
    }

    public void setYsxAmt(BigDecimal ysxAmt) {
        this.ysxAmt = ysxAmt;
    }

    public BigDecimal getJingdongAmt() {
        return jingdongAmt;
    }

    public void setJingdongAmt(BigDecimal jingdongAmt) {
        this.jingdongAmt = jingdongAmt;
    }

    public String getCutOffDirection() {
        return cutOffDirection;
    }

    public void setCutOffDirection(String cutOffDirection) {
        this.cutOffDirection = cutOffDirection == null ? null : cutOffDirection.trim();
    }

    public BigDecimal getHistoryProposedLmt() {
        return historyProposedLmt;
    }

    public void setHistoryProposedLmt(BigDecimal historyProposedLmt) {
        this.historyProposedLmt = historyProposedLmt;
    }

    public String getIndCdRst() {
        return indCdRst;
    }

    public void setIndCdRst(String indCdRst) {
        this.indCdRst = indCdRst == null ? null : indCdRst.trim();
    }

	public String getC1Credpct() {
		return c1Credpct;
	}

	public void setC1Credpct(String c1Credpct) {
		this.c1Credpct = c1Credpct;
	}

	public String getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
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

	public Date getFqzRstTime() {
		return fqzRstTime;
	}

	public void setFqzRstTime(Date fqzRstTime) {
		this.fqzRstTime = fqzRstTime;
	}

	public String getRateValueLoseDate() {
		return rateValueLoseDate;
	}

	public void setRateValueLoseDate(String rateValueLoseDate) {
		this.rateValueLoseDate = rateValueLoseDate;
	}

	public String getCustLevelTagD() {
		return custLevelTagD;
	}

	public void setCustLevelTagD(String custLevelTagD) {
		this.custLevelTagD = custLevelTagD;
	}

	public String getVigilantCust() {
		return vigilantCust;
	}

	public void setVigilantCust(String vigilantCust) {
		this.vigilantCust = vigilantCust;
	}

	public String getSpecialCust() {
		return specialCust;
	}

	public void setSpecialCust(String specialCust) {
		this.specialCust = specialCust;
	}

	public String getC4Abtype() {
		return c4Abtype;
	}

	public void setC4Abtype(String c4Abtype) {
		this.c4Abtype = c4Abtype;
	}
	
}