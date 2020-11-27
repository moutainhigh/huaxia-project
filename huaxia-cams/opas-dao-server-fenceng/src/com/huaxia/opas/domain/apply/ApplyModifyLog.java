package com.huaxia.opas.domain.apply;

import java.io.Serializable;
import java.util.Date;

/**
 * 申请信息修改日志
 * 
 * @author jiangming.yang 2017-03-17
 */
public class ApplyModifyLog implements Serializable {

	private static final long serialVersionUID = -3465556981985401391L;

	private String autoId;

	private String appId;

	private String fieldName;

	private Object fieldOldValue;

	private Object fieldNewValue;

	private String isKeyField;

	private Date crtDate;

	private String crtUser;

	private Date lstUpdDate;

	private String lstUpdUser;

	private String checkFlag;

	private String applyName;

	private String cretifiType;

	private String cretifiId;

	private String flag;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getCretifiType() {
		return cretifiType;
	}

	public void setCretifiType(String cretifiType) {
		this.cretifiType = cretifiType;
	}

	public String getCretifiId() {
		return cretifiId;
	}

	public void setCretifiId(String cretifiId) {
		this.cretifiId = cretifiId;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId == null ? null : autoId.trim();
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId == null ? null : appId.trim();
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName == null ? null : fieldName.trim();
	}

	public Object getFieldOldValue() {
		return fieldOldValue;
	}

	public void setFieldOldValue(Object fieldOldValue) {
		this.fieldOldValue = fieldOldValue;
	}

	public Object getFieldNewValue() {
		return fieldNewValue;
	}

	public void setFieldNewValue(Object fieldNewValue) {
		this.fieldNewValue = fieldNewValue;
	}

	public void setFieldNewValue(String fieldNewValue) {
		this.fieldNewValue = fieldNewValue == null ? null : fieldNewValue.trim();
	}

	public String getIsKeyField() {
		return isKeyField;
	}

	public void setIsKeyField(String isKeyField) {
		this.isKeyField = isKeyField == null ? null : isKeyField.trim();
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

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser == null ? null : lstUpdUser.trim();
	}
}