package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 前海征信 & 好信一鉴通数据信息
 * 
 * @author zhiguo.li
 *
 */
public class QHZXMsc8107 extends QHZX implements Serializable {

	private static final long serialVersionUID = -4288038340094703747L;

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
	
	// 是否真实身份
	private String isRealIdentity;
	
	// 是否本人真实活动地址
	private String isValidAddress;
	
	// 地址类型
	private String addressType;
	
	// 是否真实工作单位
	private String isRealCompany;
	
	// 单位匹配度分值
	private String companySimDeg;
	
	// 单位最初出现时间
	private String appear1ThDate;
	
	// 单位最近一次出现时间
	private String appearLastDate;
	
	// 手机验证结果
	private String isOwnerMobile;
	
	// 手机状态
	private String ownerMobileStatus;
	
	// 使用时间分数
	private String useTimeScore;
	
	// 是否存在关系
	private String isExistRel;
	
	// 关系力度
	private String relLevel;
	
	// 车辆验证结果
	private String carChkResult;
	
	// 是否异步返回结果
	private String isAsyned;
	
	// 车型
	private String carTypeInc;
	
	// 厂牌
	private String carFactoryInc;
	
	// 新车购置价
	private String carPrice;
	
	// 行驶证状态查询Id号
	private String drvStatusQryId;
	
	// 房屋验证结果
	private String houseChkResult;
	
	// 房屋数据获取时间
	private String houseDataDate;
	
	// 相片比对结果
	private String photoCmpResult;
	
	// 相片相似度
	private String photoSimDeg;
	
	// 是否本人真实最高学历
	private String isHighestEduBkg;
	
	// 数据获取时间
	private String dataDate;
	
	// 毕业院校
	private String graduateSchool;
	
	// 毕业专业
	private String graduateMajor;
	
	// 毕业年份
	private String graduateYear;
	
	// 手机验证2结果
	private String isOwnerMobile2;
	
	// 手机状态2
	private String ownerMobileStatus2;
	
	// 使用时间分数2;
	private String useTimeScore2;
	
	// 错误信息
	private String errorInfo;
	
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

	public String getIsRealIdentity() {
		return isRealIdentity;
	}

	public void setIsRealIdentity(String isRealIdentity) {
		this.isRealIdentity = isRealIdentity;
	}

	public String getIsValidAddress() {
		return isValidAddress;
	}

	public void setIsValidAddress(String isValidAddress) {
		this.isValidAddress = isValidAddress;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getIsRealCompany() {
		return isRealCompany;
	}

	public void setIsRealCompany(String isRealCompany) {
		this.isRealCompany = isRealCompany;
	}

	public String getCompanySimDeg() {
		return companySimDeg;
	}

	public void setCompanySimDeg(String companySimDeg) {
		this.companySimDeg = companySimDeg;
	}

	public String getAppear1ThDate() {
		return appear1ThDate;
	}

	public void setAppear1ThDate(String appear1ThDate) {
		this.appear1ThDate = appear1ThDate;
	}

	public String getAppearLastDate() {
		return appearLastDate;
	}

	public void setAppearLastDate(String appearLastDate) {
		this.appearLastDate = appearLastDate;
	}

	public String getIsOwnerMobile() {
		return isOwnerMobile;
	}

	public void setIsOwnerMobile(String isOwnerMobile) {
		this.isOwnerMobile = isOwnerMobile;
	}

	public String getOwnerMobileStatus() {
		return ownerMobileStatus;
	}

	public void setOwnerMobileStatus(String ownerMobileStatus) {
		this.ownerMobileStatus = ownerMobileStatus;
	}

	public String getUseTimeScore() {
		return useTimeScore;
	}

	public void setUseTimeScore(String useTimeScore) {
		this.useTimeScore = useTimeScore;
	}

	public String getIsExistRel() {
		return isExistRel;
	}

	public void setIsExistRel(String isExistRel) {
		this.isExistRel = isExistRel;
	}

	public String getRelLevel() {
		return relLevel;
	}

	public void setRelLevel(String relLevel) {
		this.relLevel = relLevel;
	}

	public String getCarChkResult() {
		return carChkResult;
	}

	public void setCarChkResult(String carChkResult) {
		this.carChkResult = carChkResult;
	}

	public String getIsAsyned() {
		return isAsyned;
	}

	public void setIsAsyned(String isAsyned) {
		this.isAsyned = isAsyned;
	}

	public String getCarTypeInc() {
		return carTypeInc;
	}

	public void setCarTypeInc(String carTypeInc) {
		this.carTypeInc = carTypeInc;
	}

	public String getCarFactoryInc() {
		return carFactoryInc;
	}

	public void setCarFactoryInc(String carFactoryInc) {
		this.carFactoryInc = carFactoryInc;
	}

	public String getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(String carPrice) {
		this.carPrice = carPrice;
	}

	public String getDrvStatusQryId() {
		return drvStatusQryId;
	}

	public void setDrvStatusQryId(String drvStatusQryId) {
		this.drvStatusQryId = drvStatusQryId;
	}

	public String getHouseChkResult() {
		return houseChkResult;
	}

	public void setHouseChkResult(String houseChkResult) {
		this.houseChkResult = houseChkResult;
	}

	public String getHouseDataDate() {
		return houseDataDate;
	}

	public void setHouseDataDate(String houseDataDate) {
		this.houseDataDate = houseDataDate;
	}

	public String getPhotoCmpResult() {
		return photoCmpResult;
	}

	public void setPhotoCmpResult(String photoCmpResult) {
		this.photoCmpResult = photoCmpResult;
	}

	public String getPhotoSimDeg() {
		return photoSimDeg;
	}

	public void setPhotoSimDeg(String photoSimDeg) {
		this.photoSimDeg = photoSimDeg;
	}

	public String getIsHighestEduBkg() {
		return isHighestEduBkg;
	}

	public void setIsHighestEduBkg(String isHighestEduBkg) {
		this.isHighestEduBkg = isHighestEduBkg;
	}

	public String getDataDate() {
		return dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getGraduateMajor() {
		return graduateMajor;
	}

	public void setGraduateMajor(String graduateMajor) {
		this.graduateMajor = graduateMajor;
	}

	public String getGraduateYear() {
		return graduateYear;
	}

	public void setGraduateYear(String graduateYear) {
		this.graduateYear = graduateYear;
	}

	public String getIsOwnerMobile2() {
		return isOwnerMobile2;
	}

	public void setIsOwnerMobile2(String isOwnerMobile2) {
		this.isOwnerMobile2 = isOwnerMobile2;
	}

	public String getOwnerMobileStatus2() {
		return ownerMobileStatus2;
	}

	public void setOwnerMobileStatus2(String ownerMobileStatus2) {
		this.ownerMobileStatus2 = ownerMobileStatus2;
	}

	public String getUseTimeScore2() {
		return useTimeScore2;
	}

	public void setUseTimeScore2(String useTimeScore2) {
		this.useTimeScore2 = useTimeScore2;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getReasonNo() {
		return reasonNo;
	}

	public void setReasonNo(String reasonNo) {
		this.reasonNo = reasonNo;
	}

}
