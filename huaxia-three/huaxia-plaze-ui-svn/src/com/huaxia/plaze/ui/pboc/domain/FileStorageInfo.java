package com.huaxia.plaze.ui.pboc.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("FileStorageInfo")
public class FileStorageInfo {

	// 记录编号
	private String uuid;
	// 创建时间
	private String crtTime;
	// 创建用户
	private String crtUser;
	// 资源编号
	private String sourceId;
	// 资源名称
	private String sourceName;
	// 资源类型
	private String sourceType;
	// 资源路径
	private String sourcePath;
	// 资源令牌
	private String sourceToken;

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCrtTime() {
		return crtTime;
	}
	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	public String getSourceToken() {
		return sourceToken;
	}
	public void setSourceToken(String sourceToken) {
		this.sourceToken = sourceToken;
	}
	

}
