package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.Income;

public interface BzkIncomeService {

	// 查询
	public int queryIncomeCount(Income income) throws CoreException;

	public List<Income> queryIncomeList(Income income, int curNum, int pageNum) throws CoreException;

	// 添加
	public int insertIncome(Income income) throws CoreException;

	// 修改
	public int updateIncome(Income income) throws CoreException;

	// 删除
	public int deleteIncome(Income income) throws CoreException;


	public int setIncomeStatusOK(Income income) throws CoreException;

	public int setIncomeStatusNO(Income income) throws CoreException;

	public int insertIncomeList(List<Income> list);
	
}
