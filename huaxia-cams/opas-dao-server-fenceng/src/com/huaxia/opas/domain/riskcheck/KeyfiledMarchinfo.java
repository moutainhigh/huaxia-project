package com.huaxia.opas.domain.riskcheck;

import java.io.Serializable;
import java.util.Date;

public class KeyfiledMarchinfo  implements Serializable{
	
	private static final long serialVersionUID = -3052220375615827320L;

	private String autoId;

    private String appId;

    private String marchNode;

    private Date crtDate;

    private String crtUser;

    private String marchResult;
    
    private String aml;
    
    private String is_life_cust;
    
    
    public String getIs_life_cust() {
		return is_life_cust;
	}

	public void setIs_life_cust(String is_life_cust) {
		this.is_life_cust = is_life_cust;
	}

	public String getAml() {
		return aml;
	}

	public void setAml(String aml) {
		this.aml = aml;
	}

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

    public String getMarchNode() {
        return marchNode;
    }

    public void setMarchNode(String marchNode) {
        this.marchNode = marchNode == null ? null : marchNode.trim();
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser == null ? null : crtUser.trim();
    }

    public String getMarchResult() {
        return marchResult;
    }

    public void setMarchResult(String marchResult) {
        this.marchResult = marchResult == null ? null : marchResult.trim();
    }
}