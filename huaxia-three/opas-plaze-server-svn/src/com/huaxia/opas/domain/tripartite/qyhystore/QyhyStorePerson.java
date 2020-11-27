package com.huaxia.opas.domain.tripartite.qyhystore;

import java.io.Serializable;
import java.util.Date;
/**
 * 企业信息库 企业行业信息PERSON 主要管理人员		
 * @author gaoh
 */
public class QyhyStorePerson implements Serializable{
	private static final long serialVersionUID = 2018795782065448683L;

	private String uuid;

	private String relateUuid;//关联的uuid

    private String entname;//企业名称

    private String pername;//人员姓名

    private String personamount;//人员总数量

    private String position;//职位

    private Date crtTime;//创建时间

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getRelateUuid() {
        return relateUuid;
    }

    public void setRelateUuid(String relateUuid) {
        this.relateUuid = relateUuid == null ? null : relateUuid.trim();
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