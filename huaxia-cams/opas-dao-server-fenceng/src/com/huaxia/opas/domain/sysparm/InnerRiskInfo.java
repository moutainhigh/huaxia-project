package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 名单库管理--内部风险信息名单实体类
 * 
 * @author luzhen.ou
 * 
 * @since 2017-03-16
 * 
 * @version 1.0
 */
public class InnerRiskInfo implements Serializable {
	
	private static final long serialVersionUID = 6992448495323853218L;

	/**
	 * 内部风险信息名单实例的ID PK NOT_NULL
	 */
	private String autoId;
	
	/**
	 * 内部风险信息名单实例的历史操作ID NULL
	 */
	private String opretionId;

	/**
	 * 内部风险信息名单实例的单位名称
	 */
	private String companyName;

	/**
	 * 内部风险信息名单实例的单位电话
	 */
	private String companyTel;	

	/**
	 * 内部风险信息名单实例的证件号码
	 */
	private String certifiNo;
	
	/**
	 * 内部风险信息名单实例的户籍
	 */
	private String houseHold;
	
	/**
	 * 内部风险信息名单实例的推广机构
	 */
	private String spreadOrg;
	
	/**
	 * 内部风险信息名单实例的推广人员
	 */
	private String spreadPer;
	
	/**
	 * 内部风险信息名单实例的推广人编号
	 */
	private String spreadNo;
	
	/**
	 * 内部风险信息名单实例的内容描述（输入项）
	 */
	private String contentDesc;
	
	/**
	 * 内部风险信息名单实例的失效时间
	 */
	private Date invalidTime;

	/**
	 * 内部风险信息名单实例的添加时间
	 */
	private Date createTime;

	/**
	 *内部风险信息名单实例的操作时间
	 */
	private Date operatTime;
	
	/**
	 * 内部风险信息名单实例的操作用户
	 */
	private String operator;

	/**
	 * 内部风险信息名单实例的当前状态
	 */
	private String currStatus;

	/**
	 * 身份类风险名单实例的ID的集合
	 */
	private List<String> ids ;
	
	/**
	 * 风险等级 
	 */
	private String opretionLevel;
	
	public String getOpretionLevel() {
		return opretionLevel;
	}

	public void setOpretionLevel(String opretionLevel) {
		this.opretionLevel = opretionLevel;
	}

	/**
	 * 批量导入排序
	 */
	private String orderByNo;
	public String getOrderByNo() {
		return orderByNo;
	}

	public void setOrderByNo(String orderByNo) {
		this.orderByNo = orderByNo;
	}

	/**
	 * 获取身份类风险名单实例的ID
	 */
	public String getAutoId() {
		return autoId;
	}

	/**
	 * 设置身份类风险名单实例的ID
	 */
	public void setAutoId(String autoId) {
		this.autoId = (autoId == null ? null : autoId.trim());
	}
	
	/**
	 * 获取身份类风险名单实例的证件号码
	 */
	public String getCertifiNo() {
		return certifiNo;
	}
	
	/**
	 * 设置身份类风险名单实例的证件号码
	 */
	public void setCertifiNo(String certifiNo) {
		this.certifiNo = (certifiNo == null ? null : certifiNo.trim());
	}

	/**
	 * 获取身份类风险名单实例的失效时间
	 */
	public Date getInvalidTime() {
		return invalidTime;
	}

	/**
	 * 设置身份类风险名单实例的失效时间
	 */
	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	/**
	 * 获取身份类风险名单实例的添加时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置身份类风险名单实例的添加时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取身份类风险名单实例的操作用户
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * 设置身份类风险名单实例的操作用户
	 */	
	public void setOperator(String operator) {
		this.operator = (operator == null ? null : operator.trim());
	}

	/**
	 * 获取身份类风险名单实例的操作时间
	 */	
	public Date getOperatTime() {
		return operatTime;
	}

