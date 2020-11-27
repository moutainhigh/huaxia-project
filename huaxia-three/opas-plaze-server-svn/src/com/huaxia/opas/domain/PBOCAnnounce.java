package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 人行 & 声明信息
 * 
 * @author zhiguo.li
 *
 */
public class PBOCAnnounce implements Serializable {

	private static final long serialVersionUID = -3807969427489004442L;
	
	// 本人声明
	private AnnounceInfo announceInfo;
	
	// 异议标注
	private DissentInfo dissentInfo;

	public AnnounceInfo getAnnounceInfo() {
		return announceInfo;
	}

	public void setAnnounceInfo(AnnounceInfo announceInfo) {
		this.announceInfo = announceInfo;
	}

	public DissentInfo getDissentInfo() {
		return dissentInfo;
	}

	public void setDissentInfo(DissentInfo dissentInfo) {
		this.dissentInfo = dissentInfo;
	}

	public class AnnounceInfo {
		
		// 声明内容
		private String content;
		
		// 添加日期
		private String getTime;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getGetTime() {
			return getTime;
		}

		public void setGetTime(String getTime) {
			this.getTime = getTime;
		}
		
	}
	
	public class DissentInfo {
		
		// 标注内容
		private String content;
		
		// 添加日期
		private String getTime;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getGetTime() {
			return getTime;
		}

		public void setGetTime(String getTime) {
			this.getTime = getTime;
		}
	}
	
}
