package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 参数管理 二维码名单维护
 * 
 *
 */
public class MarktQRCode implements Serializable {

	private static final long serialVersionUID = -2316813983971509644L;

	/**
	 * UUID
	 */
	private String uuId;

	/**
	 * 网申商户编号
	 */
	private String coopmarchant_code;
	/**
	 * 网申商户编号
	 */
	private String coopmarchant_name;
	/**
	 * 创建日期
	 */
	private Date crtDate;
	/**
	 * 启用日期
	 */
	private Date startDate;
	/**
	 * 停用日期
	 */
	private Date stopDate;
	/**
	 * 最后一次修改日期
	 */
	private Date lstUpdDate;

	/**
	 * 状态,0-停用,1-启用 , 2-暂停
	 */
	private String status;
	private String lstUpdUser;
	private String operator;

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getCoopmarchant_code() {
		return coopmarchant_code;
	}

	public void setCoopmarchant_code(String coopmarchant_code) {
		this.coopmarchant_code = coopmarchant_code;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCoopmarchant_name() {
		return coopmarchant_name;
	}

	public void setCoopmarchant_name(String coopmarchant_name) {
		this.coopmarchant_name = coopmarchant_name;
	}

	/**
	 * 设置ID的集合,便于批量操作时的调用
	 */
	private List<String> ids;

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}


}
