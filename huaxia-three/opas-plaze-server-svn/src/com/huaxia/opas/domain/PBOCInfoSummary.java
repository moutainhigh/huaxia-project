package com.huaxia.opas.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 第三方 & 人行 & 信息概要
 * 
 * @author zhiguo.li
 *
 */
public class PBOCInfoSummary implements Serializable {

	private static final long serialVersionUID = -2259171099801461542L;

	// 信用汇总信息
	private PBOCCreditCue creditCue;
	
	// 逾期及违约信息概要
	private OverdueAndFellback overdueAndFellback;
	
	// 授信及负债信息概要
	private ShareAndDebt shareAndDebt;

	public PBOCCreditCue getCreditCue() {
		return creditCue;
	}

	public void setCreditCue(PBOCCreditCue creditCue) {
		this.creditCue = creditCue;
	}

	public OverdueAndFellback getOverdueAndFellback() {
		return overdueAndFellback;
	}

	public void setOverdueAndFellback(OverdueAndFellback overdueAndFellback) {
		this.overdueAndFellback = overdueAndFellback;
	}
	
	public ShareAndDebt getShareAndDebt() {
		return shareAndDebt;
	}

	public void setShareAndDebt(ShareAndDebt shareAndDebt) {
		this.shareAndDebt = shareAndDebt;
	}

	public class OverdueAndFellback {
		
		// 违约信息汇总
		private FellbackSummary fellbackSummary;

		// 逾期（透支）信息汇总
		private OverdueSummary overdueSummary;

		public FellbackSummary getFellbackSummary() {
			return fellbackSummary;
		}

		public void setFellbackSummary(FellbackSummary fellbackSummary) {
			this.fellbackSummary = fellbackSummary;
		}

		public OverdueSummary getOverdueSummary() {
			return overdueSummary;
		}

		public void setOverdueSummary(OverdueSummary overdueSummary) {
			this.overdueSummary = overdueSummary;
		}

		public class FellbackSummary {

			// 呆账信息汇总
			private FellbackDebtSum fellbackDebtSum;

			// 资产处置信息汇总
			private AssetDispositionSum assetDispositionSum;

			// 保证人代偿汇总
			private AssureerRepaySum assureerRepaySum;

			public FellbackDebtSum getFellbackDebtSum() {
				return fellbackDebtSum;
			}

			public void setFellbackDebtSum(FellbackDebtSum fellbackDebtSum) {
				this.fellbackDebtSum = fellbackDebtSum;
			}

			public AssetDispositionSum getAssetDispositionSum() {
				return assetDispositionSum;
			}

			public void setAssetDispositionSum(AssetDispositionSum assetDispositionSum) {
				this.assetDispositionSum = assetDispositionSum;
			}

			public AssureerRepaySum getAssureerRepaySum() {
				return assureerRepaySum;
			}

			public void setAssureerRepaySum(AssureerRepaySum assureerRepaySum) {
				this.assureerRepaySum = assureerRepaySum;
			}

			public class FellbackDebtSum {

				// 笔数
				private String count;

				// 余额
				private String balance;

				public String getCount() {
					return count;
				}

				public void setCount(String count) {
					this.count = count;
				}

				public String getBalance() {
					return balance;
				}

				public void setBalance(String balance) {
					this.balance = balance;
				}

			}

			public class AssetDispositionSum {

				// 笔数
				private String  count;

				// 余额
				private String balance;

				public String getCount() {
					return count;
				}

				public void setCount(String count) {
					this.count = count;
				}

				public String getBalance() {
					return balance;
				}

				public void setBalance(String balance) {
					this.balance = balance;
				}

			}

			public class AssureerRepaySum {

				// 笔数
				private String count;

				// 余额
				private String balance;

				public String getCount() {
					return count;
				}

				public void setCount(String count) {
					this.count = count;
				}

				public String getBalance() {
					return balance;
				}

				public void setBalance(String balance) {
					this.balance = balance;
				}
			}

		}
		
		public class OverdueSummary {
			
			// 贷款逾期
			private LoanSum loanSum;

			// 贷记卡逾期
			private LoancardSum loancardSum;

			// 准贷记卡60天以上透支
			private StandardLoancardSum standardLoancardSum;

			public LoanSum getLoanSum() {
				return loanSum;
			}

			public void setLoanSum(LoanSum loanSum) {
				this.loanSum = loanSum;
			}

