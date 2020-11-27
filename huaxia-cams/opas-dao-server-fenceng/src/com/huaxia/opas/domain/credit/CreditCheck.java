package com.huaxia.opas.domain.credit;
import java.io.Serializable;
import java.util.Date;

/**************************************
 *@describe:征信提报、复核、调查、审批数据实体对象
 *@author：xiaoJianFeng
 *@date:2017-03-04
 ****************************/
public class CreditCheck implements Serializable {
	private static final long serialVersionUID = 1L;
	//来源 客户基本信息表[OPAS_CUST_BASEINFO]
	private String check_custName;//客户姓名
	private String check_certType;//证件类型
	private String check_certNo;//证件号
	private String check_currentCN;//现单位名称
	//来源 申请件分配表：OPAS_ALLOT_APPLY_ALLOT
	private String check_number;//流水号
	private String check_autoId;
	private String check_lastOU;//最后操作员
	private String check_lastOPT;//最后操作员
	private String check_zshy_inner_check;
	public String getCheck_lastOPT() {
		return check_lastOPT;
	}
	public void setCheck_lastOPT(String check_lastOPT) {
		this.check_lastOPT = check_lastOPT;
	}
	private String check_lastOptGroup;//最后操作组
	private String check_lastOptQueue;//最后操作队列
	private Date check_lastOD;//最后操作时间
	private String check_GourpDate;//分组天数		计算得到结果
	private String check_queueDate;//进入队列天数      	计算得到结果
	private String check_submitType;//提报类型  1 提报反欺 2 提报催收 3提报账号 4 提报授权 5 提报反洗钱 -add by wenyh
	private String check_SubmitStatus; //提报状态
	private String check_delStatus;//人工处理状态 0:未完成1:已完成2:补件3:退回4:挂起
	private String check_currOptUser;//当前操作人
	private String check_currOptGroup;//当前操作组
	private String check_currNode;//当前操作环节 01：录入比对 02：征信调 03：人工审批
	private String check_ydjFlag;//易达金标示 1易达金 2标准卡
	private String entrySystemDate;//进入系统时间
	
