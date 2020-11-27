package com.huaxia.plaze.ui.tongdun.domain;

import com.mysql.jdbc.Clob;

/**
 * 多头借贷页面显示关注名单
 * @author User
 *
 */
public class MulBorAttentionList {


	 //逻辑主键
	private String pkUuid;
	
	//风险名称
	private String riskName;
	
	//得分
	private Integer score;
		
	//描述
	private String description;
	
	//风险类型
	private String fraudTypeDisplayName;
		
	private String hitTypeDisplayName;
	
	public String getPkUuid() {
		return pkUuid;
	}

	public void setPkUuid(String pkUuid) {
		this.pkUuid = pkUuid;
	}

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

	@Override
	public String toString() {
		return "MulBorAttentionList [pkUuid=" + pkUuid + ", riskName=" + riskName + ", score=" + score
				+ ", description=" + description + ", fraudTypeDisplayName=" + fraudTypeDisplayName
				+ ", hitTypeDisplayName=" + hitTypeDisplayName + "]";
	}

}
