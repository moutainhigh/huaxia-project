package com.huaxia.opas.dao.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.Income;

public interface BzkIncomeDao {

	public int qureyIncomeCount(Income income) throws CoreException;

	public List<Income> queryIncome(Income income, int curNum, int pageNum) throws CoreException;

	public int insertIncome(Income income) throws CoreException;

	public int updateIncome(Income income) throws CoreException;

	public int deleteIncome(Income income) throws CoreException;

	// 上传
	public int insertIncomeList(List<Income> list);

	public int setIncomeStatusOK(Income income) throws CoreException;

	public int setIncomeStatusNO(Income income) throws CoreException;
}
