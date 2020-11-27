package com.huaxia.opas.domain.pboc.pco;

import java.io.Serializable;
/**
 * 准贷记卡账户汇总信息段 
 * @author gaoh
 *
 */
public class PC02I implements Serializable {
	
	private static final long serialVersionUID = -1833520227550975903L;
	private String   PC02IS01;//发卡机构数    [1..1]  -- 
	private String   PC02IS02;//账户数    [1..1]   -- 
	private String   PC02IJ01;//授信总额    [1..1]   --  
	private String   PC02IJ02;//单家行最高授信额    [1..1]    --  
	private String   PC02IJ03;//单家行最低授信额    [1..1]    -- 
	private String   PC02IJ04;//透支余额   [1..1]   --  
	private String   PC02IJ05;//最近6个月平均透支余额   [1..1]  -- 
	private String   appId;//申请件编号
	public String getPC02IS01() {
		return PC02IS01;
	}
	public void setPC02IS01(String pC02IS01) {
		PC02IS01 = pC02IS01;
	}
	public String getPC02IS02() {
		return PC02IS02;
	}
	public void setPC02IS02(String pC02IS02) {
		PC02IS02 = pC02IS02;
	}
	public String getPC02IJ01() {
		return PC02IJ01;
	}
	public void setPC02IJ01(String pC02IJ01) {
		PC02IJ01 = pC02IJ01;
	}
	public String getPC02IJ02() {
		return PC02IJ02;
	}
	public void setPC02IJ02(String pC02IJ02) {
		PC02IJ02 = pC02IJ02;
	}
	public String getPC02IJ03() {
		return PC02IJ03;
	}
	public void setPC02IJ03(String pC02IJ03) {
		PC02IJ03 = pC02IJ03;
	}
	public String getPC02IJ04() {
		return PC02IJ04;
	}
	public void setPC02IJ04(String pC02IJ04) {
		PC02IJ04 = pC02IJ04;
	}
	public String getPC02IJ05() {
		return PC02IJ05;
	}
	public void setPC02IJ05(String pC02IJ05) {
		PC02IJ05 = pC02IJ05;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

}