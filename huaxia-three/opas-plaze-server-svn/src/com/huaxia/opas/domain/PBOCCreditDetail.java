package com.huaxia.opas.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 第三方 & 人行 & 信贷交易信息明细
 * 
 * @author zhiguo.li
 *
 */
public class PBOCCreditDetail implements Serializable {

	private static final long serialVersionUID = -3850004884038451220L;

	// 资产处置信息
	private List<AssetDisposition> assetDispositionList;

	// 保证人代偿信息
	private List<AssurerRepay> assurerRepayList;

	// 贷款
	private List<Loan> loanList;

	// 贷记卡
	private List<Loancard> loancardList;

	// 准贷记卡
	private List<StandardLoancard> standardLoancardList;

	// 担保信息
	private List<GuaranteeInfo> guaranteeInfoList;

	public List<AssetDisposition> getAssetDispositionList() {
		return assetDispositionList;
	}

	public void setAssetDispositionList(List<AssetDisposition> assetDispositionList) {
		this.assetDispositionList = assetDispositionList;
	}

	public List<AssurerRepay> getAssurerRepayList() {
		return assurerRepayList;
	}

	public void setAssurerRepayList(List<AssurerRepay> assurerRepayList) {
		this.assurerRepayList = assurerRepayList;
	}

	public List<Loan> getLoanList() {
		return loanList;
	}

	public void setLoanList(List<Loan> loanList) {
		this.loanList = loanList;
	}

	public List<Loancard> getLoancardList() {
		return loancardList;
	}

	public void setLoancardList(List<Loancard> loancardList) {
		this.loancardList = loancardList;
	}

	public List<StandardLoancard> getStandardLoancardList() {
		return standardLoancardList;
	}

	public void setStandardLoancardList(List<StandardLoancard> standardLoancardList) {
		this.standardLoancardList = standardLoancardList;
	}

	public List<GuaranteeInfo> getGuaranteeInfoList() {
		return guaranteeInfoList;
	}

	public void setGuaranteeInfoList(List<GuaranteeInfo> guaranteeInfoList) {
		this.guaranteeInfoList = guaranteeInfoList;
	}

	public class AssetDisposition {

		// 资产管理公司
		private String organname;

		// 债务接收日期
		private String receiveTime;

		// 接收的债务金额
		private String money;

		// 最近一次还款日期
		private String latestRepayDate;

		// 余额
		private String balance;

		public String getOrganname() {
			return organname;
		}

		public void setOrganname(String organname) {
			this.organname = organname;
		}

		public String getReceiveTime() {
			return receiveTime;
		}

		public void setReceiveTime(String receiveTime) {
			this.receiveTime = receiveTime;
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public String getLatestRepayDate() {
			return latestRepayDate;
		}

		public void setLatestRepayDate(String latestRepayDate) {
			this.latestRepayDate = latestRepayDate;
		}

		public String getBalance() {
			return balance;
		}

		public void setBalance(String balance) {
			this.balance = balance;
		}

	}

	public class AssurerRepay {

		// 代偿机构
		private String organname;

		// 最近一次代偿日期
		private String latestAssurerRepayDate;

		// 累计代偿金额
		private String money;

		// 最近一次还款日期
		private String latestRepayDate;

		// 余额
		private String balance;

		public String getOrganname() {
			return organname;
		}

		public void setOrganname(String organname) {
			this.organname = organname;
		}

		public String getLatestAssurerRepayDate() {
			return latestAssurerRepayDate;
		}

		public void setLatestAssurerRepayDate(String latestAssurerRepayDate) {
			this.latestAssurerRepayDate = latestAssurerRepayDate;
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public String getLatestRepayDate() {
			return latestRepayDate;
		}

		public void setLatestRepayDate(String latestRepayDate) {
			this.latestRepayDate = latestRepayDate;
		}

		public String getBalance() {
			return balance;
		}

		public void setBalance(String balance) {
			this.balance = balance;
		}

	}

	public class Loan {

		// 基本信息提示
		private String cue;

		// 贷款的合同信息
		private ContractInfo contractInfo;

		// 当前状态
		private String state;

		// 当前账户信息
		private CurrAccountInfo currAccountInfo;

		// 当前逾期信息
		private CurrOverdue currOverdue;

		// 最近24个月还款状态
		private Latest24MonthPaymentState latest24MonthPaymentState;

		// 最近5年内的逾期记录
		private Latest5YearOverdueRecord latest5YearOverdueRecord;

		// 特殊交易信息
		private List<SpecialTrade> specialTradeList;

		// 与该笔业务相关的贷款机构说明
		private List<BankIlluminate> bankIlluminateList;

		// 异议标注
		private List<DissentInfo> dissentInfoList;

		// 本人声明
		private List<AnnounceInfo> announceInfoList;

		public String getCue() {
			return cue;
		}

		public void setCue(String cue) {
			this.cue = cue;
		}

		public ContractInfo getContractInfo() {
			return contractInfo;
		}

