package com.huaxia.opas.domain.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BizApprovalOperateexc implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4473694975095701422L;

	private String id;

    private String operateCode;

    private String operateName;

    private Date startdate;

    private Date enddate;

    private Long exceptiondatelong;

    //后加字段用于转换
    private BigDecimal exceptionsTime;
    
    private Date exceptiondate;
    
    private String startStr;
    
    private String endStr;
    
    private String dateStr;
    
    private String exceptionTime;
    
    
	public BigDecimal getExceptionsTime() {
		return exceptionsTime;
	}

	public void setExceptionsTime(BigDecimal exceptionsTime) {
		this.exceptionsTime = exceptionsTime;
	}

	public String getExceptionTime() {
		return exceptionTime;
	}

	public void setExceptionTime(String exceptionTime) {
		this.exceptionTime = exceptionTime;
	}

	public String getStartStr() {
		return startStr;
	}

	public void setStartStr(String startStr) {
		this.startStr = startStr;
	}

	public String getEndStr() {
		return endStr;
	}

	public void setEndStr(String endStr) {
		this.endStr = endStr;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode == null ? null : operateCode.trim();
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName == null ? null : operateName.trim();
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Long getExceptiondatelong() {
		return exceptiondatelong;
	}

	public void setExceptiondatelong(Long exceptiondatelong) {
		this.exceptiondatelong = exceptiondatelong;
	}

	public Date getExceptiondate() {
        return exceptiondate;
    }

    public void setExceptiondate(Date exceptiondate) {
        this.exceptiondate = exceptiondate;
    }
}