			public LoancardSum getLoancardSum() {
				return loancardSum;
			}

			public void setLoancardSum(LoancardSum loancardSum) {
				this.loancardSum = loancardSum;
			}

			public StandardLoancardSum getStandardLoancardSum() {
				return standardLoancardSum;
			}

			public void setStandardLoancardSum(StandardLoancardSum standardLoancardSum) {
				this.standardLoancardSum = standardLoancardSum;
			}

			public class LoanSum {

				// 笔数/账户数
				private String count;

				// 月份数
				private String months;

				// 单月最高逾期总额/单月最高透支总额
				private String highestOverdueAmountPerMon;

				// 最长逾期月数/最长透支月数
				private String maxDuration;

				public String getCount() {
					return count;
				}

				public void setCount(String count) {
					this.count = count;
				}

				public String getMonths() {
					return months;
				}

				public void setMonths(String months) {
					this.months = months;
				}

				public String getHighestOverdueAmountPerMon() {
					return highestOverdueAmountPerMon;
				}

				public void setHighestOverdueAmountPerMon(String highestOverdueAmountPerMon) {
					this.highestOverdueAmountPerMon = highestOverdueAmountPerMon;
				}

				public String getMaxDuration() {
					return maxDuration;
				}

				public void setMaxDuration(String maxDuration) {
					this.maxDuration = maxDuration;
				}

			}

			public class LoancardSum {

				// 笔数/账户数
				private String count;

				// 月份数
				private String months;

				// 单月最高逾期总额/单月最高透支总额
				private String highestOverdueAmountPerMon;

				// 最长逾期月数/最长透支月数
				private String maxDuration;

				public String getCount() {
					return count;
				}

				public void setCount(String count) {
					this.count = count;
				}

				public String getMonths() {
					return months;
				}

				public void setMonths(String months) {
					this.months = months;
				}

				public String getHighestOverdueAmountPerMon() {
					return highestOverdueAmountPerMon;
				}

				public void setHighestOverdueAmountPerMon(String highestOverdueAmountPerMon) {
					this.highestOverdueAmountPerMon = highestOverdueAmountPerMon;
				}

				public String getMaxDuration() {
					return maxDuration;
				}

				public void setMaxDuration(String maxDuration) {
					this.maxDuration = maxDuration;
				}

			}

			public class StandardLoancardSum {

				// 笔数/账户数
				private String count;

				// 月份数
				private String months;

				// 单月最高逾期总额/单月最高透支总额
				private String highestOverdueAmountPerMon;

				// 最长逾期月数/最长透支月数
				private String maxDuration;

				public String getCount() {
					return count;
				}

				public void setCount(String count) {
					this.count = count;
				}

				public String getMonths() {
					return months;
				}

				public void setMonths(String months) {
					this.months = months;
				}

				public String getHighestOverdueAmountPerMon() {
					return highestOverdueAmountPerMon;
				}

				public void setHighestOverdueAmountPerMon(String highestOverdueAmountPerMon) {
					this.highestOverdueAmountPerMon = highestOverdueAmountPerMon;
				}

				public String getMaxDuration() {
					return maxDuration;
				}

				public void setMaxDuration(String maxDuration) {
					this.maxDuration = maxDuration;
				}

			}
			
		}
		
	}
	
	public class ShareAndDebt {
		
		// 未结清贷款信息汇总
		private UnpaidLoan unpaidLoan;

		// 未销户贷记卡信息汇总
		private UndestoryLoancard undestoryLoancard;

		// 未销户准贷记卡信息汇总
		private UndestoryStandardLoancard undestoryStandardLoancard;

		// 对外担保信息汇总
		private GuaranteeSummary guaranteeSummary;

		public UnpaidLoan getUnpaidLoan() {
			return unpaidLoan;
		}

		public void setUnpaidLoan(UnpaidLoan unpaidLoan) {
			this.unpaidLoan = unpaidLoan;
		}

		public UndestoryLoancard getUndestoryLoancard() {
			return undestoryLoancard;
		}

		public void setUndestoryLoancard(UndestoryLoancard undestoryLoancard) {
			this.undestoryLoancard = undestoryLoancard;
		}

		public UndestoryStandardLoancard getUndestoryStandardLoancard() {
			return undestoryStandardLoancard;
		}

