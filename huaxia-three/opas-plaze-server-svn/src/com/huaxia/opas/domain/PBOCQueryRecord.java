package com.huaxia.opas.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 第三方 & 人行 & 查询记录
 * 
 * @author zhiguo.li
 *
 */
public class PBOCQueryRecord implements Serializable {

	private static final long serialVersionUID = 4037081854954038089L;

	// 查询汇总记录
	private RecordSummary recordSummary;
	
	// 查询明细
	private List<RecordInfo> recordInfoList;
	
	public RecordSummary getRecordSummary() {
		return recordSummary;
	}

	public void setRecordSummary(RecordSummary recordSummary) {
		this.recordSummary = recordSummary;
	}

	public List<RecordInfo> getRecordInfoList() {
		return recordInfoList;
	}

	public void setRecordInfoList(List<RecordInfo> recordInfoList) {
		this.recordInfoList = recordInfoList;
	}

	public class RecordSummary {

		// 最近一个月内的查询机构数
		private List<LatestMonthQueryorgSum> latestMonthQueryorgSumList;

		// 最近一个月内的查询次数
		private List<LatestMonthQueryrecordSum> latestMonthQueryrecordSumList;

		// 最近两年内的查询次数
		private List<TwoyearQueryrecordSum> twoyearQueryrecordSumList;

		public List<LatestMonthQueryorgSum> getLatestMonthQueryorgSumList() {
			return latestMonthQueryorgSumList;
		}

		public void setLatestMonthQueryorgSumList(List<LatestMonthQueryorgSum> latestMonthQueryorgSumList) {
			this.latestMonthQueryorgSumList = latestMonthQueryorgSumList;
		}

		public List<LatestMonthQueryrecordSum> getLatestMonthQueryrecordSumList() {
			return latestMonthQueryrecordSumList;
		}

		public void setLatestMonthQueryrecordSumList(List<LatestMonthQueryrecordSum> latestMonthQueryrecordSumList) {
			this.latestMonthQueryrecordSumList = latestMonthQueryrecordSumList;
		}

		public List<TwoyearQueryrecordSum> getTwoyearQueryrecordSumList() {
			return twoyearQueryrecordSumList;
		}

		public void setTwoyearQueryrecordSumList(List<TwoyearQueryrecordSum> twoyearQueryrecordSumList) {
			this.twoyearQueryrecordSumList = twoyearQueryrecordSumList;
		}

		public class LatestMonthQueryorgSum {

			// 查询原因
			private String queryReason;

			// 数目
			private String sum;

			public String getQueryReason() {
				return queryReason;
			}

			public void setQueryReason(String queryReason) {
				this.queryReason = queryReason;
			}

			public String getSum() {
				return sum;
			}

			public void setSum(String sum) {
				this.sum = sum;
			}

		}

		public class LatestMonthQueryrecordSum {

			// 查询原因
			private String queryReason;

			// 数目
			private String sum;

			public String getQueryReason() {
				return queryReason;
			}

			public void setQueryReason(String queryReason) {
				this.queryReason = queryReason;
			}

			public String getSum() {
				return sum;
			}

			public void setSum(String sum) {
				this.sum = sum;
			}

		}

		public class TwoyearQueryrecordSum {

			// 查询原因
			private String queryReason;

			// 数目
			private String sum;

			public String getQueryReason() {
				return queryReason;
			}

			public void setQueryReason(String queryReason) {
				this.queryReason = queryReason;
			}

			public String getSum() {
				return sum;
			}

			public void setSum(String sum) {
				this.sum = sum;
			}
		}

	}
	
	public class RecordInfo {

		// 查询申请方式
		private String queryReqFormat;
		
		// 信贷审批查询记录明细
		private List<RecordDetail> recordDetailList;
		
		public String getQueryReqFormat() {
			return queryReqFormat;
		}

		public void setQueryReqFormat(String queryReqFormat) {
			this.queryReqFormat = queryReqFormat;
		}

		public List<RecordDetail> getRecordDetailList() {
			return recordDetailList;
		}

		public void setRecordDetailList(List<RecordDetail> recordDetailList) {
			this.recordDetailList = recordDetailList;
		}

		public class RecordDetail {
			
			// 查询日期
			private String queryDate;
			
			// 查询者
			private String querier;
			
			// 查询原因
			private String queryReason;

			public String getQueryDate() {
				return queryDate;
			}

			public void setQueryDate(String queryDate) {
				this.queryDate = queryDate;
			}

			public String getQuerier() {
				return querier;
			}

			public void setQuerier(String querier) {
				this.querier = querier;
			}

			public String getQueryReason() {
				return queryReason;
			}

			public void setQueryReason(String queryReason) {
				this.queryReason = queryReason;
			}
			
		}
	}

}
