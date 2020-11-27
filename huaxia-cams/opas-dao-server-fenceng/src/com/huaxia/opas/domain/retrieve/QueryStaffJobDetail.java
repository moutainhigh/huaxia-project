package com.huaxia.opas.domain.retrieve;

import java.io.Serializable;

//zhanglibo  组长调阅  组员工作情况统计
public class QueryStaffJobDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6274523957775352645L;
	private String id;
	private String remarkInfoFig;//是否有组织备注标识
	private String appId;//流水号
	private String c1Cname;//客户姓名
	private String c1Idnbr;//证件号码
	private String c1Coname;//单位名称
	private String lastOptUser;//最后操作员
	private String lstTeamDate;//最后操作时间
	private String remark;//备注
	private String delStatus;//0:未完成	1:已完成	2:补件	3:退回	4:挂起
	private String dilStatusFig;
	private String appProd;//申请卡片产品
	private int queDate;//进去队列天数
	private String ydjFig;
	private String matchingCardFlag;//套卡标识 
	private String currNode;//当前节点 
	//private String lastOptUser;//最后操作人
	public String getId() {
		return id;
	}
	public String getRemarkInfoFig() {
		return remarkInfoFig;
	}
	public void setRemarkInfoFig(String remarkInfoFig) {
		this.remarkInfoFig = remarkInfoFig;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getC1Cname() {
		return c1Cname;
	}
	public void setC1Cname(String c1Cname) {
		this.c1Cname = c1Cname;
	}
	public String getC1Idnbr() {
		return c1Idnbr;
	}
	public void setC1Idnbr(String c1Idnbr) {
		this.c1Idnbr = c1Idnbr;
	}
	public String getC1Coname() {
		return c1Coname;
	}
	public void setC1Coname(String c1Coname) {
		this.c1Coname = c1Coname;
	}
	public String getLastOptUser() {
		return lastOptUser;
	}
	public void setLastOptUser(String lastOptUser) {
		this.lastOptUser = lastOptUser;
	}
	public String getLstTeamDate() {
		return lstTeamDate;
	}
	public void setLstTeamDate(String lstTeamDate) {
		this.lstTeamDate = lstTeamDate;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}
	public String getDilStatusFig() {
		return dilStatusFig;
	}
	public void setDilStatusFig(String dilStatusFig) {
		this.dilStatusFig = dilStatusFig;
	}
	
	public String getAppProd() {
		return appProd;
	}
	public void setAppProd(String appProd) {
		this.appProd = appProd;
	}
	public int getQueDate() {
		return queDate;
	}
	public void setQueDate(int queDate) {
		this.queDate = queDate;
	}
	public String getYdjFig() {
		return ydjFig;
	}
	public void setYdjFig(String ydjFig) {
		this.ydjFig = ydjFig;
	}
	public String getMatchingCardFlag() {
		return matchingCardFlag;
	}
	public void setMatchingCardFlag(String matchingCardFlag) {
		this.matchingCardFlag = matchingCardFlag;
	}
	public String getCurrNode() {
		return currNode;
	}
	public void setCurrNode(String currNode) {
		this.currNode = currNode;
	}
	
}
