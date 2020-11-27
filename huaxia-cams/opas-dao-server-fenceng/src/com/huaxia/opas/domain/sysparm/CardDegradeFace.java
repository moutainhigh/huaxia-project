package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;

public class CardDegradeFace  implements Serializable{
	
	private static final long serialVersionUID = -7196323978768934403L;

	//主键uuid
	private String id;
	
	//申请卡产品
	private String startCard;
	
	//申请卡产品名称
	private String startCardName;
	
	//授信降级卡产品
	private String endCard;
	
	//授信降级卡产品名称
	private String endCardName;
	
	//启用状态
	private String status;
	
	//卡版面对应关系
	private String faceReation;
	
	//卡版面对应描述
	private String faceReationDesc;
	
	//创建时间
	private Date crtTime;
	
	//创建人
	private String crtUser;
	
	//最后操作时间
	private Date lastTime;
	
	//最后操作人
	private String lastUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartCard() {
		return startCard;
	}

	public void setStartCard(String startCard) {
		this.startCard = startCard;
	}

	public String getEndCard() {
		return endCard;
	}

	public void setEndCard(String endCard) {
		this.endCard = endCard;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFaceReation() {
		return faceReation;
	}

	public void setFaceReation(String faceReation) {
		this.faceReation = faceReation;
	}

	public String getFaceReationDesc() {
		return faceReationDesc;
	}

	public void setFaceReationDesc(String faceReationDesc) {
		this.faceReationDesc = faceReationDesc;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getLastUser() {
		return lastUser;
	}

	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}

	public String getStartCardName() {
		return startCardName;
	}

	public void setStartCardName(String startCardName) {
		this.startCardName = startCardName;
	}

	public String getEndCardName() {
		return endCardName;
	}

	public void setEndCardName(String endCardName) {
		this.endCardName = endCardName;
	}
	
	
}

