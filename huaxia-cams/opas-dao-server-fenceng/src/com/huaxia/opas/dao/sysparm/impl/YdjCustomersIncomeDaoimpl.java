package com.huaxia.opas.dao.sysparm.impl;

import java.util.ArrayList;
import java.util.List;

import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.sysparm.YdjCustomersIncomeDao;
import com.huaxia.opas.domain.sysparm.CustomersIncome;
/**
 *  易达金 客群策略 职级替代收入
 * @author liyanjie
 *
 */
public class YdjCustomersIncomeDaoimpl extends AbstractDAO implements YdjCustomersIncomeDao{

	private static final long serialVersionUID = -2308449203826742558L;

	private static final String NAMESPACES = "YdjCustomersIncome.";
	/**
	 * 数量统计
	 */
	@Override
	public int queryIncomeCount(CustomersIncome income) {
		return sqlMap.queryForObject(NAMESPACES+"queryIncomeCount", income);
	}
	/**
	 * 分页查询
	 */
	@Override
	public List<CustomersIncome> queryIncome(CustomersIncome income, int curNum, int pageNum) {
		List<CustomersIncome> list = new ArrayList<CustomersIncome>();
		list = sqlMap.queryForList(NAMESPACES+"queryIncome", income, curNum,pageNum);
		return list;
	}
	/**
	 * 添加   启用
	 */
	@Override
	public int insertCustomersIncomeStart(CustomersIncome income) {
		return sqlMap.insert(NAMESPACES+"insertCustomersIncomeStart",income);
	}
	/**
	 * 添加   停用
	 */
	@Override
	public int insertCustomersIncomeEnd(CustomersIncome income) {
		return sqlMap.insert(NAMESPACES+"insertCustomersIncomeEnd",income);
	}
	/**
	 * 批量删除
	 */
	@Override
	public int deleteCustomersIncome(CustomersIncome income) {
		List ids = income.getIds();
		return sqlMap.delete(NAMESPACES+"deleteCustomersIncome", ids);
	}
	/**
	 * 批量启用
	 */
	@Override
	public int setCustomersIncomeStatusOK(CustomersIncome income) {
		return sqlMap.update(NAMESPACES+"setCustomersIncomeStatusOK",income);
	}
	/**
	 * 批量禁用
	 */
	@Override
	public int setCustomersIncomeStatusNO(CustomersIncome income) {
		return sqlMap.update(NAMESPACES+"setCustomersIncomeStatusNO",income);
	}
	/**
	 * 查询数据是否已存在
	 */
	@Override
	public CustomersIncome checkIsExist(CustomersIncome income) {
		return sqlMap.queryForObject(NAMESPACES+"checkIsExist", income);
	}
	@Override
	public String queryCustomersIncomeStatus(String autoId) {
		return sqlMap.queryForObject(NAMESPACES+"queryCustomersIncomeStatus", autoId);
	}
	@Override
	public int updateCustomersIncomeWithoutStatus(CustomersIncome income) {
		return sqlMap.update(NAMESPACES+"updateCustomersIncomeWithoutStatus", income);
	}
	@Override
	public int updateCustomersIncomeAndStatus(CustomersIncome income) {
		return sqlMap.update(NAMESPACES+"updateCustomersIncomeAndStatus", income);
	}
	@Override
	public int insertCustomersIncomeUpload(List<CustomersIncome> list) {
		return sqlMap.update(NAMESPACES+"insertCustomersIncomeUpload", list);
	}

}
