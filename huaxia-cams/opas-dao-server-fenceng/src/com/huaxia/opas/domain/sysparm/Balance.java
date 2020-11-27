package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 余额转移实体类
 * @author lipengfei
 *
 */
public class Balance implements Serializable{
	
	private static final long serialVersionUID = -8974190188150724362L;

	/**
	 * ID
	 */
	private String autoId;
	
	/**
	 * 产品编号
	 */
	private String prodCode;
	
	/**
	 * 产品名称
	 */
	private String prodName;
	
	/**
	 * 转移额度百分比
	 */
	private String moveLimitPer;
	
	/**
	 * 最高限额
	 */
	private String maxLimit;
	
	/**
	 * 最低限额
	 */
	private String lowLimit;
	
	/**
	 * 状态,0-停用,1-启用
	 */
	private String status;
	
	/**
	 * 启用日期
	 */
	private Date startDate;
	
	/**
	 * 停用日期
	 */
	private Date stopDate;
		
	/**
	 * 创建日期
	 */
	private Date crtDate;
		
	/**
	 * 创建人
	 */
	private String crtUser;
	
	/**
	 * 最后操作日期
	 */
	private Date lstUpdDate;
	
	/**
	 * 最后修改人
	 */
	private String lstUpUser;
	
	/**
	 * 当前登陆用户
	 */
	private String operator;

	/**
	 * id集合,便于批量操作
	 */
	private List<String> ids;

	public Balance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Balance(String autoId, String prodCode, String prodName, String moveLimitPer, String maxLimit,
			String lowLimit, String status, Date startDate, Date stopDate, Date crtDate, String crtUser,
			Date lstUpdDate, String lstUpUser, String operator, List<String> ids) {
		super();
		this.autoId = autoId;
		this.prodCode = prodCode;
		this.prodName = prodName;
		this.moveLimitPer = moveLimitPer;
		this.maxLimit = maxLimit;
		this.lowLimit = lowLimit;
		this.status = status;
		this.startDate = startDate;
		this.stopDate = stopDate;
		this.crtDate = crtDate;
		this.crtUser = crtUser;
		this.lstUpdDate = lstUpdDate;
		this.lstUpUser = lstUpUser;
		this.operator = operator;
		this.ids = ids;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getMoveLimitPer() {
		return moveLimitPer;
	}

	public void setMoveLimitPer(String moveLimitPer) {
		this.moveLimitPer = moveLimitPer;
	}

	public String getMaxLimit() {
		return maxLimit;
	}

	public void setMaxLimit(String maxLimit) {
		this.maxLimit = maxLimit;
	}

	public String getLowLimit() {
		return lowLimit;
	}

	public void setLowLimit(String lowLimit) {
		this.lowLimit = lowLimit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public String getLstUpUser() {
		return lstUpUser;
	}

	public void setLstUpUser(String lstUpUser) {
		this.lstUpUser = lstUpUser;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	
}
