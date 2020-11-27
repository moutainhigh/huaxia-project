package com.huaxia.cams.modules.unicom.domain;

/**
 * 
 * @author dingguofeng
 * 
 * 联通在网时长实体类
 *
 */
public class UnicomOnline {
	
	/** 创建用户 */
	private String crtUser;
	
	/** 申请件编号 */
	private String appId;
	
	/** 状态码 */
	private String status;
	
	/** 返回码 */
	private String code;
	
	/** 返回码的中文描述 */
	private String errorDesc;
	
	/** 校验结果 */
	private String checkResult;
	
	/** 运营商类别 */
	private String carrier;

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	
}
