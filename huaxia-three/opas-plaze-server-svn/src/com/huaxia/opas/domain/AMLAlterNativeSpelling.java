package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 反洗钱 & 其他可能拼写方式信息
 * 
 * @author zhiguo.li
 *
 */
public class AMLAlterNativeSpelling extends AML implements Serializable {

	private static final long serialVersionUID = 7879697809314671183L;
	
	// 档案唯一ID
	private String id;
	
	// 其他可能拼写方式
	private String alterNativeSpelling;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlterNativeSpelling() {
		return alterNativeSpelling;
	}

	public void setAlterNativeSpelling(String alterNativeSpelling) {
		this.alterNativeSpelling = alterNativeSpelling;
	}
	
}
