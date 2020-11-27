package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 芝麻信用 & 行业关注名单2.0版
 * 
 * @author zhiguo.li
 *
 */
public class ZMXYWatchList2 implements Serializable {

	private static final long serialVersionUID = -4500369708664331684L;

	// 申请件编号
	private String appId;

	// 创建日期
	private String crtTime;

	// 创建人
	private String crtUser;

	// 最后更新日期
	private String lstUpdTime;

	// 最后更新人
	private String lstUpdUser;

	// 是否匹配（true-命中、false-未命中）
	private String isMatched;

	// 芝麻信用业务号
	private String bizNo;

	// 行业关注名单信息列表
	private WatchList2Detail watchList2Detail;

	// [非持久化字段] 报文响应结果
	private Boolean responseResult;

	// [非持久化字段] 错误代码
	private String errorCode;

	// [非持久化字段] 错误描述
	private String errorDesc;

	public Boolean getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(Boolean responseResult) {
		this.responseResult = responseResult;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getIsMatched() {
		return isMatched;
	}

	public void setIsMatched(String isMatched) {
		this.isMatched = isMatched;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
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

	public WatchList2Detail getWatchList2Detail() {
		return watchList2Detail;
	}

	public void setWatchList2Detail(WatchList2Detail watchList2Detail) {
		this.watchList2Detail = watchList2Detail;
	}

	public class WatchList2Detail {

		// 风险信息行业编码
		private String bizCode;

		// 保留字段（默认：0）
		private String level;

		// 风险类型
		private String type;

		// 风险编码
		private String code;

		// 数据刷新时间
		private String refreshTime;

		// 结清状态
		private String settlement;

		// 异议处理流程状态
		private String status;

		// 核查完成异议声明
		private String statement;

		// 扩展信息（复合字段: 其值为"扩展信息详情", 包含多条信息）
		private String extendInfo;

		public String getBizCode() {
			return bizCode;
		}

		public void setBizCode(String bizCode) {
			this.bizCode = bizCode;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getRefreshTime() {
			return refreshTime;
		}

		public void setRefreshTime(String refreshTime) {
			this.refreshTime = refreshTime;
		}

		public String getSettlement() {
			return settlement;
		}

		public void setSettlement(String settlement) {
			this.settlement = settlement;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getStatement() {
			return statement;
		}

		public void setStatement(String statement) {
			this.statement = statement;
		}

		public String getExtendInfo() {
			return extendInfo;
		}

		public void setExtendInfo(String extendInfo) {
			this.extendInfo = extendInfo;
		}

	}

}
