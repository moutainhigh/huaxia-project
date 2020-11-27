package com.huaxia.opas.dao.decision.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.decision.OpasConfQuestionDao;
import com.huaxia.opas.domain.thirdparty.OpasConfQuestion;

public class OpasConfQuestionDaoImpl extends AbstractDAO implements OpasConfQuestionDao {

	private static final long serialVersionUID = 2757582418545513971L;

	private static final String NAMESPACES = "OpasConfQuestion.";
	private static final String SYSRESULTINFO_NAMESPACES_BZK = "OpaBzkSysResultInfoMapper.";//主卡决策结果map
	private static final String SYSRESULTINFO_NAMESPACES_YDJ = "YdjSysresultInfo.";//主卡决策结果map


	@Override
	public int deleteByPrimaryKey(String autoId) {
		return getSqlMap().delete(NAMESPACES + "deleteByPrimaryKey", autoId);
	}

	@Override
	public int insert(OpasConfQuestion record) {
		return getSqlMap().insert(NAMESPACES + "insert", record);
	}

	@Override
	public int insertSelective(OpasConfQuestion record) {
		return getSqlMap().insert(NAMESPACES + "insertSelective", record);
	}

	@Override
	public OpasConfQuestion selectByPrimaryKey(String autoId) {
		return (OpasConfQuestion) (getSqlMap().queryForObject(NAMESPACES + "selectByPrimaryKey", autoId));
	}

	@Override
	public int updateByPrimaryKeySelective(OpasConfQuestion record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(OpasConfQuestion record) {
		return getSqlMap().update(NAMESPACES + "updateByPrimaryKey", record);
	}

	@Override
	public int queryConfQuestionCount() {
		return getSqlMap().queryForObject(NAMESPACES + "updateByPrimaryKey");
	}

	@Override
	public List<OpasConfQuestion> queryConfQuestion(String rownum) {
		List<OpasConfQuestion> list = new ArrayList<OpasConfQuestion>();
		list = getSqlMap().queryForList(NAMESPACES + "queryConfQuestion", rownum);
		return list;
	}

	@Override
	public List<OpasConfQuestion> queryConfQuestion(List<String> lists) {
		List<OpasConfQuestion> list = new ArrayList<OpasConfQuestion>();
		list = getSqlMap().queryForList(NAMESPACES + "queryConfQuestion", lists);
		return list;
	}

	@Override
	public String selectReultByAppId(String appId) {
		return getSqlMap().queryForObject(SYSRESULTINFO_NAMESPACES_BZK + "selectResultByAppId", appId);
	}

	@Override
	public String queryConfQuestionCount(List<String> questionLevel) {
		return getSqlMap().queryForObject(NAMESPACES + "queryConfQuestionCountLevel", questionLevel);
	}

	@Override
	public String selectReultByAppIdYDJ(String appId) {
		return getSqlMap().queryForObject(SYSRESULTINFO_NAMESPACES_YDJ + "selectResultByAppId", appId);
	}

	@Override
	public String queryQuestionCount(Map<String, Object> map) {
		return getSqlMap().queryForObject(NAMESPACES + "queryQuestionCountLevel", map);
	}

	@Override
	public String selectCreditDecisionDescByAppIdYDJ(String appId) {
		return getSqlMap().queryForObject(SYSRESULTINFO_NAMESPACES_YDJ + "queryCreditDecisionDescByAppIdYDJ", appId);
		
	}
}