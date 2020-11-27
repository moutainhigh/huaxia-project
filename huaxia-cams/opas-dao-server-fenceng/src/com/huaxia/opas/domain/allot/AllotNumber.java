package com.huaxia.opas.domain.allot;

import java.io.Serializable;
import java.util.Date;
/**
 * 自动分件数量记录
 * @author wangdebin
 * @version v1.1(初始v1.0)
 * @history 2017.10.27
 */
public class AllotNumber implements Serializable {
	private static final long serialVersionUID = -8172672632963014783L;
	private String numberId;
	
	private String ruleCode;

	private String autoNumber;
	
	private String typeFlag;

	private String ydjFlag;
	
	private String currNode;
	
	private Date crtDate;
	
	private Date lastDate;
	
	private String ydjCreditNumber;
	
	private String bzkReviewNumber;
	
	private String bzkCreditNumber;
	
	private String bzkApprovalNumber;
	
	private String flag;
	public String getNumberId() {
		return numberId;
	}

	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}

	public String getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}

	public String getAutoNumber() {
		return autoNumber;
	}

	public void setAutoNumber(String autoNumber) {
		this.autoNumber = autoNumber;
	}

	public String getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}

	public String getYdjFlag() {
		return ydjFlag;
	}

	public void setYdjFlag(String ydjFlag) {
		this.ydjFlag = ydjFlag;
	}

	public String getCurrNode() {
		return currNode;
	}

	public void setCurrNode(String currNode) {
		this.currNode = currNode;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getYdjCreditNumber() {
		return ydjCreditNumber;
	}

	public void setYdjCreditNumber(String ydjCreditNumber) {
		this.ydjCreditNumber = ydjCreditNumber;
	}

	public String getBzkReviewNumber() {
		return bzkReviewNumber;
	}

	public void setBzkReviewNumber(String bzkReviewNumber) {
		this.bzkReviewNumber = bzkReviewNumber;
	}

	public String getBzkCreditNumber() {
		return bzkCreditNumber;
	}

	public void setBzkCreditNumber(String bzkCreditNumber) {
		this.bzkCreditNumber = bzkCreditNumber;
	}

	public String getBzkApprovalNumber() {
		return bzkApprovalNumber;
	}

	public void setBzkApprovalNumber(String bzkApprovalNumber) {
		this.bzkApprovalNumber = bzkApprovalNumber;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
