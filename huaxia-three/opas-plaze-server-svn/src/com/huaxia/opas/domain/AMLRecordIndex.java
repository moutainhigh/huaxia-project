package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 反洗钱 & 路透主索引信息
 * 
 * @author zhiguo.li
 *
 */
public class AMLRecordIndex extends AML implements Serializable {

	private static final long serialVersionUID = -4572417096989252988L;
	
	// 档案信息
	private String id;
	
	// 档案类型
	private String ei;
	
	// 分类
	private String category;
	
	// 更新类型
	private String updateCategory;
	
	// 子分类
	private String subCategory;
	
	// 更新日期
	private String updated;
	
	// 职位（PEP相关）
	private String position;
	
	// 头衔
	private String title;
	
	// 出生地址
	private String placeOfBirth;
	
	// 出生日期
	private String bob;
	
	// 死亡日期
	private String deceased;
	
	// 国籍
	private String countries;
	
	// 名
	private String firstName;
	
	// 姓
	private String lastName;
	
	// 输入日期
	private String entered;
	
	// 导入日期
	private String impDateStr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEi() {
		return ei;
	}

	public void setEi(String ei) {
		this.ei = ei;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUpdateCategory() {
		return updateCategory;
	}

	public void setUpdateCategory(String updateCategory) {
		this.updateCategory = updateCategory;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getBob() {
		return bob;
	}

	public void setBob(String bob) {
		this.bob = bob;
	}

	public String getDeceased() {
		return deceased;
	}

	public void setDeceased(String deceased) {
		this.deceased = deceased;
	}

	public String getCountries() {
		return countries;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEntered() {
		return entered;
	}

	public void setEntered(String entered) {
		this.entered = entered;
	}

	public String getImpDateStr() {
		return impDateStr;
	}

	public void setImpDateStr(String impDateStr) {
		this.impDateStr = impDateStr;
	}

}
