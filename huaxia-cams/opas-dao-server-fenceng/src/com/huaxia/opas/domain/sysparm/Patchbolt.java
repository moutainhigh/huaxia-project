package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

public class Patchbolt implements Serializable {
	
	private static final long serialVersionUID = 4922700002556649285L;

	private String autoId;

    private String appId;

    private String patchCode;

    private String identityCard;

    private String incomeConfirm;

    private String drivingLicense;

    private String otherCreditcard;

    private String others;

    private String isHavesign;

    private String liveConfirm;

    private String eduConfirm;

    private String ownerBankConfirm;

    private String nonAddfiles;

    private String workConfirm;

    private String houseConfirm;

    private String titleConfirm;

    private String financalConfirm;

    private Date crtDate;

    private String crtUser;
    
    private String memo;

    private String applyer;
    
    private String promoter;
    
    private String risk;
    
    private Date dueDate;
    
    private String optTelno;
    
    private String msgFlag;
    
    private String sendFlag;
    
    private String completedFlag;
    //工单
    private String workerOrder;
    //VIP项
    private String vipAttr;
    public String getWorkerOrder() {
		return workerOrder;
	}

	public void setWorkerOrder(String workerOrder) {
		this.workerOrder = workerOrder;
	}

	public String getVipAttr() {
		return vipAttr;
	}

	public void setVipAttr(String vipAttr) {
		this.vipAttr = vipAttr;
	}

	public String getCompletedFlag() {
		return completedFlag;
	}

	public void setCompletedFlag(String completedFlag) {
		this.completedFlag = completedFlag;
	}

