package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 行职业匹配收入实体类,适用于标准卡和易达金卡
 * @author lipengfei
 *
 */
public class Income implements Serializable{

	private static final long serialVersionUID = -2997444597304759399L;

	/**
	 * UUID
	 */
	private String uuId;
	
	/**
	 * 城市代码
	 */
	private String cityCode;
	
	/**
	 * 单位性质
	 */
	private String unionType;
	
	/**
	 * 教育程度
	 */
	private String education;
	
	/**
	 * 行业代码
	 */
	private String industryCode;
	
	/**
	 * 职业代码
	 */
	private String jobCode;
	
	/**
	 * 行职业匹配收入
	 */
	private String matchIncome;
	
	/**
	 * 创建日期
	 */
	private Date crtDate;
	
	/**
	 * 状态,0-停用,1-启用
	 */
	private String status;
	
	/**
	 * 操作人姓名
	 */
	private String operator;
	
	/**
	 * 备注
	 */
	private String remark;


	/**
	 * @return the uuId
	 */
	public String getUuId() {
		return uuId;
	}


	/**
	 * @param uuId the uuId to set
	 */
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}


	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}


	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}


	/**
	 * @return the unionType
	 */
	public String getUnionType() {
		return unionType;
	}


	/**
	 * @param unionType the unionType to set
	 */
	public void setUnionType(String unionType) {
		this.unionType = unionType;
	}


	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}


	/**
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}


	/**
	 * @return the industryCode
	 */
	public String getIndustryCode() {
		return industryCode;
	}


	/**
	 * @param industryCode the industryCode to set
	 */
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}


	/**
	 * @return the jobCode
	 */
	public String getJobCode() {
		return jobCode;
	}


	/**
	 * @param jobCode the jobCode to set
	 */
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}


	/**
	 * @return the matchIncome
	 */
	public String getMatchIncome() {
		return matchIncome;
	}


	/**
	 * @param matchIncome the matchIncome to set
	 */
	public void setMatchIncome(String matchIncome) {
		this.matchIncome = matchIncome;
	}


	/**
	 * @return the crtDate
	 */
	public Date getCrtDate() {
		return crtDate;
	}


	/**
	 * @param crtDate the crtDate to set
	 */
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}


	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}


	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}


	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Income(String uuId, String cityCode, String unionType, String education, String industryCode, String jobCode,
			String matchIncome, Date crtDate, String status, String operator, String remark) {
		super();
		this.uuId = uuId;
		this.cityCode = cityCode;
		this.unionType = unionType;
		this.education = education;
		this.industryCode = industryCode;
		this.jobCode = jobCode;
		this.matchIncome = matchIncome;
		this.crtDate = crtDate;
		this.status = status;
		this.operator = operator;
		this.remark = remark;
	}


	public Income() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * 设置income实例的ID的集合,便于批量操作时的调用
	 */
	private List<String> ids;


	public List<String> getIds() {
		return ids;
	}


	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	

	
	

	

	
	
	
}
