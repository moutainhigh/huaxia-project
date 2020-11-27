package com.huaxia.opas.domain.tripartite;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * METADATA 元数据来源
 * @author gaoh
 */
public class QyhyInfoMetaData implements Serializable{
	
	private static final long serialVersionUID = -2558821342504791421L;

	private String uuid;

    private String appId;

    private String source;//匹配标志-工商(1：精确匹配2：模糊匹配3：无法匹配)

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

    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }
}