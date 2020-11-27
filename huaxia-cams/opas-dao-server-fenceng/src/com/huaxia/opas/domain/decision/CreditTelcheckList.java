package com.huaxia.opas.domain.decision;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CreditTelcheckList implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8532319660603483906L;

	private String autoId;

    private String appId;

    private String companyName;

    private String companyTel;

    private String memo;

    private String operator;

    private Date createTime;

    private Date operatTime;

    private String currStatus;
    
    private String createDate;
    
    private String companyDept;

    private String orderByNo;
    
    private String telSource;
    public String getOrderByNo() {
		return orderByNo;
	}

	public void setOrderByNo(String orderByNo) {
		this.orderByNo = orderByNo;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel == null ? null : companyTel.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = (memo == null ? null : memo.trim());
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOperatTime() {
        return operatTime;
    }

    public void setOperatTime(Date operatTime) {
        this.operatTime = operatTime;
    }

    public String getCurrStatus() {
        return currStatus;
    }

    public void setCurrStatus(String currStatus) {
        this.currStatus = currStatus == null ? null : currStatus.trim();
    }
    
	public String getTelSource() {
		return telSource;
	}

	public void setTelSource(String telSource) {
		this.telSource = telSource;
	}

	/**
	 * 征信电话核查白名单实例历史操作表的ID NULL
	 */
	private String opretionId;
	

	/**
	 * 获取征信电话核查白名单实例历史操作表的ID NULL
	 */
	public String getOpretionId() {
		return opretionId;
	}

	/**
	 * 设置电话核查白名单实例历史操作表的ID NULL
	 */
	public void setOpretionId(String opretionId) {
		this.opretionId = (opretionId == null ? null : opretionId.trim());
	}
	
	/**
	 * 征信电话核查白名单实例的ID的集合
	 */
	private List<String> ids;

	/**
	 * 获取征信电话核查白名单实例的ID的集合
	 */
	public List<String> getIds() {
		return ids;
	}

	/**
	 * 设置征信电话核查白名单实例的ID的集合
	 */
	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	
	public String getCompanyDept() {
		return companyDept;
	}

	public void setCompanyDept(String companyDept) {
		this.companyDept = companyDept;
	}

	@Override
	public String toString() {
		return "CreditTelcheckList [autoId=" + autoId + ", appId=" + appId + ", companyName=" + companyName
				+ ", companyTel=" + companyTel + ", memo=" + memo + ", operator=" + operator + ", currStatus="
				+ currStatus + "]";
	}
	
}