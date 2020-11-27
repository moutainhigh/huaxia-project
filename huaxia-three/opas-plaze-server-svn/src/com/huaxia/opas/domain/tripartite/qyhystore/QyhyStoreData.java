package com.huaxia.opas.domain.tripartite.qyhystore;

import java.io.Serializable;
import java.util.Date;
/**
 * 企业信息库 企业行业信息 成功失败 参数存储表
 * @author gaoh
 */
public class QyhyStoreData implements Serializable{
	private static final long serialVersionUID = -6376366378076735397L;

	private String uuid;

	private String enterprise;//单位名称

    private Integer code;//返回的结果吗 用来判断查询成功或是失败

    private String msg;//返回的信息
    
    private String relateUuid;//关联的uuid
    
    private Date crtTime;//创建日期

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise == null ? null : enterprise.trim();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public String getRelateUuid() {
        return relateUuid;
    }

    public void setRelateUuid(String relateUuid) {
        this.relateUuid = relateUuid == null ? null : relateUuid.trim();
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }
}