package com.huaxia.opas.entity;

import java.io.Serializable;

/**
 * 接口请求参数
 * 
 * @author zhiguo.li
 *
 */
public class QueryParam implements Serializable {

	private static final long serialVersionUID = 7296885868030608784L;
	
	// 姓名
	private String name;

	// 证件类型
	private String certType;
	
	// 证件号码
	private String certNo;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

}
