package com.huaxia.plaze.ui.tongdun.domain;

import org.apache.ibatis.type.Alias;
/**
 * 单条查询的任务表
 * @author liuwei
 *
 */
@Alias("MulBorTrnSingle")
public class MulBorSingle {
	
	 	// uuid
		private String uuid;
		// 创建时间
		private String crtTime;
		// 创建者
		private String crtUser;
		// 交易id
		private String trnId;
		//证件类型
		private String certType;
		//证件号码
		private String certNo;
		//姓名
		private String name;
		//手机号码
		private String mobile;
		//查询原因
		private String queryReason;
		//文件编号
		private String fileId;
		//查询状态
		private String queryStatus;
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
		public String getCertType() {
			return certType;
		}
		public void setCertType(String certType) {
			this.certType = certType;
		}
		public String getCertNo() {
			return certNo;
		}
		public void setCertNo(String certNo) {
			this.certNo = certNo;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getQueryReason() {
			return queryReason;
		}
		public void setQueryReason(String queryReason) {
			this.queryReason = queryReason;
		}
		public String getFileId() {
			return fileId;
		}
		public void setFileId(String fileId) {
			this.fileId = fileId;
		}
		public String getQueryStatus() {
			return queryStatus;
		}
		public void setQueryStatus(String queryStatus) {
			this.queryStatus = queryStatus;
		}
		@Override
		public String toString() {
			return "MulBorTrnSingle [uuid=" + uuid + ", crtTime=" + crtTime + ", crtUser=" + crtUser + ", trnId="
					+ trnId + ", certType=" + certType + ", certNo=" + certNo + ", name=" + name + ", mobile=" + mobile
					+ ", queryReason=" + queryReason + ", fileId=" + fileId + ", queryStatus=" + queryStatus + "]";
		}
		
}
