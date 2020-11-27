package com.huaxia.opas.domain.credit;
import java.io.Serializable;
/************************
 *@describe:人行查询管理 对象
 *@author xiaoJianFeng
 *@date:2017-03-20
 *************************/
public class HumanQueryManagemet implements Serializable{
	private static final long serialVersionUID = 1L;
	private String hu_autoId;//序列值
	private String hu_autoMax;// 自动查询人行上限数
	private String hu_manualMax;//手动查询人行上限数
	private String hu_totalMax;//总查询上限数
	private String hu_autoStartTime;//自动查人行开始时间
	private String hu_autoEndTime;//自动查人行结束时间
	private String hu_manualStartTime;//手动查人行开始时间
	private String hu_manualEndTime;//手动查人行结束时间
	private String hu_crtDate;//创建日期
	private String hu_crtUser;//创建人
	private String zd_lifeQuery;//自动年限查询
	private String sd_lifeQuery;//手动年限查询
	private String lifeQuery; 
	private String lifeQueryFlag;  
	
	public String getZd_lifeQuery() {
		return zd_lifeQuery;
	}
	public void setZd_lifeQuery(String zd_lifeQuery) {
		this.zd_lifeQuery = zd_lifeQuery;
	}
	public String getSd_lifeQuery() {
		return sd_lifeQuery;
	}
	public void setSd_lifeQuery(String sd_lifeQuery) {
		this.sd_lifeQuery = sd_lifeQuery;
	}
	public String getLifeQuery() {
		return lifeQuery;
	}
	public void setLifeQuery(String lifeQuery) {
		this.lifeQuery = lifeQuery;
	}
	public String getLifeQueryFlag() {
		return lifeQueryFlag;
	}
	public void setLifeQueryFlag(String lifeQueryFlag) {
		this.lifeQueryFlag = lifeQueryFlag;
	}
	public String getHu_autoId() {
		return hu_autoId;
	}
	public String getHu_autoMax() {
		return hu_autoMax;
	}
	public void setHu_autoMax(String hu_autoMax) {
		this.hu_autoMax = hu_autoMax;
	}
	public String getHu_manualMax() {
		return hu_manualMax;
	}
	public void setHu_manualMax(String hu_manualMax) {
		this.hu_manualMax = hu_manualMax;
	}
	public String getHu_totalMax() {
		return hu_totalMax;
	}
	public void setHu_totalMax(String hu_totalMax) {
		this.hu_totalMax = hu_totalMax;
	}
	public String getHu_autoStartTime() {
		return hu_autoStartTime;
	}
	public void setHu_autoStartTime(String hu_autoStartTime) {
		this.hu_autoStartTime = hu_autoStartTime;
	}
	public String getHu_autoEndTime() {
		return hu_autoEndTime;
	}
	public void setHu_autoEndTime(String hu_autoEndTime) {
		this.hu_autoEndTime = hu_autoEndTime;
	}
	public String getHu_manualStartTime() {
		return hu_manualStartTime;
	}
	public void setHu_manualStartTime(String hu_manualStartTime) {
		this.hu_manualStartTime = hu_manualStartTime;
	}
	public String getHu_manualEndTime() {
		return hu_manualEndTime;
	}
	public void setHu_manualEndTime(String hu_manualEndTime) {
		this.hu_manualEndTime = hu_manualEndTime;
	}
	public String getHu_crtDate() {
		return hu_crtDate;
	}
	public void setHu_crtDate(String hu_crtDate) {
		this.hu_crtDate = hu_crtDate;
	}
	public String getHu_crtUser() {
		return hu_crtUser;
	}
	public void setHu_crtUser(String hu_crtUser) {
		this.hu_crtUser = hu_crtUser;
	}
	public void setHu_autoId(String hu_autoId) {
		this.hu_autoId = hu_autoId;
	}
	 
	
	

}
