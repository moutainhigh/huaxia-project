package com.huaxia.opas.domain.collect;

import java.io.Serializable;

/**
 * 录入
 * 
 * @author xiebinxu 2017年3月3日
 */
public class InfoCollect implements Serializable {

	private static final long serialVersionUID = 8247305692571856861L;
	
	private String appId;
	private String educationDegree;// 学历类型
	private String educationApproach;// 学习形式
	

	public String getEducationDegree() {
		return educationDegree;
	}

	public void setEducationDegree(String educationDegree) {
		this.educationDegree = educationDegree;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getEducationApproach() {
		return educationApproach;
	}

	public void setEducationApproach(String educationApproach) {
		this.educationApproach = educationApproach;
	}

	

}