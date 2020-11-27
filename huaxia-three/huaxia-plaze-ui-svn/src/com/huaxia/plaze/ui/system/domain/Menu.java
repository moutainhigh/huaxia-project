package com.huaxia.plaze.ui.system.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

/**
 * 菜单
 * 
 * @author zhiguo.li
 *
 */
@Alias("Menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = 5066877857713992299L;

	/** 菜单编号 */
	private String id;

	/** 菜单名称 */
	private String name;

	/** 菜单级别 */
	private Integer level;

	/** 菜单链接 */
	private String link;

	/** 菜单序号 */
	private Integer index;

	/** 所属父级菜单编号 */
	private String pid;
	
	/** 菜单状态（0-禁用|1-启用） */
	private String status;

	private List<Menu> children;
	
	// 创建用户
	private String createUser;
	
	// 最后修改用户
	private String lastModifyUser;
	
	// 选中状态
	private String checked;
	
	// 所属菜单
	private String pname;

	public Menu() {
	}

	/**
	 * 菜单构造函数
	 * 
	 * @param id
	 *            菜单编号
	 * @param name
	 *            菜单名称
	 * @param level
	 *            菜单级别
	 * @param link
	 *            菜单链接
	 * @param index
	 *            菜单序号
	 * @param pid
	 *            所属父级菜单编号
	 */
	public Menu(String id, String name, Integer level, String link, Integer index, String pid) {
		this.id = id;
		this.name = name;
		this.level = level;
		this.link = link;
		this.index = index;
		this.pid = pid;
		
		// 默认启用
		this.status = "1";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
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

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

}
