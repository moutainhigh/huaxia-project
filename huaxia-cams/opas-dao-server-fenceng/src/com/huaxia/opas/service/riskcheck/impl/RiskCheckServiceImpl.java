package com.huaxia.opas.service.riskcheck.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.riskcheck.RiskCheckDao;
import com.huaxia.opas.domain.decision.CreditTelcheckList;
import com.huaxia.opas.domain.decision.ExcellentCompanyList;
import com.huaxia.opas.domain.decision.MerchTeamdealList;
import com.huaxia.opas.domain.decision.TeamdealList;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.riskcheck.AddrRiskList;
import com.huaxia.opas.domain.riskcheck.CompanyRisk;
import com.huaxia.opas.domain.riskcheck.IfsCustInfo;
import com.huaxia.opas.domain.riskcheck.KeyfiledMarchinfo;
import com.huaxia.opas.domain.riskcheck.KeyfiledResultInfo;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.domain.riskcheck.TelRiskList;
import com.huaxia.opas.domain.riskcheck.Union3014Risk;
import com.huaxia.opas.domain.sysparm.MajorschoolList;
import com.huaxia.opas.domain.sysparm.SpecialprojectList;
import com.huaxia.opas.domain.sysparm.TargetcompanyList;
import com.huaxia.opas.domain.thirdparty.PbocInfo;
import com.huaxia.opas.domain.thirdparty.ProfessionInfo;
import com.huaxia.opas.domain.thirdparty.ResidenceInfo;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

