package com.huaxia.opas.domain.allot;

import java.io.Serializable;
import java.util.Date;

public class WaitingEnter implements Serializable {
	private static final long serialVersionUID = 1114442293333617382L;
	private String appId;// 申请件编号APP_ID
	private String c1Cname;//姓名C1_CNAME
	private String c1Idnbr;//身份证号C1_IDNBR
	private String c1CoName;//单位中文名称 C1_CONAME
	private Date crtTime;//批次号时间 TIMESTAMP
	private String operateDesc;//流程节点最后状态 OPERATE_DESC
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getC1Cname() {
		return c1Cname;
	}
	public void setC1Cname(String c1Cname) {
		this.c1Cname = c1Cname;
	}
	public String getC1Idnbr() {
		return c1Idnbr;
	}
	public void setC1Idnbr(String c1Idnbr) {
		this.c1Idnbr = c1Idnbr;
	}
	public String getC1CoName() {
		return c1CoName;
	}
	public void setC1CoName(String c1CoName) {
		this.c1CoName = c1CoName;
	}
	public Date getCrtTime() {
		return crtTime;
	}
	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}
	public String getOperateDesc() {
		return operateDesc;
	}
	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}
	
}
