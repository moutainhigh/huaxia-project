package com.huaxia.opas.domain.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BizApprovalOpeexchis implements Serializable{
    private String id;

    private String operateCode;

    private String operateName;

    private Date startdate;

    private Date enddate;

    private Long excdatelong;

    private Date excdatedate;

    public Long getExcdatelong() {
		return excdatelong;
	}

	public void setExcdatelong(Long excdatelong) {
		this.excdatelong = excdatelong;
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

    public Date getExcdatedate() {
        return excdatedate;
    }

    public void setExcdatedate(Date excdatedate) {
        this.excdatedate = excdatedate;
    }
}