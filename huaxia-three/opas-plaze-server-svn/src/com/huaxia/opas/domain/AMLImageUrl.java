package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 反洗钱 & 外部资源信息
 * 
 * @author zhiguo.li
 *
 */
public class AMLImageUrl extends AML implements Serializable {

	private static final long serialVersionUID = 1229633002766122000L;
	
	// 档案唯一ID
	private String id;
	
	// URL
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
