package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @version1.0
 * @author liuwei
 *2020
 */
@SuppressWarnings("serial")
public class LowRiskCustomers implements Serializable {
	/**
	 * 唯一标示
	 */
	private String uuid;
	
	/**
	 * 创建人
	 */
	private String crtUser;
	
	/**
	 * 创建时间
	 */
	private Timestamp crtTime;
	
	
	/**
	 * 姓名
	 */
	private String cusName;
	
	/**
	 * 身份证号码
	 */
	private String credNo;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 单位名称
	 */
	private String companyName;

	/**
	 * 名单类型 1-欺诈 2-信用
	 */
	private String  listType;
	
	/**
	 * 启停状态 0-停用 1-启用
	 */
	private String STATUS;
	
    private String operator;
	/**
	 * 低风险客户名单的ID的集合
	 */
	private List<String> ids;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	
	public Timestamp getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Timestamp crtTime) {
		this.crtTime = crtTime;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCredNo() {
		return credNo;
	}

	public void setCredNo(String credNo) {
		this.credNo = credNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}


	/**
	 * 获取低风险客户名单实例的ID的集合
	 */
	public List<String> getIds() {
		return ids;
	}

	/**
	 * 设置低风险客户名单实例的ID的集合
	 */
	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return "LowRiskCustomers [uuid=" + uuid + ", crtUser=" + crtUser + ", crtTime=" + crtTime + ", cusName="
				+ cusName + ", credNo=" + credNo + ", mobile=" + mobile + ", companyName=" + companyName + ", listType="
				+ listType + ", STATUS=" + STATUS + ", operator=" + operator + "]";
	}

}
