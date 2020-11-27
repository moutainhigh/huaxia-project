package com.huaxia.opas.service.sysparm.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.sysparm.YdjCustomersIncomeDao;
import com.huaxia.opas.domain.sysparm.CustomersIncome;
import com.huaxia.opas.service.AbstractService;
import com.huaxia.opas.service.sysparm.YdjCustomersIncomeService;
/**
 * 易达金 客群策略 职级替代收入 serviceImpl
 * @author liyanjie
 *
 */
public class YdjCustomersIncomeServiceImpl extends AbstractService implements YdjCustomersIncomeService,Serializable{

	private static final long serialVersionUID = -6001811659170101139L;

	@Resource
	private YdjCustomersIncomeDao ydjCustomersIncomeDao;
	/**
	 * 查询符合条件的数量
	 */
	@Override
	public int queryIncomeCount(CustomersIncome income) throws CoreException {
		return ydjCustomersIncomeDao.queryIncomeCount(income);
	}
	/**
	 * 分页查询
	 */
	@Override
	public List<CustomersIncome> queryIncomeList(CustomersIncome income, int curNum, int pageNum) throws CoreException {
		return ydjCustomersIncomeDao.queryIncome(income,curNum,pageNum);
	}
	/**
	 * 添加   启用状态
	 */
	@Override
	public int insertCustomersIncomeStart(CustomersIncome income) throws CoreException {
		return ydjCustomersIncomeDao.insertCustomersIncomeStart(income);
	}
	/**
	 * 添加 停用状态
	 */
	@Override
	public int insertCustomersIncomeEnd(CustomersIncome income) throws CoreException {
		return ydjCustomersIncomeDao.insertCustomersIncomeEnd(income);
	}
	/**
	 * 删除 
	 */
	@Override
	public int deleteCustomersIncome(CustomersIncome income) throws CoreException {
		return ydjCustomersIncomeDao.deleteCustomersIncome(income);
	}
	/**
	 * 批量启用
	 */
	@Override
	public int setCustomersIncomeStatusOK(CustomersIncome income) throws CoreException {
		return ydjCustomersIncomeDao.setCustomersIncomeStatusOK(income);
	}
	/**
	 * 批量禁用
	 */
	@Override
	public int setCustomersIncomeStatusNO(CustomersIncome income) throws CoreException {
		return ydjCustomersIncomeDao.setCustomersIncomeStatusNO(income);
	}
	/**
	 * 查看是否已存在
	 */
	@Override
	public CustomersIncome checkIsExist(CustomersIncome income) throws CoreException {
		return ydjCustomersIncomeDao.checkIsExist(income);
	}
	/**
	 * 查询状态
	 */
	@Override
	public String queryCustomersIncomeStatus(String autoId) throws CoreException {
		return ydjCustomersIncomeDao.queryCustomersIncomeStatus(autoId);
	}
	/**
	 * 修改  状态不变
	 */
	@Override
	public int updateCustomersIncomeWithoutStatus(CustomersIncome income) throws CoreException {
		return ydjCustomersIncomeDao.updateCustomersIncomeWithoutStatus(income);
	}
	@Override
	public int updateCustomersIncomeAndStatus(CustomersIncome income) throws CoreException {
		return  ydjCustomersIncomeDao.updateCustomersIncomeAndStatus(income);
	}
	/**
	 * 批量导入 
	 */
	@Override
	public int insertCustomersIncomeUpload(List<CustomersIncome> list) throws CoreException {
		return ydjCustomersIncomeDao.insertCustomersIncomeUpload(list);
	}

}
