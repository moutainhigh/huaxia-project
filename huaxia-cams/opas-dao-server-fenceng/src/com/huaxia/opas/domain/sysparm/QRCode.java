package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 参数管理 二维码名单维护
 * 
 * @author zhangjunwen
 *
 */
public class QRCode implements Serializable {

	private static final long serialVersionUID = -2316813983971509644L;

	/**
	 * UUID
	 */
	private String uuId;

	/**
	 * 公司编码
	 */
	private String companyCode;

	/**
	 * 公司名称
	 */
	private String companyName;

	/**
	 * 模块编码
	 */
	private String moduleCode;

	/**
	 * 模块名称
	 */
	private String moduleName;

	/**
	 * 推广编码
	 */
	private String extensionCode;
	/**
	 * 模块名称
	 */
	private String areaCode;
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

	/**
	 * 创建人姓名
	 */
	private String operator;
	/**
	 * 最后修改人
	 */
	private String lstUpdUser;

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getExtensionCode() {
		return extensionCode;
	}

	public void setExtensionCode(String extensionCode) {
		this.extensionCode = extensionCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

}
