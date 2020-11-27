package com.huaxia.opas.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 第三方 & 人行 & 信用提示
 * 
 * @author zhiguo.li
 *
 */
public class PBOCCreditCue implements Serializable {

	private static final long serialVersionUID = -8055617736086102187L;

	// 信用汇总提示
	private CreditSummaryCue creditSummaryCue;

	// 个人信用报告"数字解读"
	private CreditScore creditScore;

	public CreditSummaryCue getCreditSummaryCue() {
		return creditSummaryCue;
	}

	public void setCreditSummaryCue(CreditSummaryCue creditSummaryCue) {
		this.creditSummaryCue = creditSummaryCue;
	}

	public CreditScore getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(CreditScore creditScore) {
		this.creditScore = creditScore;
	}

	public class CreditSummaryCue {

		// 个人住房贷款笔数
		private String perHouseLoanCount;

		// 个人商用房（包括商住两用）贷款笔数
		private String perBusinessHouseLoanCount;

		// 其他贷款笔数
		private String otherLoanCount;

		// 首笔贷款发放月份
		private String firstLoanOpenMonth;

		// 贷记卡账户数
		private String loancardCount;

		// 首张贷记卡发卡月份
		private String firstLoancardOpenMonth;

		// 准贷记卡账户数
		private String standardLoancardCount;

		// 首张准贷记卡发卡月份
		private String firstStandardLoancardOpenMonth;

		// 本人声明数目
		private String announceCount;

		// 异议标注数目
		private String dissentCount;

		public String getPerHouseLoanCount() {
			return perHouseLoanCount;
		}

		public void setPerHouseLoanCount(String perHouseLoanCount) {
			this.perHouseLoanCount = perHouseLoanCount;
		}

		public String getPerBusinessHouseLoanCount() {
			return perBusinessHouseLoanCount;
		}

		public void setPerBusinessHouseLoanCount(String perBusinessHouseLoanCount) {
			this.perBusinessHouseLoanCount = perBusinessHouseLoanCount;
		}

		public String getOtherLoanCount() {
			return otherLoanCount;
		}

		public void setOtherLoanCount(String otherLoanCount) {
			this.otherLoanCount = otherLoanCount;
		}

		public String getFirstLoanOpenMonth() {
			return firstLoanOpenMonth;
		}

		public void setFirstLoanOpenMonth(String firstLoanOpenMonth) {
			this.firstLoanOpenMonth = firstLoanOpenMonth;
		}

		public String getLoancardCount() {
			return loancardCount;
		}

		public void setLoancardCount(String loancardCount) {
			this.loancardCount = loancardCount;
		}

		public String getFirstLoancardOpenMonth() {
			return firstLoancardOpenMonth;
		}

		public void setFirstLoancardOpenMonth(String firstLoancardOpenMonth) {
			this.firstLoancardOpenMonth = firstLoancardOpenMonth;
		}

		public String getStandardLoancardCount() {
			return standardLoancardCount;
		}

		public void setStandardLoancardCount(String standardLoancardCount) {
			this.standardLoancardCount = standardLoancardCount;
		}

		public String getFirstStandardLoancardOpenMonth() {
			return firstStandardLoancardOpenMonth;
		}

		public void setFirstStandardLoancardOpenMonth(String firstStandardLoancardOpenMonth) {
			this.firstStandardLoancardOpenMonth = firstStandardLoancardOpenMonth;
		}

		public String getAnnounceCount() {
			return announceCount;
		}

		public void setAnnounceCount(String announceCount) {
			this.announceCount = announceCount;
		}

		public String getDissentCount() {
			return dissentCount;
		}

		public void setDissentCount(String dissentCount) {
			this.dissentCount = dissentCount;
		}

	}

	public class CreditScore {

		// 数字解读
		private String score;

		// 相对位置
		private String scoreLevel;

		// 特殊：说明内容
		//private String scoreEle;
		private List<HashMap<String,Object>> scoreElesMapList;
		
		
		public String getScore() {
			return score;
		}

		public void setScore(String score) {
			this.score = score;
		}

		public String getScoreLevel() {
			return scoreLevel;
		}

		public void setScoreLevel(String scoreLevel) {
			this.scoreLevel = scoreLevel;
		}

		public List<HashMap<String, Object>> getScoreElesMapList() {
			return scoreElesMapList;
		}

		public void setScoreElesMapList(List<HashMap<String, Object>> scoreElesMapList) {
			this.scoreElesMapList = scoreElesMapList;
		}

		/*public String getScoreEle() {
			return scoreEle;
		}

		public void setScoreEle(String scoreEle) {
			this.scoreEle = scoreEle;
		}*/
		
		
	}
}
