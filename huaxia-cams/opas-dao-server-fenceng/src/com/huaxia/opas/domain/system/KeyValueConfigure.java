package com.huaxia.opas.domain.system;

import java.io.Serializable;
import java.util.Date;

public class KeyValueConfigure implements Serializable {

	/**
	 * 键值对配置信息实体
	 */
	private static final long serialVersionUID = 5957788824689536915L;
	
	/**
	 * 主键
	 */
	private String autoId;

	/**
	 * 键名称
	 */
	private String keyName;

	/**
	 * 值内容
	 */
	private String valueContent;
	
	/**
	 * 备注
	 */
	private String remark;
	
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
	 * 最后修改时间
	 */
	private Date lstUpdDate;

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getValueContent() {
		return valueContent;
	}

	public void setValueContent(String valueContent) {
		this.valueContent = valueContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

}
