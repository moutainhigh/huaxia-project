package com.huaxia.opas.domain.pboc.prh;

import java.io.Serializable;

/**
 * 本次查询请求信息段
 * @author gaoh
 *
 */
public class PA01B implements Serializable {
	private static final long serialVersionUID = -5035340459096217244L;
	private String PA01BQ01 ;  //被查询者姓名
	private String PA01BD01 ;  //被查询者证件类型
	private String PA01BI01 ;  //被查询者证件号码
	private String PA01BI02 ;  //查询机构代码 
	private String PA01BD02 ;  //查询原因代码
	public String getPA01BQ01() {
		return PA01BQ01;
	}
	public void setPA01BQ01(String pA01BQ01) {
		PA01BQ01 = pA01BQ01;
	}
	public String getPA01BD01() {
		return PA01BD01;
	}
	public void setPA01BD01(String pA01BD01) {
		PA01BD01 = pA01BD01;
	}
	public String getPA01BI01() {
		return PA01BI01;
	}
	public void setPA01BI01(String pA01BI01) {
		PA01BI01 = pA01BI01;
	}
	public String getPA01BI02() {
		return PA01BI02;
	}
	public void setPA01BI02(String pA01BI02) {
		PA01BI02 = pA01BI02;
	}
	public String getPA01BD02() {
		return PA01BD02;
	}
	public void setPA01BD02(String pA01BD02) {
		PA01BD02 = pA01BD02;
	}
	
}