	/**
	 * 设置身份类风险名单实例的操作时间
	 */
	public void setOperatTime(Date operatTime) {
		this.operatTime = operatTime;
	}

	/**
	 * 获取身份类风险名单实例的当前状态
	 */	
	public String getCurrStatus() {
		return currStatus;
	}

	/**
	 * 设置身份类风险名单实例的当前状态
	 */	
	public void setCurrStatus(String currStatus) {
		this.currStatus = (currStatus == null ? null : currStatus.trim());
	}

	/**
	 * 获取身份类风险名单实例的ID的集合
	 */
	public List<String> getIds() {
		return ids;
	}

	/**
	 * 设置身份类风险名单实例的ID的集合
	 */
	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	/**
	 * 获取身份类风险名单实例历史操作表的ID
	 */
	public String getOpretionId() {
		return opretionId;
	}

	/**
	 * 设置身份类风险名单实例历史操作表的ID
	 */
	public void setOpretionId(String opretionId) {
		this.opretionId = (opretionId == null ? null :opretionId.trim());
	}

	/**
	 * 获取征信电话核查白名单实例的单位名称
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 设置电话核查白名单实例的单位名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = (companyName == null ? null : companyName.trim());
	}
	
	/**
	 * 征信电话核查白名单实例的单位电话
	 */
	public String getCompanyTel() {
		return companyTel;
	}

	/**
	 * 征信电话核查白名单实例的单位电话
	 */
	public void setCompanyTel(String companyTel) {
		this.companyTel = (companyTel == null ? null : companyTel.trim());
	}
	
	/**
	 * 获取内部风险信息名单实例的户籍
	 */
	public String getHouseHold() {
		return houseHold;
	}
	
	/**
	 * 设置内部风险信息名单实例的户籍
	 */
	public void setHouseHold(String houseHold) {
		this.houseHold = (houseHold == null ? null : houseHold.trim());
	}
	
	/**
	 * 获取内部风险信息名单实例的推广机构
	 */
	public String getSpreadOrg() {
		return spreadOrg;
	}
	
	/**
	 * 设置内部风险信息名单实例的推广机构
	 */
	public void setSpreadOrg(String spreadOrg) {
		this.spreadOrg = (spreadOrg == null ? null : spreadOrg.trim());
	}
	
	/**
	 * 获取内部风险信息名单实例的推广人员
	 */
	public String getSpreadPer() {
		return spreadPer;
	}
	
	/**
	 * 设置内部风险信息名单实例的推广人员
	 */
	public void setSpreadPer(String spreadPer) {
		this.spreadPer = (spreadPer == null ? null : spreadPer.trim());
	}

	/**
	 * 获取内部风险信息名单实例的推广人员编号
	 */	
	public String getSpreadNo() {
		return spreadNo;
	}
	
	/**
	 * 设置内部风险信息名单实例的推广人员编号
	 */
	public void setSpreadNo(String spreadNo) {
		this.spreadNo = (spreadNo == null ? null : spreadNo.trim());
	}
	
	/**
	 * 获取内部风险信息名单实例的内容描述（输入项）
	 */
	public String getContentDesc() {
		return contentDesc;
	}
	
	/**
	 * 设置内部风险信息名单实例的内容描述（输入项）
	 */
	public void setContentDesc(String contentDesc) {
		this.contentDesc = (contentDesc == null ? null : contentDesc.trim());
	}

	@Override
	public String toString() {
		return "InnerRiskInfo [autoId=" + autoId + ", companyName=" + companyName + ", companyTel=" + companyTel
				+ ", certifiNo=" + certifiNo + ", houseHold=" + houseHold + ", spreadOrg=" + spreadOrg + ", spreadPer="
				+ spreadPer + ", spreadNo=" + spreadNo + ", contentDesc=" + contentDesc + ", invalidTime=" + invalidTime
				+ ", operator=" + operator + ", currStatus=" + currStatus + "]";
	}
	
}
