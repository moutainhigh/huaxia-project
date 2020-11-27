package com.huaxia.opas.dao.thirdparty.impl;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.thirdparty.BankDao;
import com.huaxia.opas.domain.thirdparty.LoanCardInfo;

public class BankDaoImpl extends AbstractDAO implements BankDao {

	private static final long serialVersionUID = -5955606481100793793L;
	
	private static final String NAMESPACES = "BANK.";
	@Override
	public Map<String, String> selectBaseInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryBsaeInfo", appId);
	}

	@Override
	public List<Map<String, String>> selectResideInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "selectResideInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> selectOtherBaseInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryOtherBaseInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> selectAntiFraudInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryAntiFraudInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> selectDissentHintInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryDissentHintInfo", appId);
	}
	@Override
	public List<Map<String, String>> selectProessionInfo(String appId) {
	//	List<Map<String, String>> list= getSqlMap().queryForList(NAMESPACES + "queryProessionInfo", appId);
	//	System.err.println(list);
		return getSqlMap().queryForList(NAMESPACES + "queryProessionInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> selectReportInfo(String appId) {
	//	List<Map<String, String>> list= getSqlMap().queryForList(NAMESPACES + "queryProessionInfo", appId);
	//	System.err.println(list);
		return getSqlMap().queryForList(NAMESPACES + "queryReportInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryMessageInfo(String appId) {
		
	//	return getSqlMap().queryForObject(NAMESPACES + "queryMessageInfo", appId);
		return getSqlMap().queryForList(NAMESPACES + "queryMessageInfo", appId);
	}

	@Override
	public List<Map<String, String>> queryRecoveryInfo(String appId) {
		
	//	return getSqlMap().queryForObject(NAMESPACES + "queryMessageInfo", appId);
		return getSqlMap().queryForList(NAMESPACES + "queryRecoveryInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> queryOverDraftInfo(String appId) {
		
	//	return getSqlMap().queryForObject(NAMESPACES + "queryMessageInfo", appId);
		return getSqlMap().queryForList(NAMESPACES + "queryOverDraftInfo", appId);
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
	public List<Map<String, String>> queryNoRevolveInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryNoRevolveInfo", appId);
	}
	@Override
	public List<Map<String, String>> queryRevolveLimInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryRevolveLimInfo", appId);
	}
	@Override
	public List<Map<String, String>> queryRepayInfo11(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryRepayInfo11", appId);
	}
	@Override
	public List<Map<String, String>> queryRepayInfo19(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryRepayInfo19", appId);
	}
	@Override
	public List<Map<String, String>> queryRepayInfo21(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryRepayInfo21", appId);
	}
	@Override
	public List<Map<String, String>> queryRepayInfo29(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryRepayInfo29", appId);
	}
	@Override
	public List<Map<String, String>> queryPublicInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryPublicInfo", appId);
	}
	@Override
	public List<Map<String, String>> queryLastQueryRecInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryLastQueryRecInfo", appId);
	}
	@Override
	public List<Map<String, String>> queryLastQueryInfoNew(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryLastQueryInfoNew", appId);
	}
	@Override
	public List<Map<String, String>> queryC1Info(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryC1Info", appId);
	}
	
	@Override
	public List<Map<String, String>> querySpetradInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "querySpetradInfo", appId);
	}
	@Override
	public List<Map<String, String>> queryD1Info(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryD1Info", appId);
	}
	@Override
	public List<Map<String, String>> queryR4Info(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryR4Info", appId);
	}
	@Override
	public List<Map<String, String>> queryR1Info(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryR1Info", appId);
	}
	@Override
	public List<Map<String, String>> queryR2Info(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryR2Info", appId);
	}
	@Override
	public List<Map<String, String>> queryR3Info(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryR3Info", appId);
	}
	@Override
	public List<Map<String, String>> queryNearestMInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryNearestMInfo", appId);
	}
	@Override
	public List<Map<String, String>> queryLatest24MonthInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryLatest24MonthInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> queryLatest5YInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryLatest5YInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> queryStateInfo(Map map){
		return getSqlMap().queryForList(NAMESPACES + "queryStateInfo", map);
	}
	@Override
	public List<Map<String, String>> queryDezxInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryDezxInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> queryDezx4PersonInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryDezx4PersonInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> queryDezx4GroupInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryDezx4GroupInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> queryPcaInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryPcaInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> queryTelPaymentInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryTelPaymentInfo", appId);
	}                                                 
	
	@Override
	public List<Map<String, String>> queryTaxArrearInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryTaxArrearInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> queryCivJudgeInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryCivJudgeInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> queryForceexeInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryForceexeInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> queryAdminPunishInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryAdminPunishInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> queryAccfundInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryAdminPunishInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> querySalvationInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "querySalvationInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> queryCompetenceInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryCompetenceInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> queryAdminAwardInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryAdminAwardInfo", appId);
	}
	
	@Override
	public List<Map<String, String>> queryQueryRecordInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryQueryRecordInfo", appId);
	}
	@Override
	public List<Map<String, String>> querySpecialEventInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "querySpecialEventInfo", appId);
	}
	@Override
	public List<Map<String, String>> queryArrgepayInfo(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryArrgepayInfo", appId);
	}
	@Override
	public List<Map<String, String>> querySemiCreditInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "querySemiCreditInfo", appId);
	}
	@Override
	public List<Map<String, String>> queryRevolveInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES + "queryRevolveInfo", appId);
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
	public List<Map<String, String>> queryPersonaldk(String appId) {
		return getSqlMap().queryForList(NAMESPACES +"queryPersonaldk", appId);
	}
	@Override
	public List<Map<String, String>> selectPhoneInfo(String appId) {
		return getSqlMap().queryForList(NAMESPACES+"queryPhoneNum", appId);
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
	public List<LoanCardInfo> selectLoanCardDataByMap(Map map) {
		return getSqlMap().queryForList(NAMESPACES +"selectLoanCardDataByMap", map);
	}

	@Override
	public String queryLoanAccountsNum(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryLoanAccountsNum", appId);
	}
	
	@Override
	public String queryLoanAccountsNum1(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryLoanAccountsNum1", appId);
	}
	
	@Override
	public Map<String, String> queryBankInfo(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "queryBankInfo", appId);
	}
	@Override
	public List<Map<String, String>> queryDezx4Person(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryDezx4Person", appId);
	}
	
	@Override
	public List<Map<String, String>> queryDezx4PersonOther(String appId){
		return getSqlMap().queryForList(NAMESPACES + "queryDezx4PersonOther", appId);
	}
}
