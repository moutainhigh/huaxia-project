package com.huaxia.opas.domain.pboc.pco;

import java.io.Serializable;
import java.util.List;
/**
 * 被追偿汇总信息段
 * @author gaoh
 *
 */
public class PC02B implements Serializable {
	
	private static final long serialVersionUID = 1469864922200575126L;

	private PC02Bdata PC02Bdata;//被追偿汇总信息段数据
	
	private List<PC02BH> PC02BHList;//被追偿汇总信息

	public PC02Bdata getPC02Bdata() {
		return PC02Bdata;
	}

	public void setPC02Bdata(PC02Bdata pC02Bdata) {
		PC02Bdata = pC02Bdata;
	}

	public List<PC02BH> getPC02BHList() {
		return PC02BHList;
	}

	public void setPC02BHList(List<PC02BH> pC02BHList) {
		PC02BHList = pC02BHList;
	}
	
}