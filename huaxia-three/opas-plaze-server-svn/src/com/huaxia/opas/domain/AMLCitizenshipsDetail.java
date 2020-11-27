package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 反洗钱 & 归属国信息
 * 
 * @author zhiguo.li
 *
 */
public class AMLCitizenshipsDetail extends AML implements Serializable {

	private static final long serialVersionUID = -7344132480544214890L;
	
	// 档案唯一ID
	private String id;
	
	// 归属国
	private String citizenships;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCitizenships() {
		return citizenships;
	}

	public void setCitizenships(String citizenships) {
		this.citizenships = citizenships;
	}
	
}
