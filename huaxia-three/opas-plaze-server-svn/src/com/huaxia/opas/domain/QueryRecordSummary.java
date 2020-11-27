package com.huaxia.opas.domain;

import java.io.Serializable;

/**
 * 第三方 & 人行 & 查询记录 & 查询汇总
 * 
 * @author zhiguo.li
 *
 */
public class QueryRecordSummary implements Serializable {

	private static final long serialVersionUID = 5002585142288018075L;

	// 申请件编号
	private String appId;

	// 最近一个月内的查询机构数_贷后管理
	private String queryOrg1MSum1;

	// 最近一个月内的查询机构数_贷款审批
	private String queryOrg1MSum2;

	// 最近一个月内的查询机构数_信用卡审批
	private String queryOrg1MSum3;

	// 最近一个月内的查询机构数_本人查询
	private String queryOrg1MSum4;

	// 最近一个月内的查询机构数_担保资格审查
	private String queryOrg1MSum5;

	// 最近一个月内的查询机构数_特约商户实名审查
	private String queryOrg1MSum6;

	
	// 最近一个月内的查询次数_贷后管理
	private String queryRec1MSum1;

	// 最近一个月内的查询次数_贷款审批
	private String queryRec1MSum2;

	// 最近一个月内的查询次数_信用卡审批
	private String queryRec1MSum3;

	// 最近一个月内的查询次数_本人查询
	private String queryRec1MSum4;

	// 最近一个月内的查询次数_担保资格审查
	private String queryRec1MSum5;
	
	// 最近一个月内的查询次数_特约商户实名审查
	private String queryRec1MSum6;
	

	// 最近两年内的查询次数_贷后管理
	private String queryRec2YSum1;

	// 最近两年内的查询次数_贷款审批
	private String queryRec2YSum2;

	// 最近两年内的查询次数_信用卡审批
	private String queryRec2YSum3;

	// 最近两年内的查询次数_本人查询
	private String queryRec2YSum4;

	// 最近两年内的查询次数_担保资格审查
	private String queryRec2YSum5;

	// 最近两年内的查询次数_特约商户实名审查
	private String queryRec2YSum6;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getQueryOrg1MSum1() {
		return queryOrg1MSum1;
	}

	public void setQueryOrg1MSum1(String queryOrg1MSum1) {
		this.queryOrg1MSum1 = queryOrg1MSum1;
	}

	public String getQueryOrg1MSum2() {
		return queryOrg1MSum2;
	}

	public void setQueryOrg1MSum2(String queryOrg1MSum2) {
		this.queryOrg1MSum2 = queryOrg1MSum2;
	}

	public String getQueryOrg1MSum3() {
		return queryOrg1MSum3;
	}

	public void setQueryOrg1MSum3(String queryOrg1MSum3) {
		this.queryOrg1MSum3 = queryOrg1MSum3;
	}

	public String getQueryOrg1MSum4() {
		return queryOrg1MSum4;
	}

	public void setQueryOrg1MSum4(String queryOrg1MSum4) {
		this.queryOrg1MSum4 = queryOrg1MSum4;
	}

	public String getQueryOrg1MSum5() {
		return queryOrg1MSum5;
	}

	public void setQueryOrg1MSum5(String queryOrg1MSum5) {
		this.queryOrg1MSum5 = queryOrg1MSum5;
	}

	public String getQueryOrg1MSum6() {
		return queryOrg1MSum6;
	}

	public void setQueryOrg1MSum6(String queryOrg1MSum6) {
		this.queryOrg1MSum6 = queryOrg1MSum6;
	}

	public String getQueryRec1MSum1() {
		return queryRec1MSum1;
	}

	public void setQueryRec1MSum1(String queryRec1MSum1) {
		this.queryRec1MSum1 = queryRec1MSum1;
	}

	public String getQueryRec1MSum2() {
		return queryRec1MSum2;
	}

	public void setQueryRec1MSum2(String queryRec1MSum2) {
		this.queryRec1MSum2 = queryRec1MSum2;
	}

	public String getQueryRec1MSum3() {
		return queryRec1MSum3;
	}

	public void setQueryRec1MSum3(String queryRec1MSum3) {
		this.queryRec1MSum3 = queryRec1MSum3;
	}

	public String getQueryRec1MSum4() {
		return queryRec1MSum4;
	}

	public void setQueryRec1MSum4(String queryRec1MSum4) {
		this.queryRec1MSum4 = queryRec1MSum4;
	}

	public String getQueryRec1MSum5() {
		return queryRec1MSum5;
	}

	public void setQueryRec1MSum5(String queryRec1MSum5) {
		this.queryRec1MSum5 = queryRec1MSum5;
	}

	public String getQueryRec1MSum6() {
		return queryRec1MSum6;
	}

	public void setQueryRec1MSum6(String queryRec1MSum6) {
		this.queryRec1MSum6 = queryRec1MSum6;
	}

	public String getQueryRec2YSum1() {
		return queryRec2YSum1;
	}

	public void setQueryRec2YSum1(String queryRec2YSum1) {
		this.queryRec2YSum1 = queryRec2YSum1;
	}

	public String getQueryRec2YSum2() {
		return queryRec2YSum2;
	}

	public void setQueryRec2YSum2(String queryRec2YSum2) {
		this.queryRec2YSum2 = queryRec2YSum2;
	}

	public String getQueryRec2YSum3() {
		return queryRec2YSum3;
	}

	public void setQueryRec2YSum3(String queryRec2YSum3) {
		this.queryRec2YSum3 = queryRec2YSum3;
	}

	public String getQueryRec2YSum4() {
		return queryRec2YSum4;
	}

	public void setQueryRec2YSum4(String queryRec2YSum4) {
		this.queryRec2YSum4 = queryRec2YSum4;
	}

	public String getQueryRec2YSum5() {
		return queryRec2YSum5;
	}

	public void setQueryRec2YSum5(String queryRec2YSum5) {
		this.queryRec2YSum5 = queryRec2YSum5;
	}

	public String getQueryRec2YSum6() {
		return queryRec2YSum6;
	}

	public void setQueryRec2YSum6(String queryRec2YSum6) {
		this.queryRec2YSum6 = queryRec2YSum6;
	}

}
