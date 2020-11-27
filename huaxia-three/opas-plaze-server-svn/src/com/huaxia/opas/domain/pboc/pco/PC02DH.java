package com.huaxia.opas.domain.pboc.pco;

import java.io.Serializable;
/**
 * 逾期（透支）汇总信息
 * @author gaoh
 *
 */
public class PC02DH implements Serializable {
	private static final long serialVersionUID = -1365017957666696583L;
	private String PC02DD01;//  账户类型   [1..1]  --  
	private String PC02DS02;//账户数   [1..1]   -- 
	private String PC02DS03;//月份数   [1..1]  -- 
	private String PC02DJ01;// 单月最高逾期（透支）总额    [1..1]  -- 
	private String PC02DS04;// 最长逾期（透支）月数    [1..1] --
	private String appId ;//申请件编号
	public String getPC02DD01() {
		return PC02DD01;
	}
	public void setPC02DD01(String pC02DD01) {
		PC02DD01 = pC02DD01;
	}
	public String getPC02DS02() {
		return PC02DS02;
	}
	public void setPC02DS02(String pC02DS02) {
		PC02DS02 = pC02DS02;
	}
	public String getPC02DS03() {
		return PC02DS03;
	}
	public void setPC02DS03(String pC02DS03) {
		PC02DS03 = pC02DS03;
	}
	public String getPC02DJ01() {
		return PC02DJ01;
	}
	public void setPC02DJ01(String pC02DJ01) {
		PC02DJ01 = pC02DJ01;
	}
	
	public String getPC02DS04() {
		return PC02DS04;
	}
	public void setPC02DS04(String pC02DS04) {
		PC02DS04 = pC02DS04;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

}