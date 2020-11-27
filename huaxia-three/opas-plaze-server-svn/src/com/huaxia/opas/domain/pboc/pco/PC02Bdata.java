package com.huaxia.opas.domain.pboc.pco;

import java.io.Serializable;

/**
 * 被追偿汇总信息段数据
 * @author gaoh
 *
 */
public class PC02Bdata implements Serializable {
	private static final long serialVersionUID = -9153538045971508094L;
	private String  PC02BS01;// 账户数合计   [1..1]    --
	private String  PC02BJ01;//余额合计    [1..1]     --  
	private String  PC02BS02;//  业务类型数量   [1..1]     -- 
	private String  appId;//申请件编号
	public String getPC02BS01() {
		return PC02BS01;
	}
	public void setPC02BS01(String pC02BS01) {
		PC02BS01 = pC02BS01;
	}
	public String getPC02BJ01() {
		return PC02BJ01;
	}
	public void setPC02BJ01(String pC02BJ01) {
		PC02BJ01 = pC02BJ01;
	}
	public String getPC02BS02() {
		return PC02BS02;
	}
	public void setPC02BS02(String pC02BS02) {
		PC02BS02 = pC02BS02;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

}