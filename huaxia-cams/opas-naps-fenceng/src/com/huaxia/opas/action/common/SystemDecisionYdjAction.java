package com.huaxia.opas.action.common;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.apply.ApplyModifyLog;
import com.huaxia.opas.domain.apply.ApplyRemark;
import com.huaxia.opas.domain.decision.CreditTelcheckList;
import com.huaxia.opas.domain.decision.OpaInnerDataCheck;
import com.huaxia.opas.domain.decision.OpaRiskInfoCheck;
import com.huaxia.opas.domain.decision.OpasCustBaseInfo;
import com.huaxia.opas.domain.decision.OpasHaveCardCustInfo;
import com.huaxia.opas.domain.decision.TeamdealList;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.input.FicoYdjBlaze;
import com.huaxia.opas.domain.sysparm.TargetcompanyList;
import com.huaxia.opas.service.apply.ApplyRemarkService;
import com.huaxia.opas.service.decision.SysButtonCommonService;
import com.huaxia.opas.service.decision.SysDecisionYdjService;
import com.huaxia.opas.service.thirdparty.AntService;
import com.huaxia.opas.util.CommonUtil;
import com.huaxia.opas.util.StringUtil;

/**
 * 
 * @author xuzhiguo 系统决策结果tab页的展示 2017-3-7 14:59:27
 */

