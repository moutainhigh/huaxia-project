package com.huaxia.opas.domain.allot;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 描述：申请件分配
 * @author wangdebin
 * @version 1.0
 * @since
 */
public class AllotApply implements Serializable {
	private static final long serialVersionUID = 8114063341426529298L;
	// 申请件编号id
	private String appId;
	//申请件前15位
	private  String appNo;
	// 当前操作队列
	private String currQueue;
	// 最后操作队列
	private String lastQueue;
	// 当前操作组
	private String currGroup;
	// 最后操作组
	private String lastGroup;
	// 当前操作人
	private String currUser;
	// 上次操作人
	private String lastUser;
	// 分配状态 0 池中待分配   1-未分配未挂起 2-未分配已挂起 3-已分配未挂起 4-已分配已挂起
	private String currStatus;
	
	private String appStatus;
	
	private String appStatus2;
	// 节点
	private String currNode;
	// 处理状态 0代表未完成 1代表已完成 2:补件 3:退回 4:挂起
	private String delStatus;
	//创建日期
	private  Date crtTeamDate;
	//最后操作日期
	private Date lstTeamDate;
	//流程实例编号
	private String processId;
	//
	private String submitMemo;
	//提报类型
	private String submitType;
	//复核状态
	private String reviewStatus;
	//提报状态
	private String submitStatus;
	//进入队列日期
	private Date queueDate;
	//进入分组日期(审查  征信)
	private Date groupDate;
	//进入操作员日期
	private Date userDate;
	//组日期(全环节 主要  欺诈  审批用 )
	private Date groupTeamDate;
	//欺诈池日期
	private Date fraudDate;
	//易达金卡标识】
	private String  ydjFlag;
	//套卡标识】
	private String  matchingCardFlag;
	//隐藏标识
	private String synFlag;
	//乐观锁  避免行级锁情况
	private int allotVersion;
	//逆向操作BACK_FLAG  默认0 1 二次决策被拒捞回  2审批被拒捞回  3 修改关键信息    4 退回   
	private String backFlag;

	//捞件标识
	private String laoJianFlag;
	
	//中文名称
	private String currUserName;
	
	//申请件集合
	private List<String> ids;
	//批量标识  审查    BATCH_OPERATE_FLAG
	private String batchOperateFlag;
	
	//征信批量
	private String batchCreditFlag;
	
	//审批批量
	private String batchApprovalFlag;
	private String check_meuoFlag;
	
	private String zshyInnerCheck;
	

	//OPAS_BIZ_INP_APPL_C1主卡进件信息表
	//卡产品类型
	private String appProd;
	//姓名C1_CNAME
	private String c1Cname;
	//快速发卡标志C4_RUSHFLG   NUMBER	1-快速发卡0－正常发卡2-紧急发卡
	private int c4RushFlg;
	//快速申请标识QUICK_CARD_FLAG 1为快速发卡，0或空位非快速发卡
	private String quickCardFlag;
	//  是否已持卡客户 0:否 1:是
	private String isHaveCardCust;
	
	private String existCardFlag;
	//渠道代码
	//private String channelCode;
	//单位中文名称 C1_CONAME
	private String c1CoName;
	//时间类型
	private String shareTime;
	//批次号时间 TIMESTAMP
	private Date crtTime;
	//团办号编号
	private String c4ApBatch;
	//VARCHAR2	1	账单邮寄地址1*  C4_CYCADD1 C4_CYCADD2
	private String c4CyCadd1;
	
	private String c4CyCadd2;
	//分配状态
	private String allotStatus;
	//分配状态名称
	private String statusName;
	//C4_ABUSER	VARCHAR2	12	推广人编号	否	推广人编号
	private String c4AbUser;
	//主卡进件信息表（OPAS_BIZ_INP_APPL_C1）  C5_ABCODE	VARCHAR2	6	推广机构号	否	　
	private String c5AbCode; 
	

	//申请件条形码解析 流水号字母
	private String serialNum;
	//申请件条形码解析 地区
	private String appRegion;
	//申请件条形码解析 渠道
	private String appChannel;
	//申请件条形码解析 录入商
	private String appInput;
	//申请件条形码解析 申请时间
	private String appTime;
	//申请件条形码解析 城市
	private String appCardCity;
	//如果勾选筛选条件：时间或单位名称（筛选条件互斥），则根据勾选条件进行查询分配。
	private String allotType;
	//别名  count 
	private int num;
	
