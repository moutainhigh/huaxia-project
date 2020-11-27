package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

public class UserCard implements Serializable {

	private static final long serialVersionUID = 3624431842940950572L;

	private String cardId;// 主键

	private String userId;// 用户ID

	private String cardCode;// 卡编号

	private Date crtDate;// 创建日期

	private String crtUser;// 创建人

	private String lstUpdUser;// 最后操作人

	private Date lstUpdDate;// 最后操作时间
	
	private String prodName;// 卡编号
	
	private String autoId;// 卡产品主键
	
	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId == null ? null : cardId.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser == null ? null : crtUser.trim();
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser == null ? null : lstUpdUser.trim();
	}

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}
}