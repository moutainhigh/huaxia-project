package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * @author susha 
 * 创建时间:2017-03-01 
 * 描述:提报账户实体类
 */
public class SubReport implements Serializable {

	private static final long serialVersionUID = -93312842093594275L;

	private String autoId;// id
	private String appId;// 流水号
	private String submitType;// 提交类型
	private String subReason;// 提交原因
	private String delStatus;// 处理状态
	private String memo;// 提报备注
	private Date crtDate;// 创建日期
	private String crtUser;// 创建人
	private String percent;//百分比
	private String c1Idnbr;
	private String product1;
	
	private String c1Name;
	public String getC1Name() {
		return c1Name;
	}

	public void setC1Name(String c1Name) {
		this.c1Name = c1Name;
	}

	public String getC1Idnbr() {
		return c1Idnbr;
	}

	public void setC1Idnbr(String c1Idnbr) {
		this.c1Idnbr = c1Idnbr;
	}
	public String getProduct1() {
		return product1;
	}

	public void setProduct1(String product1) {
		this.product1 = product1;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public String getCurrNode() {
		return currNode;
	}

	public void setCurrNode(String currNode) {
		this.currNode = currNode;
	}

	private String ydjFlag;
	private String currNode;//节点
	
	public String getYdjFlag() {
		return ydjFlag;
	}

	public void setYdjFlag(String ydjFlag) {
		this.ydjFlag = ydjFlag;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSubmitType() {
		return submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	public String getSubReason() {
		return subReason;
	}

	public void setSubReason(String subReason) {
		this.subReason = subReason;
	}

	public String getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
		this.crtUser = crtUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
