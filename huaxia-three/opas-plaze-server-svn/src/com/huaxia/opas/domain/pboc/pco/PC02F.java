package com.huaxia.opas.domain.pboc.pco;

import java.io.Serializable;
/**
 * 循环额度下分账户汇总信息段
 * @author gaoh
 *
 */
public class PC02F implements Serializable {
	private static final long serialVersionUID = -5047731242426238898L;
	private String  PC02FS01;//管理机构数    [1..1]    -- 
	private String  PC02FS02;// 账户数    [1..1]    -- 
	private String  PC02FJ01;// 授信总额    [1..1]    -- 
	private String  PC02FJ02;// 余额    [1..1]    -- 
	private String  PC02FJ03;// 最近6个月平均应还款     [1..1]    -- 
	private String appId ;//申请件编号
	public String getPC02FS01() {
		return PC02FS01;
	}
	public void setPC02FS01(String pC02FS01) {
		PC02FS01 = pC02FS01;
	}
	public String getPC02FS02() {
		return PC02FS02;
	}
	public void setPC02FS02(String pC02FS02) {
		PC02FS02 = pC02FS02;
	}
	public String getPC02FJ01() {
		return PC02FJ01;
	}
	public void setPC02FJ01(String pC02FJ01) {
		PC02FJ01 = pC02FJ01;
	}
	public String getPC02FJ02() {
		return PC02FJ02;
	}
	public void setPC02FJ02(String pC02FJ02) {
		PC02FJ02 = pC02FJ02;
	}
	public String getPC02FJ03() {
		return PC02FJ03;
	}
	public void setPC02FJ03(String pC02FJ03) {
		PC02FJ03 = pC02FJ03;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

}