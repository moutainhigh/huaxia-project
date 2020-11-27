package com.huaxia.plaze.ui.pboc.service;

import java.util.List;
import java.util.Map;

/**
 * 人行查询报告服务类
 * 
 * @author zhiguo.li
 *
 */
public interface CreditReportService {

	Map<String, String> queryBaseInfo(String reportNo);

	List<Map<String, String>> queryBaseInfoPhone(String reportNo);

	List<Map<String, String>> queryResidenceInfo(String reportNo);

	List<Map<String, String>> queryProessionInfo(String reportNo);

	Map<String, String> queryNumberRead(String reportNo);

	List<Map<String, String>> queryCreditTransaction(String reportNo);

	List<Map<String, String>> queryOverDue(String reportNo);

	Map<String, String> queryCreditCard(String reportNo);

	List<Map<String, String>> queryOtherIdCard(String reportNo);

	List<Map<String, String>> queryRecoveried(String reportNo);

	Map<String, String> queryBadDebts(String reportNo);

	Map<String, String> queryCycleCreditCard(String reportNo);

	List<Map<String, String>> queryRelateRepay(String reportNo);

	List<Map<String, String>> queryNoCreditLoanSummary(String reportNo);

	List<Map<String, String>> queryPublicMesCollect(String reportNo);

	Map<String, String> queryRecords(String reportNo);

	List<Map<String, Object>> queryUrgeGet(Map<String, Object> parm);

	List<Map<String, Object>> queryNoCycle(Map<String, Object> parm);

	List<Map<String, Object>> queryBaseAndNewstMessage(Map<String, Object> parm);

	Map<String, Object> queryNearMonthMessage(String relateId);

	Map<String, Object> query24MonthMessage(String relateId);

	Map<String, Object> query5YearsMessage(String relateId);

	List<Map<String, Object>> querySpecialEventMessage(String relateId);

	List<Map<String, Object>> querySpecialTrnMessage(String relateId);

	List<Map<String, Object>> queryLableMessage(String relateId);

	List<Map<String, Object>> queryDezxMessage(String relateId);

	List<Map<String, Object>> queryRelateRepayMessage(Map<String, Object> parm);

	List<Map<String, Object>> queryPcaMessage(String reportNo);

	List<Map<String, Object>> queryTelpaymentMessage(String reportNo);

	List<Map<String, Object>> queryTaxarrearMessage(String reportNo);

	List<Map<String, Object>> queryCivjudgeMessage(String reportNo);

	List<Map<String, Object>> queryAdminPunishMessage(String reportNo);

	List<Map<String, Object>> queryAccfundMessage(String reportNo);

	List<Map<String, Object>> queryAalvationMessage(String reportNo);

	List<Map<String, Object>> queryCompetenceMessage(String reportNo);

	List<Map<String, Object>> queryAdminawardMessage(String reportNo);

	List<Map<String, Object>> queryForceexeMessage(String reportNo);

	List<Map<String, Object>> queryPosLabelMessage(Map<String, Object> parm);

	List<Map<String, Object>> queryQueryRecMessage(String reportNo);

	Map<String, String> queryStaCreditCard(String reportNo);

}
