package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 反洗钱 & 路透中文名称信息
 * 
 * @author zhiguo.li
 *
 */
public class AMLForeignNameDetail extends AML implements Serializable {

	private static final long serialVersionUID = -6408212463230864094L;
	
	// 档案唯一ID
	private String id;
	
	// 别名类型
	private String languageType;
	
	// 原语言名称
	private String foreignAliasName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

	public String getForeignAliasName() {
		return foreignAliasName;
	}

	public void setForeignAliasName(String foreignAliasName) {
		this.foreignAliasName = foreignAliasName;
	}
	
}
