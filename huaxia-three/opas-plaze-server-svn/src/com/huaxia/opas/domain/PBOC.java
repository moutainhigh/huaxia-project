package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 人行
 * 
 * <p>
 * <strong>说明：</strong>人行报文信息段定义
 * </p>
 * 
 * @author zhiguo.li
 *
 */
public class PBOC implements Serializable {

	private static final long serialVersionUID = 627818995395948430L;

	// 申请件编号
	private String appId;

	// 报告头（Header）
	private PBOCHeader header;

	// 个人基本信息（PersonalInfo）
	private PBOCPersonalInfo personalInfo;

	// 信息概要（InfoSummary）
	private PBOCInfoSummary infoSummary;

	// 信贷交易信息明细（CreditDetail）
	private PBOCCreditDetail creditDetail;

	// 公共信息明细（PublicInfo）
	private PBOCPublicInfo publicInfo;

	// 声明信息（Announce）
	private PBOCAnnounce announce;

	// 查询记录（QueryRecord）
	private PBOCQueryRecord queryRecord;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public PBOCHeader getHeader() {
		return header;
	}

	public void setHeader(PBOCHeader header) {
		this.header = header;
	}

	public PBOCPersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PBOCPersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public PBOCInfoSummary getInfoSummary() {
		return infoSummary;
	}

	public void setInfoSummary(PBOCInfoSummary infoSummary) {
		this.infoSummary = infoSummary;
	}

	public PBOCCreditDetail getCreditDetail() {
		return creditDetail;
	}

	public void setCreditDetail(PBOCCreditDetail creditDetail) {
		this.creditDetail = creditDetail;
	}

	public PBOCPublicInfo getPublicInfo() {
		return publicInfo;
	}

	public void setPublicInfo(PBOCPublicInfo publicInfo) {
		this.publicInfo = publicInfo;
	}

	public PBOCAnnounce getAnnounce() {
		return announce;
	}

	public void setAnnounce(PBOCAnnounce announce) {
		this.announce = announce;
	}

	public PBOCQueryRecord getQueryRecord() {
		return queryRecord;
	}

	public void setQueryRecord(PBOCQueryRecord queryRecord) {
		this.queryRecord = queryRecord;
	}

}
