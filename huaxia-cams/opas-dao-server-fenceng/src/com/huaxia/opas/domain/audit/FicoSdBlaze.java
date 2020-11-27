package com.huaxia.opas.domain.audit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FicoSdBlaze implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String houseInfoFrom;
	private BigDecimal houseCost;
	private BigDecimal houseArea;
	private String carCost;
	private String carHand;
	private String carOld;
	private Date registerDate;
	private Date issueDate;
	private Date perOpendate;
	private BigDecimal perAssetBalance;
	private BigDecimal perLoanpri;
	private BigDecimal perPayWage;
	private BigDecimal perFinAssetSum;
	private String perLoanstatus;
	private String sbCurrPayStatus;
	private Date sbPayDate;
	private BigDecimal sbCurrCompanyMonth;
	private BigDecimal sbMonthPaybase;
	private BigDecimal sbMonthPayment;
	private String sbCurrPaycompany;
	private String gjjPayStatus;
	private BigDecimal gjjMonthPayment;
	private BigDecimal gjjPerPayrate;
	private Date gjjPayDate;
	private String gjjEndMonth;
	private String eduType;
	private String eduModel;
	private String jobCode;
	private String industryCode;
	private String carCategroy;
	
	
	public String getCarCategroy() {
		return carCategroy;
	}
	public void setCarCategroy(String carCategroy) {
		this.carCategroy = carCategroy;
	}
	public String getHouseInfoFrom() {
		return houseInfoFrom;
	}
	public void setHouseInfoFrom(String houseInfoFrom) {
		this.houseInfoFrom = houseInfoFrom;
	}
	public BigDecimal getHouseCost() {
		return houseCost;
	}
	public void setHouseCost(BigDecimal houseCost) {
		this.houseCost = houseCost;
	}
	public BigDecimal getHouseArea() {
		return houseArea;
	}
	public void setHouseArea(BigDecimal houseArea) {
		this.houseArea = houseArea;
	}
	public String getCarCost() {
		return carCost;
	}
	public void setCarCost(String carCost) {
		this.carCost = carCost;
	}
	public String getCarHand() {
		return carHand;
	}
	public void setCarHand(String carHand) {
		this.carHand = carHand;
	}
	public String getCarOld() {
		return carOld;
	}
	public void setCarOld(String carOld) {
		this.carOld = carOld;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getPerOpendate() {
		return perOpendate;
	}
	public void setPerOpendate(Date perOpendate) {
		this.perOpendate = perOpendate;
	}
	public BigDecimal getPerAssetBalance() {
		return perAssetBalance;
	}
	public void setPerAssetBalance(BigDecimal perAssetBalance) {
		this.perAssetBalance = perAssetBalance;
	}
	public BigDecimal getPerLoanpri() {
		return perLoanpri;
	}
	public void setPerLoanpri(BigDecimal perLoanpri) {
		this.perLoanpri = perLoanpri;
	}
	public BigDecimal getPerPayWage() {
		return perPayWage;
	}
	public void setPerPayWage(BigDecimal perPayWage) {
		this.perPayWage = perPayWage;
	}
	public BigDecimal getPerFinAssetSum() {
		return perFinAssetSum;
	}
	public void setPerFinAssetSum(BigDecimal perFinAssetSum) {
		this.perFinAssetSum = perFinAssetSum;
	}
	public String getPerLoanstatus() {
		return perLoanstatus;
	}
	public void setPerLoanstatus(String perLoanstatus) {
		this.perLoanstatus = perLoanstatus;
	}
	public String getSbCurrPayStatus() {
		return sbCurrPayStatus;
	}
	public void setSbCurrPayStatus(String sbCurrPayStatus) {
		this.sbCurrPayStatus = sbCurrPayStatus;
	}
	public Date getSbPayDate() {
		return sbPayDate;
	}
	public void setSbPayDate(Date sbPayDate) {
		this.sbPayDate = sbPayDate;
	}
	public BigDecimal getSbCurrCompanyMonth() {
		return sbCurrCompanyMonth;
	}
	public void setSbCurrCompanyMonth(BigDecimal sbCurrCompanyMonth) {
		this.sbCurrCompanyMonth = sbCurrCompanyMonth;
	}
	public BigDecimal getSbMonthPaybase() {
		return sbMonthPaybase;
	}
	public void setSbMonthPaybase(BigDecimal sbMonthPaybase) {
		this.sbMonthPaybase = sbMonthPaybase;
	}
	public BigDecimal getSbMonthPayment() {
		return sbMonthPayment;
	}
	public void setSbMonthPayment(BigDecimal sbMonthPayment) {
		this.sbMonthPayment = sbMonthPayment;
	}
	public String getSbCurrPaycompany() {
		return sbCurrPaycompany;
	}
	public void setSbCurrPaycompany(String sbCurrPaycompany) {
		this.sbCurrPaycompany = sbCurrPaycompany;
	}
	public String getGjjPayStatus() {
		return gjjPayStatus;
	}
	public void setGjjPayStatus(String gjjPayStatus) {
		this.gjjPayStatus = gjjPayStatus;
	}
	public BigDecimal getGjjMonthPayment() {
		return gjjMonthPayment;
	}
	public void setGjjMonthPayment(BigDecimal gjjMonthPayment) {
		this.gjjMonthPayment = gjjMonthPayment;
	}
	public BigDecimal getGjjPerPayrate() {
		return gjjPerPayrate;
	}
	public void setGjjPerPayrate(BigDecimal gjjPerPayrate) {
		this.gjjPerPayrate = gjjPerPayrate;
	}
	public Date getGjjPayDate() {
		return gjjPayDate;
	}
	public void setGjjPayDate(Date gjjPayDate) {
		this.gjjPayDate = gjjPayDate;
	}
	public String getGjjEndMonth() {
		return gjjEndMonth;
	}
	public void setGjjEndMonth(String gjjEndMonth) {
		this.gjjEndMonth = gjjEndMonth;
	}
	public String getEduType() {
		return eduType;
	}
	public void setEduType(String eduType) {
		this.eduType = eduType;
	}
	public String getEduModel() {
		return eduModel;
	}
	public void setEduModel(String eduModel) {
		this.eduModel = eduModel;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "FicoSdBlaze [houseInfoFrom=" + houseInfoFrom + ", houseCost=" + houseCost + ", houseArea=" + houseArea
				+ ", carCost=" + carCost + ", carHand=" + carHand + ", carOld=" + carOld + ", registerDate="
				+ registerDate + ", issueDate=" + issueDate + ", perOpendate=" + perOpendate + ", perAssetBalance="
				+ perAssetBalance + ", perLoanpri=" + perLoanpri + ", perPayWage=" + perPayWage + ", perFinAssetSum="
				+ perFinAssetSum + ", perLoanstatus=" + perLoanstatus + ", sbCurrPayStatus=" + sbCurrPayStatus
				+ ", sbPayDate=" + sbPayDate + ", sbCurrCompanyMonth=" + sbCurrCompanyMonth + ", sbMonthPaybase="
				+ sbMonthPaybase + ", sbMonthPayment=" + sbMonthPayment + ", sbCurrPaycompany=" + sbCurrPaycompany
				+ ", gjjPayStatus=" + gjjPayStatus + ", gjjMonthPayment=" + gjjMonthPayment + ", gjjPerPayrate="
				+ gjjPerPayrate + ", gjjPayDate=" + gjjPayDate + ", gjjEndMonth=" + gjjEndMonth + ", eduType=" + eduType
				+ ", eduModel=" + eduModel + ", jobCode=" + jobCode + ", industryCode=" + industryCode
				+ ", carCategroy=" + carCategroy + "]";
	}
}