	private int num2;
	
	private int num3;
	
	//别名
	private String appCity;
	
	private String appCity2;
	
	private String appCity3;
	//卡名称
	private String prodName;
	//其它表信息
	//内部数据检查[OPAS_INNERDATA_CHECK]  APP_ID   NOSAFE_PROMOTER	不安全推广人员	CHAR	1	0:否   1:是  业务 字典维护
	private String nosafePromoter;
	//人行信用提示信息表 OPAS_PBOC_CREDIT_CUE  NUMBER_READ	VARCHAR(100)	数字解读	N   INSIDE_APP_NO	CHAR(32)	内审编号	N	　
	private String numberRead;
	private String minNumberRead;
	private String maxNumberRead;
	//芝麻 OPAS_BIZ_ZM_IVS_DATA表  DESCRIPTION	VARCHAR2	100	风险描述说明
	private String description;
	
	//公安 数据库表表[OPAS_BIZ_PUBSEC_GATHER] IDENTITY_NO_CHECK_RST  NAME_CHECK_RST
	private String pubPolice;
	
	private String ideRst;
	
	private String nameRst;
	//系统决策建议额度（数值区间）  可输入指定系统决策建议额度值或系统决策建议额度值段形式  
	//标准卡BLAZE返回表[OPAS_FICO_SD_BLAZE] PROPOSED_LMT	NUMBER	(30,4)	易达金BLAZE返回表[OPAS_FICO_YDJ_BLAZE]
	private double proposedLmt;
	private double minProposedLmt;
	private double maxProposedLmt;
	//人行不良 PBOC_RST_FIN_DESP 征信策略结果描述(执行次策略后)	
	private String pbocRstFinDesp;
	//征信策略结论   易达金：系统征信、人工征信、重点关注、重点关注-真实性、重点关注-信用表现
	//标准卡：信息真实性校验通过、人工正核、高风险人工正核、人工侧核、人工审核、真实性校验失败。系统决策结果信息表(标准卡)[OPAS_BZK_SYSRESULT_INFO]
	//CREDIT_DECISION_RESULT  OPAS_YDJ_SYSRESULT_INFO
	private String creditDecisionResult;
	
	private String creditResultDescription;
	
	private String creditDecisionLayer;
	//回收节点  
	private String  recoveryNode;
	//
	private String batchLabel;
	//审查决策结果信息表[OPAS_REVIEW_DECISION_RESULT BATCH_CODE 批量标识
	private String batchCode;
	//征信电核结果信息表[OPAS_TELCHECK_RESULT]BLOCK_CODE	过件码 VARCHAR2	4
	private String blockCode;
	//征信拒绝原因CREDIT_REFUSE_REASON
	private String creditRefuseReason;
	//审批复核信息表[OPAS_BIZ_APPROVAL]  REFUSE_CODE1	拒绝码1	VARCHAR2	4
	//REFUSE_CODE2	拒绝码2	VARCHAR2	4
	//REFUSE_CODE3	拒绝码3	VARCHAR2	4
	private String refuseCode;
	private String refuseCode1;
	private String refuseCode2;
	private String refuseCode3;
	//备注[拒绝、审批]
	private String memo;
	//次级节点
	private String secondNode;
	
	private String autoId;
	
	private String crtDate;
	
	//关键信息修改表OPAS_KEYMESSAGE_MODIFY
	private String operator;
	
	//天域分
	private String riskScore;
	
	private String minRiskScore;
	
	private String maxRiskScore;
	
	//手机实名制结果
	private String phoneCertification;
	
	//最后征信人员
	private String crediterName;
	
	//项目号
	private String c5JcType;
	
	private String approveReturn;
	private String conclusion;
	private String sourceType;
	
	public String getC5JcType() {
		return c5JcType;
	}

	public void setC5JcType(String c5JcType) {
		this.c5JcType = c5JcType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCurrQueue() {
		return currQueue;
	}

	public void setCurrQueue(String currQueue) {
		this.currQueue = currQueue;
	}

	public String getLastQueue() {
		return lastQueue;
	}

	public void setLastQueue(String lastQueue) {
		this.lastQueue = lastQueue;
	}

	public String getCurrGroup() {
		return currGroup;
	}

	public void setCurrGroup(String currGroup) {
		this.currGroup = currGroup;
	}

	public String getLastGroup() {
		return lastGroup;
	}

	public void setLastGroup(String lastGroup) {
		this.lastGroup = lastGroup;
	}

	public String getCurrUser() {
		return currUser;
	}

	public void setCurrUser(String currUser) {
		this.currUser = currUser;
	}

	public String getLastUser() {
		return lastUser;
	}

	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}