		public void setContractInfo(ContractInfo contractInfo) {
			this.contractInfo = contractInfo;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public CurrAccountInfo getCurrAccountInfo() {
			return currAccountInfo;
		}

		public void setCurrAccountInfo(CurrAccountInfo currAccountInfo) {
			this.currAccountInfo = currAccountInfo;
		}

		public CurrOverdue getCurrOverdue() {
			return currOverdue;
		}

		public void setCurrOverdue(CurrOverdue currOverdue) {
			this.currOverdue = currOverdue;
		}

		public Latest24MonthPaymentState getLatest24MonthPaymentState() {
			return latest24MonthPaymentState;
		}

		public void setLatest24MonthPaymentState(Latest24MonthPaymentState latest24MonthPaymentState) {
			this.latest24MonthPaymentState = latest24MonthPaymentState;
		}

		public Latest5YearOverdueRecord getLatest5YearOverdueRecord() {
			return latest5YearOverdueRecord;
		}

		public void setLatest5YearOverdueRecord(Latest5YearOverdueRecord latest5YearOverdueRecord) {
			this.latest5YearOverdueRecord = latest5YearOverdueRecord;
		}

		public List<SpecialTrade> getSpecialTradeList() {
			return specialTradeList;
		}

		public void setSpecialTradeList(List<SpecialTrade> specialTradeList) {
			this.specialTradeList = specialTradeList;
		}

		public List<BankIlluminate> getBankIlluminateList() {
			return bankIlluminateList;
		}

		public void setBankIlluminateList(List<BankIlluminate> bankIlluminateList) {
			this.bankIlluminateList = bankIlluminateList;
		}

		public List<DissentInfo> getDissentInfoList() {
			return dissentInfoList;
		}

		public void setDissentInfoList(List<DissentInfo> dissentInfoList) {
			this.dissentInfoList = dissentInfoList;
		}

		public List<AnnounceInfo> getAnnounceInfoList() {
			return announceInfoList;
		}

		public void setAnnounceInfoList(List<AnnounceInfo> announceInfoList) {
			this.announceInfoList = announceInfoList;
		}

		public class ContractInfo {

			// 贷款机构
			private String financeOrg;

			// 机构类型
			private String financeType;

			// 业务员
			private String account;

			// 贷款种类细分
			private String type;

			// 币种
			private String currency;

			// 发放日期
			private String openDate;

			// 到期日期
			private String endDate;

			// 合同金额
			private String creditLimitAmount;

			// 担保方式
			private String guaranteeType;

			// 还款频率
			private String paymentRating;

			// 还款期数
			private String paymentCyc;

			public String getFinanceOrg() {
				return financeOrg;
			}

			public void setFinanceOrg(String financeOrg) {
				this.financeOrg = financeOrg;
			}

			public String getFinanceType() {
				return financeType;
			}

			public void setFinanceType(String financeType) {
				this.financeType = financeType;
			}

			public String getAccount() {
				return account;
			}

			public void setAccount(String account) {
				this.account = account;
			}

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public String getCurrency() {
				return currency;
			}

			public void setCurrency(String currency) {
				this.currency = currency;
			}

			public String getOpenDate() {
				return openDate;
			}

			public void setOpenDate(String openDate) {
				this.openDate = openDate;
			}

			public String getEndDate() {
				return endDate;
			}

			public void setEndDate(String endDate) {
				this.endDate = endDate;
			}

			public String getCreditLimitAmount() {
				return creditLimitAmount;
			}

			public void setCreditLimitAmount(String creditLimitAmount) {
				this.creditLimitAmount = creditLimitAmount;
			}

			public String getGuaranteeType() {
				return guaranteeType;
			}

			public void setGuaranteeType(String guaranteeType) {
				this.guaranteeType = guaranteeType;
			}

			public String getPaymentRating() {
				return paymentRating;
			}

			public void setPaymentRating(String paymentRating) {
				this.paymentRating = paymentRating;
			}

			public String getPaymentCyc() {
				return paymentCyc;
			}

			public void setPaymentCyc(String paymentCyc) {
				this.paymentCyc = paymentCyc;
			}

		}

		public class CurrAccountInfo {

			// 状态截止日
			private String stateEndDate;

			// 状态截止月
			private String stateEndMonth;

			// 五级分类
			private String class5State;

			// 本金金额
			private String balance;

			// 剩余还款期数
			private String remainPaymentCyc;

			// 本月应还款
			private String scheduledPaymentAmount;

			// 应还款日
			private String scheduledPaymentDate;

			// 本月实还款
			private String actualPaymentAmount;

			// 最近一次还款日期
			private String recentPayDate;

			public String getStateEndDate() {
				return stateEndDate;
			}

			public void setStateEndDate(String stateEndDate) {
				this.stateEndDate = stateEndDate;
			}

			public String getStateEndMonth() {
				return stateEndMonth;
			}

			public void setStateEndMonth(String stateEndMonth) {
				this.stateEndMonth = stateEndMonth;
			}

			public String getClass5State() {
				return class5State;
			}

			public void setClass5State(String class5State) {
				this.class5State = class5State;
			}

			public String getBalance() {
				return balance;
			}

			public void setBalance(String balance) {
				this.balance = balance;
			}

			public String getRemainPaymentCyc() {
				return remainPaymentCyc;
			}

			public void setRemainPaymentCyc(String remainPaymentCyc) {
				this.remainPaymentCyc = remainPaymentCyc;
			}

			public String getScheduledPaymentAmount() {
				return scheduledPaymentAmount;
			}

			public void setScheduledPaymentAmount(String scheduledPaymentAmount) {
				this.scheduledPaymentAmount = scheduledPaymentAmount;
			}

			public String getScheduledPaymentDate() {
				return scheduledPaymentDate;
			}

			public void setScheduledPaymentDate(String scheduledPaymentDate) {
				this.scheduledPaymentDate = scheduledPaymentDate;
			}

			public String getActualPaymentAmount() {
				return actualPaymentAmount;
			}

			public void setActualPaymentAmount(String actualPaymentAmount) {
				this.actualPaymentAmount = actualPaymentAmount;
			}

			public String getRecentPayDate() {
				return recentPayDate;
			}

			public void setRecentPayDate(String recentPayDate) {
				this.recentPayDate = recentPayDate;
			}

		}

		public class CurrOverdue {

			// 当前逾期期数
			private String currOverdueCyc;

			// 当前逾期金额
			private String currOverdueAmount;

			// 逾期31-60天未归还贷款本金
			private String overdue31To60Amount;

			// 逾期61-90天未归还贷款本金
			private String overdue61To90Amount;

			// 逾期91-180天未归还贷款本金
			private String overdue91To180Amount;

			// 逾期180天以上未归还贷款本金
			private String overdueOver180Amount;

			public String getCurrOverdueCyc() {
				return currOverdueCyc;
			}

			public void setCurrOverdueCyc(String currOverdueCyc) {
				this.currOverdueCyc = currOverdueCyc;
			}

			public String getCurrOverdueAmount() {
				return currOverdueAmount;
			}

			public void setCurrOverdueAmount(String currOverdueAmount) {
				this.currOverdueAmount = currOverdueAmount;
			}

			public String getOverdue31To60Amount() {
				return overdue31To60Amount;
			}

			public void setOverdue31To60Amount(String overdue31To60Amount) {
				this.overdue31To60Amount = overdue31To60Amount;
			}

			public String getOverdue61To90Amount() {
				return overdue61To90Amount;
			}

			public void setOverdue61To90Amount(String overdue61To90Amount) {
				this.overdue61To90Amount = overdue61To90Amount;
			}

			public String getOverdue91To180Amount() {
				return overdue91To180Amount;
			}

			public void setOverdue91To180Amount(String overdue91To180Amount) {
				this.overdue91To180Amount = overdue91To180Amount;
			}

			public String getOverdueOver180Amount() {
				return overdueOver180Amount;
			}

			public void setOverdueOver180Amount(String overdueOver180Amount) {
				this.overdueOver180Amount = overdueOver180Amount;
			}

		}

		public class Latest24MonthPaymentState {

			// 起始月
			private String beginMonth;

			// 截止月
			private String endMonth;

			// 最近24个月还款状态
			private String latest24State;

			public String getBeginMonth() {
				return beginMonth;
			}

			public void setBeginMonth(String beginMonth) {
				this.beginMonth = beginMonth;
			}

			public String getEndMonth() {
				return endMonth;
			}

			public void setEndMonth(String endMonth) {
				this.endMonth = endMonth;
			}

			public String getLatest24State() {
				return latest24State;
			}

			public void setLatest24State(String latest24State) {
				this.latest24State = latest24State;
			}

		}

		public class Latest5YearOverdueRecord {

			// 起始月
			private String beginMonth;

			// 截止月
			private String endMonth;

			// 逾期记录明细
			private List<OverdueRecord> overdueRecordList;

			public String getBeginMonth() {
				return beginMonth;
			}

			public void setBeginMonth(String beginMonth) {
				this.beginMonth = beginMonth;
			}

			public String getEndMonth() {
				return endMonth;
			}

			public void setEndMonth(String endMonth) {
				this.endMonth = endMonth;
			}

			public List<OverdueRecord> getOverdueRecordList() {
				return overdueRecordList;
			}

			public void setOverdueRecordList(List<OverdueRecord> overdueRecordList) {
				this.overdueRecordList = overdueRecordList;
			}

			public class OverdueRecord {

				// 月份
				private String month;

				// 持续月数
				private String lastMonths;

				// 金额
				private String amount;

				public String getMonth() {
					return month;
				}

				public void setMonth(String month) {
					this.month = month;
				}

				public String getLastMonths() {
					return lastMonths;
				}

				public void setLastMonths(String lastMonths) {
					this.lastMonths = lastMonths;
				}

				public String getAmount() {
					return amount;
				}

				public void setAmount(String amount) {
					this.amount = amount;
				}

			}

		}

		public class SpecialTrade {

			// 类型
			private String type;

			// 发生日期
			private String getTime;

			// 变更月数
			private String changingMonths;

			// 发生金额
			private String changingAmount;

			// 明细记录
			private String content;

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public String getGetTime() {
				return getTime;
			}

			public void setGetTime(String getTime) {
				this.getTime = getTime;
			}

			public String getChangingMonths() {
				return changingMonths;
			}

			public void setChangingMonths(String changingMonths) {
				this.changingMonths = changingMonths;
			}

			public String getChangingAmount() {
				return changingAmount;
			}

			public void setChangingAmount(String changingAmount) {
				this.changingAmount = changingAmount;
			}

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

		}

		public class BankIlluminate {

			// 机构说明
			private String content;

			// 添加日期
			private String getTime;

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public String getGetTime() {
				return getTime;
			}

			public void setGetTime(String getTime) {
				this.getTime = getTime;
			}

		}

		public class DissentInfo {

			// 标注内容
			private String content;

			// 添加日期
			private String getTime;

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public String getGetTime() {
				return getTime;
			}

			public void setGetTime(String getTime) {
				this.getTime = getTime;
			}

		}

		public class AnnounceInfo {

			// 声明内容
			private String content;

			// 添加日期
			private String getTime;

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public String getGetTime() {
				return getTime;
			}

			public void setGetTime(String getTime) {
				this.getTime = getTime;
			}

		}
	}

