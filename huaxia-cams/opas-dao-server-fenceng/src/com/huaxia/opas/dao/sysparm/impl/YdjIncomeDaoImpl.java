package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.YdjIncomeDao;
import com.huaxia.opas.domain.sysparm.Income;

public class YdjIncomeDaoImpl extends AbstractDAO implements YdjIncomeDao {

	private static final long serialVersionUID = 9203558194539851682L;

	private static final String NAMESPACES = "YdjIncome.";

	@Override
	public int qureyIncomeCount(Income income) {
		return getSqlMap().queryForObject(NAMESPACES + "queryIncomeCount", income);
	}

	@Override
	public List<Income> queryIncome(Income income, int curNum, int pageNum) {
		List<Income> list = new ArrayList<Income>();
		list = getSqlMap().queryForList(NAMESPACES + "queryIncome", income, curNum, pageNum);
		return list;
	}

	@Override
	public int insertIncome(Income income) {
		return getSqlMap().insert(NAMESPACES + "insertIncome", income);
	}

	@Override
	public int updateIncome(Income income) {
		return getSqlMap().update(NAMESPACES + "updateIncome", income);
	}

	@Override
	public int deleteIncome(Income income) {
		List<String> ids = income.getIds();
		return getSqlMap().delete(NAMESPACES + "deleteIncome", ids);
	}


	//上传方法
	@Override
	public void insertIncomeList(List<Income> list) {
		getSqlMap().insert(NAMESPACES + "insertIncomeList", list);
	}

	@Override
	public int setIncomeStatusOK(Income income) throws CoreException {
		return getSqlMap().update(NAMESPACES + "setIncomeStatusOK", income);
	}

	@Override
	public int setIncomeStatusNO(Income income) throws CoreException {
		return getSqlMap().update(NAMESPACES + "setIncomeStatusNO", income);
	}

}
