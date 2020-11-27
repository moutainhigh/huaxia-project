package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 行员名单库实体类
 * 
 * @author lipengfei
 *
 */
public class ConfRosterLibrary implements Serializable{

	private static final long serialVersionUID = -1856166333247738271L;

	//行员ID
	private String rosterID;
	//行员姓名
	private String rosterName;
	//行员身份证号
	private String rosterLdnumber;
	//行员部门
	private String rosterUnits;
	//行员等级
	private String rosterLevel;
	//创建日期
	private Date crtDate;
	//创建人
	private String crtUser;	
	//最后修改日期
	private Date lstUpdDate;
	//最后修改人
	private String lstUpdUser;
	//当前登录用户
	private String operator;
	//查询验证创建时间
	private String crtDate1;
	
	public String getCrtDate1() {
		return crtDate1;
	}
	public void setCrtDate1(String crtDate1) {
		this.crtDate1 = crtDate1;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public ConfRosterLibrary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConfRosterLibrary(String rosterID, String rosterName, String rosterLdnumber, String rosterUnits,
			String rosterLevel, Date crtDate, String crtUser, Date lstUpdDate, String lstUpdUser) {
		super();
		this.rosterID = rosterID;
		this.rosterName = rosterName;
		this.rosterLdnumber = rosterLdnumber;
		this.rosterUnits = rosterUnits;
		this.rosterLevel = rosterLevel;
		this.crtDate = crtDate;
		this.crtUser = crtUser;
		this.lstUpdDate = lstUpdDate;
		this.lstUpdUser = lstUpdUser;
	}
	public String getRosterID() {
		return rosterID;
	}
	public void setRosterID(String rosterID) {
		this.rosterID = rosterID;
	}
	public String getRosterName() {
		return rosterName;
	}
	public void setRosterName(String rosterName) {
		this.rosterName = rosterName;
	}
	public String getRosterLdnumber() {
		return rosterLdnumber;
	}
	public void setRosterLdnumber(String rosterLdnumber) {
		this.rosterLdnumber = rosterLdnumber;
	}
	public String getRosterUnits() {
		return rosterUnits;
	}
	public void setRosterUnits(String rosterUnits) {
		this.rosterUnits = rosterUnits;
	}
	public String getRosterLevel() {
		return rosterLevel;
	}
	public void setRosterLevel(String rosterLevel) {
		this.rosterLevel = rosterLevel;
	}
	public Date getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	public String getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	public Date getLstUpdDate() {
		return lstUpdDate;
	}
	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}
	public String getLstUpdUser() {
		return lstUpdUser;
	}
	public void setLstUpdUser(String lstUpdUser) {
		this.lstUpdUser = lstUpdUser;
	}
	
	/**
	 * ID集合,批量操作使用
	 */
	private List<String> ids;


	public List<String> getIds() {
		return ids;
	}


	public void setIds(List<String> ids) {
		this.ids = ids;
	}
}
