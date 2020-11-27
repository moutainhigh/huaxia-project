package com.huaxia.opas.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huaxia.opas.common.AppConst;
import com.huaxia.opas.dao.PBOCAccFundDao;
import com.huaxia.opas.dao.PBOCAdminAwardDao;
import com.huaxia.opas.dao.PBOCAdminPunishmentDao;
import com.huaxia.opas.dao.PBOCAnnounceInfoDao;
import com.huaxia.opas.dao.PBOCAssetDispositionInfoDao;
import com.huaxia.opas.dao.PBOCAssurerRepayInfoDao;
import com.huaxia.opas.dao.PBOCCivilJudgementDao;
import com.huaxia.opas.dao.PBOCCompetenceDao;
import com.huaxia.opas.dao.PBOCCreditCueDao;
import com.huaxia.opas.dao.PBOCCreditDetailDao;
import com.huaxia.opas.dao.PBOCDissentInfoDao;
import com.huaxia.opas.dao.PBOCEndowmentInsuranceDeliverDao;
import com.huaxia.opas.dao.PBOCEndowmentInsuranceDepositDao;
import com.huaxia.opas.dao.PBOCForceExecutionDao;
import com.huaxia.opas.dao.PBOCGuaranteeInfoDao;
import com.huaxia.opas.dao.PBOCLatestMonthQueryorgSumDao;
import com.huaxia.opas.dao.PBOCLatestMonthQueryrecordSumDao;
import com.huaxia.opas.dao.PBOCOverdueAndFellbackDao;
import com.huaxia.opas.dao.PBOCOverdueRecordDao;
import com.huaxia.opas.dao.PBOCPersonalInfoDao;
import com.huaxia.opas.dao.PBOCProfessionalDao;
import com.huaxia.opas.dao.PBOCRecordDetailDao;
import com.huaxia.opas.dao.PBOCRecordInfoDao;
import com.huaxia.opas.dao.PBOCRecordSummaryDao;
import com.huaxia.opas.dao.PBOCResidenceDao;
import com.huaxia.opas.dao.PBOCSalvationDao;
import com.huaxia.opas.dao.PBOCShareAndDebtDao;
import com.huaxia.opas.dao.PBOCSpecialTradeInfoDao;
import com.huaxia.opas.dao.PBOCTaxArrearDao;
import com.huaxia.opas.dao.PBOCTelPaymentDao;
import com.huaxia.opas.dao.PBOCTwoyearQueryrecordSumDao;
import com.huaxia.opas.dao.PBOCVehicleDao;
import com.huaxia.opas.domain.PBOC;
import com.huaxia.opas.domain.PBOCAnnounce;
import com.huaxia.opas.domain.PBOCAnnounce.AnnounceInfo;
import com.huaxia.opas.domain.PBOCAnnounce.DissentInfo;
import com.huaxia.opas.domain.PBOCCreditCue;
import com.huaxia.opas.domain.PBOCCreditDetail;
import com.huaxia.opas.domain.PBOCCreditDetail.AssetDisposition;
import com.huaxia.opas.domain.PBOCCreditDetail.AssurerRepay;
import com.huaxia.opas.domain.PBOCCreditDetail.GuaranteeInfo;
import com.huaxia.opas.domain.PBOCCreditDetail.Loancard;
import com.huaxia.opas.domain.PBOCCreditDetail.StandardLoancard;
import com.huaxia.opas.domain.PBOCHeader;
import com.huaxia.opas.domain.PBOCHeader.MessageHeader;
import com.huaxia.opas.domain.PBOCHeader.QueryReq;
import com.huaxia.opas.domain.PBOCInfoSummary;
import com.huaxia.opas.domain.PBOCInfoSummary.OverdueAndFellback;
import com.huaxia.opas.domain.PBOCInfoSummary.OverdueAndFellback.FellbackSummary;
import com.huaxia.opas.domain.PBOCInfoSummary.OverdueAndFellback.OverdueSummary;
import com.huaxia.opas.domain.PBOCInfoSummary.ShareAndDebt;
import com.huaxia.opas.domain.PBOCPersonalInfo;
import com.huaxia.opas.domain.PBOCPersonalInfo.Identity;
import com.huaxia.opas.domain.PBOCPersonalInfo.Professional;
import com.huaxia.opas.domain.PBOCPersonalInfo.Residence;
import com.huaxia.opas.domain.PBOCPersonalInfo.Spouse;
import com.huaxia.opas.domain.PBOCPublicInfo;
import com.huaxia.opas.domain.PBOCPublicInfo.AccFund;
import com.huaxia.opas.domain.PBOCPublicInfo.AdminAward;
import com.huaxia.opas.domain.PBOCPublicInfo.AdminPunishment;
import com.huaxia.opas.domain.PBOCPublicInfo.CivilJudgement;
import com.huaxia.opas.domain.PBOCPublicInfo.Competence;
import com.huaxia.opas.domain.PBOCPublicInfo.EndowmentInsuranceDeliver;
import com.huaxia.opas.domain.PBOCPublicInfo.EndowmentInsuranceDeposit;
import com.huaxia.opas.domain.PBOCPublicInfo.ForceExecution;
import com.huaxia.opas.domain.PBOCPublicInfo.Salvation;
import com.huaxia.opas.domain.PBOCPublicInfo.TaxArrear;
import com.huaxia.opas.domain.PBOCPublicInfo.TelPayment;
import com.huaxia.opas.domain.PBOCPublicInfo.Vehicle;
import com.huaxia.opas.domain.PBOCQueryRecord;
import com.huaxia.opas.domain.PBOCQueryRecord.RecordInfo;
import com.huaxia.opas.domain.PBOCQueryRecord.RecordInfo.RecordDetail;
import com.huaxia.opas.domain.PBOCQueryRecord.RecordSummary;
import com.huaxia.opas.domain.PBOCQueryRecord.RecordSummary.LatestMonthQueryorgSum;
import com.huaxia.opas.domain.PBOCQueryRecord.RecordSummary.LatestMonthQueryrecordSum;
import com.huaxia.opas.domain.PBOCQueryRecord.RecordSummary.TwoyearQueryrecordSum;
import com.huaxia.opas.domain.QueryRecordSummary;
import com.huaxia.opas.service.PBOCService;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.opas.util.UUIDGen;

