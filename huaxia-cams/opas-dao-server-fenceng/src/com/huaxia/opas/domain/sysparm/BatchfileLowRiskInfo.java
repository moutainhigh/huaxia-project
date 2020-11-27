package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BatchfileLowRiskInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String fileName;

    private Date inputTime;

    private BigDecimal inputCounts;
    
    private String operatCode;

    private String operator;

    private String remark;

    private String fileStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public BigDecimal getInputCounts() {
        return inputCounts;
    }

    public void setInputCounts(BigDecimal inputCounts) {
        this.inputCounts = inputCounts;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus == null ? null : fileStatus.trim();
    }

	public BatchfileLowRiskInfo(String id, String fileName, Date inputTime, BigDecimal inputCounts, 
			String operatCode, String operator, String remark, String fileStatus) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.inputTime = inputTime;
		this.inputCounts = inputCounts;
		this.operatCode = operatCode;
		this.operator = operator;
		this.remark = remark;
		this.fileStatus = fileStatus;
	}

	public BatchfileLowRiskInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BatchfileLowRiskInfo [id=" + id + ", fileName=" + fileName + ", inputTime=" + inputTime
				+ ", inputCounts=" + inputCounts + ", operatCode=" + operatCode + ", operator=" + operator + ", remark="
				+ remark + ", fileStatus=" + fileStatus + "]";
	}
    
}