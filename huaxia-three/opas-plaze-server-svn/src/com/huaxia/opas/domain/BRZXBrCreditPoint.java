package com.huaxia.opas.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 第三方 & 百融征信 & 评分 & 百融通用评分
 * 
 * @author zhiguo.li
 *
 */
public class BRZXBrCreditPoint implements Serializable {

	private static final long serialVersionUID = -2284390822544260309L;

	// 申请件编号
	private String appId;

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

	// 创建日期
	private Date createDate;

	// 创建人
	private String createUser;

	// 最后修改日期
	private Date lastUpdateDate;

	// 最后修改人
	private String lastUpdateUser;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

}
