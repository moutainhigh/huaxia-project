package com.huaxia.opas.domain.avaya;

import java.io.Serializable;
import java.util.Date;

public class Avaya implements Serializable{
	
	private static final long serialVersionUID = -2736526524787299687L;
	
	private String Id ;
	
	private String userid ;
	
	private String Ip ;
	
	private String avayaLoginId ;
	
	private String avayaPassWd ;
	
	private String avayaStationId ;
	
	private String status ;
	
	private Date createTime ;

	public Avaya(String Id, String userid, String Ip, String avayaLoginId, String avayaPassWd, String avayaStationId,
			String status, Date createTime) {
		super();
		this.Id = Id;
		this.userid = userid;
		this.Ip = Ip;
		this.avayaLoginId = avayaLoginId;
		this.avayaPassWd = avayaPassWd;
		this.avayaStationId = avayaStationId;
		this.status = status;
		this.createTime = createTime;
	}

	public Avaya() {
		super();
	}

	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public String getuserid() {
		return userid;
	}

	public void setuserid(String userid) {
		this.userid = userid;
	}

	public String getIp() {
		return Ip;
	}

	public void setIp(String Ip) {
		this.Ip = Ip;
	}

	public String getAvayaLoginId() {
		return avayaLoginId;
	}

	public void setAvayaLoginId(String avayaLoginId) {
		this.avayaLoginId = avayaLoginId;
	}

	public String getAvayaPassWd() {
		return avayaPassWd;
	}

	public void setAvayaPassWd(String avayaPassWd) {
		this.avayaPassWd = avayaPassWd;
	}

	public String getAvayaStationId() {
		return avayaStationId;
	}

	public void setAvayaStationId(String avayaStationId) {
		this.avayaStationId = avayaStationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((Ip == null) ? 0 : Ip.hashCode());
		result = prime * result + ((avayaLoginId == null) ? 0 : avayaLoginId.hashCode());
		result = prime * result + ((avayaPassWd == null) ? 0 : avayaPassWd.hashCode());
		result = prime * result + ((avayaStationId == null) ? 0 : avayaStationId.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avaya other = (Avaya) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (Ip == null) {
			if (other.Ip != null)
				return false;
		} else if (!Ip.equals(other.Ip))
			return false;
		if (avayaLoginId == null) {
			if (other.avayaLoginId != null)
				return false;
		} else if (!avayaLoginId.equals(other.avayaLoginId))
			return false;
		if (avayaPassWd == null) {
			if (other.avayaPassWd != null)
				return false;
		} else if (!avayaPassWd.equals(other.avayaPassWd))
			return false;
		if (avayaStationId == null) {
			if (other.avayaStationId != null)
				return false;
		} else if (!avayaStationId.equals(other.avayaStationId))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Avaya [Id=" + Id + ", userid=" + userid + ", Ip=" + Ip + ", avayaLoginId=" + avayaLoginId
				+ ", avayaPassWd=" + avayaPassWd + ", avayaStationId=" + avayaStationId + ", status=" + status
				+ ", createTime=" + createTime + "]";
	}

	
	
}
