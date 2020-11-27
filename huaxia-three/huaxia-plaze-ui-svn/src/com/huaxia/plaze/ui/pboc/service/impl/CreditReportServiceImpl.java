package com.huaxia.plaze.ui.pboc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huaxia.plaze.ui.pboc.mapper.CreditReportMapper;
import com.huaxia.plaze.ui.pboc.service.CreditReportService;

@Service("creditReportService")
public class CreditReportServiceImpl implements CreditReportService {

	@Resource
	private CreditReportMapper creditReportMapper;

	@Override
	public Map<String, String> queryBaseInfo(String reportNo) {
		Map<String, String> baseInfo = creditReportMapper.selectBaseInfo(reportNo);
		return baseInfo;
	}

	@Override
	public List<Map<String, String>> queryBaseInfoPhone(String reportNo) {
		List<Map<String, String>> info = creditReportMapper.selectBaseInfoPhone(reportNo);
		return info;
	}

	@Override
	public List<Map<String, String>> queryResidenceInfo(String reportNo) {
		List<Map<String, String>> info = creditReportMapper.selectResidenceInfo(reportNo);
		return info;
	}

	@Override
	public List<Map<String, String>> queryProessionInfo(String reportNo) {
		List<Map<String, String>> info = creditReportMapper.selectProessionInfo(reportNo);
		return info;
	}

	@Override
	public Map<String, String> queryNumberRead(String reportNo) {
		Map<String, String> info = creditReportMapper.selectNumberRead(reportNo);
		return info;
	}

	@Override
	public List<Map<String, String>> queryCreditTransaction(String reportNo) {
		List<Map<String, String>> info = creditReportMapper.selectCreditTransaction(reportNo);
		return info;
	}

	@Override
	public List<Map<String, String>> queryOverDue(String reportNo) {
		List<Map<String, String>> info = creditReportMapper.selectOverDue(reportNo);
		return info;
	}

	@Override
	public Map<String, String> queryCreditCard(String reportNo) {
		Map<String, String> info = creditReportMapper.selectCreditCard(reportNo);
		return info;
	}

	@Override
	public List<Map<String, String>> queryOtherIdCard(String reportNo) {
		List<Map<String, String>> info = creditReportMapper.selectOtherIdCard(reportNo);
		return info;
	}

	@Override
	public List<Map<String, String>> queryRecoveried(String reportNo) {
		List<Map<String, String>> info = creditReportMapper.selectRecoveried(reportNo);
		return info;
	}

	@Override
	public Map<String, String> queryBadDebts(String reportNo) {
		Map<String, String> info = creditReportMapper.selectBadDebts(reportNo);
		return info;
	}

	@Override
	public Map<String, String> queryCycleCreditCard(String reportNo) {
		Map<String, String> info = creditReportMapper.selectCycleCreditCard(reportNo);
		return info;
	}

	@Override
	public List<Map<String, String>> queryRelateRepay(String reportNo) {
		List<Map<String, String>> info = creditReportMapper.selectRelateRepay(reportNo);
		return info;
	}

	@Override
	public List<Map<String, String>> queryNoCreditLoanSummary(String reportNo) {
		List<Map<String, String>> info = creditReportMapper.selectNoCreditLoanSummary(reportNo);
		return info;
	}

	@Override
	public List<Map<String, String>> queryPublicMesCollect(String reportNo) {
		List<Map<String, String>> info = creditReportMapper.selectPublicMesCollect(reportNo);
		return info;
	}

	@Override
	public Map<String, String> queryRecords(String reportNo) {
		Map<String, String> info = creditReportMapper.selectRecords(reportNo);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryUrgeGet(Map<String, Object> parm) {
		List<Map<String, Object>> info = creditReportMapper.selectUrgeGet(parm);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryNoCycle(Map<String, Object> parm) {
		List<Map<String, Object>> info = creditReportMapper.selectNoCycle(parm);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryBaseAndNewstMessage(Map<String, Object> parm) {
		List<Map<String, Object>> info = creditReportMapper.selectBaseAndNewstMessage(parm);
		return info;
	}

	@Override
	public Map<String, Object> queryNearMonthMessage(String relateId) {
		Map<String, Object> info = creditReportMapper.selectNearMonthMessage(relateId);
		return info;
	}

	@Override
	public Map<String, Object> query24MonthMessage(String relateId) {
		Map<String, Object> info = creditReportMapper.select24MonthMessage(relateId);
		return info;
	}

	@Override
	public Map<String, Object> query5YearsMessage(String relateId) {

		Map<String, Object> info = creditReportMapper.select5YearsMessage(relateId);
		return info;
	}

	@Override
	public List<Map<String, Object>> querySpecialEventMessage(String relateId) {
		List<Map<String, Object>> info = creditReportMapper.selectSpecialEventMessage(relateId);
		return info;
	}

	@Override
	public List<Map<String, Object>> querySpecialTrnMessage(String relateId) {
		List<Map<String, Object>> info = creditReportMapper.selectSpecialTrnMessage(relateId);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryLableMessage(String relateId) {
		List<Map<String, Object>> info = creditReportMapper.selectLableMessage(relateId);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryDezxMessage(String relateId) {
		List<Map<String, Object>> info = creditReportMapper.selectDezxMessage(relateId);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryRelateRepayMessage(Map<String, Object> parm) {
		List<Map<String, Object>> info = creditReportMapper.selectRelateRepayMessage(parm);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryPcaMessage(String reportNo) {
		List<Map<String, Object>> info = creditReportMapper.selectPcaMessage(reportNo);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryTelpaymentMessage(String reportNo) {
		List<Map<String, Object>> info = creditReportMapper.selectTelpaymentMessage(reportNo);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryTaxarrearMessage(String reportNo) {
		List<Map<String, Object>> info = creditReportMapper.selectTaxarrearMessage(reportNo);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryCivjudgeMessage(String reportNo) {
		List<Map<String, Object>> info = creditReportMapper.selectCivjudgeMessage(reportNo);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryAdminPunishMessage(String reportNo) {
		List<Map<String, Object>> info = creditReportMapper.selectAdminPunishMessage(reportNo);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryAccfundMessage(String reportNo) {
		List<Map<String, Object>> info = creditReportMapper.selectAccfundMessage(reportNo);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryAalvationMessage(String reportNo) {
		List<Map<String, Object>> info = creditReportMapper.selectAalvationMessage(reportNo);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryCompetenceMessage(String reportNo) {
		List<Map<String, Object>> info = creditReportMapper.selectCompetenceMessage(reportNo);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryAdminawardMessage(String reportNo) {
		List<Map<String, Object>> info = creditReportMapper.selectAdminawardMessage(reportNo);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryForceexeMessage(String reportNo) {
		List<Map<String, Object>> info = creditReportMapper.selectForceexeMessage(reportNo);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryPosLabelMessage(Map<String, Object> parm) {
		List<Map<String, Object>> info = creditReportMapper.selectPosLabelMessage(parm);
		return info;
	}

	@Override
	public List<Map<String, Object>> queryQueryRecMessage(String reportNo) {
		List<Map<String, Object>> info = creditReportMapper.selectQueryRecMessage(reportNo);
		return info;
	}

	@Override
	public Map<String, String> queryStaCreditCard(String reportNo) {
		Map<String, String> info = creditReportMapper.selectStaCreditCard(reportNo);
		return info;
	}

}
