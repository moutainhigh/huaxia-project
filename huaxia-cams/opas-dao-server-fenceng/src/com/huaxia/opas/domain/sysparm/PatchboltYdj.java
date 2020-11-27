package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

public class PatchboltYdj implements Serializable {
	private static final long serialVersionUID = 7537514792629445337L;

	private String autoId;

    private String appId;

    private String patchCode;

    private String identityCard;

    private String payWater;

    private String workProof;

    private String incomeProof;

    private String socialSecurity;

    private String houseProof;

    private String accumulationFund;

    private String educationProof;

    private String taxReceipt;

    private String creditCardBill;

    private String ownerBankProof;

    private String financalProof;

    private String loanSettleProof;

    private String rprProof;

    private String marriageCertificate;

    private String businessLicense;

    private String carProof;

    private String retricalMasterCopy;

    private String imageRepair;

    private String other;

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
    
    private String workerOrder;
    
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

    public String getPayWater() {
        return payWater;
    }

    public void setPayWater(String payWater) {
        this.payWater = payWater == null ? null : payWater.trim();
    }

    public String getWorkProof() {
        return workProof;
    }

    public void setWorkProof(String workProof) {
        this.workProof = workProof == null ? null : workProof.trim();
    }

    public String getIncomeProof() {
        return incomeProof;
    }

    public void setIncomeProof(String incomeProof) {
        this.incomeProof = incomeProof == null ? null : incomeProof.trim();
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity == null ? null : socialSecurity.trim();
    }

    public String getHouseProof() {
        return houseProof;
    }

    public void setHouseProof(String houseProof) {
        this.houseProof = houseProof == null ? null : houseProof.trim();
    }

    public String getAccumulationFund() {
        return accumulationFund;
    }

    public void setAccumulationFund(String accumulationFund) {
        this.accumulationFund = accumulationFund == null ? null : accumulationFund.trim();
    }

    public String getEducationProof() {
        return educationProof;
    }

    public void setEducationProof(String educationProof) {
        this.educationProof = educationProof == null ? null : educationProof.trim();
    }

    public String getTaxReceipt() {
        return taxReceipt;
    }

    public void setTaxReceipt(String taxReceipt) {
        this.taxReceipt = taxReceipt == null ? null : taxReceipt.trim();
    }

    public String getCreditCardBill() {
        return creditCardBill;
    }

    public void setCreditCardBill(String creditCardBill) {
        this.creditCardBill = creditCardBill == null ? null : creditCardBill.trim();
    }

    public String getOwnerBankProof() {
        return ownerBankProof;
    }

    public void setOwnerBankProof(String ownerBankProof) {
        this.ownerBankProof = ownerBankProof == null ? null : ownerBankProof.trim();
    }

    public String getFinancalProof() {
        return financalProof;
    }

    public void setFinancalProof(String financalProof) {
        this.financalProof = financalProof == null ? null : financalProof.trim();
    }

    public String getLoanSettleProof() {
        return loanSettleProof;
    }

    public void setLoanSettleProof(String loanSettleProof) {
        this.loanSettleProof = loanSettleProof == null ? null : loanSettleProof.trim();
    }

    public String getRprProof() {
        return rprProof;
    }

    public void setRprProof(String rprProof) {
        this.rprProof = rprProof == null ? null : rprProof.trim();
    }

    public String getMarriageCertificate() {
        return marriageCertificate;
    }

    public void setMarriageCertificate(String marriageCertificate) {
        this.marriageCertificate = marriageCertificate == null ? null : marriageCertificate.trim();
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    public String getCarProof() {
        return carProof;
    }

    public void setCarProof(String carProof) {
        this.carProof = carProof == null ? null : carProof.trim();
    }

    public String getRetricalMasterCopy() {
        return retricalMasterCopy;
    }

    public void setRetricalMasterCopy(String retricalMasterCopy) {
        this.retricalMasterCopy = retricalMasterCopy == null ? null : retricalMasterCopy.trim();
    }

    public String getImageRepair() {
        return imageRepair;
    }

    public void setImageRepair(String imageRepair) {
        this.imageRepair = imageRepair == null ? null : imageRepair.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
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
}