package com.huaxia.opas.service.thirdparty.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.thirdparty.PBOCDao;
import com.huaxia.opas.domain.thirdparty.Grurantee;
import com.huaxia.opas.domain.thirdparty.LoanCardInfo;
import com.huaxia.opas.domain.thirdparty.last5yearOverdue;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.thirdparty.PBOCService;

/**
 * 人行服务实现类
 * 
 * @author zhiguo.li
 *
 */
public class PBOCServiceImpl extends AbstractService implements PBOCService, Serializable {

	private static final long serialVersionUID = 2724788464475014026L;

	@Resource(name = "pbocDao")
	private PBOCDao pbocDao;

	public PBOCDao getPbocDao() {
		return pbocDao;
	}

	public void setPbocDao(PBOCDao pbocDao) {
		this.pbocDao = pbocDao;
	}
	//人行摘要信息-标准卡审查
	@Override
	public Map<String, String> querySummaryInfoScBzk(String appId) {
		return getPbocDao().querySummaryInfoScBzk(appId);
	}
	//人行摘要信息-标准卡征信审批
	@Override
	public Map<String, String> querySummaryInfoZxSpBzk(String appId) {
		return getPbocDao().querySummaryInfoZxSpBzk(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-工作单位、单位地址、更新时间第二条数据
	@Override
	public List<Map<String, String>> querySummaryInfoZxSpBzkYdj2(String appId) {
		return getPbocDao().querySummaryInfoZxSpBzkYdj2(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-欠费记录信息
	@Override
	public  String querySummaryInfoErr1(String appId) {
		return getPbocDao().querySummaryInfoErr1(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-民事判决记录信息
	@Override
	public  String querySummaryInfoErr2(String appId) {
		return getPbocDao().querySummaryInfoErr2(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-强制执行记录信息
	@Override
	public  String querySummaryInfoErr3(String appId) {
		return getPbocDao().querySummaryInfoErr3(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-养老保险金缴存记录信息
	@Override
	public  String querySummaryInfoErr4(String appId) {
		return getPbocDao().querySummaryInfoErr4(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-养老保险金发放记录信息
	@Override
	public  String querySummaryInfoErr5(String appId) {
		return getPbocDao().querySummaryInfoErr5(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-行政处罚记录信息
	@Override
	public  String querySummaryInfoErr6(String appId) {
		return getPbocDao().querySummaryInfoErr6(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-贷款当前逾期总额
	@Override
	public  String querySummaryInfoAmt1(String appId) {
		return getPbocDao().querySummaryInfoAmt1(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-信用卡当前逾期总额
	@Override
	public  String querySummaryInfoAmt2(String appId) {
		return getPbocDao().querySummaryInfoAmt2(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-是否列入失信被执行人名单
	@Override
	public  List<Map<String,Object>>  querySummaryInfoCase(String appId) {
		return getPbocDao().querySummaryInfoCase(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-信用卡状态异常
	@Override
	public  List<String> querySummaryInfoCredit(String appId) {
		return getPbocDao().querySummaryInfoCredit(appId);
	}
	//人行摘要信息-易达金
	@Override
	public Map<String, String> querySummaryInfoYdj(String appId) {
		return getPbocDao().querySummaryInfoYdj(appId);
	}
	//人行摘要信息-易达金-贷款异常情况
	@Override
	public  List<String> querySummaryInfoRecSeq(String appId) {
		return getPbocDao().querySummaryInfoRecSeq(appId);
	}
	//人行摘要信息-易达金-担保信息异常情况
	@Override
	public  String querySummaryInfoLoanSort5(String appId) {
		return getPbocDao().querySummaryInfoLoanSort5(appId);
	}
	//人行摘要信息-易达金-到期未结清贷款笔数
	@Override
	public  String querySummaryInfoExpireLoansNum(String appId) {
		return getPbocDao().querySummaryInfoExpireLoansNum(appId);
	}
	//人行摘要信息-易达金-即将到期贷款笔数
	@Override
	public  String querySummaryInfoAboutExpireLoansNum(String appId) {
		return getPbocDao().querySummaryInfoAboutExpireLoansNum(appId);
	}
	
	@Override
	public Map<String, String> selectDetailInfo(String appId) {
		return getPbocDao().selectDetailInfo(appId);
	}

	@Override
	public Map<String, String> selectBaseInfo(String appId) {
		
		return getPbocDao().selectBaseInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectResideInfo(String appId) {
		return getPbocDao().selectResideInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectProessionInfo(String appId) {
		
		return getPbocDao().selectProessionInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectMessageInfo(String appId) {
		
		return getPbocDao().queryMessageInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectCreditGradeInfo(String appId) {
		return getPbocDao().queryCreditGradeInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectOverdueInfo(String appId) {
		return getPbocDao().queryOverdueInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectCreditInfo(String appId) {
		return getPbocDao().queryCreditInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectAssetInfo(String appId) {
		return getPbocDao().queryAssetInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectAssureInfo(String appId) {
		return getPbocDao().queryAssureInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectArrearageInfo(String appId) {
		return getPbocDao().queryArrearageInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectJudgmentInfo(String appId) {
		return getPbocDao().queryJudgmentInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectForceInfoInfo(String appId) {
		return getPbocDao().queryForceInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectPenaltyInfo(String appId) {
		return getPbocDao().queryPenaltyInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectHousingInfo(String appId) {
		return getPbocDao().queryHousingInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectPayAnnuityInfo(String appId) {
		return getPbocDao().queryPayAnnuityInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectGrantAnnuityInfo(String appId) {
		return getPbocDao().queryGrantAnnuityInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectLowHouseholdInfo(String appId) {
		return getPbocDao().queryLowHouseholdInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectOccupationInfo(String appId) {
		return getPbocDao().queryOccupationInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectAwardInfo(String appId) {
		return getPbocDao().queryAwardInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectVehicleInfo(String appId) {
		return getPbocDao().queryVehicleInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectTelecomInfo(String appId) {
		return getPbocDao().queryTelecomInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectAnnounceInfo(String appId) {
		return getPbocDao().queryAnnounceInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectDissentInfo(String appId) {
		return getPbocDao().queryDissentInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectReCreditInfo(String appId) {
		return getPbocDao().queryReCreditInfo(appId);
	}
/*
	@Override
	public Map<String, String> selectAllInfo(String appId) {
		String queryDegreeInfo =getPbocDao().queryDegreeInfo(appId);
		String queryOrganizationInfo =getPbocDao().queryOrganizationInfo(appId);
		Map<String,String> map = new HashMap<String, String>();
		map.put("sum", queryDegreeInfo);
		map.put("sumO", queryOrganizationInfo);
		return map;
	}*/
	@Override
	public Map<String, String> selectAllInfo(String appId) {
		String queryPaymentInfo =getPbocDao().queryPaymentInfo(appId);
		String queryCartInfo =getPbocDao().queryCartInfo(appId);
		String queryPaymentTimeInfo =getPbocDao().queryPaymentTimeInfo(appId);
		String queryCartTimeInfo =getPbocDao().queryCartTimeInfo(appId);
		String querySelfInfo =getPbocDao().querySelfInfo(appId);
		String queryManage2Y =getPbocDao().queryManage2Y(appId);
		String queryAssure2Y =getPbocDao().queryAssure2Y(appId);
		String queryRealName2Y =getPbocDao().queryRealName2Y(appId);
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("sumH", queryPaymentInfo);
		map.put("sumC", queryCartInfo);
		map.put("sumTimeH", queryPaymentTimeInfo);
		map.put("sumTimeC", queryCartTimeInfo);
		map.put("sumTimeS", querySelfInfo);
		map.put("sumM2Y", queryManage2Y);
		map.put("sumA2Y", queryAssure2Y);
		map.put("sumR2Y", queryRealName2Y);
		return map;
	}

	@Override
	public List<LoanCardInfo> queryloanCardInfoByAppId(Map map) {
		return getPbocDao().queryloanCardInfoByAppId(map);
	}

	@Override
	public LoanCardInfo queryloanCardInfoBySeq(String seq) {
		return getPbocDao().queryloanCardInfoBySeq(seq);
	}

	@Override
	public List<last5yearOverdue> querylast5yearOverdue(Map record) throws CoreException {
		return getPbocDao().querylast5yearOverdue(record);
	}

	@Override
	public int querylast5yearOverdueCount(Map record) throws CoreException {
		return getPbocDao().querylast5yearOverdueCount(record);
	}

	@Override
	public List<Grurantee> queryGruranteeByAppId(Map map) {
		return getPbocDao().queryGruranteeByAppId(map);
	}

	@Override
	public Map<String, String> queryThirtyAddress(String appId) {
		return getPbocDao().queryThirtyAddress(appId);
	}

	@Override
	public Map<String, Object> findListPbocScBzkByAppId(String appId) {
		List<Map<String, Object>>ListPbocSummaryInfoScBzk=pbocDao.selectListPbocScBzkByAppId(appId);
		Map<String,Object>pbocSummaryInfoScBzk=new HashMap<String,Object>();
	   if(!ListPbocSummaryInfoScBzk.isEmpty()){
		int pbiclength=ListPbocSummaryInfoScBzk.size();
		if(pbiclength>=1){
			if(ListPbocSummaryInfoScBzk.get(0)!=null){
				pbocSummaryInfoScBzk=ListPbocSummaryInfoScBzk.get(0);
			}	
		}
		if(pbiclength>=2){
			if(ListPbocSummaryInfoScBzk.get(1)!=null){
				Map<String,Object>pbocSummaryInfoScBzkTwo=ListPbocSummaryInfoScBzk.get(1);
				pbocSummaryInfoScBzk.put("company2",pbocSummaryInfoScBzkTwo.get("company"));	
				pbocSummaryInfoScBzk.put("compAddr2",pbocSummaryInfoScBzkTwo.get("compAddr"));
				pbocSummaryInfoScBzk.put("infoUpdTimeTwo",pbocSummaryInfoScBzkTwo.get("infoUpdTime"));
			}	
		}
	   }
		return pbocSummaryInfoScBzk;
	}

	@Override
	public int findScBzkMyBankLoanCount(Map param) {
		return pbocDao.selectScBzkMyBankLoanCount(param);
	}

	@Override
	public List<Map<String, Object>> findScBzkMyBankLoanList(Map param, int curNum, int pageNum) {
		return pbocDao.selectScBzkMyBankLoanList(param, curNum, pageNum);
	}

	@Override
	public Map<String, Object> queryPbocAllRecordSummary(Map paramMap) {
		return pbocDao.selectPbocAllRecordSummary(paramMap);
	}

	@Override
	public List<LoanCardInfo> findLoanCardDataByMap(Map paramMap) {
		List<LoanCardInfo> loanCardInfos =new LinkedList<LoanCardInfo>();
		Map<String,Object> useMap=new HashMap<String,Object>();
		useMap.put("appId", paramMap.get("appId"));
		useMap.put("messType", paramMap.get("messType"));
		useMap.put("loanOrCreditGuaranteeMode","4");//信用、免担保
		//1 账户状态异常
		useMap.put("loanOrCreditAcctStatus","8");//核销
		List<LoanCardInfo> loanCardListOne=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListOne.isEmpty()){
			loanCardInfos.addAll(loanCardListOne);
		}
		useMap.put("loanOrCreditAcctStatus","5");//呆帐
		List<LoanCardInfo> loanCardListTwo=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListTwo.isEmpty()){
			loanCardInfos.addAll(loanCardListTwo);
		}
		useMap.put("loanOrCreditAcctStatus","3");//止付
		List<LoanCardInfo> loanCardListThree=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListThree.isEmpty()){
			loanCardInfos.addAll(loanCardListThree);
		}
		useMap.put("loanOrCreditAcctStatus","2");//冻结
		List<LoanCardInfo> loanCardListFour=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListFour.isEmpty()){
			loanCardInfos.addAll(loanCardListFour);
		}
		//2逾期  （因为不在贷记卡、准贷记卡的账户状态中 所以不处理）
		//3 正常  1>华夏
		useMap.put("loanOrCreditAcctStatus","1");//正常
		useMap.put("bank","0");//like
		useMap.put("loanOrCreditOrg","华夏银行");//like “华夏银行”
		List<LoanCardInfo> loanCardListFive=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListFive.isEmpty()){
			loanCardInfos.addAll(loanCardListFive);
		}
		//3 正常  1>非华夏
		useMap.put("bank","1");//not like 
		useMap.put("loanOrCreditOrg","华夏银行");//not like “华夏银行”  
		List<LoanCardInfo> loanCardListSix=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListSix.isEmpty()){
			loanCardInfos.addAll(loanCardListSix);
		}
		//4 非“信用/免担保”类型贷记卡
		useMap.put("loanOrCreditAcctStatus",null);
		useMap.put("bank",null);
		useMap.put("loanOrCreditOrg",null);
		useMap.put("loanOrCreditGuaranteeMode","0");//非信用、免担保
		List<LoanCardInfo> loanCardListSeven=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListSeven.isEmpty()){
			loanCardInfos.addAll(loanCardListSeven);
		}
		//==============================
		
		useMap.put("loanOrCreditGuaranteeMode","4");//信用、免担保
		//5转出
		useMap.put("loanOrCreditAcctStatus","7");//转出
		
		List<LoanCardInfo> loanCardListEight=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListEight.isEmpty()){
			loanCardInfos.addAll(loanCardListEight);
		}
		//6未激活
		useMap.put("loanOrCreditAcctStatus","6");//未激活
		List<LoanCardInfo> loanCardListNine=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListNine.isEmpty()){
			loanCardInfos.addAll(loanCardListNine);
		}
		//4销户
		useMap.put("loanOrCreditAcctStatus","4");//销户
		List<LoanCardInfo> loanCardListTen=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListTen.isEmpty()){
			loanCardInfos.addAll(loanCardListTen);
		}
		return loanCardInfos;
	}

	@Override
	public List<LoanCardInfo> findLoanDataByMap(Map paramMap) {
		List<LoanCardInfo> loanCardInfos =new LinkedList<LoanCardInfo>();
		Map<String,Object> useMap=new HashMap<String,Object>();
		useMap.put("appId", paramMap.get("appId"));
		useMap.put("messType", paramMap.get("messType"));
		//1.账户状态异常
		useMap.put("loanOrCreditAcctStatus","6");//核销
		List<LoanCardInfo> loanCardListOne=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListOne.isEmpty()){
			loanCardInfos.addAll(loanCardListOne);
		}
		useMap.put("loanOrCreditAcctStatus","4");//呆账
		List<LoanCardInfo> loanCardListTwo=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListTwo.isEmpty()){
			loanCardInfos.addAll(loanCardListTwo);
		}
		//2.逾期
		useMap.put("loanOrCreditAcctStatus","2");
		List<LoanCardInfo> loanCardListThree=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListThree.isEmpty()){
			loanCardInfos.addAll(loanCardListThree);
		}
		//3 正常  1>华夏
		useMap.put("loanOrCreditAcctStatus","1");//正常
		useMap.put("bank","0");//like
		useMap.put("loanOrCreditOrg","华夏银行");//like “华夏银行”
		List<LoanCardInfo> loanCardListFive=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListFive.isEmpty()){
			loanCardInfos.addAll(loanCardListFive);
		}
		//3 正常  1>非华夏
		useMap.put("bank","1");//not like 
		useMap.put("loanOrCreditOrg","华夏银行");//not like “华夏银行”  
		List<LoanCardInfo> loanCardListSix=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListSix.isEmpty()){
			loanCardInfos.addAll(loanCardListSix);
		}
		//4转出
		useMap.put("loanOrCreditAcctStatus","5");
		useMap.put("bank",null);
		useMap.put("loanOrCreditOrg",null);
		List<LoanCardInfo> loanCardListSeven=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListSeven.isEmpty()){
			loanCardInfos.addAll(loanCardListSeven);
		}
		//5.结清
        useMap.put("loanOrCreditAcctStatus","3");
		List<LoanCardInfo> loanCardListEight=pbocDao.selectLoanCardDataByMap(useMap);
		if(!loanCardListEight.isEmpty()){
			loanCardInfos.addAll(loanCardListEight);
		}
		return loanCardInfos;
	}
	@Override
	public int findLoanSpecialDealCount(Map paramMap) {
		return pbocDao.selectLoanSpecialDealCount(paramMap);
	}
	@Override
	public List<Map<String, Object>> findLoanSpecialDealByMap(Map paramMap) {
		return pbocDao.selectLoanSpecialDealByMap(paramMap);
	}

	@Override
	public List<Map<String, String>> queryPbocCompanyAndAddressByAppId(String appId) {
		
		return pbocDao.queryPbocCompanyAndAddressByAppId(appId);
	}

	@Override
	public List<Map<String, String>> queryCompPhoneByAppId(String appId) {
		return pbocDao.queryCompPhoneByAppId(appId);
	}

	@Override
	public List<Map<String, String>> queryResidentAddrByAppId(String appId) {
		return pbocDao.queryResidentAddrByAppId(appId);
	}

	@Override
	public int findCountPbocPersonInfoByAppId(String appId) {
		return pbocDao.selectCountPbocPersonInfoByAppId(appId);
	}

	@Override
	public int queryMonthApplyCount(String appId) {
		return pbocDao.queryMonthApplyCount(appId);
	}

	@Override
	public int queryNowOrOverdueCount(String appId) {
		return pbocDao.queryNowOrOverdueCount(appId);
	}

	@Override
	public String queryLoanAccountsNum(String appId) {
		return getPbocDao().queryLoanAccountsNum(appId);
	}
	
	@Override
	public List<Map<String, String>> queryAllCompPhoneByAppId(String appId) {
		return pbocDao.queryAllCompPhoneByAppId(appId);
	}
	
	@Override
	public List<Map<String, String>> queryAllCellphoneByAppId(String appId) {
		return pbocDao.queryAllCellphoneByAppId(appId);
	}

	@Override
	public int queryCurOverdueMax(String appId) {
		List<Integer> curOverdueList = pbocDao.queryCurOverdueMax(appId);
		if(curOverdueList.size()==0){
			return 0;
		}
		return curOverdueList.get(0)==null?0:curOverdueList.get(0);
	}

}
