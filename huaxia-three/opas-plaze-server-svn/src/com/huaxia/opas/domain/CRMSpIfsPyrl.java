package com.huaxia.opas.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 第三方 & CRM & 代发工资（审批）
 * 
 * @author zhiguo.li
 *
 */
public class CRMSpIfsPyrl extends CRM implements Serializable {

	private static final long serialVersionUID = 3264852691756342425L;

	// 客户ID
	private String custId;
	
	// 客户号
	private String custCode;
	
	// 卡号
	private String cardNo;
	
	// 账号
	private String accNo;
	
	// 签约时间
	private String signDate;
	
	// 最后一次代发日期
	private String lDate;
	
	// 代发单位编号
	private String comNo;
	
	// 代发单位名称
	private String comName;
	
	// 本月代发笔数
	private Integer dkdjNumM;
	
	// 本月代发金额
	private BigDecimal dkdjAmtM;
	
	// 本年累计代发笔数
	private Integer dkdjNumY;
	
	// 本年累计代发金额
	private BigDecimal dkdjAmtY;
	
	// 当前累计代发笔数
	private Integer dkdjNum;
	
	// 当前累计代发金额
	private BigDecimal dkdjAmt;
	
	// 代发华夏卡存款余额
	private BigDecimal dfhxDeptBal;
	
	// 代发华夏卡金融总资产
	private BigDecimal dfhxAum;
	
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getlDate() {
		return lDate;
	}

	public void setlDate(String lDate) {
		this.lDate = lDate;
	}

	public String getComNo() {
		return comNo;
	}

	public void setComNo(String comNo) {
		this.comNo = comNo;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public Integer getDkdjNumM() {
		return dkdjNumM;
	}

	public void setDkdjNumM(Integer dkdjNumM) {
		this.dkdjNumM = dkdjNumM;
	}

	public BigDecimal getDkdjAmtM() {
		return dkdjAmtM;
	}

	public void setDkdjAmtM(BigDecimal dkdjAmtM) {
		this.dkdjAmtM = dkdjAmtM;
	}

	public Integer getDkdjNumY() {
		return dkdjNumY;
	}

	public void setDkdjNumY(Integer dkdjNumY) {
		this.dkdjNumY = dkdjNumY;
	}

	public BigDecimal getDkdjAmtY() {
		return dkdjAmtY;
	}

	public void setDkdjAmtY(BigDecimal dkdjAmtY) {
		this.dkdjAmtY = dkdjAmtY;
	}

	public Integer getDkdjNum() {
		return dkdjNum;
	}

	public void setDkdjNum(Integer dkdjNum) {
		this.dkdjNum = dkdjNum;
	}

	public BigDecimal getDkdjAmt() {
		return dkdjAmt;
	}

	public void setDkdjAmt(BigDecimal dkdjAmt) {
		this.dkdjAmt = dkdjAmt;
	}

	public BigDecimal getDfhxDeptBal() {
		return dfhxDeptBal;
	}

	public void setDfhxDeptBal(BigDecimal dfhxDeptBal) {
		this.dfhxDeptBal = dfhxDeptBal;
	}

	public BigDecimal getDfhxAum() {
		return dfhxAum;
	}

	public void setDfhxAum(BigDecimal dfhxAum) {
		this.dfhxAum = dfhxAum;
	}

	public String getdDate() {
		return dDate;
	}

	public void setdDate(String dDate) {
		this.dDate = dDate;
	}

}
