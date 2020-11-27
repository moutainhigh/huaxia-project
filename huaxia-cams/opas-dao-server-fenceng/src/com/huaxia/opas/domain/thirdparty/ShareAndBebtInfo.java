package com.huaxia.opas.domain.thirdparty;

import java.util.Date;

public class ShareAndBebtInfo {
    private String insideAppNo;

    private String outsLoansLoanCorpNumber;//未结清贷款_贷款法人机构数/发卡法人机构数

    private String outstandLoansLoanOrgNumber;//未结清贷款_贷款机构数/发卡机构数

    private String outstandLoansAcctNum;//未结清贷款_笔数/账户数

    private String outstandLoansContractAmt;//未结清贷款_合同金额/授信总额

    private String outstandLoansBalance;//未结清贷款_余额

    private String outstandLoans6monthAvePay;//未结清贷款_最近6个月平均应还款/最近6个月平均使用额度/最近6个月平均透支余额

    private String noPdCardLoanCorpNumber;//未销户贷记卡_贷款法人机构数/发卡法人机构数

    private String noPdCardLoanOrgNumber;//未销户贷记卡_贷款机构数/发卡机构数

    private String noPinDebitCardAcctNum;//未销户贷记卡_笔数/账户数

    private String noPinDebitCardContractAmt;//未销户贷记卡_合同金额/授信总额

    private String noPdCardBankMaxCreditLim;//未销户贷记卡_单家行最高授信额度

    private String noPdCardBankMinCreditLim;//未销户贷记卡_单家行最低授信额度

    private String noPdCodraftAmt;//未销户贷记卡_已用额度/透支余额

    private String noPdCard6monthAvePay;//未销户贷记卡_最近6个月平均应还款/最近6个月平均使用额度/最近6个月平均透支余额

    private String noPadcLoanCorpNumber;//未销户准贷记卡_贷款法人机构数/发卡法人机构数

    private String noPadcLoanOrgNumber;//未销户准贷记卡_贷款机构数/发卡机构数

    private String noPinAppDebitCardAcctNum;//未销户准贷记卡_笔数/账户数

    private String noPadcContractAmt;//未销户准贷记卡_已用额度/透支余额

    private String noPadcBankMaxCreditLim;//未销户准贷记卡_单家行最低授信额度

    private String noPadcBankMinCreditLim;//未销户贷记卡_单家行最低授信额度

    private String noPadCodraftAmt;//未销户准贷记卡_已用额度/透支余额

    private String noPadc6monthAvePay;//未销户准贷记卡_最近6个月平均应还款/最近6个月平均使用额度/最近6个月平均透支余额

    private String guarantyGuaranteeNum;//对外担保_担保笔数

    private String guarantyGuaranteeAmt;//对外担保_担保金额

    private String guarantyAssureBalance;//对外担保_担保本金余额   GUARANTY_ASSURE_BALANCE

    private String crtTime;

    private String crtUser;

    private Date lstUpdTime;

    private String lstUpdUser;

    private Date batchDate;

    private String recStatus;

    private String appId;

    public String getInsideAppNo() {
        return insideAppNo;
    }

    public void setInsideAppNo(String insideAppNo) {
        this.insideAppNo = insideAppNo == null ? null : insideAppNo.trim();
    }

    public String getOutsLoansLoanCorpNumber() {
        return outsLoansLoanCorpNumber;
    }

    public void setOutsLoansLoanCorpNumber(String outsLoansLoanCorpNumber) {
        this.outsLoansLoanCorpNumber = outsLoansLoanCorpNumber == null ? null : outsLoansLoanCorpNumber.trim();
    }

    public String getOutstandLoansLoanOrgNumber() {
        return outstandLoansLoanOrgNumber;
    }

    public void setOutstandLoansLoanOrgNumber(String outstandLoansLoanOrgNumber) {
        this.outstandLoansLoanOrgNumber = outstandLoansLoanOrgNumber == null ? null : outstandLoansLoanOrgNumber.trim();
    }

    public String getOutstandLoansAcctNum() {
        return outstandLoansAcctNum;
    }

    public void setOutstandLoansAcctNum(String outstandLoansAcctNum) {
        this.outstandLoansAcctNum = outstandLoansAcctNum == null ? null : outstandLoansAcctNum.trim();
    }

    public String getOutstandLoansContractAmt() {
        return outstandLoansContractAmt;
    }

    public void setOutstandLoansContractAmt(String outstandLoansContractAmt) {
        this.outstandLoansContractAmt = outstandLoansContractAmt == null ? null : outstandLoansContractAmt.trim();
    }

    public String getOutstandLoansBalance() {
        return outstandLoansBalance;
    }

    public void setOutstandLoansBalance(String outstandLoansBalance) {
        this.outstandLoansBalance = outstandLoansBalance == null ? null : outstandLoansBalance.trim();
    }

    public String getOutstandLoans6monthAvePay() {
        return outstandLoans6monthAvePay;
    }

    public void setOutstandLoans6monthAvePay(String outstandLoans6monthAvePay) {
        this.outstandLoans6monthAvePay = outstandLoans6monthAvePay == null ? null : outstandLoans6monthAvePay.trim();
    }

    public String getNoPdCardLoanCorpNumber() {
        return noPdCardLoanCorpNumber;
    }

    public void setNoPdCardLoanCorpNumber(String noPdCardLoanCorpNumber) {
        this.noPdCardLoanCorpNumber = noPdCardLoanCorpNumber == null ? null : noPdCardLoanCorpNumber.trim();
    }

    public String getNoPdCardLoanOrgNumber() {
        return noPdCardLoanOrgNumber;
    }