public class SystemDecisionYdjAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(SystemDecisionYdjAction.class);

	@Resource(name = "sysDecisionYdjService")
	private SysDecisionYdjService sysDecisionYdjService;
	@Resource(name = "applyRemarkService")
	private ApplyRemarkService applyRemarkService;
	@Resource(name = "sysButtonCommonService")
	private SysButtonCommonService sysButtonCommonService;
	@Resource(name = "antService")
	private AntService antService;
	/**
	 * 根据申请编号查询团办信息
	 * 
	 * @param appId
	 * @author xuzhiguo
	 * @throws Exception
	 */
	public void querySystemDecisionByAppId(Context ctx) throws Exception {
		try {
			int curNum = 0;
			int curPage = Integer
					.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
			int pageNum = Integer
					.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			String appId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId"));
			// 获得标示flag
			String flag = StringUtils.trimToEmpty((String) ctx.getDataMap().get("flag"));
			String paramFlag = StringUtils.trimToEmpty((String) ctx.getDataMap().get("paramFlag"));//1:是匹配结果，2是查询结果
			Map paramMap = (Map)ctx.getDataMap().get("paramMap");
			int count = 0;
			List list = new ArrayList();
			try {
				count = sysDecisionYdjService.queryCountList(appId, flag,paramMap,paramFlag);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(count>0){				
				list = sysDecisionYdjService.queryByAppId(appId, flag,paramMap,paramFlag,curNum,pageNum);
			}
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("total", count);
			dataMap.put("rows", list);
			ctx.setData("isSuccess", true);
			ctx.setDataMap(dataMap);
		} catch (Exception e) {
			logger.info("团办详情展示出现异常，请检测");
			e.printStackTrace();
		}
	}
	/**
	 * 查询目标企业
	 * @param ctx
	 * @throws Exception
	 */
	public void queryTargetcompany(Context ctx) throws Exception{
		try{
			String appId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId"));
			TargetcompanyList target = sysDecisionYdjService.queryByAppId(appId)==null?new TargetcompanyList():sysDecisionYdjService.queryByAppId(appId);
			ctx.setData("target", target);
			ctx.setData("isSuccess", true);	
			} catch (Exception e) {
				logger.info("团办详情展示出现异常，请检测");
				e.printStackTrace();
			}
	}
	/**
	 * 查询非A重要团办
	 * @param ctx
	 * @throws Exception
	 */
	public void queryTeamnoIsmatch(Context ctx) throws Exception{
		try{
			String appId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId"));
			TeamdealList teamdeal =  sysDecisionYdjService.queryTeamdealByAppId(appId)==null?new TeamdealList():sysDecisionYdjService.queryTeamdealByAppId(appId);
			ctx.setData("isSuccess", true);	
			ctx.setData("teamdeal", teamdeal);
			} catch (Exception e) {
				logger.info("团办详情展示出现异常，请检测");
				e.printStackTrace();
			}
	}
	/**
	 * 查询重点院校、优质企业、是否是本行行员
	 * @param ctx
	 * @throws Exception
	 */
	public void queryMindan(Context ctx) throws Exception{
		try{
			String appId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId"));
			OpasCustBaseInfo custInfo = sysDecisionYdjService.selectCustInfoByAppId(appId);
			Assert.notNull(custInfo, "请求申请件实体为空!");
			String workCompany = sysDecisionYdjService.queryWorkCompany(appId);
			if(!"".equals(workCompany)&&workCompany!=null){
				custInfo.setWorkCompany("1");
			}else{
				custInfo.setWorkCompany("0");
			}
			Map<String, String> resMap = sysDecisionYdjService.queryTeam(appId);
			if (resMap == null) {
				resMap = new HashMap<String, String>();
			}
			String autoid1 = resMap.get("OPAS_GOODCOMPANY_LIST-PROJECT_CODE");
			String autoid2 = resMap.get("OPAS_GOODCOMPANY_LIST-COMPANY_NAME");
			String autoid = autoid1 != null ? autoid1 : autoid2;
			String autofinal = "0";
			if (autoid != null) {
				autofinal = "1";
			}
			custInfo.setGoodCompanyMindan(autofinal);// 优质企业名单表
			custInfo.setImportSchoolMindan(resMap.get("OPAS_MAJORSCHOOL_LIST-MAJORSCHOOL_NAME") == null ? "0" : "1");// 重点院校名单表
			ctx.setData("isSuccess", true);	
			ctx.setData("custInfo", custInfo);
			} catch (Exception e) {
				logger.info("团办详情展示出现异常，请检测");
				e.printStackTrace();
			}
	}
	/**
	 * 易达金决策描述
	 * @param ctx
	 * @author hang
	 * @throws Exception
	 */
	public void queryDescByAppID(Context ctx) throws Exception{
		try {
			
			int curNum = 0;
			int curPage = Integer
					.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
			int pageNum = Integer
					.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			Map map = ctx.getDataMap();
			String appId = (String) map.get("appId");
			Assert.notNull(appId, "请求申请件编号为空!");
			String requestType = sysDecisionYdjService.queryRequestTypeByAppId(appId);
			
			int count = 0;
			List list = new ArrayList();
			List requestList=new ArrayList();
			if(requestType!=null&&!"".equals(requestType)){	
				requestList.add("YDJ1005");
				requestList.add("YDJ0600");
				map.put("requestList", requestList);
				//map.put("requesttype", "YDJ1005");
				try {
					count = sysDecisionYdjService.queryCountDesc(map);
				} catch (CoreException e) {
					e.printStackTrace();
				}
				if (count > 0) {
					try {
						list = sysDecisionYdjService.queryDescByAppId(map,curNum,pageNum);
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}else{
				//map.put("requesttype", "YDJ1004");
				requestList.add("YDJ1004");
				requestList.add("YDJ0500");
				map.put("requestList", requestList);
				try {
					count = sysDecisionYdjService.queryCountDesc(map);
				} catch (CoreException e) {
					e.printStackTrace();
				}
				if (count > 0) {
					try {
						list = sysDecisionYdjService.queryDescByAppId(map,curNum,pageNum);
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
			Map<String, Object> dataMap = new HashMap<String, Object>();
			if (list == null || list.size() == 0) {
				logger.info("msg", "查询结果为空，没有检测到对应的信息");
				return;
			}
			
			dataMap.put("total", count);
			dataMap.put("rows", list);
			ctx.setData("isSuccess", true);
			ctx.setDataMap(dataMap);
		} catch (Exception e) {
			logger.info("无决策描述，请检测");
			e.printStackTrace();
		}
		
		
	}
	/*public void queryDescByAppID(Context ctx) throws Exception{
		try {
			
			int curNum = 0;
			int curPage = Integer
					.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
			int pageNum = Integer
					.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			Map map = ctx.getDataMap();
			String appId = (String) map.get("appId");
			Assert.notNull(appId, "请求申请件编号为空!");
			AllotApplyAllot allot = sysButtonCommonService.queryAllotApplyAllot(appId);
			String curNode = allot.getCurrNode();
			String ydjFlag = allot.getYdjFlag();
			int count = 0;
			List list = new ArrayList();
			if(curNode.equals("03")||curNode=="03"){
				if(ydjFlag.equals("1")||ydjFlag=="1"){
					map.put("requesttype", "YDJ1005");
					try {
						count = sysDecisionYdjService.queryCountDesc(map);
					} catch (CoreException e) {
						e.printStackTrace();
					}
					if (count > 0) {
						try {
							 list = sysDecisionYdjService.queryDescByAppId(map,curNum,pageNum);
						} catch (CoreException e) {
							e.printStackTrace();
						}
					}
				}else{
					map.put("requesttype", "SD1005");
					try {
						count = sysDecisionYdjService.queryCountDesc(map);
					} catch (CoreException e) {
						e.printStackTrace();
					}
					if (count > 0) {
						try {
							 list = sysDecisionYdjService.queryDescByAppId(map,curNum,pageNum);
						} catch (CoreException e) {
							e.printStackTrace();
						}
					}
				}
			}else{
				if(ydjFlag.equals("1")){
					map.put("requesttype", "YDJ1004");
					try {
						count = sysDecisionYdjService.queryCountDesc(map);
					} catch (CoreException e) {
						e.printStackTrace();
					}
					if (count > 0) {
						try {
							 list = sysDecisionYdjService.queryDescByAppId(map,curNum,pageNum);
						} catch (CoreException e) {
							e.printStackTrace();
						}
					}
				}else{
					map.put("requesttype", "SD1004");
					try {
						count = sysDecisionYdjService.queryCountDesc(map);
					} catch (CoreException e) {
						e.printStackTrace();
					}
					if (count > 0) {
						try {
							 list = sysDecisionYdjService.queryDescByAppId(map,curNum,pageNum);
						} catch (CoreException e) {
							e.printStackTrace();
						}
					}
				}
			}

			Map<String, Object> dataMap = new HashMap<String, Object>();
			if (list == null || list.size() == 0) {
				logger.info("msg", "查询结果为空，没有检测到对应的信息");
				return;
			}
			
			dataMap.put("total", count);
			dataMap.put("rows", list);
			ctx.setData("isSuccess", true);
			ctx.setDataMap(dataMap);
		} catch (Exception e) {
			logger.info("无决策描述，请检测");
			e.printStackTrace();
		}
		
		
	}*/
	public void queryCustInfoByAppID(Context ctx) throws Exception {
		Map map = ctx.getDataMap();
		String appId = (String) map.get("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		OpasCustBaseInfo custInfo = sysDecisionYdjService.selectCustInfoByAppId(appId);
		Assert.notNull(custInfo, "请求申请件实体为空!");
		Map<String, String> resMap = sysDecisionYdjService.queryTeam(appId);
		if (resMap == null) {
			resMap = new HashMap<String, String>();
		}
		// 存量客户电销名单特殊处理
		String autoio_dx = resMap.get("OPAS_STOCKCUST_TELSALE_LIST-CERTIFI_NO");
		//String currstatus = resMap.get("OPAS_STOCKCUST_TELSALE_LIST-CURR_STATUS");
		if (autoio_dx != null) {
			custInfo.setStockcustTelsale(autoio_dx);
		} else {
			custInfo.setStockcustTelsale("0");
		}

		String autoid1 = resMap.get("OPAS_GOODCOMPANY_LIST-PROJECT_CODE");
		String autoid2 = resMap.get("OPAS_GOODCOMPANY_LIST-COMPANY_NAME");
		String autoid = autoid1 != null ? autoid1 : autoid2;
		String autofinal = "0";
		if (autoid != null) {
			autofinal = "1";
		}
		custInfo.setTsProjectMindan(resMap.get("OPAS_SPECIALPROJECT_LIST-PROJECT_CODE") == null ? "0" : "1");// 特殊项目名单
		custInfo.setBusinessTuanbanMindan(resMap.get("OPAS_MERCH_TEAMDEAL_LIST-TEAMDEAL_NO") == null ? "0" : "1");// 商户团办名单
		custInfo.setGoodCompanyMindan(autofinal);// 优质企业名单表
		custInfo.setImportSchoolMindan(resMap.get("OPAS_MAJORSCHOOL_LIST-MAJORSCHOOL_NAME") == null ? "0" : "1");// 重点院校名单表
		custInfo.setCompanyMindan(resMap.get("OPAS_TARGETCOMPANY_LIST-COMPANY_NAME") == null ? "0" : "1");// 目标企业名单表
		custInfo.setTeamnoIsmatch(resMap.get("OPAS_TEAMDEAL_LIST-TEAMDEAL_CODE") == null ? "0" : "1");// 易达金团办名单表
		String jsoncustInfo = "{\"\":\"\"}";
		if (null != custInfo) {
			jsoncustInfo = JSON.toJSONString(custInfo);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("客户基本信息[" + jsoncustInfo + "]");
		}
		/** 主卡决策结果 附属卡决策结果 参考数据 **/
		FicoYdjBlaze sysResultInfo = (FicoYdjBlaze)sysDecisionYdjService.selectSystemDecisionByAppId(appId, "1");
		String jsonsysInfo = "{\"\":\"\"}";
		if (null != sysResultInfo) {
			sysResultInfo.setAppId(null); // 设置为NULL，因为这5个实体类都含有，生成JSON后在前台LOAD时会有冲突
			jsonsysInfo = JSON.toJSONString(sysResultInfo);
			Date date = sysResultInfo.getCrtTime();
			if(date!=null){
				String crtTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
				ctx.setData("crtTime", crtTime.trim());
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("主卡决策结果[" + jsonsysInfo + "]");
		}
		/** 内部数据检查 **/
		//OpaInnerDataCheck innerDataCheck = sysDecisionYdjService.selectInnerDataByAppId(appId);
		OpaInnerDataCheck innerDataCheck  = selectInnerDataByAppId(resMap);
		String jsoninnerDataCheck = "{\"\":\"\"}";
		if (null != innerDataCheck) {
			innerDataCheck.setAppId(null);
			innerDataCheck.setAutoId(null);
			innerDataCheck.setCrtDate(null);
			innerDataCheck.setCrtUser(null);
			jsoninnerDataCheck = JSON.toJSONString(innerDataCheck);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("内部数据检查[" + jsoninnerDataCheck + "]");
		}
		/** 风险信息检查 **/
		//OpaRiskInfoCheck riskInfoCheck = sysDecisionYdjService.selectRiskInfoByAppId(appId,custInfo.getCertifiNo());
		OpaRiskInfoCheck riskInfoCheck = selectRiskInfoByAppId(appId,custInfo.getCertifiNo(),resMap);
		String jsonriskInfoCheck = "{\"\":\"\"}";
		if (null != riskInfoCheck) {
			riskInfoCheck.setAppId(null);
			riskInfoCheck.setAutoId(null);
			riskInfoCheck.setCrtDate(null);
			riskInfoCheck.setCrtUser(null);
			jsonriskInfoCheck = JSON.toJSONString(riskInfoCheck);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("风险信息检查[" + jsonriskInfoCheck + "]");
		}
		//反欺诈
		Map<String, Object> dataMap = sysDecisionYdjService.selectFqzRstDespByAppId(appId);
		/** 数据查询结束，拼接JSOn **/
		String jsonStr = jsoncustInfo.substring(0, jsoncustInfo.length() - 1) + ","
				+ jsonriskInfoCheck.substring(1, jsonriskInfoCheck.length() - 1) + ","
				+ jsonsysInfo.substring(1, jsonsysInfo.length() - 1) + ","
				+ jsoninnerDataCheck.substring(1, jsoninnerDataCheck.length());
		ctx.setData("jsonStr", jsonStr.trim());
		ctx.setData("dataMap", dataMap);
	}
	/**
	 * 内部数据检测
	 * 
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public OpaInnerDataCheck selectInnerDataByAppId(Map<String, String> jsonMap)
			throws Exception {
		OpaInnerDataCheck inner = new OpaInnerDataCheck();
		// 获得匹配结果
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
		String autoid66 = jsonMap.get("OPAS_INNER_RISKINFO_LIST-CERTIFI_NO");//身份证
		String autoidLv = jsonMap.get("OPAS_INNER_RISKINFO_LIST-COMPANY_LEVEL");//新审批二期 名单库
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
		if(autoid66!=null && !"".equals(autoid66)){
			ishaveInriskInfo = autoid66;
			num = num + "6";
		}
		if(autoidLv!=null && !"".equals(autoidLv)){
			ishaveInriskInfo = autoidLv;
			num = num + "7";
		}
		/*if(ishaveInriskInfoMap != null){
			ishaveInriskInfo = (String) ishaveInriskInfoMap.get("res");
			num = (int) ishaveInriskInfoMap.get("num");
		}*/
		//String ishaveInriskInfo = jsonMap.get("OPAS_INNER_RISKINFO_LIST-InnerRiskInfo");
		if (ishaveInriskInfo == null) {
			inner.setIshaveInriskInfo("0");
		}else if(autoidLv!=null&&!"".equals(autoidLv)){//先验证是否包含名单库风险等级
			inner.setIshaveInriskInfo("是"+autoidLv);
			inner.setIshaveInriskInfo_column(num);
		} else {//不包含风险等级 原来逻辑
			inner.setIshaveInriskInfo(ishaveInriskInfo);
			inner.setIshaveInriskInfo_column(num);
		}
		// 国家/证件信息与税收居民逻辑检查
		String c6ResiTaxid = jsonMap.get("OPAS_BIZ_INP_APPL_C1-C6_RESI_TAXID1"); 
		if (c6ResiTaxid == null){
			inner.setC6ResiTaxid("4");
		}else{
			inner.setC6ResiTaxid(c6ResiTaxid);
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
	public OpaRiskInfoCheck selectRiskInfoByAppId(String appId,String certifiNo,Map<String, String> jsonMap)
			throws Exception {
		OpaRiskInfoCheck opaRiskInfoCheck = new OpaRiskInfoCheck();
		// 获得匹配结果
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
		List<Map<String, Object>> mylist = antService.selectAntRiskOrder(paramMap);
		if(mylist==null || mylist.size() == 0){
			opaRiskInfoCheck.setAntRisklist("0");
		}else{
			opaRiskInfoCheck.setAntRisklist("1");
		}
		// 百融风险名单
		List<Map<String, Object>> brlist1 = antService.selectBrRistOrder1(paramMap);
		List<Map<String, Object>> brlist2 = antService.selectBrRistOrder2(paramMap);
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
		String AML2 = jsonMap.get("FOREIGN_INDEX-AML2");
		if(AML2==null || "".equals(AML2)){
			opaRiskInfoCheck.setWashmoneyRisklist2("0");
		}else{
			opaRiskInfoCheck.setWashmoneyRisklist2("1");
		}
		// 客户洗钱风险等级
		String custwashmoneyRisklevel = jsonMap.get("FOREIGN_INDEX-LEVEL");
		if(custwashmoneyRisklevel==null || "".equals(custwashmoneyRisklevel)){
			opaRiskInfoCheck.setCustwashmoneyRisklevel("-1");//否
		}else{
			opaRiskInfoCheck.setCustwashmoneyRisklevel(custwashmoneyRisklevel.trim());
		}
		String custwashmoneyRisklevel2 = jsonMap.get("FOREIGN_INDEX-LEVEL2");
		if(custwashmoneyRisklevel2==null || "".equals(custwashmoneyRisklevel2)){
			opaRiskInfoCheck.setCustwashmoneyRisklevel2("-1");//否
		}else{
			opaRiskInfoCheck.setCustwashmoneyRisklevel2(custwashmoneyRisklevel2.trim());
		}
		return opaRiskInfoCheck;
	}
	/**
	 * 拆分系统决策页面查询
	 * 
	 * @param ctx
	 * @throws CoreException
	 */
	public void queryCustInfoMesByAppID(Context ctx) throws Exception {
		Map map = ctx.getDataMap();
		String appId = (String) map.get("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		OpasCustBaseInfo custInfo = sysDecisionYdjService.selectCustInfoByAppId(appId);
		Assert.notNull(custInfo, "请求申请件实体为空!");
		Map<String, String> resMap = sysDecisionYdjService.queryTeam(appId);
		if (resMap == null) {
			resMap = new HashMap<String, String>();
		}
		// 存量客户电销名单特殊处理
		String autoio_dx = resMap.get("OPAS_STOCKCUST_TELSALE_LIST-CERTIFI_NO");
		//String currstatus = resMap.get("OPAS_STOCKCUST_TELSALE_LIST-CURR_STATUS");
		if (autoio_dx != null) {
			custInfo.setStockcustTelsale(autoio_dx);
		} else {
			custInfo.setStockcustTelsale("0");
		}

		String autoid1 = resMap.get("OPAS_GOODCOMPANY_LIST-PROJECT_CODE");
		String autoid2 = resMap.get("OPAS_GOODCOMPANY_LIST-COMPANY_NAME");
		String autoid = autoid1 != null ? autoid1 : autoid2;
		String autofinal = "0";
		if (autoid != null) {
			autofinal = "1";
		}
		custInfo.setTsProjectMindan(resMap.get("OPAS_SPECIALPROJECT_LIST-PROJECT_CODE") == null ? "0" : "1");// 特殊项目名单
		custInfo.setBusinessTuanbanMindan(resMap.get("OPAS_MERCH_TEAMDEAL_LIST-TEAMDEAL_NO") == null ? "0" : "1");// 商户团办名单
		custInfo.setGoodCompanyMindan(autofinal);// 优质企业名单表
		custInfo.setImportSchoolMindan(resMap.get("OPAS_MAJORSCHOOL_LIST-MAJORSCHOOL_NAME") == null ? "0" : "1");// 重点院校名单表
		custInfo.setCompanyMindan(resMap.get("OPAS_TARGETCOMPANY_LIST-COMPANY_NAME") == null ? "0" : "1");// 目标企业名单表
		custInfo.setTeamnoIsmatch(resMap.get("OPAS_TEAMDEAL_LIST-TEAMDEAL_CODE") == null ? "0" : "1");// 易达金团办名单表
		String jsoncustInfo = "{\"\":\"\"}";
		if (null != custInfo) {
			jsoncustInfo = JSON.toJSONString(custInfo);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("客户基本信息[" + jsoncustInfo + "]");
		}
		/** 风险信息检查 **/
		OpaRiskInfoCheck riskInfoCheck = sysDecisionYdjService.selectRiskInfoByAppId(appId,custInfo.getCertifiNo());
		String jsonriskInfoCheck = "{\"\":\"\"}";
		if (null != riskInfoCheck) {
			riskInfoCheck.setAppId(null);
			riskInfoCheck.setAutoId(null);
			riskInfoCheck.setCrtDate(null);
			riskInfoCheck.setCrtUser(null);
			jsonriskInfoCheck = JSON.toJSONString(riskInfoCheck);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("风险信息检查[" + jsonriskInfoCheck + "]");
		}
		//反欺诈
		Map<String, Object> dataMap = sysDecisionYdjService.selectFqzRstDespByAppId(appId);
		/** 数据查询结束，拼接JSOn **/
		String jsonStr = jsoncustInfo.substring(0, jsoncustInfo.length() - 1) + ","
				+ jsonriskInfoCheck.substring(1, jsonriskInfoCheck.length()) ;
		ctx.setData("jsonStr", jsonStr.trim());
		ctx.setData("dataMap", dataMap);
	}
	public void querySysResultInfoByAppID(Context ctx) throws Exception {
		Map map = ctx.getDataMap();
		String appId = (String) map.get("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		/** 主卡决策结果 附属卡决策结果 参考数据 **/
		FicoYdjBlaze sysResultInfo = (FicoYdjBlaze)sysDecisionYdjService.selectSystemDecisionByAppId(appId, "1");
		String jsonsysInfo = "{\"\":\"\"}";
		if (null != sysResultInfo) {
			sysResultInfo.setAppId(null); // 设置为NULL，因为这5个实体类都含有，生成JSON后在前台LOAD时会有冲突
			jsonsysInfo = JSON.toJSONString(sysResultInfo);
			Date date = sysResultInfo.getCrtTime();
			if(date!=null){
				String crtTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
				ctx.setData("crtTime", crtTime.trim());
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("主卡决策结果[" + jsonsysInfo + "]");
		}
		ctx.setData("jsonsysInfo", jsonsysInfo.trim());
	}
	public void queryInnerDataCheckByAppID(Context ctx) throws Exception {
		Map map = ctx.getDataMap();
		String appId = (String) map.get("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		/** 内部数据检查 **/
		OpaInnerDataCheck innerDataCheck = sysDecisionYdjService.selectInnerDataByAppId(appId);
		String jsoninnerDataCheck = "{\"\":\"\"}";
		if (null != innerDataCheck) {
			innerDataCheck.setAppId(null);
			innerDataCheck.setAutoId(null);
			innerDataCheck.setCrtDate(null);
			innerDataCheck.setCrtUser(null);
			jsoninnerDataCheck = JSON.toJSONString(innerDataCheck);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("内部数据检查[" + jsoninnerDataCheck + "]");
		}
		ctx.setData("jsoninnerDataCheck", jsoninnerDataCheck.trim());
	}
	/**
	 * 是否已持卡客户
	 * 
	 * @param ctx
	 * @throws CoreException
	 */
	@SuppressWarnings("rawtypes")
	public void queryHaveCardCustInfoYdjByAppID(Context ctx) throws CoreException {
		try {
			Map map = ctx.getDataMap();
			String appId = (String) map.get("appId");
			OpasHaveCardCustInfo haveCardInfo = sysDecisionYdjService.selectHaveCardInfoByAppId(appId);
			String haveCardInfoJson = JSON.toJSONString(haveCardInfo);
			ctx.setData("haveCardInfo", haveCardInfoJson);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据申请编号查询主卡进件信息
	 * 
	 * @throws Exception
	 */
	public void querySystemCreditZJByAppId(Context context) {
		try {
			// 获取申请编号
			String appId = StringUtils.trimToEmpty((String) context.getDataMap().get("appId"));
			// 查询主卡进件信息
			BizInpApplC1 appl = sysDecisionYdjService.selectBizInpApplC1ByAppId(appId);
			//生日
			Date birth = appl.getC1Birth(); 
			String sbirth = new SimpleDateFormat("yyyy-MM-dd").format(birth);
			//id有效期
			Date idDate = appl.getC5Idte1();
			String c5Idte1 = new SimpleDateFormat("yyyy-MM-dd").format(idDate);
			// 查询人行摘要信息
			Map<String, String> pboc = sysDecisionYdjService.queryPbocInfo(appId);
			// 查询二代人行摘要信息
			Map<String, String> bank = sysDecisionYdjService.queryBankInfo(appId);
			// 获得验证信息
			Map<String, String> yanzhenMap = sysDecisionYdjService.queryTeam(appId);
			if (yanzhenMap == null) {
				yanzhenMap = new HashMap<String, String>();
			}
			Map<String, String> yanzhenResMap = new HashMap<String, String>();
			yanzhenResMap.put("credit_COMPANY_NAME",
					yanzhenMap.get("OPAS_CREDIT_TELCHECK_LIST-COMPANY_NAME") == null ? "0" : "1");// 征信电话核查白名单表
			yanzhenResMap.put("credit_COMPANY_TEL",
					yanzhenMap.get("OPAS_CREDIT_TELCHECK_LIST-COMPANY_TEL") == null ? "0" : "1");// 征信电话核查白名单表

			yanzhenResMap.put("crm_MOBILEPHONE",
					yanzhenMap.get("SP_APS_IFS_CUST_INFO-MOBILEPHONE-MOBILEPHONE") == null ? "0" : "1");// CRM
																										// 客户基本信息(审批)--手机号码
			yanzhenResMap.put("crm_BUSPHONE", yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSPHONE") == null ? "0" : "1");// --单位电话
			yanzhenResMap.put("crm_PHONE", yanzhenMap.get("SP_APS_IFS_CUST_INFO-PHONE") == null ? "0" : "1");// --住宅电话
			yanzhenResMap.put("crm_COMPANY", yanzhenMap.get("SP_APS_IFS_CUST_INFO-COMPANY") == null ? "0" : "1");// --单位名称
			yanzhenResMap.put("crm_BUSADDR", yanzhenMap.get("SP_APS_IFS_CUST_INFO-BUSADDR") == null ? "0" : "1");// --单位地址
			yanzhenResMap.put("crm_HOMEADDR", yanzhenMap.get("SP_APS_IFS_CUST_INFO-HOMEADDR") == null ? "0" : "1");// --账单地址(住在地址)
			// 人行比对结果
			yanzhenResMap.put("pboc_CELL_PHONE",
					yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-CELL_PHONE") == null ? "0" : "1");// --手机号码
			yanzhenResMap.put("pboc_C_COMP_PHONE",
					yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-C_COMP_PHONE") == null ? "0" : "1");// --单位电话
			yanzhenResMap.put("pboc_COMPANY", yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMPANY") == null ? "0" : "1");// --单位名称

			yanzhenResMap.put("pboc_RESI_TEL", yanzhenMap.get("OPAS_PBOC_PERSONAL_INFO-RESI_TEL") == null ? "0" : "1");// --住宅电话

			yanzhenResMap.put("pboc_COMP_ADDR",
					yanzhenMap.get("OPAS_PBOC_PROFESSION_INFO-COMP_ADDR") == null ? "0" : "1");// --单位地址
			yanzhenResMap.put("pboc_RESIDENT_ADDR",
					yanzhenMap.get("OPAS_PBOC_RESIDENCE_INFO-RESIDENT_ADDR") == null ? "0" : "1");// --账单地址(住在地址)
			if (appl != null) {
				context.setData("sbirth", sbirth);
				context.setData("c5Idte1", c5Idte1);
				context.setData("appl", appl);
				context.setData("pboc", pboc);
				context.setData("bank", bank);
				context.setData("yanzhenResMap", yanzhenResMap);
			} else {
				logger.error("【主卡进件信息为空，请检测】");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @Title:findZxCreditInvestigationYdjByAppId
	 * @Description:征信大页面中 征信调查页面 数据返显(易达金)
	 * @param context
	 * @author: gaohui
	 * @Date:2017年4月27日下午1:44:42
	 */
	public void findZxCreditInvestigationYdjByAppId(Context context) {
		try {
			Map map = context.getDataMap();
			Map<String, Object> htmlDataMap = sysDecisionYdjService.findZxCreditInvestigationYdjByAppId(map);

			if (htmlDataMap != null) {
				context.setData("appl", htmlDataMap.get("appl"));
				context.setData("pboc", htmlDataMap.get("pboc"));
				context.setData("bank", htmlDataMap.get("bank"));
				context.setData("result", htmlDataMap.get("result"));
				context.setData("ydjresult", htmlDataMap.get("ydjresult"));
				context.setData("yanzhenResMap", htmlDataMap.get("yanzhenResMap"));
				context.setData("yanzhenMesShowMap", htmlDataMap.get("yanzhenMesShowMap"));
				context.setData("policeData", htmlDataMap.get("policeData"));
				context.setData("faceRes", htmlDataMap.get("faceRes"));
				context.setData("c1PoliceStatus", htmlDataMap.get("c1PoliceStatus"));
				context.setData("c2PoliceStatus", htmlDataMap.get("c2PoliceStatus"));
			} else {
				logger.error("【主卡进件信息为空，请检测】");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 *@Title:findZxCreditInvestigationBzkByAppId
	 *@Description:征信大页面中 征信调查页面 数据返显(标准卡)
	 *@param context
	 *@author: gaohui
	 *@Date:2017年5月2日下午7:12:20
	 */
	public void findZxCreditInvestigationBzkByAppId(Context context) {
		try {
			Map map = context.getDataMap();
			Map<String, Object> htmlDataMap = sysDecisionYdjService.findZxCreditInvestigationBzkByAppId(map);
			if (htmlDataMap != null) {
				context.setData("appl", htmlDataMap.get("appl"));
				context.setData("pboc", htmlDataMap.get("pboc"));
				context.setData("bank", htmlDataMap.get("bank"));
				context.setData("result", htmlDataMap.get("result"));
				context.setData("bzkresult", htmlDataMap.get("bzkresult"));
				context.setData("yanzhenResMap", htmlDataMap.get("yanzhenResMap"));
				context.setData("yanzhenMesShowMap", htmlDataMap.get("yanzhenMesShowMap"));
				context.setData("faceRes", htmlDataMap.get("faceRes"));
				context.setData("c1PoliceStatus", htmlDataMap.get("c1PoliceStatus"));
				context.setData("c2PoliceStatus", htmlDataMap.get("c2PoliceStatus"));
				context.setData("conclusion", htmlDataMap.get("conclusion"));
				context.setData("svoiceMark", htmlDataMap.get("svoiceMark"));
				context.setData("svoiceBackFlag", htmlDataMap.get("svoiceBackFlag"));	
				context.setData("outsourcingGroup", htmlDataMap.get("outsourcingGroup"));
				context.setData("authResult", htmlDataMap.get("authResult"));
			} else {
				logger.error("【主卡进件信息为空，请检测】");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * save征信电核注记信息
	 */
	public void surveyJiYaoSave(Context ctx) {
		try {
			// 获得参数
			String userId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("userId"));
			Map<String, Object> context = ctx.getDataMap();
			Map<String, String> objMap = (Map<String, String>) context.get("diaochaJiYaoData");
			String flag = objMap.get("flag");// 判读该操作是增、删、还是改
			String appId = objMap.get("appId");
			if (!"delete".equals(flag)) {// insert update
				TelcheckAddnote note = new TelcheckAddnote();
				note.setAutoId(StringUtil.randomUUIDPlainString());
				note.setAppId(appId);
				note.setTelSource(objMap.get("telSource"));
				note.setTelType(objMap.get("telType"));
				note.setTelNo(objMap.get("telNo"));
				note.setNoteObject(objMap.get("noteObject"));
				note.setTelcheckResult(objMap.get("telcheckReuslt"));
				note.setMemo(objMap.get("memo"));
				// note.setDecDate(objMap.get(""));
				/*
				 * note.setApproveResult(objMap.get(""));
				 * note.setPolicyCode(objMap.get(""));
				 * note.setViolationCode(objMap.get(""));
				 * note.setRefuseCode(objMap.get(""));
				 * note.setIndustryCode(objMap.get(""));
				 * note.setCrediter(objMap.get(""));
				 * note.setApprover(objMap.get(""));
				 * note.setBirthdate(objMap.get(""));
				 * note.setIdExpireDate(objMap.get(""));
				 */
				note.setCrtDate(new Date());
				note.setCrtUser(userId);
				sysDecisionYdjService.saveTelcheckAddnote(note);
				ctx.setData("msg", "更新成功");
			} else {// delete
				sysDecisionYdjService.deleteTelcheckAddnote(appId);
				ctx.setData("msg", "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("msg", "更新失败");
		}
	}

	/**
	 * 删除征信纪要
	 */
	public void surveyJiYaoDelete(Context ctx) {
		try {
			String appId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("appId"));
			sysDecisionYdjService.deleteTelcheckAddnote(appId);
			ctx.setData("msg", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("msg", "删除失败");
		}
	}

	/**
	 * 征信电话核查白名单 查询
	 */
	public void creditTelCheckSelect(Context ctx) {
		try {
			String companyName = StringUtils.trimToEmpty((String) ctx.getDataMap().get("companyName"));
			String companyTel = StringUtils.trimToEmpty((String) ctx.getDataMap().get("companyTel"));

			int curNum = 0;
			int curPage = Integer
					.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
			int pageNum = Integer
					.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("companyName", companyName);
			map.put("companyTel", companyTel);
			int count = sysDecisionYdjService.queryCreditTelcheckListCount(map);
			List<CreditTelcheckList> list = new ArrayList<CreditTelcheckList>();
			if (count > 0) {
				list = sysDecisionYdjService.queryCreditTelcheckList(map, curNum, pageNum);
			}
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("total", count);
			dataMap.put("rows", list);
			ctx.setDataMap(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 征信电话核查白名单YDJ 是 匹配上了 查询
	 */
	public void queryYDJCreditTelCheck(Context ctx) {
		try {
			String autoId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("autoId"));
	
			int curNum = 0;
			int curPage = Integer
					.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
			int pageNum = Integer
					.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			String [] autoIds = null;
			if (!"".equals(autoId)){
				autoIds = autoId.split("\\|");
				List<Map<String,Object>> listAutoIds=new ArrayList<Map<String,Object>>();
				for (String string : autoIds) {
					Map<String,Object> autoIdMap=new HashMap<String,Object>();
					autoIdMap.put("autoId", string);
					listAutoIds.add(autoIdMap);
				}
				map.put("listAutoIds", listAutoIds);
				
			}
			map.put("autoId", autoId);
			map.put("curNum", curNum);
			map.put("pageNum", pageNum);
			Map<String, Object> dataMap = sysDecisionYdjService.queryYDJCreditTelcheckMap(map);
			ctx.setDataMap(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 征信电话核查白名单bzk 是 匹配上了 查询
	 */
	public void queryBZKCreditTelCheck(Context ctx) {
		try {
			String autoId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("autoId"));

			int curNum = 0;
			int curPage = Integer
					.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
			int pageNum = Integer
					.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
			if (curPage > 1) {
				curNum = (curPage - 1) * pageNum;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			String [] autoIds = null;
			if (!"".equals(autoId)){
				autoIds = autoId.split("\\|");
				List<Map<String,Object>> listAutoIds=new ArrayList<Map<String,Object>>();
				for (String string : autoIds) {
					Map<String,Object> autoIdMap=new HashMap<String,Object>();
					autoIdMap.put("autoId", string);
					listAutoIds.add(autoIdMap);
				}
				map.put("listAutoIds", listAutoIds);
			}
			map.put("autoId", autoId);
			//currStatus 启用状态
			map.put("currStatus","1");
			map.put("curNum", curNum);
			map.put("pageNum", pageNum);
			Map<String, Object> dataMap = sysDecisionYdjService.queryBZKCreditTelcheckMap(map);
			ctx.setDataMap(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 征信电话核查白名单 添加   改：gaohui 20170505  createDate=createDate.replaceAll("-", "");
	 */
	public void creditTelCheckSave(Context ctx) {
		try {
			Map<String, String> context = (Map<String, String>) ctx.getDataMap().get("zhenXinKuData");
			String createDate = context.get("createDate");
			createDate=createDate.replaceAll("-", "");
			CreditTelcheckList credit = new CreditTelcheckList();
			credit.setAutoId(StringUtil.randomUUIDPlainString());
			credit.setAppId(context.get("appId"));
			credit.setMemo(context.get("memo"));
			credit.setCompanyName(context.get("companyName"));
			credit.setCompanyDept(context.get("companyDept"));
			credit.setCompanyTel(context.get("companyTel"));
			credit.setTelSource(context.get("telSource"));
			credit.setCreateDate(createDate);
			credit.setCreateTime(new Date());
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
			//Date format = sdf.parse(createDate);
			credit.setOperatTime(new Date());
			credit.setCurrStatus("1");// 默认为启用
			credit.setOperator(context.get("operator"));
			/*
			Date time = sdf.parse(createDate);
			credit.setOperatTime(time);*/
			sysDecisionYdjService.insertCreditTelcheckList(credit);
			ctx.setData("msg", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("msg", "添加失败");
		}
	}

	/**
	 * 保存易达金主卡决策内容
	 */
	public void saveBizInpApplC1(Context ctx) {
		try {
			Map<String, String> context = (Map<String, String>) ctx.getDataMap().get("bizInpApplC1Data");
			Map<String, String> oldMap = (Map<String, String>) ctx.getDataMap().get("objParam");
			String userId = StringUtils.trimToEmpty((String) ctx.getDataMap().get("userId"));
			String appId = context.get("appId");
			// 获得被修改的字段
			List<String> list = CommonUtil.mapCompareToMapStr(context, oldMap);
			// 获取申请编号
			String insideAppNo = context.get("insideAppNo");
			if (insideAppNo == null || "".equals(insideAppNo)) {
				logger.info("缺失主卡进件信息表的主键，信息缺失，原因：web前段数据没有传到Action层，请检测代码和数据");
				ctx.setData("msg", "缺失主卡进件信息表的主键，信息缺失，系统异常");
				return;
			}

			BizInpApplC1 biz = new BizInpApplC1();
			biz.setInsideAppNo(insideAppNo);
			Class cls = Class.forName(BizInpApplC1.class.getName());
			List<ApplyModifyLog> logList = new ArrayList<ApplyModifyLog>();

			ApplyRemark applyRemark = null;
			for (String str : list) {
				// 若，这个字段是全局备注字段qjRemark,则该字段存于OPAS_ALLOT_APPLY_REMARK表中
				// 申请件全局信息备注
				if ("qjRemark".equals(str)) {
					applyRemark = new ApplyRemark();
					applyRemark.setAppId(appId);
					applyRemark.setRemarkId(StringUtil.randomGUIDPlainString());
					applyRemark.setRemarkInfo(context.get("qjRemark").length() > 1024
							? context.get("qjRemark").substring(0, 1024) : context.get("qjRemark"));
					applyRemark.setRemarkTime(new Date());
					applyRemark.setRemarkUser(userId);
					applyRemarkService.insertSelective(applyRemark);
					continue;
				}
				// 将修改的主进卡字段的值赋值,参数类型不是String的
				if ("c1Hmare".equals(str)) {
					biz.setC1Hmare(context.get("c1Hmare"));
				} else if ("c1Coyr".equals(str)) {
					biz.setC1Coyr(Integer.parseInt(context.get("c1Coyr")));
				} else if ("c1Earn".equals(str)) {
					biz.setC1Earn(new BigDecimal(context.get("c1Earn")));
				} else {// 将修改的主进卡字段的值赋值,参数类型为String的
					String param = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
					Method me = cls.getMethod("set" + param, String.class);
					me.invoke(biz, context.get(str));
				}
				// 记录修改日志
				ApplyModifyLog applyModifyLog = new ApplyModifyLog();
				applyModifyLog.setAutoId(StringUtil.randomGUIDPlainString());
				applyModifyLog.setAppId(appId);
				applyModifyLog.setFieldName(str);
				applyModifyLog.setFieldOldValue(oldMap.get(str));
				applyModifyLog.setFieldNewValue(context.get(str));
				applyModifyLog.setIsKeyField("0");// 非关键字
				applyModifyLog.setCrtDate(new Date());
				applyModifyLog.setCrtUser(userId);
				applyModifyLog.setLstUpdDate(new Date());
				applyModifyLog.setLstUpdUser(userId);
				logList.add(applyModifyLog);
			}
			sysDecisionYdjService.updateBizInpApplC1ByKey(biz, logList, applyRemark);
			ctx.setData("msg", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setData("msg", "保存失败");
		}
	}

}
