package com.huaxia.plaze.ui.system.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Alias("User")
public class User implements Serializable {

	private static final long serialVersionUID = 4806819038812406820L;

	// 记录编号
	private String uuid;

	// 用户编号
	private String userId;

	// 用户账号
	private String account;

	// 用户口令
	private String password;

	// 用户口令（旧）
	private String oldPassword;

	// 用户状态
	private String status;

	// 用户状态名称
	private String statusName;

	// 终端地址
	private String ip;

	// 首次登录标志
	private String firstLogin;

	// 更新登录时间
	private String lastLoginTime;

	// 密码失效时间
	private String passwordExpireTime;

	// 员工工号
	private String staffId;

	// 员工姓名
	private String staffName;

	// 证件类型
	private String certType;

	// 证件类型名称
	private String certTypeName;

	// 证件号码
	private String certNo;

	// 手机号码
	private String mobile;

	// 部门
	private String department;

	// 团队
	private String team;

	// 团队名称
	private String teamName;

	// 岗位
	private String post;

	// 是否具有人行查询权限
	private String pbocAuth;

	// 人行查询权限
	private String pbocAuthQuery;

	// 人行查询时间（默认9:00-17:00）
	private String pbocAuthQueryTime;

	// 人行单日最大查询条数
	private int pbocDayQueryMaxCount;

	// 人行单日最大查询条数
	private int pbocDaySearchMaxCount;

	// 创建用户
	private String createUser;

	// 最后修改用户
	private String lastModifyUser;

	/**
	 * 用户是否首次登录
	 * 
	 * @return {@code true}-首次登录 / {@code false}-非首次登录
	 */
	@JsonIgnore
	public boolean isFirstTimeLogin() {
		return "1".equals(getFirstLogin()) ? true : false;
	}

	/**
	 * 向后调整密码过期时间
	 * 
	 * @param day
	 *            调整天数
	 */
	@JsonIgnore
	public void addPasswordExpireTime(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, day);
		setPasswordExpireTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
	}

	/**
	 * 向后调整密码过期时间
	 * 
	 * @param date
	 *            当前日期
	 * @param day
	 *            调整天数
	 */
	@JsonIgnore
	public void addPasswordExpireTime(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		setPasswordExpireTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(String firstLogin) {
		this.firstLogin = firstLogin;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getPasswordExpireTime() {
		return passwordExpireTime;
	}

	public void setPasswordExpireTime(String passwordExpireTime) {
		this.passwordExpireTime = passwordExpireTime;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertTypeName() {
		return certTypeName;
	}

	public void setCertTypeName(String certTypeName) {
		this.certTypeName = certTypeName;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPbocAuth() {
		return pbocAuth;
	}

	public void setPbocAuth(String pbocAuth) {
		this.pbocAuth = pbocAuth;
	}

	public String getPbocAuthQuery() {
		return pbocAuthQuery;
	}

	public void setPbocAuthQuery(String pbocAuthQuery) {
		this.pbocAuthQuery = pbocAuthQuery;
	}

	public String getPbocAuthQueryTime() {
		return pbocAuthQueryTime;
	}

	public void setPbocAuthQueryTime(String pbocAuthQueryTime) {
		this.pbocAuthQueryTime = pbocAuthQueryTime;
	}

	public int getPbocDayQueryMaxCount() {
		return pbocDayQueryMaxCount;
	}

	public void setPbocDayQueryMaxCount(int pbocDayQueryMaxCount) {
		this.pbocDayQueryMaxCount = pbocDayQueryMaxCount;
	}

	public int getPbocDaySearchMaxCount() {
		return pbocDaySearchMaxCount;
	}

	public void setPbocDaySearchMaxCount(int pbocDaySearchMaxCount) {
		this.pbocDaySearchMaxCount = pbocDaySearchMaxCount;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}
}
