package com.huaxia.cams.modules.police.domain;

import java.io.Serializable;

public class PoliceXpInfo implements Serializable{

	private static final long serialVersionUID = -7766104615472910411L;
	
	// 申请件编号
		private String appId;
		
		// 创建时间
		private String crtTime;

		// 创建人
		private String crtUser;

		// 最后操作时间
		private String lstUpdTime;

		// 最后更新人
		private String lstUpdUser;
		
		// 公民身份号码
		private String gmsfhm;
		
		// 公民身份号码核查结果
		private String resultGmsfhm;
		
		// 姓名
		private String xm;
		
		// 姓名核查结果
		private String resultXm;
		
		// 照片
		private String xp;
		
		//系统分析结果
		private String resultFx;
		
		//系统比对分数
		private String resultFs;
		
		// 错误代码
		private String errorCode;
		
		// 错误描述/缺失必录项描述
		private String errorMessage;
		
		// 缺失必录项字段名称
		private String errorMessageCol;

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
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

		public String getLstUpdTime() {
			return lstUpdTime;
		}

		public void setLstUpdTime(String lstUpdTime) {
			this.lstUpdTime = lstUpdTime;
		}

		public String getLstUpdUser() {
			return lstUpdUser;
		}

		public void setLstUpdUser(String lstUpdUser) {
			this.lstUpdUser = lstUpdUser;
		}

		public String getGmsfhm() {
			return gmsfhm;
		}

		public void setGmsfhm(String gmsfhm) {
			this.gmsfhm = gmsfhm;
		}

		public String getResultGmsfhm() {
			return resultGmsfhm;
		}

		public void setResultGmsfhm(String resultGmsfhm) {
			this.resultGmsfhm = resultGmsfhm;
		}

		public String getXm() {
			return xm;
		}

		public void setXm(String xm) {
			this.xm = xm;
		}

		public String getResultXm() {
			return resultXm;
		}

		public void setResultXm(String resultXm) {
			this.resultXm = resultXm;
		}

		public String getXp() {
			return xp;
		}

		public void setXp(String xp) {
			this.xp = xp;
		}

		public String getResultFx() {
			return resultFx;
		}

		public void setResultFx(String resultFx) {
			this.resultFx = resultFx;
		}

		public String getResultFs() {
			return resultFs;
		}

		public void setResultFs(String resultFs) {
			this.resultFs = resultFs;
		}

		public String getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public String getErrorMessageCol() {
			return errorMessageCol;
		}

		public void setErrorMessageCol(String errorMessageCol) {
			this.errorMessageCol = errorMessageCol;
		}

}
