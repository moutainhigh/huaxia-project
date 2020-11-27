package com.huaxia.opas.domain.allot;

import java.io.Serializable;
import java.util.Date;
/**
 * 分配规则
 * @author wangdebin
 */
public class AllotRule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8527587912688505132L;
	//规则代码ruelNo
	private String ruleCode;
	//规则名称
	private String ruleName;
	//规则脚本 rulesInfo
	private String ruleScript;
	//规则脚本描述
	private String ruleScriptDesc;
	//规则是否生效状态
	private String ruleStatus;
	//规则是否生效状态
	private String ruleStatusName;
	//规则类型卡
	private String ruleType;
	//自动分配顺序
	private int sortFlag;
	private String crtUser;
	private Date crtDate;
	private Date lstUpdDate;
	private String lstUpdUser;
	public String getRuleName() {
		return ruleName;
	}
	public String getRuleCode() {
		return ruleCode;
	}
	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	public String getRuleScript() {
		return ruleScript;
	}
	public void setRuleScript(String ruleScript) {
		this.ruleScript = ruleScript;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = (ruleName == null ? null : ruleName.trim());
	}
	public String getRuleStatus() {
		return ruleStatus;
	}
	public void setRuleStatus(String ruleStatus) {
		this.ruleStatus = ruleStatus;
	}
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
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
	public String getLstUpdUser() {
		return lstUpdUser;
	}
	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}
	public String getRuleStatusName() {
		return ruleStatusName;
	}
	public void setRuleStatusName(String ruleStatusName) {
		this.ruleStatusName = ruleStatusName;
	}
	public String getRuleScriptDesc() {
		return ruleScriptDesc;
	}
	public void setRuleScriptDesc(String ruleScriptDesc) {
		this.ruleScriptDesc = ruleScriptDesc;
	}
	public int getSortFlag() {
		return sortFlag;
	}
	public void setSortFlag(int sortFlag) {
		this.sortFlag = sortFlag;
	}
	
}
