package com.huaxia.opas.dao.riskcheck;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

public interface RiskCheckDao {

	public Map<String, String> Query_OPAS_PROMOTER_RISKLIST(String Id);
	public Map<String, String> Query_OPAS_IDENTITY_RISKLIST(String Id);
	public Map<String, String> Query_checkResult(String appId);
	public int update_checkResult(KeyfiledMarchinfo keyFiledMarchInfo);
	public int save_checkResult(KeyfiledMarchinfo keyFiledMarchInfo);
	public List<AddrRiskList> Query_OPAS_ADDR_RISKLIST(List<String> LIST_APPLY_ALL_ADDR);
	public List<CompanyRisk> Query_OPAS_COMPANY_RISKLIST(List<String> sTR_APPLY_MOBILE_ID);
	public Map<String, String> Query_OPAS_SAMEINDUSTRY_RISKLIST(String Id);
	public List<TelRiskList> Query_OPAS_TEL_RISKLIST();
	public List<PbocInfo> Query_OPAS_PBOC_PERSONAL_INFO(Map<String, String> phoneMap);
	public List<ProfessionInfo> Query_OPAS_PBOC_PROFESSION_INFO(String appId);
	public List<ResidenceInfo> Query_OPAS_PBOC_RESIDENCE_INFO(String appId);
	public List<IfsCustInfo> Query_SP_APS_IFS_CUST_INFO_PHONE(Map<String, String> phoneMap);
	public List<IfsCustInfo> Query_SP_APS_IFS_CUST_INFO_ADDR(String appId);
	//step2---start
	public List<TeamdealList> Query_OPAS_TEAMDEAL_LIST(String teamCode);
	public List<MajorschoolList> Query_OPAS_MAJORSCHOOL_LIST();
	public List<MerchTeamdealList> Query_OPAS_MERCH_TEAMDEAL_LIST(String teamNo);
	public List<TargetcompanyList> Query_OPAS_TARGETCOMPANY_LIST();
	public List<SpecialprojectList> Query_OPAS_SPECIALPROJECT_LIST(String projectCode);
	public List<CreditTelcheckList> Query_OPAS_CREDIT_TELCHECK_LIST();
	//=========与存量历史数据比对============
	public List<RiskCheck_Apply_C1> Query_OPAS_BIZ_INP_APPL_C1_BY_C1_IDNBR(Map<String, String> appId_Idnbr);
	public List<RiskCheck_Apply_C1> Query_OPAS_BIZ_INP_APPL_C1_BY_C1_MOBILE(Map<String, String> appId_mobile);
	public List<RiskCheck_Apply_C1> Query_OPAS_BIZ_INP_APPL_C1_BY_C1_XMOBIL1To2(Map<String, String> appId_xmobile);
	public List<RiskCheck_Apply_C1> Query_OPAS_BIZ_INP_APPL_C1_BY_C1_HMADD1To4(Map<String, String> appId_hmadd);
	//灰名单
	public Integer Query_OPAS_BIZ_Grey_Indate(Map<String, Object> initParams);
	public List<Map<String, String>> selectApplyDetailByAppId(String appId);
	public Float queryYearBetweenIssuingDate(String appId);
	public Float Query_OPAS_BIZ_IsInDate(String appId);
	//征信库
	public List<Map<String, String>> Query_OPAS_COTEL_RISKLIST(String sTR_APPLY_COTEL_ID);
	public List<Map<String, String>> Query_OPAS_COMPANY_ADDRESS_RISKLIST();
	public List<Map<String, String>> Query_OPAS_COMPANY_RISKLIST();
	public List<Map<String, String>> Query_OPAS_YDJ_COMPANY_RISKLIST();
	public Map<String, String> Query_OPAS_YDJ_COTEL_RISKLIST(String sTR_APPLY_COTEL_ID);
	//step2---end
	
	//根据APP_ID查询主卡、副卡信息表
	public List<RiskCheck_Apply_C1> selectApply_C1ByPrimaryKey(String appId);
	public List<RiskCheck_Apply_C2> selectApply_C2ByPrimaryKey(String appId);
	
