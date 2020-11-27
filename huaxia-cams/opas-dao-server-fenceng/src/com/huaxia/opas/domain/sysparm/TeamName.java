package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

public class TeamName implements Serializable{
   
	private static final long serialVersionUID = 9084083764424517304L;
	//操作Id
	private String operationId;
	//唯一Id
	private String autoId;
	//团办号
    private String teamId;
    //单位名称
    private String company;
    //批复内容
    private String approveContent;
    // 授信标准
    private String authCriterion;
    //批复时间
    private Date approveTime;
    //批复有效期
    private Date approveVailudDate;
    //创建时间
    private Date crtTime;
    //操作日期
    private Date crtDate;
    //创建人
    private String crtUser;
    //启停状态  0 停用 1 启用
    private String status;
    //备注
    private String remark;
    //批量导入排序
    private String orderByNo;
	public String getOrderByNo() {
		return orderByNo;
	}

	public void setOrderByNo(String orderByNo) {
		this.orderByNo = orderByNo;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId == null ? null : autoId.trim();
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId == null ? null : teamId.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getApproveContent() {
        return approveContent;
    }

    public void setApproveContent(String approveContent) {
        this.approveContent = approveContent == null ? null : approveContent.trim();
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Date getApproveVailudDate() {
        return approveVailudDate;
    }

    public void setApproveVailudDate(Date approveVailudDate) {
        this.approveVailudDate = approveVailudDate;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
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

	public String getAuthCriterion() {
		return authCriterion;
	}

	public void setAuthCriterion(String authCriterion) {
		this.authCriterion = authCriterion;
	}
}