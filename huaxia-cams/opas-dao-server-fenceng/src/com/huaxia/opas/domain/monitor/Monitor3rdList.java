package com.huaxia.opas.domain.monitor;

import java.io.Serializable;
import java.sql.Timestamp;

public class Monitor3rdList implements Serializable {

	private static final long serialVersionUID = -7690473662699403870L;
	
	private String id;
	private String app_ID;
	private String task_TYPE;
	private String task_STATUS;
	private Timestamp time_STAMP;
	private String identity_TYPE;
	private String identity_NO;
	private String name;
	private String process_ID;
	private String node_ID;
	private Timestamp sysdate;
	private long minus;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApp_ID() {
		return app_ID;
	}
	public void setApp_ID(String app_ID) {
		this.app_ID = app_ID;
	}
	public String getTask_TYPE() {
		return task_TYPE;
	}
	public void setTask_TYPE(String task_TYPE) {
		this.task_TYPE = task_TYPE;
	}
	public String getTask_STATUS() {
		return task_STATUS;
	}
	public void setTask_STATUS(String task_STATUS) {
		this.task_STATUS = task_STATUS;
	}
	public Timestamp getTime_STAMP() {
		return time_STAMP;
	}
	public void setTime_STAMP(Timestamp time_STAMP) {
		this.time_STAMP = time_STAMP;
	}
	public String getIdentity_TYPE() {
		return identity_TYPE;
	}
	public void setIdentity_TYPE(String identity_TYPE) {
		this.identity_TYPE = identity_TYPE;
	}
	public String getIdentity_NO() {
		return identity_NO;
	}
	public void setIdentity_NO(String identity_NO) {
		this.identity_NO = identity_NO;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProcess_ID() {
		return process_ID;
	}
	public void setProcess_ID(String process_ID) {
		this.process_ID = process_ID;
	}
	public String getNode_ID() {
		return node_ID;
	}
	public void setNode_ID(String node_ID) {
		this.node_ID = node_ID;
	}
	public Timestamp getSysdate() {
		return sysdate;
	}
	public void setSysdate(Timestamp sysdate) {
		this.sysdate = sysdate;
	}
	public long getMinus() {
		return minus;
	}
	public void setMinus(long minus) {
		this.minus = minus;
	}
	

	
	
}
