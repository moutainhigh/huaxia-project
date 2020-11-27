package com.huaxia.opas.domain.decision;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TeamdealList  implements Serializable{
  
	private static final long serialVersionUID = 8162643113123847113L;

	private String autoId;

    private String teamdealType;

    private String teamdealCode;

    private String approveDate;

    private String reportingOrg;

    private String companyType;

    private String applyRequest;

    private String operator;

    private Date operatTime;

    private String currStatus;

    private String companyName;

    //批量导入排序
    private String orderByNo;
    public String getOrderByNo() {
		return orderByNo;
	}

	public void setOrderByNo(String orderByNo) {
		this.orderByNo = orderByNo;
	}

	public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId == null ? null : autoId.trim();
    }

    public String getTeamdealType() {
        return teamdealType;
    }

    public void setTeamdealType(String teamdealType) {
        this.teamdealType = teamdealType == null ? null : teamdealType.trim();
    }

    public String getTeamdealCode() {
        return teamdealCode;
    }

    public void setTeamdealCode(String teamdealCode) {
        this.teamdealCode = teamdealCode == null ? null : teamdealCode.trim();
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate == null ? null : approveDate.trim();
    }

    public String getReportingOrg() {
        return reportingOrg;
    }

    public void setReportingOrg(String reportingOrg) {
        this.reportingOrg = reportingOrg == null ? null : reportingOrg.trim();
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    public String getApplyRequest() {
        return applyRequest;
    }

    public void setApplyRequest(String applyRequest) {
        this.applyRequest = applyRequest == null ? null : applyRequest.trim();
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }
	
	/**
	 * 易达金团办名单实例的历史操作ID NULL
	 */
	private String opretionId;
	
	/**
	 * 易达金团办名单实例的添加时间
	 */
	private Date createTime;

	/**
	 * 易达金团办名单实例的ID的集合
	 */
	private List<String> ids ;
	
	/**
	 * 获取易达金团办名单实例的历史操作ID NULL
	 */
	public String getOpretionId() {
		return opretionId;
	}
	
	/**
	 * 设置易达金团办名单实例的历史操作ID NULL
	 */
	public void setOpretionId(String opretionId) {
		this.opretionId = opretionId == null ? null : opretionId;
	}
	
	/**
	 * 获取易达金团办名单实例的添加时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置易达金团办名单实例的添加时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime == null ? null : createTime;
	}

	/**
	 * 获取易达金团办名单实例的ID的集合
	 */
	public List<String> getIds() {
		return ids;
	}

	/**
	 * 设置易达金团办名单实例的ID的集合
	 */
	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return "TeamdealList [autoId=" + autoId + ", teamdealType=" + teamdealType + ", teamdealCode=" + teamdealCode
				+ ", reportingOrg=" + reportingOrg + ", companyType=" + companyType + ", applyRequest=" + applyRequest
				+ ", operator=" + operator + ", currStatus=" + currStatus + ", companyName=" + companyName + "]";
	}

}