@Service("pbocService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PBOCServiceImpl implements PBOCService {
	private final static Logger logger = LoggerFactory.getLogger(PBOCServiceImpl.class);
	@Autowired
	private PBOCPersonalInfoDao personalInfoDao;

	@Autowired
	private PBOCResidenceDao residenceDao;

	@Autowired
	private PBOCProfessionalDao professionalDao;

	@Autowired
	private PBOCCreditCueDao creditCueDao;

	@Autowired
	private PBOCShareAndDebtDao shareAndDebtDao;

	@Autowired
	private PBOCOverdueAndFellbackDao overdueAndFellbackDao;

	@Autowired
	private PBOCCreditDetailDao creditDetailDao;

	@Autowired
	private PBOCGuaranteeInfoDao guaranteeInfoDao;

	@Autowired
	private PBOCOverdueRecordDao overdueRecordDao;

	@Autowired
	private PBOCTaxArrearDao taxArrearDao;

	@Autowired
	private PBOCCivilJudgementDao civilJudgementDao;

	@Autowired
	private PBOCForceExecutionDao forceExecutionDao;

	@Autowired
	private PBOCAdminPunishmentDao adminPunishmentDao;

	@Autowired
	private PBOCAccFundDao accFundDao;

	@Autowired
	private PBOCEndowmentInsuranceDeliverDao endowmentInsuranceDeliverDao;
	
	@Autowired
	private PBOCEndowmentInsuranceDepositDao endowmentInsuranceDepositDao;

	@Autowired
	private PBOCSalvationDao salvationDao;

	@Autowired
	private PBOCCompetenceDao competenceDao;

	@Autowired
	private PBOCAdminAwardDao adminAwardDao;

	@Autowired
	private PBOCVehicleDao vehicleDao;

	@Autowired
	private PBOCTelPaymentDao telPaymentDao;

	@Autowired
	private PBOCAnnounceInfoDao announceInfoDao;

	@Autowired
	private PBOCDissentInfoDao dissentInfoDao;

	@Autowired
	private PBOCLatestMonthQueryorgSumDao latestMonthQueryorgSumDao;

	@Autowired
	private PBOCLatestMonthQueryrecordSumDao latestMonthQueryrecordSumDao;

	@Autowired
	private PBOCTwoyearQueryrecordSumDao twoyearQueryrecordSumDao;
	
	@Autowired
	private PBOCRecordSummaryDao recordSummaryDao;

	@Autowired
	private PBOCRecordInfoDao recordInfoDao;

	@Autowired
	private PBOCRecordDetailDao recordDetailDao;
	
	@Autowired
	private PBOCSpecialTradeInfoDao  specialTradeInfoDao;
	
	@Autowired
	private PBOCAssetDispositionInfoDao assetDispositionInfoDao;
	
	@Autowired
	private PBOCAssurerRepayInfoDao assurerRepayInfoDao;
	
	public PBOCPersonalInfoDao getPersonalInfoDao() {
		return personalInfoDao;
	}

	public void setPersonalInfoDao(PBOCPersonalInfoDao personalInfoDao) {
		this.personalInfoDao = personalInfoDao;
	}

	public PBOCResidenceDao getResidenceDao() {
		return residenceDao;
	}

	public void setResidenceDao(PBOCResidenceDao residenceDao) {
		this.residenceDao = residenceDao;
	}

	public PBOCProfessionalDao getProfessionalDao() {
		return professionalDao;
	}

	public void setProfessionalDao(PBOCProfessionalDao professionalDao) {
		this.professionalDao = professionalDao;
	}

	public PBOCCreditCueDao getCreditCueDao() {
		return creditCueDao;
	}

	public void setCreditCueDao(PBOCCreditCueDao creditCueDao) {
		this.creditCueDao = creditCueDao;
	}

	public PBOCShareAndDebtDao getShareAndDebtDao() {
		return shareAndDebtDao;
	}

	public void setShareAndDebtDao(PBOCShareAndDebtDao shareAndDebtDao) {
		this.shareAndDebtDao = shareAndDebtDao;
	}

	public PBOCOverdueAndFellbackDao getOverdueAndFellbackDao() {
		return overdueAndFellbackDao;
	}

	public void setOverdueAndFellbackDao(PBOCOverdueAndFellbackDao overdueAndFellbackDao) {
		this.overdueAndFellbackDao = overdueAndFellbackDao;
	}

	public PBOCCreditDetailDao getCreditDetailDao() {
		return creditDetailDao;
	}

	public void setCreditDetailDao(PBOCCreditDetailDao creditDetailDao) {
		this.creditDetailDao = creditDetailDao;
	}

	public PBOCGuaranteeInfoDao getGuaranteeInfoDao() {
		return guaranteeInfoDao;
	}

	public void setGuaranteeInfoDao(PBOCGuaranteeInfoDao guaranteeInfoDao) {
		this.guaranteeInfoDao = guaranteeInfoDao;
	}

	public PBOCOverdueRecordDao getOverdueRecordDao() {
		return overdueRecordDao;
	}

	public void setOverdueRecordDao(PBOCOverdueRecordDao overdueRecordDao) {
		this.overdueRecordDao = overdueRecordDao;
	}

	public PBOCTaxArrearDao getTaxArrearDao() {
		return taxArrearDao;
	}

	public void setTaxArrearDao(PBOCTaxArrearDao taxArrearDao) {
		this.taxArrearDao = taxArrearDao;
	}

	public PBOCCivilJudgementDao getCivilJudgementDao() {
		return civilJudgementDao;
	}

	public void setCivilJudgementDao(PBOCCivilJudgementDao civilJudgementDao) {
		this.civilJudgementDao = civilJudgementDao;
	}

	public PBOCForceExecutionDao getForceExecutionDao() {
		return forceExecutionDao;
	}

	public void setForceExecutionDao(PBOCForceExecutionDao forceExecutionDao) {
		this.forceExecutionDao = forceExecutionDao;
	}

	public PBOCAdminPunishmentDao getAdminPunishmentDao() {
		return adminPunishmentDao;
	}

	public void setAdminPunishmentDao(PBOCAdminPunishmentDao adminPunishmentDao) {
		this.adminPunishmentDao = adminPunishmentDao;
	}

	public PBOCAccFundDao getAccFundDao() {
		return accFundDao;
	}

	public void setAccFundDao(PBOCAccFundDao accFundDao) {
		this.accFundDao = accFundDao;
	}

	public PBOCEndowmentInsuranceDepositDao getEndowmentInsuranceDepositDao() {
		return endowmentInsuranceDepositDao;
	}

	public void setEndowmentInsuranceDepositDao(PBOCEndowmentInsuranceDepositDao endowmentInsuranceDepositDao) {
		this.endowmentInsuranceDepositDao = endowmentInsuranceDepositDao;
	}

	public PBOCSalvationDao getSalvationDao() {
		return salvationDao;
	}

	public void setSalvationDao(PBOCSalvationDao salvationDao) {
		this.salvationDao = salvationDao;
	}

	public PBOCCompetenceDao getCompetenceDao() {
		return competenceDao;
	}

	public void setCompetenceDao(PBOCCompetenceDao competenceDao) {
		this.competenceDao = competenceDao;
	}

	public PBOCAdminAwardDao getAdminAwardDao() {
		return adminAwardDao;
	}

	public void setAdminAwardDao(PBOCAdminAwardDao adminAwardDao) {
		this.adminAwardDao = adminAwardDao;
	}

	public PBOCVehicleDao getVehicleDao() {
		return vehicleDao;
	}

	public void setVehicleDao(PBOCVehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	public PBOCTelPaymentDao getTelPaymentDao() {
		return telPaymentDao;
	}

	public void setTelPaymentDao(PBOCTelPaymentDao telPaymentDao) {
		this.telPaymentDao = telPaymentDao;
	}

	public PBOCAnnounceInfoDao getAnnounceInfoDao() {
		return announceInfoDao;
	}

	public void setAnnounceInfoDao(PBOCAnnounceInfoDao announceInfoDao) {
		this.announceInfoDao = announceInfoDao;
	}

	public PBOCDissentInfoDao getDissentInfoDao() {
		return dissentInfoDao;
	}

	public void setDissentInfoDao(PBOCDissentInfoDao dissentInfoDao) {
		this.dissentInfoDao = dissentInfoDao;
	}

	public PBOCLatestMonthQueryorgSumDao getLatestMonthQueryorgSumDao() {
		return latestMonthQueryorgSumDao;
	}

	public void setLatestMonthQueryorgSumDao(PBOCLatestMonthQueryorgSumDao latestMonthQueryorgSumDao) {
		this.latestMonthQueryorgSumDao = latestMonthQueryorgSumDao;
	}

	public PBOCLatestMonthQueryrecordSumDao getLatestMonthQueryrecordSumDao() {
		return latestMonthQueryrecordSumDao;
	}

	public void setLatestMonthQueryrecordSumDao(PBOCLatestMonthQueryrecordSumDao latestMonthQueryrecordSumDao) {
		this.latestMonthQueryrecordSumDao = latestMonthQueryrecordSumDao;
	}

	public PBOCTwoyearQueryrecordSumDao getTwoyearQueryrecordSumDao() {
		return twoyearQueryrecordSumDao;
	}

	public void setTwoyearQueryrecordSumDao(PBOCTwoyearQueryrecordSumDao twoyearQueryrecordSumDao) {
		this.twoyearQueryrecordSumDao = twoyearQueryrecordSumDao;
	}

	public PBOCRecordInfoDao getRecordInfoDao() {
		return recordInfoDao;
	}

	public void setRecordInfoDao(PBOCRecordInfoDao recordInfoDao) {
		this.recordInfoDao = recordInfoDao;
	}

	public PBOCRecordDetailDao getRecordDetailDao() {
		return recordDetailDao;
	}

	public void setRecordDetailDao(PBOCRecordDetailDao recordDetailDao) {
		this.recordDetailDao = recordDetailDao;
	}

	public PBOCRecordSummaryDao getRecordSummaryDao() {
		return recordSummaryDao;
	}

	public void setRecordSummaryDao(PBOCRecordSummaryDao recordSummaryDao) {
		this.recordSummaryDao = recordSummaryDao;
	}
	
	public PBOCEndowmentInsuranceDeliverDao getEndowmentInsuranceDeliverDao() {
		return endowmentInsuranceDeliverDao;
	}

	public void setEndowmentInsuranceDeliverDao(PBOCEndowmentInsuranceDeliverDao endowmentInsuranceDeliverDao) {
		this.endowmentInsuranceDeliverDao = endowmentInsuranceDeliverDao;
	}
    
	public PBOCSpecialTradeInfoDao getSpecialTradeInfoDao() {
		return specialTradeInfoDao;
	}

	public void setSpecialTradeInfoDao(PBOCSpecialTradeInfoDao specialTradeInfoDao) {
		this.specialTradeInfoDao = specialTradeInfoDao;
	}

	public PBOCAssetDispositionInfoDao getAssetDispositionInfoDao() {
		return assetDispositionInfoDao;
	}

	public void setAssetDispositionInfoDao(PBOCAssetDispositionInfoDao assetDispositionInfoDao) {
		this.assetDispositionInfoDao = assetDispositionInfoDao;
	}
	
	public PBOCAssurerRepayInfoDao getAssurerRepayInfoDao() {
		return assurerRepayInfoDao;
	}

	public void setAssurerRepayInfoDao(PBOCAssurerRepayInfoDao assurerRepayInfoDao) {
		this.assurerRepayInfoDao = assurerRepayInfoDao;
	}
//	@Autowired
//	private TaskCallStatusDao taskCallStatusDao;
//	
//	@Autowired
//	private TaskRequestInfoDao taskRequestInfoDao;
	
	@Override
	public int save(PBOC pboc) {
		if (pboc == null) {
			return -1;
		}

		int result = 0;

		// 申请件编号
		String appId = pboc.getAppId();
		
		// ===============================================
		// == 报告头（Header）
		// ===============================================
		PBOCHeader header = pboc.getHeader();

		// ===============================================
		// == 个人基本信息（PersonalInfo）
		// ===============================================
		PBOCPersonalInfo personalInfo = pboc.getPersonalInfo();
		if (personalInfo != null||header != null) {
			Map<String, Object> personalInfoMap = new HashMap<String, Object>();
			personalInfoMap.put("appId", appId);

			// -------------------------------------------
			// -- 说明：
			// -- 因数据库表结构设计问题, "报告头"逻辑判断及取值
			// -- 统一放在"个人基本信息"中
			// -- 包括: "报告头信息" / "查询请求信息"
			// -------------------------------------------
			// 报告头 & 报告头信息
			if (header != null) {
				MessageHeader messageHeader = header.getMessageHeader();
				if (messageHeader != null) {
					personalInfoMap.put("reportSN", messageHeader.getReportSN());
					personalInfoMap.put("queryTime", messageHeader.getQueryTime());
					personalInfoMap.put("reportCreateTime", messageHeader.getReportCreateTime());
				}

				// 报告头 & 查询请求信息
				QueryReq queryReq = header.getQueryReq();
				if (queryReq != null) {
					personalInfoMap.put("productType", queryReq.getProductType());
					personalInfoMap.put("format", queryReq.getFormat());
					personalInfoMap.put("formatVersion", queryReq.getFormatVersion());
					personalInfoMap.put("name", queryReq.getName());
					personalInfoMap.put("certtype", queryReq.getCerttype());
					personalInfoMap.put("certno", queryReq.getCertno());
					personalInfoMap.put("queryReason", queryReq.getQueryReason());
					personalInfoMap.put("queryOrg", queryReq.getQueryOrg());
					personalInfoMap.put("userCode", queryReq.getUserCode());
					personalInfoMap.put("queryResultCue", queryReq.getQueryResultCue());
				}
			}
			if (personalInfo != null){
				// 身份信息
				Identity identity = personalInfo.getIdentity();
				if (identity != null) {
					personalInfoMap.put("gender", identity.getGender());
					personalInfoMap.put("birthday", identity.getBirthday());
					personalInfoMap.put("maritalState", identity.getMaritalState());
					personalInfoMap.put("mobile", identity.getMobile());
					personalInfoMap.put("officeTelephoneNo", identity.getOfficeTelphoneNo());
					personalInfoMap.put("homeTelephoneNo", identity.getHomeTelphoneNo());
					personalInfoMap.put("eduLevel", identity.getEduLevel());
					personalInfoMap.put("eduDegree", identity.getEduDegree());
					personalInfoMap.put("postAddress", identity.getPostAddress());
					personalInfoMap.put("registeredAddress", identity.getRegisteredAddress());
				}

				// 配偶信息
				Spouse spouse = personalInfo.getSpouse();
				if (spouse != null) {
					personalInfoMap.put("spouseName", spouse.getName());
					personalInfoMap.put("spouseCerttype", spouse.getCerttype());
					personalInfoMap.put("spouseCertno", spouse.getCertno());
					personalInfoMap.put("spouseEmployer", spouse.getEmployer());
					personalInfoMap.put("spouseTelephoneNo", spouse.getTelephoneNo());
				}
			}
			result += getPersonalInfoDao().insert(personalInfoMap);
			clearAndSetNull(personalInfoMap);
			if (personalInfo != null){
				// [持久化] 居住信息
				List<Residence> residenceList = personalInfo.getResidenceList();
				if (residenceList != null && residenceList.size() > 0) {
					Map<String, Object> parameters = new HashMap<String, Object>();
					List<Map<String, Object>> residenceMapList = new ArrayList<Map<String, Object>>();
					for (Residence residence : residenceList) {
						Map<String, Object> residenceMap = new HashMap<String, Object>();
						residenceMap.put("appId", appId);
						residenceMap.put("address", residence.getAddress());
						residenceMap.put("residenceType", residence.getResidenceType());
						residenceMap.put("getTime", residence.getGetTime());
						residenceMapList.add(residenceMap);
					}
					parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, residenceMapList);
					result += getResidenceDao().insertBatch(parameters);
					clearAndSetNull(parameters);
					clearAndSetNull(residenceMapList);
				}
				// [持久化] 职业信息
				List<Professional> professionalList = personalInfo.getProfessionalList();
				if (professionalList != null && professionalList.size() > 0) {
					Map<String, Object> parameters = new HashMap<String, Object>();
					List<Map<String, Object>> professionalMapList = new ArrayList<Map<String, Object>>();
					for (Professional professional : professionalList) {
						Map<String, Object> professionalMap = new HashMap<String, Object>();
						professionalMap.put("appId", appId);
						professionalMap.put("employer", professional.getEmployer());
						professionalMap.put("employerAddress", professional.getEmployerAddress());
						professionalMap.put("occupation", professional.getOccupation());
						professionalMap.put("industry", professional.getIndustry());
						professionalMap.put("duty", professional.getDuty());
						professionalMap.put("title", professional.getTitle());
						professionalMap.put("startYear", professional.getStartYear());
						professionalMap.put("getTime", professional.getGetTime());
						professionalMapList.add(professionalMap);
					}
					parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, professionalMapList);
					result += getProfessionalDao().insertBatch(parameters);
					clearAndSetNull(parameters);
					clearAndSetNull(professionalMapList);
				}
			}
		}
		// ===============================================
		// == 信息概要（InfoSummary）
		// ===============================================
		PBOCInfoSummary infoSummary = pboc.getInfoSummary();
		if (infoSummary != null) {
			// [持久化] 信用提示
			PBOCCreditCue creditCue = infoSummary.getCreditCue();
			if (creditCue != null) {
				Map<String, Object> creditCueMap = new HashMap<String, Object>();
				creditCueMap.put("appId", appId);

				// 信用汇总提示
				PBOCCreditCue.CreditSummaryCue creditSummaryCue = creditCue.getCreditSummaryCue();
				if (creditSummaryCue != null) {
					creditCueMap.put("perHouseLoanCount", creditSummaryCue.getPerHouseLoanCount());
					creditCueMap.put("perBusinessHouseLoanCount", creditSummaryCue.getPerBusinessHouseLoanCount());
					creditCueMap.put("otherLoanCount", creditSummaryCue.getOtherLoanCount());
					creditCueMap.put("firstLoanOpenMonth", creditSummaryCue.getFirstLoanOpenMonth());
					creditCueMap.put("loancardCount", creditSummaryCue.getLoancardCount());
					creditCueMap.put("firstLoancardOpenMonth", creditSummaryCue.getFirstLoancardOpenMonth());
					creditCueMap.put("standardLoancardCount", creditSummaryCue.getStandardLoancardCount());
					creditCueMap.put("firstStandardLoancardOpenMonth", creditSummaryCue.getFirstStandardLoancardOpenMonth());
					creditCueMap.put("announceCount", creditSummaryCue.getAnnounceCount());
					creditCueMap.put("dissentCount", creditSummaryCue.getDissentCount());
				}

				// 个人信用报告"数字解读"
				PBOCCreditCue.CreditScore creditScore = creditCue.getCreditScore();
				if (creditScore != null) {
					creditCueMap.put("score", creditScore.getScore());
					creditCueMap.put("scoreLevel", creditScore.getScoreLevel());
						
				// "数字解读"说明内容
				//creditCueMap.put("scoreEle", creditScore.getScoreEle());
				List<HashMap<String,Object>>  scoreElesMapList = creditScore.getScoreElesMapList();
					if(scoreElesMapList!=null&&scoreElesMapList.size()>0){
						Map<String,Object> scoreElesMap=new HashMap<String,Object>();
						for (int i = 0; i < scoreElesMapList.size(); i++) {
							scoreElesMapList.get(i).put("appId", appId);
						}
						scoreElesMap.put(AppConst.MYBATIS_BATCH_COLLECTION, scoreElesMapList);
						result += getCreditCueDao().insertScoreElesMapList(scoreElesMap);
					}
				}
				result += getCreditCueDao().insert(creditCueMap);
				clearAndSetNull(creditCueMap);
			}

			// [持久化] 授信及负债信息概要
			PBOCInfoSummary.ShareAndDebt shareAndDebt = infoSummary.getShareAndDebt();
			if (shareAndDebt != null) {
				Map<String, Object> shareAndDebtMap = new HashMap<String, Object>();
				shareAndDebtMap.put("appId", appId);

				// 未结清贷款信息汇总
				ShareAndDebt.UnpaidLoan unpaidLoan = shareAndDebt.getUnpaidLoan();
				if (unpaidLoan != null) {
					shareAndDebtMap.put("financeCorpCountOfUnpaidLoan", unpaidLoan.getFinanceCorpCount());
					shareAndDebtMap.put("financeOrgCountOfUnpaidLoan", unpaidLoan.getFinanceOrgCount());
					shareAndDebtMap.put("accountCountOfUnpaidLoan", unpaidLoan.getAccountCount());
					shareAndDebtMap.put("creditLimitOfUnpaidLoan", unpaidLoan.getCreditLimit());
					shareAndDebtMap.put("maxCreditLimitPerOrgOfUnpaidLoan", unpaidLoan.getMaxCreditLimitPerOrg());
					shareAndDebtMap.put("minCreditLimitPerOrgOfUnpaidLoan", unpaidLoan.getMinCreditLimitPerOrg());
					shareAndDebtMap.put("balanceOfUnpaidLoan", unpaidLoan.getBalance());
					shareAndDebtMap.put("usedCreditLimitOfUnpaidLoan", unpaidLoan.getUsedCreditLimit());
					shareAndDebtMap.put("latest6MonthUsedAvgAmountOfUnpaidLoan",
							unpaidLoan.getLatest6MonthUsedAvgAmount());
				}

				// 未销户贷记卡信息汇总
				ShareAndDebt.UndestoryLoancard undestoryLoancard = shareAndDebt.getUndestoryLoancard();
				if (undestoryLoancard != null) {
					shareAndDebtMap.put("financeCorpCountOfUndestoryLoancard", undestoryLoancard.getFinanceCorpCount());
					shareAndDebtMap.put("financeOrgCountOfUndestoryLoancard", undestoryLoancard.getFinanceOrgCount());
					shareAndDebtMap.put("accountCountOfUndestoryLoancard", undestoryLoancard.getAccountCount());
					shareAndDebtMap.put("creditLimitOfUndestoryLoancard", undestoryLoancard.getCreditLimit());
					shareAndDebtMap.put("maxCreditLimitPerOrgOfUndestoryLoancard",
							undestoryLoancard.getMaxCreditLimitPerOrg());
					shareAndDebtMap.put("minCreditLimitPerOrgOfUndestoryLoancard",
							undestoryLoancard.getMinCreditLimitPerOrg());
					shareAndDebtMap.put("balanceOfUndestoryLoancard", undestoryLoancard.getBalance());
					shareAndDebtMap.put("usedCreditLimitOfUndestoryLoancard", undestoryLoancard.getUsedCreditLimit());
					shareAndDebtMap.put("latest6MonthUsedAvgAmountOfUndestoryLoancard",
							undestoryLoancard.getLatest6MonthUsedAvgAmount());
				}

				// 未销户准贷记卡信息汇总
				ShareAndDebt.UndestoryStandardLoancard undestoryStandardLoancard = shareAndDebt.getUndestoryStandardLoancard();
				if (undestoryStandardLoancard != null) {
					shareAndDebtMap.put("financeCorpCountOfUndestoryStandardLoancard",
							undestoryStandardLoancard.getFinanceCorpCount());
					shareAndDebtMap.put("financeOrgCountOfUndestoryStandardLoancard",
							undestoryStandardLoancard.getFinanceOrgCount());
					shareAndDebtMap.put("accountCountOfUndestoryStandardLoancard",
							undestoryStandardLoancard.getAccountCount());
					shareAndDebtMap.put("creditLimitOfUndestoryStandardLoancard",
							undestoryStandardLoancard.getCreditLimit());
					shareAndDebtMap.put("maxCreditLimitPerOrgOfUndestoryStandardLoancard",
							undestoryStandardLoancard.getMaxCreditLimitPerOrg());
					shareAndDebtMap.put("minCreditLimitPerOrgOfUndestoryStandardLoancard",
							undestoryStandardLoancard.getMinCreditLimitPerOrg());
					//NO_PIN_APP_DEBIT_CARD_BALANCE 数据库无此字段 
					//shareAndDebtMap.put("balanceOfUndestoryStandardLoancard", undestoryStandardLoancard.getBalance());
					shareAndDebtMap.put("usedCreditLimitOfUndestoryStandardLoancard",
							undestoryStandardLoancard.getUsedCreditLimit());
					shareAndDebtMap.put("latest6MonthUsedAvgAmountOfUndestoryStandardLoancard",
							undestoryStandardLoancard.getLatest6MonthUsedAvgAmount());
				}

				// 对外担保信息汇总
				ShareAndDebt.GuaranteeSummary guaranteeSummary = shareAndDebt.getGuaranteeSummary();
				if (guaranteeSummary != null) {
					shareAndDebtMap.put("count", guaranteeSummary.getCount());
					shareAndDebtMap.put("amount", guaranteeSummary.getAmount());
					shareAndDebtMap.put("balance", guaranteeSummary.getBalance());
				}

				result += getShareAndDebtDao().insert(shareAndDebtMap);
				clearAndSetNull(shareAndDebtMap);
			}

			// [持久化] 逾期及违约信息概要
			PBOCInfoSummary.OverdueAndFellback overdueAndFellback = infoSummary.getOverdueAndFellback();
			if (overdueAndFellback != null) {
				Map<String, Object> overdueAndFellbackMap = new HashMap<String, Object>();
				overdueAndFellbackMap.put("appId", appId);

				// 违约信息汇总
				OverdueAndFellback.FellbackSummary fellbackSummary = overdueAndFellback.getFellbackSummary();
				if (fellbackSummary != null) {
					// 呆账信息汇总
					FellbackSummary.FellbackDebtSum fellbackDebtSum = fellbackSummary.getFellbackDebtSum();
					if (fellbackDebtSum != null) {
						overdueAndFellbackMap.put("countOfFellbackSummary", fellbackDebtSum.getCount());
						overdueAndFellbackMap.put("balanceOfFellbackSummary", fellbackDebtSum.getBalance());
					}

					// 资产处置信息汇总
					FellbackSummary.AssetDispositionSum assetDispositionSum = fellbackSummary.getAssetDispositionSum();
					if (assetDispositionSum != null) {
						overdueAndFellbackMap.put("countOfAssetDispositionSum", assetDispositionSum.getCount());
						overdueAndFellbackMap.put("balanceOfAssetDispositionSum", assetDispositionSum.getBalance());
					}

					// 保证人代偿汇总
					FellbackSummary.AssureerRepaySum assureerRepaySum = fellbackSummary.getAssureerRepaySum();
					if (assureerRepaySum != null) {
						overdueAndFellbackMap.put("countOfAssureerRepaySum", assureerRepaySum.getCount());
						overdueAndFellbackMap.put("balanceOfAssureerRepaySum", assureerRepaySum.getBalance());
					}
				}

				// 逾期（透支）信息汇总
				OverdueAndFellback.OverdueSummary overdueSummary = overdueAndFellback.getOverdueSummary();
				if (overdueSummary != null) {
					// 贷款逾期
					OverdueSummary.LoanSum loanSum = overdueSummary.getLoanSum();
					if (loanSum != null) {
						overdueAndFellbackMap.put("countOfLoanSum", loanSum.getCount());
						overdueAndFellbackMap.put("monthsOfLoanSum", loanSum.getMonths());
						overdueAndFellbackMap.put("highestOverdueAmountPerMonOfLoanSum",
								loanSum.getHighestOverdueAmountPerMon());
						overdueAndFellbackMap.put("maxDurationOfLoanSum", loanSum.getMaxDuration());
					}

					// 贷记卡逾期
					OverdueSummary.LoancardSum loancardSum = overdueSummary.getLoancardSum();
					if (loancardSum != null) {
						overdueAndFellbackMap.put("countOfLoancardSum", loancardSum.getCount());
						overdueAndFellbackMap.put("monthsOfLoancardSum", loancardSum.getMonths());
						overdueAndFellbackMap.put("highestOverdueAmountPerMonOfLoancardSum",
								loancardSum.getHighestOverdueAmountPerMon());
						overdueAndFellbackMap.put("maxDurationOfLoancardSum", loancardSum.getMaxDuration());
					}

					// 准贷记卡60天以上透支
					OverdueSummary.StandardLoancardSum standardLoancardSum = overdueSummary.getStandardLoancardSum();
					if (standardLoancardSum != null) {
						overdueAndFellbackMap.put("countOfStandardLoancardSum", standardLoancardSum.getCount());
						overdueAndFellbackMap.put("monthsOfStandardLoancardSum", standardLoancardSum.getMonths());
						overdueAndFellbackMap.put("highestOverdueAmountPerMonOfStandardLoancardSum",
								standardLoancardSum.getHighestOverdueAmountPerMon());
						overdueAndFellbackMap.put("maxDurationOfStandardLoancardSum",
								standardLoancardSum.getMaxDuration());
					}
				}

				result += getOverdueAndFellbackDao().insert(overdueAndFellbackMap);
				clearAndSetNull(overdueAndFellbackMap);
			}

		}

		// ===============================================
		// == 信贷交易信息明细（CreditDetail）
		// ===============================================
		PBOCCreditDetail creditDetail = pboc.getCreditDetail();
		if (creditDetail != null) {
			// [持久化] 贷款
			List<PBOCCreditDetail.Loan> loanList = creditDetail.getLoanList();
			if (loanList != null && loanList.size() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> loanMapList = new ArrayList<Map<String, Object>>();
				for (PBOCCreditDetail.Loan loan : loanList) {
					String uuidOfLoan = UUIDGen.getUUID();
					Map<String, Object> loanMap = new HashMap<String, Object>();
					loanMap.put("appId", appId);
					loanMap.put("uuid", uuidOfLoan);
					loanMap.put("cue", loan.getCue());

					// 类型：贷款
					loanMap.put("messageType", "01");

					// 贷款的合同信息
					PBOCCreditDetail.Loan.ContractInfo contractInfo = loan.getContractInfo();
					if (contractInfo != null) {
						loanMap.put("financeOrg", contractInfo.getFinanceOrg());
						loanMap.put("financeType", contractInfo.getFinanceType());
						loanMap.put("account", contractInfo.getAccount());
						loanMap.put("type", contractInfo.getType());
						loanMap.put("currency", contractInfo.getCurrency());
						loanMap.put("openDate", contractInfo.getOpenDate());
						loanMap.put("endDate", contractInfo.getEndDate());
						loanMap.put("creditLimitAmount", contractInfo.getCreditLimitAmount());
						loanMap.put("guaranteeType", contractInfo.getGuaranteeType());
						loanMap.put("paymentRating", contractInfo.getPaymentRating());
						loanMap.put("paymentCyc", contractInfo.getPaymentCyc());
					}

					loanMap.put("state", loan.getState());

					// 当前账户信息
					PBOCCreditDetail.Loan.CurrAccountInfo currAccountInfo = loan.getCurrAccountInfo();
					if (currAccountInfo != null) {
						loanMap.put("stateEndDate", currAccountInfo.getStateEndDate());
						//loanMap.put("stateEndMonth", currAccountInfo.getStateEndMonth()); 库里没对应字段  
						loanMap.put("class5State", currAccountInfo.getClass5State());
						loanMap.put("balance", currAccountInfo.getBalance());
						loanMap.put("remainPaymentCyc", currAccountInfo.getRemainPaymentCyc());
						loanMap.put("scheduledPaymentAmount", currAccountInfo.getScheduledPaymentAmount());
						loanMap.put("scheduledPaymentDate", currAccountInfo.getScheduledPaymentDate());
						loanMap.put("actualPaymentAmount", currAccountInfo.getActualPaymentAmount());
						loanMap.put("recentPayDate", currAccountInfo.getRecentPayDate());
					}

					// 当前逾期信息
					PBOCCreditDetail.Loan.CurrOverdue currOverdue = loan.getCurrOverdue();
					if (currOverdue != null) {
						loanMap.put("currOverdueCyc", currOverdue.getCurrOverdueCyc());
						loanMap.put("currOverdueAmount", currOverdue.getCurrOverdueAmount());
						loanMap.put("overdue31To60Amount", currOverdue.getOverdue31To60Amount());
						loanMap.put("overdue61To90Amount", currOverdue.getOverdue61To90Amount());
						loanMap.put("overdue91To180Amount", currOverdue.getOverdue91To180Amount());
						loanMap.put("overdueOver180Amount", currOverdue.getOverdueOver180Amount());
					}

					// 最近24个月还款状态
					PBOCCreditDetail.Loan.Latest24MonthPaymentState latest24MonthPaymentState = loan
							.getLatest24MonthPaymentState();
					if (latest24MonthPaymentState != null) {
						loanMap.put("beginMonth24OfLoan", latest24MonthPaymentState.getBeginMonth());
						loanMap.put("endMonth24OfLoan", latest24MonthPaymentState.getEndMonth());
						loanMap.put("latest24StateOfLoan", latest24MonthPaymentState.getLatest24State());
					}

					// 最近5年内的逾期记录
					PBOCCreditDetail.Loan.Latest5YearOverdueRecord latest5YearOverdueRecord = loan
							.getLatest5YearOverdueRecord();
					if (latest5YearOverdueRecord != null) {
						loanMap.put("beginMonthOfLoan", latest5YearOverdueRecord.getBeginMonth());
						loanMap.put("endMonthOfLoan", latest5YearOverdueRecord.getEndMonth());

						// 逾期记录明细（01-贷款）
						List<PBOCCreditDetail.Loan.Latest5YearOverdueRecord.OverdueRecord> overdueRecordList = latest5YearOverdueRecord
								.getOverdueRecordList();
						if (overdueRecordList != null && overdueRecordList.size() > 0) {
							Map<String, Object> loanParams = new HashMap<String, Object>();
							List<Map<String, Object>> overdueRecordMapList = new ArrayList<Map<String, Object>>();
							for (PBOCCreditDetail.Loan.Latest5YearOverdueRecord.OverdueRecord overdueRecord : overdueRecordList) {
								Map<String, Object> overdueRecordMap = new HashMap<String, Object>();
								overdueRecordMap.put("appId", appId);
								
								// UUID
								overdueRecordMap.put("uuid", uuidOfLoan);

								// 自定义"逾期类型"（01-贷款）
								overdueRecordMap.put("overdueType", "01");

								overdueRecordMap.put("month", overdueRecord.getMonth());
								overdueRecordMap.put("lastMonths", overdueRecord.getLastMonths());
								overdueRecordMap.put("amount", overdueRecord.getAmount());
								overdueRecordMapList.add(overdueRecordMap);
							}

							loanParams.put(AppConst.MYBATIS_BATCH_COLLECTION, overdueRecordMapList);
							result += getOverdueRecordDao().insertBatch(loanParams);
							clearAndSetNull(loanParams);
							clearAndSetNull(overdueRecordMapList);
						}
					}
					// 特殊交易信息（SpecialTrade）
					List<PBOCCreditDetail.Loan.SpecialTrade> loanSpecialTradeList = loan.getSpecialTradeList();
					if (loanSpecialTradeList!=null&&loanSpecialTradeList.size() > 0) {
						Map<String, Object> loanParams = new HashMap<String, Object>();
						List<Map<String, Object>> loanSpecialTradeMapList = new ArrayList<Map<String, Object>>();
						for (PBOCCreditDetail.Loan.SpecialTrade loanSpecialTrade : loanSpecialTradeList) {
							Map<String, Object> loanSpecialTradeMap = new HashMap<String, Object>();
							
							loanSpecialTradeMap.put("appId", appId);
							// UUID
							loanSpecialTradeMap.put("faRecSeq", uuidOfLoan);
							// 自定义"类型"（01-贷款）
							loanSpecialTradeMap.put("messType", "01");
							
							loanSpecialTradeMap.put("type", loanSpecialTrade.getType());
							loanSpecialTradeMap.put("occurDate",loanSpecialTrade.getGetTime());
							loanSpecialTradeMap.put("changingMonths",loanSpecialTrade.getChangingMonths());
							loanSpecialTradeMap.put("occurAmt", loanSpecialTrade.getChangingAmount());
							loanSpecialTradeMap.put("detailRec", loanSpecialTrade.getContent());
						
							loanSpecialTradeMapList.add(loanSpecialTradeMap);
						}

						loanParams.put(AppConst.MYBATIS_BATCH_COLLECTION, loanSpecialTradeMapList);
						result += getSpecialTradeInfoDao().insertBatch(loanParams);
						clearAndSetNull(loanParams);
						clearAndSetNull(loanSpecialTradeMapList);
					}
					
					loanMapList.add(loanMap);
				}

				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, loanMapList);
				result += getCreditDetailDao().insertBatchForLoan(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(loanMapList);
			}

			// [持久化] 贷记卡
			List<Loancard> loancardList = creditDetail.getLoancardList();
			if (loancardList != null && loancardList.size() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> loancardMapList = new ArrayList<Map<String, Object>>();
				for (Loancard loancard : loancardList) {
					String uuidOfLoancard = UUIDGen.getUUID();
					Map<String, Object> loancardMap = new HashMap<String, Object>();
					loancardMap.put("appId", appId);
					loancardMap.put("uuid", uuidOfLoancard);
					loancardMap.put("cue", loancard.getCue());

					// 类型：贷记卡
					loancardMap.put("messageType", "02");

					// 授信情况
					PBOCCreditDetail.Loancard.AwardCreditInfo awardCreditInfo = loancard.getAwardCreditInfo();
					if (awardCreditInfo != null) {
						loancardMap.put("financeOrg", awardCreditInfo.getFinanceOrg());
						loancardMap.put("financeType", awardCreditInfo.getFinanceType());
						loancardMap.put("account", awardCreditInfo.getAccount());
						loancardMap.put("type", awardCreditInfo.getType());
						loancardMap.put("currency", awardCreditInfo.getCurrency());
						loancardMap.put("openDate", awardCreditInfo.getOpenDate());
						loancardMap.put("endDate", awardCreditInfo.getEndDate());
						loancardMap.put("creditLimitAmount", awardCreditInfo.getCreditLimitAmount());
						loancardMap.put("guaranteeType", awardCreditInfo.getGuaranteeType());
						loancardMap.put("paymentRating", awardCreditInfo.getPaymentRating());
						loancardMap.put("paymentCyc", awardCreditInfo.getPaymentCyc());
					}

					loancardMap.put("state", loancard.getState());

					// 使用/还款情况
					PBOCCreditDetail.Loancard.RepayInfo repayInfo = loancard.getRepayInfo();
					if (repayInfo != null) {
						loancardMap.put("stateEndDate", repayInfo.getStateEndDate());
						loancardMap.put("stateEndMonth", repayInfo.getStateEndMonth());
						loancardMap.put("shareCreditLimitAmount", repayInfo.getShareCreditLimitAmount());
						loancardMap.put("usedCreditLimitAmount", repayInfo.getUsedCreditLimitAmount());
						loancardMap.put("latest6MonthUsedAvgAmount", repayInfo.getLatest6MonthUsedAvgAmount());
						loancardMap.put("usedHighestAmount", repayInfo.getUsedHighestAmount());
						loancardMap.put("scheduledPaymentDate", repayInfo.getScheduledPaymentDate());
						loancardMap.put("scheduledPaymentAmount", repayInfo.getScheduledPaymentAmount());
						loancardMap.put("actualPaymentAmount", repayInfo.getActualPaymentAmount());
						loancardMap.put("guananteeMoney", repayInfo.getGuananteeMoney());
						loancardMap.put("recentPayDate", repayInfo.getRecentPayDate());
					}

					// 当前逾期信息
					PBOCCreditDetail.Loancard.CurrOverdue currOverdue = loancard.getCurrOverdue();
					if (currOverdue != null) {
						loancardMap.put("currOverdueCyc", currOverdue.getCurrOverdueCyc());
						loancardMap.put("currOverdueAmount", currOverdue.getCurrOverdueAmount());
						loancardMap.put("overdue31To60Amount", currOverdue.getOverdue31To60Amount());
						loancardMap.put("overdue61To90Amount", currOverdue.getOverdue61To90Amount());
						loancardMap.put("overdue91To180Amount", currOverdue.getOverdue91To180Amount());
						loancardMap.put("overdueOver180Amount", currOverdue.getOverdueOver180Amount());
					}

					// 最近24个月还款状态
					PBOCCreditDetail.Loancard.Latest24MonthPaymentState latest24MonthPaymentState = loancard
							.getLatest24MonthPaymentState();
					if (latest24MonthPaymentState != null) {
						loancardMap.put("beginMonthOfLatest24MonthPaymentState",
								latest24MonthPaymentState.getBeginMonth());
						loancardMap.put("endMonthOfLatest24MonthPaymentState", latest24MonthPaymentState.getEndMonth());
						loancardMap.put("latest24StateOfLatest24MonthPaymentState",
								latest24MonthPaymentState.getLatest24State());
					}

					// 最近5年内的逾期记录
					PBOCCreditDetail.Loancard.Latest5YearOverdueRecord latest5YearOverdueRecord = loancard
							.getLatest5YearOverdueRecord();
					if (latest5YearOverdueRecord != null) {
						loancardMap.put("beginMonthOfLatest5YearOverdueRecord",
								latest5YearOverdueRecord.getBeginMonth());
						loancardMap.put("endMonthOfLatest5YearOverdueRecord", latest5YearOverdueRecord.getEndMonth());

						// 逾期记录明细（02-贷记卡）
						List<PBOCCreditDetail.Loancard.Latest5YearOverdueRecord.OverdueRecord> overdueRecordList = latest5YearOverdueRecord
								.getOverdueRecordList();
						if (overdueRecordList != null && overdueRecordList.size() > 0) {
							Map<String, Object> loancardParams = new HashMap<String, Object>();
							List<Map<String, Object>> overdueRecordMapList = new ArrayList<Map<String, Object>>();
							for (PBOCCreditDetail.Loancard.Latest5YearOverdueRecord.OverdueRecord overdueRecord : overdueRecordList) {
								Map<String, Object> overdueRecordMap = new HashMap<String, Object>();
								overdueRecordMap.put("appId", appId);
								
								// UUID
								overdueRecordMap.put("uuid", uuidOfLoancard);

								// 自定义"逾期类型"（02-贷记卡）
								overdueRecordMap.put("overdueType", "02");

								overdueRecordMap.put("month", overdueRecord.getMonth());
								overdueRecordMap.put("lastMonths", overdueRecord.getLastMonths());
								overdueRecordMap.put("amount", overdueRecord.getAmount());
								overdueRecordMapList.add(overdueRecordMap);
							}

							loancardParams.put(AppConst.MYBATIS_BATCH_COLLECTION, overdueRecordMapList);
							result += getOverdueRecordDao().insertBatch(loancardParams);
							clearAndSetNull(loancardParams);
							clearAndSetNull(overdueRecordMapList);
						}
					}

					loancardMapList.add(loancardMap);
				}

				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, loancardMapList);
				result += getCreditDetailDao().insertBatchForLoancard(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(loancardMapList);
			}
			
			// [持久化] 准贷记卡
			List<StandardLoancard> standardLoancardList = creditDetail.getStandardLoancardList();
			if (standardLoancardList != null && standardLoancardList.size() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> standardLoancardMapList = new ArrayList<Map<String, Object>>();
				for (StandardLoancard standardLoancard : standardLoancardList) {
					Map<String, Object> standardLoancardMap = new HashMap<String, Object>();
					standardLoancardMap.put("appId", appId);
					
					// 基本信息提示  BASIC_INFO_PROMPT
					standardLoancardMap.put("cue", standardLoancard.getCue());

					// 类型：准贷记卡  mess_type
					standardLoancardMap.put("messageType", "03");
					
					// 账户 状态  LOAN_OR_CREDIT_ACCT_STATUS
					standardLoancardMap.put("state", standardLoancard.getState());
					
					// 贷记卡授信情况  
					PBOCCreditDetail.StandardLoancard.AwardCreditInfo awardCreditInfo = standardLoancard.getAwardCreditInfo();
					if (awardCreditInfo != null) {
						//发卡机构   LOAN_OR_CREDIT_ORG
						standardLoancardMap.put("financeOrg", awardCreditInfo.getFinanceOrg());
						//机构类型  LOAN_OR_CREDIT_ORG_TYPE
						standardLoancardMap.put("financeType", awardCreditInfo.getFinanceType());
						//该贷记卡或准贷记卡的业务号描述   LOAN_OR_CREDIT_BUSI_NO
						standardLoancardMap.put("account", awardCreditInfo.getAccount());
						//币种   LOAN_OR_CREDIT_CCY
						standardLoancardMap.put("currency", awardCreditInfo.getCurrency());
						//发卡日期   LOAN_OR_CREDIT_ISSU_DATE
						standardLoancardMap.put("openDate", awardCreditInfo.getOpenDate());
						//授信额度   LOAN_OR_CREDIT_CONT_AMT
						standardLoancardMap.put("creditLimitAmount", awardCreditInfo.getCreditLimitAmount());
						//担保方式   LOAN_OR_CREDIT_GUARANTEE_MODE
						standardLoancardMap.put("guaranteeType", awardCreditInfo.getGuaranteeType());
					}
					
					// 使用/还款情况
					PBOCCreditDetail.StandardLoancard.RepayInfo repayInfo = standardLoancard.getRepayInfo();
					if (repayInfo != null) {
						//状态截止日  ACC_STATE_END_DATE
						standardLoancardMap.put("stateEndDate", repayInfo.getStateEndDate());
						//状态截止月      数据库无对应字段 
					//	standardLoancardMap.put("stateEndMonth", repayInfo.getStateEndMonth());
						//共享额度     DEB_CARD_PAY_SHARE_LIMIT
						standardLoancardMap.put("shareCreditLimitAmount", repayInfo.getShareCreditLimitAmount());
						//已用额度/透支余额  DEB_CARD_PAY_OVERDRAFT_AMT
						standardLoancardMap.put("usedCreditLimitAmount", repayInfo.getUsedCreditLimitAmount());
						//最近6个月平均使用额度/最近6个月平均透支余额   DEB_CARD_PAY_6MAVG_USED_AMT   
						standardLoancardMap.put("latest6MonthUsedAvgAmount", repayInfo.getLatest6MonthUsedAvgAmount());
						//最大使用额度/最大透支余额  DEB_CARD_PAY_MAX_USED_BAL
						standardLoancardMap.put("usedHighestAmount", repayInfo.getUsedHighestAmount());
						//账单日 ACC_DUE_PAYMENT_DATE
						standardLoancardMap.put("schedulePaymentDate", repayInfo.getScheduledPaymentDate());
						//本月应还款 ACC_CUR_MDUE_PAY_BAL
						standardLoancardMap.put("schedulePaymentAmount", repayInfo.getScheduledPaymentAmount());
						//本月实还款     ACC_CUR_MREAL_PAY_AMT
						standardLoancardMap.put("actualPaymentAmount", repayInfo.getActualPaymentAmount());
						//最近一次还款日期      ACC_LATEST_PAYMENT_DATE
						standardLoancardMap.put("recentPayDate", repayInfo.getRecentPayDate());
					}
					
					// 当前逾期信息
					PBOCCreditDetail.StandardLoancard.CurrOverdue currOverdue = standardLoancard.getCurrOverdue();
					if (currOverdue != null) {
						//当前逾期期数  OVER_DUE_CUR_OVER_DUE_NUM
						standardLoancardMap.put("currOverdueCyc", currOverdue.getCurrOverdueCyc());
						//当前逾期金额   OVER_DUE_CUR_OVER_DUE_AMT
						standardLoancardMap.put("currOverdueAmount", currOverdue.getCurrOverdueAmount());
						//逾期31-60天未归还贷款本金   OVER_DUE_UN_PAY_COR31TO60D
						standardLoancardMap.put("overdue31To60Amount", currOverdue.getOverdue31To60Amount());
						//逾期61-90天未归还贷款本金   OVER_DUE_UN_PAY_COR61TO90D
						standardLoancardMap.put("overdue61To90Amount", currOverdue.getOverdue61To90Amount());
						//逾期91-180天未归还贷款本金   OVER_DUE_UN_PAY_COR91TO180
						standardLoancardMap.put("overdue91To180Amount", currOverdue.getOverdue91To180Amount());
						//逾期180天以上未归还贷款本金   OVER_DUE_UN_PAY_COR180DPLUS
						standardLoancardMap.put("overdueOver180Amount", currOverdue.getOverdueOver180Amount());
					}
					
					// 最近24个月还款状态
					PBOCCreditDetail.StandardLoancard.Latest24MonthPaymentState latest24MonthPaymentState = standardLoancard.getLatest24MonthPaymentState();
					if (latest24MonthPaymentState != null) {
						//24个月起始月   M24_PAYMENT_STR_MONTH
						standardLoancardMap.put("beginMonthOfLatest24MonthPaymentState", latest24MonthPaymentState.getBeginMonth());
						//24个月截止月   M24_PAYMENT_END_MONTH
						standardLoancardMap.put("endMonthOfLatest24MonthPaymentState", latest24MonthPaymentState.getEndMonth());
						//24个月还款状态     M24_PAYMENT_STATUS
						standardLoancardMap.put("latest24StateOfLatest24MonthPaymentState", latest24MonthPaymentState.getLatest24State());
						//24个月还款记录明细     数据库无（应为 将信息放入另一个表）
						//standardLoancardMap.put("state", latest24MonthPaymentState.getStateList());
					}
					
					// 最近5年内的逾期记录
					PBOCCreditDetail.StandardLoancard.Latest5YearOverdueRecord latest5YearOverdueRecord = standardLoancard.getLatest5YearOverdueRecord();
					if (latest5YearOverdueRecord != null) {
						//近5年起始月  M60_OVER_DUE_STR_MONTH
						standardLoancardMap.put("beginMonth", latest5YearOverdueRecord.getBeginMonth());
						//近5年截止月   M60_OVER_DUE_END_MONTH
						standardLoancardMap.put("endMonth", latest5YearOverdueRecord.getEndMonth());
						//近5年逾期记录明细   数据库无对应字段 （应为 将信息放入另一个表）
						//standardLoancardMap.put("overdueRecord", latest5YearOverdueRecord.getOverdueRecordList());
					}
					
					// 特殊交易信息（SpecialTrade）
					List<com.huaxia.opas.domain.PBOCCreditDetail.StandardLoancard.SpecialTrade> specialTradeList = standardLoancard.getSpecialTradeList();
					if (specialTradeList != null) {
						
					}
					
					// 与该笔业务相关的贷款机构说明（BankIlluminate）
					List<com.huaxia.opas.domain.PBOCCreditDetail.StandardLoancard.BankIlluminate> bankIlluminateList = standardLoancard.getBankIlluminateList();
					if (bankIlluminateList != null) {
						
					}
					
					// 异议标注（DissentInfo）
					List<com.huaxia.opas.domain.PBOCCreditDetail.StandardLoancard.DissentInfo> dissentInfoList = standardLoancard.getDissentInfoList();
					if (dissentInfoList != null) {
						
					}
					
					// 本人声明（AnnounceInfo）
					List<com.huaxia.opas.domain.PBOCCreditDetail.StandardLoancard.AnnounceInfo> announceInfoList = standardLoancard.getAnnounceInfoList();
					if (announceInfoList != null) {
						
					}
					
					standardLoancardMapList.add(standardLoancardMap);
				}
				
				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, standardLoancardMapList);
				result += getCreditDetailDao().insertBatchForStandardLoancard(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(standardLoancardMapList);
			}

			// [持久化] 担保信息（GuaranteeInfo）
		  List<GuaranteeInfo> guaranteeInfoList=creditDetail.getGuaranteeInfoList();
		  if(guaranteeInfoList!=null&&guaranteeInfoList.size()>0){
			  for (int i = 0; i < guaranteeInfoList.size(); i++) {
				GuaranteeInfo guaranteeInfo = guaranteeInfoList.get(i);
				if (guaranteeInfo != null) {
					// 担保信息
					List<GuaranteeInfo.Guarantee> guaranteeList = guaranteeInfo.getGuaranteeList();
					if(guaranteeList!=null&&guaranteeList.size()>0){
						Map<String, Object> parameters = new HashMap<String, Object>();
						List<Map<String, Object>> guaranteeInfoMapList = new ArrayList<Map<String, Object>>();
						for (GuaranteeInfo.Guarantee guarantee : guaranteeList) {
							Map<String, Object> guaranteeInfoMap = new HashMap<String, Object>();
							guaranteeInfoMap.put("appId", appId);
							guaranteeInfoMap.put("guaranteeFormat", guaranteeInfo.getGuaranteeFormat());
							guaranteeInfoMap.put("organname", guarantee.getOrganname());
							guaranteeInfoMap.put("contractMoney", guarantee.getContractMoney());
							guaranteeInfoMap.put("beginDate", guarantee.getBeginDate());
							guaranteeInfoMap.put("endDate", guarantee.getEndDate());
							guaranteeInfoMap.put("guaranteeMoney", guarantee.getGuaranteeMoney());
							guaranteeInfoMap.put("guaranteeBalance", guarantee.getGuaranteeBalance());
							guaranteeInfoMap.put("class5State", guarantee.getClass5State());
							guaranteeInfoMap.put("billingDate", guarantee.getBillingDate());
							guaranteeInfoMapList.add(guaranteeInfoMap);
						}
					
					parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, guaranteeInfoMapList);
					result += getGuaranteeInfoDao().insertBatch(parameters);
					clearAndSetNull(parameters);
					clearAndSetNull(guaranteeInfoMapList);
					}
				}
		    }
		  }
		
			// [L2] 资产处置信息（AssetDisposition）
			List<AssetDisposition> assetDispositionList =creditDetail.getAssetDispositionList();
			
			if(assetDispositionList!=null&&assetDispositionList.size()>0){
				
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> assetDispositionMapList = new ArrayList<Map<String, Object>>();
				for (AssetDisposition assetDisposition : assetDispositionList) {
					Map<String, Object> assetDispositionMap = new HashMap<String, Object>();
					assetDispositionMap.put("appId", appId);
					// [L2] 资产管理公司
					assetDispositionMap.put("organname", assetDisposition.getOrganname());
					// [L2] 债务接收日期
					assetDispositionMap.put("receiveTime", assetDisposition.getReceiveTime());
					// [L2]接收的债务金额
					assetDispositionMap.put("money",assetDisposition.getMoney());
					// [L2] 最近一次还款日期
					assetDispositionMap.put("latestRepayDate", assetDisposition.getLatestRepayDate());
					// [L2] 余额
					assetDispositionMap.put("balance", assetDisposition.getBalance());
					assetDispositionMapList.add(assetDispositionMap);
				}
				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, assetDispositionMapList);
				result += getAssetDispositionInfoDao().insertBatch(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(assetDispositionMapList);
			}
			
			// 保证人代偿信息（AssurerRepay）
			List<AssurerRepay> assurerRepayList =creditDetail.getAssurerRepayList();
			if(assurerRepayList!=null&&assurerRepayList.size()>0){
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> assurerRepayMapList = new ArrayList<Map<String, Object>>();
				for (AssurerRepay assurerRepay : assurerRepayList) {
					Map<String, Object> assurerRepayMap = new HashMap<String, Object>();
					assurerRepayMap.put("appId", appId);
					// [L2] 代偿机构
					assurerRepayMap.put("organname", assurerRepay.getOrganname());
					// [L2] 最近一次代偿日期
					assurerRepayMap.put("latestAssurerRepayDate",assurerRepay.getLatestAssurerRepayDate());
					// [L2]累计代偿金额
					assurerRepayMap.put("money",assurerRepay.getMoney());
					// [L2] 最近一次还款日期
					assurerRepayMap.put("latestRepayDate", assurerRepay.getLatestRepayDate());
					// [L2] 余额
					assurerRepayMap.put("balance", assurerRepay.getBalance());
					assurerRepayMapList.add(assurerRepayMap);
				}
				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, assurerRepayMapList);
				result +=   getAssurerRepayInfoDao().insertBatch(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(assurerRepayMapList);
			}
			
			
			
			
			
			
			
			
		}

		// ===============================================
		// == 公共信息明细（PublicInfo）
		// ===============================================
		PBOCPublicInfo publicInfo = pboc.getPublicInfo();
		if (publicInfo != null) {
			// 欠税记录（TaxArrear）
			List<TaxArrear> taxArrearList = publicInfo.getTaxArrearList();
			if (taxArrearList != null && taxArrearList.size() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> taxArrearMapList = new ArrayList<Map<String, Object>>();
				for (TaxArrear taxArrear : taxArrearList) {
					Map<String, Object> taxArrearMap = new HashMap<String, Object>();
					taxArrearMap.put("appId", appId);
					taxArrearMap.put("organname", taxArrear.getOrganname());
					taxArrearMap.put("taxArreaAmount", taxArrear.getTaxArreaAmount());
					taxArrearMap.put("revenuedate", taxArrear.getRevenuedate());
					taxArrearMapList.add(taxArrearMap);
				}
				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, taxArrearMapList);
				result += getTaxArrearDao().insertBatch(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(taxArrearMapList);
			}

			// 民事判决记录（CivilJudgement）
			List<CivilJudgement> civilJudgementList = publicInfo.getCivilJudgementList();
			if (civilJudgementList != null && civilJudgementList.size() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> civilJudgementMapList = new ArrayList<Map<String, Object>>();
				for (CivilJudgement civilJudgement : civilJudgementList) {
					Map<String, Object> civilJudgementMap = new HashMap<String, Object>();
					civilJudgementMap.put("appId", appId);
					civilJudgementMap.put("court", civilJudgement.getCourt());
					civilJudgementMap.put("caseReason", civilJudgement.getCaseReason());
					civilJudgementMap.put("registerDate", civilJudgement.getRegisterDate());
					civilJudgementMap.put("closedType", civilJudgement.getClosedType());
					civilJudgementMap.put("caseResult", civilJudgement.getCaseResult());
					civilJudgementMap.put("caseValidatedate", civilJudgement.getCaseValidatedate());
					civilJudgementMap.put("suitObject", civilJudgement.getSuitObject());
					civilJudgementMap.put("suitObjectMoney", civilJudgement.getSuitObjectMoney());
					civilJudgementMapList.add(civilJudgementMap);
				}
				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, civilJudgementMapList);
				result += getCivilJudgementDao().insertBatch(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(civilJudgementMapList);
			}

			// 强制执行记录（ForceExecution）
			List<ForceExecution> forceExecutionList = publicInfo.getForceExecutionList();
			if (forceExecutionList != null && forceExecutionList.size() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> forceExecutionMapList = new ArrayList<Map<String, Object>>();
				for (ForceExecution forceExecution : forceExecutionList) {
					Map<String, Object> forceExecutionMap = new HashMap<String, Object>();
					forceExecutionMap.put("appId", appId);
					forceExecutionMap.put("court", forceExecution.getCourt());
					forceExecutionMap.put("caseReason", forceExecution.getCaseReason());
					forceExecutionMap.put("registerDate", forceExecution.getRegisterDate());
					forceExecutionMap.put("closedType", forceExecution.getClosedType());
					forceExecutionMap.put("caseState", forceExecution.getCaseState());
					forceExecutionMap.put("closedDate", forceExecution.getClosedDate());
					forceExecutionMap.put("enforceObject", forceExecution.getEnforceObject());
					forceExecutionMap.put("enforceObjectMoney", forceExecution.getEnforceObjectMoney());
					forceExecutionMap.put("alreadyEnforceObject", forceExecution.getAlreadyEnforceObject());
					forceExecutionMap.put("alreadyEnforceObjectMoney", forceExecution.getAlreadyEnforceObjectMoney());
					forceExecutionMapList.add(forceExecutionMap);
				}
				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, forceExecutionMapList);
				result += getForceExecutionDao().insertBatch(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(forceExecutionMapList);
			}

			// 行政处罚记录（AdminPunishment）
			List<AdminPunishment> adminPunishmentList = publicInfo.getAdminPunishmentList();
			if (adminPunishmentList != null && adminPunishmentList.size() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> adminPunishmentMapList = new ArrayList<Map<String, Object>>();
				for (AdminPunishment adminPunishment : adminPunishmentList) {
					Map<String, Object> adminPunishmentMap = new HashMap<String, Object>();
					adminPunishmentMap.put("appId", appId);
					adminPunishmentMap.put("organname", adminPunishment.getOrganname());
					adminPunishmentMap.put("content", adminPunishment.getContent());
					adminPunishmentMap.put("money", adminPunishment.getMoney());
					adminPunishmentMap.put("beginDate", adminPunishment.getBeginDate());
					adminPunishmentMap.put("endDate", adminPunishment.getEndDate());
					adminPunishmentMap.put("result", adminPunishment.getResult());
					adminPunishmentMapList.add(adminPunishmentMap);
				}
				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, adminPunishmentMapList);
				result += getAdminPunishmentDao().insertBatch(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(adminPunishmentMapList);
			}

			// 住房公积金参缴记录（AccFund）
			List<AccFund> accFundList = publicInfo.getAccFundList();
			if (accFundList != null && accFundList.size() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> accFundMapList = new ArrayList<Map<String, Object>>();
				for (AccFund accFund : accFundList) {
					Map<String, Object> accFundMap = new HashMap<String, Object>();
					accFundMap.put("appId", appId);
					accFundMap.put("area", accFund.getArea());
					accFundMap.put("registerDate", accFund.getRegisterDate());
					accFundMap.put("firstMonth", accFund.getFirstMonth());
					accFundMap.put("toMonth", accFund.getToMonth());
					accFundMap.put("state", accFund.getState());
					accFundMap.put("pay", accFund.getPay());
					accFundMap.put("ownPercent", accFund.getOwnPercent());
					accFundMap.put("comPercent", accFund.getComPercent());
					accFundMap.put("organname", accFund.getOrganname());
					accFundMap.put("getTime", accFund.getGetTime());
					accFundMapList.add(accFundMap);
				}
				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, accFundMapList);
				result += getAccFundDao().insertBatch(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(accFundMapList);
			}

			// 养老保险金缴存记录（EndowmentInsuranceDeposit）
			List<EndowmentInsuranceDeposit> endowmentInsuranceDepositList = publicInfo.getEndowmentInsuranceDepositList();
			if (endowmentInsuranceDepositList != null && endowmentInsuranceDepositList.size() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> endowmentInsuranceDepositMapList = new ArrayList<Map<String, Object>>();
				for (EndowmentInsuranceDeposit endowmentInsuranceDeposit : endowmentInsuranceDepositList) {
					Map<String, Object> endowmentInsuranceDepositMap = new HashMap<String, Object>();
					endowmentInsuranceDepositMap.put("appId", appId);
					endowmentInsuranceDepositMap.put("area", endowmentInsuranceDeposit.getArea());
					endowmentInsuranceDepositMap.put("registerDate", endowmentInsuranceDeposit.getRegisterDate());
					endowmentInsuranceDepositMap.put("monthDuration", endowmentInsuranceDeposit.getMonthDuration());
					endowmentInsuranceDepositMap.put("workDate", endowmentInsuranceDeposit.getWorkDate());
					endowmentInsuranceDepositMap.put("state", endowmentInsuranceDeposit.getState());
					endowmentInsuranceDepositMap.put("ownBasicMoney", endowmentInsuranceDeposit.getOwnBasicMoney());
					endowmentInsuranceDepositMap.put("money", endowmentInsuranceDeposit.getMoney());
					endowmentInsuranceDepositMap.put("organname", endowmentInsuranceDeposit.getOrganname());
					endowmentInsuranceDepositMap.put("pauseReason", endowmentInsuranceDeposit.getPauseReason());
					endowmentInsuranceDepositMap.put("getTime", endowmentInsuranceDeposit.getGetTime());
					endowmentInsuranceDepositMapList.add(endowmentInsuranceDepositMap);
				}
				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, endowmentInsuranceDepositMapList);
				result += getEndowmentInsuranceDepositDao().insertBatch(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(endowmentInsuranceDepositMapList);
			}
			
			// 养老保险金发放记录（EndowmentInsuranceDeliver）
			List<EndowmentInsuranceDeliver> endowmentInsuranceDeliverList = publicInfo.getEndowmentInsuranceDeliverList();
			if (endowmentInsuranceDeliverList != null && endowmentInsuranceDeliverList.size() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> endowmentInsuranceDeliverMapList = new ArrayList<Map<String, Object>>();
				for (EndowmentInsuranceDeliver endowmentInsuranceDeliver : endowmentInsuranceDeliverList) {
					Map<String, Object> endowmentInsuranceDeliverMap = new HashMap<String, Object>();
					endowmentInsuranceDeliverMap.put("appId", appId);
					endowmentInsuranceDeliverMap.put("area", endowmentInsuranceDeliver.getArea());
					endowmentInsuranceDeliverMap.put("retireType", endowmentInsuranceDeliver.getRetireType());
					endowmentInsuranceDeliverMap.put("retiredDate", endowmentInsuranceDeliver.getRetiredDate());
					endowmentInsuranceDeliverMap.put("workDate", endowmentInsuranceDeliver.getWorkDate());
					endowmentInsuranceDeliverMap.put("money", endowmentInsuranceDeliver.getMoney());
					endowmentInsuranceDeliverMap.put("pauseReason", endowmentInsuranceDeliver.getPauseReason());
					endowmentInsuranceDeliverMap.put("organname", endowmentInsuranceDeliver.getOrganname());
					endowmentInsuranceDeliverMap.put("getTime", endowmentInsuranceDeliver.getGetTime());
					endowmentInsuranceDeliverMapList.add(endowmentInsuranceDeliverMap);
				}
				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, endowmentInsuranceDeliverMapList);
				result += getEndowmentInsuranceDeliverDao().insertBatch(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(endowmentInsuranceDeliverMapList);			
			}

			// 低保救助记录（Salvation）
			List<Salvation> salvationList = publicInfo.getSalvationList();
			if (salvationList != null && salvationList.size() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> salvationMapList = new ArrayList<Map<String, Object>>();
				for (Salvation salvation : salvationList) {
					Map<String, Object> salvationMap = new HashMap<String, Object>();
					salvationMap.put("appId", appId);
					salvationMap.put("personnalType", salvation.getPersonnelType());
					salvationMap.put("area", salvation.getArea());
					salvationMap.put("organname", salvation.getOrganname());
					salvationMap.put("money", salvation.getMoney());
					salvationMap.put("registerDate", salvation.getRegisterDate());
					salvationMap.put("passDate", salvation.getPassDate());
					salvationMap.put("getTime", salvation.getGetTime());
					salvationMapList.add(salvationMap);
				}
				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, salvationMapList);
				result += getSalvationDao().insertBatch(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(salvationMapList);
			}

			// 执业资格记录（Competence）
			List<Competence> competenceList = publicInfo.getCompetenceList();
			if (competenceList != null && competenceList.size() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> competenceMapList = new ArrayList<Map<String, Object>>();
				for (Competence competence : competenceList) {
					Map<String, Object> competenceMap = new HashMap<String, Object>();
					competenceMap.put("appId", appId);
					competenceMap.put("competencyName", competence.getCompetencyName());
					competenceMap.put("grade", competence.getGrade());
					competenceMap.put("awardDate", competence.getAwardDate());
					competenceMap.put("endDate", competence.getEndDate());
					competenceMap.put("revokeDate", competence.getRevokeDate());
					competenceMap.put("organname", competence.getOrganname());
					competenceMap.put("area", competence.getArea());
					competenceMapList.add(competenceMap);
				}
				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, competenceMapList);
				result += getCompetenceDao().insertBatch(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(competenceMapList);
			}

			// 行政奖励记录（AdminAward）
			List<AdminAward> adminAwardList = publicInfo.getAdminAwardList();
			if (adminAwardList != null && adminAwardList.size() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> adminAwardMapList = new ArrayList<Map<String, Object>>();
				for (AdminAward adminAward : adminAwardList) {
					Map<String, Object> adminAwardMap = new HashMap<String, Object>();
					adminAwardMap.put("appId", appId);
					adminAwardMap.put("organname", adminAward.getOrganname());
					adminAwardMap.put("content", adminAward.getContent());
					adminAwardMap.put("beginDate", adminAward.getBeginDate());
					adminAwardMap.put("endDate", adminAward.getEndDate());
					adminAwardMapList.add(adminAwardMap);
				}
				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, adminAwardMapList);
				result += getAdminAwardDao().insertBatch(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(adminAwardMapList);
			}

			// 车辆交易和抵押记录（Vehicle）
			List<Vehicle> vehicleList = publicInfo.getVehicleList();
			if (vehicleList != null && vehicleList.size() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> vehicleMapList = new ArrayList<Map<String, Object>>();
				for (Vehicle vehicle : vehicleList) {
					Map<String, Object> vehicleMap = new HashMap<String, Object>();
					vehicleMap.put("appId", appId);
					vehicleMap.put("engineCode", vehicle.getEngineCode());
					vehicleMap.put("licenseCode", vehicle.getLicenseCode());
					vehicleMap.put("brand", vehicle.getBrand());
					vehicleMap.put("carType", vehicle.getCarType());
					vehicleMap.put("useCharacter", vehicle.getUseCharacter());
					vehicleMap.put("state", vehicle.getState());
					vehicleMap.put("pledgeFlag", vehicle.getPledgeFlag());
					vehicleMap.put("getTime", vehicle.getGetTime());
					vehicleMapList.add(vehicleMap);
				}
				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, vehicleMapList);
				result += getVehicleDao().insertBatch(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(vehicleMapList);
			}

			// 电信缴费记录（TelPayment）
			List<TelPayment> telPaymentList = publicInfo.getTelPaymentList();
			if (telPaymentList != null && telPaymentList.size() > 0) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				List<Map<String, Object>> telPaymentMapList = new ArrayList<Map<String, Object>>();
				for (TelPayment telPayment : telPaymentList) {
					Map<String, Object> telPaymentMap = new HashMap<String, Object>();
					telPaymentMap.put("appId", appId);
					telPaymentMap.put("organname", telPayment.getOrganname());
					telPaymentMap.put("type", telPayment.getType());
					telPaymentMap.put("registerDate", telPayment.getRegisterDate());
					telPaymentMap.put("state", telPayment.getState());
					telPaymentMap.put("arrearMoney", telPayment.getArrearMoney());
					telPaymentMap.put("arrearMonths", telPayment.getArrearMonths());
					telPaymentMap.put("status24", telPayment.getStatus24());
					telPaymentMap.put("getTime", telPayment.getGetTime());
					telPaymentMapList.add(telPaymentMap);
				}
				parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, telPaymentMapList);
				result += getTelPaymentDao().insertBatch(parameters);
				clearAndSetNull(parameters);
				clearAndSetNull(telPaymentMapList);
			}
		}

		// ===============================================
		// == 声明信息（Announce）
		// ===============================================
		PBOCAnnounce announce = pboc.getAnnounce();
		if (announce != null) {
			// 本人声明
			AnnounceInfo announceInfo = announce.getAnnounceInfo();
			if (announceInfo != null) {
				Map<String, Object> announceInfoMap = new HashMap<String, Object>();
				announceInfoMap.put("appId", appId);
				announceInfoMap.put("content", announceInfo.getContent());
				announceInfoMap.put("getTime", announceInfo.getGetTime());
				result += getAnnounceInfoDao().insert(announceInfoMap);
				clearAndSetNull(announceInfoMap);
			}

			// 异议标注
			DissentInfo dissentInfo = announce.getDissentInfo();
			if (dissentInfo != null) {
				Map<String, Object> dissentInfoMap = new HashMap<String, Object>();
				dissentInfoMap.put("appId", appId);
				dissentInfoMap.put("content", dissentInfo.getContent());
				dissentInfoMap.put("getTime", dissentInfo.getGetTime());
				result += getDissentInfoDao().insert(dissentInfoMap);
				clearAndSetNull(dissentInfoMap);
			}
		}

		// ===============================================
		// == 查询记录（QueryRecord）
		// ===============================================
		PBOCQueryRecord queryRecord = pboc.getQueryRecord();
		if (queryRecord != null) {
			// 查询记录汇总
			RecordSummary recordSummary = queryRecord.getRecordSummary();
			if (recordSummary != null) {
				// [行转列] 查询记录汇总
				QueryRecordSummary summary = new QueryRecordSummary();
				summary.setAppId(appId);
				
				// 最近一个月内的查询机构数
				List<LatestMonthQueryorgSum> latestMonthQueryorgSumList = recordSummary.getLatestMonthQueryorgSumList();
				if (latestMonthQueryorgSumList != null && latestMonthQueryorgSumList.size() > 0) {
					Map<String, Object> parameters = new HashMap<String, Object>();
					List<Map<String, Object>> latestMonthQueryorgSumMapList = new ArrayList<Map<String, Object>>();
					for (LatestMonthQueryorgSum latestMonthQueryorgSum : latestMonthQueryorgSumList) {
						Map<String, Object> latestMonthQueryorgSumMap = new HashMap<String, Object>();
						latestMonthQueryorgSumMap.put("appId", appId);
						latestMonthQueryorgSumMap.put("queryReason", latestMonthQueryorgSum.getQueryReason());
						latestMonthQueryorgSumMap.put("sum", latestMonthQueryorgSum.getSum());
						latestMonthQueryorgSumMapList.add(latestMonthQueryorgSumMap);
						
						// 查询记录汇总匹配赋值
						String summaryType = latestMonthQueryorgSum.getQueryReason().split("\\|")[0];
						switch (summaryType) {
						// 贷后管理
						case "01":
							summary.setQueryOrg1MSum1(latestMonthQueryorgSum.getSum());
							break;
						// 贷款审批
						case "02":
							summary.setQueryOrg1MSum2(latestMonthQueryorgSum.getSum());
							break;
						// 信用卡审批
						case "03":
							summary.setQueryOrg1MSum3(latestMonthQueryorgSum.getSum());
							break;
						// 本人查询
						case "04":
							summary.setQueryOrg1MSum4(latestMonthQueryorgSum.getSum());
							break;
						// 担保资格审查
						case "08":
							summary.setQueryOrg1MSum5(latestMonthQueryorgSum.getSum());
							break;
						// 特约商户实名查询
						case "19":
							summary.setQueryOrg1MSum6(latestMonthQueryorgSum.getSum());
							break;
						}
					}

					parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, latestMonthQueryorgSumMapList);
					result += getLatestMonthQueryorgSumDao().insertBatch(parameters);
					clearAndSetNull(parameters);
					clearAndSetNull(latestMonthQueryorgSumMapList);
				}

				// 最近一个月内的查询次数
				List<LatestMonthQueryrecordSum> latestMonthQueryrecordSumList = recordSummary
						.getLatestMonthQueryrecordSumList();
				if (latestMonthQueryrecordSumList != null && latestMonthQueryrecordSumList.size() > 0) {
					Map<String, Object> parameters = new HashMap<String, Object>();
					List<Map<String, Object>> latesttMonthQueryrecordSumMapList = new ArrayList<Map<String, Object>>();
					for (LatestMonthQueryrecordSum latestMonthQueryrecordSum : latestMonthQueryrecordSumList) {
						Map<String, Object> latestMonthQueryrecordSumMap = new HashMap<String, Object>();
						latestMonthQueryrecordSumMap.put("appId", appId);
						latestMonthQueryrecordSumMap.put("queryReason", latestMonthQueryrecordSum.getQueryReason());
						latestMonthQueryrecordSumMap.put("sum", latestMonthQueryrecordSum.getSum());
						latesttMonthQueryrecordSumMapList.add(latestMonthQueryrecordSumMap);
						
						// 查询记录汇总匹配赋值
						String summaryType = latestMonthQueryrecordSum.getQueryReason().split("\\|")[0];
						switch (summaryType) {
						case "01":
							summary.setQueryRec1MSum1(latestMonthQueryrecordSum.getSum());
							break;
						case "02":
							summary.setQueryRec1MSum2(latestMonthQueryrecordSum.getSum());
							break;
						case "03":
							summary.setQueryRec1MSum3(latestMonthQueryrecordSum.getSum());
							break;
						case "04":
							summary.setQueryRec1MSum4(latestMonthQueryrecordSum.getSum());
							break;
						case "08":
							summary.setQueryRec1MSum5(latestMonthQueryrecordSum.getSum());
							break;
						case "19":
							summary.setQueryRec1MSum6(latestMonthQueryrecordSum.getSum());
							break;
						}
					}

					parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, latesttMonthQueryrecordSumMapList);
					result += getLatestMonthQueryrecordSumDao().insertBatch(parameters);
					clearAndSetNull(parameters);
					clearAndSetNull(latesttMonthQueryrecordSumMapList);
				}

				// 最近两年内的查询次数
				List<TwoyearQueryrecordSum> twoyearQueryrecordSumList = recordSummary.getTwoyearQueryrecordSumList();
				if (twoyearQueryrecordSumList != null && twoyearQueryrecordSumList.size() > 0) {
					Map<String, Object> parameters = new HashMap<String, Object>();
					List<Map<String, Object>> twoyearQueryrecordSumMapList = new ArrayList<Map<String, Object>>();
					for (TwoyearQueryrecordSum twoyearQueryrecordSum : twoyearQueryrecordSumList) {
						Map<String, Object> twoyearQueryrecordSumMap = new HashMap<String, Object>();
						twoyearQueryrecordSumMap.put("appId", appId);
						twoyearQueryrecordSumMap.put("queryReason", twoyearQueryrecordSum.getQueryReason());
						twoyearQueryrecordSumMap.put("sum", twoyearQueryrecordSum.getSum());
						twoyearQueryrecordSumMapList.add(twoyearQueryrecordSumMap);
						
						// 查询记录汇总匹配赋值
						String summaryType = twoyearQueryrecordSum.getQueryReason().split("\\|")[0];
						switch (summaryType) {
						case "01":
							summary.setQueryRec2YSum1(twoyearQueryrecordSum.getSum());
							break;
						case "02":
							summary.setQueryRec2YSum2(twoyearQueryrecordSum.getSum());
							break;
						case "03":
							summary.setQueryRec2YSum3(twoyearQueryrecordSum.getSum());
							break;
						case "04":
							summary.setQueryRec2YSum4(twoyearQueryrecordSum.getSum());
							break;
						case "08":
							summary.setQueryRec2YSum5(twoyearQueryrecordSum.getSum());
							break;
						case "19":
							summary.setQueryRec2YSum6(twoyearQueryrecordSum.getSum());
							break;
						}
					}

					parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, twoyearQueryrecordSumMapList);
					result += getTwoyearQueryrecordSumDao().insertBatch(parameters);
					clearAndSetNull(parameters);
					clearAndSetNull(twoyearQueryrecordSumMapList);
				}
				
				// -------------------------------------------
				// -- 说明：
				// -- 目前基于查询汇总只有"最近一个月内的查询机构数"、
				// -- "最近一个月内的查询次数"、"最近两年内的查询次数"
				// -- 三种类型的的查询汇总，故将此三种查询汇总统一做到
				// -- 一张表中处理（"行转列"形式）
				// -------------------------------------------
				result += getRecordSummaryDao().insert(summary);
				summary = null;
			}

			// 查询记录明细
			List<RecordInfo> recordInfoList = queryRecord.getRecordInfoList();
			if(recordInfoList!=null&&recordInfoList.size()>0){
				for (int j = 0; j < recordInfoList.size(); j++) {
					RecordInfo recordInfo=recordInfoList.get(j);
							if (recordInfo != null) {
								Map<String, Object> recordInfoMap = new HashMap<String, Object>();
								recordInfoMap.put("appId", appId);
								recordInfoMap.put("queryReqFormat", recordInfo.getQueryReqFormat());

								List<RecordDetail> recordDetailList = recordInfo.getRecordDetailList();
								if (recordDetailList != null && recordDetailList.size() > 0) {
									Map<String, Object> parameters = new HashMap<String, Object>();
									List<Map<String, Object>> recordDetailMapList = new ArrayList<Map<String, Object>>();
									for (RecordDetail recordDetail : recordDetailList) {
										Map<String, Object> recordDetailMap = new HashMap<String, Object>();
										recordDetailMap.put("appId", appId);
										recordDetailMap.put("queryDate", recordDetail.getQueryDate());
										recordDetailMap.put("querier", recordDetail.getQuerier());
										recordDetailMap.put("queryReason", recordDetail.getQueryReason());
										recordDetailMapList.add(recordDetailMap);
									}

									parameters.put(AppConst.MYBATIS_BATCH_COLLECTION, recordDetailMapList);
									result += getRecordDetailDao().insertBatch(parameters);
									clearAndSetNull(parameters);
									clearAndSetNull(recordDetailMapList);
								}

								result += getRecordInfoDao().insert(recordInfoMap);
								clearAndSetNull(recordInfoMap);
						}
				}
			}
		}

		return result;
	}

	// 清空临时参数集合
	private void clearAndSetNull(Object object) {
		if (object != null) {
			if (object instanceof Map) {
				((Map<?, ?>) object).clear();
			} else if (object instanceof List) {
				((List<?>) object).clear();
			}
			object = null;
		}
	}

	@Override
	public int queryCountPbocByAppId(String appId) {
		return  personalInfoDao.selectCountPbocByAppId(appId);
	}

	@Override
	public String queryLateAppIdByDayNameIdNo(Map<String, String> params) {
		return  personalInfoDao.selectLateAppIdByDayNameIdNo(params);
	}

	@Override
	public Map<String, Object> saveClonePbocData(Map<String, Object> params) {
		return personalInfoDao.saveClonePbocData(params);
	}

	@Override
	public void savePbocUpdateDataAdapterAction(PBOC pboc, String appId, String taskType) {
		save(pboc);
		// 更新申请件任务状态
		Map<String, String> paramsUpdate = new HashMap<String, String>();
	    paramsUpdate.put("appId", appId);
	    paramsUpdate.put("status", TaskStatusUtil.SUCCESS);
	    paramsUpdate.put("taskType", taskType);
	    paramsUpdate.put("errorCode", null);
	    paramsUpdate.put("lstOptUser", TaskStatusUtil.CURR_USER);
	    paramsUpdate.put("requestAddNum", null);
	    paramsUpdate.put("failureAddNum", null);
//		getTaskCallStatusDao().updateSingleTask(paramsUpdate);
	}
	
	
}