package com.huaxia.opas.domain.collect;

import java.io.Serializable;
import java.util.Date;

/**
 * 收入可采纳项
 * 
 * @author zhiguo.li
 *
 */
public class SalaryAdoptItem implements Serializable {

	private static final long serialVersionUID = -5159619513047451274L;

	// UUID
	private String adoptItemId;

	// 可采纳项-工资流水
	private String itemSalary;

	// 可采纳项-完税证明
	private String itemTaxRecord;

	// 可采纳项-工作证明
	private String itemWorkCertif;

	// 税前
	private Integer workPreTax;

	// 税后
	private Integer workAfterTax;

	// 可采纳项-社保基数
	private String itemSocialSecurityBase;

	// 社保基数
	private Integer socialSecurityBase;

	// 可采纳项-公积金
	private String itemFund;

	// 公积金基数
	private Double fundBase;
	
	//区域公积金月收入
	private Double accfundincomexm;

	// 公积金月缴纳额
	private Double fundMonthPay;

	// 可采纳项-房租收入
	private String itemHouseRent;

	// 房租月租金
	private Integer houseMonthRental;

	// 可采纳项-配偶税前月收入
	private String itemSpousePreTax;

	// 配偶税前月收入
	private Integer spousePreTaxIncome;

	// 可采纳项-高财力客户日均余额
	private String itemFinancesCust;

	// 高财力客户日均余额
	private Integer financesCustAvgDay;

	// 创建日期
	private Date createDate;

	// 创建人
	private String createUser;

	// 最后修改日期
	private Date lastUpdateDate;

	// 最后修改人
	private String lastUpdateUser;
	
	
	public Double getAccfundincomexm() {
		return accfundincomexm;
	}

	public void setAccfundincomexm(Double accfundincomexm) {
		this.accfundincomexm = accfundincomexm;
	}

	public SalaryAdoptItem() {
	}

	public String getAdoptItemId() {
		return adoptItemId;
	}

	public void setAdoptItemId(String adoptItemId) {
		this.adoptItemId = adoptItemId;
	}
	
	public String getItemSalary() {
		return itemSalary;
	}

	public void setItemSalary(String itemSalary) {
		this.itemSalary = itemSalary;
	}

	public String getItemTaxRecord() {
		return itemTaxRecord;
	}

	public void setItemTaxRecord(String itemTaxRecord) {
		this.itemTaxRecord = itemTaxRecord;
	}

	public String getItemWorkCertif() {
		return itemWorkCertif;
	}

	public void setItemWorkCertif(String itemWorkCertif) {
		this.itemWorkCertif = itemWorkCertif;
	}

	public Integer getWorkPreTax() {
		return workPreTax;
	}

	public void setWorkPreTax(Integer workPreTax) {
		this.workPreTax = workPreTax;
	}

	public Integer getWorkAfterTax() {
		return workAfterTax;
	}

	public void setWorkAfterTax(Integer workAfterTax) {
		this.workAfterTax = workAfterTax;
	}

	public String getItemSocialSecurityBase() {
		return itemSocialSecurityBase;
	}

	public void setItemSocialSecurityBase(String itemSocialSecurityBase) {
		this.itemSocialSecurityBase = itemSocialSecurityBase;
	}

	public Integer getSocialSecurityBase() {
		return socialSecurityBase;
	}

	public void setSocialSecurityBase(Integer socialSecurityBase) {
		this.socialSecurityBase = socialSecurityBase;
	}

	public String getItemFund() {
		return itemFund;
	}

	public void setItemFund(String itemFund) {
		this.itemFund = itemFund;
	}

	public Double getFundBase() {
		return fundBase;
	}

	public void setFundBase(Double ndepositBase) {
		this.fundBase = ndepositBase;
	}

	public Double getFundMonthPay() {
		return fundMonthPay;
	}

	public void setFundMonthPay(Double ndepositAmount) {
		this.fundMonthPay = ndepositAmount;
	}

	public String getItemHouseRent() {
		return itemHouseRent;
	}

	public void setItemHouseRent(String itemHouseRent) {
		this.itemHouseRent = itemHouseRent;
	}

	public Integer getHouseMonthRental() {
		return houseMonthRental;
	}

	public void setHouseMonthRental(Integer houseMonthRental) {
		this.houseMonthRental = houseMonthRental;
	}

	public String getItemSpousePreTax() {
		return itemSpousePreTax;
	}

	public void setItemSpousePreTax(String itemSpousePreTax) {
		this.itemSpousePreTax = itemSpousePreTax;
	}

	public Integer getSpousePreTaxIncome() {
		return spousePreTaxIncome;
	}

	public void setSpousePreTaxIncome(Integer spousePreTaxIncome) {
		this.spousePreTaxIncome = spousePreTaxIncome;
	}

	public String getItemFinancesCust() {
		return itemFinancesCust;
	}

	public void setItemFinancesCust(String itemFinancesCust) {
		this.itemFinancesCust = itemFinancesCust;
	}

	public Integer getFinancesCustAvgDay() {
		return financesCustAvgDay;
	}

	public void setFinancesCustAvgDay(Integer financesCustAvgDay) {
		this.financesCustAvgDay = financesCustAvgDay;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

}
