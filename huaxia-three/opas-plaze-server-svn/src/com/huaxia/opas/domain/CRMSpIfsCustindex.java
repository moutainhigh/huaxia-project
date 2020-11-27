package com.huaxia.opas.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 第三方 & CRM & 客户全行资产（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsCustindex extends CRM implements Serializable {

	private static final long serialVersionUID = 1697910379152863775L;

	// 客户ID
	private String custId;
	
	// 储蓄存款
	private BigDecimal deptBal;
	
	// 定期存款
	private BigDecimal tdBal;
	
	// 活期存款
	private BigDecimal curtBal;
	
	// 理财资产
	private BigDecimal finBal;
	
	// 基金资产
	private BigDecimal fundBal;
	
	// 国债资产
	private BigDecimal debtBal;
	
	// 保险资产
	private BigDecimal insurBal;
	
	// 黄金资产
	private BigDecimal goldBal;
	
	// 第三方存管资产
	private BigDecimal stockBal;
	
	// 信托资产
	private BigDecimal trustBal;
	
	// 理财在途资产
	private BigDecimal pasBal;
	
	// 金融资产总额
	private BigDecimal aum;
	
	// 金融资产可用总额
	private BigDecimal useAum;
	
	// 最高余额
	private BigDecimal highBal;
	
	// 余额最高日期
	private String highBalDate;
	
	// 数据日期
	private String dDate;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public BigDecimal getDeptBal() {
		return deptBal;
	}

	public void setDeptBal(BigDecimal deptBal) {
		this.deptBal = deptBal;
	}

	public BigDecimal getTdBal() {
		return tdBal;
	}

	public void setTdBal(BigDecimal tdBal) {
		this.tdBal = tdBal;
	}

	public BigDecimal getCurtBal() {
		return curtBal;
	}

	public void setCurtBal(BigDecimal curtBal) {
		this.curtBal = curtBal;
	}

	public BigDecimal getFinBal() {
		return finBal;
	}

	public void setFinBal(BigDecimal finBal) {
		this.finBal = finBal;
	}

	public BigDecimal getFundBal() {
		return fundBal;
	}

	public void setFundBal(BigDecimal fundBal) {
		this.fundBal = fundBal;
	}

	public BigDecimal getDebtBal() {
		return debtBal;
	}

	public void setDebtBal(BigDecimal debtBal) {
		this.debtBal = debtBal;
	}

	public BigDecimal getInsurBal() {
		return insurBal;
	}

	public void setInsurBal(BigDecimal insurBal) {
		this.insurBal = insurBal;
	}

	public BigDecimal getGoldBal() {
		return goldBal;
	}

	public void setGoldBal(BigDecimal goldBal) {
		this.goldBal = goldBal;
	}

	public BigDecimal getStockBal() {
		return stockBal;
	}

	public void setStockBal(BigDecimal stockBal) {
		this.stockBal = stockBal;
	}

	public BigDecimal getTrustBal() {
		return trustBal;
	}

	public void setTrustBal(BigDecimal trustBal) {
		this.trustBal = trustBal;
	}

	public BigDecimal getPasBal() {
		return pasBal;
	}

	public void setPasBal(BigDecimal pasBal) {
		this.pasBal = pasBal;
	}

	public BigDecimal getAum() {
		return aum;
	}

	public void setAum(BigDecimal aum) {
		this.aum = aum;
	}

	public BigDecimal getUseAum() {
		return useAum;
	}

	public void setUseAum(BigDecimal useAum) {
		this.useAum = useAum;
	}

	public BigDecimal getHighBal() {
		return highBal;
	}

	public void setHighBal(BigDecimal highBal) {
		this.highBal = highBal;
	}

	public String getHighBalDate() {
		return highBalDate;
	}

	public void setHighBalDate(String highBalDate) {
		this.highBalDate = highBalDate;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}



}
