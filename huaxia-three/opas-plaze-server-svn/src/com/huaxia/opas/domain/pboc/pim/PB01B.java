package com.huaxia.opas.domain.pboc.pim;

import java.io.Serializable;
import java.util.List;
/**
 * 手机号码信息段
 * @author gaoh
 *
 */
public class PB01B implements Serializable {

	private static final long serialVersionUID = -1132406189218600796L;
	private PB01Bdata PB01Bdata;//手机号码信息段数据
	private List<PB01BH> PB01BHList;//手机号码信息
	public PB01Bdata getPB01Bdata() {
		return PB01Bdata;
	}
	public void setPB01Bdata(PB01Bdata pB01Bdata) {
		PB01Bdata = pB01Bdata;
	}
	public List<PB01BH> getPB01BHList() {
		return PB01BHList;
	}
	public void setPB01BHList(List<PB01BH> pB01BHList) {
		PB01BHList = pB01BHList;
	}
	
}