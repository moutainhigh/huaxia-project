package com.huaxia.opas.domain.allot;

import java.io.Serializable;
import java.util.Date;
/**
 * 分配临时表
 * @author wangdebin
 */
public class TempAllot implements Serializable {
	
	private static final long serialVersionUID = 962123004359457725L;

	private String tempId;

	private String allotId;

	private Date crtDate;
	
	private Date lstDate;
	
	private String inputXml;
	
	private String currNode;
	
	private String ydjFlag;
	
	private String userCode;
	
	private String processIp;
	
	private String status;//0 未调工作流  1 调工作流

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getAllotId() {
		return allotId;
	}

	public void setAllotId(String allotId) {
		this.allotId = allotId;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public Date getLstDate() {
		return lstDate;
	}

	public void setLstDate(Date lstDate) {
		this.lstDate = lstDate;
	}

	public String getInputXml() {
		return inputXml;
	}

	public void setInputXml(String inputXml) {
		this.inputXml = inputXml;
	}

	public String getCurrNode() {
		return currNode;
	}

	public void setCurrNode(String currNode) {
		this.currNode = currNode;
	}

	public String getYdjFlag() {
		return ydjFlag;
	}

	public void setYdjFlag(String ydjFlag) {
		this.ydjFlag = ydjFlag;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getProcessIp() {
		return processIp;
	}

	public void setProcessIp(String processIp) {
		this.processIp = processIp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
