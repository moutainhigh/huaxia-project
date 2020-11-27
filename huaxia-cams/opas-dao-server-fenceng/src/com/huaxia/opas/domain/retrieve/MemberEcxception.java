package com.huaxia.opas.domain.retrieve;

import java.io.Serializable;
import java.math.BigDecimal;

public class MemberEcxception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -569609742962517062L;
	private String operateCode;//员工姓名
	private String operateName;//员工姓名			
	private String sysDate;//异常日期
	private int operateTime;//工作连续性异常统计
	private BigDecimal sumTime;//工作连续性异常统计和 用于数据库字段转换
	public String getOperateCode() {
		return operateCode;
	}
	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public int getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(int operateTime) {
		this.operateTime = operateTime;
	}
	public BigDecimal getSumTime() {
		return sumTime;
	}
	public void setSumTime(BigDecimal sumTime) {
		this.sumTime = sumTime;
	}
	
}
