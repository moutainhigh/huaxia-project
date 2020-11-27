package com.huaxia.opas.domain.allot;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 组员:用户表  用户机构关系  机构  查所有用户
 * @author wangdebin
 *
 */
public class AllotCommon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1441030982392961646L;
	//组员id
	private String userId;
	//组员编号
	private String userCode;
	//组员姓名
	private String userName;
	//
	private String autoStatus;
	//userId集合
	private List<String> ids;
	//组id
	private String orgId;
	//组代码(团队编号)
	private String orgNo;
	//组名称
	private String orgName;
	private String orgAddr;
	private String orgLevel;
	//角色
	private String roleId;
	
	private String roleCode;
	
	private String roleName;
	
	private int orgNum;
	
	private int userNum;
	
	private int userNum2;
	/**
	 * 启用状态,0-停用1-启用
	 */
	private String status;
	private Date crtDate;
	private String crtUser;
	private String lstUpdUser;
	private Date lstUpdDate;
	public String getOrgNo() {
		return orgNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setOrgNo(String orgNo) {
		this. orgNo=(orgNo == null ? null : orgNo.trim());
	}
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = (crtUser == null ? null : crtUser.trim());
	}
	public String getLstUpdUser() {
		return lstUpdUser;
	}
	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = (lstUpdUser == null ? null : lstUpdUser.trim());
	}
	public Date getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	public Date getLstUpdDate() {
		return lstUpdDate;
	}
	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getOrgAddr() {
		return orgAddr;
	}
	public void setOrgAddr(String orgAddr) {
		this.orgAddr = orgAddr;
	}
	public String getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}
	public int getOrgNum() {
		return orgNum;
	}
	public void setOrgNum(int orgNum) {
		this.orgNum = orgNum;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	public String getAutoStatus() {
		return autoStatus;
	}
	public void setAutoStatus(String autoStatus) {
		this.autoStatus = autoStatus;
	}
	public int getUserNum2() {
		return userNum2;
	}
	public void setUserNum2(int userNum2) {
		this.userNum2 = userNum2;
	}
	
	
}
