package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 百融征信 & 反欺诈 & 特殊名单核查
 * 
 * @author zhiguo.li
 *
 */
public abstract class BRZXSpecialList extends BRZX implements Serializable {

	private static final long serialVersionUID = 1791825490636164812L;

	// 请求参数 & 身份证号
	private String identityCard;

	// 请求参数 & 手机号
	private String cell;

	// 请求参数 & 姓名
	private String name;

	// 响应参数 & 联系人手机号
	private String linkmanCell;

	// 请求参数 & 百融全局设备标识
	private String gid;
	
	// 响应参数 & 产品输出标识
	private String flagSpecialList;

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkmanCell() {
		return linkmanCell;
	}

	public void setLinkmanCell(String linkmanCell) {
		this.linkmanCell = linkmanCell;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getFlagSpecialList() {
		return flagSpecialList;
	}

	public void setFlagSpecialList(String flagSpecialList) {
		this.flagSpecialList = flagSpecialList;
	}

}
