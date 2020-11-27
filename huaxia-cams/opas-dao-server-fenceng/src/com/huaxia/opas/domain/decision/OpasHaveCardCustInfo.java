package com.huaxia.opas.domain.decision;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OpasHaveCardCustInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6831404446752291358L;

	private String autoId;

    private String appId;
    /**
     * 易达金账户状态
     */
    private String acctYdjStatus;
    /**
     * 标准卡账户状态
     */
    private String acctBzkStatus;
    /**
     * 已持卡卡种
     */
    private String havecardType;
    /**
     * 卡片状态
     */
    private String cardStatus;
    /**
     * 卡片对应额度
     */
    private Long cardLimit;
    /**
     * 逾期期数（当前）
     */
    private Short cycdueTimes;
    /**
     * 逾期金额（当前）
     */
    private BigDecimal cycdueLimit;
    /**
     * 账单地址
     */
    private String billAddr;
    /**
     * 易达金信用额度
     */
    private Long creditYdjLine;
    /**
     * 标准卡信用额度
     */
    private Long creditBzkLine;
    /**
     * 单位名称
     */
    private String companyAddr;
    /**
     * 单位电话
     */
    private String companyTel;
    /**
     * 申请人手机
     */
    private String applyerTel;
    /**
     * 住宅电话
     */
    private String houseTel;
    /**
     * 直系联系人姓名
     */
    private String nearlyContactName;
    /**
     * 直系联系电话
     */
    private String nearlyContactTel;
    
    private String addressBillType;

    private Date crtDate;

    private String crtUser;

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

    public String getAcctYdjStatus() {
        return acctYdjStatus;
    }

    public void setAcctYdjStatus(String acctYdjStatus) {
        this.acctYdjStatus = acctYdjStatus == null ? null : acctYdjStatus.trim();
    }

    public String getAcctBzkStatus() {
        return acctBzkStatus;
    }

    public void setAcctBzkStatus(String acctBzkStatus) {
        this.acctBzkStatus = acctBzkStatus == null ? null : acctBzkStatus.trim();
    }

    public String getHavecardType() {
        return havecardType;
    }

    public void setHavecardType(String havecardType) {
        this.havecardType = havecardType == null ? null : havecardType.trim();
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus == null ? null : cardStatus.trim();
    }

    public Long getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(Long cardLimit) {
        this.cardLimit = cardLimit;
    }

    public Short getCycdueTimes() {
        return cycdueTimes;
    }

    public void setCycdueTimes(Short cycdueTimes) {
        this.cycdueTimes = cycdueTimes;
    }

    public BigDecimal getCycdueLimit() {
        return cycdueLimit;
    }

    public void setCycdueLimit(BigDecimal cycdueLimit) {
        this.cycdueLimit = cycdueLimit;
    }

    public String getBillAddr() {
        return billAddr;
    }

    public void setBillAddr(String billAddr) {
        this.billAddr = billAddr == null ? null : billAddr.trim();
    }

    public Long getCreditYdjLine() {
        return creditYdjLine;
    }

    public void setCreditYdjLine(Long creditYdjLine) {
        this.creditYdjLine = creditYdjLine;
    }

    public Long getCreditBzkLine() {
        return creditBzkLine;
    }

    public void setCreditBzkLine(Long creditBzkLine) {
        this.creditBzkLine = creditBzkLine;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr == null ? null : companyAddr.trim();
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel == null ? null : companyTel.trim();
    }

    public String getApplyerTel() {
        return applyerTel;
    }

    public void setApplyerTel(String applyerTel) {
        this.applyerTel = applyerTel == null ? null : applyerTel.trim();
    }

    public String getHouseTel() {
        return houseTel;
    }

    public void setHouseTel(String houseTel) {
        this.houseTel = houseTel == null ? null : houseTel.trim();
    }

    public String getNearlyContactName() {
        return nearlyContactName;
    }

    public void setNearlyContactName(String nearlyContactName) {
        this.nearlyContactName = nearlyContactName == null ? null : nearlyContactName.trim();
    }

    public String getNearlyContactTel() {
        return nearlyContactTel;
    }

    public void setNearlyContactTel(String nearlyContactTel) {
        this.nearlyContactTel = nearlyContactTel == null ? null : nearlyContactTel.trim();
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

	public String getAddressBillType() {
		return addressBillType;
	}

	public void setAddressBillType(String addressBillType) {
		this.addressBillType = addressBillType;
	}
}