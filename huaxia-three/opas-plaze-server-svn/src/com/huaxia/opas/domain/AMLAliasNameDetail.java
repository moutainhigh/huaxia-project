package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 反洗钱 & 别名信息
 * 
 * @author zhiguo.li
 *
 */
public class AMLAliasNameDetail extends AML implements Serializable {

	private static final long serialVersionUID = 4881684346131218045L;
	
	// 黑名单唯一标识码
	private String id;
	
	// 别名
	private String aliasName;
	
	// 姓名类型
	private String nameType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getNameType() {
		return nameType;
	}

	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	
}
