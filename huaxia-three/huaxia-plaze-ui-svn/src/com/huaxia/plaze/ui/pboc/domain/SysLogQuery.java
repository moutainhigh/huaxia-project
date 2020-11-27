package com.huaxia.plaze.ui.pboc.domain;

/**
 * @author User
 *
 */
public class SysLogQuery {

	// uuid
	private String uuid;
	// 创建时间
	private String crtTime;
	// 创建者
	private String crtUser;
	// 日志ID
	private String logId;
	// 交易id
	private String trnId;
	// 查询人姓名
	private String staffName;
	// 查询人证件号
	private String staffCertNo;
	// 被查询人姓名
	private String name;
	// 被查询人证件号
	private String certNo;
	// 查询方式（单条实时或单条）
	private String queryType;
	// 查询原因（贷前贷后或特约商户查询）
	private String queryReason;
	// 查询结果
	private String queryResult;
	// IP
	private String ip;
	// MAC
	private String mac;
	//查询动作
	private String queryAction;
	//0 正常查询 1例外
	private String queryExclude;
	//阻断时查询数量
	private Integer preventQueryCount;
	//阻断时查询原因
	private String preventQueryReason;
	public String getUuid() {
		return uuid;
	}
	public String getCrtTime() {
		return crtTime;
	}
	public String getCrtUser() {
		return crtUser;
	}
	public String getLogId() {
		return logId;
	}
	public String getTrnId() {
		return trnId;
	}
	public String getStaffName() {
		return staffName;
	}
	public String getStaffCertNo() {
		return staffCertNo;
	}
	public String getName() {
		return name;
	}
	public String getCertNo() {
		return certNo;
	}
	public String getQueryType() {
		return queryType;
	}
	public String getQueryReason() {
		return queryReason;
	}
	public String getQueryResult() {
		return queryResult;
	}
	public String getIp() {
		return ip;
	}
	public String getMac() {
		return mac;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public void setTrnId(String trnId) {
		this.trnId = trnId;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public void setStaffCertNo(String staffCertNo) {
		this.staffCertNo = staffCertNo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public void setQueryReason(String queryReason) {
		this.queryReason = queryReason;
	}
	public void setQueryResult(String queryResult) {
		this.queryResult = queryResult;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getQueryAction() {
		return queryAction;
	}
	public void setQueryAction(String queryAction) {
		this.queryAction = queryAction;
	}
	public String getQueryExclude() {
		return queryExclude;
	}
	public void setQueryExclude(String queryExclude) {
		this.queryExclude = queryExclude;
	}
	public Integer getPreventQueryCount() {
		return preventQueryCount;
	}
	public void setPreventQueryCount(Integer preventQueryCount) {
		this.preventQueryCount = preventQueryCount;
	}
	public String getPreventQueryReason() {
		return preventQueryReason;
	}
	public void setPreventQueryReason(String preventQueryReason) {
		this.preventQueryReason = preventQueryReason;
	}

}
