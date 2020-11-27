package com.huaxia.opas.cache.service.riskcheck.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.common.CacheConsts;
import com.huaxia.opas.cache.service.LueneService;
import com.huaxia.opas.cache.service.riskcheck.RiskFieldMachService;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.domain.riskcheck.TelRiskList;
import com.huaxia.opas.service.riskcheck.RiskCheckService;

/**
 * 电话风险名单库
 * 
 * @author qianxiwen - 2017-04-08 修改 jiangming.yang
 *
 */
public class Step1_Field_Opas_Tel_RiskList implements RiskFieldMachService {

	private static final Logger logger = Logger.getLogger(Step1_Field_Opas_Tel_RiskList.class);

	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		
		// 主卡申请人或联系人手机号码是否命中电话类黑名单库中“手机号码” --网申渠道
		List<String> STR_APPLY_MOBILE_ID = new ArrayList<String>();
		// 主卡申请人或联系人手机号码是否命中电话类黑名单库中“单位电话”和"住宅电话" --网申渠道
		List<String> LIST_CON_TEL_ID = new ArrayList<String>();
		// 主卡申请人或联系人手机号码是否命中电话类黑名单库中"其他电话" --网申渠道
		List<String> LIST_APPLY_OTHER_TEL = new ArrayList<String>();
		// 主副卡
		String substring = appId.substring(6, 7);
		if ("1".equals(appType)) {
			if ("B".equals(substring)) {
				if (logger.isInfoEnabled()) {
					logger.info("网申渠道进件..." + appId);
				}
				// ****网申渠道 风险库 手机号码 start
				STR_APPLY_MOBILE_ID.add(trimToEmpty(apply_Info1.getC1Mobile()));// 申请人手机
				STR_APPLY_MOBILE_ID.add(trimToEmpty(apply_Info1.getC1Remobil()));// 直系亲属手机
				STR_APPLY_MOBILE_ID.add(trimToEmpty(apply_Info1.getC1Xmobil1()));// 联系人1手机
				// *********网申渠道 风险库单位电话 住宅电话 start
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Hmare())+trimToEmpty(apply_Info1.getC1Hmtel()));// 申请人家庭电话
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Cotel()));// 申请人公司电话
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Fcotel()));// 申请人前公司电话
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Retelar() + apply_Info1.getC1Retel()));// 直系亲属电话
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Xtelar1() + apply_Info1.getC1Xtel1()));// 联系人1电话
				// *********网申渠道 风险库其他电话 start
				LIST_APPLY_OTHER_TEL.addAll(STR_APPLY_MOBILE_ID);
				LIST_APPLY_OTHER_TEL.addAll(LIST_CON_TEL_ID);
			} else {
				if (logger.isInfoEnabled()) {
					logger.info("非网申渠道进件..." + appId);
				}
				// *********非网申渠道 风险库 手机号码 start
				STR_APPLY_MOBILE_ID.add(trimToEmpty(apply_Info1.getC1Mobile()));// 申请人手机
				// *********非网申渠道 风险库 单位电话 住宅电话 start
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Hmare() + apply_Info1.getC1Hmtel()));// 申请人家庭电话
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Cotel()));// 申请人公司电话
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Fcotel()));// 申请人前公司电话
				// *********非网申渠道 风险库其他电话 start
				LIST_APPLY_OTHER_TEL.addAll(STR_APPLY_MOBILE_ID);
				LIST_APPLY_OTHER_TEL.addAll(LIST_CON_TEL_ID);
			}
		} else if ("2".equals(appType)) {// 副卡
			if ("B".equals(substring)) {
				if (logger.isInfoEnabled()) {
					logger.info("网申渠道进件..." + appId);
				}
				// ****网申渠道 风险库 手机号码 start
				STR_APPLY_MOBILE_ID.add(trimToEmpty(apply_Info2.getC2Mobile()));// 附卡申请人手机
				// ***网申渠道 风险库单位电话 住宅电话 start
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info2.getC2Hmare() + apply_Info2.getC2Hmtel()));// 附卡申请人家庭电话
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info2.getC2Cotel() + apply_Info2.getC2Coext()));// 附卡申请人公司电话
				// ***网申渠道 风险库其他电话 start
				LIST_APPLY_OTHER_TEL.addAll(STR_APPLY_MOBILE_ID);
				LIST_APPLY_OTHER_TEL.addAll(LIST_CON_TEL_ID);
			} else {
				if (logger.isInfoEnabled()) {
					logger.info("非网申渠道进件..." + appId);
				}
				// *********非网申渠道 风险库 手机号码 start
				STR_APPLY_MOBILE_ID.add(trimToEmpty(apply_Info2.getC2Mobile()));// 附卡申请人手机
				// *********非网申渠道 风险库其他电话 start
				LIST_APPLY_OTHER_TEL.addAll(STR_APPLY_MOBILE_ID);
			}

		} else if ("3".equals(appType)) {// 主卡
			if ("B".equals(substring)) {
				if (logger.isInfoEnabled()) {
					logger.info("网申渠道进件..." + appId);
				}
				// ****网申渠道 风险库 手机号码 start
				STR_APPLY_MOBILE_ID.add(trimToEmpty(apply_Info1.getC1Mobile()));// 申请人手机
				STR_APPLY_MOBILE_ID.add(trimToEmpty(apply_Info1.getC1Remobil()));// 直系亲属手机
				STR_APPLY_MOBILE_ID.add(trimToEmpty(apply_Info1.getC1Xmobil1()));// 联系人1手机
				// *********网申渠道 风险库单位电话 住宅电话 start
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Hmare())+trimToEmpty(apply_Info1.getC1Hmtel()));// 申请人家庭电话
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Cotel()));// 申请人公司电话
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Fcotel()));// 申请人前公司电话
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Retelar() + apply_Info1.getC1Retel()));// 直系亲属电话
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Xtelar1() + apply_Info1.getC1Xtel1()));// 联系人1电话
				// *********网申渠道 风险库其他电话 start
				LIST_APPLY_OTHER_TEL.addAll(STR_APPLY_MOBILE_ID);
				LIST_APPLY_OTHER_TEL.addAll(LIST_CON_TEL_ID);
			} else {
				if (logger.isInfoEnabled()) {
					logger.info("非网申渠道进件..." + appId);
				}
				// *********非网申渠道 风险库 手机号码 start
				STR_APPLY_MOBILE_ID.add(trimToEmpty(apply_Info1.getC1Mobile()));// 申请人手机
				// *********非网申渠道 风险库 单位电话 住宅电话 start
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Hmare() + apply_Info1.getC1Hmtel()));// 申请人家庭电话
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Cotel()));// 申请人公司电话
				LIST_CON_TEL_ID.add(trimToEmpty(apply_Info1.getC1Fcotel()));// 申请人前公司电话
				// *********非网申渠道 风险库其他电话 start
				LIST_APPLY_OTHER_TEL.addAll(STR_APPLY_MOBILE_ID);
				LIST_APPLY_OTHER_TEL.addAll(LIST_CON_TEL_ID);
			}
		}
		params.put("STR_APPLY_MOBILE_ID", STR_APPLY_MOBILE_ID);
		params.put("LIST_CON_TEL_ID", LIST_CON_TEL_ID);
		params.put("LIST_APPLY_OTHER_TEL", LIST_APPLY_OTHER_TEL);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		List<String> STR_APPLY_MOBILE_ID = (List<String>) initParams.get("STR_APPLY_MOBILE_ID");
		List<String> LIST_CON_TEL_ID = (List<String>) initParams.get("LIST_CON_TEL_ID");
		List<String> LIST_APPLY_OTHER_TEL = (List<String>) initParams.get("LIST_APPLY_OTHER_TEL");
		// 主副卡                         
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		// List<TelRiskList> telrisklist =
		// riskCheckService.Query_OPAS_TEL_RISKLIST();
		List<TelRiskList> telrisklist1 = null;
		List<TelRiskList> telrisklist2 = null;
		List<TelRiskList> telrisklist3 = null;
		if (STR_APPLY_MOBILE_ID != null && STR_APPLY_MOBILE_ID.size() > 0) {
			telrisklist1 = riskCheckService.Query_OPAS_STR_APPLY_MOBILE_ID_RISKLIST(STR_APPLY_MOBILE_ID);// STR_APPLY_MOBILE_ID
		}
		if (LIST_CON_TEL_ID != null && LIST_CON_TEL_ID.size() > 0) {
			telrisklist2 = riskCheckService.Query_OPAS_LIST_CON_TEL_ID_RISKLIST(LIST_CON_TEL_ID);// LIST_CON_TEL_ID
		}
		if (LIST_APPLY_OTHER_TEL != null && LIST_APPLY_OTHER_TEL.size() > 0) {
			telrisklist3 = riskCheckService.Query_OPAS_LIST_APPLY_OTHER_TEL_RISKLIST(LIST_APPLY_OTHER_TEL);// LIST_APPLY_OTHER_TEL
		}
		String substring = appId.substring(6, 7);
		RiskCheckResult check_APPLY_MOBILE_ID = new RiskCheckResult();
		boolean bool_APPLY_MOBILE_ID = true;
		RiskCheckResult check_APPLY_COMPANY_TEL = new RiskCheckResult();
		boolean bool_APPLY_COMPANY_TEL = true;
		RiskCheckResult check_APPLY_LIVING_TEL = new RiskCheckResult();
		RiskCheckResult check_APPLY_OTHER_TEL = new RiskCheckResult();
		boolean bool_APPLY_OTHER_TEL = true;
		boolean bool_APPLY_LIVING_TEL = true;
		if ("1".equals(appType)) {
			if ("B".equals(substring)) {
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中“手机号码” --网申渠道
				check_APPLY_MOBILE_ID.setFieldKey("APPLY_MOBILE_ID");
				check_APPLY_MOBILE_ID.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_MOBILE_ID.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_MOBILE_ID.setApplyType(appType);
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中“单位电话” --网申渠道
				check_APPLY_COMPANY_TEL.setFieldKey("APPLY_COMPANY_TEL");
				check_APPLY_COMPANY_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_COMPANY_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_COMPANY_TEL.setApplyType(appType);
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中"住宅电话" --网申渠道
				check_APPLY_LIVING_TEL.setFieldKey("APPLY_LIVING_TEL");
				check_APPLY_LIVING_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_LIVING_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_LIVING_TEL.setApplyType(appType);
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中"其他电话" --网申渠道
				check_APPLY_OTHER_TEL.setFieldKey("APPLY_OTHER_TEL");
				check_APPLY_OTHER_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_OTHER_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_OTHER_TEL.setApplyType(appType);
			} else {
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中“手机号码” --非网申渠道
				check_APPLY_MOBILE_ID.setFieldKey("APPLY_MOBILE_ID_L");
				check_APPLY_MOBILE_ID.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_MOBILE_ID.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_MOBILE_ID.setApplyType(appType);
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中“单位电话” --非网申渠道
				check_APPLY_COMPANY_TEL.setFieldKey("APPLY_COMPANY_TEL_L");
				check_APPLY_COMPANY_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_COMPANY_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_COMPANY_TEL.setApplyType(appType);
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中"住宅电话" --非网申渠道
				check_APPLY_LIVING_TEL.setFieldKey("APPLY_LIVING_TEL_L");
				check_APPLY_LIVING_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_LIVING_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_LIVING_TEL.setApplyType(appType);
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中"其他电话" --非网申渠道
				check_APPLY_OTHER_TEL.setFieldKey("APPLY_OTHER_TEL_L");
				check_APPLY_OTHER_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_OTHER_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_OTHER_TEL.setApplyType(appType);

			}

			listriskcheck.add(check_APPLY_MOBILE_ID);
			listriskcheck.add(check_APPLY_COMPANY_TEL);
			listriskcheck.add(check_APPLY_LIVING_TEL);
			listriskcheck.add(check_APPLY_OTHER_TEL);

		}
		if ("2".equals(appType)) {
			if ("B".equals(substring)) {
				// 附卡申请人或联系人手机号码是否命中电话类黑名单库中“手机号码” --网申渠道
				check_APPLY_MOBILE_ID.setFieldKey("APPLY_MOBILE_ID");
				check_APPLY_MOBILE_ID.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_MOBILE_ID.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_MOBILE_ID.setApplyType(appType);
				// 附卡申请人或联系人手机号码是否命中电话类黑名单库中“单位电话” --网申渠道
				check_APPLY_COMPANY_TEL.setFieldKey("APPLY_COMPANY_TEL");
				check_APPLY_COMPANY_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_COMPANY_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_COMPANY_TEL.setApplyType(appType);
				// 附卡申请人或联系人手机号码是否命中电话类黑名单库中"住宅电话" --网申渠道
				check_APPLY_LIVING_TEL.setFieldKey("APPLY_LIVING_TEL");
				check_APPLY_LIVING_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_LIVING_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_LIVING_TEL.setApplyType(appType);
				// 附卡申请人或联系人手机号码是否命中电话类黑名单库中"其他电话" --网申渠道
				check_APPLY_OTHER_TEL.setFieldKey("APPLY_OTHER_TEL");
				check_APPLY_OTHER_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_OTHER_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_OTHER_TEL.setApplyType(appType);
				listriskcheck.add(check_APPLY_MOBILE_ID);
				listriskcheck.add(check_APPLY_COMPANY_TEL);
				listriskcheck.add(check_APPLY_LIVING_TEL);
				listriskcheck.add(check_APPLY_OTHER_TEL);
			} else {
				// 附卡申请人或联系人手机号码是否命中电话类黑名单库中“手机号码” --非网申渠道
				check_APPLY_MOBILE_ID.setFieldKey("APPLY_MOBILE_ID_L");
				check_APPLY_MOBILE_ID.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_MOBILE_ID.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_MOBILE_ID.setApplyType(appType);
				// 附卡申请人或联系人手机号码是否命中电话类黑名单库中"其他电话" --非网申渠道
				check_APPLY_OTHER_TEL.setFieldKey("APPLY_OTHER_TEL_L");
				check_APPLY_OTHER_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_OTHER_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_OTHER_TEL.setApplyType(appType);

				listriskcheck.add(check_APPLY_MOBILE_ID);
				listriskcheck.add(check_APPLY_OTHER_TEL);
			}

		}
		if ("3".equals(appType)) {
			if ("B".equals(substring)) {
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中“手机号码” --网申渠道
				check_APPLY_MOBILE_ID.setFieldKey("APPLY_MOBILE_ID");
				check_APPLY_MOBILE_ID.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_MOBILE_ID.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_MOBILE_ID.setApplyType(appType);
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中“单位电话” --网申渠道
				check_APPLY_COMPANY_TEL.setFieldKey("APPLY_COMPANY_TEL");
				check_APPLY_COMPANY_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_COMPANY_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_COMPANY_TEL.setApplyType(appType);
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中"住宅电话" --网申渠道
				check_APPLY_LIVING_TEL.setFieldKey("APPLY_LIVING_TEL");
				check_APPLY_LIVING_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_LIVING_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_LIVING_TEL.setApplyType(appType);
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中"其他电话" --网申渠道
				check_APPLY_OTHER_TEL.setFieldKey("APPLY_OTHER_TEL");
				check_APPLY_OTHER_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_OTHER_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_OTHER_TEL.setApplyType(appType);
			} else {
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中“手机号码” --非网申渠道
				check_APPLY_MOBILE_ID.setFieldKey("APPLY_MOBILE_ID_L");
				check_APPLY_MOBILE_ID.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_MOBILE_ID.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_MOBILE_ID.setApplyType(appType);
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中“单位电话” --非网申渠道
				check_APPLY_COMPANY_TEL.setFieldKey("APPLY_COMPANY_TEL_L");
				check_APPLY_COMPANY_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_COMPANY_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_COMPANY_TEL.setApplyType(appType);
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中"住宅电话" --非网申渠道
				check_APPLY_LIVING_TEL.setFieldKey("APPLY_LIVING_TEL_L");
				check_APPLY_LIVING_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_LIVING_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_LIVING_TEL.setApplyType(appType);
				// 主卡申请人或联系人手机号码是否命中电话类黑名单库中"其他电话" --非网申渠道
				check_APPLY_OTHER_TEL.setFieldKey("APPLY_OTHER_TEL_L");
				check_APPLY_OTHER_TEL.setRiskType(CacheConsts.RISK_TYPE_BLACK);
				check_APPLY_OTHER_TEL.setTableName("OPAS_TEL_RISKLIST");
				check_APPLY_OTHER_TEL.setApplyType(appType);

			}

			listriskcheck.add(check_APPLY_MOBILE_ID);
			listriskcheck.add(check_APPLY_COMPANY_TEL);
			listriskcheck.add(check_APPLY_LIVING_TEL);
			listriskcheck.add(check_APPLY_OTHER_TEL);
		}

		if (telrisklist1 != null && telrisklist1.size() > 0) {
			for (int i = 0; i < telrisklist1.size(); i++) {
				TelRiskList tmptelrisk = telrisklist1.get(i);
				if (STR_APPLY_MOBILE_ID.contains(tmptelrisk.getMobileId()) && STR_APPLY_MOBILE_ID.size() > 0
						&& bool_APPLY_MOBILE_ID) {
					check_APPLY_MOBILE_ID.setBaseDataValue(tmptelrisk.getMobileId());
					check_APPLY_MOBILE_ID.setPriKeyValue(tmptelrisk.getAutoId());
					check_APPLY_MOBILE_ID.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					break;
				}
			}
		}

		if (telrisklist2 != null && telrisklist2.size() > 0) {
			for (int i = 0; i < telrisklist2.size(); i++) {
				TelRiskList tmptelrisk = telrisklist2.get(i);
				if (LIST_CON_TEL_ID.contains(tmptelrisk.getCompanyTel()) && LIST_CON_TEL_ID.size() > 0
						&& bool_APPLY_COMPANY_TEL) {
					check_APPLY_COMPANY_TEL.setBaseDataValue(tmptelrisk.getCompanyTel());
					check_APPLY_COMPANY_TEL.setPriKeyValue(tmptelrisk.getAutoId());
					check_APPLY_COMPANY_TEL.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					bool_APPLY_COMPANY_TEL = false;
				}

				if (LIST_CON_TEL_ID.contains(tmptelrisk.getLivingTel()) && LIST_CON_TEL_ID.size() > 0
						&& bool_APPLY_LIVING_TEL) {
					check_APPLY_LIVING_TEL.setBaseDataValue(tmptelrisk.getLivingTel());
					check_APPLY_LIVING_TEL.setPriKeyValue(tmptelrisk.getAutoId());
					check_APPLY_LIVING_TEL.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					bool_APPLY_LIVING_TEL = false;
				}
			}
		}
		if (telrisklist3 != null && telrisklist3.size() > 0) {
			for (int i = 0; i < telrisklist3.size(); i++) {
				TelRiskList tmptelrisk = telrisklist3.get(i);
				if (LIST_APPLY_OTHER_TEL.contains(tmptelrisk.getOtherTel()) && LIST_APPLY_OTHER_TEL.size() > 0
						&& bool_APPLY_OTHER_TEL) {
					check_APPLY_OTHER_TEL.setBaseDataValue(tmptelrisk.getOtherTel());
					check_APPLY_OTHER_TEL.setPriKeyValue(tmptelrisk.getAutoId());
					check_APPLY_OTHER_TEL.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					break;
				}
			}
		}

		STR_APPLY_MOBILE_ID.clear();
		LIST_CON_TEL_ID.clear();
		LIST_APPLY_OTHER_TEL.clear();

		return listriskcheck;
	}

	public LueneService getLueneService() {
		return lueneService;
	}

	public void setLueneService(LueneService lueneService) {
		this.lueneService = lueneService;
	}

	public RiskCheckService getRiskCheckService() {
		return riskCheckService;
	}

	public void setRiskCheckService(RiskCheckService riskCheckService) {
		this.riskCheckService = riskCheckService;
	}

	/**
	 * 去掉两端空格，如果传入参数是null，返回是""（空字符串）
	 * 
	 * @param str
	 * @return
	 */
	public String trimToEmpty(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	@Override
	public void init(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2, String appType)
			throws CoreException {
		// TODO Auto-generated method stub

	}

}
