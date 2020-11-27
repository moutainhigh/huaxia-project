package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 反洗钱 & 护照信息
 * 
 * @author zhiguo.li
 *
 */
public class AMLPassportsInfo extends AML implements Serializable {

	private static final long serialVersionUID = -9030218569103526850L;
	
	// 黑名单唯一标识符
	private String id;
	
	// 国籍
	private String country;
	
	// 护照号码
	private String passportsNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassportsNo() {
		return passportsNo;
	}

	public void setPassportsNo(String passportsNo) {
		this.passportsNo = passportsNo;
	}
	
}
