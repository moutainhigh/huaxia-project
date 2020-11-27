package com.huaxia.opas.domain.avaya;

import java.io.Serializable;
import java.util.Date;

public class AvayaLog implements Serializable{

	private static final long serialVersionUID = -1868884220602822192L;
	private String Id;
	// 分机号
	private String avayaStationId;
	// 客户端IP
	private String avayaIp;
	// 拨打的电话号码
	private String phoneNum;
	// 拨打电话时间
	private Date callTime;

	/**
	 * @return the id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * @return the avayaStationId
	 */
	public String getAvayaStationId() {
		return avayaStationId;
	}

	/**
	 * @param avayaStationId
	 *            the avayaStationId to set
	 */
	public void setAvayaStationId(String avayaStationId) {
		this.avayaStationId = avayaStationId;
	}

	/**
	 * @return the avayaIp
	 */
	public String getAvayaIp() {
		return avayaIp;
	}

	/**
	 * @param avayaIp
	 *            the avayaIp to set
	 */
	public void setAvayaIp(String avayaIp) {
		this.avayaIp = avayaIp;
	}

	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * @param phoneNum
	 *            the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * @return the callTime
	 */
	public Date getCallTime() {
		return callTime;
	}

	/**
	 * @param callTime
	 *            the callTime to set
	 */
	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AvayaLog [Id=" + Id + ", avayaStationId=" + avayaStationId + ", avayaIp=" + avayaIp + ", phoneNum="
				+ phoneNum + ", callTime=" + callTime + "]";
	}


}
