package com.huaxia.plaze.ui.pboc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaxia.plaze.common.Cache;
import com.huaxia.plaze.ui.pboc.service.CreditReportService;
import com.huaxia.plaze.ui.setting.service.PbocQueryService;
import com.huaxia.plaze.ui.system.domain.User;
import com.huaxia.util.CacheUtil;
import com.huaxia.util.JacksonUtil;

/**
 * 人行征信报告
 * 
 * @author zhiguo.li
 *
 */
@Controller
@RequestMapping("pboc/report")
public class CreditReportController {

	private static Logger logger = LoggerFactory.getLogger(CreditReportController.class);

	// 人行查询设置
	@Resource
	private PbocQueryService pbocQueryService;

	@Resource
	private CreditReportService creditReportService;

	@RequestMapping(value = "queryResult", method = RequestMethod.GET)
	public String queryResult(String uniqueId, ModelMap modelMap, HttpServletRequest request) {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		try {
			User loginUser = JacksonUtil.jsonToObject(body, User.class);
			modelMap.put("uniqueId", uniqueId);
			modelMap.put("staffName", loginUser.getStaffName());
			modelMap.put("account", loginUser.getAccount());
		} catch (IOException e) {
			logger.error("JSON转换对象异常:{}", new Object[] { e.getMessage() });
		}

		// 手机号码列表
		List<Map<String, String>> idCard = creditReportService.queryOtherIdCard(uniqueId);
		modelMap.put("idCardList", idCard);
		List<Map<String, String>> phone = creditReportService.queryBaseInfoPhone(uniqueId);
		modelMap.put("phoneList", phone);
		List<Map<String, String>> residenceInfo = creditReportService.queryResidenceInfo(uniqueId);
		modelMap.put("residenceInfoList", residenceInfo);
		List<Map<String, String>> proessionInfo = creditReportService.queryProessionInfo(uniqueId);
		modelMap.put("proessionInfoList", proessionInfo);

		return "pboc/pboc_report_view";
	}

	// 查询基本信息
	@RequestMapping("queryBaseInfo")
	@ResponseBody
	public Map<String, String> queryBaseInfo(HttpServletRequest request, String uniqueId, ModelMap modelMap)
			throws Exception {
		String account = CacheUtil.get(Cache.NAMESPACE + ":USER:" + request.getSession().getId());
		String body = CacheUtil.get(Cache.NAMESPACE + ":USER:" + account);
		User loginUser = JacksonUtil.jsonToObject(body, User.class);

		Map<String, String> baseInfo = creditReportService.queryBaseInfo(uniqueId);
		if (!loginUser.getAccount().equals("hxxykyaow")) {
			// 被查询人姓名
			baseInfo.put("queryedName", nameMask(baseInfo.get("queryedName")));
			// 被查询证件
			baseInfo.put("queryedCertNo", CertNoMask(baseInfo.get("queryedCertNo")));
			// 配偶姓名
			baseInfo.put("mateName", nameMask(baseInfo.get("mateName")));
			// 配偶证件号
			baseInfo.put("mateCertNo", CertNoMask(baseInfo.get("mateCertNo")));

		}
		return baseInfo;
	}

	public static String nameMask(String name) {

		if (name.length() < 3 && name.length() > 0) {
			return name.substring(0, 1) + "*";
		} else if (name.length() >= 3) {
			return name.charAt(0) + "*" + name.charAt(name.length() - 1);
		}
		return null;
	}

	public String CertNoMask(String certNo) {
		int asteriskCount = certNo.length() - 4;
		StringBuffer asteriskStr = new StringBuffer();
		for (int i = 0; i < asteriskCount; i++) {
			asteriskStr.append("*");
		}
		String regex = "(\\w{" + String.valueOf(2) + "})(\\w+)(\\w{" + String.valueOf(2) + "})";
		return certNo.replaceAll(regex, "$1" + asteriskStr + "$3");

	}

	// 查询数字解读
	@RequestMapping("queryNumberRead")
	@ResponseBody
	public Map<String, String> queryNumberRead(HttpServletRequest request, String uniqueId, ModelMap modelMap)
			throws Exception {
		Map<String, String> baseInfo = creditReportService.queryNumberRead(uniqueId);
		return baseInfo;
	}

