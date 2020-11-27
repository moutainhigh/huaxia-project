package com.huaxia.opas.domain.decision;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class OpaInnerDataCheck implements Serializable{

	private static final long serialVersionUID = 7698589136580469816L;

	private String autoId;
	private String ishaveInriskInfo_column;
    private String appId;
     /**
      * 重复申请检查 0:否
		1:是
      */
    private String repeatCheck;
    /**
     * 灰名单(个人) 0:否
		1:是
     */
    private String graylist;
    /**
     * 证件号码有效期 0:否
		1:是
     */
    private String certifinoVailud;
    //附属卡证件号码有效期
    private String certifinoVailud_fk;
    /**
     * 关联性检查-手机 0:否
		1:是
     */
    private String relatedcheckTel;
    /**
     * 关联性检查-家庭地址 0:否
	1:是
     */
    private String relatedcheckHomeaddr;
    /**
     * 关联性检查-非公司的邮寄地址 0:否
		1:是
     */
    private String relatedcheckCompanyaddr;
    /**
     * 关联性检查-直系亲属手机号码 0:否
	1:是
     */
    private String relatedcheckNearlyTelno;
    /**
     * 风险渠道检查 0:否
	1:是
     */
    private String riskchannelCheck;
    /**
     * 不安全推广人员 0:否
		1:是
     */
    private String nosafePromoter;
    /**
     * 申请信息逻辑检查 0:否
	1:是
     */
    private String applyinfoBuscheck;
    /**
     * 是否命中内部风险信息 0:否
	1:是
     */
    private String ishaveInriskInfo;
    @JSONField(format="yyyy/MM/dd HH:mm:ss")
    private Date crtDate;
    
    /**
     * 国家/证件信息与税收居民逻辑检查 0:否
	1:是
     */
    private String c6ResiTaxid;

    private String crtUser;

    public String getAutoId() {
        return autoId;
    }
    
    public String getC6ResiTaxid() {
		return c6ResiTaxid;
	}


	public void setC6ResiTaxid(String c6ResiTaxid) {
		this.c6ResiTaxid = c6ResiTaxid;
	}


	public void setAutoId(String autoId) {
        this.autoId = autoId == null ? null : autoId.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getRepeatCheck() {
        return repeatCheck;
    }

    public void setRepeatCheck(String repeatCheck) {
        this.repeatCheck = repeatCheck == null ? null : repeatCheck.trim();
    }

    public String getGraylist() {
        return graylist;
    }

    public void setGraylist(String graylist) {
        this.graylist = graylist == null ? null : graylist.trim();
    }

    public String getCertifinoVailud() {
        return certifinoVailud;
    }

    public void setCertifinoVailud(String certifinoVailud) {
        this.certifinoVailud = certifinoVailud == null ? null : certifinoVailud.trim();
    }

    public String getRelatedcheckTel() {
        return relatedcheckTel;
    }

    public void setRelatedcheckTel(String relatedcheckTel) {
        this.relatedcheckTel = relatedcheckTel == null ? null : relatedcheckTel.trim();
    }

    public String getRelatedcheckHomeaddr() {
        return relatedcheckHomeaddr;
    }

    public void setRelatedcheckHomeaddr(String relatedcheckHomeaddr) {
        this.relatedcheckHomeaddr = relatedcheckHomeaddr == null ? null : relatedcheckHomeaddr.trim();
    }

    public String getRelatedcheckCompanyaddr() {
        return relatedcheckCompanyaddr;
    }

    public void setRelatedcheckCompanyaddr(String relatedcheckCompanyaddr) {
        this.relatedcheckCompanyaddr = relatedcheckCompanyaddr == null ? null : relatedcheckCompanyaddr.trim();
    }

    public String getRelatedcheckNearlyTelno() {
        return relatedcheckNearlyTelno;
    }

    public void setRelatedcheckNearlyTelno(String relatedcheckNearlyTelno) {
        this.relatedcheckNearlyTelno = relatedcheckNearlyTelno == null ? null : relatedcheckNearlyTelno.trim();
    }

    public String getRiskchannelCheck() {
        return riskchannelCheck;
    }

    public void setRiskchannelCheck(String riskchannelCheck) {
        this.riskchannelCheck = riskchannelCheck == null ? null : riskchannelCheck.trim();
    }

    public String getNosafePromoter() {
        return nosafePromoter;
    }

    public void setNosafePromoter(String nosafePromoter) {
        this.nosafePromoter = nosafePromoter == null ? null : nosafePromoter.trim();
    }

    public String getApplyinfoBuscheck() {
        return applyinfoBuscheck;
    }

    public void setApplyinfoBuscheck(String applyinfoBuscheck) {
        this.applyinfoBuscheck = applyinfoBuscheck == null ? null : applyinfoBuscheck.trim();
    }

    public String getIshaveInriskInfo() {
        return ishaveInriskInfo;
    }

    public void setIshaveInriskInfo(String ishaveInriskInfo) {
        this.ishaveInriskInfo = ishaveInriskInfo == null ? null : ishaveInriskInfo.trim();
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser == null ? null : crtUser.trim();
    }

	public String getIshaveInriskInfo_column() {
		return ishaveInriskInfo_column;
	}

	public void setIshaveInriskInfo_column(String ishaveInriskInfo_column) {
		this.ishaveInriskInfo_column = ishaveInriskInfo_column;
	}

	public String getCertifinoVailud_fk() {
		return certifinoVailud_fk;
	}

	public void setCertifinoVailud_fk(String certifinoVailud_fk) {
		this.certifinoVailud_fk = certifinoVailud_fk;
	}
	
}