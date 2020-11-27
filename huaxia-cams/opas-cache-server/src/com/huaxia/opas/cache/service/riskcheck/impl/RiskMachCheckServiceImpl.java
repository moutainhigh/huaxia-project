package com.huaxia.opas.cache.service.riskcheck.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.service.riskcheck.RiskFieldMachService;
import com.huaxia.opas.cache.service.riskcheck.RiskMachCheckService;
import com.huaxia.opas.cache.util.JsonUtil;
import com.huaxia.opas.dao.common.OpasError;
import com.huaxia.opas.domain.riskcheck.KeyfiledMarchinfo;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

import net.sf.json.JSONArray;

/**
 * 风险检查
 * 
 * @author qianxiwen
 *
 */
public class RiskMachCheckServiceImpl implements RiskMachCheckService, Serializable {

	private static final long serialVersionUID = -2646073013013515979L;
	private static final Logger logger = Logger.getLogger(RiskMachCheckServiceImpl.class);
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	// step1
	@Resource(name = "step1_field_Opas_Identity_RiskList")
	private RiskFieldMachService step1_field_Opas_Identity_RiskList;
	@Resource(name = "step1_field_Opas_Company_RiskList")
	private RiskFieldMachService step1_field_Opas_Company_RiskList;
	@Resource(name = "step1_field_Opas_Tel_RiskList")
	private RiskFieldMachService step1_field_Opas_Tel_RiskList;
	@Resource(name = "step1_field_Opas_Addr_RiskList")
	private RiskFieldMachService step1_field_Opas_Addr_RiskList;
	@Resource(name = "step1_field_Opas_Promoter_RiskList")
	private RiskFieldMachService step1_field_Opas_Promoter_RiskList;
	@Resource(name = "step1_field_Opas_SameIndustry_RiskList")
	private RiskFieldMachService step1_field_Opas_SameIndustry_RiskList;
	@Resource(name = "step1_Field_Foreign_Index_RiskList")
	private RiskFieldMachService step1_Field_Foreign_Index_RiskList;
	@Resource(name = "step1_Field_Opas_Biz_Life_Appln_RiskList")
	private RiskFieldMachService step1_Field_Opas_Biz_Life_Appln_RiskList;
	@Resource(name = "step1_Field_Opas_Low_Risk")
	private RiskFieldMachService step1_Field_Opas_Low_Risk;
	@Resource(name = "step1_Field_Opas_Stakeholder_Type")
	private RiskFieldMachService step1_Field_Opas_Stakeholder_Type;
	
	// step2
	@Resource(name = "step2_Field_Opas_Creait_TelCheck_List")
	private RiskFieldMachService step2_Field_Opas_Creait_TelCheck_List;
	@Resource(name = "step2_Field_Opas_MajorSchool_List")
	private RiskFieldMachService step2_Field_Opas_MajorSchool_List;
	@Resource(name = "step2_Field_Opas_Merch_Teamdeal_List")
	private RiskFieldMachService step2_Field_Opas_Merch_Teamdeal_List;
	@Resource(name = "step2_Field_Opas_SpecialProject_List")
	private RiskFieldMachService step2_Field_Opas_SpecialProject_List;
	@Resource(name = "step2_Field_Opas_TargetCompany_List")
	private RiskFieldMachService step2_Field_Opas_TargetCompany_List;
	@Resource(name = "step2_Field_Opas_TeamDeal_List")
	private RiskFieldMachService step2_Field_Opas_TeamDeal_List;
	@Resource(name = "step2_Field_Opas_Biz_Inp_Appl_C1")
	private RiskFieldMachService step2_Field_Opas_Biz_Inp_Appl_C1;
	// 2017-04-05
	@Resource(name = "step2_Field_Opas_Biz_Inp_GreyList")
	private RiskFieldMachService step2_Field_Opas_Biz_Inp_GreyList;
	@Resource(name = "step2_Field_Opas_NormCredit_List")
	private RiskFieldMachService step2_Field_Opas_NormCredit_List;
	@Resource(name = "step2_Field_Opas_YdjCredit_List")
	private RiskFieldMachService step2_Field_Opas_YdjCredit_List;
	@Resource(name = "step2_Field_Opas_Biz_IdNumber_InDate")
	private RiskFieldMachService step2_Field_Opas_Biz_IdNumber_InDate;
	//2018-11-26
	@Resource(name = "step2_Field_Opas_Biz_IdNumber_InDate2")
	private RiskFieldMachService step2_Field_Opas_Biz_IdNumber_InDate2;
	// 2017-04-08
	@Resource(name = "step2_Field_Opas_Biz_LogicalCheck")
	private RiskFieldMachService step2_Field_Opas_Biz_LogicalCheck;
	// 2017-04-24
	@Resource(name = "step2_Field_Opas_Inner_RiskInfo_List")
	private RiskFieldMachService step2_Field_Opas_Inner_RiskInfo_List;
	@Resource(name = "step2_Field_Opas_GoodCompany_List")
	private RiskFieldMachService step2_Field_Opas_GoodCompany_List;
	// 20170614
	@Resource(name = "step2_Field_Opas_StockCust_TelSale")
	private RiskFieldMachService step2_Field_Opas_StockCust_TelSale;

