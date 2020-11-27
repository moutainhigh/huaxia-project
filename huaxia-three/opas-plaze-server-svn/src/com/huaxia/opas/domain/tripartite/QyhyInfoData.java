package com.huaxia.opas.domain.tripartite;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 企业行业信息 成功失败 参数存储表
 * @author gaoh
 */
public class QyhyInfoData implements Serializable{
	private static final long serialVersionUID = 3543148947960938372L;

	private String uuid;

    private String appId;

    private Integer code;//返回的结果吗 用来判断查询成功或是失败

    private String msg;//返回的信息

    private Date crtTime;//创建日期

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

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }
}