	public String Query_OPAS_BIZ_EDUCATION_BACKGROUD(String appId);
	public Float queryAgeBetweenIssuingDate(Date idNumberIssuingDate);
	public Float Query_OPAS_IDCRAD_ISDate(Map map);
	public Integer Query_OPAS_BIZ_IsInDateByIDindate(Map map);
	//4-17
	public Integer Query_OPAS_GoodCompany_ValiDate(Map map);
	public List<Map<String, String>> Query_OPAS_GoodCompany_ByProjectCode(String projectCode);
	public List<Map<String, String>> Query_OPAS_GoodCompany();
	public List<Map<String, String>> Query_OPAS_INNER_RISKINFO_RISKLIST();
	public Map<String, Object> Query_OPAS_BIZ_Grey_ResonIndate(String appId);
	public List<Map<String, String>> Query_OPAS_ANT_RISKLIST(String appId);
	//05-11
	public List<TelRiskList> Query_OPAS_STR_APPLY_MOBILE_ID_RISKLIST(List<String> sTR_APPLY_MOBILE_ID);
	public List<TelRiskList> Query_OPAS_LIST_CON_TEL_ID_RISKLIST(List<String> lIST_CON_TEL_ID);
	public List<TelRiskList> Query_OPAS_LIST_APPLY_OTHER_TEL_RISKLIST(List<String> lIST_APPLY_OTHER_TEL);
	public Map<String, String> QUERY_OPAS_PROMOTER_RISKLIST_C4ABUSER(String c4Abuser);
	public Integer QUERY_OPAS_STOCKCUST_IDNBR(Map<String, String> strMap);
	public Map<String, String> queryMatchingCardFlagAndYdjFalg(String appId);
	public List<TeamdealList> Query_OPAS_TEAMDEAL_BZK_LIST(String upperCase);
	public List<String> Query_OPAS_FILE_APPLICATION_DETAIL_BY_CREDNO(String credNo);
	public List<String> Query_TelNo(String judgeAddress2);
	public List<RiskCheck_Apply_C1> Query_Batch_Bin_Ifo(RiskCheck_Apply_C1 apply_Info1);
	public List<Union3014Risk> INTERFACE_3014_Billing_Address(String appId);
	public RiskCheck_Apply_C1 Query_For_Depot(String appId);
	public List<String> INTERFACE_3002_mobilePhone(String appId);
	public void UPDATE_OPAS_STOCKCUST_IDNBR_BY_AUTOID(String autoId);
	public List<String> QUERY_OPAS_STOCKCUST_IDNBR_AUTOIDS(Map<String, String> strMap);
	public Float queryAgeByDateOfIssue(String appId);
	public Float queryAgeByDateOfIssueC2(String appId);
	public Float queryYearBetweenIssuingDateC2(String appId);
	public List<String> FOREIGN_INDEX_AML(Map<String, Object> strMap);
	public Integer Query_OPAS_TELCHECK_ADDNOTE_BY_APPID(String appId);
	public TelcheckAddnote Query_TelcheckAddnote_BY_APPID(String appId);
	public void insert_OPAS_TELCHECK_ADDNOTE(TelcheckAddnote tt);
	public void Update_OPAS_TELCHECK_ADDNOTE(TelcheckAddnote tk);
	public List<String> IS_LIFE_CUST(String idnbr);
	public void update_opas_biz_life_appln_life_cust(KeyfiledMarchinfo kfm);
	public void insertFailedApply(Map<String, String> map);
	public List<Map<String, String>> selectUnTopbpm();
	public void updateFlagByProcessIdAndStep(Map<String, String> paramMap);
	public List<Map<String, String>> selectUnTopbpmTwo();
	public void updateFlagByProcessIdAndStepTopbpm(Map<String, String> paramMap);
	public List<Map<String, String>> Query_Enterprise_Information(String appId);
	public List<Map<String, String>> Query_Enterprise_Status(String appId);
	public KeyfiledMarchinfo Query_KeyfiledMarchinfo(String appId);
	public Float Query_WorkYear(String appId);
	public String Query_RiskLevel(String idnbr);
	public Map<String, String> QueryPadAddr(String appId);
	public Map<String, Object> Query_OPAS_PBOC_PUBLIC_ACCFUND_INFO(String appId);
	public Float Query_Pboc_InfoDate(Map<String, String> phoneMap);
	public Map<String, Object> Query_PY_PCR_CRS_CRT_SISZ2_BI(String appId);
	public Float Query_Py_InfoDate(Map<String, String> phoneMap);
	public Map<String, Object> Query_YLZ_REP_DATA(String appId);
	public Map<String, Object> Query_Gjjxx_Info(String appId);
	public String Query_XM_GJJ_JCXX(String appId);
	public Float Query_Xm_Gjj_InfoDate(Map<String, String> param);
	public List<Map<String, String>> Query_OPAS_MARKETER_LIST();
	public List<String> Query_OPAS_PAD_LOCATION_ADDRESS_INFO(String cityId);
	public String Query_NB_BASE(String appId);
	public Float Query_NB_BASE_InfoDate(Map<String, String> param);
	public String Query_WZ_GR_GJJZL(String appId);
	public Float Query_WZ_GR_GJJZLDate(Map<String, String> param);
	public String Query_YC_INFO(String appId);
	public Float Query_YC_INFODate(Map<String, String> param);
	public ExcellentCompanyList Query_OPAS_EXCELLENT_COMPANY_LIST(Map<String, Object> initParams);
	public Integer Query_OPAS_KEYFILED_RESULTINFO(KeyfiledResultInfo keyfiledResultInfo);
	public int update_KEYFILED_RESULTINFO(KeyfiledResultInfo keyfiledResultInfo);
	public int save_KEYFILED_RESULTINFO(KeyfiledResultInfo keyfiledResultInfo);
	public KeyfiledResultInfo Query_KEYFILED_RESULTINFO(
			KeyfiledResultInfo keyfiledResultInfo);
	public List<String> Query_OPAS_LOW_RISK_LIST(Map<String, Object> initParam);
}