	@Resource(name = "step2_Field_Opas_Biz_C6_Resi_Taxid")
	private RiskFieldMachService step2_Field_Opas_Biz_C6_Resi_Taxid;

	//20191016
	@Resource(name = "step2_Field_Opas_Biz_Inp_C1_PAD")
	private RiskFieldMachService step2_Field_Opas_Biz_Inp_C1_PAD;
	@Resource(name = "step2_Field_Opas_Marketer_List")
	private RiskFieldMachService step2_Field_Opas_Marketer_List;
	@Resource(name = "step2_Field_Opas_Excellent_Company_List")
	private RiskFieldMachService step2_Field_Opas_Excellent_Company_List;
	
	// step3
	@Resource(name = "step3_Field_Opas_Pboc_Personal_Info")
	private RiskFieldMachService step3_Field_Opas_Pboc_Personal_Info;
	@Resource(name = "step3_Field_Opas_Pboc_Profession_Info")
	private RiskFieldMachService step3_Field_Opas_Pboc_Profession_Info;
	@Resource(name = "step3_Field_Opas_Pboc_Residence_Info")
	private RiskFieldMachService step3_Field_Opas_Pboc_Residence_Info;
	@Resource(name = "step3_Field_Sp_Aps_Ifs_Cust_Info")
	private RiskFieldMachService step3_Field_Sp_Aps_Ifs_Cust_Info;
	@Resource(name = "step2_Field_Opas_Enterprise_Information")
	private RiskFieldMachService step2_Field_Opas_Enterprise_Information;
	//2019/8/14
	@Resource(name = "step3_Field_Py_Pcr_Crs_Crt_Sisz2_Bi_Info")
	private RiskFieldMachService step3_Field_Py_Pcr_Crs_Crt_Sisz2_Bi_Info;
	@Resource(name = "step3_Field_Ylz_Rep_Data_Info")
	private RiskFieldMachService step3_Field_Ylz_Rep_Data_Info;
	@Resource(name = "step3_Field_Opas_Pboc_Public_Accfund_Info")
	private RiskFieldMachService step3_Field_Opas_Pboc_Public_Accfund_Info;
	@Resource(name = "step3_Field_Hz_Gjjxx_Info")
	private RiskFieldMachService step3_Field_Hz_Gjjxx_Info;
	@Resource(name = "step3_Field_Xm_Gjj_Jcxx_Info")
	private RiskFieldMachService step3_Field_Xm_Gjj_Jcxx_Info;
	@Resource(name = "step3_Field_Nb_Base_Info")
	private RiskFieldMachService step3_Field_Nb_Base_Info;
	@Resource(name = "step3_Field_Yc_Gjj_Info")
	private RiskFieldMachService step3_Field_Yc_Gjj_Info;
	@Resource(name = "step3_Field_Wz_Gr_Gjjzl_Info")
	private RiskFieldMachService step3_Field_Wz_Gr_Gjjzl_Info;
	

