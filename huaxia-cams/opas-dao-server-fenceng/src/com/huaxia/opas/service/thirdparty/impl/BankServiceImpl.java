package com.huaxia.opas.service.thirdparty.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.thirdparty.BankDao;
import com.huaxia.opas.domain.thirdparty.Grurantee;
import com.huaxia.opas.domain.thirdparty.LoanCardInfo;
import com.huaxia.opas.domain.thirdparty.last5yearOverdue;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.thirdparty.BankService;

/**
 * 人行服务实现类
 * 
 * @author zhiguo.li
 *
 */
public class BankServiceImpl extends AbstractService implements BankService, Serializable {

	private static final long serialVersionUID = 2724788464475014026L;

	@Resource(name = "bankDao")
	private BankDao bankDao;

	public BankDao getBankDao() {
		return bankDao;
	}

	public void setBankDao(BankDao bankDao) {
		this.bankDao = bankDao;
	}

	@Override
	public Map<String, String> selectBaseInfo(String appId) {
		
		return getBankDao().selectBaseInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectResideInfo(String appId) {
		return getBankDao().selectResideInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectOtherBaseInfo(String appId) {
		return getBankDao().selectOtherBaseInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectAntiFraudInfo(String appId) {
		return getBankDao().selectAntiFraudInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectDissentHintInfo(String appId) {
		return getBankDao().selectDissentHintInfo(appId);
	}
	@Override
	public List<Map<String, String>> selectPhoneInfo(String appId) {
		return getBankDao().selectPhoneInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectProessionInfo(String appId) {
		
		return getBankDao().selectProessionInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectReportInfo(String appId) {
		
		return getBankDao().selectReportInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectMessageInfo(String appId) {
		
		return getBankDao().queryMessageInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectRecoveryInfo(String appId) {
		
		return getBankDao().queryRecoveryInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectOverDraftInfo(String appId) {
		
		return getBankDao().queryOverDraftInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectCreditGradeInfo(String appId) {
		return getBankDao().queryCreditGradeInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectOverdueInfo(String appId) {
		return getBankDao().queryOverdueInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectCreditInfo(String appId) {
		return getBankDao().queryCreditInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectNoRevolveInfo(String appId) {
		return getBankDao().queryNoRevolveInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectRevolveLimInfo(String appId) {
		return getBankDao().queryRevolveLimInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectRevolveInfo(String appId) {
		return getBankDao().queryRevolveInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectSemiCreditInfo(String appId) {
		return getBankDao().querySemiCreditInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectRepayInfo11(String appId) {
		return getBankDao().queryRepayInfo11(appId);
	}
	@Override
	public List<Map<String, String>> selectRepayInfo19(String appId) {
		return getBankDao().queryRepayInfo19(appId);
	}
	@Override
	public List<Map<String, String>> selectRepayInfo21(String appId) {
		return getBankDao().queryRepayInfo21(appId);
	}
	@Override
	public List<Map<String, String>> selectRepayInfo29(String appId) {
		return getBankDao().queryRepayInfo29(appId);
	}
	
	@Override
	public List<Map<String, String>> selectArrgepayInfo(String appId) {
		return getBankDao().queryArrgepayInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectPublicInfo(String appId) {
		return getBankDao().queryPublicInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectLastQueryRecInfo(String appId) {
		return getBankDao().queryLastQueryRecInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectLastQueryInfoNew(String appId) {
		return getBankDao().queryLastQueryInfoNew(appId);
	}
	
	@Override
	public List<Map<String, String>> selectC1Info(String appId) {
		return getBankDao().queryC1Info(appId);
	}
	
	@Override
	public List<Map<String, String>> selectSpetradInfo(String appId) {
		return getBankDao().querySpetradInfo(appId);
	}
	@Override
	public List<Map<String, String>> selectD1Info(String appId) {
		return getBankDao().queryD1Info(appId);
	}
	@Override
	public List<Map<String, String>> selectR4Info(String appId) {
		return getBankDao().queryR4Info(appId);
	}
	@Override
	public List<Map<String, String>> selectR1Info(String appId) {
		return getBankDao().queryR1Info(appId);
	}
	@Override
	public List<Map<String, String>> selectR2Info(String appId) {
		return getBankDao().queryR2Info(appId);
	}
	@Override
	public List<Map<String, String>> selectR3Info(String appId) {
		return getBankDao().queryR3Info(appId);
	}
	@Override
	public List<Map<String, String>> selectNearestMInfo(String appId) {
		return getBankDao().queryNearestMInfo(appId);
	}
	@Override
	public List<Map<String, String>> selectLatest5YInfo(String appId) {
		return getBankDao().queryLatest5YInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectSpecialEventInfo(String appId) {
		return getBankDao().querySpecialEventInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectLatest24MonthInfo(String appId) {
		return getBankDao().queryLatest24MonthInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectStateInfo(Map map) {
		return getBankDao().queryStateInfo(map);
	}
	
	@Override
	public List<Map<String, String>> selectDezxInfo(String appId) {
		return getBankDao().queryDezxInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectDezx4PersonInfo(String appId) {
		return getBankDao().queryDezx4PersonInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectDezx4GroupInfo(String appId) {
		return getBankDao().queryDezx4GroupInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectPcaInfo(String appId) {
		return getBankDao().queryPcaInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectPaymentInfo(String appId) {
		return getBankDao().queryTelPaymentInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectTaxArrearInfo(String appId) {
		return getBankDao().queryTaxArrearInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectCivJudgeInfo(String appId) {
		return getBankDao().queryCivJudgeInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectForceexeInfo(String appId) {
		return getBankDao().queryForceexeInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectAdminPunishInfo(String appId) {
		return getBankDao().queryAdminPunishInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectAccfundInfo(String appId) {
		return getBankDao().queryAccfundInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectSalvationInfo(String appId) {
		return getBankDao().querySalvationInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectCompetenceInfo(String appId) {
		return getBankDao().queryCompetenceInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectAdminAwardInfo(String appId) {
		return getBankDao().queryAdminAwardInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectQueryRecordInfo(String appId) {
		return getBankDao().queryQueryRecordInfo(appId);
	}
	@Override
	public List<Map<String, String>> selectAssetInfo(String appId) {
		return getBankDao().queryAssetInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectAssureInfo(String appId) {
		return getBankDao().queryAssureInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectArrearageInfo(String appId) {
		return getBankDao().queryArrearageInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectJudgmentInfo(String appId) {
		return getBankDao().queryJudgmentInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectForceInfoInfo(String appId) {
		return getBankDao().queryForceInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectPenaltyInfo(String appId) {
		return getBankDao().queryPenaltyInfo(appId);
	}
	@Override
	public List<Map<String, String>> selectHousingInfo(String appId) {
		return getBankDao().queryHousingInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectPayAnnuityInfo(String appId) {
		return getBankDao().queryPayAnnuityInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectGrantAnnuityInfo(String appId) {
		return getBankDao().queryGrantAnnuityInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectLowHouseholdInfo(String appId) {
		return getBankDao().queryLowHouseholdInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectOccupationInfo(String appId) {
		return getBankDao().queryOccupationInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectAwardInfo(String appId) {
		return getBankDao().queryAwardInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectVehicleInfo(String appId) {
		return getBankDao().queryVehicleInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectTelecomInfo(String appId) {
		return getBankDao().queryTelecomInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectAnnounceInfo(String appId) {
		return getBankDao().queryAnnounceInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectDissentInfo(String appId) {
		return getBankDao().queryDissentInfo(appId);
	}

	@Override
	public List<Map<String, String>> selectReCreditInfo(String appId) {
		return getBankDao().queryReCreditInfo(appId);
	}
	
	@Override
	public List<Map<String, String>> selectPersonaldk(String appId) {
		return getBankDao().queryPersonaldk(appId);
	}
	//---------------------------------------------------------------------------------------
	//人行摘要信息-标准卡征信审批
	@Override
	public Map<String, String> querySummaryInfoZxSpBzk(String appId) {
		return getBankDao().querySummaryInfoZxSpBzk(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-工作单位、单位地址、更新时间第二条数据
	@Override
	public List<Map<String, String>> querySummaryInfoZxSpBzkYdj2(String appId) {
		return getBankDao().querySummaryInfoZxSpBzkYdj2(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-欠费记录信息
	@Override
	public  String querySummaryInfoErr1(String appId) {
		return getBankDao().querySummaryInfoErr1(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-民事判决记录信息
	@Override
	public  String querySummaryInfoErr2(String appId) {
		return getBankDao().querySummaryInfoErr2(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-强制执行记录信息
	@Override
	public  String querySummaryInfoErr3(String appId) {
		return getBankDao().querySummaryInfoErr3(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-养老保险金缴存记录信息
	@Override
	public  String querySummaryInfoErr4(String appId) {
		return getBankDao().querySummaryInfoErr4(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-养老保险金发放记录信息
	@Override
	public  String querySummaryInfoErr5(String appId) {
		return getBankDao().querySummaryInfoErr5(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-行政处罚记录信息
	@Override
	public  String querySummaryInfoErr6(String appId) {
		return getBankDao().querySummaryInfoErr6(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-贷款当前逾期总额
	@Override
	public  String querySummaryInfoAmt1(String appId) {
		return getBankDao().querySummaryInfoAmt1(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-信用卡当前逾期总额
	@Override
	public  String querySummaryInfoAmt2(String appId) {
		return getBankDao().querySummaryInfoAmt2(appId);
	}
	//人行摘要信息-标准卡征信审批/易达金-是否列入失信被执行人名单
	@Override
	public  List<Map<String,Object>>  querySummaryInfoCase(String appId) {
		return getBankDao().querySummaryInfoCase(appId);
	}

	//人行摘要信息-标准卡征信审批/易达金-信用卡状态异常
	@Override
	public  List<String> querySummaryInfoCredit(String appId) {
		return getBankDao().querySummaryInfoCredit(appId);
	}
	//人行摘要信息-易达金
		@Override
		public Map<String, String> querySummaryInfoYdj(String appId) {
			return getBankDao().querySummaryInfoYdj(appId);
		}
		//人行摘要信息-易达金-贷款异常情况
		@Override
		public  List<String> querySummaryInfoRecSeq(String appId) {
			return getBankDao().querySummaryInfoRecSeq(appId);
		}
		//人行摘要信息-易达金-担保信息异常情况
		@Override
		public  String querySummaryInfoLoanSort5(String appId) {
			return getBankDao().querySummaryInfoLoanSort5(appId);
		}
		//人行摘要信息-易达金-到期未结清贷款笔数
		@Override
		public  String querySummaryInfoExpireLoansNum(String appId) {
			return getBankDao().querySummaryInfoExpireLoansNum(appId);
		}
		//人行摘要信息-易达金-即将到期贷款笔数
		@Override
		public  String querySummaryInfoAboutExpireLoansNum(String appId) {
			return getBankDao().querySummaryInfoAboutExpireLoansNum(appId);
		}

		@Override
		public List<LoanCardInfo> findLoanDataByMap(Map paramMap) {
			List<LoanCardInfo> loanCardInfos =new LinkedList<LoanCardInfo>();
			Map<String,Object> useMap=new HashMap<String,Object>();
			useMap.put("appId", paramMap.get("appId"));
			useMap.put("messType", paramMap.get("messType"));
			//1.账户状态异常
			useMap.put("loanOrCreditAcctStatus","6");//核销
			List<LoanCardInfo> loanCardListOne=bankDao.selectLoanCardDataByMap(useMap);
			if(!loanCardListOne.isEmpty()){
				loanCardInfos.addAll(loanCardListOne);
			}
			useMap.put("loanOrCreditAcctStatus","4");//呆账
			List<LoanCardInfo> loanCardListTwo=bankDao.selectLoanCardDataByMap(useMap);
			if(!loanCardListTwo.isEmpty()){
				loanCardInfos.addAll(loanCardListTwo);
			}
			//2.逾期
			useMap.put("loanOrCreditAcctStatus","2");
			List<LoanCardInfo> loanCardListThree=bankDao.selectLoanCardDataByMap(useMap);
			if(!loanCardListThree.isEmpty()){
				loanCardInfos.addAll(loanCardListThree);
			}
			//3 正常  1>华夏
			useMap.put("loanOrCreditAcctStatus","1");//正常
			useMap.put("bank","0");//like
			useMap.put("loanOrCreditOrg","华夏银行");//like “华夏银行”
			List<LoanCardInfo> loanCardListFive=bankDao.selectLoanCardDataByMap(useMap);
			if(!loanCardListFive.isEmpty()){
				loanCardInfos.addAll(loanCardListFive);
			}
			//3 正常  1>非华夏
			useMap.put("bank","1");//not like 
			useMap.put("loanOrCreditOrg","华夏银行");//not like “华夏银行”  
			List<LoanCardInfo> loanCardListSix=bankDao.selectLoanCardDataByMap(useMap);
			if(!loanCardListSix.isEmpty()){
				loanCardInfos.addAll(loanCardListSix);
			}
			//4转出
			useMap.put("loanOrCreditAcctStatus","5");
			useMap.put("bank",null);
			useMap.put("loanOrCreditOrg",null);
			List<LoanCardInfo> loanCardListSeven=bankDao.selectLoanCardDataByMap(useMap);
			if(!loanCardListSeven.isEmpty()){
				loanCardInfos.addAll(loanCardListSeven);
			}
			//5.结清
	        useMap.put("loanOrCreditAcctStatus","3");
			List<LoanCardInfo> loanCardListEight=bankDao.selectLoanCardDataByMap(useMap);
			if(!loanCardListEight.isEmpty()){
				loanCardInfos.addAll(loanCardListEight);
			}
			return loanCardInfos;
		}
		@Override
		public String queryLoanAccountsNum(String appId) {
			return getBankDao().queryLoanAccountsNum(appId);
		}
		@Override
		public String queryLoanAccountsNum1(String appId) {
			return getBankDao().queryLoanAccountsNum1(appId);
		}
		@Override
		public List<Map<String, String>> selectDezx4Person(String appId, String flag) {
			if("1".equals(flag)){
				return getBankDao().queryDezx4Person(appId);
			}else{
				return getBankDao().queryDezx4PersonOther(appId);
			}
			
		}
}
