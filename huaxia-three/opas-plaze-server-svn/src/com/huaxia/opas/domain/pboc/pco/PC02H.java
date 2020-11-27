package com.huaxia.opas.domain.pboc.pco;

import java.io.Serializable;
/**
 * 贷记卡账户汇总信息段
 * @author gaoh
 *
 */
public class PC02H implements Serializable {
	private static final long serialVersionUID = -5550908177678427957L;
	private String   PC02HS01;//发卡机构数    [1..1]  --
	private String   PC02HS02;//账户数    [1..1]   --
	private String   PC02HJ01;//授信总额    [1..1]  --
	private String   PC02HJ02;//单家行最高授信额   [1..1]   --
	private String   PC02HJ03;//单家行最低授信额   [1..1]  --
	private String   PC02HJ04;//已用额度   [1..1]    --
	private String   PC02HJ05;//最近6个月平均使用额度    [1..1]  --
	private String   appId;//申请件编号
	public String getPC02HS01() {
		return PC02HS01;
	}
	public void setPC02HS01(String pC02HS01) {
		PC02HS01 = pC02HS01;
	}
	public String getPC02HS02() {
		return PC02HS02;
	}
	public void setPC02HS02(String pC02HS02) {
		PC02HS02 = pC02HS02;
	}
	public String getPC02HJ01() {
		return PC02HJ01;
	}
	public void setPC02HJ01(String pC02HJ01) {
		PC02HJ01 = pC02HJ01;
	}
	public String getPC02HJ02() {
		return PC02HJ02;
	}
	public void setPC02HJ02(String pC02HJ02) {
		PC02HJ02 = pC02HJ02;
	}
	public String getPC02HJ03() {
		return PC02HJ03;
	}
	public void setPC02HJ03(String pC02HJ03) {
		PC02HJ03 = pC02HJ03;
	}
	public String getPC02HJ04() {
		return PC02HJ04;
	}
	public void setPC02HJ04(String pC02HJ04) {
		PC02HJ04 = pC02HJ04;
	}
	public String getPC02HJ05() {
		return PC02HJ05;
	}
	public void setPC02HJ05(String pC02HJ05) {
		PC02HJ05 = pC02HJ05;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
}