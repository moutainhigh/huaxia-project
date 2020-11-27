package com.huaxia.opas.domain.archive;

import java.io.Serializable;

public class ArExport implements Serializable{

	private static final long serialVersionUID = 5971648790597627974L;
	
	/**
	 * 申请件编号
	 */
	private String appId;
	
	/**
	 * 申请件查询标志,用于便捷查询套卡
	 */
	private String appId2;
	
	/**
	 * 审批结果
	 */
	private String result;
	
	/**
	 * 快速发卡方式
	 */
	private String rushFlag;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppId2() {
		return appId2;
	}

	public void setAppId2(String appId2) {
		this.appId2 = appId2;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRushFlag() {
		return rushFlag;
	}

	public void setRushFlag(String rushFlag) {
		this.rushFlag = rushFlag;
	}
	
	

}