	// 查询信贷交易明细
	@RequestMapping("queryCreditTransaction")
	@ResponseBody
	public Map<String, Object> queryCreditTransaction(HttpServletRequest request, String uniqueId, ModelMap modelMap)
			throws Exception {
		List<Map<String, String>> info = creditReportService.queryCreditTransaction(uniqueId);
		String serviceType = null;
		String accountCount = null;
		String firstYwGrantMonth = null;
		int accountAmount = 0;
		Map<String, Object> infoMap = new HashMap<>();
		if (info != null && info.size() != 0 && info.get(0) != null) {
			for (int i = 0; i < info.size(); i++) {
				serviceType = info.get(i).get("serviceType");
				accountCount = info.get(i).get("accountCount");
				firstYwGrantMonth = info.get(i).get("firstYwGrantMonth");
				if ("11".equals(serviceType)) {
					infoMap.put("houseLoan", accountCount);
					infoMap.put("houseFirstMonth", firstYwGrantMonth);
				} else if ("12".equals(serviceType)) {
					infoMap.put("businessLoad", accountCount);
					infoMap.put("businessFirstMonth", firstYwGrantMonth);
				} else if ("19".equals(serviceType)) {
					infoMap.put("otherLoad", accountCount);
					infoMap.put("otherFirstMonth", firstYwGrantMonth);
				} else if ("21".equals(serviceType)) {
					infoMap.put("loanCard", accountCount);
					infoMap.put("loanCardFirstMonth", firstYwGrantMonth);
				} else if ("22".equals(serviceType)) {
					infoMap.put("standardLoancard", accountCount);
					infoMap.put("standardFirstMonth", firstYwGrantMonth);
				} else if ("99".equals(serviceType)) {
					infoMap.put("otherTrn", accountCount);
					infoMap.put("otherTrnFirstMonth", firstYwGrantMonth);
				}
				accountAmount += Integer.parseInt(accountCount);
			}
		}
		infoMap.put("acountAmount", accountAmount);
		return infoMap;
	}

	// 信贷交易违约信息概要
	@RequestMapping("queryOverDue")
	@ResponseBody
	public Map<String, Object> queryOverDue(HttpServletRequest request, String uniqueId, ModelMap modelMap)
			throws Exception {
		List<Map<String, String>> info = creditReportService.queryOverDue(uniqueId);
		List<Map<String, String>> infoRecoveried = creditReportService.queryRecoveried(uniqueId);
		Map<String, String> infoBad = creditReportService.queryBadDebts(uniqueId);
		String accountType = null;
		String accountCount = null;
		String monthCount = null;
		String maxOverdue = null;
		String maxOverdueMonth = null;
		Map<String, Object> infoMap = new HashMap<>();
		if (info != null && info.size() != 0 && info.get(0) != null) {
			for (int i = 0; i < info.size(); i++) {
				accountType = info.get(i).get("accountType");
				accountCount = info.get(i).get("accountCount");
				monthCount = info.get(i).get("monthCount");
				maxOverdue = info.get(i).get("maxOverdue");
				maxOverdueMonth = info.get(i).get("maxOverdueMonth");
				if ("1".equals(accountType)) {
					infoMap.put("noCycleAccount", accountCount);
					infoMap.put("noCycleMonth", monthCount);
					infoMap.put("noCycleOverDue", maxOverdue);
					infoMap.put("noCycleOverDueMonth", maxOverdueMonth);
				} else if ("2".equals(accountType)) {
					infoMap.put("cycleSepAccount", accountCount);
					infoMap.put("cycleSepMonth", monthCount);
					infoMap.put("cycleSepOverDue", maxOverdue);
					infoMap.put("cycleSepOverDueMonth", maxOverdueMonth);
				} else if ("3".equals(accountType)) {
					infoMap.put("cycleAccount", accountCount);
					infoMap.put("cycleMonth", monthCount);
					infoMap.put("cycleOverDue", maxOverdue);
					infoMap.put("cycleOverDueMonth", maxOverdueMonth);
				} else if ("4".equals(accountType)) {
					infoMap.put("loanCardAccount", accountCount);
					infoMap.put("loanCardMonth", monthCount);
					infoMap.put("loanCardMonthOverDue", maxOverdue);
					infoMap.put("loanCardDueMonth", maxOverdueMonth);
				} else if ("5".equals(accountType)) {
					infoMap.put("staLoanCardAccount", accountCount);
					infoMap.put("staLoanCardMonth", monthCount);
					infoMap.put("staLoanCardMonthOverDue", maxOverdue);
					infoMap.put("staLoanCardDueMonth", maxOverdueMonth);
				}
			}
		}
		String serviceType = null;
		String balance = null;
		Integer serviceAccountTotal = 0;
		Integer serviceBaTotal = 0;
		if (infoRecoveried != null && infoRecoveried.size() != 0 && infoRecoveried.get(0) != null) {
			for (int i = 0; i < infoRecoveried.size(); i++) {
				serviceType = infoRecoveried.get(i).get("serviceType");
				accountCount = infoRecoveried.get(i).get("accountCount");
				balance = infoRecoveried.get(i).get("balance");
				serviceAccountTotal += Integer.parseInt(accountCount);
				serviceBaTotal += Integer.parseInt(balance);

				if ("1".equals(serviceType)) {
					infoMap.put("assetsServiceAccount", accountCount);
					infoMap.put("assetsServiceBa", balance);

				} else if ("2".equals(serviceType)) {
					infoMap.put("endServiceAccount", accountCount);
					infoMap.put("endServiceBa", balance);
				}
			}
		}
		infoMap.put("serviceAccountTotal", serviceAccountTotal);
		infoMap.put("serviceBaTotal", serviceBaTotal);
		if (infoBad != null) {
			infoMap.put("badDebtsAccount", infoBad.get("accountCount"));
			infoMap.put("badDebtsBa", infoBad.get("balance"));
		}
		return infoMap;
	}

