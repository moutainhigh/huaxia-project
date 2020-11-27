package com.huaxia.opas.domain.rule;

import java.io.Serializable;
import java.util.Date;

public class OpasBizApproval implements Serializable{
    private String appId;

    private String autoId;
    private String idType;
    private String idNo;

    public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	private String refuseChildcard;

    private Long preApprovelimit;

    private String applyerName;

    private String applyCard;

    private String isAgreeDegrade;

    private Date sysDecisionTime;

    private String mastercardDecresult;

    private String mastercardApprovereuslt;

    private String sysCreditProduce;

    private String childcardDecresult;

    private String childcardApprovereuslt;

    private String creditResult;

    private String creditRefuseReason;

    private String warninglistResult;

    private String approveResult;

    private Long approveCreditLimit;

    private String approveCreditProduct;

    private Long manualLimit;

    private String policyCode1;

    private String policyCode2;

    private String policyCode3;

    private String violateCode1;

    private String violateCode2;

    private String violateCode3;

    private String memo;

    private String refuseCode1;

    private String refuseCode2;

    private String refuseCode3;

    private String approver;

    private String approverName;

    private String highlevleApprover;

    private Date highlevleApprovetime;

    private Date submitHighlevleTime;

    private Date approveTime;

    private String currOptGroup;

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

    public String getRefuseChildcard() {
        return refuseChildcard;
    }

    public void setRefuseChildcard(String refuseChildcard) {
        this.refuseChildcard = refuseChildcard == null ? null : refuseChildcard.trim();
    }

    public Long getPreApprovelimit() {
        return preApprovelimit;
    }

    public void setPreApprovelimit(Long preApprovelimit) {
        this.preApprovelimit = preApprovelimit;
    }

    public String getApplyerName() {
        return applyerName;
    }

    public void setApplyerName(String applyerName) {
        this.applyerName = applyerName == null ? null : applyerName.trim();
    }

    public String getApplyCard() {
        return applyCard;
    }

    public void setApplyCard(String applyCard) {
        this.applyCard = applyCard == null ? null : applyCard.trim();
    }

    public String getIsAgreeDegrade() {
        return isAgreeDegrade;
    }

    public void setIsAgreeDegrade(String isAgreeDegrade) {
        this.isAgreeDegrade = isAgreeDegrade == null ? null : isAgreeDegrade.trim();
    }

    public Date getSysDecisionTime() {
        return sysDecisionTime;
    }

    public void setSysDecisionTime(Date sysDecisionTime) {
        this.sysDecisionTime = sysDecisionTime;
    }

    public String getMastercardDecresult() {
        return mastercardDecresult;
    }

    public void setMastercardDecresult(String mastercardDecresult) {
        this.mastercardDecresult = mastercardDecresult == null ? null : mastercardDecresult.trim();
    }

    public String getMastercardApprovereuslt() {
        return mastercardApprovereuslt;
    }

    public void setMastercardApprovereuslt(String mastercardApprovereuslt) {
        this.mastercardApprovereuslt = mastercardApprovereuslt == null ? null : mastercardApprovereuslt.trim();
    }

    public String getSysCreditProduce() {
        return sysCreditProduce;
    }

    public void setSysCreditProduce(String sysCreditProduce) {
        this.sysCreditProduce = sysCreditProduce == null ? null : sysCreditProduce.trim();
    }

    public String getChildcardDecresult() {
        return childcardDecresult;
    }

    public void setChildcardDecresult(String childcardDecresult) {
        this.childcardDecresult = childcardDecresult == null ? null : childcardDecresult.trim();
    }

    public String getChildcardApprovereuslt() {
        return childcardApprovereuslt;
    }

    public void setChildcardApprovereuslt(String childcardApprovereuslt) {
        this.childcardApprovereuslt = childcardApprovereuslt == null ? null : childcardApprovereuslt.trim();
    }

    public String getCreditResult() {
        return creditResult;
    }

    public void setCreditResult(String creditResult) {
        this.creditResult = creditResult == null ? null : creditResult.trim();
    }

