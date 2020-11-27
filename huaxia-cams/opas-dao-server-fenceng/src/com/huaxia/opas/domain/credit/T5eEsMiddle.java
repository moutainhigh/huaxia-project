package com.huaxia.opas.domain.credit;

import java.io.Serializable;

//反洗钱全量数据
public class T5eEsMiddle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8379044076821762071L;
	private String id;// 档案唯一ID
	private String ei;// 档案类型
	private String category;// 分类
	private String updateCategory;// 更新类型
	private String subCategory;// 子分类
	private String updated;// 更新日期
	private String position;// 职位（PEP相关）
	private String title;// 头衔
	private String placeOfBirth;// 出生地址
	private String dob;// 出生日期
	private String deceased;// 死亡日期
	private String countries;// 国籍
	private String firstName;// 名
	private String lastName;// 姓
	private String entered;// 输入日期
	private String no;// 证件号码
	private String passportsNo;// 护照号码
	private String fullName;// 全名
	private String cardType;// 证件类型（51 身份证， 52护照，01其他）
	private String cardNo;// 证件号码
	private String createDt;// 数据日期
	private String listSource;// 名单来源（1路透名单，2手工录入数据）
	private String createTm;// 创建日期
	private String altasName;// 别名
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
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
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getPassportsNo() {
		return passportsNo;
	}
	public void setPassportsNo(String passportsNo) {
		this.passportsNo = passportsNo;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCreateDt() {
		return createDt;
	}
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
	public String getListSource() {
		return listSource;
	}
	public void setListSource(String listSource) {
		this.listSource = listSource;
	}
	public String getCreateTm() {
		return createTm;
	}
	public void setCreateTm(String createTm) {
		this.createTm = createTm;
	}
	public String getAltasName() {
		return altasName;
	}
	public void setAltasName(String altasName) {
		this.altasName = altasName;
	}
	@Override
	public String toString() {
		return "T5eEsMiddle [id=" + id + ", ei=" + ei + ", category=" + category + ", updateCategory=" + updateCategory
				+ ", subCategory=" + subCategory + ", updated=" + updated + ", position=" + position + ", title="
				+ title + ", placeOfBirth=" + placeOfBirth + ", dob=" + dob + ", deceased=" + deceased + ", countries="
				+ countries + ", firstName=" + firstName + ", lastName=" + lastName + ", entered=" + entered + ", no="
				+ no + ", passportsNo=" + passportsNo + ", fullName=" + fullName + ", cardType=" + cardType
				+ ", cardNo=" + cardNo + ", createDt=" + createDt + ", listSource=" + listSource + ", createTm="
				+ createTm + ", altasName=" + altasName + "]";
	}
	

}