	@Override
	public void exeRiskCheck(String appId, String step, String appType) throws CoreException {
		List<RiskCheckResult> listriskcheckresult = new ArrayList<RiskCheckResult>();
		RiskCheck_Apply_C1 apply_Info1 = null;
		RiskCheck_Apply_C2 apply_Info2 = null;

		if (logger.isInfoEnabled()) {
			logger.info("[黑名单] 风险检查类型：" + appType + "  流水号:" + appId);
		}

		if ("3".equals(appType)) {// 主卡信息
			List<RiskCheck_Apply_C1> applyc1List = riskCheckService.selectApply_C1ByPrimaryKey(appId);
			if (applyc1List == null || applyc1List.size() <= 0) {
				throw new CoreException(OpasError.UNKOWN_ERROR.getErrorCode(), "传入的申请件编号有误!!!");
			}
			apply_Info1 = applyc1List.get(0);

		} else if ("2".equals(appType)) {// 附卡
			List<RiskCheck_Apply_C1> applyc1List = riskCheckService.selectApply_C1ByPrimaryKey(appId);
			List<RiskCheck_Apply_C2> applyc2List = riskCheckService.selectApply_C2ByPrimaryKey(appId);
			if (applyc2List == null || applyc2List.size() <= 0 || applyc1List == null || applyc1List.size() <= 0) {
				throw new CoreException(OpasError.UNKOWN_ERROR.getErrorCode(), "传入的申请件编号有误!!!");
			}
			apply_Info2 = applyc2List.get(0);
			apply_Info1 = applyc1List.get(0);
		} else if ("1".equals(appType)) {// 主附同审
			List<RiskCheck_Apply_C1> applyc1List = riskCheckService.selectApply_C1ByPrimaryKey(appId);
			List<RiskCheck_Apply_C2> applyc2List = riskCheckService.selectApply_C2ByPrimaryKey(appId);
			if (applyc2List == null || applyc2List.size() <= 0 || applyc1List == null || applyc1List.size() <= 0) {
				throw new CoreException(OpasError.UNKOWN_ERROR.getErrorCode(), "传入的申请件编号有误!!!");
			}
			apply_Info2 = applyc2List.get(0);
			apply_Info1 = applyc1List.get(0);
		}

		if (logger.isInfoEnabled()) {
			logger.info("[黑名单] 风险检查步骤：" + step + "  流水号:" + appId);
		}

		// 黑名单检查
		if ("1".equals(step)) {
			List<RiskCheckResult> aml_RiskList = step1_Field_Foreign_Index_RiskList.macheField(appId, apply_Info1,
					apply_Info2, appType);
			List<RiskCheckResult> life_RiskList = step1_Field_Opas_Biz_Life_Appln_RiskList.macheField(appId,
					apply_Info1, apply_Info2, appType);
			//反欺诈需要尽快接受到名单匹配结果  节点提前
			List<RiskCheckResult> marketer_List = step2_Field_Opas_Marketer_List.macheField(appId,
					apply_Info1, apply_Info2, appType);
			List<RiskCheckResult> pad_Info = step2_Field_Opas_Biz_Inp_C1_PAD.macheField(appId, apply_Info1, apply_Info2, appType);
			
			listriskcheckresult.addAll(pad_Info);
			listriskcheckresult.addAll(marketer_List);
			listriskcheckresult.addAll(aml_RiskList);
			listriskcheckresult.addAll(life_RiskList);
			//准入白名单匹配结果
			listriskcheckresult
					.addAll(step2_Field_Opas_Excellent_Company_List.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step1_Field_Opas_Low_Risk.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step1_Field_Opas_Stakeholder_Type.macheField(appId, apply_Info1, apply_Info2, appType));
			
		} else if ("2".equals(step)) {//内部交叉
			listriskcheckresult
					.addAll(step2_Field_Opas_Creait_TelCheck_List.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_MajorSchool_List.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_Merch_Teamdeal_List.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_SpecialProject_List.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_TargetCompany_List.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_TeamDeal_List.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_Biz_Inp_Appl_C1.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_Biz_Inp_GreyList.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_NormCredit_List.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_Biz_IdNumber_InDate.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_Biz_IdNumber_InDate2.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_Biz_LogicalCheck.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_GoodCompany_List.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_Inner_RiskInfo_List.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_StockCust_TelSale.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_Enterprise_Information.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_Biz_C6_Resi_Taxid.macheField(appId, apply_Info1, apply_Info2, appType));

		} else if ("3".equals(step)) {//三方匹配
			List<RiskCheckResult> pbocphonecheck = step3_Field_Opas_Pboc_Personal_Info.macheField(appId, apply_Info1,
					apply_Info2, appType);
			List<RiskCheckResult> pbocomAddrcheck = step3_Field_Opas_Pboc_Profession_Info.macheField(appId, apply_Info1,
					apply_Info2, appType);
			List<RiskCheckResult> residentAddrcheck = step3_Field_Opas_Pboc_Residence_Info.macheField(appId,
					apply_Info1, apply_Info2, appType);
			List<RiskCheckResult> crmAddrAndPhonecheck = step3_Field_Sp_Aps_Ifs_Cust_Info.macheField(appId, apply_Info1,
					apply_Info2, appType);
			List<RiskCheckResult> pyAddrAndPhonecheck = step3_Field_Py_Pcr_Crs_Crt_Sisz2_Bi_Info.macheField(appId, apply_Info1,
					apply_Info2, appType);
			List<RiskCheckResult> ylzAddrAndPhonecheck = step3_Field_Ylz_Rep_Data_Info.macheField(appId, apply_Info1,
					apply_Info2, appType);
			List<RiskCheckResult> pbocAddrAndPhonecheck = step3_Field_Opas_Pboc_Public_Accfund_Info.macheField(appId, apply_Info1,
					apply_Info2, appType);
			List<RiskCheckResult> hzGjjxxcheck = step3_Field_Hz_Gjjxx_Info.macheField(appId, apply_Info1,
					apply_Info2, appType);
			List<RiskCheckResult> xmGjjJcxx = step3_Field_Xm_Gjj_Jcxx_Info.macheField(appId, apply_Info1,
					apply_Info2, appType);
			List<RiskCheckResult> nbBase = step3_Field_Nb_Base_Info.macheField(appId, apply_Info1,
					apply_Info2, appType);
			List<RiskCheckResult> wzGjjJcxx = step3_Field_Wz_Gr_Gjjzl_Info.macheField(appId, apply_Info1,
					apply_Info2, appType);
			List<RiskCheckResult> ycGjjJcxx = step3_Field_Yc_Gjj_Info.macheField(appId, apply_Info1,
					apply_Info2, appType);
			
			listriskcheckresult.addAll(pbocphonecheck);
			listriskcheckresult.addAll(pbocomAddrcheck);
			listriskcheckresult.addAll(residentAddrcheck);
			listriskcheckresult.addAll(crmAddrAndPhonecheck);
			listriskcheckresult.addAll(pyAddrAndPhonecheck);
			listriskcheckresult.addAll(ylzAddrAndPhonecheck);
			listriskcheckresult.addAll(pbocAddrAndPhonecheck);
			listriskcheckresult.addAll(hzGjjxxcheck);
			listriskcheckresult.addAll(xmGjjJcxx);
			listriskcheckresult.addAll(nbBase);
			listriskcheckresult.addAll(wzGjjJcxx);
			listriskcheckresult.addAll(ycGjjJcxx);
			
		} else if ("4".equals(step)) {//二次内部交叉
			listriskcheckresult
					.addAll(step2_Field_Opas_Biz_Inp_Appl_C1.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_Biz_Inp_GreyList.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_Biz_IdNumber_InDate.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
				    .addAll(step2_Field_Opas_Biz_IdNumber_InDate2.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_Biz_LogicalCheck.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_Inner_RiskInfo_List.macheField(appId, apply_Info1, apply_Info2, appType));
			listriskcheckresult
					.addAll(step2_Field_Opas_Biz_C6_Resi_Taxid.macheField(appId, apply_Info1, apply_Info2, appType));
		}

		if (logger.isInfoEnabled()) {
			logger.info("[黑名单] 风险检查步骤完成" + "  流水号:" + appId);
		}

		saveRiskCheckResult(appId, listriskcheckresult, apply_Info1);
		listriskcheckresult.clear();
		if (logger.isInfoEnabled()) {
			logger.info("[黑名单] 风险检查结果保存成功" + "  流水号:" + appId);
		}
	}

	public boolean saveRiskCheckResult(String appId, List<RiskCheckResult> listriskcheckresult,
			RiskCheck_Apply_C1 apply_Info1) throws CoreException {
		String queryAppId = appId;
		if (apply_Info1 != null) {
			if(("1").equals(apply_Info1.getYdjFlag())){
				if("1".equals(apply_Info1.getMatchingCardFlag())){
					queryAppId = appId.substring(15, 16)=="1"?appId.substring(0, appId.length() - 1) + "2" : appId.substring(0, appId.length() - 1) + "1";
				} else {
					queryAppId = appId;
				}
			}else {
				queryAppId = appId;
			}
		}
		// 判断数据库是否已经存在对应appid记录，没有新增，有则修改
		Map<String, String> oldriskinfo = getRiskCheckService().Query_checkResult(queryAppId);
		KeyfiledMarchinfo keyFiledMarchInfo = new KeyfiledMarchinfo();
		if (oldriskinfo != null && oldriskinfo.size() >= 0) {// 存在记录
			keyFiledMarchInfo.setAppId(oldriskinfo.get("appId"));
			// 将原存在结果集反序列成对象
			List<RiskCheckResult> listresult = null;
			if (oldriskinfo.get("marchResult") != null) {
				listresult = JsonUtil.String2ObjectsList(oldriskinfo.get("marchResult"), RiskCheckResult.class);
			}
			List<RiskCheckResult> listresult2 = new ArrayList<RiskCheckResult>();
			List<RiskCheckResult> listresult3 = new ArrayList<RiskCheckResult>();
			if (listresult == null)
				listresult = new ArrayList<RiskCheckResult>();
			// 2017-4-05 --修改
			for (RiskCheckResult riskCheckResult : listriskcheckresult) {
				if (!listresult.contains(riskCheckResult)) {// 原结果集不包含当前的结果
					listresult3.add(riskCheckResult);// 记录需要新加的结果
				} else {// 包含
					for (RiskCheckResult riskCheckResultOld : listresult) {
						if (riskCheckResult.equals(riskCheckResultOld)) {
							listresult2.add(riskCheckResultOld);// 记录需要删除的结果集
							listresult3.add(riskCheckResult);
						}
					}
				}

			}
			// 替换原来存在的结果 删除重复的结果
			listresult.removeAll(listresult2);
			listresult.addAll(listresult3);
			// listresult.addAll(listriskcheckresult);
			// 结果序列化成字符串保存到数据库
			JSONArray jarray = JSONArray.fromObject(listresult);
			keyFiledMarchInfo.setMarchResult(jarray.toString());
			keyFiledMarchInfo.setCrtDate(new Date());
			keyFiledMarchInfo.setCrtUser("SYSTEM");
			// 更新数据库记录
			riskCheckService.update_checkResult(keyFiledMarchInfo);
		} else {
			keyFiledMarchInfo.setAppId(queryAppId);
			keyFiledMarchInfo.setCrtUser("SYSTEM");
			keyFiledMarchInfo.setCrtDate(new Date());
			// 结果序列化
			// -------- 开始-----------------
			ArrayList<RiskCheckResult> lischeck = new ArrayList<RiskCheckResult>();
			lischeck.addAll(listriskcheckresult);
			JSONArray jarray = JSONArray.fromObject(lischeck);
			keyFiledMarchInfo.setMarchResult(jarray.toString());
			keyFiledMarchInfo.setAutoId(StringUtil.randomUUIDPlainString());
			// --------结束------------------
			// 保存入库
			riskCheckService.save_checkResult(keyFiledMarchInfo);
		}
		return true;

	}

	public RiskCheckService getRiskCheckService() {
		return riskCheckService;
	}

	public void setRiskCheckService(RiskCheckService riskCheckService) {
		this.riskCheckService = riskCheckService;
	}

	public RiskFieldMachService getStep1_field_Opas_Identity_RiskList() {
		return step1_field_Opas_Identity_RiskList;
	}

	public void setStep1_field_Opas_Identity_RiskList(RiskFieldMachService step1_field_Opas_Identity_RiskList) {
		this.step1_field_Opas_Identity_RiskList = step1_field_Opas_Identity_RiskList;
	}

	public RiskFieldMachService getStep1_field_Opas_Company_RiskList() {
		return step1_field_Opas_Company_RiskList;
	}

	public void setStep1_field_Opas_Company_RiskList(RiskFieldMachService step1_field_Opas_Company_RiskList) {
		this.step1_field_Opas_Company_RiskList = step1_field_Opas_Company_RiskList;
	}

	public RiskFieldMachService getStep1_field_Opas_Tel_RiskList() {
		return step1_field_Opas_Tel_RiskList;
	}

	public void setStep1_field_Opas_Tel_RiskList(RiskFieldMachService step1_field_Opas_Tel_RiskList) {
		this.step1_field_Opas_Tel_RiskList = step1_field_Opas_Tel_RiskList;
	}

	public RiskFieldMachService getStep1_field_Opas_Addr_RiskList() {
		return step1_field_Opas_Addr_RiskList;
	}

	public void setStep1_field_Opas_Addr_RiskList(RiskFieldMachService step1_field_Opas_Addr_RiskList) {
		this.step1_field_Opas_Addr_RiskList = step1_field_Opas_Addr_RiskList;
	}

	public RiskFieldMachService getStep1_field_Opas_Promoter_RiskList() {
		return step1_field_Opas_Promoter_RiskList;
	}

	public void setStep1_field_Opas_Promoter_RiskList(RiskFieldMachService step1_field_Opas_Promoter_RiskList) {
		this.step1_field_Opas_Promoter_RiskList = step1_field_Opas_Promoter_RiskList;
	}

	public RiskFieldMachService getStep1_field_Opas_SameIndustry_RiskList() {
		return step1_field_Opas_SameIndustry_RiskList;
	}

	public void setStep1_field_Opas_SameIndustry_RiskList(RiskFieldMachService step1_field_Opas_SameIndustry_RiskList) {
		this.step1_field_Opas_SameIndustry_RiskList = step1_field_Opas_SameIndustry_RiskList;
	}

	public RiskFieldMachService getStep3_Field_Opas_Pboc_Personal_Info() {
		return step3_Field_Opas_Pboc_Personal_Info;
	}

	public void setStep3_Field_Opas_Pboc_Personal_Info(RiskFieldMachService step3_Field_Opas_Pboc_Personal_Info) {
		this.step3_Field_Opas_Pboc_Personal_Info = step3_Field_Opas_Pboc_Personal_Info;
	}

	public RiskFieldMachService getStep3_Field_Opas_Pboc_Profession_Info() {
		return step3_Field_Opas_Pboc_Profession_Info;
	}

	public void setStep3_Field_Opas_Pboc_Profession_Info(RiskFieldMachService step3_Field_Opas_Pboc_Profession_Info) {
		this.step3_Field_Opas_Pboc_Profession_Info = step3_Field_Opas_Pboc_Profession_Info;
	}

	public RiskFieldMachService getStep3_Field_Opas_Pboc_Residence_Info() {
		return step3_Field_Opas_Pboc_Residence_Info;
	}

	public void setStep3_Field_Opas_Pboc_Residence_Info(RiskFieldMachService step3_Field_Opas_Pboc_Residence_Info) {
		this.step3_Field_Opas_Pboc_Residence_Info = step3_Field_Opas_Pboc_Residence_Info;
	}

	public RiskFieldMachService getStep3_Field_Sp_Aps_Ifs_Cust_Info() {
		return step3_Field_Sp_Aps_Ifs_Cust_Info;
	}

	public void setStep3_Field_Sp_Aps_Ifs_Cust_Info(RiskFieldMachService step3_Field_Sp_Aps_Ifs_Cust_Info) {
		this.step3_Field_Sp_Aps_Ifs_Cust_Info = step3_Field_Sp_Aps_Ifs_Cust_Info;
	}

	public RiskFieldMachService getStep2_Field_Opas_Creait_TelCheck_List() {
		return step2_Field_Opas_Creait_TelCheck_List;
	}

	public void setStep2_Field_Opas_Creait_TelCheck_List(RiskFieldMachService step2_Field_Opas_Creait_TelCheck_List) {
		this.step2_Field_Opas_Creait_TelCheck_List = step2_Field_Opas_Creait_TelCheck_List;
	}

	public RiskFieldMachService getStep2_Field_Opas_MajorSchool_List() {
		return step2_Field_Opas_MajorSchool_List;
	}

	public void setStep2_Field_Opas_MajorSchool_List(RiskFieldMachService step2_Field_Opas_MajorSchool_List) {
		this.step2_Field_Opas_MajorSchool_List = step2_Field_Opas_MajorSchool_List;
	}

	public RiskFieldMachService getStep2_Field_Opas_Merch_Teamdeal_List() {
		return step2_Field_Opas_Merch_Teamdeal_List;
	}

	public void setStep2_Field_Opas_Merch_Teamdeal_List(RiskFieldMachService step2_Field_Opas_Merch_Teamdeal_List) {
		this.step2_Field_Opas_Merch_Teamdeal_List = step2_Field_Opas_Merch_Teamdeal_List;
	}

	public RiskFieldMachService getStep2_Field_Opas_SpecialProject_List() {
		return step2_Field_Opas_SpecialProject_List;
	}

	public void setStep2_Field_Opas_SpecialProject_List(RiskFieldMachService step2_Field_Opas_SpecialProject_List) {
		this.step2_Field_Opas_SpecialProject_List = step2_Field_Opas_SpecialProject_List;
	}

	public RiskFieldMachService getStep2_Field_Opas_TargetCompany_List() {
		return step2_Field_Opas_TargetCompany_List;
	}

	public void setStep2_Field_Opas_TargetCompany_List(RiskFieldMachService step2_Field_Opas_TargetCompany_List) {
		this.step2_Field_Opas_TargetCompany_List = step2_Field_Opas_TargetCompany_List;
	}

	public RiskFieldMachService getStep2_Field_Opas_TeamDeal_List() {
		return step2_Field_Opas_TeamDeal_List;
	}

	public void setStep2_Field_Opas_TeamDeal_List(RiskFieldMachService step2_Field_Opas_TeamDeal_List) {
		this.step2_Field_Opas_TeamDeal_List = step2_Field_Opas_TeamDeal_List;
	}

	public RiskFieldMachService getStep2_Field_Opas_Biz_Inp_Appl_C1() {
		return step2_Field_Opas_Biz_Inp_Appl_C1;
	}

	public void setStep2_Field_Opas_Biz_Inp_Appl_C1(RiskFieldMachService step2_Field_Opas_Biz_Inp_Appl_C1) {
		this.step2_Field_Opas_Biz_Inp_Appl_C1 = step2_Field_Opas_Biz_Inp_Appl_C1;
	}

}
