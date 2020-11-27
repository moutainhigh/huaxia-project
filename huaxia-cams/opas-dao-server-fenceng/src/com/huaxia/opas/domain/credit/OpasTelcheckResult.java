package com.huaxia.opas.domain.credit;
import java.io.Serializable;
import java.util.Date;
/*************************
 *@describe:征信电核结果信息表
 *@author xiaoJianFeng
 *@date:2017-03-31
 ***********************/
public class OpasTelcheckResult  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String auto_id;//序列值
	private String app_id;//申请件编号
	private String block_code;//过件码
	private String credit_refuse_reason;//征信拒绝原因
	private String telcheck_result;//电核情况
	private String identity_result;//核身问题库结果
	private Date credit_time;//征信日期
	private String crediter;//征信员登录名
	private String crediter_name;//征信员姓名
	private Date crt_date;//创建日期
	private String crt_user;//创建人
	private String curr_opt_group;//当前操作组
	private String ydj_flag;//易达金标示
	public String getAuto_id() {
		return auto_id;
	}
	public void setAuto_id(String auto_id) {
		this.auto_id = auto_id;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getBlock_code() {
		return block_code;
	}
	public void setBlock_code(String block_code) {
		this.block_code = block_code;
	}
	public String getCredit_refuse_reason() {
		return credit_refuse_reason;
	}
	public void setCredit_refuse_reason(String credit_refuse_reason) {
		this.credit_refuse_reason = credit_refuse_reason;
	}
	public String getTelcheck_result() {
		return telcheck_result;
	}
	public void setTelcheck_result(String telcheck_result) {
		this.telcheck_result = telcheck_result;
	}
	public String getIdentity_result() {
		return identity_result;
	}
	public void setIdentity_result(String identity_result) {
		this.identity_result = identity_result;
	}
	 
	public Date getCredit_time() {
		return credit_time;
	}
	public void setCredit_time(Date credit_time) {
		this.credit_time = credit_time;
	}
	public Date getCrt_date() {
		return crt_date;
	}
	public void setCrt_date(Date crt_date) {
		this.crt_date = crt_date;
	}
	public String getCrediter() {
		return crediter;
	}
	public void setCrediter(String crediter) {
		this.crediter = crediter;
	}
	public String getCrediter_name() {
		return crediter_name;
	}
	public void setCrediter_name(String crediter_name) {
		this.crediter_name = crediter_name;
	}
	 
	public String getCrt_user() {
		return crt_user;
	}
	public void setCrt_user(String crt_user) {
		this.crt_user = crt_user;
	}
	public String getCurr_opt_group() {
		return curr_opt_group;
	}
	public void setCurr_opt_group(String curr_opt_group) {
		this.curr_opt_group = curr_opt_group;
	}
	public String getYdj_flag() {
		return ydj_flag;
	}
	public void setYdj_flag(String ydj_flag) {
		this.ydj_flag = ydj_flag;
	}


}