public class RiskCheckServiceImpl implements RiskCheckService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1099058036952951366L;
	@Resource(name = "riskCheckDao")
	private RiskCheckDao riskCheckDao;

	@Override
	public int save(Object t) throws CoreException {
		return 0;
	}

	@Override
	public int remove(Object t) throws CoreException {
		return 0;
	}

	@Override
	public int update(Object t) throws CoreException {
		return 0;
	}

	@Override
	public Object get(Object t) {
		return null;
	}

	@Override
	public Map<String, String> Query_OPAS_PROMOTER_RISKLIST(String Id) {
		return riskCheckDao.Query_OPAS_PROMOTER_RISKLIST(Id);
	}

	@Override
	public Map<String, String> Query_OPAS_IDENTITY_RISKLIST(String Id) {
		return riskCheckDao.Query_OPAS_IDENTITY_RISKLIST(Id);
	}

	@Override
	public Map<String, String> Query_checkResult(String appId) {
		return riskCheckDao.Query_checkResult(appId);
	}

	@Override
	public int update_checkResult(KeyfiledMarchinfo keyFiledMarchInfo) {
		return riskCheckDao.update_checkResult(keyFiledMarchInfo);
	}

	@Override
	public int save_checkResult(KeyfiledMarchinfo keyFiledMarchInfo) {
		return riskCheckDao.save_checkResult(keyFiledMarchInfo);
	}

	@Override
	public List<AddrRiskList> Query_OPAS_ADDR_RISKLIST(List<String> LIST_APPLY_ALL_ADDR) {
		return riskCheckDao.Query_OPAS_ADDR_RISKLIST(LIST_APPLY_ALL_ADDR);
	}

	@Override
	public List<CompanyRisk> Query_OPAS_COMPANY_RISKLIST(List<String> sTR_APPLY_MOBILE_ID) {
		return riskCheckDao.Query_OPAS_COMPANY_RISKLIST(sTR_APPLY_MOBILE_ID);
	}

	@Override
	public Map<String, String> Query_OPAS_SAMEINDUSTRY_RISKLIST(String Id) {
		return riskCheckDao.Query_OPAS_SAMEINDUSTRY_RISKLIST(Id);
	}

	@Override
	public List<TelRiskList> Query_OPAS_TEL_RISKLIST() {
		return riskCheckDao.Query_OPAS_TEL_RISKLIST();
	}

	public RiskCheckDao getRiskCheckDao() {
		return riskCheckDao;
	}

	public void setRiskCheckDao(RiskCheckDao riskCheckDao) {
		this.riskCheckDao = riskCheckDao;
	}

	@Override
	public List<PbocInfo> Query_OPAS_PBOC_PERSONAL_INFO(Map<String, String> phoneMap) {
		return riskCheckDao.Query_OPAS_PBOC_PERSONAL_INFO(phoneMap);
	}

	@Override
	public List<ProfessionInfo> Query_OPAS_PBOC_PROFESSION_INFO(String appId) {
		return riskCheckDao.Query_OPAS_PBOC_PROFESSION_INFO(appId);
	}

	@Override
	public List<ResidenceInfo> Query_OPAS_PBOC_RESIDENCE_INFO(String appId) {
		return riskCheckDao.Query_OPAS_PBOC_RESIDENCE_INFO(appId);
	}

	@Override
	public List<IfsCustInfo> Query_SP_APS_IFS_CUST_INFO_PHONE(Map<String, String> phoneMap) {
		return riskCheckDao.Query_SP_APS_IFS_CUST_INFO_PHONE(phoneMap);
	}

	@Override
	public List<IfsCustInfo> Query_SP_APS_IFS_CUST_INFO_ADDR(String appId) {
		return riskCheckDao.Query_SP_APS_IFS_CUST_INFO_ADDR(appId);
	}

	@Override
	public List<TeamdealList> Query_OPAS_TEAMDEAL_LIST(String teamCode) {
		return riskCheckDao.Query_OPAS_TEAMDEAL_LIST(teamCode);
	}

	@Override
	public List<MajorschoolList> Query_OPAS_MAJORSCHOOL_LIST() {
		return riskCheckDao.Query_OPAS_MAJORSCHOOL_LIST();
	}

	@Override
	public List<MerchTeamdealList> Query_OPAS_MERCH_TEAMDEAL_LIST(String teamNo) {
		return riskCheckDao.Query_OPAS_MERCH_TEAMDEAL_LIST(teamNo);
	}

	@Override
	public List<TargetcompanyList> Query_OPAS_TARGETCOMPANY_LIST() {
		return riskCheckDao.Query_OPAS_TARGETCOMPANY_LIST();
	}

	@Override
	public List<SpecialprojectList> Query_OPAS_SPECIALPROJECT_LIST(String projectCode) {
		return riskCheckDao.Query_OPAS_SPECIALPROJECT_LIST(projectCode);
	}

	@Override
	public List<CreditTelcheckList> Query_OPAS_CREDIT_TELCHECK_LIST() {
		return riskCheckDao.Query_OPAS_CREDIT_TELCHECK_LIST();
	}

	@Override
	public List<RiskCheck_Apply_C1> Query_OPAS_BIZ_INP_APPL_C1_BY_C1_IDNBR(Map<String, String> appId_Idnbr) {
		return riskCheckDao.Query_OPAS_BIZ_INP_APPL_C1_BY_C1_IDNBR(appId_Idnbr);
	}

	@Override
	public List<RiskCheck_Apply_C1> Query_OPAS_BIZ_INP_APPL_C1_BY_C1_MOBILE(Map<String, String> appId_mobile) {
		return riskCheckDao.Query_OPAS_BIZ_INP_APPL_C1_BY_C1_MOBILE(appId_mobile);
	}

	@Override
	public List<RiskCheck_Apply_C1> Query_OPAS_BIZ_INP_APPL_C1_BY_C1_XMOBIL1To2(Map<String, String> appId_xmobile) {
		return riskCheckDao.Query_OPAS_BIZ_INP_APPL_C1_BY_C1_XMOBIL1To2(appId_xmobile);
	}

	@Override
	public List<RiskCheck_Apply_C1> Query_OPAS_BIZ_INP_APPL_C1_BY_C1_HMADD1To4(Map<String, String> appId_hmadd) {
		return riskCheckDao.Query_OPAS_BIZ_INP_APPL_C1_BY_C1_HMADD1To4(appId_hmadd);
	}

	@Override
	public List<RiskCheck_Apply_C1> selectApply_C1ByPrimaryKey(String appId) {
		return riskCheckDao.selectApply_C1ByPrimaryKey(appId);
	}

	@Override
	public List<RiskCheck_Apply_C2> selectApply_C2ByPrimaryKey(String appId) {
		return riskCheckDao.selectApply_C2ByPrimaryKey(appId);
	}

	@Override
	public Integer Query_OPAS_BIZ_Grey_Indate(Map<String, Object> initParams) {
		return riskCheckDao.Query_OPAS_BIZ_Grey_Indate(initParams);
	}

	@Override
	public List<Map<String, String>> selectApplyDetailByAppId(String appId) {
		return riskCheckDao.selectApplyDetailByAppId(appId);
	}

	@Override
	public Float queryYearBetweenIssuingDate(String appId) {
		return riskCheckDao.queryYearBetweenIssuingDate(appId);
	}

	@Override
	public Float Query_OPAS_BIZ_IsInDate(String appId) {
		return riskCheckDao.Query_OPAS_BIZ_IsInDate(appId);
	}

	@Override
	public List<Map<String, String>> Query_OPAS_COTEL_RISKLIST(String sTR_APPLY_COTEL_ID) {
		return riskCheckDao.Query_OPAS_COTEL_RISKLIST(sTR_APPLY_COTEL_ID);
	}

	@Override
	public List<Map<String, String>> Query_OPAS_COMPANY_ADDRESS_RISKLIST() {
		return riskCheckDao.Query_OPAS_COMPANY_ADDRESS_RISKLIST();
	}

	@Override
	public List<Map<String, String>> Query_OPAS_COMPANY_RISKLIST() {
		return riskCheckDao.Query_OPAS_COMPANY_RISKLIST();
	}

	@Override
	public List<Map<String, String>> Query_OPAS_YDJ_COMPANY_RISKLIST() {
		return riskCheckDao.Query_OPAS_YDJ_COMPANY_RISKLIST();
	}

	@Override
	public Map<String, String> Query_OPAS_YDJ_COTEL_RISKLIST(String sTR_APPLY_COTEL_ID) {
		return riskCheckDao.Query_OPAS_YDJ_COTEL_RISKLIST(sTR_APPLY_COTEL_ID);
	}

	@Override
	public String Query_OPAS_BIZ_EDUCATION_BACKGROUD(String appId) {
		return riskCheckDao.Query_OPAS_BIZ_EDUCATION_BACKGROUD(appId);
	}

	@Override
	public Float queryAgeBetweenIssuingDate(Date idNumberIssuingDate) {
		return riskCheckDao.queryAgeBetweenIssuingDate(idNumberIssuingDate);
	}

	@Override
	public Float Query_OPAS_IDCRAD_ISDate(Map map) {
		return riskCheckDao.Query_OPAS_IDCRAD_ISDate(map);
	}

	@Override
	public Integer Query_OPAS_BIZ_IsInDateByIDindate(Map map) {
		return riskCheckDao.Query_OPAS_BIZ_IsInDateByIDindate(map);
	}

	// 4-17
	@Override
	public Integer Query_OPAS_GoodCompany_ValiDate(Map map) {
		return riskCheckDao.Query_OPAS_GoodCompany_ValiDate(map);
	}

	@Override
	public List<Map<String, String>> Query_OPAS_GoodCompany_ByProjectCode(String projectCode) {
		return riskCheckDao.Query_OPAS_GoodCompany_ByProjectCode(projectCode);
	}

	@Override
	public List<Map<String, String>> Query_OPAS_GoodCompany() {
		return riskCheckDao.Query_OPAS_GoodCompany();
	}

	@Override
	public List<Map<String, String>> Query_OPAS_INNER_RISKINFO_RISKLIST() {
		return riskCheckDao.Query_OPAS_INNER_RISKINFO_RISKLIST();
	}

	@Override
	public Map<String, Object> Query_OPAS_BIZ_Grey_ResonIndate(String appId) {

		return riskCheckDao.Query_OPAS_BIZ_Grey_ResonIndate(appId);
	}

	@Override
	public List<Map<String, String>> Query_OPAS_ANT_RISKLIST(String appId) {
		return riskCheckDao.Query_OPAS_ANT_RISKLIST(appId);
	}

	@Override
	public List<TelRiskList> Query_OPAS_STR_APPLY_MOBILE_ID_RISKLIST(List<String> sTR_APPLY_MOBILE_ID) {
		return riskCheckDao.Query_OPAS_STR_APPLY_MOBILE_ID_RISKLIST(sTR_APPLY_MOBILE_ID);
	}

	@Override
	public List<TelRiskList> Query_OPAS_LIST_CON_TEL_ID_RISKLIST(List<String> lIST_CON_TEL_ID) {
		return riskCheckDao.Query_OPAS_LIST_CON_TEL_ID_RISKLIST(lIST_CON_TEL_ID);
	}

	@Override
	public List<TelRiskList> Query_OPAS_LIST_APPLY_OTHER_TEL_RISKLIST(List<String> lIST_APPLY_OTHER_TEL) {
		return riskCheckDao.Query_OPAS_LIST_APPLY_OTHER_TEL_RISKLIST(lIST_APPLY_OTHER_TEL);
	}

	@Override
	public Map<String, String> QUERY_OPAS_PROMOTER_RISKLIST_C4ABUSER(String c4Abuser) {
		return riskCheckDao.QUERY_OPAS_PROMOTER_RISKLIST_C4ABUSER(c4Abuser);
	}

	@Override
	public Integer QUERY_OPAS_STOCKCUST_IDNBR(Map<String, String> strMap) {
		return riskCheckDao.QUERY_OPAS_STOCKCUST_IDNBR(strMap);
	}

	@Override
	public Map<String, String> queryMatchingCardFlagAndYdjFalg(String appId) {
		return riskCheckDao.queryMatchingCardFlagAndYdjFalg(appId);
	}

	@Override
	public List<TeamdealList> Query_OPAS_TEAMDEAL_BZK_LIST(String upperCase) {
		return  riskCheckDao.Query_OPAS_TEAMDEAL_BZK_LIST(upperCase);
	}

	@Override
	public List<String> Query_OPAS_FILE_APPLICATION_DETAIL_BY_CREDNO(String credNo) {
		return  riskCheckDao.Query_OPAS_FILE_APPLICATION_DETAIL_BY_CREDNO(credNo);
	}

	@Override
	public List<String> Query_TelNo(String judgeAddress2) {
		return  riskCheckDao.Query_TelNo(judgeAddress2);
	}

	@Override
	public List<RiskCheck_Apply_C1> Query_Batch_Bin_Ifo(RiskCheck_Apply_C1 apply_Info1) {
		return riskCheckDao.Query_Batch_Bin_Ifo(apply_Info1);
	}

	@Override
	public List<Union3014Risk> INTERFACE_3014_Billing_Address(String appId) {
		return riskCheckDao.INTERFACE_3014_Billing_Address(appId);
	}

	@Override
	public RiskCheck_Apply_C1 Query_For_Depot(String appId) {
		return riskCheckDao.Query_For_Depot(appId);
	}

	@Override
	public List<String> INTERFACE_3002_mobilePhone(String appId) {
		return riskCheckDao.INTERFACE_3002_mobilePhone(appId);
	}

	@Override
	public List<String> QUERY_OPAS_STOCKCUST_IDNBR_AUTOIDS(Map<String, String> strMap) {
		return riskCheckDao.QUERY_OPAS_STOCKCUST_IDNBR_AUTOIDS(strMap);
	}

	@Override
	public void UPDATE_OPAS_STOCKCUST_IDNBR_BY_AUTOID(String autoId) {
		riskCheckDao.UPDATE_OPAS_STOCKCUST_IDNBR_BY_AUTOID(autoId);
	}

	@Override
	public Float queryAgeByDateOfIssue(String appId) {
		return riskCheckDao.queryAgeByDateOfIssue(appId);
	}

	@Override
	public Float queryAgeByDateOfIssueC2(String appId) {
		return riskCheckDao.queryAgeByDateOfIssueC2(appId);
	}

	@Override
	public Float queryYearBetweenIssuingDateC2(String appId) {
		return riskCheckDao.queryYearBetweenIssuingDateC2(appId);
	}

	@Override
	public List<String> FOREIGN_INDEX_AML(Map<String, Object> strMap) {
		return riskCheckDao.FOREIGN_INDEX_AML(strMap);
	}

	@Override
	public Integer Query_OPAS_TELCHECK_ADDNOTE_BY_APPID(String appId) {
		return riskCheckDao.Query_OPAS_TELCHECK_ADDNOTE_BY_APPID(appId);
	}

	@Override
	public TelcheckAddnote Query_TelcheckAddnote_BY_APPID(String appId) {
		return riskCheckDao.Query_TelcheckAddnote_BY_APPID(appId);
	}

	@Override
	public void insert_OPAS_TELCHECK_ADDNOTE(TelcheckAddnote tt) {
		riskCheckDao.insert_OPAS_TELCHECK_ADDNOTE(tt);
	}

	@Override
	public void Update_OPAS_TELCHECK_ADDNOTE(TelcheckAddnote tk) {
		riskCheckDao.Update_OPAS_TELCHECK_ADDNOTE(tk);
	}

	@Override
	public List<String> IS_LIFE_CUST(String idnbr) {
		return riskCheckDao.IS_LIFE_CUST(idnbr);
	}

	@Override
	public void update_opas_biz_life_appln_life_cust(KeyfiledMarchinfo kfm) {
		riskCheckDao.update_opas_biz_life_appln_life_cust(kfm);
	}
	
	@Override
	public void insertFailedApply(Map<String, String> map) {
		riskCheckDao.insertFailedApply(map);
	}

	@Override
	public List<Map<String, String>> selectUnTopbpm() {
		return riskCheckDao.selectUnTopbpm();
	}


	@Override
	public void updateFlagByProcessIdAndStep(Map<String, String> paramMap) {
		riskCheckDao.updateFlagByProcessIdAndStep(paramMap);
	}

	@Override
	public List<Map<String, String>> selectUnTopbpmTwo() {
		return riskCheckDao.selectUnTopbpmTwo();
	}

	@Override
	public void updateFlagByProcessIdAndStepTopbpm(Map<String, String> paramMap) {
		 riskCheckDao.updateFlagByProcessIdAndStepTopbpm(paramMap);
	}

	@Override
	public List<Map<String, String>> Query_Enterprise_Information(String appId) {
		return riskCheckDao.Query_Enterprise_Information(appId);
	}

	@Override
	public List<Map<String, String>> Query_Enterprise_Status(String appId) {
		return riskCheckDao.Query_Enterprise_Status(appId);
	}

	@Override
	public KeyfiledMarchinfo selectKeyfiledMarchinfoByAppId(String appId) {
		return riskCheckDao.Query_KeyfiledMarchinfo(appId);
	}

	@Override
	public Float selectWorkYear(String appId) {
		return riskCheckDao.Query_WorkYear(appId);
	}

	@Override
	public String selectRiskLevel(String idnbr) {
		return riskCheckDao.Query_RiskLevel(idnbr);
	}

	@Override
	public Map<String, String> QueryPadAddr(String appId) {
		return riskCheckDao.QueryPadAddr(appId);
	}

	@Override
	public Map<String, Object> Query_OPAS_PBOC_PUBLIC_ACCFUND_INFO(String appId) {
		return riskCheckDao.Query_OPAS_PBOC_PUBLIC_ACCFUND_INFO(appId);
	}

	@Override
	public Float Query_Pboc_InfoDate(Map<String, String> phoneMap) {
		return riskCheckDao.Query_Pboc_InfoDate(phoneMap);
	}

	@Override
	public Map<String, Object> Query_PY_PCR_CRS_CRT_SISZ2_BI(String appId) {
		return riskCheckDao.Query_PY_PCR_CRS_CRT_SISZ2_BI(appId);
	}

	@Override
	public Float Query_Py_InfoDate(Map<String, String> phoneMap) {
		return riskCheckDao.Query_Py_InfoDate(phoneMap);
	}

	@Override
	public Map<String, Object> Query_YLZ_REP_DATA(String appId) {
		return riskCheckDao.Query_YLZ_REP_DATA(appId);
	}

	@Override
	public Map<String, Object> Query_Gjjxx_Info(String appId) {
		return riskCheckDao.Query_Gjjxx_Info(appId);
	}

	@Override
	public String Query_XM_GJJ_JCXX(String appId) {
		return riskCheckDao.Query_XM_GJJ_JCXX(appId);
	}

	@Override
	public Float Query_Xm_Gjj_InfoDate(Map<String, String> param) {
		return riskCheckDao.Query_Xm_Gjj_InfoDate(param);
	}

	@Override
	public List<Map<String, String>> Query_OPAS_MARKETER_LIST() {
		return riskCheckDao.Query_OPAS_MARKETER_LIST();
	}

	@Override
	public List<String> Query_OPAS_PAD_LOCATION_ADDRESS_INFO(String cityId) {
		return riskCheckDao.Query_OPAS_PAD_LOCATION_ADDRESS_INFO(cityId);
	}

	@Override
	public String Query_NB_BASE(String appId) {
		return riskCheckDao.Query_NB_BASE(appId);
	}

	@Override
	public Float Query_NB_BASE_InfoDate(Map<String, String> param) {
		return riskCheckDao.Query_NB_BASE_InfoDate(param);
	}

	@Override
	public String Query_WZ_GR_GJJZL(String appId) {
		return riskCheckDao.Query_WZ_GR_GJJZL(appId);
	}

	@Override
	public Float Query_WZ_GR_GJJZLDate(Map<String, String> param) {
		return riskCheckDao.Query_WZ_GR_GJJZLDate(param);
	}

	@Override
	public String Query_YC_INFO(String appId) {
		return riskCheckDao.Query_YC_INFO(appId);
	}

	@Override
	public Float Query_YC_INFODate(Map<String, String> param) {
		return riskCheckDao.Query_YC_INFODate(param);
	}

	@Override
	public ExcellentCompanyList Query_OPAS_EXCELLENT_COMPANY_LIST(Map<String, Object> initParams) {
		return  riskCheckDao.Query_OPAS_EXCELLENT_COMPANY_LIST(initParams);
	}

	@Override
	public void update_OPAS_KEYFILED_RESULTINFO(KeyfiledResultInfo keyfiledResultInfo,RiskCheck_Apply_C1 apply_Info1) {
		String appId = apply_Info1.getAppId();
		String queryAppId = "";
		if(("1").equals(apply_Info1.getYdjFlag())){
			if("1".equals(apply_Info1.getMatchingCardFlag())){
				queryAppId = appId.substring(15, 16)=="1"?appId.substring(0, appId.length() - 1) + "2" : appId.substring(0, appId.length() - 1) + "1";
			} else {
				queryAppId = appId;
			}
		}else {
			queryAppId = appId;
		}
		keyfiledResultInfo.setAppId(appId.substring(0, 15));
		
		int result = riskCheckDao.Query_OPAS_KEYFILED_RESULTINFO(keyfiledResultInfo);
		keyfiledResultInfo.setAppId(queryAppId);
		if(result>0){
			// 更新数据库记录
			riskCheckDao.update_KEYFILED_RESULTINFO(keyfiledResultInfo);
		}else{
			// 保存入库
			keyfiledResultInfo.setAutoId(StringUtil.randomUUIDPlainString());
			riskCheckDao.save_KEYFILED_RESULTINFO(keyfiledResultInfo);
		}
	}
	
	@Override
	public KeyfiledResultInfo Query_KEYFILED_RESULTINFO(KeyfiledResultInfo keyfiledResultInfo) {
		return riskCheckDao.Query_KEYFILED_RESULTINFO(keyfiledResultInfo);
	}

	@Override
	public List<String> Query_OPAS_LOW_RISK_LIST(Map<String, Object> initParam) {
		return riskCheckDao.Query_OPAS_LOW_RISK_LIST(initParam);
	}
}
