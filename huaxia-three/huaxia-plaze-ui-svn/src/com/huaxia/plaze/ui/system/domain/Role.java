package com.huaxia.plaze.ui.system.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("Role")
public class Role implements Serializable {

	private static final long serialVersionUID = 4999222191204107216L;

	// 角色ID
	private String roleId;

	// 角色权限
	private String roleName;

	// 权限描述
	private String roleDesc;

	// 选中状态
	private String checked;

	// 创建用户
	private String createUser;

	// 最后修改用户
	private String lastModifyUser;
	
	//角色状态
    private String roleStatus;
    
	

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	private List<Menu> menuList;

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

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
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
