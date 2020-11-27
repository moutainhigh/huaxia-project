package com.huaxia.plaze.ui.tongdun.domain;

/**
 * 多头借贷显示风险名单
 * @author Liuwei
 *
 */
public class MulBorRiskList {

	//ID
	private Integer ruleId;
	
	//风险名称
	private String riskName;
	
	//得分
	private Integer score;
	
	//描述
	private String description;
	
	//风险类型
	private String fraudTypeDisplayName;
	
	//匹配字段
	private String hitTypeDisplayName;
	
	//判断是否有法院详情
	private String value;

	
	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFraudTypeDisplayName() {
		return fraudTypeDisplayName;
	}

	public void setFraudTypeDisplayName(String fraudTypeDisplayName) {
		this.fraudTypeDisplayName = fraudTypeDisplayName;
	}

	public String getHitTypeDisplayName() {
		return hitTypeDisplayName;
	}

	public void setHitTypeDisplayName(String hitTypeDisplayName) {
		this.hitTypeDisplayName = hitTypeDisplayName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	@Override
	public String toString() {
		return "MulBorRiskList [ruleId=" + ruleId + ", riskName=" + riskName + ", score=" + score + ", description="
				+ description + ", fraudTypeDisplayName=" + fraudTypeDisplayName + ", hitTypeDisplayName="
				+ hitTypeDisplayName + ", value=" + value + "]";
	}

}
