package com.huaxia.opas.dao.collect.impl;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.collect.SalaryComputeDao;
import com.huaxia.opas.domain.collect.SalaryCompute;

/**
 * 增强信息收入计算采集接口实现类
 * 
 * @author zhiguo.li
 *
 */
public class SalaryComputeDaoImpl extends AbstractDAO implements SalaryComputeDao {

	private static final long serialVersionUID = 8989899956543557993L;
	
	private static final String NAMESPACES = "SalaryCompute.";

	@Override
	public int insert(SalaryCompute salaryCompute) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertSalaryCompute", salaryCompute);
	}

	@Override
	public int update(SalaryCompute salaryCompute) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateSalaryCompute", salaryCompute);
	}

	@Override
	public int delete(SalaryCompute salaryCompute) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteSalaryCompute", salaryCompute);
	}

	@Override
	public SalaryCompute selectById(SalaryCompute salaryCompute) {
		return getSqlMap().queryForObject(NAMESPACES + "selectById", salaryCompute);
	}

	@Override
	public SalaryCompute selectByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectByAppId", appId);
	}

}
