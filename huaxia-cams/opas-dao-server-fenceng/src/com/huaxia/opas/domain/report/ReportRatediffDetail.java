package com.huaxia.opas.domain.report;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ReportRatediffDetail  implements Serializable{
	
	private static final long serialVersionUID = -359700323691021001L;

	private String autoId;//序列值
	
	private String appId;//条形码
	
	private String prodName;//卡产品名称

	private String custLevelTag; //利率客户分层结果标签
	
	private String rateCode; //利率代码
	
	private String lowpayCustLevelTag; //最低还款额客户分层结果标签
	
	private String repayRatioValue;//最低还款额比例代码
	
	private String freeCustLevelTag;//免息还款期客户分层结果标签
	
	private String repayFreeCode; //免息还款期代码
	
	private String toArchiveTime;//归档日期
    //@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date crtDate;//创建日期

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

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getCustLevelTag() {
		return custLevelTag;
	}

	public void setCustLevelTag(String custLevelTag) {
		this.custLevelTag = custLevelTag;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	public String getLowpayCustLevelTag() {
		return lowpayCustLevelTag;
	}

	public void setLowpayCustLevelTag(String lowpayCustLevelTag) {
		this.lowpayCustLevelTag = lowpayCustLevelTag;
	}

	public String getRepayRatioValue() {
		return repayRatioValue;
	}

	public void setRepayRatioValue(String repayRatioValue) {
		this.repayRatioValue = repayRatioValue;
	}

	public String getFreeCustLevelTag() {
		return freeCustLevelTag;
	}

	public void setFreeCustLevelTag(String freeCustLevelTag) {
		this.freeCustLevelTag = freeCustLevelTag;
	}

	public String getRepayFreeCode() {
		return repayFreeCode;
	}

	public void setRepayFreeCode(String repayFreeCode) {
		this.repayFreeCode = repayFreeCode;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getToArchiveTime() {
		return toArchiveTime;
	}

	public void setToArchiveTime(String toArchiveTime) {
		this.toArchiveTime = toArchiveTime;
	}
	
    
}
