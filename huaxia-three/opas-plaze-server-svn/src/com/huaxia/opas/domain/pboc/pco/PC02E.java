package com.huaxia.opas.domain.pboc.pco;

import java.io.Serializable;

/**
 * 非循环贷账户汇总信息段
 * @author gaoh
 *
 */
public class PC02E implements Serializable {

	private static final long serialVersionUID = 8680981864008781429L;
	private String PC02ES01;//  管理机构数    [1..1] -- 
	private String PC02ES02;//  账户数   [1..1]  -- 
	private String PC02EJ01;//  授信总额    [1..1]  -- 
	private String PC02EJ02;// 余额   [1..1]  -- 
	private String PC02EJ03;//  最近6个月平均应还款    [1..1]   -- 
	private String appId;//申请件编号
	public String getPC02ES01() {
		return PC02ES01;
	}
	public void setPC02ES01(String pC02ES01) {
		PC02ES01 = pC02ES01;
	}
	public String getPC02ES02() {
		return PC02ES02;
	}
	public void setPC02ES02(String pC02ES02) {
		PC02ES02 = pC02ES02;
	}
	public String getPC02EJ01() {
		return PC02EJ01;
	}
	public void setPC02EJ01(String pC02EJ01) {
		PC02EJ01 = pC02EJ01;
	}
	public String getPC02EJ02() {
		return PC02EJ02;
	}
	public void setPC02EJ02(String pC02EJ02) {
		PC02EJ02 = pC02EJ02;
	}
	public String getPC02EJ03() {
		return PC02EJ03;
	}
	public void setPC02EJ03(String pC02EJ03) {
		PC02EJ03 = pC02EJ03;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

}