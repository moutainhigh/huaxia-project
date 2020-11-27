package com.huaxia.opas.service.sysparm;

import java.io.File;
import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.Income;

public interface YdjIncomeService {

	// 查询
	public int queryIncomeCount(Income income);

	public List<Income> queryIncomeList(Income income, int curNum, int pageNum);

	// 添加
	public int insertIncome(Income income) throws CoreException;

	// 修改
	public int updateIncome(Income income) throws CoreException;

	// 删除
	public int deleteIncome(Income income) throws CoreException;

	//上传方法
	public void insertIncomeFromFile(File file, String operator);

	public int setIncomeStatusOK(Income income) throws CoreException;

	public int setIncomeStatusNO(Income income) throws CoreException;

}
