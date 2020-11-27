package com.huaxia.opas.domain.allot;

import java.io.Serializable;
import java.util.Date;
/**
 * 队列存储
 * @author wangdebin
 */
public class AllotQueue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -290840716516656715L;
	//队列id
	private String queueId;
	//队列代码
	private String queueCode;
	//队列名称  
	private String queueName;
	
	//队列等级
	private String queueLevel;
	private Date crtDate;
	private String crtUser;
	private String lstUpdUser;
	private Date lstUpdDate;
	
	private int queueNum;
	public String getQueueId() {
		return queueId;
	}
	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}
	public String getQueueCode() {
		return queueCode;
	}
	public void setQueueCode(String queueCode) {
		this.queueCode = queueCode;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = (crtUser == null ? null : crtUser.trim());
	}
	public String getLstUpdUser() {
		return lstUpdUser;
	}
	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = (lstUpdUser == null ? null : lstUpdUser.trim());
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
	public String getQueueLevel() {
		return queueLevel;
	}
	public void setQueueLevel(String queueLevel) {
		this.queueLevel = queueLevel;
	}
	
	public int getQueueNum() {
		return queueNum;
	}
	public void setQueueNum(int queueNum) {
		this.queueNum = queueNum;
	}
}
