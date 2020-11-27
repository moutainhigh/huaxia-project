package com.huaxia.opas.domain.allot;

import java.io.Serializable;
import java.util.Date;
/**
 * 规则层级映射
 * @author wangdebin
 *
 */
public class AllotMappingRule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3730131107935046381L;
	//映射id
	private String mappingId;
	//规则代码
	private String ruleCode;
	//层级类型 1 队列 2组  3组员
	private String levelType;
	//层级代码  队列 组  组员 编号
	private String levelCode;
	//映射机构
	private String levelName;
	//启用状态   1 启用  0 停用  
	private String ruleProp;
	private String ruleTime;
	//规则名称
	private String ruleName;
	//规则脚本 rulesInfo
	private String ruleScript;
	//规则是否生效状态
	private String ruleStatus;
	//规则类型
	private String ruleType;
	//组员集合
	private String userScript;
	private String userScriptDesc;
	//队列id
	private String queueId;
	//队列代码
	private String queueCode;
	//队列名称  
	private String queueName;
	//队列级别 
	private String queueLevel;
	//组id
	private String orgId;
	//组代码(团队编号)
	private String orgNo;
	//组名称
	private String orgName;
	
	private String crtUser;
	private Date crtDate;
	private Date lstUpdDate;
	private String lstUpdUser;
	
	public String getMappingId() {
		return mappingId;
	}
	public void setMappingId(String mappingId) {
		this.mappingId = mappingId;
	}
	public String getRuleCode() {
		return ruleCode;
	}
	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getRuleProp() {
		return ruleProp;
	}
	public void setRuleProp(String ruleProp) {
		this.ruleProp = ruleProp;
	}
	public String getRuleTime() {
		return ruleTime;
	}
	public void setRuleTime(String ruleTime) {
		this.ruleTime = ruleTime;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public String getRuleScript() {
		return ruleScript;
	}
	public void setRuleScript(String ruleScript) {
		this.ruleScript = ruleScript;
	}
	public String getRuleStatus() {
		return ruleStatus;
	}
	public void setRuleStatus(String ruleStatus) {
		this.ruleStatus = ruleStatus;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getQueueId() {
		return queueId;
	}
	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}
	public String getQueueCode() {
		return queueCode;
	}
	public void setQueueCode(String queueCode) {
		this.queueCode = queueCode;
	}
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = (crtUser == null ? null : crtUser.trim());
	}
	public String getLstUpdUser() {
		return lstUpdUser;
	}
	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = (lstUpdUser == null ? null : lstUpdUser.trim());
	}
	public Date getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	public Date getLstUpdDate() {
		return lstUpdDate;
	}
	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}
	public String getQueueLevel() {
		return queueLevel;
	}
	public void setQueueLevel(String queueLevel) {
		this.queueLevel = queueLevel;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getUserScript() {
		return userScript;
	}
	public void setUserScript(String userScript) {
		this.userScript = userScript;
	}
	public String getUserScriptDesc() {
		return userScriptDesc;
	}
	public void setUserScriptDesc(String userScriptDesc) {
		this.userScriptDesc = userScriptDesc;
	}
	
}
