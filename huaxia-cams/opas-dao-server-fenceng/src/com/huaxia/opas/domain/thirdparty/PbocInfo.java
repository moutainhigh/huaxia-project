package com.huaxia.opas.domain.thirdparty;

import java.io.Serializable;
import java.util.Date;

public class PbocInfo implements Serializable{
    private String insideAppNo;

    private String reportNo;

    private String queryReqTime;

    private String reportTime;

    private String productType;

    private String productFormat;

    private String productVersion;

    private String queryedName;

    private String queryedCertType;

    private String queryedCertNo;

    private String qryReason;

    private String queryOrg;

    private String qryOperator;

    private String queryResultPrompt;

    private String gender;

    private String birthDay;

    private String maritalStatus;

    private String cellPhone;

    private String cCompPhone;

    private String resiTel;

    private String residencyStatus;

    private String degree;

    private String communicationAddr;

    private String domicileAddr;

    private String mateName;

    private String mateCertType;

    private String mateCertNo;

    private String mateCompany;

    private String mateContactTel;

    private Date crtTime;

    private String crtUser;

    private Date lstUpdTime;

    private String lstUpdUser;

    private Date batchDate;

    private String recStatus;

    private String appId;
    
    private String phoneNum;
    
    public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getInsideAppNo() {
        return insideAppNo;
    }

    public void setInsideAppNo(String insideAppNo) {
        this.insideAppNo = insideAppNo == null ? null : insideAppNo.trim();
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo == null ? null : reportNo.trim();
    }

    public String getQueryReqTime() {
        return queryReqTime;
    }

    public void setQueryReqTime(String queryReqTime) {
        this.queryReqTime = queryReqTime == null ? null : queryReqTime.trim();
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime == null ? null : reportTime.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getProductFormat() {
        return productFormat;
    }

    public void setProductFormat(String productFormat) {
        this.productFormat = productFormat == null ? null : productFormat.trim();
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion == null ? null : productVersion.trim();
    }

    public String getQueryedName() {
        return queryedName;
    }

    public void setQueryedName(String queryedName) {
        this.queryedName = queryedName == null ? null : queryedName.trim();
    }

    public String getQueryedCertType() {
        return queryedCertType;
    }

    public void setQueryedCertType(String queryedCertType) {
        this.queryedCertType = queryedCertType == null ? null : queryedCertType.trim();
    }

    public String getQueryedCertNo() {
        return queryedCertNo;
    }

    public void setQueryedCertNo(String queryedCertNo) {
        this.queryedCertNo = queryedCertNo == null ? null : queryedCertNo.trim();
    }

    public String getQryReason() {
        return qryReason;
    }

    public void setQryReason(String qryReason) {
        this.qryReason = qryReason == null ? null : qryReason.trim();
    }

    public String getQueryOrg() {
        return queryOrg;
    }

    public void setQueryOrg(String queryOrg) {
        this.queryOrg = queryOrg == null ? null : queryOrg.trim();
    }

    public String getQryOperator() {
        return qryOperator;
    }

    public void setQryOperator(String qryOperator) {
        this.qryOperator = qryOperator == null ? null : qryOperator.trim();
    }

    public String getQueryResultPrompt() {
        return queryResultPrompt;
    }

    public void setQueryResultPrompt(String queryResultPrompt) {
        this.queryResultPrompt = queryResultPrompt == null ? null : queryResultPrompt.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay == null ? null : birthDay.trim();
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus == null ? null : maritalStatus.trim();
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone == null ? null : cellPhone.trim();
    }

    public String getcCompPhone() {
        return cCompPhone;
    }

    public void setcCompPhone(String cCompPhone) {
        this.cCompPhone = cCompPhone == null ? null : cCompPhone.trim();
    }

    public String getResiTel() {
        return resiTel;
    }

    public void setResiTel(String resiTel) {
        this.resiTel = resiTel == null ? null : resiTel.trim();
    }

    public String getResidencyStatus() {
        return residencyStatus;
    }

    public void setResidencyStatus(String residencyStatus) {
        this.residencyStatus = residencyStatus == null ? null : residencyStatus.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getCommunicationAddr() {
        return communicationAddr;
    }

    public void setCommunicationAddr(String communicationAddr) {
        this.communicationAddr = communicationAddr == null ? null : communicationAddr.trim();
    }

    public String getDomicileAddr() {
        return domicileAddr;
    }

    public void setDomicileAddr(String domicileAddr) {
        this.domicileAddr = domicileAddr == null ? null : domicileAddr.trim();
    }

    public String getMateName() {
        return mateName;
    }

    public void setMateName(String mateName) {
        this.mateName = mateName == null ? null : mateName.trim();
    }

    public String getMateCertType() {
        return mateCertType;
    }

    public void setMateCertType(String mateCertType) {
        this.mateCertType = mateCertType == null ? null : mateCertType.trim();
    }

    public String getMateCertNo() {
        return mateCertNo;
    }

    public void setMateCertNo(String mateCertNo) {
        this.mateCertNo = mateCertNo == null ? null : mateCertNo.trim();
    }

    public String getMateCompany() {
        return mateCompany;
    }

    public void setMateCompany(String mateCompany) {
        this.mateCompany = mateCompany == null ? null : mateCompany.trim();
    }

    public String getMateContactTel() {
        return mateContactTel;
    }

    public void setMateContactTel(String mateContactTel) {
        this.mateContactTel = mateContactTel == null ? null : mateContactTel.trim();
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser == null ? null : crtUser.trim();
    }

    public Date getLstUpdTime() {
        return lstUpdTime;
    }

    public void setLstUpdTime(Date lstUpdTime) {
        this.lstUpdTime = lstUpdTime;
    }

    public String getLstUpdUser() {
        return lstUpdUser;
    }

    public void setLstUpdUser(String lstUpdUser) {
        this.lstUpdUser = lstUpdUser == null ? null : lstUpdUser.trim();
    }

    public Date getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(Date batchDate) {
        this.batchDate = batchDate;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus == null ? null : recStatus.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }
}