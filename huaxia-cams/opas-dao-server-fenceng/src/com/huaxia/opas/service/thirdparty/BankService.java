package com.huaxia.opas.service.thirdparty;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.thirdparty.LoanCardInfo;

/**
 * 第三方-人行接口
 * 
 * @author zhiguo.li
 *
 */
public interface BankService {

	
	
	/**
	 * 个人基本信息查询
	 * @param appId
	 * @return
	 */

	Map<String, String> selectBaseInfo(String appId);
	/**
	 * 居住信息
	 * @param appId
	 * @return
	 */
	
	List<Map<String, String>> selectResideInfo(String appId);
	
	/**
	 * 其他身份信息
	 * @param appId
	 * @return
	 */
	
	List<Map<String, String>> selectOtherBaseInfo(String appId);
	
	/**
	 * 反欺诈
	 * @param appId
	 * @return
	 */
	
	List<Map<String, String>> selectAntiFraudInfo(String appId);
	
	/**
	 * 异议标注信息
	 * @param appId
	 * @return
	 */
	
	List<Map<String, String>> selectDissentHintInfo(String appId);
	/**
	 * 手机信息
	 * @param appId
	 * @return
	 */
	
	List<Map<String, String>> selectPhoneInfo(String appId);
	/**
	 * 职业信息	
	 * @param appId
	 * @return
	 */
	
	List<Map<String, String>> selectProessionInfo(String appId);
	/**
	 * 个人信用报告 数字解读	
	 * @param appId
	 * @return
	 */
	
	List<Map<String, String>> selectReportInfo(String appId);
	/**
	 * 信息提示
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectMessageInfo(String appId);
	
	/**
	 * 被追偿信息汇总
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectRecoveryInfo(String appId);
	
	/**
	 * 逾期信息汇总
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectOverDraftInfo(String appId);
	
	/**
	 * 中征信"信用1000"评分
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectCreditGradeInfo(String appId);
	
	/**
	 * 逾期及违约信息
	 * @param appId
	 * @return
	 */
	
	List<Map<String, String>> selectOverdueInfo(String appId);

	/**
	 *  授信及违约信息		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectCreditInfo(String appId);
	
	/**
	 *  非循环贷账户信息汇总		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectNoRevolveInfo(String appId);
	/**
	 *  循环额度下分账户信息汇总 		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectRevolveLimInfo(String appId);
	/**
	 *  循环贷账户信息汇总		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectRevolveInfo(String appId);
	/**
	 *  准贷记卡账户信息汇总		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectSemiCreditInfo(String appId);
	/**
	 *  相关还款责任信息汇总 个人-担保责任		
	 * @param appId 
	 * @return
	 */
	List<Map<String, String>> selectRepayInfo11(String appId);
	/**
	 *  相关还款责任信息汇总 个人-其他相关还款责任		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectRepayInfo19(String appId);
	/**
	 *  相关还款责任信息汇总 企业-担保责任		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectRepayInfo21(String appId);
	/**
	 *  相关还款责任信息汇总 企业-其他相关还款责任		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectRepayInfo29(String appId);
	
	/**
	 *  后付费业务欠费信息汇总		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectArrgepayInfo(String appId);
	
	/**
	 *  公共信息汇总		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectPublicInfo(String appId);
	
	/**
	 *  上一次查询记录		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectLastQueryRecInfo(String appId);
	
	/**
	 *  最近一次查询记录		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectLastQueryInfoNew(String appId);
	
	/**
	 *  被追偿信息账户		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectC1Info(String appId);
	
	/**
	 *  特殊交易类型		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectSpetradInfo(String appId);
	/**
	 *  非循环贷账户		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectD1Info(String appId);
	/**
	 *  循环额度下分账户		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectR4Info(String appId);
	/**
	 *  循环贷账户		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectR1Info(String appId);
	/**
	 *  贷记卡账户		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectR2Info(String appId);
	/**
	 *  准贷记卡账户		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectR3Info(String appId);
	/**
	 * 最近一次月度表现信息段		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectNearestMInfo(String appId);
	/**
	 * 最近一次月度表现信息段		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectLatest24MonthInfo(String appId);
	/**
	 * 最近5年内的历史表现信息段		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectLatest5YInfo(String appId);
	
	/**
	 * 特殊事件说明信息		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectSpecialEventInfo(String appId);
	
	/**
	 * 标注及声明信息		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectStateInfo(Map map);
	
	/**
	 * 大额		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectDezxInfo(String appId);
	
	/**
	 * 相关还款责任信息-有相关还款责任的个人借款		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectDezx4PersonInfo(String appId);
	/**
	 * 相关还款责任信息-有相关还款责任的企业借款		
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectDezx4GroupInfo(String appId);
	
	/**
	 * 授信协议信息	
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectPcaInfo(String appId);
	/**
	 * 后付费信息	
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectPaymentInfo(String appId);
	
	/**
	 * 欠税记录	
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectTaxArrearInfo(String appId);
	
	/**
	 * 民事判决记录	
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectCivJudgeInfo(String appId);
	
	/**
	 * 强制执行记录
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectForceexeInfo(String appId);
	
	/**
	 * 行政处罚记录
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectAdminPunishInfo(String appId);
	
	/**
	 * 住房公积金参缴记录
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectAccfundInfo(String appId);
	/**
	 * 低保救助记录
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectSalvationInfo(String appId);
	
	/**
	 * 职业资格记录
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectCompetenceInfo(String appId);
	/**
	 * 行政奖励记录
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectAdminAwardInfo(String appId);
	
	/**
	 * 查询记录
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectQueryRecordInfo(String appId);
	/**
	 * 资产处置信息
	 * @param appId
	 * @return
	 */
	
