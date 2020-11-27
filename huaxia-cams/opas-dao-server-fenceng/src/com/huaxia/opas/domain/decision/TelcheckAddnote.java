package com.huaxia.opas.domain.decision;

import java.io.Serializable;
import java.util.Date;

public class TelcheckAddnote implements Serializable {

	private static final long serialVersionUID = 2876376595070023960L;

	private String autoId;

    private String appId;

    private String telSource;

	private String telType;

    private String telNo;

    private String noteObject;

    private String telcheckResult;

    private String memo;

    private Date crtDate;
    //数据库中无此字段，用于反显crtdate
    private String crtTime;
    private String crtUser;
    
    private Date updateDate;
    //集体电核标识
    private String teamTelCheckStatus;
    
    private String bigMemo;

    private String typeFlag;
    
    private String higeMemo;
    //集体电核共用uuId
    private String talId;
    
    private String tabId;
    //集体电核共用winId
    private String winId;
    
    //用于纪要信息变色标识
    private String colorFlag;
    
    //用于显示中文名
    private String crtUserName;
    
	public String getColorFlag() {
		return colorFlag;
	}

	public void setColorFlag(String colorFlag) {
		this.colorFlag = colorFlag;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}

	public String getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTelSource() {
		return telSource;
	}

	public void setTelSource(String telSource) {
		this.telSource = telSource;
	}

	public String getTelType() {
		return telType;
	}

	public void setTelType(String telType) {
		this.telType = telType;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getNoteObject() {
		return noteObject;
	}

	public void setNoteObject(String noteObject) {
		this.noteObject = noteObject;
	}

	public String getTelcheckResult() {
		return telcheckResult;
	}

	public void setTelcheckResult(String telcheckResult) {
		this.telcheckResult = telcheckResult;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
		this.crtUser = crtUser;
	}

	public String getTeamTelCheckStatus() {
		return teamTelCheckStatus;
	}

	public void setTeamTelCheckStatus(String teamTelCheckStatus) {
		this.teamTelCheckStatus = teamTelCheckStatus;
	}

	public String getBigMemo() {
		return bigMemo;
	}

	public void setBigMemo(String bigMemo) {
		this.bigMemo = bigMemo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTalId() {
		return talId;
	}

	public void setTalId(String talId) {
		this.talId = talId;
	}

	public String getWinId() {
		return winId;
	}

	public void setWinId(String winId) {
		this.winId = winId;
	}

	public String getHigeMemo() {
		return higeMemo;
	}

	public void setHigeMemo(String higeMemo) {
		this.higeMemo = higeMemo;
	}

	public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	
	public String getCrtUserName() {
		return crtUserName;
	}

	public void setCrtUserName(String crtUserName) {
		this.crtUserName = crtUserName;
	}

	@Override
	public String toString() {
		return "TelcheckAddnote [autoId=" + autoId + ", appId=" + appId
				+ ", telNo=" + telNo + ", crtUser=" + crtUser + ", updateDate="
				+ updateDate + ", bigMemo=" + bigMemo + ", colorFlag="
				+ colorFlag + ", crtUserName="
						+ crtUserName+ "]";
	}

}