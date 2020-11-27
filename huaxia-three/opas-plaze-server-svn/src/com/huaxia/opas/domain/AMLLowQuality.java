package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 反洗钱 & 其他别名信息
 * 
 * @author zhiguo.li
 *
 */
public class AMLLowQuality extends AML implements Serializable {

	private static final long serialVersionUID = 1034915888167275537L;
	
	// 档案唯一ID
	private String id;
	
	// 其他可能别名
	private String lowQualityAliases;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLowQualityAliases() {
		return lowQualityAliases;
	}

	public void setLowQualityAliases(String lowQualityAliases) {
		this.lowQualityAliases = lowQualityAliases;
	}
	
}