		public void setUndestoryStandardLoancard(UndestoryStandardLoancard undestoryStandardLoancard) {
			this.undestoryStandardLoancard = undestoryStandardLoancard;
		}

		public GuaranteeSummary getGuaranteeSummary() {
			return guaranteeSummary;
		}

		public void setGuaranteeSummary(GuaranteeSummary guaranteeSummary) {
			this.guaranteeSummary = guaranteeSummary;
		}

		public class UnpaidLoan {

			// 贷款法人机构数/发卡法人机构数
			private String financeCorpCount;

			// 贷款机构数/发卡机构数
			private String financeOrgCount;

			// 笔数/账户数
			private String accountCount;

			// 合同金额/授信总额
			private String creditLimit;

			// 单家行最高授信额度
			private String maxCreditLimitPerOrg;

			// 单家行最低授信额度
			private String minCreditLimitPerOrg;

			// 余额
			private String balance;

			// 已用额度 / 透支额度
			private String usedCreditLimit;

			// 最近6个月平均应还款/最近6个月平均使用额度/最近6个月平均透支额度
			private String latest6MonthUsedAvgAmount;

			public String getFinanceCorpCount() {
				return financeCorpCount;
			}

			public void setFinanceCorpCount(String financeCorpCount) {
				this.financeCorpCount = financeCorpCount;
			}

			public String getFinanceOrgCount() {
				return financeOrgCount;
			}

			public void setFinanceOrgCount(String financeOrgCount) {
				this.financeOrgCount = financeOrgCount;
			}

			public String getAccountCount() {
				return accountCount;
			}

			public void setAccountCount(String accountCount) {
				this.accountCount = accountCount;
			}

			public String getCreditLimit() {
				return creditLimit;
			}

			public void setCreditLimit(String creditLimit) {
				this.creditLimit = creditLimit;
			}

			public String getMaxCreditLimitPerOrg() {
				return maxCreditLimitPerOrg;
			}

			public void setMaxCreditLimitPerOrg(String maxCreditLimitPerOrg) {
				this.maxCreditLimitPerOrg = maxCreditLimitPerOrg;
			}

			public String getMinCreditLimitPerOrg() {
				return minCreditLimitPerOrg;
			}

			public void setMinCreditLimitPerOrg(String minCreditLimitPerOrg) {
				this.minCreditLimitPerOrg = minCreditLimitPerOrg;
			}

			public String getBalance() {
				return balance;
			}

			public void setBalance(String balance) {
				this.balance = balance;
			}

			public String getUsedCreditLimit() {
				return usedCreditLimit;
			}

			public void setUsedCreditLimit(String usedCreditLimit) {
				this.usedCreditLimit = usedCreditLimit;
			}

			public String getLatest6MonthUsedAvgAmount() {
				return latest6MonthUsedAvgAmount;
			}

			public void setLatest6MonthUsedAvgAmount(String latest6MonthUsedAvgAmount) {
				this.latest6MonthUsedAvgAmount = latest6MonthUsedAvgAmount;
			}

		}

		public class UndestoryLoancard {

			// 贷款法人机构数/发卡法人机构数
			private String financeCorpCount;

			// 贷款机构数/发卡机构数
			private String financeOrgCount;

			// 笔数/账户数
			private String accountCount;

			// 合同金额/授信总额
			private String creditLimit;

			// 单家行最高授信额度
			private String maxCreditLimitPerOrg;

			// 单家行最低授信额度
			private String minCreditLimitPerOrg;

			// 余额
			private String balance;

			// 已用额度 / 透支额度
			private String usedCreditLimit;

			// 最近6个月平均应还款/最近6个月平均使用额度/最近6个月平均透支额度
			private String latest6MonthUsedAvgAmount;

			public String getFinanceCorpCount() {
				return financeCorpCount;
			}

			public void setFinanceCorpCount(String financeCorpCount) {
				this.financeCorpCount = financeCorpCount;
			}

			public String getFinanceOrgCount() {
				return financeOrgCount;
			}

			public void setFinanceOrgCount(String financeOrgCount) {
				this.financeOrgCount = financeOrgCount;
			}

			public String getAccountCount() {
				return accountCount;
			}

			public void setAccountCount(String accountCount) {
				this.accountCount = accountCount;
			}

			public String getCreditLimit() {
				return creditLimit;
			}

			public void setCreditLimit(String creditLimit) {
				this.creditLimit = creditLimit;
			}

