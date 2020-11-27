package com.huaxia.opas.dao.collect.impl;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.collect.SalaryAdoptItemDao;
import com.huaxia.opas.domain.collect.SalaryAdoptItem;

/**
 * 增强信息收入计算采集接口实现类
 * 
 * @author zhiguo.li
 *
 */
public class SalaryAdoptItemDaoImpl extends AbstractDAO implements SalaryAdoptItemDao {

	private static final long serialVersionUID = 8989899956543557993L;
	
	private static final String NAMESPACES = "SalaryAdoptItem.";

	@Override
	public int insert(SalaryAdoptItem salaryAdoptItem) throws CoreException {
		return getSqlMap().insert(NAMESPACES + "insertSalaryAdoptItem", salaryAdoptItem);
	}

	@Override
	public int update(SalaryAdoptItem salaryAdoptItem) throws CoreException {
		return getSqlMap().update(NAMESPACES + "updateSalaryAdoptItem", salaryAdoptItem);
	}

	@Override
	public int delete(SalaryAdoptItem salaryAdoptItem) throws CoreException {
		return getSqlMap().delete(NAMESPACES + "deleteById", salaryAdoptItem);
	}

	@Override
	public SalaryAdoptItem selectById(SalaryAdoptItem salaryAdoptItem) {
		return getSqlMap().queryForObject(NAMESPACES + "selectById", salaryAdoptItem);
	}

	@Override
	public SalaryAdoptItem selectByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES + "selectByAppId", appId);
	}

	@Override
	public int deleteByAppId(String appId) {
		return getSqlMap().delete(NAMESPACES + "deleteByAppId", appId);
	}

	@Override
	public String selectDepositBaseByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectDepositBaseByAppId",appId);
	}

	@Override
	public String selectDepositAmountByAppId(String appId) {
		return getSqlMap().queryForObject(NAMESPACES+"selectDepositAmountByAppId", appId);
	}
}
