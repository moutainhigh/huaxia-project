package com.huaxia.opas.domain.input;

import java.io.Serializable;
import java.util.Date;

public class BizInpAppl implements Serializable{
	private String appId;//流水号
	private String c1Cname;//客户姓名
	private String c1Idnbr;//证件号码
	private String c1Coname;//单位名称
	private String lastOptUser;//最后操作员
	private String telcheckResultGD;//工单
	private String telcheckResultTJ;//推荐
	private String telcheckResultGZ;//关注
	private String patchOverTimeYdj;
	private String patchOverTimeBzk;
	
	public String getPatchOverTimeYdj() {
		return patchOverTimeYdj;
	}
	public void setPatchOverTimeYdj(String patchOverTimeYdj) {
		this.patchOverTimeYdj = patchOverTimeYdj;
	}
	public String getPatchOverTimeBzk() {
		return patchOverTimeBzk;
	}
	public void setPatchOverTimeBzk(String patchOverTimeBzk) {
		this.patchOverTimeBzk = patchOverTimeBzk;
	}
	public String getTelcheckResultGD() {
		return telcheckResultGD;
	}
	public void setTelcheckResultGD(String telcheckResultGD) {
		this.telcheckResultGD = telcheckResultGD;
	}
	public String getTelcheckResultTJ() {
		return telcheckResultTJ;
	}
	public void setTelcheckResultTJ(String telcheckResultTJ) {
		this.telcheckResultTJ = telcheckResultTJ;
	}
	public String getTelcheckResultGZ() {
		return telcheckResultGZ;
	}
	public void setTelcheckResultGZ(String telcheckResultGZ) {
		this.telcheckResultGZ = telcheckResultGZ;
	}
	private int remark;//备注
	private int patchStatusR;//人行
	public int getPatchStatusR() {
		return patchStatusR;
	}
	public void setPatchStatusR(int patchStatusR) {
		this.patchStatusR = patchStatusR;
	}
	public int getPatchStatusP() {
		return patchStatusP;
	}
	public void setPatchStatusP(int patchStatusP) {
		this.patchStatusP = patchStatusP;
	}
	public String getIsHavecardCust() {
		return isHavecardCust;
	}
	public void setIsHavecardCust(String isHavecardCust) {
		this.isHavecardCust = isHavecardCust;
	}
	private Integer patchStatusPP;//可信任身份证
	
	public Integer getPatchStatusPP() {
		return patchStatusPP;
	}
	public void setPatchStatusPP(Integer patchStatusPP) {
		this.patchStatusPP = patchStatusPP;
	}
	private Integer patchStatusS;//智能语音
	
	private int patchStatusP;//公安
	private String isHavecardCust;//是否持卡客户
	private int c4RushFlg;//1-快速发卡0－正常发卡2-紧急发卡
	private String currNode;//01：录入比对	02：征信调查	03：人工审批
	private String delStatus;//0:未完成	1:已完成	2:补件	3:退回	4:挂起
	private int crtDate;//创建天数
	private int queDate;//进去队列天数
	private int groDate;//进入组天数
	private String quickCardFlag;//快速发卡标志--1为快速发卡，0或空位非快速发卡 该字段仅为审批系统中快速发卡标志与appln中的快速发卡标志不同
	private Date lastOptTime;//最后操作时间(审查录入队列显示)
	private String patchStatus;//0：补件未达	1：补件完成（需补件码）	2：PAD补件

	private String telcheckResult;//电核情况
	private String teamTelcheckStatus;//集体电核标志
	private String appProd;//申请卡片产品 85... 
	//集体电核
	private String c4ApBatch;//团办号 C1_COTEL
	private String c1Cotel;//单位电话
	private String currUser;//当前操作人
	private String createDate;
	private String lstDate;
	private String userDates;
	private String userDate;

