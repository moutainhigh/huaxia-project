package com.huaxia.opas.domain.archive;

import java.io.Serializable;
import java.util.Date;

public class SuppArchive implements Serializable {

	private static final long serialVersionUID = -1459433560722543863L;
	/**
	 * 主键
	 */
	private String autoId;
	/**
	 * 申请件编号
	 */
	private String appId;

	/**
	 * 客户姓名
	 */
	private String custName;

	/**
	 * 补件码
	 */
	private String patchCode;

	/**
	 * 补件选中标志
	 */
	private String flag;

	/**
	 * 证件号码
	 */
	private String credNo;

	/**
	 * 最后操作时间
	 */
	private Date operatTime;

	/**
	 * 补件完成状态
	 */
	private String patchStatus;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPatchCode() {
		return patchCode;
	}

	public void setPatchCode(String patchCode) {
		this.patchCode = patchCode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCredNo() {
		return credNo;
	}

	public void setCredNo(String credNo) {
		this.credNo = credNo;
	}

	public Date getOperatTime() {
		return operatTime;
	}

	public void setOperatTime(Date operatTime) {
		this.operatTime = operatTime;
	}

	public String getPatchStatus() {
		return patchStatus;
	}

	public void setPatchStatus(String patchStatus) {
		this.patchStatus = patchStatus;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	
}
