package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;

/**
 * 年费方式实体类
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public class YearFeeDerate implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -1276166886761745503L;

	/**
	 * ID
	 */
	private String autoId;

	/**
	 * 年费编号
	 */
	private String yearFeeDerateCode;

	/**
	 * 年费描述
	 */
	private String yearFeeDerateDesc;


	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = (autoId == null ? null : autoId.trim());
	}


	public String getYearFeeDerateCode() {
		return yearFeeDerateCode;
	}

	public void setYearFeeDerateCode(String yearFeeDerateCode) {
		this.yearFeeDerateCode = (yearFeeDerateCode == null ? null : yearFeeDerateCode.trim());
	}

	public String getYearFeeDerateDesc() {
		return yearFeeDerateDesc;
	}

	public void setYearFeeDerateDesc(String yearFeeDerateDesc) {
		this.yearFeeDerateDesc = (yearFeeDerateDesc == null ? null : yearFeeDerateDesc.trim());
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
