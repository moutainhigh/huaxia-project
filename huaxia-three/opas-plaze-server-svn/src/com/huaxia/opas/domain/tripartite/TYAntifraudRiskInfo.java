package com.huaxia.opas.domain.tripartite;

import java.io.Serializable;
import java.util.Date;

public class TYAntifraudRiskInfo implements Serializable {
   
	private static final long serialVersionUID = 7767482137743311760L;

	private String id;//uuid

    private String appId;//申请件编号

    private String riskCode;//风险码

    private String riskCodeValue;//风险详情值

    private Date crtTime;//创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		 this.riskCode = riskCode == null ? null : riskCode.trim();
	}

	public String getRiskCodeValue() {
		return riskCodeValue;
	}

	public void setRiskCodeValue(String riskCodeValue) {
		 this.riskCodeValue = riskCodeValue == null ? null : riskCodeValue.trim();
	}

	public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }
}