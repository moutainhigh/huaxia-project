package com.huaxia.opas.domain.credit;
import java.io.Serializable;
import java.util.Date;
/*****************************************
 *@describe:学历查询匹配设置、 学历本年查询上限数设置 对象
 *@author xiaoJianFeng
 *@date:2017-03-20
 ******************************************/
public class EducationManagemet  implements Serializable{
	private static final long serialVersionUID = 1L;
	//OPAS_EDUCATION_SET表
	private String edu_autoId;//	序列值
	private String edu_eduRuleId;//规则id
	private String edu_eduRuleName;//规则名称
	private String edu_eduCurrStatus;//启用状态
	private String edu_crtUser;//创建人
	private Date edu_crtTime;//创建时间
	
	//OPAS_EDUBACK_MAXCOUNT表
	private String set_autoId;//	序列值
	private String set_autoMax;//自动查询学历上限数设置
	private String set_manualMax;//手动查询学历上限数设置
	private String set_totalMax;//总查询上限数
	private String set_autoStartTime;//自动查学历开始时间
	private String set_autoEndTime;//自动查学历结束时间
	private String set_manualStartTime;//手动查学历开始时间
	private String set_manualEndTime;//手动查学历结束时间
	private String set_bathTime;//批量查学历时间
	private String set_crtDate;//创建日期
	private String set_crtUser;//创建人
	
	private String incomingdata_type;// 类型   自动  手动
	private String zd_lifeQuery;//自动年限查询
	private String sd_lifeQuery;//手动年限查询
	private String lifeQuery;//总年限查询
	private String lifeQueryFlag;
	
	
	
	
	
	
	
	 
	public void setSet_autoStartTime(String set_autoStartTime) {
		this.set_autoStartTime = set_autoStartTime;
	}
	public void setSet_autoEndTime(String set_autoEndTime) {
		this.set_autoEndTime = set_autoEndTime;
	}
	public void setSet_manualStartTime(String set_manualStartTime) {
		this.set_manualStartTime = set_manualStartTime;
	}
	public void setSet_manualEndTime(String set_manualEndTime) {
		this.set_manualEndTime = set_manualEndTime;
	}
	public void setSet_bathTime(String set_bathTime) {
		this.set_bathTime = set_bathTime;
	}
	public void setSet_crtDate(String set_crtDate) {
		this.set_crtDate = set_crtDate;
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
	public String getIncomingdata_type() {
		return incomingdata_type;
	}
	public void setIncomingdata_type(String incomingdata_type) {
		this.incomingdata_type = incomingdata_type;
	}
	public String getSet_autoId() {
		return set_autoId;
	}
	public void setSet_autoId(String set_autoId) {
		this.set_autoId = set_autoId;
	}
	public String getSet_autoMax() {
		return set_autoMax;
	}
	public void setSet_autoMax(String set_autoMax) {
		this.set_autoMax = set_autoMax;
	}
	public String getSet_manualMax() {
		return set_manualMax;
	}
	public void setSet_manualMax(String set_manualMax) {
		this.set_manualMax = set_manualMax;
	}
	public String getSet_totalMax() {
		return set_totalMax;
	}
	public void setSet_totalMax(String set_totalMax) {
		this.set_totalMax = set_totalMax;
	}
	 
	 
	public String getSet_autoStartTime() {
		return set_autoStartTime;
	}
	public String getSet_autoEndTime() {
		return set_autoEndTime;
	}
	public String getSet_manualStartTime() {
		return set_manualStartTime;
	}
	public String getSet_manualEndTime() {
		return set_manualEndTime;
	}
	public String getSet_bathTime() {
		return set_bathTime;
	}
	public String getSet_crtDate() {
		return set_crtDate;
	}
	public String getSet_crtUser() {
		return set_crtUser;
	}
	public void setSet_crtUser(String set_crtUser) {
		this.set_crtUser = set_crtUser;
	}
	public String getEdu_autoId() {
		return edu_autoId;
	}
	public void setEdu_autoId(String edu_autoId) {
		this.edu_autoId = edu_autoId;
	}
	public String getEdu_eduRuleId() {
		return edu_eduRuleId;
	}
	public void setEdu_eduRuleId(String edu_eduRuleId) {
		this.edu_eduRuleId = edu_eduRuleId;
	}
	public String getEdu_eduRuleName() {
		return edu_eduRuleName;
	}
	public void setEdu_eduRuleName(String edu_eduRuleName) {
		this.edu_eduRuleName = edu_eduRuleName;
	}
	public String getEdu_eduCurrStatus() {
		return edu_eduCurrStatus;
	}
	public void setEdu_eduCurrStatus(String edu_eduCurrStatus) {
		this.edu_eduCurrStatus = edu_eduCurrStatus;
	}
	public String getEdu_crtUser() {
		return edu_crtUser;
	}
	public void setEdu_crtUser(String edu_crtUser) {
		this.edu_crtUser = edu_crtUser;
	}
	public Date getEdu_crtTime() {
		return edu_crtTime;
	}
	public void setEdu_crtTime(Date edu_crtTime) {
		this.edu_crtTime = edu_crtTime;
	}
 
	
	

}
