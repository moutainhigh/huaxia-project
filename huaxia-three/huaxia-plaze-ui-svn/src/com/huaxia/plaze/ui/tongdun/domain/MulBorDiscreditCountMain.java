package com.huaxia.plaze.ui.tongdun.domain;

/**
 *风险名单discredit_count的主要类，还有类似的多个下属小类
 * @author User
 *
 */
public class MulBorDiscreditCountMain {

	 //逻辑主键
	private String pkUuid;
	
	//风险名称
	private String riskName;
	
	//描述
	private String description;
	
	//逾期次数
	private Integer discreditTimes;
	
	//得分
	private Integer score;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDiscreditTimes() {
		return discreditTimes;
	}

	public void setDiscreditTimes(Integer discreditTimes) {
		this.discreditTimes = discreditTimes;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "MulBorDiscreditCountMain [pkUuid=" + pkUuid + ", riskName=" + riskName + ", description=" + description
				+ ", discreditTimes=" + discreditTimes + ", score=" + score + "]";
	}
	
}
