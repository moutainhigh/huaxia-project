package com.huaxia.opas.domain.thirdparty;

import java.util.Date;

public class AdminPunish {
    private String insideAppNo;

    private String punishOrganiz;//处罚机构

    private String punishContent;//处罚内容

    private String punishAmt;//处罚金额

    private String effectDate;//生效日期

    private String endDate;//截止日期

    private String adReviewResult;//行政复议结果

    private String crtTime;

    private String crtUser;

    private Date lstUpdTime;

    private String lstUpdUser;//

    private Date batchDate;//记录状态

    private String recStatus;//记录状态

    private String appId;

    public String getInsideAppNo() {
        return insideAppNo;
    }

    public void setInsideAppNo(String insideAppNo) {
        this.insideAppNo = insideAppNo == null ? null : insideAppNo.trim();
    }

    public String getPunishOrganiz() {
        return punishOrganiz;
    }

    public void setPunishOrganiz(String punishOrganiz) {
        this.punishOrganiz = punishOrganiz == null ? null : punishOrganiz.trim();
    }

    public String getPunishContent() {
        return punishContent;
    }

    public void setPunishContent(String punishContent) {
        this.punishContent = punishContent == null ? null : punishContent.trim();
    }

    public String getPunishAmt() {
        return punishAmt;
    }

    public void setPunishAmt(String punishAmt) {
        this.punishAmt = punishAmt == null ? null : punishAmt.trim();
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate == null ? null : effectDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public String getAdReviewResult() {
        return adReviewResult;
    }

    public void setAdReviewResult(String adReviewResult) {
        this.adReviewResult = adReviewResult == null ? null : adReviewResult.trim();
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