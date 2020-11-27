package com.huaxia.opas.domain.report;

import java.io.Serializable;
/**
 * 进件情况统计报表
 * @author wenyh 
 */
public class WsPlatformDataStatistics implements Serializable{
	private static final long serialVersionUID = -7810002326853250840L;
	//进件日期
	private String crtDate;
	//进件数量
	private String appcnt;
	
	public String getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}

	public String getAppcnt() {
		return appcnt;
	}

	public void setAppcnt(String appcnt) {
		this.appcnt = appcnt;
	}


}
