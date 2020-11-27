package com.huaxia.opas.dao.decision;

import java.util.List;
import java.util.Map;

import com.huaxia.opas.domain.thirdparty.OpasConfQuestion;

public interface OpasConfQuestionDao {
    int deleteByPrimaryKey(String autoId);

    int insert(OpasConfQuestion record);

    int insertSelective(OpasConfQuestion record);

    OpasConfQuestion selectByPrimaryKey(String autoId);

    int updateByPrimaryKeySelective(OpasConfQuestion record);

    int updateByPrimaryKey(OpasConfQuestion record);

	List<OpasConfQuestion> queryConfQuestion(String rownum);

	int queryConfQuestionCount();
	
	List<OpasConfQuestion> queryConfQuestion(List<String> list);

	String selectReultByAppId(String appId);

	String queryConfQuestionCount(List<String> questionLevel);

	String selectReultByAppIdYDJ(String appId);

	String queryQuestionCount(Map<String, Object> map);

	String selectCreditDecisionDescByAppIdYDJ(String appId);
}