    public void setNoPdCardLoanOrgNumber(String noPdCardLoanOrgNumber) {
        this.noPdCardLoanOrgNumber = noPdCardLoanOrgNumber == null ? null : noPdCardLoanOrgNumber.trim();
    }

    public String getNoPinDebitCardAcctNum() {
        return noPinDebitCardAcctNum;
    }

    public void setNoPinDebitCardAcctNum(String noPinDebitCardAcctNum) {
        this.noPinDebitCardAcctNum = noPinDebitCardAcctNum == null ? null : noPinDebitCardAcctNum.trim();
    }

    public String getNoPinDebitCardContractAmt() {
        return noPinDebitCardContractAmt;
    }

    public void setNoPinDebitCardContractAmt(String noPinDebitCardContractAmt) {
        this.noPinDebitCardContractAmt = noPinDebitCardContractAmt == null ? null : noPinDebitCardContractAmt.trim();
    }

    public String getNoPdCardBankMaxCreditLim() {
        return noPdCardBankMaxCreditLim;
    }

    public void setNoPdCardBankMaxCreditLim(String noPdCardBankMaxCreditLim) {
        this.noPdCardBankMaxCreditLim = noPdCardBankMaxCreditLim == null ? null : noPdCardBankMaxCreditLim.trim();
    }

    public String getNoPdCardBankMinCreditLim() {
        return noPdCardBankMinCreditLim;
    }

    public void setNoPdCardBankMinCreditLim(String noPdCardBankMinCreditLim) {
        this.noPdCardBankMinCreditLim = noPdCardBankMinCreditLim == null ? null : noPdCardBankMinCreditLim.trim();
    }

    public String getNoPdCodraftAmt() {
        return noPdCodraftAmt;
    }

    public void setNoPdCodraftAmt(String noPdCodraftAmt) {
        this.noPdCodraftAmt = noPdCodraftAmt == null ? null : noPdCodraftAmt.trim();
    }

    public String getNoPdCard6monthAvePay() {
        return noPdCard6monthAvePay;
    }

    public void setNoPdCard6monthAvePay(String noPdCard6monthAvePay) {
        this.noPdCard6monthAvePay = noPdCard6monthAvePay == null ? null : noPdCard6monthAvePay.trim();
    }

    public String getNoPadcLoanCorpNumber() {
        return noPadcLoanCorpNumber;
    }

    public void setNoPadcLoanCorpNumber(String noPadcLoanCorpNumber) {
        this.noPadcLoanCorpNumber = noPadcLoanCorpNumber == null ? null : noPadcLoanCorpNumber.trim();
    }

    public String getNoPadcLoanOrgNumber() {
        return noPadcLoanOrgNumber;
    }

    public void setNoPadcLoanOrgNumber(String noPadcLoanOrgNumber) {
        this.noPadcLoanOrgNumber = noPadcLoanOrgNumber == null ? null : noPadcLoanOrgNumber.trim();
    }

    public String getNoPinAppDebitCardAcctNum() {
        return noPinAppDebitCardAcctNum;
    }

    public void setNoPinAppDebitCardAcctNum(String noPinAppDebitCardAcctNum) {
        this.noPinAppDebitCardAcctNum = noPinAppDebitCardAcctNum == null ? null : noPinAppDebitCardAcctNum.trim();
    }

    public String getNoPadcContractAmt() {
        return noPadcContractAmt;
    }

    public void setNoPadcContractAmt(String noPadcContractAmt) {
        this.noPadcContractAmt = noPadcContractAmt == null ? null : noPadcContractAmt.trim();
    }

    public String getNoPadcBankMaxCreditLim() {
        return noPadcBankMaxCreditLim;
    }

    public void setNoPadcBankMaxCreditLim(String noPadcBankMaxCreditLim) {
        this.noPadcBankMaxCreditLim = noPadcBankMaxCreditLim == null ? null : noPadcBankMaxCreditLim.trim();
    }

    public String getNoPadcBankMinCreditLim() {
        return noPadcBankMinCreditLim;
    }

    public void setNoPadcBankMinCreditLim(String noPadcBankMinCreditLim) {
        this.noPadcBankMinCreditLim = noPadcBankMinCreditLim == null ? null : noPadcBankMinCreditLim.trim();
    }

    public String getNoPadCodraftAmt() {
        return noPadCodraftAmt;
    }

    public void setNoPadCodraftAmt(String noPadCodraftAmt) {
        this.noPadCodraftAmt = noPadCodraftAmt == null ? null : noPadCodraftAmt.trim();
    }

    public String getNoPadc6monthAvePay() {
        return noPadc6monthAvePay;
    }

    public void setNoPadc6monthAvePay(String noPadc6monthAvePay) {
        this.noPadc6monthAvePay = noPadc6monthAvePay == null ? null : noPadc6monthAvePay.trim();
    }

    public String getGuarantyGuaranteeNum() {
        return guarantyGuaranteeNum;
    }

    public void setGuarantyGuaranteeNum(String guarantyGuaranteeNum) {
        this.guarantyGuaranteeNum = guarantyGuaranteeNum == null ? null : guarantyGuaranteeNum.trim();
    }

    public String getGuarantyGuaranteeAmt() {
        return guarantyGuaranteeAmt;
    }

    public void setGuarantyGuaranteeAmt(String guarantyGuaranteeAmt) {
        this.guarantyGuaranteeAmt = guarantyGuaranteeAmt == null ? null : guarantyGuaranteeAmt.trim();
    }

    public String getGuarantyAssureBalance() {
        return guarantyAssureBalance;
    }

    public void setGuarantyAssureBalance(String guarantyAssureBalance) {
        this.guarantyAssureBalance = guarantyAssureBalance == null ? null : guarantyAssureBalance.trim();
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