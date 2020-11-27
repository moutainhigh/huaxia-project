package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 前海征信 & 地址通数据信息
 * 
 * @author zhiguo.li
 *
 */
public class QHZXMsc8007 extends QHZX implements Serializable {

	private static final long serialVersionUID = 3917138606894791109L;

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
	
	// 手机号码
	private String mobileNo;
	
	// 序列号
	private String seqNo;
	
	// 地址
	private String address;
	
	// 来源代码
	private String sourceId;
	
	// 省（简称）
	private String province;
	
	// 城市信息
	private String cityInfo;
	
	// 区（简称）
	private String borough;
	
	// 格式化地址
	private String fmtAddress;
	
	// 经度
	private String longitude;
	
	// 纬度
	private String latitude;
	
	// 楼盘名称
	private String buildingName;
	
	// 楼盘地址
	private String buildingAddress;
	
	// 楼盘周边均价
	private String houseArodAvgPrice;
	
	// 楼盘均价
	private String houseAvgPrice;
	
	// 查询数据状态
	private String state;
	
	// 建筑类型
	private String buildingType;
	
	// 业务发生时间
	private String dataBuildTime;
	
	// 是否匹配
	private String isMatch;
	
	// 地址属性
	private String addressPorperty;
	
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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCityInfo() {
		return cityInfo;
	}

	public void setCityInfo(String cityInfo) {
		this.cityInfo = cityInfo;
	}

	public String getBorough() {
		return borough;
	}

	public void setBorough(String borough) {
		this.borough = borough;
	}

	public String getFmtAddress() {
		return fmtAddress;
	}

	public void setFmtAddress(String fmtAddress) {
		this.fmtAddress = fmtAddress;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingAddress() {
		return buildingAddress;
	}

	public void setBuildingAddress(String buildingAddress) {
		this.buildingAddress = buildingAddress;
	}

	public String getHouseArodAvgPrice() {
		return houseArodAvgPrice;
	}

	public void setHouseArodAvgPrice(String houseArodAvgPrice) {
		this.houseArodAvgPrice = houseArodAvgPrice;
	}

	public String getHouseAvgPrice() {
		return houseAvgPrice;
	}

	public void setHouseAvgPrice(String houseAvgPrice) {
		this.houseAvgPrice = houseAvgPrice;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	public String getDataBuildTime() {
		return dataBuildTime;
	}

	public void setDataBuildTime(String dataBuildTime) {
		this.dataBuildTime = dataBuildTime;
	}

	public String getIsMatch() {
		return isMatch;
	}

	public void setIsMatch(String isMatch) {
		this.isMatch = isMatch;
	}

	public String getAddressPorperty() {
		return addressPorperty;
	}

	public void setAddressPorperty(String addressPorperty) {
		this.addressPorperty = addressPorperty;
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
