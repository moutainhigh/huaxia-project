package com.huaxia.plaze.ui.pboc.domain;

/**
 * @author User
 *
 */
public class HistoryQuery {

	// 报告编号
	private String reportNo;
	// 报告时间
	private String reportTime;
	// 证件号
	private String certNo;
	// 查询时间
	private String queryTime;
	
	private String uniqueId;
	
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	
}
