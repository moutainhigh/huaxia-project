package com.huaxia.opas.domain.thirdparty;
/**
 * 企业及行业信息 组织机构列表实体类
 */

import java.io.Serializable;
import java.util.Date;

public class QiYeOrgbasic implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 599355425354344164L;

	private String uuid;

    private String appId;

    private String jgdm;

    private String jgdz1;

    private String jgmc;

    private String zybz;

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

    public String getJgdm() {
        return jgdm;
    }

    public void setJgdm(String jgdm) {
        this.jgdm = jgdm == null ? null : jgdm.trim();
    }

    public String getJgdz1() {
        return jgdz1;
    }

    public void setJgdz1(String jgdz) {
        this.jgdz1 = jgdz1 == null ? null : jgdz1.trim();
    }

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc == null ? null : jgmc.trim();
    }

    public String getZybz() {
        return zybz;
    }

    public void setZybz(String zybz) {
        this.zybz = zybz == null ? null : zybz.trim();
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }
}