package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

public class TargetcompanyList  implements Serializable{
	
	private static final long serialVersionUID = -8414427933692613995L;

	private String Id;
	
    private String autoId;

    private String companyName;

    private String companyType;

    private String operator;

    private Date operatTime;

    private String currStatus;
    
    private Date createTime;
    //批量导入排序
    private String orderByNo;
    public String getOrderByNo() {
		return orderByNo;
	}

	public void setOrderByNo(String orderByNo) {
		this.orderByNo = orderByNo;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId == null ? null : autoId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
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
}