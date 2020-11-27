package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;

/**
 * 卡版面实体类
 * @author liudi
 * @since 2017-03-06
 * @version 1.0
 */
public class CardFace implements Serializable{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 8187694717903076203L;


	/**
	 * ID
	 */
	private String autoId;

	/**
	 * 卡版面编号
	 */
	private String cardFaceCode;

	/**
	 * 卡版面描述
	 */
	private String cardFaceDesc;


	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = (autoId == null ? null : autoId.trim());
	}

	public String getCardFaceCode() {
		return cardFaceCode;
	}

	public void setCardFaceCode(String cardFaceCode) {
		this.cardFaceCode = (cardFaceCode == null ? null : cardFaceCode.trim());
	}

	public String getCardFaceDesc() {
		return cardFaceDesc;
	}

	public void setCardFaceDesc(String cardFaceDesc) {
		this.cardFaceDesc = (cardFaceDesc == null ? null : cardFaceDesc.trim());
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