    public String getCreditRefuseReason() {
        return creditRefuseReason;
    }

    public void setCreditRefuseReason(String creditRefuseReason) {
        this.creditRefuseReason = creditRefuseReason == null ? null : creditRefuseReason.trim();
    }

    public String getWarninglistResult() {
        return warninglistResult;
    }

    public void setWarninglistResult(String warninglistResult) {
        this.warninglistResult = warninglistResult == null ? null : warninglistResult.trim();
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult == null ? null : approveResult.trim();
    }

    public Long getApproveCreditLimit() {
        return approveCreditLimit;
    }

    public void setApproveCreditLimit(Long approveCreditLimit) {
        this.approveCreditLimit = approveCreditLimit;
    }

    public String getApproveCreditProduct() {
        return approveCreditProduct;
    }

    public void setApproveCreditProduct(String approveCreditProduct) {
        this.approveCreditProduct = approveCreditProduct == null ? null : approveCreditProduct.trim();
    }

    public Long getManualLimit() {
        return manualLimit;
    }

    public void setManualLimit(Long manualLimit) {
        this.manualLimit = manualLimit;
    }

    public String getPolicyCode1() {
        return policyCode1;
    }

    public void setPolicyCode1(String policyCode1) {
        this.policyCode1 = policyCode1 == null ? null : policyCode1.trim();
    }

    public String getPolicyCode2() {
        return policyCode2;
    }

    public void setPolicyCode2(String policyCode2) {
        this.policyCode2 = policyCode2 == null ? null : policyCode2.trim();
    }

    public String getPolicyCode3() {
        return policyCode3;
    }

    public void setPolicyCode3(String policyCode3) {
        this.policyCode3 = policyCode3 == null ? null : policyCode3.trim();
    }

    public String getViolateCode1() {
        return violateCode1;
    }

    public void setViolateCode1(String violateCode1) {
        this.violateCode1 = violateCode1 == null ? null : violateCode1.trim();
    }

    public String getViolateCode2() {
        return violateCode2;
    }

    public void setViolateCode2(String violateCode2) {
        this.violateCode2 = violateCode2 == null ? null : violateCode2.trim();
    }

    public String getViolateCode3() {
        return violateCode3;
    }

    public void setViolateCode3(String violateCode3) {
        this.violateCode3 = violateCode3 == null ? null : violateCode3.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getRefuseCode1() {
        return refuseCode1;
    }

    public void setRefuseCode1(String refuseCode1) {
        this.refuseCode1 = refuseCode1 == null ? null : refuseCode1.trim();
    }

    public String getRefuseCode2() {
        return refuseCode2;
    }

    public void setRefuseCode2(String refuseCode2) {
        this.refuseCode2 = refuseCode2 == null ? null : refuseCode2.trim();
    }

    public String getRefuseCode3() {
        return refuseCode3;
    }

    public void setRefuseCode3(String refuseCode3) {
        this.refuseCode3 = refuseCode3 == null ? null : refuseCode3.trim();
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName == null ? null : approverName.trim();
    }

    public String getHighlevleApprover() {
        return highlevleApprover;
    }

    public void setHighlevleApprover(String highlevleApprover) {
        this.highlevleApprover = highlevleApprover == null ? null : highlevleApprover.trim();
    }

    public Date getHighlevleApprovetime() {
        return highlevleApprovetime;
    }

    public void setHighlevleApprovetime(Date highlevleApprovetime) {
        this.highlevleApprovetime = highlevleApprovetime;
    }

    public Date getSubmitHighlevleTime() {
        return submitHighlevleTime;
    }

    public void setSubmitHighlevleTime(Date submitHighlevleTime) {
        this.submitHighlevleTime = submitHighlevleTime;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getCurrOptGroup() {
        return currOptGroup;
    }

    public void setCurrOptGroup(String currOptGroup) {
        this.currOptGroup = currOptGroup == null ? null : currOptGroup.trim();
    }
}