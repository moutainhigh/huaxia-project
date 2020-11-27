package com.huaxia.opas.dao.thirdparty;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.thirdparty.LoanCardInfo;

/**
 * 第三方-人行接口
 * 
 * @author zhiguo.li
 *
 */
public interface BankDao {

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
 * 其他身份信息	
 * @param appId
 * @return
 */
		
	List<Map<String, String>> selectAntiFraudInfo(String appId);
	
/**
 * 异议信息提示	
 * @param appId
 * @return
 */
		
	List<Map<String, String>> selectDissentHintInfo(String appId);
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
	
	List<Map<String, String>> queryMessageInfo(String appId);
	
/**
 * 被追偿信息汇总
 * @param appId
 * @return
 */
	
	List<Map<String, String>> queryRecoveryInfo(String appId);
	
/**
 * 逾期信息汇总
 * @param appId
 * @return
 */
	
	List<Map<String, String>> queryOverDraftInfo(String appId);
	
/**
 *  中征信"信用1000"评分	
 * @param appId
 * @return
 */
	
	List<Map<String, String>> queryCreditGradeInfo(String appId);
	
	
/**
 * 逾期及违约信息	
 * @param appId
 * @return
 */
	List<Map<String, String>> queryOverdueInfo(String appId);

/**
 * 	 授信及违约信息	
 * @param appId
 * @return
 */
	List<Map<String, String>> queryCreditInfo(String appId);
	
/**
 * 	 非循环贷账户信息汇总	
 * @param appId
 * @return
 */
	List<Map<String, String>> queryNoRevolveInfo(String appId);
/**
 * 	循环额度下分账户信息汇总 	
 * @param appId
 * @return
 */
	List<Map<String, String>> queryRevolveLimInfo(String appId);
/**
 * 	 循环贷账户信息汇总	
 * @param appId
 * @return
 */
	List<Map<String, String>> queryRevolveInfo(String appId);
	
/**
 * 	 准贷记卡账户信息汇总	
 * @param appId
 * @return
 */
	List<Map<String, String>> querySemiCreditInfo(String appId);
	
/**
 * 	 	
 * @param appId
 * @return
 */
	List<Map<String, String>> queryRepayInfo11(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryRepayInfo19(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryRepayInfo21(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryRepayInfo29(String appId);

/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryArrgepayInfo(String appId);

/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryPublicInfo(String appId);

/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryLastQueryRecInfo(String appId);

/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryLastQueryInfoNew(String appId);

/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryC1Info(String appId);

/**
 * 	 
 * @param appId
 * @return
 */
List<Map<String, String>> querySpetradInfo(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryD1Info(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryR4Info(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryR1Info(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryR2Info(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryR3Info(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryNearestMInfo(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryLatest24MonthInfo(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryStateInfo(Map map);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryDezxInfo(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryDezx4PersonInfo(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryDezx4GroupInfo(String appId);

/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryPcaInfo(String appId);

/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryTelPaymentInfo(String appId);

/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryTaxArrearInfo(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryCivJudgeInfo(String appId);

/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryForceexeInfo(String appId);

/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryAdminPunishInfo(String appId);

/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryAccfundInfo(String appId);

/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> querySalvationInfo(String appId);

/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryCompetenceInfo(String appId);

/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryAdminAwardInfo(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryQueryRecordInfo(String appId);
/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> queryLatest5YInfo(String appId);

/**
 * 	 	
 * @param appId
 * @return
 */
List<Map<String, String>> querySpecialEventInfo(String appId);
/**
 * 资产处置信息
 * @param appId
 * @return
 */
List<Map<String, String>> queryAssetInfo(String appId);
/**
 * 保证人代偿信息	
 * @param appId
 * @return
 */
	List<Map<String, String>> queryAssureInfo(String appId);
/**
 * 欠税记录
 * @param appId
 * @return
 */
	List<Map<String, String>> queryArrearageInfo(String appId);
/**
 * 民事判决记录
 * @param appId
 * @return
 */
	List<Map<String, String>> queryJudgmentInfo(String appId);
/**
 * 强制执行记录
 * @param appId
 * @return
 */
	List<Map<String, String>> queryForceInfo(String appId);
/**
 * 行政处罚记录
 * @param appId
 * @return
 */
	List<Map<String, String>> queryPenaltyInfo(String appId);
/**
 * 住房公积金参数记录
 * @param appId
 * @return
 */
	List<Map<String, String>> queryHousingInfo(String appId);
/**
 * 养老保险金缴存记录
 * @param appId
 * @return
 */
	List<Map<String, String>> queryPayAnnuityInfo(String appId);
/**
 * 养老保险金发放记录
 * @param appId
 * @return
 */
	List<Map<String, String>> queryGrantAnnuityInfo(String appId);
/**
 * 低保救助记录
 * @param appId
 * @return
 */
	List<Map<String, String>> queryLowHouseholdInfo(String appId);
/**
 * 职业资格记录
 * @param appId
 * @return
 */
	
	List<Map<String, String>> queryOccupationInfo(String appId);
/**
 * 行政奖励记录
 * @param appId
 * @return
 */
	
	List<Map<String, String>> queryAwardInfo(String appId);
/**
 * 车辆交易和抵押记录	
 * @param appId
 * @return
 */
	
	List<Map<String, String>> queryVehicleInfo(String appId);
/**
 * 电信缴费记录
 * @param appId
 * @return
 */
	List<Map<String, String>> queryTelecomInfo(String appId);
/**
 * 本人声明
 * @param appId
 * @return
 */
	List<Map<String, String>> queryAnnounceInfo(String appId);
/**
 * 异议标注	
 * @param appId
 * @return
 */
	List<Map<String, String>> queryDissentInfo(String appId);
/**
 * 信贷审批查询记录	
 * @param appId
 * @return
 */
	List<Map<String, String>> queryReCreditInfo(String appId);
	/**
	 * 我行个人贷款情况
	 * @param appId
	 * @return
	 */
		List<Map<String, String>> queryPersonaldk(String appId);
	/**
	 * 手机信息	
	 * @param appId
	 * @return
	 */
		
	List<Map<String, String>> selectPhoneInfo(String appId);

	/**
	 * 人行摘要信息-标准卡征信审批
	 * 
	 * @param appId
	 *            申请件编号
	 * @return 申请件摘要信息
	 */
	Map<String, String> querySummaryInfoZxSpBzk(String appId);
	
	/**
	 * 人行摘要信息-标准卡征信审批/易达金-工作单位、单位地址、更新时间第二条数据
	 * 
	 * @param appId
	 *            申请件编号
	 * @return 申请件摘要信息
	 */
	List<Map<String, String>> querySummaryInfoZxSpBzkYdj2(String appId);
	
	/**
	 * 人行摘要信息-标准卡征信审批-欠费记录信息
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
	List<Map<String,Object>>  querySummaryInfoCase(String appId);
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
	 * @return 到期未结清贷款笔数
	 */
	String querySummaryInfoAboutExpireLoansNum(String appId);
	/**
	 *@Title:selectLoanCardDataByMap
	 *@Description:根据账户状态，担保方式 等获取排好序的数据
	 *@param map
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月11日下午3:21:53
	 */
	List<LoanCardInfo>selectLoanCardDataByMap(Map map);
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
	/**
	 *  二代人行摘要信息
	 */
	Map<String, String> queryBankInfo(String appId);
	List<Map<String, String>> queryDezx4Person(String appId);
	List<Map<String, String>> queryDezx4PersonOther(String appId);
}
