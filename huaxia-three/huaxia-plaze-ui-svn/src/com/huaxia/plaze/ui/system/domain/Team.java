package com.huaxia.plaze.ui.system.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Team")
public class Team implements Serializable {

	private static final long serialVersionUID = -8896023362410488557L;

	// 角色ID
	private String teamId;

	// 角色权限
	private String teamName;

	// 权限描述
	private String teamDesc;

	// 选中状态
	private String checked;

	// 创建用户
	private String createUser;

	// 最后修改用户
	private String lastModifyUser;
	
	//团队状态
	private String teamStatus;

	public String getTeamStatus() {
		return teamStatus;
	}

	public void setTeamStatus(String teamStatus) {
		this.teamStatus = teamStatus;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamDesc() {
		return teamDesc;
	}

	public void setTeamDesc(String teamDesc) {
		this.teamDesc = teamDesc;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
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
