package com.huaxia.opas.domain.credit;

import java.io.Serializable;
import java.util.Date;

/**
 * 征信智能语音类
 * @author User
 *
 */
public class CreditSVoice implements Serializable{

	//来源 申请件分配表：OPAS_ALLOT_APPLY_ALLOT
	//分件状态
	private String check_number;//流水号
	private String check_ydjFlag;//易达金标示 1易达金 2标准卡
	private String matchingCardFlag;//套卡标示
	private String check_aproveFlag;//快速标志
	private String check_currNode;//当前操作环节 01：录入比对 02：征信调 03：人工审批
		
	//人员及队列状态
	private Date   check_userDate;
	private String check_GourpDate;//分组天数		计算得到结果
	private String check_queueDate;//进入队列天数      	计算得到结果
	private String check_lastOU;//最后操作员
	private String lastDate;//最后操作时间
	
	//来源 客户基本信息表[OPAS_CUST_BASEINFO]
	//客户状态
	private String check_custName;//客户姓名
	private String check_certType;//证件类型
	private String check_certNo;//证件号
	private String check_currentCN;//现单位名称
	
	//智能语音结论
	private String check_conclusion;//智能语音结论
	private String check_summary;//智能语音总结
	//备注
	private int remark;//备注
	public String getCheck_number() {
		return check_number;
	}
	public void setCheck_number(String check_number) {
		this.check_number = check_number;
	}
	public String getCheck_ydjFlag() {
		return check_ydjFlag;
	}
	public void setCheck_ydjFlag(String check_ydjFlag) {
		this.check_ydjFlag = check_ydjFlag;
	}
	public String getMatchingCardFlag() {
		return matchingCardFlag;
	}
	public void setMatchingCardFlag(String matchingCardFlag) {
		this.matchingCardFlag = matchingCardFlag;
	}
	public String getCheck_aproveFlag() {
		return check_aproveFlag;
	}
	public void setCheck_aproveFlag(String check_aproveFlag) {
		this.check_aproveFlag = check_aproveFlag;
	}
	public String getCheck_currNode() {
		return check_currNode;
	}
	public void setCheck_currNode(String check_currNode) {
		this.check_currNode = check_currNode;
	}
	public Date getCheck_userDate() {
		return check_userDate;
	}
	public void setCheck_userDate(Date check_userDate) {
		this.check_userDate = check_userDate;
	}
	public String getCheck_GourpDate() {
		return check_GourpDate;
	}
	public void setCheck_GourpDate(String check_GourpDate) {
		this.check_GourpDate = check_GourpDate;
	}
	public String getCheck_queueDate() {
		return check_queueDate;
	}
	public void setCheck_queueDate(String check_queueDate) {
		this.check_queueDate = check_queueDate;
	}
	public String getCheck_lastOU() {
		return check_lastOU;
	}
	public void setCheck_lastOU(String check_lastOU) {
		this.check_lastOU = check_lastOU;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getCheck_custName() {
		return check_custName;
	}
	public void setCheck_custName(String check_custName) {
		this.check_custName = check_custName;
	}
	public String getCheck_certType() {
		return check_certType;
	}
	public void setCheck_certType(String check_certType) {
		this.check_certType = check_certType;
	}
	public String getCheck_certNo() {
		return check_certNo;
	}
	public void setCheck_certNo(String check_certNo) {
		this.check_certNo = check_certNo;
	}
	public String getCheck_currentCN() {
		return check_currentCN;
	}
	public void setCheck_currentCN(String check_currentCN) {
		this.check_currentCN = check_currentCN;
	}
	public String getCheck_conclusion() {
		return check_conclusion;
	}
	public void setCheck_conclusion(String check_conclusion) {
		this.check_conclusion = check_conclusion;
	}
	public String getCheck_summary() {
		return check_summary;
	}
	public void setCheck_summary(String check_summary) {
		this.check_summary = check_summary;
	}
	public int getRemark() {
		return remark;
	}
	public void setRemark(int remark) {
		this.remark = remark;
	}
	
}
