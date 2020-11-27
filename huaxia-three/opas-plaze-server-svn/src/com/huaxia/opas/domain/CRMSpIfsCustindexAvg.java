package com.huaxia.opas.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 第三方 & CRM & 客户全行资产日均（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsCustindexAvg extends CRM implements Serializable {

	private static final long serialVersionUID = -5942737324243830167L;

	// 客户ID
	private String custId;
	
	// 储蓄存款月日均
	private BigDecimal deptMavg;
	
	// 储蓄存款季日均
	private BigDecimal deptQavg;
	
	// 储蓄存款年日均
	private BigDecimal deptYavg;
	
	// 定期存款月日均
	private BigDecimal tdMavg;
	
	// 定期存款季日均
	private BigDecimal tdQavg;
	
	// 定期存款年日均
	private BigDecimal tdYavg;
	
	// 活期存款月日均
	private BigDecimal curtMavg;
	
	// 活期存款季日均
	private BigDecimal curtQavg;
	
	// 活期存款年日均
	private BigDecimal curtYavg;
	
	// 理财资产月日均
	private BigDecimal finMavg;
	
	// 理财资产季日均
	private BigDecimal finQavg;
	
	// 理财资产年日均
	private BigDecimal finYavg;
	
	// 基金资产月日均
	private BigDecimal fundMavg;
	
	// 基金资产季日均
	private BigDecimal fundQavg;
	
	// 基金资产年日均
	private BigDecimal fundYavg;
	
	// 国债资产月日均
	private BigDecimal debtMavg;
	
	// 国债资产季日均
	private BigDecimal debtQavg;
	
	// 国债资产年日均
	private BigDecimal debtYavg;
	
	// 保险资产月日均
	private BigDecimal insurMavg;
	
	// 保险资产季日均
	private BigDecimal insurQavg;
	
	// 保险资产年日均
	private BigDecimal insurYavg;
	
	// 黄金资产月日均
	private BigDecimal goldMavg;
	
	// 黄金资产季日均
	private BigDecimal goldQavg;
	
	// 黄金资产年日均
	private BigDecimal goldYavg;
	
	// 第三方存管资产月日均
	private BigDecimal stockMavg;
	
	// 第三方存管资产季日均
	private BigDecimal stockQavg;
	
	// 第三方存管资产年日均
	private BigDecimal stockYavg;
	
	// 信托资产月日均
	private BigDecimal trustMavg;
	
	// 信托资产季日均
	private BigDecimal trustQavg;
	
	// 信托资产年日均
	private BigDecimal trustYavg;
	
	// 理财在途资产月日均
	private BigDecimal pasMavg;
	
	// 理财在途资产季日均
	private BigDecimal pasQavg;
	
	// 理财在途资产年日均
	private BigDecimal pasYavg;
	
	// 金融资产总额月日均
	private BigDecimal aumMavg;
	
	// 金融资产总额季日均
	private BigDecimal aumQavg;
	
	// 金融资产总额年日均
	private BigDecimal aumYavg;
	
	// 金融资产可用总额月日均
	private BigDecimal useAumMavg;
	
	// 金融资产可用总额季日均
	private BigDecimal useAumQavg;
	
	// 金融资产可用总额年日均
	private BigDecimal useAumYavg;
	
	// 数据日期
	private String dDate;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public BigDecimal getDeptMavg() {
		return deptMavg;
	}

	public void setDeptMavg(BigDecimal deptMavg) {
		this.deptMavg = deptMavg;
	}

	public BigDecimal getDeptQavg() {
		return deptQavg;
	}

	public void setDeptQavg(BigDecimal deptQavg) {
		this.deptQavg = deptQavg;
	}

	public BigDecimal getDeptYavg() {
		return deptYavg;
	}

	public void setDeptYavg(BigDecimal deptYavg) {
		this.deptYavg = deptYavg;
	}

	public BigDecimal getTdMavg() {
		return tdMavg;
	}

	public void setTdMavg(BigDecimal tdMavg) {
		this.tdMavg = tdMavg;
	}

	public BigDecimal getTdQavg() {
		return tdQavg;
	}

	public void setTdQavg(BigDecimal tdQavg) {
		this.tdQavg = tdQavg;
	}

	public BigDecimal getTdYavg() {
		return tdYavg;
	}

	public void setTdYavg(BigDecimal tdYavg) {
		this.tdYavg = tdYavg;
	}

	public BigDecimal getCurtMavg() {
		return curtMavg;
	}

	public void setCurtMavg(BigDecimal curtMavg) {
		this.curtMavg = curtMavg;
	}

	public BigDecimal getCurtQavg() {
		return curtQavg;
	}

	public void setCurtQavg(BigDecimal curtQavg) {
		this.curtQavg = curtQavg;
	}

	public BigDecimal getCurtYavg() {
		return curtYavg;
	}

	public void setCurtYavg(BigDecimal curtYavg) {
		this.curtYavg = curtYavg;
	}

	public BigDecimal getFinMavg() {
		return finMavg;
	}

	public void setFinMavg(BigDecimal finMavg) {
		this.finMavg = finMavg;
	}

	public BigDecimal getFinQavg() {
		return finQavg;
	}

	public void setFinQavg(BigDecimal finQavg) {
		this.finQavg = finQavg;
	}

	public BigDecimal getFinYavg() {
		return finYavg;
	}

	public void setFinYavg(BigDecimal finYavg) {
		this.finYavg = finYavg;
	}

	public BigDecimal getFundMavg() {
		return fundMavg;
	}

	public void setFundMavg(BigDecimal fundMavg) {
		this.fundMavg = fundMavg;
	}

	public BigDecimal getFundQavg() {
		return fundQavg;
	}

	public void setFundQavg(BigDecimal fundQavg) {
		this.fundQavg = fundQavg;
	}

	public BigDecimal getFundYavg() {
		return fundYavg;
	}

	public void setFundYavg(BigDecimal fundYavg) {
		this.fundYavg = fundYavg;
	}

	public BigDecimal getDebtMavg() {
		return debtMavg;
	}

	public void setDebtMavg(BigDecimal debtMavg) {
		this.debtMavg = debtMavg;
	}

	public BigDecimal getDebtQavg() {
		return debtQavg;
	}

	public void setDebtQavg(BigDecimal debtQavg) {
		this.debtQavg = debtQavg;
	}

	public BigDecimal getDebtYavg() {
		return debtYavg;
	}

	public void setDebtYavg(BigDecimal debtYavg) {
		this.debtYavg = debtYavg;
	}

	public BigDecimal getInsurMavg() {
		return insurMavg;
	}

	public void setInsurMavg(BigDecimal insurMavg) {
		this.insurMavg = insurMavg;
	}

	public BigDecimal getInsurQavg() {
		return insurQavg;
	}

	public void setInsurQavg(BigDecimal insurQavg) {
		this.insurQavg = insurQavg;
	}

	public BigDecimal getInsurYavg() {
		return insurYavg;
	}

	public void setInsurYavg(BigDecimal insurYavg) {
		this.insurYavg = insurYavg;
	}

	public BigDecimal getGoldMavg() {
		return goldMavg;
	}

	public void setGoldMavg(BigDecimal goldMavg) {
		this.goldMavg = goldMavg;
	}

	public BigDecimal getGoldQavg() {
		return goldQavg;
	}

	public void setGoldQavg(BigDecimal goldQavg) {
		this.goldQavg = goldQavg;
	}

	public BigDecimal getGoldYavg() {
		return goldYavg;
	}

	public void setGoldYavg(BigDecimal goldYavg) {
		this.goldYavg = goldYavg;
	}

	public BigDecimal getStockMavg() {
		return stockMavg;
	}

	public void setStockMavg(BigDecimal stockMavg) {
		this.stockMavg = stockMavg;
	}

	public BigDecimal getStockQavg() {
		return stockQavg;
	}

	public void setStockQavg(BigDecimal stockQavg) {
		this.stockQavg = stockQavg;
	}

	public BigDecimal getStockYavg() {
		return stockYavg;
	}

	public void setStockYavg(BigDecimal stockYavg) {
		this.stockYavg = stockYavg;
	}

	public BigDecimal getTrustMavg() {
		return trustMavg;
	}

	public void setTrustMavg(BigDecimal trustMavg) {
		this.trustMavg = trustMavg;
	}

	public BigDecimal getTrustQavg() {
		return trustQavg;
	}

	public void setTrustQavg(BigDecimal trustQavg) {
		this.trustQavg = trustQavg;
	}

	public BigDecimal getTrustYavg() {
		return trustYavg;
	}

	public void setTrustYavg(BigDecimal trustYavg) {
		this.trustYavg = trustYavg;
	}

	public BigDecimal getPasMavg() {
		return pasMavg;
	}

	public void setPasMavg(BigDecimal pasMavg) {
		this.pasMavg = pasMavg;
	}

	public BigDecimal getPasQavg() {
		return pasQavg;
	}

	public void setPasQavg(BigDecimal pasQavg) {
		this.pasQavg = pasQavg;
	}

	public BigDecimal getPasYavg() {
		return pasYavg;
	}

	public void setPasYavg(BigDecimal pasYavg) {
		this.pasYavg = pasYavg;
	}

	public BigDecimal getAumMavg() {
		return aumMavg;
	}

	public void setAumMavg(BigDecimal aumMavg) {
		this.aumMavg = aumMavg;
	}

	public BigDecimal getAumQavg() {
		return aumQavg;
	}

	public void setAumQavg(BigDecimal aumQavg) {
		this.aumQavg = aumQavg;
	}

	public BigDecimal getAumYavg() {
		return aumYavg;
	}

	public void setAumYavg(BigDecimal aumYavg) {
		this.aumYavg = aumYavg;
	}

	public BigDecimal getUseAumMavg() {
		return useAumMavg;
	}

	public void setUseAumMavg(BigDecimal useAumMavg) {
		this.useAumMavg = useAumMavg;
	}

	public BigDecimal getUseAumQavg() {
		return useAumQavg;
	}

	public void setUseAumQavg(BigDecimal useAumQavg) {
		this.useAumQavg = useAumQavg;
	}

	public BigDecimal getUseAumYavg() {
		return useAumYavg;
	}

	public void setUseAumYavg(BigDecimal useAumYavg) {
		this.useAumYavg = useAumYavg;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}

}
