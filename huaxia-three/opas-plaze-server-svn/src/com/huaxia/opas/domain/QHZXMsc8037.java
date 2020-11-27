package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 前海征信 & 常贷客数据信息
 * 
 * @author zhiguo.li
 *
 */
public class QHZXMsc8037 extends QHZX implements Serializable {

	private static final long serialVersionUID = 8775082953365601050L;

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
	
	// 查询原因
	private String reasonCode;
	
	// 机构所属行业
	private String industry;
	
	// 命中机构数目
	private String amount;
	
	// 命中银行机构数
	private String bnkAmount;
	
	// 命中消费金融机构数
	private String cnssAmount;
	
	// 命中p2p或者小贷机构数
	private String p2pAmount;
	
	// 机构查询次数
	private String queryAmt;
	
	// 近三个月机构查询次数
	private String queryAmtM3;
	
	// 近六个月机构查询次数
	private String queryAmtM6;
	
	// 业务发生时间日期
	private String busiDate;
	
	// 错误代码
	private String erCode;
	
	// 错误信息
	private String erMsg;
	
	// 签名
	private String signatureValue;

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

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBusiDate() {
		return busiDate;
	}

	public void setBusiDate(String busiDate) {
		this.busiDate = busiDate;
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

	public String getBnkAmount() {
		return bnkAmount;
	}

	public void setBnkAmount(String bnkAmount) {
		this.bnkAmount = bnkAmount;
	}

	public String getCnssAmount() {
		return cnssAmount;
	}

	public void setCnssAmount(String cnssAmount) {
		this.cnssAmount = cnssAmount;
	}

	public String getP2pAmount() {
		return p2pAmount;
	}

	public void setP2pAmount(String p2pAmount) {
		this.p2pAmount = p2pAmount;
	}

	public String getQueryAmt() {
		return queryAmt;
	}

	public void setQueryAmt(String queryAmt) {
		this.queryAmt = queryAmt;
	}

	public String getQueryAmtM3() {
		return queryAmtM3;
	}

	public void setQueryAmtM3(String queryAmtM3) {
		this.queryAmtM3 = queryAmtM3;
	}

	public String getQueryAmtM6() {
		return queryAmtM6;
	}

	public void setQueryAmtM6(String queryAmtM6) {
		this.queryAmtM6 = queryAmtM6;
	}

}
