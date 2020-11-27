package com.huaxia.opas.dao.thirdparty.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.PBOCDao;
import com.huaxia.opas.domain.thirdparty.Grurantee;
import com.huaxia.opas.domain.thirdparty.LoanCardInfo;
import com.huaxia.opas.domain.thirdparty.last5yearOverdue;

public class PBOCDaoImpl extends AbstractDAO implements PBOCDao {

	
	private static final long serialVersionUID = -3886967099526368171L;
	private static final String NAMESPACES = "PBOC.";
	@Override
	public Map<String, String> querySummaryInfoScBzk(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoScBzk", appId);
	}
	@Override
	public Map<String, String> querySummaryInfoZxSpBzk(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoZxSpBzk", appId);
	}
	@Override
	public List<Map<String, String>> querySummaryInfoZxSpBzkYdj2(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "querySummaryInfoZxSpBzk2", appId);
	}
	@Override
	public String querySummaryInfoErr1(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoErr1", appId);
	}
	@Override
	public String querySummaryInfoErr2(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoErr2", appId);
	}
	@Override
	public String querySummaryInfoErr3(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoErr3", appId);
	}
	@Override
	public String querySummaryInfoErr4(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoErr4", appId);
	}
	@Override
	public String querySummaryInfoErr5(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoErr5", appId);
	}
	@Override
	public String querySummaryInfoErr6(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoErr6", appId);
	}
	@Override
	public String querySummaryInfoAmt1(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoAmt1", appId);
	}
	@Override
	public String querySummaryInfoAmt2(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoAmt2", appId);
	}
	@Override
	public List<Map<String,Object>>  querySummaryInfoCase(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "querySummaryInfoCase", appId);
	}
	@Override
	public List<String> querySummaryInfoCredit(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "querySummaryInfoCredit", appId);
	}
	@Override
	public Map<String, String> querySummaryInfoYdj(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoYdj", appId);
	}
	@Override
	public List<String> querySummaryInfoRecSeq(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "querySummaryInfoRecSeq", appId);
	}
	@Override
	public String querySummaryInfoLoanSort5(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoLoanSort5", appId);
	}
	@Override
	public String querySummaryInfoExpireLoansNum(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoExpireLoansNum", appId);
	}
	@Override
	public String querySummaryInfoAboutExpireLoansNum(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "querySummaryInfoAboutExpireLoansNum", appId);
	}
	@Override
	public Map<String, String> selectDetailInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryDetailInfo", appId);
	}
	@Override
	public Map<String, String> queryPbocInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryPbocInfo", appId);
	}
	@Override
	public Map<String, String> selectBaseInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryBsaeInfo", appId);
	}

	@Override
	public List<Map<String, String>> selectResideInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectResideInfo", appId);
	}

	@Override
	public List<Map<String, String>> selectProessionInfo(String appId) {
	//	List<Map<String, String>> list= getSqlMap().queryForList(NAMESPACES + "queryProessionInfo", appId);
	//	System.err.println(list);
		return getSqlMap().queryForList(NAMESPACES + "queryProessionInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryMessageInfo(String appId) {
		
	//	return getSqlMap().queryForObject(NAMESPACES + "queryMessageInfo", appId);
		return getSqlMap().queryForList(NAMESPACES + "queryMessageInfo", appId);
	}


	@Override
	public List<Map<String, String>> queryCreditGradeInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryCreditGradeInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> queryOverdueInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryOverdueInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryCreditInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryCreditInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryAssetInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryAssetInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryAssureInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryAssureInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryArrearageInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryArrearageInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryJudgmentInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryJudgmentInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryForceInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryForceInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryPenaltyInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryPenaltyInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryHousingInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryHousingInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryPayAnnuityInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryPayAnnuityInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryGrantAnnuityInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryGrantAnnuityInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryLowHouseholdInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryLowHouseholdInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryOccupationInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryOccupationInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryAwardInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryAwardInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryVehicleInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryVehicleInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryTelecomInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryTelecomInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryAnnounceInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryAnnounceInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryDissentInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryDissentInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryReCreditInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryReCreditInfo", appId);
	}

	@Override
	public String queryPaymentInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES +"queryPaymentInfo", appId);
	}

	@Override
	public String queryCartInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES +"queryCartInfo", appId);
	}

	@Override
	public String querySelfInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES +"querySelfInfo", appId);
	}

	@Override
	public String queryPaymentTimeInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES +"queryPaymentTimeInfo", appId);
	}

	@Override
	public String queryCartTimeInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES +"queryCartTimeInfo", appId);
	}

	@Override
	public String queryManage2Y(String appId) {
		return getSqlMap().queryForObject(NAMESPACES +"queryManage2Y", appId);
	}

	@Override
	public String queryAssure2Y(String appId) {
		return getSqlMap().queryForObject(NAMESPACES +"queryAssure2Y", appId);
	}

	@Override
	public String queryRealName2Y(String appId) {
		return getSqlMap().queryForObject(NAMESPACES +"queryRealName2Y", appId);
	}

	@Override
	public List<LoanCardInfo> queryloanCardInfoByAppId(Map map) {
		return getSqlMap().queryForList(NAMESPACES +"queryloanCardInfoByAppId", map);
	}

	@Override
	public LoanCardInfo queryloanCardInfoBySeq(String seq){
		return getSqlMap().queryForObject(NAMESPACES +"queryloanCardInfoById", seq);
	}

	@Override
	public List<last5yearOverdue> querylast5yearOverdue(Map map) {
		return getSqlMap().queryForList(NAMESPACES +"querylast5yearOverdue", map);
	}
	@Override
	public int querylast5yearOverdueCount(Map record){
		return getSqlMap().queryForObject(NAMESPACES + "querylast5yearOverdueCount", record);
	}

	@Override
	public List<Grurantee> queryGruranteeByAppId(Map map) {
		return getSqlMap().queryForList(NAMESPACES +"queryGruranteeByAppId", map);
	}

	@Override
	public List<Map> selectListPbocInfoPersonBankMessage(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectListPbocInfoPersonBankMessage", appId);
	}
	
	@Override
	public Map<String, String> queryMonthAvePayById(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryMonthAvePayById", appId);
	}

	@Override
	public Map<String, String> queryOriCompName(String appId) {
		return getSqlMap().queryForObject(NAMESPACES +"queryOriCompName", appId);
	}
	
	@Override
	public Map<Object, Object>  queryLoanNoInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "quertLoanSituation", appId);
	}
	
	@Override
	public List<Map<String, String>> queryloanTypeById(String appId){
		return getSqlMap().queryForList(NAMESPACES +"queryloanTypeById", appId);
	}
	@Override
	public Map<String, String> queryThirtyAddress(String appId) {
		return getSqlMap().queryForObject(NAMESPACES +"queryThirtyAddress", appId);
	}
	@Override
	public List<Map<String, String>> quertPbocLoadCardInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"quertPbocLoadCardInfo", appId);
	}
	@Override
	public List<Map<String, Object>> selectListPbocScBzkByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectListPbocScBzkByAppId", appId);
	}
	@Override
	public int selectScBzkMyBankLoanCount(Map param) {
		return getSqlMap().queryForObject(NAMESPACES + "selectScBzkMyBankLoanCount", param);
	}
	@Override
	public List<Map<String, Object>> selectScBzkMyBankLoanList(Map param, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES +"selectScBzkMyBankLoanList", param, curNum, pageNum);
	}
	@Override
	public Map<String, Object> selectPbocAllRecordSummary(Map paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectPbocAllRecordSummary", paramMap);
	}
	@Override
	public List<LoanCardInfo> selectLoanCardDataByMap(Map map) {
		return getSqlMap().queryForList(NAMESPACES +"selectLoanCardDataByMap", map);
	}
	@Override
	public List<Map<String, Object>> selectLoanSpecialDealByMap(Map paramMap) {
		return getSqlMap().queryForList(NAMESPACES +"selectLoanSpecialDealByMap", paramMap);
	}
	@Override
	public int selectLoanSpecialDealCount(Map paramMap) {
		return getSqlMap().queryForObject(NAMESPACES + "selectLoanSpecialDealCount", paramMap);
	}
	@Override
	public List<Map<String, String>> queryPbocCompanyAndAddressByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryPbocCompanyAndAddressByAppId", appId);
	}
	@Override
	public List<Map<String, String>> queryCompPhoneByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryCompPhoneByAppId", appId);
	}
	@Override
	public List<Map<String, String>> queryResidentAddrByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryResidentAddrByAppId", appId);
	}

	@Override
	public int selectCountPbocPersonInfoByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectCountPbocPersonInfoByAppId", appId);
	}

	@Override
	public int queryMonthApplyCount(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryMonthApplyCount", appId);
	}
	@Override
	public int queryNowOrOverdueCount(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"queryNowOrOverdueCount", appId);
	}
	@Override
	public String queryLoanAccountsNum(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryLoanAccountsNum", appId);
	}

	@Override
	public List<Map<String, String>> queryAllCompPhoneByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryAllCompPhoneByAppId", appId);
	}
	@Override
	public List<Map<String, String>> queryAllCellphoneByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryAllCellphoneByAppId", appId);
	}

	@Override
	public List<Integer> queryCurOverdueMax(String appId) {
		return getSqlMap().queryForList(NAMESPACES+"queryCurOverdueMax", appId);
	}

	
}
