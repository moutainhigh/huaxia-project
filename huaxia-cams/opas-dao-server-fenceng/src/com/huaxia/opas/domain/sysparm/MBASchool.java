package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

public class MBASchool implements Serializable{
	
	private static final long serialVersionUID = -6990054947293379878L;
	
	//唯一id
	private String autoId;
	//学校类型
    private String schoolType;
    //学校名称
    private String schoolName;
    //地区
    private String schoolArea;
    //上限
    private Integer upper;
    //操作用户
    private String operatCode;
    //操作时间
    private Date operatTime;
    //当前状态
    private String currStatus;
    //创建时间
    private Date crtDate;
    //创建用户
	private String crtUser;
	//修改时间
	private Date lstUpdDate;
	//修改用户
	private String lstUpdUser;
	//操作Id
	private String operationId;
	//批量导入排序
	private String orderByNo;
    public Date getCrtDate() {
		return crtDate;
	}
    
	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
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
		this.lstUpdUser = lstUpdUser;
	}

    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId == null ? null : autoId.trim();
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType == null ? null : schoolType.trim();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getSchoolArea() {
        return schoolArea;
    }

    public void setSchoolArea(String schoolArea) {
        this.schoolArea = schoolArea == null ? null : schoolArea.trim();
    }

    public Integer getUpper() {
        return upper;
    }

    public void setUpper(Integer upper) {
        this.upper = upper;
    }

    public String getOperatCode() {
        return operatCode;
    }

    public void setOperatCode(String operatCode) {
        this.operatCode = operatCode == null ? null : operatCode.trim();
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

	public String getOrderByNo() {
		return orderByNo;
	}

	public void setOrderByNo(String orderByNo) {
		this.orderByNo = orderByNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autoId == null) ? 0 : autoId.hashCode());
		result = prime * result + ((crtUser == null) ? 0 : crtUser.hashCode());
		result = prime * result + ((currStatus == null) ? 0 : currStatus.hashCode());
		result = prime * result + ((operatCode == null) ? 0 : operatCode.hashCode());
		result = prime * result + ((schoolArea == null) ? 0 : schoolArea.hashCode());
		result = prime * result + ((schoolName == null) ? 0 : schoolName.hashCode());
		result = prime * result + ((schoolType == null) ? 0 : schoolType.hashCode());
		result = prime * result + ((upper == null) ? 0 : upper.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MBASchool other = (MBASchool) obj;
		if (autoId == null) {
			if (other.autoId != null)
				return false;
		} else if (!autoId.equals(other.autoId))
			return false;
		if (crtUser == null) {
			if (other.crtUser != null)
				return false;
		} else if (!crtUser.equals(other.crtUser))
			return false;
		if (currStatus == null) {
			if (other.currStatus != null)
				return false;
		} else if (!currStatus.equals(other.currStatus))
			return false;
		if (operatCode == null) {
			if (other.operatCode != null)
				return false;
		} else if (!operatCode.equals(other.operatCode))
			return false;
		if (schoolArea == null) {
			if (other.schoolArea != null)
				return false;
		} else if (!schoolArea.equals(other.schoolArea))
			return false;
		if (schoolName == null) {
			if (other.schoolName != null)
				return false;
		} else if (!schoolName.equals(other.schoolName))
			return false;
		if (schoolType == null) {
			if (other.schoolType != null)
				return false;
		} else if (!schoolType.equals(other.schoolType))
			return false;
		if (upper == null) {
			if (other.upper != null)
				return false;
		} else if (!upper.equals(other.upper))
			return false;
		return true;
	}

	
    
    
}
