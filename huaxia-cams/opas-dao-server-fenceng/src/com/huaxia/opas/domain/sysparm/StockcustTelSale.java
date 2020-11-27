package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
/**
 * 存量客户电销
 * @author 汪涛
 *
 */
public class StockcustTelSale implements Serializable{
	private static final long serialVersionUID = 4579067459573511567L;
	/**
	 * 唯一Id
	 */
	private String autoId;
	/**
	 * 账号
	 */
	private String acctNo;
	/**
	 * 身份证号码
	 */
	private String certifiNo;
	/**
	 * 客户姓名
	 */
	private String custName;
	/**
	 * 电销额度
	 */
	private int telSaleLimit;
	/**
	 * 调额过程
	 */
	private String adjustProcess;
	/**
	 * 贷后人行评分
	 */
	private int peopleBankScore;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 操作用户
	 */
	private String operator;
	/**
	 * 操作时间
	 */
	private Date operatTime;
	/**
	 * 当前状态
	 */
	private String currStatus;
	/**
	 * 操作内容
	 */
	private String operatContent;
	/**
	 * 操作动作
	 */
	private String operatDo;
	/**
	 * 用来导入排序
	 */
	private String orderByNo;
	public String getOperatContent() {
		return operatContent;
	}
	public void setOperatContent(String operatContent) {
		this.operatContent = operatContent;
	}
	public String getOperatDo() {
		return operatDo;
	}
	public void setOperatDo(String operatDo) {
		this.operatDo = operatDo;
	}
	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public String getCertifiNo() {
		return certifiNo;
	}
	public void setCertifiNo(String certifiNo) {
		this.certifiNo = certifiNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public int getTelSaleLimit() {
		return telSaleLimit;
	}
	public void setTelSaleLimit(int telSaleLimit) {
		this.telSaleLimit = telSaleLimit;
	}
	public String getAdjustProcess() {
		return adjustProcess;
	}
	public void setAdjustProcess(String adjustProcess) {
		this.adjustProcess = adjustProcess;
	}
	public int getPeopleBankScore() {
		return peopleBankScore;
	}
	public void setPeopleBankScore(int peopleBankScore) {
		this.peopleBankScore = peopleBankScore;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	
	public String getOrderByNo() {
		return orderByNo;
	}
	public void setOrderByNo(String orderByNo) {
		this.orderByNo = orderByNo;
	}
	@Override
	public String toString() {
		return "StockcustTelSale [autoId=" + autoId + ", acctNo=" + acctNo + ", certifiNo=" + certifiNo + ", custName="
				+ custName + ", telSaleLimit=" + telSaleLimit + ", adjustProcess=" + adjustProcess
				+ ", peopleBankScore=" + peopleBankScore + ", createTime=" + createTime + ", operator=" + operator
				+ ", operatTime=" + operatTime + ", currStatus=" + currStatus + ", operatContent=" + operatContent
				+ ", operatDo=" + operatDo + ", orderByNo=" + orderByNo + "]";
	}
}
