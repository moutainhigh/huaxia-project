package com.huaxia.opas.domain.report;

import java.io.Serializable;

public class EntryStatistics implements Serializable{

	/**
	 * 进件情况统计报表
	 */
	private static final long serialVersionUID = -7810002326853250840L;

	private String appId;
	
	private String crtDate;
	
	//进件数量
	private String appcnt;
	
	//核准 PASS
	private String pass;
	
	//拒绝 REFUSE
	private String refuse;
	
	//未完成 NOFINISH
	private String noFinish;
	
	//取消
	private String cancel;
	
	//审批量 
	private String approveSum;
	
	//核准率% 
	private String passRate;
	
	//拒绝率% 
	private String refusuRate;
	
	//待处理率% 
	private String waitDealRate;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}

	public String getAppcnt() {
		return appcnt;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRefuse() {
		return refuse;
	}

	public void setRefuse(String refuse) {
		this.refuse = refuse;
	}

	public String getNoFinish() {
		return noFinish;
	}

	public void setNoFinish(String noFinish) {
		this.noFinish = noFinish;
	}

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

	public String getApproveSum() {
		return approveSum;
	}

	public void setApproveSum(String approveSum) {
		this.approveSum = approveSum;
	}

	public String getPassRate() {
		return passRate;
	}

	public void setPassRate(String passRate) {
		this.passRate = passRate;
	}

	public String getRefusuRate() {
		return refusuRate;
	}

	public void setRefusuRate(String refusuRate) {
		this.refusuRate = refusuRate;
	}

	public String getWaitDealRate() {
		return waitDealRate;
	}

	public void setWaitDealRate(String waitDealRate) {
		this.waitDealRate = waitDealRate;
	}

	public void setAppcnt(String appcnt) {
		this.appcnt = appcnt;
	}

	

}
