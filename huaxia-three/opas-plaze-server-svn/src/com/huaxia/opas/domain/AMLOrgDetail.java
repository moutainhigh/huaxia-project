package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 反洗钱 & 相关组织机构信息
 * 
 * @author zhiguo.li
 *
 */
public class AMLOrgDetail extends AML implements Serializable {

	private static final long serialVersionUID = 2572662755614891429L;
	
	// 档案唯一ID
	private String id;
	
	// 组织机构ID
	private String companyId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
}
