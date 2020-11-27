package com.huaxia.opas.domain.decision;

import java.io.Serializable;

public class JDCardInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String infoFlag;
	private int creditScore;
	private String buyCycle;
	private String useActive;
	private String buyLevle;
	private String isUsenameWhtname;
	private String isUsetelWhttel;
	private String isUseidnoWhtidno;
	private String isUsetelMalltel;
	private String isBlackIdno;
	private String isBlackTel;
	private String whtLimit;
	private String whtUsetype;
	private String whtRefusetype;
	private String isdueCurrwht;
	private String whtMaxhisDuedays;
	public String getInfoFlag() {
		return infoFlag;
	}
	public void setInfoFlag(String infoFlag) {
		this.infoFlag = infoFlag;
	}
	public int getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	public String getBuyCycle() {
		return buyCycle;
	}
	public void setBuyCycle(String buyCycle) {
		this.buyCycle = buyCycle;
	}
	public String getUseActive() {
		return useActive;
	}
	public void setUseActive(String useActive) {
		this.useActive = useActive;
	}
	public String getBuyLevle() {
		return buyLevle;
	}
	public void setBuyLevle(String buyLevle) {
		this.buyLevle = buyLevle;
	}
	public String getIsUsenameWhtname() {
		return isUsenameWhtname;
	}
	public void setIsUsenameWhtname(String isUsenameWhtname) {
		this.isUsenameWhtname = isUsenameWhtname;
	}
	public String getIsUsetelWhttel() {
		return isUsetelWhttel;
	}
	public void setIsUsetelWhttel(String isUsetelWhttel) {
		this.isUsetelWhttel = isUsetelWhttel;
	}
	public String getIsUseidnoWhtidno() {
		return isUseidnoWhtidno;
	}
	public void setIsUseidnoWhtidno(String isUseidnoWhtidno) {
		this.isUseidnoWhtidno = isUseidnoWhtidno;
	}
	public String getIsUsetelMalltel() {
		return isUsetelMalltel;
	}
	public void setIsUsetelMalltel(String isUsetelMalltel) {
		this.isUsetelMalltel = isUsetelMalltel;
	}
	public String getIsBlackIdno() {
		return isBlackIdno;
	}
	public void setIsBlackIdno(String isBlackIdno) {
		this.isBlackIdno = isBlackIdno;
	}
	public String getIsBlackTel() {
		return isBlackTel;
	}
	public void setIsBlackTel(String isBlackTel) {
		this.isBlackTel = isBlackTel;
	}
	public String getWhtLimit() {
		return whtLimit;
	}
	public void setWhtLimit(String whtLimit) {
		this.whtLimit = whtLimit;
	}
	public String getWhtUsetype() {
		return whtUsetype;
	}
	public void setWhtUsetype(String whtUsetype) {
		this.whtUsetype = whtUsetype;
	}
	public String getWhtRefusetype() {
		return whtRefusetype;
	}
	public void setWhtRefusetype(String whtRefusetype) {
		this.whtRefusetype = whtRefusetype;
	}
	public String getIsdueCurrwht() {
		return isdueCurrwht;
	}
	public void setIsdueCurrwht(String isdueCurrwht) {
		this.isdueCurrwht = isdueCurrwht;
	}
	public String getWhtMaxhisDuedays() {
		return whtMaxhisDuedays;
	}
	public void setWhtMaxhisDuedays(String whtMaxhisDuedays) {
		this.whtMaxhisDuedays = whtMaxhisDuedays;
	}

}