	public String getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}

	public String getMsgFlag() {
		return msgFlag;
	}

	public void setMsgFlag(String msgFlag) {
		this.msgFlag = msgFlag == null ? null : msgFlag.trim();
	}

	public String getOptTelno() {
		return optTelno;
	}

	public void setOptTelno(String optTelno) {
		this.optTelno = optTelno == null ? null : optTelno.trim();
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	public String getApplyer() {
		return applyer;
	}

	public void setApplyer(String applyer) {
		this.applyer = applyer == null ? null : applyer.trim();
	}

	public String getPromoter() {
		return promoter;
	}

	public void setPromoter(String promoter) {
		this.promoter = promoter == null ? null : promoter.trim();
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk == null ? null : risk.trim();
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

    public String getPatchCode() {
        return patchCode;
    }

    public void setPatchCode(String patchCode) {
        this.patchCode = patchCode == null ? null : patchCode.trim();
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard == null ? null : identityCard.trim();
    }

    public String getIncomeConfirm() {
        return incomeConfirm;
    }

    public void setIncomeConfirm(String incomeConfirm) {
        this.incomeConfirm = incomeConfirm == null ? null : incomeConfirm.trim();
    }

    public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense == null ? null : drivingLicense.trim();
	}

	public String getOtherCreditcard() {
        return otherCreditcard;
    }

    public void setOtherCreditcard(String otherCreditcard) {
        this.otherCreditcard = otherCreditcard == null ? null : otherCreditcard.trim();
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others == null ? null : others.trim();
    }

    public String getIsHavesign() {
        return isHavesign;
    }

    public void setIsHavesign(String isHavesign) {
        this.isHavesign = isHavesign == null ? null : isHavesign.trim();
    }

    public String getLiveConfirm() {
        return liveConfirm;
    }

    public void setLiveConfirm(String liveConfirm) {
        this.liveConfirm = liveConfirm == null ? null : liveConfirm.trim();
    }

    public String getEduConfirm() {
        return eduConfirm;
    }

    public void setEduConfirm(String eduConfirm) {
        this.eduConfirm = eduConfirm == null ? null : eduConfirm.trim();
    }

    public String getOwnerBankConfirm() {
        return ownerBankConfirm;
    }

    public void setOwnerBankConfirm(String ownerBankConfirm) {
        this.ownerBankConfirm = ownerBankConfirm == null ? null : ownerBankConfirm.trim();
    }

    public String getNonAddfiles() {
        return nonAddfiles;
    }

    public void setNonAddfiles(String nonAddfiles) {
        this.nonAddfiles = nonAddfiles == null ? null : nonAddfiles.trim();
    }

    public String getWorkConfirm() {
        return workConfirm;
    }

    public void setWorkConfirm(String workConfirm) {
        this.workConfirm = workConfirm == null ? null : workConfirm.trim();
    }

    public String getHouseConfirm() {
        return houseConfirm;
    }

    public void setHouseConfirm(String houseConfirm) {
        this.houseConfirm = houseConfirm == null ? null : houseConfirm.trim();
    }

    public String getTitleConfirm() {
        return titleConfirm;
    }

    public void setTitleConfirm(String titleConfirm) {
        this.titleConfirm = titleConfirm == null ? null : titleConfirm.trim();
    }

    public String getFinancalConfirm() {
        return financalConfirm;
    }

    public void setFinancalConfirm(String financalConfirm) {
        this.financalConfirm = financalConfirm == null ? null : financalConfirm.trim();
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

    
	public Patchbolt() {
		super();
	}

	public Patchbolt(String autoId, String appId, String patchCode, String identityCard, String incomeConfirm,
			String drivingLicense, String otherCreditcard, String others, String isHavesign, String liveConfirm,
			String eduConfirm, String ownerBankConfirm, String nonAddfiles, String workConfirm, String houseConfirm,
			String titleConfirm, String financalConfirm, Date crtDate, String crtUser, String memo, String applyer,
			String promoter, String risk, Date dueDate, String optTelno) {
		super();
		this.autoId = autoId;
		this.appId = appId;
		this.patchCode = patchCode;
		this.identityCard = identityCard;
		this.incomeConfirm = incomeConfirm;
		this.drivingLicense = drivingLicense;
		this.otherCreditcard = otherCreditcard;
		this.others = others;
		this.isHavesign = isHavesign;
		this.liveConfirm = liveConfirm;
		this.eduConfirm = eduConfirm;
		this.ownerBankConfirm = ownerBankConfirm;
		this.nonAddfiles = nonAddfiles;
		this.workConfirm = workConfirm;
		this.houseConfirm = houseConfirm;
		this.titleConfirm = titleConfirm;
		this.financalConfirm = financalConfirm;
		this.crtDate = crtDate;
		this.crtUser = crtUser;
		this.memo = memo;
		this.applyer = applyer;
		this.promoter = promoter;
		this.risk = risk;
		this.dueDate = dueDate;
		this.optTelno = optTelno;
	}

	@Override
	public String toString() {
		return "Patchbolt [autoId=" + autoId + ", appId=" + appId + ", patchCode=" + patchCode + ", identityCard="
				+ identityCard + ", incomeConfirm=" + incomeConfirm + ", drivingLicense=" + drivingLicense
				+ ", otherCreditcard=" + otherCreditcard + ", others=" + others + ", isHavesign=" + isHavesign
				+ ", liveConfirm=" + liveConfirm + ", eduConfirm=" + eduConfirm + ", ownerBankConfirm="
				+ ownerBankConfirm + ", nonAddfiles=" + nonAddfiles + ", workConfirm=" + workConfirm + ", houseConfirm="
				+ houseConfirm + ", titleConfirm=" + titleConfirm + ", financalConfirm=" + financalConfirm
				+ ", crtDate=" + crtDate + ", crtUser=" + crtUser + ", memo=" + memo + ", applyer=" + applyer
				+ ", promoter=" + promoter + ", risk=" + risk + ", dueDate=" + dueDate + ", optTelno=" + optTelno
				+ ", msgFlag=" + msgFlag + ", sendFlag=" + sendFlag + ", completedFlag=" + completedFlag
				+ ", workerOrder=" + workerOrder + ", vipAttr=" + vipAttr + "]";
	}

}