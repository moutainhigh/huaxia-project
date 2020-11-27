package com.huaxia.plaze.ui.pboc.mapper;

import java.util.List;
import java.util.Map;

public interface CreditReportMapper {

	Map<String, String> selectBaseInfo(String reportNo);
	
	Map<String, String> selectNumberRead(String reportNo);
	
	Map<String, String> selectCreditCard(String reportNo);

	Map<String, String> selectBadDebts(String reportNo);

	Map<String, String> selectCycleCreditCard(String reportNo);
	
	Map<String, String> selectRecords(String reportNo);
	
	Map<String, Object> selectNearMonthMessage(String relateId);

	Map<String, Object> select24MonthMessage(String relateId);

	Map<String, Object> select5YearsMessage(String relateId);
	
	Map<String, String> selectStaCreditCard(String reportNo);
	
	List<Map<String, String>> selectBaseInfoPhone(String reportNo);

	List<Map<String, String>> selectResidenceInfo(String reportNo);

	List<Map<String, String>> selectProessionInfo(String reportNo);

	List<Map<String, String>> selectCreditTransaction(String reportNo);

	List<Map<String, String>> selectOverDue(String reportNo);

	List<Map<String, String>> selectOtherIdCard(String reportNo);

	List<Map<String, String>> selectRecoveried(String reportNo);

	List<Map<String, String>> selectRelateRepay(String reportNo);

	List<Map<String, String>> selectNoCreditLoanSummary(String reportNo);

	List<Map<String, String>> selectPublicMesCollect(String reportNo);

	List<Map<String, Object>> selectUrgeGet(Map<String, Object> parm);

	List<Map<String, Object>> selectNoCycle(Map<String, Object> parm);

	List<Map<String, Object>> selectBaseAndNewstMessage(Map<String, Object> parm);

	List<Map<String, Object>> selectSpecialEventMessage(String relateId);

	List<Map<String, Object>> selectSpecialTrnMessage(String relateId);

	List<Map<String, Object>> selectLableMessage(String relateId);

	List<Map<String, Object>> selectDezxMessage(String relateId);

	List<Map<String, Object>> selectRelateRepayMessage(Map<String, Object> parm);

	List<Map<String, Object>> selectPcaMessage(String reportNo);

	List<Map<String, Object>> selectTelpaymentMessage(String reportNo);

	List<Map<String, Object>> selectTaxarrearMessage(String reportNo);

	List<Map<String, Object>> selectCivjudgeMessage(String reportNo);

	List<Map<String, Object>> selectForceexeMessage(String reportNo);

	List<Map<String, Object>> selectAccfundMessage(String reportNo);

	List<Map<String, Object>> selectAalvationMessage(String reportNo);

	List<Map<String, Object>> selectCompetenceMessage(String reportNo);

	List<Map<String, Object>> selectAdminawardMessage(String reportNo);

	List<Map<String, Object>> selectAdminPunishMessage(String reportNo);

	List<Map<String, Object>> selectPosLabelMessage(Map<String, Object> parm);

	List<Map<String, Object>> selectQueryRecMessage(String reportNo);

}
