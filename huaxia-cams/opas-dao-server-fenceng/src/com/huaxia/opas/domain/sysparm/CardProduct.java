package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * 卡产品维护实体类
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public class CardProduct implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8036148390769606692L;

	/**
	 * ID
	 */
	private String autoId;
	
	/**
	 * 用户主键
	 */
	private String userId;
	
	/**
	 * 产品编号
	 */
	private String productCode;

	/**
	 * 产品名称
	 */
	private String productName;

	/**
	 * 产品版面
	 */
	private String productFace;
	
	/**
	 * 账户类型1
	 */
	private String acctType1;
	
	/**
	 * 账户类型2
	 */
	private String acctType2;
	
	/**
	 * 是否员工卡
	 */
	private String staffCard;
	
	/**
	 * 卡类别
	 */
	private String cardType;
	
	/**
	 * 额度下限
	 */
	private String lineLow;
	
	/**
	 * 额度上限
	 */
	private String lineHight;
	
	/**
	 * 是否双币
	 */
	private String doubleCurr;
	
	/**
	 * 币种
	 */
	private String currNo;
	
	/**
	 * 取整额度
	 */
	private String integerLine;
	
	/**
	 * 是否降级
	 */
	private String flag;
	
	/**
	 * 是否套卡
	 */
	private String signedCard;
	
	/**
	 * 利率代码失效时长
	 */
	private String rateExpireLine;	
	
	/**
	 * 年费减免方式
	 */
	private String yearFeeDerateType;	
	
	/**
	 * 当前状态
	 */
	private String status;

	/**
	 * 启用时间
	 */
	private Date startDate;

	/**
	 * 停用时间
	 */
	private Date stopDate;

	/**
	 * 创建人
	 */
	private String crtUser;

	/**
	 * 创建时间
	 */
	private Date crtDate;
	
	/**
	 * 最后操作人
	 */
	private String lstUpdUser;
	
	/**
	 * 最后操作时间
	 */
	private Date lstUpdDate;
	private String yearFeeDerateTypeName;
	private String productFaceName;
	
	private String productFaceDefault;
	private String yearFeeDerateTypeDefault;
	public String getProductFaceDefault() {
		return productFaceDefault;
	}

	public void setProductFaceDefault(String productFaceDefault) {
		this.productFaceDefault = productFaceDefault;
	}

	public String getYearFeeDerateTypeDefault() {
		return yearFeeDerateTypeDefault;
	}

	public void setYearFeeDerateTypeDefault(String yearFeeDerateTypeDefault) {
		this.yearFeeDerateTypeDefault = yearFeeDerateTypeDefault;
	}

	public String getYearFeeDerateTypeName() {
		return yearFeeDerateTypeName;
	}

	public void setYearFeeDerateTypeName(String yearFeeDerateTypeName) {
		this.yearFeeDerateTypeName = yearFeeDerateTypeName;
	}

	public String getProductFaceName() {
		return productFaceName;
	}

	public void setProductFaceName(String productFaceName) {
		this.productFaceName = productFaceName;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = (autoId == null ? null : autoId.trim());
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = (userId == null ? null : userId.trim());
	}

	public String getProductCode() {
		return productCode;
	}

	public void getProductCode(String productCode) {
		this.productCode = (productCode == null ? null : productCode.trim());
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = (productName == null ? null : productName.trim());
	}

	public String ProductFace() {
		return productFace;
	}

	public void setPrductFace(String productFace) {
		this.productFace = productFace;
	}

	public String getProductFace() {
		return productFace;
	}

	public void setProductFace(String productFace) {
		this.productFace = productFace;
	}

	public String getAcctType1() {
		return acctType1;
	}

	public void setAcctType1(String acctType1) {
		this.acctType1 = acctType1;
	}

	public String getAcctType2() {
		return acctType2;
	}

	public void setAcctType2(String acctType2) {
		this.acctType2 = acctType2;
	}

	public String getStaffCard() {
		return staffCard;
	}

	public void setStaffCard(String staffCard) {
		this.staffCard = staffCard;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = (cardType == null ? null : cardType.trim());
	}

	public String getLineLow() {
		return lineLow;
	}

	public void setLineLow(String lineLow) {
		this.lineLow = (lineLow == null ? null : lineLow.trim());
	}

	public String getLineHight() {
		return lineHight;
	}

	public void setLineHight(String lineHight) {
		this.lineHight = (lineHight == null ? null : lineHight.trim());
	}

	public String getDoubleCurr() {
		return doubleCurr;
	}

	public void setDoubleCurr(String doubleCurr) {
		this.doubleCurr = doubleCurr;
	}

	public String getCurrNo() {
		return currNo;
	}

	public void setCurrNo(String currNo) {
		this.currNo = currNo;
	}

	public String getIntegerLine() {
		return integerLine;
	}

	public void setIntegerLine(String integerLine) {
		this.integerLine = (integerLine == null ? null : integerLine.trim());
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSignedCard() {
		return signedCard;
	}

	public void setSignedCard(String signedCard) {
		this.signedCard = signedCard;
	}

	public String getRateExpireLine() {
		return rateExpireLine;
	}

	public void setRateExpireLine(String rateExpireLine) {
		this.rateExpireLine = (rateExpireLine == null ? null : rateExpireLine.trim());
	}

	public String getYearFeeDerateType() {
		return yearFeeDerateType;
	}

	public void setYearFeeDerateType(String yearFeeDerateType) {
		this.yearFeeDerateType = yearFeeDerateType;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
