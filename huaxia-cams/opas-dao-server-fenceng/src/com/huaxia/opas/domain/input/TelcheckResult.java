package com.huaxia.opas.domain.input;

import java.io.Serializable;
import java.util.Date;

public class TelcheckResult implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8202474542775365564L;

	private String appId;

    private String autoId;

    private String blockCode;

    private String creditRefuseReason;

    private String telcheckResult;

    private String identityResult;

    private Date creditTime;

    private String crediter;

    private Date crtDate;

    private String crtUser;

    private String currOptGroup;

    private String crediterName;
    
    private String ydjFlag;//易达金标识
    
	private String pbocMobileFlag;//手机号码
    
    private String pbocBusphoneFlag; //单位电话
    
    private String pbocPhoneFlag; //住宅电话	
    
    private String pbocCompanyFlag; //单位名称
    
    private String pbocBusaddrFlag; //单位地址
    
    private String pbocHomeaddrFlag; //账单地址
    
    private String verifyinfoFlag; //核实信息 是否
    
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId == null ? null : autoId.trim();
    }

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode == null ? null : blockCode.trim();
    }

    public String getCreditRefuseReason() {
        return creditRefuseReason;
    }

    public void setCreditRefuseReason(String creditRefuseReason) {
        this.creditRefuseReason = creditRefuseReason == null ? null : creditRefuseReason.trim();
    }

    public String getTelcheckResult() {
        return telcheckResult;
    }

    public void setTelcheckResult(String telcheckResult) {
        this.telcheckResult = telcheckResult == null ? null : telcheckResult.trim();
    }

    public String getIdentityResult() {
        return identityResult;
    }

    public void setIdentityResult(String identityResult) {
        this.identityResult = identityResult == null ? null : identityResult.trim();
    }

    public Date getCreditTime() {
        return creditTime;
    }

    public void setCreditTime(Date creditTime) {
        this.creditTime = creditTime;
    }

    public String getCrediter() {
        return crediter;
    }

    public void setCrediter(String crediter) {
        this.crediter = crediter == null ? null : crediter.trim();
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

    public String getCurrOptGroup() {
        return currOptGroup;
    }

    public void setCurrOptGroup(String currOptGroup) {
        this.currOptGroup = currOptGroup == null ? null : currOptGroup.trim();
    }

    public String getCrediterName() {
        return crediterName;
    }

    public void setCrediterName(String crediterName) {
        this.crediterName = crediterName == null ? null : crediterName.trim();
    }

	public String getYdjFlag() {
		return ydjFlag;
	}

	public void setYdjFlag(String ydjFlag) {
		this.ydjFlag = ydjFlag;
	}
	
	public String getPbocMobileFlag() {
		return pbocMobileFlag;
	}

	public void setPbocMobileFlag(String pbocMobileFlag) {
		this.pbocMobileFlag = pbocMobileFlag;
	}

	public String getPbocBusphoneFlag() {
		return pbocBusphoneFlag;
	}

	public void setPbocBusphoneFlag(String pbocBusphoneFlag) {
		this.pbocBusphoneFlag = pbocBusphoneFlag;
	}

	public String getPbocPhoneFlag() {
		return pbocPhoneFlag;
	}

	public void setPbocPhoneFlag(String pbocPhoneFlag) {
		this.pbocPhoneFlag = pbocPhoneFlag;
	}

	public String getPbocCompanyFlag() {
		return pbocCompanyFlag;
	}

	public void setPbocCompanyFlag(String pbocCompanyFlag) {
		this.pbocCompanyFlag = pbocCompanyFlag;
	}

	public String getPbocBusaddrFlag() {
		return pbocBusaddrFlag;
	}

	public void setPbocBusaddrFlag(String pbocBusaddrFlag) {
		this.pbocBusaddrFlag = pbocBusaddrFlag;
	}

	public String getPbocHomeaddrFlag() {
		return pbocHomeaddrFlag;
	}

	public void setPbocHomeaddrFlag(String pbocHomeaddrFlag) {
		this.pbocHomeaddrFlag = pbocHomeaddrFlag;
	}

	public String getVerifyinfoFlag() {
		return verifyinfoFlag;
	}

	public void setVerifyinfoFlag(String verifyinfoFlag) {
		this.verifyinfoFlag = verifyinfoFlag;
	}

    
}