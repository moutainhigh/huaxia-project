package com.huaxia.plaze.ui.fico.domain;
/**
 * FICO风险预警响应表
 * @author liuwei
 *
 */
public class FicoRiskLevel {
	// uuid
	private String trnId;
	// 创建时间
	private String crtTime;
	// 证件号
	private String idNum;
	//手机号
	private String telNum;
	//手机号前三位
	private String telNumForth;
	//日期
	private String ficoDate;
	//华夏标识
	private String hxFlag;
	// 查询时间
	private String queryTime;
	//风险等级
	private String restLevel;
	public String getTrnId() {
		return trnId;
	}
	public void setTrnId(String trnId) {
		this.trnId = trnId;
	}
	public String getCrtTime() {
		return crtTime;
	}
	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public String getTelNumForth() {
		return telNumForth;
	}
	public void setTelNumForth(String telNumForth) {
		this.telNumForth = telNumForth;
	}
	public String getFicoDate() {
		return ficoDate;
	}
	public void setFicoDate(String ficoDate) {
		this.ficoDate = ficoDate;
	}
	public String getHxFlag() {
		return hxFlag;
	}
	public void setHxFlag(String hxFlag) {
		this.hxFlag = hxFlag;
	}
	public String getRestLevel() {
		return restLevel;
	}
	public void setRestLevel(String restLevel) {
		this.restLevel = restLevel;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
}