	public class Loancard {

		// 基本信息提示
		private String cue;

		// 授信情况
		private AwardCreditInfo awardCreditInfo;

		// 账户状态
		private String state;

		// 使用/还款情况
		private RepayInfo repayInfo;

		// 当前逾期信息
		private CurrOverdue currOverdue;

		// 最近24个月还款状态
		private Latest24MonthPaymentState latest24MonthPaymentState;

		// 最近5年内的逾期记录
		private Latest5YearOverdueRecord latest5YearOverdueRecord;

		// 特殊交易信息
		private List<SpecialTrade> specialTradeList;

		// 与该笔业务相关的贷款机构说明
		private List<BankIlluminate> bankIlluminateList;

		// 异议标注
		private List<DissentInfo> dissentInfoList;

		// 本人声明
		private List<AnnounceInfo> announceInfoList;

		public String getCue() {
			return cue;
		}

		public void setCue(String cue) {
			this.cue = cue;
		}

		public AwardCreditInfo getAwardCreditInfo() {
			return awardCreditInfo;
		}

		public void setAwardCreditInfo(AwardCreditInfo awardCreditInfo) {
			this.awardCreditInfo = awardCreditInfo;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public RepayInfo getRepayInfo() {
			return repayInfo;
		}

		public void setRepayInfo(RepayInfo repayInfo) {
			this.repayInfo = repayInfo;
		}

		public CurrOverdue getCurrOverdue() {
			return currOverdue;
		}

		public void setCurrOverdue(CurrOverdue currOverdue) {
			this.currOverdue = currOverdue;
		}

		public Latest24MonthPaymentState getLatest24MonthPaymentState() {
			return latest24MonthPaymentState;
		}

		public void setLatest24MonthPaymentState(Latest24MonthPaymentState latest24MonthPaymentState) {
			this.latest24MonthPaymentState = latest24MonthPaymentState;
		}

		public Latest5YearOverdueRecord getLatest5YearOverdueRecord() {
			return latest5YearOverdueRecord;
		}

		public void setLatest5YearOverdueRecord(Latest5YearOverdueRecord latest5YearOverdueRecord) {
			this.latest5YearOverdueRecord = latest5YearOverdueRecord;
		}

		public List<SpecialTrade> getSpecialTradeList() {
			return specialTradeList;
		}

		public void setSpecialTradeList(List<SpecialTrade> specialTradeList) {
			this.specialTradeList = specialTradeList;
		}

		public List<BankIlluminate> getBankIlluminateList() {
			return bankIlluminateList;
		}

		public void setBankIlluminateList(List<BankIlluminate> bankIlluminateList) {
			this.bankIlluminateList = bankIlluminateList;
		}

		public List<DissentInfo> getDissentInfoList() {
			return dissentInfoList;
		}

		public void setDissentInfoList(List<DissentInfo> dissentInfoList) {
			this.dissentInfoList = dissentInfoList;
		}

		public List<AnnounceInfo> getAnnounceInfoList() {
			return announceInfoList;
		}

		public void setAnnounceInfoList(List<AnnounceInfo> announceInfoList) {
			this.announceInfoList = announceInfoList;
		}

		public class AwardCreditInfo {

			// 贷款机构
			private String financeOrg;

			// 机构类型
			private String financeType;

			// 业务号
			private String account;

			// 贷款种类细分
			private String type;

			// 币种
			private String currency;

			// 发放日期
			private String openDate;

			// 到期日期
			private String endDate;

			// 合同金额
			private String creditLimitAmount;

			// 担保方式
			private String guaranteeType;

			// 还款频率
			private String paymentRating;

			// 还款期数
			private String paymentCyc;

			public String getFinanceOrg() {
				return financeOrg;
			}

			public void setFinanceOrg(String financeOrg) {
				this.financeOrg = financeOrg;
			}

			public String getFinanceType() {
				return financeType;
			}

			public void setFinanceType(String financeType) {
				this.financeType = financeType;
			}

			public String getAccount() {
				return account;
			}

			public void setAccount(String account) {
				this.account = account;
			}

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public String getCurrency() {
				return currency;
			}

			public void setCurrency(String currency) {
				this.currency = currency;
			}

			public String getOpenDate() {
				return openDate;
			}

			public void setOpenDate(String openDate) {
				this.openDate = openDate;
			}

			public String getEndDate() {
				return endDate;
			}

			public void setEndDate(String endDate) {
				this.endDate = endDate;
			}

			public String getCreditLimitAmount() {
				return creditLimitAmount;
			}

			public void setCreditLimitAmount(String creditLimitAmount) {
				this.creditLimitAmount = creditLimitAmount;
			}

			public String getGuaranteeType() {
				return guaranteeType;
			}

			public void setGuaranteeType(String guaranteeType) {
				this.guaranteeType = guaranteeType;
			}

			public String getPaymentRating() {
				return paymentRating;
			}

			public void setPaymentRating(String paymentRating) {
				this.paymentRating = paymentRating;
			}

			public String getPaymentCyc() {
				return paymentCyc;
			}

			public void setPaymentCyc(String paymentCyc) {
				this.paymentCyc = paymentCyc;
			}

		}

		public class RepayInfo {

			// 状态截止日
			private String stateEndDate;

			// 状态截止月
			private String stateEndMonth;

			// 共享额度
			private String shareCreditLimitAmount;

			// 已用额度/透支余额
			private String usedCreditLimitAmount;

			// 最近6个月平均使用额度/最近6个月平均透支余额
			private String latest6MonthUsedAvgAmount;

			// 最大使用额度/最大透支余额
			private String usedHighestAmount;

			// 账单日
			private String ScheduledPaymentDate;

			// 本月应还款
			private String scheduledPaymentAmount;

			// 本月实还款
			private String actualPaymentAmount;

			// 担保金额
			private String guananteeMoney;

			// 最近一次还款日期
			private String recentPayDate;

			public String getStateEndDate() {
				return stateEndDate;
			}

			public void setStateEndDate(String stateEndDate) {
				this.stateEndDate = stateEndDate;
			}

			public String getStateEndMonth() {
				return stateEndMonth;
			}

			public void setStateEndMonth(String stateEndMonth) {
				this.stateEndMonth = stateEndMonth;
			}

			public String getShareCreditLimitAmount() {
				return shareCreditLimitAmount;
			}

			public void setShareCreditLimitAmount(String shareCreditLimitAmount) {
				this.shareCreditLimitAmount = shareCreditLimitAmount;
			}

			public String getUsedCreditLimitAmount() {
				return usedCreditLimitAmount;
			}

			public void setUsedCreditLimitAmount(String usedCreditLimitAmount) {
				this.usedCreditLimitAmount = usedCreditLimitAmount;
			}

			public String getLatest6MonthUsedAvgAmount() {
				return latest6MonthUsedAvgAmount;
			}

			public void setLatest6MonthUsedAvgAmount(String latest6MonthUsedAvgAmount) {
				this.latest6MonthUsedAvgAmount = latest6MonthUsedAvgAmount;
			}

			public String getUsedHighestAmount() {
				return usedHighestAmount;
			}

			public void setUsedHighestAmount(String usedHighestAmount) {
				this.usedHighestAmount = usedHighestAmount;
			}

			public String getScheduledPaymentDate() {
				return ScheduledPaymentDate;
			}

			public void setScheduledPaymentDate(String scheduledPaymentDate) {
				ScheduledPaymentDate = scheduledPaymentDate;
			}

			public String getScheduledPaymentAmount() {
				return scheduledPaymentAmount;
			}

			public void setScheduledPaymentAmount(String scheduledPaymentAmount) {
				this.scheduledPaymentAmount = scheduledPaymentAmount;
			}

			public String getActualPaymentAmount() {
				return actualPaymentAmount;
			}

			public void setActualPaymentAmount(String actualPaymentAmount) {
				this.actualPaymentAmount = actualPaymentAmount;
			}

			public String getGuananteeMoney() {
				return guananteeMoney;
			}

			public void setGuananteeMoney(String guananteeMoney) {
				this.guananteeMoney = guananteeMoney;
			}

			public String getRecentPayDate() {
				return recentPayDate;
			}

			public void setRecentPayDate(String recentPayDate) {
				this.recentPayDate = recentPayDate;
			}

		}

		public class CurrOverdue {

			// 当前逾期期数
			private String currOverdueCyc;

			// 当前逾期金额
			private String currOverdueAmount;

			// 逾期31-60天未归还贷款本金
			private String overdue31To60Amount;

			// 逾期61-90天未归还贷款本金
			private String overdue61To90Amount;

			// 逾期91-180天未归还贷款本金
			private String overdue91To180Amount;

			// 逾期180天以上未归还贷款本金
			private String overdueOver180Amount;

			public String getCurrOverdueCyc() {
				return currOverdueCyc;
			}

			public void setCurrOverdueCyc(String currOverdueCyc) {
				this.currOverdueCyc = currOverdueCyc;
			}

			public String getCurrOverdueAmount() {
				return currOverdueAmount;
			}

			public void setCurrOverdueAmount(String currOverdueAmount) {
				this.currOverdueAmount = currOverdueAmount;
			}

			public String getOverdue31To60Amount() {
				return overdue31To60Amount;
			}

			public void setOverdue31To60Amount(String overdue31To60Amount) {
				this.overdue31To60Amount = overdue31To60Amount;
			}

			public String getOverdue61To90Amount() {
				return overdue61To90Amount;
			}

			public void setOverdue61To90Amount(String overdue61To90Amount) {
				this.overdue61To90Amount = overdue61To90Amount;
			}

			public String getOverdue91To180Amount() {
				return overdue91To180Amount;
			}

			public void setOverdue91To180Amount(String overdue91To180Amount) {
				this.overdue91To180Amount = overdue91To180Amount;
			}

			public String getOverdueOver180Amount() {
				return overdueOver180Amount;
			}

			public void setOverdueOver180Amount(String overdueOver180Amount) {
				this.overdueOver180Amount = overdueOver180Amount;
			}

		}

		public class Latest24MonthPaymentState {

			// 起始月
			private String beginMonth;

			// 截止月
			private String endMonth;

			// 最近24个月还款状态
			private String latest24State;

			public String getBeginMonth() {
				return beginMonth;
			}

			public void setBeginMonth(String beginMonth) {
				this.beginMonth = beginMonth;
			}

			public String getEndMonth() {
				return endMonth;
			}

			public void setEndMonth(String endMonth) {
				this.endMonth = endMonth;
			}

			public String getLatest24State() {
				return latest24State;
			}

			public void setLatest24State(String latest24State) {
				this.latest24State = latest24State;
			}

		}

		public class Latest5YearOverdueRecord {

			// 起始月
			private String beginMonth;

			// 截止月
			private String endMonth;

			// 逾期记录明细
			private List<OverdueRecord> overdueRecordList;

			public String getBeginMonth() {
				return beginMonth;
			}

			public void setBeginMonth(String beginMonth) {
				this.beginMonth = beginMonth;
			}

			public String getEndMonth() {
				return endMonth;
			}

			public void setEndMonth(String endMonth) {
				this.endMonth = endMonth;
			}

			public List<OverdueRecord> getOverdueRecordList() {
				return overdueRecordList;
			}

			public void setOverdueRecordList(List<OverdueRecord> overdueRecordList) {
				this.overdueRecordList = overdueRecordList;
			}

			public class OverdueRecord {

				// 月份
				private String month;

				// 持续月数
				private String lastMonths;

				// 金额
				private String amount;

				public String getMonth() {
					return month;
				}

				public void setMonth(String month) {
					this.month = month;
				}

				public String getLastMonths() {
					return lastMonths;
				}

				public void setLastMonths(String lastMonths) {
					this.lastMonths = lastMonths;
				}

				public String getAmount() {
					return amount;
				}

				public void setAmount(String amount) {
					this.amount = amount;
				}

			}

		}

		public class SpecialTrade {

			// 类型
			private String type;

			// 发生日期
			private String getTime;

			// 变更月数
			private String changingMonths;

			// 发生金额
			private String changingAmount;

			// 明细记录
			private String content;

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public String getGetTime() {
				return getTime;
			}

			public void setGetTime(String getTime) {
				this.getTime = getTime;
			}

			public String getChangingMonths() {
				return changingMonths;
			}

			public void setChangingMonths(String changingMonths) {
				this.changingMonths = changingMonths;
			}

			public String getChangingAmount() {
				return changingAmount;
			}

			public void setChangingAmount(String changingAmount) {
				this.changingAmount = changingAmount;
			}

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

		}

		public class BankIlluminate {

			// 机构说明
			private String content;

			// 添加日期
			private String getTime;

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public String getGetTime() {
				return getTime;
			}

			public void setGetTime(String getTime) {
				this.getTime = getTime;
			}

		}

		public class DissentInfo {

			// 标注内容
			private String content;

			// 添加日期
			private String getTime;

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public String getGetTime() {
				return getTime;
			}

			public void setGetTime(String getTime) {
				this.getTime = getTime;
			}

		}

		public class AnnounceInfo {

			// 声明内容
			private String content;

			// 添加日期
			private String getTime;

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public String getGetTime() {
				return getTime;
			}

			public void setGetTime(String getTime) {
				this.getTime = getTime;
			}

		}

	}