	List<Map<String, String>> selectAssetInfo(String appId);

	/**
	 * 保证人代偿信息
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectAssureInfo(String appId);
	/**
	 * 欠税记录
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectArrearageInfo(String appId);
	/**
	 * 	民事判决记录
	 * @param appId
	 * @return
	 */

	List<Map<String, String>> selectJudgmentInfo(String appId);
	/**
	 * 强制执行记录
	 * @param appId
	 * @return
	 */
	
	List<Map<String, String>> selectForceInfoInfo(String appId);
	/**
	 * 行政处罚记录
	 * @param appId
	 * @return
	 */
	
	List<Map<String, String>> selectPenaltyInfo(String appId);
	/**
	 * 住房公积金参数记录
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectHousingInfo(String appId);
	/**
	 * 养老保险金缴存记录
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectPayAnnuityInfo(String appId);
	/**
	 * 养老保险金发放记录
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectGrantAnnuityInfo(String appId);
	/**
	 * 低保救助记录
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectLowHouseholdInfo(String appId);
	/**
	 * 职业资格记录
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectOccupationInfo(String appId);
	/**
	 * 行政奖励记录
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectAwardInfo(String appId);
	/**
	 * 车辆交易和抵押记录	
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectVehicleInfo(String appId);
	/**
	 * 电信缴费记录
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectTelecomInfo(String appId);
	/**
	 * 本人声明
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectAnnounceInfo(String appId);
	/**
	 * 异议标注
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectDissentInfo(String appId);
	/**
	 *信贷审批查询记录 
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> selectReCreditInfo(String appId);
	
	List<Map<String, String>> selectPersonaldk(String appId);
	/**
	 * 人行摘要信息-标准卡征信审批
	 * 
	 * @param appId
	 *            申请件编号
	 * @return 申请件摘要信息
	 */
	Map<String, String> querySummaryInfoZxSpBzk(String appId);
	
	/**
	 * 人行摘要信息-标准卡征信审批-工作单位、单位地址、更新时间第二条数据
	 * 
	 * @param appId
	 *            申请件编号
	 * @return 申请件摘要信息
	 */
	List<Map<String, String>> querySummaryInfoZxSpBzkYdj2(String appId);
	
	/**
	 * 人行摘要信息-标准卡征信审批/易达金-欠费记录信息
	 * 
	 * @param 
	 * @return 欠费记录信息申请件编号
	 */
	String querySummaryInfoErr1(String appId);
	
