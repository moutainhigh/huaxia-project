package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统参数--身份类风险名单实体类
 * 
 * @author luzhen.ou
 * 
 * @since 2017-02-28
 * 
 * @version 1.0
 * 
 */
public class IdentityRisk implements Serializable {

	private static final long serialVersionUID = 4751993142188855136L;

	/**
	 * 身份类风险实例的ID PK NOT_NULL
	 */
	private String autoId;
	
	/**
	 * 身份类风险实例历史操作表的ID NULL
	 */
	private String opretionId;

	/**
	 * 身份类风险实例的姓名
	 */
	private String name;
	
	/**
	 * 身份类风险实例的证件类型
	 */	
	private String certifiType;
	
	/**
	 * 身份类风险实例的证件号
	 */
	private String certifiNo;
	
	/**
	 * 身份类风险实例的消息来源
	 */	
	private String infoChannel;
	
	/**
	 * 身份类风险实例的欺诈类型
	 */	
	private String fraudType;
	
	/**
	 * 身份类风险实例的备注
	 */	
	private String memo;
	
	/**
	 * 身份类风险实例的其他证件类型
	 */	
	private String certifiTypeExtra;
	
	/**
	 * 身份类风险实例的其他证件消息来源
	 */	
	private String infoChannelExtra;
	
	/**
	 * 身份类风险实例的其他证件欺诈类型
	 */	
	private String fraudTypeExtra;
	
	/**
	 * 身份类风险实例的失效时间
	 */
	private Date invalidTime;

	/**
	 * 身份类风险实例的添加时间
	 */
	private Date createTime;

	/**
	 * 身份类风险实例的操作用户
	 */
	private String operator;

	/**
	 * 身份类风险实例的操作时间
	 */
	private Date operatTime;

	/**
	 * 身份类风险实例的当前状态
	 */
	private String currStatus;

	/**
	 * 身份类风险实例的ID的集合
	 */
	private List<String> ids ;

	/**
	 * 获取身份类风险实例的ID
	 */
	public String getAutoId() {
		return autoId;
	}

	/**
	 * 设置身份类风险实例的ID
	 */
	public void setAutoId(String autoId) {
		this.autoId = (autoId == null ? null : autoId.trim());
	}

	/**
	 * 获取身份类风险实例的姓名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置身份类风险实例的姓名
	 */
	public void setName(String name) {
		this.name = (name == null ? null : name.trim());
	}
	
	/**
	 * 获取身份类风险实例的证件类型
	 */	
	public String getCertifiType() {
		return certifiType;
	}
	
	/**
	 * 设置身份类风险实例的证件类型
	 */
	public void setCertifiType(String certifiType) {
		this.certifiType = (certifiType == null ? null : certifiType.trim());
	}

	/**
	 * 获取身份类风险实例的证件号
	 */
	public String getCertifiNo() {
		return certifiNo;
	}
	
	/**
	 * 设置身份类风险实例的证件号
	 */
	public void setCertifiNo(String certifiNo) {
		this.certifiNo = (certifiNo == null ? null : certifiNo.trim());
	}

	/**
	 * 获取身份类风险实例的失效时间
	 */
	public Date getInvalidTime() {
		return invalidTime;
	}

	/**
	 * 设置身份类风险实例的失效时间
	 */
	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	/**
	 * 获取身份类风险实例的添加时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置身份类风险实例的添加时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取身份类风险实例的操作用户
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * 设置身份类风险实例的操作用户
	 */	
	public void setOperator(String operator) {
		this.operator = (operator == null ? null : operator.trim());
	}

	/**
	 * 获取身份类风险实例的操作时间
	 */	
	public Date getOperatTime() {
		return operatTime;
	}

	/**
	 * 设置身份类风险实例的操作时间
	 */
	public void setOperatTime(Date operatTime) {
		this.operatTime = operatTime;
	}

	/**
	 * 获取身份类风险实例的当前状态
	 */	
	public String getCurrStatus() {
		return currStatus;
	}

	/**
	 * 设置身份类风险实例的当前状态
	 */	
	public void setCurrStatus(String currStatus) {
		this.currStatus = (currStatus == null ? null : currStatus.trim());
	}


	/**
	 * 获取身份类风险实例的ID的集合
	 */
	public List<String> getIds() {
		return ids;
	}

	/**
	 * 设置身份类风险实例的ID的集合
	 */
	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	/**
	 * 获取身份类风险实例历史操作表的ID
	 */
	public String getOpretionId() {
		return opretionId;
	}

	/**
	 * 设置身份类风险实例历史操作表的ID
	 */
	public void setOpretionId(String opretionId) {
		this.opretionId = (opretionId == null ? null :opretionId.trim());
	}
	
	/**
	 * 获取身份类风险实例的消息来源
	 */	
	public String getInfoChannel() {
		return infoChannel;
	}
	
	/**
	 * 设置身份类风险实例的消息来源
	 */	
	public void setInfoChannel(String infoChannel) {
		this.infoChannel = (infoChannel == null ? null :infoChannel.trim());
	}
	
	/**
	 * 获取身份类风险实例的欺诈类型
	 */	
	public String getFraudType() {
		return fraudType;
	}
	
	/**
	 * 设置身份类风险实例的欺诈类型
	 */	
	public void setFraudType(String fraudType) {
		this.fraudType = (fraudType == null ? null :fraudType.trim());
	}
	
	/**
	 * 设置身份类风险实例的备注
	 */	
	public String getMemo() {
		return memo;
	}
	
	/**
	 * 获取身份类风险实例的备注
	 */	
	public void setMemo(String memo) {
		this.memo = (memo == null ? null :memo.trim());
	}
	
	/**
	 * 获取身份类风险实例的其他证件类型
	 */	
	public String getCertifiTypeExtra() {
		return certifiTypeExtra;
	}
	
	/**
	 * 设置身份类风险实例的其他证件类型
	 */	
	public void setCertifiTypeExtra(String certifiTypeExtra) {
		this.certifiTypeExtra = (certifiTypeExtra == null ? null :certifiTypeExtra.trim());
	}
	
	/**
	 * 获取身份类风险实例的其他证件消息来源
	 */	
	public String getInfoChannelExtra() {
		return infoChannelExtra;
	}
	
	/**
	 * 设置身份类风险实例的其他证件消息来源
	 */	
	public void setInfoChannelExtra(String infoChannelExtra) {
		this.infoChannelExtra = (infoChannelExtra == null ? null :infoChannelExtra.trim());
	}
	
	/**
	 * 获取身份类风险实例的其他证件欺诈类型
	 */	
	public String getFraudTypeExtra() {
		return fraudTypeExtra;
	}
	
	/**
	 * 设置身份类风险实例的其他证件欺诈类型
	 */	
	public void setFraudTypeExtra(String fraudTypeExtra) {
		this.fraudTypeExtra = (fraudTypeExtra == null ? null :fraudTypeExtra.trim());
	}

	@Override
	public String toString() {
		return "IdentityRisk [autoId=" + autoId + ", name=" + name + ", certifiType=" + certifiType + ", certifiNo="
				+ certifiNo + ", infoChannel=" + infoChannel + ", fraudType=" + fraudType + ", memo=" + memo
				+ ", certifiTypeExtra=" + certifiTypeExtra + ", infoChannelExtra=" + infoChannelExtra
				+ ", fraudTypeExtra=" + fraudTypeExtra + ", invalidTime=" + invalidTime + ", operator=" + operator
				+ ", currStatus=" + currStatus + "]";
	}
	
}
