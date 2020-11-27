package com.huaxia.opas.domain.tripartite;

import java.io.Serializable;
import java.util.Date;
/**
 * 企业行业信息PERSON 主要管理人员		
 * @author gaoh
 */
public class QyhyInfoPerson implements Serializable{
	private static final long serialVersionUID = -2244764819592407128L;

	private String uuid;

    private String appId;//申请件编号

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