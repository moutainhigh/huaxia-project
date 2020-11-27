package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 反洗钱 & 信息使用地区
 * 
 * @author zhiguo.li
 *
 */
public class AMLInfoUseArea extends AML implements Serializable {

	private static final long serialVersionUID = 3693589047022214866L;
	
	// 黑名单唯一标识码
	private String id;
	
	// 国籍
	private String country;
	
	// 城市
	private String city;
	
	// 地区
	private String state;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
