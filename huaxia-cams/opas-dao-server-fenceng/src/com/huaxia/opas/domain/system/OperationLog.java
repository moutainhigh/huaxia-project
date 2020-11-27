package com.huaxia.opas.domain.system;

import java.io.Serializable;
import java.util.Date;

public class OperationLog implements Serializable {

	private static final long serialVersionUID = 4399639542992242651L;

	/**
	 * 序列值
	 */
	private String autoId;

	/**
	 * 操作动作
	 */
	private String useAction;

	/**
	 * 操作人
	 */
	private String crtUser;
	
	/**
	 * 业务模块
	 */
	private String moudleType;

	/**
	 * 最后操作日期
	 */
	private Date userDate;
	
	/**
	 * 操作明细
	 */
	private String memo;
	

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getUseAction() {
		return useAction;
	}

	public void setUseAction(String useAction) {
		this.useAction = useAction;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public String getMoudleType() {
		return moudleType;
	}

	public void setMoudleType(String moudleType) {
		this.moudleType = moudleType;
	}

	public Date getUserDate() {
		return userDate;
	}

	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
