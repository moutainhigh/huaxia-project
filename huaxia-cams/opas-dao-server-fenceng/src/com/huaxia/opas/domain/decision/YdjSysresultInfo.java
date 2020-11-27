package com.huaxia.opas.domain.decision;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class YdjSysresultInfo implements Serializable {

	private static final long serialVersionUID = -5216079011700449427L;

	private String custLevelTagRate;
	private String custLevelTagLimit;
	private String custLevelTagTerm;
	private String rateName;
	private String repayRatioValue;
	private String repayFreeValue;
	private String autoId;

	private String appId;

	private Date decisionDate;

	private String creditDecisionResult;

	private String creditDecisionLayer;

	private String creditDecisionDesc;

	private Long adviseLimit;

	private BigDecimal macthIncome;

	private BigDecimal applyScore;

	private BigDecimal creditScore;

	private BigDecimal zcredit;

	private String fraudDecisionResult;

	private String fraudResultDesc;

	private String sysDecisionResult;

	private String sysResultDesc;

	private BigDecimal industryIncome;

	private BigDecimal violationLimit;

	private BigDecimal violationRefer;

	private BigDecimal pretaxIncome;

	private BigDecimal sysUnmortgageDti;

	private BigDecimal sysSyntheticalDti;

	private Integer sysRiskOpenMue;

	private BigDecimal adaptUnmortgageDti;

	private BigDecimal adaptSyntheticalDti;

	private Integer adaptRiskOpenMue;

	private BigDecimal refUnmortgageDti;

	private BigDecimal refSyntheticalDti;

	private Integer refRiskOpenMue;

	private BigDecimal debtControl;

	private BigDecimal proLineXiaxian;

	private Short decisionSeq;

	private String strategyType;

	private Date crtDate;

	private String crtUser;

	public String getCustLevelTagRate() {
		return custLevelTagRate;
	}

	public void setCustLevelTagRate(String custLevelTagRate) {
		this.custLevelTagRate = custLevelTagRate;
	}

	public String getCustLevelTagLimit() {
		return custLevelTagLimit;
	}

	public void setCustLevelTagLimit(String custLevelTagLimit) {
		this.custLevelTagLimit = custLevelTagLimit;
	}

	public String getCustLevelTagTerm() {
		return custLevelTagTerm;
	}

	public void setCustLevelTagTerm(String custLevelTagTerm) {
		this.custLevelTagTerm = custLevelTagTerm;
	}

	public String getRateName() {
		return rateName;
	}

	public void setRateName(String rateName) {
		this.rateName = rateName;
	}

	public String getRepayRatioValue() {
		return repayRatioValue;
	}

	public void setRepayRatioValue(String repayRatioValue) {
		this.repayRatioValue = repayRatioValue;
	}

	public String getRepayFreeValue() {
		return repayFreeValue;
	}

	public void setRepayFreeValue(String repayFreeValue) {
		this.repayFreeValue = repayFreeValue;
	}

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

	public Date getDecisionDate() {
		return decisionDate;
	}

	public void setDecisionDate(Date decisionDate) {
		this.decisionDate = decisionDate;
	}

	public String getCreditDecisionResult() {
		return creditDecisionResult;
	}

	public void setCreditDecisionResult(String creditDecisionResult) {
		this.creditDecisionResult = creditDecisionResult == null ? null : creditDecisionResult.trim();
	}

	public String getCreditDecisionLayer() {
		return creditDecisionLayer;
	}

	public void setCreditDecisionLayer(String creditDecisionLayer) {
		this.creditDecisionLayer = creditDecisionLayer == null ? null : creditDecisionLayer.trim();
	}

	public String getCreditDecisionDesc() {
		return creditDecisionDesc;
	}

	public void setCreditDecisionDesc(String creditDecisionDesc) {
		this.creditDecisionDesc = creditDecisionDesc == null ? null : creditDecisionDesc.trim();
	}

	public Long getAdviseLimit() {
		return adviseLimit;
	}

	public void setAdviseLimit(Long adviseLimit) {
		this.adviseLimit = adviseLimit;
	}

	public BigDecimal getMacthIncome() {
		return macthIncome;
	}

	public void setMacthIncome(BigDecimal macthIncome) {
		this.macthIncome = macthIncome;
	}

	public BigDecimal getApplyScore() {
		return applyScore;
	}

	public void setApplyScore(BigDecimal applyScore) {
		this.applyScore = applyScore;
	}

	public BigDecimal getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(BigDecimal creditScore) {
		this.creditScore = creditScore;
	}

	public BigDecimal getZcredit() {
		return zcredit;
	}

	public void setZcredit(BigDecimal zcredit) {
		this.zcredit = zcredit;
	}

	public String getFraudDecisionResult() {
		return fraudDecisionResult;
	}

	public void setFraudDecisionResult(String fraudDecisionResult) {
		this.fraudDecisionResult = fraudDecisionResult == null ? null : fraudDecisionResult.trim();
	}

	public String getFraudResultDesc() {
		return fraudResultDesc;
	}

	public void setFraudResultDesc(String fraudResultDesc) {
		this.fraudResultDesc = fraudResultDesc == null ? null : fraudResultDesc.trim();
	}

	public String getSysDecisionResult() {
		return sysDecisionResult;
	}

	public void setSysDecisionResult(String sysDecisionResult) {
		this.sysDecisionResult = sysDecisionResult == null ? null : sysDecisionResult.trim();
	}

	public String getSysResultDesc() {
		return sysResultDesc;
	}

	public void setSysResultDesc(String sysResultDesc) {
		this.sysResultDesc = sysResultDesc == null ? null : sysResultDesc.trim();
	}

	public BigDecimal getIndustryIncome() {
		return industryIncome;
	}

	public void setIndustryIncome(BigDecimal industryIncome) {
		this.industryIncome = industryIncome;
	}

	public BigDecimal getViolationLimit() {
		return violationLimit;
	}

	public void setViolationLimit(BigDecimal violationLimit) {
		this.violationLimit = violationLimit;
	}

	public BigDecimal getViolationRefer() {
		return violationRefer;
	}

	public void setViolationRefer(BigDecimal violationRefer) {
		this.violationRefer = violationRefer;
	}

	public BigDecimal getPretaxIncome() {
		return pretaxIncome;
	}

	public void setPretaxIncome(BigDecimal pretaxIncome) {
		this.pretaxIncome = pretaxIncome;
	}

	public BigDecimal getSysUnmortgageDti() {
		return sysUnmortgageDti;
	}

	public void setSysUnmortgageDti(BigDecimal sysUnmortgageDti) {
		this.sysUnmortgageDti = sysUnmortgageDti;
	}

	public BigDecimal getSysSyntheticalDti() {
		return sysSyntheticalDti;
	}

	public void setSysSyntheticalDti(BigDecimal sysSyntheticalDti) {
		this.sysSyntheticalDti = sysSyntheticalDti;
	}

	public Integer getSysRiskOpenMue() {
		return sysRiskOpenMue;
	}

	public void setSysRiskOpenMue(Integer sysRiskOpenMue) {
		this.sysRiskOpenMue = sysRiskOpenMue;
	}

	public BigDecimal getAdaptUnmortgageDti() {
		return adaptUnmortgageDti;
	}

	public void setAdaptUnmortgageDti(BigDecimal adaptUnmortgageDti) {
		this.adaptUnmortgageDti = adaptUnmortgageDti;
	}

	public BigDecimal getAdaptSyntheticalDti() {
		return adaptSyntheticalDti;
	}

	public void setAdaptSyntheticalDti(BigDecimal adaptSyntheticalDti) {
		this.adaptSyntheticalDti = adaptSyntheticalDti;
	}

	public Integer getAdaptRiskOpenMue() {
		return adaptRiskOpenMue;
	}

	public void setAdaptRiskOpenMue(Integer adaptRiskOpenMue) {
		this.adaptRiskOpenMue = adaptRiskOpenMue;
	}

	public BigDecimal getRefUnmortgageDti() {
		return refUnmortgageDti;
	}

	public void setRefUnmortgageDti(BigDecimal refUnmortgageDti) {
		this.refUnmortgageDti = refUnmortgageDti;
	}

	public BigDecimal getRefSyntheticalDti() {
		return refSyntheticalDti;
	}

	public void setRefSyntheticalDti(BigDecimal refSyntheticalDti) {
		this.refSyntheticalDti = refSyntheticalDti;
	}

	public Integer getRefRiskOpenMue() {
		return refRiskOpenMue;
	}

	public void setRefRiskOpenMue(Integer refRiskOpenMue) {
		this.refRiskOpenMue = refRiskOpenMue;
	}

	public BigDecimal getDebtControl() {
		return debtControl;
	}

	public void setDebtControl(BigDecimal debtControl) {
		this.debtControl = debtControl;
	}

	public BigDecimal getProLineXiaxian() {
		return proLineXiaxian;
	}

	public void setProLineXiaxian(BigDecimal proLineXiaxian) {
		this.proLineXiaxian = proLineXiaxian;
	}

	public Short getDecisionSeq() {
		return decisionSeq;
	}

	public void setDecisionSeq(Short decisionSeq) {
		this.decisionSeq = decisionSeq;
	}

	public String getStrategyType() {
		return strategyType;
	}

	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType == null ? null : strategyType.trim();
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