	// 信贷交易负债信息概要
	@RequestMapping("queryCreditTranAndDebt")
	@ResponseBody
	public Map<String, Object> queryCreditCard(String uniqueId) throws Exception {
		Map<String, String> infoCycle = creditReportService.queryCycleCreditCard(uniqueId);
		Map<String, String> infoCredit = creditReportService.queryCreditCard(uniqueId);
		Map<String, String> infoStaCredit = creditReportService.queryStaCreditCard(uniqueId);
		List<Map<String, String>> repayList = creditReportService.queryRelateRepay(uniqueId);
		Map<String, String> repay = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		String identityType = null;
		String dutyType = null;
		if (repayList != null && repayList.size() != 0 && repayList.get(0) != null) {
			for (int i = 0; i < repayList.size(); i++) {
				identityType = repayList.get(i).get("identityType");
				dutyType = repayList.get(i).get("dutyType");
				dutyType = repayList.get(i).get("accountCount");
				dutyType = repayList.get(i).get("dutyType");
				dutyType = repayList.get(i).get("dutyType");
				// 为个人
				if ("1".equals(identityType)) {
					// 担保责任
					if ("1".equals(dutyType)) {
						repay.put("peEnAccountCount", repayList.get(i).get("accountCount"));
						repay.put("peEnRepay", repayList.get(i).get("repayDutyMoney"));
						repay.put("peEnBalance", repayList.get(i).get("balance"));
					} else if ("9".equals(dutyType)) {
						repay.put("peReAccountCount", repayList.get(i).get("accountCount"));
						repay.put("peReRepay", repayList.get(i).get("repayDutyMoney"));
						repay.put("peReBalance", repayList.get(i).get("balance"));
					}
				}
				if ("2".equals(identityType)) {
					// 担保责任
					if ("1".equals(dutyType)) {
						repay.put("coEnAccountCount", repayList.get(i).get("accountCount"));
						repay.put("coEnRepay", repayList.get(i).get("repayDutyMoney"));
						repay.put("coEnBalance", repayList.get(i).get("balance"));
					} else if ("9".equals(dutyType)) {
						repay.put("coReAccountCount", repayList.get(i).get("accountCount"));
						repay.put("coReRepay", repayList.get(i).get("repayDutyMoney"));
						repay.put("coReBalance", repayList.get(i).get("balance"));
					}

				}

			}
		}
		info.put("infoCycle", infoCycle);
		info.put("infoCredit", infoCredit);
		info.put("infoStaCredit", infoStaCredit);
		info.put("repay", repay);
		return info;
	}

