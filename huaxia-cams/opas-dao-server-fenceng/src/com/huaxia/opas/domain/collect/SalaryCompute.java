package com.huaxia.opas.domain.collect;

import java.io.Serializable;
import java.util.Date;

/**
 * 收入计算
 * 
 * @author zhiguo.li
 *
 */
public class SalaryCompute implements Serializable {

	private static final long serialVersionUID = 5980076599886877991L;

	// UUID
	private String insideAppNo;

	// 申请件编号
	private String appId;
	
	// 数据存储器
	private String storage;
	
	// 数据计数器
	private String counter;

	// 创建日期
	private Date createDate;

	// 创建人
	private String createUser;

	// 最后修改日期
	private Date lastUpdateDate;

	// 最后修改人
	private String lastUpdateUser;

	public SalaryCompute() {
	}

	public String getInsideAppNo() {
		return insideAppNo;
	}

	public void setInsideAppNo(String insideAppNo) {
		this.insideAppNo = insideAppNo;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getCounter() {
		return counter;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

}
