package com.huaxia.opas.domain.allot;

import java.io.Serializable;
import java.util.Date;
/**
 * 分配规则域
 * @author wangdebin
 */
public class AllotRuleField implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5263612369239440070L;
	//规则代码(主键)
	private String ruleCode;
	//流水号字母
	private String serialNum;
	
	private String appRegion;

	private String appChannel;

	private String appInput;

	private String appProd;

	private String shareTime;
	
	private Date appTime;
	
	private Date crtTime;
	
	private String nosafePromoter;
	
	private String c1CoName;
	
	private String c4CyCadd1;
	
	private String c4ApBatch;
	
	private String c5AbCode;
	
	private String c4AbUser;
	
	private String minIvsScore;
	
	private String maxIvsScore;
	
	private String minZmScore;
	
	private String maxZmScore;
	
	private String minNumberRead;
	
	private String maxNumberRead;
	
	private String appStatus;
	
	private String identify;
	
	private String isHaveCardCust;
	
	private String quickCardFlag;
	
	private String pubPolice;
	
	private String creditDecisionLayer;
	
	private String creditBzkResult;

	private String minProposedLmt;
	
	private String maxProposedLmt;
	
	private String allotType;
	
	private String certNoList;
	
	private String name;
	
	private String emailList;
	
	private String mobileList;
	
	private String addressList;
	
	private String pbocRstFinDesp;
	
	private String batchLabel;
	
	private String appStatus2;

	public String getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getAppRegion() {
		return appRegion;
	}

	public void setAppRegion(String appRegion) {
		this.appRegion = appRegion;
	}

	public String getAppChannel() {
		return appChannel;
	}

	public void setAppChannel(String appChannel) {
		this.appChannel = appChannel;
	}

	public String getAppInput() {
		return appInput;
	}

	public void setAppInput(String appInput) {
		this.appInput = appInput;
	}

	public String getAppProd() {
		return appProd;
	}

	public void setAppProd(String appProd) {
		this.appProd = appProd;
	}

	public String getShareTime() {
		return shareTime;
	}

	public void setShareTime(String shareTime) {
		this.shareTime = shareTime;
	}

	public Date getAppTime() {
		return appTime;
	}

	public void setAppTime(Date appTime) {
		this.appTime = appTime;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public String getNosafePromoter() {
		return nosafePromoter;
	}

	public void setNosafePromoter(String nosafePromoter) {
		this.nosafePromoter = nosafePromoter;
	}

	public String getC1CoName() {
		return c1CoName;
	}

	public void setC1CoName(String c1CoName) {
		this.c1CoName = c1CoName;
	}

	public String getC4CyCadd1() {
		return c4CyCadd1;
	}

	public void setC4CyCadd1(String c4CyCadd1) {
		this.c4CyCadd1 = c4CyCadd1;
	}

	public String getC4ApBatch() {
		return c4ApBatch;
	}

	public void setC4ApBatch(String c4ApBatch) {
		this.c4ApBatch = c4ApBatch;
	}

	public String getC5AbCode() {
		return c5AbCode;
	}

	public void setC5AbCode(String c5AbCode) {
		this.c5AbCode = c5AbCode;
	}

	public String getC4AbUser() {
		return c4AbUser;
	}

	public void setC4AbUser(String c4AbUser) {
		this.c4AbUser = c4AbUser;
	}

	public String getMinIvsScore() {
		return minIvsScore;
	}

	public void setMinIvsScore(String minIvsScore) {
		this.minIvsScore = minIvsScore;
	}

	public String getMaxIvsScore() {
		return maxIvsScore;
	}

	public void setMaxIvsScore(String maxIvsScore) {
		this.maxIvsScore = maxIvsScore;
	}

	public String getMinZmScore() {
		return minZmScore;
	}

	public void setMinZmScore(String minZmScore) {
		this.minZmScore = minZmScore;
	}

	public String getMaxZmScore() {
		return maxZmScore;
	}

	public void setMaxZmScore(String maxZmScore) {
		this.maxZmScore = maxZmScore;
	}

	public String getMinNumberRead() {
		return minNumberRead;
	}

	public void setMinNumberRead(String minNumberRead) {
		this.minNumberRead = minNumberRead;
	}

	public String getMaxNumberRead() {
		return maxNumberRead;
	}

	public void setMaxNumberRead(String maxNumberRead) {
		this.maxNumberRead = maxNumberRead;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getIsHaveCardCust() {
		return isHaveCardCust;
	}

	public void setIsHaveCardCust(String isHaveCardCust) {
		this.isHaveCardCust = isHaveCardCust;
	}

	public String getQuickCardFlag() {
		return quickCardFlag;
	}

	public void setQuickCardFlag(String quickCardFlag) {
		this.quickCardFlag = quickCardFlag;
	}

	public String getPubPolice() {
		return pubPolice;
	}

	public void setPubPolice(String pubPolice) {
		this.pubPolice = pubPolice;
	}

	public String getCreditDecisionLayer() {
		return creditDecisionLayer;
	}

	public void setCreditDecisionLayer(String creditDecisionLayer) {
		this.creditDecisionLayer = creditDecisionLayer;
	}

	public String getCreditBzkResult() {
		return creditBzkResult;
	}

	public void setCreditBzkResult(String creditBzkResult) {
		this.creditBzkResult = creditBzkResult;
	}

	public String getMinProposedLmt() {
		return minProposedLmt;
	}

	public void setMinProposedLmt(String minProposedLmt) {
		this.minProposedLmt = minProposedLmt;
	}

	public String getMaxProposedLmt() {
		return maxProposedLmt;
	}

	public void setMaxProposedLmt(String maxProposedLmt) {
		this.maxProposedLmt = maxProposedLmt;
	}

	public String getAllotType() {
		return allotType;
	}

	public void setAllotType(String allotType) {
		this.allotType = allotType;
	}

	public String getCertNoList() {
		return certNoList;
	}

	public void setCertNoList(String certNoList) {
		this.certNoList = certNoList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailList() {
		return emailList;
	}

	public void setEmailList(String emailList) {
		this.emailList = emailList;
	}

	public String getMobileList() {
		return mobileList;
	}

	public void setMobileList(String mobileList) {
		this.mobileList = mobileList;
	}

	public String getAddressList() {
		return addressList;
	}

	public void setAddressList(String addressList) {
		this.addressList = addressList;
	}

	public String getPbocRstFinDesp() {
		return pbocRstFinDesp;
	}

	public void setPbocRstFinDesp(String pbocRstFinDesp) {
		this.pbocRstFinDesp = pbocRstFinDesp;
	}

	public String getBatchLabel() {
		return batchLabel;
	}

	public void setBatchLabel(String batchLabel) {
		this.batchLabel = batchLabel;
	}

	public String getAppStatus2() {
		return appStatus2;
	}

	public void setAppStatus2(String appStatus2) {
		this.appStatus2 = appStatus2;
	}
	
	
}
