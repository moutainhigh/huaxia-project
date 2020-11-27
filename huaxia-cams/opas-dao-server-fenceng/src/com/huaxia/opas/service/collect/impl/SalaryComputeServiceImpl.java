package com.huaxia.opas.service.collect.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.collect.SalaryComputeDao;
import com.huaxia.opas.domain.collect.SalaryCompute;
import com.huaxia.opas.service.collect.SalaryComputeService;

/**
 * 增强信息收入计算采集服务实现类
 * 
 * @author zhiguo.li
 *
 */
public class SalaryComputeServiceImpl implements SalaryComputeService , Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2931306700378567617L;
	@Resource(name = "salaryComputeDao")
	private SalaryComputeDao salaryComputeDao;

	@Override
	public int save(SalaryCompute salaryCompute) throws CoreException {
		return getSalaryComputeDao().insert(salaryCompute);
	}

	@Override
	public int remove(SalaryCompute salaryCompute) throws CoreException {
		return getSalaryComputeDao().delete(salaryCompute);
	}

	@Override
	public int update(SalaryCompute salaryCompute) throws CoreException {
		return getSalaryComputeDao().update(salaryCompute);
	}

	@Override
	public SalaryCompute get(SalaryCompute salaryCompute) {
		return getSalaryComputeDao().selectById(salaryCompute);
	}

	@Override
	public SalaryCompute getByAppId(String appId) {
		return getSalaryComputeDao().selectByAppId(appId);
	}

	public SalaryComputeDao getSalaryComputeDao() {
		return salaryComputeDao;
	}

	public void setSalaryComputeDao(SalaryComputeDao salaryComputeDao) {
		this.salaryComputeDao = salaryComputeDao;
	}

}
