package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 优化易达金客群策略 职级替代收入参数
 * @author liyanji
 *
 */
public class CustomersIncome implements Serializable{

	private static final long serialVersionUID = -2316813983971509644L;

	/**
	 * UUID
	 */
	private String uuId;
	
	/**
	 * 城市代码
	 */
	private String cityCode;
	
	/**
	 * 客群细分
	 */
	private String customersDetail;
	
	/**
	 * 职级
	 */
	private String jobLevel;
	
	/**
	 * 收入
	 */
	private String income;
	
	/**
	 * 创建日期
	 */
	private Date crtDate;
	/**
	 * 启用日期
	 */
	private Date startDate;
	/**
	 * 停用日期
	 */
	private Date stopDate;
	/**
	 * 最后一次修改日期
	 */
	private Date lstUpdDate;
	
	/**
	 * 状态,0-停用,1-启用
	 */
	private String status;
	
	/**
	 * 创建人姓名
	 */
	private String operator;
	/**
	 * 最后修改人
	 */
	private String lstUpdUser;
	/**
	 * 备注
	 */
	private String remark;

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCustomersDetail() {
		return customersDetail;
	}

	public void setCustomersDetail(String customersDetail) {
		this.customersDetail = customersDetail;
	}

	public String getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CustomersIncome(String uuId, String cityCode, String customersDetail, String jobLevel, String income,
			Date crtDate, Date startDate, Date stopDate, Date lstUpdDate, String status, String operator,
			String lstUpdUser, String remark, List<String> ids) {
		super();
		this.uuId = uuId;
		this.cityCode = cityCode;
		this.customersDetail = customersDetail;
		this.jobLevel = jobLevel;
		this.income = income;
		this.crtDate = crtDate;
		this.startDate = startDate;
		this.stopDate = stopDate;
		this.lstUpdDate = lstUpdDate;
		this.status = status;
		this.operator = operator;
		this.lstUpdUser = lstUpdUser;
		this.remark = remark;
		this.ids = ids;
	}

	public CustomersIncome() {
		super();
	}

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
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