	private String currStatus;
	private String submitStatus;//提报状态
	private String ydjFlag;	//易达金标示
	private String matchingCardFlag;//套卡标识 
	private String synFlag;//控制 套卡的同步提交
	private String stopFlag;//质检状态
	private String userName;//最后操作人姓名
	private String completedFlag;//pad补件
	private String completedFlagYdj;
	private String completedFlagBzk;
	private String batchCreditFlag;
	private int leadNum;
	public String getAppProd() {
		return appProd;
	}
	public String getQuickCardFlag() {
		return quickCardFlag;
	}
	public void setQuickCardFlag(String quickCardFlag) {
		this.quickCardFlag = quickCardFlag;
	}
	public String getPatchStatus() {
		return patchStatus;
	}
	public void setPatchStatus(String patchStatus) {
		this.patchStatus = patchStatus;
	}
	public String getTelcheckResult() {
		return telcheckResult;
	}
	public void setTelcheckResult(String telcheckResult) {
		this.telcheckResult = telcheckResult;
	}
	public String getTeamTelcheckStatus() {
		return teamTelcheckStatus;
	}
	public void setTeamTelcheckStatus(String teamTelcheckStatus) {
		this.teamTelcheckStatus = teamTelcheckStatus;
	}
	public int getQueDate() {
		return queDate;
	}
	public void setQueDate(int queDate) {
		this.queDate = queDate;
	}
	public int getGroDate() {
		return groDate;
	}
	public void setGroDate(int groDate) {
		this.groDate = groDate;
	}
	public int getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(int crtDate) {
		this.crtDate = crtDate;
	}
	public int getC4RushFlg() {
		return c4RushFlg;
	}
	public void setC4RushFlg(int c4RushFlg) {
		this.c4RushFlg = c4RushFlg;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getCurrNode() {
		return currNode;
	}
	public void setCurrNode(String currNode) {
		this.currNode = currNode;
	}
	public String getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
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
	public int getRemark() {
		return remark;
	}
	public void setRemark(int remark) {
		this.remark = remark;
	}
	
	public String getC4ApBatch() {
		return c4ApBatch;
	}
	public void setC4ApBatch(String c4ApBatch) {
		this.c4ApBatch = c4ApBatch;
	}
	public String getC1Cotel() {
		return c1Cotel;
	}
	public void setC1Cotel(String c1Cotel) {
		this.c1Cotel = c1Cotel;
	}
	public String getCurrUser() {
		return currUser;
	}
	public void setCurrUser(String currUser) {
		this.currUser = currUser;
	}
	public String getLstDate() {
		return lstDate;
	}
	public void setLstDate(String lstDate) {
		this.lstDate = lstDate;
	}
	public String getCurrStatus() {
		return currStatus;
	}
	public void setCurrStatus(String currStatus) {
		this.currStatus = currStatus;
	}
	public String getSubmitStatus() {
		return submitStatus;
	}
	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}
	public String getYdjFlag() {
		return ydjFlag;
	}
	public void setYdjFlag(String ydjFlag) {
		this.ydjFlag = ydjFlag;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public void setAppProd(String appProd) {
		this.appProd = appProd;
	}
	public Date getLastOptTime() {
		return lastOptTime;
	}
	public void setLastOptTime(Date lastOptTime) {
		this.lastOptTime = lastOptTime;
	}
	@Override
	public String toString() {
		return "BizInpAppl [appId=" + appId + ", c1Cname=" + c1Cname + ", c1Idnbr=" + c1Idnbr + ", c1Coname=" + c1Coname
				+ ", lastOptUser=" + lastOptUser + ", remark=" + remark + ", c4RushFlg=" + c4RushFlg + ", currNode="
				+ currNode + ", delStatus=" + delStatus + ", crtDate=" + crtDate + ", queDate=" + queDate + ", groDate="
				+ groDate + ", quickCardFlag=" + quickCardFlag + ", patchStatus=" + patchStatus + ", telcheckResult="
				+ telcheckResult + ", teamTelcheckStatus=" + teamTelcheckStatus + ", c4ApBatch=" + c4ApBatch +
				", c1Cotel=" + c1Cotel + "]";
	}
	public String getMatchingCardFlag() {
		return matchingCardFlag;
	}
	public void setMatchingCardFlag(String matchingCardFlag) {
		this.matchingCardFlag = matchingCardFlag;
	}
	public String getSynFlag() {
		return synFlag;
	}
	public void setSynFlag(String synFlag) {
		this.synFlag = synFlag;
	}
	public String getUserDates() {
		return userDates;
	}
	public void setUserDates(String userDates) {
		this.userDates = userDates;
	}
	public String getUserDate() {
		return userDate;
	}
	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}
	public String getStopFlag() {
		return stopFlag;
	}
	public void setStopFlag(String stopFlag) {
		this.stopFlag = stopFlag;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCompletedFlag() {
		return completedFlag;
	}
	public void setCompletedFlag(String completedFlag) {
		this.completedFlag = completedFlag;
	}
	public String getCompletedFlagYdj() {
		return completedFlagYdj;
	}
	public void setCompletedFlagYdj(String completedFlagYdj) {
		this.completedFlagYdj = completedFlagYdj;
	}
	public String getCompletedFlagBzk() {
		return completedFlagBzk;
	}
	public void setCompletedFlagBzk(String completedFlagBzk) {
		this.completedFlagBzk = completedFlagBzk;
	}
	public String getBatchCreditFlag() {
		return batchCreditFlag;
	}
	public void setBatchCreditFlag(String batchCreditFlag) {
		this.batchCreditFlag = batchCreditFlag;
	}
	public int getLeadNum() {
		return leadNum;
	}
	public void setLeadNum(int leadNum) {
		this.leadNum = leadNum;
	}
//	private String applyerJob;//
//	private String sic;//
//	private String applyStatus;//
//	private String reasonCode;//
	public Integer getPatchStatusS() {
		return patchStatusS;
	}
	public void setPatchStatusS(Integer patchStatusS) {
		this.patchStatusS = patchStatusS;
	}
}
