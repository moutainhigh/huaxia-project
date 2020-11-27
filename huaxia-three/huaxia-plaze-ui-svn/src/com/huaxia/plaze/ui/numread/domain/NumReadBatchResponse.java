package com.huaxia.plaze.ui.numread.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
@Alias("NumReadBatchResponse")
public class NumReadBatchResponse {

	private String uuId;				//uuid
	private Date   ctrTime;			//创建时间
	private String crtUser;			//创建用户  默认'system'
	private String requestId;			//信息记录号
	private String resultCode;			//查询结果代码
	private String resultDesc;			//查询结果描述
	private String originateOrgCode;	//发起机构代码
	private String originateUserCode;	//发起用户代码
	private String name;				//信息主体姓名
	private String idType;				//信息主体证件类型
	private String idNum;				//信息主体证件号码
	private String queryReason;			//查询原因
	private String serviceCode;			//服务代码
	private String productDate;			//产品日期
	private String resultType;			//查询结果类型
	private String score;				//数字解读值
	private String factorNum;			//影响因素个数   个数不确定,可能有1到5个,可能没有
	private String factor1;				//影响因素1
	private String factor2;				//影响因素2
	private String factor3;				//影响因素3
	private String factor4;				//影响因素4
	private String factor5;				//影响因素5
	private String position;			//相对位置
	private String scoreDate;			//计算日期
	private String trnId;				//业务主键(与请求表一致)
	public String getUuId() {
		return uuId;
	}
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
	public Date getCtrTime() {
		return ctrTime;
	}
	public void setCtrTime(Date ctrTime) {
		this.ctrTime = ctrTime;
	}
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	public String getOriginateOrgCode() {
		return originateOrgCode;
	}
	public void setOriginateOrgCode(String originateOrgCode) {
		this.originateOrgCode = originateOrgCode;
	}
	public String getOriginateUserCode() {
		return originateUserCode;
	}
	public void setOriginateUserCode(String originateUserCode) {
		this.originateUserCode = originateUserCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getQueryReason() {
		return queryReason;
	}
	public void setQueryReason(String queryReason) {
		this.queryReason = queryReason;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getFactorNum() {
		return factorNum;
	}
	public void setFactorNum(String factorNum) {
		this.factorNum = factorNum;
	}
	public String getFactor1() {
		return factor1;
	}
	public void setFactor1(String factor1) {
		this.factor1 = factor1;
	}
	public String getFactor2() {
		return factor2;
	}
	public void setFactor2(String factor2) {
		this.factor2 = factor2;
	}
	public String getFactor3() {
		return factor3;
	}
	public void setFactor3(String factor3) {
		this.factor3 = factor3;
	}
	public String getFactor4() {
		return factor4;
	}
	public void setFactor4(String factor4) {
		this.factor4 = factor4;
	}
	public String getFactor5() {
		return factor5;
	}
	public void setFactor5(String factor5) {
		this.factor5 = factor5;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getScoreDate() {
		return scoreDate;
	}
	public void setScoreDate(String scoreDate) {
		this.scoreDate = scoreDate;
	}
	public String getTrnId() {
		return trnId;
	}
	public void setTrnId(String trnId) {
		this.trnId = trnId;
	}

	
	
	
}
