package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.dict.ApDictDetailDao;
import com.huaxia.opas.dao.sysparm.BzkIncomeDao;
import com.huaxia.opas.domain.sysparm.Income;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.BzkIncomeService;


public class BzkIncomeServiceImpl extends AbstractService implements BzkIncomeService,Serializable{

	private static final long serialVersionUID = -5065580577681811254L;

	@Resource(name = "bzkIncomeDao")
	private  BzkIncomeDao bzkIncomeDao;
	
	//完成数据字典的验证
	@Resource(name = "apDictDetailDao")
	private  ApDictDetailDao apDictDetailDao;

	/**
	 * @return the bzkIncomeDao
	 */
	public BzkIncomeDao getBzkIncomeDao() {
		return bzkIncomeDao;
	}

	/**
	 * @param bzkIncomeDao the bzkIncomeDao to set
	 */
	public void setBzkIncomeDao(BzkIncomeDao bzkIncomeDao) {
		this.bzkIncomeDao = bzkIncomeDao;
	}

	@Override
	public int queryIncomeCount(Income income) throws CoreException {
		return getBzkIncomeDao().qureyIncomeCount(income);
	}

	@Override
	public List<Income> queryIncomeList(Income income, int curNum, int pageNum) throws CoreException {
		return getBzkIncomeDao().queryIncome(income, curNum, pageNum);
	}

	@Override
	public int insertIncome(Income income) throws CoreException {
		return getBzkIncomeDao().insertIncome(income);
	}

	@Override
	public int updateIncome(Income income) throws CoreException {
		return getBzkIncomeDao().updateIncome(income);
	}

	@Override
	public int deleteIncome(Income income) throws CoreException {
		return getBzkIncomeDao().deleteIncome(income);
	}


	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	@Override
	public int setIncomeStatusOK(Income income) throws CoreException {
		return getBzkIncomeDao().setIncomeStatusOK(income);
	}

	@Override
	public int setIncomeStatusNO(Income income) throws CoreException {
		return getBzkIncomeDao().setIncomeStatusNO(income);
	}

	@Override
	public int insertIncomeList(List<Income> list) {
		return getBzkIncomeDao().insertIncomeList(list);
	}

}
