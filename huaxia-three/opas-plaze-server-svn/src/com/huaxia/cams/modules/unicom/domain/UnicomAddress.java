package com.huaxia.cams.modules.unicom.domain;
/**
 * 联通地址类信息实体类
 * 与UNICOM_ADDRESS_INFO表对应
 * @author lipengfei
 *
 */
public class UnicomAddress {

	//创建用户
	private String crtUser;
	
	//申请件编号
	private String appId;
	
	//产品ID
	private String apiKey;
	
	//是否成功
	private String success;
	
	//响应编码
	private String responseCode;
	
	//响应描述
	private String responseDesc;
	
	//校验结果
	private String responseResult;
	
	//校验结果中文描述
	private String responseResultDesc;
		
	//运营商类别
	private String carrier;
	
	//交易编码
	private String trn_id;
	
	//手机号码
	private String mobile;

	public UnicomAddress() {
		super();
	}

	public UnicomAddress(String crtUser, String appId, String apiKey, String success, String responseCode,
			String responseDesc, String responseResult, String responseResultDesc, String carrier, String trn_id,
			String mobile) {
		super();
		this.crtUser = crtUser;
		this.appId = appId;
		this.apiKey = apiKey;
		this.success = success;
		this.responseCode = responseCode;
		this.responseDesc = responseDesc;
		this.responseResult = responseResult;
		this.responseResultDesc = responseResultDesc;
		this.carrier = carrier;
		this.trn_id = trn_id;
		this.mobile = mobile;
	}

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

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDesc() {
		return responseDesc;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}

	public String getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(String responseResult) {
		this.responseResult = responseResult;
	}

	public String getResponseResultDesc() {
		return responseResultDesc;
	}

	public void setResponseResultDesc(String responseResultDesc) {
		this.responseResultDesc = responseResultDesc;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getTrn_id() {
		return trn_id;
	}

	public void setTrn_id(String trn_id) {
		this.trn_id = trn_id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
		
}
