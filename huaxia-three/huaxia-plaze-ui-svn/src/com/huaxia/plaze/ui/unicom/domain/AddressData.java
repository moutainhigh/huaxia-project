package com.huaxia.plaze.ui.unicom.domain;

import org.apache.ibatis.type.Alias;

@Alias("AddressData")
public class AddressData {

	private String uuid;
	// 创建时间
	private String crtTime;
	// 创建用户
	private String crtUser;
	// 业务主键
	private String trnId;
	// 状态码 1成功 , 2失败
	private String status;
	// 返回码
	private String code;
	
	private String mobile;
	
	// 返回码的中文描述
	private String errorDesc;
	// 校验结果
	private String checkResult;
	//校验结果描述
	private String checkDesc;
	
	//产品ID 360015工作地址 360016居住地址
	private String apiKey;
	
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
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
	public String getTrnId() {
		return trnId;
	}
	public void setTrnId(String trnId) {
		this.trnId = trnId;
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
	@Override
	public String toString() {
		return "AddressData [uuid=" + uuid + ", crtTime=" + crtTime + ", crtUser=" + crtUser + ", trnId=" + trnId
				+ ", status=" + status + ", code=" + code + ", errorDesc=" + errorDesc + ", checkResult=" + checkResult
				+ ", apiKeyl=" + apiKey + "]";
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCheckDesc() {
		return checkDesc;
	}
	public void setCheckDesc(String checkDesc) {
		this.checkDesc = checkDesc;
	}

}
