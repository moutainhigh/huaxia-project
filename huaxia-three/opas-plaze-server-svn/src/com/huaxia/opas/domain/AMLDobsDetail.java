package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 反洗钱 & 出生日期信息
 * 
 * @author zhiguo.li
 *
 */
public class AMLDobsDetail extends AML implements Serializable {

	private static final long serialVersionUID = 5704141788944401301L;
	
	// 黑名单唯一标识符
	private String id;
	
	// 出生日期（多个出生日期）
	private String dobs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDobs() {
		return dobs;
	}

	public void setDobs(String dobs) {
		this.dobs = dobs;
	}
	
}
