package com.huaxia.opas.domain.decision;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class OpaBzkSysResultInfo implements Serializable {

	private static final long serialVersionUID = 5168854553251009650L;

	private String autoId;

	private String appId;

	/**
	 * 征信决策结果 L-低风险人工征信 M-人工征信 H-高风险人工征信 S-自动征信 L1-低风险人工征信L1
	 * 
	 */
	private String creditDecisionResult;
	/**
	 * 征信决策分层 L-低风险人工征信 M-人工征信 H-高风险人工征信
	 * 
	 */
	private String creditDecisionLayer;
	/**
	 * 征信结果描述 信息真实性校验通过（对应低风险）、人工正核、高风险人工正核、人工侧核、人工审核、信息真实性校验失败（对应高风险）
	 */
	private String creditDecisionDesc;
	/**
	 * 建议额度
	 */
	private Long adviseLimit;
	/**
	 * 匹配收入
	 */
	private BigDecimal macthIncome;
	/**
	 * 申请评分
	 */
	private BigDecimal applyScore;
	/**
	 * 反欺诈决策结果
	 */
	private String fraudDecisionResult;
	/**
	 * 反欺诈结果描述
	 */
	private String fraudResultDesc;
	/**
	 * 系统决策结果 1-直接批准 2-建议批准 3-直接拒绝 4-建议拒绝 5-人工审核
	 * 
	 */
	private String sysDecisionResult;
	/**
	 * 系统结果描述
	 */
	private String sysDecisionResultDesc;
	/**
	 * 系统授信产品
	 */
	private String sysCreditProduct;
	/**
	 * 附属卡决策结果
	 */
	private String atchDecisionResult;
	/**
	 * 附属卡决策结果描述
	 */
	private String atchDecisionResultDesc;
	/**
	 * 附属卡申请额度比例
	 */
	private BigDecimal atchApplyRatio;
	/**
	 * 标准卡DTI检查 以百分比显示
	 */
	private BigDecimal standardDtiCheck;
	/**
	 * 标准DL 单位：元
	 */
	private BigDecimal standardDl;
	/**
	 * 决策顺序标识 记录每一次决策结果
	 */
	private Short decisionSeq;
	/**
	 * 
	 */
	@JSONField(format = "yyyy/MM/dd HH:mm:ss")
	private Date crtDate;
	/**
	 * 
	 */
	private String crtUser;
	/**
	 * 决策时间
	 */
	@JSONField(format = "yyyy/MM/dd HH:mm:ss")
	private Date decisionDate;
	/**
	 * 征信评分
	 */
	private BigDecimal creditScore;
	/**
	 * 无抵押DTI（额度调整） 以百分比显示
	 */
	private BigDecimal nonmortgageDtiAd;
	/**
	 * 综合DTI（额度调整）以百分比显示
	 */
	private BigDecimal cmphsiveDtiAd;
	/**
	 * 风险敞口MUE（额度调整）以百分比显示
	 */
	private BigDecimal riskOpenMueAd;
	/**
	 * 无抵押DTI（额度下限）以百分比显示
	 */
	private BigDecimal nonmortgageDtiDown;
	/**
	 * 综合DTI（额度下限）以百分比显示
	 */
	private BigDecimal cmphsiveDtiDown;
	/**
	 * 风险敞口MUE（额度下限）以百分比显示
	 */
	private BigDecimal riskOpenMueDown;
	/**
	 * 负债审批权限 以百分比显示
	 */
	private BigDecimal debtAppAuth;
	/**
	 * 产品额度下限 以百分比显示
	 */
	private BigDecimal prodLimitDown;
	private String custLevelTagRate;
	private String custLevelTagLimit;
	private String custLevelTagTerm;

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

	public String getSysDecisionResultDesc() {
		return sysDecisionResultDesc;
	}

	public void setSysDecisionResultDesc(String sysDecisionResultDesc) {
		this.sysDecisionResultDesc = sysDecisionResultDesc == null ? null : sysDecisionResultDesc.trim();
	}

	public String getSysCreditProduct() {
		return sysCreditProduct;
	}

	public void setSysCreditProduct(String sysCreditProduct) {
		this.sysCreditProduct = sysCreditProduct == null ? null : sysCreditProduct.trim();
	}

	public String getAtchDecisionResult() {
		return atchDecisionResult;
	}

	public void setAtchDecisionResult(String atchDecisionResult) {
		this.atchDecisionResult = atchDecisionResult == null ? null : atchDecisionResult.trim();
	}

	public String getAtchDecisionResultDesc() {
		return atchDecisionResultDesc;
	}

	public void setAtchDecisionResultDesc(String atchDecisionResultDesc) {
		this.atchDecisionResultDesc = atchDecisionResultDesc == null ? null : atchDecisionResultDesc.trim();
	}

	public BigDecimal getAtchApplyRatio() {
		return atchApplyRatio;
	}

	public void setAtchApplyRatio(BigDecimal atchApplyRatio) {
		this.atchApplyRatio = atchApplyRatio;
	}

	public BigDecimal getStandardDtiCheck() {
		return standardDtiCheck;
	}

	public void setStandardDtiCheck(BigDecimal standardDtiCheck) {
		this.standardDtiCheck = standardDtiCheck;
	}

	public BigDecimal getStandardDl() {
		return standardDl;
	}

	public void setStandardDl(BigDecimal standardDl) {
		this.standardDl = standardDl;
	}

	public Short getDecisionSeq() {
		return decisionSeq;
	}

	public void setDecisionSeq(Short decisionSeq) {
		this.decisionSeq = decisionSeq;
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

	public Date getDecisionDate() {
		return decisionDate;
	}

	public void setDecisionDate(Date decisionDate) {
		this.decisionDate = decisionDate;
	}

	public BigDecimal getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(BigDecimal creditScore) {
		this.creditScore = creditScore;
	}

	public BigDecimal getNonmortgageDtiAd() {
		return nonmortgageDtiAd;
	}

	public void setNonmortgageDtiAd(BigDecimal nonmortgageDtiAd) {
		this.nonmortgageDtiAd = nonmortgageDtiAd;
	}

	public BigDecimal getCmphsiveDtiAd() {
		return cmphsiveDtiAd;
	}

	public void setCmphsiveDtiAd(BigDecimal cmphsiveDtiAd) {
		this.cmphsiveDtiAd = cmphsiveDtiAd;
	}

	public BigDecimal getRiskOpenMueAd() {
		return riskOpenMueAd;
	}

	public void setRiskOpenMueAd(BigDecimal riskOpenMueAd) {
		this.riskOpenMueAd = riskOpenMueAd;
	}

	public BigDecimal getNonmortgageDtiDown() {
		return nonmortgageDtiDown;
	}

	public void setNonmortgageDtiDown(BigDecimal nonmortgageDtiDown) {
		this.nonmortgageDtiDown = nonmortgageDtiDown;
	}

	public BigDecimal getCmphsiveDtiDown() {
		return cmphsiveDtiDown;
	}

	public void setCmphsiveDtiDown(BigDecimal cmphsiveDtiDown) {
		this.cmphsiveDtiDown = cmphsiveDtiDown;
	}

	public BigDecimal getRiskOpenMueDown() {
		return riskOpenMueDown;
	}

	public void setRiskOpenMueDown(BigDecimal riskOpenMueDown) {
		this.riskOpenMueDown = riskOpenMueDown;
	}

	public BigDecimal getDebtAppAuth() {
		return debtAppAuth;
	}

	public void setDebtAppAuth(BigDecimal debtAppAuth) {
		this.debtAppAuth = debtAppAuth;
	}

	public BigDecimal getProdLimitDown() {
		return prodLimitDown;
	}

	public void setProdLimitDown(BigDecimal prodLimitDown) {
		this.prodLimitDown = prodLimitDown;
	}

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
}