	public String getCheck_zshy_inner_check() {
		return check_zshy_inner_check;
	}
	public void setCheck_zshy_inner_check(String check_zshy_inner_check) {
		this.check_zshy_inner_check = check_zshy_inner_check;
	}
	public String getCheckStr() {
		return checkStr;
	}
	public void setCheckStr(String checkStr) {
		this.checkStr = checkStr;
	}
	public String getEntrySystemDate() {
		return entrySystemDate;
	}
	public void setEntrySystemDate(String entrySystemDate) {
		this.entrySystemDate = entrySystemDate;
	}
	private String check_reasonAutoId;//提报原因码id
	private String checkStr;//排序
	public String getInvest_checkBackMeo() {
		return invest_checkBackMeo;
	}
	public void setInvest_checkBackMeo(String invest_checkBackMeo) {
		this.invest_checkBackMeo = invest_checkBackMeo;
	}
	private String check_currStatus;//分配状态     0-当前环节池中 1-未分配未挂起 2-未分配已挂起 3-已分配未挂起  4-已分配已挂起
	private String check_meuoFlag;//菜单标志1-征信；2-征审合一
	private String check_synFlag;//套卡提交标识
	private Date check_USER_DATE;			//进入当前操作员日前
	private String matchingCardFlag;//套卡标示
	private String appProd;//卡产品编号
	private String lastDate;//最后操作时间
	private int remark;//备注
	private String invest_checkBackMeo;//回退备注
	public int getRemark() {
		return remark;
	}
	public void setRemark(int remark) {
		this.remark = remark;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public Date getCheck_USER_DATE() {
		return check_USER_DATE;
	}
	public void setCheck_USER_DATE(Date check_USER_DATE) {
		this.check_USER_DATE = check_USER_DATE;
	}
	//来源  征信电核结果信息表[OPAS_TELCHECK_RESULT]
	private String check_nclearPS;//电核情况  
	// 来源 主卡进件信息表（OPAS_BIZ_INP_APPL_C1）
	private String check_aproveFlag;//快速审批标识 快速审批标识（是/否） 
	private String check_crtDate;//进件日期
	
	private String check_prompt;//提示 （二卡/补件完成/P(公安未匹配)/R（人行未匹配））
	private String check_remark;//备注
	
	private String check_reason  ;//提报原因
	private String check_memo;//提报备注
	private String check_reasonType;//提报备注类型
	
	private String check_reasonCode; 
	private String check_reasonTypeCode; 
	
	//征信提报欺诈结果信息表[OPAS_SUBMITFRAUD_INFO]
	private String invest_autoId;//序列值	 
	private String invest_resultName;//调查结果原因名称	
	private String invest_resultCode;//调查结果原因码
	private String invest_delStatus;//处理状态	 	0:未完成 1:退回欺诈调查 2:提交欺诈审批 3:退回征信调查 4:已完成
	private String invest_memo;//	调查备注	 
	private Date invest_crtDate;//创建日期	 	
	private String invest_crtUser;//提交审批员	
	private String invest_lastOU;//提报员	 
	private String invest_approvalMemo;//审核备注	 DEL_STATUS
	private String invest_resultFlag;//是否调查结果复核	 
	private String invest_detailCode;//调查原因编号
	private String invest_detailName;//调查原因码名称
	private Date invest_operationDate;//操作时间
	private String invest_crtUserName;
	
	private String queryID;//查询排序标识
	
	//预审分件表 ys_allot_apply_allot
	private String fenHang;//分行码值
	private String curr_opt_user;//组别号
	private String curr_status;//分配处理状态
	private String curr_node;//当前环节
	private Date crt_time;//创建时间
	private Date lst_team_date;//最后操作时间
	private String del_stasus;//处理状态
	private String syn_flag;//是否隐藏标识
	private String his_vdel_status;//派发协查之前 VDEL_STATUS字段的状态
	private String pfxcFlag;//是否发起协查标志
	
	public String getFenHang() {
		return fenHang;
	}
	public void setFenHang(String fenHang) {
		this.fenHang = fenHang;
	}
	public String getCurr_opt_user() {
		return curr_opt_user;
	}
	public void setCurr_opt_user(String curr_opt_user) {
		this.curr_opt_user = curr_opt_user;
	}
	public String getCurr_status() {
		return curr_status;
	}
	public void setCurr_status(String curr_status) {
		this.curr_status = curr_status;
	}
	public String getCurr_node() {
		return curr_node;
	}
	public void setCurr_node(String curr_node) {
		this.curr_node = curr_node;
	}
	public Date getCrt_time() {
		return crt_time;
	}
	public void setCrt_time(Date crt_time) {
		this.crt_time = crt_time;
	}
	public Date getLst_team_date() {
		return lst_team_date;
	}
	public void setLst_team_date(Date lst_team_date) {
		this.lst_team_date = lst_team_date;
	}
	public String getDel_stasus() {
		return del_stasus;
	}
	public void setDel_stasus(String del_stasus) {
		this.del_stasus = del_stasus;
	}
	public String getSyn_flag() {
		return syn_flag;
	}
	public void setSyn_flag(String syn_flag) {
		this.syn_flag = syn_flag;
	}
	public String getHis_vdel_status() {
		return his_vdel_status;
	}
	public void setHis_vdel_status(String his_vdel_status) {
		this.his_vdel_status = his_vdel_status;
	}
	public String getPfxcFlag() {
		return pfxcFlag;
	}
	public void setPfxcFlag(String pfxcFlag) {
		this.pfxcFlag = pfxcFlag;
	}
	public String getCheck_reasonAutoId() {
		return check_reasonAutoId;
	}
	public void setCheck_reasonAutoId(String check_reasonAutoId) {
		this.check_reasonAutoId = check_reasonAutoId;
	}
	public String getCheck_reasonCode() {
		return check_reasonCode;
	}
	public void setCheck_reasonCode(String check_reasonCode) {
		this.check_reasonCode = check_reasonCode;
	}
	public String getCheck_reasonTypeCode() {
		return check_reasonTypeCode;
	}
	public void setCheck_reasonTypeCode(String check_reasonTypeCode) {
		this.check_reasonTypeCode = check_reasonTypeCode;
	}
	public String getCheck_ydjFlag() {
		return check_ydjFlag;
	}
	public void setCheck_ydjFlag(String check_ydjFlag) {
		this.check_ydjFlag = check_ydjFlag;
	}
	public Date getInvest_operationDate() {
		return invest_operationDate;
	}
	public void setInvest_operationDate(Date invest_operationDate) {
		this.invest_operationDate = invest_operationDate;
	}
	public String getInvest_crtUserName() {
		return invest_crtUserName;
	}
	public void setInvest_crtUserName(String invest_crtUserName) {
		this.invest_crtUserName = invest_crtUserName;
	}
	public String getInvest_lastOU() {
		return invest_lastOU;
	}
	public void setInvest_lastOU(String invest_lastOU) {
		this.invest_lastOU = invest_lastOU;
	}
	public String getQueryID() {
		return queryID;
	}
	public void setQueryID(String queryID) {
		this.queryID = queryID;
	}
	public String getInvest_resultCode() {
		return invest_resultCode;
	}
	public void setInvest_resultCode(String invest_resultCode) {
		this.invest_resultCode = invest_resultCode;
	}
	public String getInvest_detailCode() {
		return invest_detailCode;
	}
	public void setInvest_detailCode(String invest_detailCode) {
		this.invest_detailCode = invest_detailCode;
	}
	public String getInvest_detailName() {
		return invest_detailName;
	}
	public void setInvest_detailName(String invest_detailName) {
		this.invest_detailName = invest_detailName;
	}
	public String getInvest_autoId() {
		return invest_autoId;
	}
	public void setInvest_autoId(String invest_autoId) {
		this.invest_autoId = invest_autoId;
	}
 
	public String getCheck_autoId() {
		return check_autoId;
	}
	public void setCheck_autoId(String check_autoId) {
		this.check_autoId = check_autoId;
	}
	public String getInvest_resultName() {
		return invest_resultName;
	}
	public void setInvest_resultName(String invest_resultName) {
		this.invest_resultName = invest_resultName;
	}
	public String getInvest_delStatus() {
		return invest_delStatus;
	}
	public void setInvest_delStatus(String invest_delStatus) {
		this.invest_delStatus = invest_delStatus;
	}
	public String getInvest_memo() {
		return invest_memo;
	}
	public void setInvest_memo(String invest_memo) {
		this.invest_memo = invest_memo;
	}
 
	public Date getInvest_crtDate() {
		return invest_crtDate;
	}
	public void setInvest_crtDate(Date invest_crtDate) {
		this.invest_crtDate = invest_crtDate;
	}
	public String getInvest_crtUser() {
		return invest_crtUser;
	}
	public void setInvest_crtUser(String invest_crtUser) {
		this.invest_crtUser = invest_crtUser;
	}
	public String getInvest_approvalMemo() {
		return invest_approvalMemo;
	}
	public void setInvest_approvalMemo(String invest_approvalMemo) {
		this.invest_approvalMemo = invest_approvalMemo;
	}
 
	public String getInvest_resultFlag() {
		return invest_resultFlag;
	}
	public void setInvest_resultFlag(String invest_resultFlag) {
		this.invest_resultFlag = invest_resultFlag;
	}
	public String getCheck_lastOptGroup() {
		return check_lastOptGroup;
	}
	public void setCheck_lastOptGroup(String check_lastOptGroup) {
		this.check_lastOptGroup = check_lastOptGroup;
	}
	public String getCheck_lastOptQueue() {
		return check_lastOptQueue;
	}
	public void setCheck_lastOptQueue(String check_lastOptQueue) {
		this.check_lastOptQueue = check_lastOptQueue;
	}
	public String getCheck_reasonType() {
		return check_reasonType;
	}
	public void setCheck_reasonType(String check_reasonType) {
		this.check_reasonType = check_reasonType;
	}
	public String getCheck_reason() {
		return check_reason;
	}
	public void setCheck_reason(String check_reason) {
		this.check_reason = check_reason;
	}
	public String getCheck_memo() {
		return check_memo;
	}
	public void setCheck_memo(String check_memo) {
		this.check_memo = check_memo;
	}
	public String getCheck_SubmitStatus() {
		return check_SubmitStatus;
	}
	public void setCheck_SubmitStatus(String check_SubmitStatus) {
		this.check_SubmitStatus = check_SubmitStatus;
	}
	public String getCheck_number() {
		return check_number;
	}
	public void setCheck_number(String check_number) {
		this.check_number = check_number;
	}
	public String getCheck_custName() {
		return check_custName;
	}
	public void setCheck_custName(String check_custName) {
		this.check_custName = check_custName;
	}
	public String getCheck_certType() {
		return check_certType;
	}
	public void setCheck_certType(String check_certType) {
		this.check_certType = check_certType;
	}
	public String getCheck_certNo() {
		return check_certNo;
	}
	public void setCheck_certNo(String check_certNo) {
		this.check_certNo = check_certNo;
	}
	public String getCheck_currentCN() {
		return check_currentCN;
	}
	public void setCheck_currentCN(String check_currentCN) {
		this.check_currentCN = check_currentCN;
	}
	public String getCheck_lastOU() {
		return check_lastOU;
	}
	public void setCheck_lastOU(String check_lastOU) {
		this.check_lastOU = check_lastOU;
	}
 
	public Date getCheck_lastOD() {
		return check_lastOD;
	}
	public void setCheck_lastOD(Date check_lastOD) {
		this.check_lastOD = check_lastOD;
	}
	public String getCheck_GourpDate() {
		return check_GourpDate;
	}
	public void setCheck_GourpDate(String check_GourpDate) {
		this.check_GourpDate = check_GourpDate;
	}
	public String getCheck_queueDate() {
		return check_queueDate;
	}
	public void setCheck_queueDate(String check_queueDate) {
		this.check_queueDate = check_queueDate;
	}
	public String getCheck_nclearPS() {
		return check_nclearPS;
	}
	public void setCheck_nclearPS(String check_nclearPS) {
		this.check_nclearPS = check_nclearPS;
	}
	public String getCheck_aproveFlag() {
		return check_aproveFlag;
	}
	public void setCheck_aproveFlag(String check_aproveFlag) {
		this.check_aproveFlag = check_aproveFlag;
	}
	public String getCheck_prompt() {
		return check_prompt;
	}
	public void setCheck_prompt(String check_prompt) {
		this.check_prompt = check_prompt;
	}
	public String getCheck_remark() {
		return check_remark;
	}
	public void setCheck_remark(String check_remark) {
		this.check_remark = check_remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCheck_submitType() {
		return check_submitType;
	}
	public void setCheck_submitType(String check_submitType) {
		this.check_submitType = check_submitType;
	}
	public String getCheck_delStatus() {
		return check_delStatus;
	}
	public void setCheck_delStatus(String check_delStatus) {
		this.check_delStatus = check_delStatus;
	}
	public String getCheck_currOptUser() {
		return check_currOptUser;
	}
	public void setCheck_currOptUser(String check_currOptUser) {
		this.check_currOptUser = check_currOptUser;
	}
	public String getCheck_currOptGroup() {
		return check_currOptGroup;
	}
	public void setCheck_currOptGroup(String check_currOptGroup) {
		this.check_currOptGroup = check_currOptGroup;
	}
	public String getCheck_currNode() {
		return check_currNode;
	}
	public void setCheck_currNode(String check_currNode) {
		this.check_currNode = check_currNode;
	}
	public String getCheck_crtDate() {
		return check_crtDate;
	}
	public void setCheck_crtDate(String check_crtDate) {
		this.check_crtDate = check_crtDate;
	}
	
	public String getCheck_currStatus() {
		return check_currStatus;
	}
	public void setCheck_currStatus(String check_currStatus) {
		this.check_currStatus = check_currStatus;
	}
	public String getCheck_meuoFlag() {
		return check_meuoFlag;
	}
	public void setCheck_meuoFlag(String check_meuoFlag) {
		this.check_meuoFlag = check_meuoFlag;
	}
	
	public String getCheck_synFlag() {
		return check_synFlag;
	}
	public void setCheck_synFlag(String check_synFlag) {
		this.check_synFlag = check_synFlag;
	}
	@Override
	public String toString() {
		return "CreditCheck [check_custName=" + check_custName + ", check_certType=" + check_certType
				+ ", check_certNo=" + check_certNo + ", check_currentCN=" + check_currentCN + ", check_number="
				+ check_number + ", check_autoId=" + check_autoId + ", check_lastOU=" + check_lastOU
				+ ", check_lastOptGroup=" + check_lastOptGroup + ", check_lastOptQueue=" + check_lastOptQueue
				+ ", check_lastOD=" + check_lastOD + ", check_GourpDate=" + check_GourpDate + ", check_queueDate="
				+ check_queueDate + ", check_submitType=" + check_submitType + ", check_SubmitStatus="
				+ check_SubmitStatus + ", check_delStatus=" + check_delStatus + ", check_currOptUser="
				+ check_currOptUser + ", check_currOptGroup=" + check_currOptGroup + ", check_currNode="
				+ check_currNode + ", check_ydjFlag=" + check_ydjFlag + ", check_reasonAutoId=" + check_reasonAutoId
				+ ", check_currStatus=" + check_currStatus + ", check_nclearPS=" + check_nclearPS
				+ ", check_aproveFlag=" + check_aproveFlag + ", check_crtDate=" + check_crtDate + ", check_prompt="
				+ check_prompt + ", check_remark=" + check_remark + ", check_reason=" + check_reason + ", check_memo="
				+ check_memo + ", check_reasonType=" + check_reasonType + ", check_reasonCode=" + check_reasonCode
				+ ", check_reasonTypeCode=" + check_reasonTypeCode + ", invest_autoId=" + invest_autoId
				+ ", invest_resultName=" + invest_resultName + ", invest_resultCode=" + invest_resultCode
				+ ", invest_delStatus=" + invest_delStatus + ", invest_memo=" + invest_memo + ", invest_crtDate="
				+ invest_crtDate + ", invest_crtUser=" + invest_crtUser + ", invest_approvalMemo=" + invest_approvalMemo
				+ ", invest_resultFlag=" + invest_resultFlag + ", invest_detailCode=" + invest_detailCode
				+ ", invest_detailName=" + invest_detailName + ", invest_operationDate=" + invest_operationDate
				+ ", invest_crtUserName=" + invest_crtUserName + ", queryID=" + queryID + "]";
	}
	public String getMatchingCardFlag() {
		return matchingCardFlag;
	}
	public void setMatchingCardFlag(String matchingCardFlag) {
		this.matchingCardFlag = matchingCardFlag;
	}
	public String getAppProd() {
		return appProd;
	}
	public void setAppProd(String appProd) {
		this.appProd = appProd;
	}
}
