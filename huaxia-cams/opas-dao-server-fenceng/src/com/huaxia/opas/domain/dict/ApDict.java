package com.huaxia.opas.domain.dict;

import java.io.Serializable;
import java.util.Date;

public class ApDict implements Serializable {

	private static final long serialVersionUID = -3053011839615403131L;

	/**
	 * 业务字典大类id
	 */
	private String dictId;

	/**
	 * 业务字典大类编码
	 */
	private String dictCode;

	/**
	 * 业务字典大类名称
	 */
	private String dictName;

	/**
	 * 创建日期
	 */
	private Date crtDate;

	/**
	 * 创建时间
	 */
	private Date crtTime;

	/**
	 * 创建人
	 */
	private String crtUser;

	/**
	 * 最后操作人
	 */
	private String lstUpdUser;

	/**
	 * 最后操作日期
	 */
	private Date lstUpdDate;

	/**
	 * 最后更新时间
	 */
	private Date lstUpdTime;

	/**
	 * 批次日期
	 */
	private Date batchDate;

	/**
	 * 记录状态
	 */
	private String recStatus;

	/**
	 * 保密级别
	 */
	private String scrLevel;

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = (dictId == null ? null : dictId.trim());
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = (dictCode == null ? null : dictCode.trim());
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = (dictName == null ? null : dictName.trim());
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = (crtUser == null ? null : crtUser.trim());
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = (lstUpdUser == null ? null : lstUpdUser.trim());
	}

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public Date getLstUpdTime() {
		return lstUpdTime;
	}

	public void setLstUpdTime(Date lstUpdTime) {
		this.lstUpdTime = lstUpdTime;
	}

	public Date getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}

	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = (recStatus == null ? null : recStatus.trim());
	}

	public String getScrLevel() {
		return scrLevel;
	}

	public void setScrLevel(String scrLevel) {
		this.scrLevel = (scrLevel == null ? null : scrLevel.trim());
	}

}
