package com.huaxia.opas.service.decision.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.allot.AllotApplyAllotDao;
import com.huaxia.opas.dao.apply.ApplyInfoDao;
import com.huaxia.opas.dao.apply.ApplyModifyLogDao;
import com.huaxia.opas.dao.apply.BizInpApplC1Dao;
import com.huaxia.opas.dao.compare.RevCompInfoDao;
import com.huaxia.opas.dao.decision.CreditTelcheckListDao;
import com.huaxia.opas.dao.decision.FicoSdBlazeDao;
import com.huaxia.opas.dao.decision.FicoYdjBlazeDao;
import com.huaxia.opas.dao.decision.GoodcompanyListDao;
import com.huaxia.opas.dao.decision.KeyfiledMarchinfoDao;
import com.huaxia.opas.dao.decision.MerchTeamdealListDao;
import com.huaxia.opas.dao.decision.OpasConfQuestionDao;
import com.huaxia.opas.dao.decision.SpecialprojectListDao;
import com.huaxia.opas.dao.decision.StockcustTelsaleListDao;
import com.huaxia.opas.dao.decision.SysDecisionYdjDao;
import com.huaxia.opas.dao.decision.TargetcompanyListDao;
import com.huaxia.opas.dao.decision.TeamdealListDao;
import com.huaxia.opas.dao.decision.TeamlistDao;
import com.huaxia.opas.dao.decision.TelcheckAddnoteDao;
import com.huaxia.opas.dao.report.KeyMessageModifyDao;
import com.huaxia.opas.dao.sysparm.MajorschoolListDao;
import com.huaxia.opas.dao.sysparm.PreCreditParamDao;
import com.huaxia.opas.dao.thirdparty.AntDao;
import com.huaxia.opas.dao.thirdparty.BankDao;
import com.huaxia.opas.dao.thirdparty.PBOCDao;
import com.huaxia.opas.dao.thirdparty.PoliceDao;
import com.huaxia.opas.dao.thirdparty.TxOperatorDao;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.apply.ApplyModifyLog;
import com.huaxia.opas.domain.apply.ApplyRemark;
import com.huaxia.opas.domain.compare.RevCompInfo;
import com.huaxia.opas.domain.decision.CreditTelcheckList;
import com.huaxia.opas.domain.decision.MerchTeamdealList;
import com.huaxia.opas.domain.decision.OpaCheckinerResultInfo;
import com.huaxia.opas.domain.decision.OpaInnerDataCheck;
import com.huaxia.opas.domain.decision.OpaRiskInfoCheck;
import com.huaxia.opas.domain.decision.OpasCustBaseInfo;
import com.huaxia.opas.domain.decision.OpasHaveCardCustInfo;
import com.huaxia.opas.domain.decision.TeamdealList;
import com.huaxia.opas.domain.decision.Teamlist;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.decision.YdjSysresultInfo;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.TelcheckResult;
import com.huaxia.opas.domain.riskcheck.KeyfiledMarchinfo;
import com.huaxia.opas.domain.riskcheck.RiskCheck;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.domain.rule.Opasbizzmivsdata;
import com.huaxia.opas.domain.sysparm.GoodcompanyList;
import com.huaxia.opas.domain.sysparm.MajorschoolList;
import com.huaxia.opas.domain.sysparm.SpecialprojectList;
import com.huaxia.opas.domain.sysparm.TargetcompanyList;
import com.huaxia.opas.domain.thirdparty.StockcustTelsaleList;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.decision.SysDecisionYdjService;
import com.huaxia.opas.service.report.KeyMessageModifyService;
import com.huaxia.opas.util.CommonUtil;

import net.sf.json.JSONArray;

/**
 * @author liuzhihui
 */
