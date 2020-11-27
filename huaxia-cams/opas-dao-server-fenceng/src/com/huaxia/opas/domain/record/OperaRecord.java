package com.huaxia.opas.domain.record;

import java.io.Serializable;
import java.util.Date;

public class OperaRecord implements Serializable {

	private static final long serialVersionUID = 2179647314027810628L;

	// 主键
	private String id;

	// 申请件流水号
	private String appId;

	// IP地址
	private String userIp;

	// 操作类型
	private String type;

	// 登录用户
	private String userCode;

	// 操作菜单
	private String module;

	// 请求参数
	private String params = "";

	// 操作时间
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		if (null != params) {
			this.params = params;
		}
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "OperaRecord [id=" + id + ", appId=" + appId + ", userIp=" + userIp + ", type=" + type
				+ ", userCode=" + userCode + ", module=" + module + ", params=" + params + ", createTime=" + createTime
				+ "]";
	}

}
