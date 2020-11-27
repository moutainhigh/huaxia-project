package com.huaxia.plaze.ui.tongdun.domain;
/**
 * 批次详情表
 * @author liuwei
 *
 */
public class MulBorBatchFile {
	// uuid
	private String uuid;
	// 创建时间
	private String crtTime;
	// 创建者
	private String crtUser;
	//批次编号
	private String batchId;
	//批次文件编号
	private String batchFileId;
	//批次文件名称
	private String batchFileName;
	//批次文件总条数
	private Integer batchFileRecordSize;
	//员工ID
	private String staffId;
	
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
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getBatchFileId() {
		return batchFileId;
	}
	public void setBatchFileId(String batchFileId) {
		this.batchFileId = batchFileId;
	}
	public String getBatchFileName() {
		return batchFileName;
	}
	public void setBatchFileName(String batchFileName) {
		this.batchFileName = batchFileName;
	}
	public Integer getBatchFileRecordSize() {
		return batchFileRecordSize;
	}
	public void setBatchFileRecordSize(Integer batchFileRecordSize) {
		this.batchFileRecordSize = batchFileRecordSize;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	@Override
	public String toString() {
		return "MulBorTrnBatchFile [uuid=" + uuid + ", crtTime=" + crtTime + ", crtUser=" + crtUser + ", batchId="
				+ batchId + ", batchFileId=" + batchFileId + ", batchFileName=" + batchFileName
				+ ", batchFileRecordSize=" + batchFileRecordSize + ", staffId=" + staffId + "]";
	}
}
