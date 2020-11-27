package com.huaxia.opas.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 第三方 & CRM & 网银基本信息表（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsCybkBase extends CRM implements Serializable {

	private static final long serialVersionUID = 5845358439360156630L;
	
	// 客户ID
	private String custId;
	
	// 客户号
	private String custCode;
	
	// 卡号
	private String cardId;
	
	// 网银类型
	private String eBankType;
	
	// 开户落地机构
	private String openBr;
	
	// 机构名称
	private String brName;
	
	// 累计交易笔数
	private Integer tranNum;
	
	// 累计交易金额
	private BigDecimal tranAmt;
	
	// 实收手续费
	private BigDecimal shshSxf;
	
	// 应收手续费
	private BigDecimal yshSxf;
	
	// 手机号码
	private String mobilePhone;
	
	// 电子邮箱
	private String email;
	
	// 网银客户号
	private String eBankCustId;
	
	// 网银签约日期
	private String signDate;
	
	// 网银版本
	private String signVersion;
	
	// 网银状态
	private String eBankStatus;
	
	// 签约业务
	private String signType;
	
	// 签约渠道
	private String signChannel;
	
	// 银联网上支付签约状态
	private String payStatus;
	
	// 客户经理代码
	private String userId;
	
	// 客户经理名称
	private String userName;
	
	// 登录次数
	private Integer loginNum;
	
	// 网银签约时间
	private String signTime;
	
	// 证书发放日期
	private String grantTime;
	
	// 上次登录时间
	private String lLoginDate;
	
	// 上次交易时间
	private String lTransDate;
	
	// 激活时间
	private String activateDate;
	
	// 替代率
	private String tdl;
	
	// 数据日期
	private String dDate;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String geteBankType() {
		return eBankType;
	}

	public void seteBankType(String eBankType) {
		this.eBankType = eBankType;
	}

	public String getOpenBr() {
		return openBr;
	}

	public void setOpenBr(String openBr) {
		this.openBr = openBr;
	}

	public String getBrName() {
		return brName;
	}

	public void setBrName(String brName) {
		this.brName = brName;
	}

	public Integer getTranNum() {
		return tranNum;
	}

	public void setTranNum(Integer tranNum) {
		this.tranNum = tranNum;
	}

	public BigDecimal getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(BigDecimal tranAmt) {
		this.tranAmt = tranAmt;
	}

	public BigDecimal getShshSxf() {
		return shshSxf;
	}

	public void setShshSxf(BigDecimal shshSxf) {
		this.shshSxf = shshSxf;
	}

	public BigDecimal getYshSxf() {
		return yshSxf;
	}

	public void setYshSxf(BigDecimal yshSxf) {
		this.yshSxf = yshSxf;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String geteBankCustId() {
		return eBankCustId;
	}

	public void seteBankCustId(String eBankCustId) {
		this.eBankCustId = eBankCustId;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getSignVersion() {
		return signVersion;
	}

	public void setSignVersion(String signVersion) {
		this.signVersion = signVersion;
	}

	public String geteBankStatus() {
		return eBankStatus;
	}

	public void seteBankStatus(String eBankStatus) {
		this.eBankStatus = eBankStatus;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSignChannel() {
		return signChannel;
	}

	public void setSignChannel(String signChannel) {
		this.signChannel = signChannel;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getGrantTime() {
		return grantTime;
	}

	public void setGrantTime(String grantTime) {
		this.grantTime = grantTime;
	}

	public String getlLoginDate() {
		return lLoginDate;
	}

	public void setlLoginDate(String lLoginDate) {
		this.lLoginDate = lLoginDate;
	}

	public String getlTransDate() {
		return lTransDate;
	}

	public void setlTransDate(String lTransDate) {
		this.lTransDate = lTransDate;
	}

	public String getActivateDate() {
		return activateDate;
	}

	public void setActivateDate(String activateDate) {
		this.activateDate = activateDate;
	}

	public String getTdl() {
		return tdl;
	}

	public void setTdl(String tdl) {
		this.tdl = tdl;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}

}
