package com.huaxia.opas.domain.allot;

import java.util.Date;

public class AutoApply {
    private String autoId;

    private String appId;

    private String currNode;

    private String ydjFlag;

    private Date crtDate;

    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId == null ? null : autoId.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getCurrNode() {
        return currNode;
    }

    public void setCurrNode(String currNode) {
        this.currNode = currNode == null ? null : currNode.trim();
    }

    public String getYdjFlag() {
        return ydjFlag;
    }

    public void setYdjFlag(String ydjFlag) {
        this.ydjFlag = ydjFlag == null ? null : ydjFlag.trim();
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }
}