package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 简项公安的参数匹配实体类
 * @author wangtao
 *@version v1.0第一版
 */
@SuppressWarnings("serial")
public class MatchIdenity implements Serializable{
	//序号
	private String id;
	
	//城市名称
	private String cityName;
	
	//证件号
	private String idNo;
	
	//电话区号
	private String telArea;
	
	//进件渠道
	private String inputChannel;
	
	//推广机构单位
	private String extensionAgency;
	
	//启停状态
	private String status;
	
	//启用时间
	private Date startTime;
	
	//停用时间
	private Date stopTime;
	
	//修改时间
	private Date updTime;
	
	//最后操作人
	private String lstUpdUser;

	//是否显示标志
	private String viewFlag;
	
	//批量操作id集合
	private List<String> ids;
	
	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getTelArea() {
		return telArea;
	}

	public void setTelArea(String telArea) {
		this.telArea = telArea;
	}

	public String getInputChannel() {
		return inputChannel;
	}

	public void setInputChannel(String inputChannel) {
		this.inputChannel = inputChannel;
	}

	public String getExtensionAgency() {
		return extensionAgency;
	}

	public void setExtensionAgency(String extensionAgency) {
		this.extensionAgency = extensionAgency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	public String getViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}

	@Override
	public String toString() {
		return "MatchIdenity [id=" + id + ", cityName=" + cityName + ", idNo=" + idNo + ", telArea=" + telArea
				+ ", inputChannel=" + inputChannel + ", extensionAgency=" + extensionAgency + ", status=" + status
				+ ", startTime=" + startTime + ", stopTime=" + stopTime + ", updTime=" + updTime + ", lstUpdUser="
				+ lstUpdUser + ", viewFlag=" + viewFlag + "]";
	}

}