public class SysDecisionYdjServiceImpl extends AbstractService implements
		SysDecisionYdjService,Serializable {

	private static final long serialVersionUID = -8008508483239828116L;

	private static org.slf4j.Logger logger = LoggerFactory
			.getLogger(SysDecisionYdjServiceImpl.class);
	
	@Resource(name = "allotApplyAllotDao")
	private AllotApplyAllotDao allotApplyAllotDao;
	
	@Resource(name = "sysDecisionYdjDao")
	private SysDecisionYdjDao sysDecisionYdjDao;
	
	@Resource(name = "teamlistDao")
	private TeamlistDao teamlistDao;
	
	@Resource(name = "teamdealListDao")
	private TeamdealListDao teamdealListDao;

	@Resource(name = "keyfiledMarchinfoDao")
	private KeyfiledMarchinfoDao keyfiledMarchinfoDao;

	@Resource(name = "targetcompanyListDao")
	private TargetcompanyListDao targetcompanyListDao;

	@Resource(name = "majorschoolListDao")
	private MajorschoolListDao majorschoolListDao;

	@Resource(name = "goodcompanyListDao")
	private GoodcompanyListDao goodcompanyListDao;

	@Resource(name = "merchTeamdealListDao")
	private MerchTeamdealListDao merchTeamdealListDao;

	@Resource(name = "specialprojectListDao")
	private SpecialprojectListDao specialprojectListDao;

	@Resource(name = "bizInpApplC1Dao")
	private BizInpApplC1Dao bizInpApplC1Dao;

	@Resource(name = "pbocDao")
	private PBOCDao pbocDao;
	
	@Resource(name = "bankDao")
	private BankDao bankDao;
	@Resource(name = "telcheckAddnoteDao")
	private TelcheckAddnoteDao telcheckAddnoteDao;

	@Resource(name = "creditTelcheckListDao")
	private CreditTelcheckListDao creditTelcheckListDao;

	@Resource(name = "applyModifyLogDao")
	private ApplyModifyLogDao applyModifyLogDao;
	@Resource(name = "preCreditParamDao")
	private PreCreditParamDao preCreditParamDao;
	@Resource(name = "stockcustTelsaleListDao")
	private StockcustTelsaleListDao stockcustTelsaleListDao;
	@Resource(name = "ficoSdBlazeDao")
	private FicoSdBlazeDao ficoSdBlazeDao;
	@Resource(name = "ficoYdjBlazeDao")
	private FicoYdjBlazeDao ficoYdjBlazeDao;
	
	@Resource(name = "applyInfoDao")
	private ApplyInfoDao applyInfoDao;

	//公安
	@Resource(name = "policeDao")
	private PoliceDao policeDao;
	@Resource(name = "opasConfQuestionDao")
	private OpasConfQuestionDao opasConfQuestionDao;
	
	//可信
	@Resource(name = "keyMessageModifyDao")
	private KeyMessageModifyDao keyMessageModifyDao;
	//审查库
	@Resource(name = "revCompInfoDao")
	private RevCompInfoDao revCompInfoDao;
	
	//蚂蚁
	@Resource(name = "antDao")
	private AntDao antDao;
	@Resource(name = "txOperatorDao")
	private TxOperatorDao txOperatorDao;
	/**
	 * 查询团队是否存在
	 * 
	 * @author xuzhiguo
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> queryTeam(String appId) throws Exception {
		// 组装数据
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", appId);
		// map.put("marchNode", marchNode);
		// 根据appId获得数据源匹配信息
		KeyfiledMarchinfo info = keyfiledMarchinfoDao.selectByAppId(map);// 名单库匹配
		if (info == null) {
			logger.info("没有匹配的数据信息");
			return null;
		}
		// 获取Json
		String marchResultJson = info.getMarchResult();
		logger.info("Json=" + marchResultJson);
		// 解析Json串
		JSONArray jarray = JSONArray.fromObject(marchResultJson);
		List<RiskCheck> listriskcheck = (List<RiskCheck>) JSONArray
				.toCollection(jarray, RiskCheck.class);
		Map<String, String> jsonMap = new HashMap<String, String>();
		for (RiskCheck riskCheck : listriskcheck) {
			if ("1".equals(riskCheck.getRiskResult())) {
				// 拼接map中的key规则为表明加字段名
				String key = riskCheck.getTableName() + "-"
						+ riskCheck.getFieldKey();
				jsonMap.put(key, riskCheck.getPriKeyValue());
			}
		}
		logger.info("Json解析完成");
		return jsonMap;
	}

	/**
	 * 根据申请编号查询系统决策所需要的团办、重点院校、目标企业等信息分页
	 * 
	 * @param appId
	 * @return
	 * @author hang
	 * @throws Exception 
	 */
	public int queryCountList(String appId, String flag,Map paramMap,String paramFlag) throws Exception {
		Map<String, String> jsonMap = queryTeam(appId)==null?new HashMap():queryTeam(appId);
		int count = 0;
		if ("queryTargetcompany_tab_view".equals(flag)) {// 目标企业名单
			if("1".equals(paramFlag)){
				String autoid = jsonMap.get("OPAS_TARGETCOMPANY_LIST-COMPANY_NAME");
				if (autoid == null) {
					logger.info("不存在匹配的目标企业信息");
					return count;
				}
				TargetcompanyList target = targetcompanyListDao.selectByPrimaryKey(autoid);
				if(target!=null&&"1".equals(target.getCurrStatus()))
					count = 1;
			}else{
				count = targetcompanyListDao.queryCountList(paramMap);
			}
		} else if ("queryTeamList_tab_view".equals(flag)) {// 团办号是否匹配
			if("1".equals(paramFlag)){
				String autoid = jsonMap.get("OPAS_TEAMDEAL_LIST-TEAMDEAL_CODE");
				if (autoid == null) {
					logger.info("不存在匹配的易达金团办名单");
					return count;
				}
				TeamdealList teamdeal = teamdealListDao.selectByPrimaryKey(autoid);
				if(teamdeal!=null&&"1".equals(teamdeal.getCurrStatus()))
					count = 1;
			}else{
				count = teamdealListDao.queryCountList(paramMap);
			}
		} else if ("queryMajorschool_tab_view".equals(flag)) {// 重点院校名单
			if("1".equals(paramFlag)){
				String autoid = jsonMap
						.get("OPAS_MAJORSCHOOL_LIST-MAJORSCHOOL_NAME");
				if (autoid == null) {
					logger.info("不存在匹配的重点院校名单");
					return count ;
				}
				MajorschoolList majorschool = majorschoolListDao.selectByPrimaryKey(autoid);
				if(majorschool!=null&&"1".equals(majorschool.getCurrStatus()))
					count = 1;
			}else{
				count = majorschoolListDao.queryCountList(paramMap);
			}
		} else if ("queryGoodCompany_tab_view".equals(flag)) {// 优质企业名单
			if("1".equals(paramFlag)){
				String autoid1 = jsonMap.get("OPAS_GOODCOMPANY_LIST-PROJECT_CODE");
				String autoid2 = jsonMap.get("OPAS_GOODCOMPANY_LIST-COMPANY_NAME");
				String autoid = autoid1 != null ? autoid1 : autoid2;
				if (autoid == null) {
					logger.info("不存在匹配的优质企业名单");
					return count;
				}
				GoodcompanyList goodcompany = goodcompanyListDao.selectByPrimaryKey(autoid);
				if(goodcompany!=null&&"1".equals(goodcompany.getCurrStatus()))
					count = 1;
			}else{
				count = goodcompanyListDao.queryCountList(paramMap);
			}
		} else if ("queryMerchTeamdeal_tab_view".equals(flag)) {// 商户团办名单
			if("1".equals(paramFlag)){
				String autoid = jsonMap.get("OPAS_MERCH_TEAMDEAL_LIST-TEAMDEAL_NO");
				if (autoid == null) {
					logger.info("不存在匹配的目商户团办名单");
					return count;
				}
				MerchTeamdealList merchteam = merchTeamdealListDao.selectByPrimaryKey(autoid);
				if(merchteam!=null&&"1".equals(merchteam.getCurrStatus()))
					count = 1;
			}else{
				count = merchTeamdealListDao.queryCountList(paramMap);
			}
		} else if ("querySpecialProject_tab_view".equals(flag)) {// 特殊项目名单
			if("1".equals(paramFlag)){
				String autoid = jsonMap
						.get("OPAS_SPECIALPROJECT_LIST-PROJECT_CODE");
				if (autoid == null) {
					logger.info("不存在匹配的特殊项目名单");
					return count;
				}
				SpecialprojectList specialproject = specialprojectListDao.selectByPrimaryKey(autoid);
				if(specialproject!=null&&"1".equals(specialproject.getCurrStatus()))
					count = 1;
			}else{
				count = specialprojectListDao.queryCountList(paramMap);
			}
		}else if("queryBzkTeamList_tab_view".equals(flag)){
			count = 1;
		}else if("queryPreCreditParam_tab_view".equals(flag)){
			count = preCreditParamDao.queryPreCreditParamListByIdnoCount(appId);
		}
		return count;
	}
	/**
	 * 根据申请编号查询系统决策所需要的团办、重点院校、目标企业等信息
	 * 
	 * @param appId
	 * @return
	 * @author xuzhiguo
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryByAppId(String appId, String flag,Map paramMap,String paramFlag,int curNum,int pageNum) throws Exception {
		// 获得团办、重点院校、目标企业等Json
		Map<String, String> jsonMap = queryTeam(appId)==null?new HashMap():queryTeam(appId);
		// 根据json解析后的数据获取易达金团办名单表的主键后，获取团办名单list
		List list = new ArrayList();
		if ("queryTargetcompany_tab_view".equals(flag)) {// 目标企业名单
			if("1".equals(paramFlag)){
				String autoid = jsonMap.get("OPAS_TARGETCOMPANY_LIST-COMPANY_NAME");
				if (autoid == null) {
					logger.info("不存在匹配的目标企业信息");
					return null;
				}
				TargetcompanyList target = targetcompanyListDao.selectByPrimaryKey(autoid);
				list.add(target);
			}else{
				list = targetcompanyListDao.selectByCondtion(paramMap, curNum, pageNum);
			}
		} else if ("queryTeamList_tab_view".equals(flag)) {// 团办号是否匹配
			if("1".equals(paramFlag)){
				String autoid = jsonMap.get("OPAS_TEAMDEAL_LIST-TEAMDEAL_CODE");
				if (autoid == null) {
					logger.info("不存在匹配的易达金团办名单");
					return null;
				}
				TeamdealList teamdeal = teamdealListDao.selectByPrimaryKey(autoid);
				list.add(teamdeal);
			}else{
				list = teamdealListDao.selectByCondtion(paramMap, curNum, pageNum);
			}
		} else if ("queryMajorschool_tab_view".equals(flag)) {// 重点院校名单
			if("1".equals(paramFlag)){
				String autoid = jsonMap
						.get("OPAS_MAJORSCHOOL_LIST-MAJORSCHOOL_NAME");
				if (autoid == null) {
					logger.info("不存在匹配的重点院校名单");
					return null;
				}
				MajorschoolList majorschool = majorschoolListDao.selectByPrimaryKey(autoid);
				list.add(majorschool);
			}else{
				list = majorschoolListDao.selectByCondtion(paramMap, curNum, pageNum);
			}
		} else if ("queryGoodCompany_tab_view".equals(flag)) {// 优质企业名单
			if("1".equals(paramFlag)){
				String autoid1 = jsonMap.get("OPAS_GOODCOMPANY_LIST-PROJECT_CODE");
				String autoid2 = jsonMap.get("OPAS_GOODCOMPANY_LIST-COMPANY_NAME");
				String autoid = autoid1 != null ? autoid1 : autoid2;
				if (autoid == null) {
					logger.info("不存在匹配的优质企业名单");
					return null;
				}
				GoodcompanyList goodcompany = goodcompanyListDao.selectByPrimaryKey(autoid);
				list.add(goodcompany);
			}else{
				list = goodcompanyListDao.selectByCondtion(paramMap, curNum, pageNum);
			}
		} else if ("queryMerchTeamdeal_tab_view".equals(flag)) {// 商户团办名单
			if("1".equals(paramFlag)){
				String autoid = jsonMap.get("OPAS_MERCH_TEAMDEAL_LIST-TEAMDEAL_NO");
				if (autoid == null) {
					logger.info("不存在匹配的目商户团办名单");
					return null;
				}
				MerchTeamdealList merchteam = merchTeamdealListDao.selectByPrimaryKey(autoid);
				list.add(merchteam);
			}else{
				list = merchTeamdealListDao.selectByCondtion(paramMap, curNum, pageNum);
			}
		} else if ("querySpecialProject_tab_view".equals(flag)) {// 特殊项目名单
			if("1".equals(paramFlag)){
				String autoid = jsonMap
						.get("OPAS_SPECIALPROJECT_LIST-PROJECT_CODE");
				if (autoid == null) {
					logger.info("不存在匹配的特殊项目名单");
					return null;
				}
				SpecialprojectList specialproject = specialprojectListDao.selectByPrimaryKey(autoid);
				if (specialproject == null)
					return null;
				list.add(specialproject);
			}else{
				list = specialprojectListDao.selectByCondtion(paramMap, curNum, pageNum);
			}
		}else if("queryBzkTeamList_tab_view".equals(flag)){//标准卡团办名单库
			String autoid = jsonMap.get("OPAS_TEAMLIST-TEAM_ID");
			Teamlist teamlist = teamlistDao.selectByPrimaryKey(autoid);
			if (teamlist == null)
				return null;
			list.add(teamlist);
		}else if("queryPreCreditParam_tab_view".equals(flag)){
			list = preCreditParamDao.queryPreCreditParamListByIdno(appId,curNum, pageNum);
		}
		return list;
	}
	/**
	 * 查询目标企业
	 * @author hang
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TargetcompanyList queryByAppId(String appId) throws Exception{
		
			Map<String, String> jsonMap = queryTeam(appId)==null?new HashMap():queryTeam(appId);
			String autoid = jsonMap.get("OPAS_TARGETCOMPANY_LIST-COMPANY_NAME");
			if (autoid == null) {
				logger.info("不存在匹配的目标企业信息");
				return null;
			}
			TargetcompanyList target = targetcompanyListDao.selectByPrimaryKey(autoid);
			return target;
	}
	/**
	 * 查询目标企业
	 * @author hang
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TeamdealList queryTeamdealByAppId(String appId) throws Exception{
		
			Map<String, String> jsonMap = queryTeam(appId)==null?new HashMap():queryTeam(appId);
			String autoid = jsonMap.get("OPAS_TARGETCOMPANY_LIST-COMPANY_NAME");
			if (autoid == null) {
				logger.info("不存在匹配的目标企业信息");
				return null;
			}
			TeamdealList target = teamdealListDao.selectByPrimaryKey(autoid);;
			return target;
	}
	/**
	 * 根据申请编号AppId获得主卡进件信息
	 * 
	 * @throws CoreException
	 */
	public BizInpApplC1 selectBizInpApplC1ByAppId(String appId)
			throws CoreException {
		return bizInpApplC1Dao.selectBizInpApplC1ByAppId(appId);
	}

	/**
	 * 修改主卡进件信息
	 */
	public void updateBizInpApplC1ByKey(BizInpApplC1 bizInpApplC1,
			List<ApplyModifyLog> logList, ApplyRemark applyRemark)
			throws CoreException {
		// 修改主卡进件信息
		bizInpApplC1Dao.updateBizInfo(bizInpApplC1);
		// 插入流水日志
		for (ApplyModifyLog applyModifyLog : logList) {
			applyModifyLogDao.insertSelective(applyModifyLog);
		}
	}

	/**
	 * 人行摘要信息
	 */
	public Map<String, String> queryPbocInfo(String appId) {
		return pbocDao.queryPbocInfo(appId);
	}

	/**
	 * 二代人行摘要信息
	 */
	public Map<String, String> queryBankInfo(String appId) {
		return bankDao.queryBankInfo(appId);
	}
	
	public OpasCustBaseInfo selectCustInfoByAppId(String appId) {
		return sysDecisionYdjDao.selectCustInfoByAppId(appId);
	}

	public YdjSysresultInfo selectSysResultInfoByAppId(String appId) {
		return sysDecisionYdjDao.selectSysResultfoByAppId(appId);
	}

	/**
	 * 内部数据检测
	 * 
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public OpaInnerDataCheck selectInnerDataByAppId(String appId)
			throws Exception {
		OpaInnerDataCheck inner = new OpaInnerDataCheck();
		// 获得匹配结果
		Map<String, String> jsonMap = queryTeam(appId);
		if (jsonMap == null) {
			jsonMap = new HashMap<String, String>();
		}
		// 重复申请检查
		String repeatCheck = jsonMap.get("OPAS_BIZ_INP_APPL_C1-C1_IDNBR");
		if (repeatCheck == null) {
			inner.setRepeatCheck("0");
		} else {
			inner.setRepeatCheck(repeatCheck);
		}
		// 灰名单（个人）
		String graylist = jsonMap.get("OPAS_FILE_APPLICATION_DETAIL-CRED_NO");
		if (graylist == null) {
			inner.setGraylist("0");
		} else {
			inner.setGraylist(graylist);
		}
		// 证件号码有效期
		String certifinoVailud = jsonMap.get("OPAS_BIZ_INP_APPL_C1-C5_IDTE1");
		if (certifinoVailud == null) {
			inner.setCertifinoVailud("5");
		} else {
			inner.setCertifinoVailud(certifinoVailud);
		}
		// 关联性检查-手机
		String relatedcheckTel = jsonMap.get("OPAS_BIZ_INP_APPL_C1-C1_MOBILE");
		if (relatedcheckTel == null) {
			inner.setRelatedcheckTel("0");
		} else {
			inner.setRelatedcheckTel(relatedcheckTel);
		}
		// 关联性检查-家庭地址
		String relatedcheckHomeaddr = jsonMap.get("OPAS_BIZ_INP_APPL_C1-HMADD");
		if (relatedcheckHomeaddr == null) {
			inner.setRelatedcheckHomeaddr("0");
		} else {
			inner.setRelatedcheckHomeaddr(relatedcheckHomeaddr);
		}
		// 关联性检查-非公司的邮寄地址
		String relatedcheckCompanyaddr = jsonMap.get("OPAS_BIZ_INP_APPL_C1-CYCADD");
		if (relatedcheckCompanyaddr == null) {
			inner.setRelatedcheckCompanyaddr("0");
		} else {
			inner.setRelatedcheckCompanyaddr(relatedcheckCompanyaddr);
		}
		// 关联性检查-直系亲属手机号码
		String relatedcheckNearlyTelno = jsonMap.get("OPAS_BIZ_INP_APPL_C1-XMOBIL");
		if (relatedcheckNearlyTelno == null) {
			inner.setRelatedcheckNearlyTelno("0");
		} else {
			inner.setRelatedcheckNearlyTelno(relatedcheckNearlyTelno);
		}
		// 不安全推广人员
		String nosafePromoter = jsonMap.get("OPAS_PROMOTER_RISKLIST-PROMOTER_NO"); 
		if(nosafePromoter==null){
			inner.setNosafePromoter("0"); 
		}else{
			inner.setNosafePromoter(nosafePromoter); 
		} 
	
		// 申请信息逻辑检查
		String applyinfoBuscheck = jsonMap.get("OPAS_BIZ_INP_APPL_C1-LOGICAL");
		if (applyinfoBuscheck == null) {
			inner.setApplyinfoBuscheck("0");
		} else {
			inner.setApplyinfoBuscheck(applyinfoBuscheck);
		}
		// 是否命中内部风险信息
		String autoid11 = jsonMap.get("OPAS_INNER_RISKINFO_LIST-COMPANY_NAME");
		String autoid22 = jsonMap.get("OPAS_INNER_RISKINFO_LIST-COMPANY_TEL");
		String autoid33 = jsonMap.get("OPAS_INNER_RISKINFO_LIST-SPREAD_ORG");
		String autoid44 = jsonMap.get("OPAS_INNER_RISKINFO_LIST-SPREAD_PER");
		String autoid55 = jsonMap.get("OPAS_INNER_RISKINFO_LIST-SPREAD_NO");
		/*Map<String,Object> ishaveInriskInfoMap = CommonUtil.geNotNullMap(autoid11, autoid22,
				autoid33, autoid44,autoid55);*/
		String ishaveInriskInfo = null;
		String num = "";
		if(autoid11!=null && !"".equals(autoid11)){
			ishaveInriskInfo = autoid11;
			num = num + "1";
		}
		if(autoid22!=null && !"".equals(autoid22)){
			ishaveInriskInfo = autoid22;
			num = num + "2";
		}
		if(autoid33!=null && !"".equals(autoid33)){
			ishaveInriskInfo = autoid33;
			num = num + "3";
		}
		if(autoid44!=null && !"".equals(autoid44)){
			ishaveInriskInfo = autoid44;
			num = num + "4";
		}
		if(autoid55!=null && !"".equals(autoid55)){
			ishaveInriskInfo = autoid55;
			num = num + "5";
		}
		/*if(ishaveInriskInfoMap != null){
			ishaveInriskInfo = (String) ishaveInriskInfoMap.get("res");
			num = (int) ishaveInriskInfoMap.get("num");
		}*/
		//String ishaveInriskInfo = jsonMap.get("OPAS_INNER_RISKINFO_LIST-InnerRiskInfo");
		if (ishaveInriskInfo == null) {
			inner.setIshaveInriskInfo("0");
		} else {
			inner.setIshaveInriskInfo(ishaveInriskInfo);
			inner.setIshaveInriskInfo_column(num);
		}
		return inner;
	}

	/**
	 * 风险信息检查
	 * 
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	@Override
	public OpaRiskInfoCheck selectRiskInfoByAppId(String appId,String certifiNo)
			throws Exception {
		OpaRiskInfoCheck opaRiskInfoCheck = new OpaRiskInfoCheck();
		// 获得匹配结果
		Map<String, String> jsonMap = queryTeam(appId);
		if (jsonMap == null) {
			jsonMap = new HashMap<String, String>();
		}
		// 身份类风险名单库
		String APPLY_CERTIFI_NO = jsonMap
				.get("OPAS_IDENTITY_RISKLIST-APPLY_CERTIFI_NO");
		if (APPLY_CERTIFI_NO == null) {
			opaRiskInfoCheck.setIdentityRisklist("0");
		} else {
			opaRiskInfoCheck.setIdentityRisklist(APPLY_CERTIFI_NO);
		}
		//单位类风险名单库
		String companyRisklist = jsonMap.get("OPAS_COMPANY_RISKLIST-APPLY_COMPANY_NAME");
		if(companyRisklist == null){
			opaRiskInfoCheck.setCompanyRisklist("0");
		}else{
			opaRiskInfoCheck.setCompanyRisklist(companyRisklist);
		}
		// 电话类风险名单库
		String APPLY_MOBILE_ID = jsonMap.get("OPAS_TEL_RISKLIST-APPLY_MOBILE_ID");
		String APPLY_COMPANY_TEL = jsonMap.get("OPAS_TEL_RISKLIST-APPLY_COMPANY_TEL");
		String APPLY_LIVING_TEL = jsonMap.get("OPAS_TEL_RISKLIST-APPLY_LIVING_TEL");
		String APPLY_OTHER_TEL = jsonMap.get("OPAS_TEL_RISKLIST-APPLY_OTHER_TEL");
		String APPLY_MOBILE_ID_L = jsonMap.get("OPAS_TEL_RISKLIST-APPLY_MOBILE_ID_L");
		String APPLY_COMPANY_TEL_L = jsonMap.get("OPAS_TEL_RISKLIST-APPLY_COMPANY_TEL_L");
		String APPLY_LIVING_TEL_L = jsonMap.get("OPAS_TEL_RISKLIST-APPLY_LIVING_TEL_L");
		String APPLY_OTHER_TEL_L = jsonMap.get("OPAS_TEL_RISKLIST-APPLY_OTHER_TEL_L");
		String tel = CommonUtil.geNotNullString(APPLY_MOBILE_ID, APPLY_COMPANY_TEL,APPLY_LIVING_TEL,
				APPLY_COMPANY_TEL, APPLY_OTHER_TEL, APPLY_MOBILE_ID_L,
				APPLY_COMPANY_TEL_L, APPLY_LIVING_TEL_L, APPLY_OTHER_TEL_L);
		if (tel == null) {
			opaRiskInfoCheck.setTelRisklist("0");
		} else {
			opaRiskInfoCheck.setTelRisklist(tel);
		}
		// 地址类风险名单库
		String APPLY_COMPANY_ADDR = jsonMap
				.get("OPAS_ADDR_RISKLIST-APPLY_COMPANY_ADDR");
		String APPLY_ALL_ADDR_LIVING_ADDR = jsonMap
				.get("OPAS_ADDR_RISKLIST-APPLY_ALL_ADDR_LIVING_ADDR");
		String addr = CommonUtil.geNotNullString(APPLY_COMPANY_ADDR,
				APPLY_ALL_ADDR_LIVING_ADDR);
		if (addr == null) {
			opaRiskInfoCheck.setAddrRisklist("0");
		} else {
			opaRiskInfoCheck.setAddrRisklist(addr);
		}
		// 推广员风险名单库
		String APPLY_CERTIFI_NO_tg = jsonMap.get("OPAS_PROMOTER_RISKLIST-APPLY_CERTIFI_NO");
		/*String PROMOTER_NO = jsonMap.get("OPAS_PROMOTER_RISKLIST-PROMOTER_NO");
		String person = CommonUtil.geNotNullString(APPLY_CERTIFI_NO_tg,PROMOTER_NO);*/
		if (APPLY_CERTIFI_NO_tg == null) {
			opaRiskInfoCheck.setPromoterRisklist("0");
		} else {
			opaRiskInfoCheck.setPromoterRisklist(APPLY_CERTIFI_NO_tg);
		}
		// 同业关注风险名单库
		String APPLY_CERTIFI_NO_ty = jsonMap
				.get("OPAS_SAMEINDUSTRY_RISKLIST-APPLY_CERTIFI_NO");
		if (APPLY_CERTIFI_NO_ty == null) {
			opaRiskInfoCheck.setSameRisklist("0");
		} else {
			opaRiskInfoCheck.setSameRisklist(APPLY_CERTIFI_NO_ty);
		}
		// 蚂蚁风险名单
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("appId", appId);
		List<Map<String, Object>> mylist = antDao.selectAntRiskOrder(paramMap);
		if(mylist==null || mylist.size() == 0){
			opaRiskInfoCheck.setAntRisklist("0");
		}else{
			opaRiskInfoCheck.setAntRisklist("1");
		}
		// 百融风险名单
		List<Map<String, Object>> brlist1 = antDao.selectBrRistOrder1(paramMap);
		List<Map<String, Object>> brlist2 = antDao.selectBrRistOrder2(paramMap);
		if((brlist1==null || brlist1.size() == 0) && (brlist2==null || brlist2.size() == 0)){
			opaRiskInfoCheck.setBairongRisklist("0");
		}else{
			opaRiskInfoCheck.setBairongRisklist("1");
		}
		// 洗钱风险名单
		String AML = jsonMap.get("FOREIGN_INDEX-AML");
		if(AML==null || "".equals(AML)){
			opaRiskInfoCheck.setWashmoneyRisklist("0");
		}else{
			opaRiskInfoCheck.setWashmoneyRisklist("1");
		}
		// 客户洗钱风险等级
		Map<String,String> paramMap2 = new HashMap<String,String>();
		paramMap2.put("certNo", certifiNo);
		String custwashmoneyRisklevel = antDao.selectCustRiskLevel(paramMap2);
		if(custwashmoneyRisklevel==null ||"".equals(custwashmoneyRisklevel)){
			opaRiskInfoCheck.setCustwashmoneyRisklevel("-1");
		}else{
			opaRiskInfoCheck.setCustwashmoneyRisklevel(custwashmoneyRisklevel);
		}
		return opaRiskInfoCheck;
	}

	public OpaCheckinerResultInfo selectChecKinerInfoByAppId(String appId) {
		return sysDecisionYdjDao.selectChecKinerInfoByAppId(appId);
	}
	@Override
	public OpaCheckinerResultInfo selectChecKinerInfoByAppId_15(String appId) {
		return sysDecisionYdjDao.selectChecKinerInfoByAppId_15(appId);
	}
	public OpasHaveCardCustInfo selectHaveCardInfoByAppId(String appId) {
		return sysDecisionYdjDao.selectHaveCardInfoByAppId(appId);
	}

	@Override
	public void saveTelcheckAddnote(TelcheckAddnote telcheckAddnote)
			throws CoreException {
		// 查询该业务编号的数据是否已经存在
		/*TelcheckAddnote note = telcheckAddnoteDao.selectByAppId(telcheckAddnote
				.getAppId());
		if (note == null) {
			telcheckAddnoteDao.insertSelective(telcheckAddnote);
			logger.info("该业务的征信注纪【添加】成功");
		} else {
			telcheckAddnote.setAutoId(note.getAutoId());
			telcheckAddnoteDao.updateByPrimaryKeySelective(telcheckAddnote);
			logger.info("该业务的征信注纪【修改】成功");
		}*/
	}

	public void deleteTelcheckAddnote(String appId) throws CoreException {
		// TelcheckAddnote note = telcheckAddnoteDao.selectByAppId(appId);
		//telcheckAddnoteDao.deleteByAppId(appId);
	}

	public List<CreditTelcheckList> queryCreditTelcheckList(
			Map<String, Object> map, int curNum, int pageNum) {
		return creditTelcheckListDao.queryCreditTelcheckList(map, curNum,
				pageNum);
	}

	public int queryCreditTelcheckListCount(Map<String, Object> map) {
		return creditTelcheckListDao.queryCreditTelcheckListCount(map);
	}

	public void insertCreditTelcheckList(CreditTelcheckList creditTelcheckList) {
		creditTelcheckListDao.insertSelective(creditTelcheckList);
	}
	@Override
	public String queryRefuseCodeList(Map map) throws Exception {
		return bizInpApplC1Dao.queryRefuseCodeList(map);
	}
	@Override
	public List queryInDataChecK(Map map,int curNum,int pageNum) throws Exception {
		return bizInpApplC1Dao.queryInDataChecK(map,curNum,pageNum);
	}
	@Override
	public List queryInDataChecK(Map map) throws Exception {
		return bizInpApplC1Dao.queryInDataChecK(map);
	}
	@Override
	public int countInDataChecK(Map map) throws Exception {
		return bizInpApplC1Dao.countInDataChecK(map);
	}
	public Map queryInDataChecK(String appId) throws Exception {
		return bizInpApplC1Dao.queryInDataChecK(appId);
	}

	@Override
	public StockcustTelsaleList queryStockcustTelsaleList(String autoId)
			throws Exception {
		return stockcustTelsaleListDao.selectByPrimaryKey(autoId);
	}
	
	public OpasCustBaseInfo selectCustAndPreSellimitByAppId(String appId) {
		OpasCustBaseInfo opasCustBaseInfo = sysDecisionYdjDao.selectCustAndPreSellimitByAppId(appId);
		/*if("0058".equals(opasCustBaseInfo.getApplyCard()) && "1".equals(opasCustBaseInfo.getEpayMatch())){
			opasCustBaseInfo.setMainCardAppId(sysDecisionYdjDao.selectMainCardAppIdByAppId(appId));
		}*/
		return opasCustBaseInfo;
	}


	@Override
	public Map<String,String> selectCustInfoByCustId(Opasbizinpapplc1 on) {
		return sysDecisionYdjDao.selectCustInfoByCustId(on);
	}

	@Override
	public Map<String, Object> findZxCreditInvestigationYdjByAppId(Map paramMap) {
		Map<String, Object> htmlMap=new HashMap<String, Object>();
		try {	
		//获取申请编号
		String appId = paramMap.get("appId").toString();
		//查征信电核结果信息表 用于反显
		TelcheckResult telcheckResult = telcheckAddnoteDao.selectResultById(appId);
		//String resultJson = JSON.toJSONString(telcheckResult);
		htmlMap.put("result", telcheckResult);
		//易达金系统决策表  取征信策略结果描述
		String ydjresult = opasConfQuestionDao.selectCreditDecisionDescByAppIdYDJ(appId);
		htmlMap.put("ydjresult", ydjresult);
		
		Map<String,String> yanzhenMesShowMap = new HashMap<String,String>();//前台验证信息展示用的 map
		Map<String,String> yanzhenResMap = new HashMap<String,String>();//1 是 == 0 否
		//pbocFlag作为一个标识，如果为true则用三方比对的结果，
		boolean pbocFlag = true;
		//人行比对信息在结果信息表中查
		if (telcheckResult != null) {
			
			String pbocPhoneFlag = telcheckResult.getPbocPhoneFlag(); //住宅电话
			String pbocMobileFlag = telcheckResult.getPbocMobileFlag();//手机号码
			String pbocHomeaddrFlag = telcheckResult.getPbocHomeaddrFlag();//账单地址
			String pbocCompanyFlag = telcheckResult.getPbocCompanyFlag(); //单位名称
			String pbocBusphoneFlag = telcheckResult.getPbocBusphoneFlag();//单位电话
			String pbocBusaddrFlag = telcheckResult.getPbocBusaddrFlag();//单位地址
			
			if (pbocPhoneFlag!=null||pbocMobileFlag!=null||pbocHomeaddrFlag!=null
						||pbocCompanyFlag!=null||pbocBusphoneFlag!=null||pbocBusaddrFlag!=null){
				pbocFlag = false;//
				yanzhenResMap.put("pbocMobileFlag", pbocMobileFlag);//--手机号码
				if("1".equals(pbocMobileFlag)){
					yanzhenMesShowMap.put("pbocMobileFlag", "手机号码   人行   是");
				}
				yanzhenResMap.put("pbocBusphoneFlag", pbocBusphoneFlag);//--单位电话  
				if("1".equals(pbocBusphoneFlag)){
					yanzhenMesShowMap.put("pbocBusphoneFlag", "单位电话   人行    是");
				}
				yanzhenResMap.put("pbocCompanyFlag", pbocCompanyFlag);//--单位名称
				if("1".equals(pbocCompanyFlag)){
					yanzhenMesShowMap.put("pbocCompanyFlag", "单位名称    人行    是");
				}
				yanzhenResMap.put("pbocPhoneFlag", pbocPhoneFlag);//--住宅电话  
				if("1".equals(pbocPhoneFlag)){
					yanzhenMesShowMap.put("pbocPhoneFlag", "住宅电话      人行    是");
				}
				yanzhenResMap.put("pbocBusaddrFlag", pbocBusaddrFlag);//--单位地址
				if("1".equals(pbocBusaddrFlag)){
					yanzhenMesShowMap.put("pbocBusaddrFlag", "单位地址     人行    是");
				}
				yanzhenResMap.put("pbocHomeaddrFlag", pbocHomeaddrFlag);//--(住宅地址)
				if("1".equals(pbocHomeaddrFlag)){
					yanzhenMesShowMap.put("pbocHomeaddrFlag", "账单地址    人行   是");
				}
				htmlMap.put("yanzhenResMap", yanzhenResMap);
				htmlMap.put("yanzhenMesShowMap", yanzhenMesShowMap);
			}
		} else {
			String  oneOrZero="0";
			//人行比对结果
			yanzhenResMap.put("pbocMobileFlag",oneOrZero);//--手机号码
			yanzhenResMap.put("pbocBusphoneFlag", oneOrZero);//--单位电话  
			yanzhenResMap.put("pbocCompanyFlag",oneOrZero);//--单位名称
			yanzhenResMap.put("pbocPhoneFlag",oneOrZero);//--住宅电话  
			yanzhenResMap.put("pbocBusaddrFlag",oneOrZero);//--单位地址
			yanzhenResMap.put("pbocHomeaddrFlag",oneOrZero);//--住宅地址
			htmlMap.put("yanzhenResMap", yanzhenResMap);
			htmlMap.put("yanzhenMesShowMap", yanzhenMesShowMap);
			
		}
		
		//查询主卡进件信息
		BizInpApplC1 appl = bizInpApplC1Dao.selectBizInpApplC1ByAppId(appId);
		String c4Cycadd1=appl.getC4Cycadd1();//账单地址 （账单邮寄地址一）
		Map<String, String> policeHtmlMap=new HashMap<String, String>();//公安的 性别 和籍贯信息  征信调查 易达金页面需要 
		//String idCard=appl.getC1Idnbr();//易达金 征信调查页面   通过  身份证号 查公安的 性别 和籍贯信息 
		//String c1Name=appl.getC1Cname();//易达金 征信调查页面   通过  主卡姓名 查公安的 性别 和籍贯信息 
		//	policeHtmlMap.put("policeGender",appl.getC1Gender());//性别  c1Gender  policeMapData.get("gender")
		policeHtmlMap.put("policeGender","");//性别
		policeHtmlMap.put("policeJgssxhomeProvCity","");//籍贯
		/*if(idCard!=null&&!idCard.equals("")){
		//	Map<String, String> policeMap=new HashMap<String, String>();
			//policeMap.put("identityNo", idCard);//身份证号 
			//policeMap.put("name", c1Name);//姓名
		//	Map<String, String>policeMapData=policeDao.selectSummaryInfo(appId);//selectDetailInfo(policeMap);
		}else{
			policeHtmlMap.put("policeJgssxhomeProvCity","");//籍贯
		}*/
		htmlMap.put("policeData",policeHtmlMap);//将查出的公安数据 传到前台 
		String applJson = JSON.toJSONString(appl);
		htmlMap.put("appl",applJson);
		//查询人行摘要信息要求  信息更新日期  最近的两条
		List<Map> listPboc = pbocDao.selectListPbocInfoPersonBankMessage(appId);//queryPbocInfo(appId);
		Map<String,Object> usePboc=new HashMap<String,Object>();
		if(!listPboc.isEmpty()){
			int listPbicSize=listPboc.size();
				if(listPbicSize>=1){
					if(listPboc.get(0)!=null){
					usePboc.putAll(listPboc.get(0));
					}
						if(listPbicSize>1){
							if(listPboc.get(1)!=null){
							usePboc.put("company2", listPboc.get(1).get("company"));
							usePboc.put("compAddr2", listPboc.get(1).get("compAddr"));
							}
						}
				}
		}
		htmlMap.put("pboc",usePboc);
		Map<String, String> usebank = bankDao.queryBankInfo(appId);
		htmlMap.put("bank",usebank);
		//审查库信息
		RevCompInfo revCompInfo = revCompInfoDao.selectByPrimaryKey(appId);
		if(revCompInfo!=null){ 
			if((revCompInfo.getCompany114NameAddr()!=null&&("1").equals(revCompInfo.getCompany114NameAddr()))
					||(revCompInfo.getNoOfficwebNameAddr()!=null&&("1").equals(revCompInfo.getNoOfficwebNameAddr()))
					||(revCompInfo.getOfficwebNameAddr()!=null&&("1").equals(revCompInfo.getOfficwebNameAddr()))
					||(revCompInfo.getOtherThirdNameAddr()!=null&&("1").equals(revCompInfo.getOtherThirdNameAddr()))
					||(revCompInfo.getOrderAddress()!=null&&("1").equals(revCompInfo.getOrderAddress()))
					||(revCompInfo.getPeobankCompanyNameAddr()!=null&&("1").equals(revCompInfo.getPeobankCompanyNameAddr()))
					||(revCompInfo.getThirddataAddr()!=null&&("1").equals(revCompInfo.getThirddataAddr()))){
				yanzhenResMap.put("rev_COMP_ADDR", "是");//审查库单位地址
				yanzhenMesShowMap.put("rev_COMP_ADDR", "单位地址    审查库    是");
			} else {
				yanzhenResMap.put("rev_COMP_ADDR", "否");
			}
			if((revCompInfo.getCompany114NameTel()!=null&&("1").equals(revCompInfo.getCompany114NameTel()))
					||((revCompInfo.getNoOfficwebNameTel()!=null&&("1").equals(revCompInfo.getNoOfficwebNameTel())))
					||((revCompInfo.getOfficwebNameTel()!=null&&("1").equals(revCompInfo.getOfficwebNameTel())))
					||((revCompInfo.getPeobankCompanyNameTel()!=null&&("").equals(revCompInfo.getPeobankCompanyNameTel())))
					||((revCompInfo.getPyuanCompanyNameTel()!=null&&("").equals(revCompInfo.getPyuanCompanyNameTel())))){
				yanzhenResMap.put("rev_COMP_TEL", "是");//审查库单位电话
				yanzhenMesShowMap.put("rev_COMP_TEL", "单位电话    审查库    是");
			} else {
				yanzhenResMap.put("rev_COMP_TEL", "否");
			}
			if((revCompInfo.getPeobankPhone()!=null&&("1").equals(revCompInfo.getPeobankPhone()))){
				yanzhenResMap.put("rev_MOBILE_PHONE", "是");//手机号
				yanzhenMesShowMap.put("rev_MOBILE_PHONE", "手机号    审查库    是");
			} else {
				yanzhenResMap.put("rev_MOBILE_PHONE", "否");
			}
			if ((revCompInfo.getCompany114NameTel()!=null&&("1").equals(revCompInfo.getCompany114NameTel()))
				||((revCompInfo.getNoOfficwebNameTel()!=null&&("1").equals(revCompInfo.getNoOfficwebNameTel())))
				||((revCompInfo.getOfficwebNameTel()!=null&&("1").equals(revCompInfo.getOfficwebNameTel())))
				//||((revCompInfo.getPeobankCompanyNameTel()!=null&&("").equals(revCompInfo.getPeobankCompanyNameTel())))
				||((revCompInfo.getPyuanCompanyNameTel()!=null&&("").equals(revCompInfo.getPyuanCompanyNameTel())))
				||(revCompInfo.getCompany114NameAddr()!=null&&("1").equals(revCompInfo.getCompany114NameAddr()))
				||(revCompInfo.getNoOfficwebNameAddr()!=null&&("1").equals(revCompInfo.getNoOfficwebNameAddr()))
				||(revCompInfo.getOfficwebNameAddr()!=null&&("1").equals(revCompInfo.getOfficwebNameAddr()))
				||(revCompInfo.getOtherThirdNameAddr()!=null&&("1").equals(revCompInfo.getOtherThirdNameAddr()))
				||(revCompInfo.getOrderAddress()!=null&&("1").equals(revCompInfo.getOrderAddress()))
				||(revCompInfo.getRegionalDataListName()!=null&&("1").equals(revCompInfo.getRegionalDataListName()))
				//||(revCompInfo.getPeobankCompanyNameAddr()!=null&&("1").equals(revCompInfo.getPeobankCompanyNameAddr()))
				||(revCompInfo.getCompany()!=null&&("1").equals(revCompInfo.getCompany()))
				//||(revCompInfo.getThirddataAddr()!=null&&("1").equals(revCompInfo.getThirddataAddr()))
				){
				yanzhenResMap.put("rev_COMP_NAME", "是");//审查库单位名称
				yanzhenMesShowMap.put("rev_COMP_NAME", "单位名称    审查库    是");
			} else {
				yanzhenResMap.put("rev_COMP_NAME", "否");//审查库单位名称
			}
			if (revCompInfo.getOrderAddress()!=null&&("1").equals(revCompInfo.getOrderAddress())){
				yanzhenResMap.put("rev_RESIDENT_ADDR", "是");//审查库账单地址
				yanzhenMesShowMap.put("rev_RESIDENT_ADDR", "账单地址   审查库    是");
			}
			htmlMap.put("yanzhenResMap", yanzhenResMap);
			htmlMap.put("yanzhenMesShowMap", yanzhenMesShowMap);
		} 
		
		
		//获得验证信息
		// 组装数据
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", appId);
		// map.put("marchNode", marchNode);
		// 根据appId获得数据源匹配信息
		KeyfiledMarchinfo info = keyfiledMarchinfoDao.selectByAppId(map);// 名单库匹配
		if(info!=null){
		// 获取Json
		String marchResultJson = info.getMarchResult();
		//logger.info("Json=" + marchResultJson);
		// 解析Json串
		JSONArray jarray = JSONArray.fromObject(marchResultJson);
		List<RiskCheckResult> listriskcheck = (List<RiskCheckResult>) JSONArray.toCollection(jarray, RiskCheckResult.class);
		Map<String, String> yanzhenMap = new HashMap<String, String>();
		String c1c2Flag="";
		if(appl.getC1c2Flag()!=null){
			c1c2Flag=appl.getC1c2Flag();//主副卡标记 
		}
		for (RiskCheckResult riskCheck : listriskcheck) {
			//目前 文档中只有   征信电话核查白名单 和人行 CRM 的字段 ApplyType TableName FieldKey RiskType
			if(riskCheck.getRiskType().equals("2")){//名单库匹配  征信电话核查白名单
				if ("1".equals(riskCheck.getRiskResult())) {//得到其中返回结果是1 的
					if(riskCheck.getApplyType().equals(c1c2Flag)){
					// 拼接map中的key规则为表明加字段名
					String key = riskCheck.getTableName() + "-"+ riskCheck.getFieldKey()+"2";
					yanzhenMap.put(key, riskCheck.getPriKeyValue());
					}
				}
			}
			if(riskCheck.getRiskType().equals("3")){//三方数据匹配   人行 CRM
				if ("1".equals(riskCheck.getRiskResult())) {//得到其中返回结果是1 的
					if(riskCheck.getApplyType().equals(c1c2Flag)){
					// 拼接map中的key规则为表明加字段名
					String key = riskCheck.getTableName() + "-"+ riskCheck.getFieldKey()+"3";
					yanzhenMap.put(key, riskCheck.getPriKeyValue());
					 }
					}
				}
		}
		/*
		 *  手机号码	    CRM	    审查	 人行	 征信电话核查白名单    是
   			单位电话	    CRM	    审查	 人行	 征信电话核查白名单    是
			单位名称	    CRM	    审查	 人行	 征信电话核查白名单    是
			单位地址        	    CRM	    审查	 人行	 征信电话核查白名单    是
			账单地址	    CRM	    审查	 人行	 征信电话核查白名单    是
		 */
		
		yanzhenResMap.put("credit_COMPANY_NAME", yanzhenMap.get("OPAS_CREDIT_TELCHECK_LIST-COMPANY_NAME2")==null?"否":"是");//征信电话核查白名单表--单位名称 
		//取出auto 放到前台如果匹配到是方便去白名单库查询
		yanzhenResMap.put("UUID_credit_COMPANY_NAME", yanzhenMap.get("OPAS_CREDIT_TELCHECK_LIST-COMPANY_NAME2"));
		if( yanzhenMap.get("OPAS_CREDIT_TELCHECK_LIST-COMPANY_NAME2")!=null){
			yanzhenMesShowMap.put("credit_COMPANY_NAME", "单位名称    征信电话核查白名单    是");
		}
		yanzhenResMap.put("credit_COMPANY_TEL", yanzhenMap.get("OPAS_CREDIT_TELCHECK_LIST-COMPANY_TEL2")==null?"否":"是");//征信电话核查白名单表--单位电话
		//取出auto 放到前台如果匹配到是方便去白名单库查询
		yanzhenResMap.put("UUID_credit_COMPANY_TEL", yanzhenMap.get("OPAS_CREDIT_TELCHECK_LIST-COMPANY_TEL2"));
		if( yanzhenMap.get("OPAS_CREDIT_TELCHECK_LIST-COMPANY_TEL2")!=null){
			yanzhenMesShowMap.put("credit_COMPANY_TEL", "单位电话    征信电话核查白名单    是");
		}
		yanzhenResMap.put("crm_MOBILEPHONE", yanzhenMap.get("SP_APS_IFS_CUST_INFO-MOBILEPHONE-MOBILEPHONE3")==null?"否":"是");//CRM 客户基本信息(审批)--手机号码
		if( yanzhenMap.get("SP_APS_IFS_CUST_INFO-MOBILEPHONE-MOBILEPHONE3")!=null){
			yanzhenMesShowMap.put("crm_MOBILEPHONE", "手机号码         CRM    是");
		}
		yanzhenResMap.put("crm_BUSPHONE", yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSPHONE3")==null?"否":"是");//--单位电话  
		if(yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSPHONE3")!=null){
			yanzhenMesShowMap.put("crm_BUSPHONE", "单位电话        CRM   是");
		}
		yanzhenResMap.put("crm_PHONE", yanzhenMap.get("SP_APS_IFS_CUST_INFO-PHONE3")==null?"否":"是");//--住宅电话  
		if(yanzhenMap.get("SP_APS_IFS_CUST_INFO-PHONE3")!=null){
			yanzhenMesShowMap.put("crm_PHONE", "住宅电话       CRM   是");
		}
		yanzhenResMap.put("crm_COMPANY", yanzhenMap.get("SP_APS_IFS_CUST_INFO-COMPANY3")==null?"否":"是");//--单位名称
		if(yanzhenMap.get("SP_APS_IFS_CUST_INFO-COMPANY3")!=null){
			yanzhenMesShowMap.put("crm_COMPANY", "单位名称    CRM   是");
		}
		yanzhenResMap.put("crm_BUSADDR", yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSADDR3")==null?"否":"是");//--单位地址
		if(yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSADDR3")!=null){
			yanzhenMesShowMap.put("crm_BUSADDR", "单位地址     CRM   是");
		}
		/*yanzhenResMap.put("crm_HOMEADDR", yanzhenMap.get("SP_APS_IFS_CUST_INFO-HOMEADDR3")==null?"否":"是");//--账单地址(住在地址)
		if(yanzhenMap.get("SP_APS_IFS_CUST_INFO-HOMEADDR3")!=null){
			yanzhenMesShowMap.put("crm_HOMEADDR", "账单地址       CRM   是");
		}*/
		if(c4Cycadd1!=null){//账单地址 由单位地址与住宅地址判断出来
			if("B".equals(c4Cycadd1)){//账单地址 此时代表 单位地址 
				yanzhenResMap.put("crm_HOMEADDR", yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSADDR3")==null?"否":"是");//--单位地址
				if(yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSADDR3")!=null){
					yanzhenMesShowMap.put("crm_HOMEADDR", "账单地址       CRM   是");
				}
			}
            if("H".equals(c4Cycadd1)){//账单地址 此时代表 住宅地址 
            	yanzhenResMap.put("crm_HOMEADDR", yanzhenMap.get("SP_APS_IFS_CUST_INFO-HOMEADDR3")==null?"否":"是");//(住在地址)
        		if(yanzhenMap.get("SP_APS_IFS_CUST_INFO-HOMEADDR3")!=null){
        			yanzhenMesShowMap.put("crm_HOMEADDR", "账单地址       CRM   是");
        		}
			}
		}
			if (pbocFlag == true){
				//人行比对结果
				//===一代人行手机号电话号
				yanzhenResMap.put("pbocMobileFlag", yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-CELL_PHONE3")==null?"0":"1");//
				if(yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-CELL_PHONE3")!=null){
					yanzhenMesShowMap.put("pbocMobileFlag", "手机号码   人行   是");
				}
				yanzhenResMap.put("pbocBusphoneFlag", yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-C_COMP_PHONE3")==null?"0":"1");//
				if(yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-C_COMP_PHONE3")!=null){
					yanzhenMesShowMap.put("pbocBusphoneFlag", "单位电话   人行   是");
				}
				//==下面是二代人行手机号电话号显示
				yanzhenResMap.put("pbocMobileFlag", yanzhenMap.get("PBOC_PHONE_NUMBER_DETAIL-PHONE_NUM3")==null?"0":"1");//--手机号码
				if(yanzhenMap.get("PBOC_PHONE_NUMBER_DETAIL-PHONE_NUM3")!=null){
					yanzhenMesShowMap.put("pbocMobileFlag", "手机号码   人行   是");
				}
				yanzhenResMap.put("pbocBusphoneFlag", yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-UNIT_PHONE3")==null?"0":"1");//--单位电话  
				if(yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-UNIT_PHONE3")!=null){
					yanzhenMesShowMap.put("pbocBusphoneFlag", "单位电话   人行    是");
				}
				yanzhenResMap.put("pbocCompanyFlag", yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMPANY3")==null?"0":"1");//--单位名称
				if(yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMPANY3")!=null){
					yanzhenMesShowMap.put("pbocCompanyFlag", "单位名称    人行    是");
				}
				yanzhenResMap.put("pbocPhoneFlag", yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-RESI_TEL3")==null?"0":"1");//--住宅电话  
				if(yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-RESI_TEL3")!=null){
					yanzhenMesShowMap.put("pbocPhoneFlag", "住宅电话      人行    是");
				}
				yanzhenResMap.put("pbocBusaddrFlag", yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMP_ADDR3")==null?"0":"1");//--单位地址
				if(yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMP_ADDR3")!=null){
					yanzhenMesShowMap.put("pbocBusaddrFlag", "单位地址     人行    是");
				}
				/*yanzhenResMap.put("pbocHomeaddrFlag", yanzhenMap.get("OPAS_PBOC_RESIDENCE_INFO-RESIDENT_ADDR3")==null?"0":"1");//--账单地址(住在地址)
				if(yanzhenMap.get("OPAS_PBOC_RESIDENCE_INFO-RESIDENT_ADDR3")!=null){
					yanzhenMesShowMap.put("pbocHomeaddrFlag", "账单地址    人行   是");
				}*/
				if(c4Cycadd1!=null){//账单地址 由单位地址与住宅地址判断出来
					if("B".equals(c4Cycadd1)){//账单地址 此时代表 单位地址 
						yanzhenResMap.put("pbocHomeaddrFlag", yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMP_ADDR3")==null?"0":"1");//--单位地址
						if(yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMP_ADDR3")!=null){
							yanzhenMesShowMap.put("pbocHomeaddrFlag", "账单地址     人行    是");
						}
					}
		            if("H".equals(c4Cycadd1)){//账单地址 此时代表 住宅地址 
		            	yanzhenResMap.put("pbocHomeaddrFlag", yanzhenMap.get("OPAS_PBOC_RESIDENCE_INFO-RESIDENT_ADDR3")==null?"0":"1");//--账单地址(住在地址)
		        		if(yanzhenMap.get("OPAS_PBOC_RESIDENCE_INFO-RESIDENT_ADDR3")!=null){
		        			yanzhenMesShowMap.put("pbocHomeaddrFlag", "账单地址    人行   是");
		        		}
					}
				}
				htmlMap.put("yanzhenResMap", yanzhenResMap);
				htmlMap.put("yanzhenMesShowMap", yanzhenMesShowMap);
			} else {
				String  oneOrZero="否";
				yanzhenResMap.put("rev_MOBILE_PHONE", oneOrZero);//审查库 客户基本信息(审批)--手机号码
				yanzhenResMap.put("rev_COMP_TEL",oneOrZero);//--单位电话  
				yanzhenResMap.put("rev_COMP_NAME", oneOrZero);//--单位名称
				yanzhenResMap.put("rev_COMP_ADDR",oneOrZero);//--单位地址
				yanzhenResMap.put("rev_RESIDENT_ADDR",oneOrZero);//--审查库账单地址
				htmlMap.put("yanzhenResMap", yanzhenResMap);
				htmlMap.put("yanzhenMesShowMap", yanzhenMesShowMap);
			}
			
		}else{
			String yesOrNo="否";
			yanzhenResMap.put("credit_COMPANY_NAME",yesOrNo);//征信电话核查白名单表
			yanzhenResMap.put("credit_COMPANY_TEL",yesOrNo);//征信电话核查白名单表
			
			yanzhenResMap.put("crm_MOBILEPHONE", yesOrNo);//CRM 客户基本信息(审批)--手机号码
			yanzhenResMap.put("crm_BUSPHONE",yesOrNo);//--单位电话  
			yanzhenResMap.put("crm_PHONE", yesOrNo);//--住宅电话  
			yanzhenResMap.put("crm_COMPANY", yesOrNo);//--单位名称
			yanzhenResMap.put("crm_BUSADDR",yesOrNo);//--单位地址
			yanzhenResMap.put("crm_HOMEADDR",yesOrNo);//--账单地址(住在地址)
			
			if (pbocFlag ==true) {
				String  oneOrZero="0";
				//人行比对结果
				yanzhenResMap.put("pbocMobileFlag",oneOrZero);//--手机号码
				yanzhenResMap.put("pbocBusphoneFlag", oneOrZero);//--单位电话  
				yanzhenResMap.put("pbocCompanyFlag",oneOrZero);//--单位名称
				yanzhenResMap.put("pbocPhoneFlag",oneOrZero);//--住宅电话  
				yanzhenResMap.put("pbocBusaddrFlag",oneOrZero);//--单位地址
				yanzhenResMap.put("pbocHomeaddrFlag",oneOrZero);//--账单地址(住在地址)
				
			}
			
			//测试时使用    
			/*yanzhenMesShowMap.put("credit_COMPANY_NAME", "单位名称    征信电话核查白名单    是");
			yanzhenMesShowMap.put("pboc_RESIDENT_ADDR", "账单地址    人行   是");
			yanzhenMesShowMap.put("pboc_COMPANY", "单位名称    人行    是");*/
			htmlMap.put("yanzhenResMap", yanzhenResMap);
			htmlMap.put("yanzhenMesShowMap", yanzhenMesShowMap);
		}

		//增加人像比对结果
		String policeXpInfo = sysDecisionYdjDao.selectPoliceXPInfo(appId.substring(0,15));
		if(null==policeXpInfo) policeXpInfo="";
		htmlMap.put("faceRes", policeXpInfo);
		
		//增加公安信息
		String  appIdSub = appId.substring(0,15) + "%";
		Map<String,String>  policeStatus  =  policeDao.selectPoliceStatusInfoByAppId(appIdSub);
		String c1PoliceStatus = "",c2PoliceStatus="";
		if(null != policeStatus){
			if(policeStatus.containsKey("C1_POLICE")){
				c1PoliceStatus = policeStatus.get("C1_POLICE");
			}
			if(policeStatus.containsKey("C2_POLICE")){
				c2PoliceStatus = policeStatus.get("C2_POLICE");
			}
		}
		htmlMap.put("c1PoliceStatus",c1PoliceStatus);
		htmlMap.put("c2PoliceStatus",c2PoliceStatus);			

		} catch (Exception e) {
			throw new RuntimeException("查询数据异常。");
		}
		return htmlMap;
	}
	/**
	 * 查询标准卡或易达金具体信息
	 */
	@Override
	public Object selectSystemDecisionByAppId(String appId, String flag) throws Exception {
		if ("1".equals(flag)) {// 易达金
			return ficoYdjBlazeDao.selectSystemDecisionByAppId(appId);
		} else if ("2".equals(flag)) {// 标准卡
			return ficoSdBlazeDao.selectSystemDecisionByAppId(appId);
		} else {
			throw new Exception("flag传输值有误");
		}
	}

	@Override
	public Map<String, Object> findZxCreditInvestigationBzkByAppId(Map paramMap) {
		Map<String, Object> htmlMap=new HashMap<String, Object>();
		//获取申请编号
		String appId = paramMap.get("appId").toString();
		String ydjFlag ="";
		if(paramMap.get("ydjFlag")!=null){
			ydjFlag = paramMap.get("ydjFlag").toString();
		}
		TelcheckResult telcheckResult = telcheckAddnoteDao.selectResultById(appId);
		//String resultJson = JSON.toJSONString(telcheckResult);
		htmlMap.put("result", telcheckResult);
		//标准卡系统决策表  取征信策略结果
		String bzkresult = opasConfQuestionDao.selectReultByAppId(appId);
		htmlMap.put("bzkresult", bzkresult);
		//查询主卡进件信息
		BizInpApplC1 appl=new BizInpApplC1();
		try {
			appl = bizInpApplC1Dao.selectBizInpApplC1ByAppId(appId);
		} catch (CoreException e) {
			logger.error("查询主卡进件信息异常：{}", new Object[] { e.getMessage() }, e);
		}
		String c4Cycadd1=appl.getC4Cycadd1();//账单地址 （账单邮寄地址一）
		/*String c1Nation = appl.getC1Nation();
		if("1".equals(c1Nation)){
			appl.setC2Natncd1("CHN");
		}*/
		String applJson = JSON.toJSONString(appl);
		htmlMap.put("appl",applJson);
		String personInfoAppId="";
		String matchingCardFlag=appl.getMatchingCardFlag();
		if("1".equals(ydjFlag)&&"1".equals(matchingCardFlag)){
			if(appId.endsWith("1")){
				personInfoAppId=appId.substring(0,15)+"2";
			}else if(appId.endsWith("2")){
				personInfoAppId=appId.substring(0,15)+"1";
			}
		}else{
			personInfoAppId=appId;
		}
		/*if("1".equals(ydjFlag)){//易达金  人行查2卡 
			personInfoAppId=appId.substring(0,15)+"2";
		}else{
			personInfoAppId=appId;
		}*/
		//查询人行摘要信息要求  信息更新日期  最近的两条
		List<Map> listPboc = pbocDao.selectListPbocInfoPersonBankMessage(personInfoAppId);//queryPbocInfo(appId);
		
		Map<String,Object> usePboc=new HashMap<String,Object>();
		if(!listPboc.isEmpty()){
		int listPbicSize=listPboc.size();
		 if(listPbicSize>=1){
			 if(listPboc.get(0)!=null){
				 usePboc.putAll(listPboc.get(0));
			 }
	      if(listPboc.size()>=2){
	    	  if(listPboc.get(1)!=null){
	    		  usePboc.put("company2", listPboc.get(1).get("company"));
	    		  usePboc.put("compAddr2", listPboc.get(1).get("compAddr"));
	    	  }
		  }
		 }
		}
		htmlMap.put("pboc",usePboc);
		//查询二代人行摘要信息
		Map<String,String> bank = bankDao.queryBankInfo(appId);
		htmlMap.put("bank",bank);
		Map<String,String> yanzhenMesShowMap = new HashMap<String,String>();//前台验证信息展示用的 map
		Map<String,String> yanzhenResMap = new HashMap<String,String>();
		//蚂蚁
		Opasbizzmivsdata ivsdata = antDao.selectByPrimaryKey(appId);
		if (ivsdata != null) {
			if (ivsdata.getCodeAddrEnglish()!=null){
				if (("ADDR_Match_Recency_Good").equals(ivsdata.getCodeAddrEnglish())
						||("ADDR_Match_Recency_Bad").equals(ivsdata.getCodeAddrEnglish())
						||("ADDR_Match_Trust_Self_Recency_Bad").equals(ivsdata.getCodeAddrEnglish())
						||("ADDR_Match_Trust_Self_Recency_Good").equals(ivsdata.getCodeAddrEnglish())
						||("ADDR_Match_Trust_Other").equals(ivsdata.getCodeAddrEnglish())) {
					yanzhenResMap.put("ant_COMP_ADDR", "1");//蚂蚁单位地址
					yanzhenMesShowMap.put("ant_COMP_ADDR", "单位地址    蚂蚁    是");
				} else {
					yanzhenResMap.put("ant_COMP_ADDR", "0");//蚂蚁单位地址
				}
			} else {
				yanzhenResMap.put("ant_COMP_ADDR", "0");//蚂蚁单位地址
			}
			if (ivsdata.getCodeAddrEnglish()!=null){
				if (("PHONE_Match_Sharing_Good").equals(ivsdata.getCodePhoenEnglish())
						||("PHONE_Match_Trust_Self_Sharing_Good").equals(ivsdata.getCodePhoenEnglish())
						||("PHONE_Match_Recency_Bad").equals(ivsdata.getCodePhoenEnglish())
						||("PHONE_Match_Trust_Self_Recency_Bad").equals(ivsdata.getCodePhoenEnglish())
						||("PHONE_Match_Recency_Good").equals(ivsdata.getCodePhoenEnglish())
						||("PHONE_Match_Trust_Self_Recency_Good").equals(ivsdata.getCodePhoenEnglish())
						||("PHONE_Match_Reliabilit_Good").equals(ivsdata.getCodePhoenEnglish())
						||("PHONE_Match_Trust_Self_Reliability_Good").equals(ivsdata.getCodePhoenEnglish())
						||("PHONE_Match_Trust_Other").equals(ivsdata.getCodePhoenEnglish())) {
					yanzhenResMap.put("ant_MOBILE_PHONE", "1");//蚂蚁 手机号
					yanzhenMesShowMap.put("ant_MOBILE_PHONE", "手机号    蚂蚁    是");
				} else {
					yanzhenResMap.put("ant_MOBILE_PHONE", "0");//蚂蚁 手机号
				}
			} else {
				yanzhenResMap.put("ant_MOBILE_PHONE", "0");//蚂蚁 手机号
			}
			htmlMap.put("yanzhenResMap", yanzhenResMap);
			htmlMap.put("yanzhenMesShowMap", yanzhenMesShowMap);
		} else {
			String  oneOrZero="0";
			yanzhenResMap.put("ant_COMP_ADDR", oneOrZero);//--单位名称
			yanzhenResMap.put("ant_MOBILE_PHONE",oneOrZero);//--手机号
			htmlMap.put("yanzhenResMap", yanzhenResMap);
			htmlMap.put("yanzhenMesShowMap", yanzhenMesShowMap);
		}
		
		//审查库review
		RevCompInfo revCompInfo=null;
		try {
			revCompInfo = revCompInfoDao.selectByPrimaryKey(appId);
		} catch (CoreException e) {
			logger.error("审查库查询异常：{}", new Object[] { e.getMessage() }, e);
		}
		if(revCompInfo!=null){ 
			if((revCompInfo.getCompany114NameAddr()!=null&&("1").equals(revCompInfo.getCompany114NameAddr()))
					||(revCompInfo.getNoOfficwebNameAddr()!=null&&("1").equals(revCompInfo.getNoOfficwebNameAddr()))
					||(revCompInfo.getOfficwebNameAddr()!=null&&("1").equals(revCompInfo.getOfficwebNameAddr()))
					||(revCompInfo.getOtherThirdNameAddr()!=null&&("1").equals(revCompInfo.getOtherThirdNameAddr()))
					//||(revCompInfo.getOrderAddress()!=null&&("1").equals(revCompInfo.getOrderAddress()))
					||(revCompInfo.getPeobankCompanyNameAddr()!=null&&("1").equals(revCompInfo.getPeobankCompanyNameAddr()))
					||(revCompInfo.getPyuanCompanyNameAddr()!=null&&("1").equals(revCompInfo.getPyuanCompanyNameAddr()))
					||(revCompInfo.getThirddataAddr()!=null&&("1").equals(revCompInfo.getThirddataAddr()))){
				yanzhenResMap.put("rev_COMP_ADDR", "1");//审查库单位地址
				yanzhenMesShowMap.put("rev_COMP_ADDR", "单位地址    审查库    是");
			} else {
				yanzhenResMap.put("rev_COMP_ADDR", "0");
			}
			if((revCompInfo.getCompany114NameTel()!=null&&("1").equals(revCompInfo.getCompany114NameTel()))
					||((revCompInfo.getNoOfficwebNameTel()!=null&&("1").equals(revCompInfo.getNoOfficwebNameTel())))
					||((revCompInfo.getOfficwebNameTel()!=null&&("1").equals(revCompInfo.getOfficwebNameTel())))
					||((revCompInfo.getPeobankCompanyNameTel()!=null&&("1").equals(revCompInfo.getPeobankCompanyNameTel())))
					||((revCompInfo.getPyuanCompanyNameTel()!=null&&("1").equals(revCompInfo.getPyuanCompanyNameTel())))){
				yanzhenResMap.put("rev_COMP_TEL", "1");//审查库单位电话
				yanzhenMesShowMap.put("rev_COMP_TEL", "单位电话    审查库    是");
			} else {
				yanzhenResMap.put("rev_COMP_TEL", "0");
			}
			if ((revCompInfo.getCompany114NameTel()!=null&&("1").equals(revCompInfo.getCompany114NameTel()))
				||((revCompInfo.getNoOfficwebNameTel()!=null&&("1").equals(revCompInfo.getNoOfficwebNameTel())))
				||((revCompInfo.getOfficwebNameTel()!=null&&("1").equals(revCompInfo.getOfficwebNameTel())))
				//||((revCompInfo.getPeobankCompanyNameTel()!=null&&("").equals(revCompInfo.getPeobankCompanyNameTel())))
				||((revCompInfo.getPyuanCompanyNameTel()!=null&&("1").equals(revCompInfo.getPyuanCompanyNameTel())))
				||(revCompInfo.getCompany114NameAddr()!=null&&("1").equals(revCompInfo.getCompany114NameAddr()))
				||(revCompInfo.getNoOfficwebNameAddr()!=null&&("1").equals(revCompInfo.getNoOfficwebNameAddr()))
				||(revCompInfo.getOfficwebNameAddr()!=null&&("1").equals(revCompInfo.getOfficwebNameAddr()))
				||(revCompInfo.getOtherThirdNameAddr()!=null&&("1").equals(revCompInfo.getOtherThirdNameAddr()))
				||(revCompInfo.getCompany()!=null&&("1").equals(revCompInfo.getCompany()))
				||(revCompInfo.getPyuanCompanyNameAddr()!=null&&("1").equals(revCompInfo.getPyuanCompanyNameAddr()))
				||(revCompInfo.getRegionalDataListName()!=null&&("1").equals(revCompInfo.getRegionalDataListName()))
				//||(revCompInfo.getPeobankCompanyNameAddr()!=null&&("1").equals(revCompInfo.getPeobankCompanyNameAddr()))
				//||(revCompInfo.getThirddataAddr()!=null&&("1").equals(revCompInfo.getThirddataAddr()))
				){
				yanzhenResMap.put("rev_COMP_NAME", "1");//审查库单位名称
				yanzhenMesShowMap.put("rev_COMP_NAME", "单位名称    审查库    是");
			} else {
				yanzhenResMap.put("rev_COMP_NAME", "0");//审查库单位名称
			}
			if (revCompInfo.getPeobankPhone()!=null&&("1").equals(revCompInfo.getPeobankPhone())){
				yanzhenResMap.put("rev_MOBILE_PHONE", "1");//审查库单位名称
				yanzhenMesShowMap.put("rev_MOBILE_PHONE", "手机号    审查库    是");
			}
			if (revCompInfo.getOrderAddress()!=null&&("1").equals(revCompInfo.getOrderAddress())){
				yanzhenResMap.put("rev_RESIDENT_ADDR", "1");//审查库账单地址
				yanzhenMesShowMap.put("rev_RESIDENT_ADDR", "账单地址   审查库    是");
			}
			htmlMap.put("yanzhenResMap", yanzhenResMap);
			htmlMap.put("yanzhenMesShowMap", yanzhenMesShowMap);
		} else {
			String  oneOrZero="0";
			yanzhenResMap.put("rev_MOBILE_PHONE", oneOrZero);//审查库 客户基本信息(审批)--手机号码
			yanzhenResMap.put("rev_COMP_TEL",oneOrZero);//--单位电话  
			yanzhenResMap.put("rev_COMP_NAME", oneOrZero);//--单位名称
			yanzhenResMap.put("rev_COMP_ADDR",oneOrZero);//--单位地址
			yanzhenResMap.put("rev_RESIDENT_ADDR",oneOrZero);//审查库账单地址
			htmlMap.put("yanzhenResMap", yanzhenResMap);
			htmlMap.put("yanzhenMesShowMap", yanzhenMesShowMap);
		}
		//获得验证信息
		// 组装数据
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", appId);
		// map.put("marchNode", marchNode);
		// 根据appId获得数据源匹配信息
		KeyfiledMarchinfo info = keyfiledMarchinfoDao.selectByAppId(map);// 名单库匹配
		if(info!=null){
		// 获取Json
		String marchResultJson = info.getMarchResult();
		//logger.info("Json=" + marchResultJson);
		// 解析Json串
		JSONArray jarray = JSONArray.fromObject(marchResultJson);
		List<RiskCheckResult> listriskcheck = (List<RiskCheckResult>) JSONArray.toCollection(jarray, RiskCheckResult.class);
		Map<String, String> yanzhenMap = new HashMap<String, String>();
		String c1c2Flag="";
		if(appl.getC1c2Flag()!=null){
			c1c2Flag=appl.getC1c2Flag();//主副卡标记 
		}
		for (RiskCheckResult riskCheck : listriskcheck) {
			// 目前 文档中只有 征信电话核查白名单 和人行 CRM 的字段 ApplyType TableName FieldKey
			// RiskType
			if (riskCheck.getRiskType().equals("2")) {// 名单库匹配 征信电话核查白名单
				if ("1".equals(riskCheck.getRiskResult())) {// 得到其中返回结果是1 的
					if (riskCheck.getApplyType().equals(c1c2Flag)) {
						// 拼接map中的key规则为表明加字段名
						String key = riskCheck.getTableName() + "-" + riskCheck.getFieldKey() + "2";
						yanzhenMap.put(key, riskCheck.getPriKeyValue());
					}
				}
			}
			if (riskCheck.getRiskType().equals("3")) {// 三方数据匹配 人行 CRM
				if ("1".equals(riskCheck.getRiskResult())) {// 得到其中返回结果是1 的
					if (riskCheck.getApplyType().equals(c1c2Flag)) {
						// 拼接map中的key规则为表明加字段名
						String key = riskCheck.getTableName() + "-" + riskCheck.getFieldKey() + "3";
						yanzhenMap.put(key, riskCheck.getPriKeyValue());
					}
				}
			}
			
		}
		//企业行业
		yanzhenResMap.put("qyhy_TEL", yanzhenMap.get("TRD_QYHY_INFO_DATA-TEL2")==null?"0":"1");//
		if( yanzhenMap.get("TRD_QYHY_INFO_DATA-TEL2")!=null){
			yanzhenMesShowMap.put("qyhy_TEL", "单位电话    企业网    是");
		}
		yanzhenResMap.put("qyhy_ENTNAME", yanzhenMap.get("TRD_QYHY_INFO_DATA-ENTNAME2")==null?"0":"1");//
		if( yanzhenMap.get("TRD_QYHY_INFO_DATA-ENTNAME2")!=null){
			yanzhenMesShowMap.put("qyhy_ENTNAME", "单位名称    企业网    是");
		}
		yanzhenResMap.put("qyhy_DOM", yanzhenMap.get("TRD_QYHY_INFO_DATA-DOM2")==null?"0":"1");//
		if( yanzhenMap.get("TRD_QYHY_INFO_DATA-DOM2")!=null){
			yanzhenMesShowMap.put("qyhy_DOM", "单位地址    企业网    是");
		}
		
		//白名单表
		yanzhenResMap.put("credit_COMPANY_NAME", yanzhenMap.get("OPAS_CREDITINFO_LIST_TEMP-COMPANY_NAME2")==null?"0":"1");//征信电话核查白名单表
		//取出auto 放到前台如果匹配到是方便去白名单库查询
		yanzhenResMap.put("UUID_credit_COMPANY_NAME", yanzhenMap.get("OPAS_CREDITINFO_LIST_TEMP-COMPANY_NAME2"));
		if( yanzhenMap.get("OPAS_CREDITINFO_LIST_TEMP-COMPANY_NAME2")!=null){
			yanzhenMesShowMap.put("credit_COMPANY_NAME", "单位名称    征信电话核查白名单    是");
		}
		yanzhenResMap.put("credit_COMPANY_ADDR", yanzhenMap.get("OPAS_CREDITINFO_LIST_TEMP-COMPANY_ADDR2")==null?"0":"1");//征信电话核查白名单表
		//取出auto 放到前台如果匹配到是方便去白名单库查询
		yanzhenResMap.put("UUID_credit_COMPANY_ADDR", yanzhenMap.get("OPAS_CREDITINFO_LIST_TEMP-COMPANY_ADDR2"));
		if( yanzhenMap.get("OPAS_CREDITINFO_LIST_TEMP-COMPANY_ADDR2")!=null){
			yanzhenMesShowMap.put("credit_COMPANY_ADDR", "单位地址    征信电话核查白名单    是");
		}
		yanzhenResMap.put("credit_COMPANY_TEL", yanzhenMap.get("OPAS_CREDITINFO_LIST_TEMP-COMPANY_TEL2")==null?"0":"1");//征信电话核查白名单表
		//取出auto 放到前台如果匹配到是方便去白名单库查询
		yanzhenResMap.put("UUID_credit_COMPANY_TEL", yanzhenMap.get("OPAS_CREDITINFO_LIST_TEMP-COMPANY_TEL2"));
		if( yanzhenMap.get("OPAS_CREDITINFO_LIST_TEMP-COMPANY_TEL2")!=null){
			yanzhenMesShowMap.put("credit_COMPANY_TEL", "单位电话    征信电话核查白名单    是");
		}
		//CRM												 
		yanzhenResMap.put("crm_MOBILEPHONE", yanzhenMap.get("SP_APS_IFS_CUST_INFO-MOBILEPHONE3")==null?"0":"1");//CRM 客户基本信息(审批)--手机号码
		if( yanzhenMap.get("SP_APS_IFS_CUST_INFO-MOBILEPHONE3")!=null){
			yanzhenMesShowMap.put("crm_MOBILEPHONE", "手机号码         CRM    是");
		}
		yanzhenResMap.put("crm_BUSPHONE", yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSPHONE3")==null?"0":"1");//--单位电话  
		if(yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSPHONE3")!=null){
			yanzhenMesShowMap.put("crm_BUSPHONE", "单位电话        CRM   是");
		}
		/*yanzhenResMap.put("crm_PHONE", yanzhenMap.get("SP_APS_IFS_CUST_INFO-PHONE3")==null?"0":"1");//--住宅电话  
		if(yanzhenMap.get("SP_APS_IFS_CUST_INFO-PHONE3")!=null){
			yanzhenMesShowMap.put("crm_PHONE", "住宅电话       CRM   是");
		}*/
		yanzhenResMap.put("crm_COMPANY", yanzhenMap.get("SP_APS_IFS_CUST_INFO-COMPANY3")==null?"0":"1");//--单位名称
		if(yanzhenMap.get("SP_APS_IFS_CUST_INFO-COMPANY3")!=null){
			yanzhenMesShowMap.put("crm_COMPANY", "单位名称    CRM   是");
		}
		yanzhenResMap.put("crm_BUSADDR", yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSADDR3")==null?"0":"1");//--单位地址
		if(yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSADDR3")!=null){
			yanzhenMesShowMap.put("crm_BUSADDR", "单位地址     CRM   是");
		}
		if(c4Cycadd1!=null){//账单地址 由单位地址与住宅地址判断出来
			if("B".equals(c4Cycadd1)){//账单地址 此时代表 单位地址 
				yanzhenResMap.put("crm_HOMEADDR", yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSADDR3")==null?"0":"1");//--单位地址
				if(yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSADDR3")!=null){
					yanzhenMesShowMap.put("crm_HOMEADDR", "账单地址       CRM   是");
				}
			}
            if("H".equals(c4Cycadd1)){//账单地址 此时代表 住宅地址 
            	yanzhenResMap.put("crm_HOMEADDR", yanzhenMap.get("SP_APS_IFS_CUST_INFO-HOMEADDR3")==null?"0":"1");//(住在地址)
        		if(yanzhenMap.get("SP_APS_IFS_CUST_INFO-HOMEADDR3")!=null){
        			yanzhenMesShowMap.put("crm_HOMEADDR", "账单地址       CRM   是");
        		}
			}
		}
		//===一代人行手机号电话号
		yanzhenResMap.put("pbocMobileFlag", yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-CELL_PHONE3")==null?"0":"1");//
		if(yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-CELL_PHONE3")!=null){
			yanzhenMesShowMap.put("pbocMobileFlag", "手机号码   人行   是");
		}
		yanzhenResMap.put("pbocBusphoneFlag", yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-C_COMP_PHONE3")==null?"0":"1");//
		if(yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-C_COMP_PHONE3")!=null){
			yanzhenMesShowMap.put("pbocBusphoneFlag", "单位电话   人行   是");
		}
		//==下面是二代人行手机号电话号显示
		//人行比对结果PBOC_PHONE_NUMBER_DETAIL-PHONE_NUM
		yanzhenResMap.put("pbocMobileFlag", yanzhenMap.get("PBOC_PHONE_NUMBER_DETAIL-PHONE_NUM3")==null?"0":"1");//--手机号码
		if(yanzhenMap.get("PBOC_PHONE_NUMBER_DETAIL-PHONE_NUM3")!=null){
			yanzhenMesShowMap.put("pbocMobileFlag", "手机号码   人行   是");
		}
		yanzhenResMap.put("pbocBusphoneFlag", yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-UNIT_PHONE3")==null?"0":"1");//--单位电话  
		if(yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-UNIT_PHONE3")!=null){
			yanzhenMesShowMap.put("pbocBusphoneFlag", "单位电话   人行    是");
		}
		yanzhenResMap.put("pbocCompanyFlag", yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMPANY3")==null?"0":"1");//--单位名称
		if(yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMPANY3")!=null){
			yanzhenMesShowMap.put("pbocCompanyFlag", "单位名称    人行    是");
		}
		/*yanzhenResMap.put("pbocPhoneFlag", yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-RESI_TEL3")==null?"0":"1");//--住宅电话  
		if(yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-RESI_TEL3")!=null){
			yanzhenMesShowMap.put("pbocPhoneFlag", "住宅电话      人行    是");
		}*/
		yanzhenResMap.put("pbocBusaddrFlag", yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMP_ADDR3")==null?"0":"1");//--单位地址
		if(yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMP_ADDR3")!=null){
			yanzhenMesShowMap.put("pbocBusaddrFlag", "单位地址     人行    是");
		}
		/*yanzhenResMap.put("pbocHomeaddrFlag", yanzhenMap.get("OPAS_PBOC_RESIDENCE_INFO-RESIDENT_ADDR3")==null?"0":"1");//--账单地址(住在地址)
		if(yanzhenMap.get("OPAS_PBOC_RESIDENCE_INFO-RESIDENT_ADDR3")!=null){
			yanzhenMesShowMap.put("pbocHomeaddrFlag", "账单地址    人行   是");
		}*/
		if(c4Cycadd1!=null){//账单地址 由单位地址与住宅地址判断出来
			if("B".equals(c4Cycadd1)){//账单地址 此时代表 单位地址 
				yanzhenResMap.put("pbocHomeaddrFlag", yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMP_ADDR3")==null?"0":"1");//--单位地址
				if(yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMP_ADDR3")!=null){
					yanzhenMesShowMap.put("pbocHomeaddrFlag", "账单地址     人行    是");
				}
			}
            if("H".equals(c4Cycadd1)){//账单地址 此时代表 住宅地址 
            	yanzhenResMap.put("pbocHomeaddrFlag", yanzhenMap.get("OPAS_PBOC_RESIDENCE_INFO-RESIDENT_ADDR3")==null?"0":"1");//--账单地址(住在地址)
        		if(yanzhenMap.get("OPAS_PBOC_RESIDENCE_INFO-RESIDENT_ADDR3")!=null){
        			yanzhenMesShowMap.put("pbocHomeaddrFlag", "账单地址    人行   是");
        		}
			}
		}
		htmlMap.put("yanzhenResMap", yanzhenResMap);
		htmlMap.put("yanzhenMesShowMap", yanzhenMesShowMap);
		}else{
			String  oneOrZero="0";
			yanzhenResMap.put("qyhy_TEL",oneOrZero);//企业网
			yanzhenResMap.put("qyhy_ENTNAME",oneOrZero);//
			yanzhenResMap.put("qyhy_DOM",oneOrZero);//
			
			yanzhenResMap.put("credit_COMPANY_NAME",oneOrZero);//征信电话核查白名单表
			yanzhenResMap.put("credit_COMPANY_TEL",oneOrZero);//征信电话核查白名单表
			yanzhenResMap.put("credit_COMPANY_ADDR",oneOrZero);//征信电话核查白名单表
			
			yanzhenResMap.put("crm_MOBILEPHONE", oneOrZero);//CRM 客户基本信息(审批)--手机号码
			yanzhenResMap.put("crm_BUSPHONE",oneOrZero);//--单位电话  
			//yanzhenResMap.put("crm_PHONE", oneOrZero);//--住宅电话  
			yanzhenResMap.put("crm_COMPANY", oneOrZero);//--单位名称
			yanzhenResMap.put("crm_BUSADDR",oneOrZero);//--单位地址
			yanzhenResMap.put("crm_HOMEADDR",oneOrZero);//--账单地址(住在地址)
			
			//人行比对结果
			yanzhenResMap.put("pbocMobileFlag",oneOrZero);//--手机号码
			yanzhenResMap.put("pbocBusphoneFlag", oneOrZero);//--单位电话  
			yanzhenResMap.put("pbocCompanyFlag",oneOrZero);//--单位名称
			//yanzhenResMap.put("pbocPhoneFlag",oneOrZero);//--住宅电话  
			yanzhenResMap.put("pbocBusaddrFlag",oneOrZero);//--单位地址
			yanzhenResMap.put("pbocHomeaddrFlag",oneOrZero);//--账单地址(住在地址)
			//测试验证信息显示 时使用   
			/*yanzhenMesShowMap.put("credit_COMPANY_NAME", "单位名称    征信电话核查白名单    是");
			yanzhenMesShowMap.put("pboc_RESIDENT_ADDR", "账单地址    人行   是");
			yanzhenMesShowMap.put("pboc_COMPANY", "单位名称    人行    是");*/
			htmlMap.put("yanzhenResMap", yanzhenResMap);
			htmlMap.put("yanzhenMesShowMap", yanzhenMesShowMap);
		}

		//增加人像比对结果
		String policeXpInfo = sysDecisionYdjDao.selectPoliceXPInfo(appId.substring(0,15));
		if(null==policeXpInfo) policeXpInfo="";
		htmlMap.put("faceRes", policeXpInfo);

		//增加公安信息
		String  appIdSub = appId.substring(0,15) + "%";
		Map<String,String>  policeStatus  =  policeDao.selectPoliceStatusInfoByAppId(appIdSub);
		String c1PoliceStatus = "",c2PoliceStatus="";
		if(null != policeStatus){
			if(policeStatus.containsKey("C1_POLICE")){
				c1PoliceStatus = policeStatus.get("C1_POLICE");
			}
			if(policeStatus.containsKey("C2_POLICE")){
				c2PoliceStatus = policeStatus.get("C2_POLICE");
			}
		}
		htmlMap.put("c1PoliceStatus",c1PoliceStatus);
		htmlMap.put("c2PoliceStatus",c2PoliceStatus);
		//增加智能语言结论信息  
		String conclusion = "";
		String svoiceMark = "";
		String outsourcingGroup = "";//外包组
		Map<String,String> svoiceMap = policeDao.selectSVoiceInfo(appIdSub);
		//分件历史表中查看是否退回
		String count = policeDao.querySVoiceBackInfoByAppId(appId);
		String svoiceBackFlag = (null==count||"0".equals(count))?"0":"1";
		htmlMap.put("svoiceBackFlag",svoiceBackFlag);
		//查看申请件所在组
		AllotApplyAllot allot = allotApplyAllotDao.selectByPrimaryKey(appId);
		if(allot!=null){
			if(allot.getCurrOptGroup()!=null&&allot.getCurrOptGroup().startsWith("WB")){
				outsourcingGroup = "1";//外包组
			}
		}
		if(null!=svoiceMap){
			//CRT_TIME  CONCLUSION  SUMMARY		
			if(svoiceMap.containsKey("CONCLUSION")){
				conclusion = svoiceMap.get("CONCLUSION").trim();
			}
			if(svoiceMap.containsKey("CRT_TIME")){
				svoiceMark+= svoiceMap.get("CRT_TIME")+" ";
			}
			if(svoiceMap.containsKey("SUMMARY")){
				svoiceMark+= svoiceMap.get("SUMMARY")+" ";
			}
		}
		htmlMap.put("conclusion",conclusion);
		htmlMap.put("svoiceMark",svoiceMark);
		htmlMap.put("outsourcingGroup",outsourcingGroup);
		//当为网申件的时候查可信结果用于提交校验
		String authResult="";
		if("B".equals(appId.substring(6, 7))){
			map.put("appId", appId);
			map.put("cardFlag", "0");
			authResult = txOperatorDao.querySfrzBaseData(map);
			if(null != authResult){
				authResult = authResult.substring(0, 1);
			}
		}
		htmlMap.put("authResult",authResult);
		//当为网申件事查是否需要清除可信任务表
		if("B".equals(appId.substring(6, 7))){
			try {
				//检测可信是否需要删除任务表信息
				if(keyMessageModifyDao.selectKxTaskCount(appId)>0){
					keyMessageModifyDao.deleteKxSuccTask(appId);
				}
			} catch (Exception e) {
				logger.error("可信重查清除任务表异常",e);
			}
		}
		
		return htmlMap;
	}

	/**
	 * 返回决策结果描述
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public List queryDescByAppId(Map map,int curNum,int pageNum) throws Exception {
		return applyInfoDao.queryDescByAppId(map,curNum,pageNum);
	}

	@Override
	public int queryCountDesc(Map record) throws CoreException {
		return applyInfoDao.queryCountDesc(record);
	}

	@Override
	public Map<String, Object> queryYDJCreditTelcheckMap(Map<String, Object> map) {
		Map<String, Object> dateMap = new HashMap<String, Object>();
		int curNum = (int) map.get("curNum");
		int pageNum = (int) map.get("pageNum");
		int count = creditTelcheckListDao.queryCreditTelcheckListCount(map);
		List<CreditTelcheckList> list = new ArrayList<CreditTelcheckList>();
		if (count > 0) {
			list = creditTelcheckListDao.queryCreditTelcheckList(map, curNum,pageNum);
		}
		dateMap.put("total", count);
		dateMap.put("rows", list);
		return dateMap;
	}

	@Override
	public Map<String, Object> queryBZKCreditTelcheckMap(Map<String, Object> map) {
		Map<String, Object> dateMap = new HashMap<String, Object>();
		int curNum = (int) map.get("curNum");
		int pageNum = (int) map.get("pageNum");
		int count = creditTelcheckListDao.selectBZKCreditTelcheckListCount(map);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (count > 0) {
			list = creditTelcheckListDao.selectBZKCreditTelcheckList(map, curNum,pageNum);
		}
		dateMap.put("total", count);
		dateMap.put("rows", list);
		return dateMap;
	}

	@Override
	public String queryWorkCompany(String appId) {
		
		return bizInpApplC1Dao.queryWorkCompany(appId);
	}

	@Override
	public String queryRequestTypeByAppId(String appId) {
		
		return bizInpApplC1Dao.queryRequestTypeByAppId(appId);
	}

	@Override
	public Map<String, Object> selectFqzRstDespByAppId(String appId) {
		appId = appId.substring(0, appId.length()-1);
		Map<String, Object> param = new HashMap<String, Object>();
		String fqzRequesttype=sysDecisionYdjDao.selectMaxFqzRequestTypeByAppId(appId);
		param.put("fqzRequesttype", fqzRequesttype);
		param.put("appId", appId);
		//疑似中介进件
		String misszdResult= sysDecisionYdjDao.selectMisszdResultByAppId(appId);
		List<Map<String, Object>> fqzRstDesp = sysDecisionYdjDao.selectFqzRstDespByAppId(param);
		List<Map<String, Object>> fqzRiskDec = sysDecisionYdjDao.selectFqzRiskDec(param);
		
		param.put("fqzRiskDec",fqzRiskDec);
		param.put("misszdResult",misszdResult);
		param.put("fqzRstDesp",fqzRstDesp);
		return param;
	}
	/**
	 * 审批页面-客户信息 wdb 
	 */
	@Override
	public OpasCustBaseInfo selectCustAndPreSellimitByAppNo(Map map) {
		return sysDecisionYdjDao.selectCustAndPreSellimitByAppNo(map);
	}

	@Override
	public String selectPoliceXPInfo(String appId) {
		return sysDecisionYdjDao.selectPoliceXPInfo(appId);
	}

}
