package com.huaxia.opas.dao.rule.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.rule.OpasLoansMsgDao;

public class OpasLoansMsgDaoImpl extends AbstractDAO implements OpasLoansMsgDao ,Serializable{

	@Override
	public List<String> queryLoansQueryYdjMsg(String appIdThd) {
		return getSqlMap().queryForList("OpasLoansMsg.queryLoansQueryYdjMsg", appIdThd);
	}

	@Override
	public List<String> queryLoansQueryBzkMsg(String appIdThd) {
		return getSqlMap().queryForList("OpasLoansMsg.queryLoansQueryBzkMsg", appIdThd);
	}
	
	@Override
	public List<Map<String, Object>> queryDateAndCountLimit() {
		return getSqlMap().queryForList("OpasLoansMsg.queryDateAndCountLimit");
	}

	@Override
	public int queryLoansCount() {
		return getSqlMap().queryForObject("OpasLoansMsg.queryLoansCount");
	}
	
	@Override
	public int queryTodayLoansCount() {
		return getSqlMap().queryForObject("OpasLoansMsg.queryTodayLoansCount");
	}

	@Override
	public List<String> queryLoansMsg() {
		return getSqlMap().queryForList("OpasLoansMsg.queryLoansMsg");
	}

	@Override
	public List<Map<String, String>> queryRuleContent() {
		return getSqlMap().queryForList("OpasLoansMsg.queryRuleContent");
	}

}
