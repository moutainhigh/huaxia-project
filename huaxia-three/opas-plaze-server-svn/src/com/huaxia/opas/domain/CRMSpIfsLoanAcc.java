package com.huaxia.opas.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 第三方 & CRM & 个贷账户（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsLoanAcc extends CRM implements Serializable {

	private static final long serialVersionUID = -8090141755527868751L;

	// 客户ID
	private String custId;
	
	// 客户号
	private String custCode;
	
	// 贷款账号/公积金账号
	private String accNo;
	
	// 产品大类
	private String prodClass;
	
	// 产品子类
	private String prodType;
	
	// 贷款名称
	private String prodName;
	
	// 授信额度
	private BigDecimal credLimt;
	
	// 授信余额
	private BigDecimal usableLimit;
	
	// 贷款发放额
	private BigDecimal credGd;
	
	// 是否结清
	private String isSettl;
	
	// 贷款余额
	private BigDecimal loanBal;
	
	// 每月还款日期
	private String psDueDt;
	
	// 本月需还款金额
	private BigDecimal repayment;
	
	// 欠息余额
	private BigDecimal oweintBal;
	
	// 贷款期限
	private String loanTerm;
	
	// 贷款利率
	private BigDecimal loanRate;
	
	// 还款账号
	private String payAccNo;
	
	// 还款方式
	private String payType;
	
	// 贷款合同号
	private String loanContNo;
	
	// 贷款发放日
	private String isSueDt;
	
	// 贷款到期日
	private String endDate;
	
	// 利率浮动标识
	private String floatingType;
	
	// 利率浮动比例
	private BigDecimal floatingRate;
	
	// 是否逾期
	private String isDue;
	
	// 五级分类
	private String fiveClass;
	
	// 当前罚息
	private BigDecimal overdue;
	
	// 当期逾期期数
	private String overdueNum;
	
	// 累计逾期期数
	private String tOverdueNum;
	
	// 逾期天数
	private String overdueDay;
	
	// 担保方式
	private String guarType;
	
	// 质押存单号/房产证信息
	private String pledgeNo;
	
	// 抵押品价值
	private BigDecimal collCost;
	
	// 抵押率
	private BigDecimal collRate;
	
	// 首付款
	private BigDecimal firPay;
	
	// 贷款网点
	private String subBrId;
	
	// 所属分行
	private String brId;
	
	// 主办客户经理
	private String ownerId;
	
	// 账户状态
	private String accStatus;
	
	// 数据日期
	private String dDate;
	
	// 产品ID
	private String productId;
	
	// 开户日期
	private String openDate;
	
	// 贷款类型
	private String loanType;
	
	// 币种
	private String currency;
	
	// 人民币贷款余额
	private BigDecimal cnyLoanBal;
	
	// 子账号
	private String subAccNo;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getProdClass() {
		return prodClass;
	}

	public void setProdClass(String prodClass) {
		this.prodClass = prodClass;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public BigDecimal getCredLimt() {
		return credLimt;
	}

	public void setCredLimt(BigDecimal credLimt) {
		this.credLimt = credLimt;
	}

	public BigDecimal getUsableLimit() {
		return usableLimit;
	}

	public void setUsableLimit(BigDecimal usableLimit) {
		this.usableLimit = usableLimit;
	}

	public BigDecimal getCredGd() {
		return credGd;
	}

	public void setCredGd(BigDecimal credGd) {
		this.credGd = credGd;
	}

	public String getIsSettl() {
		return isSettl;
	}

	public void setIsSettl(String isSettl) {
		this.isSettl = isSettl;
	}

	public BigDecimal getLoanBal() {
		return loanBal;
	}

	public void setLoanBal(BigDecimal loanBal) {
		this.loanBal = loanBal;
	}

	public String getPsDueDt() {
		return psDueDt;
	}

	public void setPsDueDt(String psDueDt) {
		this.psDueDt = psDueDt;
	}

	public BigDecimal getRepayment() {
		return repayment;
	}

	public void setRepayment(BigDecimal repayment) {
		this.repayment = repayment;
	}

	public BigDecimal getOweintBal() {
		return oweintBal;
	}

	public void setOweintBal(BigDecimal oweintBal) {
		this.oweintBal = oweintBal;
	}

	public String getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}

	public BigDecimal getLoanRate() {
		return loanRate;
	}

	public void setLoanRate(BigDecimal loanRate) {
		this.loanRate = loanRate;
	}

	public String getPayAccNo() {
		return payAccNo;
	}

	public void setPayAccNo(String payAccNo) {
		this.payAccNo = payAccNo;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getLoanContNo() {
		return loanContNo;
	}

	public void setLoanContNo(String loanContNo) {
		this.loanContNo = loanContNo;
	}

	public String getIsSueDt() {
		return isSueDt;
	}

	public void setIsSueDt(String isSueDt) {
		this.isSueDt = isSueDt;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFloatingType() {
		return floatingType;
	}

	public void setFloatingType(String floatingType) {
		this.floatingType = floatingType;
	}

	public BigDecimal getFloatingRate() {
		return floatingRate;
	}

	public void setFloatingRate(BigDecimal floatingRate) {
		this.floatingRate = floatingRate;
	}

	public String getIsDue() {
		return isDue;
	}

	public void setIsDue(String isDue) {
		this.isDue = isDue;
	}

	public String getFiveClass() {
		return fiveClass;
	}

	public void setFiveClass(String fiveClass) {
		this.fiveClass = fiveClass;
	}

	public BigDecimal getOverdue() {
		return overdue;
	}

	public void setOverdue(BigDecimal overdue) {
		this.overdue = overdue;
	}

	public String getOverdueNum() {
		return overdueNum;
	}

	public void setOverdueNum(String overdueNum) {
		this.overdueNum = overdueNum;
	}

	public String gettOverdueNum() {
		return tOverdueNum;
	}

	public void settOverdueNum(String tOverdueNum) {
		this.tOverdueNum = tOverdueNum;
	}

	public String getOverdueDay() {
		return overdueDay;
	}

	public void setOverdueDay(String overdueDay) {
		this.overdueDay = overdueDay;
	}

	public String getGuarType() {
		return guarType;
	}

	public void setGuarType(String guarType) {
		this.guarType = guarType;
	}

	public String getPledgeNo() {
		return pledgeNo;
	}

	public void setPledgeNo(String pledgeNo) {
		this.pledgeNo = pledgeNo;
	}

	public BigDecimal getCollCost() {
		return collCost;
	}

	public void setCollCost(BigDecimal collCost) {
		this.collCost = collCost;
	}

	public BigDecimal getCollRate() {
		return collRate;
	}

	public void setCollRate(BigDecimal collRate) {
		this.collRate = collRate;
	}

	public BigDecimal getFirPay() {
		return firPay;
	}

	public void setFirPay(BigDecimal firPay) {
		this.firPay = firPay;
	}

	public String getSubBrId() {
		return subBrId;
	}

	public void setSubBrId(String subBrId) {
		this.subBrId = subBrId;
	}

	public String getBrId() {
		return brId;
	}

	public void setBrId(String brId) {
		this.brId = brId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getCnyLoanBal() {
		return cnyLoanBal;
	}

	public void setCnyLoanBal(BigDecimal cnyLoanBal) {
		this.cnyLoanBal = cnyLoanBal;
	}

	public String getSubAccNo() {
		return subAccNo;
	}

	public void setSubAccNo(String subAccNo) {
		this.subAccNo = subAccNo;
	}


}