	// 非信贷交易信息概要
	@RequestMapping("queryNoCreditLoanSummary")
	@ResponseBody
	public List<Map<String, String>> queryNoCreditLoanSummary(String uniqueId) throws Exception {
		List<Map<String, String>> info = creditReportService.queryNoCreditLoanSummary(uniqueId);

		return info;
	}

	// 公共信息概要
	@RequestMapping("queryPublicMesCollect")
	@ResponseBody
	public List<Map<String, String>> queryPublicMesCollect(String uniqueId) throws Exception {
		List<Map<String, String>> info = creditReportService.queryPublicMesCollect(uniqueId);
		return info;
	}

	// 查询记录概要
	@RequestMapping("queryRecords")
	@ResponseBody
	public Map<String, String> queryRecords(String uniqueId) throws Exception {
		Map<String, String> info = creditReportService.queryRecords(uniqueId);
		return info;
	}

	// 信贷交易信息明细
	@RequestMapping("queryUrgeGet")
	@ResponseBody
	public List<Map<String, Object>> queryUrgeGet(String uniqueId, String accountType) throws Exception {
		Map<String, Object> parm = new HashMap<>();
		List<Map<String, Object>> info = null;
		parm.put("uniqueId", uniqueId);
		parm.put("accountType", accountType);
		info = creditReportService.queryBaseAndNewstMessage(parm);
		for (int i = 0; i < info.size(); i++) {
			String relateId = (String) info.get(i).get("RELATE_ID");
			String status = (String) info.get(i).get("ACCOUNT_STATUS");
			String activeStatus = getActiveStatus(accountType, status);
			info.get(i).put("activeStatus", activeStatus);
			info.get(i).put("accountType", accountType);
			if (!"C1".equals(accountType)) {
				if ("持续更新".equals(activeStatus)) {
					Map<String, Object> nearMonth = creditReportService.queryNearMonthMessage(relateId);
					info.get(i).put("nearMonth", nearMonth);
					if ("R2".equals(accountType)) {
						List<Map<String, Object>> Dezx = creditReportService.queryDezxMessage(relateId);
						info.get(i).put("Dezx", Dezx);
					}
				}
				if (!"未激活".equals(accountType)) {
					Map<String, Object> month24 = creditReportService.query24MonthMessage(relateId);
					Map<String, Object> years5 = creditReportService.query5YearsMessage(relateId);
					info.get(i).put("month24", month24);
					info.get(i).put("years5", years5);
				}
				if ("持续更新".equals(activeStatus) && ("D1".equals(accountType) || "R2".equals(accountType))) {
					List<Map<String, Object>> specailEvent = creditReportService.querySpecialEventMessage(relateId);
					info.get(i).put("specailEvent", specailEvent);

				}

			}
			List<Map<String, Object>> specailTrn = creditReportService.querySpecialTrnMessage(relateId);
			info.get(i).put("specailTrn", specailTrn);
			List<Map<String, Object>> label = creditReportService.queryLableMessage(relateId);
			info.get(i).put("label", label);
		}

		return info;
	}

	// 相关还款责任人借款
	@RequestMapping("queryRelateRepayMessage")
	@ResponseBody
	public List<Map<String, Object>> queryRelateRepayMessage(String uniqueId, String identityType) {
		Map<String, Object> parm = new HashMap<>();
		parm.put("uniqueId", uniqueId);
		parm.put("identityType", identityType);
		List<Map<String, Object>> info = creditReportService.queryRelateRepayMessage(parm);
		/*
		 * for (int i = 0; i < info.size(); i++) { String relateId = (String)
		 * info.get(i).get("RELATE_ID"); List<Map<String, Object>> label =
		 * creditReportService.queryLableMessage(relateId);
		 * info.get(i).put("label", label); }
		 */
		return info;
	}

	// 授信协议信息
	@RequestMapping("queryPcaMessage")
	@ResponseBody
	public List<Map<String, Object>> queryPcaMessage(String uniqueId) {
		List<Map<String, Object>> info = creditReportService.queryPcaMessage(uniqueId);

		return info;
	}

	// 后付费记录
	@RequestMapping("queryTelpaymentMessage")
	@ResponseBody
	public List<Map<String, Object>> queryTelpaymentMessage(String uniqueId) {
		List<Map<String, Object>> info = creditReportService.queryTelpaymentMessage(uniqueId);

		return info;
	}

