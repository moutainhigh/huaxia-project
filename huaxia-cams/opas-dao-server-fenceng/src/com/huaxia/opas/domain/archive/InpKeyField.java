package com.huaxia.opas.domain.archive;

import java.io.Serializable;

/**
 * 
 * Description: 进件校验域
 * Copyright: Copyright (c) 2016
 * Company: HUATENG
 * Author 李涛
 * Version 1.0
 * Created at 2016-4-13
 */
public class InpKeyField implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3891525624621939693L;


	/**
	 * 字段名称
	 */
	private String name;


	/**
	 * 字段待转换类型
	 */
	private char toType;

	/**
	 * 字段长度
	 */
	private int length;

	/**
	 * 字段小数点位数，默认:0
	 */
	private int pointLen;

	/**
	 * 字段截取开始列数
	 */
	private int colBeginIndex;
	
	/**
	 * 字段注释
	 */
	private String comments;
	

	/**
	 * 字段是否必须, 取值：true/false，默认:false
	 */
	private boolean required = false;

	/**
	 *是否为附卡信息字段， 取值：true/false，默认:false
	 */
	private boolean card2 = false;
	
	public Integer getColBeginIndex() {
		return colBeginIndex;
	}

	public Integer getLength() {
		return length;
	}

	public String getName() {
		return name;
	}

	public Integer getPointLen() {
		return pointLen;
	}

	public boolean isRequired() {
		return required;
	}

	public void setColBeginIndex(Integer colBeginIndex) {
		this.colBeginIndex = colBeginIndex;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPointLen(Integer pointLen) {
		this.pointLen = pointLen;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public char getToType() {
		return toType;
	}

	public void setToType(char toType) {
		this.toType = toType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
	public boolean isCard2() {
		return card2;
	}

	public void setCard2(boolean card2) {
		this.card2 = card2;
	}

	@Override
	public String toString() {
		return "InpKeyField [colBeginIndex=" + colBeginIndex + ", length="
				+ length + ", name=" + name + ", pointLen=" + pointLen
				+ ", required=" + required + ", toType="
				+ toType + ", comments=" + comments + ",card2=" + card2 + "]";
	}
}
