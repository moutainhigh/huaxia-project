package com.huaxia.opas.domain.thirdparty;
import java.io.Serializable;
/**
 * 企业及行业信息    失信被执行人实体类
 */
import java.util.Date;

public class QiYePunishbreak implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1698300137775112484L;

	private String uuid;

    private String appId;

    private String casestate;

    private String courtname;

    private String disrupttypename;

    private String performance;

    private String publishdateclean;

    private Date crtTime;

    private String duty;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getCasestate() {
        return casestate;
    }

    public void setCasestate(String casestate) {
        this.casestate = casestate == null ? null : casestate.trim();
    }

    public String getCourtname() {
        return courtname;
    }

    public void setCourtname(String courtname) {
        this.courtname = courtname == null ? null : courtname.trim();
    }

    public String getDisrupttypename() {
        return disrupttypename;
    }

    public void setDisrupttypename(String disrupttypename) {
        this.disrupttypename = disrupttypename == null ? null : disrupttypename.trim();
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance == null ? null : performance.trim();
    }

    public String getPublishdateclean() {
        return publishdateclean;
    }

    public void setPublishdateclean(String publishdateclean) {
        this.publishdateclean = publishdateclean == null ? null : publishdateclean.trim();
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty == null ? null : duty.trim();
    }
}