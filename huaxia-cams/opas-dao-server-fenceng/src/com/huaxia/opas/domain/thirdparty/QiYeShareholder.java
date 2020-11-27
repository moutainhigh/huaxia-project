package com.huaxia.opas.domain.thirdparty;
import java.io.Serializable;
/**
 * 企业及行业信息  股东及出资人信息实体类
 */
import java.util.Date;

public class QiYeShareholder implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4354726040477075902L;

	private String uuid;

    private String appId;

    private String condate;

    private String conform;

    private String country;

    private String fundedratio;

    private String invtype;

    private String regcapcur;

    private String shaname;

    private String subconam;

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

    public String getCondate() {
        return condate;
    }

    public void setCondate(String condate) {
        this.condate = condate == null ? null : condate.trim();
    }

    public String getConform() {
        return conform;
    }

    public void setConform(String conform) {
        this.conform = conform == null ? null : conform.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getFundedratio() {
        return fundedratio;
    }

    public void setFundedratio(String fundedratio) {
        this.fundedratio = fundedratio == null ? null : fundedratio.trim();
    }

    public String getInvtype() {
        return invtype;
    }

    public void setInvtype(String invtype) {
        this.invtype = invtype == null ? null : invtype.trim();
    }

    public String getRegcapcur() {
        return regcapcur;
    }

    public void setRegcapcur(String regcapcur) {
        this.regcapcur = regcapcur == null ? null : regcapcur.trim();
    }

    public String getShaname() {
        return shaname;
    }

    public void setShaname(String shaname) {
        this.shaname = shaname == null ? null : shaname.trim();
    }

    public String getSubconam() {
        return subconam;
    }

    public void setSubconam(String subconam) {
        this.subconam = subconam == null ? null : subconam.trim();
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }
}