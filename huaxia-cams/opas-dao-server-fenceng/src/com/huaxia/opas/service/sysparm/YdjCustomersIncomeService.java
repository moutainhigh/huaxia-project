package com.huaxia.opas.service.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.CustomersIncome;

/**
 * 客群策略 职级替代收入 service
 * @author liyanjie
 *
 */
public interface YdjCustomersIncomeService {
	/**
	 * 查询符合条件的数据条数
	 * @param income
	 * @return
	 * @throws CoreException
	 */
	public int queryIncomeCount(CustomersIncome income) throws CoreException;
	/**
	 * 分页查询
	 * @param income
	 * @param curNum
	 * @param pageNum
	 * @return
	 * @throws CoreException 
	 */
	public List<CustomersIncome> queryIncomeList(CustomersIncome income, int curNum, int pageNum) throws CoreException;
	/**
	 * 添加 客群细分 职级替代收入  启用状态
	 * @param income
	 */
	public int insertCustomersIncomeStart(CustomersIncome income) throws CoreException;
	/**
	 * 添加 客群细分 职级替代收入 停用状态
	 * @param income
	 */
	public int insertCustomersIncomeEnd(CustomersIncome income) throws CoreException;
	/**
	 * 删除
	 * @param income
	 * @return
	 */
	public int deleteCustomersIncome(CustomersIncome income) throws CoreException;
	/**
	 * 批量启用
	 * @param income
	 * @return
	 */
	public int setCustomersIncomeStatusOK(CustomersIncome income) throws CoreException;
	/**
	 * 批量禁用
	 * @param income
	 * @return
	 */
	public int setCustomersIncomeStatusNO(CustomersIncome income) throws CoreException;
	/**
	 * 查看是否已存在
	 * @param income
	 * @return
	 */
	public CustomersIncome checkIsExist(CustomersIncome income) throws CoreException;
	public String queryCustomersIncomeStatus(String autoId) throws CoreException;
	/**
	 * 修改 状态不变
	 * @param income
	 * @return
	 * @throws CoreException
	 */
	public int updateCustomersIncomeWithoutStatus(CustomersIncome income) throws CoreException;
	public int updateCustomersIncomeAndStatus(CustomersIncome income) throws CoreException;
	/**
	 * 批量导入 插入
	 * @param list
	 * @return
	 * @throws CoreException
	 */
	public int insertCustomersIncomeUpload(List<CustomersIncome> list) throws CoreException;
}
