package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 反洗钱 & 制裁机构信息
 * 
 * @author zhiguo.li
 *
 */
public class AMLKeywordsDetail extends AML implements Serializable {

	private static final long serialVersionUID = 2676697554401864490L;
	
	// 档案唯一ID
	private String id;
	
	// 制裁机构
	private String keywords;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
}
