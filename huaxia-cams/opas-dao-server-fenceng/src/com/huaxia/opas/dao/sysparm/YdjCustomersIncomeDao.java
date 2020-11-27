package com.huaxia.opas.dao.sysparm;

import java.util.List;

import com.huaxia.opas.domain.sysparm.CustomersIncome;

/**
 * 易达金客群策略 职级替代收入
 * @author liyanjie Dao
 *
 */
public interface YdjCustomersIncomeDao {

	int queryIncomeCount(CustomersIncome income);

	List<CustomersIncome> queryIncome(CustomersIncome income, int curNum, int pageNum);

	int insertCustomersIncomeStart(CustomersIncome income);
	int insertCustomersIncomeEnd(CustomersIncome income);

	int deleteCustomersIncome(CustomersIncome income);

	int setCustomersIncomeStatusOK(CustomersIncome income);

	int setCustomersIncomeStatusNO(CustomersIncome income);

	CustomersIncome checkIsExist(CustomersIncome income);

	String queryCustomersIncomeStatus(String autoId);

	int updateCustomersIncomeWithoutStatus(CustomersIncome income);

	int updateCustomersIncomeAndStatus(CustomersIncome income);

	int insertCustomersIncomeUpload(List<CustomersIncome> list);

}