			public String getMaxCreditLimitPerOrg() {
				return maxCreditLimitPerOrg;
			}

			public void setMaxCreditLimitPerOrg(String maxCreditLimitPerOrg) {
				this.maxCreditLimitPerOrg = maxCreditLimitPerOrg;
			}

			public String getMinCreditLimitPerOrg() {
				return minCreditLimitPerOrg;
			}

			public void setMinCreditLimitPerOrg(String minCreditLimitPerOrg) {
				this.minCreditLimitPerOrg = minCreditLimitPerOrg;
			}

			public String getBalance() {
				return balance;
			}

			public void setBalance(String balance) {
				this.balance = balance;
			}

			public String getUsedCreditLimit() {
				return usedCreditLimit;
			}

			public void setUsedCreditLimit(String usedCreditLimit) {
				this.usedCreditLimit = usedCreditLimit;
			}

			public String getLatest6MonthUsedAvgAmount() {
				return latest6MonthUsedAvgAmount;
			}

			public void setLatest6MonthUsedAvgAmount(String latest6MonthUsedAvgAmount) {
				this.latest6MonthUsedAvgAmount = latest6MonthUsedAvgAmount;
			}

		}

		public class UndestoryStandardLoancard {

			// 贷款法人机构数/发卡法人机构数
			private String financeCorpCount;

			// 贷款机构数/发卡机构数
			private String financeOrgCount;

			// 笔数/账户数
			private String accountCount;

			// 合同金额/授信总额
			private String creditLimit;

			// 单家行最高授信额度
			private String maxCreditLimitPerOrg;

			// 单家行最低授信额度
			private String minCreditLimitPerOrg;

			// 余额
			private String balance;

			// 已用额度 / 透支额度
			private String usedCreditLimit;

			// 最近6个月平均应还款/最近6个月平均使用额度/最近6个月平均透支额度
			private String latest6MonthUsedAvgAmount;

			public String getFinanceCorpCount() {
				return financeCorpCount;
			}

			public void setFinanceCorpCount(String financeCorpCount) {
				this.financeCorpCount = financeCorpCount;
			}

			public String getFinanceOrgCount() {
				return financeOrgCount;
			}

			public void setFinanceOrgCount(String financeOrgCount) {
				this.financeOrgCount = financeOrgCount;
			}

			public String getAccountCount() {
				return accountCount;
			}

			public void setAccountCount(String accountCount) {
				this.accountCount = accountCount;
			}

			public String getCreditLimit() {
				return creditLimit;
			}

			public void setCreditLimit(String creditLimit) {
				this.creditLimit = creditLimit;
			}

			public String getMaxCreditLimitPerOrg() {
				return maxCreditLimitPerOrg;
			}

			public void setMaxCreditLimitPerOrg(String maxCreditLimitPerOrg) {
				this.maxCreditLimitPerOrg = maxCreditLimitPerOrg;
			}

			public String getMinCreditLimitPerOrg() {
				return minCreditLimitPerOrg;
			}

			public void setMinCreditLimitPerOrg(String minCreditLimitPerOrg) {
				this.minCreditLimitPerOrg = minCreditLimitPerOrg;
			}

			public String getBalance() {
				return balance;
			}

			public void setBalance(String balance) {
				this.balance = balance;
			}

			public String getUsedCreditLimit() {
				return usedCreditLimit;
			}

			public void setUsedCreditLimit(String usedCreditLimit) {
				this.usedCreditLimit = usedCreditLimit;
			}

			public String getLatest6MonthUsedAvgAmount() {
				return latest6MonthUsedAvgAmount;
			}

			public void setLatest6MonthUsedAvgAmount(String latest6MonthUsedAvgAmount) {
				this.latest6MonthUsedAvgAmount = latest6MonthUsedAvgAmount;
			}

		}

		public class GuaranteeSummary {

			// 担保笔数
			private String count;

			// 担保金额
			private String amount;

			// 担保本金体系
			private String balance;

			public String getCount() {
				return count;
			}

			public void setCount(String count) {
				this.count = count;
			}

			public String getAmount() {
				return amount;
			}

			public void setAmount(String amount) {
				this.amount = amount;
			}

			public String getBalance() {
				return balance;
			}

			public void setBalance(String balance) {
				this.balance = balance;
			}

		}
		
	}
	
}
