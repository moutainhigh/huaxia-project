package com.huaxia.opas.domain.riskcheck;

import java.io.Serializable;
import java.util.Date;

//内部交叉结果表映射 OPAS_KEYFILED_RESULTINFO
public class KeyfiledResultInfo implements Serializable{

	private static final long serialVersionUID = -7784790317717007052L;

	private String autoId;

    private String appId;

    private String yzBmd;//优质单位准入白名单,1-命中,2-姓名证件号单名命中,-1-其他情况
    private Date crtDate;
    
    private String stakeholderType;//关系人类型,0-非关系人,1-关联自然人,2-员工,4-申请员工
    
    private String tjMatch;//推荐人名单结果,1-欺诈,2-信用,1|2两个都命中
    
	public String getStakeholderType() {
		return stakeholderType;
	}
	public void setStakeholderType(String stakeholderType) {
		this.stakeholderType = stakeholderType;
	}
	public String getTjMatch() {
		return tjMatch;
	}
	public void setTjMatch(String tjMatch) {
		this.tjMatch = tjMatch;
	}
	public KeyfiledResultInfo() {
		super();
	}
	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getYzBmd() {
		return yzBmd;
	}
	public void setYzBmd(String yzBmd) {
		this.yzBmd = yzBmd;
	}
	public Date getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

}