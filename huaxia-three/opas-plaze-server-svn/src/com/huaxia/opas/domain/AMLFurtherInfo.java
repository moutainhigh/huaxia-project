package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 反洗钱 & 制裁信息
 * 
 * @author zhiguo.li
 *
 */
public class AMLFurtherInfo extends AML implements Serializable {

	private static final long serialVersionUID = 2645489681411411170L;
	
	// 黑名单唯一标识码
	private String id;
	
	// 制裁信息等报告内容
	private String furtherInformation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFurtherInformation() {
		return furtherInformation;
	}

	public void setFurtherInformation(String furtherInformation) {
		this.furtherInformation = furtherInformation;
	}
	
}
