package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.ConfQuestionDao;
import com.huaxia.opas.domain.sysparm.ConfQuestion;

public class ConfQuestionDaoImpl extends AbstractDAO implements ConfQuestionDao{

	private static final long serialVersionUID = 1845847142439045630L;

	private static final String NAMESPACES = "ConfQuestion.";
	@Override
	public int insertConfQuestion(ConfQuestion confQuestion) {
		return getSqlMap().insert(NAMESPACES + "insertConfQuestion",confQuestion);
	}
	
	@Override
	public int updateConfQuestion(ConfQuestion confQuestion) {
		return getSqlMap().update(NAMESPACES + "updateConfQuestion", confQuestion);
	}

	@Override
	public List<ConfQuestion> queryConfQuestionList(ConfQuestion confQuestion, int curNum, int pageNum) {
		List<ConfQuestion> list = new ArrayList<ConfQuestion>();
		list = getSqlMap().queryForList(NAMESPACES + "queryConfQuestionForList", confQuestion,curNum,pageNum);
		return list;
	}

	@Override
	public int queryConfQuestionCount(ConfQuestion confQuestion) {
		return getSqlMap().queryForObject(NAMESPACES + "queryConfQuestionCount",confQuestion);
	}

	@Override
	public int deleteConfQuestion(ConfQuestion confQuestion) {
		List ids = confQuestion.getIds();
		return getSqlMap().delete(NAMESPACES + "deleteConfQuestion",ids); 
	}

	@Override
	public int confQuestionsetStatusOK(ConfQuestion confQuestion) {
		return getSqlMap().update(NAMESPACES + "confQuestionsetStatusOK",confQuestion);
	}

	@Override
	public int confQuestionsetStatusNO(ConfQuestion confQuestion) {
		return getSqlMap().update(NAMESPACES + "confQuestionsetStatusNO",confQuestion);
	}

	@Override
	public int insertConfQuestion(List<ConfQuestion> list) {
		return getSqlMap().insert(NAMESPACES + "insertConfQuestionList", list);
	}

	//历史查询
	@Override
	public int queryConfQuestionHistoryCount(ConfQuestion confQuestion) throws CoreException {
		return getSqlMap().queryForObject(NAMESPACES + "queryConfQuestionHistoryCount", confQuestion);
	}

	@Override
	public List<ConfQuestion> queryConfQuestionHistoryList(ConfQuestion confQuestion, int curNum, int pageNum) {
		return getSqlMap().queryForList(NAMESPACES + "queryConfQuestionHistoryList", confQuestion, curNum, pageNum);
	}

	//修改历史表
	@Override
	public int insertupdateHistory(List<ConfQuestion> list) throws CoreException{
		return getSqlMap().insert(NAMESPACES + "insertConfQuestionHistoryList", list);		
	}

	//查询修改部分
	@Override
	public List<ConfQuestion> queryUpdateConfQuestion(ConfQuestion confQuestion, int i, int j) {
		return getSqlMap().queryForList(NAMESPACES + "queryUpdateConfQuestion",confQuestion,i,j);
	}

}
