package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 参数管理 营销员白名单
 * @author hxb
 */
public class Marketer implements Serializable {

	private static final long serialVersionUID = 6831696381655757089L;

	private String autoId;
	
	private String userName;
	
	private String spreadOrg;
	
	private String spreadNo;
	
	private Date startDate;
	
	private Date endDate;
	
	private Date crtDate;
	
	private Date lstUpdDate;
	
	private String status;

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSpreadOrg() {
		return spreadOrg;
	}

	public void setSpreadOrg(String spreadOrg) {
		this.spreadOrg = spreadOrg;
	}

	public String getSpreadNo() {
		return spreadNo;
	}

	public void setSpreadNo(String spreadNo) {
		this.spreadNo = spreadNo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
