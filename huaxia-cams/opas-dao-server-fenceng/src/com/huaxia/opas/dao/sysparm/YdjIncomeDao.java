package com.huaxia.opas.dao.sysparm;
import java.util.List;
import java.util.Map;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.Income;


public interface YdjIncomeDao {

	public int qureyIncomeCount(Income income);
	public List<Income> queryIncome(Income income, int curNum, int pageNum);
	public int insertIncome(Income income) throws CoreException;
	public int updateIncome(Income income) throws CoreException;
	public int deleteIncome(Income income) throws CoreException;
	
	//上传
	public void insertIncomeList(List<Income> list);
	public int setIncomeStatusOK(Income income) throws CoreException;
	public int setIncomeStatusNO(Income income) throws CoreException;
	

}
