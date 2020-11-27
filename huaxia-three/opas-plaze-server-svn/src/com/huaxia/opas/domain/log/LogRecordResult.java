package com.huaxia.opas.domain.log;

import java.io.Serializable;

/**
 * 
 * 日志记录结果信息
 * 
 * @author zhiguo.li
 *
 */
public class LogRecordResult implements Serializable {

	private static final long serialVersionUID = 3120312460994723005L;

	// 记录编号
	private String uuid;

	// 创建时间
	private String crtTime;

	// 创建人
	private String crtUser;

	// 最后操作时间
	private String lstUpdTime;

	// 最后更新人
	private String lstUpdUser;

	// 申请件编号
	private String appId;
	
	// 第三方模块名称
	private String moduleName;
	
	// 处理结果标志
	private String flagDispose;
	
	// 处理结果代码
	private String disposeResultCode;
	
	// 处理结果描述
	private String disposeResultDesc;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getLstUpdTime() {
		return lstUpdTime;
	}

	public void setLstUpdTime(String lstUpdTime) {
		this.lstUpdTime = lstUpdTime;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getFlagDispose() {
		return flagDispose;
	}

	public void setFlagDispose(String flagDispose) {
		this.flagDispose = flagDispose;
	}

	public String getDisposeResultCode() {
		return disposeResultCode;
	}

	public void setDisposeResultCode(String disposeResultCode) {
		this.disposeResultCode = disposeResultCode;
	}

	public String getDisposeResultDesc() {
		return disposeResultDesc;
	}

	public void setDisposeResultDesc(String disposeResultDesc) {
		this.disposeResultDesc = disposeResultDesc;
	}

	@Override
	public String toString() {
		return "LogRecordResult [uuid=" + uuid + ", crtTime=" + crtTime + ", crtUser=" + crtUser + ", lstUpdTime="
				+ lstUpdTime + ", lstUpdUser=" + lstUpdUser + ", appId=" + appId + ", moduleName=" + moduleName
				+ ", flagDispose=" + flagDispose + ", disposeResultCode=" + disposeResultCode + ", disposeResultDesc="
				+ disposeResultDesc + "]";
	}



}
