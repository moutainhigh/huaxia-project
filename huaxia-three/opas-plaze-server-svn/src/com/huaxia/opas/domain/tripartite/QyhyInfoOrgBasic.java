package com.huaxia.opas.domain.tripartite;

import java.io.Serializable;
import java.util.Date;
/**
 * 企业行业信息ORGBASIC 组织机构列表		
 * @author gaoh
 */
public class QyhyInfoOrgBasic implements Serializable{
	private static final long serialVersionUID = 503426942949565454L;

	private String uuid;//uuid

    private String appId;//申请件编号

    private String jgdm;//组织机构代码

    private String jgdz;//机构地址

    private String jgmc;//组织机构名称

    private String zybz;//质疑标志

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

    public String getJgdm() {
        return jgdm;
    }

    public void setJgdm(String jgdm) {
        this.jgdm = jgdm == null ? null : jgdm.trim();
    }

    public String getJgdz() {
        return jgdz;
    }

    public void setJgdz(String jgdz) {
        this.jgdz = jgdz == null ? null : jgdz.trim();
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