package com.huaxia.opas.domain.sysparm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 征信问题实体类
 * @author lipengfei
 *
 */
public class ConfQuestion implements Serializable {

	private static final long serialVersionUID = 3366739864147085995L;
	
	/**
	 * ID
	 */
	private String autoId;
	
	private String crtDate1;
	public String getCrtDate1() {
		return crtDate1;
	}



	public void setCrtDate1(String crtDate1) {
		this.crtDate1 = crtDate1;
	}

	/**
	 * 历史表操作的ID
	 */
	private String historyId;

	/**
	 * 问题编号
	 */
	private String questionNo;
	
	public String getHistoryId() {
		return historyId;
	}



	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

	/**
	 * 用户类型(1.标准卡 2.易达金卡)
	 */
	private String userFlag;
	
	/**
	 * 启停状态(0.禁用 1.启用)
	 */
	private String status;
	
	/**
	 * 问题等级
	 */
	private String questionLevel;
	
	/**
	 * 问题描述
	 */
	private String questionDesc;
		
	/**
	 * 来源表描述
	 */
	private String fromTableDesc;
	
 	/**
	 * 来源域描述
	 */
	private String fromFelDesc;
	
	/**
	 * 创建日期
	 */
	private Date crtDate;
	
	/**
	 * 创建人
	 */
	private String crtUser;
	
	/**
	 * 最后修改日期
	 */
	private Date lstUpdDate;
	
	/**
	 * 最后修改人
	 */
	private String lstUpdUser;
	
	/**
	 * 当前登陆用户
	 */
	private String operator;

	public ConfQuestion() {
		super();
	}

	

	public String getAutoId() {
		return autoId;
	}



	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}



	public String getQuestionNo() {
		return questionNo;
	}



	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}



	public String getUserFlag() {
		return userFlag;
	}



	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getQuestionLevel() {
		return questionLevel;
	}



	public void setQuestionLevel(String questionLevel) {
		this.questionLevel = questionLevel;
	}



	public String getQuestionDesc() {
		return questionDesc;
	}



	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	public String getFromTableDesc() {
		return fromTableDesc;
	}



	public void setFromTableDesc(String fromTableDesc) {
		this.fromTableDesc = fromTableDesc;
	}



	public String getFromFelDesc() {
		return fromFelDesc;
	}



	public void setFromFelDesc(String fromFelDesc) {
		this.fromFelDesc = fromFelDesc;
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



	public String getOperator() {
		return operator;
	}



	public void setOperator(String operator) {
		this.operator = operator;
	}



	public ConfQuestion(String autoId, String questionNo, String userFlag, String status, String questionLevel,
			String questionDesc, String fromTblNo, String fromFelNo, String fromTableDesc, String fromFelDesc,
			Date crtDate, String crtUser, Date lstUpdDate, String lstUpdUser, String operator, List<String> ids) {
		super();
		this.autoId = autoId;
		this.questionNo = questionNo;
		this.userFlag = userFlag;
		this.status = status;
		this.questionLevel = questionLevel;
		this.questionDesc = questionDesc;
		this.fromTableDesc = fromTableDesc;
		this.fromFelDesc = fromFelDesc;
		this.crtDate = crtDate;
		this.crtUser = crtUser;
		this.lstUpdDate = lstUpdDate;
		this.lstUpdUser = lstUpdUser;
		this.operator = operator;
		this.ids = ids;
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
