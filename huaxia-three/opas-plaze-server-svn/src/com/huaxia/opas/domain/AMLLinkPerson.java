package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 反洗钱 & 相关人物信息
 * 
 * @author zhiguo.li
 *
 */
public class AMLLinkPerson extends AML implements Serializable {

	private static final long serialVersionUID = 889000546063466176L;
	
	// 档案唯一ID
	private String id;
	
	// 关联人档案号
	private String linkId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	
}
