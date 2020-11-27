package com.huaxia.opas.domain.thirdparty;
import java.io.Serializable;
/**
 * 企业及行业信息  主要管理人员实体类
 */
import java.util.Date;

public class QiYePerson implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -64486869905446873L;

	private String uuid;

    private String appId;

    private String entname;

    private String pername;

    private String personamount;

    private String position;

    private Date crtTime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getEntname() {
        return entname;
    }

    public void setEntname(String entname) {
        this.entname = entname == null ? null : entname.trim();
    }

    public String getPername() {
        return pername;
    }

    public void setPername(String pername) {
        this.pername = pername == null ? null : pername.trim();
    }

    public String getPersonamount() {
        return personamount;
    }

    public void setPersonamount(String personamount) {
        this.personamount = personamount == null ? null : personamount.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }
}