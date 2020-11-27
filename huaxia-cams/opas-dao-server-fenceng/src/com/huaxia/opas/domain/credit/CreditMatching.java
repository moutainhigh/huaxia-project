package com.huaxia.opas.domain.credit;
import java.io.Serializable;
import java.util.Date;
/******************************
 *@describe:人行征信查询匹配设置
 *@author:xiaoJianFeng
 *Date:2017-03-19
 *******************************/
public class CreditMatching implements Serializable{
	private static final long serialVersionUID = 1L;
    private String match_autoID;//序列值
    private String match_peobankName;//不查询人行名称
    private String match_nonPeobankMatct;//不查询人行匹配值
    private String match_currStatus;// 启用状态
    private String match_crtUser;//  创建人
    private Date match_crtTime;//创建时间
	public String getMatch_autoID() {
		return match_autoID;
	}
	public void setMatch_autoID(String match_autoID) {
		this.match_autoID = match_autoID;
	}
	public String getMatch_peobankName() {
		return match_peobankName;
	}
	public void setMatch_peobankName(String match_peobankName) {
		this.match_peobankName = match_peobankName;
	}
	public String getMatch_nonPeobankMatct() {
		return match_nonPeobankMatct;
	}
	public void setMatch_nonPeobankMatct(String match_nonPeobankMatct) {
		this.match_nonPeobankMatct = match_nonPeobankMatct;
	}
	public String getMatch_currStatus() {
		return match_currStatus;
	}
	public void setMatch_currStatus(String match_currStatus) {
		this.match_currStatus = match_currStatus;
	}
	public String getMatch_crtUser() {
		return match_crtUser;
	}
	public void setMatch_crtUser(String match_crtUser) {
		this.match_crtUser = match_crtUser;
	}
	 
	public Date getMatch_crtTime() {
		return match_crtTime;
	}
	public void setMatch_crtTime(Date match_crtTime) {
		this.match_crtTime = match_crtTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
