package com.huaxia.opas.dao.riskcheck.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
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

public class RiskCheckDaoImpl extends AbstractDAO implements RiskCheckDao{

	private static final String NAMESPACES_STEP1 = "riskcheck_step1.";
	private static final String NAMESPACES_STEP2 = "riskcheck_step2.";
	private static final String NAMESPACES_STEP3 = "riskcheck_step3.";
	private static final String NAMESPACES_STEP4 = "riskcheck_step4.";
	private static final String NAMESPACES_APPLY_C1 = "riskcheck_ApplyC1.";
	private static final String NAMESPACES_APPLY_C2 = "riskcheck_ApplyC2.";
	private static final long serialVersionUID = 4229183934930791024L;

	@Override
	public Map<String, String> Query_OPAS_PROMOTER_RISKLIST(String Id) {
		return getSqlMap().queryForObject(NAMESPACES_STEP1 + "Query_OPAS_PROMOTER_RISKLIST", Id);
	}

	@Override
	public Map<String, String> Query_OPAS_IDENTITY_RISKLIST(String Id) {
		return getSqlMap().queryForObject(NAMESPACES_STEP1 + "Query_OPAS_IDENTITY_RISKLIST", Id);

	}

	@Override
	public Map<String, String> Query_checkResult(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP1 + "Query_checkResult", appId);

	}

	@Override
	public int update_checkResult(KeyfiledMarchinfo keyFiledMarchInfo) {
		return getSqlMap().update(NAMESPACES_STEP1 + "update_checkResult", keyFiledMarchInfo);
	}

	@Override
	public int save_checkResult(KeyfiledMarchinfo keyFiledMarchInfo) {
		return getSqlMap().insert(NAMESPACES_STEP1 + "save_checkResult", keyFiledMarchInfo);
	}

	@Override
	public List<AddrRiskList> Query_OPAS_ADDR_RISKLIST(List<String> LIST_APPLY_ALL_ADDR) {
		return getSqlMap().queryForList(NAMESPACES_STEP1 + "Query_OPAS_ADDR_RISKLIST",LIST_APPLY_ALL_ADDR);
	}

	@Override
	public List<CompanyRisk> Query_OPAS_COMPANY_RISKLIST(List<String> sTR_APPLY_MOBILE_ID) {
		return getSqlMap().queryForList(NAMESPACES_STEP1 + "Query_OPAS_COMPANY_RISKLIST",sTR_APPLY_MOBILE_ID);
	}

	@Override
	public Map<String, String> Query_OPAS_SAMEINDUSTRY_RISKLIST(String Id) {
		return getSqlMap().queryForObject(NAMESPACES_STEP1 + "Query_OPAS_SAMEINDUSTRY_RISKLIST", Id);
	}

	@Override
	public List<TelRiskList> Query_OPAS_TEL_RISKLIST() {
		return getSqlMap().queryForList(NAMESPACES_STEP1 + "Query_OPAS_TEL_RISKLIST");
	}

	@Override
	public List<PbocInfo> Query_OPAS_PBOC_PERSONAL_INFO(Map<String, String> phoneMap) {
		return getSqlMap().queryForList(NAMESPACES_STEP3 + "Query_OPAS_PBOC_PERSONAL_INFO",phoneMap);
	}

	@Override
	public List<ProfessionInfo> Query_OPAS_PBOC_PROFESSION_INFO(String appId) {
		return getSqlMap().queryForList(NAMESPACES_STEP3 + "Query_OPAS_PBOC_PROFESSION_INFO",appId);
	}

	@Override
	public List<ResidenceInfo> Query_OPAS_PBOC_RESIDENCE_INFO(String appId) {
		return getSqlMap().queryForList(NAMESPACES_STEP3 + "Query_OPAS_PBOC_RESIDENCE_INFO",appId);
	}

	@Override
	public List<IfsCustInfo> Query_SP_APS_IFS_CUST_INFO_PHONE(Map<String, String> phoneMap) {
		return getSqlMap().queryForList(NAMESPACES_STEP3 + "Query_SP_APS_IFS_CUST_INFO_PHONE",phoneMap);
	}

	@Override
	public List<IfsCustInfo> Query_SP_APS_IFS_CUST_INFO_ADDR(String appId) {
		return getSqlMap().queryForList(NAMESPACES_STEP3 + "Query_SP_APS_IFS_CUST_INFO_ADDR",appId);
	}

	@Override
	public List<TeamdealList> Query_OPAS_TEAMDEAL_LIST(String teamCode) {
		return getSqlMap().queryForList(NAMESPACES_STEP2 + "Query_OPAS_TEAMDEAL_LIST",teamCode);
	}

	@Override
	public List<MajorschoolList> Query_OPAS_MAJORSCHOOL_LIST() {
		return getSqlMap().queryForList(NAMESPACES_STEP2 + "Query_OPAS_MAJORSCHOOL_LIST");
	}

	@Override
	public List<MerchTeamdealList> Query_OPAS_MERCH_TEAMDEAL_LIST(String teamNo) {
		return getSqlMap().queryForList(NAMESPACES_STEP2 + "Query_OPAS_MERCH_TEAMDEAL_LIST",teamNo);
	}

	@Override
	public List<TargetcompanyList> Query_OPAS_TARGETCOMPANY_LIST() {
		return getSqlMap().queryForList(NAMESPACES_STEP2 + "Query_OPAS_TARGETCOMPANY_LIST");
	}

	@Override
	public List<SpecialprojectList> Query_OPAS_SPECIALPROJECT_LIST(String projectCode) {
		return getSqlMap().queryForList(NAMESPACES_STEP2 + "Query_OPAS_SPECIALPROJECT_LIST",projectCode);
	}

	@Override
	public List<CreditTelcheckList> Query_OPAS_CREDIT_TELCHECK_LIST() {
		return getSqlMap().queryForList(NAMESPACES_STEP2 + "Query_OPAS_CREDIT_TELCHECK_LIST");
	}

	@Override
	public List<RiskCheck_Apply_C1> Query_OPAS_BIZ_INP_APPL_C1_BY_C1_IDNBR(Map<String, String> appId_Idnbr) {
		return getSqlMap().queryForList(NAMESPACES_STEP2 + "Query_OPAS_BIZ_INP_APPL_C1_BY_C1_IDNBR",appId_Idnbr);
	}

	@Override
	public List<RiskCheck_Apply_C1> Query_OPAS_BIZ_INP_APPL_C1_BY_C1_MOBILE(Map<String, String> appId_mobile) {
		return getSqlMap().queryForList(NAMESPACES_STEP2 + "Query_OPAS_BIZ_INP_APPL_C1_BY_C1_MOBILE",appId_mobile);
	}

	@Override
	public List<RiskCheck_Apply_C1> Query_OPAS_BIZ_INP_APPL_C1_BY_C1_XMOBIL1To2(Map<String, String> appId_xmobile) {
		return getSqlMap().queryForList(NAMESPACES_STEP2 + "Query_OPAS_BIZ_INP_APPL_C1_BY_C1_XMOBIL1To2",appId_xmobile);
	}

	@Override
	public List<RiskCheck_Apply_C1> Query_OPAS_BIZ_INP_APPL_C1_BY_C1_HMADD1To4(Map<String, String> appId_hmadd) {
		return getSqlMap().queryForList(NAMESPACES_STEP2 + "Query_OPAS_BIZ_INP_APPL_C1_BY_C1_HMADD1To4",appId_hmadd);
	}

	@Override
	public List<RiskCheck_Apply_C1> selectApply_C1ByPrimaryKey(String appId) {
		return getSqlMap().queryForList(NAMESPACES_APPLY_C1 + "selectApply_C1ByPrimaryKey",appId);

	}

	@Override
	public List<RiskCheck_Apply_C2> selectApply_C2ByPrimaryKey(String appId) {
		return getSqlMap().queryForList(NAMESPACES_APPLY_C2 + "selectApply_C2ByPrimaryKey",appId);
	}

	@Override
	public Integer Query_OPAS_BIZ_Grey_Indate(Map<String, Object> initParams) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"Query_OPAS_BIZ_Grey_Indate",initParams);
	}

	@Override
	public List<Map<String, String>> selectApplyDetailByAppId(String appId) {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"selectApplyDetailByAppId",appId);
	}

	@Override
	public Float queryYearBetweenIssuingDate(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"queryYearBetweenIssuingDate", appId);
	}

	@Override
	public Float Query_OPAS_BIZ_IsInDate(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"Query_OPAS_BIZ_IsInDate", appId);
	}

	@Override
	public List<Map<String, String>> Query_OPAS_COTEL_RISKLIST(String sTR_APPLY_COTEL_ID) {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"Query_OPAS_COTEL_RISKLIST",sTR_APPLY_COTEL_ID);
	}

	@Override
	public List<Map<String, String>> Query_OPAS_COMPANY_ADDRESS_RISKLIST() {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"Query_OPAS_COMPANY_ADDRESS_RISKLIST");
	}

	@Override
	public List<Map<String, String>> Query_OPAS_COMPANY_RISKLIST() {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"Query_OPAS_COMPANY_RISKLIST");
	}

	@Override
	public List<Map<String, String>> Query_OPAS_YDJ_COMPANY_RISKLIST() {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"Query_OPAS_YDJ_COMPANY_RISKLIST");
	}

	@Override
	public Map<String, String> Query_OPAS_YDJ_COTEL_RISKLIST(String sTR_APPLY_COTEL_ID) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"Query_OPAS_YDJ_COTEL_RISKLIST",sTR_APPLY_COTEL_ID);
	}

	@Override
	public String Query_OPAS_BIZ_EDUCATION_BACKGROUD(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"Query_OPAS_BIZ_EDUCATION_BACKGROUD",appId);
	}

	@Override
	public Float queryAgeBetweenIssuingDate(Date idNumberIssuingDate) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"queryAgeBetweenIssuingDate",idNumberIssuingDate);
	}

	@Override
	public Float Query_OPAS_IDCRAD_ISDate(Map map) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"Query_OPAS_IDCRAD_ISDate",map);
	}

	@Override
	public Integer Query_OPAS_BIZ_IsInDateByIDindate(Map map) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"Query_OPAS_BIZ_IsInDateByIDindate",map);
	}
	//4-17
	@Override
	public Integer Query_OPAS_GoodCompany_ValiDate(Map map) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"Query_OPAS_GoodCompany_ValiDate",map);
	}

	@Override
	public List<Map<String, String>> Query_OPAS_GoodCompany_ByProjectCode(String projectCode) {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"Query_OPAS_GoodCompany_ByProjectCode",projectCode);
	}

	@Override
	public List<Map<String, String>> Query_OPAS_GoodCompany() {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"Query_OPAS_GoodCompany");
	}

	@Override
	public List<Map<String, String>> Query_OPAS_INNER_RISKINFO_RISKLIST() {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"Query_OPAS_INNER_RISKINFO_RISKLIST");
	}

	@Override
	public Map<String, Object> Query_OPAS_BIZ_Grey_ResonIndate(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"Query_OPAS_BIZ_Grey_ResonIndate",appId);
	}

	@Override
	public List<Map<String, String>> Query_OPAS_ANT_RISKLIST(String appId) {
		 return getSqlMap().queryForList(NAMESPACES_STEP3+"Query_OPAS_ANT_RISKLIST",appId);
	}

	@Override
	public List<TelRiskList> Query_OPAS_STR_APPLY_MOBILE_ID_RISKLIST(List sTR_APPLY_MOBILE_ID) {
		return getSqlMap().queryForList(NAMESPACES_STEP1+"Query_OPAS_STR_APPLY_MOBILE_ID_RISKLIST",sTR_APPLY_MOBILE_ID);
	}

	@Override
	public List<TelRiskList> Query_OPAS_LIST_CON_TEL_ID_RISKLIST(List lIST_CON_TEL_ID) {
		return getSqlMap().queryForList(NAMESPACES_STEP1+"Query_OPAS_LIST_CON_TEL_ID_RISKLIST",lIST_CON_TEL_ID);
	}

	@Override
	public List<TelRiskList> Query_OPAS_LIST_APPLY_OTHER_TEL_RISKLIST(List lIST_APPLY_OTHER_TEL) {
		return getSqlMap().queryForList(NAMESPACES_STEP1+"Query_OPAS_LIST_APPLY_OTHER_TEL_RISKLIST",lIST_APPLY_OTHER_TEL);
	}

	@Override
	public Map<String, String> QUERY_OPAS_PROMOTER_RISKLIST_C4ABUSER(String c4Abuser) {
		return getSqlMap().queryForObject(NAMESPACES_STEP1+"QUERY_OPAS_PROMOTER_RISKLIST_C4ABUSER",c4Abuser);
	}

	@Override
	public Integer QUERY_OPAS_STOCKCUST_IDNBR(Map<String, String> strMap) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"QUERY_OPAS_STOCKCUST_IDNBR", strMap);
	}

	@Override
	public Map<String, String> queryMatchingCardFlagAndYdjFalg(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"queryMatchingCardFlagAndYdjFalg", appId);
	}

	@Override
	public List<TeamdealList> Query_OPAS_TEAMDEAL_BZK_LIST(String teamCode) {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"Query_OPAS_TEAMDEAL_BZK_LIST",teamCode);
	}

	@Override
	public List<String> Query_OPAS_FILE_APPLICATION_DETAIL_BY_CREDNO(String credNo) {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"Query_OPAS_FILE_APPLICATION_DETAIL_BY_CREDNO",credNo);
	}

	@Override
	public List<String> Query_TelNo(String judgeAddress2) {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"Query_TelNo", judgeAddress2);
	}

	@Override
	public List<RiskCheck_Apply_C1> Query_Batch_Bin_Ifo(RiskCheck_Apply_C1 apply_Info1) {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"Query_Batch_Bin_Ifo",apply_Info1);
	}

	@Override
	public List<Union3014Risk> INTERFACE_3014_Billing_Address(String appId) {
		return getSqlMap().queryForList(NAMESPACES_STEP3+"INTERFACE_3014_Billing_Address",appId);
	}

	@Override
	public RiskCheck_Apply_C1 Query_For_Depot(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_APPLY_C1+"Query_For_Depot", appId);
	}

	@Override
	public List<String> INTERFACE_3002_mobilePhone(String appId) {
		return getSqlMap().queryForList(NAMESPACES_STEP3+"INTERFACE_3002_mobilePhone",appId);
	}

	@Override
	public void UPDATE_OPAS_STOCKCUST_IDNBR_BY_AUTOID(String autoId) {
		getSqlMap().update(NAMESPACES_STEP2+"UPDATE_OPAS_STOCKCUST_IDNBR_BY_AUTOID",autoId);
	}

	@Override
	public List<String> QUERY_OPAS_STOCKCUST_IDNBR_AUTOIDS(Map<String, String> strMap) {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"QUERY_OPAS_STOCKCUST_IDNBR_AUTOIDS",strMap);
	}

	@Override
	public Float queryAgeByDateOfIssue(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"queryAgeByDateOfIssue", appId);
	}

	@Override
	public Float queryAgeByDateOfIssueC2(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"queryAgeByDateOfIssueC2", appId);
	}

	@Override
	public Float queryYearBetweenIssuingDateC2(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"queryYearBetweenIssuingDateC2", appId);
	}
	
	@Override
	public List<String> FOREIGN_INDEX_AML(Map<String, Object> map) {
		return getSqlMap().queryForList(NAMESPACES_STEP1+"FOREIGN_INDEX_AML",map);
	}

	@Override
	public Integer Query_OPAS_TELCHECK_ADDNOTE_BY_APPID(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_OPAS_TELCHECK_ADDNOTE_BY_APPID", appId);
	}

	@Override
	public TelcheckAddnote Query_TelcheckAddnote_BY_APPID(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_TelcheckAddnote_BY_APPID", appId);
	}

	@Override
	public void insert_OPAS_TELCHECK_ADDNOTE(TelcheckAddnote tt) {
		getSqlMap().insert(NAMESPACES_STEP3+"insert_OPAS_TELCHECK_ADDNOTE", tt);
	}

	@Override
	public void Update_OPAS_TELCHECK_ADDNOTE(TelcheckAddnote tk) {
		getSqlMap().update(NAMESPACES_STEP3+"Update_OPAS_TELCHECK_ADDNOTE", tk);
	}

	@Override
	public List<String> IS_LIFE_CUST(String idnbr) {
		return getSqlMap().queryForList(NAMESPACES_STEP1+"IS_LIFE_CUST",idnbr);
	}

	@Override
	public void update_opas_biz_life_appln_life_cust(KeyfiledMarchinfo kfm) {
		getSqlMap().update(NAMESPACES_STEP1+"update_opas_biz_life_appln_life_cust", kfm);
	}
	
	@Override
	public void insertFailedApply(Map<String, String> map) {
		getSqlMap().insert(NAMESPACES_STEP1+"insertFailedApply", map);
	}

	@Override
	public List<Map<String, String>> selectUnTopbpm() {
		return getSqlMap().queryForList(NAMESPACES_STEP1+"selectUnTopbpm");
	}


	@Override
	public void updateFlagByProcessIdAndStep(Map<String,String> paramMap) {
		getSqlMap().update(NAMESPACES_STEP1+"updateFlagByProcessIdAndStep",paramMap);
	}

	@Override
	public List<Map<String, String>> selectUnTopbpmTwo() {
		return getSqlMap().queryForList(NAMESPACES_STEP1+"selectUnTopbpmTwo");
	}


	@Override
	public void updateFlagByProcessIdAndStepTopbpm(Map<String, String> paramMap) {
		getSqlMap().update(NAMESPACES_STEP1+"updateFlagByProcessIdAndStepTopbpm",paramMap);
	}

	@Override
	public List<Map<String, String>> Query_Enterprise_Information(String appId) {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"Query_Enterprise_Information",appId);
	}

	@Override
	public List<Map<String, String>> Query_Enterprise_Status(String appId) {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"Query_Enterprise_Status",appId);
	}

	@Override
	public KeyfiledMarchinfo Query_KeyfiledMarchinfo(String appId) {
		return (KeyfiledMarchinfo) (getSqlMap().queryForObject(NAMESPACES_STEP2 + "Query_KeyfiledMarchinfo", appId));
	}

	@Override
	public Float Query_WorkYear(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"Query_WorkYear_BY_APPID", appId);
	}

	@Override
	public String Query_RiskLevel(String idnbr) {
		return getSqlMap().queryForObject(NAMESPACES_STEP1+"Query_RiskLevel", idnbr);
	}

	@Override
	public Map<String, String> QueryPadAddr(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP2+"QueryPadAddr", appId);
	}

	@Override
	public Map<String, Object> Query_OPAS_PBOC_PUBLIC_ACCFUND_INFO(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_OPAS_PBOC_PUBLIC_ACCFUND_INFO", appId);
	}

	@Override
	public Float Query_Pboc_InfoDate(Map<String, String> phoneMap) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_Pboc_InfoDate", phoneMap);
	}

	@Override
	public Map<String, Object> Query_PY_PCR_CRS_CRT_SISZ2_BI(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_PY_PCR_CRS_CRT_SISZ2_BI", appId);
	}

	@Override
	public Float Query_Py_InfoDate(Map<String, String> phoneMap) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_Py_InfoDate", phoneMap);
	}

	@Override
	public Map<String, Object> Query_YLZ_REP_DATA(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_YLZ_REP_DATA", appId);
	}

	@Override
	public Map<String, Object> Query_Gjjxx_Info(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_Gjjxx_Info", appId);
	}

	@Override
	public String Query_XM_GJJ_JCXX(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_XM_GJJ_JCXX", appId);
	}

	@Override
	public Float Query_Xm_Gjj_InfoDate(Map<String, String> param) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_Xm_Gjj_InfoDate", param);
	}

	@Override
	public List<Map<String, String>> Query_OPAS_MARKETER_LIST() {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"Query_OPAS_MARKETER_LIST");
	}

	@Override
	public List<String> Query_OPAS_PAD_LOCATION_ADDRESS_INFO(String cityId) {
		return getSqlMap().queryForList(NAMESPACES_STEP2+"Query_OPAS_PAD_LOCATION_ADDRESS_INFO",cityId);
	}

	@Override
	public String Query_NB_BASE(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_NB_BASE", appId);
	}

	@Override
	public Float Query_NB_BASE_InfoDate(Map<String, String> param) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_NB_BASE_InfoDate", param);
	}

	@Override
	public String Query_WZ_GR_GJJZL(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_WZ_GR_GJJZL",appId);
	}

	@Override
	public Float Query_WZ_GR_GJJZLDate(Map<String, String> param) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_WZ_GR_GJJZLDate",param);
	}

	@Override
	public String Query_YC_INFO(String appId) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_YC_INFO",appId);
	}

	@Override
	public Float Query_YC_INFODate(Map<String, String> param) {
		return getSqlMap().queryForObject(NAMESPACES_STEP3+"Query_YC_INFODate",param);
	}

	@Override
	public ExcellentCompanyList Query_OPAS_EXCELLENT_COMPANY_LIST(Map<String, Object> initParams) {
		return getSqlMap().queryForObject(NAMESPACES_STEP4+"Query_OPAS_EXCELLENT_COMPANY_LIST",initParams);
	}

	@Override
	public Integer Query_OPAS_KEYFILED_RESULTINFO(KeyfiledResultInfo keyfiledResultInfo) {
		return getSqlMap().queryForObject(NAMESPACES_STEP4+"Query_OPAS_KEYFILED_RESULTINFO",keyfiledResultInfo);
	}
	@Override
	public int update_KEYFILED_RESULTINFO(KeyfiledResultInfo keyfiledResultInfo) {
		return getSqlMap().update(NAMESPACES_STEP4 + "update_KEYFILED_RESULTINFO", keyfiledResultInfo);
	}
	@Override
	public int save_KEYFILED_RESULTINFO(KeyfiledResultInfo keyfiledResultInfo) {
		return getSqlMap().insert(NAMESPACES_STEP4 + "save_KEYFILED_RESULTINFO", keyfiledResultInfo);
	}
	
	@Override
	public KeyfiledResultInfo Query_KEYFILED_RESULTINFO(KeyfiledResultInfo keyfiledResultInfo) {
		return getSqlMap().queryForObject(NAMESPACES_STEP4+"Query_KEYFILED_RESULTINFO",keyfiledResultInfo);
	}

	@Override
	public List<String> Query_OPAS_LOW_RISK_LIST(Map<String, Object> initParam) {
		return getSqlMap().queryForList(NAMESPACES_STEP1+"Query_OPAS_LOW_RISK_LIST",initParam);
	}
}
