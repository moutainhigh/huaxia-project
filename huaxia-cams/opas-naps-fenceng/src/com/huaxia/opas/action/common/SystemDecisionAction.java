package com.huaxia.opas.action.common;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.common.CacheConsts;
import com.huaxia.opas.domain.decision.JDCardInfo;
import com.huaxia.opas.domain.decision.OpaCheckinerResultInfo;
import com.huaxia.opas.domain.decision.OpaInnerDataCheck;
import com.huaxia.opas.domain.decision.OpaRiskInfoCheck;
import com.huaxia.opas.domain.decision.OpasCustBaseInfo;
import com.huaxia.opas.domain.input.FicoSdBlaze;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.service.decision.SysApprovalCommonService;
import com.huaxia.opas.service.decision.SysDecisionService;
import com.huaxia.opas.service.decision.SysDecisionYdjService;
import com.huaxia.opas.service.thirdparty.AntService;
import com.huaxia.opas.util.CommonUtil;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * 
 * @author 刘志辉 系统决策结果tab页的展示 2017-3-7 14:59:27
 */
public class SystemDecisionAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(SystemDecisionAction.class);
	@Resource(name = "sysDecisionService")
	private SysDecisionService sysDecisionService;
	@Resource(name = "sysDecisionYdjService")
	private SysDecisionYdjService sysDecisionYdjService;
	@Resource(name = "sysApprovalCommonService")
	private SysApprovalCommonService sysApprovalCommonService;
	@Resource(name = "antService")
	private AntService antService;
	
	public void queryCustInfoByAppID(Context ctx) throws Exception {
		Map map=ctx.getDataMap();
		String appId=(String) map.get("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		
		Map<String,String> resMap=null;//结果对比 map
		try {
			 resMap = sysDecisionYdjService.queryTeam(appId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(resMap==null){
			resMap = new HashMap<String,String>();
		}
		
		/**客户基本信息   客户标签   政策入组规则提示 手机实名制**/
		OpasCustBaseInfo custInfo = sysDecisionService.selectCustInfoByAppId(appId);
		Assert.notNull(custInfo, "请求申请件实体为空!");
		String jsoncustInfo = "{\"\":\"\"}";
		if(null!=custInfo){
			custInfo.setTeamnoIsmatch(resMap.get("OPAS_TEAMLIST-TEAM_ID")==null?"0":"1");//团办名单表
			jsoncustInfo=JSON.toJSONString(custInfo);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("客户基本信息[" + jsoncustInfo + "]");
		}
		/**主卡决策结果   附属卡决策结果   参考数据OPAS_FICO_SD_BLAZE**/
		FicoSdBlaze sysResultInfo = (FicoSdBlaze)sysDecisionYdjService.selectSystemDecisionByAppId(appId, "2");
		String jsonsysInfo="{\"\":\"\"}";
		if(null!=sysResultInfo){
			sysResultInfo.setAppId(null);  //设置为NULL，因为这5个实体类都含有，生成JSON后在前台LOAD时会有冲突
			jsonsysInfo=JSON.toJSONString(sysResultInfo);
			Date date = sysResultInfo.getCrtTime();
			if(date!=null){
				String ctrStr = (new SimpleDateFormat("yyyy-MM-dd")).format(date);
				ctx.setData("ctrStr", ctrStr);
			}
			Date fqzRstTime = sysResultInfo.getFqzRstTime();
			if(fqzRstTime!=null){
				String fqzRstDate = (new SimpleDateFormat("yyyy-MM-dd")).format(fqzRstTime);
				ctx.setData("fqzRstDate", fqzRstDate);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("主卡决策结果[" + jsonsysInfo + "]");
		}
		/**内部数据检查**/
		//OpaInnerDataCheck innerDataCheck  = sysDecisionYdjService.selectInnerDataByAppId(appId);
		OpaInnerDataCheck innerDataCheck  = selectInnerDataByAppId(resMap);
		String jsoninnerDataCheck="{\"\":\"\"}";
		if(null!=innerDataCheck){
			innerDataCheck.setAppId(null);
			innerDataCheck.setAutoId(null);
			innerDataCheck.setCrtDate(null);
			innerDataCheck.setCrtUser(null);
			jsoninnerDataCheck=JSON.toJSONString(innerDataCheck);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("内部数据检查[" + jsoninnerDataCheck + "]");
		}
		/**风险信息检查**/
	//OpaRiskInfoCheck riskInfoCheck = sysDecisionService.selectRiskInfoByAppId(appId);
		//OpaRiskInfoCheck riskInfoCheck = sysDecisionYdjService.selectRiskInfoByAppId(appId,custInfo.getCertifiNo());
		OpaRiskInfoCheck riskInfoCheck = selectRiskInfoByAppId(appId,custInfo.getCertifiNo(),resMap);
		String jsonriskInfoCheck="{\"\":\"\"}";
		if(null!=riskInfoCheck){
			riskInfoCheck.setAppId(null);
			riskInfoCheck.setAutoId(null);
			riskInfoCheck.setCrtDate(null);
			riskInfoCheck.setCrtUser(null);
			jsonriskInfoCheck=JSON.toJSONString(riskInfoCheck);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("风险信息检查[" + jsonriskInfoCheck + "]");
		}
		//反欺诈
		Map<String, Object> dataMap = sysDecisionYdjService.selectFqzRstDespByAppId(appId);
		
		/**数据查询结束，拼接JSOn **/
		String jsonStr = jsoncustInfo.substring(0, jsoncustInfo.length()-1)+","
			+jsonriskInfoCheck.substring(1,jsonriskInfoCheck.length()-1)+","
			+jsonsysInfo.substring(1,jsonsysInfo.length()-1)+","
			+jsoninnerDataCheck.substring(1, jsoninnerDataCheck.length());
		ctx.setData("jsonStr", jsonStr.trim());
		ctx.setData("dataMap", dataMap);
	}
	
	/**
	 * 内部数据检查信息
	 * @param jsonMap
	 * @return
	 * @throws Exception
	 */
	public OpaInnerDataCheck selectInnerDataByAppId(Map<String, String> jsonMap)
			throws Exception {
		OpaInnerDataCheck inner = new OpaInnerDataCheck();
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
		// 主卡证件号码有效期
		String certifinoVailud = jsonMap.get("OPAS_BIZ_INP_APPL_C1-C5_IDTE1");
		if (certifinoVailud == null) {
			inner.setCertifinoVailud("5");
		} else {
			inner.setCertifinoVailud(certifinoVailud);
		}
		//附属卡证件号码有效期
		String certifinoVailud_fk = jsonMap.get("OPAS_BIZ_INP_APPL_C2-C5_IDTE1");
		if (certifinoVailud_fk == null) {
			inner.setCertifinoVailud_fk("5");
		} else {
			inner.setCertifinoVailud_fk(certifinoVailud_fk);
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
//		autoidLv = "大D话事人";//test
		/*Map<String,Object> ishaveInriskInfoMap = CommonUtil.geNotNullMap(autoid11, autoid22,
				autoid33, autoid44,autoid55);*/
		String ishaveInriskInfo = null;
		String num = "";//页面展示是否高亮用
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
			if(ishaveInriskInfo!=null&&!"".equals(ishaveInriskInfo)){
				inner.setIshaveInriskInfo("是"+autoidLv+","+ishaveInriskInfo);
			}else{
				inner.setIshaveInriskInfo("是"+autoidLv);
			}
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
	public void queryCheckinerResultInfoByAppID(Context ctx) throws CoreException {
		String appId = (String) ctx.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		List<OpaCheckinerResultInfo> notesList = sysDecisionService.selectCheckinerResultInfoByAppID(appId);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", notesList);
		ctx.setDataMap(dataMap);
	}
	
	public void queryJDCardInfoByAppID(Context ctx) throws CoreException{
		String appId = (String) ctx.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		JDCardInfo cardInfo = sysDecisionService.selectJDCardInfoByAppId(appId);
		ctx.setData("cardInfo", cardInfo);
	}
	
	//反欺诈结果描述  满足，不满足 都需要展示。
	public void queryFqzRstDespByAppId(Context ctx) throws CoreException{
		String appId = (String) ctx.getData("appId");
		//易达金不会传1码   截取15位,展示
		appId= appId.substring(0, appId.length()-1);
		String fqzRequesttype = (String) ctx.getData("fqzRequesttype");
		String fqzRisknoW = (String) ctx.getData("fqzRisknoW");
		String fqzRisknoH = (String) ctx.getData("fqzRisknoH");
		String fqzRisknoZ = (String) ctx.getData("fqzRisknoZ");
		Assert.notNull(appId, "请求申请件编号为空!");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("appId", appId);
		dataMap.put("fqzRequesttype", fqzRequesttype);
		dataMap.put("fqzRisknoW",fqzRisknoW);
		dataMap.put("fqzRisknoH",fqzRisknoH);
		dataMap.put("fqzRisknoZ",fqzRisknoZ);
		dataMap = sysDecisionService.queryFqzRstDespByAppId(dataMap);
		ctx.setDataMap(dataMap);
	}
	
}
