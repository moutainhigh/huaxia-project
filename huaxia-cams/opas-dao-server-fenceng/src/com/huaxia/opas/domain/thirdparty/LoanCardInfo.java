package com.huaxia.opas.domain.thirdparty;

import java.io.Serializable;
import java.util.Date;

public class LoanCardInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 608526725870523553L;

	private String insideAppNo;

    private String basicInfoPrompt;//基本信息提示

    private String loanOrCreditOrg;//贷款/发卡_机构

    private String loanOrCreditOrgType;//贷款/发卡_机构类型

    private String loanOrCreditBusiNo;//贷款/发卡_业务号

    private String loanLoanTypeSeg;//贷款_贷款种类细分

    private String loanOrCreditCcy;//贷款/发卡_币种

    private String loanOrCreditIssuDate;//贷款/发卡_发放/发卡日期

    private String loanExpDate;//贷款_到期日期

    private String loanOrCreditContAmt;//贷款/发卡_合同金额/授信额度

    private String loanOrCreditGuaranteeMode;//贷款/发卡_担保方式

    private String loanPaymentFreq;//贷款_还款频率

    private String loanPaymentCycNum;//贷款_还款期数

    private String loanOrCreditAcctStatus;//贷款/发卡_账户状态

    private String accStateEndDate;//账户_状态截止日

    private String accClass5state;//账户_五级分类

    private String accCorpusBal;//账户_本金余额

    private String accRemPaymentCycNum;//账户_剩余还款期数

    private String accCurMduePayBal;//账户_本月应还款

    private String accDuePaymentDate;//账户_应还款日/账单日

    private String accCurMrealPayAmt;//账户_本月实还款

    private String accLatestPaymentDate;//账户_最近一次还款日期

    private String overDueCurOverDueNum;//逾期_当前逾期期数

    private String overDueCurOverDueAmt;//逾期_当前逾期金额

    private String overDueUnPayCor31to60d;//逾期_逾期31-60天未归还贷款本金

    private String overDueUnPayCor61to90d;//

    private String overDueUnPayCor91to180;//

    private String overDueUnPayCor180dplus;//

    private String m24PaymentStrMonth;//24个月_还款起始月

    private String m24PaymentEndMonth;//24个月_还款截止月

    private String m24PaymentStatus;//24个月_还款状态

    private String m60OverDueStrMonth;//

    private String m60OverDueEndMonth;//

    private String crtTime;

    private String crtUser;

    private Date lstUpdTime;

    private String lstUpdUser;

    private Date batchDate;

    private String recStatus;

    private String recSeq;

    private String messType;

    private String debCardPayOverdraftAmt;

    private String debCardPayShareLimit;

    private String debCardPay6mavgUsedAmt;

    private String debCardPayMaxUsedBal;

    private String appId;

    public String getInsideAppNo() {
        return insideAppNo;
    }

    public void setInsideAppNo(String insideAppNo) {
        this.insideAppNo = insideAppNo == null ? null : insideAppNo.trim();
    }

    public String getBasicInfoPrompt() {
        return basicInfoPrompt;
    }

    public void setBasicInfoPrompt(String basicInfoPrompt) {
        this.basicInfoPrompt = basicInfoPrompt == null ? null : basicInfoPrompt.trim();
    }

    public String getLoanOrCreditOrg() {
        return loanOrCreditOrg;
    }

    public void setLoanOrCreditOrg(String loanOrCreditOrg) {
        this.loanOrCreditOrg = loanOrCreditOrg == null ? null : loanOrCreditOrg.trim();
    }

    public String getLoanOrCreditOrgType() {
        return loanOrCreditOrgType;
    }

    public void setLoanOrCreditOrgType(String loanOrCreditOrgType) {
        this.loanOrCreditOrgType = loanOrCreditOrgType == null ? null : loanOrCreditOrgType.trim();
    }

    public String getLoanOrCreditBusiNo() {
        return loanOrCreditBusiNo;
    }

    public void setLoanOrCreditBusiNo(String loanOrCreditBusiNo) {
        this.loanOrCreditBusiNo = loanOrCreditBusiNo == null ? null : loanOrCreditBusiNo.trim();
    }

    public String getLoanLoanTypeSeg() {
        return loanLoanTypeSeg;
    }

    public void setLoanLoanTypeSeg(String loanLoanTypeSeg) {
        this.loanLoanTypeSeg = loanLoanTypeSeg == null ? null : loanLoanTypeSeg.trim();
    }

    public String getLoanOrCreditCcy() {
        return loanOrCreditCcy;
    }

    public void setLoanOrCreditCcy(String loanOrCreditCcy) {
        this.loanOrCreditCcy = loanOrCreditCcy == null ? null : loanOrCreditCcy.trim();
    }

    public String getLoanOrCreditIssuDate() {
        return loanOrCreditIssuDate;
    }

    public void setLoanOrCreditIssuDate(String loanOrCreditIssuDate) {
        this.loanOrCreditIssuDate = loanOrCreditIssuDate == null ? null : loanOrCreditIssuDate.trim();
    }

    public String getLoanExpDate() {
        return loanExpDate;
    }

    public void setLoanExpDate(String loanExpDate) {
        this.loanExpDate = loanExpDate == null ? null : loanExpDate.trim();
    }

    public String getLoanOrCreditContAmt() {
        return loanOrCreditContAmt;
    }

    public void setLoanOrCreditContAmt(String loanOrCreditContAmt) {
        this.loanOrCreditContAmt = loanOrCreditContAmt == null ? null : loanOrCreditContAmt.trim();
    }

    public String getLoanOrCreditGuaranteeMode() {
        return loanOrCreditGuaranteeMode;
    }

    public void setLoanOrCreditGuaranteeMode(String loanOrCreditGuaranteeMode) {
        this.loanOrCreditGuaranteeMode = loanOrCreditGuaranteeMode == null ? null : loanOrCreditGuaranteeMode.trim();
    }

    public String getLoanPaymentFreq() {
        return loanPaymentFreq;
    }

    public void setLoanPaymentFreq(String loanPaymentFreq) {
        this.loanPaymentFreq = loanPaymentFreq == null ? null : loanPaymentFreq.trim();
    }

    public String getLoanPaymentCycNum() {
        return loanPaymentCycNum;
    }

    public void setLoanPaymentCycNum(String loanPaymentCycNum) {
        this.loanPaymentCycNum = loanPaymentCycNum == null ? null : loanPaymentCycNum.trim();
    }

    public String getLoanOrCreditAcctStatus() {
        return loanOrCreditAcctStatus;
    }

    public void setLoanOrCreditAcctStatus(String loanOrCreditAcctStatus) {
        this.loanOrCreditAcctStatus = loanOrCreditAcctStatus == null ? null : loanOrCreditAcctStatus.trim();
    }

    public String getAccStateEndDate() {
        return accStateEndDate;
    }

    public void setAccStateEndDate(String accStateEndDate) {
        this.accStateEndDate = accStateEndDate == null ? null : accStateEndDate.trim();
    }

    public String getAccClass5state() {
        return accClass5state;
    }

    public void setAccClass5state(String accClass5state) {
        this.accClass5state = accClass5state == null ? null : accClass5state.trim();
    }

    public String getAccCorpusBal() {
        return accCorpusBal;
    }

    public void setAccCorpusBal(String accCorpusBal) {
        this.accCorpusBal = accCorpusBal == null ? null : accCorpusBal.trim();
    }

    public String getAccRemPaymentCycNum() {
        return accRemPaymentCycNum;
    }

    public void setAccRemPaymentCycNum(String accRemPaymentCycNum) {
        this.accRemPaymentCycNum = accRemPaymentCycNum == null ? null : accRemPaymentCycNum.trim();
    }

    public String getAccCurMduePayBal() {
        return accCurMduePayBal;
    }

    public void setAccCurMduePayBal(String accCurMduePayBal) {
        this.accCurMduePayBal = accCurMduePayBal == null ? null : accCurMduePayBal.trim();
    }

    public String getAccDuePaymentDate() {
        return accDuePaymentDate;
    }

    public void setAccDuePaymentDate(String accDuePaymentDate) {
        this.accDuePaymentDate = accDuePaymentDate == null ? null : accDuePaymentDate.trim();
    }

    public String getAccCurMrealPayAmt() {
        return accCurMrealPayAmt;
    }

    public void setAccCurMrealPayAmt(String accCurMrealPayAmt) {
        this.accCurMrealPayAmt = accCurMrealPayAmt == null ? null : accCurMrealPayAmt.trim();
    }

    public String getAccLatestPaymentDate() {
        return accLatestPaymentDate;
    }

    public void setAccLatestPaymentDate(String accLatestPaymentDate) {
        this.accLatestPaymentDate = accLatestPaymentDate == null ? null : accLatestPaymentDate.trim();
    }

    public String getOverDueCurOverDueNum() {
        return overDueCurOverDueNum;
    }

    public void setOverDueCurOverDueNum(String overDueCurOverDueNum) {
        this.overDueCurOverDueNum = overDueCurOverDueNum == null ? null : overDueCurOverDueNum.trim();
    }

    public String getOverDueCurOverDueAmt() {
        return overDueCurOverDueAmt;
    }

    public void setOverDueCurOverDueAmt(String overDueCurOverDueAmt) {
        this.overDueCurOverDueAmt = overDueCurOverDueAmt == null ? null : overDueCurOverDueAmt.trim();
    }

    public String getOverDueUnPayCor31to60d() {
        return overDueUnPayCor31to60d;
    }

    public void setOverDueUnPayCor31to60d(String overDueUnPayCor31to60d) {
        this.overDueUnPayCor31to60d = overDueUnPayCor31to60d == null ? null : overDueUnPayCor31to60d.trim();
    }

    public String getOverDueUnPayCor61to90d() {
        return overDueUnPayCor61to90d;
    }

    public void setOverDueUnPayCor61to90d(String overDueUnPayCor61to90d) {
        this.overDueUnPayCor61to90d = overDueUnPayCor61to90d == null ? null : overDueUnPayCor61to90d.trim();
    }

    public String getOverDueUnPayCor91to180() {
        return overDueUnPayCor91to180;
    }

    public void setOverDueUnPayCor91to180(String overDueUnPayCor91to180) {
        this.overDueUnPayCor91to180 = overDueUnPayCor91to180 == null ? null : overDueUnPayCor91to180.trim();
    }

    public String getOverDueUnPayCor180dplus() {
        return overDueUnPayCor180dplus;
    }

    public void setOverDueUnPayCor180dplus(String overDueUnPayCor180dplus) {
        this.overDueUnPayCor180dplus = overDueUnPayCor180dplus == null ? null : overDueUnPayCor180dplus.trim();
    }

    public String getM24PaymentStrMonth() {
        return m24PaymentStrMonth;
    }

    public void setM24PaymentStrMonth(String m24PaymentStrMonth) {
        this.m24PaymentStrMonth = m24PaymentStrMonth == null ? null : m24PaymentStrMonth.trim();
    }

    public String getM24PaymentEndMonth() {
        return m24PaymentEndMonth;
    }

    public void setM24PaymentEndMonth(String m24PaymentEndMonth) {
        this.m24PaymentEndMonth = m24PaymentEndMonth == null ? null : m24PaymentEndMonth.trim();
    }

    public String getM24PaymentStatus() {
        return m24PaymentStatus;
    }

    public void setM24PaymentStatus(String m24PaymentStatus) {
        this.m24PaymentStatus = m24PaymentStatus == null ? null : m24PaymentStatus.trim();
    }

    public String getM60OverDueStrMonth() {
        return m60OverDueStrMonth;
    }

    public void setM60OverDueStrMonth(String m60OverDueStrMonth) {
        this.m60OverDueStrMonth = m60OverDueStrMonth == null ? null : m60OverDueStrMonth.trim();
    }

    public String getM60OverDueEndMonth() {
        return m60OverDueEndMonth;
    }

    public void setM60OverDueEndMonth(String m60OverDueEndMonth) {
        this.m60OverDueEndMonth = m60OverDueEndMonth == null ? null : m60OverDueEndMonth.trim();
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

    public String getRecSeq() {
        return recSeq;
    }

    public void setRecSeq(String recSeq) {
        this.recSeq = recSeq == null ? null : recSeq.trim();
    }

    public String getMessType() {
        return messType;
    }

    public void setMessType(String messType) {
        this.messType = messType == null ? null : messType.trim();
    }

    public String getDebCardPayOverdraftAmt() {
        return debCardPayOverdraftAmt;
    }

    public void setDebCardPayOverdraftAmt(String debCardPayOverdraftAmt) {
        this.debCardPayOverdraftAmt = debCardPayOverdraftAmt == null ? null : debCardPayOverdraftAmt.trim();
    }

    public String getDebCardPayShareLimit() {
        return debCardPayShareLimit;
    }

    public void setDebCardPayShareLimit(String debCardPayShareLimit) {
        this.debCardPayShareLimit = debCardPayShareLimit == null ? null : debCardPayShareLimit.trim();
    }

    public String getDebCardPay6mavgUsedAmt() {
        return debCardPay6mavgUsedAmt;
    }

    public void setDebCardPay6mavgUsedAmt(String debCardPay6mavgUsedAmt) {
        this.debCardPay6mavgUsedAmt = debCardPay6mavgUsedAmt == null ? null : debCardPay6mavgUsedAmt.trim();
    }

    public String getDebCardPayMaxUsedBal() {
        return debCardPayMaxUsedBal;
    }

    public void setDebCardPayMaxUsedBal(String debCardPayMaxUsedBal) {
        this.debCardPayMaxUsedBal = debCardPayMaxUsedBal == null ? null : debCardPayMaxUsedBal.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }
}