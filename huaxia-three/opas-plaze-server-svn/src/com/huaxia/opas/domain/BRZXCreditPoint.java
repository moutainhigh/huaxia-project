package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 百融征信 & 评分 & 百融通用评分
 * 
 * @author zhiguo.li
 *
 */
public class BRZXCreditPoint extends BRZX implements Serializable {

	private static final long serialVersionUID = -2284390822544260309L;

	// 请求参数 & 身份证号
	private String identityCard;

	// 请求参数 & 手机号
	private String cell;

	// 请求参数 & 姓名
	private String name;

	// 响应参数 & 百融评分输出标识
	private String flagScore;

	// 响应参数 & 百融征信局评分
	private String brCreditPoint;

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlagScore() {
		return flagScore;
	}

	public void setFlagScore(String flagScore) {
		this.flagScore = flagScore;
	}

	public String getBrCreditPoint() {
		return brCreditPoint;
	}

	public void setBrCreditPoint(String brCreditPoint) {
		this.brCreditPoint = brCreditPoint;
	}

}