	public class StandardLoancard {

		// 基本信息提示
		private String cue;

		// 贷记卡授信情况
		private AwardCreditInfo awardCreditInfo;

		// 账户状态
		private String state;

		// 使用/还款情况
		private RepayInfo repayInfo;

		// 当前逾期信息
		private CurrOverdue currOverdue;

		// 最近24个月还款状态
		private Latest24MonthPaymentState latest24MonthPaymentState;

		// 最近5年内的逾期记录
		private Latest5YearOverdueRecord latest5YearOverdueRecord;

		// 特殊交易信息
		private List<SpecialTrade> specialTradeList;

		// 与该笔业务相关的贷款机构说明
		private List<BankIlluminate> bankIlluminateList;

		// 异议标注
		private List<DissentInfo> dissentInfoList;

		// 本人声明
		private List<AnnounceInfo> announceInfoList;

		public String getCue() {
			return cue;
		}

		public void setCue(String cue) {
			this.cue = cue;
		}

		public AwardCreditInfo getAwardCreditInfo() {
			return awardCreditInfo;
		}

		public void setAwardCreditInfo(AwardCreditInfo awardCreditInfo) {
			this.awardCreditInfo = awardCreditInfo;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public RepayInfo getRepayInfo() {
			return repayInfo;
		}

		public void setRepayInfo(RepayInfo repayInfo) {
			this.repayInfo = repayInfo;
		}

		public CurrOverdue getCurrOverdue() {
			return currOverdue;
		}

		public void setCurrOverdue(CurrOverdue currOverdue) {
			this.currOverdue = currOverdue;
		}

		public Latest24MonthPaymentState getLatest24MonthPaymentState() {
			return latest24MonthPaymentState;
		}

		public void setLatest24MonthPaymentState(Latest24MonthPaymentState latest24MonthPaymentState) {
			this.latest24MonthPaymentState = latest24MonthPaymentState;
		}

		public Latest5YearOverdueRecord getLatest5YearOverdueRecord() {
			return latest5YearOverdueRecord;
		}

		public void setLatest5YearOverdueRecord(Latest5YearOverdueRecord latest5YearOverdueRecord) {
			this.latest5YearOverdueRecord = latest5YearOverdueRecord;
		}

		public List<SpecialTrade> getSpecialTradeList() {
			return specialTradeList;
		}

		public void setSpecialTradeList(List<SpecialTrade> specialTradeList) {
			this.specialTradeList = specialTradeList;
		}

		public List<BankIlluminate> getBankIlluminateList() {
			return bankIlluminateList;
		}

		public void setBankIlluminateList(List<BankIlluminate> bankIlluminateList) {
			this.bankIlluminateList = bankIlluminateList;
		}

		public List<DissentInfo> getDissentInfoList() {
			return dissentInfoList;
		}

		public void setDissentInfoList(List<DissentInfo> dissentInfoList) {
			this.dissentInfoList = dissentInfoList;
		}

		public List<AnnounceInfo> getAnnounceInfoList() {
			return announceInfoList;
		}

		public void setAnnounceInfoList(List<AnnounceInfo> announceInfoList) {
			this.announceInfoList = announceInfoList;
		}

		public class AwardCreditInfo {

			// 发卡机构
			private String financeOrg;

			// 机构类型
			private String financeType;

			// 业务号
			private String account;

			// 币种
			private String currency;

			// 发卡日期
			private String openDate;

			// 授信额度
			private String creditLimitAmount;

			// 担保方式
			private String guaranteeType;

			public String getFinanceOrg() {
				return financeOrg;
			}

			public void setFinanceOrg(String financeOrg) {
				this.financeOrg = financeOrg;
			}

			public String getFinanceType() {
				return financeType;
			}

			public void setFinanceType(String financeType) {
				this.financeType = financeType;
			}

			public String getAccount() {
				return account;
			}

			public void setAccount(String account) {
				this.account = account;
			}

			public String getCurrency() {
				return currency;
			}

			public void setCurrency(String currency) {
				this.currency = currency;
			}

			public String getOpenDate() {
				return openDate;
			}

			public void setOpenDate(String openDate) {
				this.openDate = openDate;
			}

			public String getCreditLimitAmount() {
				return creditLimitAmount;
			}

			public void setCreditLimitAmount(String creditLimitAmount) {
				this.creditLimitAmount = creditLimitAmount;
			}

			public String getGuaranteeType() {
				return guaranteeType;
			}

			public void setGuaranteeType(String guaranteeType) {
				this.guaranteeType = guaranteeType;
			}

		}

		public class RepayInfo {

			// 状态截止日
			private String stateEndDate;

			// 状态截止月
			private String stateEndMonth;

			// 共享额度
			private String shareCreditLimitAmount;

			// 已用额度/透支余额
			private String usedCreditLimitAmount;

			// 最近6个月平均使用额度/最近6个月平均透支余额
			private String latest6MonthUsedAvgAmount;

			// 最大使用额度/最大透支余额
			private String usedHighestAmount;

			// 账单日
			private String scheduledPaymentDate;

			// 本月应还款
			private String scheduledPaymentAmount;

			// 本月实还款
			private String actualPaymentAmount;

			// 担保金额
			private String guananteeMoney;

			// 最后一次还款日期
			private String recentPayDate;

			public String getStateEndDate() {
				return stateEndDate;
			}

			public void setStateEndDate(String stateEndDate) {
				this.stateEndDate = stateEndDate;
			}

			public String getStateEndMonth() {
				return stateEndMonth;
			}

			public void setStateEndMonth(String stateEndMonth) {
				this.stateEndMonth = stateEndMonth;
			}

			public String getShareCreditLimitAmount() {
				return shareCreditLimitAmount;
			}

			public void setShareCreditLimitAmount(String shareCreditLimitAmount) {
				this.shareCreditLimitAmount = shareCreditLimitAmount;
			}

			public String getUsedCreditLimitAmount() {
				return usedCreditLimitAmount;
			}

			public void setUsedCreditLimitAmount(String usedCreditLimitAmount) {
				this.usedCreditLimitAmount = usedCreditLimitAmount;
			}

			public String getLatest6MonthUsedAvgAmount() {
				return latest6MonthUsedAvgAmount;
			}

			public void setLatest6MonthUsedAvgAmount(String latest6MonthUsedAvgAmount) {
				this.latest6MonthUsedAvgAmount = latest6MonthUsedAvgAmount;
			}

			public String getUsedHighestAmount() {
				return usedHighestAmount;
			}

			public void setUsedHighestAmount(String usedHighestAmount) {
				this.usedHighestAmount = usedHighestAmount;
			}

			public String getScheduledPaymentDate() {
				return scheduledPaymentDate;
			}

			public void setScheduledPaymentDate(String scheduledPaymentDate) {
				this.scheduledPaymentDate = scheduledPaymentDate;
			}

			public String getScheduledPaymentAmount() {
				return scheduledPaymentAmount;
			}

			public void setScheduledPaymentAmount(String scheduledPaymentAmount) {
				this.scheduledPaymentAmount = scheduledPaymentAmount;
			}

			public String getActualPaymentAmount() {
				return actualPaymentAmount;
			}

			public void setActualPaymentAmount(String actualPaymentAmount) {
				this.actualPaymentAmount = actualPaymentAmount;
			}

			public String getGuananteeMoney() {
				return guananteeMoney;
			}

			public void setGuananteeMoney(String guananteeMoney) {
				this.guananteeMoney = guananteeMoney;
			}

			public String getRecentPayDate() {
				return recentPayDate;
			}

			public void setRecentPayDate(String recentPayDate) {
				this.recentPayDate = recentPayDate;
			}

		}

		public class CurrOverdue {

			// 当前逾期期数
			private String currOverdueCyc;

			// 当前逾期金额
			private String currOverdueAmount;

			// 逾期31-60天未归还贷款本金
			private String overdue31To60Amount;

			// 逾期61-90天未归还贷款本金
			private String overdue61To90Amount;

			// 逾期91-180天未归还贷款本金
			private String overdue91To180Amount;

			// 逾期180天以上未归还贷款本金
			private String overdueOver180Amount;

			public String getCurrOverdueCyc() {
				return currOverdueCyc;
			}

			public void setCurrOverdueCyc(String currOverdueCyc) {
				this.currOverdueCyc = currOverdueCyc;
			}

			public String getCurrOverdueAmount() {
				return currOverdueAmount;
			}

			public void setCurrOverdueAmount(String currOverdueAmount) {
				this.currOverdueAmount = currOverdueAmount;
			}

			public String getOverdue31To60Amount() {
				return overdue31To60Amount;
			}

			public void setOverdue31To60Amount(String overdue31To60Amount) {
				this.overdue31To60Amount = overdue31To60Amount;
			}

			public String getOverdue61To90Amount() {
				return overdue61To90Amount;
			}

			public void setOverdue61To90Amount(String overdue61To90Amount) {
				this.overdue61To90Amount = overdue61To90Amount;
			}

			public String getOverdue91To180Amount() {
				return overdue91To180Amount;
			}

			public void setOverdue91To180Amount(String overdue91To180Amount) {
				this.overdue91To180Amount = overdue91To180Amount;
			}

			public String getOverdueOver180Amount() {
				return overdueOver180Amount;
			}

			public void setOverdueOver180Amount(String overdueOver180Amount) {
				this.overdueOver180Amount = overdueOver180Amount;
			}

		}

		public class Latest24MonthPaymentState {

			// 起始月
			private String beginMonth;

			// 截止月
			private String endMonth;

			// 最近24个月还款状态
			private String latest24State;

			// 还款记录明细
			private List<State> stateList;

			public String getBeginMonth() {
				return beginMonth;
			}

			public void setBeginMonth(String beginMonth) {
				this.beginMonth = beginMonth;
			}

			public String getEndMonth() {
				return endMonth;
			}

			public void setEndMonth(String endMonth) {
				this.endMonth = endMonth;
			}

			public String getLatest24State() {
				return latest24State;
			}

			public void setLatest24State(String latest24State) {
				this.latest24State = latest24State;
			}

			public List<State> getStateList() {
				return stateList;
			}

			public void setStateList(List<State> stateList) {
				this.stateList = stateList;
			}

		}

		public class State {

			// 还款月
			private String payMonth;

			// 该月还款状态
			private String payState;

			public String getPayMonth() {
				return payMonth;
			}

			public void setPayMonth(String payMonth) {
				this.payMonth = payMonth;
			}

			public String getPayState() {
				return payState;
			}

			public void setPayState(String payState) {
				this.payState = payState;
			}

		}

		public class Latest5YearOverdueRecord {

			// 起始月
			private String beginMonth;

			// 截止月
			private String endMonth;

			// 逾期记录明细
			private List<OverdueRecord> overdueRecordList;

			public String getBeginMonth() {
				return beginMonth;
			}

			public void setBeginMonth(String beginMonth) {
				this.beginMonth = beginMonth;
			}

			public String getEndMonth() {
				return endMonth;
			}

			public void setEndMonth(String endMonth) {
				this.endMonth = endMonth;
			}

			public List<OverdueRecord> getOverdueRecordList() {
				return overdueRecordList;
			}

			public void setOverdueRecordList(List<OverdueRecord> overdueRecordList) {
				this.overdueRecordList = overdueRecordList;
			}

			public class OverdueRecord {

				// 月份
				private String month;

				// 持续月数
				private Integer lastMonths;

				// 金额
				private String amount;

				public String getMonth() {
					return month;
				}

				public void setMonth(String month) {
					this.month = month;
				}

				public Integer getLastMonths() {
					return lastMonths;
				}

				public void setLastMonths(Integer lastMonths) {
					this.lastMonths = lastMonths;
				}

				public String getAmount() {
					return amount;
				}

				public void setAmount(String amount) {
					this.amount = amount;
				}

			}

		}

		public class SpecialTrade {

			// 类型
			private String type;

			// 发生日期
			private String getTime;

			// 变更月数
			private String changingMonths;

			// 发生金额
			private String changingAmount;

			// 明细记录
			private String content;

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public String getGetTime() {
				return getTime;
			}

			public void setGetTime(String getTime) {
				this.getTime = getTime;
			}

			public String getChangingMonths() {
				return changingMonths;
			}

			public void setChangingMonths(String changingMonths) {
				this.changingMonths = changingMonths;
			}

			public String getChangingAmount() {
				return changingAmount;
			}

			public void setChangingAmount(String changingAmount) {
				this.changingAmount = changingAmount;
			}

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

		}

		public class BankIlluminate {

			// 机构说明
			private String content;

			// 添加日期
			private String getTime;

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public String getGetTime() {
				return getTime;
			}

			public void setGetTime(String getTime) {
				this.getTime = getTime;
			}

		}

		public class DissentInfo {

			// 标注内容
			private String content;

			// 添加日期
			private String getTime;

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public String getGetTime() {
				return getTime;
			}

			public void setGetTime(String getTime) {
				this.getTime = getTime;
			}

		}

		public class AnnounceInfo {

			// 声明内容
			private String content;

			// 添加日期
			private String getTime;

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			public String getGetTime() {
				return getTime;
			}

			public void setGetTime(String getTime) {
				this.getTime = getTime;
			}

		}

	}
	
