package com.huaxia.opas.domain.tripartite.qyhystore;

import java.io.Serializable;
import java.util.Date;
/**
 * 企业信息库 METADATA 元数据来源
 * @author gaoh
 */
public class QyhyStoreMetaData implements Serializable{
	
	private static final long serialVersionUID = -7589747866264189354L;

	private String uuid;

	private String relateUuid;//关联的uuid

    private String source;//匹配标志-工商(1：精确匹配2：模糊匹配3：无法匹配)

    private Date crtTime;//创建日期

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