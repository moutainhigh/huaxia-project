package com.huaxia.opas.domain.apply;

import java.io.Serializable;
import java.util.Date;

/**
 * 备注
 * 
 * @author xiebinxu 2017年2月25日
 */
public class ApplyRemark implements Serializable {

	private static final long serialVersionUID = 5684930398834923245L;

	private String remarkId;

	private String remarkInfo;

	private String remarkUser;

	private Date remarkTime;

	private String appId;
	
	private String currNode;
	private String remarkDate;
	
	public String getRemarkDate() {
		return remarkDate;
	}

	public void setRemarkDate(String remarkDate) {
		this.remarkDate = remarkDate;
	}

	public String getRemarkId() {
		return remarkId;
	}

	public void setRemarkId(String remarkId) {
		this.remarkId = remarkId == null ? null : remarkId.trim();
	}

	public String getRemarkInfo() {
		return remarkInfo;
	}

	public void setRemarkInfo(String remarkInfo) {
		this.remarkInfo = remarkInfo == null ? null : remarkInfo.trim();
	}

	public String getRemarkUser() {
		return remarkUser;
	}

	public void setRemarkUser(String remarkUser) {
		this.remarkUser = remarkUser == null ? null : remarkUser.trim();
	}

	public Date getRemarkTime() {
		return remarkTime;
	}

	public void setRemarkTime(Date remarkTime) {
		this.remarkTime = remarkTime;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = (appId == null ? null : appId.trim());
	}
	
	public String getCurrNode() {
		return currNode;
	}

	public void setCurrNode(String currNode) {
		this.currNode = currNode;
	}

	@Override
	public String toString() {
		return "ApplyRemark [remarkId=" + remarkId + ", remarkInfo="
				+ remarkInfo + ", remarkUser=" + remarkUser + ", remarkTime="
				+ remarkTime + ", appId=" + appId +", currNode=" + currNode + "]";
	}

}