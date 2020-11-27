package com.huaxia.opas.domain.report;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
//关键信息修改表
public class KeyMessageModify implements Serializable{
	
	private static final long serialVersionUID = 5646962148330798816L;
	
    private String autoId;//序列值
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;//修改日期

    private String operator;//操作员

    private String appId;//条形码

    private String applyName;//申请人

    private String cretifiType;//证件类别

    private String cretifiId;//证件号码

    private String keyWordsId;//数据项

    private String currValue;//原信息

    private String modifyValue;//修改后信息

    private String reviewStatus;//复核状态

    private String reviewer;//复核人 
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date crtDate;//创建日期
    
    private String ydjFlag;//易达金标识     1为易达金，2为标准卡
    
    private String remark;
    
	private String flag;
    
    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
    
    public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	private String columnName;
    
    
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

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName == null ? null : applyName.trim();
    }

    public String getCretifiType() {
        return cretifiType;
    }

    public void setCretifiType(String cretifiType) {
        this.cretifiType = cretifiType == null ? null : cretifiType.trim();
    }

    public String getCretifiId() {
        return cretifiId;
    }

    public void setCretifiId(String cretifiId) {
        this.cretifiId = cretifiId == null ? null : cretifiId.trim();
    }

    public String getKeyWordsId() {
        return keyWordsId;
    }

    public void setKeyWordsId(String keyWordsId) {
        this.keyWordsId = keyWordsId == null ? null : keyWordsId.trim();
    }

    public String getCurrValue() {
        return currValue;
    }

    public void setCurrValue(String currValue) {
        this.currValue = currValue == null ? null : currValue.trim();
    }

    public String getModifyValue() {
        return modifyValue;
    }

    public void setModifyValue(String modifyValue) {
        this.modifyValue = modifyValue == null ? null : modifyValue.trim();
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus == null ? null : reviewStatus.trim();
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer == null ? null : reviewer.trim();
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }
    
    public String getYdjFlag() {
        return ydjFlag;
    }

    public void setYdjFlag(String ydjFlag) {
        this.ydjFlag = ydjFlag == null ? null : ydjFlag.trim();
    }
}