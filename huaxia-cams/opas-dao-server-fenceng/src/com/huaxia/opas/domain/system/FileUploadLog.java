package com.huaxia.opas.domain.system;

import java.io.Serializable;
import java.util.Date;

public class FileUploadLog implements Serializable {

	private static final long serialVersionUID = -7576902980453968832L;

	private String id;

	private String fileId;

	private String fileName;

	private String operatType;

	private String operatCode;

	private String operator;

	private Date operatDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId == null ? null : fileId.trim();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName == null ? null : fileName.trim();
	}

	public String getOperatType() {
		return operatType;
	}

	public void setOperatType(String operatType) {
		this.operatType = operatType == null ? null : operatType.trim();
	}

	public String getOperatCode() {
		return operatCode;
	}

	public void setOperatCode(String operatCode) {
		this.operatCode = operatCode == null ? null : operatCode.trim();
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator == null ? null : operator.trim();
	}

	public Date getOperatDate() {
		return operatDate;
	}

	public void setOperatDate(Date operatDate) {
		this.operatDate = operatDate;
	}
}