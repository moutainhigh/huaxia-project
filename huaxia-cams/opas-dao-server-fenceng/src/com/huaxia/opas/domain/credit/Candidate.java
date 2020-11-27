package com.huaxia.opas.domain.credit;

import java.io.Serializable;

/******************************
 *@describe:候选人对象
 *@author:xiaoJianFeng
 *Date:2017-03-15
 ***************************/
public class Candidate implements Serializable{
	private static final long serialVersionUID = 1L;
	private String role_name;//角色名称
	private String user_code;//角色码
	private String user_name;//用户名称
	private String group_name;//组名称
	private String group_no;//组码
	
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getGroup_no() {
		return group_no;
	}
	public void setGroup_no(String group_no) {
		this.group_no = group_no;
	}
	
	
	
}
