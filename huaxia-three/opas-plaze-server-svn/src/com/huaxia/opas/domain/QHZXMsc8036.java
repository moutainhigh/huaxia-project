package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 前海征信 & 风险度提示数据信息
 * 
 * @author zhiguo.li
 *
 */
public class QHZXMsc8036 extends QHZX implements Serializable {

	private static final long serialVersionUID = -5186379191493065573L;

	// 机构代码
	private String orgCode;
	
	// 渠道
	private String chnlId;
	
	// 交易流水号
	private String transNo;
	
	// 交易时间
	private String transDate;
	
	// 授权代码
	private String authCode;
	
	// 授权时间
	private String authDate;
	
	// 错误代码
	private String rtCode;
	
	// 错误信息
	private String rtMsg;
	
	// 批次号
	private String batchNo;
	
	// 证件号码
	private String idNo;
	
	// 证件类型
	private String idType;
	
	// 主体名称
	private String name;
	
	// 序列号
	private String seqNo;
	
	// 来源代码
	private String sourceId;
	
	// 风险得分
	private String rskScore;
	
	// 风险标记
	private String rskMark;
	
	// 业务发生时间
	private String dataBuildTime;
		
	// 数据状态
	private String dataStatus;
	
	// 错误代码
	private String erCode;
	
	// 错误信息
	private String erMsg;
	
	// 签名
	private String signatureValue;
	
	// 查询原因
	private String reasonNo;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getChnlId() {
		return chnlId;
	}

	public void setChnlId(String chnlId) {
		this.chnlId = chnlId;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthDate() {
		return authDate;
	}

	public void setAuthDate(String authDate) {
		this.authDate = authDate;
	}

	public String getRtCode() {
		return rtCode;
	}

	public void setRtCode(String rtCode) {
		this.rtCode = rtCode;
	}

	public String getRtMsg() {
		return rtMsg;
	}

	public void setRtMsg(String rtMsg) {
		this.rtMsg = rtMsg;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getRskScore() {
		return rskScore;
	}

	public void setRskScore(String rskScore) {
		this.rskScore = rskScore;
	}

	public String getRskMark() {
		return rskMark;
	}

	public void setRskMark(String rskMark) {
		this.rskMark = rskMark;
	}

	public String getDataBuildTime() {
		return dataBuildTime;
	}

	public void setDataBuildTime(String dataBuildTime) {
		this.dataBuildTime = dataBuildTime;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getErCode() {
		return erCode;
	}

	public void setErCode(String erCode) {
		this.erCode = erCode;
	}

	public String getErMsg() {
		return erMsg;
	}

	public void setErMsg(String erMsg) {
		this.erMsg = erMsg;
	}

	public String getSignatureValue() {
		return signatureValue;
	}

	public void setSignatureValue(String signatureValue) {
		this.signatureValue = signatureValue;
	}

	public String getReasonNo() {
		return reasonNo;
	}

	public void setReasonNo(String reasonNo) {
		this.reasonNo = reasonNo;
	}

}
