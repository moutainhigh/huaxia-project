package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * 配发卡信息实体类
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public class MatchingCard implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 9006648199193457103L;

	/**
	 * ID
	 */
	private String autoId;

	/**
	 * 产品编号
	 */
	private String prodCode;

	/**
	 * 产品名称
	 */
	private String prodName;

	/**
	 * 配发产品编号
	 */
	private String allotProdCode;
	
	/**
	 * 配发产品名称
	 */
	private String allotProdName;

	/**
	 * 产品版面
	 */
	private String prductFace;
	
	/**
	 * 年费代码
	 */
	private String annualFeeCode;
	
	/**
	 * 人民币自动购汇还款
	 */
	private String rmbAutoPay;
	
	/**
	 * 自动购汇还款方式
	 */
	private String autoPayMode;
	
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
	
	/**
	 * 是否申请虚拟卡
	 * 0:否，1:是
	 */
	private Integer vcnYn;
	
	/**
	 * 虚拟卡限制渠道
	 */
	private Integer vcnChnl;

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = (autoId == null ? null : autoId.trim());
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = (prodCode == null ? null : prodCode.trim());
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = (prodName == null ? null : prodName.trim());
	}

	public String getAllotProdCode() {
		return allotProdCode;
	}

	public void setAllotProdCode(String allotProdCode) {
		this.allotProdCode = (allotProdCode == null ? null : allotProdCode.trim());
	}

	public String getAllotProdName() {
		return allotProdName;
	}

	public void setAllotProdName(String allotProdName) {
		this.allotProdName = (allotProdName == null ? null : allotProdName.trim());
	}

	public String getPrductFace() {
		return prductFace;
	}

	public void setPrductFace(String prductFace) {
		this.prductFace = prductFace;
	}

	public String getAnnualFeeCode() {
		return annualFeeCode;
	}

	public void setAnnualFeeCode(String annualFeeCode) {
		this.annualFeeCode = (annualFeeCode == null ? null : annualFeeCode.trim());
	}

	public String getRmbAutoPay() {
		return rmbAutoPay;
	}

	public void setRmbAutoPay(String rmbAutoPay) {
		this.rmbAutoPay = (rmbAutoPay == null ? null : rmbAutoPay.trim());
	}

	public String getAutoPayMode() {
		return autoPayMode;
	}

	public void setAutoPayMode(String autoPayMode) {
		this.autoPayMode = autoPayMode;
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
	
	public Integer getVcnYn() {
		return vcnYn;
	}

	public void setVcnYn(Integer vcnYn) {
		this.vcnYn = vcnYn;
	}

	public Integer getVcnChnl() {
		return vcnChnl;
	}

	public void setVcnChnl(Integer vcnChnl) {
		this.vcnChnl = vcnChnl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
