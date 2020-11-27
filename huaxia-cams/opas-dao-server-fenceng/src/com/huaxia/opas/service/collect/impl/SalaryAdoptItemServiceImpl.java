package com.huaxia.opas.service.collect.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.collect.SalaryAdoptItemDao;
import com.huaxia.opas.domain.collect.SalaryAdoptItem;
import com.huaxia.opas.service.collect.SalaryAdoptItemService;

/**
 * 增强信息收入可采纳项服务实现类
 * 
 * @author zhiguo.li
 *
 */
public class SalaryAdoptItemServiceImpl implements SalaryAdoptItemService, Serializable {

	private static final long serialVersionUID = 4586144714316825396L;
	
	@Resource(name = "salaryAdoptItemDao")
	private SalaryAdoptItemDao salaryAdoptItemDao;

	@Override
	public int save(SalaryAdoptItem salaryAdoptItem) throws CoreException {
		return getSalaryAdoptItemDao().insert(salaryAdoptItem);
	}

	@Override
	public int remove(SalaryAdoptItem salaryAdoptItem) throws CoreException {
		return getSalaryAdoptItemDao().delete(salaryAdoptItem);
	}

	@Override
	public int update(SalaryAdoptItem salaryAdoptItem) throws CoreException {
		return getSalaryAdoptItemDao().update(salaryAdoptItem);
	}

	@Override
	public SalaryAdoptItem get(SalaryAdoptItem salaryAdoptItem) {
		return getSalaryAdoptItemDao().selectById(salaryAdoptItem);
	}

	@Override
	public SalaryAdoptItem getByAppId(String appId) {
		return getSalaryAdoptItemDao().selectByAppId(appId);
	}

	public SalaryAdoptItemDao getSalaryAdoptItemDao() {
		return salaryAdoptItemDao;
	}

	public void setSalaryAdoptItemDao(SalaryAdoptItemDao salaryAdoptItemDao) {
		this.salaryAdoptItemDao = salaryAdoptItemDao;
	}

	public String selectDepositBaseByAppId(String appId) {
		return getSalaryAdoptItemDao().selectDepositBaseByAppId(appId);
	}

	public String selectDepositAmountByAppId(String appId) {
		return getSalaryAdoptItemDao().selectDepositAmountByAppId(appId);
	}

}
