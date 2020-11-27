package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 人行 & 报告头
 * 
 * @author zhiguo.li
 *
 */
public class PBOCHeader implements Serializable {

	private static final long serialVersionUID = 8981129168950987879L;
	
	// 报告头描述
	private MessageHeader messageHeader;
	
	// 查询请求信息
	private QueryReq queryReq;

	public MessageHeader getMessageHeader() {
		return messageHeader;
	}

	public void setMessageHeader(MessageHeader messageHeader) {
		this.messageHeader = messageHeader;
	}

	public QueryReq getQueryReq() {
		return queryReq;
	}

	public void setQueryReq(QueryReq queryReq) {
		this.queryReq = queryReq;
	}
	
	public class MessageHeader {
		
		// 报告编号
		private String reportSN;
		
		// 查询时间
		private String queryTime;
		
		// 信用报告生成时间
		private String reportCreateTime;

		public String getReportSN() {
			return reportSN;
		}

		public void setReportSN(String reportSN) {
			this.reportSN = reportSN;
		}

		public String getQueryTime() {
			return queryTime;
		}

		public void setQueryTime(String queryTime) {
			this.queryTime = queryTime;
		}

		public String getReportCreateTime() {
			return reportCreateTime;
		}

		public void setReportCreateTime(String reportCreateTime) {
			this.reportCreateTime = reportCreateTime;
		}
		
	}
	
	public class QueryReq {
		
		// 产品各类代码
		private String productType;
		
		// 产品版式代码
		private String format;
		
		// 产品版本代码
		private String formatVersion;
		
		// 被查询者姓名
		private String name;
		
		// 被查询者证件类型
		private String certtype;
		
		// 被查询者证件号码
		private String certno;
		
		// 查询原因
		private String queryReason;
		
		// 查询机构
		private String queryOrg;
		
		// 操作用户
		private String userCode;
		
		// 查询结果提示
		private String queryResultCue;

		public String getProductType() {
			return productType;
		}

		public void setProductType(String productType) {
			this.productType = productType;
		}

		public String getFormat() {
			return format;
		}

		public void setFormat(String format) {
			this.format = format;
		}

		public String getFormatVersion() {
			return formatVersion;
		}

		public void setFormatVersion(String formatVersion) {
			this.formatVersion = formatVersion;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCerttype() {
			return certtype;
		}

		public void setCerttype(String certtype) {
			this.certtype = certtype;
		}

		public String getCertno() {
			return certno;
		}

		public void setCertno(String certno) {
			this.certno = certno;
		}

		public String getQueryReason() {
			return queryReason;
		}

		public void setQueryReason(String queryReason) {
			this.queryReason = queryReason;
		}

		public String getQueryOrg() {
			return queryOrg;
		}

		public void setQueryOrg(String queryOrg) {
			this.queryOrg = queryOrg;
		}

		public String getUserCode() {
			return userCode;
		}

		public void setUserCode(String userCode) {
			this.userCode = userCode;
		}

		public String getQueryResultCue() {
			return queryResultCue;
		}

		public void setQueryResultCue(String queryResultCue) {
			this.queryResultCue = queryResultCue;
		}
		
	}

}
