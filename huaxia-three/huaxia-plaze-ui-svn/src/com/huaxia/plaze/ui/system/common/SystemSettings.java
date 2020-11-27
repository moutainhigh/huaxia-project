package com.huaxia.plaze.ui.system.common;

/**
 * 系统设置枚举类
 * 
 * @author zhiguo.li
 *
 */
public enum SystemSettings {

	/** 系统单位时间登录限制 */
	LOGIN_SYSTEM_MAX_COUNT("000001", "系统单位时间登录限制!"),
	/** 单位时间人行单条查询阻断 */
	PBOC_SINGLE_QUERY_MAX_COUNT("000101", "单位时间人行单条查询阻断"),
	/** 单位时间人行单条查找阻断 */
	PBOC_SINGLE_SEARCH_MAX_COUNT("000102", "单位时间人行单条查找阻断"),
	/** 单位时间人行单条查询阻断 */
	SAME_CERTNO_QUERY_MAX_COUNT("000103", "单位时间相同证件查询阻断"),
	/** 单位时间人行单条查找阻断 */
	SAME_CERTNO_SEARCH_MAX_COUNT("000104", "单位时间相同证件查找阻断");

	private String code;

	private String desc;

	private SystemSettings(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