	public class GuaranteeInfo {
		
		// 担保类型
		private String guaranteeFormat;
		
		// 担保信息
		private List<Guarantee> guaranteeList;

		public String getGuaranteeFormat() {
			return guaranteeFormat;
		}

		public void setGuaranteeFormat(String guaranteeFormat) {
			this.guaranteeFormat = guaranteeFormat;
		}
		
		public List<Guarantee> getGuaranteeList() {
			return guaranteeList;
		}

		public void setGuaranteeList(List<Guarantee> guaranteeList) {
			this.guaranteeList = guaranteeList;
		}

		public class Guarantee {
			
			// 担保贷款发放机构
			private String organname;
			
			// 担保贷款合同金额
			private String contractMoney;
			
			// 担保贷款发放日期
			private String beginDate;
			
			// 担保贷款到期日期
			private String endDate;
			
			// 担保金额
			private String guaranteeMoney;
			
			// 担保贷款本金金额
			private String guaranteeBalance;
			
			// 担保贷款五级分类
			private String class5State;
			
			// 结算日期
			private String billingDate;

			public String getOrganname() {
				return organname;
			}

			public void setOrganname(String organname) {
				this.organname = organname;
			}

			public String getContractMoney() {
				return contractMoney;
			}

			public void setContractMoney(String contractMoney) {
				this.contractMoney = contractMoney;
			}

			public String getBeginDate() {
				return beginDate;
			}

			public void setBeginDate(String beginDate) {
				this.beginDate = beginDate;
			}

			public String getEndDate() {
				return endDate;
			}

			public void setEndDate(String endDate) {
				this.endDate = endDate;
			}

			public String getGuaranteeMoney() {
				return guaranteeMoney;
			}

			public void setGuaranteeMoney(String guaranteeMoney) {
				this.guaranteeMoney = guaranteeMoney;
			}

			public String getGuaranteeBalance() {
				return guaranteeBalance;
			}

			public void setGuaranteeBalance(String guaranteeBalance) {
				this.guaranteeBalance = guaranteeBalance;
			}

			public String getClass5State() {
				return class5State;
			}

			public void setClass5State(String class5State) {
				this.class5State = class5State;
			}

			public String getBillingDate() {
				return billingDate;
			}

			public void setBillingDate(String billingDate) {
				this.billingDate = billingDate;
			}
			
		}
		
	}

}