	/**
	 * 人行摘要信息-标准卡征信审批/易达金-民事判决记录信息
	 * 
	 * @param 
	 * @return 民事判决记录信息申请件编号
	 */
	String querySummaryInfoErr2(String appId);
	
	/**
	 * 人行摘要信息-标准卡征信审批/易达金-强制执行记录信息
	 * 
	 * @param 
	 * @return 强制执行记录信息申请件编号
	 */
	String querySummaryInfoErr3(String appId);
	
	/**
	 * 人行摘要信息-标准卡征信审批/易达金-养老保险金缴存记录信息
	 * 
	 * @param 
	 * @return 养老保险金缴存记录信息申请件编号
	 */
	String querySummaryInfoErr4(String appId);
	
	/**
	 * 人行摘要信息-标准卡征信审批/易达金-养老保险金发放记录信息
	 * 
	 * @param 
	 * @return 养老保险金发放记录信息申请件编号
	 */
	String querySummaryInfoErr5(String appId);
	
	/**
	 * 人行摘要信息-标准卡征信审批/易达金-行政处罚记录信息
	 * 
	 * @param 
	 * @return 行政处罚记录信息申请件编号
	 */
	String querySummaryInfoErr6(String appId);
	
	/**
	 * 人行摘要信息-标准卡征信审批/易达金-贷款当前逾期总额
	 * 
	 * @param 
	 * @return 贷款当前逾期总额
	 */
	String querySummaryInfoAmt1(String appId);
	
	/**
	 * 人行摘要信息-标准卡征信审批/易达金-信用卡当前逾期总额
	 * 
	 * @param 
	 * @return 信用卡当前逾期总额
	 */
	String querySummaryInfoAmt2(String appId);
	
	/**
	 * 人行摘要信息-标准卡征信审批/易达金-是否列入失信被执行人名单
	 * 
	 * @param 
	 * @return 列入失信被执行人名单
	 */
	List<Map<String,Object>> querySummaryInfoCase(String appId);
	/**
	 * 人行摘要信息-标准卡征信审批/易达金-信用卡状态异常
	 * 
	 * @param 
	 * @return 信用卡状态异常(呆账、止付、冻结)
	 */
	List<String> querySummaryInfoCredit(String appId);
	/**
	 * 人行摘要信息-易达金
	 * 
	 * @param appId
	 *            申请件编号
	 * @return 申请件摘要信息
	 */
	Map<String, String> querySummaryInfoYdj(String appId);
	
	/**
	 * 人行摘要信息-易达金-贷款异常情况
	 * 
	 * @param 
	 * @return 贷款异常情况(关注、次级、可疑、损失)
	 */
	List<String> querySummaryInfoRecSeq(String appId);
	
	/**
	 * 人行摘要信息-易达金-担保信息异常情况
	 * 
	 * @param 
	 * @return 担保信息异常情况(关注、次级、可疑、损失)
	 */
	String querySummaryInfoLoanSort5(String appId);
	
	/**
	 * 人行摘要信息-易达金-到期未结清贷款笔数
	 * 
	 * @param 
	 * @return 到期未结清贷款笔数
	 */
	String querySummaryInfoExpireLoansNum(String appId);
	
	/**
	 * 人行摘要信息-易达金-即将到期贷款笔数
	 * 
	 * @param 
	 * @return 即将到期贷款笔数
	 */
	String querySummaryInfoAboutExpireLoansNum(String appId);
	/**
	 *@Title:findLoanDataByMap
	 *@Description:贷款 根据账户状态，担保方式 等获取排好序的数据
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月12日上午9:05:37
	 */
	List<LoanCardInfo>findLoanDataByMap(Map paramMap);
	/**
	 * 贷款账户数
	 * @param appId
	 * @return
	 */
	String queryLoanAccountsNum(String appId);
	/**
	 * 贷款账户数
	 * @param appId
	 * @return
	 */
	String queryLoanAccountsNum1(String appId);
	List<Map<String, String>> selectDezx4Person(String appId,String flag);
}
