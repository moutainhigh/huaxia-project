package com.huaxia.opas.domain.monitor;

import java.io.Serializable;
import java.sql.Timestamp;

public class Monitor3rdLog implements Serializable {
	
	private static final long serialVersionUID = 5329768494255201812L;
	
	private String app_ID;
	private Timestamp crt_TIME;
	private String module_NAME;
	private String flag_DISPOSE;
	private String dispose_RESULT_CODE;
	private String dispose_RESULT_DESC;
	public String getApp_ID() {
		return app_ID;
	}
	public void setApp_ID(String app_ID) {
		this.app_ID = app_ID;
	}
	public Timestamp getCrt_TIME() {
		return crt_TIME;
	}
	public void setCrt_TIME(Timestamp crt_TIME) {
		this.crt_TIME = crt_TIME;
	}
	public String getModule_NAME() {
		return module_NAME;
	}
	public void setModule_NAME(String module_NAME) {
		this.module_NAME = module_NAME;
	}
	public String getFlag_DISPOSE() {
		return flag_DISPOSE;
	}
	public void setFlag_DISPOSE(String flag_DISPOSE) {
		this.flag_DISPOSE = flag_DISPOSE;
	}
	public String getDispose_RESULT_CODE() {
		return dispose_RESULT_CODE;
	}
	public void setDispose_RESULT_CODE(String dispose_RESULT_CODE) {
		this.dispose_RESULT_CODE = dispose_RESULT_CODE;
	}
	public String getDispose_RESULT_DESC() {
		return dispose_RESULT_DESC;
	}
	public void setDispose_RESULT_DESC(String dispose_RESULT_DESC) {
		this.dispose_RESULT_DESC = dispose_RESULT_DESC;
	}
	
	

}
