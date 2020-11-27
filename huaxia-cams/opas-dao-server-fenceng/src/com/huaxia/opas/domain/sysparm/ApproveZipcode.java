package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 邮编区号维护实体类
 * @author liudi
 * @since 2017-03-16
 * @version 1.0
 */
public class ApproveZipcode implements Serializable{
	
	private static final long serialVersionUID = 8272030085929549511L;

	/**
	 * ID
	 */
	private String autoId;

	/**
	 * 邮编代码
	 */
	private String zipCode;
	
	/**
	 * 账户类型
	 */
	private String acctType;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 电话区号
	 */
	private String telNo;
	
	/**
	 * 区号渠道
	 */
	private String areaChannel;

	/**
	 * 网点状态
	 */
	private String status;
	
	/**
	 * 网点启用时间
	 */
	private Date startDate;
	
	/**
	 * 网点停用时间
	 */
	private Date stopDate;
	
	/**
	 * 异地营销
	 */
	private String remoteMarket;
	
	/**
	 * 异地营销城市分类
	 */
	private String remoteMarketCity;
	
	/**
	 * 异地营销城市状态
	 */
	private String remoteMarketStatus;
	
	/**
	 * 异地营销城市启用时间
	 */
	private Date remoteStartDate;

	/**
	 * 异地营销城市停用时间
	 */
	private Date remoteStopDate;

	/**
	 * 创建人
	 */
	private String crtUser;

	/**
	 * 创建时间
	 */
	private Date crtDate;
	
	/**
	 * 最后操作人
	 */
	private String lstUpdUser;
	
	/**
	 * 最后操作时间
	 */
	private Date lstUpdDate;
	
	/**
	 * 省份
	 */
	private String province;
	
	/**
	 * id集合,用于批量操作
	 */
	private List<String> ids;

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = (autoId == null ? null : autoId.trim());
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = (zipCode == null ? null : zipCode.trim());
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = (acctType == null ? null : acctType.trim());
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = (city == null ? null : city.trim());
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = (telNo == null ? null : telNo.trim());
	}

	public String getAreaChannel() {
		return areaChannel;
	}

	public void setAreaChannel(String areaChannel) {
		this.areaChannel = (areaChannel == null ? null : areaChannel.trim());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = (status == null ? null : status.trim());
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public String getRemoteMarket() {
		return remoteMarket;
	}

	public void setRemoteMarket(String remoteMarket) {
		this.remoteMarket = (remoteMarket == null ? null : remoteMarket.trim());
	}

	public String getRemoteMarketCity() {
		return remoteMarketCity;
	}

	public void setRemoteMarketCity(String remoteMarketCity) {
		this.remoteMarketCity = (remoteMarketCity == null ? null : remoteMarketCity.trim());
	}

	public String getRemoteMarketStatus() {
		return remoteMarketStatus;
	}

	public void setRemoteMarketStatus(String remoteMarketStatus) {
		this.remoteMarketStatus = (remoteMarketStatus == null ? null : remoteMarketStatus.trim());
	}

	public Date getRemoteStartDate() {
		return remoteStartDate;
	}

	public void setRemoteStartDate(Date remoteStartDate) {
		this.remoteStartDate = remoteStartDate;
	}

	public Date getRemoteStopDate() {
		return remoteStopDate;
	}

	public void setRemoteStopDate(Date remoteStopDate) {
		this.remoteStopDate = remoteStopDate;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getLstUpdUser() {
		return lstUpdUser;
	}

	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}

	public Date getLstUpdDate() {
		return lstUpdDate;
	}

	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
