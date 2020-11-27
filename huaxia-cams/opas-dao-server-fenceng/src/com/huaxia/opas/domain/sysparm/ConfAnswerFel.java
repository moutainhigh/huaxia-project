package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * 答案来源域表实体类
 * @author lipengfei
 *
 */
public class ConfAnswerFel implements Serializable{

	private static final long serialVersionUID = -9185568945675587281L;

	/**
	 * ID
	 */
	private String autoId;
	
	/**
	 * 来源域编号
	 */
	private String fromFieldNo;
	
	/**
	 * 来源域名称
	 */
	private String fromFieldName;
	
	/**
	 * 来源域描述
	 */
	private String fromFieldDesc;
	
	/**
	 * 来源表编号
	 */
	private String fromTableNo;
	
	/**
	 * 创建日期
	 */
	private Date crtDate;
	
	/**
	 * 创建人
	 */
	private String crtUser;
	
	/**
	 * 最后修改日期
	 */
	private Date lstUpdDate;
	
	/**
	 * 最后修改人
	 */
	private String lstUpdUser;

	
	public ConfAnswerFel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConfAnswerFel(String autoId, String fromFieldNo, String fromFieldName, String fromFieldDesc,
			String fromTableNo, Date crtDate, String crtUser, Date lstUpdDate, String lstUpdUser) {
		super();
		this.autoId = autoId;
		this.fromFieldNo = fromFieldNo;
		this.fromFieldName = fromFieldName;
		this.fromFieldDesc = fromFieldDesc;
		this.fromTableNo = fromTableNo;
		this.crtDate = crtDate;
		this.crtUser = crtUser;
		this.lstUpdDate = lstUpdDate;
		this.lstUpdUser = lstUpdUser;
	}

	/**
	 * @return the autoId
	 */
	public String getAutoId() {
		return autoId;
	}

	/**
	 * @param autoId the autoId to set
	 */
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	/**
	 * @return the fromFieldNo
	 */
	public String getFromFieldNo() {
		return fromFieldNo;
	}

	/**
	 * @param fromFieldNo the fromFieldNo to set
	 */
	public void setFromFieldNo(String fromFieldNo) {
		this.fromFieldNo = fromFieldNo;
	}

	/**
	 * @return the fromFieldName
	 */
	public String getFromFieldName() {
		return fromFieldName;
	}

	/**
	 * @param fromFieldName the fromFieldName to set
	 */
	public void setFromFieldName(String fromFieldName) {
		this.fromFieldName = fromFieldName;
	}

	/**
	 * @return the fromFieldDesc
	 */
	public String getFromFieldDesc() {
		return fromFieldDesc;
	}

	/**
	 * @param fromFieldDesc the fromFieldDesc to set
	 */
	public void setFromFieldDesc(String fromFieldDesc) {
		this.fromFieldDesc = fromFieldDesc;
	}

	/**
	 * @return the fromTableNo
	 */
	public String getFromTableNo() {
		return fromTableNo;
	}

	/**
	 * @param fromTableNo the fromTableNo to set
	 */
	public void setFromTableNo(String fromTableNo) {
		this.fromTableNo = fromTableNo;
	}

	/**
	 * @return the crtDate
	 */
	public Date getCrtDate() {
		return crtDate;
	}

	/**
	 * @param crtDate the crtDate to set
	 */
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	/**
	 * @return the crtUser
	 */
	public String getCrtUser() {
		return crtUser;
	}

	/**
	 * @param crtUser the crtUser to set
	 */
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	/**
	 * @return the lstUpdDate
	 */
	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	/**
	 * @param lstUpdDate the lstUpdDate to set
	 */
	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	/**
	 * @return the lstUpdUser
	 */
	public String getLstUpdUser() {
		return lstUpdUser;
	}

	/**
	 * @param lstUpdUser the lstUpdUser to set
	 */
	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
