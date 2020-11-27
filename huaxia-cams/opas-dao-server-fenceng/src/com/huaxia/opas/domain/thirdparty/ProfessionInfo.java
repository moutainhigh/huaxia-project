package com.huaxia.opas.domain.thirdparty;

import java.io.Serializable;
import java.util.Date;

public class ProfessionInfo implements Serializable{
	private static final long serialVersionUID = -2159531929639696330L;

	private String insideAppNo;

    private String company;

    private String compAddr;

    private String job;

    private String industryType;

    private String occupationCode;

    private String technicalTitle;

    private String curCompWorkStatY;

    private String infoUpdTime;

    private String crtTime;

    private String crtUser;

    private Date lstUpdTime;

    private String lstUpdUser;

    private Date batchDate;

    private String recStatus;

    private String appId;
    private String unitPhone;

    public String getUnitPhone() {
		return unitPhone;
	}

	public void setUnitPhone(String unitPhone) {
		this.unitPhone = unitPhone;
	}

	public String getInsideAppNo() {
        return insideAppNo;
    }

    public void setInsideAppNo(String insideAppNo) {
        this.insideAppNo = insideAppNo == null ? null : insideAppNo.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getCompAddr() {
        return compAddr;
    }

    public void setCompAddr(String compAddr) {
        this.compAddr = compAddr == null ? null : compAddr.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType == null ? null : industryType.trim();
    }

    public String getOccupationCode() {
        return occupationCode;
    }

    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode == null ? null : occupationCode.trim();
    }

    public String getTechnicalTitle() {
        return technicalTitle;
    }

    public void setTechnicalTitle(String technicalTitle) {
        this.technicalTitle = technicalTitle == null ? null : technicalTitle.trim();
    }

    public String getCurCompWorkStatY() {
        return curCompWorkStatY;
    }

    public void setCurCompWorkStatY(String curCompWorkStatY) {
        this.curCompWorkStatY = curCompWorkStatY == null ? null : curCompWorkStatY.trim();
    }

    public String getInfoUpdTime() {
        return infoUpdTime;
    }

    public void setInfoUpdTime(String infoUpdTime) {
        this.infoUpdTime = infoUpdTime == null ? null : infoUpdTime.trim();
    }

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime == null ? null : crtTime.trim();
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