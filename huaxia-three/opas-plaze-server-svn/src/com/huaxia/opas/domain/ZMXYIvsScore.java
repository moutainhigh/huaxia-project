package com.huaxia.opas.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 第三方 & 芝麻信用 & 反欺诈信息验证
 * 
 * @author zhiguo.li
 *
 */
public class ZMXYIvsScore implements Serializable {

	private static final long serialVersionUID = -5224433895132189188L;

	// 申请件编号
	private String appId;

	// 商户业务流水号
	private String transactionId;

	// 产品码
	private String productCode;

	// 证件类型
	private String certType;

	// 证件号码
	private String certNo;

	// 姓名
	private String name;

	// 手机号
	private String mobile;

	// 电子邮箱
	private String email;

	// IVS评分（取值区间为[0,100]）
	private String ivsScore;

	// 芝麻信用业务号
	private String bizNo;

	// 银行卡号
	private String bankCard;

	// 用户地址
	private String address;

	// 反欺诈信息明细
	private List<IvsDetail> ivsDetailList;

	// 反欺诈信息明细 & 风险因素代码 & 身份证代码
	private String certNoRiskFactorCode;

	// 反欺诈信息明细 & 风险因素说明 & 身份证代码
	private String certNoRiskFactorDesc;

	// 反欺诈信息明细 & 风险因素代码 & 姓名
	private String nameRiskFactorCode;

	// 反欺诈信息明细 & 风险因素说明 & 姓名
	private String nameRiskFactorDesc;

	// 反欺诈信息明细 & 风险因素代码 & Email
	private String emailRiskFactorCode;

	// 反欺诈信息明细 & 风险因素说明 & Email
	private String emailRiskFactorDesc;

	// 反欺诈信息明细 & 风险因素代码 & 电话号码
	private String phoneRiskFactorCode;

	// 反欺诈信息明细 & 风险因素说明 & 电话号码
	private String phoneRiskFactorDesc;

	// 反欺诈信息明细 & 风险因素代码 & 银行卡
	private String bankCardRiskFactorCode;

	// 反欺诈信息明细 & 风险因素说明 & 银行卡
	private String bankCardRiskFactorDesc;

	// 反欺诈信息明细 & 风险因素代码 & 地址
	private String addrRiskFactorCode;

	// 反欺诈信息明细 & 风险因素说明 & 地址
	private String addrRiskFactorDesc;

	// 反欺诈信息明细 & 风险因素代码 & IP
	private String ipRiskFactorCode;

	// 反欺诈信息明细 & 风险因素说明 & IP
	private String ipRiskFactorDesc;

	// 反欺诈信息明细 & 风险因素代码 & IMSI
	private String imsiRiskFactorCode;

	// 反欺诈信息明细 & 风险因素说明 & IMSI
	private String imsiRiskFactorDesc;

	// 反欺诈信息明细 & 风险因素代码 & IMEI
	private String imeiRiskFactorCode;

	// 反欺诈信息明细 & 风险因素说明 & IMEI
	private String imeiRiskFactorDesc;

	// 反欺诈信息明细 & 风险因素代码 & MAC
	private String macRiskFactorCode;

	// 反欺诈信息明细 & 风险因素说明 & MAC
	private String macRiskFactorDesc;

	// 反欺诈信息明细 & 风险因素代码 & WIFI-MAC
	private String wifiMacRiskFactorCode;

	// 反欺诈信息明细 & 风险因素说明 & WIFI-MAC
	private String wifiMacRiskFactorDesc;

	// 创建日期
	private String crtTime;

	// 创建人
	private String crtUser;

	// 最后更新日期
	private String lstUpdTime;

	// 最后更新人
	private String lstUpdUser;
	
	// [非持久化字段] 报文响应结果
	private Boolean responseResult;

