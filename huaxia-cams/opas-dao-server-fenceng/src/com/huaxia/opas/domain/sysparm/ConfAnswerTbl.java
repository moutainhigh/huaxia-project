package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

/**
 * 答案来源渠道表实体类
 * @author lipengfei
 *
 */
public class ConfAnswerTbl implements Serializable{

	private static final long serialVersionUID = -7542087714845588174L;

	/**
	 * ID
	 */
	private String autoId;
	
	/**
	 * 来源表编号
	 */
	private String fromTableNo;
	
	/**
	 * 来源表名称
	 */
	private String feomTableName;
	
	/**
	 * 来源表描述
	 */
	private String fromTableDesc;
	
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
	
	
	public ConfAnswerTbl(String autoId, String fromTableNo, String feomTableName, String fromTableDesc, Date crtDate,
			String crtUser, Date lstUpdDate, String lstUpdUser) {
		super();
		this.autoId = autoId;
		this.fromTableNo = fromTableNo;
		this.feomTableName = feomTableName;
		this.fromTableDesc = fromTableDesc;
		this.crtDate = crtDate;
		this.crtUser = crtUser;
		this.lstUpdDate = lstUpdDate;
		this.lstUpdUser = lstUpdUser;
	}
	public ConfAnswerTbl() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the feomTableName
	 */
	public String getFeomTableName() {
		return feomTableName;
	}
	/**
	 * @param feomTableName the feomTableName to set
	 */
	public void setFeomTableName(String feomTableName) {
		this.feomTableName = feomTableName;
	}
	/**
	 * @return the fromTableDesc
	 */
	public String getFromTableDesc() {
		return fromTableDesc;
	}
	/**
	 * @param fromTableDesc the fromTableDesc to set
	 */
	public void setFromTableDesc(String fromTableDesc) {
		this.fromTableDesc = fromTableDesc;
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
