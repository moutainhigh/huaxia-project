package com.huaxia.opas.domain.pboc.ppo;

import java.io.Serializable;
/**
 * 公共信息概要信息
 * @author gaoh
 *
 */
public class PC040H implements Serializable {

	private static final long serialVersionUID = -5787434228976053128L;
	private String PC040D01;//公共信息类型  -- [1..1]
	private String PC040S02;//记录数  --  [1..1]
	private String PC040J01;//涉及金额  --  [1..1] 
	private String appId;//申请件编号
	public String getPC040D01() {
		return PC040D01;
	}
	public void setPC040D01(String pC040D01) {
		PC040D01 = pC040D01;
	}
	public String getPC040S02() {
		return PC040S02;
	}
	public void setPC040S02(String pC040S02) {
		PC040S02 = pC040S02;
	}
	public String getPC040J01() {
		return PC040J01;
	}
	public void setPC040J01(String pC040J01) {
		PC040J01 = pC040J01;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

}