package com.huaxia.opas.service.thirdparty;

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
public interface PBOCService {

	/**
	 * 查询摘要信息
	 * 
	 * @param appId
	 *            申请件编号
	 * @return 申请件摘要信息
	 */
	Map<String, String> querySummaryInfoScBzk(String appId);
	/**
	 *@Title:findListPbocScBzkByAppId
	 *@Description:根据appId查找审查录入人行摘要的前两条数据,封装成一条数据
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月3日上午11:31:22
	 */
	Map<String,Object>findListPbocScBzkByAppId(String appId);
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
	 * 查询详细信息
	 * 
	 * @param appId
	 *            申请件编号
	 * @return 申请件详细信息
	 */
	Map<String, String> selectDetailInfo(String appId);
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
	List<Map<String, String>> selectMessageInfo(String appId);
	
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
	/**
	 * 一个月内的查询机构数
	 * @param appId
	 * @return
	 */
	/*List<Map<String, String>> selectOrganizationInfo(String appId);*/
	/**
	 * 一个月内的查询次数
	 * @param appId
	 * @return
	 */

	Map<String, String> selectAllInfo(String appId);
	/**
	 *@Title:queryPbocAllRecordSummary
	 *@Description:查询总记录汇总(人行详情)
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月7日下午7:13:28
	 */
	Map<String,Object> queryPbocAllRecordSummary(Map paramMap);
	/**
	 * 人行信贷交易明细贷款及贷记卡信息
	 * @param appId
	 * @return
	 */
	List<LoanCardInfo> queryloanCardInfoByAppId(Map map);
	/**
	 *@Title:findLoanCardDataByMap
	 *@Description:贷记卡 、准贷记卡 、根据账户状态，担保方式 等获取排好序的数据
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月11日下午3:05:17
	 */
	List<LoanCardInfo>findLoanCardDataByMap(Map paramMap);
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
	 *@Title:findScBzkMyBankLoanCount
	 *@Description:查询审查bzk我行贷款总条数
	 *@param param
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月3日下午7:32:37
	 */
	int findScBzkMyBankLoanCount(Map param);
	/**
	 *@Title:findScBzkMyBankLoanList
	 *@Description:查询审查bzk我行贷款数据
	 *@param param
	 *@param curNum
	 *@param pageNum
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月3日下午7:44:36
	 */
	List<Map<String,Object>> findScBzkMyBankLoanList(Map param, int curNum, int pageNum);
	/**
	 * 人行信贷交易明细贷款及贷记卡信息
	 * @param appId
	 * @return
	 */
	List<Grurantee> queryGruranteeByAppId(Map map);
	
	/**
	 * 查询人行的家庭地址和单位地址
	 * @author wangtao
	 * @param appId
	 * @return
	 */
	Map<String, String> queryThirtyAddress(String appId);
		/**
	 *@Title:findLoanSpecialDealCount
	 *@Description:人行详情  贷款的 特殊交易查询 数量
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月15日上午10:30:00
	 */
	int findLoanSpecialDealCount(Map paramMap);
	/**
	 *@Title:findLoanSpecialDealByMap
	 *@Description:人行详情  贷款的 特殊交易查询
	 *@param paramMap
	 *@return
	 *@author: gaohui
	 *@Date:2017年7月14日下午8:29:27
	 */
	List<Map<String,Object>>findLoanSpecialDealByMap(Map paramMap);
	
	/**
	 * 查询appId的所有单位名称和单位地址
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> queryPbocCompanyAndAddressByAppId(String appId);
	
	/**
	 * 查询appId的单位电话和手机号码
	 * @param appId
	 * @return
	 */
	List<Map<String, String>> queryCompPhoneByAppId(String appId);
	
	/**
	 * 查询appId的 所有 单位电话
	 * @param appId
	 * @return
	 * @author Mr.Chen
	 */
	List<Map<String, String>> queryAllCompPhoneByAppId(String appId);
	
	/**
	 * 查询appId的 所有 手机号码
	 * @param appId
	 * @return
	 * @author Mr.Chen
	 */
	List<Map<String, String>> queryAllCellphoneByAppId(String appId);
	List<Map<String, String>> queryResidentAddrByAppId(String appId);
	/**
	 *@Title:findCountPbocPersonInfoByAppId
	 *@Description:根据appId查询人行条数 
	 *@param appId
	 *@return
	 *@author: gaohui
	 *@Date:2017年11月1日下午5:45:48
	 */
	int findCountPbocPersonInfoByAppId(String appId);


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
	int queryCurOverdueMax(String appId);


}
