package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 
 * @author susha 2017/3/13
 *
 */
public class CreditWhiteList implements Serializable{

	private static final long serialVersionUID = -1086223624951327786L;

	private String autoId;//id 主键 
	private String companyName;//单位名称
	private String companyTel;//单位电话
	private String companyAddr;//单位地址
	private String companyDept;//单位部门
	private String legaler;//法人
	private String registerFund;//注册资金
	private String registerAddr;//注册地址
	private String manageStatus;//经营状态
	private String partnerInfo;//股东信息
	private String manageExceptionInfo;//经营异常信息
	private String operator;//操作用户
	private Date operatTime;//操作时间
	private String currStatus;//当前状态
	private String operatStatus;//审核状态  null未进行审核/0未通过/1通过
	private String orderByNo;//批量导入的排序
	//getter&setter
	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyTel() {
		return companyTel;
	}
	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
	public String getCompanyAddr() {
		return companyAddr;
	}
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}
	public String getLegaler() {
		return legaler;
	}
	public void setLegaler(String legaler) {
		this.legaler = legaler;
	}
	public String getRegisterFund() {
		return registerFund;
	}
	public void setRegisterFund(String registerFund) {
		this.registerFund = registerFund;
	}
	public String getRegisterAddr() {
		return registerAddr;
	}
	public void setRegisterAddr(String registerAddr) {
		this.registerAddr = registerAddr;
	}
	public String getManageStatus() {
		return manageStatus;
	}
	public void setManageStatus(String manageStatus) {
		this.manageStatus = manageStatus;
	}
	public String getPartnerInfo() {
		return partnerInfo;
	}
	public void setPartnerInfo(String partnerInfo) {
		this.partnerInfo = partnerInfo;
	}
	public String getManageExceptionInfo() {
		return manageExceptionInfo;
	}
	public void setManageExceptionInfo(String manageExceptionInfo) {
		this.manageExceptionInfo = manageExceptionInfo;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperatTime() {
		return operatTime;
	}
	public void setOperatTime(Date operatTime) {
		this.operatTime = operatTime;
	}
	public String getCurrStatus() {
		return currStatus;
	}
	public void setCurrStatus(String currStatus) {
		this.currStatus = currStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDelStatus() {
		return operatStatus;
	}
	public void setDelStatus(String operatStatus) {
		this.operatStatus = operatStatus;
	}
	
	/**
	 * 标准卡征信信息名单实例历史操作表的ID NULL
	 * @author ouluzhen 2017/3/15
	 */
	private String opretionId;
	
	/**
	 * 标准卡征信信息名单实例的ID的集合 NULL
	 * @author ouluzhen 2017/3/15
	 */
	private List<String> ids;
	
	/**
	 * 标准卡征信信息名单实例的添加时间 NULL
	 * @author ouluzhen 2017/3/15
	 */
	private Date createTime;

	/**
	 * 征信电话核查白名单实例的申请件编号 NULL
	 * @author ouluzhen 2017/3/15
	 */
	private String appId;	
	
	/**
	 * 获取标准卡征信信息名单实例历史操作表的ID NULL
	 * @author ouluzhen 2017/3/15
	 */
	public String getOpretionId() {
		return opretionId;
	}

	/**
	 * 设置标准卡征信信息名单实例历史操作表的ID NULL
	 * @author ouluzhen 2017/3/15
	 */
	public void setOpretionId(String opretionId) {
		this.opretionId = (opretionId == null ? null : opretionId.trim());
	}	

	/**
	 * 获取标准卡征信信息名单实例的ID的集合 NULL
	 * @author ouluzhen 2017/3/15
	 */
	public List<String> getIds() {
		return ids;
	}

	/**
	 * 设置标准卡征信信息名单实例的ID的集合 NULL
	 * @author ouluzhen 2017/3/15
	 */
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	
	/**
	 * 获取标准卡征信信息名单实例的添加时间
	 * @author ouluzhen 2017/3/15
	 */	
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置标准卡征信信息名单实例的添加时间
	 * @author ouluzhen 2017/3/15
	 */	
	public void setCreateTime(Date createTime) {
		this.createTime = (createTime == null ? null : createTime);
	}
	
	/**
	 * 获取标准卡征信信息名单实例历史操作表的ID NULL
	 * @author ouluzhen 2017/3/15
	 */	
	public String getAppId() {
		return appId;
	}
	
	/**
	 * 设置标准卡征信信息名单实例历史操作表的ID NULL
	 * @author ouluzhen 2017/3/15
	 */	
	public void setAppId(String appId) {
		this.appId = (appId == null ? null : appId);
	}
	
	public String getOrderByNo() {
		return orderByNo;
	}
	public void setOrderByNo(String orderByNo) {
		this.orderByNo = orderByNo;
	}
	
	/**
	 * 标准卡名单库增加公司部门
	 * @author andong 2018/12/28
	 */	
	public String getCompanyDept() {
		return companyDept;
	}
	public void setCompanyDept(String companyDept) {
		this.companyDept = companyDept;
	}
	public String getOperatStatus() {
		return operatStatus;
	}
	public void setOperatStatus(String operatStatus) {
		this.operatStatus = operatStatus;
	}
	@Override
	public String toString() {
		return "CreditWhiteList [autoId=" + autoId + ", companyName=" + companyName + ", companyTel=" + companyTel
				+ ", companyAddr=" + companyAddr + ", legaler=" + legaler + ", registerFund=" + registerFund
				+ ", registerAddr=" + registerAddr + ", manageStatus=" + manageStatus + ", partnerInfo=" + partnerInfo
				+ ", manageExceptionInfo=" + manageExceptionInfo + ", operator=" + operator + ", currStatus="
				+ currStatus + ", appId=" + appId + "]";
	}
	
}