	public Boolean getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(Boolean responseResult) {
		this.responseResult = responseResult;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIvsScore() {
		return ivsScore;
	}

	public void setIvsScore(String ivsScore) {
		this.ivsScore = ivsScore;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getLstUpdTime() {
		return lstUpdTime;
	}

	public void setLstUpdTime(String lstUpdTime) {
		this.lstUpdTime = lstUpdTime;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	public List<IvsDetail> getIvsDetailList() {
		return ivsDetailList;
	}

	public void setIvsDetailList(List<IvsDetail> ivsDetailList) {
		this.ivsDetailList = ivsDetailList;
	}

	public String getCertNoRiskFactorCode() {
		return certNoRiskFactorCode;
	}

	public void setCertNoRiskFactorCode(String certNoRiskFactorCode) {
		this.certNoRiskFactorCode = certNoRiskFactorCode;
	}

	public String getCertNoRiskFactorDesc() {
		return certNoRiskFactorDesc;
	}

	public void setCertNoRiskFactorDesc(String certNoRiskFactorDesc) {
		this.certNoRiskFactorDesc = certNoRiskFactorDesc;
	}

	public String getNameRiskFactorCode() {
		return nameRiskFactorCode;
	}

	public void setNameRiskFactorCode(String nameRiskFactorCode) {
		this.nameRiskFactorCode = nameRiskFactorCode;
	}

	public String getNameRiskFactorDesc() {
		return nameRiskFactorDesc;
	}

	public void setNameRiskFactorDesc(String nameRiskFactorDesc) {
		this.nameRiskFactorDesc = nameRiskFactorDesc;
	}

	public String getEmailRiskFactorCode() {
		return emailRiskFactorCode;
	}

	public void setEmailRiskFactorCode(String emailRiskFactorCode) {
		this.emailRiskFactorCode = emailRiskFactorCode;
	}

	public String getEmailRiskFactorDesc() {
		return emailRiskFactorDesc;
	}

	public void setEmailRiskFactorDesc(String emailRiskFactorDesc) {
		this.emailRiskFactorDesc = emailRiskFactorDesc;
	}

	public String getPhoneRiskFactorCode() {
		return phoneRiskFactorCode;
	}

	public void setPhoneRiskFactorCode(String phoneRiskFactorCode) {
		this.phoneRiskFactorCode = phoneRiskFactorCode;
	}

	public String getPhoneRiskFactorDesc() {
		return phoneRiskFactorDesc;
	}

	public void setPhoneRiskFactorDesc(String phoneRiskFactorDesc) {
		this.phoneRiskFactorDesc = phoneRiskFactorDesc;
	}

	public String getBankCardRiskFactorCode() {
		return bankCardRiskFactorCode;
	}

	public void setBankCardRiskFactorCode(String bankCardRiskFactorCode) {
		this.bankCardRiskFactorCode = bankCardRiskFactorCode;
	}

	public String getBankCardRiskFactorDesc() {
		return bankCardRiskFactorDesc;
	}

	public void setBankCardRiskFactorDesc(String bankCardRiskFactorDesc) {
		this.bankCardRiskFactorDesc = bankCardRiskFactorDesc;
	}

	public String getAddrRiskFactorCode() {
		return addrRiskFactorCode;
	}

	public void setAddrRiskFactorCode(String addrRiskFactorCode) {
		this.addrRiskFactorCode = addrRiskFactorCode;
	}

	public String getAddrRiskFactorDesc() {
		return addrRiskFactorDesc;
	}

	public void setAddrRiskFactorDesc(String addrRiskFactorDesc) {
		this.addrRiskFactorDesc = addrRiskFactorDesc;
	}

	public String getIpRiskFactorCode() {
		return ipRiskFactorCode;
	}

	public void setIpRiskFactorCode(String ipRiskFactorCode) {
		this.ipRiskFactorCode = ipRiskFactorCode;
	}

	public String getIpRiskFactorDesc() {
		return ipRiskFactorDesc;
	}

	public void setIpRiskFactorDesc(String ipRiskFactorDesc) {
		this.ipRiskFactorDesc = ipRiskFactorDesc;
	}

	public String getImsiRiskFactorCode() {
		return imsiRiskFactorCode;
	}

	public void setImsiRiskFactorCode(String imsiRiskFactorCode) {
		this.imsiRiskFactorCode = imsiRiskFactorCode;
	}

	public String getImsiRiskFactorDesc() {
		return imsiRiskFactorDesc;
	}

	public void setImsiRiskFactorDesc(String imsiRiskFactorDesc) {
		this.imsiRiskFactorDesc = imsiRiskFactorDesc;
	}

	public String getImeiRiskFactorCode() {
		return imeiRiskFactorCode;
	}

	public void setImeiRiskFactorCode(String imeiRiskFactorCode) {
		this.imeiRiskFactorCode = imeiRiskFactorCode;
	}

	public String getImeiRiskFactorDesc() {
		return imeiRiskFactorDesc;
	}

	public void setImeiRiskFactorDesc(String imeiRiskFactorDesc) {
		this.imeiRiskFactorDesc = imeiRiskFactorDesc;
	}

	public String getMacRiskFactorCode() {
		return macRiskFactorCode;
	}

	public void setMacRiskFactorCode(String macRiskFactorCode) {
		this.macRiskFactorCode = macRiskFactorCode;
	}

	public String getMacRiskFactorDesc() {
		return macRiskFactorDesc;
	}

	public void setMacRiskFactorDesc(String macRiskFactorDesc) {
		this.macRiskFactorDesc = macRiskFactorDesc;
	}

	public String getWifiMacRiskFactorCode() {
		return wifiMacRiskFactorCode;
	}

	public void setWifiMacRiskFactorCode(String wifiMacRiskFactorCode) {
		this.wifiMacRiskFactorCode = wifiMacRiskFactorCode;
	}

	public String getWifiMacRiskFactorDesc() {
		return wifiMacRiskFactorDesc;
	}

	public void setWifiMacRiskFactorDesc(String wifiMacRiskFactorDesc) {
		this.wifiMacRiskFactorDesc = wifiMacRiskFactorDesc;
	}
	
	public class IvsDetail {
		
		// 芝麻信用业务号
		private String bizNo;

		// 风险因素代码
		private String code;

		// 风险描述说明
		private String description;

		public String getBizNo() {
			return bizNo;
		}

		public void setBizNo(String bizNo) {
			this.bizNo = bizNo;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
	}

}
