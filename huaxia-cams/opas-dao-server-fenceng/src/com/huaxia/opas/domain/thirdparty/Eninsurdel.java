package com.huaxia.opas.domain.thirdparty;

import java.util.Date;

public class Eninsurdel {
    private String insideAppNo;

    private String distributedArea;//发放地

    private String retiredType;//离退休类别

    private String retiredDate;//离退休月份

    private String workMonths;//参加工作月份

    private String curMrealPayPens;//本月实发养老金

    private String pausePayReason;//中断或终止缴费原因

    private String oriCompName;//原单位名称

    private String infoUpdTime;//信息更新日期

    private String crtTime;

    private String crtUser;

    private Date lstUpdTime;

    private String lstUpdUser;

    private Date batchDate;

    private String recStatus;

    private String appId;

    public String getInsideAppNo() {
        return insideAppNo;
    }

    public void setInsideAppNo(String insideAppNo) {
        this.insideAppNo = insideAppNo == null ? null : insideAppNo.trim();
    }

    public String getDistributedArea() {
        return distributedArea;
    }

    public void setDistributedArea(String distributedArea) {
        this.distributedArea = distributedArea == null ? null : distributedArea.trim();
    }

    public String getRetiredType() {
        return retiredType;
    }

    public void setRetiredType(String retiredType) {
        this.retiredType = retiredType == null ? null : retiredType.trim();
    }

    public String getRetiredDate() {
        return retiredDate;
    }

    public void setRetiredDate(String retiredDate) {
        this.retiredDate = retiredDate == null ? null : retiredDate.trim();
    }

    public String getWorkMonths() {
        return workMonths;
    }

    public void setWorkMonths(String workMonths) {
        this.workMonths = workMonths == null ? null : workMonths.trim();
    }

    public String getCurMrealPayPens() {
        return curMrealPayPens;
    }

    public void setCurMrealPayPens(String curMrealPayPens) {
        this.curMrealPayPens = curMrealPayPens == null ? null : curMrealPayPens.trim();
    }

    public String getPausePayReason() {
        return pausePayReason;
    }

    public void setPausePayReason(String pausePayReason) {
        this.pausePayReason = pausePayReason == null ? null : pausePayReason.trim();
    }

    public String getOriCompName() {
        return oriCompName;
    }

    public void setOriCompName(String oriCompName) {
        this.oriCompName = oriCompName == null ? null : oriCompName.trim();
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