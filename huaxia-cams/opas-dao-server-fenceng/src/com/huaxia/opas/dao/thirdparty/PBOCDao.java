package com.huaxia.opas.dao.thirdparty;

import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.thirdparty.Grurantee;
import com.huaxia.opas.domain.thirdparty.LoanCardInfo;
import com.huaxia.opas.domain.thirdparty.last5yearOverdue;

/**
 * 第三方-人行接口
 * 
 * @author zhiguo.li
 *
 */
public interface PBOCDao {

	/**
	 * 人行摘要信息-标准卡审查
	 * 
	 * @param appId
	 *            申请件编号
	 * @return 申请件摘要信息
	 */
	Map<String, String> querySummaryInfoScBzk(String appId);
	/**
	 *@Title:selectListPbocScBzkByAppId
	 *@Description:根据appId查找审查录入人行摘要的前两条数据
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月3日上午11:37:39
	 */
	List<Map<String,Object>>selectListPbocScBzkByAppId(String appId);
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
	 * 查询详细信息
	 * 
	 * @param appId
	 *            申请件编号
	 * @return 申请件详细信息
	 */
	Map<String, String> selectDetailInfo(String appId);
	/**
	 *  人行摘要信息
	 */
	Map<String, String> queryPbocInfo(String appId);
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
 * 职业信息	
 * @param appId
 * @return
 */

	List<Map<String, String>> selectProessionInfo(String appId);
/**
 * 信息提示
 * @param appId
 * @return
 */
	
	List<Map<String, String>> queryMessageInfo(String appId);
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
 * 一个月内的查询机构数  货款审批
 * @param appId
 * @return
 */
	String queryPaymentInfo(String appId);
/**
 * 一个月内的查询机构数 信用卡审批
 * @param appId
 * @return
 */

	String queryCartInfo(String appId);
/**
 * 一个月内的次数 本人查询
 * @param appId
 * @return
 */
	String querySelfInfo(String appId);
/**
 * 一个月内的查询次数  货款审批
 * @param appId
 * @return
 */
	String queryPaymentTimeInfo(String appId);
/**
 * 一个月内的查询次数   货款审批
 * @param appId
 * @return
 */
	String queryCartTimeInfo(String appId);
/**
 *最近2年内的查询次数 货后管理
 * @param appId
 * @return
 */
	String queryManage2Y(String appId);
/**
 * 最近2年内的查询次数 担保资格审查
 * @param appId
 * @return
 */
	String queryAssure2Y(String appId);
/**
 * 最近2年内的查询次数 特约商户实名审查
 * @param appId
 * @return
 */
	String queryRealName2Y(String appId);
	/**
	 * 人行信贷交易明细贷款及贷记卡信息
	 * @param appId
	 * @return
	 */
	List<LoanCardInfo> queryloanCardInfoByAppId(Map map);
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
	 * 人行信贷交易明细贷款及贷记卡信息
	 * @param appId
	 * @return
	 */
	LoanCardInfo queryloanCardInfoBySeq(String seq);

	/**
	 * 进5年逾期记录
	 * @param map
	 * @return
	 */
	List<last5yearOverdue>  querylast5yearOverdue(Map record) throws CoreException;
	
	/**
	 * 进5年逾期记录
	 * @param map
	 * @return
	 */
	int querylast5yearOverdueCount(Map record) throws CoreException;
	/**
	 *@Title:selectScBzkMyBankLoanCount
	 *@Description:查询审查bzk我行贷款总条数
	 *@param param
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月3日下午7:34:50
	 */
	int selectScBzkMyBankLoanCount(Map param);
	/**
	 *@Title:selectScBzkMyBankLoanList
	 *@Description:查询审查bzk我行贷款数据
	 *@param param
	 *@param curNum
	 *@param pageNum
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月3日下午7:46:46
	 */
	List<Map<String,Object>> selectScBzkMyBankLoanList(Map param,int curNum,int pageNum);
	/**
	 * 人行信贷交易明细贷款及贷记卡信息
	 * @param appId
	 * @return
	 */
	List<Grurantee> queryGruranteeByAppId(Map map);
	/**
	 *@Title:selectListPbocInfoPersonBankMessage
	 *@Description:获取银行摘要信息
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2017年4月27日下午2:04:57
	 */
	public List<Map>selectListPbocInfoPersonBankMessage(String appId);
	
	/**
	 *@Description:问题库答案查询
	 *@param appId
	 *@author: 安东
	 *@Date:2017年5月9日
	 */
	Map<String, String> queryMonthAvePayById(String appId);
	Map<String, String> queryOriCompName(String appId);
	/**
	 *@Description:是否存在贷款情况
	 *@param appId
	 *@author: 安东
	 *@Date:2017年6月15日
	 */
	Map<Object, Object>  queryLoanNoInfo(String appId);
	/**
	 *@Description:首行发卡日期
	 *@param appId
	 *@author: 安东
	 *@Date:2017年6月15日
	 */
	List<Map<String, String>> queryloanTypeById(String appId);
	
	/**
	 * 查询人行的家庭住址和单位地址
	 * @author wangtao
	 * @param appId
	 * @return
	 */
	Map<String, String> queryThirtyAddress(String appId);
	/**
	 *@Description:贷款种类，时间，金额，月还款额
	 *@param appId
	 *@author: 安东
	 *@Date:2017年6月15日
	 */
	List<Map<String, String>> quertPbocLoadCardInfo(String appId);
	/**
	 *@Title:selectPbocAllRecordSummary
	 *@Description:查询总记录汇总(人行详情)
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月7日下午7:18:01
	 */
	Map<String,Object> selectPbocAllRecordSummary(Map paramMap);
	/**
	 *@Title:selectLoanSpecialDealCount
	 *@Description:人行详情  贷款的 特殊交易查询 数量
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月15日上午10:31:57
	 */
	int selectLoanSpecialDealCount(Map paramMap);
	/**
	 *@Title:selectLoanSpecialDealByMap
	 *@Description:人行详情  贷款的 特殊交易查询
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月14日下午8:31:31
	 */
	List<Map<String,Object>>selectLoanSpecialDealByMap(Map paramMap);
	
	List<Map<String, String>> queryPbocCompanyAndAddressByAppId(String appId);
	List<Map<String, String>> queryCompPhoneByAppId(String appId);
	
	/**
	 * 查询appId所有的单位电话
	 * @param appId
	 * @return
	 * @author Mr.Chen
	 */
	List<Map<String, String>> queryAllCompPhoneByAppId(String appId);
	
	/**
	 * 查询appId所有的手机号码
	 * @param appId
	 * @return
	 * @author Mr.Chen
	 */
	List<Map<String, String>> queryAllCellphoneByAppId(String appId);
	List<Map<String, String>> queryResidentAddrByAppId(String appId);
	/**
	 *@Title:selectCountPbocPersonInfoByAppId
	 *@Description:根据appId查询条数
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2017年11月1日下午5:50:07
	 */
	int selectCountPbocPersonInfoByAppId(String appId);
	/**
	 * 一个月申请查询机构数
	 * @param 
	 * @return
	 */
	int queryMonthApplyCount(String appId);
	
	/**
	 * 历史或者当前逾期
	 * @param appId
	 * @return
	 */
	int queryNowOrOverdueCount(String appId);
	/**
	 * 贷款账户数
	 * @param appId
	 * @return
	 */
	String queryLoanAccountsNum(String appId);
	/**
	 * 历史或者当前逾期 二代人行
	 * @param appId
	 * @return
	 */
	List<Integer> queryCurOverdueMax(String appId);

}
