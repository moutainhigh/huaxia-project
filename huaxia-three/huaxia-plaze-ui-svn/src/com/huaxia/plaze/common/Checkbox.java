package com.huaxia.plaze.common;

/**
 * 复选框状态
 * 
 * @author zhiguo.li
 *
 */
public enum Checkbox {

	CHECKED("1", "选中"), UNCHECK("0", "未选中");

	private String status;

	private String description;

	private Checkbox(String status, String description) {
		this.status = status;
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}
}