	public String getCurrStatus() {
		return currStatus;
	}

	public void setCurrStatus(String currStatus) {
		this.currStatus = currStatus;
	}

	public String getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}

	public String getCurrNode() {
		return currNode;
	}

	public void setCurrNode(String currNode) {
		this.currNode = currNode;
	}


	public String getAppCardCity() {
		return appCardCity;
	}

	public void setAppCardCity(String appCardCity) {
		this.appCardCity = appCardCity;
	}

	public String getAppTime() {
		return appTime;
	}

	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}

	public String getC1CoName() {
		return c1CoName;
	}

	public void setC1CoName(String c1CoName) {
		this.c1CoName = c1CoName;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getAppProd() {
		return appProd;
	}

	public void setAppProd(String appProd) {
		this.appProd = appProd;
	}

	public String getAllotStatus() {
		return allotStatus;
	}

	public void setAllotStatus(String allotStatus) {
		this.allotStatus = allotStatus;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getAppRegion() {
		return appRegion;
	}

	public void setAppRegion(String appRegion) {
		this.appRegion = appRegion;
	}

	public String getAppChannel() {
		return appChannel;
	}

	public void setAppChannel(String appChannel) {
		this.appChannel = appChannel;
	}

	public String getAppInput() {
		return appInput;
	}

	public void setAppInput(String appInput) {
		this.appInput = appInput;
	}

	public String getRecoveryNode() {
		return recoveryNode;
	}

	public void setRecoveryNode(String recoveryNode) {
		this.recoveryNode = recoveryNode;
	}

	public int getC4RushFlg() {
		return c4RushFlg;
	}

	public void setC4RushFlg(int c4RushFlg) {
		this.c4RushFlg = c4RushFlg;
	}

	public Date getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}

	public String getAllotType() {
		return allotType;
	}

	public void setAllotType(String allotType) {
		this.allotType = allotType;
	}

	public String getIsHaveCardCust() {
		return isHaveCardCust;
	}

	public void setIsHaveCardCust(String isHaveCardCust) {
		this.isHaveCardCust = isHaveCardCust;
	}

	public String getNumberRead() {
		return numberRead;
	}

	public void setNumberRead(String numberRead) {
		this.numberRead = numberRead;
	}

	public String getNosafePromoter() {
		return nosafePromoter;
	}

	public void setNosafePromoter(String nosafePromoter) {
		this.nosafePromoter = nosafePromoter;
	}

	public String getMinNumberRead() {
		return minNumberRead;
	}

	public void setMinNumberRead(String minNumberRead) {
		this.minNumberRead = minNumberRead;
	}

	public String getMaxNumberRead() {
		return maxNumberRead;
	}

	public void setMaxNumberRead(String maxNumberRead) {
		this.maxNumberRead = maxNumberRead;
	}


	public String getC4ApBatch() {
		return c4ApBatch;
	}

	public void setC4ApBatch(String c4ApBatch) {
		this.c4ApBatch = c4ApBatch;
	}


	public String getC4AbUser() {
		return c4AbUser;
	}

	public void setC4AbUser(String c4AbUser) {
		this.c4AbUser = c4AbUser;
	}

	public String getC5AbCode() {
		return c5AbCode;
	}

	public void setC5AbCode(String c5AbCode) {
		this.c5AbCode = c5AbCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Date getCrtTeamDate() {
		return crtTeamDate;
	}

	public void setCrtTeamDate(Date crtTeamDate) {
		this.crtTeamDate = crtTeamDate;
	}

	public Date getLstTeamDate() {
		return lstTeamDate;
	}

	public void setLstTeamDate(Date lstTeamDate) {
		this.lstTeamDate = lstTeamDate;
	}

	public String getSubmitType() {
		return submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}

	public Date getQueueDate() {
		return queueDate;
	}

	public void setQueueDate(Date queueDate) {
		this.queueDate = queueDate;
	}

	public Date getGroupDate() {
		return groupDate;
	}

	public void setGroupDate(Date groupDate) {
		this.groupDate = groupDate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getYdjFlag() {
		return ydjFlag;
	}

	public void setYdjFlag(String ydjFlag) {
		this.ydjFlag = ydjFlag;
	}

	public String getShareTime() {
		return shareTime;
	}

	public void setShareTime(String shareTime) {
		this.shareTime = shareTime;
	}

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public String getRefuseCode() {
		return refuseCode;
	}

	public void setRefuseCode(String refuseCode) {
		this.refuseCode = refuseCode;
	}

	public String getRefuseCode1() {
		return refuseCode1;
	}

	public void setRefuseCode1(String refuseCode1) {
		this.refuseCode1 = refuseCode1;
	}

	public String getRefuseCode2() {
		return refuseCode2;
	}

	public void setRefuseCode2(String refuseCode2) {
		this.refuseCode2 = refuseCode2;
	}

	public String getRefuseCode3() {
		return refuseCode3;
	}

	public void setRefuseCode3(String refuseCode3) {
		this.refuseCode3 = refuseCode3;
	}

	public String getBatchOperateFlag() {
		return batchOperateFlag;
	}

	public void setBatchOperateFlag(String batchOperateFlag) {
		this.batchOperateFlag = batchOperateFlag;
	}

	public double getProposedLmt() {
		return proposedLmt;
	}

	public void setProposedLmt(double proposedLmt) {
		this.proposedLmt = proposedLmt;
	}

	public String getCreditDecisionResult() {
		return creditDecisionResult;
	}

	public void setCreditDecisionResult(String creditDecisionResult) {
		this.creditDecisionResult = creditDecisionResult;
	}

	public String getCreditRefuseReason() {
		return creditRefuseReason;
	}

	public void setCreditRefuseReason(String creditRefuseReason) {
		this.creditRefuseReason = creditRefuseReason;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getIdeRst() {
		return ideRst;
	}

	public void setIdeRst(String ideRst) {
		this.ideRst = ideRst;
	}

	public String getNameRst() {
		return nameRst;
	}

	public void setNameRst(String nameRst) {
		this.nameRst = nameRst;
	}

	public double getMinProposedLmt() {
		return minProposedLmt;
	}

	public void setMinProposedLmt(double minProposedLmt) {
		this.minProposedLmt = minProposedLmt;
	}

	public double getMaxProposedLmt() {
		return maxProposedLmt;
	}

	public void setMaxProposedLmt(double maxProposedLmt) {
		this.maxProposedLmt = maxProposedLmt;
	}

	public String getBatchLabel() {
		return batchLabel;
	}

	public void setBatchLabel(String batchLabel) {
		this.batchLabel = batchLabel;
	}

	public String getPbocRstFinDesp() {
		return pbocRstFinDesp;
	}

	public void setPbocRstFinDesp(String pbocRstFinDesp) {
		this.pbocRstFinDesp = pbocRstFinDesp;
	}

	public String getSecondNode() {
		return secondNode;
	}

	public void setSecondNode(String secondNode) {
		this.secondNode = secondNode;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getUserDate() {
		return userDate;
	}

	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}

	public String getMatchingCardFlag() {
		return matchingCardFlag;
	}

	public void setMatchingCardFlag(String matchingCardFlag) {
		this.matchingCardFlag = matchingCardFlag;
	}

	public String getSubmitMemo() {
		return submitMemo;
	}

	public void setSubmitMemo(String submitMemo) {
		this.submitMemo = submitMemo;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getQuickCardFlag() {
		return quickCardFlag;
	}

	public void setQuickCardFlag(String quickCardFlag) {
		this.quickCardFlag = quickCardFlag;
	}

	public String getC1Cname() {
		return c1Cname;
	}

	public void setC1Cname(String c1Cname) {
		this.c1Cname = c1Cname;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}

	public int getNum3() {
		return num3;
	}

	public void setNum3(int num3) {
		this.num3 = num3;
	}

	public String getAppCity() {
		return appCity;
	}

	public void setAppCity(String appCity) {
		this.appCity = appCity;
	}

	public String getAppCity2() {
		return appCity2;
	}

	public void setAppCity2(String appCity2) {
		this.appCity2 = appCity2;
	}

	public String getAppCity3() {
		return appCity3;
	}

	public void setAppCity3(String appCity3) {
		this.appCity3 = appCity3;
	}

	public String getC4CyCadd1() {
		return c4CyCadd1;
	}

	public void setC4CyCadd1(String c4CyCadd1) {
		this.c4CyCadd1 = c4CyCadd1;
	}

	public String getC4CyCadd2() {
		return c4CyCadd2;
	}

	public void setC4CyCadd2(String c4CyCadd2) {
		this.c4CyCadd2 = c4CyCadd2;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getAppStatus2() {
		return appStatus2;
	}

	public void setAppStatus2(String appStatus2) {
		this.appStatus2 = appStatus2;
	}

	public String getCreditResultDescription() {
		return creditResultDescription;
	}

	public void setCreditResultDescription(String creditResultDescription) {
		this.creditResultDescription = creditResultDescription;
	}

	public String getSynFlag() {
		return synFlag;
	}

	public void setSynFlag(String synFlag) {
		this.synFlag = synFlag;
	}

	public String getBackFlag() {
		return backFlag;
	}

	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}

	public String getCreditDecisionLayer() {
		return creditDecisionLayer;
	}

	public void setCreditDecisionLayer(String creditDecisionLayer) {
		this.creditDecisionLayer = creditDecisionLayer;
	}

	public String getLaoJianFlag() {
		return laoJianFlag;
	}

	public void setLaoJianFlag(String laoJianFlag) {
		this.laoJianFlag = laoJianFlag;
	}

	public String getPubPolice() {
		return pubPolice;
	}

	public void setPubPolice(String pubPolice) {
		this.pubPolice = pubPolice;
	}

	public String getBatchCreditFlag() {
		return batchCreditFlag;
	}

	public void setBatchCreditFlag(String batchCreditFlag) {
		this.batchCreditFlag = batchCreditFlag;
	}

	public String getBatchApprovalFlag() {
		return batchApprovalFlag;
	}

	public void setBatchApprovalFlag(String batchApprovalFlag) {
		this.batchApprovalFlag = batchApprovalFlag;
	}

	public String getCurrUserName() {
		return currUserName;
	}

	public void setCurrUserName(String currUserName) {
		this.currUserName = currUserName;
	}

	public String getExistCardFlag() {
		return existCardFlag;
	}

	public void setExistCardFlag(String existCardFlag) {
		this.existCardFlag = existCardFlag;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public Date getGroupTeamDate() {
		return groupTeamDate;
	}

	public void setGroupTeamDate(Date groupTeamDate) {
		this.groupTeamDate = groupTeamDate;
	}

	public Date getFraudDate() {
		return fraudDate;
	}

	public void setFraudDate(Date fraudDate) {
		this.fraudDate = fraudDate;
	}

	public String getCheck_meuoFlag() {
		return check_meuoFlag;
	}

	public void setCheck_meuoFlag(String check_meuoFlag) {
		this.check_meuoFlag = check_meuoFlag;
	}
	
	public String getZshyInnerCheck() {
		return zshyInnerCheck;
	}

	public void setZshyInnerCheck(String zshyInnerCheck) {
		this.zshyInnerCheck = zshyInnerCheck;
	}

	public int getAllotVersion() {
		return allotVersion;
	}

	public void setAllotVersion(int allotVersion) {
		this.allotVersion = allotVersion;
	}

	public String getRiskScore() {
		return riskScore;
	}

	public void setRiskScore(String riskScore) {
		this.riskScore = riskScore;
	}

	public String getMinRiskScore() {
		return minRiskScore;
	}

	public void setMinRiskScore(String minRiskScore) {
		this.minRiskScore = minRiskScore;
	}

	public String getMaxRiskScore() {
		return maxRiskScore;
	}

	public void setMaxRiskScore(String maxRiskScore) {
		this.maxRiskScore = maxRiskScore;
	}

	public String getPhoneCertification() {
		return phoneCertification;
	}

	public void setPhoneCertification(String phoneCertification) {
		this.phoneCertification = phoneCertification;
	}

	public String getCrediterName() {
		return crediterName;
	}

	public void setCrediterName(String crediterName) {
		this.crediterName = crediterName;
	}

	public String getApproveReturn() {
		return approveReturn;
	}

	public void setApproveReturn(String approveReturn) {
		this.approveReturn = approveReturn;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

}