	// 欠税记录
	@RequestMapping("queryTaxarrearMessage")
	@ResponseBody
	public List<Map<String, Object>> queryTaxarrearMessage(String uniqueId) {
		List<Map<String, Object>> info = creditReportService.queryTaxarrearMessage(uniqueId);
		return info;
	}

	// 民事判决记录
	@RequestMapping("queryCivjudgeMessage")
	@ResponseBody
	public List<Map<String, Object>> queryCivjudgeMessage(String uniqueId) {
		List<Map<String, Object>> info = creditReportService.queryCivjudgeMessage(uniqueId);
		return info;
	}

	// 强制执行记录
	@RequestMapping("queryForceexeMessage")
	@ResponseBody
	public List<Map<String, Object>> queryForceexeMessage(String uniqueId) {
		List<Map<String, Object>> info = creditReportService.queryForceexeMessage(uniqueId);
		return info;
	}

	// 行政处罚记录
	@RequestMapping("queryAdminPunishMessage")
	@ResponseBody
	public List<Map<String, Object>> queryAdminPunishMessage(String uniqueId) {
		List<Map<String, Object>> info = creditReportService.queryAdminPunishMessage(uniqueId);
		return info;
	}

	// 住房公积金参缴记录
	@RequestMapping("queryAccfundMessage")
	@ResponseBody
	public List<Map<String, Object>> queryAccfundMessage(String uniqueId) {
		List<Map<String, Object>> info = creditReportService.queryAccfundMessage(uniqueId);
		return info;
	}

	// 低保救助记录
	@RequestMapping("queryAalvationMessage")
	@ResponseBody
	public List<Map<String, Object>> queryAalvationMessage(String uniqueId) {
		List<Map<String, Object>> info = creditReportService.queryAalvationMessage(uniqueId);
		return info;
	}

	// 执业资格记录
	@RequestMapping("queryCompetenceMessage")
	@ResponseBody
	public List<Map<String, Object>> queryCompetenceMessage(String uniqueId) {
		List<Map<String, Object>> info = creditReportService.queryCompetenceMessage(uniqueId);

		return info;
	}

	// 行政奖励记录
	@RequestMapping("queryAdminawardMessage")
	@ResponseBody
	public List<Map<String, Object>> queryAdminawardMessage(String uniqueId) {
		List<Map<String, Object>> info = creditReportService.queryAdminawardMessage(uniqueId);

		return info;
	}

	// 本人声明 和异议标注
	@RequestMapping("queryPosLabelMessage")
	@ResponseBody
	public List<Map<String, Object>> queryPosLabelMessage(String uniqueId, String type) {
		Map<String, Object> parm = new HashMap<>();
		parm.put("uniqueId", uniqueId);
		parm.put("type", type);
		List<Map<String, Object>> info = creditReportService.queryPosLabelMessage(parm);

		return info;
	}

	// 查询记录
	@RequestMapping("queryQueryRecMessage")
	@ResponseBody
	public List<Map<String, Object>> queryQueryRecMessage(String uniqueId) {
		List<Map<String, Object>> info = creditReportService.queryQueryRecMessage(uniqueId);

		return info;
	}

	// 账户状态转换
	private String getActiveStatus(String accountType, String status) {
		if (("R2".equals(accountType) || "R3".equals(accountType)) && "6".equals(status)) {
			return "未激活";
		}
		if ("D1".equals(accountType) && ("3".equals(status) || "5".equals(status))) {
			return "关闭";
		}
		if (("R1".equals(accountType) || "R4".equals(accountType)) && "3".equals(status)) {
			return "关闭";
		}
		if (("R2".equals(accountType) || "R3".equals(accountType)) && "4".equals(status)) {
			return "关闭";
		}
		if (("C1".equals(accountType) && "2".equals(status))) {
			return "关闭";
		}
		if (("D1".equals(accountType) || "R1".equals(accountType) || "R4".equals(accountType)) && "4".equals(status)) {
			return "呆账";
		}
		if (("R2".equals(accountType) || "R3".equals(accountType)) && "5".equals(status)) {
			return "呆账";
		}
		return "持续更新";
	}
}
