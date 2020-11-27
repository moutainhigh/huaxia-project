package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * 卡产品降级信息实体类
 * @author liudi
 * @since 2017-03-14
 * @version 1.0
 */
public class CardDegrade implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7817300824965176562L;


	/**
	 * ID
	 */
	private String autoId;

	/**
	 * 申请卡种编号
	 */
	private String cardCode;

	/**
	 * 申请卡种名称
	 */
	private String cardName;

	/**
	 * 降级卡种编号
	 */
	private String degradeCardNo;
	
	/**
	 * 降级卡种名称
	 */
	private String degradeCardName;
	
	/**
	 * 当前状态
	 */
	private String status;

	/**
	 * 启用时间
	 */
	private Date startDate;

	/**
	 * 停用时间
	 */
	private Date stopDate;

	/**
	 * 创建人
	 */
	private String crtUser;

	/**
	 * 创建时间
	 */
	private Date crtDate;
	
	/**
	 * 最后操作人
	 */
	private String lstUpdUser;
	
	/**
	 * 最后操作时间
	 */
	private Date lstUpdDate;

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = (autoId == null ? null : autoId.trim());
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = (cardCode == null ? null : cardCode.trim());
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = (cardName == null ? null : cardName.trim());
	}

	public String getDegradeCardNo() {
		return degradeCardNo;
	}

	public void setDegradeCardNo(String degradeCardNo) {
		this.degradeCardNo = (degradeCardNo == null ? null : degradeCardNo.trim());
	}

	public String getDegradeCardName() {
		return degradeCardName;
	}

	public void setDegradeCardName(String degradeCardName) {
		this.degradeCardName = (degradeCardName == null ? null : degradeCardName.trim());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
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

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
