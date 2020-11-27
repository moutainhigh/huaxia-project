package com.huaxia.opas.domain.thirdparty;

import java.io.Serializable;
import java.util.Date;

public class last5yearOverdue implements Serializable{
    @Override
	public String toString() {
		return "last5yearOverdue [faRecSeq=" + faRecSeq + ", insideAppNo=" + insideAppNo + ", msgType=" + msgType
				+ ", month=" + month + ", lastMonthsStr=" + lastMonthsStr + ", amt=" + amt + ", crtTime=" + crtTime
				+ ", crtUser=" + crtUser + ", lstUpdTime=" + lstUpdTime + ", lstUpdUser=" + lstUpdUser + ", batchDate="
				+ batchDate + ", recStatus=" + recStatus + ", appId=" + appId + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7390040122708403999L;

	private String faRecSeq;

    private String insideAppNo;

    private String msgType;

    private String month;

    private String lastMonthsStr;

    private String amt;

    private String crtTime;

    private String crtUser;

    private Date lstUpdTime;

    private String lstUpdUser;

    private Date batchDate;

    private String recStatus;

    private String appId;

    public String getFaRecSeq() {
        return faRecSeq;
    }

    public void setFaRecSeq(String faRecSeq) {
        this.faRecSeq = faRecSeq == null ? null : faRecSeq.trim();
    }

    public String getInsideAppNo() {
        return insideAppNo;
    }

    public void setInsideAppNo(String insideAppNo) {
        this.insideAppNo = insideAppNo == null ? null : insideAppNo.trim();
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getLastMonthsStr() {
        return lastMonthsStr;
    }

    public void setLastMonthsStr(String lastMonthsStr) {
        this.lastMonthsStr = lastMonthsStr == null ? null : lastMonthsStr.trim();
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt == null ? null : amt.trim();
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