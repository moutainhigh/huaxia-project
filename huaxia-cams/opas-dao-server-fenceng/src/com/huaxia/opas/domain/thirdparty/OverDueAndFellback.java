package com.huaxia.opas.domain.thirdparty;

import java.util.Date;

public class OverDueAndFellback {
    private String insideAppNo;//

    private String badDebtNum;//呆帐_笔数

    private String badDebtBalance;//呆帐_余额

    private String assDisNum;//资产处置_笔数

    private String assDisBalance;//资产处置_余额

    private String guaCompenNum;//保证人代偿_笔数

    private String guaCompenBalance;//保证人代偿_余额

    private String loanOverdueAcctNum;//贷款逾期_笔数/账户数

    private String loanOverdueMonthNum;//贷款逾期_月份数

    private String loanOverdueOverDueAmt;//贷款逾期_单月最高逾期总额/单月最高透支总额

    private String loanOverdueLongestOverDue;//贷款逾期_最长逾期月数/最长透支月数

    private String debitCardOverDueAcctNum;//贷记卡逾期_笔数/账户数

    private String debitCardOverDueMonthNum;//贷记卡逾期_月份数

    private String debitCoDueOverDueAmt;//贷记卡逾期_单月最高逾期总额/单月最高透支总额

    private String debitCoDueLongestOverDue;//贷款逾期_最长逾期月数/最长透支月数

    private String debC60overdAcctNum;//贷记卡逾期_笔数/账户数

    private String debC60overdMonthNum;//贷记卡逾期_月份数

    private String debC60overdOverDueAmt;//贷记卡逾期_单月最高逾期总额/单月最高透支总额

    private String debC60overdLongestOverDue;//贷记卡逾期_最长逾期月数/最长透支月数

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

    public String getBadDebtNum() {
        return badDebtNum;
    }

    public void setBadDebtNum(String badDebtNum) {
        this.badDebtNum = badDebtNum == null ? null : badDebtNum.trim();
    }

    public String getBadDebtBalance() {
        return badDebtBalance;
    }

    public void setBadDebtBalance(String badDebtBalance) {
        this.badDebtBalance = badDebtBalance == null ? null : badDebtBalance.trim();
    }

    public String getAssDisNum() {
        return assDisNum;
    }

    public void setAssDisNum(String assDisNum) {
        this.assDisNum = assDisNum == null ? null : assDisNum.trim();
    }

    public String getAssDisBalance() {
        return assDisBalance;
    }

    public void setAssDisBalance(String assDisBalance) {
        this.assDisBalance = assDisBalance == null ? null : assDisBalance.trim();
    }

    public String getGuaCompenNum() {
        return guaCompenNum;
    }

    public void setGuaCompenNum(String guaCompenNum) {
        this.guaCompenNum = guaCompenNum == null ? null : guaCompenNum.trim();
    }

    public String getGuaCompenBalance() {
        return guaCompenBalance;
    }

    public void setGuaCompenBalance(String guaCompenBalance) {
        this.guaCompenBalance = guaCompenBalance == null ? null : guaCompenBalance.trim();
    }

    public String getLoanOverdueAcctNum() {
        return loanOverdueAcctNum;
    }

    public void setLoanOverdueAcctNum(String loanOverdueAcctNum) {
        this.loanOverdueAcctNum = loanOverdueAcctNum == null ? null : loanOverdueAcctNum.trim();
    }

    public String getLoanOverdueMonthNum() {
        return loanOverdueMonthNum;
    }

    public void setLoanOverdueMonthNum(String loanOverdueMonthNum) {
        this.loanOverdueMonthNum = loanOverdueMonthNum == null ? null : loanOverdueMonthNum.trim();
    }

    public String getLoanOverdueOverDueAmt() {
        return loanOverdueOverDueAmt;
    }

    public void setLoanOverdueOverDueAmt(String loanOverdueOverDueAmt) {
        this.loanOverdueOverDueAmt = loanOverdueOverDueAmt == null ? null : loanOverdueOverDueAmt.trim();
    }

    public String getLoanOverdueLongestOverDue() {
        return loanOverdueLongestOverDue;
    }

    public void setLoanOverdueLongestOverDue(String loanOverdueLongestOverDue) {
        this.loanOverdueLongestOverDue = loanOverdueLongestOverDue == null ? null : loanOverdueLongestOverDue.trim();
    }

    public String getDebitCardOverDueAcctNum() {
        return debitCardOverDueAcctNum;
    }

    public void setDebitCardOverDueAcctNum(String debitCardOverDueAcctNum) {
        this.debitCardOverDueAcctNum = debitCardOverDueAcctNum == null ? null : debitCardOverDueAcctNum.trim();
    }

    public String getDebitCardOverDueMonthNum() {
        return debitCardOverDueMonthNum;
    }

    public void setDebitCardOverDueMonthNum(String debitCardOverDueMonthNum) {
        this.debitCardOverDueMonthNum = debitCardOverDueMonthNum == null ? null : debitCardOverDueMonthNum.trim();
    }

    public String getDebitCoDueOverDueAmt() {
        return debitCoDueOverDueAmt;
    }

    public void setDebitCoDueOverDueAmt(String debitCoDueOverDueAmt) {
        this.debitCoDueOverDueAmt = debitCoDueOverDueAmt == null ? null : debitCoDueOverDueAmt.trim();
    }

    public String getDebitCoDueLongestOverDue() {
        return debitCoDueLongestOverDue;
    }

    public void setDebitCoDueLongestOverDue(String debitCoDueLongestOverDue) {
        this.debitCoDueLongestOverDue = debitCoDueLongestOverDue == null ? null : debitCoDueLongestOverDue.trim();
    }

    public String getDebC60overdAcctNum() {
        return debC60overdAcctNum;
    }

    public void setDebC60overdAcctNum(String debC60overdAcctNum) {
        this.debC60overdAcctNum = debC60overdAcctNum == null ? null : debC60overdAcctNum.trim();
    }

    public String getDebC60overdMonthNum() {
        return debC60overdMonthNum;
    }

    public void setDebC60overdMonthNum(String debC60overdMonthNum) {
        this.debC60overdMonthNum = debC60overdMonthNum == null ? null : debC60overdMonthNum.trim();
    }

    public String getDebC60overdOverDueAmt() {
        return debC60overdOverDueAmt;
    }

    public void setDebC60overdOverDueAmt(String debC60overdOverDueAmt) {
        this.debC60overdOverDueAmt = debC60overdOverDueAmt == null ? null : debC60overdOverDueAmt.trim();
    }

    public String getDebC60overdLongestOverDue() {
        return debC60overdLongestOverDue;
    }

    public void setDebC60overdLongestOverDue(String debC60overdLongestOverDue) {
        this.debC60overdLongestOverDue = debC60overdLongestOverDue == null ? null : debC60overdLongestOverDue.trim();
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