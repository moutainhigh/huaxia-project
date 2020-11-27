package com.huaxia.opas.dao.rule;

import java.util.List;
import java.util.Map;

public interface OpasLoansMsgDao {

//	List<String> queryLoansQueryMsg(String appIdBzk);

	List<Map<String, Object>> queryDateAndCountLimit();

	int queryLoansCount();

	List<String> queryLoansMsg();

	List<Map<String, String>> queryRuleContent();

	List<String> queryLoansQueryYdjMsg(String appIdThd);

	List<String> queryLoansQueryBzkMsg(String appIdThd);

	int queryTodayLoansCount();


}