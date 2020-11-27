package com.huaxia.opas.domain.collect;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 增强采集BLAZE结果实体类
 * 
 * @author zhiguo.li
 *
 */
public class EnhanceBlazeResult implements Serializable {

	private static final long serialVersionUID = -8648187166151088643L;

	// 申请件编号
	private String appId;

	// BLAZE - 实际月收入
	private BigDecimal realMonthIncome;

	// BLAZE - 人行抵押贷款负债
	private BigDecimal pbocMontgageDebt;

	// BLAZE - 人行无抵押贷款负债
	private BigDecimal pbocUnMontgageDebt;

	// BLAZE - 人行信用卡负债
	private BigDecimal pbocCreditDebt;

	// BLAZE - 人行无抵押贷款敞口
	private BigDecimal pbocUnmontgageOpen;

	// BLAZE - 信用卡敞口
	private BigDecimal creditOpen;

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

	public EnhanceBlazeResult() {
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public BigDecimal getRealMonthIncome() {
		return realMonthIncome;
	}

	public void setRealMonthIncome(BigDecimal realMonthIncome) {
		this.realMonthIncome = realMonthIncome;
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

}
