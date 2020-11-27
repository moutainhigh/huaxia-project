package com.huaxia.opas.dao.sysparm;

import java.util.List;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.sysparm.Balance;

public interface BalanceDao {

	public int insertBalance(Balance balance) throws CoreException;

	public int updateBalance(Balance balance) throws CoreException;

	public int deleteBalance(Balance balance) throws CoreException;

	public int queryBalanceCount(Balance balance);

	public List<Balance> queryBalance(Balance balance, int curNum, int pageNum);

	public int setBalanceStatusOK(Balance balance) throws CoreException;

	public int setBalanceStatusNO(Balance balance) throws CoreException;
	//根据id查询数据
	public Balance findByid(String id);
	//修改的方法
	public int updateBalance2(Balance balance);

	public int queryBalanceCountByProCode(String prodCode);

}
