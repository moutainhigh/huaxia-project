package com.huaxia.opas.domain.system;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ApUser implements Serializable {

	private static final long serialVersionUID = 3624431842940950572L;

	private String userId;

	private String userCode;

	private String userPassword;

	private String userName;

	private String applyerSex;

	private String cellphone;

	private String email;

	private String status;

	private Date crtDate;

	private Date crtTime;

	private String crtUser;

	private String lstUpdUser;

	private Date lstUpdTime;

	private Date lstUpdDate;

	private Date batchDate;

	private String recStatus;

	private String scrLevel;

	private BigDecimal ydjLimitLevel;

	private String telephone;

	private BigDecimal bzkLimitLevel;

	private Date userValidityPerriod;//用户到期日

	private String ipAddr;

	private String department;

	private String team;

	private String userGroup;

	private String station;

	private String fileRightControl;
	
	private String roleCode;
	
	private String sessionId;
	
	private Date loginTime;
	
	private String loginStatus;
	
	private Integer loginFailNum;//登录 失败次数
	
	private Date loginFailTime;//登录 失败时间
	
	private String fileUploadControl;//文档上传权限管理类型
	
	private String passwordFlag;//修改密码的标志
	
	private String userLdnamber;//身份证号
	
	
	public String getPasswordFlag() {
		return passwordFlag;
	}

	public String getUserLdnamber() {
		return userLdnamber;
	}

	public void setUserLdnamber(String userLdnamber) {
		this.userLdnamber = userLdnamber;
	}

	public void setPasswordFlag(String passwordFlag) {
		this.passwordFlag = passwordFlag;
	}
	
	
	public String getFileUploadControl() {
		return fileUploadControl;
	}

	public void setFileUploadControl(String fileUploadControl) {
		this.fileUploadControl = fileUploadControl;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Date getLstUpdTime() {
		return lstUpdTime;
	}

	public void setLstUpdTime(Date lstUpdTime) {
		this.lstUpdTime = lstUpdTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode == null ? null : userCode.trim();
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword == null ? null : userPassword.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getApplyerSex() {
		return applyerSex;
	}

	public void setApplyerSex(String applyerSex) {
		this.applyerSex = applyerSex == null ? null : applyerSex.trim();
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone == null ? null : cellphone.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser == null ? null : crtUser.trim();
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser == null ? null : lstUpdUser.trim();
	}

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public Date getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}

	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus == null ? null : recStatus.trim();
	}

	public String getScrLevel() {
		return scrLevel;
	}

	public void setScrLevel(String scrLevel) {
		this.scrLevel = scrLevel == null ? null : scrLevel.trim();
	}

	public BigDecimal getYdjLimitLevel() {
		return ydjLimitLevel;
	}

	public void setYdjLimitLevel(BigDecimal ydjLimitLevel) {
		this.ydjLimitLevel = ydjLimitLevel;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone == null ? null : telephone.trim();
	}

	public BigDecimal getBzkLimitLevel() {
		return bzkLimitLevel;
	}

	public void setBzkLimitLevel(BigDecimal bzkLimitLevel) {
		this.bzkLimitLevel = bzkLimitLevel;
	}

	public Date getUserValidityPerriod() {
		return userValidityPerriod;
	}

	public void setUserValidityPerriod(Date userValidityPerriod) {
		this.userValidityPerriod = userValidityPerriod;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr == null ? null : ipAddr.trim();
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department == null ? null : department.trim();
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station == null ? null : station.trim();
	}

	public String getFileRightControl() {
		return fileRightControl;
	}

	public void setFileRightControl(String fileRightControl) {
		this.fileRightControl = fileRightControl;
	}

	public Integer getLoginFailNum() {
		return loginFailNum;
	}

	public void setLoginFailNum(Integer loginFailNum) {
		this.loginFailNum = loginFailNum;
	}

	public Date getLoginFailTime() {
		return loginFailTime;
	}

	public void setLoginFailTime(Date loginFailTime) {
		this.loginFailTime = loginFailTime;
	}
	
}