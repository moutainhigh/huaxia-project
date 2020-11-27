package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

public class CompanyInfoList implements Serializable {

	private static final long serialVersionUID = -2002874144984366949L;

	private String Id;
	
    private String autoId;

    private String companyName;//企业名称

    private String securitiesAbbreviation;//证券简称
    
    private String businessType;//企业类别
    
    private String companyProperty;//公司属性
    
    private String bizIndustry;//所属行业
    
    private String businessLevel;//企业级别

    private Date operatTime;//操作时间
    
    private String operator;//维护人
    
    private Date createTime;//创建时间
    
    private String createUser;//创建人
    
    //private String currStatus;
   
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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
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

	public String getSecuritiesAbbreviation() {
		return securitiesAbbreviation;
	}

	public void setSecuritiesAbbreviation(String securitiesAbbreviation) {
		this.securitiesAbbreviation = securitiesAbbreviation == null ? null : securitiesAbbreviation.trim();
	}

	public String getCompanyProperty() {
		return companyProperty;
	}

	public void setCompanyProperty(String companyProperty) {
		this.companyProperty = companyProperty == null ? null : companyProperty.trim();
	}

	public String getBizIndustry() {
		return bizIndustry;
	}

	public void setBizIndustry(String bizIndustry) {
		this.bizIndustry = bizIndustry == null ? null : bizIndustry.trim();
	}

	public String getBusinessLevel() {
		return businessLevel;
	}

	public void setBusinessLevel(String businessLevel) {
		this.businessLevel = businessLevel == null ? null : businessLevel.trim();
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

    /*public String getCurrStatus() {
        return currStatus;
    }

    public void setCurrStatus(String currStatus) {
        this.currStatus = currStatus == null ? null : currStatus.trim();
    }*/
    
    
}
