package com.huaxia.plaze.ui.numread.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("NumReadBatchDetail")
public class NumReadBatchDetail {
	
	//记录编号
	private String uuid;
	//创建时间
	private String crtTime;
	//创建用户
	private String crtUser;
	//交易编号
	private String trnId;
	//文件编号:入库时依次给予,用于文件的排序   关联导入文件 
	private String fileId;
	//姓名
	private String name;
	//证件号码
	private String certNo;
	//证件类型
	private String certType;
	//查询原因
	private String queryReason;
	//查询状态:0-待查   1-已发送查询    2-写入请求文件失败    3-返回结果成功
	private String status;
	//产品日期
	private String productDate;
	//批次号
	private String batchNo;
	
	
	public String getQueryReason() {
		return queryReason;
	}
	public void setQueryReason(String queryReason) {
		this.queryReason = queryReason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
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
	public String getTrnId() {
		return trnId;
	}
	public void setTrnId(String trnId) {
		this.trnId = trnId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	

}
