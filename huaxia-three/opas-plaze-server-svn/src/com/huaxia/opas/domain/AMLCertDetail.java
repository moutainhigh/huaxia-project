package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 反洗钱 & 证件信息
 * 
 * @author zhiguo.li
 *
 */
public class AMLCertDetail extends AML implements Serializable {

	private static final long serialVersionUID = 1493554798692887204L;
	
	// 档案唯一ID
	private String id;
	
	// ISO国家编码
	private String loc;
	
	// 证件类型缩写
	private String type;
	
	// 证件号码
	private String no;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
}
